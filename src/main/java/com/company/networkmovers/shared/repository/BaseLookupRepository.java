package com.company.networkmovers.shared.repository;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseLookupRepository<E extends BaseLookupEntity> extends JpaRepository<E, UUID> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.active = true")
    List<E> findAllActive();

    @Query("SELECT e FROM #{#entityName} e WHERE e.code = :code")
    Optional<E> findByCode(@Param("code") String code);

    @Query("SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.code = :code AND e.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") UUID id);
}
