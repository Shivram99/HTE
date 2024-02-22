
<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@page import="java.util.List"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dataList" value="${resValue.recordList}"></c:set>
<c:set var="userpostlist" value="${resValue.userpostlist}"></c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"></c:set>
<%--<c:set var="orgPostDtlList" value="${resValue.orgPostDtlList}"></c:set>--%>
<c:set var="orderList" value="${resultObj.resultValue.orderList}"></c:set>
<c:set var="datasize" value="${resValue.recordsize}"></c:set>

<c:set var="empName" value="${resValue.empName}"></c:set>
<c:set var="designation" value="${resValue.Designation}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = dataList.size();

pageContext.setAttribute("listSize",size);
%>
<html>
<head>
<style>
span.pagebanner {
display:none;
}

span.pagelinks{
display:none;
}
</style>

<script type="text/javascript" src="common/script/tabcontent.js"></script>


<script type="text/javascript">
	function EnterkeyPressed(e, form) {

		var whichCode = (window.Event) ? e.which : e.keyCode;
		if ((e.keyCode == 13)) {
			searchvalues();
		}
	}

	function GetPostfromOrder() {
		//clearPostList();

		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var url = '';
		var uri = '';
		url = uri + '&orderId=' + document.frmAdminCrtPost.order.value;
		var actionf = "GetPostfromOrder";
		uri = './hrms.htm?actionFlag=' + actionf;
		url = uri + url + "&editFlag=0";
		xmlHttp.onreadystatechange = GetPostsfromOrder;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}

	function GetPostsfromOrder() {
		if (xmlHttp.readyState == complete_state) {
			var post = document.getElementById("post");

			var XMLDoc = xmlHttp.responseXML.documentElement;
			var entries = XMLDoc.getElementsByTagName('post-mapping');
			var val = 0;
			var text = '';
			for ( var i = 0; i < entries.length; i++) {
				val = entries[i].childNodes[0].text;
				text = entries[i].childNodes[1].text;
				var y = document.createElement('option')
				y.value = val;
				y.text = text;

				try {
					post.add(y, null);
				} catch (ex) {
					post.add(y);
				}

			}
		}
	}

	function clearOrderList() {
		var v = document.getElementById("order").length;
		for (i = 1; i < v; i++) {
			lgth = document.getElementById("order").options.length - 1;
			document.getElementById("order").options[lgth] = null;
		}
	}

	function clearPostList() {
		var v = document.getElementById("post").length;
		for (i = 1; i < v; i++) {
			lgth = document.getElementById("post").options.length - 1;
			document.getElementById("post").options[lgth] = null;
		}
	}

	function getOrderList() {
		alert('function called');
		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var url;
		var uri = '';
		url = uri + '&cmbDept=' + document.forms[0].cmbDept.value;
		var actionf = "getOrderListByLocation";
		uri = './hrms.htm?actionFlag=' + actionf;
		url = uri + url;
		//alert(' ' + url);	  		  		  
		xmlHttp.onreadystatechange = deptChanged;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}

	function deptChanged() {
		if (xmlHttp.readyState == complete_state) {

			clearOrderList();

			var order = document.getElementById("order");

			var XMLDoc = xmlHttp.responseXML.documentElement;

			if (XMLDoc == null) {
				window.status = 'No Records Found.';
			} else {
				window.status = '';
				var orderList = XMLDoc.getElementsByTagName('order-mapping');
				for ( var i = 0; i < orderList.length; i++) {
					val = orderList[i].childNodes[0].text;
					text = orderList[i].childNodes[1].text;
					//     				    alert('Village val is:   ' + val + 'and text is:-   ' + text);
					var y = document.createElement('option')
					y.value = val;
					y.text = text;

					try {
						order.add(y, null);
					}

					catch (ex) {
						order.add(y);
					}
				}
			}
		}
	}
	function addNewEntry() {
		document.forms[0].action = 'hdiits.htm?actionFlag=addAdminPostDtl';
		document.forms[0].submit();
	}
	function test(reqId) {
		document.forms[0].action = "hdiits.htm?actionFlag=editAdminOrgPostData&reqId="
				+ reqId;
		document.forms[0].submit();
	}
	function editData() {
		var postId = document.frmAdminCrtPost.post.value;
		if (postId == "" || postId == null) {
			alert('Please Select Post to Edit!!!');
			return;
		} else {
			document.forms[0].action = "hdiits.htm?actionFlag=editAdminOrgPostData&reqId="
					+ postId;
			document.forms[0].submit();
		}
	}
	function deleteData() {
		var isChecked = false;
		for ( var i = 0; i < document.forms[0].deletedata.length; i++) {
			if (document.forms[0].deletedata[i].checked) {
				isChecked = true;
			}
		}
		if (isChecked) {

			var answer = confirm("Are you sure want to delete the selected data?");
			if (answer) {
				document.forms[0].action = 'hdiits.htm?actionFlag=deleteAdminOrgPostData';
				document.forms[0].submit();
			}
		} else {
			alert("Please select the checkbox");
		}
	}
	function searchData() {
		document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl';
		document.forms[0].submit();
	}
	function submitFormAuto() {
		if (chkValue()) {
			var empId = document.getElementById("Employee_ID_viewPostSearch").value;
			document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&empId=' + empId;
			document.forms[0].submit();
			//document.frmAdminCrtPostsearch.submit();
		}
		return true;
	}
	function chkValue() {
		var empId = document.getElementById("Employee_ID_viewPostSearch").value;
		if (empId == "") {
			alert("Please search the employee first");
			return false;
		} else {
			return true;
		}
	}
	function setStyle(x) {
		document.getElementById(x).style.background = "#ccccff";
	}
	function searchvalues() {
		var value = document.getElementById("value").value;
		var option_selected = document.getElementById("option_selected").value;
		if (option_selected == "Post") {
			document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&Post=' + value;
			document.forms[0].submit();
		}
		if (option_selected == "PsrNo") {
			if (check4number(value)) {
				document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&PsrNo=' + value;
				document.forms[0].submit();
			}
		}
		if (option_selected == "BillNo") {
			if (check4number(value)) {
				document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&BillNo=' + value;
				document.forms[0].submit();
			}
		}
		if (option_selected == "Dsgn") {
			document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&Dsgn=' + value;
			document.forms[0].submit();
		}
		if (option_selected == "")
			alert("Please Select Criteria For Search");
	}
	function check4number(n) {
		var flag = true;
		if (isNaN(n) == true) {
			flag = false;
			alert("This Value Is Not A Number. \nPlease Enter A Valid Field Value.");
		}
		if (n == "") {
			flag = false;
			alert("The Field is blank ");
		}
		return flag;
	}

	function searchFunction() {

		var FieldDept = document.getElementById('cmbFieldDept').value;
		var DdoDtl = document.getElementById('cmbDdoDtl').value;

		if (FieldDept == "-1") {
			alert('Please select a Field Department');
			document.getElementById("cmbFieldDept").focus();
			return false;
		}
		if (DdoDtl == "-1") {
			alert('Please select a DDO Office');
			document.getElementById("cmbDdoDtl").focus();
			return false;
		}

		var dsgnId = document.getElementById('designationCmb').value;
		var billGrpId = document.getElementById('billCmb').value;

		if (dsgnId > 0 && billGrpId > 0) {
			var url = "./ifms.htm?actionFlag=searchDeletePostDtls&dsgnId=" + dsgnId
					+ "&billGrpId=" + billGrpId + "&FieldDept=" + FieldDept
					+ "&DdoDtl=" + DdoDtl;
			document.frmAdminCrtPost.action = url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
		} else if (dsgnId > 0) {

			var url = "./ifms.htm?actionFlag=searchDeletePostDtls&dsgnId=" + dsgnId
					+ "&FieldDept=" + FieldDept + "&DdoDtl=" + DdoDtl;
			document.frmAdminCrtPost.action = url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;

		} else if (billGrpId > 0) {
			var url = "./ifms.htm?actionFlag=searchDeletePostDtls&billGrpId="
					+ billGrpId + "&FieldDept=" + FieldDept + "&DdoDtl="
					+ DdoDtl;
			document.frmAdminCrtPost.action = url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;

		} else {
			var url = "./ifms.htm?actionFlag=searchDeletePostDtls&billGrpId=&FieldDept="
					+ FieldDept + "&DdoDtl=" + DdoDtl;
			document.frmAdminCrtPost.action = url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;

		}
	}
	function backtoSearchPage() {
		self.location.href = "ifms.htm?actionFlag=PostDeletion&elementId=90002642";
	}
	function showPostDtls() {
		var FieldDept = document.getElementById('cmbFieldDept').value;
		var DdoDtl = document.getElementById('cmbDdoDtl').value;

		if (FieldDept != "-1" && DdoDtl != "-1") {
			self.location.href = "ifms.htm?actionFlag=showDeletePostDtl&elementId=90002642&FD="
					+ FieldDept + "&DDO=" + DdoDtl;
		}
	}


	function DisplayOrderDate(i) {
	
		var xmlHttp = null;
		var orderId = document.getElementById("orderCmb"+i).value;
         if(orderId==-1)
        	 document.getElementById("OrderDate"+i).value ="";
		var retValue = true;
		if (orderId == "") {
			alert("Please search the Order Name");
			retValue = false;
		} else {

			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				// Internet Explorer    
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						retValue = false;
					}
				}
			}
			var url = "hrms.htm?actionFlag=getOrderDate&orderId=" + orderId
					+ "";
			xmlHttp.onreadystatechange = function() {

				if (xmlHttp.readyState == 4) {

					if (xmlHttp.status == 200) {

						var oderDate;

						var XMLDocForAjax = xmlHttp.responseXML.documentElement;

						var orderDate = XMLDocForAjax
								.getElementsByTagName('getOldOrderDate');

						if (orderDate.length != 0) {

							orderDate = orderDate[0].childNodes[0].firstChild.nodeValue;

							document.getElementById("OrderDate"+i).value = orderDate;
						}
					}
				}
			}

			xmlHttp.open("POST", encodeURI(url), false);
			xmlHttp.setRequestHeader("Content-Type",
					"text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
			return retValue;
		}
	}

	function checkboxfunction()
	{     
	
		
		var size = document.getElementById('datavalue').value;
		 showProgressbar("Please wait...");
		if(document.getElementById("mainchkbx").checked==true)
		{
		for ( var i = 0; i < size; i++) 
	    {
		document.getElementById("chkbx" + i).checked=true;
		}
		}
		else if(document.getElementById("mainchkbx").checked==false)
			for ( var j = 0; j < size; j++) 
		    {
			document.getElementById("chkbx" + j).checked=false;
			}
	     
		 hideProgressbar("Please wait...");
	}

	function convertFunction() {
		

		var stringfor1 = null;
		var totalString = null;
		var size = document.getElementById('datavalue').value;
		 var remark;
		  var isChecked=false;
		for ( var i = 0; i < size; i++) {
			if (document.getElementById("chkbx" + i).checked == true) {
				  isChecked=true;   
				var postId = document.getElementById("chkbx" + i).value;
				var postType = document.getElementById("postType" + i).innerHTML;
                var orderid=document.getElementById("orderCmb" + i).value;
var rem=document.getElementById("Remarks" + i).value;

                 if((document.getElementById("Remarks" + i)!=null)&&(document.getElementById("Remarks" + i).value.length!=0))
                	 remark=document.getElementById("Remarks" + i).value;
                 else{
					remark="-";
				     
                     }
                 
                
				stringfor1 = postId + "~" + postType+"~"+orderid+"~"+remark;
				totalString= totalString+","+stringfor1;
			}

		}
		
		if(isChecked){
		var uri = 'ifms.htm?actionFlag=saveDeletePostDtls&totalString=' + totalString;

		var url="postId="+postId;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getCount(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
     
	}
		else
		{
			alert("Please select at least 1 post");
			hideProgressbar("Please Wait......");
				return 0;
		}
		  hideProgressbar("Please Wait......");
	}
	function getCount(myAjax){
		XMLDoc = myAjax.responseXML.documentElement;
		var tempCount = XMLDoc.getElementsByTagName('tempCount');
		var tCount = tempCount[0].firstChild.nodeValue;

		alert(tCount+ ' post has been deleted' );
var url="./ifms.htm?actionFlag=PostDeletion";
		
		document.frmAdminCrtPost.action = url ;
		document.frmAdminCrtPost.submit();

		
	}
	

	//document.frmAdminCrtPost.action = uri ;
	//document.frmAdminCrtPost.submit();
	//	var url="./ifms.htm?actionFlag=saveChangePostDtls&totalString="+totalString;
</script>
</head>
<body>
<c:set value="0" var="i"></c:set>
<hdiits:form name="frmAdminCrtPost" validate="true" method="post"
	encType="multipart/form-data">
	
	<br />
	<br />
	<fieldset class="tabstyle"><legend>Search Post
	Details</legend>
	<table width="100%">
		<tr>
			<td width="8%"></td>
			<td width="10%"><fmt:message key="CMN.FIELDDEPT"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="30%"><select name="cmbFieldDept" id="cmbFieldDept"
				style="width: 350px" disabled="disabled">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="FieldDept" items="${resValue.lLstFieldDept}">
					<c:choose>
						<c:when test="${resValue.FieldDeptCode == FieldDept.id}">
							<option value="${FieldDept.id}" selected="selected"><c:out
								value="${FieldDept.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${FieldDept.id}"><c:out
								value="${FieldDept.desc}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
			<td width="15%">DDO</td>
			<td width="35%"><select name="cmbDdoDtl" id="cmbDdoDtl"
				onchange="showPostDtls();" style="width: 350px" disabled="disabled">
				<c:if test="${resValue.lLstDdo == null}">
					<option value="-1"><fmt:message
						key="COMMON.DROPDOWN.SELECT" /></option>
				</c:if>
				<c:forEach var="DDO" items="${resValue.lLstDdo}">
					<c:choose>
						<c:when test="${resValue.DDOCode == DDO.id}">
							<option value="${DDO.id}" selected="selected"><c:out
								value="${DDO.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${DDO.id}"><c:out value="${DDO.desc}" />
							${resValue.DDOCode}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td></td>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.designation" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:select name="designationCmb"
				id="designationCmb" captionid="admin.designation"
				bundle="${adminCreatePostLabel}" mandatory="false"
				validation="sel.isrequired">
				<hdiits:option value="">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${designation}" var="desi">
					<option value="<c:out value="${desi.dsgnCode}"/>"><c:out
						value="${desi.dsgnName}" /></option>
				</c:forEach>
			</hdiits:select></td>

			<td class="fieldLabel">Scheme Code (<hdiits:caption
				captionid="admin.bill" bundle="${adminCreatePostLabel}" />)</td>
			<td class="fieldLabel"><select name="billCmb" id="billCmb"
				captionid="admin.bill" style="width: 350px"
				bundle="${adminCreatePostLabel}" mandatory="false"
				validation="sel.isrequired">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach items="${billList}" var="billList">
					<option value="<c:out value="${billList.dcpsDdoBillGroupId}"/>"
						title="${billList.dcpsDdoBillDescription}"><c:out
						value="${billList.dcpsDdoSchemeCode} (${billList.dcpsDdoBillDescription})" /></option>
				</c:forEach>
			</select></td>
		</tr>

	</table>
	<br>
	<center><hdiits:button name="Search" id="Search" type="button"
		value="Search" onclick="searchFunction();" /> <hdiits:button
		name="btnBack" id="btnBack" type="button" value="Back"
		onclick="backtoSearchPage();" /></center>
	<br>
	</fieldset>
	<fieldset class="tabstyle"><legend>Delete Post
	Details</legend>
	<div id="combo" style="overflow: auto;height:350px;">
	<table class="datatable">
	<thead>
	<tr>
	<th class="datatableheader sortable" colspan="4">&nbsp;
	</th>
<th class="datatableheader sortable" colspan="4">
Current Details 
</th>
<th class="datatableheader sortable" colspan="3">
GR Details/Remarks for Deletion of Post(s)
</th>

</tr>
	<tr>
	<th class="datatableheader sortable">
<input type="checkbox" id="mainchkbx" value="" onclick="checkboxfunction()" />
</th>
<th class="datatableheader sortable">
Sr. No.
</th>
<th class="datatableheader">Designation</th>
<th class="datatableheader">Post Name</th>

<th class="datatableheader sortable">
Post Type
</th>
<th class="datatableheader sortable">
Post Start Date
</th>
<th class="datatableheader sortable">
Post End Date
</th>

<!--<th class="datatableheader sortable">
Bill Group
</th>
--><th class="datatableheader sortable">
GR No./GR Date
</th>
<th class="datatableheader sortable">
GR No.
</th>
<th class="datatableheader sortable">
GR Date
</th>
<th class="datatableheader sortable">
Remarks
</th>

</tr>
</thead>
<tbody>
<c:set var="i" value="0"/>
<c:forEach items="${dataList}" var="row">
<c:choose>
<c:when test="${i%2==0}">
<tr class="odd">
</c:when>
<c:otherwise>
<tr class="even">
</c:otherwise>
</c:choose>

<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<input type="checkbox" id="chkbx${i}" value="${row.postId}" />
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<c:out value="${i+1}"></c:out>
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
${row.dsgnname}
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
		${row.postname}
</td>


<td class="tablecelltext" style="text-align: center; font-size: 12px;">
	<label id="postType${i}">${row.postType}</label>
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<label></label>
			<input type="hidden" id="startDate${i}" value="${row.startDate}" />
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
	${row.endDate}
</td>


<!--<td class="tablecelltext" style="text-align: center; font-size: 12px;">
   	${row.billNo}
</td>
--><td class="tablecelltext" style="text-align: center; font-size: 12px;">
		${row.empFname}/&nbsp${row.empMname}
</td>
<!--<td class="tablecelltext" style="text-align: center; font-size: 12px;">


--><td class="tablecelltext" style="text-align: center; font-size: 12px;">
<select name="orderCmb" id="orderCmb${i}" style="width: 230px"
				onchange="DisplayOrderDate('${i}');">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach items="${resultObj.resultValue.orderList}" var="orderList">
					<option value="<c:out value="${orderList.orderId}"/>"><c:out
						value="${orderList.orderName}" /></option>

				</c:forEach>
			</select>

</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<input type="text" size="10" name = "OrderDate" id="OrderDate${i}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="" disabled="disabled"/> 
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
 	<input type="text" size="10" name ="Remarks" id="Remarks${i}" />         

</td>

</tr>
<c:set var="i" value="${i+1}"/>
</c:forEach>
</tbody>

	
		</table>
	
	<!--<display:table pagesize="1000" name="${dataList}" id="row"
		export="false" requestURI="hrms.htm?actionFlag=showDeletePostDtl"
		requestURIcontext="false"
		decorator="com.tcs.sgv.user.service.AdminOrgPostDtlDecorator">

		<display:column style="text-align: center;font-size:12px;"
			class="tablecelltext"  headerClass="datatableheader"
			sortable="true">
			<input type="checkbox" id="chkbx${i}" value="${row.postId}" />
		</display:column>


		<display:column style="text-align: center;font-size:12px;"
			class="tablecelltext" title="Sr. No." headerClass="datatableheader"
			sortable="true" value="${i+1}">
			<c:out value="${i}"></c:out>
		</display:column>

		<c:set value="${row.postId}" var="otherIdForLink" />
		


		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post Name" headerClass="datatableheader">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.postname}
		</display:column>


		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post Type" headerClass="datatableheader"
			sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
			<label id="postType${i}">${row.postType}</label>
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post Start Date"
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
			<label>${row.startDate}</label>
			<input type="hidden" id="startDate${i}" value="${row.startDate}" />
		</display:column>
		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post End Date"
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.endDate}
		</display:column>

		<display:column class="tablecelltext" title="Designation"
			headerClass="datatableheader"
			style="text-align: LEFT;font-size:12px;" sortable="true">${row.dsgnname}</display:column>

		<display:column class="tablecelltext" title="Bill Group Name"
			headerClass="datatableheader"
			style="text-align: LEFT;font-size:12px;" sortable="true">
	   	${row.billNo}
	   </display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="GR No." headerClass="datatableheader"
			sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.empFname}
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="GR Date" headerClass="datatableheader"
			sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.empMname}
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="New GR No."
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>

			<select name="orderCmb" id="orderCmb${i}" style="width: 230px"
				onchange="DisplayOrderDate('${i}');">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach items="${resultObj.resultValue.orderList}" var="orderList">
					<option value="<c:out value="${orderList.orderId}"/>"><c:out
						value="${orderList.orderName}" /></option>

				</c:forEach>
			</select>
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title=" New GR Date."
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>

			<input type="text" size="10" name = "OrderDate" id="OrderDate${i}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="" disabled="disabled"/> 
												
		</display:column>
              
              
           <display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title=" Remarks."
			headerClass="datatableheader" sortable="true">   
              
          	<input type="text" size="10" name ="Remarks" id="Remarks${i}" />         

           </display:column>

		<display:setProperty name="export.pdf" value="false" />
		<c:set var="i" value="${i+1}"></c:set>

	</display:table>
	--></div>
	</fieldset>
	<table width="100%">
		<tr width="100%">
			<td align="center"><%--	<hdiits:button name="addNewEntry_button" captionid="admin.AddEntry" bundle="${adminCreatePostLabel}" onclick="addNewEntry()" type="button"/> --%>
			<%--	<hdiits:button captionid="admin.Delete" bundle="${adminCreatePostLabel}" onclick="deleteData()" name="cmdDel2" type="button"/> --%>
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when
			test="${listSize eq 1 && empName != null && not empty empName }">
			<script>
	document.getElementById("otherLink${otherIdForLink}").click();
</script>
		</c:when>
	</c:choose>
	<input type="hidden" value="${datasize}" id="datavalue">
	<center>
	<c:if test="${datasize gt '0'}">
	<hdiits:button name="Delete" id="Delete" type="button"
		value="Delete" onclick="convertFunction();" />
		</c:if>
		</center>
</hdiits:form>





</body>
</html>
<%
}
catch(Exception e) {e.printStackTrace();}
%>

<ajax:select
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDdoListFromFieldDept"
	source="cmbFieldDept" target="cmbDdoDtl"
	parameters="FieldDeptCode={cmbFieldDept}"></ajax:select>