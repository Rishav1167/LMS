package com.courseservice.service;

import com.courseservice.exception.ResourceNotFoundException;
import com.courseservice.mapper.CourseMapper;
import com.courseservice.model.dto.CourseDTO;
import com.courseservice.model.entity.Course;
import com.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public CourseDTO createCourse(CourseDTO dto) {
        Course course = courseMapper.toEntity(dto);
        Course saved = courseRepository.save(course);
        return courseMapper.toDTO(saved);
    }

    public CourseDTO updateCourse(UUID id, CourseDTO dto) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        existing.setName(dto.name());
        existing.setCourseCode(dto.courseCode());
        existing.setCourseDescription(dto.courseDescription());
        existing.setInstructorId(dto.instructorId());

        Course updated = courseRepository.save(existing);
        return courseMapper.toDTO(updated);
    }

    public void deleteCourse(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));

        courseRepository.delete(course);
    }


    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> dtoList = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO dto = courseMapper.toDTO(course);
            dtoList.add(dto);
        }
        return dtoList;
    }


    public CourseDTO getCourseById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        return courseMapper.toDTO(course);
    }

}
