/**
 * 
 */
package com.eej.security.exception;

import org.springframework.security.core.userdetails.UserDetails;

import com.eej.security.ApplicationVersion;

/**
 * @author DOIBALMI
 *
 */
public class UserDetailsAuthenticationException extends
		BaseAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private UserDetails userDetails;

	/**
	 * @param msg
	 * @param t
	 */
	public UserDetailsAuthenticationException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public UserDetailsAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param msg
	 * @param t
	 * @param userDetails
	 */
	public UserDetailsAuthenticationException(String msg, Throwable t, UserDetails userDetails) {
		super(msg, t);
		this.userDetails = userDetails;
	}

	/**
	 * 
	 * @return
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	
	
}
