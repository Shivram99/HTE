<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="BillGroupList" value="${resValue.BillGroupList}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>

<c:choose>
	<c:when test="${resValue.CurrPayComm == '700337'}">
		<c:set var="varAsterisksShownForConsolidatedBasicPay" scope="page" value="style='display: inline;' "></c:set>
		<c:set var="varConsolidatedBasicPay" scope="page" value=""></c:set>
		<c:set var="varConsolidatedBasicPayForCombo" scope="page" value="disabled='disabled'"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="varAsterisksShownForConsolidatedBasicPay" scope="page" value="style='display: none;' "></c:set>
		<c:set var="varConsolidatedBasicPay" scope="page" value="readonly='readonly'"></c:set>
		<c:set var="varConsolidatedBasicPayForCombo" scope="page" value=""></c:set>
	</c:otherwise>
</c:choose>

<c:set var="varPayInPayBandAndGradePayRow" scope="page" value="style='display:none'"></c:set>
<c:if test="${resValue.CurrPayComm == '700016'}">
	<c:set var="varPayInPayBandAndGradePayRow" scope="page"	value="style='display:inline'"></c:set>
</c:if> 

<!-- Added by mani and commend by mani -->
<c:set var="varSevenBasicRow" scope="page"
	value="style='display:none'"></c:set>
<c:if test="${resValue != null && resValue.CurrPayComm == '700005'}">
	<c:set var="varSevenBasicRow" scope="page"
	value="style='display: '"></c:set> 
	<c:set var="varBasicRow" scope="page"
	value="style='display: none'"></c:set> 
</c:if>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>
<script>

var lBlFlagConsolidatedFlag = false;
var lBlFlagPCChangedToConsolidated = false;

function validatePIPBAndChangeBasicInChangePayScale()
{
	var payscale = document.forms[0].cmbPayScale.options[document.forms[0].cmbPayScale.selectedIndex].text;
	var tempArray1 = payscale.split("-");
	minPIPB = tempArray1[0];
	var tempArray2 = tempArray1[1].trim().split("(");
	maxPIPB = tempArray2[0];
	gradePay = tempArray2[1].split(")")[0];
	
	document.getElementById("txtGradePay").value=gradePay;
	paycommission = document.forms[0].cmbPayCommission.value.trim();
	if(paycommission == '700016')
		{
			var PIPB = document.forms[0].txtPayInPayBand.value.trim();
			var minPIPB ;
			var maxPIPB ;
			var gradePay ;
			
	
			if(document.forms[0].cmbPayScale != null)
				{
					if(document.forms[0].cmbPayScale.value != -1 && document.forms[0].cmbPayScale.value != "")
						{
							// validate PIPB
							
							if(PIPB != "")
								{
									if(Number(PIPB) < Number(minPIPB))
									{
										alert('Pay In Pay band must be greater than ' + minPIPB);
										document.forms[0].txtPayInPayBand.value = "";
										return false;
									}
									
									if(Number(PIPB) > Number(maxPIPB))
									{
										alert('Pay In Pay band must be less than ' + maxPIPB);
										document.forms[0].txtPayInPayBand.value = "";
										return false;
									}
									
									document.forms[0].txtBasicPay.value = Number(PIPB) + Number(gradePay);
								}
							
							
						}
				}
		}
	return true;
	
}

