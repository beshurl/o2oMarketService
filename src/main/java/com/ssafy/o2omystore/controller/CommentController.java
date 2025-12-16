package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Comment;
import com.ssafy.o2omystore.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/product/{productId}")
    public List<Comment> getComments(@PathVariable int productId) {
        return commentService.getCommentsByProductId(productId);
    }

    @PostMapping
    public void createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable int commentId,
                              @RequestBody Comment comment) {
        comment.setCommentId(commentId);
        commentService.modifyComment(comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable int commentId) {
        commentService.removeComment(commentId);
    }
}
