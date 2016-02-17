/**
 * 
 */
package com.eej.security.source;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author doibalmi
 *
 */
public interface AuthenticationDetailsGenerator {
	
	public abstract WebAuthenticationDetails generateAuthenticationDetails
		(HttpServletRequest req, String userName);

}
