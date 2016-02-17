/**
 * 
 */
package com.eej.security.handler.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author jlumietu
 *
 */
public class MyGrantedAuthority implements GrantedAuthority {

	String authority;
	
	
	
	/**
	 * @param authority
	 */
	public MyGrantedAuthority(String authority) {
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
