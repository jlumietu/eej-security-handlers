/**
 * 
 */
package net.brdk.security.handler.model;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author DOMESANU
 *
 */
public class UsernameSsoCodeAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameSsoCodeAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	/**
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public UsernameSsoCodeAuthenticationToken(Object principal,
			Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}	
	
	

}
