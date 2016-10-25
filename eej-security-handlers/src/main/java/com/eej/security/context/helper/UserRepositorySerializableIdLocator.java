/**
 * 
 */
package com.eej.security.context.helper;

import org.springframework.security.core.Authentication;

import com.eej.security.handler.model.UserRepositorySerializableId;

/**
 * @author DOIBALMI
 *
 */
public interface UserRepositorySerializableIdLocator {
	
	public abstract UserRepositorySerializableId find(Authentication authentication);

}
