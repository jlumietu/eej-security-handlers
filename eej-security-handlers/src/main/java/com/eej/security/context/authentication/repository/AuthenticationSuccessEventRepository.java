/**
 * 
 */
package com.eej.security.context.authentication.repository;

import com.eej.security.handler.model.UserRepositorySerializableId;

/**
 * @author DOIBALMI
 *
 */
public interface AuthenticationSuccessEventRepository {
	
	public abstract void publishEvent(UserRepositorySerializableId userSerializableId);

}
