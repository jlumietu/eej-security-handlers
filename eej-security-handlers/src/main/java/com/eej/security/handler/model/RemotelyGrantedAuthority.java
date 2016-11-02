/**
 * 
 */
package com.eej.security.handler.model;

import org.springframework.security.core.GrantedAuthority;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class RemotelyGrantedAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	String authority;
	
	
	
	/**
	 * @param authority
	 */
	public RemotelyGrantedAuthority(String authority) {
		super();
		this.authority = authority;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getAuthority();
	}
	
	
	

}
