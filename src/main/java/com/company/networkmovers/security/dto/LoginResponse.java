package com.company.networkmovers.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private List<String> roles;

    @com.fasterxml.jackson.annotation.JsonProperty("access_token")
    public String getAccessToken() {
        return token;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("token_type")
    public String getTokenType() {
        return "bearer";
    }
}
