/**
 * 
 */
package com.eej.security.provider;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.eej.security.exception.UserDetailsAuthenticationException;
import com.eej.security.handler.model.PrincipalDetails;
import com.eej.security.handler.model.UserDetailsBasedAuthentication;
import com.eej.security.service.GrantedAuthoritiesBuilder;
import com.eej.security.service.ThirdPartyLoginService;
import com.eej.security.service.principal.PrincipalBuilder;


/**
 * La aproximacion es mala, es mucho mejor basarse en un (@link AbstractUserDetailsAuthenticationProvider)
 * 
 * Creo que lo mejor sería hacer una subclase abstracta de @link AbstractUserDetailsAuthenticationProvider que
 * crease un nuevo método abstracto para realizar alguna tarea posterior al login, además
 * de los que ya se crean.
 * 
 * En el caso de Bonita si que tenía sentido hacer un provider entero, puesto que la especificación
 * de un @link UserDetailsService, loadUserByUsername(String username) no incluye el password
 * como parámetro.
 * 
 * Por lo tanto, la pregunta a la hora de abordar un nuveo modo de autenticación es:
 * ¿La fuente de usuarios requiere que le enviemos el password para validar, o simplemente con
 * el usuario vale?
 * Si simplemente tenemos que enviar el userName, es mucha mejor aproximación crear un nuevo
 * UserDetailsService y listo.
 * 
 * Es cierto que ...
 * 
 * @author jlumietu
 *
 */
@Deprecated
public class ThirdPartyAuthenticationProvider implements AuthenticationProvider {
	
	private Logger logger = Logger.getLogger(this.getClass());

	private ThirdPartyLoginService loginService;

	private GrantedAuthoritiesBuilder grantedAuthoritiesBuilder;
	
	private PrincipalBuilder principalBuilder;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Assert.notNull(this.loginService, "LoginService cannot be null");
		UserDetails userDetails = null;
		try {
            logger.debug("Authentication attempt name: " + authentication.getName());
            logger.debug("Authentication attempt principal: " + authentication.getPrincipal());
            logger.debug("Authentication attempt credentials: " + authentication.getCredentials());
            /*logger.debug("Authentication attempt credentials: " + 
            		((UsernamePasswordAuthenticationToken)authentication).);*/
            userDetails = this.loginService.login(authentication.getName(), authentication.getCredentials().toString());
            if(userDetails == null){
            	throw new BadCredentialsException("Login attempt failed for provided username " + authentication.getName());
            }
            if(!userDetails.isAccountNonLocked()){
            	throw new LockedException("Account is locked");
            }
            if(!userDetails.isEnabled()){
            	throw new DisabledException("Account is disabled");
            }
            
            List<? extends GrantedAuthority> authorities = null;
            if(this.grantedAuthoritiesBuilder != null){
            	authorities = this.grantedAuthoritiesBuilder.getGrantedAuthorities(userDetails);
            }else{
            	authorities = (List<GrantedAuthority>)userDetails.getAuthorities();
            }
            
            Object principal = null;
            if(this.principalBuilder != null){
            	principal = this.principalBuilder.getPrincipal(userDetails);
            }else if(userDetails instanceof PrincipalDetails){
            	principal = ((PrincipalDetails) userDetails).getPrincipal();
            }else{            	
            	principal = userDetails.getUsername();
            }
            
            Authentication auth = 
            		new UserDetailsBasedAuthentication(
            				principal, 
            				authentication.getCredentials(),
            				authorities,
            				userDetails);            
            
            return auth;
            
        } catch (Exception ex) {
            throw new UserDetailsAuthenticationException(
            		"Login error: " + ex.getMessage(), 
            		ex,
            		userDetails
            );       
        }
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/**
	 * @return the grantedAuthoritiesBuilder
	 */
	public GrantedAuthoritiesBuilder getGrantedAuthoritiesBuilder() {
		return grantedAuthoritiesBuilder;
	}

	/**
	 * @param grantedAuthoritiesBuilder the grantedAuthoritiesBuilder to set
	 */
	public void setGrantedAuthoritiesBuilder(
			GrantedAuthoritiesBuilder grantedAuthoritiesBuilder) {
		this.grantedAuthoritiesBuilder = grantedAuthoritiesBuilder;
	}

	/**
	 * @return the loginService
	 */
	public ThirdPartyLoginService getLoginService() {
		return loginService;
	}

	/**
	 * @param loginService the loginService to set
	 */
	public void setLoginService(ThirdPartyLoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * @return the principalBuilder
	 */
	public PrincipalBuilder getPrincipalBuilder() {
		return principalBuilder;
	}

	/**
	 * @param principalBuilder the principalBuilder to set
	 */
	public void setPrincipalBuilder(PrincipalBuilder principalBuilder) {
		this.principalBuilder = principalBuilder;
	}

	
}
