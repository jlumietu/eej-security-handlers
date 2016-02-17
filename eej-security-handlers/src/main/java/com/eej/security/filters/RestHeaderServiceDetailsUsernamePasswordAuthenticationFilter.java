/**
 * 
 */
package com.eej.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @author DOIBALMI
 *
 */
public class RestHeaderServiceDetailsUsernamePasswordAuthenticationFilter
		extends ServiceDetailsUsernamePasswordAuthenticationFilter {

	private Logger logger = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see com.eej.security.filters.BaseSecurityFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		logger.debug("Entrada en " + this.getClass().getName() + ".doFilter");
		super.doFilter(req, res, chain);
	}

	/* (non-Javadoc)
	 * @see com.eej.security.filters.ServiceDetailsUsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		logger.debug("Header[" + this.getPasswordParameter() + "]=" + request.getHeader(this.getPasswordParameter())); 
		return request.getHeader(this.getPasswordParameter());
	}

	/* (non-Javadoc)
	 * @see com.eej.security.filters.ServiceDetailsUsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		logger.debug("Header[" + this.getUsernameParameter() + "]=" + request.getHeader(this.getUsernameParameter()));
		return request.getHeader(this.getUsernameParameter());
	}

	@Override
	protected boolean matchesRequest(ServletRequest req) {
		if(req instanceof HttpServletRequest){
			return (
					(((HttpServletRequest) req).getHeader(this.getPasswordParameter())!= null)
					&&
					(((HttpServletRequest) req).getHeader(this.getUsernameParameter())!= null)
				);
		}
		return false;
	}

}