function checkForPIPBAndGradePayInChangePayScale()
{
	var paycommission = "" ;
	var payscale = "" ;
	payscaleValue = "";
	var gradePay = "";
	
	if(document.forms[0].cmbPayCommission != null)
		{
			paycommission = document.forms[0].cmbPayCommission.value.trim();
		}
	if(document.forms[0].cmbPayScale != null)
		{
			payscaleValue = document.forms[0].cmbPayScale.value.trim();
			if(payscaleValue != -1)
				{
					payscale = document.forms[0].cmbPayScale.options[document.forms[0].cmbPayScale.selectedIndex].text;
				}
		}
	
	if(document.forms[0].cmbPayCommission != null)
		{
			if(paycommission == '700016')
				{
					// Show pay in pay band and grade pay for the employee
					document.getElementById("payInPayBandAndGradePayRow").style.display = "inline";
					document.getElementById("lableMandatoryForPayInPayBand").style.display = "inline";
					document.getElementById("lableMandatoryForGradePay").style.display = "inline";
					document.forms[0].txtBasicPay.setAttribute('readOnly', 'readOnly');
					
					if(document.forms[0].cmbPayScale != null)
						{
							if(payscaleValue != "" && payscaleValue != -1)
								{
								var payscale = document.forms[0].cmbPayScale.options[document.forms[0].cmbPayScale.selectedIndex].text;
								var tempArray1 = payscale.split("-");
								minPIPB = tempArray1[0];
								var tempArray2 = tempArray1[1].trim().split("(");
								maxPIPB = tempArray2[0];
								gradePay = tempArray2[1].split(")")[0];
								
								document.getElementById("txtGradePay").value=gradePay;
							
									document.forms[0].txtGradePay.value = gradePay;
								}
						}
					
				}
			else
				{				

				document.getElementById("payInPayBandAndGradePayRow").style.display = "none";
				document.getElementById("lableMandatoryForPayInPayBand").style.display = "none";
				document.getElementById("lableMandatoryForGradePay").style.display = "none";
				document.getElementById("lableMandatoryForBasicPay").style.display = "none"; 
				document.forms[0].txtBasicPay.value = "";
				document.forms[0].txtBasicPay.removeAttribute('readOnly');
			
				}
			
			  if(paycommission == '700005')
			  {
			 	 
			 	 
			 	 document.getElementById("sevenLabel").style.display = "block";
			 	 document.getElementById("basicPayRowId").style.display = "none";
			  }
			  else
			 	 {
			 	document.getElementById("txtBasicPay").style.display = "block";
			 	 document.getElementById("basicPayRowId").style.display = "block";
				 document.getElementById("sevenlabel").style.display = "none";
			 	 }
			  
		}
	
}
function GetScalePostfromDesg()
{


	  var commissionId = document.forms[0].elements['cmbPayCommission'].value.trim();
	  if(commissionId == '700337')
	  {
		  document.forms[0].cmbPayScale.value=-1;
		  document.forms[0].cmbPayScale.disabled = true;
		  document.getElementById("lableMandatoryForPayscale").innerHTML = "";
		  document.getElementById("cmbSvnBasic").style.display = "none";
		  document.getElementById("sevenLabel").style.display = "none";
		  document.getElementById("basicPayRowId").style.display = "block";
		  //document.forms[0].txtBasicPay.value = "";
	  } 
	  
	  else
	  {
		  document.getElementById("lableMandatoryForPayscale").innerHTML = "*";
		  document.forms[0].cmbPayScale.disabled = false;
		  var v=document.getElementById("cmbPayScale").length;
		  for(i=1;i<v;i++)
		  {
			  lgth = document.getElementById("cmbPayScale").options.length -1;				  
			  document.getElementById("cmbPayScale").options[lgth] = null;
		  }	
	
		 if(commissionId == '700005')
		  {
		 	 
		 	 
		 	 document.getElementById("sevenLabel").style.display = "block";
		 	 document.getElementById("sevenlabel").style.display = "block";
		 	 document.getElementById("cmbSvnBasic").style.display = "block";
		 	 document.getElementById("txtBasicPay").style.display = "none";
		 	 //document.getElementById("basicPayLbl").style.display = "none";
		 	 document.getElementById("basicPayRowId").style.display = "none";
		  }
		  else
		 	 {
		 	 document.getElementById("txtBasicPay").style.display = "block";
		 	// document.getElementById("basicPayLbl").style.display = "block";
		 	 document.getElementById("basicPayRowId").style.display = "block";
		 	 document.getElementById("cmbSvnBasic").style.display = "none";
			 document.getElementById("sevenLabel").style.display = "none";
		 	 }
		   
		  var url= '&commissionId='+document.forms[0].elements['cmbPayCommission'].value+'&cadre='+document.forms[0].elements['cmbCadre'].value+'&ifAjax=TRUE';
		  var uri= 'ifms.htm?actionFlag=GetScalefromDesignation';
		  
		  var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
					getDataStateChangedForGettingScalesFromPC(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	  }
}


function getDataStateChangedForGettingScalesFromPC(myAjax)
{	
	var XMLDoc=myAjax.responseXML.documentElement;
	var entries = XMLDoc.getElementsByTagName('scale-mapping');
	var scale = document.getElementById("cmbPayScale");
	var val=0;
	var text='';
	var y ;
	for ( var i = 0 ; i < entries.length ; i++ )
	{
		val=entries[i].childNodes[0].firstChild.nodeValue;
		text =entries[i].childNodes[1].firstChild.nodeValue; 

		y = document.createElement('option');
		y.value=val;
		y.text=text;	
		try
		{      				    					
			scale.add(y,null);
		}
		catch(ex)
		{
			scale.add(y); 
		}	

	}
}

function GetSvnBasicData()
{   
	 var commissionId = document.forms[0].elements['cmbPayCommission'].value.trim();
	 //alert("Commissionid="+commissionId) ;
	 if(commissionId == '700005')
	 {
   var cell= document.forms[0].cmbPayScale.value;
		  var uri='ifms.htm?actionFlag=GetSvnPcBasicData';
		  var url= '&cell='+ document.forms[0].cmbPayScale.value;
		  var myAjax = new Ajax.Request(uri,

			       {
			  
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
					getDataStateChangedForGettingPostFromDesig1(myAjax);
					
					},
			        onFailure: function()
			        { 
			        	alert('Something went wrong.Mani..');
			        	} 
			          } );
	 }
  }
//Cretad by Ashish
function getDataStateChangedForGettingPostFromDesig1(myAjax)
{
	
	var dropDown = document.getElementById("cmbSvnBasic");
    dropDown.selectedIndex = 0;
 
		var XMLDoc = myAjax.responseXML.documentElement;
        var namesEntries = XMLDoc.getElementsByTagName('post-mapping');
        document.getElementById("cmbSvnBasic").options.length = 0 ;
   
        var optn = document.createElement('option');
		optn.text = "-- Select --";
		optn.value = "-1";
		document.getElementById("cmbSvnBasic").options.add(optn);
        
		var billNoListObject = document.getElementById('cmbSvnBasic');
		
	
		for(var k = 0; k < namesEntries.length;k++)
		{
           var y1 = document.createElement('option');  
           val=namesEntries[k].childNodes[0].firstChild.nodeValue;    
		    text = namesEntries[k].childNodes[1].firstChild.nodeValue; 
	        y1.value=val;
	        y1.text=text;	
	        try
	        {      				    					
	        	billNoListObject.add(y1,null);
		    }
	        catch(ex)
	        {
	        	billNoListObject.add(y1); 
   	        }	
        }                                                    	
}
//Ended by Ashish



function SaveDataUsingAjaxForChanges(empId,flag){

	//Added by Mani for Change Details
	 var commissionId = document.forms[0].elements['cmbPayCommission'].value.trim();
	 
		if(commissionId == '700005'){
			var curnsvnSelect = document.getElementById("cmbSvnBasic"); //7th Pay Basic
			 if(curnsvnSelect!= cmbSvnBasic){
			var curntsvnText = curnsvnSelect.options[curnsvnSelect.selectedIndex].text;
		 	document.getElementById("cmbSvnBasic").value =curntsvnText; 
			
			} 
		}
	//Ended by Mani for Change Details

	if(!validateRegFormData()){
		 //alert("SaveDataUsingAjax - > in ValidateForm");
		hideProgressbar();
		
		return false;
	}
	var txtAuthorityLetterNo = document.getElementById("txtAuthorityLetterNo").value ;
	var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value ;
	var txtRemarksForApproval = document.getElementById("txtRemarksForApproval").value ;

	if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate == ""  || txtRemarksForApproval == "")
	{
		alert('Please fill the Authority Details.') ;
		hideProgressbar();
		if(document.getElementById("btnSave") != null)
		{
			document.getElementById("btnSave").disabled = false;
		}
		return false;
	}
	
	var txtWithEffectFromDate = document.getElementById("txtWithEffectFromDate").value;
	//alert('wef is'+txtWithEffectFromDate);
	
	if(txtWithEffectFromDate == "" || txtWithEffectFromDate == null)
	{
		alert('Please enter With Effect From Date');
		return false;
	}

	
	var Emp_Id=empId;
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	var saveOrUpdateFlag = flag;
	//alert(saveOrUpdateFlag);
	
	var uri = 'ifms.htm?actionFlag=saveForChanges';
	alert("darshan"+uri);
	var url = runForm(0);
	alert("darshan22"+url);
	url = uri + url;
	alert("darshan33"+url);
	url = url + "&saveOrUpdateFlag=" + saveOrUpdateFlag+ "&empId=" + Emp_Id;
	alert("darshan44"+url);

	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

				var test_Id = XmlHiddenValues[0].childNodes[0].text;
				emp_Id = XmlHiddenValues[0].childNodes[1].text;

				if (test_Id) {
					if(saveOrUpdateFlag==2)
					{
						alert("Form with the changes is forwarded successfully");
					}
					else
					{
						nomineeSavedOrNot = submitNomineeDtls(emp_Id);
						if (nomineeSavedOrNot == 1) {
								alert('All the details updated successfully');
						}
						
						if (nomineeSavedOrNot == 2 && serialNo > 1) {
								alert('All the details except Nominee details updated successfully');
						}
						if (nomineeSavedOrNot == 2 && serialNo == 1) {
								alert('All the details updated successfully');
						}
					}

				  	self.location.href = "ifms.htm?actionFlag=empChangesList&use=Chnage&user=asstDDO";
				}
			}
		}
	};

	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(url);
}


