/**
 * 
 */
package com.eej.security.model;

import java.io.Serializable;

/**
 * @author DOIBALMI
 *
 */
public interface User extends Serializable{
	
	String getName();

	int getId();
	
	String getEmail();
	
	LastLoginInformation getLastLoginInformation();
	
	public GroupInfo getGroupInfo();

}
