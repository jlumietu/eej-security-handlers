package com.eej.security.handler.model;

import java.io.Serializable;

public interface AuthenticationRequestToken {
	
	public abstract Serializable getUserId();

	public abstract void setUserId(Serializable userId);

	public abstract Serializable getCredentials();

	public abstract void setCredentials(Serializable credentials);
	
	public abstract String getUserIdParamName();
	
	public abstract String getPasswordParamName();

}