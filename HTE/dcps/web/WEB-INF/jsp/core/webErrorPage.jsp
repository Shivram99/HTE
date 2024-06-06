<%@ page isErrorPage="true" %>
<%@ taglib prefix="cw" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmtw" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ page contentType="text/html;charset=UTF-8"%>

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
<script type="text/javascript" src='<cw:url value="/script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<cw:url value="/script/login/frmUtils.js"/>'></script>
<script type="text/javascript" src='<cw:url value="/script/login/statusbar.js"/>'></script>	
<!-- link rel="stylesheet" href='themes/defaulttheme/header3.css' type="text/css"--> 
<link rel="stylesheet" href='themes/ifmsblue/taglib.css' type="text/css"> 
<link rel="stylesheet" href='themes/ifmsblue/statusbar.css' type="text/css" />
<link rel="stylesheet" href='themes/ifmsblue/IfmsBlueAll.css' type="text/css">
<script type="text/javascript">
 hideProgressbar();
  provideProperHeight();		
	function submitErrorForm()
{
//showProgressbar();
var varUrl = "ifms.htm?actionFlag=getHomePage";

document.wePage.action=varUrl;
document.wePage.submit();

}
 if (document.layers)
{
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown=clickNS4;
}
else if (document.all&&!document.getElementById)
{
	document.onmousedown=clickIE4;
}

document.oncontextmenu=new Function("return false")
<%--
document.oncontextmenu=new Function("alert(message);return false")
--%>

<%-- To disable F3,F5 and F11 key on page --- starts --%>
	document.onkeydown = function()
	{	
		if (114 == event.keyCode || 116 == event.keyCode)
		{
			event.keyCode = 0;
			return false;
		}		
	}
<%-- To disable F3,F5 and F11 key on page --- ends --%>
</script>

<body>
<div id='statusbar' style="position: absolute;"><table><tr><td id='imgtd'>&nbsp;</td><td align='left' valign='middle' id='statuBarTd1'></td></tr></table></div>
<div id="fadeBackground" class="fade_background"></div>

	<div id="pageContent">
		<div id="inContent">
			<div id="header" style="display:none">
				<jsp:include page="blank.jsp" />
			</div>			
			<div id="toolbar" style="display:none">
				<jsp:include page="blank.jsp" />
			</div>					
			<div id="nav" style="display:none">
				<jsp:include page="blank.jsp" />
			</div>						
			<div id="currentApplication" style="display:none">
				<jsp:include page="blank.jsp" />
			</div>				
<div id="content" style="width: 100%;">
	<fmtw:setBundle basename="resources.CommonLables" var="ErrorPageLables" scope="request"/>
 	<cw:set var="msgVar" value="ERRORPG.INTLSERVER" ></cw:set>

<center>
<table border=0 cellpspacing=0 cellpadding=2 width="400" height="400">
	<tr>
		<td valign="middle" align="center"></td>
		<td height="100%">
		<hdiits:form name="wePage" method="POST" validate="false" action="javascript:submitErrorForm();">
		<input type="hidden" name="csrfToken" value="${csrfToken}"/>
			<fieldset style="width:100%; align: left" class="tabstyle">
				<legend>Alert</legend>
					<table border=0 width="100%" cellpadding="2">
						<tr>
							<td valign="middle" align="center">&nbsp;</td>
							<td valign="middle" align="center">
								<font style="font-size: 14px; font-weight: normal;">
							 		<hdiits:caption captionid="${msgVar}" bundle="${ErrorPageLables}" captionLang="single" />
								</font>
  						    </td>
						</tr>
						<tr>
							<td colspan=2 valign="middle" align="center">
							<hdiits:submitbutton name="okbutton" value="OK" /></td>
						</tr>
					</table>
			</fieldset>				
		</hdiits:form>
		</td>
	</tr>
</table>
</center>

</div>

<div id="footer">
    <jsp:include page="footer.jsp" />
</div>		
</div>
</div>
</body>					
  			   