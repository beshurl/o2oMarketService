package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Comment;
import com.ssafy.o2omystore.dto.CommentWithProduct;
import com.ssafy.o2omystore.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review API", description = "상품에 대한 리뷰 조회, 작성, 수정, 삭제 API")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "{productId}에 해당하는 상품의 리뷰 전체 조회")
    @GetMapping("/product/{productId}")
    public List<Comment> getComments(@PathVariable int productId) {
        return commentService.getCommentsByProductId(productId);
    }

    @Operation(summary = "리뷰를 작성합니다.")
    @PostMapping
    public void createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
    }

    @Operation(summary = "{userId}에 해당하는 사용자의 리뷰와 상품 정보를 반환합니다.")
    @GetMapping("/user/{userId}")
    public List<CommentWithProduct> getCommentsByUserId(@PathVariable String userId) {
        return commentService.getCommentsByUserIdWithProduct(userId);
    }

    @Operation(summary = "{commentId}에 해당하는 리뷰를 수정합니다.")
    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable int commentId,
                              @RequestBody Comment comment) {
        comment.setCommentId(commentId);
        commentService.modifyComment(comment);
    }

    @Operation(summary = "{commentId}에 해당하는 리뷰를 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable int commentId) {
        commentService.removeComment(commentId);
    }
}