function getBGAndGRForPost()
{
	var postId = document.getElementById("cmbPost").value;
	if(postId != -1)
		{
			var uri="ifms.htm?actionFlag=getBGAndGRForPost";
			var url="postId="+postId;
			
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
							getDataStateChangedForGetBGAndGRForPost(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
		}
}

function getDataStateChangedForGetBGAndGRForPost(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var hidbillGroupId = "";
	var billGroupDesc = "";
	if(XmlHiddenValues[0].childNodes[0].firstChild != null)
		{
			hidbillGroupId = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			document.getElementById("hidbillGroupId").value = hidbillGroupId;
		}
	if(XmlHiddenValues[0].childNodes[1].firstChild != null)
		{
			billGroupDesc = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;	
			document.getElementById("txtBillGroup").value = billGroupDesc;
			document.getElementById("txtBillGroup").readOnly = true;
			document.getElementById("cmbBillGroupComboTr").style.display = 'none';
		}
	else
		{
			document.getElementById("txtBillGroup").value = ""; 
			document.getElementById("cmbBillGroupComboTr").style.display = 'inline';
		}
	
	var hidGROrderId = "";
	var txtGROrder = "";
	if(XmlHiddenValues[0].childNodes[3].firstChild != null)
		{
			txtGROrder = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
			document.getElementById("txtGROrder").value = txtGROrder;
			document.getElementById("txtGROrder").readOnly = true;
		}
	else
		{
			document.getElementById("txtGROrder").value = "";
		}
	
	if(XmlHiddenValues[0].childNodes[2].firstChild != null)
		{
			
			hidGROrderId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
			document.getElementById("hidGROrderId").value = hidGROrderId;
		}
}
// addred by sudarshan 
function forwardRequestAfterValidation(flag) {
	
	//Added by sudarhsan empconf	
	
	var commissionId = document.forms[0].elements['cmbPayCommission'].value.trim();
	 
		if(commissionId == '700005'){
			var curnsvnSelect = document.getElementById("cmbSvnBasic"); //7th Pay Basic
			if(curnsvnSelect!= undefined){
			var curntsvnText = curnsvnSelect.options[curnsvnSelect.selectedIndex].text;
			document.getElementById("txtBasicPay").value =curntsvnText;
			
			}
		}
	//Ended by sudarhsan empconf
	//alert("forwardRequestAfterValidation"+flag);
	forwardFlag = true ;
	
	if(serialNo>1)
	{
		if(calculatePercentShare())
		{
			 if(validateRegFormData())
			 {
				 //alert("inside validateRegFormData Single Para");
				SaveDataUsingAjax();
				
				if (!(nomineeSavedOrNot == 2 && serialNo > 1)) {
					ForwardRequest(emp_Id,flag);
				} else {
					alert('Form is saved but not forwarded due to incorrect Nominee Details');
				}
			 }
		}
	}
	else
	{
		//alert('Please Enter Nominee Details');
		//Lines added to make nominee details non mandatory
		if(validateRegFormData())
		 {
		 //alert("Else of Single PARA");
			SaveDataUsingAjax();
			ForwardRequest(emp_Id,flag);
		 }
	}
}
//ended by sudarshan

function getVacantPostsInOffice()
{
	//alert("inside getVacantPostsInOffice");
	var cmbOffice  = document.getElementById("cmbOffice").value;
	var cmbDesig  = document.getElementById("cmbDesignation").value;
	//alert("cmbOffice: "+cmbOffice+" cmbDesig : "+cmbDesig);
	if(cmbOffice == -1)
	{
		alert('Please select office.');
		return;
	}
	if(cmbDesig == -1)
	{
		alert('Please select designation.');
		return;
	}
	if(cmbOffice != -1 && cmbDesig != -1)
	{
		var uri="ifms.htm?actionFlag=getVacantPostsInOffice";
		//var url="cmbOffice="+cmbOffice;
		var url="cmbOffice="+cmbOffice+"&fromDDOAsst=Yes"+"&cmbDesig="+cmbDesig;

		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForGetVacantPostInOffice(myAjax,cmbOffice);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	else{
		document.getElementById("cmbPost").innerHTML = '';
		var optn = document.createElement("OPTION");
		optn.value = "-1";
		optn.text = "-- Select --";
		document.getElementById("cmbPost").options.add(optn);
	}
}

function getDataStateChangedForGetVacantPostInOffice(myAjax,cmbOffice)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
	document.getElementById('cmbPost').options.length = 0;
	var optn = document.createElement("OPTION");
	optn.value = "-1";
	optn.text = "-- Select --";
	document.getElementById("cmbPost").options.add(optn);
	
	for(var j = 0;j<XmlHiddenValues.length;j++)
	{
		var postName =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
		var postCode =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
		var optn = document.createElement("OPTION");
		optn.value = postCode;
		optn.text = postName;
		document.getElementById("cmbPost").options.add(optn);
	}
	
	var oldPost = document.getElementById("hidOldPost").value.trim();
	var oldPostDesc = document.getElementById("hidOldPostDesc").value.trim();
	
	var oldOffice = document.getElementById("oldOffice").value.trim();
	
	if(cmbOffice == oldOffice)
		{
			var optn = document.createElement("OPTION");
			optn.value = oldPost;
			optn.text = oldPostDesc;
			document.getElementById("cmbPost").options.add(optn);
		}
	
	document.getElementById("cmbPost").value = oldPost;
	document.getElementById("cmbPost").selected = 'selected';
}

function popReasonsForSalaryChange()
{
	xmlHttp = GetXmlHttpObject();
	
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}

	var cmbPayCommission = document.getElementById("cmbPayCommission").value.trim();
	var hidCurrPayCommission = document.getElementById("hidCurrPayCommission").value.trim();
	var CurrPayScale = document.getElementById("CurrPayScale").value.trim();
	
	if(hidCurrPayCommission == 700015 || hidCurrPayCommission == 700016)
	{
		if(cmbPayCommission == 700337)
			{
				alert('You cannot change pay commission to Consolidated Pay for 5th and 6th Pay Commission Employee');
				lBlFlagPCChangedToConsolidated = true;
				document.getElementById("cmbPayCommission").value = hidCurrPayCommission;
				GetScalePostfromDesgInChangePayScale();
				document.getElementById("cmbPayScale").value = CurrPayScale;
				return false;
			}
	}
	
	if(lBlFlagPCChangedToConsolidated)
		{
			return false;
		}
	
	if(cmbPayCommission == 700337)
		{	
			document.getElementById("cmbPayScale").value = -1;
			document.getElementById("cmbPayScale").disabled = true;
		}
	else
		{
			document.getElementById("cmbPayScale").disabled = false;
		}
	
	if(hidCurrPayCommission == 700337)
		{
			lBlFlagConsolidatedFlag = true;
		}
	
	if(cmbPayCommission == 700337)
		{
			document.getElementById("labelForReasonForPSChange").style.display = 'none' ;
			document.getElementById("cmbReasonForSalChange").readOnly = true;
			document.getElementById("cmbReasonForSalChange").disabled = true ;
			document.getElementById("labelForOtherReasonForPSChange").style.display = 'inline';
			document.getElementById("txtOtherReason").disabled = false ;
			document.getElementById("txtOtherReason").readOnly = false ;
		}
	else 
		{
			document.getElementById("labelForReasonForPSChange").style.display = 'inline' ;
			document.getElementById("cmbReasonForSalChange").readOnly = false;
			document.getElementById("cmbReasonForSalChange").disabled = false ;
			document.getElementById("labelForOtherReasonForPSChange").style.display = 'none';
			document.getElementById("txtOtherReason").disabled = true ;
			document.getElementById("txtOtherReason").readOnly = true ;
		}
	
	if(cmbPayCommission == 700015 || cmbPayCommission == 700016 || cmbPayCommission == 700339 || cmbPayCommission == 700340 || cmbPayCommission == 700345 || cmbPayCommission == 700338)
		{
				var uri="ifms.htm?actionFlag=popUpReasonsForSalaryChange&cmbPayCommission="+cmbPayCommission;
			
				xmlHttp.onreadystatechange = function()
				{
					if (xmlHttp.readyState == 4) {
						
						if (xmlHttp.status == 200) {
			
							var cmbReasonForSalChange = document.getElementById('cmbReasonForSalChange');
							cmbReasonForSalChange.options.length = 0;
							var optn = document.createElement("OPTION");
							optn.text = "-- Select --";
							optn.value = "-1";
							cmbReasonForSalChange.options.add(optn);
						
							XMLDoc = xmlHttp.responseXML.documentElement;
							var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
							var totalReasons = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			
								var count=1;
								while(count<=(2*totalReasons))
								{
									var optn = document.createElement("OPTION");
									optn.value = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
									optn.text = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
									document.getElementById("cmbReasonForSalChange").options.add(optn);
									count=count+2;
								}
						}
					}
				};
				
				xmlHttp.open("POST", uri, false);
				xmlHttp.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				xmlHttp.send(uri);
		}
}

