package com.company.networkmovers.security.permission;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {

    private final PermissionService permissionService;

    public PermissionEvaluator(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || permission == null) {
            return false;
        }
        String requiredPermission = permission.toString();
        String username = authentication.getName();
        return permissionService.hasPermission(username, requiredPermission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication == null || permission == null) {
            return false;
        }
        String requiredPermission = permission.toString();
        String username = authentication.getName();
        return permissionService.hasPermission(username, requiredPermission);
    }
}
