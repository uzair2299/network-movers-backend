package com.company.networkmovers.modules.identity.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserResponse {
    private Long id;
    private String username;
    private String email;
    private boolean enabled;
    private List<String> roles;
    
    // Profile Fields
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePictureUrl;
    private String address;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
