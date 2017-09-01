package com.alice.shop.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.alice.shop.shiro.session.ShiroSessionRepository;

public class CustomSessionListener implements SessionListener {
	
	private ShiroSessionRepository shiroSessionRepository;
	
	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		System.out.println("on start");
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		System.out.println("on stop");
	}

	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		shiroSessionRepository.deleteSession(session.getId());
	}

	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}
}
