/**
 * 
 */
package com.eej.security.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class HeaderUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	/**
	 * 
	 */
	public HeaderUsernamePasswordAuthenticationFilter() {
		super();
		this.setFilterProcessesUrl("/**");
		this.setPostOnly(false);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		return request.getHeader(this.getPasswordParameter());
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return request.getHeader(this.getPasswordParameter());
	}	

}