function EnableSalChangesFields()
{
	var cmbPayScale =  document.getElementById("cmbPayScale").value.trim();
	var cmbPayCommission = document.getElementById("cmbPayCommission").value.trim();
	
	if(lBlFlagPCChangedToConsolidated)
	{
		return false;
	}

	if(cmbPayScale != -1)
	{
		document.getElementById("cmbReasonForSalChange").disabled = false ;
		//document.getElementById("txtWithEffectFromDate").readOnly = false ;
		document.getElementById("labelForReasonForPSChange").style.display = 'inline' ;
		//document.getElementById("labelForWEFDateForPSChange").style.display = 'inline' ;
		document.getElementById("labelForBasicPayForPSChange").style.display = 'inline' ;
	}
	else if(cmbPayCommission == 700337)
	{
		document.getElementById("cmbReasonForSalChange").disabled = true ;
		//document.getElementById("txtWithEffectFromDate").readOnly = false ;
		//document.getElementById("labelForWEFDateForPSChange").style.display = 'inline' ;
		document.getElementById("labelForBasicPayForPSChange").style.display = 'inline' ;
	}
	else
	{
		document.getElementById("cmbReasonForSalChange").disabled = false ;
		//document.getElementById("txtWithEffectFromDate").readOnly = true ;
		document.getElementById("labelForReasonForPSChange").style.display = 'none' ;
		//document.getElementById("labelForWEFDateForPSChange").style.display = 'none' ;
		document.getElementById("labelForBasicPayForPSChange").style.display = 'none' ;
	}
}

function checkForOtherReason()
{
	var reasonId = document.getElementById("cmbReasonForSalChange").value;
	if(reasonId == 700161 || reasonId == 700172)
	{
		document.getElementById("txtOtherReason").readOnly = true ;
		document.getElementById("labelForOtherReasonForPSChange").style.display = 'inline' ;
	}
	else
	{
		document.getElementById("txtOtherReason").readOnly = false ;
		document.getElementById("labelForOtherReasonForPSChange").style.display = 'none' ;
	}
}

function goBackToSearchList()
{
	var hidDcpsId = document.getElementById("hidDcpsId").value;
	var hidEmpName = document.getElementById("hidEmpName").value;
	var hidBirthDate = document.getElementById("hidBirthDate").value;
	var hidSevarthId = document.getElementById("hidSevarthId").value;
	var hidName = document.getElementById("hidName").value;
	
	self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="+hidDcpsId+"&txtEmployeeName="+hidEmpName+"&txtBirthDate="+hidBirthDate+"&sevarthId="+hidSevarthId+"&employeeName="+hidName;
}

