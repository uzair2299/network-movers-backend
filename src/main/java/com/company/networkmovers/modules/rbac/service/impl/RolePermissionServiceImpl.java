package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.dto.request.BulkRolePermissionRequest;
import com.company.networkmovers.modules.rbac.dto.request.RolePermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.RolePermissionResponse;
import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.modules.rbac.entity.RolePermission;
import com.company.networkmovers.modules.rbac.repository.PermissionRepository;
import com.company.networkmovers.modules.rbac.repository.RolePermissionRepository;
import com.company.networkmovers.modules.rbac.repository.RoleRepository;
import com.company.networkmovers.modules.rbac.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("modulesRolePermissionServiceImpl")
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionServiceImpl(
            @Qualifier("modulesRolePermissionRepository") RolePermissionRepository rolePermissionRepository,
            @Qualifier("modulesRoleRepository") RoleRepository roleRepository,
            @Qualifier("modulesPermissionRepository") PermissionRepository permissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RolePermissionResponse assign(RolePermissionRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + request.getRoleId()));

        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new IllegalArgumentException("Permission not found: " + request.getPermissionId()));

        // Check for existing non-deleted assignment
        rolePermissionRepository.findByRoleIdAndPermissionId(request.getRoleId(), request.getPermissionId())
                .ifPresent(existing -> {
                    throw new IllegalStateException("Permission '" + permission.getName() + "' is already assigned to role '" + role.getName() + "'.");
                });

        RolePermission entity = RolePermission.builder()
                .role(role)
                .permission(permission)
                .active(request.isActive())
                .build();

        return toResponse(rolePermissionRepository.save(entity));
    }

    @Override
    @Transactional
    public List<RolePermissionResponse> assignBulk(BulkRolePermissionRequest request) {
        if (request.getRoleId() == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + request.getRoleId()));

        List<UUID> reqPermissionIds = request.getPermissionIds() != null ? request.getPermissionIds() : Collections.emptyList();

        // 1. Fetch all active permissions in bulk
        List<Permission> activePermissions = reqPermissionIds.isEmpty() ? Collections.emptyList()
                : permissionRepository.findAllActiveByIds(reqPermissionIds);

        // Validate all requested permission IDs are valid active permissions
        if (activePermissions.size() != reqPermissionIds.size()) {
            Set<UUID> foundIds = activePermissions.stream().map(Permission::getId).collect(Collectors.toSet());
            List<UUID> missingIds = reqPermissionIds.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toList());
            throw new IllegalArgumentException("Permissions not found or inactive with IDs: " + missingIds);
        }

        Map<UUID, Permission> permissionMap = activePermissions.stream()
                .collect(Collectors.toMap(Permission::getId, p -> p));

        // 2. Fetch all existing mappings for the role (including soft-deleted ones) in a single optimized query
        List<RolePermission> existingMappings = rolePermissionRepository.findByRoleIdIncludingDeleted(request.getRoleId());

        Map<UUID, RolePermission> mappingByPermissionId = existingMappings.stream()
                .collect(Collectors.toMap(rp -> rp.getPermission().getId(), rp -> rp, (rp1, rp2) -> rp1));

        Set<UUID> requestedIdsSet = new HashSet<>(reqPermissionIds);

        // 3. Identify and soft-delete active assignments NOT in the request
        for (RolePermission existing : existingMappings) {
            if (!existing.isDeleted() && !requestedIdsSet.contains(existing.getPermission().getId())) {
                existing.delete(null); // Soft-delete
                existing.setActive(false);
                rolePermissionRepository.save(existing);
            }
        }

        List<RolePermission> activeResults = new ArrayList<>();

        // 4. Save or update requested assignments
        for (UUID permId : reqPermissionIds) {
            RolePermission mapping = mappingByPermissionId.get(permId);
            Permission permission = permissionMap.get(permId);

            if (mapping == null) {
                // Create new assignment
                mapping = RolePermission.builder()
                        .role(role)
                        .permission(permission)
                        .active(true)
                        .build();
            } else {
                // Restore or activate existing
                if (mapping.isDeleted()) {
                    mapping.restore();
                }
                mapping.setActive(true);
            }
            activeResults.add(rolePermissionRepository.save(mapping));
        }

        return activeResults.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void revoke(UUID id) {
        RolePermission entity = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RolePermission not found: " + id));
        entity.delete(null); // soft delete
        entity.setActive(false);
        rolePermissionRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public RolePermissionResponse getById(UUID id) {
        return rolePermissionRepository.findById(id)
                .filter(rp -> !rp.isDeleted())
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("RolePermission not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RolePermissionResponse> getAll(Pageable pageable) {
        return rolePermissionRepository.findAll(pageable)
                .map(rp -> rp.isDeleted() ? null : toResponse(rp))
                .map(r -> r); // filter nulls handled via stream below
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionResponse> getByRoleId(UUID roleId) {
        return rolePermissionRepository.findByRoleId(roleId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionResponse> getByPermissionId(UUID permissionId) {
        return rolePermissionRepository.findByPermissionId(permissionId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // -------------------------------------------------------------------------

    private RolePermissionResponse toResponse(RolePermission entity) {
        return RolePermissionResponse.builder()
                .id(entity.getId())
                .roleId(entity.getRole() != null ? entity.getRole().getId() : null)
                .roleName(entity.getRole() != null ? entity.getRole().getName() : null)
                .roleCode(entity.getRole() != null ? entity.getRole().getCode() : null)
                .permissionId(entity.getPermission() != null ? entity.getPermission().getId() : null)
                .permissionName(entity.getPermission() != null ? entity.getPermission().getName() : null)
                .permissionCode(entity.getPermission() != null ? entity.getPermission().getCode() : null)
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
