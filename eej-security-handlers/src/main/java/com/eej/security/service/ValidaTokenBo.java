/**
 * 
 */
package com.eej.security.service;

import com.eej.security.service.model.TokenValidationRequest;
import com.eej.security.service.model.TokenValidationResponse;

/**
 * @author jlumietu
 *
 */
public interface ValidaTokenBo {

	public TokenValidationResponse validar(TokenValidationRequest token);
	
}
