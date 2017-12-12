/**
 * 
 */
package net.brdk.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author domesanu
 *
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements
		AuthenticationFailureHandler {
	
	private Logger logger = Logger.getLogger(this.getClass());

	private Map<String, String> exceptionClassses = new HashMap<String, String>();
	/*
	 *	map.put("BadCredentialsException", "/miRuta/mi.htm");
	 */
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.debug("CustomAuthenticationFailureHandler inicio");
		try{
			String nombreClase = exception.getClass().getName();
			if (this.exceptionClassses.containsKey(nombreClase)) {
				
				String s = this.exceptionClassses.get(nombreClase);
				if(s != null){
					this.setDefaultFailureUrl(this.exceptionClassses.get(s));
				}
				
				
			} else {
				
				logger.debug("Lanzo ClassNotFoundException ");
				new ClassNotFoundException("La lanzo yo");
			}
			
			
			
			}catch(Exception cnfe){
				logger.debug("ClassNotFoundException " + cnfe.getMessage());
			}
		
		super.onAuthenticationFailure(request, response, exception);
	}

	public Map<String, String> getExceptionClassses() {
		return exceptionClassses;
	}

	public void setExceptionClassses(Map<String, String> exceptionClassses) {
		this.exceptionClassses = exceptionClassses;
	}
	
	

}
