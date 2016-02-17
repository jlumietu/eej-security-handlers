/**
 * 
 */
package com.eej.security.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.eej.security.handler.model.SecurityRoleSpec;

/**
 * @author DOIBALMI
 *
 */
public interface GrantedAuthoritiesRoleBuilder {
	
	public List<? extends GrantedAuthority> getGrantedAuthorities(Collection<? extends SecurityRoleSpec> authList);

}
