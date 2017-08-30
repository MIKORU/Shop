package com.alice.shop.shiro.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

import com.alice.shop.shiro.session.CustomSessionManager;
import com.alice.shop.shiro.session.SessionStatus;
import com.alice.shop.shiro.session.ShiroSessionRepository;
import com.alice.shop.util.SerializeUtil;

public class JedisShiroSessionRepository implements ShiroSessionRepository {

	public static final String REDIS_SHIRO_SESSION = "shiro-session:";

	public static final String REDIS_SHIRO_ALL = "*shiro-session:*";

	private static final int SESSION_VAL_TIME_SPAN = 18000;

	private static final int DB_INDEX = 1;

	private JedisManager jedisManager;

	@Override
	public void saveSession(Session session) {
		// TODO Auto-generated method stub
		if (session == null || session.getId() == null) {
			throw new NullPointerException("session is empty");
		}
		try {
			byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
			if (null == session.getAttribute(CustomSessionManager.SESSION_STATUS)) {
				SessionStatus sessionStatus = new SessionStatus();
				session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
			}

			byte[] value = SerializeUtil.serialize(session);
			long sessionTimeOut = session.getTimeout() / 1000;
			Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
			getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime.intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		if(sessionId == null) {
			throw new NullPointerException("session id is empty");
		}
		try {
			getJedisManager().deleteByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(sessionId)));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Session getSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		if(sessionId == null) {
			throw new NullPointerException("session id is empty");
		}
		Session session = null;
		try {
			byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(sessionId)));
			session = SerializeUtil.deserialize(value,Session.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	@Override
	public Collection<Session> getAllSessions() {
		// TODO Auto-generated method stub
		Collection<Session> sessions = null;
		try {
			sessions = getJedisManager().AllSession(DB_INDEX, REDIS_SHIRO_SESSION);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return sessions;
	}

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	private String buildRedisSessionKey(Serializable sessionId) {
		return REDIS_SHIRO_SESSION + sessionId;
	}
	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

}
