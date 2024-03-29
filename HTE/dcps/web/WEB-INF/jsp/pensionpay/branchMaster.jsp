<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<script>
function clearBranch()
{
   document.frmBF.txtBranchName.value='';
}
function trim(s) 
{
// Remove leading spaces and carriage returns
//  s = s.replace(/&nbsp;/gi,'');

 while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
  {
    s = s.substring(1,s.length);   
  }

  // Remove trailing spaces and carriage returns

  while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
  {
    s = s.substring(0,s.length-1);
  }
  return s;
}

function chkBranchName(val)
{ 
 if(document.frmBF.cmbBankName.value=='Select')
 {
  alert('Please Enter Bank Name');
  document.frmBF.cmbBankName.focus();
 }
 else
 {
  
  if(!trim(document.frmBF.txtBranchName.value) == '')

 
  var name = document.frmBF.txtBranchName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&branchName='+ document.frmBF.txtBranchName.value + '&bank_id=' +document.frmBF.cmbBankName.value;
		  var actionf="chkBranchName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
         //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_branchName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	
 }
}

function chk_branchName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('branch-name');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
                    {                    
                     alert('Branch Name already exists.');
                     document.frmBF.txtBranchName.value = '';
                     document.frmBF.txtBranchName.focus();
                    }
  }
}

