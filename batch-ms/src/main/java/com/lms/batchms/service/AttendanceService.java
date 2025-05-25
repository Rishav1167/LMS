package com.lms.batchms.service;

import com.lms.batchms.domain.entity.Attendance;
import com.lms.batchms.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repo;

    public Attendance markAttendance(UUID batchId, UUID studentId, LocalDate date, boolean present) {
        Attendance attendance = repo.findByBatchIdAndStudentIdAndDate(batchId, studentId, date)
                .orElse(new Attendance());

        attendance.setBatchId(batchId);
        attendance.setStudentId(studentId);
        attendance.setDate(date);
        attendance.setPresent(present);

        return repo.save(attendance);
    }

    public List<Attendance> getBatchAttendance(UUID batchId) {
        return repo.findByBatchId(batchId);
    }

    public List<Attendance> getStudentAttendance(UUID studentId) {
        return repo.findByStudentId(studentId);
    }
}

