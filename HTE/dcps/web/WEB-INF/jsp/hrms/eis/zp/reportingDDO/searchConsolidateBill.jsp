
<%
try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="ReportingDDO" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="month" value="${resValue.month}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="scheme" value="${resValue.scheme}"></c:set>
<c:set var="list" value="${resValue.list}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>
<%--Added by kinjal--%>
<c:set var="count" value="1"></c:set>
<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>

<!-- For BEAMS Integration starts -->
<c:set var="authNo" value="${resValue.authNo}"></c:set>
<c:set var="statusCode" value="${resValue.statusCode}"></c:set>
<c:set var="finalMsg" value="${resValue.finalMsg}"></c:set>
<c:set var="statusMsg" value="${resValue.statusMsg}"></c:set>
<c:set var="CHECKPUNE" value="${resValue.CHECKPUNE}"></c:set>
<c:set var="beamsDDOflag" value="1"></c:set>
<!-- For BEAMS Integration ends -->
<c:set var="selMonth" value="${resValue.selMonth}"></c:set>
<c:set var="selYear" value="${resValue.selYear}"></c:set>
<c:set var="selSchemeCode" value="${resValue.selSchemeCode}"></c:set>


<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

</head>

<hdiits:form name="frmReportingDDO" validate="true" method="post" action="ifms.htm?actionFlag=viewConsolidatedBill">
    <hdiits:hidden name="seladminDept" id="seladminDept" default="2" />
    <div id="tabmenu">
        <ul id="maintab" class="shadetabs">
            <li class="selected">
                <a href="#" rel="tcontent1"><b>Search consolidated Pay Bill</b></a>
            </li>
        </ul>
    </div>
    <br />
    <div id="tcontent1" style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">
        <table width="100%">
            <tr>
                <td><b><hdiits:caption captionid="PR.MONTH" bundle="${ReportingDDO}" /></b></td>
                <td>
                    <select name="month" id="month">
                        <option value="-1"><fmt:message key="EIS.Select" bundle="${ReportingDDO}" /></option>
                        <c:forEach var="months" items="${month}">
                            <c:choose>
                                <c:when test="${selMonth == months.lookupShortName}">
                                    <option selected="selected" value="${months.lookupShortName}">
                                        <c:out value="${months.lookupDesc}" />
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${months.lookupShortName}">
                                        <c:out value="${months.lookupDesc}" />
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><b><hdiits:caption captionid="PR.YEAR" bundle="${ReportingDDO}" /></b></td>
                <td>
                    <select name="year" id="year">
                        <option value="-1"><fmt:message key="EIS.Select" bundle="${ReportingDDO}" /></option>
                        <c:forEach var="years" items="${year}">
                            <c:choose>
                                <c:when test="${selYear == years.lookupName}">
                                    <option selected="selected" value="${years.lookupName}">
                                        <c:out value="${years.lookupDesc}" />
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${years.lookupName}">
                                        <c:out value="${years.lookupDesc}" />
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><b>Scheme Code</b></td>
                <td>
                    <select name="schemecode" id="schemecode">
                        <option value="-1">--select----</option>
                        <c:forEach var="schemes" items="${scheme}">
                            <c:choose>
                                <c:when test="${selSchemeCode == schemes[1]}">
                                    <option selected="selected" value="${schemes[1]}">
                                        <c:out value="${schemes[2]}" />
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${schemes[1]}">
                                        <c:out value="${schemes[2]}" />
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <br><br>
        <table align="center">
            <tr>
                <td align="center">
                    <hdiits:submitbutton value="Search" id="search" name="search" />
                </td>
            </tr>
        </table>
        <br>
        <input type="hidden" value="yes" id="flag" name="flag">
        <hdiits:validate locale="${locale}" controlNames="" />
    </div>
</hdiits:form>

