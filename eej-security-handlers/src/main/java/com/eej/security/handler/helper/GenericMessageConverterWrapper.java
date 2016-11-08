package com.eej.security.handler.helper;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;


public abstract class GenericMessageConverterWrapper<T> implements MessageConverterHelper<T>{

	public GenericMessageConverterWrapper() {
		super();
	}

	@Override
	public void write(T body, HttpServletRequest request, HttpServletResponse response) throws HttpMessageNotWritableException, IOException {
		HttpEntity<T> httpEntity = 
				new ResponseEntity<T>(
						body,
						HttpStatus.FORBIDDEN);
		MediaType mediaType = MediaType.valueOf(request.getContentType());
		for(GenericHttpMessageConverter<HttpEntity<?>> messageConverter : this.getMessageConverters()){
			if(messageConverter.canWrite(httpEntity.getClass(), mediaType)){
				HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);			
				messageConverter.write(httpEntity, httpEntity.getClass(), mediaType, outputMessage);	
				return;
			}
		}
	}

	protected abstract Collection<GenericHttpMessageConverter<HttpEntity<?>>> getMessageConverters();

}