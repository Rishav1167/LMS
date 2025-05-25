package com.studentms.mapper;

import com.studentms.domain.dto.StudentDto;
import com.studentms.domain.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);
    Student toEntity(StudentDto studentDTO);
}