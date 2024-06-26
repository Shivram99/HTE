<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="dcpsOrGpf" value="${resValue.dcpsOrGpf}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjEmpPayrollData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="parentDeptByDefault" value="${resValue.listParentDept[0]}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="isDcpsGrAvl" value="${resValue.isDcpsGrAvl}"></c:set>

<c:if test="${resValue.EditForm != null && resValue.EditForm =='N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page" value="style='display: none;' "></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>

<c:if test="${resValue.MumbaiOrNagpurAGMst == 'No'}">
	<c:set var="varDisableForNonAGMst" value="disabled='disabled'"></c:set>
</c:if>

<c:if test="${resValue.MumbaiOrNagpurAG == 'No'}">
	<c:set var="varDisableForNonAG" value="disabled='disabled'"></c:set>
</c:if>




<!--Added by Mayuresh-->

<c:if test="${EMPVO != null && EMPVO.acDcpsMaintainedBy == '700180'}">
	<c:set var="varAccountMain" scope="page" value="style='display:inline'"></c:set>
</c:if>



<script type="text/javascript">


function checkAcMntndBy()
{
	var acMaintainedBy = document.getElementById("dcpsAcntMntndBy").value.trim();
	
	if(acMaintainedBy == '10001198187')
	{
		//alert('HIIIIIIIIIII');
		document.getElementById("tdForAcno").style.display = 'inline' ;
		document.getElementById("txtAcNoForNonSRKAEmp").style.display = 'inline' ;
		document.getElementById("tdForAcNoTxtBox").style.display = 'inline' ;

		document.getElementById("tdForOthers").style.display = 'inline' ;
		document.getElementById("tdForOthersTxtBox").style.display = 'inline' ;
		document.getElementById("txtOthersNonSRKAEmp").style.display = 'inline' ;
	}
	else
	{
		//alert('BYYYYYYYYYY');
		document.getElementById("tdForAcno").style.display = 'none' ;
		document.getElementById("txtAcNoForNonSRKAEmp").style.display = 'none' ;
		document.getElementById("tdForAcNoTxtBox").style.display = 'none' ;	

		document.getElementById("tdForOthers").style.display = 'none' ;
		document.getElementById("tdForOthersTxtBox").style.display = 'none' ;
		document.getElementById("txtOthersNonSRKAEmp").style.display = 'none' ;	
	}
}




