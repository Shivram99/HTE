package com.tcs.sgv.common.helper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tcs.sgv.acl.acegilogin.filter.MetaCharFilter;
import com.tcs.sgv.common.constant.AESUtil;

public class CSRFProcessingFilter extends MetaCharFilter implements Filter {
	Logger logger = Logger.getLogger(CSRFProcessingFilter.class);

	private final String loginPageName = "/login.jsp";
	private final String projectName = "/";
	private final String springCheck = "j_spring_security_check";
	private String errorPage;

	public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// response.addHeader("csrfToken", "unique");]]

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		HttpSession httpSession = request.getSession();

		Cookie[] cookies = httpServletRequest.getCookies();

		String csrfToken = null;

		// && request.getMethod().equals("POST")
		if (httpSession != null && httpSession.getAttribute("csrfToken") != null
				&& request.getParameter("csrfToken") != null) {
			String checkCsrf = request.getParameter("csrfToken").toString();
			if (httpSession.getAttribute("csrfToken").toString().equals(checkCsrf)) {
				try {
					csrfToken = AESUtil.getToken();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				httpSession.setAttribute("csrfToken", csrfToken);
				request.setAttribute("csrfToken", csrfToken);
				// response.addHeader("csrfToken",csrfToken);
			} else {
				logger.info("NO CSRF Token present  ======");
				RequestDispatcher localRequestDispatcher = request.getRequestDispatcher(this.loginPageName);
				request = null;
				localRequestDispatcher.forward(request, response);
			}
		} else {
			try {
				csrfToken = AESUtil.getToken();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			httpSession.setAttribute("csrfToken", csrfToken);
			request.setAttribute("csrfToken", csrfToken);
			// response.addHeader("csrfToken",csrfToken);
		}

		chain.doFilter(request, response);

	}

}
