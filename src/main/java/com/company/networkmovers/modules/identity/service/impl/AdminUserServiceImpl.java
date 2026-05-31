package com.company.networkmovers.modules.identity.service.impl;

import com.company.networkmovers.modules.identity.dto.request.AdminUserRequest;
import com.company.networkmovers.modules.identity.dto.response.AdminUserResponse;
import com.company.networkmovers.modules.identity.entity.User;
import com.company.networkmovers.modules.identity.entity.UserProfile;
import com.company.networkmovers.modules.identity.repository.UserProfileRepository;
import com.company.networkmovers.modules.identity.repository.UserRepository;
import com.company.networkmovers.modules.identity.service.AdminUserService;
import com.company.networkmovers.security.rbac.Role;
import com.company.networkmovers.security.rbac.RoleRepository;
import com.company.networkmovers.security.rbac.UserRole;
import com.company.networkmovers.security.rbac.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserServiceImpl(UserRepository userRepository,
                                UserProfileRepository userProfileRepository,
                                UserRoleRepository userRoleRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminUserResponse create(AdminUserRequest request) {
        Long currentUserId = com.company.networkmovers.security.util.SecurityUtils.getCurrentUserId();
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(request.isEnabled())
                .build();
        
        user.setCreatedBy(currentUserId);
        user = userRepository.save(user);

        UserProfile profile = UserProfile.builder()
                .user(user)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .profilePictureUrl(request.getProfilePictureUrl())
                .build();
        profile.setCreatedBy(currentUserId);
        userProfileRepository.save(profile);

        user.setProfile(profile);

        assignRoles(user.getId(), request.getRoles());

        return mapToResponse(user, profile, request.getRoles());
    }

    @Override
    public AdminUserResponse update(Long id, AdminUserRequest request) {
        Long currentUserId = com.company.networkmovers.security.util.SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getUsername().equals(request.getUsername()) && userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setEnabled(request.isEnabled());
        user.setUpdatedBy(currentUserId);
        
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user = userRepository.save(user);

        UserProfile profile = user.getProfile();
        if (profile == null) {
            profile = UserProfile.builder().user(user).build();
            profile.setCreatedBy(currentUserId);
        } else {
            profile.setUpdatedBy(currentUserId);
        }
        
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setAddress(request.getAddress());
        profile.setProfilePictureUrl(request.getProfilePictureUrl());
        
        userProfileRepository.save(profile);

        assignRoles(user.getId(), request.getRoles());

        return mapToResponse(user, profile, request.getRoles());
    }

    @Override
    public AdminUserResponse toggleActive(Long id) {
        Long currentUserId = com.company.networkmovers.security.util.SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(!user.isEnabled());
        user.setUpdatedBy(currentUserId);
        user = userRepository.save(user);
        
        List<String> roles = userRoleRepository.findByUserId(user.getId()).stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());
        return mapToResponse(user, user.getProfile(), roles);
    }

    @Override
    public void softDelete(Long id) {
        Long currentUserId = com.company.networkmovers.security.util.SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.delete(currentUserId);
        userRepository.save(user);
        
        if (user.getProfile() != null) {
            user.getProfile().delete(currentUserId);
            userProfileRepository.save(user.getProfile());
        }
    }

    @Override
    public org.springframework.data.domain.Page<AdminUserResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        org.springframework.data.domain.Pageable pageable = createPageable(requestParams);
        String search = requestParams.getSearch();
        org.springframework.data.domain.Page<User> userPage;
        if (search == null || search.trim().isEmpty()) {
            userPage = userRepository.findAllWithProfile(pageable);
        } else {
            userPage = userRepository.findBySearch(search.trim(), pageable);
        }
        return userPage.map(user -> {
            List<String> roles = userRoleRepository.findByUserId(user.getId()).stream()
                    .map(ur -> ur.getRole().getName())
                    .collect(Collectors.toList());
            return mapToResponse(user, user.getProfile(), roles);
        });
    }

    @Override
    public org.springframework.data.domain.Page<AdminUserResponse> getAllActive(com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        org.springframework.data.domain.Pageable pageable = createPageable(requestParams);
        String search = requestParams.getSearch();
        org.springframework.data.domain.Page<User> userPage;
        if (search == null || search.trim().isEmpty()) {
            userPage = userRepository.findAllActive(pageable);
        } else {
            userPage = userRepository.findActiveBySearch(search.trim(), pageable);
        }
        return userPage.map(user -> {
            List<String> roles = userRoleRepository.findByUserId(user.getId()).stream()
                    .map(ur -> ur.getRole().getName())
                    .collect(Collectors.toList());
            return mapToResponse(user, user.getProfile(), roles);
        });
    }

    private org.springframework.data.domain.Pageable createPageable(com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        String[] sortParams = requestParams.getSort().split(",");
        String sortField = sortParams[0];
        org.springframework.data.domain.Sort.Direction direction = org.springframework.data.domain.Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            direction = org.springframework.data.domain.Sort.Direction.DESC;
        }
        return org.springframework.data.domain.PageRequest.of(
                requestParams.getPage(),
                requestParams.getSize(),
                org.springframework.data.domain.Sort.by(direction, sortField)
        );
    }

    @Override
    public AdminUserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<String> roles = userRoleRepository.findByUserId(user.getId()).stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());
        return mapToResponse(user, user.getProfile(), roles);
    }

    private void assignRoles(Long userId, List<String> roleNames) {
        List<UserRole> existingUserRoles = userRoleRepository.findByUserId(userId);
        userRoleRepository.deleteAll(existingUserRoles);

        if (roleNames != null && !roleNames.isEmpty()) {
            List<Role> roles = roleNames.stream()
                    .map(name -> roleRepository.findByName(name)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + name)))
                    .collect(Collectors.toList());

            List<UserRole> newUserRoles = roles.stream()
                    .map(role -> UserRole.builder()
                            .userId(userId)
                            .role(role)
                            .build())
                    .collect(Collectors.toList());

            userRoleRepository.saveAll(newUserRoles);
        }
    }

    private AdminUserResponse mapToResponse(User user, UserProfile profile, List<String> roles) {
        AdminUserResponse response = AdminUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .roles(roles)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

        if (profile != null) {
            response.setFirstName(profile.getFirstName());
            response.setLastName(profile.getLastName());
            response.setPhoneNumber(profile.getPhoneNumber());
            response.setAddress(profile.getAddress());
            response.setProfilePictureUrl(profile.getProfilePictureUrl());
        }

        return response;
    }
}
