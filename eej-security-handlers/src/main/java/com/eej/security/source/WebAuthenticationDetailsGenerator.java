/**
 * 
 */
package com.eej.security.source;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author jlumietu
 *
 */
public class WebAuthenticationDetailsGenerator implements
		AuthenticationDetailsGenerator {

	/* (non-Javadoc)
	 * @see com.eej.security.source.AuthenticationDetailsGenerator#generateAuthenticationDetails(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public WebAuthenticationDetails generateAuthenticationDetails(
			HttpServletRequest req, String userName) {
		return new WebAuthenticationDetails(req);
	}

}
