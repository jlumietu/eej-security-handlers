/**
 * 
 */
package com.eej.security.exception;

import org.springframework.security.access.AuthorizationServiceException;

import com.eej.security.ApplicationVersion;
import com.eej.security.handler.model.SecurityRoleSpec;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class UnsupportedSecurityRoleSpecException extends
		AuthorizationServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private Class<? extends SecurityRoleSpec> expected;
	
	private Class<? extends Object> received;

	public UnsupportedSecurityRoleSpecException(String msg) {
		super(msg);
	}

	/**
	 * @param msg
	 * @param t
	 */
	public UnsupportedSecurityRoleSpecException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 * @param expected
	 * @param received
	 */
	public UnsupportedSecurityRoleSpecException(String msg,
			Class<? extends SecurityRoleSpec> expected,
			Class<? extends Object> received) {
		super(msg);
		this.expected = expected;
		this.received = received;
	}

	/**
	 * @param msg
	 * @param t
	 * @param expected
	 * @param received
	 */
	public UnsupportedSecurityRoleSpecException(String msg, Throwable t,
			Class<? extends SecurityRoleSpec> expected,
			Class<? extends Object> received) {
		super(msg, t);
		this.expected = expected;
		this.received = received;
	}
	
	/**
	 * @param msg
	 * @param expected
	 * @param received
	 */
	public UnsupportedSecurityRoleSpecException(
			Class<? extends SecurityRoleSpec> expected,
			Class<? extends Object> received) {
		super("Unsupported class; expected " + expected.getName() + ", received" + received.getName());
		this.expected = expected;
		this.received = received;
	}

	/**
	 * @return the expected
	 */
	public Class<? extends SecurityRoleSpec> getExpected() {
		return expected;
	}

	/**
	 * @param expected the expected to set
	 */
	public void setExpected(Class<? extends SecurityRoleSpec> expected) {
		this.expected = expected;
	}

	/**
	 * @return the received
	 */
	public Class<? extends Object> getReceived() {
		return received;
	}

	/**
	 * @param received the received to set
	 */
	public void setReceived(Class<? extends Object> received) {
		this.received = received;
	}
	

}
