/**
 * 
 */
package com.eej.security.service.principal;

import org.springframework.security.core.userdetails.UserDetails;

import com.eej.security.handler.model.PrincipalDetails;

/**
 * @author jlumietu
 *
 */
public class PrincipalDetailsBasedBuilder implements PrincipalBuilder {

	/* (non-Javadoc)
	 * @see com.eej.security.service.PrincipalBuilder#getPrincipal(org.springframework.security.core.userdetails.UserDetails)
	 */
	@Override
	public Object getPrincipal(UserDetails userDetails) {
		if(userDetails instanceof PrincipalDetails){
			return ((PrincipalDetails) userDetails).getPrincipal();
		}
		return userDetails.getUsername();
	}

}
