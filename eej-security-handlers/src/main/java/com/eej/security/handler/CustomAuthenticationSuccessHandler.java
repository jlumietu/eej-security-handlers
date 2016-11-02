/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.eej.security.handler.model.SecurityHandlerExceptionResolver;
import com.eej.security.handler.model.SecurityHandlerExceptionResolverFactory;




/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class CustomAuthenticationSuccessHandler extends
	SimpleUrlAuthenticationSuccessHandler implements CustomSecurityExceptionBasedHandler{

	private Logger logger = Logger.getLogger(this.getClass());
	
	private Map<String, String> uriResolverMap;
	
	private SecurityHandlerExceptionResolverFactory securityHandlerResolverFactory;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {

		logger.debug("AdmcpAuthenticationSuccessHandler.onAuthenticationSuccess");
		
		for(Enumeration<?> en = request.getHeaderNames(); en.hasMoreElements();){
			String header = (String)en.nextElement(); 
			logger.debug(header + " = " + request.getHeader(header));
		}
		logger.debug("AdmcpAuthenticationSuccessHandler.onAuthenticationSuccess Fin de headers");
		
		Collection<? extends GrantedAuthority> authorities = 
				SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority authority : authorities){
			logger.debug("AdmcpAuthenticationSuccessHandler.onAuthenticationSuccess Authority:: " + authority.getAuthority());
		}
		
		String referer = request.getHeader("referer"); 
		for(String s: uriResolverMap.keySet()){
			logger.debug(this.getClass().getSimpleName() + 
					".onAuthenticationSuccess: referer= " + referer + ", key=" + s);
			if(referer.contains(s)){
				setDefaultTargetUrl(this.uriResolverMap.get(s));
				logger.debug(this.getClass().getSimpleName() + 
						".onAuthenticationSuccess: set default target= " + this.uriResolverMap.get(s));
				SecurityHandlerExceptionResolver exceptionHandler =
						this.securityHandlerResolverFactory.getHandlerResolverForPattern(referer);
				if(exceptionHandler!=null){
					logger.debug("Valid exceptionHandler found");
					for (GrantedAuthority authority : authorities){
						if(authority.getAuthority().equals(exceptionHandler.getRole())){
							logger.debug("Valid exceptionHandler has rule for role " + authority.getAuthority());
							setDefaultTargetUrl(exceptionHandler.getSuccessLoginUri());
							break;
						}
					}
				}
				break;
			}
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * @return the uriResolverMap
	 */
	public Map<String, String> getUriResolverMap() {
		return uriResolverMap;
	}

	/**
	 * @param uriResolverMap the uriResolverMap to set
	 */
	public void setUriResolverMap(Map<String, String> uriResolverMap) {
		this.uriResolverMap = uriResolverMap;
	}

	@Override
	public SecurityHandlerExceptionResolverFactory getSecurityHandlerResolverFactory() {
		return this.securityHandlerResolverFactory;
	}

	@Override
	public void setSecurityHandlerResolverFactory(
			SecurityHandlerExceptionResolverFactory securityHandlerResolverFactory) {
		this.securityHandlerResolverFactory = securityHandlerResolverFactory;
		
	}
	
	

}
