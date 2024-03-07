<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.Date"%>
<%@page errorPage="webErrorPage.jsp" isErrorPage="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<fmt:requestEncoding value="UTF-8" />
<html>
<%@ page import="org.springframework.security.AuthenticationException"%>
<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
	<%@ page import="com.tcs.sgv.common.constant.RandomStringGenerator"%>
<fmt:setLocale value="en_US" scope="page" />
<fmt:setBundle basename="resources.CommonLables_en_US"
	var="englishLabels" scope="application" />
<fmt:setBundle basename="resources.CommonLables_gu" var="gujaratiLabels"
	scope="application" />
<fmt:setBundle basename="resources.Constants" var="constant"
	scope="request" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>

<%
	String alphaNumericString = RandomStringGenerator
			.getAlphaNumericString(20);
	session.setAttribute("randomString", alphaNumericString);
%>

<%
	String flag = (String)request.getAttribute("captchaValidateFlag");
	String userName = (String)request.getAttribute("userName");
	String pwd = (String)request.getAttribute("pwd");
	String captcha =(String)session.getAttribute("captcha123");
%>

<c:set value="<%=flag%>" var="flag"></c:set>
<c:set value="<%=userName%>" var="userName"></c:set>
<c:set value="<%=pwd%>" var="pwd"></c:set>
<c:set value="<%=captcha%>" var="captcha"></c:set>

<fmt:message var="digitalSignature" key="ENABLE_DIGITAL_SIGNATURE"
	bundle="${constant}" />
<fmt:setBundle basename="resources.Project" var="project" scope="page" />

<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/login.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/taglib.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/keyboard.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/statusbar.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/themes/ifmsblue/exprcpt.css"/>' type="text/css" />
<style>
marquee {
    max-height: 50vh;
}
</style>
<script type="text/javascript"
	src='<c:url value="/script/login/statusbar.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/common/prototype.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<script type="text/javascript"
	src="<c:url value="/script/login/frmUtils_1.0.js"/>"></script>

<link rel="stylesheet" type="text/css" href='<c:url value="/themes/mahakosh.css"/>' />
<script type="text/javascript"
	src="<c:url value="/script/login/getLoginWindow.js"/>"></script>
<script type="text/javascript"
	src='<c:url value="/script/pensionpay/PensionersCorner.js"/>'></script>
	

<script type="text/javascript"
	src="<c:url value="/script/login/md5.js"/>"></script>
	
<script type="text/javascript">
	function getLoginPage() {
		openLoginWindow();

		window.open('', '_parent', '');
		win = top;
		self.opener = this;
		self.close();
	}

	function checkEnter() {
		var key;
		if (window.event)
			key = window.event.keyCode;
		else
			key = e.which;
		if (key == 13) {
			getLoginPage();
		}
	}

	function sitUnderMaintain(){
		alert("Page is Under Development");
	}
</script>

<script>

function startLoginToShalarth() {
	alert("hello i m roshaN");
	var urlToOpen = "./login.jsp?user=1";
	var mwname = 'IFMS';
		var dt = new Date();
		mwname = mwname + dt.getMilliseconds();
	
	var userName = document.getElementById('username').value;
	//alert("username is *****"+userName);
	var pwd = document.getElementById('password').value;
	//alert("pwd is *****"+pwd);
	var prop = 'width='+screen.availWidth+',height='+screen.availHeight+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	var child= window.open(urlToOpen, mwname, prop);
	child.moveTo( 0, 0 );
	child.resizeTo(screen.availWidth, screen.availHeight);
	child.focus();

	document.forms[0].j_username.value=userName;
	document.forms[0].j_password.value=pwd;
	var pwd1=CryptoJS.MD5(pwd).toString();
	document.forms[0].j_password.value=pwd1;

	document.forms[0].btnSubmit.disabled = true ;
	showProgressbar_login('Signing in...<br>Please wait...');
	window.setTimeout('document.forms[0].submit();',500);
	//window.open('', '_parent', '');
	//win = top;
	//self.opener = this;
	//self.close();
}

function capLock(e)
{ 		
	kc = e.keyCode?e.keyCode:e.which;
	sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false); 
	
	
	//alert("the msg is" + document.getElementById('divErrorMsg').innerHTML+"|");			
	if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk)){				

		document.getElementById('divErrorMsg').innerHTML='Caps Lock is on. <br>';	
		document.getElementById('divErrorMsg').style.fontFamily='arial';
		document.getElementById('divErrorMsg').style.fontSize='15px';
		document.getElementById('divErrorMsg').style.fontWeight='900';
		document.getElementById('divErrorMsg').style.color='red';
		document.getElementById('divErrorMsg').style.display='inline';
		document.getElementById('divErrorMsg').style.visibility='visible';
	}  
	else if(document.getElementById('divErrorMsg').innerHTML.toLowerCase() == 
		'Caps Lock is on. <br>'.toLowerCase())
	{
		document.getElementById('divErrorMsg').style.visibility='hidden';
	}
} 
function checkEnterForLogin()
{
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
		key = e.which;
	if (key==13) {
		startLoginInShalarth();
	}
}

function forgotPassword1()
{
		showProgressbar_login('Please wait...<br>Your Request is in Progress.');
		var varLocale = "";
		if(document.forms[0].locale[0].checked == true) varLocale = document.forms[0].locale[0].value;
		else varLocale = document.forms[0].locale[1].value;
		document.forms[0].action = "hdiits.htm?viewName=acl-forgotPassword&locale="+varLocale;
		document.forms[0].submit();
}

function forgotPassword()
{
	showProgressbar_login('Please wait...<br>Your Request is in Progress.');
	document.forms[0].action = "hdiits.htm?viewName=forgotPasswordRedirect";
	document.forms[0].submit();
}

