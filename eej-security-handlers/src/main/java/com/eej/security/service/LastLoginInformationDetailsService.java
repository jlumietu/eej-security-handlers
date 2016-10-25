/**
 * 
 */
package com.eej.security.service;

import java.io.Serializable;

import com.eej.security.model.LastLoginInformation;

/**
 * @author jlumietu
 *
 */
public interface LastLoginInformationDetailsService {
	
	public LastLoginInformation getLastLoginInformation(Serializable userId);
	
	public void saveLoginInformation(Serializable userId);

}
