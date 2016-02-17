/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;

import com.eej.security.handler.ApplicationVersion;

/**
 * @author Mikel
 *
 */
public class SecurityRole implements Serializable, SecurityRoleSpec{

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private int roleId;
	
	private String roleName;
	
	private String roleDesc;

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.SecurityRoleSpec#getRoleId()
	 */
	@Override
	public int getRoleId() {
		return roleId;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.SecurityRoleSpec#setRoleId(int)
	 */
	@Override
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.SecurityRoleSpec#getRoleName()
	 */
	@Override
	public String getRoleName() {
		return roleName;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.SecurityRoleSpec#setRoleName(java.lang.String)
	 */
	@Override
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.SecurityRoleSpec#getRoleDesc()
	 */
	@Override
	public String getRoleDesc() {
		return roleDesc;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.SecurityRoleSpec#setRoleDesc(java.lang.String)
	 */
	@Override
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Override
	public String getAuthority() {
		return this.getRoleName();
	}
	
	

}
