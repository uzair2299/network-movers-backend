package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.modules.rbac.repository.RoleRepository;
import com.company.networkmovers.modules.rbac.entity.UserRole;
import com.company.networkmovers.modules.rbac.repository.UserRoleRepository;
import com.company.networkmovers.modules.rbac.dto.request.BulkUserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.modules.rbac.mapper.UserRoleMapper;
import com.company.networkmovers.modules.rbac.service.UserRoleService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("modulesUserRoleServiceImpl")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, 
                               RoleRepository roleRepository,
                               @Qualifier("modulesUserRoleMapper") UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserRoleResponse assignRole(UserRoleRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + request.getRoleId()));

        UserRole userRole = userRoleMapper.toEntity(request);
        userRole.setRole(role);

        UserRole saved = userRoleRepository.save(userRole);
        return userRoleMapper.toResponse(saved);
    }

    @Override
    public List<UserRoleResponse> updateRolesBulk(BulkUserRoleRequest request) {
        UUID userId = request.getUserId();
        List<UUID> targetRoleIds = request.getRoleIds() != null ? request.getRoleIds() : new ArrayList<>();

        /*
         * Implement delta reconciliation:
         * Retrieve all current UserRole mappings for userId.
         * Compute differences:
         * rolesToRemove: existing mappings whose role ID is not in requested roleIds.
         * roleIdsToAdd: requested roleIds not present in current user roles.
         * Delete rolesToRemove records.
         * Create and save new UserRole mappings for roleIdsToAdd.
         * Fetch and return the updated list of UserRoleResponse objects.
         */

        // Retrieve all current UserRole mappings for userId.
        List<UserRole> existingUserRoles = userRoleRepository.findByUserId(userId);

        // Compute differences:
        // rolesToRemove: existing mappings whose role ID is not in requested roleIds.
        List<UserRole> rolesToRemove = existingUserRoles.stream()
                .filter(ur -> !targetRoleIds.contains(ur.getRole().getId()))
                .collect(Collectors.toList());

        // roleIdsToAdd: requested roleIds not present in current user roles.
        List<UUID> existingRoleIds = existingUserRoles.stream()
                .map(ur -> ur.getRole().getId())
                .collect(Collectors.toList());

        List<UUID> roleIdsToAdd = targetRoleIds.stream()
                .filter(id -> !existingRoleIds.contains(id))
                .collect(Collectors.toList());

        // Delete rolesToRemove records.
        if (!rolesToRemove.isEmpty()) {
            userRoleRepository.deleteAll(rolesToRemove);
        }

        // Create and save new UserRole mappings for roleIdsToAdd.
        List<UserRole> newMappings = new ArrayList<>();
        for (UUID roleId : roleIdsToAdd) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
            
            UserRole userRole = UserRole.builder()
                    .userId(userId)
                    .role(role)
                    .build();
            newMappings.add(userRole);
        }
        if (!newMappings.isEmpty()) {
            userRoleRepository.saveAll(newMappings);
        }

        // Fetch and return the updated list of UserRoleResponse objects.
        return userRoleRepository.findByUserId(userId).stream()
                .map(userRoleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void unassignRole(UUID id) {
        if (!userRoleRepository.existsById(id)) {
            throw new RuntimeException("UserRole mapping not found with ID: " + id);
        }
        userRoleRepository.deleteById(id);
    }

    @Override
    public void unassignRolesBulk(List<UUID> ids) {
        userRoleRepository.deleteAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoleResponse> getRolesForUser(UUID userId) {
        return userRoleRepository.findByUserId(userId).stream()
                .map(userRoleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoleResponse> getUsersForRole(UUID roleId) {
        return userRoleRepository.findByRoleId(roleId).stream()
                .map(userRoleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserRoleResponse> getAll(RequestParamDto requestParams) {
        String[] sortParams = requestParams.getSort().split(",");
        String sortField = sortParams[0];
        // Special case: mapping fields of UserRole Response
        if ("role".equalsIgnoreCase(sortField)) {
            sortField = "role.name";
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(
                requestParams.getPage(),
                requestParams.getSize() > 0 ? requestParams.getSize() : 10,
                Sort.by(direction, sortField)
        );
        String search = requestParams.getSearch();
        Page<UserRole> entityPage;
        if (search == null || search.trim().isEmpty()) {
            entityPage = userRoleRepository.findAll(pageable);
        } else {
            entityPage = userRoleRepository.findBySearch(search.trim(), pageable);
        }
        return entityPage.map(userRoleMapper::toResponse);
    }
}
