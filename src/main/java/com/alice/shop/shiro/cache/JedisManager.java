package com.alice.shop.shiro.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;

import com.alice.shop.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisManager {
	private JedisPool jedisPool;
	public Jedis getJedis() {
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
		}catch (JedisConnectionException e) {
			// TODO: handle exception
			throw new JedisConnectionException(e);
		}catch (Exception e) {
            throw new RuntimeException(e);
        }
		return jedis;
	}
	
	public void returnResource(Jedis jedis,boolean isBroken) {
		if(jedis == null) {
			return ;
		}
		jedis.close();
	}
	
	public byte[] getValueByKey(int dbIndex ,byte[] key) throws Exception{
		Jedis jedis = null;
		byte[] result = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			result = jedis.get(key);
		}catch(Exception e) {
			isBroken = true;
			throw e;
		}finally {
			returnResource(jedis,isBroken);
		}
		return result;
	}
	public void deleteByKey(int dbIndex,byte[] key) throws Exception{
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			Long result = jedis.del(key);
		}catch (Exception e) {
			// TODO: handle exception
			isBroken = true;
			throw e;
		}finally {
			returnResource(jedis,isBroken);
		}
	}
	
	public void saveValueByKey(int dbIndex,byte[] key,byte[] value,int expireTime) throws Exception{
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.set(key, value);
			if(expireTime > 0) {
				jedis.expire(key, expireTime);
			}
		}catch(Exception e) {
			isBroken = true;
			throw e;
		}finally {
			returnResource(jedis, isBroken);
		}
	}
	
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}
	
	public Collection<Session> AllSession(int dbIndex,String redisShiroSession) throws Exception{
		Jedis jedis = null;
		boolean isBroken = false;
		Set<Session> sessions = new HashSet<Session>();
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			
			Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());
			if(byteKeys != null && byteKeys.size()>0) {
				for(byte[] bs:byteKeys) {
					Session obj = SerializeUtil.deserialize(jedis.get(bs),Session.class);
					if(obj instanceof Session) {
						sessions.add(obj);
					}
				}
			}
		}catch (Exception e) {
			isBroken = true;
			throw e;
		}finally {
			returnResource(jedis, isBroken);
		}
		return sessions;
	}
}
