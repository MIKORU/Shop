package com.alice.shop.shiro.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.alice.shop.bean.User;
import com.alice.shop.bean.UserOnline;
import com.alice.shop.shiro.dao.CustomShiroSessionDAO;

public class CustomSessionManager {
	public static final String SESSION_STATUS="online-status";
	
	ShiroSessionRepository shiroSessionRepository;
	
	CustomShiroSessionDAO customShiroSessionDAO;
	
	public List<UserOnline> getAllUser(){
		Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
		List<UserOnline> list = new ArrayList<UserOnline>();
		for(Session session:sessions) {
			UserOnline user = getSessionUser(session);
			if(null != user) {
				list.add(user);
			}
		}
		return list;
	}
	
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds){
		Set<Long> idset = (Set<Long>) arrayToSet(userIds);
		Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		
		for(Session session :sessions) {
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if(null != obj && obj instanceof SimplePrincipalCollection) {
				SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
				obj = spc.getPrimaryPrincipal();
				if(null != obj && obj instanceof User) {
					User user = (User) obj;
					if(null != user && idset.contains(user.getId())) {
						list.add(spc);
					}
				}
			}
		}
		return list;
	}
	
	public UserOnline getSession(String sessionId) {
		Session session = shiroSessionRepository.getSession(sessionId);
		UserOnline user = getSessionUser(session);
		return user;
	}
	
	private UserOnline getSessionUser(Session session) {
		// TODO Auto-generated method stub
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if(null == obj) {
			return null;
		}
		if(obj instanceof SimplePrincipalCollection) {
			SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
			obj = spc.getPrimaryPrincipal();
			if(null != obj && obj instanceof User) {
				UserOnline user = new UserOnline((User)obj);
				user.setLastAccess(session.getLastAccessTime());
				user.setHost(session.getHost());
				user.setSessionId(session.getId().toString());
				user.setTimeout(session.getTimeout());
				user.setStartTime(session.getStartTimestamp());
				SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
				boolean status = Boolean.TRUE;
				if(null != sessionStatus) {
					status = sessionStatus.getOnlineStatus();
				}
				user.setSessionStatus(status);
				return user;
			}
		}
		
		return null;
	}
	public Map<String ,Object> changeSessionStatus(Boolean status,
			String sessionIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String[] sessionIdArray = null;
			if(sessionIds.indexOf(",") == -1) {
				sessionIdArray = new String[] {sessionIds};
			}else {
				sessionIdArray = sessionIds.split(",");
			}
			for(String id:sessionIdArray) {
				Session session = shiroSessionRepository.getSession(id);
				SessionStatus sessionStatus = new SessionStatus();
				session.setAttribute(SESSION_STATUS, sessionStatus);
				customShiroSessionDAO.update(session);
			}
			map.put("status", 200);
			map.put("sessionStatus", status?1:0);
			map.put("sessionStatusText", status?"踢出":"激活");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	public static Set<?> arrayToSet(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if(null != id){
				set.add(id);
			}
		}
		return set;
	}

	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

	public CustomShiroSessionDAO getCustomShiroSessionDAO() {
		return customShiroSessionDAO;
	}

	public void setCustomShiroSessionDAO(CustomShiroSessionDAO customShiroSessionDAO) {
		this.customShiroSessionDAO = customShiroSessionDAO;
	}
	
}
