package com.eej.security.handler.helper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotWritableException;

import com.eej.security.handler.model.RestServiceAccessDeniedMessageBody;

public interface MessageConverterHelper<T> {

	void write(T body, HttpServletRequest request, HttpServletResponse response)
			throws HttpMessageNotWritableException, IOException;

}