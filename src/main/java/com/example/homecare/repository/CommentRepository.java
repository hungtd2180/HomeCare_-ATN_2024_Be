package com.example.homecare.repository;

import com.example.homecare.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByCollaborator_CollaboratorIdOrderByTimeDesc(String collaborator);
}
