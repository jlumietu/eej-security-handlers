/**
 * 
 */
package com.eej.security.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.eej.security.source.AuthenticationDetailsGenerator;
import com.eej.security.source.WebAuthenticationDetailsGenerator;

/**
 * @author jlumietu
 *
 */
public abstract class ServiceDetailsUsernamePasswordAuthenticationFilter extends
		CustomUsernamePasswordBasedSecurityFilter {

	private Logger logger = Logger.getLogger(this.getClass());

	private AuthenticationDetailsGenerator defaultAuthenticationDetails = new WebAuthenticationDetailsGenerator();
	
	private Map<Class<?>, AuthenticationDetailsGenerator> authenticationDetailsMapper;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		logger.debug(this.getClass().getName() + "doFilter request class " + req.getClass().getName());
		if(!this.alreadySecured()){
			try{
				if(this.matchesRequest(req)){
					SecurityContext context = SecurityContextHolder.getContext();
					Authentication auth = this.attemptAuthentication((HttpServletRequest)req, (HttpServletResponse)res);
					context.setAuthentication(auth);
					((HttpServletRequest)req).getSession().setMaxInactiveInterval(1);					
				}
			}catch(AuthenticationException authenticationException){
				logger.debug("Authentication exception " + authenticationException.getMessage());
			}
			logger.debug(this.getClass().getName() + " continue filterChain");
		}
		fc.doFilter(req, res);
	}
	
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

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected abstract String obtainPassword(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected abstract String obtainUsername(HttpServletRequest request);
	
	protected abstract boolean matchesRequest(ServletRequest req);

	/**
	 * 
	 * @return
	 */
	protected boolean alreadySecured() {
		SecurityContext context = SecurityContextHolder.getContext();
		logger.debug("context.getAuthentication() = " + 
				(context.getAuthentication()==null?
						null:context.getAuthentication().getClass().getName()+", isAuthenticated=" + context.getAuthentication().isAuthenticated()));
		if(context.getAuthentication() != null && context.getAuthentication().isAuthenticated()){
			logger.debug("Already authenticated " + context.getAuthentication().getName());
			return true;
		}
		logger.debug("Not Already authenticated ");
		return false;
	}
	
	

}
