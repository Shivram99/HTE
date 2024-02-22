<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />


<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_DCPS.jpg";
</script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="lstAisType" value="${resValue.lstAisType}" />
<c:set var="lstYear" value="${resValue.lstYear}" />
<c:set var="lstEmp" value="${resValue.lstAlIndiaSerEmp}"></c:set>
<c:set var="payYear" value="${resValue.yearid}"></c:set>
<c:set var="payMonth" value="${resValue.payMonth}"></c:set>
<c:set var="aisType" value="${resValue.aisType}"></c:set>
<c:set var="status" value="${resValue.status}"></c:set>
<c:set var="yearDesc1" value="${resValue.yearDesc1}"></c:set>
<c:set var="Monthnm" value="${resValue.Monthnm}"></c:set>
<c:set var="Yearcode" value="${resValue.Yearcode}"></c:set>
<c:set var="totalRecordsMstContri"
	value="${resValue.totalRecordsMstContri}"></c:set>
<c:set var="flagg" value="${resValue.flagg}"></c:set>
<c:set var="count" value="1"></c:set>
<c:set var="check" value="${resValue.check}"></c:set>
<c:set var="aisTypeSelected" value="${resValue.aisTypeSelected}"></c:set>
<c:set var="finTypeSelected" value="${resValue.finTypeSelected}"></c:set>
<c:set var="lstbillNo" value="${resValue.lstbillNo}"></c:set>
<c:set var="billno" value="${resValue.billno}"></c:set>
<c:set var="treasuryno" value="${resValue.treasuryno}"></c:set>
<c:set var="treasury" value="${resValue.treasury}"></c:set>

<script type="text/javascript">
function getListOfEmp(){
	
	if(validate())
	{
		showProgressbar();

var treasList=document.getElementById("treasuryList").value.trim();

if (treasList!=null)   	 
  	{  
		   document.frmNSDL.action = "ifms.htm?actionFlag=getLegactFileGeneration&treasno="+treasList;
		   document.frmNSDL.submit();
  	}
	
	}
}


</script>




<script type="text/javascript">

 function  validate()
 {
	var treasList=document.getElementById("treasuryList").value.trim();	
		 if (treasList == -1)
		 {             alert('Please Select Validate Period');
		 return false;	
		  }
		return true;  
 }

    function createTxTfile(flag,flagFile )
    {
    	
    	document.getElementById("createtxtButn").disabled=true;
    	
	  	var treasList=document.getElementById("treasuryList").value.trim();
		   	 if ( treasList!=null )   	 
		   	{   
				  var url = "";
					   var uri = "ifms.htm?actionFlag=createNewFilesForNSDL&flag="+flag+"&flagFile="+flagFile+"&treasno="+treasList;;
					 
					   document.getElementById("createtxtButn").disabled = true;
						document.frmNSDL.action=uri;
						document.frmNSDL.submit();
						//location.reload(true);
			 }

   		return true;
    
    }



    
	 function getDataStateChangedForCheckpranNO(myAjax)
   	 {	
	   	 
   	 	var XMLDoc = myAjax.responseXML.documentElement;
   	 	var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
   		var msg = namesEntries[0].childNodes[0].text	
   		if(msg =='Success')   	 		
   	 			alert('File has been Created on D:\final.txt');
   		    //   self.location.href = "ifms.htm?actionFlag=ContributionList";
   	 			
   	 		
   	 }
		
	

	
 
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Legacy data contribution file generation</title>
</head>
<style>
input[type="button" i]:disabled {
    pointer-events: none;
    opacity: 0.6;
}
</style>
<body>

<hdiits:form name="frmNSDL" action="" id="frmEmpStatistics"
	encType="multipart/form-data" validate="true" method="post">

	<input type="hidden" id="aisStatus" value="${status}">
	<input type="hidden" id="Monthnm" value="${Monthnm}">
	<input type="hidden" id="Yearcode" value="${Yearcode}">
	<input type="hidden" id="yearDesc" value="${yearDesc1}">
	<div align='center'>


	<table border="1" bordercolor="black" align="center" id="searchTable">
		<tr>

			<td
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
			Validate Period:</td>

			<td><select id="treasuryList">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="treaList" items="${resValue.treasury}">


					<c:choose>
						<c:when test="${treaList.id == treasuryno}">
							<option value="${treaList.id}" selected="selected"><c:out
								value="${treaList.desc}"></c:out></option>
						</c:when>


						<c:otherwise>
							<option value="${treaList.id}"><c:out
								value="${treaList.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> <label class="mandatoryindicator">*</label></td>



		</tr>
		<tr>
			<td colspan="4" align="center">
			<center><input type="button" onclick="getListOfEmp();"
				class="buttontag" value="View Records" /></center>
			</td>
		</tr>
	</table>



	</div>
