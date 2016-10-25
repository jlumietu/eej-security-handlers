/**
 * 
 */
package com.eej.security.filters;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.eej.security.source.AuthenticationDetailsGenerator;
import com.eej.security.source.WebAuthenticationDetailsGenerator;



/**
 * Processes an authentication form submission. Called
 * {@code AuthenticationProcessingFilter} prior to Spring Security 3.0.
 * <p>
 * Login forms must present two parameters to this filter: a username and password. The
 * default parameter names to use are contained in the static fields
 * {@link #SPRING_SECURITY_FORM_USERNAME_KEY} and
 * {@link #SPRING_SECURITY_FORM_PASSWORD_KEY}. The parameter names can also be changed by
 * setting the {@code usernameParameter} and {@code passwordParameter} properties.
 * <p>
 * This filter by default responds to the URL {@code /login}.
 *
 * @author Ben Alex
 * @author Colin Sampaleanu
 * @author Luke Taylor
 * @since 3.0
 */
public class DetailsUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private Logger logger = Logger.getLogger(this.getClass());

	private AuthenticationDetailsGenerator defaultAuthenticationDetails = new WebAuthenticationDetailsGenerator();
	
	private Map<Class<?>, AuthenticationDetailsGenerator> authenticationDetailsMapper;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		Authentication auth = super.attemptAuthentication(request, response);
		if(defaultAuthenticationDetails == null && authenticationDetailsMapper.isEmpty()){
			return auth;
		}
		StringBuffer sb = new StringBuffer("\nAUTH:");
		sb.append("\n\tName:" + auth.getName());
		sb.append("\n\tAuthorities:" + auth.getAuthorities());
		sb.append("\n\tCredentials:" + auth.getCredentials());
		sb.append("\n\tDetails:" + auth.getDetails());
		sb.append("\n\tPrincipal:" + auth.getPrincipal());
		logger.debug("DetailsUsernamePasswordAuthenticationFilter.attemptAuthentication " + sb.toString());
		logger.debug("auth.getPrincipal().getClass()=" + auth.getPrincipal().getClass());
		logger.debug("auth instanceof UsernamePasswordAuthenticationToken?" + (auth instanceof UsernamePasswordAuthenticationToken));
		if (auth instanceof UsernamePasswordAuthenticationToken){
			((UsernamePasswordAuthenticationToken) auth).setDetails(
					this.generateAuthenticationDetails(
							request, 
							auth.getName(),
							auth.getPrincipal()
						)
					);
					/*this.authenticationDetails.generateAuthenticationDetails(
							request,
							auth.getName()							
						)
					);*/
		}
		return auth;
	}

	/**
	 * 
	 * @param request
	 * @param name
	 * @param principal
	 * @return
	 */
	protected WebAuthenticationDetails generateAuthenticationDetails(HttpServletRequest request,
			String name, Object principal) {
		if(this.authenticationDetailsMapper.containsKey(principal.getClass())){
			return this.authenticationDetailsMapper.get(principal.getClass()).generateAuthenticationDetails(request, name);
		}
		return this.defaultAuthenticationDetails.generateAuthenticationDetails(request, name);
	}







	/**
	 * @return the defaultAuthenticationDetails
	 */
	public AuthenticationDetailsGenerator getDefaultAuthenticationDetails() {
		return defaultAuthenticationDetails;
	}

	/**
	 * @param defaultAuthenticationDetails the defaultAuthenticationDetails to set
	 */
	public void setDefaultAuthenticationDetails(
			AuthenticationDetailsGenerator defaultAuthenticationDetails) {
		this.defaultAuthenticationDetails = defaultAuthenticationDetails;
	}

	/**
	 * @return the authenticationDetailsMapper
	 */
	public Map<Class<?>, AuthenticationDetailsGenerator> getAuthenticationDetailsMapper() {
		return authenticationDetailsMapper;
	}

	/**
	 * @param authenticationDetailsMapper the authenticationDetailsMapper to set
	 */
	public void setAuthenticationDetailsMapper(
			Map<Class<?>, AuthenticationDetailsGenerator> authenticationDetailsMapper) {
		this.authenticationDetailsMapper = authenticationDetailsMapper;
	}
}