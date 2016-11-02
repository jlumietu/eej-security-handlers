/**
 * 
 */
package com.eej.security.model;

import java.util.Date;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public abstract class AbstractLastLoginInformation implements LastLoginInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private Date lastLogin;
	
	/**
	 * @param lastLogin
	 */
	public AbstractLastLoginInformation(Date lastLogin) {
		super();
		this.lastLogin = lastLogin;
	}



	/* (non-Javadoc)
	 * @see com.eej.security.model.LastLoginInformation#getlastLogin()
	 */
	@Override
	public Date getLastLogin() {
		return this.lastLogin;
	}

}
