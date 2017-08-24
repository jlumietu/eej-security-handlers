/**
 * 
 */
package com.eej.security.events;

import org.springframework.context.ApplicationEvent;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class InteractiveLogoutSuccessEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;

	public InteractiveLogoutSuccessEvent(Object source) {
		super(source);
	}

}
