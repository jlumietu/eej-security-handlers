/**
 * 
 */
package com.eej.security.context.authentication.repository;

import com.eej.security.handler.model.UserRepositorySerializableId;

/**
 * @author DOIBALMI
 *
 */
public interface AuthenticationSuccessRepository {
	
	public abstract void process(UserRepositorySerializableId userSerializableId);

}