function chkIfscCode()
{ 
 if(document.frmBF.cmbBankName.value=='Select')
 {
  alert('Please Enter Bank Name');
  document.frmBF.cmbBankName.focus();
 }
 else
 {  
  if(!trim(document.frmBF.txtIfscCode.value) == '')
  {
	  var ifscCode = document.frmBF.txtIfscCode.value;
	  var uri='ifms.htm?actionFlag=chkIfscCode';
		var url = '&IFSCcode='+ifscCode;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function (myAjax) {
						chk_IfscCode(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
  }
 }
}

function chk_IfscCode(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var state = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(state == "Y"){
		alert("IFSC code already exist");
		document.getElementById("txtIfscCode").value="";
	}
}

function chkMicrCode()
{
	var MicrCode = document.getElementById("txtMicrCode").value;

	if(MicrCode.length != 0)
	{
		var url='ifms.htm?actionFlag=chkMicrCode';
		var uri = '&MICRcode='+MicrCode;
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:uri,
			        onSuccess: function (myAjax) {
						chk_MicrCode(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function chk_MicrCode(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var branchName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(branchName == "N"){
		var msg = 'This MICR Code does not belong to any branch. Do you want to add new MICR code?';
		if(confirm(msg)){
			
		}else{
			document.getElementById("txtMicrCode").value = "";
			document.getElementById("txtMicrCode").focus();
		}
	}else{	
		var branchAddr = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		var msg1 = 'This MICR Code belongs to '+branchName+' with address :'+branchAddr;
		if(confirm(msg1)){
			
		}else{
			document.getElementById("txtMicrCode").value = "";
			document.getElementById("txtMicrCode").focus();
		}
	}
}
</script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
 <c:set var="bankList" value="${resValue.bankList}" />






<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertBranchMasterData&edit=N" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="BR.INSERTBRANCHINFO" bundle="${commonLables}"/> </b></a></li>
	</ul>
	</div>
	
	

	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >  

   <TABLE  width="80%"   align="center"><br>
 	<TR>  									
			<td  width="2%"></td>
			<TD align="left" width="20%"> 
				<b><fmt:message key="BM.bankName" bundle="${commonLables}"/> </b>
			</TD>			
			<TD width="20%">
			 <hdiits:select name="cmbBankName" sort="true" validation="sel.isrequired" id="bankID" size="1"  mandatory="true" 
			caption="Bank Name" onchange="clearBranch()">
		       <hdiits:option value="Select">-------------------Select-------------------</hdiits:option>
		
		       
		     <c:forEach items ="${resValue.bankList}" var="list">
			<hdiits:option value="${list.bankId}"> ${list.bankName} </hdiits:option>
			</c:forEach>  
			</hdiits:select>		       
	</TD>
			
	<td  width="2%"></td>
	   <TD  align="left" width="20%">
			 <b><fmt:message key="BR.NAME" bundle="${commonLables}"/></b>
	  </TD>
			<TD>		
	 <hdiits:text	name="txtBranchName" validation="txt.isrequired" mandatory="true" caption="Branch Name"  id="branchName" size="30" maxlength="40"
				 onblur="chkBranchName(this)" onkeypress="if(event.keyCode == 13) event.returnValue = false;"/>	 
			</TD>
	</TR>
	<tr></tr><tr></tr>
	
	
	
	
	<TR>			
		<td  width="2%"></td>
		<TD  align="left"  width="20%"> <b><fmt:message key="BR.ADD" bundle="${commonLables}"/></b></TD>
		<TD>
			<hdiits:textarea rows="3" cols="32" name="txtBranchAdd" maxlength="190" caption ="Address"  >
			</hdiits:textarea>
		</TD>
		
		<td  width="2%"></td>
			<TD  align="left" width="20%">
			<b><fmt:message key="PPMT.MICRCODE" bundle="${pensionLabels}"/></b>
			</TD>
			
		<TD  width="20%">
		
		 <hdiits:number	name="txtMicrCode"  caption="MICR Code"  mandatory="true"  validation="txt.isrequired,txt.isnumber" onblur="chkMicrCode();" maxlength="20" size="30" />
	   
	   </TD>
	</tr>
	
	<tr></tr><tr></tr>
		
	
	<TR>
	   <td  width="2%"></td>
		<TD  align="left"  width="20%"> <b><fmt:message key="PPMT.IFSCCODE" bundle="${pensionLabels}"/></b></TD>
		<TD>			
			<hdiits:text	name="txtIfscCode" validation="txt.isrequired" mandatory="true" caption ="IFSC Code" id="txtIfscCode" 
				onblur="chkIfscCode();" size="30" maxlength="40"/>	 
		</TD>	
				
		<td  width="2%"></td>
			<TD  align="left" width="8%">
			<b><fmt:message key="PPMT.CONTACT" bundle="${pensionLabels}"/></b>
			</TD>
			
		<TD  width="20%">
		
		 <hdiits:number	name="txtContactNo"  caption="Contact No"  maxlength="20" />
	   
	   </TD>
	</TR>
	
	<TR>
	   <td  width="2%"></td>
			<TD align="left" width="20%"> 
				<b><fmt:message key="PPMT.TREASURYNAME" bundle="${pensionLabels}"/> </b>
			</TD>
	   <TD width="20%">
			 <hdiits:select name="cmbTreasury" sort="true" id="cmbTreasury" size="1"  mandatory="true" 
			caption="Treasury Name">		       
		       <hdiits:option value="-1">-------------------Select-------------------</hdiits:option>
		       
		     <c:forEach items ="${resValue.treasuryList}" var="list">
			<hdiits:option value="${list.id}"> ${list.desc} </hdiits:option>
			</c:forEach>  
			</hdiits:select>
		</TD>	
	</TR>
	</table> 


    
 <br><br><br>
 

	
 
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
<hdiits:hidden default="getBranchMasterDtls" name="givenurl"/> 

	<table class="tabNavigationBar">
	<tr align="center">
		<td class="tabnavtdcenter" id="tabnavtdcenter"><hdiits:button
			name="formSubmitButton" value="Save" type="button"
			captionid="eis.save" bundle="${Lables}" onclick="validateData();" /> 
		<hdiits:button name="Close" value="Close" type="button" captionid="eis.close"
			bundle="${Lables}" onclick="onBackfn();" /><%-- <hdiits:button
			name="Back" value="Back" type="button" captionid="eis.back"
			bundle="${Lables}" onclick="onBackfn();" />  <hdiits:resetbutton type="button" value="Reset" name="reset" />  --%>
		<hdiits:button name="Reset" type="button" value="Reset"
			onclick="resetForm()" /> <!-- <input type="reset" value="Reset"/>-->
		<script language="javaScript">             
              //if (navDisplay)
              //{
				//document.write('<input type="button" value="Reset" onClick="resetForm()">');
				//document.write('<input type="button" value="Previous" onClick="goToPrevTab()">');
				//document.write('<input type="button" value="Next" onClick="goToNextTab()">');
			 // }

			  /*function resetForm()
			  {
			  	if(confirm("All values will be reseted please confirm !") == true)
			  	{
			  		document.forms[0].reset();
			  	}

			  }*/
			  function onclosefn()
			  {
						document.forms[0].action="ifms.htm?actionFlag=getHomePage";
						document.forms[0].submit();
			  }
			  function onBackfn()
			  {						
						if(document.forms[0].givenurl!=null)
						{
						var url="ifms.htm?actionFlag=getBranchMasterDtls";
						document.forms[0].action=url;
						}
						else
						{
						document.forms[0].action="ifms.htm?actionFlag=getHomePage";
						}
						document.forms[0].submit();
			  }
			  function resetForm()
			  {
				  document.forms[0].cmbBankName.value = "Select";
				  document.forms[0].txtBranchName.value = "";				  
				  document.forms[0].txtBranchAdd.value = "";
				  document.forms[0].txtMicrCode.value = "";
				  document.forms[0].txtIfscCode.value = "";
				  document.forms[0].txtContactNo.value = "";
				  document.forms[0].cmbTreasury.value = "-1";
			  }
			  function validateData()
			  {
				  if(document.forms[0].cmbBankName.value == "Select"){
					  alert("Please Select Bank Name");
					  document.forms[0].cmbBankName.focus();
						return false;
				  }else if(document.forms[0].txtBranchName.value == ""){
					  alert("Please Enter Branch Name");
					  document.forms[0].txtBranchName.focus();
						return false;
				  }else if(document.forms[0].cmbTreasury.value == "-1"){
					  alert("please select treasury");
					  document.forms[0].cmbTreasury.focus();
					  return false;
				  }else if(document.forms[0].txtMicrCode.value == ""){
					  alert("Please Enter Micr Code");
					  document.forms[0].txtMicrCode.focus();
						return false;
				  }else if(document.forms[0].txtIfscCode.value == ""){
					  alert("Please Enter IFSC Code");
					  document.forms[0].txtIfscCode.focus();
						return false;
				  }
				  else{
					  document.forms[0].submit();
					  return true;
				  }
			  }

				</script> <!-- Message in gujarati is still remains....Jaspal.. --></td>
	</tr>
</table>
	<br><br><br>
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
		document.frmBF.cmbBankName.focus();
		</script>
		
<c:if test="${resValue.key == 'Update'}">
<script>
alert("Record Updated Successfully");
var url="ifms.htm?actionFlag=getBranchMasterDtls";
document.frmBF.action=url;
document.frmBF.submit();
</script>
</c:if>

<c:if test="${resValue.key == 'Insert'}">
<script>
alert("Record Inserted Successfully");
var url1="ifms.htm?actionFlag=getBranchMasterDtls";
document.frmBF.action=url1;
document.frmBF.submit();
</script>
</c:if>
		
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

