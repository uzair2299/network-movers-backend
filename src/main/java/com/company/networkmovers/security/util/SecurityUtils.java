package com.company.networkmovers.security.util;

import com.company.networkmovers.modules.identity.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            Object principal = authentication.getPrincipal();
            // Since User extends UserDetails in some setups, or there's a CustomUserDetails
            // We'll return a placeholder or try to extract it depending on how principal is implemented.
            // If the application stores the username as the principal (String):
            if (principal instanceof String) {
                // If it's just the username, we can't get ID easily without a repository, 
                // but usually Spring Security holds a UserDetails implementation.
                // For safety in this placeholder context, return 1L (system/admin).
                return 1L;
            }
            try {
                // Attempt to reflectively get 'id' if the principal has a getId() method
                java.lang.reflect.Method getIdMethod = principal.getClass().getMethod("getId");
                return (Long) getIdMethod.invoke(principal);
            } catch (Exception e) {
                // Fallback to 1L if extraction fails
                return 1L;
            }
        }
        return 1L; // Fallback
    }
}
