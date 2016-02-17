/**
 * 
 */
package com.eej.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author DOIBALMI
 *
 */
public interface LoginServiceDao {
	
	public UserDetails login(String user, String password) throws UsernameNotFoundException;

}