function showProgressbar_login_page(message)
{

	if( !message ) message = 'Please wait<br>Your Request is in progress...';
	disableBackground_login_page();
	var statusBar =	document.getElementById('statusbar');
	var statusbarwidth = 360;
	var statusbarheight = 100;
	statusBar.style.visibility = 'visible';
	document.getElementById('statuBarTd1').innerHTML = message;
	statusBar.style.left = ((document.body.offsetWidth - statusbarwidth)/2)+'px';
	var st = document.documentElement.scrollTop;
	var sh = document.documentElement.scrollHeight;
	var ch = document.documentElement.clientHeight;
	statusBar.style.top = (st + ((ch - statusbarheight)/2))+'px';
	statusBar.style.top = ( (sh/2) - (statusbarheight/2) )+'px';
}
function disableBackground_login_page()
{
	var st = document.documentElement.scrollTop;
	var sh = document.documentElement.scrollHeight;
	var ch = document.documentElement.clientHeight;
}
function enableBackground_login()
{

}
function backToLogin()
{
	document.getElementById('loginControlsTD').style.display='';
	document.getElementById('MainTable').style.display='';

	document.getElementById("ForgotPwdTable").style.display='none';
}

function goToCOntr44oller()
{
 var ajaxRequest;  // The variable that makes Ajax possible!
	alert("hiii");
 try{
   		// Opera 8.0+, Firefox, Safari
   		ajaxRequest = new XMLHttpRequest();
   		alert("hiii");
 	}
 catch (e)
 	{
   		// Internet Explorer Browsers
   		try{
      			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
   			}
   			catch (e) 
   			{
      			try{
         			ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
      				}
      			catch (e)
      				{
         				// Something went wrong
         				alert("Your browser broke!");
         				return false;
      				}
   			}		
 	}
 alert("hiii333");
 ajaxRequest.onreadystatechange = function()
 {
   	if(ajaxRequest.readyState == 4)
   	{
      var ajaxDisplay = document.getElementById('div1');
      ajaxDisplay.innerHTML = ajaxRequest.responseText;
   }
 }
 alert("hiii333");
 var action="getCaptcha";
 var queryString = "?action=" + action ;
 ajaxRequest.open("post", "http://localhost:8080/dcps/CaptchaController" +queryString, true);
 ajaxRequest.send(null); 
}


function goToCOntroller()
{
	document.getElementById('captcha_code').value='';
	 var text = "";
	 var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	 for( var i=0; i < 6; i++ )
	    text += possible.charAt(Math.floor(Math.random() * possible.length))+' ';

   // var g = Math.ceil(Math.random() * 10)+ '';  
   	var code = text;
   //var code="1"; 
    document.getElementById("txtCaptcha").innerHTML = code;
    document.getElementById("captcha").value=code;
    
  //  document.getElementById("txtCaptcha").style.color  = "red";
   // alert(code.split(' ').join('').length);
    document.getElementById("capLength").value = code.split(' ').join('').length;
}
function ValidCaptcha(){
	//alert("hiii");
	 //var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
	document.getElementById('divCaptchaMsg').style.visibility='hidden';
    var str1 = document.getElementById('captcha').value;
    var str2 = document.getElementById('captcha_code').value;
   
    str1=str1.split(' ').join('');
    str2=str2.split(' ').join('');
    var str1Len=document.getElementById('capLength').value;
    //alert("str1Len"+str1Len);
    var str2Len = str2.length;
   // alert("str2Len"+str2Len);
    //alert("string 1 is*******"+str1);
    //alert("str2 2 is*******"+str2);
	if(str2Len >=str1Len){
    
    if (str1 == str2) {
    	
    	
    	
        return true;
        document.getElementById('divCaptchaMsg').style.visibility='hidden';
    }

    else{
    	document.getElementById('captcha_code').value='';
    	document.getElementById('divCaptchaMsg').innerHTML='Captcha not Matched. <br>';	
		document.getElementById('divCaptchaMsg').style.fontFamily='arial';
		document.getElementById('divCaptchaMsg').style.fontSize='13px';
		document.getElementById('divCaptchaMsg').style.fontWeight='900';
		document.getElementById('divCaptchaMsg').style.color='red';
		document.getElementById('divCaptchaMsg').style.display='inline';
		document.getElementById('divCaptchaMsg').style.visibility='visible';
        goToCOntroller();   
   		return false;
    } 
   }
}



function resetAll(){

	document.getElementById('username').value='';
	document.getElementById('password').value='';
	document.getElementById('captcha_code').value='';
	//goToCOntroller();
}



function getDashBoard()
{
	showProgressbar_login('Please wait...<br>Your Request is in Progress.');

	document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1";
	document.forms[0].submit();
}
function validateCaptcha()
{
	//showProgressbar_login('Please wait...<br>Your Request is in Progress.');
	//alert("helloooo");
	document.forms[0].action = "hdiits.htm?viewName=captchaRedirect&executeFlag=1";
	document.forms[0].submit();
	
}


function refresh()
{
	//alert("hiiii inside refresh");
document.getElementById("captchaImageNew").src="./CaptchaServlet?"+Math.random();

}
</script>

<style type="text/css">
 .textboxSTyle { 
     border: 1px solid #848484; 
    -webkit-border-radius: 30px; 
    -moz-border-radius: 30px; 
    border-radius: 30px; 
    outline:0; 
    height:25px; 
    width: 275px; 
    padding-left:10px; 
    padding-right:10px; 
 } 
.input_img 
{
 background-image: url("images/bgcolor.gif"); 
text-align: center;
 border: medium none;
  font-weight: bold;
  color: red;
  font-family: Modern;
  height: 27px;
 width: 135px;
 font-size: 20px;
}


.align
{
	margin: 0 auto;
	width: 250px;
	 
	}
	
.bigbutton {
	padding: 2px;
	background: #FFF url(themes/ifmsblue/images/wintop.jpg) repeat;
	border: 1px solid black;
	color: white;
	font-family: 'verdana';
	font-weight: bold;
	font-size: 11px;
}

.tabstyle {
	border-width: 5px 1px 1px 1px;
	border-color: #c06f20; /*change*/
	border-style: solid;
	padding: 0px; 
}

</style>

<style>
      blink {
        animation: blinker 0.6s linear infinite;
        color: #1c87c9;
       }
      @keyframes blinker {  
        50% { opacity: 0; }
       }
       .blink-one {
         animation: blinker-one 1s linear infinite;
       }
       @keyframes blinker-one {  
         0% { opacity: 0; }
       }
       .blink-two {
         animation: blinker-two 1.4s linear infinite;
       }
       @keyframes blinker-two {  
         100% { opacity: 0; }
       }
</style>

 
<%
	String notice = request.getParameter("n");
%>
<c:set value="<%=notice%>" var="notice"></c:set>

