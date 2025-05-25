package com.examservice.services;
import com.examservice.exceptions.ResourceNotFoundException;
import com.examservice.mapper.ExamMapper;
import com.examservice.model.dto.ExamDTO;
import com.examservice.model.entity.Exam;
import com.examservice.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamMapper examMapper;

    public ExamDTO createExam(ExamDTO dto) {
        Exam exam = examMapper.toEntity(dto);
        Exam saved = examRepository.save(exam);
        return examMapper.toDTO(saved);
    }

    public ExamDTO updateExam(UUID id, ExamDTO dto) {
        Exam existing = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + id));

        existing.setExamName(dto.examName());
        existing.setExamDate(dto.examDate());
        existing.setStudentId(dto.studentId());
        existing.setStatus(dto.status());

        Exam updated = examRepository.save(existing);
        return examMapper.toDTO(updated);
    }

    public void deleteExam(UUID id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + id));
        examRepository.delete(exam);
    }

    public List<ExamDTO> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDTO> dtoList = new ArrayList<>();
        for (Exam exam : exams) {
            dtoList.add(examMapper.toDTO(exam));
        }
        return dtoList;
    }

    public ExamDTO getExamById(UUID id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + id));
        return examMapper.toDTO(exam);
    }
}
