/**
 * 
 */
package net.brdk.security.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * @author DOMESANU
 *
 */
public class CustomHttp403ForbiddenEntryPoint extends
		Http403ForbiddenEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

	private String errorPage;
	
	private Map<String, String> roleUriMap;
	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
		
		
		//TODO AQUI IGUAL LLEGA TENIENDO ACCESO A OTRO ENTORNO Y TE MATA LA SESION
		
		
		SecurityContext context = SecurityContextHolder.getContext();
		if (context.getAuthentication()!=null) {
		 context.setAuthentication(null);	
		}
		
		
		//TODO borrar contexto	
		
		
	}
	
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		SecurityContext context = SecurityContextHolder.getContext();
		if (context.getAuthentication()!=null) {
		 context.setAuthentication(null);	
		}
		
		// TODO Auto-generated method stub
		
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public Map<String, String> getRoleUriMap() {
		return roleUriMap;
	}

	public void setRoleUriMap(Map<String, String> roleUriMap) {
		this.roleUriMap = roleUriMap;
	}

	
	
	
	
	

}
