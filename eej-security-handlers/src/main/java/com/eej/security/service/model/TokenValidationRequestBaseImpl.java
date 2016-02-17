/**
 * 
 */
package com.eej.security.service.model;

/**
 * @author jlumietu
 *
 */
public class TokenValidationRequestBaseImpl implements TokenValidationRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;

	/* (non-Javadoc)
	 * @see com.eej.security.service.model.TokenValidationRequest#getToken()
	 */
	@Override
	public String getToken() {
		return this.token;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.service.model.TokenValidationRequest#setToken()
	 */
	@Override
	public void setToken(String token) {
		// TODO Auto-generated method stub
		this.token = token;
	}

}