<form name="frmDeletePaybill" method="post">
    <div id="tabmenu">
        <ul id="maintab" class="shadetabs">
            <li><a href="#" rel="tcontent1" class="selected"><b>Consolidated Pay Bills</b></a></li>
        </ul>
    </div>

    <div id="tcontent1" style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">
        <table width="100%" border="1">
            <tr>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Select</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Consolidated Bill Id</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Scheme Code</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Sub Scheme Code</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Gross Amount</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Net Amount</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Authorisation Number</b></td>
                <td align="center" style="background-color: #F7E7D7; color: #CA610C; font-size: small; font-weight: bold"><b>Status</b></td>
            </tr>
            <c:forEach var="entry" items="${list}" varStatus="loop">
                <tr>
                    <td align="center">
                        <input type="radio" name="billno" id="billid${loop.index}" value="${entry[0]}" onclick="disableEnableBtn(${entry[4]});" />
                    </td>
                    <td align="center">
                        <form id="billForm${entry[0]}" action="ifms.htm?actionFlag=getConsolidatedReport" method="post" style="display:none;">
    						<input type="hidden" name="billId" value="${entry[0]}"/>
						</form>
					<a href="#" onclick="document.getElementById('billForm${entry[0]}').submit();">${entry[0]}</a>
                        
                    </td>
                    <td align="center"><c:out value='${entry[1]}' /></td>
                    <td align="center"><c:out value='${entry[5]}' /></td>
                    <td align="center"><c:out value='${entry[2]}' /></td>
                    <td align="center"><c:out value='${entry[3]}' /></td>
                    <c:choose>
                        <c:when test="${entry[6] != null}">
                            <td align="center">
                                <a href="#" onclick="openAuthSlipPDF('${entry[6]}')">${entry[6]}</a>
                                <hdiits:hidden name="authNo${loop.index}" id="authNo${loop.index}" default="${entry[6]}" />
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td align="center">-</td>
                        </c:otherwise>
                    </c:choose>
                    <td align="center">
                        <c:choose>
                            <c:when test="${entry[4] == '0'}"><label id="lblStatus${loop.index}">Pending</label></c:when>
                            <c:when test="${entry[4] == '5'}"><label id="lblStatus${loop.index}">BDS Generated<br />(Pending for Approval)</label></c:when>
                            <c:when test="${entry[4] == '2'}"><label id="lblStatus${loop.index}">Bill Rejected by BEAMS</label></c:when>
                            <c:when test="${entry[4] == '4'}"><label id="lblStatus${loop.index}">Bill Rejected by BEAMS</label></c:when>
                            <c:when test="${entry[4] == '1'}"><label id="lblStatus${loop.index}">Approved</label></c:when>
                        </c:choose>
                        <input type="hidden" id="hdStatus${loop.index}" value="${entry[5]}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <table align="center">
            <tr>
                <td><input type="button" name="delete" class="buttontag" id="delete" value="Delete" onclick="return deleteConsPayBill();" /></td>
                <td><input type="button" name="viesConsDetails" class="buttontag" id="viesConsDetails" value="View Details" onclick="return viewConsBillDetails();" /></td>
                <td><input type="button" name="abstractReport" class="buttontag" id="abstractReport" value="Abstract Report" onclick="return abstractReports();" /></td>
                <td><input type="button" name="fwdConsoBill" class="bigbutton" id="fwdConsoBill" value="Forward Bill to BEAMS" onclick="return forwardBillDataToBEAMS();" /></td>
            </tr>
        </table>
    </div>
</form>




<%
} catch (Exception e) {
e.printStackTrace();
}
%>

<script>
	initializetabcontent("maintab");
function approvedBill(ele){
	alert("Bill Is approved so cant be deleted");
	this.checked=false;
	
	
}

	function CloseScreen() {
		var urlstyle = "ifms.htm?actionFlag=validateLogin";
		document.forms[0].action = urlstyle;
		document.forms[0].submit();
	}

	function searchConsPayBill(){
		var url="ifms.htm?actionFlag=viewConsolidatedBill&flag='yes'";
		document.frmReportingDDO.action="";
		document.frmReportingDDO.submit();
	}
	
	
</script>

<script type="text/javascript">

function abstractReports(){
	
	var len = '${size}';
	
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		
		if (document.getElementById("billid"+i).checked) {
			
			isChecked = true;
			var billNo=document.getElementById("billid"+i).value;
			
			break;

		}

	}
	if (!isChecked) {
		alert("Please select atleast one Bill to abstract reports. ");
		return false;
	}

	else {
		/* var url="ifms.htm?actionFlag=abstractReports&billid="+billNo;
		document.frmDeletePaybill.action=url;
		document.frmDeletePaybill.submit(); */
		
		var form = document.createElement("form");
		form.setAttribute("method", "POST");
		form.setAttribute("action", "ifms.htm?actionFlag=abstractReports");
		
		var inputBill = document.createElement("input");
		inputBill.setAttribute("type", "hidden");
		inputBill.setAttribute("name", "billid");
		inputBill.setAttribute("value", billNo);

		form.appendChild(inputBill);

		document.body.appendChild(form);
		form.submit();
		document.body.removeChild(form);
		
		showProgressbar("Please wait<br>Your Request is in progress ...");
		return true;
	}
}



