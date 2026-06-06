package com.company.networkmovers.bootstrap.seeder;

import com.company.networkmovers.modules.identity.entity.User;
import com.company.networkmovers.modules.identity.entity.UserProfile;
import com.company.networkmovers.modules.identity.repository.UserRepository;
import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.modules.rbac.repository.RoleRepository;
import com.company.networkmovers.modules.rbac.entity.UserRole;
import com.company.networkmovers.modules.rbac.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SecurityDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityDataSeeder(UserRepository userRepository,
                              RoleRepository roleRepository,
                              UserRoleRepository userRoleRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Seed default roles if they don't exist
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name("ROLE_ADMIN")
                        .code("ROLE_ADMIN")
                        .description("Administrator Role")
                        .active(true)
                        .deleted(false)
                        .build()));

        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name("ROLE_CUSTOMER")
                        .code("ROLE_CUSTOMER")
                        .description("Default Customer Role")
                        .active(true)
                        .deleted(false)
                        .build()));

        // Seed default admin user if none exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@company.com")
                    .password(passwordEncoder.encode("adminpassword"))
                    .enabled(true)
                    .deleted(false)
                    .build();

            UserProfile profile = UserProfile.builder()
                    .user(admin)
                    .firstName("Admin")
                    .lastName("User")
                    .phoneNumber("+123456789")
                    .address("Admin HQ")
                    .deleted(false)
                    .build();
            admin.setProfile(profile);

            User savedAdmin = userRepository.save(admin);

            // Link ROLE_ADMIN to default admin user
            UserRole adminRoleMapping = UserRole.builder()
                    .userId(savedAdmin.getId())
                    .role(adminRole)
                    .build();
            userRoleRepository.save(adminRoleMapping);
        }

        // Seed default customer user if none exists
        if (!userRepository.existsByUsername("customer")) {
            User customer = User.builder()
                    .username("customer")
                    .email("customer@company.com")
                    .password(passwordEncoder.encode("customerpassword"))
                    .enabled(true)
                    .deleted(false)
                    .build();

            UserProfile profile = UserProfile.builder()
                    .user(customer)
                    .firstName("Customer")
                    .lastName("User")
                    .phoneNumber("+987654321")
                    .address("Customer Address")
                    .deleted(false)
                    .build();
            customer.setProfile(profile);

            User savedCustomer = userRepository.save(customer);

            // Link ROLE_CUSTOMER to default customer user
            UserRole customerRoleMapping = UserRole.builder()
                    .userId(savedCustomer.getId())
                    .role(customerRole)
                    .build();
            userRoleRepository.save(customerRoleMapping);
        }
    }
}
