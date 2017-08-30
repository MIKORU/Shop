package com.alice.shop.shiro.session;

import java.io.Serializable;

public class SessionStatus implements Serializable {
	private Boolean onlineStatus = Boolean.TRUE;
	
	public Boolean isOnlineStatus() {
		return onlineStatus;
	}

	public Boolean getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Boolean onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
}