function deleteConsPayBill(){
	
	var len = '${size}';
	var month= document.getElementById("month").value;
	var year= document.getElementById("year").value;
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		
		if (document.getElementById("billid"+i).checked) {
			
			isChecked = true;
			var billNo=document.getElementById("billid"+i).value;
			
			break;

		}

	}
	if (!isChecked) {
		alert("Please select atleast one Bill ");
		return false;
	}

	else {
		var answer = confirm('Do you want to delete the Consolidated Bill?');
		if (answer) {
			//alert(billNo);
			/* var url="ifms.htm?actionFlag=deleteconsBillGenerated&billNo="+billNo+"&month="+month+"&year="+year;
			document.frmDeletePaybill.action=url;
			document.frmDeletePaybill.submit(); */
			
			var form = document.createElement("form");
			form.setAttribute("method", "POST");
			form.setAttribute("action", "ifms.htm?actionFlag=deleteconsBillGenerated");
			
			var inputBill = document.createElement("input");
			inputBill.setAttribute("type", "hidden");
			inputBill.setAttribute("name", "billNo");
			inputBill.setAttribute("value", billNo);
			
			var inputMonth = document.createElement("input");
			inputBill.setAttribute("type", "hidden");
			inputBill.setAttribute("name", "month");
			inputBill.setAttribute("value", month);
			
			var inputYear = document.createElement("input");
			inputBill.setAttribute("type", "hidden");
			inputBill.setAttribute("name", "year");
			inputBill.setAttribute("value", year);

			form.appendChild(inputBill);
			form.appendChild(inputMonth);
			form.appendChild(inputYear);

			document.body.appendChild(form);
			form.submit();
			document.body.removeChild(form);
			
			showProgressbar("Please wait<br>While deleting Consolidated Bill ...");
		} else {
			return false;
		}return true;
	}
	
	showProgressbar("Please Wait ");
}
</script>

<script type="text/javascript">
function viewConsBillDetails(){
	
	var len = '${size}';
	
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		
		if (document.getElementById("billid"+i).checked) {
			
			isChecked = true;
			var billNo=document.getElementById("billid"+i).value;
			
			break;

		}

	}
	if (!isChecked) {
		alert("Please select atleast one Bill to view details. ");
		return false;
	}

	else {
		/* var url="ifms.htm?actionFlag=viewconsBillForDetailView&billid="+billNo;
		document.frmDeletePaybill.action=url;
		document.frmDeletePaybill.submit(); */
		
		var form = document.createElement("form");
		form.setAttribute("method", "POST");
		form.setAttribute("action", "ifms.htm?actionFlag=viewconsBillForDetailView");
		
		var inputBill = document.createElement("input");
		inputBill.setAttribute("type", "hidden");
		inputBill.setAttribute("name", "billid");
		inputBill.setAttribute("value", billNo);

		form.appendChild(inputBill);

		document.body.appendChild(form);
		form.submit();
		document.body.removeChild(form);
		
		showProgressbar("Please wait<br>Your Request is in progress ...");
		return true;
	}
}

//added by roshan for to login
function forwardConsBillDetails(){
	
	var len = '${size}';
	
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		
		if (document.getElementById("billid"+i).checked) {
			
			isChecked = true;
			var billNo=document.getElementById("billid"+i).value;
			var authNumber=document.getElementById("authNo"+i).value;
			//alert(authNumber);
			if(authNumber==""){
			alert("Please enter authorisation number.");
			return false;
				}
			break;

		}

	}
	if (!isChecked) {
		alert("Please select atleast one Bill ");
		return false;
	}

	else {
		var answer = confirm('Do you want to Forward the Consolidated Bill to Treasury?');
		if (answer) {
			
			/* var url="ifms.htm?actionFlag=fwdconsBillGenerated&billid="+billNo+"&authNumber="+authNumber;
			document.frmDeletePaybill.action=url;
			document.frmDeletePaybill.submit(); */
			
			var form = document.createElement("form");
			form.setAttribute("method", "POST");
			form.setAttribute("action", "ifms.htm?actionFlag=fwdconsBillGenerated");
			
			var inputBill = document.createElement("input");
			inputBill.setAttribute("type", "hidden");
			inputBill.setAttribute("name", "billid");
			inputBill.setAttribute("value", billNo);
			
			var inputAuth = document.createElement("input");
			inputBill.setAttribute("type", "hidden");
			inputBill.setAttribute("name", "authNumber");
			inputBill.setAttribute("value", authNumber);

			form.appendChild(inputBill);
			form.appendChild(inputAuth);

			document.body.appendChild(form);
			form.submit();
			document.body.removeChild(form);
			
			
			showProgressbar("Please wait<br>While deleting Consolidated Bill ...");
		} else {
			return false;
		}return true;
	}
	
	showProgressbar("Please Wait ");
}

