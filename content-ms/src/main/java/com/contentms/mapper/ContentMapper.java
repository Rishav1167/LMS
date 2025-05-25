package com.contentms.mapper;


import com.contentms.domain.dto.ContentDTO;
import com.contentms.domain.entity.Content;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    ContentDTO toDto(Content content);
    Content toEntity(ContentDTO contentDTO);
}

