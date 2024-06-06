<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setHeader("Expires", "0"); //prevents caching at the proxy server
%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page errorPage="webErrorPage.jsp" isErrorPage="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:setLocale value='<%=session.getAttribute("locale")%>' />
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<%
response.setHeader("HttpOnly", "true");
response.setHeader("Secure", "true");
response.setHeader("SameSite", "Strict");

response.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
response.setHeader("X-Content-Type-Options", "nosniff");
response.setHeader("X-XSS-Protection", "1; mode=block");
response.setHeader("Content-Security-Policy",
		"frame-ancestors 'none' connect-src 'self' font-src 'self'  img-src 'self'  default-src 'self' style-src 'self' *.https://unpkg.com/sweetalert/dist/sweetalert.min.js 'unsafe-inline' *.https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css *. https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js *. https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js *.https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js ");
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", " no-cache, no-store, must-revalidate");
response.setHeader("Expires", "0");
response.setHeader("Referrer-Policy", "no-referrer");
response.setHeader("Permissions-Policy", "FEATURE ORIGIN");
response.setHeader("X-Powered-By", "none");
response.setHeader("Location", "none");

response.setHeader("X-Frame-Options", "DENY");
%>
<%-- <script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>
	';
	var navDisplay = navDisplay;
	if (navDisplay == undefined)
		navDisplay = true;
</script> --%>


<script type = "text/javascript" >  
    function preventBack() { window.history.forward(); }  
    setTimeout("preventBack()", 0);  
    window.onunload = function () { null };  
</script>
