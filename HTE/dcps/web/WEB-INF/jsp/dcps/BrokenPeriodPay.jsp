<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/BrokenPeriod.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<!-- Added By Tejashree -->
<c:set var="currMonthId" value="${resValue.CURR_MONTH_ID}"></c:set>
<c:set var="currYearId" value="${resValue.CURR_YEAR_ID}"></c:set>
<!--  Ended By Tejashree-->
<c:set var="BrokenPeriodPayListSize"
	value="${resValue.BrokenPeriodPayListSize}"></c:set>
<c:set var="DataForDisplayList" value="${resValue.DataForDisplayList}"></c:set>
<c:set var="allowMonthGpf" value="${resValue.allowMonthGpf}"></c:set>
<c:set var="allowMonthGpf" value="${resValue.allowMonthGpf}"></c:set>
<c:set var="dedRuleList" value="${resValue.dedRuleList}"></c:set>
<c:set var="allowRuleList" value="${resValue.allowRuleList}"></c:set>
<c:set var="curYear" value="${resValue.curYear}"></c:set>
<c:set var="curMonth" value="${resValue.curMonth}"></c:set>
<script type="text/javascript">
	/* Added By Tejashree */
	var curMonthId = '${currMonthId}';
	var curYearId = '${currYearId}';
	/*Ended By Tejashree  */
	function populateEmployeeSalary(rownumber, dedRuleListVal,
			allowRuleListVal, basicAmt) {

		//var allowRuleList = "${allowRuleList}";
		var allowRuleList = allowRuleListVal;
		allowRuleList = allowRuleList.replace('[', "");
		allowRuleList = allowRuleList.replace(']', "");
		//var dedRuleList = "${dedRuleList}";
		var dedRuleList = dedRuleListVal;
		dedRuleList = dedRuleList.replace('[', "");
		dedRuleList = dedRuleList.replace(']', "");

		var dedRuleListArray = dedRuleList.split(",");
		var allowRuleListArray = allowRuleList.split(",");
		var ArrayDeductionFromJSP = document
				.getElementsByName("hidDeductionCode");
		var ArrayAllowancesFromJSP = document
				.getElementsByName("hidAllowanceCode");
		var totalAllowances = document.getElementById("hidTotalAllowances").value;
		var totalDeductions = document.getElementById("hidTotalDeductions").value;

		document.getElementById("txtBasicPay" + String(rownumber)).value = basicAmt;
		for (var lInt = 0; lInt < totalAllowances; lInt++) {
			var index = allowRuleListArray
					.indexOf(Number(ArrayAllowancesFromJSP[lInt].value));
			document.getElementById("txtAllowance" + (Number(lInt) + Number(1))
					+ String(rownumber)).value = allowRuleListArray[Number(index)
					+ Number(1)];
		}
		for (lInt = 0; lInt < totalDeductions; lInt++) {
			index = dedRuleListArray
					.indexOf(Number(ArrayDeductionFromJSP[lInt].value));
			if (Number(index) > 0)
				document.getElementById("txtDeduction"
						+ (Number(lInt) + Number(1)) + String(rownumber)).value = dedRuleListArray[Number(index)
						+ Number(1)];
		}
		calcGrossAmount(rownumber);
		calcTotalDeduction(rownumber);
		calcNetPay(rownumber);
	}

	//

	function AddNewRowBrokenPrd() {
		//alert('  respected  sir pandey  ji ');
		var table = document.getElementById("vo");
		var nextRow = Number(document.getElementById("hidTotalRows").value) + 1;

		var newRow = table.insertRow(-1);
		color2 = "rgb(255, 218, 178)";
		newRow.style.borderColor = color2;

		var Cell1 = newRow.insertCell(-1);
		Cell1.style.border = "1px solid rgb(255, 218, 178)";
		Cell1.innerHTML = '<input type="text" size="10" name="txtFromDate" id="txtFromDate'
				+ nextRow
				+ '" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 	onBlur="validateDate(this);checkDateForThisMonth(this,'
				+ nextRow
				+ ');setNoOfDays('
				+ nextRow
				+ ')" value="" />'
				+ '&nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick="window_open(\'txtFromDate'
				+ nextRow
				+ '\', 375, 570, \'\', \'\', '
				+ nextRow
				+ ');"	style="cursor: pointer;"/>';
		//Cell1.innerHTML = Cell1.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForFromDate'+ nextRow +'" >*</label>' ;

		var Cell2 = newRow.insertCell(-1);
		Cell2.style.border = "1px solid rgb(255, 218, 178)";
		Cell2.innerHTML = '<input type="text" size="10" name="txtToDate" id="txtToDate'
				+ nextRow
				+ '" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 	onBlur="validateDate(this);checkDateForThisMonth(this,'
				+ nextRow
				+ ');checkSameMonth(this,'
				+ nextRow
				+ ');setNoOfDays('
				+ nextRow
				+ ');loadSalaryFromRuleEngine('
				+ nextRow
				+ ')" value="" />'
				+ '&nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick="window_open(\'txtToDate'
				+ nextRow
				+ '\', 375, 570, \'\', \'\', '
				+ nextRow
				+ ');"	style="cursor: pointer;"/>';
		//Cell2.innerHTML = Cell2.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForToDate'+ nextRow +'" >*</label>' ;

		var Cell3 = newRow.insertCell(-1);
		Cell3.style.border = "1px solid rgb(255, 218, 178)";
		Cell3.innerHTML = '<input type="text" size="8" name="txtNoOfDays" id="txtNoOfDays'+nextRow+'"  value="" />';
		//Cell3.innerHTML = Cell3.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForNoOfDays'+ nextRow +'" >*</label>';

		var Cell4 = newRow.insertCell(-1);
		Cell4.style.border = "1px solid rgb(255, 218, 178)";
		Cell4.innerHTML = '<input type="text" size="8" name="txtBasicPay" id="txtBasicPay'
				+ nextRow
				+ '"  value="" onkeypress="digitFormat(this);" onblur="calcGrossAmount('
				+ nextRow + ');calcNetPay(' + nextRow + ');" />';
		//Cell4.innerHTML = Cell4.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForBasicPay'+ nextRow +'" >*</label>';

		var totalAllowances = document.getElementById("hidTotalAllowances").value;
		var totalDeductions = document.getElementById("hidTotalDeductions").value;

		var ArrayAllowancesFromJSP = document
				.getElementsByName("hidAllowanceCode");
		var ArrayAllowancesNamesFromJSP = document
				.getElementsByName("hidAllowanceName");
		var nextlInt = 0;

		for (var lInt = 0; lInt < totalAllowances; lInt++) {
			nextlInt = nextlInt + 1;
			var CellForAllowance = newRow.insertCell(-1);

			if (Number(ArrayAllowancesFromJSP[lInt].value) == Number('135')
					|| Number(ArrayAllowancesFromJSP[lInt].value) == Number('77')
					|| Number(ArrayAllowancesFromJSP[lInt].value) == Number('78')
					|| Number(ArrayAllowancesFromJSP[lInt].value) == Number('75')
					|| Number(ArrayAllowancesFromJSP[lInt].value) == Number('76')
					|| Number(ArrayAllowancesFromJSP[lInt].value) == Number('36')
					|| Number(ArrayAllowancesFromJSP[lInt].value) == Number('72')) {
				CellForAllowance.style.border = "1px solid rgb(255, 218, 178)";
				CellForAllowance.innerHTML = '<input type="text"  id="txtAllowance'
						+ nextlInt
						+ ""
						+ nextRow
						+ '" size="5" onkeypress="digitFormat(this);" onblur="checkServcExpDate('
						+ nextRow
						+ ',this);calcGrossAmount('
						+ nextRow
						+ ');calcNetPay('
						+ nextRow
						+ ');" />'
						+ '<input type="hidden" name="hidAllowanceCode" id="hidAllowanceCode'+ nextlInt + nextRow+'" value="'+ArrayAllowancesFromJSP[lInt].value +'"/>'
						+ '<input type="hidden" name="hidAllowanceName" id="hidAllowanceName'+ nextlInt + nextRow+'" value="'+ArrayAllowancesNamesFromJSP[lInt].value+'" />';
			} else {
				CellForAllowance.style.border = "1px solid rgb(255, 218, 178)";
				CellForAllowance.innerHTML = '<input type="text" id="txtAllowance'
						+ nextlInt
						+ ""
						+ nextRow
						+ '" size="5" onkeypress="digitFormat(this);" onblur="calcGrossAmount('
						+ nextRow
						+ ');calcNetPay('
						+ nextRow
						+ ');" />'
						+ '<input type="hidden" name="hidAllowanceCode" id="hidAllowanceCode'+ nextlInt + nextRow+'" value="'+ArrayAllowancesFromJSP[lInt].value +'"/>'
						+ '<input type="hidden" name="hidAllowanceName" id="hidAllowanceName'+ nextlInt + nextRow+'" value="'+ArrayAllowancesNamesFromJSP[lInt].value+'" />';

			}
			//	CellForAllowance.innerHTML = CellForAllowance.innerHTML  + '&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator" id="labelForAllowance'+ nextlInt + nextRow+'" >*</label>';

		}

		var Cell5 = newRow.insertCell(-1);
		Cell5.style.border = "1px solid rgb(255, 218, 178)";
		Cell5.innerHTML = '<input type="text" size="8" name="txtGrossAmt" id="txtGrossAmt'+nextRow+'" value="" readonly="readonly" />';
		//Cell5.innerHTML =  Cell5.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForGrossAmt'+ nextRow +'" >*</label>';

		var ArrayDeductionFromJSP = document
				.getElementsByName("hidDeductionCode");
		var ArrayDeductionNamesFromJSP = document
				.getElementsByName("hidDeductionName");
		nextlInt = 0;

		for (lInt = 0; lInt < totalDeductions; lInt++) {

			//alert('inside for value '+(ArrayDeductionNamesFromJSP[lInt].value));
			nextlInt = nextlInt + 1;
			var CellForDeduction = newRow.insertCell(-1);
			// added by pandey
			//console.log(ArrayDeductionNamesFromJSP[lInt].value);
			if (ArrayDeductionNamesFromJSP[lInt].value == "Revenue Stamp") {
				//alert('inside the loop ');

				CellForDeduction.style.border = "1px solid rgb(255, 218, 178)";
				CellForDeduction.innerHTML = '<input type="text"  readonly="readonly"  id="txtDeduction'
						+ nextlInt
						+ ""
						+ nextRow
						+ '" size="5" onkeypress="digitFormat(this);" onblur="calcTotalDeduction('
						+ nextRow
						+ ');calcNetPay('
						+ nextRow
						+ ');" />'
						+ '<input type="hidden" name="hidDeductionCode" id="hidDeductionCode'+ nextlInt + nextRow+'" value="'+ArrayDeductionFromJSP[lInt].value +'"/>'
						+ '<input type="hidden" name="hidDeductionName" id="hidDeductionName'+ nextlInt + nextRow+'" value="'+ArrayDeductionNamesFromJSP[lInt].value+'" />';
			} else {

				// ended by pandey

				CellForDeduction.style.border = "1px solid rgb(255, 218, 178)";
				CellForDeduction.innerHTML = '<input type="text"    id="txtDeduction'
						+ nextlInt
						+ ""
						+ nextRow
						+ '" size="5" onkeypress="digitFormat(this);" onblur="calcTotalDeduction('
						+ nextRow
						+ ');calcNetPay('
						+ nextRow
						+ ');" />'
						+ '<input type="hidden" name="hidDeductionCode" id="hidDeductionCode'+ nextlInt + nextRow+'" value="'+ArrayDeductionFromJSP[lInt].value +'"/>'
						+ '<input type="hidden" name="hidDeductionName" id="hidDeductionName'+ nextlInt + nextRow+'" value="'+ArrayDeductionNamesFromJSP[lInt].value+'" />';
				//CellForDeduction.innerHTML = CellForDeduction.innerHTML + '&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator" id="labelForDeduction'+ nextlInt + nextRow+'" >*</label>';

			}

		}
		var Cell6 = newRow.insertCell(-1);
		Cell6.style.border = "1px solid rgb(255, 218, 178)";
		Cell6.innerHTML = '<input type="text" size="8" name="txtTotalDeduction" id="txtTotalDeduction'+nextRow+'" value="" readonly="readonly" />';
		//Cell6.innerHTML =  Cell6.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForTotalDeduction'+ nextRow +'" >*</label>';

		var Cell7 = newRow.insertCell(-1);
		Cell7.style.border = "1px solid rgb(255, 218, 178)";
		Cell7.innerHTML = '<input type="text" size="8" name="txtNetPay" id="txtNetPay'+nextRow+'" value="" readonly="readonly" />';
		//Cell7.innerHTML =  Cell7.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForNetPay'+ nextRow +'" >*</label>';

		var Cell8 = newRow.insertCell(-1);
		Cell8.style.border = "1px solid rgb(255, 218, 178)";
		Cell8.width = "190px";
		Cell8.innerHTML = '<select name="cmbReasonForBrokenPeriod" id="cmbReasonForBrokenPeriod'
				+ nextRow
				+ '" style="width: 90%;" onChange="checkForOtherReason('
				+ nextRow
				+ ');" >'
				+ '<option value="-1">--Select--</option>'
				+ LISTREASONSFORBROKENPERIOD + '</select>';
		//Cell8.innerHTML = Cell8.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForReason'+nextRow+'" >*</label>';

		var Cell9 = newRow.insertCell(-1);
		Cell9.style.border = "1px solid rgb(255, 218, 178)";
		Cell9.innerHTML = '<textarea name="txtRemarks" id="txtRemarks'+nextRow+'" rows="2" cols="20" ></textarea>';
		//Cell9.innerHTML = Cell9.innerHTML  + '&nbsp;<label class="mandatoryindicator" id="labelForRemarksForOtherReason'+nextRow+'" style="display:none" >*</label>';

		var Cell10 = newRow.insertCell(-1);
		Cell10.style.border = "1px solid rgb(255, 218, 178)";
		Cell10.innerHTML = '<a href=# onclick="DeleteRowBrokenPrd(' + nextRow
				+ ');"> <label id="DeleteRowBrokenPrd">Delete</label></a>';

		document.getElementById("hidTotalRows").value = Number(document
				.getElementById("hidTotalRows").value) + 1;
	}

	// added by pandey here 
	function saveBrokenPrdData() // find by ashish pandey  broken perod save button not workiing 
	{
		//alert('ashish pandey  line 171');
		if (!validateSaveForBrokenPeriodPay()) {
			//alert('ashish pandey  line 174');
			return false;
		}

		//alert('ashish pandey  line 178');
		var totalRows = document.getElementById("hidTotalRows").value;
		//alert('ashish pandey  line 180');
		var year = document.getElementById("cmbYear").value;
		//alert('ashish pandey  line 182');
		var month = document.getElementById("cmbMonth").value;
		//alert('ashish pandey  line 184');
		var eisEmpId = document.getElementById("txtEmployeeId").value;
		//alert('ashish pandey  line 186');

		var fromDates = "";
		var toDates = "";
		var noOfDays = "";
		var basicPays = "";
		var netPays = "";
		var reasons = "";
		var remarks = "";

		var totalAllowances = document.getElementById("hidTotalAllowances").value;
		var totalDeductions = document.getElementById("hidTotalDeductions").value;
		var allowancesCodes = "";
		var allowancesValues = "";
		var deductionCodes = "";
		var deductionValues = "";
		//alert('ashish pandey  line 194');
		for (var counter = 1; counter <= totalRows; counter++) { //alert('ashish pandey  line 198');
			fromDates = fromDates
					+ document.getElementById("txtFromDate" + counter).value
					+ "~";
			toDates = toDates
					+ document.getElementById("txtToDate" + counter).value
					+ "~";
			noOfDays = noOfDays
					+ document.getElementById("txtNoOfDays" + counter).value
					+ "~";
			basicPays = basicPays
					+ document.getElementById("txtBasicPay" + counter).value
					+ "~";
			netPays = netPays
					+ document.getElementById("txtNetPay" + counter).value
					+ "~";
			reasons = reasons
					+ document.getElementById("cmbReasonForBrokenPeriod"
							+ counter).value + "~";
			remarks = remarks
					+ document.getElementById("txtRemarks" + counter).value
					+ "~";

			var netPays1 = document.getElementById("txtNetPay" + counter).value;
			//alert("net pay is   by pandey "+netPays1);
			if (netPays1 <= 0) {
				alert("all amount should be in positive and Please check Netpay ");
				return false;
			}
			//getting allowances for the employee

			for (var lInt = 1; lInt <= totalAllowances; lInt++) {//alert('ashish pandey  line 210');
				if (lInt == totalAllowances) {
					allowancesCodes = allowancesCodes
							+ document.getElementById("hidAllowanceCode" + lInt
									+ counter).value + ":" + "~";
					allowancesValues = allowancesValues
							+ document.getElementById("txtAllowance" + lInt
									+ counter).value + ":" + "~";
				} else {
					allowancesCodes = allowancesCodes
							+ document.getElementById("hidAllowanceCode" + lInt
									+ counter).value + ":";
					allowancesValues = allowancesValues
							+ document.getElementById("txtAllowance" + lInt
									+ counter).value + ":";
				}
			}

			//getting deductions for the employee

			for (lInt = 1; lInt <= totalDeductions; lInt++) {
				if (lInt == totalDeductions) {
					deductionCodes = deductionCodes
							+ document.getElementById("hidDeductionCode" + lInt
									+ counter).value + ":" + "~";
					deductionValues = deductionValues
							+ document.getElementById("txtDeduction" + lInt
									+ counter).value + ":" + "~";
				} else {
					deductionCodes = deductionCodes
							+ document.getElementById("hidDeductionCode" + lInt
									+ counter).value + ":";
					deductionValues = deductionValues
							+ document.getElementById("txtDeduction" + lInt
									+ counter).value + ":";
				}
			}
		}
		//alert('ashish pandey  line 239');
		var uri = 'ifms.htm?actionFlag=saveBrokenPeriodPay';
		//alert('ashish pandey  line 241');
		var url = 'year=' + year + '&month=' + month + '&eisEmpId=' + eisEmpId
				+ '&fromDates=' + fromDates + '&toDates=' + toDates
				+ '&noOfDays=' + noOfDays + '&basicPays=' + basicPays
				+ '&netPays=' + netPays + '&reasons=' + reasons + '&remarks='
				+ remarks + '&allowancesCodes=' + allowancesCodes
				+ '&allowancesValues=' + allowancesValues + '&deductionCodes='
				+ deductionCodes + '&deductionValues=' + deductionValues;

		//alert(url);

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				getDataStateChangedForSaveBrokenPrds(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	}

	function loadSalaryFromRuleEngine(rownumber) {
		showProgressbar('Please Wait<br>Your request is in progress...');
		var url = "";
		try {
			var toDate = document.getElementById("txtToDate" + rownumber).value;
			var fromDate = document.getElementById("txtFromDate" + rownumber).value;
			if (toDate != "" && fromDate != "") {
				var toDateArray = toDate.split("/");
				var month = toDateArray[1];
				var year = toDateArray[2];

				var noOfDays = document.getElementById("txtNoOfDays"
						+ rownumber).value;
				var eisEmpId = document.getElementById("txtEmployeeId").value;
				uri = "ifms.htm?actionFlag=calculateEmployeeSalary&eisEmpId="
						+ eisEmpId + "&month=" + month + "&year=" + year
						+ "&noOfDays=" + noOfDays;
				myAjax = new Ajax.Request(
						uri,
						{
							method : 'post',
							asynchronous : true,
							parameters : uri,
							onSuccess : function(myAjax) {
								var XMLDoc = myAjax.responseXML.documentElement;
								var dedRuleListTag = XMLDoc
										.getElementsByTagName('dedRuleList');
								var allowRuleListTag = XMLDoc
										.getElementsByTagName('allowRuleList');
								var basicAmtTag = XMLDoc
										.getElementsByTagName('basicAmt');
								var dedRuleListVal = dedRuleListTag[0].firstChild.nodeValue;
								var allowRuleListVal = allowRuleListTag[0].firstChild.nodeValue;
								var basicAmt = basicAmtTag[0].firstChild.nodeValue;
								populateEmployeeSalary(rownumber,
										dedRuleListVal, allowRuleListVal,
										basicAmt);
								hideProgressbar();
								return false;

							},
							onFailure : function() {
								alert('Something went wrong...');
							}
						});
			} else {
				hideProgressbar();
				alert("Please enter To Date and From Date");
			}
		} catch (error) {
			hideProgressbar();
			alert("Salary not calculated from Rule engine becuase of some error");
		}

	}
	// added  new method by Tejashree from broken peroid js
	function SearchEmployee1() {
		//showProgressbar("Please Wait...");
		// added by Tejashree
		var d = new Date();
		var n = d.getFullYear();
		//alert('pandey ji=='+n);  // getting 2020
		var m = d.getMonth();
		//alert('pandey ji=='+m);  // getting 3 in april
		var firstYear = 0;
		var lastYear = 0;
		var searchName = document.getElementById("txtSearchName").value;
		var yearId = document.getElementById("cmbYear").value;
		var monthId = document.getElementById("cmbMonth").value;
		if (searchName == "") {
			alert('Please  enter name in search option');
			return;
		}
		if (yearId == -1) {
			alert('Please enter pay year');
			return;
		}
		if (monthId == -1) {
			alert('Please enter pay month');
			return;
		}
		// added by Tejashree
		/* if((yearId==33)&&((monthId == 1)||(monthId == 2)||(monthId == 3)))
			{
		    			if(m < 3)
		    		{
		    			firstYear = n-1;
		    			lastYear = n;
		    		}
		    		else
		    		{
		    			firstYear = n;
		    			lastYear = n+1;
		    		}
		    var Vfirst = firstYear - 1;
			alert('Please select  correct Financial  Pay Month .  NOTE  [ Please Select  January, February and March  Month for      '+Vfirst+'-'+firstYear+'       and for April onwards - Select          '+firstYear+'-'+lastYear+'  . ]');
			//alert('Please  Select the correct Month  As  Per Your  Pay Year');
			return ;
			} */
		document.getElementById("btnSubmitData").disabled = true;
		self.location.href = "ifms.htm?actionFlag=searchForBrokenPay&txtSearchName="
				+ searchName + "&yearId=" + yearId + "&monthId=" + monthId;
	}
	// ended by Tejashree

	function loadNetPay() {
		var noOfRows = "${BrokenPeriodPayListSize}";
		var Genrtd = "${resValue.Generated}";
		if (Genrtd == "1") {
			document.getElementById("btnAddRow").disabled = "disabled";
			document.getElementById("btnSave").disabled = "disabled";
		}
		if (noOfRows > 0) {
			for (var i = 1; i <= noOfRows; i++) {
				calcGrossAmount(i);

			}
		}
	}
	function loadGrossAmount() {
		var noOfRows = "${BrokenPeriodPayListSize}";
		var Genrtd = "${resValue.Generated}";
		if (Genrtd == "1") {
			document.getElementById("btnAddRow").disabled = "disabled";
			document.getElementById("btnSave").disabled = "disabled";
		}
		if (noOfRows > 0) {
			for (var i = 1; i <= noOfRows; i++) {
				calcTotalDeduction(i);
			}
		}
	}
	function loadTotalDeduction() {
		var noOfRows = "${BrokenPeriodPayListSize}";
		var Genrtd = "${resValue.Generated}";
		if (Genrtd == "1") {
			document.getElementById("btnAddRow").disabled = "disabled";
			document.getElementById("btnSave").disabled = "disabled";
		}
		if (noOfRows > 0) {
			for (var i = 1; i <= noOfRows; i++) {
				calcNetPay(i);
			}
		}
	}
	/*  //added by Tejashree andcommented
	function selectCorrectFinYear()
	{
	var d = new Date();
	   var n = d.getFullYear();
	   var m = d.getMonth();
	var firstYear = 0;
	var lastYear = 0;
	   if(m < 3)
	   {
	   	firstYear = n-1;
	   	lastYear = n;
	   	
	   }
	   else
	   {
	   	firstYear = n;
	   	lastYear = n+1;
	   }
	   var Vfirst = firstYear - 1;
	alert('Please select  correct Financial Pay Year. For January, February and March - Select '+Vfirst+'-'+firstYear+' and for April onwards - Select '+firstYear+'-'+lastYear+'.');

	}
	 *///ended by Tejashree
</script>
<body>
	<hdiits:form name="BrokenPeriod" validate="true" method="POST"
		encType="multipart/form-data">
		<fieldset class="tabstyle">
			<legend>Search Employee</legend>

			<table border="0" width="100%" align="left" cellpadding="4"
				cellspacing="4">


				<tr>
					<td width="10%" align="left"><fmt:message
							key="CMN.SEARCHWITHNAME" bundle="${dcpsLables}" /></td>
					<td width="20%" align="left"><input type="text"
						id="txtSearchName" style="text-transform: uppercase" size="30"
						name="txtSearchName" value="${resValue.EmpName}" /> <label
						class="mandatoryindicator">*</label> <span
						id="roleIndicatorRegion" style="display: none"> <img
							src="./images/busy-indicator.gif" /></span></td>
					</td>

					<%-- <td width="10%"><fmt:message key="CMN.Month" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
				<select name="cmbMonth" id="cmbMonth" onChange="selectCorrectFinYear();" >
											<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
												<c:forEach var="month" items="${resValue.MONTHS}" >
																<c:choose>
																	<c:when test="${resValue.monthId == month.id}">
																						<option value="${month.id}" selected="selected"><c:out 
																								value="${month.desc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																						<option value="${month.id}"><c:out 
																								value="${month.desc}"></c:out></option>
																	</c:otherwise>						
															    </c:choose>
												</c:forEach>
				</select>
				<label class="mandatoryindicator">*</label>
			</td> --%>

					<td width="10%"><fmt:message key="CMN.Year"
							bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left"><select name="cmbYear"
						id="cmbYear" onChange="">
							<option value="-1"><fmt:message
									key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="year" items="${resValue.YEARS}">
								<c:choose>
									<c:when test="${resValue.yearId == year.id}">
										<option value="${year.id}" selected="selected"><c:out
												value="${year.desc}"></c:out></option>
									</c:when>
									<c:otherwise>
										<option value="${year.id}"><c:out
												value="${year.desc}"></c:out></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <label class="mandatoryindicator">*</label></td>


					<!-- Added By Tejashree -->
					<td width="10%"><fmt:message key="CMN.Month"
							bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left"><select name="cmbMonth"
						id="cmbMonth" onChange="selectCorrectFinYear();">
							<option value="-1"><fmt:message
									key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="month" items="${resValue.MONTHS}">
								<c:choose>
									<c:when test="${resValue.monthId == month.id}">
										<option value="${month.id}" selected="selected"><c:out
												value="${month.desc}"></c:out></option>
									</c:when>
									<c:otherwise>
										<option value="${month.id}"><c:out
												value="${month.desc}"></c:out></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <label class="mandatoryindicator">*</label></td>
					<!-- Ended By Tejashree -->

				</tr>

				<tr>
					<td colspan="6" align="left"><hdiits:button
							name="btnSubmitData" id="btnSubmitData" type="button"
							captionid="BTN.SEARCH" bundle="${dcpsLables}"
							onclick="SearchEmployee();" /></td>
				</tr>
				<tr>
					<td width="10%" align="left"><fmt:message
							key="CMN.EmployeeName" bundle="${dcpsLables}" /></td>
					<td width="20%" align="left"><input type="text"
						id="txtEmployeeName" style="text-transform: uppercase" size="30"
						name="txtEmployeeName" readonly="readonly"
						value="${resValue.EmpName}" /></td>
					<td width="10%" align="left" style="display: none"><fmt:message
							key="CMN.EmployeeId" bundle="${dcpsLables}" /></td>
					<td width="20%" align="left" style="display: none"><input
						type="text" id="txtEmployeeId" style="text-transform: uppercase"
						size="20" name="txtEmployeeId" readonly="readonly"
						value="${resValue.EmpId }" /></td>
					<td width="10%" align="left">Sevaarth Id</td>
					<td width="20%" align="left"><input type="text"
						id="txtSevarthId" style="text-transform: uppercase" size="20"
						name="txtSevarthId" readonly="readonly"
						value="${resValue.sevarthId }" /></td>
					<td width="10%" align="left"><fmt:message key="CMN.EMPDESIG"
							bundle="${dcpsLables}" /></td>
					<td width="20%" align="left"><input type="text"
						id="txtDesignation" style="text-transform: uppercase" size="20"
						name="txtDesignation" readonly="readonly"
						value="${resValue.Designation }" /></td>
				</tr>

				<tr>
					<td width="10%" align="left"><fmt:message key="CMN.OFFICENAME"
							bundle="${dcpsLables}" /></td>
					<td width="20%" align="left"><input type="text"
						id="txtOfficeName" style="text-transform: uppercase" size="20"
						name="txtOfficeName" readonly="readonly"
						value="${resValue.OfficeName }" /></td>
					<td width="10%" align="left"><fmt:message key="CMN.GPFNO"
							bundle="${dcpsLables}" /></td>
					<td width="20%" align="left"><input type="text" id="txtGpfNo"
						style="text-transform: uppercase" size="20" name="txtGpfNo"
						readonly="readonly" value="${resValue.GPFOrDCPSNo }" /></td>
					<td width="10%" align="left">Super Annuation Date</td>
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
						value="${resValue.superAnnuationDate}" var="birthDate" />
					<td width="20%" align="left"><input type="text"
						id="superAnnuationDate" style="text-transform: uppercase"
						size="20" name="superAnnuationDate" readonly="readonly"
						value="${birthDate}" /></td>
				</tr>


			</table>
		</fieldset>
		<br />

		<c:if test="${resValue.SearchStatus == 'true'}">
			<fieldset class="tabstyle">
				<legend>Broken Period Pay</legend>

				<c:set var="counter" value="1" />
				<c:set var="AllowancesList" value="${resValue.AllowancesList}" />
				<c:set var="DeductionsList" value="${resValue.DeductionsList}" />

				<script type="text/javascript">
					LISTREASONSFORBROKENPERIOD = '';
				</script>

				<c:forEach var="EventList"
					items="${resValue.lListReasonsForBrokenPeriod}">
					<script>
						LISTREASONSFORBROKENPERIOD += "<option value='${EventList.lookupId}'> ${EventList.lookupDesc}</option>";
					</script>
				</c:forEach>

				<div style="width: 175vh; overflow: auto; height: 210px">
					<table id="vo" style="width: 140%; align: center; Border: 1px"
						cellpadding="4" class="datatable">
						<thead>
							<tr>
								<th class="datatableheader">From Date <label
									class="mandatoryindicator">*</label></th>
								<th class="datatableheader">To Date <label
									class="mandatoryindicator">*</label>
								</th>
								<th class="datatableheader">No. Of Days <label
									class="mandatoryindicator">*</label>
								</th>
								<th class="datatableheader">Pay+GP <label
									class="mandatoryindicator">*</label>
								</th>

								<c:forEach var="allowance" items="${AllowancesList}">
									<th class="datatableheader">${allowance[1]}<label
										class="mandatoryindicator">*</label>
									</th>
								</c:forEach>
								<th class="datatableheader">Gross Amount</th>
								<c:forEach var="deduction" items="${DeductionsList}">
									<th class="datatableheader">${deduction[1]}<label
										class="mandatoryindicator">*</label>
									</th>
								</c:forEach>
								<th class="datatableheader">Total Deduction <label
									class="mandatoryindicator">*</label>
								</th>
								<th class="datatableheader">Net Pay <label
									class="mandatoryindicator">*</label>
								</th>
								<th class="datatableheader">Reason for Non-drawal <label
									class="mandatoryindicator">*</label>
								</th>
								<th class="datatableheader">Remarks <label
									class="mandatoryindicator">*</label>
								</th>
								<th class="datatableheader">Delete</th>
							</tr>
						</thead>

						<c:choose>
							<c:when test="${resValue.PaysAddedBefore == 'No'}">
								<tbody>
									<tr style="border: 1px">
										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="10" name="txtFromDate"
											id="txtFromDate${counter}" maxlength="10"
											onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);checkDateForThisMonth(this,'${counter}');setNoOfDays('${counter}')"
											value="" /> &nbsp; <img
											src='images/CalendarImages/ico-calendar.gif'
											onClick='window_open("txtFromDate${counter}",375,570)'
											style="cursor: pointer;" /> <%-- <label class="mandatoryindicator" id="labelForFromDate${counter}" >*</label> --%>
										</td>

										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="10" name="txtToDate"
											id="txtToDate${counter}" maxlength="10"
											onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);checkDateForThisMonth(this,'${counter}');checkSameMonth(this,'${counter}');setNoOfDays('${counter}');loadTotalDeduction('${counter}');loadSalaryFromRuleEngine('${counter}')"
											value="" /> &nbsp; <img
											src='images/CalendarImages/ico-calendar.gif'
											onClick='window_open("txtToDate${counter}",375,570)'
											style="cursor: pointer;" /> <%-- <label class="mandatoryindicator" id="labelForToDate${counter}" >*</label>--%>
										</td>

										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="8" name="txtNoOfDays"
											id="txtNoOfDays${counter}" value="" readonly="readonly" /> <%-- <label class="mandatoryindicator" id="labelForNoOfDays${counter}" >*</label> --%>
										</td>

										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="8" name="txtBasicPay"
											id="txtBasicPay${counter}" value="" maxlength="8"
											onkeypress="digitFormat(this);"
											onblur="calcNetPay('${counter}');" /> <%-- <label class="mandatoryindicator" id="labelForBasicPay${counter}" >*</label> --%>
										</td>

										<c:set var="totalAllowancesVar" value="0" />

										<c:forEach var="allowance" items="${AllowancesList}"
											varStatus="counterOfAllowance">
											<c:choose>
												<c:when
													test="${allowance[0] eq '9' || allowance[0] eq '10' || allowance[0] eq '11' || allowance[0] eq '14' || allowance[0] eq '97' || allowance[0] eq '98' || allowance[0] eq '99' || allowance[0] eq '101' || allowance[0] eq '100' || allowance[0] eq '102' || allowance[0] eq '103' || allowance[0] eq '104' || allowance[0] eq '145' || allowance[0] eq '161' || allowance[0] eq '162' || allowance[0] eq '179'}">
													<td style="border: 1px solid rgb(255, 218, 178);"><input
														type="text"
														id="txtAllowance${counterOfAllowance.index+1}${counter}"
														size="5" maxlength="6" onkeypress="digitFormat(this);"
														onblur="calcGrossAmount('${counter}');calcNetPay('${counter}');" />
														<input type="hidden" name="hidAllowanceCode"
														id="hidAllowanceCode${counterOfAllowance.index+1}${counter}"
														value="${allowance[0]}" /> <input type="hidden"
														name="hidAllowanceName"
														id="hidAllowanceName${counterOfAllowance.index+1}${counter}"
														value="${allowance[1]}" /> <%-- <label class="mandatoryindicator" id="labelForAllowance${counterOfAllowance.index+1}${counter}" >*</label>  --%>
														<c:set var="totalAllowancesVar"
															value="${totalAllowancesVar+1}" /></td>
												</c:when>
												<c:otherwise>
													<td style="border: 1px solid rgb(255, 218, 178);"><input
														type="text"
														id="txtAllowance${counterOfAllowance.index+1}${counter}"
														size="5" maxlength="6" onkeypress="digitFormat(this);"
														onblur="calcGrossAmount('${counter}');calcNetPay('${counter}');" />
														<input type="hidden" name="hidAllowanceCode"
														id="hidAllowanceCode${counterOfAllowance.index+1}${counter}"
														value="${allowance[0]}" /> <input type="hidden"
														name="hidAllowanceName"
														id="hidAllowanceName${counterOfAllowance.index+1}${counter}"
														value="${allowance[1]}" /> <%-- <label class="mandatoryindicator" id="labelForAllowance${counterOfAllowance.index+1}${counter}" >*</label>  --%>
														<c:set var="totalAllowancesVar"
															value="${totalAllowancesVar+1}" />
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<input type="hidden" id="hidTotalAllowances"
											name="hidTotalAllowances" value="${totalAllowancesVar}" />
										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="8" name="txtGrossAmt"
											id="txtGrossAmt${counter}" value="" readonly="readonly" /> <%-- <label class="mandatoryindicator" id="labelForGrossAmt${counter}" >*</label></td> --%>

											<c:set var="totalDeductionsVar" value="0" /> <c:forEach
												var="deduction" items="${DeductionsList}"
												varStatus="counterOfDeduction">
												<c:choose>
													<c:when
														test="${deduction[0] eq '72' || deduction[0] eq '36' || deduction[0] eq '76' || deduction[0] eq '75' || deduction[0] eq '78' || deduction[0] eq '77'}">

														<td style="border: 1px solid rgb(255, 218, 178);"><input
															type="text"
															id="txtDeduction${counterOfDeduction.index+1}${counter}"
															value="" size="5" maxlength="6"
															onkeypress="digitFormat(this);"
															onblur="calcTotalDeduction('${counter}',this);calcNetPay('${counter}');checkServcExpDate('${counter}');" />
															<input type="hidden" name="hidDeductionCode"
															id="hidDeductionCode${counterOfDeduction.index+1}${counter}"
															value="${deduction[0]}" /> <input type="hidden"
															name="hidDeductionName"
															id="hidDeductionName${counterOfDeduction.index+1}${counter}"
															value="${deduction[1]}" /> <%-- <label class="mandatoryindicator" id="labelForDeduction${counterOfDeduction.index+1}${counter}" >*</label>  --%>
															<c:set var="totalDeductionsVar"
																value="${totalDeductionsVar+1}" /></td>
													</c:when>
													<c:when
														test="${deduction[0] eq '28' || deduction[0] eq '35' || deduction[0] eq '59' || deduction[0] eq '82' || deduction[0] eq '83' || deduction[0] eq '84' || deduction[0] eq '85' || deduction[0] eq '86'}">

														<td style="border: 1px solid rgb(255, 218, 178);"><input
															type="text"
															id="txtDeduction${counterOfDeduction.index+1}${counter}"
															value="" size="5" maxlength="6"
															onkeypress="digitFormat(this);"
															onblur="calcTotalDeduction('${counter}',this);calcNetPay('${counter}');" />
															<input type="hidden" name="hidDeductionCode"
															id="hidDeductionCode${counterOfDeduction.index+1}${counter}"
															value="${deduction[0]}" /> <input type="hidden"
															name="hidDeductionName"
															id="hidDeductionName${counterOfDeduction.index+1}${counter}"
															value="${deduction[1]}" /> <%-- <label class="mandatoryindicator" id="labelForDeduction${counterOfDeduction.index+1}${counter}" >*</label>  --%>
															<c:set var="totalDeductionsVar"
																value="${totalDeductionsVar+1}" /></td>
													</c:when>

													<c:when test="${deduction[0] eq '135'}">

														<td style="border: 1px solid rgb(255, 218, 178);"><input
															type="text"
															id="txtDeduction${counterOfDeduction.index+1}${counter}"
															value="" size="5" maxlength="6"
															onkeypress="digitFormat(this);" readonly="readonly"
															onblur="calcTotalDeduction('${counter}',this);calcNetPay('${counter}');" />
															<input type="hidden" name="hidDeductionCode"
															id="hidDeductionCode${counterOfDeduction.index+1}${counter}"
															value="${deduction[0]}" /> <input type="hidden"
															name="hidDeductionName"
															id="hidDeductionName${counterOfDeduction.index+1}${counter}"
															value="${deduction[1]}" /> <%-- <label class="mandatoryindicator" id="labelForDeduction${counterOfDeduction.index+1}${counter}" >*</label>  --%>
															<c:set var="totalDeductionsVar"
																value="${totalDeductionsVar+1}" /></td>
													</c:when>

													<c:otherwise>

														<td style="border: 1px solid rgb(255, 218, 178);"><input
															type="text"
															id="txtDeduction${counterOfDeduction.index+1}${counter}"
															value="" size="5" maxlength="6"
															onkeypress="digitFormat(this);"
															onblur="calcTotalDeduction('${counter}');calcNetPay('${counter}');" />
															<input type="hidden" name="hidDeductionCode"
															id="hidDeductionCode${counterOfDeduction.index+1}${counter}"
															value="${deduction[0]}" /> <input type="hidden"
															name="hidDeductionName"
															id="hidDeductionName${counterOfDeduction.index+1}${counter}"
															value="${deduction[1]}" /> <%-- <label class="mandatoryindicator" id="labelForDeduction${counterOfDeduction.index+1}${counter}" >*</label> --%>
															<c:set var="totalDeductionsVar"
																value="${totalDeductionsVar+1}" /></td>
													</c:otherwise>
												</c:choose>


											</c:forEach> <input type="hidden" id="hidTotalDeductions"
											name="hidTotalDeductions" value="${totalDeductionsVar}" />
										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="8" name="txtTotalDeduction"
											id="txtTotalDeduction${counter}" value="" readonly="readonly" ;"/>
											<%-- <label class="mandatoryindicator" id="labelForTotalDeduction${counter}" >*</label>  --%>
										</td>

										<td style="border: 1px solid rgb(255, 218, 178);"><input
											type="text" size="8" name="txtNetPay"
											id="txtNetPay${counter}" value="" readonly="readonly" /> <%-- <label class="mandatoryindicator" id="labelForNetPay${counter}" >*</label>   --%>
										</td>

										<td width="190px"
											style="border: 1px solid rgb(255, 218, 178);"><select
											name="cmbReasonForBrokenPeriod"
											id="cmbReasonForBrokenPeriod${counter}" style="width: 90%;"
											onChange="checkForOtherReason('${counter}');">
												<option value="-1"><fmt:message
														key="COMMON.DROPDOWN.SELECT" /></option>
												<c:forEach var="reason"
													items="${resValue.lListReasonsForBrokenPeriod}">
													<option value="${reason.lookupId}"
														title="${reason.lookupDesc}"><c:out
															value="${reason.lookupDesc}"></c:out></option>
												</c:forEach>
										</select> <%-- <label class="mandatoryindicator" id="labelForReason${counter}" >*</label>  --%>
										</td>

										<td style="border: 1px solid rgb(255, 218, 178);"><textarea
												name="txtRemarks" id="txtRemarks${counter}" rows="2"
												cols="20"></textarea> <%-- <label class="mandatoryindicator" id="labelForRemarksForOtherReason${counter}" style="display:none" >*</label>  --%>
										</td>

										<td style="border: 1px solid rgb(255, 218, 178);"><a
											href=# onclick="DeleteRowBrokenPrd('${counter}');"
											style="cursor: pointer;"> <label id="DeleteRowBrokenPrd">Delete</label></a>
										</td>

									</tr>
									<c:set var="counter" value="${counter+1}"></c:set>
								</tbody>
							</c:when>
							<c:otherwise>
								<tbody>
									<c:forEach var="DataForDisplayList"
										items="${DataForDisplayList}">
										<tr style="border: 1px">
											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
												value="${DataForDisplayList.fromDate}"
												var="brokenPeriodFromDate" />
											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="10" name="txtFromDate"
												id="txtFromDate${counter}" maxlength="10"
												onkeypress="digitFormat(this);dateFormat(this);"
												onBlur="validateDate(this);checkDateForThisMonth(this,'${counter}');setNoOfDays('${counter}')"
												value="${brokenPeriodFromDate}" /> &nbsp; <img
												src='images/CalendarImages/ico-calendar.gif'
												onClick='window_open("txtFromDate${counter}",375,570)'
												style="cursor: pointer;" /> <%--	<label class="mandatoryindicator" id="labelForFromDate${counter}" >*</label>  --%>
											</td>

											<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
												value="${DataForDisplayList.toDate}"
												var="brokenPeriodToDate" />
											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="10" name="txtToDate"
												id="txtToDate${counter}" maxlength="10"
												onkeypress="digitFormat(this);dateFormat(this);"
												onBlur="validateDate(this);checkDateForThisMonth(this,'${counter}');setNoOfDays('${counter}');loadSalaryFromRuleEngine('${counter}')"
												value="${brokenPeriodToDate}" /> &nbsp; <img
												src='images/CalendarImages/ico-calendar.gif'
												onClick='window_open("txtToDate${counter}",375,570)'
												style="cursor: pointer;" /> <%--	<label class="mandatoryindicator" id="labelForToDate${counter}" >*</label>  --%>
											</td>

											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="8" name="txtNoOfDays"
												id="txtNoOfDays${counter}"
												value="${DataForDisplayList.noOfDays}" readonly="readonly" />
												<%-- <label class="mandatoryindicator" id="labelForNoOfDays${counter}" >*</label>  --%>
											</td>

											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="8" name="txtBasicPay"
												id="txtBasicPay${counter}"
												value="${DataForDisplayList.basicPay}" maxlength="6"
												onkeypress="digitFormat(this);"
												onblur="calcGrossAmount('${counter}');calcNetPay('${counter}');" />
												<%-- <label class="mandatoryindicator" id="labelForBasicPay${counter}" >*</label> --%>
											</td>

											<c:set var="counterForAllowance" value="0"></c:set>
											<c:set var="totalAllowancesVar" value="0" />
											<c:forEach var="addedAllowance"
												items="${DataForDisplayList.allowList}">
												<td style="border: 1px solid rgb(255, 218, 178);"><input
													type="text"
													id="txtAllowance${counterForAllowance+1}${counter}"
													size="5" maxlength="6" onkeypress="digitFormat(this);"
													onblur="calcGrossAmount('${counter}');calcNetPay('${counter}');"
													value="${addedAllowance[3]}" /> <input type="hidden"
													name="hidAllowanceCode"
													id="hidAllowanceCode${counterForAllowance+1}${counter}"
													value="${addedAllowance[2]}" /> <input type="hidden"
													name="hidAllowanceName"
													id="hidAllowanceName${counterForAllowance+1}${counter}"
													value="${addedAllowance[4]}" /> <%-- <label class="mandatoryindicator" id="labelForAllowance${counterForAllowance+1}${counter}" >*</label>   --%>
													<c:set var="totalAllowancesVar"
														value="${totalAllowancesVar+1}" /></td>
												<c:set var="counterForAllowance"
													value="${counterForAllowance+1}"></c:set>
											</c:forEach>
											<input type="hidden" id="hidTotalAllowances"
												name="hidTotalAllowances" value="${totalAllowancesVar}" />
											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="8" name="txtGrossAmt"
												id="txtGrossAmt${counter}" value="" readonly="readonly" ;" />
												<%-- <label class="mandatoryindicator" id="labelForGrossAmt${counter}" >*</label></td>  - --%>

												<c:set var="counterForDeduction" value="0"></c:set> <c:set
													var="totalDeductionsVar" value="0" /> <c:forEach
													var="addedDeduction"
													items="${DataForDisplayList.deductList}">
													<td style="border: 1px solid rgb(255, 218, 178);"><input
														type="text"
														id="txtDeduction${counterForDeduction+1}${counter}"
														size="5" maxlength="6" onkeypress="digitFormat(this);"
														onblur="calcTotalDeduction('${counter}');calcNetPay('${counter}');"
														value="${addedDeduction[3]}" /> <input type="hidden"
														name="hidDeductionCode"
														id="hidDeductionCode${counterForDeduction+1}${counter}"
														value="${addedDeduction[2]}" /> <input type="hidden"
														name="hidDeductionName"
														id="hidDeductionName${counterForDeduction+1}${counter}"
														value="${addedDeduction[4]}" /> <%--	<label class="mandatoryindicator" id="labelForDeduction${counterForDeduction+1}${counter}" >*</label>   --%>
														<c:set var="totalDeductionsVar"
															value="${totalDeductionsVar+1}" /></td>
													<c:set var="counterForDeduction"
														value="${counterForDeduction+1}"></c:set>
												</c:forEach> <input type="hidden" id="hidTotalDeductions"
												name="hidTotalDeductions" value="${totalDeductionsVar}" />
											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="8" name="txtTotalDeduction"
												id="txtTotalDeduction${counter}" value=""
												readonly="readonly" ;"/> <label class="mandatoryindicator"
												id="labelForTotalDeduction${counter}">*</label>
											<td style="border: 1px solid rgb(255, 218, 178);"><input
												type="text" size="8" name="txtNetPay"
												id="txtNetPay${counter}"
												value="${DataForDisplayList.netPay}" readonly="readonly" />
												<%-- <label class="mandatoryindicator" id="labelForNetPay${counter}" >*</label>   --%>
											</td>

											<td width="190px"
												style="border: 1px solid rgb(255, 218, 178);"><select
												name="cmbReasonForBrokenPeriod"
												id="cmbReasonForBrokenPeriod${counter}" style="width: 100%;"
												onChange="checkForOtherReason('${counter}');">
													<option value="-1"><fmt:message
															key="COMMON.DROPDOWN.SELECT" /></option>
													<c:forEach var="reason"
														items="${resValue.lListReasonsForBrokenPeriod}">
														<c:choose>
															<c:when
																test="${reason.lookupId == DataForDisplayList.reason}">
																<option value="${reason.lookupId}" selected="selected"><c:out
																		value="${reason.lookupDesc}"></c:out></option>
															</c:when>
															<c:otherwise>
																<option value="${reason.lookupId}"
																	title="${reason.lookupDesc}"><c:out
																		value="${reason.lookupDesc}"></c:out></option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
											</select> <%-- 	<label class="mandatoryindicator" id="labelForReason${counter}" >*</label>   --%>
											</td>

											<td style="border: 1px solid rgb(255, 218, 178);"><textarea
													name="txtRemarks" id="txtRemarks${counter}" rows="2"
													cols="20">${DataForDisplayList.remarks}</textarea> <%-- 	<label class="mandatoryindicator" id="labelForRemarksForOtherReason${counter}" style="display:none" >*</label>--%>
											</td>

											<td style="border: 1px solid rgb(255, 218, 178);"><a
												href=# onclick="DeleteRowBrokenPrd('${counter}');"
												style="cursor: pointer;"> <label id="DeleteRowBrokenPrd">Delete</label></a>
											</td>

										</tr>
										<c:set var="counter" value="${counter+1}"></c:set>
									</c:forEach>
								</tbody>
							</c:otherwise>
						</c:choose>

					</table>
					<c:if test="${resValue.Generated == 1}">

						<tr>
							<td style="font-size: 9px;"><label style="color: red;">*
									As the Bill is already generated, Your Changes will not get
									reflected in Paybill. </label></td>
						</tr>
					</c:if>
					<input type="hidden" id="hidTotalRows" value="${counter-1}" />
				</div>
				<br>
				<div align="center">
					<hdiits:button name="btnAddRow" id="btnAddRow" style="width:75px"
						type="button" captionid="BTN.ADD" bundle="${dcpsLables}"
						onclick="AddNewRowBrokenPrd();" />
					<hdiits:button name="btnSave" id="btnSave" type="button"
						captionid="BTN.SAVE" bundle="${dcpsLables}"
						onclick="saveBrokenPrdData();" />
				</div>
			</fieldset>
		</c:if>
		<input type="hidden" name="hidSearchFromSRKA" id="hidSearchFromSRKA"
			value="searchBySRKA" />

		<ajax:autocomplete source="txtSearchName" target="txtSearchName"
			baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompletePayrollSearch"
			parameters="searchKey={txtSearchName},searchBy={hidSearchFromSRKA}"
			className="autocomplete" minimumCharacters="3"
			indicator="roleIndicatorRegion" />


	</hdiits:form>
	<script type="text/javascript">
		
	<%-- try{
	var Genrtd = "${resValue.PaysAddedBefore}";
	if (Genrtd != 'Yes') 
		loadSalaryFromRuleEngine("1");
}catch(error){
}--%>
		loadNetPay();
		loadGrossAmount();
		loadTotalDeduction();
	</script>
</body>
