<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption" scope="request" />
<fmt:setBundle basename="resources.hr.roster.RosterAlertMsges" var="alertMsges" scope="request" />

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/-calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/hrms/hr/rosterScript/rosterMst.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resValue" value="${result.resultValue}"></c:set>
<c:set var="rosterdtl" value="${resValue.rosterlist}"></c:set>
<c:set var="empcategory" value="${resValue.category}"></c:set>
<c:set var="reqId" value="${resValue.reqId}"></c:set>
<c:set var="previousList" value="${resValue.previousList}"></c:set>
<c:set var="subdsgn" value="${resValue.subdsgn}"></c:set>
<c:set var="designationId" value="${resValue.designationId}"></c:set>


<c:set var="SCPer" value="${resValue.SCPer}"></c:set>
<c:set var="STPer" value="${resValue.STPer}"></c:set>
<c:set var="SEBCPer" value="${resValue.SEBCPer}"></c:set>
<c:set var="PHPer" value="${resValue.PHPer}"></c:set>
<c:set var="xmlKey" value="${resValue.xmlKey}"></c:set>

<style>    
 		.odd{background-color: #CCCCCC;} 
 		.even{background-color: #999999;} 
 		.preOdd{background-color: #CCFFCC;} 
 		.preEven{background-color: #99FFCC;} 
</style>
<script type="text/javascript">
var GlobaltorwId='';
 
	function empSearch(from)
		{
			
			for(var i=0; i<from.length; i++)
			{
				empArray[i] = from[i].split("~"); 
			}
			
		 
			var single = empArray[0];
			
			if(!(validateEmployee(single[4].trim()))){
				alert("<fmt:message key="HRMS.EmployeePresent" bundle="${alertMsges}" />");
				return false;
			}
			
			if((single[14]=="0")){
				alert("<fmt:message key="HRMS.CatNotExist" bundle="${alertMsges}" />");
				return false;
			}
			
			document.getElementById(document.getElementById('hidUserId').value).value= single[2].trim();
			
			
			document.getElementById(document.getElementById('empSearchName').value).value = single[1];
			
			document.getElementById(document.getElementById('deptname').value).innerHTML = single[13];
			
			document.getElementById(document.getElementById('hidcatId').value).value=single[14];
			document.getElementById(document.getElementById('doj').value).value=single[12];
			
			//End	
			cntSC=0;
			cntST=0;
			cntSEBC=0;
			cntPH=0;
			
			for(var j=1;j<=totalRow;j++)
			{
					var scVal='sc'+j;
					var stVal='st'+j;
					var sebcVal='sebc'+j
					var phVal='ph'+j;
					document.getElementById(scVal).innerHTML="";
					document.getElementById(stVal).innerHTML="";
					document.getElementById(sebcVal).innerHTML="";
					document.getElementById(phVal).innerHTML="";
					
					document.getElementById('scsur').innerHTML ="";
				    document.getElementById('stsur').innerHTML = "";
					document.getElementById('sebcsur').innerHTML = "";
					document.getElementById('phsur').innerHTML = "";
					
			}
			cntSC=0;
			cntST=0;
			cntSEBC=0;
			cntPH=0;
			for(var i=1;i<=totalRow;i++)
			{
				
				srId=i;
				var name="empName"+i;
				
				var boxVal=document.getElementById(name).value;
				
				
				var len=boxVal.length;
			
				if(len!=0)
				{
					presetCountForSurplus();
				}
			}
		} 
function writeRemarks(trowId)
{		
		GlobaltorwId=trowId;
		 
		document.getElementById('textData').value=document.getElementById('text_'+trowId).value;
			
		document.getElementById('rwFlag').value=document.getElementById('rwFlag_'+trowId).value;
		win=window.open("hrms.htm?viewName=rosterWriteRemark","WriteRemarks","width=650,height=250,scrollbars=yes,toolbar=no,status=yes,menubar=no,resizable=no,top=40,left=100,dependent=yes");
		if(win.opener == null) {win.opener = self;}	
		if(window.complete_state){win.focus();}
		try {
			win.focus();
		}catch(ex){}
	
}
function SetData()
{
	document.getElementById('text_'+GlobaltorwId).value=document.getElementById('textData').value;
}
function formValidate(){
	

	for(var i=previousSize+1;i<=totalRow;i++)
	{
		var empName='empName'+i;
		if(document.getElementById(empName).value=="")
		{
		 	alert("<fmt:message key="HRMS.employeeReq" bundle="${alertMsges}" />");      
			return false;
		}
		var Categorysel='Categorysel'+i;
		if(document.getElementById(Categorysel).value=="-1")
		{
		 	alert("<fmt:message key="HRMS.calCategoryReq" bundle="${alertMsges}" />");      
			return false;
		}
	}
	return true;
}
function SearchEmp(form){
	
	//document.getElementById('search').value='search'+srId;
	var n=form.name;
	srId=n.substring(6,n.length);

	document.getElementById('empSearchName').value='empName'+srId;
	document.getElementById('deptname').value='lblDept'+srId;
	document.getElementById('hidUserId').value='userId'+srId;
	document.getElementById('doj').value='lblDoj'+srId;
	document.getElementById('hidcatId').value='catId'+srId;
	//var href='./hrms.htm?actionFlag=getRosterAllEmpSearch';
	
	var href="./hrms.htm?actionFlag=getRosterEmpSearchSelData&multiple=false&code=ROSTER&designationId="+document.getElementById('designationId').value;
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}
		
</script>

<style>

.bubble_tooltip{
	width:147px;
	position:absolute;
	display:none;
}
.bubble_tooltip .bubble_top{
	background-image: url('images/bubble_top.gif');
	background-repeat:no-repeat;
	height:16px;	
}
.bubble_tooltip .bubble_middle{
	background-image: url('images/bubble_middle.gif');
	background-repeat:repeat-y;	
	background-position:bottom left;
	padding-left:7px;
	padding-right:7px;
}
.bubble_tooltip .bubble_middle span{
	position:relative;
	top:-8px;
	font-family: Trebuchet MS, Lucida Sans Unicode, Arial, sans-serif;
	font-size:15px;
}
.bubble_tooltip .bubble_bottom{
	background-image: url('images/bubble_bottom.gif');
	background-repeat:no-repeat;
	background-repeat:no-repeat;	
	height:44px;
	position:relative;
	top:-6px;
}
</style>

<hdiits:form name="Roster" validate="true" method="POST"
	action="./hrms.htm?actionFlag=submitAllocationDlts">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="Hr_recrallocation" bundle="${caption}" /> </a></li>
	</ul>
	</div>


	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0"><hdiits:hidden
		name="empSearchName" id="empSearchName" /> <hdiits:hidden
		name="deptname" id="deptname" /> <hdiits:hidden name="doj" id="doj" />
	<hdiits:hidden name="hidUserId" id="hidUserId" /> <hdiits:hidden
		name="prevacancyNo" id="prevacancyNo" /> <hdiits:hidden
		name="preValPresent" id="preValPresent" /> <hdiits:hidden
		name="hidcatId" id="hidcatId" /> <hdiits:hidden name="search"
		id="search" /> <hdiits:hidden name="totalLength" id="totalLength" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />

	<div style="width: 100%;height: 100%;overflow:scroll">
	
	
	<table border="3" bordercolor="black" class="datatable" id="rostertb"	style="width: 100% ;height: 100%">
		<%-- First row for header--%>
		<tr class="datatableheader" style="text-align: center" >
			<td valign="bottom" colspan="4" rowspan="1"><hdiits:caption
				captionid="Hr_resservedseat" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_lastrecruitment" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_serialno" bundle="${caption}" /></td>
			<td align="center" valign="bottom">${SCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${STPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${SEBCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${PHPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_nameofseats" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2" style="width:5%"><hdiits:caption
				captionid="Hr_nameofemployee" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_dateofjoin" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_nameofempcategory" bundle="${caption}" /></td>
			<td align="center" valign="bottom">${SCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${STPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${SEBCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${PHPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" rowspan="2" ><hdiits:caption
				captionid="Hr_transfer" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_retired" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_promotion" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_specremarks" bundle="${caption}" /></td>
		</tr>


	
		<tr class="datatableheader" >
			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" /></td>

			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" /></td>

			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" /></td>
		</tr>
		<!-- previous Data -->

		<c:forEach var="previousList" items="${previousList}">
			<script>
			previousSize=previousSize+1;
		</script>
		<div id="tooltip_main${previousList.serialNo}" class="bubble_tooltip">
				<div class="bubble_top"><span></span></div>
				<div class="bubble_middle"><span id="tooltip${previousList.serialNo}"></span></div>
				<div class="bubble_bottom"></div>
		</div>
			<tr bgcolor="WHITE">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td id="${previousList.serialNo}" onmouseover= "xstooltip_show(event,'previouslist','tooltip_main${previousList.serialNo}','tooltip${previousList.serialNo}', '${previousList.serialNo}')" onmouseout="xstooltip_hide('tooltip_main${previousList.serialNo}','tooltip${previousList.serialNo}')"><hdiits:hidden name="serial${previousList.serialNo}"
					default="${previousList.serialNo}" /><c:out
					value="${previousList.serialNo}" /></td>
				<td><hdiits:hidden name="scval${previousList.serialNo}"
					default="${previousList.scVal}" />&nbsp;<LABEL
					id="lscval${previousList.serialNo}" />
					<fmt:formatNumber type="number" value="${previousList.scVal}" maxFractionDigits="2" var="SCVale"/>
					<c:out value="${SCVale}" /></td>
				<td><hdiits:hidden name="stval${previousList.serialNo}"
					default="${previousList.stVal}" />&nbsp;<LABEL
					id="lstval${previousList.serialNo}" />
					<fmt:formatNumber type="number" value="${previousList.stVal}" maxFractionDigits="2" var="STVale"/>
					<c:out value="${STVale}" /></td>
				<td><hdiits:hidden name="sebcval${previousList.serialNo}"
					default="${previousList.sebcVal}" />&nbsp;<LABEL
					id="lsebcval${previousList.serialNo}" /><c:out
					value="${previousList.sebcVal}" /></td>
				<td><hdiits:hidden name="phval${previousList.serialNo}"
					default="${previousList.phVal}" />&nbsp;<LABEL
					id="lphval${previousList.serialNo}" /><c:out
					value="${previousList.phVal}" /></td>


				<td><hdiits:hidden name="Categorysel${previousList.serialNo}"
					id="Categorysel${previousList.serialNo}"
					default="${previousList.plannedCatId}" />&nbsp;<label id="plan_category${previousList.serialNo}"> <c:out
					value="${previousList.plannedCat}" /></label></td>
				<td><hdiits:hidden name="empName${previousList.serialNo}"
					id="empName${previousList.serialNo}"
					default="${previousList.empName}" /><hdiits:hidden
					name="userId${previousList.serialNo}"
					id="userId${previousList.serialNo}"
					default="${previousList.userId}" /><c:out
					value="${previousList.empName}" /></td>
				<td id="joindate${previousList.serialNo}">
					<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss" value="${previousList.joinDate}"/>
					<fmt:formatDate value="${bday}"  pattern="MM/dd/yyyy"/></td>
				<td id="category${previousList.serialNo}">&nbsp;<label id="lblDept${previousList.serialNo}"><c:out
					value="${previousList.actualCat}" /></label> <hdiits:hidden
					name="catId${previousList.serialNo}"
					id="catId${previousList.serialNo}"
					default="${previousList.actualCatId}" /></td>

				<td id="sc${previousList.serialNo}">&nbsp;<label id="sc${previousList.serialNo}"></label> <hdiits:hidden
					name="hidsc${previousList.serialNo}"
					id="hidsc${previousList.serialNo}" default="0" /></td>
				<td id="sc${previousList.serialNo}">&nbsp; <label id="st${previousList.serialNo}"></label><hdiits:hidden
					name="hidst${previousList.serialNo}"
					id="hidst${previousList.serialNo}" default="0" /></td>
				<td id="sc${previousList.serialNo}">&nbsp;<label id="sebc${previousList.serialNo}"></label><hdiits:hidden
					name="hidsebc${previousList.serialNo}"
					id="hidsebc${previousList.serialNo}" default="0" /></td>
				<td id="sc${previousList.serialNo}">&nbsp; <label id="ph${previousList.serialNo}"></label><hdiits:hidden
					name="hidph${previousList.serialNo}"
					id="hidph${previousList.serialNo}" default="0" /></td>

				<td> &nbsp;</td>
				<td>
					<a href="javascript:void('remarks');" onclick=javascript:writeRemarks('row${previousList.serialNo}')><hdiits:caption captionid="HRMS.ReadMsg" bundle="${caption}"/></a>
					<INPUT type='textarea' name='remarks' id='text_row${previousList.serialNo}' value='${previousList.remarks}' style="display:none"  maxlength="4s000"/>
					<input type="hidden" name='rwFlag_row${previousList.serialNo}' value="R" id='rwFlag_row${previousList.serialNo}' />
				</td>
			</tr>

		</c:forEach>

		<!-- end -->
		<%--  	Row Data started	--%>

		<c:forEach var="rlist" items="${resValue.rosterlist}">
			<script>
			
			counterRoster=counterRoster+1;
			
		</script>
		
		<div id="tooltip_main${rlist.serialNo}" class="bubble_tooltip">
				<div class="bubble_top"><span></span></div>
				<div class="bubble_middle"><span id="tooltip${rlist.serialNo}"></span></div>
				<div class="bubble_bottom"></div>
		</div>
			<tr id="row${rlist.serialNo}">
				
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td id="${rlist.serialNo}" onmouseover= "xstooltip_show(event,'rlist','tooltip_main${rlist.serialNo}','tooltip${rlist.serialNo}', '${rlist.serialNo}')" onmouseout="xstooltip_hide('tooltip_main${rlist.serialNo}','tooltip${rlist.serialNo}')">
					<hdiits:hidden name="serial${rlist.serialNo}"
					default="${rlist.serialNo}" /><c:out value="${rlist.serialNo}" /></td>
				<td id="lscval${rlist.serialNo}"><hdiits:hidden name="scval${rlist.serialNo}"
					default="${rlist.scVal}" />&nbsp;<LABEL id="lscval${rlist.serialNo}" /><c:out
					value="${rlist.scVal}" /></td>
				<td id="lscval${rlist.serialNo}">
					<hdiits:hidden name="stval${rlist.serialNo}"
					default="${rlist.stVal}" />&nbsp;<LABEL id="lstval${rlist.serialNo}" /><c:out
					value="${rlist.stVal}" /></td>
				<td id="lscval${rlist.serialNo}">
					<hdiits:hidden name="sebcval${rlist.serialNo}"
					default="${rlist.sebcVal}" />&nbsp;<LABEL id="lsebcval${rlist.serialNo}" /><c:out
					value="${rlist.sebcVal}" /></td>
				<td id="lscval${rlist.serialNo}">
					<hdiits:hidden name="phval${rlist.serialNo}"
					default="${rlist.phVal}" />&nbsp;<LABEL id="lphval${rlist.serialNo}" /><c:out
					value="${rlist.phVal}" /></td>


				<td><hdiits:select name="Categorysel${rlist.serialNo}"
					id="Categorysel${rlist.serialNo}" captionid="Category"
					onchange="enableSearch(this)">
					<hdiits:option value="-1">
							-----Select-------
						</hdiits:option>
					<c:forEach var="c" items="${empcategory}">
						<hdiits:option value="${c.lookupId}">
							<c:out value="${c.lookupDesc}" />
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>


				<td style="display: none"></td>

				<td nowrap="nowrap"><hdiits:text name="empName${rlist.serialNo}" readonly="true" 
					id="empName${rlist.serialNo}"  /><img  src="images/search_icon.gif" name="Search${rlist.serialNo}"  id="Search${rlist.serialNo}" tooltip="Click Here To Search Employee" onclick="SearchEmp(this)" style="display:none" />
					<img src="images/disable_search_icon.GIF" name="disSearch${rlist.serialNo}" id="disSearch${rlist.serialNo}" tooltip="" onclick="" /> 
					
				<hdiits:hidden name="userId${rlist.serialNo}" id="userId${rlist.serialNo}" /></td>
				<td id="joindate1${rlist.serialNo}"><input type="text" size="6"  readonly="readonly" id="lblDoj${rlist.serialNo}"
					name="lblDoj${rlist.serialNo}" /></td>
				<td id="category${rlist.serialNo}">&nbsp;<label for="empName${rlist.serialNo}"
					id="lblDept${rlist.serialNo}"></label> <hdiits:hidden
					name="catId${rlist.serialNo}" id="catId${rlist.serialNo}" /> <hdiits:hidden
					name="lblDateOfJoin${rlist.serialNo}"
					id="lblDateOfJoin${rlist.serialNo}" /></td>
				<td id="sc${rlist.serialNo}">
					&nbsp;<label id="sc${rlist.serialNo}"></label> <hdiits:hidden
					name="hidsc${rlist.serialNo}" id="hidsc${rlist.serialNo}"
					default="0" /></td>
				<td id="st${rlist.serialNo}">
					&nbsp; <label id="st${rlist.serialNo}"></label><hdiits:hidden
					name="hidst${rlist.serialNo}" id="hidst${rlist.serialNo}"
					default="0" /></td>
				<td id="sebc${rlist.serialNo}">
					&nbsp; <label id="sebc${rlist.serialNo}"></label><hdiits:hidden
					name="hidsebc${rlist.serialNo}" id="hidsebc${rlist.serialNo}"
					default="0" /></td>
				<td id="ph${rlist.serialNo}">
					&nbsp; <label id="ph${rlist.serialNo}"></label><hdiits:hidden
					name="hidph${rlist.serialNo}" id="hidph${rlist.serialNo}"
					default="0" /></td>

				<td>&nbsp;</td> 
				<td>
					<a href="javascript:void('remarks');" onclick=javascript:writeRemarks('row${rlist.serialNo}')><<hdiits:caption captionid="HRMS.WriteMsg" bundle="${caption}"/></a>
					<INPUT type='textarea' name='remarks' id='text_row${rlist.serialNo}' value="" style="display:none"  maxlength="4s000"/>
					<input type="hidden" name='rwFlag_row${rlist.serialNo}' value="W" id='rwFlag_row${rlist.serialNo}' />
				</td>

			</tr>
			<script type="text/javascript">
			totalLength=totalLength+1;
		</script>
		</c:forEach>

		<%--  Last row for the surplus--%>

		<script type="text/javascript">
			document.getElementById('totalLength').value = totalLength;
		</script>



		<tr  class="datatableheader">
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;<LABEL id="prescsur" />&nbsp;</td>
			<td>&nbsp;<LABEL id="prestsur" />&nbsp;</td>
			<td>&nbsp;<LABEL id="presebcsur" />&nbsp;</td>
			<td>&nbsp;<LABEL id="prephsur" />&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><LABEL id="scsur" />&nbsp;</td>
			<td><LABEL id="stsur" />&nbsp;</td>
			<td><LABEL id="sebcsur" />&nbsp;</td>
			<td><LABEL id="phsur" />&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
		</tr>


		<script>
		document.getElementById('prevacancyNo').value=previousSize;
		if(previousSize!=0)
		{
			
			document.getElementById('preValPresent').value="true";
		}else
		{
			
			document.getElementById('preValPresent').value="false";
		}
		
		for(var i=1;i<=previousSize;i++)
		{
			srId=i;

			
			presetCountForSurplus();
		}
		
		var table = document.getElementById('rostertb');   
   		var rows = table.getElementsByTagName("tr");   
   		
		for(i = previousSize+2; i < rows.length-1; i++){           
			 
    		 if(i % 2 == 0){ 
    			   rows[i].className = "even"; 
   			  }else{ 
   				    rows[i].className = "odd"; 
   				  }       

   		} 
		
	
		</script>
	</table>
	</div>
	<center><hdiits:button name="sub" type="button" id="sub"  value="Submit" onclick="submitDtls()" />
		<hdiits:button name="Close" type="button" id="Close" value="Close" onclick="closewindow()" />

	</center>


	</div>
	</div>
	<!-- Hidden Parameters -->
	<hdiits:hidden name="vacancyNo" id="vacancyNo" />
	<hdiits:hidden name="reqId" id="reqId" default="${reqId}" />
	<hdiits:hidden name="flag" id="flag" default="A" />
	<hdiits:hidden name="textData" id="textData" default="" />
	<hdiits:hidden name="rwFlag" id="rwFlag" default="" />
	<hdiits:hidden name="xmlKey" id="xmlKey" default="${xmlKey}" />
	<hdiits:hidden name="subdsgn" id="subdsgn" default="${subdsgn}" />
	<hdiits:hidden name="designationId" id="designationId" default="${designationId}" />
	
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script type="text/javascript">		

	document.getElementById('vacancyNo').value=counterRoster+previousSize;
		totalRow=document.getElementById('vacancyNo').value;
		initializetabcontent("maintab");

</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
