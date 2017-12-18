/**
 * 
 */
package com.eej.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.StringUtils;

/**
 * @author jlumietu
 *
 */
public class LoginHintUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint
		implements AuthenticationEntryPoint {

	private String userLoginHintParameterName;

	/**
	 * 
	 * @param loginFormUrl
	 */
	public LoginHintUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);	
	}
	
	/**
	 * @param loginFormUrl
	 * @param userLoginHintParameterName
	 */
	public LoginHintUrlAuthenticationEntryPoint(String loginFormUrl, String userLoginHintParameterName) {
		super(loginFormUrl);
		this.userLoginHintParameterName = userLoginHintParameterName;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint#determineUrlToUseForThisRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		String loginHint = request.getParameter(this.getUserLoginHintParameterName());
		StringBuilder sb = new StringBuilder(super.determineUrlToUseForThisRequest(request, response, exception));
		sb.append(StringUtils.isEmpty(loginHint)?"":new StringBuilder("?").append(this.getUserLoginHintParameterName()).append("=").append(loginHint));
		return sb.toString();				
	}

	public String getUserLoginHintParameterName() {
		return this.userLoginHintParameterName;
	}

	/**
	 * @param userLoginHintParameterName the userLoginHintParameterName to set
	 */
	public void setUserLoginHintParameterName(String userLoginHintParameterName) {
		this.userLoginHintParameterName = userLoginHintParameterName;
	}
	
	public static void main(String[] args){
		System.out.println(new StringBuilder("?").append("user").append("=").append("pepe"));
	}

}
