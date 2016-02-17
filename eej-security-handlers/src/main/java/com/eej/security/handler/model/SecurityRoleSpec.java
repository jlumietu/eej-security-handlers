package com.eej.security.handler.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author DOIBALMI
 *
 */
public interface SecurityRoleSpec extends GrantedAuthority{

	/**
	 * @return the roleId
	 */
	public abstract int getRoleId();

	/**
	 * @param roleId the roleId to set
	 */
	public abstract void setRoleId(int roleId);

	/**
	 * @return the roleName
	 */
	public abstract String getRoleName();

	/**
	 * @param roleName the roleName to set
	 */
	public abstract void setRoleName(String roleName);

	/**
	 * @return the roleDesc
	 */
	public abstract String getRoleDesc();

	/**
	 * @param roleDesc the roleDesc to set
	 */
	public abstract void setRoleDesc(String roleDesc);

}