</hdiits:form>
<c:if test="${check==true}">
	<c:choose>
		<c:when test="${lstEmp!= null  && flagg == false}">

			<fieldset id="datalistframe" style="margin: 10px;"><legend>All
			Employee Details</legend> <display:table list="${lstEmp}" id="ais"
				style="width:100%;align:center;" pagesize="20"
				requestURIcontext="false" requestURI="" export="false">


				<display:column headerClass="datatableheader"
					style="text-align:center;width:150px" class="oddcentre"
					title="Sr no.">
					<label id="srno"> <c:choose>
						<c:when test="${ais[0] eq 'Total'}">
			Total			
			</c:when>
						<c:otherwise>
			${ais_rowNum}
			</c:otherwise>
					</c:choose> </label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:250px" class="oddcentre"
					title="DTA REG No.">
					<label id="DTA REG No."> <c:choose>
						<c:when test="${ais[0] eq 'Total'}">
						</c:when>
						<c:otherwise>
					${ais[8]}
					</c:otherwise>
					</c:choose> </label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:300px" class="oddcentre"
					title="DDO REG No.">
					<label id="DTO REG No."> <c:choose>
						<c:when test="${ais[0] eq 'Total'}">
						</c:when>
						<c:otherwise>
			${ais[9]}
			</c:otherwise>
					</c:choose> </label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:500px" class="oddcentre"
					title="Employee Name">
					<label id="Emplyee Name"> <c:choose>
						<c:when test="${ais[0] eq 'Total'}">
						</c:when>
						<c:otherwise>
			${ais[0]}
			</c:otherwise>
					</c:choose> </label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:200px" class="oddcentre"
					title="DCPS ID">
					<label id="DCPS ID">${ais[1]}</label>
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;width:150px" class="oddcentre"
					title="PRAN NO">
					<label id="Pran No">${ais[2]}</label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:500px" class="oddcentre"
					title="Employee Contribution">
					<label id="Employee Contribution">${ais[3]}</label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:500px" class="oddcentre"
					title="Employer Contribution">
					<label id="Employer Contribution">${ais[4]}</label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:500px" class="oddcentre"
					title="Employee Interest">
					<label id="Employee Interest">${ais[5]}</label>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;width:500px" class="oddcentre"
					title="Employer Interest">
					<label id="Employer Interest">${ais[6]}</label>
				</display:column>
			</display:table></fieldset>

		</c:when>
		<c:otherwise>
			<center><c:out value="No Records found "></c:out></center>

		</c:otherwise>
	</c:choose>
</c:if>




<c:if test="${lstEmp!= null && lstEmp[0] !=null && flagg == false}">

	<table align="center">
		<tr>

			<td><input type="button" id="createtxtButn"
				onclick="createTxTfile('txt','1');" class="buttontag"
				value="Text File" /></td>


		</tr>
	</table>

</c:if>
<script>
	var status = '${status}';

	var monthnm = document.getElementById("Monthnm").value;
	var Yearcode = document.getElementById("Yearcode").value;

	if (status.charAt(0) == '1') {

		alert('Report has been already generated for ' + monthnm
				+ ' month of year ' + Yearcode);

		//	document.getElementById("createtxtButn").disabled=true;
		//	document.getElementById("createfpuButn").disabled=true;
	}
	if (status.charAt(1) == '1') {
		alert('Report has been already generated for ' + monthnm
				+ ' month of year ' + Yearcode);

		//	document.getElementById("createtxtButn").disabled=true;
		//	document.getElementById("createfpuButn").disabled=true;
	}

	if (status.charAt(2) == '1') {
		alert('Report has been already generated for ' + monthnm
				+ ' month of year ' + Yearcode);

		//	document.getElementById("createtxtButn").disabled=true;
		//	document.getElementById("createfpuButn").disabled=true;
	}
</script>
</body>

</html>