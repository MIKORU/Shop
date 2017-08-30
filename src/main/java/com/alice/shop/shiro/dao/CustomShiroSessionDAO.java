package com.alice.shop.shiro.dao;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.alice.shop.shiro.session.ShiroSessionRepository;

public class CustomShiroSessionDAO extends AbstractSessionDAO {
	
	private ShiroSessionRepository shiroSessionRepository;
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		getShiroSessionRepository().saveSession(session);
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		if(session == null) {
			return ;
		}
		Serializable id = session.getId();
		if(id != null) {
			getShiroSessionRepository().deleteSession(id);
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		return getShiroSessionRepository().getAllSessions();
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		getShiroSessionRepository().saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		return getShiroSessionRepository().getSession(sessionId);
	}

	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}
	
	
}
