<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.tcs.sgv.nps.valueobject.EmpDtlsCustomVO" %>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_DCPS.jpg";
</script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="NpsList" value="${resValue.lListTotalDdowiseEntries}"></c:set>
<c:set var="TotalAmt" value="${resValue.TotalAmt}"></c:set>
<c:set var="deputationFlag" value="${resValue.idDeputation}"></c:set>
<c:set var="empList" value="${resValue.empListServiceEndDateEmp}"></c:set>
<c:set var="FileStatus" value="${resValue.FileStatus}"></c:set>


<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript">

function getStartAndEndDateForYear()
{
	var year = document.getElementById("cmbFinYear").value ;
	year = document.getElementById("cmbFinYear").options[document.getElementById("cmbFinYear").selectedIndex].text;
	year = year.substring(0,4);
	var nextYear = Number(Number(year)+1);
	var nextYearString = nextYear.toString();
	var partOfYear = "01/04/" ;
	var partOfNextYear =  "31/03/" ;
	var startDate = partOfYear.concat(year);
	var endDate = partOfNextYear.concat(nextYearString);
	document.getElementById("txtFromDate").value = startDate ;
	document.getElementById("txtToDate").value = endDate ;
}




function getNsdlEntries()
{

	showProgressbar();
	var yearId = document.getElementById("cmbYear").value ;
	var monthId = document.getElementById("cmbMonth").value ;
	var cmbTr = '';//document.getElementById("cmbTr").value ;
//	var cmbDeputation = document.getElementById("cmbDep").value ;
	var cmbDeputation = 1 ;
	 
	if(yearId== -1)
	{
		alert('Please select financial year to confirm request.');
		hideProgressbar();
		return;
	}
	/* if(cmbTr== -1)
	{
		alert('Please select Treasury/Subtreasury to confirm request.');
		hideProgressbar();
		return;
	}
	else */ if(monthId == "")
	{
		alert('Please select month to confirm request.');
		hideProgressbar();
		return;
	}
	else if(cmbDeputation == -1)
	{
		alert('Please select if deputation employees to confirm request.');
		hideProgressbar();
		return;
	}
	
	else if(yearId==2015 && (monthId==1 || monthId==2 || monthId==3)){
		alert('NSDL file can not be generated for selected Month and Year.');
			hideProgressbar();
			return;
		}
	
	else
	{
		
		self.location.href='ifms.htm?actionFlag=loadNPSNSDLForm&yearId='+yearId+'&monthId='+monthId+'&cmbTr='+cmbTr+'&ifDeputation='+cmbDeputation;;
	}
	
}

function getDDOWiseReports(ddoCodeValue)
{
	 
	var ddoCode = ddoCodeValue ;
	var year = document.getElementById("cmbYear").options[document.getElementById("cmbYear").selectedIndex].text;
	var month = document.getElementById("cmbMonth").options[document.getElementById("cmbMonth").selectedIndex].value;
	var cmbTr = '';//document.getElementById("cmbTr").value ;
//	var cmbDeputation = document.getElementById("cmbDep").value ;
	var cmbDeputation = 2 ;
	
/* 	url = "ifms.htm?actionFlag=reportService&reportCode=80000956&ddoCode="+ddoCode
		+"&year="+year+"&month="+month+"&ifDeputation='+cmbDeputation;
	 */
	 url ="ifms.htm?actionFlag=reportService&reportCode=80000956&action=generateReport&ddoCode="+ddoCode 
				 + "&year=" + year + "&month=" + month;
	 
	 showProgressbar();
	 self.location.href = url ;

	}


function createTextFilesDeputaion()
{
	 
	
	showProgressbar();
	var yearId = document.getElementById("cmbYear").value ;
	var monthId = document.getElementById("cmbMonth").value ;
	var subTr = document.getElementById("cmbTr").value ;
//	var cmbDeputation = document.getElementById("cmbDep").value ;
	var cmbDeputation = 2 ;
	
	url = 'ifms.htm?actionFlag=genNsdlTxtFileForDeputation&yearId='+yearId+'&monthId='+monthId+'&subTr='+subTr+'&ifDeputation='+cmbDeputation;;
	showProgressbar();
	self.location.href = url ;

	}

   


