/**
 * 
 */
package com.eej.security.service;

import com.eej.security.model.LastLoginInformation;

/**
 * @author jlumietu
 *
 */
public interface LastLoginInformationDetailsService {
	
	public LastLoginInformation getLastLoginInformation(int userId);
	
	public void saveLoginInformation(int userId);

}
