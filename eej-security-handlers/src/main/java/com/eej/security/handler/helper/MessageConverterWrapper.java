/**
 * 
 */
package com.eej.security.handler.helper;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;

import com.eej.security.handler.model.RestServiceAccessDeniedMessageBody;

/**
 * @author DOIBALMI
 *
 */
public class MessageConverterWrapper extends GenericMessageConverterWrapper<RestServiceAccessDeniedMessageBody> implements MessageConverterHelper<RestServiceAccessDeniedMessageBody>{
	
	private List<GenericHttpMessageConverter<HttpEntity<?>>> messageConverters;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void write(RestServiceAccessDeniedMessageBody body, HttpServletRequest request, HttpServletResponse response) throws HttpMessageNotWritableException, IOException {
		
		super.write(body, request, response);
		
		/*HttpEntity<RestServiceAccessDeniedMessageBody> httpEntity = 
				new ResponseEntity<RestServiceAccessDeniedMessageBody>(
						body,
						HttpStatus.FORBIDDEN);
		MediaType mediaType = MediaType.valueOf(request.getContentType());
		for(GenericHttpMessageConverter<HttpEntity<?>> messageConverter : this.messageConverters){
			if(messageConverter.canWrite(httpEntity.getClass(), mediaType)){
				HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);			
				messageConverter.write(httpEntity, httpEntity.getClass(), mediaType, outputMessage);				
			}
		}*/
	}

	@Override
	protected Collection<GenericHttpMessageConverter<HttpEntity<?>>> getMessageConverters() {
		return this.messageConverters;
	}

}
