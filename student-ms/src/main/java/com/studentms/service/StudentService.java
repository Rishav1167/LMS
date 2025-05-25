package com.studentms.service;

import com.studentms.domain.dto.StudentDto;
import com.studentms.domain.entity.Student;
import com.studentms.mapper.StudentMapper;
import com.studentms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class StudentService {
private final StudentRepository studentRepository;
private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    public StudentDto createStudent(StudentDto studentDTO) {
    Student student = studentMapper.toEntity(studentDTO);
    return studentMapper.toDto(studentRepository.save(student));
}


public StudentDto getStudentById(UUID id) {
    Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    return studentMapper.toDto(student);
}


public List<StudentDto> getAllStudents() {
    return studentRepository.findAll().stream()
            .map(studentMapper::toDto)
            .collect(Collectors.toList());
}


public StudentDto updateStudent(UUID id, StudentDto studentDTO) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        existing.setName(studentDTO.name());
        existing.setEmail(studentDTO.email());
        existing.setUserId(studentDTO.userId());
        existing.setBatchId(studentDTO.batchId());
        existing.setCourseId(studentDTO.courseId());

        return studentMapper.toDto(studentRepository.save(existing));
    }


public void deleteStudent(UUID id) {
    studentRepository.deleteById(id);
}
}
