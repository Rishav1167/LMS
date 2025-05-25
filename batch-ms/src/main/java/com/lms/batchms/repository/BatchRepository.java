package com.lms.batchms.repository;

import com.lms.batchms.domain.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BatchRepository extends JpaRepository<Batch, UUID> {
    List<Batch> findByInstructorId(UUID instructorId);
    List<Batch> findByCourseId(UUID courseId);
}