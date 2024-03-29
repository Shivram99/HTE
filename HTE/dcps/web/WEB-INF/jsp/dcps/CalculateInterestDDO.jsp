<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/InterestCalculation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />


<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<fieldset class="tabstyle">
<legend><b><font color="red"><fmt:message key="CMN.CALCULATEINTERESTFORDDO" bundle="${dcpsLables}"></fmt:message></font></b></legend>

<table id="tblMain" align="center" cellpadding="4" cellspacing="4" width="100%" >	
	<tr>
		<td width="10%">
			<fmt:message key="CMN.TREASURYNAME" bundle="${dcpsLables}"></fmt:message>				
		</td>
		<td width="35%">
			<select name="cmbTreasury" id="cmbTreasury" style="width: 70%">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="treasury" items="${resValue.TREASURIES}" >
								<c:choose>
													<c:when test="${resValue.selectedTreasury == treasury.id}">
														<option value="${treasury.id}" selected="selected"><c:out 
																value="${treasury.desc}"></c:out></option>
									</c:when>
									<c:otherwise>
														<option value="${treasury.id}"><c:out 
																value="${treasury.desc}"></c:out></option>
									</c:otherwise>						
							    </c:choose>
					</c:forEach>	
			</select>
		</td>
		<td width="10%">
			<fmt:message key="CMN.DDONAME" bundle="${dcpsLables}"></fmt:message>				
		</td>
		<td width="45%">
			<select name="cmbDDOCode" id="cmbDDOCode" style="width:100%"  onChange="" >
			<c:if test="${resValue.DDOList == null}">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			</c:if>
											<c:forEach var="DDOName" items="${resValue.DDOList}" >
															<c:choose>
																<c:when test="${resValue.selectedDDO == DDOName.id}">
																					<option value="${DDOName.id}" selected="selected" title="${DDOName.desc}"><c:out 
																							value="${DDOName.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${DDOName.id}" title="${DDOName.desc}"><c:out 
																							value="${DDOName.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
			</select>
		</td>
	</tr>
</table>
</fieldset>

<ajax:select source="cmbTreasury" target="cmbDDOCode"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOsForTreasury"
	parameters="cmbTreasuryCode={cmbTreasury}">
</ajax:select>