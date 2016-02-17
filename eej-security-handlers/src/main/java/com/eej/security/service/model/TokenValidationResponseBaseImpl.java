/**
 * 
 */
package com.eej.security.service.model;

/**
 * @author jlumietu
 *
 */
public class TokenValidationResponseBaseImpl implements TokenValidationResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean valid;

	@Override
	public boolean isValid() {
		return this.valid;
	}

	@Override
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
