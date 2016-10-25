/**
 * 
 */
package com.eej.security.context.helper;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;

import com.eej.security.handler.model.UserRepositorySerializableId;

/**
 * Tries to find any UserRepositorySerializableId instance object in some locations inside the Authentication
 * object received as param.
 * 
 * @author DOIBALMI
 *
 */
public class BaseUserRepositorySerializableIdLocator implements UserRepositorySerializableIdLocator {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.eej.security.context.helper.UserRepositorySerializableIdLocator#find(org.springframework.security.core.Authentication)
	 */
	@Override
	public UserRepositorySerializableId find(Authentication authentication) {
		UserRepositorySerializableId result = null;
		result = this.searchInPrincipal(authentication);
		if(result != null){
			return result;
		}
		result = this.searchInDetails(authentication);
		return result;
	}

	/**
	 * 
	 * @param authentication
	 * @return
	 */
	private UserRepositorySerializableId searchInDetails(Authentication authentication) {
		Object principal = authentication.getDetails();
		UserRepositorySerializableId result = null;
		result = this.searchInObject(principal);
		return result;
	}

	/**
	 * 
	 * @param authentication
	 * @return
	 */
	private UserRepositorySerializableId searchInPrincipal(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		UserRepositorySerializableId result = null;
		result = this.searchInObject(principal);
		return result;
	}

	protected UserRepositorySerializableId searchInObject(Object object) {
		if(object instanceof UserRepositorySerializableId){
			return (UserRepositorySerializableId)object;
		}
		Class<? extends Object> clazz = object.getClass();
		for(Field f: clazz.getDeclaredFields()){
			f.setAccessible(true);
			if(UserRepositorySerializableId.class.isAssignableFrom(f.getType())){
				try {
					return (UserRepositorySerializableId)f.get(object);
				} catch (IllegalArgumentException e) {
					logger.warn("Error accesing " + object.getClass().getName() 
								+ " field " + f.getName() + " = " + e.getMessage(), e);
				} catch (IllegalAccessException e) {
					logger.warn("Error accesing " + object.getClass().getName() 
							+ " field " + f.getName() + " = " + e.getMessage(), e);
				}
			}
		}
		return null;
	}

}
