package com.alice.shop.shiro.cache.Impl;

import org.apache.shiro.cache.Cache;

import com.alice.shop.shiro.cache.JedisManager;
import com.alice.shop.shiro.cache.JedisShiroCache;
import com.alice.shop.shiro.cache.ShiroCacheManager;

public class JedisShiroCacheManager implements ShiroCacheManager {
	
	private JedisManager jdedisManager;
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		// TODO Auto-generated method stub
		return new JedisShiroCache<K,V>(name,getJdedisManager());
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub

	}

	public JedisManager getJdedisManager() {
		return jdedisManager;
	}

	public void setJdedisManager(JedisManager jdedisManager) {
		this.jdedisManager = jdedisManager;
	}
	
	

}