</script>
<style type="text/css">
p.mynote {
    text-align: center;
    color: red;
    font-weight: 600;
    margin-bottom: 5px;
} 
</style>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />
<hdiits:form name="frmDCPSMatchedEntries" encType="multipart/form-data"
	validate="true" method="post">
	<input type="hidden" name="csrfToken" value="${csrfToken}"/>

	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}" />
	<c:set var="entries" value="${resValue.EntryList}" />

	<input type="hidden" name='txtDdoCode' id="txtDdoCode"
		style="text-align: left" value="${resValue.DDOCODE}" />

	<br />
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.INPUTDETAILSFORNPS" bundle="${dcpsLables}"></fmt:message></b></legend>
	<table id="table1" align="center">

		<tr>
			<td width="4%" >Pay Year</td>

			<td width="20%"><select name="cmbYear" id="cmbYear"
				style="width: 50%;" onChange="">
				<option value="-1">---Select----</option>
				<c:forEach var="year" items="${resValue.YEARS}">
					<c:choose>
						<c:when test="${resValue.selectedYear == year.id}">
							<option value="${year.id}" selected="selected"><c:out
								value="${year.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${year.id}"><c:out value="${year.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> <label class="mandatoryindicator">*</label></td>

			<td width="4%" ><fmt:message
				key="CMN.Month" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%"><select name="cmbMonth" id="cmbMonth"
				style="width: 50%;">
				<option value="-1">----Select----</option>
				<c:forEach var="month" items="${resValue.MONTHS}">
					<c:choose>
						<c:when test="${resValue.selectedMonth == month.id}">
							<option value="${month.id}" selected="selected"><c:out
								value="${month.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${month.id}"><c:out value="${month.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> <label class="mandatoryindicator">*</label></td>
	<!-- 		
<td width="4%" >Treasury </td>
			<td width="20%"><select name="cmbTr" id="cmbTr"
				style="width: 50%;">
				<c:forEach var="subTr" items="${resValue.subTr}">
							<option value="${subTr.id}" selected="selected"><c:out
								value="${subTr.desc}"></c:out></option>
				</c:forEach>
			</select> <label class="mandatoryindicator">*</label></td> -->

		</tr>
	</table>
	<center><hdiits:button type="button" captionid="BTN.SUBMIT"
		bundle="${DCPSLables}" id="submit" name="submit"
		onclick="getNsdlEntries();"></hdiits:button></center>
		
		<p class="mynote">Note : "PRAN no should created &amp; mapped with NSDL. If PRAN no is not created or not mapped with NSDL then NPS regular contribution will not send to the NSDL"</p>
	</fieldset>
	<br />


	<br />

	
	<c:if test="${empList != null}">

		<div align="center">
		<div class="scrollablediv" style="width: 70%; overflow: auto;">
		<display:table list="${empList}" id="vo"
			style="width:100%;align:center;" requestURIcontext="false"
			requestURI="" export="false">

			<display:setProperty name="paging.banner.placement" value="bottom" />
	
	
			<display:column style="text-align:center" titleKey="CMN.SEVARTHID"
				headerClass="datatableheader" class="oddcentre"
				value="${vo.getEmpSevaarthId()}">
			</display:column>
			
			  <display:column style="text-align:center" titleKey="CMN.NAMEOFEMPLOYEE"
				headerClass="datatableheader" class="oddcentre"
				value="${vo.getEmpEmployeeName()}">
			</display:column>
			
			<display:column style="text-align:center" titleKey="PRAN.PRANNO"
				headerClass="datatableheader" class="oddcentre"
				value="${vo.getEmpPRANNO()}">
			</display:column>
			
			<display:column style="text-align:center" titleKey="CMN.SUPERANNUATIONDATE"
				headerClass="datatableheader" class="oddcentre"
				value="${vo.getEmpServiceEndDate()}">
			</display:column>  

		</display:table>
		
		
		<br />
		<div align="left">
		<table style="width:100%;" align="right">
			<tr>
				<td style="font-size: large; color: #FF0000;">Note :-> Above Employee List Found for Retired Employee.</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td style="font-size: large;"><u>Do Below Step By Step Procedure : </u></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td style="font-size: large;">1. Deactivate PRAN No for Retired Employee.(PATH : Worklist  > NPS  > Data Updation Utility  > Pran Activation and Deactivation ) </td>
			</tr>
			<tr>
				<td style="font-size: large;">2. Then Re-Generate File once again. </td>
			</tr>
		</table>
		
		</div>
		</div>
		
		</div>
	</c:if>
	
	
	
	<c:if test="${NpsList != null}">

		<c:set var="counter" value="1"></c:set>
		<div align="center">
		<div class="scrollablediv" style="width: 70%; overflow: auto;">
		<display:table list="${NpsList}" id="vo"
			style="width:100%;align:center;" requestURIcontext="false"
			requestURI="" export="false">

			<display:setProperty name="paging.banner.placement" value="bottom" />


		<display:column style="text-align: center;" titleKey="CMN.SRNO"
				headerClass="datatableheader" class="oddcentre">
			
	
				<c:choose>

					<c:when test="${vo[0] == 'A'}">
						<c:out value=""></c:out>
					</c:when>

					<c:otherwise>
						<c:out value="${vo_rowNum}" />
					</c:otherwise>
				</c:choose>

			</display:column>
			<%-- <display:column style="text-align: center;" titleKey="CMN.CHECKBOX" headerClass="datatableheader" class="oddcentre">
				<input type="checkbox" id="ddo_select${vo_rowNum}" name="ddo_select${vo_rowNum}" value="${vo[0]}"/>
			</display:column>
 --%>
			<display:column style="text-align:center" titleKey="CMN.DDOCODE"
				headerClass="datatableheader" class="oddcentre">

				<c:choose>

					<c:when test="${vo[0] == 'A'}">
						<c:out value="Total"></c:out>
					</c:when>
					<c:otherwise>

						<a href=# onclick="getDDOWiseReports('${vo[0]}');"><c:out
							value="${vo[0]}" /></a>
					</c:otherwise>
				</c:choose>


			</display:column>
			
					
					
					<c:choose>
					<c:when test="${deputationFlag ==  '1'}">
					<display:column style="text-align:center" titleKey="CMN.EMP_COUNT_WITH_PRAN"
				headerClass="datatableheader" class="oddcentre" value="${vo[2]}">
			</display:column>
					</c:when>
					<c:otherwise>
				<display:column style="text-align:center" titleKey="CMN.EMP_COUNT_WITH_PRAN"
				headerClass="datatableheader" class="oddcentre" value="${vo[2]}">
			</display:column>
					</c:otherwise>
					</c:choose>
					<c:choose>

					<c:when test="${deputationFlag ==  '1'}">
					<display:column style="text-align:center" titleKey="CMN.EMP_COUNT_NOT_PRAN"
					headerClass="datatableheader" class="oddcentre" value="${vo[1]}">
				</display:column>
					</c:when>
					<c:otherwise>
					<display:column style="text-align:center" titleKey="CMN.EMP_COUNT_NOT_PRAN"
					headerClass="datatableheader" class="oddcentre" value="${vo[2]}">
					</display:column>
					</c:otherwise>
					</c:choose>
						<c:choose>
						<c:when test="${deputationFlag ==  '1'}">
			<display:column style="text-align:center" titleKey="CMN.EMPCONTRI"
				headerClass="datatableheader" class="oddcentre" value="${vo[3]}">
			</display:column>
					</c:when>
					<c:otherwise>
				
			<display:column style="text-align:center" titleKey="CMN.EMPCONTRI"
				headerClass="datatableheader" class="oddcentre" value="${vo[4]}">
			</display:column>
					</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${deputationFlag ==  '1'}">
					
			<display:column style="text-align:center" titleKey="CMN.EMPLRCONTRI"
				headerClass="datatableheader" class="oddcentre" value="${vo[4]}">
			</display:column>
					</c:when>
					<c:otherwise>
				
			<display:column style="text-align:center" titleKey="CMN.EMPLRCONTRI"
				headerClass="datatableheader" class="oddcentre" value="${vo[5]}">
			</display:column>
					</c:otherwise>
					</c:choose>
			
			
		<c:choose>
						<c:when test="${deputationFlag ==  '1'}">
			<display:column style="text-align:center" titleKey="CMN.TOTALAMT"
				headerClass="datatableheader" class="oddcentre"
				value="${vo[4]+vo[3]}">
			</display:column>
					</c:when>
					<c:otherwise>
			<display:column style="text-align:center" titleKey="CMN.TOTALAMT"
				headerClass="datatableheader" class="oddcentre"
				value="${vo[4]+vo[5]}">
			</display:column>
					</c:otherwise>
					</c:choose>
			



			




			<c:set var="counter" value="${counter+1}"></c:set>
		</display:table>
		
		
		<br />
		
		</div>

		</div>
