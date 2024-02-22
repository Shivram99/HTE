<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script language="javascript">
		function showRpt(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=50,left=25,width=950,height=400"); 
		}	
	</script>
</head>
<link href="common/css/TableBorders.css" rel="stylesheet">
<link href="common/css/WebGUIStandards.css" rel="stylesheet">
<body>
<hdiits:form name="frmDailyVitoIn" validate="true" >
<hdiits:table width="90%" align="center" rules="TablerowBG2">
	<hdiits:tr>
		<hdiits:td align="center">
			<b>Treasury > Bill Processing > Audit Branch > Reports > Auditor wise Daily Vito</b>
		</hdiits:td>
	</hdiits:tr>
	<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
		<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	<hdiits:tr align="center">
		<hdiits:td >
			<b>AUDITOR WISE DAILY VITO</b>
		</hdiits:td>
	</hdiits:tr>
	<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	<hdiits:tr>
		<hdiits:td>
			<hdiits:table width="90%" align="center">
				<hdiits:tr>
					<hdiits:td width="25%" align="left">
						<fmt:message key="CMN.FROM_DATE" bundle="${billprocLabels}" ></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%" align="left">
						:&nbsp;<hdiits:text name="txtFromDate" size="10" readonly="true"></hdiits:text>
						<img src="common/images/calendar.gif" >
					</hdiits:td>
					<hdiits:td width="25%" align="left">
						<fmt:message key="CMN.TO_DATE" bundle="${billprocLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%" align="left">
						:&nbsp;<hdiits:text name="txtToDate" size="10" readonly="true"></hdiits:text>
						<img src="common/images/calendar.gif" >
					</hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td>
						Auditor
					</hdiits:td>
					<hdiits:td>
						:&nbsp;<hdiits:select name="cmbAuditor">
							<hdiits:option value="0" >------Select------</hdiits:option>
							<hdiits:option value="1" >K.C. Patel</hdiits:option>
							<hdiits:option value="2" >R.M. Shah</hdiits:option>
							<hdiits:option value="3" >P.K. Parikh</hdiits:option>
						</hdiits:select>
					</hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td width="50%" colspan="2"></hdiits:td>
				</hdiits:tr>		
			</hdiits:table>
		</hdiits:td>
	</hdiits:tr>
	<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	<hdiits:tr>
		<hdiits:td>
			<center>
				<hdiits:button type="button" name="btnGenRpt" value="Generate Report" onclick="javascript:showRpt('ifms.htm?viewName=adtDailyVitoRpt');"></hdiits:button>
				<hdiits:button type="button" name="btnClose" value="Close" onclick="window.location.href='indexReport.jsp'"></hdiits:button>
			</center>
		</hdiits:td>
	</hdiits:tr>					
</hdiits:table>

</hdiits:form>
	
</body>
</html>