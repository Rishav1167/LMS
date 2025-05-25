package com.contentms.service;


import com.contentms.domain.dto.ContentDTO;
import com.contentms.domain.entity.Content;
import com.contentms.mapper.ContentMapper;
import com.contentms.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContentService{
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    public ContentService(ContentRepository contentRepository, ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
    }


    public ContentDTO createContent(ContentDTO contentDTO) {
        Content content = contentMapper.toEntity(contentDTO);
        return contentMapper.toDto(contentRepository.save(content));
    }


    public ContentDTO getContentById(UUID id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with ID: " + id));
        return contentMapper.toDto(content);
    }


    public List<ContentDTO> getAllContent() {
        return contentRepository.findAll().stream()
                .map(contentMapper::toDto)
                .collect(Collectors.toList());
    }


    public ContentDTO updateContent(UUID id, ContentDTO contentDTO) {
        Content existing = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with ID: " + id));

        existing.setTitle(contentDTO.title());
        existing.setDescription(contentDTO.description());
        existing.setMediaUrl(contentDTO.mediaUrl());
        existing.setCourseId(contentDTO.courseId());
        existing.setInstructorId(contentDTO.instructorId());
        existing.setStudentId(contentDTO.studentId());

        return contentMapper.toDto(contentRepository.save(existing));
    }


    public void deleteContent(UUID id) {
        contentRepository.deleteById(id);
    }
}
