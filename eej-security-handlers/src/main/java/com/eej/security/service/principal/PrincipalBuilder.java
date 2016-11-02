/**
 * 
 */
package com.eej.security.service.principal;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface PrincipalBuilder {
	
	public abstract Object getPrincipal(UserDetails userDetails);

}
