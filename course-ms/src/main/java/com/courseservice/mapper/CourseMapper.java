package com.courseservice.mapper;


import com.courseservice.model.dto.CourseDTO;
import com.courseservice.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "instructor.id", target = "instructorId")
    @Mapping(source = "students", target = "studentIds")
    @Mapping(source = "contents", target = "contentIds")
    CourseDTO toDTO(Course course);

    @Mapping(source = "instructorId", target = "instructor.id")
    @Mapping(target = "students", ignore = true) // populate manually in service
    @Mapping(target = "contents", ignore = true)
    Course toEntity(CourseDTO dto);
}
