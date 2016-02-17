/**
 * 
 */
package com.eej.security.model;

import com.eej.security.ApplicationVersion;

/**
 * @author doibalmi
 *
 */
public abstract class AbstractUser implements User{

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private String name;
	private int id;
	private String email;
	private LastLoginInformation lastLoginInformation;
	private GroupInfo groupInfo;

	/* (non-Javadoc)
	 * @see com.eej.security.model.User#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.model.User#getId()
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.model.User#getEmail()
	 */
	@Override
	public String getEmail() {
		return this.email;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.model.User#getLastLoginInformation()
	 */
	@Override
	public LastLoginInformation getLastLoginInformation() {
		return this.lastLoginInformation;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.model.User#getGroupInfo()
	 */
	@Override
	public GroupInfo getGroupInfo() {
		return this.groupInfo;
	}

}
