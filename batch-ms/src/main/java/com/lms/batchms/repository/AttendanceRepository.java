package com.lms.batchms.repository;

import com.lms.batchms.domain.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    List<Attendance> findByBatchId(UUID batchId);
    List<Attendance> findByStudentId(UUID studentId);
    Optional<Attendance> findByBatchIdAndStudentIdAndDate(UUID batchId, UUID studentId, LocalDate date);
}

