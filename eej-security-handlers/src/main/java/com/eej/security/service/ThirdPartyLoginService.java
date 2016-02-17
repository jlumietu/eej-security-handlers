/**
 * 
 */
package com.eej.security.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author DOIBALMI
 *
 */
public interface ThirdPartyLoginService{
	
	public abstract UserDetails login(String userName, String password) throws BadCredentialsException;

}
