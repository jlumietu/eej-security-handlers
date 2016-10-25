/**
 * 
 */
package com.eej.security.context.authentication.repository;

import org.apache.log4j.Logger;

import com.eej.security.handler.model.UserRepositorySerializableId;

/**
 * @author DOIBALMI
 *
 */
public class BaseAuthenticationSuccessEventRepository implements AuthenticationSuccessEventRepository {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.eej.security.context.authentication.repository.AuthenticationSuccessEventRepository#publishEvent(com.eej.security.handler.model.UserRepositorySerializableId)
	 */
	@Override
	public void publishEvent(UserRepositorySerializableId userSerializableId) {
		if(logger.isDebugEnabled()){
			StringBuilder sb = new StringBuilder(userSerializableId.getClass().getName());
			sb.append(" with id ");
			sb.append(userSerializableId.getId());
			logger.debug(sb.toString());
		}
	}

}
