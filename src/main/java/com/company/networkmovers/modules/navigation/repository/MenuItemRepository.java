package com.company.networkmovers.modules.navigation.repository;

import com.company.networkmovers.modules.navigation.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("SELECT DISTINCT m FROM MenuItem m " +
           "LEFT JOIN FETCH m.permission p " +
           "LEFT JOIN FETCH m.parent " +
           "WHERE m.active = true " +
           "AND m.section = :section " +
           "AND (p IS NULL OR p.name IN :permissions) " +
           "ORDER BY m.sortOrder ASC")
    List<MenuItem> findAllActiveBySectionAndPermissions(
            @Param("section") String section, 
            @Param("permissions") List<String> permissions
    );
    
    @Query("SELECT DISTINCT m FROM MenuItem m " +
           "LEFT JOIN FETCH m.permission p " +
           "LEFT JOIN FETCH m.parent " +
           "WHERE m.active = true " +
           "AND (p IS NULL OR p.name IN :permissions) " +
           "ORDER BY m.sortOrder ASC")
    List<MenuItem> findAllActiveByPermissions(@Param("permissions") List<String> permissions);
}
