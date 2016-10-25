/**
 * 
 */
//package net.iberdok.security.filters;
package com.eej.security.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eej.security.model.SessionDetails;
import com.eej.security.model.User;
import com.eej.security.exception.BadCredentialsNoTokenException;
import com.eej.security.handler.model.UsernameSsoCodeAuthenticationToken;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author DOMESANU
 *
 */
public class TokenAuthenticationFilter implements Filter {

	Logger logger = Logger.getLogger(this.getClass());
	
	private AuthenticationFailureHandler authenticationFailureHandler;
										 
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	private AuthenticationManager authenticationManager;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		//logger.info("init doFilter");
		SecurityContext context = SecurityContextHolder.getContext();
		try{
	        if (context.getAuthentication() != null && 
	        		context.getAuthentication().isAuthenticated() &&
	        		context.getAuthentication().getPrincipal()!= null 
	        		 
	        		) {
	        	//logger.info("Ya esta autenticado!!!!!!");
	        	// Habra que comprobar que es el mismo que esta autenticado
	        	Map<String,String[]> params = (Map<String,String[]>)req.getParameterMap();
	            //logger.debug("params: " + params.keySet());
	            if (!params.isEmpty() && params.containsKey("token")&& params.containsKey("idUsuario") ){
		        	//SOLO SI TRAE datos de validacion
	            	 String idUsuario = params.get("idUsuario")[0];
	            	 User usuarioConectado = (User)context.getAuthentication().getPrincipal(); 
	            	 if (idUsuario!=null && idUsuario.equals(usuarioConectado.getId())){
	            		 int peticionesConectadas = 0;
	            		 SessionDetails details =null;
	            		 if (context.getAuthentication().getDetails()!=null) {
	            			 details = (SessionDetails)context.getAuthentication().getDetails();
	            			 if (details!=null) {
	            				
		            			 peticionesConectadas = details.getPeticionesConectadas();
		            			 logger.debug("Details"+details.getPeticionesConectadas());
	            			 }
	            			 
	            		 }
	            		 if (peticionesConectadas<0) {
	            			 peticionesConectadas = 0;
	            		 }
	            		 peticionesConectadas++;
	            		 details.setPeticionesConectadas(peticionesConectadas);
	     				
	            		 
	            		 details = (SessionDetails)context.getAuthentication().getDetails();
	            		 if (details!=null) {
	            			 logger.debug("peticionesConectadas EN DETAILS:::" +details.getPeticionesConectadas());
	            		 }
	            		 logger.debug("peticionesConectadas:::" +peticionesConectadas);
	            		 this.authenticationSuccessHandler.onAuthenticationSuccess((HttpServletRequest)req, (HttpServletResponse)res, context.getAuthentication());
				        	
	            	 } else {
	            		 logger.debug("intenta entrar con otro usuario");
	                 	throw new BadCredentialsException("Intenta iniciar sesion con otro");
	            	 }
	            }
		    } else {
			        	logger.info("No esta autenticado, vamos a revisar");
			        	
			        	Authentication authentication = this.attemptAuthentication((HttpServletRequest)req, (HttpServletResponse)res);
			        	Authentication authResult = this.getAuthenticationManager().authenticate(authentication);
			        	context.setAuthentication(authResult);
			        	this.authenticationSuccessHandler.onAuthenticationSuccess((HttpServletRequest)req, (HttpServletResponse)res, authResult);
			        	// return;
			        		
		    }
	        
		}catch(AuthenticationException authenticationException){
    		logger.debug("Authentication exception " + authenticationException.getMessage());
    		this.authenticationFailureHandler.onAuthenticationFailure((HttpServletRequest)req, (HttpServletResponse)res, authenticationException);
    		return;
    	}
       
        
        //logger.debug("fin doFilter");
        fc.doFilter(req, res);
    }

	
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException,
			IOException, ServletException {
		Map<String,String[]> params = (Map<String,String[]>)req.getParameterMap();
        //logger.debug("params: " + params.keySet());
        if (!params.isEmpty() && params.containsKey("token") && params.containsKey("idUsuario")) {
            String token = params.get("token")[0];
            String idUsuario = params.get("idUsuario")[0];
            if (token != null) {
            	Authentication auth = new UsernameSsoCodeAuthenticationToken(idUsuario, token);
               // logger.debug("fin autenticar");
                return auth;                
            } else {
            	//logger.debug("token == null");
            	throw new BadCredentialsNoTokenException("No tiene token");
            }
        } else {
        	 // logger.debug("PArametros vacios");
        	  throw new BadCredentialsNoTokenException("el token viene vacio token");
        }
   
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("INIT filter method");
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}


	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}


	public void setAuthenticationFailureHandler(
			AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}


	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return authenticationSuccessHandler;
	}


	public void setAuthenticationSuccessHandler(
			AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	
	

	

}
