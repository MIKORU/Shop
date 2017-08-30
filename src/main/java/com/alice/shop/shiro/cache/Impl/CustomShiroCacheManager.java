package com.alice.shop.shiro.cache.Impl;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import com.alice.shop.shiro.cache.ShiroCacheManager;

public class CustomShiroCacheManager implements CacheManager, Destroyable {
	
	private ShiroCacheManager shiroCacheManager;
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		shiroCacheManager.destory();
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		// TODO Auto-generated method stub
		return getShiroCacheManager().getCache(name);
	}

	public ShiroCacheManager getShiroCacheManager() {
		return shiroCacheManager;
	}

	public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
		this.shiroCacheManager = shiroCacheManager;
	}
	
	
}
