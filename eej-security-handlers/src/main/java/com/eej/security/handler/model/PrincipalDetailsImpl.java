/**
 * 
 */
package com.eej.security.handler.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.eej.security.ApplicationVersion;

/**
 * @author jlumietu
 *
 */
public class PrincipalDetailsImpl extends ThreePartPrincipalImpl implements Serializable, PrincipalDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = ApplicationVersion.APP_VERSION;
	
	

	private Collection<? extends GrantedAuthority> authorities;

	private boolean accountNotExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	private String password;
	
	/**
	 * 
	 * @param name
	 * @param mail
	 * @param id
	 * @param authorities
	 */
	public PrincipalDetailsImpl(String name, String mail, int id,
			Collection<? extends GrantedAuthority> authorities) {
		super(name, mail, id);
		this.authorities = authorities;
	}
	
	/**
	 * 
	 * @param name
	 * @param mail
	 * @param id
	 * @param authorities
	 * @param enabled
	 */
	public PrincipalDetailsImpl(String name, String mail, int id,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super(name, mail, id);
		this.authorities = authorities;
		this.enabled = enabled;
	}

	/**
	 * 
	 * @param name
	 * @param mail
	 * @param id
	 * @param authorities
	 * @param accountNotExpired
	 * @param accountNonLocked
	 * @param credentialsNonExpired
	 * @param enabled
	 */
	public PrincipalDetailsImpl(String name, String mail, int id, String password,
			Collection<? extends GrantedAuthority> authorities,
			boolean accountNotExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, boolean enabled) {
		super(name, mail, id);
		this.password = password;
		this.authorities = authorities;
		this.accountNotExpired = accountNotExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNotExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public boolean isAccountNotExpired() {
		return accountNotExpired;
	}

	public void setAccountNotExpired(boolean accountNotExpired) {
		this.accountNotExpired = accountNotExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Principal getPrincipal() {
		return new ThreePartPrincipalImpl(this.getName(), this.getMail(), this.getId());
	}
	
	

}
