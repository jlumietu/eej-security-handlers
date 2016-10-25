/**
 * 
 */
package com.eej.security.context.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

import com.eej.security.context.authentication.repository.AuthenticationSuccessEventRepository;
import com.eej.security.context.authentication.repository.BaseAuthenticationSuccessEventRepository;
import com.eej.security.context.helper.BaseUserRepositorySerializableIdLocator;
import com.eej.security.context.helper.UserRepositorySerializableIdLocator;
import com.eej.security.handler.model.UserRepositorySerializableId;

/**
 * @author DOIBALMI
 *
 */
public class SuccesfullLoginEventPublisher implements ApplicationEventPublisher {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private UserRepositorySerializableIdLocator userIdLocator = new BaseUserRepositorySerializableIdLocator();
	
	private AuthenticationSuccessEventRepository repository = new BaseAuthenticationSuccessEventRepository();

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisher#publishEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void publishEvent(ApplicationEvent event) {
		if(event instanceof InteractiveAuthenticationSuccessEvent){
			logger.info("Successfull authentication performed for principal+ " + 
					((InteractiveAuthenticationSuccessEvent) event).getAuthentication().getName());
			UserRepositorySerializableId id = 
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
			repository.publishEvent((UserRepositorySerializableId)object);
		}

	}

}
