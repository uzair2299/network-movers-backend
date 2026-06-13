package com.company.networkmovers.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profilePictureUrl;
    private String address;
    private List<ModulePermission> permissions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModulePermission {
        private String code;
        private String name;
        private List<ResourcePermission> resources;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResourcePermission {
        private String code;
        private String name;
        private Map<String, Boolean> actions;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("access_token")
    public String getAccessToken() {
        return token;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("token_type")
    public String getTokenType() {
        return "bearer";
    }
}
