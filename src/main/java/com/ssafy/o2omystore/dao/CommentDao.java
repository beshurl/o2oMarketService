package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Comment;
@Mapper
public interface CommentDao {

    List<Comment> selectCommentsByProductId(int productId);

    int insertComment(Comment comment);

    int updateComment(Comment comment);

    int deleteComment(int commentId);
}