function checkGroupDtls(){
	
	
	//alert("In Main *********** ");
	var Group = document.getElementById('txtGroup').value;
	var AccMaintainedBy= document.getElementById('cmbAcMaintainedBy');
	var index = AccMaintainedBy.selectedIndex;
	var selected_text = AccMaintainedBy.options[index].text.trim();
	var selected_value = AccMaintainedBy.options[index].value.trim();
	
	// AG Mumbai	    - 700092
	// AG Nagpur	    - 700093
	// Department	    - 700094
	// Not applicable	- 700095
	// Others			- 700096
	
	if(AccMaintainedBy.value != -1 &&  Group != ''){
		if(Group == 'D' && selected_text != 'Department'){
			alert('Group D accounts maintained by Department');
		}
		else if((Group == 'A' || Group == 'B' || Group == 'C') && selected_value != 700092 && selected_value != 700093 && selected_value != 700096)
		{
			//alert('Group A,B,C accounts maintained by A.G.');
		}
	}
	
	if(selected_value == 700094)
	{
		
		//alert("In 94 *********** ");
		//document.getElementById("cmbPFSeries").value = -1;
		
		document.getElementById("cmbPFSeries").disabled = true;
		document.getElementById('cmbPFSeries').style.display ='inline';
		document.getElementById('txtPFSeries').style.display ='none';
		document.getElementById("txtPFSeriesDesc").readOnly = false;
		document.getElementById("txtPFSeriesDesc").value = '';
		document.getElementById("txtPfAccountNo").readOnly = false;
		
		if(document.getElementById("labelForGPFSeriesDesc") != null)
			{
				document.getElementById("labelForGPFSeriesDesc").style.display = '' ;
			}
		if(document.getElementById("labelForGPFAcNo") != null)
		{
			document.getElementById("labelForGPFAcNo").style.display = 'inline' ;
		}
	}
	
	
	else if(selected_value == 700095){
		
		//alert("In 95 *********** ");
		document.getElementById('cmbPFSeries').style.display ='none';
		document.getElementById('txtPFSeries').style.display ='';
		document.getElementById("txtPFSeries").value = 'Not Applicable';
		document.getElementById("txtPFSeries").readOnly = true;
		document.getElementById("txtPFSeriesDesc").readOnly = true;
		document.getElementById("txtPFSeriesDesc").value = 'Not Applicable';
		//document.getElementById("txtPfAccountNo").readOnly = true;
		document.getElementById("txtPfAccountNo").readOnly = false;
		
		if(document.getElementById("labelForGPFAcNo") != null)
		{
			document.getElementById("labelForGPFAcNo").style.display = 'none' ;
		}
		
		
		if(document.getElementById("labelForGPFSeriesDesc") != null)
		{
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none' ;
		}
	}
	else if(selected_value == 700096){
		
		//alert("In 96 *********** ");
		document.getElementById('cmbPFSeries').style.display ='none';
		document.getElementById('txtPFSeries').style.display ='';
		document.getElementById("txtPFSeries").value = 'Others';
		document.getElementById("txtPFSeries").readOnly = false;
		document.getElementById("txtPFSeriesDesc").readOnly = false;
		document.getElementById("txtPFSeriesDesc").value = 'Others';
		//document.getElementById("txtPfAccountNo").readOnly = true;
		document.getElementById("txtPfAccountNo").readOnly = false;
		
		
		if(document.getElementById("labelForGPFAcNo") != null)
		{
			document.getElementById("labelForGPFAcNo").style.display = 'none' ;
		}
		
		
		if(document.getElementById("labelForGPFSeriesDesc") != null)
		{
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none' ;
		}
	}
	else if(selected_value == 700092 || selected_value == 700093)
	{
		//alert("In 92 & 93 *********** ");
		document.getElementById("cmbPFSeries").disabled = false;
		document.getElementById('cmbPFSeries').style.display ='';
		document.getElementById('txtPFSeries').style.display ='none';
		document.getElementById("txtPFSeriesDesc").readOnly = true;
		document.getElementById("txtPFSeriesDesc").value = '';
		document.getElementById("txtPfAccountNo").readOnly = false;
		
		if(document.getElementById("labelForGPFSeriesDesc") != null)
		{
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none' ;
		}
		if(document.getElementById("labelForGPFAcNo") != null)
		{
			document.getElementById("labelForGPFAcNo").style.display = 'inline' ;
		}
		
		var uri="ifms.htm?actionFlag=getLookupValuesForParentAG";
		var url="typeOfAG="+selected_value;
				
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
			        	getDataStateChangedForCheckGroup(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	else{
		
		//alert("In else *********** ");
		var optnTemp = document.createElement("OPTION");
		optnTemp.value = -1;s
		optnTemp.text = "-- Select --";
		document.getElementById("cmbPFSeries").options.length = 0; 
		document.getElementById("cmbPFSeries").options.add(optnTemp);
		document.getElementById("cmbPFSeries").value = -1;
		
		document.getElementById("cmbPFSeries").disabled = false;
		document.getElementById('cmbPFSeries').style.display ='';
		document.getElementById('txtPFSeries').style.display ='none';
		document.getElementById("txtPFSeriesDesc").readOnly = true;
		document.getElementById("txtPFSeriesDesc").value = '';
		document.getElementById("txtPfAccountNo").readOnly = false;
		if(document.getElementById("labelForGPFSeriesDesc") != null)
		{
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none' ;
		}
	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function checkAccountno(){
	//alert("hiii");
	var accNo =  document.getElementById("txtbankAccountNo").value;
	if(accNo<=0){
		alert("Please Enter Valid Account Number");
		document.getElementById("txtbankAccountNo").value = "";
		return false;
	}
	return true;
}


function chkDCPSIDalreadyExists()
{
	///alert('Inside chkDCPSIDalreadyExists');
	  var dcpsId = document.getElementById("txtAcNoForNonSRKAEmp").value.trim();

	  
		var uri = 'ifms.htm?actionFlag=chkDCPSIDalreadyExists';
		var url = 'dcpsId='+dcpsId;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
			        	getResponseDCPSID(myAjax,dcpsId);
						
					},
			        onFailure: function()
			        			{ 
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
}


function getResponseDCPSID(myAjax,dcpsId){

	//alert("hiii checdksdsd");
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var empName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
///	alert('checkFlag'+empName);
	//alert("checkFlag"+checkFlag);
	if(checkFlag=="Y")
	{
		alert('Entered DCPS ID:'+dcpsId+' is already present for the employee :'+empName+' in system. Please enter correct DCPS ID number.');
		clearAllData();
		return false;
	}
	return true;
}

function clearAllData()
{
	 document.getElementById("txtAcNoForNonSRKAEmp").value = "";
   
    
}


</script> 




<fieldset class="tabstyle"><Legend>Bank Details</Legend>
	<table width="100%" align="center" cellpadding="4" cellspacing="4">

		<tr>
			<td width="15%" align="left">
				<fmt:message key="CMN.BANKNAME" bundle="${dcpsLables}"></fmt:message>
			</td>
			
			<td width="35%" align="left">
			<select name="cmbBankName" id="cmbBankName" style="width: 360px" onChange=""${varDisabled} >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="bankName" items="${resValue.BANKNAMES}">
					<c:choose>
						<c:when test="${EMPVO.bankName == bankName.id}">
							<option value="${bankName.id}" selected="selected"><c:out value="${bankName.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${bankName.id}"><c:out value="${bankName.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<label class="mandatoryindicator" ${varLabelDisabled}>*</label></td>
		
			<td width="15%" align="left"> <fmt:message key="CMN.BRANCHNAME" bundle="${dcpsLables}"></fmt:message></td>
			
			<td width="35%" align="left">
			<select name="cmbBranchName" id="cmbBranchName" style="width: 360px" ${varDisabled} onchange="popUpIFSCCode();"  >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:if test="${EMPVO!=null}">
					<c:forEach var="branchName" items="${resValue.BRANCHNAMES}">
						<c:choose>
							<c:when test="${EMPVO.branchName == branchName.id}">
								<option value="${branchName.id}" selected="selected"><c:out value="${branchName.desc}"></c:out></option>
							</c:when>
							<c:otherwise>
								<option value="${branchName.id}"><c:out value="${branchName.desc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</select>
			<label class="mandatoryindicator" ${varLabelDisabled}>*</label></td>
		</tr>

		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.BANKACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left">
			<input type="text"
				id="txtbankAccountNo" size="48" onkeypress="bankACFormat(this);return isNumberKey(event);" onblur="checkAccountno();" name="txtbankAccountNo"
				value="${EMPVO.bankAccountNo}" ${varDisabled} maxlength="20" onblur="" />
				<label class="mandatoryindicator" ${varLabelDisabled} >*</label></td>
				
				
				
			<td width="15%" align="left"><fmt:message key="CMN.IFSCODE"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text" id="txtIFSCCode"
				style="text-transform: uppercase" size="48" name="txtIFSCCode"
				value="${EMPVO.IFSCCode}" ${varDisabled} readonly="readonly"/>
			</td>
		</tr>
	</table>
	</fieldset>
	<br/><br/>
	<table width="100%" border="0">
		<tr>
			<td width="15%" align="left">
				<fmt:message key="CMN.DCPS?" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="35%" align="left" style="padding-left: 5px" >
				<c:choose>
					<c:when test="${EMPVO != null}">
						<c:choose>
							<c:when test="${EMPVO.dcpsOrGpf == 78}">
								<c:choose>
									<c:when test="${isDcpsGrAvl=='-1'}">
										<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();" ${varDisabled} disabled="disabled"/> 
										<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
										<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();" ${varDisabled}  checked="checked"  />
										<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
										<c:set var="varDCPSDisabled" value="disabled='disabled'"/>
										<c:set var="varDcpsAsterisks" value="style='display: none'"/>
										<c:set var="varGpfAsterisks" value="style='display: inline'"/>
										
										<c:choose>
											<c:when test="${EMPPAYROLLVO.acMaintainedBy == 700092 || EMPPAYROLLVO.acMaintainedBy == 700093 || EMPPAYROLLVO.acMaintainedBy == 700094 }">
												<c:set var="varGpfAsterisksForPFAcNo" value="style='display: inline'"/>
											</c:when>
											<c:otherwise>
												<c:set var="varGpfAsterisksForPFAcNo" value="style='display: none'"/>
											</c:otherwise>
										</c:choose>
									</c:when>
									
									<c:otherwise>
										<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();" ${varDisabled} /> 
										<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
										<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();" ${varDisabled}  checked="checked"  />
										<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
										<c:set var="varDCPSDisabled" value="disabled='disabled'"/>
										<c:set var="varDcpsAsterisks" value="style='display: none'"/>
										<c:set var="varGpfAsterisks" value="style='display: inline'"/>
										
										<c:choose>
											<c:when test="${EMPPAYROLLVO.acMaintainedBy == 700092 || EMPPAYROLLVO.acMaintainedBy == 700093 || EMPPAYROLLVO.acMaintainedBy == 700094 }">
												<c:set var="varGpfAsterisksForPFAcNo" value="style='display: inline'"/>
											</c:when>
											<c:otherwise>
												<c:set var="varGpfAsterisksForPFAcNo" value="style='display: none'"/>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>
								
							<c:otherwise>
							<c:choose>
							<c:when test="${isDcpsGrAvl=='-1'}">
								<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();" ${varDisabled} disabled="disabled"/> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();" ${varDisabled}  checked="checked"  />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varDCPSDisabled" value="disabled='disabled'"/>
								<c:set var="varDcpsAsterisks" value="style='display: none'"/>
								<c:set var="varGpfAsterisks" value="style='display: inline'"/>
							</c:when>
							<c:otherwise>
								<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();" ${varDisabled} checked="checked" /> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();" ${varDisabled} />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varPFDisabled" scope="page" value="disabled='disabled' readOnly='readOnly'"></c:set>
								<c:set var="varDcpsAsterisks" value="style='display: inline'"/>
								<c:set var="varGpfAsterisks" value="style='display: none'"/>
							</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${isDcpsGrAvl!='-1'}">
							<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();" ${varDisabled} checked="checked" /> 
							<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
							<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();" ${varDisabled} />
							<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
							<c:set var="varPFDisabled" scope="page" value="disabled='disabled' readOnly='readOnly'"></c:set>
							<c:set var="varGpfAsterisks" value="style='display: none'"/>
							<c:set var="varGpfAsterisksForPFAcNo" value="style='display: none'"/>
							</c:when>
							<c:otherwise>							
								
								<input type="radio"	id="radioDCPS" name="radioDCPS" value="Y" onclick="displayPFEntryDetails();hideAsterisks();" ${varDisabled} disabled="disabled"/> 
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioGPF" name="radioDCPS" value="N" onclick="displayPFEntryDetails();displayAsterisks();" ${varDisabled}  checked="checked"  />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<c:set var="varDCPSDisabled" value="disabled='disabled'"/>
								<c:set var="varDcpsAsterisks" value="style='display: none'"/>
								<c:set var="varGpfAsterisks" value="style='display: inline'"/>
							
							</c:otherwise>
							</c:choose>
					</c:otherwise>
				</c:choose>
			</td>
			
		
			<td width="15%" align="left"><fmt:message
				key="CMN.DCPSACNTMNTBY" bundle="${dcpsLables}"></fmt:message></td>
			
			
			
			<td width="35%" align="left">
			<select name="dcpsAcntMntndBy" id="dcpsAcntMntndBy" style="360px; display: inline;" onchange="checkAcMntndBy();" ${varDisabled} ${varDCPSDisabled}>
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT_1" bundle="${dcpsLables}"></fmt:message></option>
				
				<c:forEach var="acntMntndBy" items="${resValue.lLstPFAccntMntdByDCPS}">
					<c:choose>
						<c:when
							test="${EMPVO.acDcpsMaintainedBy==acntMntndBy.lookupId}">
							<option value="${acntMntndBy.lookupId}" selected="selected" ><c:out value="${acntMntndBy.lookupDesc}"></c:out></option>
						</c:when>
						
						<c:otherwise>
							<c:choose>
								<c:when test="${acntMntndBy.lookupId == '700179'||acntMntndBy.lookupId == '700343'||acntMntndBy.lookupId == '10001198172'||acntMntndBy.lookupId == '700344'||acntMntndBy.lookupId == '10001198187'}">
									<option value='<c:out value="${acntMntndBy.lookupId}"/>'>
									<c:out value="${acntMntndBy.lookupDesc}"/>
									</option>
								</c:when>
								<%--
								<c:otherwise>
		     						 <option value="${acntMntndBy.lookupId}" title="${acntMntndBy.lookupDesc}"><c:out
									value="${acntMntndBy.lookupDesc}"></c:out></option>
								</c:otherwise>
								--%>
							</c:choose>	
								
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
			</select><label class="mandatoryindicator" id="labelForDcpsAcMntndBy" ${varLabelDisabled} ${varDcpsAsterisks}>*</label>
			
			</td>
		</tr>
		
		<c:choose>
			<c:when test="${EMPVO == null || EMPVO.acDcpsMaintainedBy == null}">
				<c:set var="varAcNoDisplay" value="style='display: none'" ></c:set>
			</c:when>
			<c:otherwise>
					<c:choose>
						<c:when test="${EMPVO.acDcpsMaintainedBy != '700174'}">
							<c:set var="varAcNoDisplay" value="" ></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="varAcNoDisplay" value="style='display: none'" ></c:set>
						</c:otherwise>
					</c:choose>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${EMPVO.acDcpsMaintainedBy == '700180'}">
				<c:set var="varAcMntndByOthers" value="" ></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="varAcMntndByOthers" value="style='display: none'" ></c:set>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${EMPVO.acDcpsMaintainedBy == '700344'}">
				<c:set var="varAcMntndByOtherState" value="" ></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="varAcMntndByOtherState" value="style='display: none'" ></c:set>
			</c:otherwise>
		</c:choose>
		
		<tr id="accountMain"  ${varAccountMain}>
		
		<%--
		
		<td width="15%" align="left" ${varAcNoDisplay} id="tdForAcno"><fmt:message
				key="CMN.ACCOUNTNOFORNONSRKAEMP" bundle="${dcpsLables}"></fmt:message>
		</td>
		
		<td width="20%" align="left" ${varAcNoDisplay} id="tdForAcNoTxtBox">
		<input
			type="text" id="txtAcNoForNonSRKAEmp" size="31"
			name="txtAcNoForNonSRKAEmp" value="${EMPVO.acNonSRKAEmp}" ${varDisabled} />
			<label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</td>
		<td width="15%" align="left" ${varAcNoDisplay} id="tdForOthers"><fmt:message
				key="CMN.ACCOUNTNOFOROTHERS" bundle="${dcpsLables}"></fmt:message>
		</td>
		
		<td width="20%" align="left" ${varAcNoDisplay} id="tdForOthersTxtBox">
		<input
			type="text" id="txtOthersNonSRKAEmp" size="31"
			name="txtOthersNonSRKAEmp" value="${EMPVO.acMntndByOthers}" ${varDisabled} />
			<label class="mandatoryindicator"${varLabelDisabled} >*</label>
		</td>
		
		--%>
	



		<c:choose>
			<c:when test="${EMPVO.acDcpsMaintainedBy == '10001198187'}">
				<td width="15%" align="left" ${varAcNoDisplay} id="tdForAcno">
					<fmt:message key="CMN.ACCOUNTNOFORNONSRKAEMP" bundle="${dcpsLables}"></fmt:message>
				</td>
				<td width="35%" align="left" ${varAcNoDisplay} id="tdForAcNoTxtBox">
					<input type="text" id="txtAcNoForNonSRKAEmp" size="48" name="txtAcNoForNonSRKAEmp" value="${EMPVO.acNonSRKAEmp}"  ${varDisabled} onblur="chkDCPSIDalreadyExists();"  />
					<label class="mandatoryindicator"${varLabelDisabled} >*</label>
				</td>
				<td width="15%" align="left" ${varAcNoDisplay} id="tdForOthers">
				<fmt:message key="CMN.ACCOUNTNOFOROTHERS" bundle="${dcpsLables}"></fmt:message>
				</td>
				<td width="35%" align="left" ${varAcNoDisplay} id="tdForOthersTxtBox">
					<input type="text" id="txtOthersNonSRKAEmp" size="48" name="txtOthersNonSRKAEmp" value="${EMPVO.acMntndByOthers}" ${varDisabled} />
					<label class="mandatoryindicator"${varLabelDisabled} >*</label>
				</td>
			</c:when>
			
			<c:otherwise>
				<td width="15%" align="left" ${varAcNoDisplay} id="tdForAcno" style="display: none;">
					<fmt:message key="CMN.ACCOUNTNOFORNONSRKAEMP" bundle="${dcpsLables}"></fmt:message>
				</td>
				<td width="35%" align="left" ${varAcNoDisplay} id="tdForAcNoTxtBox" style="display: none;">
					<input type="text" id="txtAcNoForNonSRKAEmp" size="48" name="txtAcNoForNonSRKAEmp" value="${EMPVO.acNonSRKAEmp}"  ${varDisabled} onblur="chkDCPSIDalreadyExists();"/>
					<label class="mandatoryindicator"${varLabelDisabled} >*</label>
				</td>
				<td width="15%" align="left" ${varAcNoDisplay} id="tdForOthers" style="display: none;">
					<fmt:message key="CMN.ACCOUNTNOFOROTHERS" bundle="${dcpsLables}"></fmt:message>
				</td>
				<td width="35%" align="left" ${varAcNoDisplay} id="tdForOthersTxtBox" style="display: none;">
					<input type="text" id="txtOthersNonSRKAEmp" size="48" name="txtOthersNonSRKAEmp" value="${EMPVO.acMntndByOthers}" ${varDisabled} />
					<label class="mandatoryindicator"${varLabelDisabled} >*</label>
				</td>
			</c:otherwise>
		</c:choose>





	</tr>
		
	</table>
	</br>
	
	
	<fieldset id="PFDetails" class="tabstyle" ><legend><fmt:message
		key="CMN.PFDETAILS" bundle="${dcpsLables}"></fmt:message> </legend>
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.ACMAINTENEDBY" bundle="${dcpsLables}"></fmt:message></td>
					<td width="35%" align="left">
					<select name="cmbAcMaintainedBy"
						id="cmbAcMaintainedBy" style="width: 360px" onChange="checkGroupDtls();" ${varDisabled} ${varPFDisabled}  >
						<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="acMntndByVar" items="${resValue.lLstPFAccntMntdBy}" >
																<c:choose>
																	<c:when test="${EMPPAYROLLVO.acMaintainedBy == acMntndByVar.lookupId}">
																	<option value="${acMntndByVar.lookupId}" selected="selected"><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${acMntndByVar.lookupId}" ><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
						</c:forEach>
					</select>
					<label class="mandatoryindicator" id="labelForGPFAcmntndBy" ${varLabelDisabled} ${varGpfAsterisks}>*</label>
					</td>
					
			</tr>
			
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.PFSERIES" bundle="${dcpsLables}"></fmt:message></td>
					<td width="35%" align="left">
					<select name="cmbPFSeries" style="width: 360px"
									id="cmbPFSeries" onChange="getPFDesc();" ${varDisabled} ${varPFDisabled} ${varDisableForNonAG}> 
									<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
									<c:forEach var="pfSeriesVar" items="${resValue.lLstPFSeries}" >
																			<c:choose>
																				<c:when test="${EMPPAYROLLVO.pfSeries == pfSeriesVar.lookupId}">
																				<option value="${pfSeriesVar.lookupId}" selected="selected"><c:out 
																						value="${pfSeriesVar.lookupDesc}"></c:out></option>
																				</c:when>
																				<c:otherwise>
																				<option value="${pfSeriesVar.lookupId}" ><c:out 
																						value="${pfSeriesVar.lookupDesc}"></c:out></option>
																				</c:otherwise>
																			</c:choose>
									</c:forEach>
					</select> 
					<input type="text" id="txtPFSeries"	size="28" name="txtPFSeries" value="${EMPPAYROLLVO.pfSeries}" style="display: none" />
					<label class="mandatoryindicator" id="labelForGPFSeries" ${varLabelDisabled} ${varGpfAsterisks}>*</label>
					</td>
					
					<c:choose>
						<c:when test="${EMPVO != null}">
							<c:choose>
								<c:when test="${EMPPAYROLLVO.acMaintainedBy == 700094}">
									<c:set var="grayOutSeriesDescForDeptSelected" value="style=''"  ></c:set>
									<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display:inline'"  ></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="grayOutSeriesDescForDeptSelected" value="style='readOnly=readOnly'"></c:set>
									<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display:none'"  ></c:set>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
								<c:set var="grayOutSeriesDescForDeptSelected" value="style='readOnly=readOnly'"></c:set>
								<c:set var="displayLabelOfSeriesDescForDeptSelected" value="style='display:none'"  ></c:set>
						</c:otherwise>
					</c:choose>
					
					<td width="15%" align="left"><fmt:message key="CMN.PFSERIESDESC"
				bundle="${dcpsLables}"></fmt:message></td>
				
					<td width="35%" align="left"><input type="text" id="txtPFSeriesDesc"
				size="48" name="txtPFSeriesDesc" value="${EMPPAYROLLVO.pfSeriesDesc}"  maxlength="100" ${grayOutSeriesDescForDeptSelected} ${varDisabled} onkeypress="alphaFormat(this);"/>
					<label class="mandatoryindicator" id="labelForGPFSeriesDesc" ${varLabelDisabled} ${displayLabelOfSeriesDescForDeptSelected}>*</label>
				</td>
					
			</tr>
			
			<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PFACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text"
				id="txtPfAccountNo" size="48" maxlength="100" name="txtPfAccountNo"
				value="${EMPPAYROLLVO.pfAcNo}" onkeypress="AlphaAnddigitFormat(this);" ${varPFDisabled} ${varDisabled}/>
				<label class="mandatoryindicator"  id="labelForGPFAcNo" ${varLabelDisabled} ${varGpfAsterisksForPFAcNo}>*</label>
				</td>
			</tr>
		</table>
</fieldset>