function SaveChangedOfficeDtls()
{
	
	//Added by sudarhsan empconf	
	
	var commissionId = document.forms[0].elements['cmbPayCommission'].value.trim();
	 
		if(commissionId == '700005'){
			var curnsvnSelect = document.getElementById("cmbSvnBasic"); //7th Pay Basic
			if(curnsvnSelect!= undefined){
			var curntsvnText = curnsvnSelect.options[curnsvnSelect.selectedIndex].text;
			document.getElementById("txtBasicPay").value =curntsvnText;
			
			}
		}
	if(document.getElementById("btnSave") != null)
	{
		document.getElementById("btnSave").disabled = true;
	}
	showProgressbar();
	var cmbCadre =  document.getElementById("cmbCadre").value;
	if(cmbCadre == -1)
	{
		alert('Please select new cadre of employee');
		hideProgressbar();
		if(document.getElementById("btnSave") != null)
		{
			document.getElementById("btnSave").disabled = false;
		}
		return false;
	}
	
	var cmbDesignation = document.getElementById("cmbDesignation").value;
	if(cmbDesignation == -1)
	{
		alert('Please select new designation of employee');
		hideProgressbar();
		if(document.getElementById("btnSave") != null)
		{
			document.getElementById("btnSave").disabled = false;
		}
		return false;
	}
	var cmbPayCommission = document.getElementById("cmbPayCommission").value.trim();
	var hidCurrPayCommission = document.getElementById("hidCurrPayCommission").value.trim();
	
	if(cmbPayCommission == -1)
		{
			alert('Please select pay commission of employee');
			hideProgressbar();
			if(document.getElementById("btnSave") != null)
			{
				document.getElementById("btnSave").disabled = false;
			}
			return false;
		}
	
	if(cmbPayCommission == 700015 || cmbPayCommission == 700016 || cmbPayCommission == 700339 || cmbPayCommission == 700340 || cmbPayCommission == 700345 || cmbPayCommission == 700338)
		{
			var cmbPayScale = document.getElementById("cmbPayScale").value.trim();
			if(cmbPayScale == -1 || cmbPayScale == "" )
			{
				alert('Please select the Pay scale of employee');
				hideProgressbar();
				if(document.getElementById("btnSave") != null)
				{
					document.getElementById("btnSave").disabled = false;
				}
				return false;
			}
		}
	
	var CurrPayScale = document.getElementById("CurrPayScale").value.trim();
	
		
	if(cmbPayScale != CurrPayScale)
	{
		if(cmbPayCommission == 700015 || cmbPayCommission == 700016 || cmbPayCommission == 700339 || cmbPayCommission == 700340 || cmbPayCommission == 700345 || cmbPayCommission == 700338)
			{
				if(document.getElementById("cmbReasonForSalChange").value == -1)
				{
					alert('Please provide the reason for Pay Scale Change.');
					hideProgressbar();
					if(document.getElementById("btnSave") != null)
					{
						document.getElementById("btnSave").disabled = false;
					}
					return false;
				}
			}
		if(document.getElementById("cmbReasonForSalChange").value == '700161' ||  document.getElementById("cmbReasonForSalChange").value == '700172' || cmbPayCommission == 700337 )
		{
			if(document.getElementById("txtOtherReason").value.trim() == "")
			{
				alert('Please Provide Other Reason');
				hideProgressbar();
				if(document.getElementById("btnSave") != null)
				{
					document.getElementById("btnSave").disabled = false;
				}
				return false;
			}
		}
		if(document.getElementById("txtWithEffectFromDate").value.trim() == "")
		{
			alert('Please provide With Effect From Date for Pay Scale Change.');
			hideProgressbar();
			if(document.getElementById("btnSave") != null)
			{
				document.getElementById("btnSave").disabled = false;
			}
			return false;
		}
		
		if(cmbPayCommission == 700016)
		{
			var PIPB = document.forms[0].txtPayInPayBand.value.trim();
			var GradePay = document.forms[0].txtGradePay;
			if(PIPB == "" || PIPB == 0)
				{
					alert('Please enter Pay In Pay Band value');
					hideProgressbar();
					if(document.getElementById("btnSave") != null)
					{
						document.getElementById("btnSave").disabled = false;
					}
					return false;
				}
			if(GradePay == "")
				{
					alert('Please enter Grade Pay value');
					hideProgressbar();
					if(document.getElementById("btnSave") != null)
					{
						document.getElementById("btnSave").disabled = false;
					}
					return false;
				}
		}
		
		
		if(document.getElementById("cmbSvnBasic").value.trim() == "" || document.getElementById("cmbSvnBasic").value.trim() == "") 
		{
			alert('Please provide basic darshan');
			hideProgressbar();
			if(document.getElementById("btnSave") != null)
			{
				document.getElementById("btnSave").disabled = false;
			}
			return false;
		}
		else if (document.getElementById("txtBasicPay").value.trim() == "" || document.getElementById("txtBasicPay").value.trim() ==0) 
		{
			alert('Please provide basic pay');
			hideProgressbar();
			if(document.getElementById("btnSave") != null)
			{
				document.getElementById("btnSave").disabled = false;
			}
			return false;
		}

	}
	
	var cmbPost = "";
	if(document.getElementById("cmbPost") != null)
		{
			cmbPost = document.getElementById("cmbPost").value.trim();
		}
	var oldPost = document.getElementById("hidOldPost").value.trim();
	
	if(cmbPost == -1 || cmbPost == "")
		{
			alert('Please select post of employee');
			hideProgressbar();
			if(document.getElementById("btnSave") != null)
			{
				document.getElementById("btnSave").disabled = false;
			}
			return false;
		}
	
	if(cmbPost != -1 && cmbPost != oldPost)
		{
			if(document.getElementById("hidbillGroupId").value.trim() == "")
			{
				if(document.getElementById("cmbBillGroup") != null)
					{
						if(document.getElementById("cmbBillGroup").value.trim() == "" || document.getElementById("cmbBillGroup").value.trim() == -1)
							{
								alert('Please select the new bill group for the selected post');
								hideProgressbar();
								if(document.getElementById("btnSave") != null)
								{
									document.getElementById("btnSave").disabled = false;
								}
								return false;
							}
					}
			}
			if(document.getElementById("txtWithEffectFromDate").value.trim() == "")
			{
				alert('Please provide With Effect From Date for Pay Scale Change.');
				hideProgressbar();
				if(document.getElementById("btnSave") != null)
				{
					document.getElementById("btnSave").disabled = false;
				}
				return false;
			}
		}
	
	if(document.getElementById("txtWithEffectFromDate").value.trim() == "")
	{
		alert('Please provide With Effect From Date for Pay Scale Change.');
		hideProgressbar();
		if(document.getElementById("btnSave") != null)
		{
			document.getElementById("btnSave").disabled = false;
		}
		return false;
	}

	var txtAuthorityLetterNo = document.getElementById("txtAuthorityLetterNo").value ;
	var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value ;

	if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate == "")
	{
		alert('Please fill the Authority Details.') ;
		hideProgressbar();
		if(document.getElementById("btnSave") != null)
		{
			document.getElementById("btnSave").disabled = false;
		}
		return false;
	}

	
	
	var uri = 'ifms.htm?actionFlag=changePayDtlsOfEmp';
	var hidDcpsEmpId = document.getElementById("hidDcpsEmpId").value.trim();
	var hidOldPost = document.getElementById("hidOldPost").value.trim();
    var url = runForm(0) ; 
    url = url + "&hidDcpsEmpIdPassed="+hidDcpsEmpId+"&hidOldPost="+hidOldPost;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForChangePayDtls(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
}

