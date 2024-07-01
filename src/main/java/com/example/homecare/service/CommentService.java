package com.example.homecare.service;

import com.example.homecare.model.dto.CommentObject;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.Comment;
import com.example.homecare.model.entity.User;
import com.example.homecare.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.DateUtil.getCurrentTime;
import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CollaboratorService collaboratorService;
    public List<Comment> getCommentsByCollaboratorId(String collaboratorId) {
        return commentRepository.findByCollaborator_CollaboratorIdOrderByTimeDesc(collaboratorId);
    }
    public Comment saveComment(CommentObject commentObject) {
        Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(commentObject.getCollaboratorId());
        Optional<User> user = userService.getUserById(commentObject.getUserId());
        Comment comment = new Comment(createId("comment"), commentObject.getContent(), getCurrentTime(), user.get(), collaborator.get());
        return commentRepository.save(comment);
    }
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }
}
