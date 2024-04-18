package com.example.promokodapi.repository;

import com.example.promokodapi.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
