<%@ include file="/WEB-INF/jsp/core/include.jsp" %>

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
<script type="text/javascript" src="<c:url value="/script/login/getLoginWindow.js"/>"></script>
<script type="text/javascript">
	window.onload=function()
	{
		openLoginWindow();		

		window.open('', '_parent', '');
		win = top;
		self.opener = this;
		self.close();      			
	}	
</script> 