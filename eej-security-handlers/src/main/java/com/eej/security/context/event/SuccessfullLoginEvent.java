/**
 * 
 */
package com.eej.security.context.event;

import org.springframework.context.ApplicationEvent;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class SuccessfullLoginEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;

	public SuccessfullLoginEvent(Object source) {
		super(source);
	}
	
	

}