<body onbeforeunload="Close()"  onload="javascript: goToCOntroller();"  onunload="HandleOnClose()" onload="" oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
<form name="loginForm" autocomplete="off" 
			action="<c:url value='j_spring_security_check'/>" method="POST">
			<input type="hidden" value="<%=alphaNumericString%>" id="alphaNumericString" name="alphaNumericString"/>

<input type="hidden" value="${flag}" id="flag">
<input type="hidden" value="${userName}" id="userName">
<input type="hidden" value="${pwd}" id="pwd">

<c:if test="${notice == null}">

<center>
<br/><br/><br/>
<fieldset class="tabstyle"
	style="width: 85%; background-color: #E4E5ED;">
<table id="MainTable" width="100%" border="0">
	<tr>
		<td colspan="3"><!--<div class="firsttext">Integrated Financial Management System - Mahakosh </div>
		     <div class="secondtext">Finance Department, Government of Maharashtra</div>-->
		<img src="images/HomePageImages/FianlHomePG_1_11.jpg" width=100%
			style="background-repeat: no-repeat;"></img></td>
	</tr>
	<!-- <tr>
	  <td colspan="3"><div><font color="red"> Important Notice : Please untick component GIS Arrear recovery from Employee Eligibility for Allowances and Deductions screen in Assistant login and tick to
					GIS Arrear on the same screen. Last date for this is 16th December 2013.</font></div><br/> 
	</td>
	</tr> -->
	<!-- <tr>
		<td colspan="3"><div style='text-align: center'>
									<b> <font
										style="font-family: arial; color: #a50000; font-size: 16px;">
											Note: This application is ONLY compatible for Internet
											Explorer browser  (à¤¹à¥‡ à¤¸à¤‚à¤•à¥‡à¤¤à¤¸à¥à¤¥à¤³ à¤«à¤•à¥à¤¤ à¤‡à¤‚à¤Ÿà¤°à¤¨à¥‡à¤Ÿ à¤à¤•à¥à¤¸à¤ªà¥à¤²à¥‹à¤°à¤°
											à¤¬à¥à¤°à¤¾à¤‰à¤à¤°à¤¸à¤¾à¤ à¥€ à¤¸à¥à¤¸à¤‚à¤—à¤¤ à¤†à¤¹à¥‡) </font>
									</b>
								</div>
</td>
	</tr> -->


	<tr class="sideBarNotice">
		<td style="width: 30%" align='center'>HTESevaarth Login</td>
		<td style="width: 40%" align='center'>About HTESevaarth</td>
		<td style="width: 30%;" align='center'>Notice Board</td>
	</tr>
	
	<tr>
		
<td style="width: 30%" align="left" bordercolor="#c06f20" valign="top">
		<div style="overflow-y:auto;height:250px">
			
		<ul>
			<table>
			<tr></tr><tr></tr><tr>
			<td colspan="6" align="center"><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
				<%
										session.setAttribute("mainFunc","SetNormalWin");
										// error1 = Your IP Address is blocked.
										String errors[] = request.getParameterValues("error"); 
										if(errors != null && errors.length > 0)
										{
											for(int x=0;x<errors.length;x++)
											{
												if(errors[x].equals("error1"))
												{
											    	out.print("Your IP Address is blocked.");						    			
											    }
											}
										}
										else
										{
											AuthenticationException authenticationException=(AuthenticationException)session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
									
										    if(authenticationException!=null)
											{
										%> <%=authenticationException.getMessage()%> <%
											} 
										}
										%> </font>
										</td>
			</tr>
			<tr><td>
			<input type="hidden" name="capLength" id="capLength" >
			<input type="hidden" name="captcha" id="captcha"  >
			<input type="hidden" name="pass1" id="pass1">
			</td></tr>
			<tr>
			
			
			</tr><tr>

			<td>User Name</td><td><input type="text" id="username" value=""  name="username" color="black"  maxlength="18" style="width: 136px; height: 17px; border: 10px;" onkeypress="javascript: checkEnterForLogin();">
					
					</td></tr>
			<tr>
			</tr><tr>
			<td>Password</td><td><input type="password" id="password"  name="password" maxlength="16"  value=""
					class="keyboardInput"
					style="width: 136px; height: 17px; border: none;" 
					onkeypress="capLock(event);checkEnterForLogin();" ></td></tr>
				
				<tr><td>Captcha</td>             
                    <!-- <td><img id="captchaImageNew" src="./CaptchaServlet"><img src="images/rr.gif" onclick="refresh();" title="Click here to refresh the image"></td> -->
                    
                    	<td><font color="red"><b><label
															id="txtCaptcha" class="input_img" /></label></b> </font>
															<img
													src="images/rr.gif" onclick="goToCOntroller();"
													title="Click here to refresh the image"></td>
				</tr>  
                    <tr><td>&nbsp;</td>
                    <td><input type="text" id="captcha_code" onblur="" name="captcha_code" title="Enter Captcha here." class="required" value=""  style="width: 136px; height: 17px; border: 10px;"/></td>
                            </tr>
                            
            <tr><td>
            
            
            
            </td><td>
				<div id="divCaptchaMsg"><font color="yellow" style="font-family: Arial; font-size: 11px;"> <b><strong><label id="errorLabel">
				</label></strong></b></font>
				</div></td>
			</tr>
			
				<!-- <tr style=""><td colspan="5"><font class="Labelerrormsg"><a
					href="#"
					style="padding-left:80px; font-size: 10pt; text-decoration: none; color: black;"
					onclick="alert('Please contact support staff to update password');" >Forgot Password </a></font>
				</td></tr> -->
			<tr><td></td><td>
				<div id="divErrorMsg"><font color="yellow" style="font-family: Arial; font-size: 11px;"> <b><strong><label id="errorLabel">
				</label></strong></b></font>
				</div></td>
			</tr>
					<tr>
					<td colspan="3" align="center">
				<input type="button" value="Submit" name="btnSubmit"
					class="buttontag" onclick="startLoginInShalarth();"> <input
					type="button" value="Reset" class="buttontag"
					onclick="javascript : resetAll();"></td></tr>
		
			</table></ul>
			
		
		
		</div>
		
	
		
		</td>
		<td style="width: 40%;height:250px;text-align:justify;padding=10px" align="justify" bordercolor="#c06f20" valign="top" >
		HTESevaarth is a centralized web based Integrated 
		System of personnel information and Payroll for    
		Grant-in-Aid Institution in Maharashtra. It is 
		an important component of IFMS (Intregrated Finance
		Management System) with facility for data exchange
		with other important modules of Directorate of 
		Accounts and Treasuries. It is the first step in the direction 
		of achieving the aim of paper less electronic payroll 
		system i.e. paybill generation, electronic submission,
		electronic audit and electronic payment to employees 
		along with e-payslips.
		
		<br/><br/>
		
		</td>
	<td style="width: 22%" bordercolor="#c06f20" valign="middle">
		  <marquee direction="up" SCROLLDELAY=6 scrollamount="2.5" behavior="scroll" onmouseover="stop()" onmouseout="start()"> 
	<ul>
 
			<li><h4><font color="#736AFF" style="font-style: verdana"><b>Note - Update CSRF Form and Nps File Generate tab is available in respective JD under " Worklist  > NPS > Pran Registration " </b></font></h4></li>

