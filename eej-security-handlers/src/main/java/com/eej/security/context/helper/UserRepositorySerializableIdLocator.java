/**
 * 
 */
package com.eej.security.context.helper;

import org.springframework.security.core.Authentication;

import com.erax.principal.PrincipalSerializableId;
import com.erax.principal.PrincipalSerializableIdLocator;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface UserRepositorySerializableIdLocator extends PrincipalSerializableIdLocator {
	
	public abstract PrincipalSerializableId find(Authentication authentication);

}
