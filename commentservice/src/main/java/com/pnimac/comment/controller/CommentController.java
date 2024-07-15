package com.pnimac.comment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pnimac.comment.dto.CommentDTO;
import com.pnimac.comment.model.Comment;
import com.pnimac.comment.request.CommentRequest;
import com.pnimac.comment.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private final CommentService commentService;
    @Autowired
    private final ObjectMapper objectMapper;

    public CommentController(CommentService commentService, ObjectMapper objectMapper) {
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/getComments/{postId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long postId) {
        List<Comment> comments = commentService.findByPostidByOrderByCreatedAtDesc(postId);
        List<CommentDTO> commentDTOs = comments.stream().map(CommentDTO::fromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(commentDTOs);
    }
    
    @PostMapping("/addComment")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentRequest commentRequest) {
        Comment newComment = new Comment();
        newComment.setContent(commentRequest.getContent());
        newComment.setPostId(commentRequest.getPostId());
        newComment.setUsername(commentRequest.getUsername());
        CommentDTO commentDTO = CommentDTO.fromEntity(commentService.save(newComment));
        return ResponseEntity.ok(commentDTO);
    }
    
    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
