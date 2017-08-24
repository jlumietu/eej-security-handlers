/**
 * 
 */
package com.eej.security.context.helper;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.eej.security.handler.model.UserRepositorySerializableId;
import com.erax.principal.PrincipalSerializableId;

/**
 * Tries to find any UserRepositorySerializableId instance object in some locations inside the Authentication
 * object received as param.
 * 
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class BaseUserRepositorySerializableIdLocator implements UserRepositorySerializableIdLocator {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.eej.security.context.helper.UserRepositorySerializableIdLocator#find(org.springframework.security.core.Authentication)
	 */
	@Override
	public PrincipalSerializableId find(Authentication authentication) {
		PrincipalSerializableId result = null;
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
	private PrincipalSerializableId searchInDetails(Authentication authentication) {
		Object principal = authentication.getDetails();
		PrincipalSerializableId result = null;
		result = this.searchInObject(principal);
		logger.debug("looking for PrincipalSerializableId in details, success? " + 
				(result!=null?result.getClass().getName() + " = " + result.getId():"not found")
				);
		return result;
	}

	/**
	 * 
	 * @param authentication
	 * @return
	 */
	private PrincipalSerializableId searchInPrincipal(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		PrincipalSerializableId result = null;
		result = this.searchInObject(principal);
		logger.debug("looking for PrincipalSerializableId in principal, success? " + 
				(result!=null?result.getClass().getName() + " = " + result.getId():"not found")
				);
		return result;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	protected PrincipalSerializableId searchInObject(Object object) {
		if(object instanceof UserRepositorySerializableId){
			return (PrincipalSerializableId)object;
		}
		Class<? extends Object> clazz = object.getClass();
		for(Field f: clazz.getDeclaredFields()){
			f.setAccessible(true);
			if(UserRepositorySerializableId.class.isAssignableFrom(f.getType())){
				try {
					return (PrincipalSerializableId)f.get(object);
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

	@Override
	public PrincipalSerializableId findPrincipalSerializableId() {
		SecurityContext sc = SecurityContextHolder.getContext();
		return this.find(sc.getAuthentication());
	}

}
