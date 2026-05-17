package com.company.networkmovers.security.permission;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PermissionCache {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Boolean>> cache = new ConcurrentHashMap<>();

    public Boolean getPermission(String username, String permission) {
        if (!cache.containsKey(username)) {
            return null;
        }
        return cache.get(username).get(permission);
    }

    public void putPermission(String username, String permission, boolean granted) {
        cache.computeIfAbsent(username, k -> new ConcurrentHashMap<>()).put(permission, granted);
    }

    public void clear(String username) {
        cache.remove(username);
    }

    public void clearAll() {
        cache.clear();
    }
}
