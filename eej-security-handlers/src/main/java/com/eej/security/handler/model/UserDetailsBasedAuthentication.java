/**
 * 
 */
package com.eej.security.handler.model;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class UserDetailsBasedAuthentication extends UsernamePasswordAuthenticationToken implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;

	/**
	 * 
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public UserDetailsBasedAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param principal
	 * @param credentials
	 * @param authorities
	 * @param details
	 */
	public UserDetailsBasedAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, Object details) {
		super(principal, credentials, authorities);
		this.setDetails(details);
	}

	/**
	 * 
	 * @param principal
	 * @param credentials
	 */
	public UserDetailsBasedAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDetailsBasedAuthentication(PrincipalDetails principal){
		super(principal.getPrincipal(), principal.getPassword(), principal.getAuthorities());
		this.setDetails(principal);
	}
	
}
