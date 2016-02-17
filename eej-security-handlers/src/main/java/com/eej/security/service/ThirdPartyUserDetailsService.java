/**
 * 
 */
package com.eej.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author jlumietu
 *
 */
public class ThirdPartyUserDetailsService implements ThirdPartyLoginService {
	
	@Autowired
	private LoginServiceDao loginServiceDao;
	
	//private 

	/* (non-Javadoc)
	 * @see com.eej.security.service.ThirdPartyLoginService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public UserDetails login(String userName, String password) {
		UserDetails details = this.loginServiceDao.login(userName, password);
		
		
		return details;
	}

	/**
	 * @return the loginServiceDao
	 */
	public LoginServiceDao getLoginServiceDao() {
		return loginServiceDao;
	}

	/**
	 * @param loginServiceDao the loginServiceDao to set
	 */
	public void setLoginServiceDao(LoginServiceDao loginServiceDao) {
		this.loginServiceDao = loginServiceDao;
	}
	
	

}
