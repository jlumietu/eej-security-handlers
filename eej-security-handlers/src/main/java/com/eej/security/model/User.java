/**
 * 
 */
package com.eej.security.model;

import java.io.Serializable;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface User extends Serializable{
	
	String getName();

	Serializable getId();
	
	String getEmail();
	
	LastLoginInformation getLastLoginInformation();
	
	public GroupInfo getGroupInfo();

}
