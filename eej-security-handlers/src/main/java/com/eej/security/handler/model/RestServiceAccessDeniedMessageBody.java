package com.eej.security.handler.model;

import java.io.Serializable;

/**
 * 
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface RestServiceAccessDeniedMessageBody extends Serializable{

	int getStatusCode();

	void setStatusCode(int statusCode);

	String getMessage();

	void setMessage(String message);

}