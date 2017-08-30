package com.alice.shop.shiro.cache;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.alice.shop.util.SerializeUtil;

public class JedisShiroCache<K, V> implements Cache<K, V> {
	
	private static final String REDIS_SHIRO_CACHE="shiro-cache:";
	
	private static final int DB_INDEX = 1;
	
	private String name;
	
	private JedisManager jedisManager;
	
	static final Class<JedisShiroCache> SELF = JedisShiroCache.class;
	

	public JedisShiroCache(String name, JedisManager jedisManager) {
		super();
		this.name = name;
		this.jedisManager = jedisManager;
	}

	public String getName() {
		if(name == null)
			return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	@Override
	public V get(K key) throws CacheException {
		// TODO Auto-generated method stub
		byte[] byteValue = new byte[0];
		try{
			byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
			byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (V) SerializeUtil.deserialize(byteValue);
	}

	@Override
	public V put(K key, V value) throws CacheException {
		// TODO Auto-generated method stub
		V previos = get(key);
		try {
			jedisManager.saveValueByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)), 
					SerializeUtil.serialize(value), -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previos;
	}

	@Override
	public V remove(K key) throws CacheException {
		// TODO Auto-generated method stub
		V previos = get(key);
		try {
			jedisManager.deleteByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previos;
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String buildCacheKey(Object key) {
        return REDIS_SHIRO_CACHE + getName() + ":" + key;
    }
}