function getDataStateChangedForChangePayDtls(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	if(document.getElementById("btnSave") != null)
	{
		document.getElementById("btnSave").disabled = false;
	}
			
	var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(test_Id)
	{
		var hidDcpsId = document.getElementById("hidDcpsId").value;
		var hidEmpName = document.getElementById("hidEmpName").value;
		var hidBirthDate = document.getElementById("hidBirthDate").value;
		var hidSevarthId = document.getElementById("hidSevarthId").value;
		var hidName = document.getElementById("hidName").value;
		alert("Pay Details are saved.");
		hideProgressbar();
		self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="+hidDcpsId+"&txtEmployeeName="+hidEmpName+"&txtBirthDate="+hidBirthDate+"&sevarthId="+hidSevarthId+"&employeeName="+hidName;
	}	
}

function valueRange(){
	var percentage = document.getElementById("selperesentage").value;
	
	if(percentage <0 || percentage > 100)
	{
		alert("Please Enter O to 100 Value.");
		document.getElementById("selperesentage").focus();
		}	
}

function validateBasicPay(){
	
	var index = document.forms[0].cmbPayCommission.selectedIndex ;
	var payCommission= document.forms[0].cmbPayCommission[index].text;
	index = document.forms[0].cmbPayScale.selectedIndex ;
	var payScale = document.forms[0].cmbPayScale[index].text ;
	var basicPay = document.getElementById("txtBasicPay").value;
	var payArray;
	var count=0;
	
	if((basicPay != null && basicPay != "") && (document.getElementById("cmbPayScale").value != null && document.getElementById("cmbPayScale").value != -1) )
	{
		if(payCommission == '6th Pay Commission'){
			var tempArray = payScale.split("(");
			payArray = tempArray[0].split("-");
			var payIn = basicPay - (tempArray[1].split(")"))[0];
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();

			}
			if(parseInt(payIn)< parseInt(payArray[0]) || parseInt(payIn) >parseInt(payArray[1])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				document.getElementById("txtBasicPay").focus();
				return;
			}
			
		}else if(payCommission == '5th Pay Commission'){
			payArray = payScale.split("-");
		
			var temp;
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();
			}
			for(var j=0;j<payArray.length;j++){
				if(payArray[j] == 'EB')
				{
					payArray.splice(j,1); 
				}
			}
			if(basicPay == payArray[payArray.length - 1]){
				return;
			}
			for(var i=0;i<payArray.length;i+=2){
		
				if(i != 0){
					if(parseInt(basicPay)>parseInt(payArray[i])){
						count = i;
						continue;
					}
					
					else{
						var start = payArray[i-2];
						var variance = payArray[i-1];
	
						if((basicPay-start)%variance != 0){
							alert("The Basic Pay is not in accordance with the Pay Scale selected");
							document.getElementById("txtBasicPay").value = '';
						}
						return;
					}
				}
				else{
					if(parseInt(basicPay)<parseInt(payArray[i])){

						alert("The Basic Pay is not in accordance with the Pay Scale selected");
						document.getElementById("txtBasicPay").value = '';
						return;
					}
				}
				count = i;
			}
			if(count!=0 && parseInt(basicPay)>parseInt(payArray[count])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				return;
			}
		}
	}
}


</script>

<hdiits:form name="frmChangePayDetails" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="csrfToken" value="${csrfToken}"/>
<input type="hidden" id="hidDcpsId" value="${resValue.hidDcpsId}"/>
<input type="hidden" id="hidEmpName" value="${resValue.hidEmpName}"/>
<input type="hidden" id="hidBirthDate" value="${resValue.hidBirthDate}"/>
<input type="hidden" id="hidDcpsEmpId" value="${resValue.DcpsEmpId}"/>
<input type="hidden" id="hidSevarthId" value="${resValue.hidSevarthId}"/>
<input type="hidden" id="hidName" value="${resValue.hidName}"/>


<fieldset class="tabstyle"><legend> 
	                  <fmt:message key="CMN.PAYDTLSOFSELECTEDEMP" bundle="${dcpsLables}"></fmt:message></legend>
