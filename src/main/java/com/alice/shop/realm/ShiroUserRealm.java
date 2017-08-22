package com.alice.shop.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alice.shop.bean.User;
import com.alice.shop.service.UserService;
import com.alice.shop.util.CipherUtil;

public class ShiroUserRealm extends AuthorizingRealm{
	
	private static final String ALGORITHM = "MD5";
	
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String currentUsername = (String)super.getAvailablePrincipal(principals);
		User user = userService.getUserbyName(currentUsername);
		if(user == null) throw new AuthenticationException("用户不存在");
		
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		
		int roles = userService.getRole(currentUsername);
		
		simpleAuthorInfo.addRole(String.valueOf(roles));

		
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
		
		User user = userService.getUserbyName(usertoken.getUsername());
		if(user!=null) {
			return new SimpleAuthenticationInfo(user.getName(),
					user.getPassword(),
					getName());
		}else {
			throw new AuthenticationException();
		}
	}
	
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal,getName());
		clearCachedAuthorizationInfo(principals);
	}
	
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object,AuthorizationInfo> cache = getAuthorizationCache();
		if(cache != null) {
			for(Object key:cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
}