<table align="center">
			<tr>
				<c:if test="${deputationFlag ==  '2'}">
					<td id="tdNonDep"><input type="button" id="createtxtButn" style="width: 100%"
					onclick="createTextFiles();" class="buttontag"
					value="Generate Deputation NSDL File" /></td>
				</c:if>
				<c:if test="${deputationFlag ==  '1'}">
				<td  id="tdDep"">
				<input type="button" id="createtxtButn" style="width: 100%"
					onclick="createTextFiles();" class="buttontag"
					value="Generate NSDL File" />
				</td>
				</c:if>

			</tr>
		</table>


	</c:if>

</hdiits:form>

<script >

function createTextFiles()
{
	 
	
	//showProgressbar();
	var yearId = document.getElementById("cmbYear").value ;
	var monthId = document.getElementById("cmbMonth").value ;
	var subTr = '';//document.getElementById("cmbTr").value ;
//	var cmbDeputation = document.getElementById("cmbDep").value ;
	var counter=${counter};
		var cmbDeputation = 2 ;
		var ddoCode="";
		var flag=0;
		/* for(i=1;i<=(counter-1);i++){ //checked 
			console.log("counter i ddo_select"+i);
			if(document.getElementById("ddo_select"+i).checked == true){
				ddoCode=ddoCode+document.getElementById("ddo_select"+i).value+"~" ;	
				flag++;
			} 
			if(flag>5){
				break;
			}
		}
		if(flag>5){
			alert("Only Five ddo Code select is allowed");
			return false;
		} */
		
		//ddoCode=ddoCode.substr(0,ddoCode.length - 1);
		//console.log("trim  ddo_CODE=>"+ddoCode);
		//alert(ddoCode);
	 	
	url = 'ifms.htm?actionFlag=genNsdlTxtFile&yearId='+yearId+'&monthId='+monthId+'&subTr='+subTr+'&ifDeputation='+cmbDeputation;
	//alert("hi"+url);
	showProgressbar();
	 self.location.href = url ;

	}

</script>