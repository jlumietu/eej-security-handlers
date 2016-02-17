/**
 * 
 */
package com.eej.security.handler;

import com.eej.security.handler.model.SecurityHandlerExceptionResolverFactory;


/**
 * @author jlumietu
 *
 */
public interface CustomSecurityExceptionBasedHandler {

	/**
	 * 
	 * @return
	 */
	SecurityHandlerExceptionResolverFactory getSecurityHandlerResolverFactory();
	
	/**
	 * 
	 * @param securityHandlerResolverFactory
	 */
	void setSecurityHandlerResolverFactory(SecurityHandlerExceptionResolverFactory securityHandlerResolverFactory);
	
}
