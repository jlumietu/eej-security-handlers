/**
 * 
 */
package net.brdk.security.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.brdk.security.model.BrdkUser;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author domesanu
 *
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	protected Logger logger = Logger.getLogger(this.getClass());



	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) throws IOException {
		handle(request, response, authentication);
		logger.debug("request"+ request.getRequestURI());
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = request.getRequestURI();//determineTargetUrl(authentication);
		String contextPath = request.getContextPath();
		String servletPath = this.getUrlPathHelper().getPathWithinApplication(request);

		Map<?, ?> parametros = request.getParameterMap();

		logger.debug("contextPath " + contextPath);
		logger.debug("servletPath " + servletPath);
		logger.debug("servletPath " + servletPath);
		logger.debug("parametros " + parametros);
		logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);

		SecurityContext context = SecurityContextHolder.getContext();
		BrdkUser usuarioConectado = null;
		if (context.getAuthentication() != null && 
				context.getAuthentication().getPrincipal()!= null){ 

			usuarioConectado = (BrdkUser)context.getAuthentication().getPrincipal(); 


		}

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);

			return;
		}

		if (usuarioConectado!=null) {
			servletPath = servletPath.concat("?lang=" +usuarioConectado.getIdioma());
		}
		if (parametros!=null && !parametros.isEmpty()) {
			Iterator<?> it = parametros.entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry e = (Map.Entry)it.next();
				if (!e.getKey().equals("idUsuario") && !e.getKey().equals("token")){
					String[] valor = (String[]) e.getValue();
					String strValor ="";
					if (valor.length>1) {

					}else{
						strValor = valor[0]; 
					}
					servletPath = servletPath.concat("&"+e.getKey() + "=" +strValor );
				}
			}
		}
		logger.debug("REDIRIGE " + servletPath);

		redirectStrategy.sendRedirect(request, response, servletPath);
	}
	/** Builds the target URL according to the logic defined in the main class Javadoc. */
	protected String determineTargetUrl(Authentication authentication) {


		/*  if (isUser) {
	            return "/homepage.html";
	        } else if (isAdmin) {*/
		return "/console.html";
		/*   } else {
	            throw new IllegalStateException();
	        }*/
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	/**
	 * @return the urlPathHelper
	 */
	public UrlPathHelper getUrlPathHelper() {
		return urlPathHelper;
	}

	/**
	 * @param urlPathHelper the urlPathHelper to set
	 */
	public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
		this.urlPathHelper = urlPathHelper;
	}

	/*@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.debug("CustomAuthenticationFailureHandler inicio");
		try{
			String nombreClase = exception.getClass().getName();
			if (this.exceptionClassses.containsKey(nombreClase)) {

				String s = this.exceptionClassses.get(nombreClase);
				if(s != null){
					this.setDefaultFailureUrl(this.exceptionClassses.get(s));
				}


			} else {

				logger.debug("Lanzo ClassNotFoundException ");
				new ClassNotFoundException("La lanzo yo");
			}



			}catch(Exception cnfe){
				logger.debug("ClassNotFoundException " + cnfe.getMessage());
			}

		super.onAuthenticationFailure(request, response, exception);
	}

	public Map<String, String> getExceptionClassses() {
		return exceptionClassses;
	}

	public void setExceptionClassses(Map<String, String> exceptionClassses) {
		this.exceptionClassses = exceptionClassses;
	}
	 */


}
