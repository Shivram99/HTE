<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%
response.setContentType("application/vnd.ms-excel;");
%>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/xmldom.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>	
<c:set var="PKConstraint" value="${resValue.PKConstraint}" > </c:set>	
<script>

function checkFile()
		{
		var flag=0;
	
		
		if(flag==0)
			{
			document.frmGpfMonthlyUpload.submit();
			document.getElementById("submitM").disabled=true;
			}
		}
		

</script>

<hdiits:form name="frmGpfMonthlyUpload" validate="true" method="POST"
	action="hrms.htm?actionFlag=gpfMonUpload" encType="multipart/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<fmt:message key="GPF"/>
		</a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">

	<div id="tcontent1" class="tabcontent" tabno="0">

<!--<table width="100%">

 <tr bgcolor="#386CB7" >
		<td class="fieldLabel" colspan="4" width="100%">
		<font color="#ffffff">
		<strong><u>
		<hdiits:caption captionid="GPF.monBal" bundle="${gpfLables}"/>
		</u></strong>
		</font>
		</td>
	</tr>
</table>
	

-->

<hdiits:fieldGroup bundle="${gpfLables}" titleCaptionId="GPF.monBal">

</hdiits:fieldGroup>
<table>
	<tr>
	<td>
		<A  target="_blank" href="common/hrms/gpfTemplate/MonUploadFinal.xls">
		<fmt:message key="GPF.Template"/>
		</A>
	</td> 
</tr>
</table>

<table>
<tr>
	<td>
		
		<p id="excelFormat" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.selExcelFile" bundle="${gpfLables}"/>
			</font>
		</p>
	</td>
</tr>	
</table>

<table width=100% align="center">
	<tr> 
	<td> 
	<hdiits:caption captionid="downloadTemplate" bundle="${gpfLables}"/>
	</td>
		 	
	 	<tr align="center">
	 	
	 	
	 	<td align="center"><center>
	 		<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp" flush="true" >
			    <jsp:param name="attachmentName" value="MonthlyAttachment" />
			    <jsp:param name="formName" value="frmGpfMonthlyUpload" />
			    <jsp:param name="attachmentType" value="Document" />  
			    <jsp:param name="multiple" value="N" />   
			    <jsp:param name="rowNumber" value="1" />  
			    <jsp:param name="mandatory" value="Y"/>
			    <jsp:param name="attachmentSize" value="1" /> 
			      </jsp:include>
			      </center>
	 	</td>
	 	</tr>
	 	
	 	<tr align="center">
	 	<td><br><hr><br></td>
	 	</tr>
	 	<br>
	 	<tr align="center">
	 	<td align="center">
	 <hdiits:button type="button" name="submitM" id="submitM" captionid="GPF.uploadData" bundle="${gpfLables}" onclick="checkFile();"/>	 		
	 	</td>
	 	</tr>
	</table>
	</div>

</div>
 
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<script>
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
