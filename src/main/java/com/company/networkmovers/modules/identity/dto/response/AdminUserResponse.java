package com.company.networkmovers.modules.identity.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserResponse {
    private UUID id;
    private String username;
    private String email;
    private boolean enabled;
    private List<String> roles;
    
    private ProfileResponse profile;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProfileResponse {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String profilePictureUrl;
        private String address;
    }
}
