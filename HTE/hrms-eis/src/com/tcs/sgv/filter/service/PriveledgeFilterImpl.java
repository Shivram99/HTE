package com.tcs.sgv.filter.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.providers.dao.cache.EhCacheBasedUserCache;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tcs.sgv.acl.acegilogin.event.RequestSecuritySuccessEvent;
import com.tcs.sgv.acl.acegilogin.exception.RequestSecurityFaliureException;
import com.tcs.sgv.common.constant.SecurityUtil;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.filter.dao.PriveledgeFilterDAOImpl;

public class PriveledgeFilterImpl extends SpringSecurityFilter
implements ApplicationEventPublisherAware
{

	protected ApplicationEventPublisher eventPublisher;
	protected MessageSourceAccessor messages;
	private CommonsMultipartResolver multipartResolver;
	static final String FILTER_APPLIED = "_meta_char_filter_applied";
	private EhCacheBasedUserCache userCache;
	private String errorPage="/dcps/login.jsp";

	public PriveledgeFilterImpl()
	{
		this.messages = SpringSecurityMessageSource.getAccessor();
	}

	public void afterPropertiesSet()
			throws Exception
	{
		Assert.notNull(this.multipartResolver, "multipartResolver must be specified");
	}

	protected void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		logger.info("session is : "+session);
		
		
		if(session!=null){
			String userName= request.getParameter("j_username");
			logger.info("userName is : "+userName);
			if(userName==null){
				userName=(String) session.getAttribute("userName");
				logger.info("userName Get Attribute : "+userName);
			}else{
				session.setAttribute("userName", userName);
			}
			if(!userName.equals("anonymousUser") && userName != null){
				this.logger.info("Priveledge Filter**************** "+userName);
				String str = null;
				ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
				PriveledgeFilterDAOImpl dao = new PriveledgeFilterDAOImpl(OrgUserMst.class,serviceLocator.getSessionFactory());
				List roleForUser= dao.getRoleListForUser(userName);
				String roleId= null;
				for(int i=0 ; i<roleForUser.size();i++){
					if(roleId!=null){
						roleId=roleId+","+roleForUser.get(i).toString();
					}
					else{
						roleId=roleForUser.get(i).toString();
					}
				}

				if(userName!= null && !userName.equals("")){
					
					logger.info("userName outside loop is : "+userName);

					try{

						str = checkForSecurity(request,roleId);
						if(str.equals("Yes")){

							this.eventPublisher.publishEvent(new RequestSecuritySuccessEvent(request));
							request.setAttribute("_priveledge_escalation_filter", Boolean.TRUE);

							chain.doFilter(request, response);
						}
					}

					catch (RequestSecurityFaliureException localRequestSecurityFaliureException)
					{
						logger.info("Inside catch in filter");
						RequestDispatcher localRequestDispatcher = request.getRequestDispatcher(this.errorPage);
						localRequestDispatcher.forward(request, response);
					}
				}
				logger.info("userName outside main loop is : "+userName);
				this.logger.info(" Priveledge matches......");

				this.eventPublisher.publishEvent(new RequestSecuritySuccessEvent(request));
				request.setAttribute("_priveledge_escalation_filter", Boolean.TRUE);
				this.logger.info("request "+request);
				this.logger.info("response "+response);
			}
			
		}
		chain.doFilter(request, response);
	}



	private String checkForSecurity(HttpServletRequest paramHttpServletRequest,String roleId)
			throws RequestSecurityFaliureException	  {

		Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
		ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
		PriveledgeFilterDAOImpl dao = null;
		String str1 = null;
		String authorize="No";
		while (localEnumeration.hasMoreElements())
		{
			String str2 = (String)localEnumeration.nextElement();
			str1 = paramHttpServletRequest.getParameter(str2);
			logger.info("str1 is ::::::"+str1);
			logger.info("str2 is ::::::"+str2);

			/*Added By Shivram 10052023*/
			if(!str2.equals("txtUIDNo1") || !str2.equals("txtUIDNo2") || !str2.equals("txtUIDNo3") || !str2.equals("txtPANNo")){
	    		  String cleanedString=SecurityUtil.cleanIt(str1);
			 	     System.out.println("before clean in priveledge : " +str1);
			 	     paramHttpServletRequest.setAttribute(str2, cleanedString);
			 	     System.out.println("after clean in priveledge : "+cleanedString);
	    	  }
			/*Ended By Shivram 10052023*/
			
			
			if(str2!=null && !str2.equals("") && str2.equals("elementId")){
				dao = new PriveledgeFilterDAOImpl(OrgUserMst.class,serviceLocator.getSessionFactory());
				boolean isValid= dao.checkPriveledgeForElementId(str1,roleId);
				if(isValid){
					authorize="Yes";
				}
				else{
					throw new RequestSecurityFaliureException("Priveledge Escalation has been found", str1);
				}
			}
		}
		return authorize;
	}

	public CommonsMultipartResolver getMultipartResolver()
	{
		return this.multipartResolver;
	}

	public void setMultipartResolver(CommonsMultipartResolver paramCommonsMultipartResolver)
	{
		this.multipartResolver = paramCommonsMultipartResolver; }

	public void setMessageSource(MessageSource paramMessageSource) {
		this.messages = new MessageSourceAccessor(paramMessageSource);
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher)
	{
		this.eventPublisher = eventPublisher;
	}

	public EhCacheBasedUserCache getUserCache() {
		return this.userCache;
	}

	public void setUserCache(EhCacheBasedUserCache userCache) {
		this.userCache = userCache;
	}

	public void setErrorPage(String paramString)
	{
		if ((paramString != null) && (!(paramString.startsWith("/")))) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = paramString;
	}

	public int getOrder() {
		return 0;
	}
}
