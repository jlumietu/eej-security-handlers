/**
 * 
 */
package com.eej.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.eej.security.handler.model.SecurityRoleSpec;

/**
 * @author DOIBALMI
 *
 */
public abstract class GrantedAuthoritiesRoleBuilderBase implements
		GrantedAuthoritiesRoleBuilder {

	/* (non-Javadoc)
	 * @see com.eej.security.service.GrantedAuthoritiesRoleBuilder#getGrantedAuthorities(java.util.List)
	 */
	@Override
	public List<? extends GrantedAuthority> getGrantedAuthorities(
			Collection<? extends SecurityRoleSpec> authList) {
		if(authList == null || authList.isEmpty()){
			return new ArrayList<GrantedAuthority>();
		}
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for(SecurityRoleSpec role : authList){
			list.add(this.getGrantedAuthority(role));
		}
		return list;
	}

	public abstract <T extends GrantedAuthority> T getGrantedAuthority(SecurityRoleSpec role);
	
	public abstract boolean supports(Class<? extends SecurityRoleSpec> clazz);

}
