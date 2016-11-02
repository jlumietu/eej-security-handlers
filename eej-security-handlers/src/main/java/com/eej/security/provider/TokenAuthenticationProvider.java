/**
 * 
 */
package com.eej.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.eej.security.handler.model.MyGrantedAuthority;
import com.eej.security.handler.model.UsernameSsoCodeAuthenticationToken;
import com.eej.security.service.ValidaTokenBo;
import com.eej.security.service.model.TokenValidationRequest;
import com.eej.security.service.model.TokenValidationRequestBaseImpl;
import com.eej.security.service.model.TokenValidationResponse;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private ValidaTokenBo validaToken;
	
	

	/**
	 * 
	 */
	public TokenAuthenticationProvider() {
		super();
		logger.info("TokenAuthenticationProvider()!");
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		
		logger.info("Entrada en Authentication :: " );
		
		String tokenStr = (String)authentication.getCredentials();
		String userName = (String)authentication.getPrincipal();
		
		logger.info("Token : " + tokenStr );
		logger.info("userName : " + userName );
		
		// 1 - Validar token!
		TokenValidationRequest token = new TokenValidationRequestBaseImpl();
		token.setToken(tokenStr);
		TokenValidationResponse response = this.validaToken.validar(token);
		
		logger.info("token validado? " + response.isValid() );
		
		// 2 - Obtener roles de usuario
		if(!response.isValid()){
			throw new BadCredentialsException("Token invalido");
		}
				
		// Mock :P
		List<String> lista = new ArrayList<String>();
		lista.add("ROLE_ADMIN");
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		List<String> theRoles = lista;
		for(String role : theRoles){
			GrantedAuthority gr = new MyGrantedAuthority(role);
			roles.add(gr );
		}		
		
		UsernameSsoCodeAuthenticationToken authenticatedToken = 
				new UsernameSsoCodeAuthenticationToken(userName, tokenStr, roles);
		//authenticatedToken.setAuthenticated(response.isValid()); //La constructora con roles lo hace directamente, no se puede llamarlo así
		//authenticatedToken.setAuthenticated(true);
		//password en credentials?
		return authenticatedToken;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		logger.info("supports?");
		//if(authentication. instanceof UsernamePasswordAuthenticationToken){
		return authentication.equals(UsernameSsoCodeAuthenticationToken.class);
		//}
		//return false;
	}

	/**
	 * @return the validaToken
	 */
	public ValidaTokenBo getValidaToken() {
		logger.info("getValidaToken");
		return validaToken;
	}

	/**
	 * @param validaToken the validaToken to set
	 */
	public void setValidaToken(ValidaTokenBo validaToken) {
		logger.info("setValidaToken");
		this.validaToken = validaToken;
	}
	
	

}
