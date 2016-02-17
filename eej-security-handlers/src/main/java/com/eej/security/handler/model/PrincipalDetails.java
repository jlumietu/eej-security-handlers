/**
 * 
 */
package com.eej.security.handler.model;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jlumietu
 *
 */
public interface PrincipalDetails extends Principal, UserDetails,
		ThreePartPrincipal {
	
	public abstract Principal getPrincipal(); 

}
