<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>



 <fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/> 

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="AuditTrial" value="${resValue.AuditTrial}"></c:set>

   

<c:if test="${AuditTrial != null && AuditTrial[0] != null}"> 
<fieldset class="tabstyle" ><legend>Audit Trial </legend>   
	<div class="scrollablediv" >	
    <display:table list="${AuditTrial}" id="so" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="User Id" >		
				<c:out  value="${so[0]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="IP Address" > 				
				<c:out value="${so[1]}"></c:out>
		</display:column>
		<%-- <display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Time Stamp" >					
				<c:out value="${so[4]}"></c:out>
		</display:column> --%>
	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Login Date and time" >					
				<c:out value="${so[2]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Login Status" >					
				<c:out value="${so[3]}"></c:out>
	</display:column>
	<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Logout date and time" >					
				<c:if  test="${empty so[4]}">
				<c:out value="NA"></c:out>
			</c:if> 
			<c:if  test="${not empty so[4]}">
				<c:out value="${so[4]}"></c:out>
			</c:if>
	</display:column>
	<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Logout Status" >					
				<c:out value="${so[5]}"></c:out>
	</display:column>
	</display:table>
	 </div>
	 </fieldset>
	 </c:if> 
	