function disableEnableBtn(status){
	//var status= document.getElementById("hdStatus"+id).value;
	if(status=='5'){
		document.getElementById("delete").disabled='disabled';
		document.getElementById("fwdConsoBill").disabled='disabled';
	}

	else if(status=='1'){
		document.getElementById("delete").disabled='disabled';
		document.getElementById("fwdConsoBill").disabled='disabled';
	}

	else if(status=='2'){
		document.getElementById("delete").disabled='';
		document.getElementById("fwdConsoBill").disabled='disabled';
	}

	else if(status=='4'){
		document.getElementById("delete").disabled='';
		document.getElementById("fwdConsoBill").disabled='disabled';
	}

	else{
		document.getElementById("delete").disabled='';
		document.getElementById("fwdConsoBill").disabled='';
	}
}

//end byr oshan

function forwardBillDataToBEAMS(){
	var len = '${size}';
	var billNo="";
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		if (document.getElementById("billid"+i).checked) {
			isChecked = true;
			billNo=document.getElementById("billid"+i).value;
			break;
		}
	}
	if (!isChecked) {
		alert("Please select atleast one Bill.");
		return false;
	}
	else{
			var answer = confirm (" Are You sure You want to Forward Bill to BEAMS?");
			if(answer)
			{	
				showProgressbar("Please wait...");
				/* document.frmReportingDDO.action="./hdiits.htm?actionFlag=forwardBillDataToBEAMS&billNo="+billNo;
				document.frmReportingDDO.submit(); */
				
				var form = document.createElement("form");
				form.setAttribute("method", "POST");
				form.setAttribute("action", "./hdiits.htm?actionFlag=forwardBillDataToBEAMS");
				
				var inputBill = document.createElement("input");
				inputBill.setAttribute("type", "hidden");
				inputBill.setAttribute("name", "billNo");
				inputBill.setAttribute("value", billNo);

				form.appendChild(inputBill);

				document.body.appendChild(form);
				form.submit();
				document.body.removeChild(form);
			}
		}
}

function openAuthSlipPDF(authNo)
{	
	var urlstring = "hrms.htm?actionFlag=getAuthSlip";
	var urlstyle = "height=600,width=1000,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	
	var form = document.createElement("form");
	form.setAttribute("method", "POST");
	form.setAttribute("action", urlstring);

	var inputAuthNo = document.createElement("input");
	inputAuthNo.setAttribute("type", "hidden");
	inputAuthNo.setAttribute("name", "authNo");
	inputAuthNo.setAttribute("value", authNo);

	form.appendChild(inputAuthNo);
	form.submit();
	window.open(urlstring, "viewAuthSlipPDF", urlstyle);
}
</script>

<script type="text/javascript">

var statusCode = '${statusCode}';

var authNo = '${authNo}';

var finalMsg = '${finalMsg}';

var finalMsgArr=new Array();
if(finalMsg != null && finalMsg != '')
{	
	finalMsg=finalMsg.replace("~","\n   ");
	finalMsg=finalMsg.replace("~","\n   ");
	finalMsg=finalMsg.replace("~","\n   ");
	finalMsg=finalMsg.replace("~","\n   ");
	finalMsg=finalMsg.replace("~","\n   ");
	alert(finalMsg);
	document.frmReportingDDO.action="./hdiits.htm?actionFlag=viewConsolidatedBill";
	document.frmReportingDDO.submit();
	//resetForm1();
}
else if(statusCode != null && statusCode != '' && statusCode == '00')
{
	alert("Bill is successfully authorized by BEAMS.\nAuthorization number is : "+authNo+"\nPlease note authorization number for further reference.");
	document.frmReportingDDO.action="./hdiits.htm?actionFlag=viewConsolidatedBill";
	document.frmReportingDDO.submit();
}
</script>
