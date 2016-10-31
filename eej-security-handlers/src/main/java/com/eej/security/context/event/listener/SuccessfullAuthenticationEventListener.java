/**
 * 
 */
package com.eej.security.context.event.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

import com.eej.security.context.authentication.repository.AuthenticationSuccessRepository;
import com.eej.security.context.authentication.repository.BaseAuthenticationSuccessRepository;
import com.eej.security.context.helper.BaseUserRepositorySerializableIdLocator;
import com.eej.security.context.helper.UserRepositorySerializableIdLocator;
import com.erax.principal.PrincipalSerializableId;
import com.erax.principal.PrincipalSerializableIdLocator;

/**
 * @author DOIBALMI
 *
 */
public class SuccessfullAuthenticationEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private UserRepositorySerializableIdLocator userIdLocator = new BaseUserRepositorySerializableIdLocator();
	
	private AuthenticationSuccessRepository repository = new BaseAuthenticationSuccessRepository();
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		logger.info("Successfull authentication performed for principal+ " + 
				((InteractiveAuthenticationSuccessEvent) event).getAuthentication().getName());
		PrincipalSerializableId id = 
				userIdLocator.find(((InteractiveAuthenticationSuccessEvent) event).getAuthentication());
		logger.debug("UserRepositorySerializableId found? " + (id==null?"null, not found":id.getClass().getName() + " = " + id.getId()));
		repository.process(id);		
	}

	public PrincipalSerializableIdLocator getUserIdLocator() {
		return userIdLocator;
	}

	public void setUserIdLocator(UserRepositorySerializableIdLocator userIdLocator) {
		this.userIdLocator = userIdLocator;
	}

	public AuthenticationSuccessRepository getRepository() {
		return repository;
	}

	public void setRepository(AuthenticationSuccessRepository repository) {
		this.repository = repository;
	}
	
	

}
