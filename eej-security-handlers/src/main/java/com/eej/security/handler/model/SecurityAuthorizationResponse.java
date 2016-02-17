/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;
import java.util.List;

import com.eej.security.ApplicationVersion;

/**
 * @author DOIBALMI
 *
 */
public class SecurityAuthorizationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private boolean valid;
	
	private List<SecurityRoleSpec> roles;
	
	private String userName;
	

}
