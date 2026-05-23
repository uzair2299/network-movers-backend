package com.company.networkmovers.modules.navigation.controller;

import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.service.NavigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/navigation")
public class NavigationController {

    private final NavigationService navigationService;

    public NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping
    public ResponseEntity<?> getUserNavigation(
            @RequestParam(value = "section", required = false) String section,
            @AuthenticationPrincipal UserDetails userDetails) {
            
        if (section != null) {
            return ResponseEntity.ok(navigationService.getNavigationMenuForUser(section.toUpperCase(), userDetails));
        }
        return ResponseEntity.ok(navigationService.getAllNavigationMenusForUser(userDetails));
    }
}