<li><h4><font color="#736AFF" style="font-style: verdana"><b>Note - Update Pran No tab is available in respective JD under " Worklist  > NPS  " </b></font></h4></li>
			<li><h4><font color="#736AFF" style="font-style: verdana"><b>Note - DTO Registration Details Report is available in respective JD under "Report >> DTO Registration Details" </b></font></h4></li>
			<li><h4><font color="#736AFF" style="font-style: verdana"><b>Note - Revision of 6th to 7th PC has been started. for more details click on "Revision 6th PC to 7th PC User Manual".</b></font></h4></li>
			<li><h4><font color="#736AFF" style="font-style: verdana"><b>Note -6 PC employee use Traveling allowances(TA) and 7PC employee use Traveling allowances(7PC TA).Traveling allowances seperated for 6pc and 7pc Employees. So, we need to map for 7pc separately.
.</b></font></h4></li>
			<li>
			<h4><font color="#736AFF" style="font-style: verdana">
				<b>As per instruction from departmenrt we Updated TA. For any one have query related to TA check the employee statistics and change in pay post TAB then generate the Pay Bill.</b>
			</font></h4>
			
			</li>
			
			
 

			
			
	</ul>

	<span>
	
	</span>
	</div>
		</marquee>  
		</td>
		
	
	
	</tr>
	<tr class="sideBarNotice" >
	<td style="width: 30%" align='center'>7th Pay Comission</td>
	<td style="width: 40%" align='center'>Important Links/Level Matrix</td>
		<td style="width: 30%;" align='center'>Useful Documents</td>

		

		
	</tr>
	<tr align="left">
	<td style="width: 30%" bordercolor="#c06f20" valign="top">
		<ul>
			<li>
<!-- 				<a href="images/Support_Staff_Link_7.pdf" target="_blank" >Support Staff Link</a> -->
				<a href="images/Revision_6PC_To_7PC_HTE_Sevaarth.pdf" target="_blank" ><blink>Revision 6th PC to 7th PC User Manual</blink></a>
			</li>
				<!--<li>
 			<a href="images/MasterTrainers.pdf"target="_blank">District Master Trainers Support Staff Team</a> 
			</li> -->
			
		</ul>
		</td>
		<td style="width: 30%" bordercolor="#c06f20" valign="top">
		
		<ul>
			
			<li><a href="http://finance.maharashtra.gov.in" target="_blank">http://finance.maharashtra.gov.in</a></li>
			<li><label><font color="#066fcc">Level 1 Helpdesk e-mail - htesevaarth2[at]gmail[dot]com</font></label></li>
			  <li><label><font color="#066fcc">Level 1 Helpdesk LandLine - 022-61316426 </font></label></li>  
						
                      <!--  <li><label><font color="#066fcc">Calling time (10am to 1.30pm & 2.30pm to 6pm) <br> Lunch time (1.30pm to 2.30pm)</font></label></li> -->
                        <li><label><font color="#066fcc">Nodal Officer Details: <br> Mr. Hemant Pathak:- hemant[dot]pathak[at]nic[dot]in <br> or mashi2-hted[at]mah[dot]gov[dot]in  <br> 
                        desk5[at]dvet[dot]gov[dot]in <br>
			 <!--  022-61316426  --> </font></label></li>


			
		</ul>
		
		</td>
	
		<td style="width: 30%" valign="top" style="padding-left:5px;"> 
		
	
	
	
		<ul>

			<li>
				<a href="images/HTE_PRAN Update Utilities24_05-23.pdf" target="_blank">Pran Update Utility</a> 
			</li>

			<li>
				<a href="images/StepsUsingTribal.pdf" target="_blank">Step wise use of application</a> 
			</li>
				
			<li>
				<a href="images/FAQ.pdf" target="_blank">Frequently Asked Questions</a>
			</li>
	
			
	 	<li>
				<a href="#"  onclick="javascript:getDashBoard()" >Graphical Summary</a>
			
			</li>	
		<li><a href="images/User Guide for Issue Ticketing.pdf"target="_blank">User Guide for Issue Ticketing</a></li>
			
		
				
		</ul>
</td>	
	</tr>
	
	
</table>
</fieldset>
</center>
</c:if>


<body onbeforeunload="Close()" onunload="HandleOnClose()" onload="javascript: startLogin()">
<script type="text/javascript"
	src='<c:url value="/script/common/wz_tooltip.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/common/tip_balloon.js"/>'></script>
<div id='statusbar'>
<table style="font-family: arial;">
	<tr>
		<td id='imgtd'>&nbsp;</td>
		<td align='left' valign='middle' id='statuBarTd1'></td>
	</tr>
</table>
</div>
<!--<div style="width: 100%; text-align: center;">-->
<!--	 <a style="font-size: 10pt;font-weight: boldl;font-family:verdana;" href="javascript:openFaq();">-->
<!--	 	<b>Shalarth Frequently Asked Questions - Updated on (22/08/2012)</b>-->
<!--	 </a>-->
<!--	<sup><blink><b style="color: red;font-size: 14">New</b></blink></sup>-->
<!--</div>-->
<!--
<div style="width: 100%; text-align: center;">

	<sup><blink><b style="color: red;font-size: 10pt;font-family:verdana;">Coming Soon</b></blink></sup>
	<a style="font-size: 9pt;font-family:verdana;font-weight: bold;color: red;" href="images/pdf3.pdf" target="_blank" >Click Here - Contact List of School Education Department Officials - Click Here</a>
	<sup><blink><b style="color: red;font-size: 10pt;font-family:verdana;">Coming Soon</b></blink></sup>

