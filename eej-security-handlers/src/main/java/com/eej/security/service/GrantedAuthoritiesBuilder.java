/**
 * 
 */
package com.eej.security.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author DOIBALMI
 *
 */
public interface GrantedAuthoritiesBuilder {
	
	public abstract List<? extends GrantedAuthority> getGrantedAuthorities(UserDetails userDetails); 

}
