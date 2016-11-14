/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author DOIBALMI
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequestBodyAuthenticationToken implements Serializable, AuthenticationRequestToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = com.eej.security.ApplicationVersion.APP_VERSION;

	public static final String DEFAULT_USER_ID_PARAM_NAME = "userId";

	public static final String DEFAULT_CREDENTIALS_PARAM_NAME = "credentials";

	private String userId;
	
	private String credentials;

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.AuthenticationRequestToken#getUserId()
	 */
	@Override
	public Serializable getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.AuthenticationRequestToken#setUserId(java.io.Serializable)
	 */
	@Override
	public void setUserId(Serializable userId) {
		this.userId = userId.toString();
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.AuthenticationRequestToken#getCredentials()
	 */
	@Override
	public Serializable getCredentials() {
		return credentials;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.AuthenticationRequestToken#setCredentials(java.io.Serializable)
	 */
	@Override
	public void setCredentials(Serializable credentials) {
		this.credentials = credentials.toString();
	}

	@Override
	public String getUserIdParamName() {
		return DEFAULT_USER_ID_PARAM_NAME;
	}

	@Override
	public String getPasswordParamName() {
		return DEFAULT_CREDENTIALS_PARAM_NAME;
	}
	
	
	
	

}