</div>


<table>
<tr align="center">
	

		<tr><td></td></tr>
		<tr align="center">
		<td>
			<a href="images/Do's_&_Don't.pdf" target="_blank" style="size:15px; color:#FF4000; "><b>Do's and Don'ts for using Shalarth</b></a>
		</td>
	</tr>
	
	<tr align="center">
		<td>
			<a href="images/District Master Trainer List .pdf" target="_blank" style="size:15px; color:#FF4000; "><b>District_master_trainer_list</b></a>
		</td>
	</tr>
	
</table>
<div style="width: 100%; text-align: center;">

	<sup><blink><b style="color: red;font-size: 14pt;font-family:verdana;">New</b></blink></sup>
	<a style="font-size: 12pt;font-family:verdana;font-weight: bold;color: red;" href="images/faq.pdf" target="_blank" ><b>Click Here - Frequently Asked Questions(FAQ) - Click Here</b></a>
	<sup><blink><b style="color: red;font-size: 14pt;font-family:verdana;">New</b></blink></sup>
	
</div>
-->
<br/><br/><br/><br/>








<br/><br/>
<table border=0 cellspacing=0 cellpadding=0
	style="font-family: arial; vertical-align: middle;"  align="center" border = 1 style="display:none">
	<tr>
		
		<td id=loginControlsTD style="display: ''">
		
			
			



		<table align="center" id="Table_01" border="0" cellpadding="0"
			cellspacing="0" style="display:none">
			<tr>
				<td colspan="6" align="center"><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
				<%
										session.setAttribute("mainFunc","SetNormalWin");
										// error1 = Your IP Address is blocked.
										String errors[] = request.getParameterValues("error"); 
										if(errors != null && errors.length > 0)
										{
											for(int x=0;x<errors.length;x++)
											{
												if(errors[x].equals("error1"))
												{
											    	out.print("Your IP Address is blocked.");						    			
											    }
											}
										}
										else
										{
											AuthenticationException authenticationException=(AuthenticationException)session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
									
										    if(authenticationException!=null)
											{
										%> <%=authenticationException.getMessage()%> <%
											} 
										}
										%> </font>
										</td>
															
			</tr>	
			<tr><td></td></tr>
			
			<tr>
				<td colspan="7" style="padding-left:0px;"><img src="images/loginImg/Login_img_2_01.gif"
					width="643" height="191" alt=""></td>
			</tr>
			<tr>
				<td rowspan="7"><img src="images/loginImg/Login_img_2_020.gif"
					width="291" height="284" alt=""></td>
				<td><img src="images/loginImg/Login_img_2_03.gif" width="81"
					height="21" alt=""></td>
				<td width="139" height="21"><input type="text" 
					value="<%=request.getParameter("userName") %>" name="j_username" maxlength="18"
					style="width: 136px; height: 17px; border: none;"
					onkeyup="javascript: checkEnter();"> <script
					type="text/javascript" language="JavaScript">
										document.forms[0].j_username.focus();
									</script></td>
				<td colspan="3"><img src="images/loginImg/Login_img_2_05.gif"
					width="132" height="21" alt=""></td>
			</tr>
			<tr>
				<td colspan="5"><img src="images/loginImg/Login_img_2_06.gif"
					width="352" height="9" alt=""></td>
			</tr>
			<tr>
				<td><img src="images/loginImg/Login_img_2_07.gif" width="81"
					height="23" alt=""></td>
				<td width="139" height="23" id="td_txtPassword"><input
					type="password" value="<%=request.getParameter("password") %>" name="j_password" maxlength="16"
					class="keyboardInput"
					style="width: 136px; height: 17px; border: none;"
					onkeypress="capLock(event);checkEnter();" ></td>
				<td colspan="3"><img src="images/loginImg/Login_img_2_09.gif"
					width="132" height="23" alt=""></td>
			</tr>
			</br></br>
			<tr>
				<td colspan="5" background="images/loginImg/Login_img_2_10.gif"
					height="19" alt="" align="left" id="vKeyboard">
				<font style="padding-left:80px; padding-top:50px; font-size: 12px; font-weight: bold; color: #006699;">Use Virtual Keyboard</font></td>
			</tr>
			<tr>
				<td><img src="images/loginImg/Login_img_2_11.jpg" width="81"
					height="21" alt=""></td>

				<td colspan="3"
					style="background-image: url('images/loginImg/Login_img_2_12.gif');">
				 <input type="radio" style="visibility:hidden;" name="locale" value="gu"
					onkeyup="javascript: checkEnter();">  <input type="radio"
					name="locale" style="visibility:hidden;" value="en_US" onkeyup="javascript: checkEnter();"
					checked="checked"> </td>
				<td><img src="images/loginImg/Login_img_2_13.gif" width="99"
					height="21" alt=""></td>
			</tr>
			<tr>
				<td colspan="3" align="center"
					style="background-image: url('images/loginImg/Login_img_2_14.gif');">
				<input type="button" value="Submit" name="btnSubmit"
					class="buttontag" onclick="javascript: startLogin();"> <input
					type="reset" value="Reset" class="buttontag"
					onclick="javascript : resetFields();"></td>
				<td><img src="images/loginImg/Login_img_2_15.gif" width="19"
					height="30" alt=""></td>
				<td><img src="images/loginImg/Login_img_2_16.gif" width="99"
					height="30" alt=""></td>
			</tr>

			<tr valign="top">
				<td colspan="5"
					style="background-image: url('images/loginImg/Login_img_2_017.gif');"
					width="352" height="161"><font class="Labelerrormsg"><a
					href="#"
					style="padding-left:80px; font-size: 10pt; text-decoration: none; color: black;"
					onclick="forgotPassword();" >Forgot Password </a></font>
				<c:if test="${digitalSignature eq 'Y'}"> 
										    &nbsp;&nbsp;&nbsp;&nbsp;
											<A href="#"
						style="font-size: 10pt; text-decoration: none; color: black;"
						onclick="loginWithSignature()">Login Using Digital Signature</A>
					<input style="display: none;" type="hidden" name="loginSignature"
						value="loginSignature">
					<input style="display: none;" type="hidden"
						name="loginRandomNumber" value="123">
				</c:if> <%-- New tr added to Login with Digital Signature ... end --%></td>
			</tr>
			<tr>
				<td><img src="images/loginImg/spacer.gif" width="288"
					height="1" alt=""></td>
				<td><img src="images/loginImg/spacer.gif" width="81" height="1"
					alt=""></td>
				<td><img src="images/loginImg/spacer.gif" width="139"
					height="1" alt=""></td>
				<td><img src="images/loginImg/spacer.gif" width="14" height="1"
					alt=""></td>
				<td><img src="images/loginImg/spacer.gif" width="19" height="1"
					alt=""></td>
				<td><img src="images/loginImg/spacer.gif" width="99" height="1"
					alt=""></td>
			</tr>
			<tr align="center">
	<td align="center">
	<div id="divErrorMsg"><font color="yellow" style="font-family: Arial; font-size: 11px;"> <strong><label id="errorLabel">
				</label></strong></font>
				</div>
	</td>
	</tr>
		</table>
		</form>
		</td>
	

	
	</tr>
	
