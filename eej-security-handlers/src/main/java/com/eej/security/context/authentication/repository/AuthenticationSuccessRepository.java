/**
 * 
 */
package com.eej.security.context.authentication.repository;

import com.erax.principal.PrincipalSerializableId;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface AuthenticationSuccessRepository {
	
	public abstract void process(PrincipalSerializableId userSerializableId);

}