<table cellpadding = "3" width = "100%">
	<tr>
		<td><fmt:message key="CMN.EMPLOYEENAME"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><input type="text" id="txtEmpName" size="28" name="txtEmpName"  value="${resValue.EmpName}" readOnly="readOnly" />
		</td>
	</tr>
	<tr>
		<td><fmt:message key="CMN.DCPSID"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><input type="text" id="txtDcpsId" size="28" name="txtDcpsId"  value="${resValue.DcpsId}" readOnly="readOnly" /></td>
	</tr>
	
	<tr>
		<td><fmt:message key="CMN.OFFICENAME"
			bundle="${dcpsLables}"></fmt:message></td>
		<td>
		<input type="hidden" id="oldOffice" value="${resValue.CurrOff}">
		<select name="cmbOffice"
			id="cmbOffice" style="width: 100%; " >
			
		<c:forEach var="OffList" items="${resValue.OfficeList}" >
																<c:choose>
																	<c:when test="${resValue.CurrOff == OffList.id}">
																	<option value="${OffList.id}" title="${OffList.desc}" selected="selected"><c:out 
																			value="${OffList.desc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${OffList.id}" title="${OffList.desc}" ><c:out 
																			value="${OffList.desc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
									</c:forEach>
									</select></td>
	</tr>
	
	<tr>
		<td><fmt:message key="CMN.CADRE"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><select name="cmbCadre"
			id="cmbCadre" style="width: 100%; " onchange="">
			<c:forEach var="CadreList" items="${resValue.CadreList}" >
				<c:choose>
																	<c:when test="${resValue.CurrCadre == CadreList.id}">
																<option value="${CadreList.id}" title="${CadreList.desc}" selected="selected"><c:out 
																						value="${CadreList.desc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${CadreList.id}" title="${CadreList.desc}" ><c:out 
																						value="${CadreList.desc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
																				
									</c:forEach></select></td>
	</tr>
	
	<tr>
		<td><fmt:message key="CMN.DESIGNATION"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><select name="cmbDesignation"
			id="cmbDesignation" style="width: 80%; " onchange="getVacantPostsInOffice();">
			<c:forEach var="DesignationList" items="${resValue.DesignationList}" >
			<c:choose>
																	<c:when test="${resValue.CurrDesig == DesignationList.id}">
																<option value="${DesignationList.id}" selected="selected"><c:out 
																						value="${DesignationList.desc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																<option value="${DesignationList.id}" ><c:out 
																						value="${DesignationList.desc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
																				
									</c:forEach></select></td>
	</tr>
	
	<tr>
		<td><fmt:message key="CMN.PAYCOMMISSION"
			bundle="${dcpsLables}"></fmt:message></td>
		<td>
		<input type="hidden" id="hidCurrPayCommission" value="${resValue.CurrPayComm}"/>
		<select name="cmbPayCommission"
			id="cmbPayCommission" style="width: 80%; " onchange="GetScalePostfromDesg();popReasonsForSalaryChange();EnableSalChangesFields();checkForPIPBAndGradePayInChangePayScale();"><option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="listPayCommission" items="${resValue.listPayCommission}" >
				<c:choose>
																				<c:when test="${resValue.CurrPayComm == listPayCommission.lookupId}">
																				<option value="${listPayCommission.lookupId}" selected="selected"><c:out 
																						value="${listPayCommission.lookupDesc}"></c:out></option>
																				</c:when>
																				<c:otherwise>
																					<c:choose>
																						<c:when test="${listPayCommission.lookupId=='700015'|| listPayCommission.lookupId=='700016'|| listPayCommission.lookupId=='700337' || listPayCommission.lookupId=='10001198186'  || listPayCommission.lookupId=='700005'}"> 
																				<option value="${listPayCommission.lookupId}" ><c:out 
																						value="${listPayCommission.lookupDesc}"></c:out></option>
																						</c:when>
																						</c:choose>
																				</c:otherwise>
																			</c:choose>															
									</c:forEach></select></td>
	</tr>
	
	<tr>
		<td><fmt:message key="CMN.PAYSCALE"
			bundle="${dcpsLables}"></fmt:message></td>
		<td>
		<input type ="hidden" name ="CurrPayScale" id="CurrPayScale" value="${resValue.CurrPayScale}" />
		<input type ="hidden" name ="cmbLevel" id="cmbLevel" value="${resValue.cmbLevel}" />
		
		<select name="cmbPayScale"
			id="cmbPayScale" style="width: 80%; " 
			onchange="GetSvnBasicData();EnableSalChangesFields();checkForPIPBAndGradePayInChangePayScale();validatePIPBAndChangeBasicInChangePayScale();" 
			${varConsolidatedBasicPayForCombo}><option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="PayScaleList" items="${resValue.PayScaleList}" >
			<c:choose>                  
					<c:when test="${resValue.CurrPayScale == PayScaleList.id && resValue.payCommission != '700005'}">
						<option value="${PayScaleList.id}" selected="selected"><c:out
							value="${PayScaleList.desc}"></c:out></option>
					</c:when>
					<c:when test="${resValue.cmbLevel == PayScaleList.id}"> 
						<option value="${PayScaleList.id}" selected="selected"><c:out
							value="${PayScaleList.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${PayScaleList.id}" ><c:out
							value="${PayScaleList.desc}"></c:out></option>
					</c:otherwise>


				</c:choose>
									
									</c:forEach>
									</select>
		<label class="mandatoryindicator" id="lableMandatoryForPayscale"  style="display:none"  >*</label>
		</td>
	</tr>
	
	<tr id="sevenLabel"${varSevenBasicRow} style="display: none;">
			<td width="15%" align="left" id="sevenlabel"><fmt:message key="CMN.7THPAYBASIC" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left">
			
			
			<input type="hidden" name="sevenPcBasic" id="sevenPcBasic" value ="${resValue.sevenPcBasic}" />
			${resValue.SevenPcLevel}
			 <select name="cmbSvnBasic" id="cmbSvnBasic" style="width: 65%" onChange="" ${varDisabled}>
					<option value="-1"> <fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
							
			<c:forEach var="SvnPcBasic" items="${resValue.SvnPcBasic}">
				<c:choose> 
					<c:when test="${resValue.lstSvnPcBasic1 ==  SvnPcBasic.desc}">
						<option value="${SvnPcBasic.id}"  selected="selected"><c:out
							value="${SvnPcBasic.desc}" ></c:out></option>
					</c:when>
				  
					<c:otherwise>
						<option value="${SvnPcBasic.id}"><c:out
							value="${SvnPcBasic.desc}"></c:out></option>
					</c:otherwise>


				</c:choose>

			</c:forEach>
				
			</select>
		 <label class="mandatoryindicator" ${varLabelDisabled}></label></td>
		</tr>
	
	
	
	
	<tr>
			<td align="left"><fmt:message key="CMN.REASONSFORSALARYCHANGE"
					bundle="${dcpsLables}"></fmt:message></td>
				
					
			<td align="left"><select name="cmbReasonForSalChange"
								id="cmbReasonForSalChange" style="width: 65%" > 
									<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
	 		<c:forEach var="reasonForSalChange" items="${resValue.lListReasonsForSalaryChangeMst}">
				<c:choose>
                   
					<c:when test="${resValue.rsnForChange == reasonForSalChange.lookupId}">
						<option value="${reasonForSalChange.lookupId}"  selected="selected"><c:out
							value="${reasonForSalChange.lookupName}" ></c:out></option>
					</c:when>
				  
					<c:otherwise>
						<option value="${reasonForSalChange.lookupId}"><c:out
							value="${reasonForSalChange.lookupName}"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach> 
							</select>
							<label class="mandatoryindicator" id="labelForReasonForPSChange" style="display: none">*</label>
			</td>
			
			<c:if test="${resValue.rsnForChange!='700161'}">
				<c:set var="displaystatus" value="none;"></c:set>
			</c:if> 
			
			<td align="left"  style="display: ${displaystatus}"  ><fmt:message key="CMN.OTHERREASON" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" id="otrRsnTD"  style="display: ${displaystatus}"  ><input type="text" id="txtOtherReason"
										size="30" name="txtOtherReason" value="" onblur=""  />
			<label class="mandatoryindicator" id="labelForOtherReasonForPSChange" ${varAsterisksShownForConsolidatedBasicPay}>*</label>							
			</td>
	</tr>
	
	<tr>
	<td align="left"><fmt:message key="CMN.WITHEFFECTFROMDATE"
					bundle="${dcpsLables}"></fmt:message></td>
	<td align="left"><input type="text"
				name="txtWithEffectFromDate" id="txtWithEffectFromDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtWithEffectFromDate);" value=""  /> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtWithEffectFromDate",375,570)'
				style="cursor: pointer;" />
	<label class="mandatoryindicator" id="labelForWEFDateForPSChange" >*</label>			
	</td>
	</tr>
	
	<tr id = "payInPayBandAndGradePayRow" ${varPayInPayBandAndGradePayRow} >
	
		
		<td width="15%" align="left"><fmt:message key="CMN.PAYINPAYBAND"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%" align="left"><input type="text"
			id="txtPayInPayBand" size="30" name="txtPayInPayBand"
			onBlur="validatePIPBAndChangeBasicInChangePayScale();"
			onkeypress="digitFormat(this);" value="${resValue.PIPB}" />
		<label class="mandatoryindicator" id="lableMandatoryForPayInPayBand">*</label>
		</td>
		
		
		<td width="15%" align="left"><fmt:message key="CMN.GRADEPAY"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%" align="left"><input type="text"
			name="txtGradePay" id="txtGradePay" maxlength="7" readonly="readonly"
			onBlur="validatePIPBAndChangeBasicInChangePayScale();"
			onkeypress="digitFormat(this);" ${varDisabled}
			onBlur=""
			value="${resValue.GradePay}" /> <label class="mandatoryindicator"
			id="lableMandatoryForGradePay">*</label></td>		
				
		
	</tr>
	
	<tr id="basicPayRowId"${varBasicRow}> 
		<td><fmt:message key="CMN.BASIC"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><input type="text" id="txtBasicPay" size="15" name="txtBasicPay" maxlength="7" value="${resValue.CurrBasic}" onblur="validateBasicPay();" onkeypress="digitFormat(this);" />
		 <label class="mandatoryindicator" id="labelForBasicPayForPSChange">*</label>
		</td>
		</tr> 
		
		
		
		<tr>
				<td><fmt:message key="CMN.pers"
					bundle="${dcpsLables}"></fmt:message></td>
				<td>
					<input type="text" id="selperesentage" size="15" name="selperesentage" maxlength="7" onblur="valueRange();" onkeypress="digitFormat(this);" />
					
			   </td>		
		</tr>
	
	<tr>
		<td><fmt:message key="CMN.POST"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><select name="cmbPost"
			id="cmbPost" style="width: 80%; " onchange="getBGAndGRForPost();"><option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		<option value="${resValue.PostId}" selected="selected" ><c:out value="${resValue.PostDesc}"></c:out></option>
		<c:forEach var="PostList" items="${resValue.PostList}" >
																			
																				<option value="${PostList.id}" ><c:out 
																						value="${PostList.desc}"></c:out></option>
																						
																						
																				
																			
		</c:forEach></select>
		<input type="hidden" id="hidOldPost" value="${resValue.PostId}"/>
		<input type="hidden" id="hidOldPostDesc" value="${resValue.PostDesc}"/>
		</td>
		
		<td><fmt:message key="CMN.MEDICAL"
			bundle="${dcpsLables}"></fmt:message></td>
		<td>
			<input type="text" id="selmedical" size="15" name="selmedical" maxlength="10" onblur=""  onkeypress="digitFormat(this);" />
			
	   </td>
	</tr>
	
	<tr>
		<td><fmt:message key="CMN.BILLGROUP"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><input type="text" name="txtBillGroup"  id="txtBillGroup" size="60" readonly="readonly" value="${resValue.BGDesc}"/>
			<input type="hidden" name="hidbillGroupId" id="hidbillGroupId" />
		</td>
		<td><fmt:message key="CMN.GRORDER"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><input type="text" name="txtGROrder"  id="txtGROrder" size="60" readonly="readonly" value="${resValue.GROrderDesc}"/>
			<input type="hidden" name="hidGROrderId" id="hidGROrderId" />
		</td>
	</tr>
	
	<tr style="display:none" id="cmbBillGroupComboTr" >
		<td><fmt:message key="CMN.BILLGROUP"
			bundle="${dcpsLables}"></fmt:message></td>
		<td><select name="cmbBillGroup"
			id="cmbBillGroup" style="width: 100%; " onchange=""><option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		<c:forEach var="BillGroupList" items="${resValue.BillGroupList}" >
																				<option value="${BillGroupList.id}" ><c:out 
																						value="${BillGroupList.desc}"></c:out></option>
		</c:forEach></select></td>
		
	
	</tr>
	<tr>
			
	<td><fmt:message key="CMN.TRAVEL"
			bundle="${dcpsLables}"></fmt:message></td>
		<td>
			<input type="text" id="seltravel" size="15" name="seltravel" maxlength="10" onblur=""  onkeypress="digitFormat(this);" />
			
	   </td>
	</tr>
	
</table>
</fieldset>

<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AUTHORITYDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		<table width="38%" align="left" cellpadding = "0" border="0">
		<tr>
			<td width="14%" align="left">Authority Letter No.</td>
			<td width="20%" align="left" colspan = "3"><input type="text"
				id="txtAuthorityLetterNo" style="text-transform: uppercase" size="30"
				name="txtAuthorityLetterNo" value="" onblur="" />
				<label class="mandatoryindicator">*</label></td>
		</tr>
		<tr>
			<td width="14%" align="left">Letter Date</td>
			
			<td width="20%" align="left" colspan = "3"><input type="text"
				id="txtAuthorityLetterDate" style="text-transform: uppercase" size="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				name="txtAuthorityLetterDate" value="" /><img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtAuthorityLetterDate", 375, 570)'
				style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label></td>
		</tr>
		</table>
				
</fieldset>
<br>
			<div align="center">
				<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="SaveChangedOfficeDtls();"/>
				<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="goBackToSearchList();"/>
			</div>


</hdiits:form>
