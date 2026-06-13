package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.dto.request.MoveStatusWorkflowRequest;
import com.company.networkmovers.modules.property.dto.request.MoveStatusNodeRequest;
import com.company.networkmovers.modules.property.dto.request.MoveStatusTransitionRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusWorkflowResponse;
import com.company.networkmovers.modules.property.dto.response.MoveStatusNodeResponse;
import com.company.networkmovers.modules.property.dto.response.MoveStatusTransitionResponse;
import com.company.networkmovers.modules.property.entity.MoveStatus;
import com.company.networkmovers.modules.property.entity.MoveStatusLayout;
import com.company.networkmovers.modules.property.entity.MoveStatusTransition;
import com.company.networkmovers.modules.property.repository.MoveStatusRepository;
import com.company.networkmovers.modules.property.repository.MoveStatusLayoutRepository;
import com.company.networkmovers.modules.property.repository.MoveStatusTransitionRepository;
import com.company.networkmovers.modules.property.service.MoveStatusWorkflowService;
import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.modules.rbac.repository.RoleRepository;
import com.company.networkmovers.security.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoveStatusWorkflowServiceImpl implements MoveStatusWorkflowService {

    private final MoveStatusRepository moveStatusRepository;
    private final MoveStatusLayoutRepository layoutRepository;
    private final MoveStatusTransitionRepository transitionRepository;
    private final RoleRepository roleRepository;

    public MoveStatusWorkflowServiceImpl(MoveStatusRepository moveStatusRepository,
                                         MoveStatusLayoutRepository layoutRepository,
                                         MoveStatusTransitionRepository transitionRepository,
                                         RoleRepository roleRepository) {
        this.moveStatusRepository = moveStatusRepository;
        this.layoutRepository = layoutRepository;
        this.transitionRepository = transitionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public MoveStatusWorkflowResponse saveWorkflow(MoveStatusWorkflowRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();

        // 1. Save or Update Layout Coordinates (nodes)
        if (request.getNodes() != null) {
            for (MoveStatusNodeRequest nodeReq : request.getNodes()) {
                if (nodeReq.getId() == null) {
                    throw new RuntimeException("Node status ID cannot be null");
                }
                MoveStatus status = moveStatusRepository.findById(nodeReq.getId())
                        .orElseThrow(() -> new RuntimeException("MoveStatus not found with ID: " + nodeReq.getId()));

                MoveStatusLayout layout = layoutRepository.findActiveByStatusId(status.getId())
                        .orElseGet(() -> MoveStatusLayout.builder()
                                .status(status)
                                .build());

                layout.setPositionX(nodeReq.getX());
                layout.setPositionY(nodeReq.getY());
                
                if (layout.isDeleted()) {
                    layout.restore();
                }
                layoutRepository.save(layout);
            }
        }

        // 2. Sync Transitions
        List<MoveStatusTransition> existingTransitions = transitionRepository.findAllActiveTransitions();
        Map<UUID, MoveStatusTransition> existingMap = existingTransitions.stream()
                .collect(Collectors.toMap(MoveStatusTransition::getId, t -> t));

        List<MoveStatusTransitionRequest> requestedTransitions = request.getTransitions() != null 
                ? request.getTransitions() : Collections.emptyList();

        Set<UUID> requestedIds = requestedTransitions.stream()
                .map(MoveStatusTransitionRequest::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Soft-delete transitions not in the request
        for (MoveStatusTransition existing : existingTransitions) {
            if (!requestedIds.contains(existing.getId())) {
                existing.delete(currentUserId);
                transitionRepository.save(existing);
            }
        }

        // Save or update requested transitions
        for (MoveStatusTransitionRequest reqTrans : requestedTransitions) {
            MoveStatusTransition transitionEntity;

            if (reqTrans.getId() != null && existingMap.containsKey(reqTrans.getId())) {
                transitionEntity = existingMap.get(reqTrans.getId());
            } else {
                transitionEntity = new MoveStatusTransition();
                if (reqTrans.getId() != null) {
                    transitionEntity.setId(reqTrans.getId());
                } else {
                    transitionEntity.setId(UUID.randomUUID());
                }
            }

            MoveStatus fromStatus = moveStatusRepository.findById(reqTrans.getFromStatusId())
                    .orElseThrow(() -> new RuntimeException("FromStatus not found with ID: " + reqTrans.getFromStatusId()));
            MoveStatus toStatus = moveStatusRepository.findById(reqTrans.getToStatusId())
                    .orElseThrow(() -> new RuntimeException("ToStatus not found with ID: " + reqTrans.getToStatusId()));

            transitionEntity.setFromStatus(fromStatus);
            transitionEntity.setToStatus(toStatus);
            transitionEntity.setTransitionName(reqTrans.getTransitionName());
            transitionEntity.setRequiresApproval(reqTrans.getRequiresApproval() != null && reqTrans.getRequiresApproval());
            transitionEntity.setCustomerVisible(reqTrans.getCustomerVisible() == null || reqTrans.getCustomerVisible());
            transitionEntity.setActive(reqTrans.getActive() == null || reqTrans.getActive());

            if (reqTrans.getAllowedRoleId() != null) {
                Role role = roleRepository.findById(reqTrans.getAllowedRoleId())
                        .orElseThrow(() -> new RuntimeException("Role not found with ID: " + reqTrans.getAllowedRoleId()));
                transitionEntity.setAllowedRole(role);
            } else {
                transitionEntity.setAllowedRole(null);
            }

            if (transitionEntity.isDeleted()) {
                transitionEntity.restore();
            }

            transitionRepository.save(transitionEntity);
        }

        return getWorkflow();
    }

    @Override
    @Transactional(readOnly = true)
    public MoveStatusWorkflowResponse getWorkflow() {
        // Fetch all statuses
        List<MoveStatus> statuses = moveStatusRepository.findAll();
        
        // Fetch all layouts
        List<MoveStatusLayout> layouts = layoutRepository.findAll();
        Map<UUID, MoveStatusLayout> layoutMap = layouts.stream()
                .filter(l -> !l.isDeleted())
                .collect(Collectors.toMap(l -> l.getStatus().getId(), l -> l, (l1, l2) -> l1));

        List<MoveStatusNodeResponse> nodeResponses = statuses.stream()
                .map(s -> {
                    MoveStatusLayout layout = layoutMap.get(s.getId());
                    return MoveStatusNodeResponse.builder()
                            .id(s.getId())
                            .x(layout != null ? layout.getPositionX() : null)
                            .y(layout != null ? layout.getPositionY() : null)
                            .build();
                })
                .collect(Collectors.toList());

        // Fetch all active transitions
        List<MoveStatusTransition> transitions = transitionRepository.findAllActiveTransitions();

        List<MoveStatusTransitionResponse> transitionResponses = transitions.stream()
                .map(t -> MoveStatusTransitionResponse.builder()
                        .id(t.getId())
                        .fromStatusId(t.getFromStatus().getId())
                        .toStatusId(t.getToStatus().getId())
                        .transitionName(t.getTransitionName())
                        .allowedRoleId(t.getAllowedRole() != null ? t.getAllowedRole().getId() : null)
                        .requiresApproval(t.isRequiresApproval())
                        .customerVisible(t.isCustomerVisible())
                        .active(t.isActive())
                        .build())
                .collect(Collectors.toList());

        return MoveStatusWorkflowResponse.builder()
                .nodes(nodeResponses)
                .transitions(transitionResponses)
                .build();
    }
}
