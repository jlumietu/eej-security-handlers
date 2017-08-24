/**
 * 
 */
package com.eej.security.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.eej.security.handler.model.AuthenticationRequestToken;
import com.eej.security.handler.model.RestRequestBodyAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class RestBodyServiceDetailsUsernamePasswordAuthenticationFilter
		extends ServiceDetailsUsernamePasswordAuthenticationFilter implements Filter {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return super.attemptAuthentication(request, response);
	}

	/* (non-Javadoc)
	 * @see com.eej.security.filters.ServiceDetailsUsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.filters.ServiceDetailsUsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eej.security.filters.ServiceDetailsUsernamePasswordAuthenticationFilter#matchesRequest(javax.servlet.ServletRequest)
	 */
	@Override
	protected boolean matchesRequest(ServletRequest req) {
		StringBuffer sb = new StringBuffer();
		BufferedReader bufferedReader = null;
		String content = "";
		boolean matches = false;
		try {
			//InputStream inputStream = request.getInputStream();
			//inputStream.available();
			//if (inputStream != null) {
			bufferedReader =  req.getReader() ; //new BufferedReader(new InputStreamReader(inputStream));
			char[] charBuffer = new char[128];
			int bytesRead;
			while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
				sb.append(charBuffer, 0, bytesRead);
			}
			content = sb.toString();
			logger.debug("userIdParamName = "+ this.getUsernameParameter());
			logger.debug("TokenParamName = "+ this.getPasswordParameter());
			logger.debug("RequestBody Content = " + content);
			if(content.contains(this.getUsernameParameter()) && content.contains(this.getPasswordParameter())){
				matches =  true;
			}       

		} catch (IOException ex) {
			logger.error("Error ioException " + ex.getMessage(),ex);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					logger.error("Error ioException closing bufferedReader " + ex.getMessage(),ex);
				}
			}
		}
		logger.debug("matches ? =" + matches);
		return matches;
	}
	
	/**
	 * 
	 * @param reader
	 * @param clientDetails
	 * @return
	 * @throws IOException
	 */
	protected Authentication getSecuredRequestFromBody(BufferedReader reader, Serializable clientDetails) throws IOException{
		StringBuffer sb = new StringBuffer();
		BufferedReader bufferedReader = null;
		String content = "";
		AuthenticationRequestToken sr = null;

		try {
			//InputStream inputStream = request.getInputStream();
			//inputStream.available();
			//if (inputStream != null) {
			bufferedReader =  reader ; //new BufferedReader(new InputStreamReader(inputStream));
			char[] charBuffer = new char[128];
			int bytesRead;
			while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
				sb.append(charBuffer, 0, bytesRead);
			}
			content = sb.toString();
			logger.debug("RequestBody Content = " + content);
			logger.debug("User Id param name = " + this.getUsernameParameter());
			logger.debug("Token param name = " + this.getPasswordParameter());
			if(content.contains(this.getUsernameParameter()) && content.contains(this.getPasswordParameter())){
				ObjectMapper objectMapper = new ObjectMapper();
				content = content.replace(this.getUsernameParameter(), RestRequestBodyAuthenticationToken.DEFAULT_USER_ID_PARAM_NAME);
				content = content.replace(this.getPasswordParameter(), RestRequestBodyAuthenticationToken.DEFAULT_CREDENTIALS_PARAM_NAME);
				try{
					logger.debug("Content before desealizing: " + content);
					sr = objectMapper.readValue(content, RestRequestBodyAuthenticationToken.class);
					//sr.
				}catch(Throwable t){
					logger.error("Error mappgin json readValue: " + t.getMessage(), t);
					throw new IOException(t.getMessage(), t);
				}
			}       

		} catch (IOException ex) {
			logger.error("Error ioException " + ex.getMessage(),ex);
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					logger.error("Error ioException closing bufferedReader " + ex.getMessage(),ex);
					throw ex;
				}
			}
		}
		logger.debug("sr.userId : " + sr.getUserId());
		logger.debug("sr.token : " + sr.getCredentials());
		return new UsernamePasswordAuthenticationToken(sr.getUserId(), sr.getCredentials());
	}

}
