<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request"/>


<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsSrka.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsDDO.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.DESELECTEMPLIST}"></c:set>

	<hdiits:form name="DDOEmpDeselect" id="DDOEmpDeselect" encType="multipart/form-data" validate="true" method="post">
	<input type="hidden" name="csrfToken" value="${csrfToken}"/>
	<fieldset style="width:100%" class="tabstyle">
		<legend id="headingMsg">
		<b><fmt:message key="CMN.DDOEMPDESELECTSEARCH" bundle="${dcpsLabels}"></fmt:message></b>
		</legend>
		<table>
		<tr align="center">
			<td width="100%" align="left" colspan="2">
				<font color="green">Employees which are detached from the bill group are available for relieving.</font>
				</td>
			</tr>
			<tr align="center">
				<td width="25%" align="left" ><fmt:message
					key="CMN.SEVARTHID" bundle="${dcpsLabels}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtSevaarthId" style="text-transform: uppercase" size="30"
					name="txtSevaarthId"/></td>
			</tr>
			<tr align="center">
				<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
					bundle="${dcpsLabels}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtEmployeeName" size="30"
					name="txtEmployeeName" />
				<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><label style="color: red"><fmt:message
					key="MSG.SEARCH" bundle="${dcpsLabels}" /></label></td>
			</tr>
		</table>
			<div style="width:50;text-align: center;align:center">
				<hdiits:button name="btnSearch" style="align:center" type="button" captionid="CMN.SEARCH" bundle="${dcpsLabels}" onclick="showEmpDeSelectionList();" />
				<hdiits:button name="btnDisplayAll" style="align:center" type="button" captionid="BTN.DISPLAYALL" bundle="${dcpsLabels}" onclick="showAllForDeSelection();" />
			</div>
	</fieldset>
	
	<br>
	
	<fieldset style="width:100%" class="tabstyle">
	<legend id="headingMsg">
	<b><fmt:message key="CMN.DDOEMPDESELECT" bundle="${dcpsLabels}"></fmt:message></b>
	</legend>
			<table border = "0" cellpadding = "4" width="50%" align="center">
			<tr>
			<td style="width:10%" align = "right"><b><fmt:message key="CMN.DESIGNATION" bundle="${dcpsLabels}"></fmt:message></b></td>
			<td style="width:30%">
			<select name="cmbDesig" id="cmbDesig" onChange="" style="width:100%">
							<c:choose>
								<c:when test="${resValue.lStrSelectedDesig == -1}">
									<option value="-1" selected="selected" >Select</option>
								</c:when>
								<c:otherwise>
									<option value="-1">Select</option>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${resValue.lStrSelectedDesig == -2}">
									<option value="-2" selected="selected" >All Designations</option>
								</c:when>
								<c:otherwise>
									<option value="-2">All Designations</option>
								</c:otherwise>
							</c:choose>
								
							<c:forEach var="desigList" items="${resValue.DESIGNATIONLIST}">
								<c:choose>
									<c:when test="${resValue.lStrSelectedDesig == desigList.id}">
										<option value="${desigList.id}" selected="selected" ><c:out value="${desigList.desc}"></c:out></option>	
									</c:when>
									<c:otherwise>
										<option value="${desigList.id}"><c:out value="${desigList.desc}"></c:out></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						</td>
						<td style="width:10%" align = "left"><hdiits:button name="btnFilter" type="button" captionid="BTN.FILTER" bundle="${dcpsLabels}" onclick="filterDesig();" /></td>
			</tr>
			</table>
	
			<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
			<input type="hidden" name="currDate1" id="currDate1" value="${resValue.gDtCurDate}" />
			
			<div class="scrollablediv"  style="width: 90%;overflow:auto;height: 200px;margin: auto;" >
						<c:if test="${VOList != null}">
						<display:table list="${VOList}" id="vo" 
						cellpadding="5" style="width:150%" requestURI="">
						
							<display:column titleKey="CMN.CHKBXEMPDESELECT" title="<input name='chkDeSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader" style="text-align:center;width: 3%" class="oddcentre" sortable="true">
								<input type="checkbox" align="left" name="checkbox${vo_rowNum}" id="checkbox${vo_rowNum}"
								 value="${vo[0]}" />
								 <script>
										document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
								</script>
							</display:column>
							
							<display:column titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader" sortable="true" style="width:12%" >
								<c:out value="${vo[1]}"></c:out> 
									<input type="hidden" id="hidName${vo_rowNum}" value="${vo[1]}"/>
									<input type="hidden" id="hidDdoAsstOrNotStatus${vo_rowNum}" value="${vo[5]}"/>
						    </display:column>
						    
						    <display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;width: 5%" sortable="true"  titleKey="CMN.SEVARTHID" >		
									<c:out value="${vo[4]}" />
							</display:column>
						    
							<display:column style="text-align: left;width: 10%"
								class="tablecelltext" titleKey="CMN.DESIGNATIONWOCOLON"
								headerClass="datatableheader" sortable="true" >
								${vo[2]}
							</display:column>
							
							<display:column headerClass="datatableheader" style="text-align:left;width: 20%" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >		
								<c:out value="${vo[6]}"></c:out> 
							</display:column>
							
							<display:column style="text-align: left;width: 9%"
								class="tablecelltext" titleKey="CMN.DATEOFDESELECTION"
								headerClass="datatableheader" sortable="true" >
							<input type="text" name="txtDateOfDeselection${vo_rowNum}" size="10"  id="txtDateOfDeselection${vo_rowNum}" maxlength="10" 
								 onkeypress="digitFormat(this);dateFormat(this);numberFormat(this);" onblur="validateDate(this);compareDatesAndEraseFirst(this,document.getElementById('currDate1'),'Date of Deselection should be less than current date.','<')" />
							<img src='images/CalendarImages/ico-calendar.gif' width='20'
							onClick='window_open("txtDateOfDeselection${vo_rowNum}",375,570)'
							style="cursor: pointer;" ${disabled}/>
							
							<label class="mandatoryindicator" >*</label>
							</display:column>
							
							<display:column style="text-align: left;width: 10%"
								class="tablecelltext" titleKey="CMN.RSNFRDESELECTION"
								headerClass="datatableheader" sortable="true" >
								<select name="cmbReason" id="cmbReason${vo_rowNum}" style="width:90%" onChange="ChangeEditablityOfDeputationLocation('${vo_rowNum}');">
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
									<c:forEach var="reasonList" items="${resValue.REASONLIST}">
										<option value="${reasonList.lookupId}"><c:out value="${reasonList.lookupDesc}"></c:out></option>
								</c:forEach>
								</select>
								<label class="mandatoryindicator"${varLabelDisabled}>*</label>
							</display:column>
							
							<display:column style="text-align: left;width: 10%"
								class="tablecelltext" titleKey="CMN.REMARKSLOCATION"
								headerClass="datatableheader" sortable="true" >
								<input type="text" size="30" style="width: 90%" name="txtRemarks" id ="txtRemarks${vo_rowNum}" readonly="readonly" ></input>
								<label class="mandatoryindicator" id="labelForLocation${vo_rowNum}" style="display: none" >*</label>
							</display:column>
							
							<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width: 10%" sortable="true"  titleKey="CMN.ORDERNO" >
								<input type="text"	id="txtAuthorityLetterNo${vo_rowNum}" style="text-transform: uppercase" size="20"	name="txtAuthorityLetterNo" value="" onblur="" />
								<label class="mandatoryindicator">*</label>
							</display:column>
							
							<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width: 8%" sortable="true"  titleKey="CMN.ORDERDATE" >
								<input type="text"	id="txtAuthorityLetterDate${vo_rowNum}" style="text-transform: uppercase" size="9"	onkeypress="digitFormat(this);dateFormat(this);"
									name="txtAuthorityLetterDate" value="" /><img	src='images/CalendarImages/ico-calendar.gif' width='20'
									onClick='window_open("txtAuthorityLetterDate${vo_rowNum}", 375, 570)'
									style="cursor: pointer;"/>
								<label class="mandatoryindicator">*</label></td>
							</display:column>
						
							<display:setProperty name="paging.banner.placement" value="bottom" />
							
						</display:table>
						</c:if>
		</div>
	</fieldset>
</br>
<div style="width:50;text-align: center;align:center">
			<hdiits:button name="btnDeselect" id="btnDeselect" classcss="bigbutton" type="button" captionid="CMN.DDOEMPDESELECTBTN" bundle="${dcpsLabels}" onclick="dcpsDDOEmpDeselect();" />
</div>


<input type="hidden" name="hidSearchFromDDODeSelection" id="hidSearchFromDDODeSelection" value="searchFromDDODeSelection" />

</hdiits:form>

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDODeSelection}"
	className="autocomplete" minimumCharacters="3"
	indicator="roleIndicatorRegion" />