/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;

import com.eej.security.handler.ApplicationVersion;



/**
 * @author DOIBALMI
 *
 */
public class SecurityHandlerExceptionResolver implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private String pattern;
	
	private String role;
	
	private String successLoginUri;
	
	private String failureLoginUri;
	
	private String successLogoutUri;

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the successLoginUri
	 */
	public String getSuccessLoginUri() {
		return successLoginUri;
	}

	/**
	 * @param successLoginUri the successLoginUri to set
	 */
	public void setSuccessLoginUri(String successLoginUri) {
		this.successLoginUri = successLoginUri;
	}

	/**
	 * @return the failureLoginUri
	 */
	public String getFailureLoginUri() {
		return failureLoginUri;
	}

	/**
	 * @param failureLoginUri the failureLoginUri to set
	 */
	public void setFailureLoginUri(String failureLoginUri) {
		this.failureLoginUri = failureLoginUri;
	}

	/**
	 * @return the successLogoutUri
	 */
	public String getSuccessLogoutUri() {
		return successLogoutUri;
	}

	/**
	 * @param successLogoutUri the successLogoutUri to set
	 */
	public void setSuccessLogoutUri(String successLogoutUri) {
		this.successLogoutUri = successLogoutUri;
	}

}
