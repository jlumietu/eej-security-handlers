/**
 * 
 */
package com.eej.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.eej.security.handler.model.PrincipalDetails;

/**
 * @author doibalmi
 *
 */
public class UserDetailsServiceBasedAuthenticationProvider extends
		DaoAuthenticationProvider implements AuthenticationProvider {
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#createSuccessAuthentication(java.lang.Object, org.springframework.security.core.Authentication, org.springframework.security.core.userdetails.UserDetails)
	 */
	/**
	 * Creates a successful {@link Authentication} object.
	 * <p>
	 * Protected so subclasses can override.
	 * </p>
	 * <p>
	 * Subclasses will usually store the original credentials the user supplied (not
	 * salted or encoded passwords) in the returned <code>Authentication</code> object.
	 * </p>
	 *
	 * @param principal that should be the principal in the returned object (defined by
	 * the {@link #isForcePrincipalAsString()} method)
	 * @param authentication that was presented to the provider for validation
	 * @param user that was loaded by the implementation
	 *
	 * @return the successful authentication token
	 */
	@Override
	protected Authentication createSuccessAuthentication(Object principal,
			Authentication authentication, UserDetails user) {
		Authentication fromSuper = super.createSuccessAuthentication(principal, authentication, user);
		if(this.isForcePrincipalAsString() || !(user instanceof PrincipalDetails)){
			return fromSuper;
		}
		
		// Ensure we return the original credentials the user supplied,
		// so subsequent attempts are successful even with encoded passwords.
		// Also ensure we return the original getDetails(), so that future
		// authentication events after cache expiry contain the details
		UsernamePasswordAuthenticationToken result = 
				new UsernamePasswordAuthenticationToken(
						((PrincipalDetails)user).getPrincipal(), 
						authentication.getCredentials(),
						fromSuper.getAuthorities()
					);
		result.setDetails(authentication.getDetails());

		return result;
		
	}
	
	

	

	
	
}
