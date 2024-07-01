package com.example.homecare.controller;

import com.example.homecare.model.dto.CommentObject;
import com.example.homecare.model.entity.Comment;
import com.example.homecare.service.CommentService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.COMMENT)
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/collaborator/{id}")
    public ResponseEntity<List<Comment>> getCommentsByCollaboratorId(@PathVariable String id) {
        return new ResponseEntity<>(commentService.getCommentsByCollaboratorId(id), HttpStatus.OK);
    }
    @PostMapping
    public  ResponseEntity<Comment> addComment(@RequestBody CommentObject comment) {
        try {
            Comment saveComment = commentService.saveComment(comment);
            return new ResponseEntity<Comment>(saveComment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable String id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
