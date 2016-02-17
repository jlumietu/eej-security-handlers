/**
 * 
 */
package com.eej.security.model;

import com.eej.security.ApplicationVersion;

/**
 * @author doibalmi
 *
 */
public abstract class AbstractGroupInfo implements GroupInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private String name;

	/* (non-Javadoc)
	 * @see com.eej.security.model.GroupInfo#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

}
