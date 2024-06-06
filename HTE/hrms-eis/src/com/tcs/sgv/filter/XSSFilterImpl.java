package com.tcs.sgv.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tcs.sgv.acl.acegilogin.event.RequestFailureCrossSiteEvent;
import com.tcs.sgv.acl.acegilogin.event.RequestSecuritySuccessEvent;
import com.tcs.sgv.acl.acegilogin.exception.RequestSecurityFaliureException;
import com.tcs.sgv.acl.acegilogin.filter.MetaCharFilter;
import com.tcs.sgv.common.constant.AESUtil;
import com.tcs.sgv.common.constant.SecurityUtil;
import com.tcs.sgv.common.helper.CookieHelper;
import com.tcs.sgv.common.helper.SameSite;

public class XSSFilterImpl extends MetaCharFilter implements Filter {

	protected ApplicationEventPublisher eventPublisher;
	protected MessageSourceAccessor messages;
	private CommonsMultipartResolver multipartResolver;
	private static ResourceBundle skipParamResourceBundle = null;
	private String errorPage;
	static final String FILTER_APPLIED = "_meta_char_filter_applied";

	public XSSFilterImpl() {
		this.messages = SpringSecurityMessageSource.getAccessor();
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.multipartResolver, "multipartResolver must be specified");
	}

	protected void doFilterHttp(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {

		// Validate Host Header
		String host = paramHttpServletRequest.getHeader("Host");
		logger.info("Host name == "+host);
		List<String> allowedHosts = Arrays.asList("localhost:8084", "customizedsevaarthaudit.mahaitgov.in");
		if (host == null || !allowedHosts.contains(host)) {
			paramHttpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Host Header");
			return;
		}

		Cookie[] cookies = paramHttpServletRequest.getCookies();

		if (cookies == null) {
			paramHttpServletResponse.sendRedirect("index.jsp");
			return;
		}

		String cookieValue = null;
		String cookieName = "JSESSIONID";
		String domain = "/";
		String path = "/";
		boolean isSecure = true;
		boolean isHttpOnly = true;
		Integer maxAge = 5000;
		String comment = "custom cookie";
		SameSite sameSite = SameSite.STRICT;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("JSESSIONID")) {
				cookieValue = cookie.getValue();
				domain = cookie.getDomain();
				path = cookie.getPath();
			}
		}

		if (cookieName != null && cookieValue != null) {
			CookieHelper.createSetCookieHeader(paramHttpServletResponse, cookieName, cookieValue, domain, path,
					sameSite, isSecure, isHttpOnly, maxAge, comment);
		}

		paramHttpServletResponse.setHeader("HttpOnly", "true");
		paramHttpServletResponse.setHeader("Secure", "true");
		paramHttpServletResponse.setHeader("SameSite", "Strict");

		paramHttpServletResponse.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
		paramHttpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
		paramHttpServletResponse.setHeader("X-XSS-Protection", "1; mode=block");
		paramHttpServletResponse.setHeader("Content-Security-Policy",
				"frame-ancestors 'none'; connect-src 'self'; font-src 'self'; img-src 'self'; default-src 'self'; style-src 'self' 'unsafe-inline' https://unpkg.com/sweetalert/dist/sweetalert.min.js https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css; script-src 'self' 'unsafe-inline' https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js;");
		paramHttpServletResponse.setHeader("Pragma", "no-cache");
		paramHttpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		paramHttpServletResponse.setHeader("Expires", "0");
		paramHttpServletResponse.setHeader("Referrer-Policy", "no-referrer");
		paramHttpServletResponse.setHeader("Permissions-Policy", "FEATURE ORIGIN");
		paramHttpServletResponse.setHeader("X-Powered-By", "none");
		paramHttpServletResponse.setHeader("Location", "none");

		paramHttpServletResponse.setHeader("X-Frame-Options", "DENY");

		Enumeration<String> localEnumeration = paramHttpServletRequest.getParameterNames();
		String str1 = null;
		while (localEnumeration.hasMoreElements()) {
			String str2 = localEnumeration.nextElement();
			if (str2 != null) {
				str1 = paramHttpServletRequest.getParameter(str2);

				AESUtil aESUtil = new AESUtil();
				if ("password".equals(str2)) {
					if (str1.length() == 160) {
						str2 = aESUtil.decrypt("Message", str1);
					}
				}

				if (!str2.equals("txtUIDNo1") && !str2.equals("txtUIDNo2") && !str2.equals("txtUIDNo3")
						&& !str2.equals("txtPANNo")) {
					String cleanedString = SecurityUtil.cleanIt(str1);
					System.out.println("before clean" + str1);
					paramHttpServletRequest.setAttribute(str2, cleanedString);
					System.out.println("after clean" + cleanedString);
				}
			}
		}

		paramHttpServletRequest.setAttribute("_meta_char_filter_applied", Boolean.TRUE);
		logger.info("Inside XSSFilter");

		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Meta character filter checking request for cross site script");
		}

		String str = null;
		try {
			if (this.multipartResolver.isMultipart(paramHttpServletRequest)) {
				MultipartHttpServletRequest localMultipartHttpServletRequest = this.multipartResolver
						.resolveMultipart(paramHttpServletRequest);
				str = checkForSecurity(localMultipartHttpServletRequest, paramHttpServletResponse);
				paramHttpServletRequest = localMultipartHttpServletRequest;
			} else {
				str = checkForSecurity(paramHttpServletRequest, paramHttpServletResponse);
			}

			if (paramHttpServletRequest.getAttribute("_meta_char_filter_applied") != null) {
				paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);
				return;
			}

		} catch (RequestSecurityFaliureException localRequestSecurityFaliureException) {
			logger.info("Inside catch in filter");
			if (this.logger.isDebugEnabled())
				this.logger.debug("Meta character filter found cross site script in "
						+ localRequestSecurityFaliureException.getExtraInformation() + " Parameter");

			if (this.eventPublisher != null) {
				this.eventPublisher.publishEvent(new RequestFailureCrossSiteEvent(paramHttpServletRequest,
						localRequestSecurityFaliureException));
			}
			paramHttpServletRequest = null;
			RequestDispatcher localRequestDispatcher = paramHttpServletRequest.getRequestDispatcher(this.errorPage);
			localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
		}
		if (this.logger.isDebugEnabled())
			this.logger.debug("Meta character filter not found cross site script in http request");

		this.eventPublisher.publishEvent(new RequestSecuritySuccessEvent(paramHttpServletRequest));

		paramHttpServletRequest.setAttribute("_meta_char_filter_applied", Boolean.TRUE);
		HttpServletResponse res = (HttpServletResponse) paramHttpServletResponse;
		paramHttpServletResponse.setHeader("X-FRAME-OPTIONS", "DENY");

		paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);
	}

	public CommonsMultipartResolver getMultipartResolver() {
		return this.multipartResolver;
	}

	public void setMultipartResolver(CommonsMultipartResolver paramCommonsMultipartResolver) {
		this.multipartResolver = paramCommonsMultipartResolver;
	}

	public void setMessageSource(MessageSource paramMessageSource) {
		this.messages = new MessageSourceAccessor(paramMessageSource);
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher paramApplicationEventPublisher) {
		this.eventPublisher = paramApplicationEventPublisher;
	}

	private String checkForSecurity(HttpServletRequest paramHttpServletRequest, HttpServletResponse httpServletResponse)
			throws RequestSecurityFaliureException, IOException {

		logger.info("Inside our filter");
		Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
		String str1 = null;
		while (localEnumeration.hasMoreElements()) {
			String str2 = (String) localEnumeration.nextElement();
			str1 = paramHttpServletRequest.getParameter(str2);
			logger.info("str1 is ::::::" + str1);
			logger.info("str2 is ::::::" + str2);

			if (!str2.equals("txtUIDNo1") || !str2.equals("txtUIDNo2") || !str2.equals("txtUIDNo3")
					|| !str2.equals("txtPANNo")) {
				String cleanedString = SecurityUtil.cleanIt(str1);
				System.out.println("before clean" + str1);
				paramHttpServletRequest.setAttribute(str2, cleanedString);
				System.out.println("after clean" + cleanedString);
			}

			if (str1 != null && (str1.contains("<") || str1.contains(">"))) {
				// try {
				// str1 = str1.replaceAll("<", "%ld");
				// str1 = str1.replaceAll(">", "%gd");
				// paramHttpServletRequest.ser(str2, null);
				httpServletResponse.sendRedirect("index.jsp");
				logger.info("paramHttpServletRequest1 " + paramHttpServletRequest.getParameter(str2));
				throw new RequestSecurityFaliureException("Cross site script has been found", str1);
				/*
				 * } catch (XSSFoundException e) {
				 * logger.error("Exception is ::::"+e.getMessage());
				 * setErrorPage("/WEB-INF/jsp/core/webErrorPage.jsp"); }
				 */
			}

			else if (str2 != null && (str2.contains("<") || str2.contains(">"))) {
				paramHttpServletRequest.setAttribute(str2, null);

				logger.info("paramHttpServletRequest2 " + paramHttpServletRequest.getParameter(str2));
				// str2 = str2.replaceAll("<", "%ld");
				// str2 = str2.replaceAll(">", "%gd");
				httpServletResponse.sendRedirect("index.jsp");
				throw new RequestSecurityFaliureException("Cross site script has been found", str2);
			}
		}

		return null;
	}

	public void setErrorPage(String paramString) {
		if ((paramString != null) && (!(paramString.startsWith("/")))) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = paramString;
	}
}
