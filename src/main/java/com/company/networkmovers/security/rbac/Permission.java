package com.company.networkmovers.security.rbac;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sec_permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
