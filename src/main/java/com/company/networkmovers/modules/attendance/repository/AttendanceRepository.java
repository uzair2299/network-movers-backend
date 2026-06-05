package com.company.networkmovers.modules.attendance.repository;

import java.util.UUID;

import com.company.networkmovers.modules.attendance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, UUID> {
}
