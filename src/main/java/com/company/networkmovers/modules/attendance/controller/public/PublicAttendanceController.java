package com.company.networkmovers.modules.attendance.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.attendance.dto.response.AttendanceResponse;
import com.company.networkmovers.modules.attendance.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/attendance")
public class PublicAttendanceController {

    private final AttendanceService service;

    public PublicAttendanceController(AttendanceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
