package com.lms.batchms.controller;

import com.lms.batchms.domain.entity.Batch;
import com.lms.batchms.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    @Autowired
    private BatchService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Batch batch) {
        return new ResponseEntity<>(service.createBatch(batch), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return service.getBatch(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Batch> getByInstructor(@PathVariable UUID instructorId) {
        return service.getBatchesByInstructor(instructorId);
    }

    @PutMapping("/{batchId}/enroll/{studentId}")
    public ResponseEntity<?> enrollStudent(@PathVariable UUID batchId, @PathVariable UUID studentId) {
        service.enrollStudent(batchId, studentId);
        return ResponseEntity.ok("Student enrolled");
    }

    @GetMapping("/")
    public List<Batch> getAll() {
        return service.getAllBatches();
    }
}