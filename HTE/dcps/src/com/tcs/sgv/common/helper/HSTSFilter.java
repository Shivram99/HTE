package com.tcs.sgv.common.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class HSTSFilter implements Filter {
    private static final String HEADER_NAME = "Strict-Transport-Security";
    private static final String MAX_AGE_DIRECTIVE = "max-age=%s";
    private static final String INCLUDE_SUB_DOMAINS_DIRECTIVE = "includeSubDomains";


    private int maxAgeSeconds = 0;
    private boolean includeSubDomains = false;
    private String directives;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("request.isSecure() :: " + request.isSecure());
        System.out.println("request.isSecure() :: " + request.isSecure());

        if (request.isSecure() && response instanceof HttpServletResponse) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.addHeader(HEADER_NAME, this.directives);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        maxAgeSeconds = Integer.parseInt(filterConfig.getInitParameter("maxAgeSeconds"));
        includeSubDomains = true;

        if (this.maxAgeSeconds <= 0) {
            throw new ServletException("Invalid maxAgeSeconds value :: " + maxAgeSeconds);
        }

        this.directives = String.format(MAX_AGE_DIRECTIVE, this.maxAgeSeconds);
        if (this.includeSubDomains) {
            this.directives += (" ; " + INCLUDE_SUB_DOMAINS_DIRECTIVE);
        }
        System.out.println("directives :: "+directives);
    }

    @Override
    public void destroy() {
    }
}