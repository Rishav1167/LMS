package com.examservice.mapper;

import com.examservice.model.dto.ExamDTO;
import com.examservice.model.entity.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    ExamDTO toDTO(Exam exam);

    Exam toEntity(ExamDTO dto);

}
