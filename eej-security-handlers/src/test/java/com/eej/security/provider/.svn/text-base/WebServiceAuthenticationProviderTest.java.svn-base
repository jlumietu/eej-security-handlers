/**
 * 
 */
package com.eej.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * @author DOIBALMI
 *
 */
public class WebServiceAuthenticationProviderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebServiceAuthenticationProvider provider = new WebServiceAuthenticationProvider();
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken("mikel","pwd");
		//authentication.
		Authentication autorizado = provider.authenticate(authentication );
		System.out.println(autorizado.getCredentials());
		System.out.println(autorizado.getName());
		System.out.println(autorizado.getPrincipal());
		System.out.println(autorizado.getAuthorities());
	}

}
