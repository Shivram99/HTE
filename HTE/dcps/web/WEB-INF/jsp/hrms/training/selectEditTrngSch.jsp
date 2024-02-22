<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>
<fmt:setBundle basename="resources.trng.AllotmentLables" var="allotLables" scope="request" />
<fmt:setBundle basename="resources.trng.trschLables" var="trngLables" scope="request" />

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/training/training.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trainingMstList" value="${resValue.trainingMstList}" > </c:set>

<hdiits:form name="selectTraining" validate="true" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="getTrngSchedule"/>
<hdiits:hidden name="editFlag" default="Y" />

<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SelectTrngAttnd" bundle="${trngLables}"></hdiits:caption> </a></li>
		</ul>
</div>

<div id="tcontent1" class="tabcontent" tabno="0">
<fmt:message key="TR.SEARCH_ATTENDANCE"  bundle="${trngLables}" var="attendanceTitle"></fmt:message>
<TABLE class="tabtable">
<tr>  
	<td class="fieldLabel" colspan="6">
	<jsp:include page="editTrngScheduleSearch.jsp">
		<jsp:param name="searchAttendanceTitle" value="${attendanceTitle}"/>
		<jsp:param name="mandatory" value="Yes" />
	</jsp:include>
	</td>
</tr>

</TABLE>

<br>
		

</div>
<jsp:include page="../../core/tabnavigation.jsp" />

<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>