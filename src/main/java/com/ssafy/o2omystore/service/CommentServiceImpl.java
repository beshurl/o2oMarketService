package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.CommentDao;
import com.ssafy.o2omystore.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public List<Comment> getCommentsByProductId(int productId) {
		return commentDao.selectCommentsByProductId(productId);
	}

	@Override
	public void createComment(Comment comment) {

		Comment existing = commentDao.selectCommentByUserAndProduct(comment.getUserId(), comment.getProductId());

		if (existing == null) {
			commentDao.insertComment(comment);
		} else {
			comment.setCommentId(existing.getCommentId());
			commentDao.updateComment(comment);
		}
	}

	@Override
	public void modifyComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	@Override
	public void removeComment(int commentId) {
		commentDao.deleteComment(commentId);
	}

	@Override
	public int countCommentsByUserId(String userId) {
		return commentDao.countCommentsByUserId(userId);
	}
}
