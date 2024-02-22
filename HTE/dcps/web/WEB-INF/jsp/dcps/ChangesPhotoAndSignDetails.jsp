<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="CHANGESHISTORYVO" value="${resValue.lObjHstDcpsChanges}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<script type="text/javascript">
function approvePhotoAndSignDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
	var User =document.getElementById("User").value.trim() ; 
	 
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	{
	   hideProgressbar();
	   return;
	}

	var uri = 'ifms.htm?actionFlag=approveChangesByDDO';
	var url = uri + '&dcpsChangesId='+ dcpsChangesId ;
	
	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].text;

						if (test_Id) {
							alert('The Changes are Approved.');
							self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
						}
					}
				}
		} ;

	 xmlHttp.open("POST",uri,false);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(url);				
}

function rejectPhotoAndSignDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
	var sentBackRemarks = document.getElementById("sentBackRemarks").value.trim();
	var User =document.getElementById("User").value.trim() ; 

	if(sentBackRemarks == "")
	{
		alert('Please Enter Remarks.');
		hideProgressbar();
		return false ;
	}
	 
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	{
		hideProgressbar();
	   return;
	}

	var uri = 'ifms.htm?actionFlag=rejectChangesToDDOAsst';
	var url = uri + '&dcpsChangesId='+ dcpsChangesId + '&sentBackRemarks='+sentBackRemarks ;
	//alert(url);
	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].text;

						if (test_Id) {
							alert('The changes are rejected and sent back to DDO Assistant.');
							self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
						}
					}
				}
		} ;

	 xmlHttp.open("POST",uri,false);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(url);				
}
function updateOrForwardPhotoAndSignDetails(emp_id,flag)
{
		showProgressbar();
			var txtAuthorityLetterNo = document.getElementById("txtAuthorityLetterNo").value.trim() ;
			var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value.trim() ;

	/*		if(flag==2)
			{
				if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate == "")
				{
					alert('Please fill the Authority Details.') ;
					return false;
				}
			}
	*/

			var empId= emp_id;
			
			var saveOrForwardFlag=flag;
			var designationId = document.getElementById("lStrDesignation").value.trim();
			var typeOfChanges = document.getElementById("lStrChangesType").value.trim();
			var oldPhotoAttachmentId = document.getElementById("lStrOldPhotoId").value.trim();
			var oldSignAttachmentId = document.getElementById("lStrOldSignId").value.trim();
			var User =document.getElementById("User").value.trim() ;
			designationId 
			//alert(startStatusCheckPhoto());
			 
			
				
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				hideProgressbar();
			   return;
			}
			 if(Number(document.getElementById('myTablePhoto').rows.length)==2 && Number(document.getElementById('myTableSign').rows.length)==2 ) {
			 var uri = 'ifms.htm?actionFlag=updatePhotoAndSignDtls';
			 var url = runForm(0); 
			 url = uri + url; 
			 url = url + "&empId="+empId;	
			 var dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value.trim();
			 var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
			 url = url + "&dcpsHstChangesId="+dcpsHstChangesId; 
			 url = url + "&oldPhotoAttachmentId="+oldPhotoAttachmentId;
			 url = url + "&oldSignAttachmentId="+oldSignAttachmentId;
			 url = url + "&change="+ "PhotoAndSignDetails"  ;
			 url = url + "&User="+User;
			 
				 
			 xmlHttp.onreadystatechange = function()
				{
					if (xmlHttp.readyState == 4) {
						if (xmlHttp.status == 200) {
							
							hideProgressbar();
							XMLDoc = xmlHttp.responseXML.documentElement;
							var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
								var test_Id = XmlHiddenValues[0].childNodes[0].text;
								var dcpsChangesId =  XmlHiddenValues[0].childNodes[1].text;
								if (test_Id) {
									
									if(saveOrForwardFlag==1)
									{
										if(dcpsHstChangesId == "")
										{
											alert('Photo and Signature Details have been successfully changed and saved.');
											self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
										}
										else
										{
											alert('Photo and Signature Details have been successfully changed and saved.');
											self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
										}
									}
									if(saveOrForwardFlag==2)
									{
										var ForwardToPost =  document.getElementById("ForwardToPost").value.trim();
										var uriForward ;
											if(dcpsHstChangesId == "")
											{
											uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="+dcpsChangesId+"&ForwardToPost="+ForwardToPost;
											}
											else
											{
											uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="+dcpsHstChangesId+"&ForwardToPost="+ForwardToPost;
											}
										//alert('uriForward-->'+uriForward);
										xmlHttpNew=GetXmlHttpObject();
										
										if (xmlHttpNew==null)
										{
											alert ("Your browser does not support AJAX!");
											return;
										} 
										xmlHttpNew.onreadystatechange= function()
										{
											if (xmlHttpNew.readyState == 4) {
												if (xmlHttpNew.status == 200) {
													
													XMLDocNew = xmlHttpNew.responseXML.documentElement;
													var XmlHiddenValuesNew = XMLDocNew.getElementsByTagName('XMLDOC');
													var success_flag = XmlHiddenValuesNew[0].childNodes[0].text;
													if(success_flag=='true')
													{
														if(dcpsHstChangesId == "")
														{
															alert("Changes are forwarded successfully");
															self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
														}
														else
														{
															alert("Changes are forwarded successfully");
															self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
														}
													}
												  }
												}
										};
										xmlHttpNew.open("POST",uriForward,false);
										xmlHttpNew.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
										xmlHttpNew.send(uriForward);
									}
									
								}
							}
						}
				};
			   xmlHttp.open("POST",uri,false);
			   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			   xmlHttp.send(url);

			 } else {
				 
					alert("Please select Files for photo & signature then upload respectively.");
					hideProgressbar();
				 }
			   
}
function hidImg()
{
	newWindow1.close()
}
function showImg(lStr)
{
	document.getElementById("attachmentName1").value = lStr.name;
	var height = 600;
	var width = 600;
	var urlstring = "ifms.htm?viewName=dcpsPhoto";
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window.open(urlstring,"PensionerPhoto","'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0","false");
	//globArray[9] = newWindow1; 
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>

<input type="hidden" name="hidPhotoUrl" id="hidPhotoUrl" >
<input type="hidden" name="hidSignUrl" id="hidSignUrl" >
<input type="hidden" name="attachmentName1" id="attachmentName1" >

<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg"><b>Existing Details</b></legend>
			
		<table width="100%" >
		<tr>
		
			
			<td align = "center">
				<fieldset class="tabstyle" style="width: 180px;height: 150px;"  >
					<legend id="headingMsg"><b>Photo </b></legend>
				
				<c:choose>
						<c:when test="${resValue.PhotoId1 != null}" >
							<div id="prewPhoto1" style="width: 180px;height: 150px;">
								
									<img style="width: 180px;height: 150px;" id="Photo1"  src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId1}&attachmentSerialNumber=${resValue.PhotosrNo1}">
								
								
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewPhoto1" style="width: 180px;height: 150px;">
							
							</div>	
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;
								
				</fieldset>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<fieldset class="tabstyle" style="width: 180px;height: 150px;"  >
					<legend id="headingMsg"><b> Signature</b></legend>
						<c:choose>
						<c:when test="${resValue.SignId1 != null}" >
							<div id="prewSign1" style="width: 180px;height: 150px;">
								
									<img  style="width: 180px;height: 150px;" id="Sign1" src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId1}&attachmentSerialNumber=${resValue.SignsrNo1}">
								
								
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewSign1" style="width: 180px;height: 150px;">
							</div>	
						</c:otherwise>
					</c:choose>
						
					
				
     			</fieldset>	
				
			</td>
		</tr>
	</table>
		