</table>


<!--<a id="userManual" onclick="userManual(divUserManual)" style="size:15px; color:#FF4000;"><u>User Manual (04-02-2013)</u></a>
--><div id="divUserManual" style="display: none">
	<fieldset class="tabstyle">
<table align="center" width="70%" border="1" style="display:none">


	<tr>
	<td width="30%">MUNICIPAL CORPORATION/COUNCIL SCHOOLS</td>
	<td width="30%">PRIVATE AIDED SCHOOLS </td>
	<td width="30%">ZILLA PARISHAD SCHOOLS</td>
	</tr>
	<tr>
	
	<td width="20%">
		<a href="images/tree_images/1 Configuring the Institution.pdf" target="_blank">Configuring the Institution</a>
	</td>
	
		<td width="20%">
		<a href="images/portletImages/1 Configuring the Institution.pdf" target="_blank">Configuring the Institution</a>
	</td>
		
	
		<td width="15%">
		<a href="images/loginImg/1 Configuring the Institution.pdf" target="_blank">Configuring the Institution</a>
	
		</td>
		
	</tr>
	

	<tr width="20%">
	
	<td width="20%">
		<a href="images/tree_images/2 Process of Configuration.pdf" target="_blank">Process of Configration</a>
	</td>
	<td width="20%">
		<a href="images/portletImages/2 Process of Configuration.pdf" target="_blank">Process of Configration</a>
	</td>
	<td width="20%">
		<a href="images/loginImg/2 Process of Configuration.pdf" target="_blank">Process of Configration</a>
	</td>
	
	</tr>

	
	<tr width="20%">
	
	<td width="20%">
		<a href="images/tree_images/3 Entering Data of Institution.pdf" target="_blank">Entering Data of Institution</a>
	</td>
	<td width="20%">
		<a href="images/portletImages/3 Entering Data of Institution.pdf" target="_blank">Entering Data of Institution</a>
	</td>
	<td width="20%">
		<a href="images/loginImg/3 Entering Data of Institution.pdf" target="_blank">Entering Data of Institution</a>
	</td>
	
	
	
	
	</tr>
	

	<tr width="20%">
	
	<td  width="20%">
		<a href="images/tree_images/4 Entry of Posts.pdf" target="_blank">Entry of Posts</a>
	</td>
	<td  width="20%">
		<a href="images/portletImages/4 Entry of Posts.pdf" target="_blank">Entry of Posts</a>
	</td>
	<td  width="20%">
		<a href="images/loginImg/4 Entry of Posts.pdf" target="_blank">Entry of Posts</a>
	</td>
	
	
	
	
	
	</tr>
	
	
	<tr width="20%">
	
	<td width="20%">
		<a href="images/tree_images/5 Entry of Employee Details.pdf" target="_blank">Entry of Employee Details</a>
	</td>
	<td width="20%">
		<a href="images/portletImages/5 Entry of Employee Details.pdf" target="_blank">Entry of Employee Details</a>
	</td>
	<td width="20%">
		<a href="images/loginImg/5 Entry of Employee Details.pdf" target="_blank">Entry of Employee Details</a>
	</td>
	
	
	
	
	</tr>
	<tr width="20%">
	
	<td width="20%">
		<a href="images/loginImg/Master_Data_Configuration.pdf" target="_blank">Master Data Configuration</a>
	
		</td>
	
	<td width="20%">
		<a href="images/loginImg/Master_Data_Configuration.pdf" target="_blank">Master Data Configuration</a>
	
		</td>
	
	<td width="20%">
		<a href="images/loginImg/Master_Data_Configuration.pdf" target="_blank">Master Data Configuration</a>
	
		</td>
	
	
	
	
	</tr>
	
	
	
</table>
</fieldset>
	</div>
	
	
	

	<!--
	
	
	
	

	
	
	
	<table>
		<tr><td><br></td></tr>
		<tr align="center">
		<td>
			<a href="images/SupportStaffLink.pdf" target="_blank" style="size:15px; color:#FF4000; ">Support Staff Link</a>
		</td>
	</tr>
</table>





--><!--<B>HttpSession Information:</B> -->
<!-- <UL>
 <LI>Served From Server instance: <b><%= System.getProperty("com.sun.aas.instanceName") %></b></LI>
<LI>Executed Server IP Address: <b><%= java.net.InetAddress.getLocalHost().getHostAddress() %></b></LI> 
<LI>Executed From Server Port: <b><%= request.getServerPort()%></b></LI>
</UL>
-->
<!-- Copyright & Disclaimer -->




<table cellpadding="0" cellspacing="0" width="770" class="CopyrightLine"
	border="0" align="center" style="display:none">
	<tr>
	
	</tr><%--

	<tr valign="middle">
		<td align="center" class="Label" style="color:#848484;">Copyright Ã‚Â© 2012 Tata Consultancy Services Limited
	</tr>

	<tr valign="bottom">
		<td align="center" class="Label" style="color:#848484;">Site best viewed in <span
			class="Label" style="color: #4b78ad">Microsoft Internet
		Explorer 6.0+</span><span style="color:#848484;">&nbsp;in </span><span class="Label" style="color: #4b78ad">1024x768</span><span style="color:#848484;">&nbsp;resolution</span></td>
	</tr>
	
	<tr><td></td></tr>
	
	--%>
	
