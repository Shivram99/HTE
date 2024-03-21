package com.tcs.sgv.common.helper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Add headers to prevent caching
        System.out.println("---------- Cache Control Start ------------");
        httpResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpResponse.setHeader("Pragma", "no-cache");
        System.out.println("---------- Cache Control End ------------");
        // Proceed with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
