/**
 * 
 */
package com.eej.security.handler;

import com.eej.security.handler.model.SecurityHandlerExceptionResolverFactory;


/**
 * @author jlumietu - Mikel Ibiricu Alfaro
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
