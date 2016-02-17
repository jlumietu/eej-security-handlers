/**
 * 
 */
package com.eej.security.service.model;

import java.io.Serializable;

/**
 * @author DOIBALMI
 *
 */
public interface TokenValidationResponse extends Serializable{
	
	public boolean isValid();
	
	public void setValid(boolean valid);

}
