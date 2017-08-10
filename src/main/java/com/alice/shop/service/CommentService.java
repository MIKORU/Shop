package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Comment;

public interface CommentService {
	
	public boolean addComment(int userId,String userName,int commodityID,String comment);
	
	public List<Comment> getComments();
	
	public List<Comment> getCommentById(int commodityId);
	
}
