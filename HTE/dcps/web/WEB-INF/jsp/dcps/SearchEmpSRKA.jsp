<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />

<script>
function ReturnToSearchForSRKA()
{
	var url = "ifms.htm?actionFlag=loadSearchEmpForSRKA&elementId=700174";
	self.location.href = url;	
}

function printForm1()
{
	var noOfEmployeesSelected = 0;
	var flag = 0;
	var totalSelectedEmployees=document.getElementById("totalCount").value;

	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}
	
	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			for(i=0;i<totalSelectedEmployees;i++)
			{
				if(document.getElementById("chkbxFormVeri"+i).checked == true)
					{
						var EmpId = document.getElementById("chkbxFormVeri"+i).value;
						url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
						window_new_update(url);
					}
			}
		}
		else
		{
			alert('Please select only one form');
		}
	}
	else
	{
		alert("Please select a form");
	}
}

function printForm1Acmnt()
{
	var noOfEmployeesSelected = 0;
	var flag = 0;
	var totalSelectedEmployees=document.getElementById("totalCount").value;

	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}
	
	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			for(i=0;i<totalSelectedEmployees;i++)
			{
				if(document.getElementById("chkbxFormVeri"+i).checked == true)
					{
						var EmpId = document.getElementById("chkbxFormVeri"+i).value;
						url = "ifms.htm?actionFlag=reportService&reportCode=700007&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
						window_new_update(url);
					}
			}
		}
		else
		{
			alert('Please select only one form');
		}
	}
	else
	{
		alert("Please select a form");
	}
}

function validateCriteria()
{
	var sevarthId = document.getElementById("txtSevarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	
	if(sevarthId == "" && txtEmployeeName == "")
	{
		alert('Please Enter Search Criteria');
		return false;
	}
	
	return true ;
}
function submitSearchDetails()
{
	if(!validateCriteria())
	{
			return false ;
	}
	var sevarthId = document.getElementById("txtSevarthId").value;
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim(); 
	var url = "ifms.htm?actionFlag=searchEmpForSRKA&sevarthId="+sevarthId+"&employeeName="+txtEmployeeName;
	document.EmpSearchForm.action = url ;
	document.EmpSearchForm.submit();
}
function clearAllfields()
{
	document.getElementById("txtSevarthId").value = "";
}
</script>

<hdiits:form name="EmpSearchForm" id="EmpSearchForm"
	encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="csrfToken" value="${csrfToken}"/>
	<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.SEARCHEMPLOYEE" bundle="${dcpsLables}"></fmt:message></b></legend>

		<table border="0" width="40%" align="left" cellpadding="4"
			cellspacing="4">
			
			<tr>
				<td width="21%" align="left" ><fmt:message
					key="CMN.SEVARTHID" bundle="${dcpsLables}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtSevarthId" style="text-transform: uppercase" size="30"
					name="txtSevarthId" /></td>
			</tr>
	
			<tr align="center">
				<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
											bundle="${dcpsLables}" /></td>
				<td width="50%" align="left"><input type="text"
											id="txtEmployeeName" size="30" style="text-transform: uppercase"
											name="txtEmployeeName" />
				<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
			</tr>
	
			<tr>
				<td style="width: 50%" align="center" colspan="2">
				<table border="0" width="70%" align="center">
					<tr>
						<td ><hdiits:button name="btnSubmitData"
							id="btnSubmitData" type="button" captionid="BTN.SEARCH"
							bundle="${dcpsLables}" onclick="submitSearchDetails();" /></td>
						<td ><hdiits:button name="btnClearAllFields"
							id="btnClearData" type="button" captionid="BTN.CLEAR"
							bundle="${dcpsLables}" onclick="clearAllfields();" /></td>
						<td ><hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" 
							bundle="${dcpsLables}" onclick="ReturnToSearchForSRKA();"/></td>
					</tr>
				</table>
				</td>
			</tr>

	</table>
	</fieldset>
	<c:if test="${resValue.totalRecords != 0}">
	<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.SEARCHRESULT" bundle="${dcpsLables}"></fmt:message></b></legend>
			<c:set var="counterForSearch" value="0" ></c:set>
	<br>
    <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri${counterForSearch}" id="chkbxFormVeri${counterForSearch}" value="${vo[0]}"/>
		</display:column>
	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<c:out value="${vo[1]}" />
		</display:column>
		
		<c:choose>
			<c:when test="${vo[2] != null && vo[2] != ''}">
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DCPSID" >		
						<c:out value="${vo[2]}" />
				</display:column>
			</c:when>
			<c:when test="${vo[7] != null && vo[7] != ''}">
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.SEVARTHID" >		
						<c:out value="${vo[7]}" />
				</display:column>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
				
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.GENDER" >    			
    		<c:out value="${vo[3]}"></c:out>
    		<%-- <c:choose>
					<c:when test="${vo[3] == 'M'}">
						<c:out value="Male"></c:out>
					</c:when>
					<c:when test="${vo[3] == 'F'}">
						<c:out value="Female"></c:out>
					</c:when>
					<c:otherwise>
						<c:out value="Transgender"></c:out>
					</c:otherwise>
    		</c:choose> --%>
		</display:column>
      	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="birthDate"/>
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >		
				<c:out value="${vo[5]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPDESIG" >		
				<c:out value="${vo[6]}"></c:out>
				
				<input type="hidden" name="hidSevarthId${counterForSearch}" id="hidSevarthId${counterForSearch}" value="${resValue.lStrSevarthId}"/>
				<input type="hidden" name="hidName${counterForSearch}" id="hidName${counterForSearch}" value="${resValue.lStrEmpName}"/>
				<c:set var="counterForSearch" value="${counterForSearch+1}"></c:set> 
				
		</display:column>
		
		<display:column style="height:35;text-align: center;"  class="oddcentre" titleKey="CMN.DDOCODE" headerClass="datatableheader" sortable="true">
			<c:out value="${vo[8]}"></c:out>
		</display:column>
		
		<display:column style="height:35;text-align: center;"  class="oddcentre" titleKey="CMN.DDONAME" headerClass="datatableheader" sortable="true">
			<c:out value="${vo[9]}"></c:out>
		</display:column>

	</display:table>
	
		<input type="hidden" id="totalCount" name="totalCount" value="${counterForSearch}" /> 
		<br>
	<div align="center">
		<hdiits:button name="btnBackAgain" id="btnBackAgain" type="button"  captionid="BTN.BACK" 	bundle="${dcpsLables}" onclick="ReturnToSearchForSRKA();"/>	
		<hdiits:button name="btnPrintForm1" id="btnPrintForm1" type="button"  captionid="BTN.PRINTFORM1" bundle="${dcpsLables}" onclick="printForm1();"/>
		<hdiits:button name="btnPrintForm1Ackngmnt" id="btnPrintForm1Ackngmnt" type="button" style="width: 250px" captionid="BTN.PRINTFORM1ACKNMNT" bundle="${dcpsLables}" onclick="printForm1Acmnt();"/>
	</div>
	</fieldset>
	</c:if>
	
	<input type="hidden" name="hidSearchFromSRKA" id="hidSearchFromSRKA" value="searchBySRKA" />
</hdiits:form>

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromSRKA}" className="autocomplete" minimumCharacters="7" indicator="roleIndicatorRegion" />