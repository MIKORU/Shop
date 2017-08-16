package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Comment;

public interface CommentService {
	
	public boolean addComment(String userId,String userName,String commodityID,String comment);
	
	public List<Comment> getComments();
	
	public List<Comment> getCommentById(String commodityId);
	
}
