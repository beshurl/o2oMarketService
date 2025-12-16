package com.ssafy.o2omystore.service;

import java.util.List;
import com.ssafy.o2omystore.dto.Comment;

public interface CommentService {

    List<Comment> getCommentsByProductId(int productId);

    void createComment(Comment comment);

    void modifyComment(Comment comment);

    void removeComment(int commentId);
}
