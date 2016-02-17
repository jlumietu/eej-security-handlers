/**
 * 
 */
package com.eej.security.service.model;

import java.io.Serializable;

/**
 * @author jlumietu
 *
 */
public interface TokenValidationRequest extends Serializable{
	
	public String getToken();
	
	public void setToken(String token);

}
