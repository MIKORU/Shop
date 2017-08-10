package com.alice.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Comment;
import com.alice.shop.dao.CommentMapper;
import com.alice.shop.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper comMapper;
	
	@Override
	public boolean addComment(int userId, String userName, int commodityID, String comment) {
		// TODO Auto-generated method stub
		Comment com = new Comment();
		com.setComment(comment);
		com.setCommodityid(commodityID);
		com.setUserid(userId);
		com.setUsername(userName);
		return 0!=comMapper.insertSelective(com);
	}

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		Comment com = new Comment();
		return comMapper.getAllList(com);
	}

	@Override
	public List<Comment> getCommentById(int commodityId) {
		// TODO Auto-generated method stub
		Comment com = new Comment();
		com.setCommodityid(commodityId);
		return comMapper.getAllList(com);
	}

}
