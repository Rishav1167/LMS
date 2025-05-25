package com.lms.batchms.domain.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Batch {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;
    private UUID courseId;
    private UUID instructorId;

    @ElementCollection
    private List<UUID> studentIds = new ArrayList<>();
}
