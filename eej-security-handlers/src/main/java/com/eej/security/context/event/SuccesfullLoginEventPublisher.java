/**
 * 
 */
package com.eej.security.context.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

import com.eej.security.context.authentication.repository.AuthenticationSuccessRepository;
import com.eej.security.context.authentication.repository.BaseAuthenticationSuccessRepository;
import com.eej.security.context.helper.BaseUserRepositorySerializableIdLocator;
import com.eej.security.context.helper.UserRepositorySerializableIdLocator;
import com.eej.security.handler.model.UserRepositorySerializableId;
import com.erax.principal.PrincipalSerializableId;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class SuccesfullLoginEventPublisher implements ApplicationEventPublisher {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private UserRepositorySerializableIdLocator userIdLocator = new BaseUserRepositorySerializableIdLocator();
	
	private AuthenticationSuccessRepository repository = new BaseAuthenticationSuccessRepository();

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisher#publishEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void publishEvent(ApplicationEvent event) {
		logger.debug("publishEvent(" + event.getClass().getName() + " from " + event.getSource());
		if(event instanceof InteractiveAuthenticationSuccessEvent){
			logger.info("Successfull authentication performed for principal+ " + 
					((InteractiveAuthenticationSuccessEvent) event).getAuthentication().getName());
			PrincipalSerializableId id = 
					userIdLocator.find(((InteractiveAuthenticationSuccessEvent) event).getAuthentication());
			this.publishEvent(id);
		}

	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisher#publishEvent(java.lang.Object)
	 */
	@Override
	public void publishEvent(Object object) {
		if(object != null && object instanceof UserRepositorySerializableId){
			repository.process((PrincipalSerializableId)object);
		}

	}

}