</fieldset>

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post">
	
<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg"><b>Changed Details</b></legend>
	<table width="100%"> 	
		<tr>
		
			<td>
				<div id="divPhoto">
						<jsp:include page="/WEB-INF/jsp/dcps/ChangesAttachment.jsp">
							<jsp:param name="attachmentName" value="Photo"/>
						    <jsp:param name="formName" value="DCPSForm"/>
						   	<jsp:param name="attachmentType" value="Document"/>
						   	<jsp:param name="attachmentTitle" value="Photo"/>
						   	<jsp:param name="multiple" value="N" />
						   	<jsp:param name="removeAttachmentFromDB" value="Y" />
						</jsp:include>
						</div>
						<script type="text/javascript">
						  document.getElementById('descPhoto').value="Photo";
						  document.getElementById('descPhoto').readOnly =true;
						</script>
			</td>
			<td>
				<fieldset class="tabstyle" style="width: 80%;" >
					<legend id="headingMsg"><b>Photo </b></legend>
				
				<c:choose>
						<c:when test="${resValue.PhotoId != null}" >
							<div id="prewPhoto" style="width: 180px;height: 150px;">
								<a href="#" id="Photo" name="Photo" onmouseout="hidImg()"  onmouseover="showImg(this)"  >	
									<img style="width: 180px;height: 150px;" id="Photo"  src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}">
								</a>
								<script type="text/javascript">
									document.getElementById("hidPhotoUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}"
								</script>
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewPhoto" style="width: 180px;height: 150px;">
							
							</div>	
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;
					<c:set value="Upload?attachmentNameHidden=Photo&attachmentMpgSrNo=${resValue.srNo}" var="url"></c:set>			
				</fieldset>
			</td>	
			
			</tr>
			<tr>		
     	  	<td>
				<div id="divSign">
					<jsp:include page="/WEB-INF/jsp/dcps/ChangesAttachment.jsp">
						<jsp:param name="attachmentName" value="Sign"/>
					    <jsp:param name="formName" value="DCPSForm"/>
					   	<jsp:param name="attachmentType" value="Document"/>
					   	<jsp:param name="attachmentTitle" value="Signature"/>
					   	<jsp:param name="multiple" value="N" />
					   	<jsp:param name="removeAttachmentFromDB" value="Y" />
					</jsp:include>
				</div>
				<script type="text/javascript">
						  document.getElementById('descSign').value="Signature";
						  document.getElementById('descSign').readOnly =true;
						</script>
			</td>
			<td>
				<fieldset class="tabstyle" style="width: 80%;" >
					<legend id="headingMsg"><b> Signature</b></legend>
						<c:choose>
						<c:when test="${resValue.SignId != null}" >
							<div id="prewSign" style="width: 180px;height: 150px;">
								<a href="#" name="Sign" id="Sign" onmouseout="hidImg()"  onmouseover="showImg(this)"  >	
									<img  style="width: 180px;height: 150px;" src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}">
								</a>
								<script type="text/javascript">
									document.getElementById("hidSignUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}"
								</script>
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewSign" style="width: 180px;height: 150px;">
							</div>	
						</c:otherwise>
					</c:choose>
						
					<c:set value="Upload?attachmentNameHidden=Sign&attachmentMpgSrNo=${resValue.srNo}" var="url"></c:set>	
				
     			</fieldset>	
				
			</td>
		</tr>
	</table>
</fieldset>

<br/>
	<br/>
	
	<fieldset ><legend> <b>Authority Details</b> </legend>
		<table width="50%" align="left" cellpadding="4" cellspacing="4" >
		<tr>
			<td width="15%" align="left">Authority Letter No.</td>
			<td width="20%" align="left" colspan = "3"><input type="text"
				id="txtAuthorityLetterNo" style="text-transform: uppercase" size="30"
				name="txtAuthorityLetterNo" value="${CHANGESHISTORYVO.letterNo}" onblur="isIntegerOrNot(document.getElementById('txtAuthorityLetterNo'),'Authority Letter No should be Number Only')"/>
				<label class="mandatoryindicator">*</label></td>
		
		</tr>
		<tr>
			<td width="15%" align="left">Letter Date</td>
			<c:if test="${resValue.lObjHstDcpsChanges != null}">
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${CHANGESHISTORYVO.letterDate}" var="letterDate"/>
			</c:if>
			<td width="20%" align="left" colspan = "3"><input type="text"
				id="txtAuthorityLetterDate" style="text-transform: uppercase" size="30"
				onkeypress="digitFormat(this);dateFormat(this);"
				name="txtAuthorityLetterDate" value="${letterDate}" /><img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtAuthorityLetterDate", 375, 570)'
				style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label></td>
		
		</tr>
		
		</table>
		
		</fieldset>
		<br/>
		

		<input type="hidden" id="txtDdoCode" name="txtDdoCode" value="${resValue.DDOCODE}"/>
		<input type="hidden" id="lStrDesignation" name="lStrDesignation" value="${resValue.lStrDesignation}"/>
		<input type="hidden" id="lStrChangesType" name="lStrChangesType" value="${resValue.lStrChangesType}"/>
		<input type="hidden" id="lStrOldPhotoId" name="lStrOldPhotoId" value="${resValue.PhotoId1}"/>
		<input type="hidden" id="lStrOldSignId" name="lStrOldSignId" value="${resValue.SignId1}"/>
		
		<input type="hidden" id="User" name="User" value="${resValue.UserType}">
		<c:if test="${resValue.UserType == 'DDO'}">
		<table width="100%">
			<tr>
				<td width="20%" align="left" style="padding-left: 5px"><fmt:message key="CMN.REMARKS"
					bundle="${dcpsLables}"></fmt:message></td>
				<td align="left" width="80%" style="padding-left: 23px">
					<input type="text" name="sentBackRemarks" id="sentBackRemarks" value="" size="100"  />
				</td>
			</tr>
		</table>
		</c:if>
	
		<div align="right"	>
				<c:choose>
							<c:when test="${resValue.dcpsChangesId != null}">
								<input type="hidden" id="dcpsHstChangesId" value="${resValue.dcpsChangesId}"/>
								<input type="hidden" id="lStrDesignationDraft" value="${resValue.lStrDesignationDraft}"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" id="dcpsHstChangesId" value=""/>
								<input type="hidden" id="lStrDesignationDraft" value=""/>
							</c:otherwise>
				</c:choose>
				<c:choose>
							<c:when test="${resValue.UserType == 'DDOAsst'}">
								<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnUpdatedataForUpdateTotally" id="btnUpdatedataForUpdateTotally" type="button"
									captionid="BTN.SAVEASDRAFT" bundle="${dcpsLables}"
									onclick="updateOrForwardPhotoAndSignDetails('${resValue.dcpsEmpId}',1);" />
									
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
								<hdiits:button name="BTN.FORWARD" id="btnForwardForUpdateTotally" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick="updateOrForwardPhotoAndSignDetails('${resValue.dcpsEmpId}',2);" />
							</c:when>
							<c:otherwise>
								<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnApprovePersonalDtls" id="btnApprovePersonalDtls" type="button"
									captionid="BTN.APPROVE" bundle="${dcpsLables}"
									onclick="approvePhotoAndSignDetails();" />
							
								<hdiits:button name="BTN.REJECT" id="btnRejectPersonalDtls" type="button"
									captionid="BTN.REJECT" bundle="${dcpsLables}" onclick="rejectPhotoAndSignDetails();" />
							</c:otherwise>	
				</c:choose>
		</div>					
			
</hdiits:form>
