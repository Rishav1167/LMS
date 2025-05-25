package com.lms.batchms.service;

import com.lms.batchms.domain.entity.Batch;
import com.lms.batchms.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BatchService {
    @Autowired
    private BatchRepository repository;

    public Batch createBatch(Batch batch) {
        return repository.save(batch);
    }

    public Optional<Batch> getBatch(UUID id) {
        return repository.findById(id);
    }

    public List<Batch> getAllBatches() {
        return repository.findAll();
    }

    public List<Batch> getBatchesByInstructor(UUID instructorId) {
        return repository.findByInstructorId(instructorId);
    }

    public void enrollStudent(UUID batchId, UUID studentId) {
        Batch batch = repository.findById(batchId).orElseThrow();
        batch.getStudentIds().add(studentId);
        repository.save(batch);
    }
}
