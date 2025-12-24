package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Comment;
import com.ssafy.o2omystore.dto.CommentWithProduct;
@Mapper
public interface CommentDao {

    List<Comment> selectCommentsByProductId(int productId);
    Comment selectCommentByUserAndProduct(String userId, int productId);

    int insertComment(Comment comment);

    int updateComment(Comment comment);

    int deleteComment(int commentId);

    int countCommentsByUserId(String userId);

    List<CommentWithProduct> selectCommentsByUserIdWithProduct(String userId);
}
