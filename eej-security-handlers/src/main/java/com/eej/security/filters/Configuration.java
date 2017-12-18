/**
 * 
 */
package com.eej.security.filters;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
//@Configuration
public class Configuration extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.addFilterAt(
	    		new HeaderUsernamePasswordAuthenticationFilter(), 
	    		UsernamePasswordAuthenticationFilter.class)
	    	.authorizeRequests()
	    	.antMatchers("/index.html").permitAll()
	    	.antMatchers("/swagger-ui.html").hasRole("ADMIN")
	    	.anyRequest().authenticated();
	}

}
