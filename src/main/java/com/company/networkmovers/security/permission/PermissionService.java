package com.company.networkmovers.security.permission;

import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final PermissionCache permissionCache;

    public PermissionService(PermissionCache permissionCache) {
        this.permissionCache = permissionCache;
    }

    public boolean hasPermission(String username, String permission) {
        Boolean hasPerm = permissionCache.getPermission(username, permission);
        if (hasPerm != null) {
            return hasPerm;
        }
        
        // Admin user override
        if ("admin".equalsIgnoreCase(username)) {
            return true;
        }
        
        return false;
    }
}
