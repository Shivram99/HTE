<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EmpStatistics" value="${resValue.EmpStatistics}"></c:set>
<c:set var="lFlag" value="${resValue.lFlag}"></c:set>

<script type="text/javascript" src="script/common/common.js"></script>
<script>
function generateExcel()
{
	var Ddocode = document.getElementById("txtDdocode").value;
	alert("Ddocode"+Ddocode);
	var url = "ifms.htm?actionFlag=generateExcel&Ddocode="+Ddocode+"&empStat=Y";
	document.frmEmpStatistics.action = url ;
	document.frmEmpStatistics.submit();
}

function getEmpStatistics()
{	
	var Ddocode = document.getElementById("txtDdocode").value;
	if(Ddocode.trim() == "")
	{
		alert("Ddocode Cannot be Empty.");
		document.getElementById("txtDdocode").focus();		
		return false;
	}		
	else
	{
		var url = "ifms.htm?actionFlag=loadEmpDtlsDdoWise&Ddocode="+Ddocode+"&empStat=N&elementId=700215";
		document.frmEmpStatistics.action = url ;
		document.frmEmpStatistics.submit();
	}
		
}
function saveReport() 
{
	document.execCommand("SaveAs");
}
function printReport() 
{

	//document.getElementById('btnExporttoExcel').style.visibility = 'hidden'; // hide
	//document.getElementById('Back').style.visibility = 'hidden'; // hide   
	//document.getElementById('Save').style.visibility = 'hidden'; // hide   
	window.print();
	document.getElementById('Print').style.visibility = 'visible'; // show 
	//document.getElementById('Back').style.visibility = 'visible'; // show 
	//document.getElementById('Save').style.visibility = 'visible'; // show 

	
}
</script>
<hdiits:form name="frmEmpStatistics" action="" id="frmEmpStatistics" encType="multipart/form-data" validate="true" method="post">
<c:if test="${lFlag != 'Y'}">
<fieldset class="tabstyle">
<table width="70%" align="center" >
		<tr>
			<td><fmt:message key="DDOCODE" bundle="${commonLables}"></fmt:message>	</td>
			<td><input type="text" maxlength="10" id="txtDdocode" name="txtDdocode" onkeypress="digitFormat(this);" value="${resValue.Ddocode}"/>	<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
			<td><hdiits:button name="btnSubmit" id="btnSubmit" type="button" captionid="EIS.Submit" bundle="${commonLables}" onclick="getEmpStatistics()" /></td>
		</tr>
</table>
</fieldset>
</c:if>
	
<c:if test="${EmpStatistics != null && EmpStatistics[0] != null}">
<fieldset class="tabstyle" ><legend>Employee Statistics</legend>
	<div class="scrollablediv" >	
    <display:table list="${EmpStatistics}"  id="vo" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Employee Name" >		
				<c:out value="${vo[1]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="HTESevaarth Id" >					
				<c:out value="${vo[0]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Date of Birth" >	
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[15]}" var="DOB"/>					
				<c:out value="${DOB}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" title="Employee Type" >		
				<c:out value="${vo[16]}"></c:out>				 		
 		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Cadre"  >	
			<c:out value="${vo[11]}"></c:out>		
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" title="Post Name" >		
				<c:out value="${vo[3]}"></c:out>				 		
 		</display:column>
 		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Post Type"  >
			<c:if test="${vo[14]==10001198130}">
				<c:out value="Temporary"></c:out>
			</c:if> 
			<c:if test="${vo[14]==10001198129}">
				<c:out value="Permanent"></c:out>
			</c:if> 
			<c:if test="${vo[14]==10001198155}">
				<c:out value="Statutory"></c:out>
			</c:if>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Post Start Date"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="startDate"/>		
				<c:out value="${startDate}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Post End Date"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[5]}" var="endDate"/>		
				<c:out value="${endDate}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Date of Joining"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}" var="DOJ"/>		
				<c:out value="${DOJ}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Date of Service Expiry"  >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[7]}" var="DOS"/>		
				<c:out value="${DOS}"></c:out> 
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Scale Description"  >	
			<c:out value="${vo[12]}"></c:out>		
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Basic Salary" >		
				<c:out value="${vo[2]}"></c:out>				 	
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="7PC Basic Salary" >		
				<c:out value="${vo[21]}"></c:out>				 	
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="7PC Level" >		
			<c:if test="${empty vo[22]}">
		<c:out value="NA"></c:out>	
		</c:if>	
		<c:if test="${not empty vo[22]}">
		<c:out value="${vo[22]}"></c:out>	
		</c:if>					 	
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="PF Series"  >		
				<c:out value="${vo[9]}"></c:out> 
		</display:column>	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="GPF/DCPS Account No."  >	
			<c:out value="${vo[10]}"></c:out>		
		</display:column>			
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="GIS Group "  >	
			<c:out value="${vo[11]}"></c:out>		
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="GIS Value "  >	
			<c:out value="${vo[8]}"></c:out>		
		</display:column>
			
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Physically Handicapped "  >
			<c:if test="${vo[13]=='FALSE'}">
				<c:out value="-"></c:out>
			</c:if> 
			<c:if test="${vo[13]=='TRUE'}">
				<c:out value="Yes"></c:out>
			</c:if> 		
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Bank name "  >	
			<c:out value="${vo[18]}"></c:out>		
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Bank Branch Name "  >	
			<c:out value="${vo[20]}"></c:out>		
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Account No "  >	
			<c:out value="${vo[19]}"></c:out>		
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Pran No "  >	
			<c:if test="${empty vo[23] && vo[9]=='DCPS'}">
				<c:out value=""></c:out>
			</c:if> 
			<c:if test="${vo[9]!='DCPS'}">
				<c:out value="NA"></c:out>
			</c:if>
			<c:if test="${not empty vo[23] && vo[23]!='-'}">
				<c:out value="${vo[23]}"></c:out>
			</c:if> 	
		</display:column>		
		</display:table>	
	</div>
</fieldset>
</c:if>
<hdiits:button id="btnExporttoExcel" name="btnExporttoExcel" value="Export to Excel" classcss="bigbutton" type="button" onclick="generateExcel()"/>
<hdiits:button id="btnprintReport" name="btnprintReport" value="Print Report" classcss="bigbutton" type="button" onclick="printReport()"/>
</hdiits:form>
<ajax:autocomplete source="txtDdocode" target="txtDdocode" baseUrl="ifms.htm?actionFlag=getDdoCodeForAutoComplete"
	parameters="searchKey={txtDdocode}" className="autocomplete" minimumCharacters="4" indicator="roleIndicatorRegion"/>	
