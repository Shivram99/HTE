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
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjEmpPayrollData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="parentDeptByDefault" value="${resValue.listParentDept[0]}"></c:set>
<c:set var="UserList" value="${resValue.UserList}" />
<c:set var="empList" value="${resValue.empList}"></c:set>

<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page"
		value="style='display: none;' "></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>


<!--Added by Mayuresh-->

<c:if test="${EMPPAYROLLVO != null && EMPPAYROLLVO.gisApplicable == '700342'}">
	<c:set var="vargisDescription" scope="page" value="style='display:inline'"></c:set>
</c:if>


<script type="text/javascript">

function gisFieldDisplay()
{
	var appn = document.getElementById("cmbGisApplicable").value;

	if(appn == '700342')
	{
		document.getElementById("gisDescription").style.display="contents";
	}
	else
	{
		document.getElementById("gisDescription").style.display="none";
	}
	
}

</script>




<fieldset class="tabstyle"><Legend><fmt:message
	key="CMN.GISDETAILS" bundle="${dcpsLables}"></fmt:message></Legend>
<table width="100%" align="center" cellpadding="4" cellspacing="4">

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.GISAPPLICABLE"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><select name="cmbGisApplicable"
			id="cmbGisApplicable" style="width: 360px"
			onChange="changeGISDetails(); gisFieldDisplay();" ${varDisabled} >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>

			<c:forEach var="GISDetails" items="${resValue.lLstGISDetails}">
				<c:choose>
					<c:when test="${GISDetails.lookupId != 700341}">
						<c:choose>
							<c:when
								test="${EMPPAYROLLVO.gisApplicable == GISDetails.lookupId}">
								<option value="${GISDetails.lookupId}" selected="selected"><c:out
									value="${GISDetails.lookupDesc}"></c:out></option>

								<c:choose>
									<c:when test="${EMPVO==others}">
										<option value=""></option>
									</c:when>
								</c:choose>

							</c:when>
							<c:otherwise>
								<option value="${GISDetails.lookupId}"><c:out
									value="${GISDetails.lookupDesc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>
			</c:forEach>
		</select> <label class="mandatoryindicator"${varLabelDisabled}>*</label></td>
		
		
		
	</tr>
	
	
	<tr id="gisDescription"  ${vargisDescription} style="display:none">
		<td width="15%" align="left" id = "descriptionLable" ${varDisabled}><fmt:message key="CMN.DESCRIPTION"
			bundle="${dcpsLables}"></fmt:message></td>

		<td width="35%" align="left" id="description"  name="description" ${varDisabled}><input type="text" id="description"
			size="48" name="description" value="${EMPPAYROLLVO.gisDescription}"/></td>
	</tr>
	
	
	<tr>

		<td width="15%" align="left"><fmt:message key="CMN.GISGROUP"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><select name="cmbGisGroup"
			id="cmbGisGroup" style="width: 360px"
			${varDisabled} onchange="checkGISGroup();" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>

			<c:forEach var="GISGroup" items="${resValue.lLstGISGroup}">
				<c:choose>
					<c:when test="${EMPPAYROLLVO.gisGroup == GISGroup.lookupId}">
						<option value="${GISGroup.lookupId}" selected="selected"><c:out
							value="${GISGroup.lookupDesc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${GISGroup.lookupId}"><c:out
							value="${GISGroup.lookupDesc}"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</select> <label class="mandatoryindicator"${varLabelDisabled}>*</label></td>
	</tr>


	<tr>
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
			value="${EMPPAYROLLVO.membershipDate}" var="empGISDate" />
		<td width="15%" align="left"><fmt:message
			key="CMN.MEMBERSHIPDATE" bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text"
			id="txtMembershipDate" size="20" name="txtMembershipDate"
			value="${empGISDate}"
			onkeypress="digitFormat(this);dateFormat(this);"
			onBlur="validateDate(txtMembershipDate);"
			${varDisabled} readonly="readonly" /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtMembershipDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /> <label
			class="mandatoryindicator"${varLabelDisabled}>*</label></td>


	</tr>
</table>
</fieldset>
