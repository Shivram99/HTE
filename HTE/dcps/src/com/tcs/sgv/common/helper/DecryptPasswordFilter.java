package com.tcs.sgv.common.helper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.sgv.common.constant.AESUtil;

public class DecryptPasswordFilter implements Filter {
	
	
	static HttpServletRequest httpServletRequest=null;
	
	
	 static class FilteredRequest extends HttpServletRequestWrapper {
	
	        public FilteredRequest(ServletRequest request) {
	            super((HttpServletRequest)request);
	            httpServletRequest=(HttpServletRequest) request;
	        }

	        public String getParameter(String paramName) {
	            String value = super.getParameter(paramName);
	            
	            AESUtil aESUtil1=new AESUtil();
	            HttpSession session=httpServletRequest.getSession();
	            if(paramName.equals("password") || paramName.equals("j_password")){
	            	 if(value!=null){
		        		 if(value.length()==120){
		        			 String message=super.getParameter("pass1"); //encrypted captcha  
		        			 if(message.length()==120){
		        				 
		        				 String randomString=(String) session.getAttribute("randomString");
		        				 
		        				String oldCaptcha=aESUtil1.decrypt("Message", message); 
		        				oldCaptcha = oldCaptcha.replaceAll(" ", "");
		        				
		        				
		        				System.out.println(" oldCaptcha is : "+oldCaptcha); 
		        				
		        				 String captchaCode=super.getParameter("captcha_code"); 
		        				 captchaCode=aESUtil1.decrypt(randomString, captchaCode); 
		        				 System.out.println(" captchaCode is : "+captchaCode);
		        				 captchaCode = captchaCode.replaceAll(" ", "");
		        					
		        				 
		        				 String captcha=super.getParameter("captcha"); 
		        				 captcha=aESUtil1.decrypt(randomString, captcha); 
		        				 captcha = captcha.replaceAll(" ", "");
		        				 
		        				 if(captcha.length()==6 && captchaCode.length()==6 && oldCaptcha.length()==6 && captchaCode.equals(oldCaptcha)  && captcha.equals(oldCaptcha) ){
		        					 
		        					 value=aESUtil1.decrypt(randomString, value); 
		        					 
		        				 }
		        				 
		        				
		        				
		        			 }
		        			 
		        		 }
		        	  }
	            }
	           
	            return value;
	        }
	    }

	 

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	 public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
			
	        chain.doFilter(new FilteredRequest(request), response);
	    }	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
		
	}
	
	
	
	
}
