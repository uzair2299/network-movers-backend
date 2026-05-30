package com.company.networkmovers.security.controller;

import com.company.networkmovers.modules.identity.entity.User;
import com.company.networkmovers.modules.identity.entity.UserProfile;
import com.company.networkmovers.modules.identity.repository.UserRepository;
import com.company.networkmovers.security.dto.LoginRequest;
import com.company.networkmovers.security.dto.LoginResponse;
import com.company.networkmovers.security.dto.RegisterRequest;
import com.company.networkmovers.security.jwt.JwtTokenUtil;
import com.company.networkmovers.security.rbac.Role;
import com.company.networkmovers.security.rbac.RoleRepository;
import com.company.networkmovers.security.rbac.UserRole;
import com.company.networkmovers.security.rbac.UserRoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
@SecurityRequirements
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          UserRoleRepository userRoleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private ResponseEntity<LoginResponse> performLogin(String username, String password) {
        if (username == null || password == null) {
            return ResponseEntity.badRequest().build();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        User user = userRepository.findByUsernameWithProfile(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + userDetails.getUsername()));

        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .roles(roles)
                .firstName(user.getProfile() != null ? user.getProfile().getFirstName() : null)
                .lastName(user.getProfile() != null ? user.getProfile().getLastName() : null)
                .email(user.getEmail())
                .phoneNumber(user.getProfile() != null ? user.getProfile().getPhoneNumber() : null)
                .profilePictureUrl(user.getProfile() != null ? user.getProfile().getProfilePictureUrl() : null)
                .address(user.getProfile() != null ? user.getProfile().getAddress() : null)
                .build());
    }

    @PostMapping(value = "/login", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Login (JSON)", description = "Authenticate user via JSON request body and return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request or bad credentials", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<LoginResponse> loginJson(@org.springframework.web.bind.annotation.RequestBody LoginRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        return performLogin(request.getUsername(), request.getPassword());
    }

    @PostMapping(value = "/login", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(summary = "Login (Form)", description = "Authenticate user via Form parameters and return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request or bad credentials", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<LoginResponse> loginForm(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        return performLogin(username, password);
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register a new user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "User registered successfully!"))),
            @ApiResponse(responseCode = "400", description = "Username or email already in use",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error: Username is already taken!")))
    })
    public ResponseEntity<Object> register(@org.springframework.web.bind.annotation.RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .build();

        UserProfile profile = UserProfile.builder()
                .user(user)
                .build();
        user.setProfile(profile);

        User savedUser = userRepository.save(user);

        // Find or seed default Customer role
        Role defaultRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name("ROLE_CUSTOMER")
                        .description("Default customer role")
                        .build()));

        // Map role to user
        UserRole userRole = UserRole.builder()
                .userId(savedUser.getId())
                .role(defaultRole)
                .build();
        userRoleRepository.save(userRole);

        return ResponseEntity.ok("User registered successfully!");
    }
}