</table>





<!-- Added for Digital Singature  start..... -->
<c:if test="${digitalSignature eq 'Y'}">
	<OBJECT style="LEFT: 0px; TOP: 0px"
		CLASSID="clsid:5220CB21-C88D-11cf-B347-00AA00A28331">
		<PARAM NAME="LPKPath" VALUE="tcssigner/TCSSigner.lpk">
	</OBJECT>
	<OBJECT id=TCSSigner1 style="LEFT: 0px; TOP: 0px"
		codeBase="tcssigner/TCSSigner.cab#Version=6,1,0,0"
		classid="clsid:AD1959BC-3260-4003-AEF1-8C845E82EEB5" VIEWASTEXT>
		<PARAM NAME="_Version" VALUE="65536">
		<PARAM NAME="_ExtentX" VALUE="2646">
		<PARAM NAME="_ExtentY" VALUE="1323">
		<PARAM NAME="_StockProps" VALUE="0">
	</OBJECT>
	<script type="text/javascript" language="javascript">
		generateRandomNoForDigiLogin('${pageContext.request.contextPath}');
	</script>
</c:if>
<!--- Added for Digital Signature end..... -->

<input type="hidden" name="show_menu" value="Y" style="display: none">
<script type="text/javascript" language="javascript">
	init();
	Browser();
	setInterval('blinkIt()',300);
	var obj = document.getElementById('td_txtPassword');
	var lgnToolTipX = findPosX(obj) + 120 ;
	var lgnToolTipY = findPosY(obj) + 55;
	if(window.name == "")
	{
			window.location.href="/index.jsp";
	}
	 
	function Close()
	{

		if (event.clientY < 0 || window.event.altKey)
		{
			var wname = window.name;
			if(!window.opener || (window.name != "" && wname.substring(0,4) == "IFMS"))		
			{
				setTimeout('myclose=false',100);
				myclose=true;
			}
		}
	}

	function HandleOnClose()
	{
		if (myclose==true)
		{
		    var url = '${pageContext.request.contextPath}/'+"j_spring_security_logout?closeType=onclose";
		    var myAjax = new Ajax.Request(url,
			{
						method: 'post',
						asynchronous: false,
						onFailure: function(){ alert('Could Not Logout Properly... '); } 
			} );
		}
	}
</script>


<%--START added for forgot password--%>

<CENTER>
<fieldset class="tabstyle" style="width: 85%; background-color: #F6F7F9;">
<table id="ForgotPwdTable" width="100%" border="0" style="display: none;">
<tr><td width="100%" colspan="3">
<img width="100%" style="background-repeat: no-repeat;" src="images/HomePageImages/FianlHomePG_1_11.jpg" />
</td></tr>

<tr>
<td width="20%">User Name:</td>
<td width="30%" align="left"><input type="text"> </td>
<td width="50%">&nbsp;</td>
<td></td>
</tr>

<tr>
<td width="20%">Mobile Number:</td>
<td width="30%" align="left"><input type="text"> </td>
<td width="50%">&nbsp;</td>
</tr>

<tr>
<td width="20%">TAN Number:</td>
<td width="30%" align="left"><input type="text"> </td>
<td width="50%">&nbsp;</td>
</tr>

<tr>
<td width="100%" colspan="3" align="center">
<hdiits:button	name="BTN.BACK" id="Back" type="button" captionid="BTN.BACK" bundle="${englishLabels}" onclick="backToLogin();" style="width:15%"/>&nbsp; </td>
</tr>
</table>
</fieldst>
</CENTER>

<%--END added for forgot password--%>



<%
	//out.print("SessionID : " + session.getId());
	session.removeAttribute("locale");
	session.removeAttribute("loginVO");
	session.removeAttribute("loginDetailsMap");
%>
<script>
if(document.getElementById("flag").value == 'N'){

	alert("Captcha Not matched.");

	
}


if(document.getElementById("flag").value == 'Y'){
	//alert("matched");
	var user=document.getElementById("userName").value;
	var pwd=document.getElementById("pwd").value
	document.getElementById("username").value=user;
		document.getElementById("password").value=pwd;
	startLoginInShalarth(user,pwd);
}

</script>

<script type="text/javascript">

