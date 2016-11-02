/**
 * 
 */
package com.eej.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface LoginServiceDao {
	
	public UserDetails login(String user, String password) throws UsernameNotFoundException;

}
