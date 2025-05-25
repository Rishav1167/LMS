package com.lms.batchms.controller;

import com.lms.batchms.domain.entity.Attendance;
import com.lms.batchms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @PostMapping("/mark")
    public ResponseEntity<Attendance> mark(@RequestParam UUID batchId,
                                           @RequestParam UUID studentId,
                                           @RequestParam LocalDate date,
                                           @RequestParam boolean present) {
        return ResponseEntity.ok(service.markAttendance(batchId, studentId, date, present));
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<Attendance>> getBatch(@PathVariable UUID batchId) {
        return ResponseEntity.ok(service.getBatchAttendance(batchId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(service.getStudentAttendance(studentId));
    }
}
