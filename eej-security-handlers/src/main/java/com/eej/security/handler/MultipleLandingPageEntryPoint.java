package com.eej.security.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class MultipleLandingPageEntryPoint extends LoginUrlAuthenticationEntryPoint
		implements AuthenticationEntryPoint {
	
	private Map<String, String> landingPages;
	
	public MultipleLandingPageEntryPoint(String defaultLoginFormUrl, Map<String, String> landingPages) {
		super(defaultLoginFormUrl);
		this.landingPages = landingPages;
	}
	
	public MultipleLandingPageEntryPoint(String defaultLoginFormUrl) {
		super(defaultLoginFormUrl);
	}

	public Map<String, String> getLandingPages() {
		return landingPages;
	}

	public void setLandingPages(Map<String, String> landingPages) {
		this.landingPages = landingPages;
	}

	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		for(String key : this.landingPages.keySet()){
			RequestMatcher rm = new RegexRequestMatcher(key, null);
			if(rm.matches(request)){
				return this.landingPages.get(key);
			}
		}
		// If not found in the map, return the default landing page through superclass
		return super.determineUrlToUseForThisRequest(request, response, exception);
	}
	

}
