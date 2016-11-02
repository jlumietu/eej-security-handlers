/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;
import java.util.List;
import com.eej.security.ApplicationVersion;


/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class SecurityHandlerExceptionResolverFactory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	private List<SecurityHandlerExceptionResolver> handlerResolvers;

	/**
	 * @return the handlerResolvers
	 */
	public List<SecurityHandlerExceptionResolver> getHandlerResolvers() {
		return handlerResolvers;
	}

	/**
	 * @param handlerResolvers the handlerResolvers to set
	 */
	public void setHandlerResolvers(
			List<SecurityHandlerExceptionResolver> handlerResolvers) {
		this.handlerResolvers = handlerResolvers;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public SecurityHandlerExceptionResolver getHandlerResolverForPattern(String url){
		for(SecurityHandlerExceptionResolver handler: handlerResolvers){
			if(url.contains(handler.getPattern())){
				return handler;
			}
		}
		return null;
	} 
	

}
