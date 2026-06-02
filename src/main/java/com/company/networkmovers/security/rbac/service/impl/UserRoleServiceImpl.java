package com.company.networkmovers.security.rbac.service.impl;

import com.company.networkmovers.security.rbac.Role;
import com.company.networkmovers.security.rbac.RoleRepository;
import com.company.networkmovers.security.rbac.UserRole;
import com.company.networkmovers.security.rbac.UserRoleRepository;
import com.company.networkmovers.security.rbac.dto.request.BulkUserRoleRequest;
import com.company.networkmovers.security.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.security.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.security.rbac.mapper.UserRoleMapper;
import com.company.networkmovers.security.rbac.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, 
                               RoleRepository roleRepository,
                               UserRoleMapper userRoleMapper) {
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
    public List<UserRoleResponse> assignRolesBulk(BulkUserRoleRequest request) {
        List<UserRole> mappingsToSave = new ArrayList<>();

        for (UUID roleId : request.getRoleIds()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
            
            UserRole userRole = UserRole.builder()
                    .userId(request.getUserId())
                    .role(role)
                    .build();
            mappingsToSave.add(userRole);
        }

        List<UserRole> saved = userRoleRepository.saveAll(mappingsToSave);
        return saved.stream().map(userRoleMapper::toResponse).collect(Collectors.toList());
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
    public List<UserRoleResponse> getRolesForUser(Long userId) {
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
}
