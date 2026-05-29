package com.company.networkmovers.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtProvider.class);

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public JwtProvider(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public Authentication getAuthentication(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean isValid = jwtTokenUtil.validateToken(token, userDetails);
            if (!isValid) {
                log.warn("Token is invalid or expired for user: {}", username);
            }
            return isValid;
        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage(), e);
            return false;
        }
    }
}
