/**
 * 
 */
//package net.iberdok.security.exception;
package com.eej.security.exception;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * @author domesanu
 *
 */
public class BadCredentialsNoTokenException extends BadCredentialsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadCredentialsNoTokenException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public BadCredentialsNoTokenException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}
	

}