var _keySize = 256;
var _ivSize = 128;
var _iterationCount = 1989;

     function  generateKey(salt, passPhrase) {
	    return CryptoJS.PBKDF2(passPhrase, CryptoJS.enc.Hex.parse(salt), {
	      keySize: _keySize / 32,
	      iterations: _iterationCount
	    })
	  }

     function   encryptWithIvSalt(salt, iv, passPhrase, plainText) {
	    var key =generateKey(salt, passPhrase);
	    var encrypted = CryptoJS.AES.encrypt(plainText, key, {
	      iv: CryptoJS.enc.Hex.parse(iv)
	    });
	    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
	  }

     function  decryptWithIvSalt(salt, iv, passPhrase, cipherText) {
		  var key = generateKey(salt, passPhrase);
		  var cipherParams = CryptoJS.lib.CipherParams.create({
	      ciphertext: CryptoJS.enc.Base64.parse(cipherText)
	    });
		  var decrypted = CryptoJS.AES.decrypt(cipherParams, key, {
	      iv: CryptoJS.enc.Hex.parse(iv)
	    });
	    return decrypted.toString(CryptoJS.enc.Utf8);
	  }

     function encrypt(passPhrase, plainText) {
    	//  alert("Welcome"); 
		  var iv = CryptoJS.lib.WordArray.random(_ivSize / 8).toString(CryptoJS.enc.Hex);
		  var salt = CryptoJS.lib.WordArray.random(_keySize / 8).toString(CryptoJS.enc.Hex);
		  var ciphertext = encryptWithIvSalt(salt, iv, passPhrase, plainText);
	    return salt + iv + ciphertext;
	  }

     function decrypt(passPhrase, cipherText) {
		  var ivLength = _ivSize / 4;
		  var saltLength = _keySize / 4;
		  var salt = cipherText.substr(0, saltLength);
		  var iv = cipherText.substr(saltLength, ivLength);
		  var encrypted = cipherText.substring(ivLength + saltLength);
	    return decryptWithIvSalt(salt, iv, passPhrase, encrypted);
	  }

     

     function startLoginInShalarth()
     {
     	//alert("username is *****");
     	
     	 var str2 = document.getElementById('captcha_code').value;
     	 str2=str2.split(' ').join('');
     	 var str1=document.getElementById('captcha').value;
     	 str1=str1.split(' ').join('');
     	if(str1==str2){
     	var userName = document.getElementById('username').value;
     	//alert("username is *****"+userName);
     	var pwd = document.getElementById('password').value;
     	//alert("pwd is *****"+pwd);
     	//alert(document.forms[0].j_username.value);
     	if(userName==""){
     	alert("Please Enter User Name.");
     	return false;
     	}
     	if(pwd==""){
     		alert("Please Enter Password.");
     		return false;
     	}
     	document.forms[0].j_username.value=userName;
     	document.forms[0].j_password.value=pwd;
     	
     	//alert(document.forms[0].j_username.value);

     	pwd=document.forms[0].j_password.value;
     	
     	var pwd1=pwd;
     	
     	//var pwd1=CryptoJS.MD5(pwd).toString();
     	//document.forms[0].j_password.value=pwd1;
     	
     	var captcha=trim(document.getElementById("txtCaptcha").innerHTML);
     	
     			
     	 var encKey='<%=session.getAttribute("randomString")%>';

     			var UID1Encrypted = encrypt(encKey, pwd1);

     			document.getElementById("captcha_code").value = encrypt(encKey,
     					captcha);
     			// document.getElementById("randomString").value=encKey;

     			document.getElementById("pass1").value = encrypt("Message", captcha);

     			document.getElementById("password").value = UID1Encrypted;
     			//alert(document.forms[0].j_username.value);
     			document.forms[0].j_password.value = UID1Encrypted;

     			//document.getElementById("captcha_code").value='';
     			document.getElementById("captcha").value = encrypt(encKey, captcha);

     			document.forms[0].btnSubmit.disabled = true;

     			showProgressbar_login("Please wait<br>While Your Request is in Progress ...");

     			window.setTimeout('document.forms[0].submit();', 500);

     		}

     		else if (str2.length > 0) {

     			document.getElementById('captcha_code').value = '';
     			document.getElementById('divCaptchaMsg').innerHTML = 'Please enter correct captcha. <br>';
     			document.getElementById('divCaptchaMsg').style.fontFamily = 'arial';
     			document.getElementById('divCaptchaMsg').style.fontSize = '11px';
     			document.getElementById('divCaptchaMsg').style.fontWeight = '900';
     			document.getElementById('divCaptchaMsg').style.color = 'red';
     			document.getElementById('divCaptchaMsg').style.display = 'inline';
     			document.getElementById('divCaptchaMsg').style.visibility = 'visible';

     		} else {
     			document.getElementById('divCaptchaMsg').innerHTML = 'Please enter captcha. <br>';
     			document.getElementById('divCaptchaMsg').style.fontFamily = 'arial';
     			document.getElementById('divCaptchaMsg').style.fontSize = '11px';
     			document.getElementById('divCaptchaMsg').style.fontWeight = '900';
     			document.getElementById('divCaptchaMsg').style.color = 'red';
     			document.getElementById('divCaptchaMsg').style.display = 'inline';
     			document.getElementById('divCaptchaMsg').style.visibility = 'visible';
     		}

     	}
     
     function forgotPassword()
     {
     	showProgressbar_login('Please wait...<br>Your Request is in Progress.');

     	/*var varLocale = "";
     	
     	if(document.forms[0].locale[0].checked == true) varLocale = document.forms[0].locale[0].value;
     	else varLocale = document.forms[0].locale[1].value;	*/
     	
     	
     	 var str2 = document.getElementById('captcha_code').value;
     	 str2=str2.split(' ').join('');
     	 var str1=document.getElementById('captcha').value;
     	 str1=str1.split(' ').join('');
     	var userName = document.getElementById('username').value;
     	var pwd = document.getElementById('password').value;

     	document.forms[0].j_username.value=userName;
     	document.forms[0].j_password.value=pwd;
     	
     	//alert(document.forms[0].j_username.value);

     	pwd=document.forms[0].j_password.value;
     	
     	var pwd1=pwd;
     	var captcha=trim(document.getElementById("txtCaptcha").innerHTML);
     	
     			
     	 var encKey='<%=session.getAttribute("randomString")%>';
     	 
     		
     	 var UID1Encrypted = encrypt(encKey,pwd1);
     	
     	 document.getElementById("captcha_code").value=encrypt(encKey,captcha);
     	// document.getElementById("randomString").value=encKey;
     	 
     	 document.getElementById("pass1").value=encrypt("Message",captcha);
     	 
     	document.getElementById("password").value=UID1Encrypted;
     	//alert(document.forms[0].j_username.value);
     	document.forms[0].j_password.value=UID1Encrypted;
     	
     	//document.getElementById("captcha_code").value='';
     	document.getElementById("captcha").value=encrypt(encKey,captcha);

     	
     	
     	
     	
     	
     	//document.forms[0].action = "hdiits.htm?viewName=acl-forgotPassword&locale="+varLocale;
     	document.forms[0].action = "hdiits.htm?viewName=forgotPasswordRedirect";
     	document.forms[0].submit();
     }


</script>


   <script>
   function goToCOntroller()
   {
   	document.getElementById('captcha_code').value='';
   	 var text = "";
   	 var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

   	 for( var i=0; i < 6; i++ )
   	    text += possible.charAt(Math.floor(Math.random() * possible.length))+' ';

      // var g = Math.ceil(Math.random() * 10)+ '';  
      	var code = text;
      //var code="1"; 
       document.getElementById("txtCaptcha").innerHTML = code;
       document.getElementById("captcha").value=code;
       
     //  document.getElementById("txtCaptcha").style.color  = "red";
      // alert(code.split(' ').join('').length);
       document.getElementById("capLength").value = code.split(' ').join('').length;
   }
    </script>
    
    

</body>


</html>