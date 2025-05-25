package com.courseservice.model.dto;


import java.util.UUID;

public record CourseDTO (

    UUID id,
    String name,
    String courseCode,
    String courseDescription,
    UUID instructorId
){
}
