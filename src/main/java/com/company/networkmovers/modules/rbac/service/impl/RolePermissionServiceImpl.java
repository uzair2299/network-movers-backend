package com.company.networkmovers.modules.rbac.service.impl;

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

import java.util.List;
import java.util.UUID;
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
