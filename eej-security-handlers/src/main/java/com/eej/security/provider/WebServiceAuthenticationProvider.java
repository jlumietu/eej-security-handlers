/**
 * 
 */
package com.eej.security.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.eej.security.handler.model.MyGrantedAuthority;
import com.eej.security.handler.model.WebServiceAuthenticationUser;

/**
 * @author DOIBALMI
 *
 */
public class WebServiceAuthenticationProvider implements AuthenticationProvider {

	/**
	 * 
	 */
	Map<String, List<String>> userTypeRoleMap; 
		
	/**
	 * 
	 */
	public WebServiceAuthenticationProvider() {
		super();
		// se puede prescindir de esto si se mete en el application-context-Spring security tal que asi:
		/*
		 * 
		 * <beans:bean id="loginAuthenticationProvider" class="ccom.eej.security.provider.WebServiceAuthenticationProvider">
  		<beans:property name="userTypeRoleMap">
  			<beans:map>
  				<beans:entry key="gestor">	
  					<beans:list>
  						<beans:value>ROLE_GESTOR</beans:value>
  						<beans:value>ROLE_EMP</beans:value>
  						<beans:value>ROLE_ADMIN</beans:value>
  						<beans:value>ROLE_REVISOR</beans:value>
  					</beans:list>
  				</beans:entry>
  				<beans:entry key="administrador">	
  					<beans:list>
  						<beans:value>ROLE_EMP</beans:value>
  						<beans:value>ROLE_ADMIN</beans:value>
  					</beans:list>
  				</beans:entry>
  				<beans:entry key="revisor">	
  					<beans:list>
  						<beans:value>ROLE_EMP</beans:value>
  						<beans:value>ROLE_REVISOR</beans:value>
  					</beans:list>
  				</beans:entry>
  				<beans:entry key="proveedor">	
  					<beans:list>
  						<beans:value>ROLE_PROVEEDOR</beans:value>
  					</beans:list>
  				</beans:entry>
  			</beans:map>
  		
  		
  		</beans:property>
  	</beans:bean>
		 * 
		 */
		userTypeRoleMap = new HashMap<String, List<String>>();
		List<String> listaGestor = new ArrayList<String>();
		listaGestor.add("ROLE_EMP");
		listaGestor.add("ROLE_ADMIN");
		listaGestor.add("ROLE_REVISOR");
		listaGestor.add("ROLE_GESTOR");
		userTypeRoleMap.put("gestor", listaGestor);
		List<String> listaAdmin = new ArrayList<String>();
		listaAdmin.add("ROLE_EMP");
		listaAdmin.add("ROLE_ADMIN");
		userTypeRoleMap.put("administrador", listaAdmin);
		List<String> listaRevisor = new ArrayList<String>();
		listaRevisor.add("ROLE_EMP");
		listaRevisor.add("ROLE_REVISOR");
		userTypeRoleMap.put("revisor", listaRevisor);
		List<String> listaProveedor = new ArrayList<String>();
		listaProveedor.add("ROLE_PROVEEDOR");
		userTypeRoleMap.put("proveedor", listaProveedor);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken preAuthToken = (UsernamePasswordAuthenticationToken) authentication;
		String usuario = preAuthToken.getPrincipal().toString();
		String password = preAuthToken.getCredentials().toString();
		
		WebServiceAuthenticationUser user = this.validaUsuario();
		/*
		 * Llamada al WS:
		 * 
		 * boolean autenticado;
		 * String nombreUsuario;
		 * String tipoUsuario;
		 * 
		 */	
		if(!user.isAuthenticated()){
			throw new BadCredentialsException("Login inválido");	
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		List<String> theRoles = this.userTypeRoleMap.get(user.getUserType());
		for(String role : theRoles){
			GrantedAuthority gr = new MyGrantedAuthority(role);
			roles.add(gr );
		}		
		
		UsernamePasswordAuthenticationToken authenticatedToken = 
				new UsernamePasswordAuthenticationToken(usuario, null, roles);
		//authenticatedToken.setAuthenticated(true);
		//password en credentials?
		return authenticatedToken;
	}

	

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

	/**
	 * @return the userTypeRoleMap
	 */
	public Map<String, List<String>> getUserTypeRoleMap() {
		return userTypeRoleMap;
	}

	/**
	 * @param userTypeRoleMap the userTypeRoleMap to set
	 */
	public void setUserTypeRoleMap(Map<String, List<String>> userTypeRoleMap) {
		this.userTypeRoleMap = userTypeRoleMap;
	}
	
	private WebServiceAuthenticationUser validaUsuario() {
		WebServiceAuthenticationUser user = new WebServiceAuthenticationUser();
		user.setUserName("mikel");
		user.setAuthenticated(true);
		user.setUserType("gestor");
		return user;
	}

}
