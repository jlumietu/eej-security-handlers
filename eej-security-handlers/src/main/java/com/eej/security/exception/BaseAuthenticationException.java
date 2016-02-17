/**
 * 
 */
package com.eej.security.exception;

import org.springframework.security.core.AuthenticationException;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu
 *
 */
public class BaseAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;

	/**
	 * @param msg
	 * @param t
	 */
	public BaseAuthenticationException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public BaseAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	
	
	

}
