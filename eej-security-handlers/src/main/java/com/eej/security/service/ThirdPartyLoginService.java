/**
 * 
 */
package com.eej.security.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author jlumietu
 *
 */
public interface ThirdPartyLoginService{
	
	public abstract UserDetails login(String userName, String password) throws BadCredentialsException;

}
