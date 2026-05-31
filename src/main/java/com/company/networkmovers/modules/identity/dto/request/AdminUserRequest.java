package com.company.networkmovers.modules.identity.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserRequest {
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private List<String> roles;
    
    // Profile Fields
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePictureUrl;
    private String address;
}
