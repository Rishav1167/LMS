package com.contentms.repository;


import com.contentms.domain.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}

