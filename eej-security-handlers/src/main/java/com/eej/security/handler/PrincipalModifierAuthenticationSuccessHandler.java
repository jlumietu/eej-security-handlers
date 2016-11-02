/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class PrincipalModifierAuthenticationSuccessHandler extends 
		SimpleUrlAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private UserDetailsService userDetails;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		logger.debug("On authentication success");
		logger.debug("Authentication.details.class = " + authentication.getDetails().getClass().getName());
		//UserDetails userDetails = this.userDetails.loadUserByUsername(authentication.getName());
		//if(userDetails instanceof PrincipalDetails){
		//	logger.debug("userDetails instanceof PrincipalDetails");
			/*authentication = 
            		new UserDetailsBasedAuthentication(
            				((PrincipalDetails) userDetails).getPrincipal(), 
            				authentication.getCredentials(),
            				userDetails.getAuthorities(),
            				userDetails);/    
			logger.debug("Se supone que ya se ha modificado" + authentication.getClass().getName());
			*/
			
		//}

		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * @return the userDetails
	 */
	public UserDetailsService getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetailsService userDetails) {
		this.userDetails = userDetails;
	}
	
	

}
