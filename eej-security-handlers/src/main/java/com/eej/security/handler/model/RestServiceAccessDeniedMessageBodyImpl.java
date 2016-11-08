/**
 * 
 */
package com.eej.security.handler.model;

import com.eej.security.ApplicationVersion;

/**
 * @author DOIBALMI
 *
 */
public class RestServiceAccessDeniedMessageBodyImpl implements RestServiceAccessDeniedMessageBody {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;

	private int statusCode;
	
	private String message;
	
	/**
	 * 
	 * @param statusCode
	 * @param message
	 */
	public RestServiceAccessDeniedMessageBodyImpl(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public RestServiceAccessDeniedMessageBodyImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.RestServiceAccessDeniedMessageBody#getStatusCode()
	 */
	@Override
	public int getStatusCode() {
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.RestServiceAccessDeniedMessageBody#setStatusCode(int)
	 */
	@Override
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.RestServiceAccessDeniedMessageBody#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.handler.model.RestServiceAccessDeniedMessageBody#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
