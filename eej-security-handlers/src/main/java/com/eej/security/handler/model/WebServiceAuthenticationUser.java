/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu
 *
 */
public class WebServiceAuthenticationUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private String userName;
	
	private boolean authenticated;
	
	private String userType;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the authenticated
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/**
	 * @param authenticated the authenticated to set
	 */
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
