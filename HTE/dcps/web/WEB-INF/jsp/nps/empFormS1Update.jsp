<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ajax" uri="http://ajaxtags.org/tags/ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DDOCode" value="${resValue.DDOCode}"></c:set>
<c:set var="DdoRegNO" value="${resValue.DdoRegNO}"></c:set>
<c:set var="DtoCode" value="${resValue.DtoCode}"></c:set>
<c:set var="EmpSevarthId" value="${resValue.EmpSevarthId}"></c:set>
<c:set var="EmpFName" value="${resValue.EmpFName}"></c:set>
<c:set var="EmpMName" value="${resValue.EmpMName}"></c:set>
<c:set var="EmpLName" value="${resValue.EmpLName}"></c:set>
<c:set var="RelationList" value="${resValue.RelationList}"></c:set>
<c:set var="empDetails" value="${resValue.empDetails}"></c:set>
<c:set var="BankLists" value="${resValue.BankLists}"></c:set>
<c:set var="StateLists" value="${resValue.StateLists}"></c:set>
<c:set var="payScaleLists" value="${resValue.payScaleLists}"></c:set>
<c:set var="currentDate" value="${resValue.curretDate}"></c:set>
<c:set var="DOJ" value="${resValue.DOJ}"></c:set>
<c:set var="dsgnName" value="${resValue.dsgnName}"></c:set>
<c:set var="DcpsId" value="${resValue.DcpsId}"></c:set>
<c:set var="titleList" value="${resValue.titleList}"></c:set>

<c:set var="nomineePK1" value="${resValue.nomineePK1}"></c:set>
<c:set var="nomineeName1" value="${resValue.nomineeName1}"></c:set>
<c:set var="nomineePK2" value="${resValue.nomineePK2}"></c:set>
<c:set var="nomineeName2" value="${resValue.nomineeName2}"></c:set>
<c:set var="nomineePK3" value="${resValue.nomineePK3}"></c:set>
<c:set var="nomineeName3" value="${resValue.nomineeName3}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="PhotoFlag" value="${resValue.PhotoFlag}"></c:set>
<c:set var="SignFlag" value="${resValue.SignFlag}"></c:set>
<c:set var="signSize" value="${resValue.signSize}"></c:set>
<c:set var="photoSize" value="${resValue.photoSize}"></c:set>
<style>
.radiomainTb input[type=radio] td {
	margin-right: 20px;
	border: none;
	padding: 5px
}
.error {
    color: red;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    animation: blink-animation 1s steps(5, start) infinite;
        -webkit-animation: blink-animation 1s steps(5, start) infinite;
}

      @keyframes blink-animation {
        to {
          visibility: hidden;
        }
      }
      @-webkit-keyframes blink-animation {
        to {
          visibility: hidden;
        }
      }
</style>
<script type="text/javascript">

var _keySize = 256;
var _ivSize = 128;
var _iterationCount = 1989;

function generateKey(salt, passPhrase) {
	return CryptoJS.PBKDF2(passPhrase, CryptoJS.enc.Hex.parse(salt), {
		keySize : _keySize / 32,
		iterations : _iterationCount
	})
}

function encryptWithIvSalt(salt, iv, passPhrase, plainText) {
	var key = generateKey(salt, passPhrase);
	var encrypted = CryptoJS.AES.encrypt(plainText, key, {
		iv : CryptoJS.enc.Hex.parse(iv)
	});
	return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
}

function decryptWithIvSalt(salt, iv, passPhrase, cipherText) {
	var key = generateKey(salt, passPhrase);
	var cipherParams = CryptoJS.lib.CipherParams.create({
		ciphertext : CryptoJS.enc.Base64.parse(cipherText)
	});
	var decrypted = CryptoJS.AES.decrypt(cipherParams, key, {
		iv : CryptoJS.enc.Hex.parse(iv)
	});
	return decrypted.toString(CryptoJS.enc.Utf8);
}

/*
 * function encrypt(passPhrase, plainText) { // alert("Welcome"); var iv =
 * CryptoJS.lib.WordArray.random(_ivSize / 8).toString(CryptoJS.enc.Hex); var
 * salt = CryptoJS.lib.WordArray.random(_keySize /
 * 8).toString(CryptoJS.enc.Hex); var ciphertext = encryptWithIvSalt(salt, iv,
 * passPhrase, plainText); return salt + iv + ciphertext; }
 */

function encrypt(passPhrase, plainText) {
	// alert("Welcome");
	var iv = CryptoJS.lib.WordArray.random(_ivSize / 8).toString(
			CryptoJS.enc.Hex);
	var salt = CryptoJS.lib.WordArray.random(_keySize / 8).toString(
			CryptoJS.enc.Hex);
	var ciphertext = encryptWithIvSalt(salt, iv, passPhrase, plainText);

	return salt + iv + ciphertext;
}

function decrypt(passPhrase, cipherText) {
	var ivLength = _ivSize / 4;
	var saltLength = keySize / 4;
	var salt = cipherText.substr(0, saltLength);
	var iv = cipherText.substr(saltLength, ivLength);
	var encrypted = cipherText.substring(ivLength + saltLength);
	return decryptWithIvSalt(salt, iv, passPhrase, encrypted);
}


function resetForm()
{
	//alert('inside reset');
	document.getElementById("permanentAddFlatNo").readOnly=true;
	document.getElementById("permanentAddBuilding").readOnly=true;
	document.getElementById("permanentAddTaluka").readOnly=true;
	document.getElementById("permanentAddDist").readOnly=true;
	document.getElementById("permanentAddPin").readOnly=true;
	document.formS1Form.reset();
}

function radioButtonValue(objButton){
	
	var values;
		for(var i = 0; i < objButton.length; i++){
		    if(objButton[i].checked){
		    	values = objButton[i].value;
		    }
		
		}
		return values;
}
function submitForm1(){

	var salutation = document.getElementById("salutation").selected.trim();
	alert(salutation);
	}

function submitForm()
{

	//document.getElementById("btnSubmit").disabled=true;

	var salutation = document.getElementById("salutation").value.trim();
	var sevarthId = document.getElementById("sevarthId").value.trim();
	var ddoName = document.getElementById("ddoName").value.trim();
// var cmbSalutation
	var empFName = document.getElementById("empFName").value.trim();
	var empFName = document.getElementById("empFName").value.trim();
	var empMName = document.getElementById("empMName").value.trim();
	var empLName = document.getElementById("empLName").value.trim();
	
	var empFatherFName = document.getElementById("empFatherFName").value.trim();
	var empFatherLName = document.getElementById("empFatherLName").value.trim();
	var empMotherName = document.getElementById("empMotherName").value.trim();
    var empDob	= document.getElementById("empDob").value.trim();
// var empGender;
	
	var panNo=document.getElementById("panNo").value.trim();
	var aadharNo=document.getElementById("uidNo").value.trim();
	
	var UID1Encrypted = encrypt("Message", panNo);
	console.log("input panNo " + UID1Encrypted);
	var UID2Encrypted = encrypt("Message", aadharNo);
	console.log("input aadharNo " + UID2Encrypted);
	

	document.getElementById("panNo").value = UID1Encrypted;
	document.getElementById("uidNo").value = UID2Encrypted;
	
	
	var DOJ= document.getElementById("doj").value.trim();
	var superAnnDate=document.getElementById("superAnnDate").value.trim();
	var empClass= document.getElementById("empClass").value.trim();
	var empDept= document.getElementById("empDept").value.trim();
	var empMinistry= document.getElementById("empMinistry").value.trim();
	var basicSalary= document.getElementById("basicSalary").value.trim();
	var payScale= document.getElementById("payScale").value.trim();
	

	var dsgnName= document.getElementById("dsgnName").value.trim();
	var DcpsId= document.getElementById("DcpsId").value.trim();
	var DtoCode=document.getElementById("DtoCode").value.trim();
	var DDORegNo=document.getElementById("DDORegNo").value.trim();
	var presentAddFlatNo = document.getElementById("presentAddFlatNo").value.trim();
	var presentAddBuilding = document.getElementById("presentAddBuilding").value.trim();
	var presentAddTaluka = document.getElementById("presentAddTaluka").value.trim();
	var presentAddLandmark = document.getElementById("presentAddLandmark").value.trim();
	var presentAddDist = document.getElementById("presentAddDist").value.trim();
	var presentAddState = document.getElementById("presentAddState").value.trim();
	var presentAddCountry = document.getElementById("presentAddCountry").value.trim();
	var presentAddPin = document.getElementById("presentAddPin").value.trim();
	
	var permanentAddFlatNo = document.getElementById("permanentAddFlatNo").value.trim();
	var permanentAddBuilding = document.getElementById("permanentAddBuilding").value.trim();
	var permanentAddTaluka = document.getElementById("permanentAddTaluka").value.trim();
	var permanentAddLandmark = document.getElementById("permanentAddLandmark").value.trim();
	var permanentAddDist = document.getElementById("permanentAddDist").value.trim();
	var permanentAddState = document.getElementById("permanentAddState").value.trim();
	var permanentAddCountry = document.getElementById("permanentAddCountry").value.trim();
	var permanentAddPin = document.getElementById("permanentAddPin").value.trim();
	
	//var phoneSTDCode = document.getElementById("phoneSTDCode").value.trim();
	var phoneNo = document.getElementById("phoneNo").value.trim();
	var mobileNo = document.getElementById("mobileNo").value.trim();
	var emailId = document.getElementById("emailId").value.trim();
	// var smsSubFlag=Array
	//var emailSubFlag
	var smsSubFlag=document.getElementsByName("smsSubFlag");
	var emailSubFlag=document.getElementsByName("emailSubFlag");
	var noOfNominee=document.getElementsByName("noOfNominee");
	var nominee1Name = document.getElementById("nominee1Name").value.trim();
	var nominee1DOB = document.getElementById("nominee1DOB").value.trim();
	var nominee1Relation = document.getElementById("nominee1Relation").value.trim();//nominee1 relation drop down
	var nominee1Percent = document.getElementById("nominee1Percent").value.trim();
	var nominee1Guardian = document.getElementById("nominee1Guardian").value.trim();
	var nominee1InvalidCondition = document.getElementById("nominee1InvalidCondition").value.trim();
	var nominee1Address = document.getElementById("nominee1Address").value.trim();
	
	var nominee2Name = document.getElementById("nominee2Name").value.trim();
	var nominee2DOB = document.getElementById("nominee2DOB").value.trim();
	var nominee2Relation = document.getElementById("nominee2Relation").value.trim();//nominee2 relation drop down
	var nominee2Percent = document.getElementById("nominee2Percent").value.trim();
	var nominee2Guardian = document.getElementById("nominee2Guardian").value.trim();
	var nominee2InvalidCondition = document.getElementById("nominee2InvalidCondition").value.trim();
	var nominee2Address = document.getElementById("nominee2Address").value.trim();
	
	var nominee3Name = document.getElementById("nominee3Name").value.trim();
	var nominee3DOB = document.getElementById("nominee3DOB").value.trim();
	var nominee3Relation = document.getElementById("nominee3Relation").value.trim();//nominee3 relation drop down
	var nominee3Percent = document.getElementById("nominee3Percent").value.trim();
	var nominee3Guardian = document.getElementById("nominee3Guardian").value.trim();
	var nominee3InvalidCondition = document.getElementById("nominee3InvalidCondition").value.trim();
	var nominee3Address = document.getElementById("nominee3Address").value.trim();

	if(salutation==0){
		alert('Please select salutation.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
		}
	if(sevarthId == '')
	{
		alert('Please enter Sevarth Id.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(ddoName == '')
	{
		alert('Please enter ddo name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	
	if(empFName == '')
	{
		alert('Please enter employee first name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	/* if(empMName == '')
	{
		alert('Please enter employee middle name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empLName == '')
	{
		alert('Please enter employee last name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	 */
	if(empFatherFName == '')
	{
		alert('Please enter employee\'s father first name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empFatherLName == '')
	{
		alert('Please enter employee\'s father last name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empMotherName == '')
	{
		alert('Please enter employee\'s Mother  name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empDob == '')
	{
		alert('Please enter employee\'s date of birth.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(!empDobAgeValidate(empDob,'Please enter employee\'s valid date of birth. It\'s under age.') || 
			!compareDates(document.getElementById('empDob'),document.getElementById('currentDate'),'Date of Birth should be less than current date.','<')  )
	{
		 
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	var empGender=document.getElementsByName("empGender");
	//var empGender=document.querySelector('input[name="empGender"]:checked');
		
	if(empGender.checked==false)
	{
		alert('Please select Gender.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}	
	
var maritalStatus=document.getElementsByName("maritalStatus");
//var maritalStatus=document.querySelector( 'input[name="maritalStatus"]:checked');
	
	if(maritalStatus.checked==false)
	{
		alert('Please select marital status.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(panNo == '')
	{
		alert('Please enter pan Number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(panNo.length !=10)
	{
		alert('Please enter correct pan Number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	/* if(aadharNo == '')
	{
		alert('Please enter aadhar number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	} */
	if(aadharNo != '' && aadharNo.length!=12)
	{
		alert('Please enter correct aadhar number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(DOJ == '')
	{
		alert('Please enter employee\'s date of joining date.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(!compareDates(document.getElementById('empDob'),document.getElementById('doj'),'Date of Joining should be greater than DOB!','<'))
		{ document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(superAnnDate==''){
		alert('Please enter employee\'s date of retirement.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
		}

	if(!compareDates(document.getElementById('doj'),document.getElementById('superAnnDate'),'Date of Retirment should be greater than DOJ!','<')){
		document.getElementById("btnSubmit").disabled=false;
		return false;
		}
	
	if(empClass == '' )
	{
		alert('Please enter correct employee class.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empDept == '')
	{
		alert('Please enter employee department.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empMinistry == '')
	{
		alert('Please enter correct employee Department ministry.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(basicSalary == '')
	{
		alert('Please enter employee basic salary.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(basicSalary == '')
	{
		alert('Please enter employee pay scale.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	
	if(presentAddFlatNo == '' || presentAddFlatNo.length>30)
	{
		alert('Please enter present Flat/ Unit No, Block No. and length not more than 30 ');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

 
	
	if(presentAddBuilding == '' || presentAddBuilding.length>30)
	{
		alert('Please enter present Name of premise/Building/ Village. and length not more than 30');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddTaluka == '' || presentAddTaluka.length>30)
	{
		alert('Please enter present Area/ Locality/taluka.and length not more than 30');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	/* if(presentAddLandmark == '')
	{
		alert('Please enter present LandMark.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	} */
	
	if(presentAddDist == '')
	{
		alert('Please enter present District/TOWN/CITY.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddState == '')
	{
		alert('Please enter present STATE/UNION territory.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddCountry == '')
	{
		alert('Please enter present Country.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddPin == '')
	{
		alert('Please enter present PIN CODE.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(presentAddPin.length!=6)
	{
		alert('Please enter valid Pin Code. It should consist of 6 digits');
		document.getElementById("btnSubmit").disabled=false;
	  	return false;
	}
	if(presentAddPin.charAt(0)!='4')
	{
		alert('Please enter correct Pin code.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(!(document.getElementById('presentSamePerm').checked) && !(document.getElementById('presentNotSamePerm').checked)) 
	{
		alert('Please select whether present address is same as permanent address.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(permanentAddFlatNo == '')
	{
		alert('Please enter permanent Flat/ Unit No, Block No.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddBuilding == '')
	{
		alert('Please enter permanent Name of premise/Building/ Village.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddTaluka == '')
	{
		alert('Please enter permanent Area/ Locality/taluka.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	/* if(permanentAddLandmark == '')
	{
		alert('Please enter permanent LandMark.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	} */
	
	if(permanentAddDist == '')
	{
		alert('Please enter permanent District/TOWN/CITY.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddState == '')
	{
		alert('Please enter permanent STATE/UNION territory.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddCountry == '')
	{
		alert('Please enter permanent Country.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddPin == '')
	{
		alert('Please enter permanent PIN CODE.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	/* if(phoneNo == '')
	{
		alert('Please enter phone number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	} */
	if(phoneNo != ''){
		if(phoneNo.length != 10)
		{
			alert('Please enter 8 digit phone number.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
	}
	if(mobileNo == '')
	{
		alert('Please enter mobile number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	// var smsSubFlag=Array
	//var emailSubFlag
		
	if(smsSubFlag.checked==false)
	{
		alert('Please select subscribe to SMS Alerts.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(emailId == '')
	{
		alert('Please enter email Id.');
		return false;
	} 

	if(emailSubFlag.checked==false)
	{
		alert('Please select subscribe to EMAIL Alerts.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(!(document.getElementById('1nominee').checked) && !(document.getElementById('2nominee').checked)&& !(document.getElementById('3nominee').checked)) 
	{
		alert('Please select number of nominees.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	var noOfNomeenies=null;
	if(document.getElementById('1nominee').checked)
	{
		noOfNomeenies='1';
	}
	if(document.getElementById('2nominee').checked)
	{
		noOfNomeenies='2';
	}
	if(document.getElementById('3nominee').checked)
	{
		noOfNomeenies='3';
	}
	//alert('noOfNomeenies: '+noOfNomeenies);
	if(noOfNomeenies=='1')
	{
		
		if(nominee1Name == '' || nominee1Name.length>30)
		{
			alert('Please enter nominee 1 name  and  length should be 30.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		
		if(nominee1DOB == '')
		{
			alert('Please enter nominee 1 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Relation == '-1')
		{
			alert('Please enter nominee 1 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Percent == '')
		{
			alert('Please enter valid nominee 1 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee1Percent != 100)
		{
			alert('Please enter nominee percentage 100% for single nominee.');
			document.getElementById("nominee1Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Guardian == '')
		{
			alert('Please enter nominee 1 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1InvalidCondition == '')
		{
			alert('Please enter nominee 1 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		nominee2Name='NA';
		nominee2DOB=null;
		nominee2Relation='NA';
		nominee2Percent='0';
		nominee2Guardian='NA';
		nominee2InvalidCondition='NA';
		nominee3Name='NA';
		nominee3DOB=null;
		nominee3Relation='NA';
		nominee3Percent='0';
		nominee3Guardian='NA';
		nominee3InvalidCondition='NA';
	}
	if(noOfNomeenies=='2')
	{
		
		if(nominee1Name == '')
		{
			alert('Please enter nominee 1 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1DOB == '')
		{
			alert('Please enter nominee 1 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Relation == '-1')
		{
			alert('Please enter nominee 1 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Percent == '')
		{
			alert('Please enter valid nominee 1 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Guardian == '')
		{
			alert('Please enter nominee 1 guardian nae in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1InvalidCondition == '')
		{
			alert('Please enter nominee 1 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee1Address == '')
		{
			alert('Please enter nominee 1 address.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		

		
		if(nominee2Name == '')
		{
			alert('Please enter nominee 2 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2DOB == '')
		{
			alert('Please enter nominee 2 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Relation == '-1')
		{
			alert('Please enter nominee 2 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Percent == '')
		{
			alert('Please enter valid nominee 2 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Guardian == '')
		{
			alert('Please enter nominee 2 guardian nae in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2InvalidCondition == '')
		{
			alert('Please enter nominee 2 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee2Address == '')
		{
			alert('Please enter nominee 2 address.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if((Number(nominee1Percent)== 0))
		{
			alert('Nominee 1 percentage can not be zero.');
			document.getElementById("nominee1Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee2Percent)== 0))
		{
			alert('Nominee 2 percentage can not be zero.');
			document.getElementById("nominee2Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee1Percent)+Number(nominee2Percent))!=100)
		{
			document.getElementById("nominee1Percent").value='';
			document.getElementById("nominee2Percent").value='';
			alert('Please enter nominee percentage equal to 100 %.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		nominee3Name='NA';
		nominee3DOB=null;
		nominee3Relation='NA';
		nominee3Percent='0';
		nominee3Guardian='NA';
		nominee3InvalidCondition='NA';
	}

	if(noOfNomeenies=='3')
	{
		
		if(nominee1Name == '')
		{
			alert('Please enter nominee 1 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1DOB == '')
		{
			alert('Please enter nominee 1 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}

		if(nominee1DOB!='' && !empDobAgeValidate(nominee1DOB,'Please enter nominee 1 guardian details(name), for minor nominee.')    )
		{
			document.getElementById("nominee1Guardian").readOnly=false;
			document.getElementById("nominee1Guardian").value='';
			return false;
		}
		
		 
		
		if(nominee1Relation == '-1')
		{
			alert('Please enter nominee 1 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Percent == '')
		{
			alert('Please enter valid nominee 1 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Guardian == '')
		{
			alert('Please enter nominee 1 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}

		
		if(nominee1InvalidCondition == '')
		{
			alert('Please enter nominee 1 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}

		if(nominee1Address == '')
		{
			alert('Please enter nominee 1 address.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		
		if(nominee2Name == '')
		{
			alert('Please enter nominee 2 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2DOB == '')
		{
			alert('Please enter nominee 2 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}

		if(nominee2DOB!='' && !empDobAgeValidate(nominee1DOB,'Please enter nominee 2 guardian details(name), for minor nominee.')    )
		{
			document.getElementById("nominee2Guardian").readOnly=false;
			document.getElementById("nominee2Guardian").value='';
			return false;
		}


		
		if(nominee2Relation == '-1')
		{
			alert('Please enter nominee 2 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Percent == '')
		{
			alert('Please enter valid nominee 2 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Guardian == '')
		{
			alert('Please enter nominee 2 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2InvalidCondition == '')
		{
			alert('Please enter nominee 2 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee2Address == '')
		{
			alert('Please enter nominee 2 address.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		

		
		if(nominee3Name == '')
		{
			alert('Please enter nominee 3 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3DOB == '')
		{
			alert('Please enter nominee 3 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee3DOB!='' && !empDobAgeValidate(nominee1DOB,'Please enter nominee 3 guardian details(name), for minor nominee.'))
		{
			document.getElementById("nominee3Guardian").readOnly=false;
			document.getElementById("nominee3Guardian").value='';
			return false;
		}
		
		if(nominee3Relation == '-1')
		{
			alert('Please enter nominee 3 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3Percent == '')
		{
			alert('Please enter valid nominee 3 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3Guardian == '')
		{
			alert('Please enter nominee 3 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3InvalidCondition == '')
		{
			alert('Please enter nominee 3 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee3Address == '')
		{
			alert('Please enter nominee 3 address.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if((Number(nominee1Percent)== 0))
		{
			alert('Nominee 1 percentage can not be zero.');
			document.getElementById("nominee1Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee2Percent)== 0))
		{
			alert('Nominee 2 percentage can not be zero.');
			document.getElementById("nominee2Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee3Percent)== 0))
		{
			alert('Nominee 3 percentage can not be zero.');
			document.getElementById("nominee3Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee1Percent)+Number(nominee2Percent)+Number(nominee3Percent))!=100)
		{
			document.getElementById("nominee1Percent").value='';
			document.getElementById("nominee2Percent").value='';
			document.getElementById("nominee3Percent").value='';
			alert('Please enter nominee percentage equal to 100 %.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
	}

	var bankAcntNo=document.getElementById("bankAcntNo").value;
	var empBankName=document.getElementById("empBankName").value;
	var empBankBranchName=document.getElementById("empBankBranchName").value;
	var empBankBranchAddress=document.getElementById("empBankBranchAddress").value;
	var empBankPinCode=document.getElementById("empBankPinCode").value;
	var bankstate=document.getElementById("empBankstate").value;
	var IfscCode=document.getElementById("IfscCode").value;
  
	if(bankAcntNo == '')
	{
		alert('Please enter bank account number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empBankName == '' || empBankName==0)
	{
		alert('Please select bank name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empBankBranchName == '')
	{
		alert('Please enter bank branch name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empBankBranchAddress == '')
	{
		alert('Please enter bank branch addres.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empBankPinCode == '')
	{
		alert('Please enter bank pin code.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empBankPinCode != '' && empBankPinCode.length != 6)
	{
		alert('Pin code should be 6 digit only.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(bankstate == '' || bankstate==0)
	{
		alert('Please enter bank state.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	/* if(IfscCode == '' )
	{
		alert('Please enter bank ifsc code.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}  */
	if(IfscCode != '' &&  IfscCode.length != 11)
	{
		alert('Ifsc code should be 11 character.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	var dispNameFlag=document.getElementsByName("dispNameFlag");
	var dobProofFlag=document.getElementsByName("dobProof");
	var eduQualFlag=document.getElementsByName("eduQual");
	var incomeRangeFlag=document.getElementsByName("incomeRange");

/* var dispNameFlag=document.querySelector('input[name="dispNameFlag"]:checked');
var dobProof=document.querySelector('input[name="dobProof"]:checked');
var eduQual=document.querySelector('input[name="eduQual"]:checked');
var incomeRange=document.querySelector('input[name="incomeRange"]:checked'); */
if(dispNameFlag.checked==false)
{
	alert('Please select display name on pran card.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(dobProofFlag.checked==false)
{
	alert('Please select date of birth proof document.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(eduQualFlag.checked==false)
{
	alert('Please select education qualification.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(incomeRangeFlag.checked==false)
{
	alert('Please select income range.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
var hdnDDOCode=	 document.getElementById("hdnDDOCode").value;
var url = 'ifms.htm?actionFlag=saveFormS1Dtls';
var noOfNominee=radioButtonValue(document.getElementsByName("noOfNominee"));
var Gender=radioButtonValue(empGender);
var Marital=radioButtonValue(maritalStatus);
var dispName=radioButtonValue(dispNameFlag);
var dobProof=radioButtonValue(dobProofFlag);
var eduQual=radioButtonValue(eduQualFlag);
var incomeRange=radioButtonValue(incomeRangeFlag);
var smsSub=radioButtonValue(smsSubFlag);
var emailSub=radioButtonValue(emailSubFlag);
//for photo annd signature
if(document.getElementById("photoAttachementID").value == '' )
{
	alert('photo attachment ID   should not blank');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(document.getElementById("photoAttachementSrNo").value == '' )
{
	alert('Photo sr no   should not blank');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(document.getElementById("signAttachementID").value == '' )
{
	alert('Signature attachment ID   should not blank');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(document.getElementById("signAttachementSrNo").value == '' )
{
	alert('Signature sr no should not blank');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
var photoAttachementID=document.getElementById("photoAttachementID").value.trim();
var photoAttachementSrNo=document.getElementById("photoAttachementSrNo").value.trim();
var signAttachementID=document.getElementById("signAttachementID").value.trim();
var signAttachementSrNo=document.getElementById("signAttachementSrNo").value.trim();
if(document.getElementById("photoAttachementID").value == '' )
{
	alert('photo attachment ID is should not blank');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}

var uri= "&sevarthId="+sevarthId+"&DtoCode="+DtoCode+"&DDORegNo="+DDORegNo+"&hdnDDOCode="+hdnDDOCode+"&ddoName=" +ddoName+"&salutation="+salutation+
"&empFName=" +empFName+"&empMName" +empMName+"&empLName=" +empLName+"&empFatherFName=" +empFatherFName+
"&empFatherLName=" +empFatherLName+"&empMotherName="+empMotherName+"&empDob="+empDob+
"&empGender="+Gender+"&empMaritalStatus="+Marital+
"&panNo="+panNo+"&aadharNo="+aadharNo+"&DOJ="+DOJ+"&superAnnDate="+superAnnDate+"&empClass="+empClass+
"&empDept="+empDept+"&empMinistry="+empMinistry+"&basicSalary="+basicSalary+"&payScale="+payScale+	
"&dsgnName="+dsgnName+"&DcpsId="+DcpsId+
"&presentAddFlatNo="+presentAddFlatNo+"&presentAddBuilding="+presentAddBuilding+
"&presentAddTaluka="+presentAddTaluka+"&presentAddLandmark"+presentAddLandmark+"&presentAddDist="+presentAddDist+
"&presentAddState="+presentAddState+"&presentAddCountry="+presentAddCountry+
"&presentAddPin="+presentAddPin+
"&permanentAddFlatNo="+permanentAddFlatNo+"&permanentAddBuilding="+permanentAddBuilding+
"&permanentAddTaluka="+permanentAddTaluka+"&permanentAddLandmark"+permanentAddLandmark+"&permanentAddDist="+permanentAddDist+
"&permanentAddState="+permanentAddState+"&permanentAddCountry="+permanentAddCountry+
"&permanentAddPin="+permanentAddPin+
"&phoneNo="+phoneNo+"&mobileNo="+mobileNo+"&emailId="+emailId+"&smsSubFlag="+smsSub+"&emailSubFlag="+emailSub+
"&noOfNominee"+noOfNominee+
"&nominee1Name="+nominee1Name+"&nominee1DOB="+nominee1DOB+
"&nominee1Relation="+nominee1Relation+"&nominee1Percent="+nominee1Percent+
"&nominee1Guardian="+nominee1Guardian+"&nominee1InvalidCondition="+nominee1InvalidCondition+
"&nominee2Name="+nominee2Name+"&nominee2DOB="+nominee2DOB+
"&nominee2Relation="+nominee2Relation+"&nominee2Percent="+nominee2Percent+
"&nominee2Guardian="+nominee2Guardian+"&nominee2InvalidCondition="+nominee2InvalidCondition+
"&nominee3Name="+nominee3Name+"&nominee3DOB="+nominee3DOB+
"&nominee3Relation="+nominee3Relation+"&nominee3Percent="+nominee3Percent+
"&nominee3Guardian="+nominee3Guardian+"&nominee3InvalidCondition="+nominee3InvalidCondition+	
"&bankAcntNo="+bankAcntNo+"&empBankName="+empBankName+"&empBankBranchName="+empBankBranchName+"&empBankBranchAddress="+empBankBranchAddress+
"&empBankPinCode="+empBankPinCode+"&bankstate="+bankstate+"&IfscCode="+IfscCode+	
"&dispNameFlag="+dispName+"&dobProofFlag="+dobProof+"&eduQualFlag="+eduQual+"&incomeRangeFlag="+incomeRange+	
"&nominee1Address="+nominee1Address+"&nominee2Address="+nominee2Address+"&nominee3Address="+nominee3Address+
"&photoAttachementID="+photoAttachementID+"&photoAttachementSrNo="+photoAttachementSrNo+"&signAttachementID="+signAttachementID+
"&signAttachementSrNo="+signAttachementSrNo+"&flag=sevarthId";
//alert(uri);
 
	document.formS1Form.action= url+uri;
	document.formS1Form.submit();
	showProgressbar("Saving Form S1 Form Details...");
	//alert("Data Save successfully");
       
 

}

function copyPresentPermAdd()
{
	var presentFlat=document.getElementById("presentAddFlatNo").value.trim();
	var presentBuilding=document.getElementById("presentAddBuilding").value.trim();
	var presentTaluka=document.getElementById("presentAddTaluka").value.trim();
	var presentAddLandmark=document.getElementById("presentAddLandmark").value.trim();
	var presentDist=document.getElementById("presentAddDist").value.trim();
	var presentPin=document.getElementById("presentAddPin").value.trim();
	var presentAddState=document.getElementById("presentAddState").value.trim();
	var presentAddCountry=document.getElementById("presentAddCountry").value.trim();
	if((presentFlat=='' || presentBuilding=='' || presentTaluka=='' || presentDist=='' || presentPin==''))
	{
		alert('Please fill all mandatory fields in present address.');
		document.getElementById('presentSamePerm').checked=false;
		document.getElementById('presentNotSamePerm').checked=false;
		return false;
	}
	if(document.getElementById('presentSamePerm').checked) 
		{
			//alert('present address is same as permanent');
		document.getElementById("permanentAddFlatNo").readOnly=true;
		document.getElementById("permanentAddBuilding").readOnly=true;
		document.getElementById("permanentAddTaluka").readOnly=true;
		document.getElementById("permanentAddDist").readOnly=true;
		document.getElementById("permanentAddState").disabled=true;
		document.getElementById("permanentAddCountry").readOnly=true;
		document.getElementById("permanentAddPin").readOnly=true;
		document.getElementById("permanentAddLandmark").readOnly=true;

		document.getElementById("permanentAddFlatNo").value=presentFlat;
		document.getElementById("permanentAddBuilding").value=presentBuilding;
		document.getElementById("permanentAddTaluka").value=presentTaluka;
		document.getElementById("permanentAddLandmark").value=presentAddLandmark;
		document.getElementById("permanentAddDist").value=presentDist;
		document.getElementById("permanentAddState").value=presentAddState;
		document.getElementById("permanentAddCountry").value=presentAddCountry;
		document.getElementById("permanentAddPin").value=presentPin;
		}
	else if(document.getElementById('presentNotSamePerm').checked) 
		{
		
		//alert('present address is not same as permanent');
		  document.getElementById("permanentAddFlatNo").readOnly=false;
		document.getElementById("permanentAddBuilding").readOnly=false;
		document.getElementById("permanentAddTaluka").readOnly=false;
		document.getElementById("permanentAddLandmark").readOnly=false;
		document.getElementById("permanentAddDist").readOnly=false;
		document.getElementById("permanentAddState").disabled=false;
		document.getElementById("permanentAddPin").readOnly=false;  

		document.getElementById("permanentAddFlatNo").value='';
		document.getElementById("permanentAddBuilding").value='';
		document.getElementById("permanentAddTaluka").value='';
		document.getElementById("permanentAddLandmark").value='';
		document.getElementById("permanentAddDist").value='';
		document.getElementById("permanentAddState").value='';
		document.getElementById("permanentAddPin").value='';
		}
	return false;
}
function onlyNos(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    catch (err) {
        alert(err.Description);
    }
}
  	
/* if(!compareDates(document.getElementById('txtBirthDate'),document.getElementById('txtJoiningDate'),'Date Of Joining should be greater than DOB!','<') ||
 */		//!compareDates(document.getElementById('txtJoiningDate'),document.getElementById('currDate1'),'Date of Joining should be less than current date.','<') ||
		//!compareDates(document.getElementById('joinDate'),document.getElementById('txtJoiningDate'),'Date of Joining should be greater than 01/01/2005.','<') ||
		//!compareDates(document.getElementById('txtJoiningDate'),document.getElementById('txtJoinParentDeptDate'),'Date of joining parent dept should be greater than Joining Date','<') ||
		//!compareDates(document.getElementById('txtJoiningDate'),document.getElementById('txtJoinPostDate'),'Date of joining current post should be greater than date of joining','<') ||
		/*!compareDates(document.getElementById('txtJoiningDate'),document.getElementById('txtJoinCadreDate'),'Date Of Cadre should be greater than date of joining','<') ||*/
		//!validateDate(document.getElementById('txtJoiningDate')) ||
		/* !validateDate(document.getElementById('txtBirthDate')) ||
		!compareDates(document.getElementById('txtBirthDate'),document.getElementById('currDate1'),'Date of Birth should be less than current date.','<') ||
		!isName(document.getElementById('txtName'),'This field should not contain any special characters or digits.') ||
	 */// !emailValidate(document.getElementById('txtEmailId')) ||
		/*!validateDate(document.getElementById('txtJoinCadreDate')) ||*/
		//!validateDate(document.getElementById('txtJoinParentDeptDate')) ||
		//!compareDates(document.getElementById('txtJoinParentDeptDate'),document.getElementById('currDate1'),'Date of joining parent dept should be less than current date.','<') ||
		//!compareDates(document.getElementById('txtJoinParentDeptDate'),document.getElementById('txtJoinPostDate'),'Date of joining current post should be greater than date of joining','<') ||
		/* !validateDate(document.getElementById('txtJoinPostDate')) ||
		!compareDates(document.getElementById('txtJoinPostDate'),document.getElementById('currDate1'),'Date of joining post should be less than current date.','<'))
		 *///!compareDates(document.getElementById('txtJoinParentDeptDate'),document.getElementById('txtJoinPostDate'),'Date of joining current post should be greater than date of initial appointment in parent department','<'))
		/* {
	return false;
}  */

function validatePresentPin(pin)
{
	var pinCode=pin.value;
	if(pinCode.length < 6)
	{
		alert('Pin code should be of length 6.\nPlease enter correct Pin code.');
		pin.value='';
		return false;
	}	

		if(pinCode.charAt(0)!='4')
	{
		alert('Please enter correct Pin code.');
		pin.value='';
		return false;
	}
	return false;
}

function validatePermanentPin(pin)
{
			var pinCode=pin.value;
			if(pinCode.length < 6)
			{
				alert('Pin code should be of length 6.\nPlease enter correct Pin code.');
				pin.value='';
				return false;
			}	
			if(pinCode.charAt(0)=='0')
			{
				alert('Please enter correct Pin code.');
				pin.value='';
				return false;
			}
			return false;
		}

function validatePhoneSTDCode(phonestdcode)
{
	var PhoneSTDCode=phonestdcode.value;
	if(PhoneSTDCode.length != 3)
	{
		alert('Phone STD code should be of length 3.\nPlease enter correct Phone STD code.');
		phonestdcode.value='';
		return false;
	}	
	if(PhoneSTDCode.charAt(0)!='0')
	{
		alert('Please enter correct Phone STD code.');
		phonestdcode.value='';
		return false;
	}
	return true;
}
function validatePhoneNo(phone)
{
	var phoneNo=phone.value;
	if(phoneNo.length != 10)
	{
		alert('Phone number should be of length 8.\nPlease enter correct Phone Number.');
		phone.value='';
		return false;
	}	
	if(phoneNo.charAt(0)=='0')
	{
		alert('Please enter correct phone number.');
		phone.value='';
		return false;
	}
	return true;
}		
function validateMobileNo(mobile)
{
	var mobileNo=mobile.value;
	if(mobileNo.length != 10)
	{
		alert('Mobile number should be of length 10.\nPlease enter correct Mobile Number.');
		mobile.value='';
		return false;
	}	
	if(mobileNo.charAt(0)!='7' && mobileNo.charAt(0)!='8' && mobileNo.charAt(0)!='9')
	{
		alert('Please enter correct Mobile number.');
		mobile.value='';
		return false;
	}
	return true;
}
function validateEmail(email)
{
	//alert('email validation');
	var emailId=email.value;
	var atpos = emailId.indexOf("@");
    var dotpos = emailId.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=emailId.length) 
	{
        alert("Please enter valid email id.");
        email.value='';
        return false;
    }	
    return false;
}
function validateDOBAndMinorGuardianDtls(dob,noOfNominee)
{
	 
	var dateString=dob.value.split("/"); 
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - dateString[2];
    var mon = today.getMonth() - dateString[1];
    var day = today.getDate() - dateString[0];
    if (mon < 0 || mon === 0 && today.getDate() < birthDate.getDate()) {
    	age--;
    }
    if (mon < 0) {
    mon += 12;
    }
    if (day < 0) {
    day += 30;
    }

    if(age<18)
	{
		document.getElementById("nominee"+noOfNominee+"Guardian").readOnly=false;
		alert('Please enter guardian details(name), for minor nominee.');
		document.getElementById("nominee"+noOfNominee+"Guardian").value='';
	}
	else
	{
		document.getElementById("nominee"+noOfNominee+"Guardian").readOnly=true;
		document.getElementById("nominee"+noOfNominee+"Guardian").value='NA';
	} 
	
	/* var currentDate=document.getElementById("currentDate");
	var parsedDOB=dob.value.split('/');
	var curd = new Date(curyear,curmon-1,curday);
	var cald = new Date(parsedDOB[2],parsedDOB[1]-1,parsedDOB[0]);
	var diff = Date.UTC(curyear,curmon,curday,0,0,0) - Date.UTC(parsedDOB[2],parsedDOB[1],parsedDOB[0],0,0,0);
	var dife = datediff(curd,cald);
	var age=dife[0];
	if(age<18)
	{
		document.getElementById("nominee"+noOfNominee+"Guardian").readOnly=false;
		alert('Please enter guardian details(name), for minor nominee.');
		document.getElementById("nominee"+noOfNominee+"Guardian").value='';
	}
	else
	{
		document.getElementById("nominee"+noOfNominee+"Guardian").readOnly=true;
		document.getElementById("nominee"+noOfNominee+"Guardian").value='NA';
	} */
}


function enableNomineeFields(nominees)
{
	
	if(nominees == '1')
	{
	 

		document.getElementById("nominee1Name").readOnly=false;
		document.getElementById("nominee1DOB").readOnly=false;
		document.getElementById("nominee1Relation").disabled=false;//main drop down
		//document.getElementById("nominee1RelationSelect").readOnly=;//selected option
		document.getElementById("nominee1Percent").readOnly=false;
		document.getElementById("nominee1Guardian").readOnly=false;
		document.getElementById("nominee1InvalidCondition").readOnly=false;
		document.getElementById("nominee1Address").readOnly=true;

		//for nominee 2
		document.getElementById("nominee2Name").value='NA';
		document.getElementById("nominee2DOB").value='';
		document.getElementById("nominee2RelationSelect").selected=true;
		document.getElementById("nominee2Percent").value='0';
		document.getElementById("nominee2Guardian").value='NA';
		document.getElementById("nominee2InvalidCondition").value='NA';
		document.getElementById("nominee1Address").value='NA';
		document.getElementById("nominee2Name").readOnly=true;
		document.getElementById("nominee2DOB").readOnly=true;
		document.getElementById("nominee2Relation").disabled=true;//main drop down
		//document.getElementById("nominee2RelationSelect").readOnly=;//selected option
		document.getElementById("nominee2Percent").readOnly=true;
		document.getElementById("nominee2Guardian").readOnly=true;
		document.getElementById("nominee2InvalidCondition").readOnly=true;
		document.getElementById("nominee2Address").readOnly=true;
		document.getElementById("nominee2Address").value='NA';

		//for nominee 3
		document.getElementById("nominee3Name").value='NA';
		document.getElementById("nominee3DOB").value='';
		document.getElementById("nominee3RelationSelect").selected=true;
		document.getElementById("nominee3Percent").value='0';
		document.getElementById("nominee3Guardian").value='NA';
		document.getElementById("nominee3InvalidCondition").value='NA';
		document.getElementById("nominee3Address").value='NA';
		document.getElementById("nominee3Name").readOnly=true;
		document.getElementById("nominee3DOB").readOnly=true;
		document.getElementById("nominee3Relation").disabled=true;//main drop down
		//document.getElementById("nominee3RelationSelect").readOnly=;//selected option
		document.getElementById("nominee3Percent").readOnly=true;
		document.getElementById("nominee3Guardian").readOnly=true;
		document.getElementById("nominee3InvalidCondition").readOnly=true;
		document.getElementById("nominee3Address").readOnly=true;
	}


	if(nominees == '2')
	{
		

		document.getElementById("nominee1Name").readOnly=false;
		document.getElementById("nominee1DOB").readOnly=false;
		document.getElementById("nominee1Relation").disabled=false;//main drop down
		//document.getElementById("nominee1RelationSelect").readOnly=;//selected option
		document.getElementById("nominee1Percent").readOnly=false;
		document.getElementById("nominee1Guardian").readOnly=false;
		document.getElementById("nominee1InvalidCondition").readOnly=false;
		document.getElementById("nominee1Address").readOnly=false;
		document.getElementById("nominee1Address").value='';
		//for nominee 2
		
		document.getElementById("nominee2Name").value='';
		document.getElementById("nominee2DOB").value='';
		//document.getElementById("nominee2RelationSelect").selected=true;
		document.getElementById("nominee2Percent").value='0';
		document.getElementById("nominee2Guardian").value='NA';
		document.getElementById("nominee2InvalidCondition").value='NA';
		document.getElementById("nominee2Address").value='';
		document.getElementById("nominee2Name").readOnly=false;
		document.getElementById("nominee2DOB").readOnly=false;
		document.getElementById("nominee2Relation").disabled=false;//main drop down
		//document.getElementById("nominee2RelationSelect").readOnly=;//selected option
		document.getElementById("nominee2Percent").readOnly=false;
		document.getElementById("nominee2Guardian").readOnly=true;
		document.getElementById("nominee2InvalidCondition").readOnly=false;
		document.getElementById("nominee2Address").readOnly=false;

		//for nominee 3
		document.getElementById("nominee3Name").value='NA';
		document.getElementById("nominee3DOB").value='';
		document.getElementById("nominee3RelationSelect").selected=true;
		document.getElementById("nominee3Percent").value='0';
		document.getElementById("nominee3Guardian").value='NA';
		document.getElementById("nominee3InvalidCondition").value='NA';
		document.getElementById("nominee3Address").value='NA';
			
		document.getElementById("nominee3Name").readOnly=true;
		document.getElementById("nominee3DOB").readOnly=true;
		document.getElementById("nominee3Relation").disabled=true;//main drop down
		//document.getElementById("nominee3RelationSelect").readOnly=;//selected option
		document.getElementById("nominee3Percent").readOnly=true;
		document.getElementById("nominee3Guardian").readOnly=true;
		document.getElementById("nominee3InvalidCondition").readOnly=true;
		document.getElementById("nominee3Address").readOnly=true;
	}


	if(nominees == '3')
	{
		document.getElementById("nominee1Name").readOnly=false;
		document.getElementById("nominee1DOB").readOnly=false;
		document.getElementById("nominee1Relation").disabled=false;//main drop down
		//document.getElementById("nominee1RelationSelect").readOnly=;//selected option
		document.getElementById("nominee1Percent").readOnly=false;
		document.getElementById("nominee1Guardian").readOnly=false;
		document.getElementById("nominee1InvalidCondition").readOnly=false;
		document.getElementById("nominee1Address").readOnly=false;
		document.getElementById("nominee2Name").readOnly=false;
		document.getElementById("nominee2DOB").readOnly=false;
		document.getElementById("nominee2Relation").disabled=false;//main drop down
		//document.getElementById("nominee2RelationSelect").readOnly=;//selected option
		document.getElementById("nominee2Percent").readOnly=false;
		document.getElementById("nominee2Guardian").readOnly=true;
		document.getElementById("nominee2InvalidCondition").readOnly=false;
		document.getElementById("nominee2Address").readOnly=false;

		//for nominee 3
		document.getElementById("nominee3Name").value='';
		document.getElementById("nominee3DOB").value='';
		//document.getElementById("nominee3RelationSelect").selected=true;
		document.getElementById("nominee3Percent").value='0';
		document.getElementById("nominee3Guardian").value='NA';
		document.getElementById("nominee3InvalidCondition").value='NA';
		document.getElementById("nominee3Address").value='';
		
		document.getElementById("nominee3Name").readOnly=false;
		document.getElementById("nominee3DOB").readOnly=false;
		document.getElementById("nominee3Relation").disabled=false;//main drop down
		//document.getElementById("nominee3RelationSelect").readOnly=;//selected option
		document.getElementById("nominee3Percent").readOnly=false;
		document.getElementById("nominee3Guardian").readOnly=true;
		document.getElementById("nominee3InvalidCondition").readOnly=false;
		document.getElementById("nominee3Address").readOnly=false;
	}
	
}
function empDobAgeValidate(dob,alterStr){
	/* var optimizedDob = dob.replace(/-/g, "/");
	var Dob = new Date(optimizedDob);
	var currentDate = new Date().toJSON().slice(0,10)+' 01:00:00';
	var age = ~~((Date.now(currentDate) - Dob) / (31557600000));
	if(age < 18) {
		alert(alterStr);
     	    return false;
        }else{
	    return true;
	}  */

var dateString=dob.split("/"); 
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - dateString[2];
    var mon = today.getMonth() - dateString[1];
    var day = today.getDate() - dateString[0];
    if (mon < 0 || mon === 0 && today.getDate() < birthDate.getDate()) {
    	age--;
    }
    if (mon < 0) {
    mon += 12;
    }
    if (day < 0) {
    day += 30;
    }

    if (age < 18 || age > 100) {
    	alert(alterStr);
     	    return false; 
    }else{
	    return true;
	} 

}
function onlyAlphabets(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 32))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}


function dblQuotesRmvr(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode != 34)&&(charCode != 39)&&(charCode != 96))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}
function panNoValidation()
{
	var panValue=document.getElementById('panNo').value;
	var regex1=/^[A-Za-z]{5}\d{4}[A-Za-z]{1}$/;  

	if(regex1.test(panValue)== false && panValue.length != 0){  
		alert('Please enter valid PAN No');  
		document.getElementById('panNo').focus();
		return false;
	}
	return true;
}

function chkPANalreadyExists()
{
	///alert('Inside chkPANalreadyExists');
	  var panNo = document.getElementById("panNo").value.trim();
	  var sevarthId = document.getElementById("sevarthId").value.trim();
	 /// alert("sevarthId"+sevarthId);
	  
		var uri = 'ifms.htm?actionFlag=chkPANalreadyExists'; 
		var url = 'panNo='+panNo + '&sevarthId='+sevarthId;
		///var url = 'panNo='+panNo;

		var myAjax = new Ajax.Request(uri, 
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
			        	getResponsePANNo(myAjax,panNo);
						
					},
			        onFailure: function()
			        			{  
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
}


function getResponsePANNo(myAjax,panNo){

	//alert("hiii checdksdsd");
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var empName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
///	alert('checkFlag'+empName);
	//alert("checkFlag"+checkFlag);
	if(checkFlag=="Y")
	{
		alert('Entered PAN No:'+panNo+' is already present for the employee :'+empName+' in system. Please enter correct PAN number.');
		 document.getElementById("panNo").value = "";
		///clearAllData();
		return false;
	}
	return true;
} 

function clearAllData()
{
	 document.getElementById("panNo").value = "";
   
    
}


</script>



<script type="text/javascript">
var startyear = "1910";
var endyear = "2100";
var dat = new Date();
var curday = dat.getDate();
var curmon = dat.getMonth()+1;
var curyear = dat.getFullYear();


function checkleapyear(datea)
{
	if(datea.getYear()%4 == 0)
	{
		if(datea.getYear()% 10 != 0)
		{
			return true;
		}
		else
		{
			if(datea.getYear()% 400 == 0)
				return true;
			else
				return false;
		}
	}
	return false; 
} 

function DaysInMonth(Y, M) 
{
	with (new Date(Y, M, 1, 12)) 
	{
		setDate(0);
		return getDate();
	} 
} 

function datediff(date1, date2) 
{
	var y1 = date1.getFullYear(), m1 = date1.getMonth(), d1 = date1.getDate(),
			 y2 = date2.getFullYear(), m2 = date2.getMonth(), d2 = date2.getDate();
	if (d1 < d2) 
	{
		m1--;
		d1 += DaysInMonth(y2, m2);
	}
	if (m1 < m2) 
	{
		y1--;
		m1 += 12;
	}
	return [y1 - y2, m1 - m2, d1 - d2]; 
} 
</script>
 
<c:forEach items="${empDetails}" var="empDetail">

	<hdiits:form name="formS1Form" id="formS1Form"
		encType="multipart/form-data" validate="true" method="post" action="">
		<input type="hidden" id="hdnEmpSevarthId" name="hdnEmpSevarthId"
			value="${EmpSevarthId}">
		<input type="hidden" id="hdnDDOCode" name="hdnDDOCode"
			value="${empDetail[5]}">
		<input type="hidden" id="currentDate" name="currentDate"
			value="${currentDate}">
		<input type="hidden" id="dsgnName" name="dsgnName" value="${dsgnName}">
		<input type="hidden" id="DcpsId" name="DcpsId" value="${DcpsId}">
		<input type="hidden" id="DtoCode" name="DtoCode" value="${DtoCode}">
		<input type="hidden" id="DDORegNo" name="DDORegNo" value="${DdoRegNO}">

		<fieldset class="tabstyle">
			<legend>Employee Form S1 Update</legend>

			<br>
			<c:if test="${signSize > 12 || photoSize > 12 }">  
							<p class="error">Your image and signature size ${signSize} Kb and ${photoSize} Kb. But Nsdl not accept the more then 12 kb size. </p>
			 </c:if>
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Employee Personal Details</legend>
					<!-- <table width="100%" align="left">
	<tr>
	 <td width="15%" align="left">Sevarth Id:</td>
	 <td width="35%" align="left"> <input size="50" type="text" id="sevarthId" name="sevarthId" value="${EmpSevarthId}" readonly="readonly" onkeypress="return onlyAlphabets(event,this);" /><label class="mandatoryindicator">*</label> </td>
	 <td width="15%" align="left">Employee Name:</td>
	 <td width="35%" align="left"> <input size="50" type="text" id="empName" name="empName" value="${EmpName}" readonly="readonly" onkeypress="return onlyAlphabets(event,this);" /><label class="mandatoryindicator">*</label></td>
	</tr>
	<tr>
	 <td width="15%" align="left">Father's Full Name:</td>
	 <td width="85%" align="left" colspan="3"> <input size="50" maxlength="30" type="text" name="empFatherName" id="empFatherName" onkeypress="return onlyAlphabets(event,this);" /><label class="mandatoryindicator">*</label></td>
	</tr>
	</table> -->

					<table width="100%" align="left">
						<tr>
							<td width="15%" align="left">Sevarth Id:</td>
							<td width="35%" align="left"><input size="50" type="text"
								id="sevarthId" name="sevarthId" value="${EmpSevarthId}"
								readonly="readonly"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">DDO Office Name:</td>
							<td width="35%" align="left"><input size="75" type="text"
								id="ddoName" name="ddoName"
								value="${empDetail[5]}-${empDetail[6]}"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>

						</tr>
						<tr>
							<td width="15%" align="left">Salutation</td>
							<td width="30%" align="left"><select name="salutation"
								id="salutation" class="form-control">
									<option value="0">-- Select --</option>
									<c:forEach items="${titleList}" var="Title">
										<option value="${fn:replace(Title[1],'.','')}"
											<c:if test="${Title[0] eq empDetail[3]}">selected</c:if>>
											${fn:replace(Title[1],".","")}</option>
									</c:forEach>

							</select><label class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">First Name :</td>
							<td width="40%" align="left"><input size="30" type="text"
								id="empFName" name="empFName" value="${EmpFName}"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
						</tr>
						<tr>
							<td width="15%" align="left">Middle Name:</td>
							<td width="35" align="left"><input size="30" maxlength="30"
								type="text" name="empMName" id="empMName" value="${EmpMName}"
								onkeypress="return onlyAlphabets(event,this);" />
							</td>
							<td width="15%" align="left">Last Name :</td>
							<td width="35%" align="left"><input size="30" type="text"
								id="empLName" name="empLName" value="${EmpLName}"
								onkeypress="return onlyAlphabets(event,this);" />
							</td>
						</tr>
						<tr>
							<td width="15%" align="left">Father's First Name:</td>
							<td width="35%" align="left"><input size="30" type="text"
								id="empFatherFName" name="empFatherFName"
								value="${fn:split(empDetail[7],' ')[0]}"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">Father's Last Name:</td>
							<td width="35%" align="left"><input size="30" type="text"
								id="empFatherLName" name="empFatherLName"
								value="${fn:split(empDetail[7],' ')[2]}"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
						</tr>
						<tr>
							<td width="15%" align="left">Mother's First Name:</td>
							<td width="35%" align="left"><input size="30" type="text"
								id="empMotherName" name="empMotherName" value=""
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">Date of Birth :</td>
							<td width="35" align="left"><input size="10" maxlength="10"
								type="text" name="empDob" id="empDob" value="${empDetail[8]}"
								onkeypress="digitFormat(this);dateFormat(this);"
								onblur="chkValidDate(this);" /> <!--  onblur="chkValidDate(this);validateDOBAndMinorGuardianDtls(this,1);"/> -->
								<img src='images/CalendarImages/ico-calendar.gif' width='20'
								onClick='window_open("empDob", 375, 570)'
								style="cursor: pointer;" /> <label class="mandatoryindicator">*</label>
							</td>
						</tr>

						<tr>
							<td width="15%" align="left">Gender :</td>
							<td width="35%" align="left">Male <input type="radio"
								id="radioGender" name="empGender" value="M"
								<c:if test="${empDetail[9]=='M'}">checked</c:if>>
								Female <input type="radio" id="radioGender" name="empGender"
								value="F" <c:if test="${empDetail[9]=='F'}">checked</c:if>>
								Transgender <input type="radio" id="radioGender"
								name="empGender" value="T"
								<c:if test="${empDetail[9]=='T'}">checked</c:if>> <label
								class="mandatoryindicator">*</label>
							</td>
							<td width="25%" align="left">Martial Status</td>
							<td width="35%" align="left">YES <input type="radio"
								id="martialStatusMarried" name="maritalStatus" value="M"
								<c:if test="${empDetail[10]=='M'}">checked</c:if>> NO <input
								type="radio" id="martialStatusUnmarried" name="maritalStatus"
								value="U" checked="checked"
								<c:if test="${empDetail[10]=='U'}">checked</c:if>> <label>other</label>
								<input type="radio" id="martialStatusOther" name="maritalStatus"
								value="O" <c:if test="${empDetail[10]=='O'}">checked</c:if>>
								<label class="mandatoryindicator">*</label></td>

						</tr>
					</table>


				</fieldset>
			</center>
			<br>
			<br>
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Identity Details</legend>
					<table width="100%" align="left">

						<tr>
							<td width="25%" align="left">PAN</td>
							<td width="25%" align="left"><input type="password" name="panNo" onblur="panNoValidation();chkPANalreadyExists();"
								value="${empDetail[11]}" id="panNo" maxlength="99"
								class="form-control"> <label class="mandatoryindicator">*</label>
							</td>
							<td width="25%" align="left">Aadhaar</td>
							<td width="25%" align="left"><input type="password" name="uidNo"
								id="uidNo" value="${empDetail[12]}" maxlength="12"
								class="form-control" onkeypress="return onlyNos(event,this);"
								value="${empDetail[10]}"></td>
						</tr>
						<tr>
							<td width="25%" align="left">Date of Joining(DCPS/NPS
								Applicable Date)</td>
							<td width="25%" align="left"><input type="text" name="doj"
								id="doj" value="${empDetail[13]}" maxlength="10"
								onkeypress="digitFormat(this);dateFormat(this);"
								onblur="chkValidDate(this);" class="form-control"> <img
								src='images/CalendarImages/ico-calendar.gif' width='20'
								onClick='window_open("doj", 375, 570)' style="cursor: pointer;" />
								<label class="mandatoryindicator">*</label></td>
							<td width="25%" align="left">Subscriber's Date of Retirement</td>
							<td width="25%" align="left"><input type="text"
								name="superAnnDate" id="superAnnDate" value="${empDetail[14]}"
								onkeypress="digitFormat(this);dateFormat(this);"
								onblur="chkValidDate(this);" class="form-control"> <img
								src='images/CalendarImages/ico-calendar.gif' width='20'
								onClick='window_open("superAnnDate", 375, 570)'
								style="cursor: pointer;" /> <label class="mandatoryindicator">*</label></td>
							</td>
						</tr>
						<tr>
							<td width="25%" align="left">Subscriber's Employment Class</td>
							<td width="25%" align="left"><c:set var="classList"
									value="${resValue.classList}"></c:set> <select name="empClass"
								id="empClass">
									<c:forEach items="${classList}" var="class1">
										<option value="${class1[1]}"
											<c:if test="${class1[1] eq empDetail[15]}">selected</c:if>>${class1[1]}</option>
									</c:forEach>
							</select> <label class="mandatoryindicator">*</label> <%-- <input type="text" name="empClass"  id="empClass" value="${empDetail[8]}" maxlength="3" class="form-control">
			<label class="mandatoryindicator">*</label> --%></td>
							<td width="25%" align="left">Subscriber's Department</td>
							<td width="25%" align="left"><input type="text"
								name="empDept" id="empDept" value="${empDetail[16]}"
								maxlength="40" class="form-control"> <label
								class="mandatoryindicator">*</label></td>

						</tr>
						<tr>
							<td width="25%" align="left">Subscriber's Ministry</td>
							<td width="25%" align="left" colspan="3"><input type="text"
								name="empMinistry" id="empMinistry"
								value="${fn:substring(empDetail[17],1,40)}" maxlength="40"
								class="form-control"> <label class="mandatoryindicator">*</label>
							</td>

						</tr>
						<tr>
							<td width="25%" align="left">Basic Salary</td>
							<td width="25%" align="left"><input type="text"
								name="basicSalary" value="${empDetail[18]}" id="basicSalary"
								maxlength="8" onkeypress="return onlyNos(event,this);"
								class="form-control"> <label class="mandatoryindicator">*</label>
							</td>
							<td width="25%" align="left">Pay scale</td>
							<td width="25%" align="left">
								<%-- <input type="text" name="payScale" id="payScale" value="${empDetail[16]}" maxlength="30" class="form-control"> --%>
								<select name="payScale" id="payScale">
									<c:forEach items="${payScaleLists}" var="payScale">
										<option value="${payScale[1]}-${payScale[2]}"
											<c:if test="${payScale[0] eq empDetail[19]}">selected</c:if>>${payScale[0]}</option>
									</c:forEach>
							</select> <label class="mandatoryindicator">*</label>
							</td>
						</tr>
					</table>
				</fieldset>
			</center>
			<br /> <br />
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Present Address</legend>
					<table width="100%" align="left">
						<tr>
							<td width="15%" align="left">Flat/ Unit No, Block No</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="presentAddFlatNo" name="presentAddFlatNo"
								value="${empDetail[20]}"
								onkeypress="return dblQuotesRmvr(event,this);" />
							<!-- onkeypress="return dblQuotesRmvr(event,this);" -->
								<label class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">Name of premise/Building/
								Village</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="presentAddBuilding" name="presentAddBuilding"
								value="${empDetail[21]}"
								onkeypress="return dblQuotesRmvr(event,this);" /><label
								class="mandatoryindicator">*</label></td>
						</tr>

						<tr>
							<td width="15%" align="left">Area/ Locality/taluka</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="presentAddTaluka" name="presentAddTaluka"
								value="${empDetail[22]}"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">Landmark</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="presentAddLandmark" name="presentAddLandMark"
								value="${empDetail[23]}"
								onkeypress="return onlyAlphabets(event,this);" /></td>
						</tr>

						<tr>
							<td width="15%" align="left">District/TOWN/CITY</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="presentAddDist" name="presentAddDist"
								value="${empDetail[24]}"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">STATE/UNION territory</td>
							<td width="35%" align="left"><select id="presentAddState"
								name="presentAddState">
									<option selected="selected" value="0">Please Select</option>
									<c:forEach items="${StateLists}" var="State">
										<option value="${State[0]}"
											<c:if test="${State[2]==empDetail[25]}">selected</c:if>>${State[1]}</option>
									</c:forEach>
							</select> <!-- <input size="50" maxlength="30" type="text" id="presentAddState" name="presentAddState" value="${empDetail[25]}"  onkeypress="return onlyAlphabets(event,this);" /> -->
								<label class="mandatoryindicator">*</label></td>
						</tr>

						<tr>
							<td width="15%" align="left">Country</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="presentAddCountry" name="presentAddCountry"
								value="India" readonly="readonly"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>

							<td width="15%" align="left">PIN CODE</td>
							<td width="35%" align="left"><input size="10" maxlength="6"
								type="text" id="presentAddPin" name="presentAddPin"
								value="${empDetail[26]}"
								onkeypress="return onlyNos(event,this);"
								onblur="validatePresentPin(this);" /><label
								class="mandatoryindicator">*</label></td>
						</tr>
					</table>
				</fieldset>
			</center>
			<br>
			<br>
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Present Address Same as Permanent Address?</legend>
					<table width="100%" align="left">
						<tr>
							<td width="100%" align="center">Present Address Same as
								Permanent Address?&nbsp; <b>NO:</b><input type="radio"
								name="sameAdd" value="NO" id="presentNotSamePerm"
								onclick="copyPresentPermAdd();">&nbsp; <b>YES:</b><input
								type="radio" name="sameAdd" value="YES" id="presentSamePerm"
								onclick="copyPresentPermAdd();">
							</td>
						</tr>
					</table>
				</fieldset>
			</center>
			<br>
			<br>
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Permanent Address</legend>
					<table width="100%" align="left">
						<tr>
							<td width="15%" align="left">Flat/ Unit No, Block No</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="permanentAddFlatNo" name="permanentAddFlatNo"
								readonly="readonly"
								onkeypress="return dblQuotesRmvr(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">Name of premise/Building/
								Village</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="permanentAddBuilding"
								name="permanentAddBuilding" readonly="readonly"
								onkeypress="return dblQuotesRmvr(event,this);" /><label
								class="mandatoryindicator">*</label></td>
						</tr>

						<tr>
							<td width="15%" align="left">Area/ Locality/taluka</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="permanentAddTaluka" name="permanentAddTaluka"
								readonly="readonly"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">Landmark</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="permanentAddLandmark"
								name="permanentAddLandmark" readonly="readonly"
								onkeypress="return onlyAlphabets(event,this);" /></td>


						</tr>

						<tr>
							<td width="15%" align="left">District/TOWN/CITY</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="permanentAddDist" name="permanentAddDist"
								readonly="readonly"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">STATE/UNION territory</td>
							<td width="35%" align="left"><select id="permanentAddState"
								name="permanentAddState" disabled>
									<option selected="selected" value="">Please Select</option>
									<c:forEach items="${StateLists}" var="State">
										<option value="${State[0]}">${State[1]}</option>
									</c:forEach>
							</select> <!-- input size="50" maxlength="30" type="text" id="permanentAddState" name="permanentAddState" value="" readonly="readonly" onkeypress="return onlyAlphabets(event,this);" /> -->
								<label class="mandatoryindicator">*</label></td>

						</tr>

						<tr>
							<td width="15%" align="left">Country</td>
							<td width="35%" align="left"><input size="50" maxlength="30"
								type="text" id="permanentAddCountry" name="permanentAddCountry"
								value="India" readonly="readonly"
								onkeypress="return onlyAlphabets(event,this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="15%" align="left">PIN CODE</td>
							<td width="35%" align="left"><input size="10" maxlength="6"
								type="text" id="permanentAddPin" name="permanentAddPin"
								readonly="readonly" onkeypress="return onlyNos(event,this);"
								onblur="validatePermanentPin(this)" /><label
								class="mandatoryindicator">*</label></td>
						</tr>
					</table>
				</fieldset>
			</center>
			<br>
			<br>
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Contact Details</legend>
					<table width="100%" align="left">
						<tr>
							<!-- <td width="25%">STD Code:</td>
	 			<td width="25%"><input size="10" maxlength="5" type="text" id="phoneSTDCode" name="phoneSTDCode" onblur="validatePhoneSTDCode(this);" onkeypress="return onlyNos(event,this);"/><label class="mandatoryindicator">*</label></td>
	 			 -->
							<td width="15%" align="left">Phone No.</td>
							<td width="85%" colspan="3" align="left">+91<input size="10"
								maxlength="11" type="text" id="phoneNo" value="" name="phoneNo"
								onblur="validatePhoneNo(this);"
								onkeypress="return onlyNos(event,this);" /></td>
						</tr>
						<tr>
							<td width="15%" align="left">Mobile No.</td>
							<td width="35%" align="left">+91<input size="12"
								maxlength="10" type="text" id="mobileNo" name="mobileNo"
								value="${empDetail[27]}"
								onkeypress="return onlyNos(event,this);"
								onblur="validateMobileNo(this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="20%" align="left"><label>Do you want to
									subscribe to SMS Alerts :</label></td>
							<td width="30%" align="left">Yes <input type="radio"
								id="smsSubFlag" name="smsSubFlag" value="Y" checked="checked">
								No <input type="radio" id="smssubflag" name="smssubflag"
								value="Y">
							</td>
						</tr>

						<tr>
							<td width="15%" align="left">Email ID</td>
							<td width="35%" align="left"><input size="50" maxlength="50"
								type="text" maxlength="50" id="emailId" value="${empDetail[28]}"
								name="emailId" onblur="validateEmail(this);" /><label
								class="mandatoryindicator">*</label></td>
							<td width="20%" align="left"><label>Do you want to
									subscribe to EMAIL Alerts :</label></td>
							<td width="30%" align="left">Yes <input type="radio"
								id="emailSubFlag" name="emailSubFlag" value="Y"
								checked="checked"> No <input type="radio"
								id="emailsubflag" name="emailsubflag" value="Y">
							</td>
						</tr>
						<tr>

						</tr>
					</table>
				</fieldset>
			</center>
			<br>
			<br>
			<center>

				<c:set var="empNomineedetails" value="${resValue.empNomineeDetails}"></c:set>
				<c:set var="empNomineesize" value="${resValue.empNomineesize }"></c:set>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Select number of nominees</legend>
					<table width="100%" align="center">
						<tr>
							<td width="100%" align="center">Number of nominees?&nbsp; <b>1:</b><input
								type="radio" name="noOfNominee"
								<c:if test="${empNomineesize=='1'}">checked</c:if> value="1"
								id="1nominee" onclick="enableNomineeFields(1);">&nbsp;
								<b>2:</b><input type="radio" name="noOfNominee"
								<c:if test="${empNomineesize=='2'}">checked</c:if> value="2"
								id="2nominee" onclick="enableNomineeFields(2);">&nbsp; <b>3:</b><input
								type="radio" name="noOfNominee"
								<c:if test="${empNomineesize=='3'}">checked</c:if> value="3"
								id="3nominee" onclick="enableNomineeFields(3);">
							</td>
						</tr>
					</table>
				</fieldset>
			</center>
			<br>
			<br>

			<c:set var="nomineeCounter" value="1"></c:set>
			<c:forEach items="${empNomineedetails}" var="empNomineedetail">

				<%-- ${empNomineedetail[2]}
	 			${empNomineedetail[3]}
	 			${empNomineedetail[4]} --%>
				<center>
					<fieldset style="width: 95%;" class="tabstyle">
						<legend>Nominee ${nomineeCounter} Details</legend>
						<table width="100%" align="left">
							<tr>
								<td width="15%" align="left">Nominee ${nomineeCounter}
									Name:</td>
								<td width="35%" align="left"><input size="50"
									maxlength="30" type="text" id="nominee${nomineeCounter}Name"
									name="nominee${nomineeCounter}Name"
									value="${empNomineedetail[0]}"
									onkeypress="return onlyAlphabets(event,this);" /><label
									class="mandatoryindicator">*</label></td>
								<td width="15%" align="left">Nominee ${nomineeCounter} Date
									Of Birth</td>
								<td width="35%" align="left"><input size="10"
									maxlength="10" type="text" id="nominee${nomineeCounter}DOB"
									name="nominee${nomineeCounter}DOB"
									value="${empNomineedetail[1]}"
									onkeypress="digitFormat(this);dateFormat(this);"
									onblur="chkValidDate(this);validateDOBAndMinorGuardianDtls(this,${nomineeCounter});" />
									<img src='images/CalendarImages/ico-calendar.gif' width='20'
									onClick='window_open("nominee1DOB", 375, 570)'
									style="cursor: pointer;" /> <label class="mandatoryindicator">*</label>
								</td>
							</tr>


							<tr>
								<td width="15%" align="left">Nominee ${nomineeCounter}
									Relation:</td>
								<td width="35%" align="left"><select
									id="nominee${nomineeCounter}Relation"
									name="nominee${nomineeCounter}Relation">
										<option value="-1">--Select--</option>
										<c:forEach items="${RelationList}" var="Relation">
											<option value="${Relation[1]}"
												<c:if test="${Relation[1] eq empNomineedetail[2]}">selected</c:if>>${Relation[1]}</option>
										</c:forEach>
								</select> <label class="mandatoryindicator">*</label></td>
								<td width="15%" align="left">Nominee ${nomineeCounter}
									Percentage Share<br>(with no % sign)
								</td>
								<td width="35%" align="left"><input size="10" maxlength="3"
									type="text" id="nominee${nomineeCounter}Percent"
									name="nominee${nomineeCounter}Percent"
									value="${empNomineedetail[3]}"
									onkeypress="return onlyNos(event,this);" />%<label
									class="mandatoryindicator">*</label></td>
							</tr>
							<tr>
								<td width="15%" align="left">Nominee ${nomineeCounter}
									Guardian Name<br>(in case of minor nominee)
								</td>
								<td width="35%" align="left"><input size="50"
									maxlength="30" type="text"
									id="nominee${nomineeCounter}Guardian"
									name="nominee${nomineeCounter}Guardian" value="NA"
									readonly="readonly"
									onkeypress="return onlyAlphabets(event,this);" /><label
									class="mandatoryindicator">*</label></td>
								<td width="15%" align="left">Nominee ${nomineeCounter}
									Condition rendering<br>nomination invalid
								</td>
								<td width="35%" align="left"><input size="50"
									maxlength="15" type="text"
									id="nominee${nomineeCounter}InvalidCondition"
									name="nominee${nomineeCounter}InvalidCondition" value="NA" /><label
									class="mandatoryindicator">*</label></td>
							</tr>
							
							<tr>
								<td width="15%" align="left">Nominee ${nomineeCounter}
									Address  
								</td>
								<td width="35%" align="left"><input size="50"
									maxlength="40" type="text"
									id="nominee${nomineeCounter}Address"
									name="nominee${nomineeCounter}Address" value=""
									<c:if test="${empNomineesize==1}">readonly="readonly"</c:if> 
									onkeypress="return onlyAlphabets(event,this);" /><label
									class="mandatoryindicator">*</label></td>
								<td width="15%" align="left"> 
								</td>
								<td width="35%" align="left"> </td>
							</tr>
							
						</table>
					</fieldset>
				</center>
				<br>
				<br>
				<c:set var="nomineeCounter" value="${nomineeCounter+1}"></c:set>
			</c:forEach>

			<c:forEach begin="${empNomineesize+1}" end="3" varStatus="loop">


				<center>


					<fieldset style="width: 95%;" class="tabstyle">
						<legend>Nominee ${loop.index} Details</legend>
						<table width="100%" align="left">
							<tr>
								<td width="15%" align="left">Nominee ${loop.index} Name:</td>
								<td width="35%" align="left"><input size="50"
									maxlength="30" type="text" id="nominee${loop.index}Name"
									name="nominee${loop.index}Name"
									<c:if test="${loop.index > 1}">readonly="readonly"</c:if>
									value="" onkeypress="return onlyAlphabets(event,this);" /><label
									class="mandatoryindicator">*</label></td>
								<td width="15%" align="left">Nominee ${loop.index} Date Of
									Birth</td>
								<td width="35%" align="left"><input size="10"
									maxlength="10" type="text" id="nominee${loop.index}DOB"
									name="nominee${loop.index}DOB"
									<c:if test="${loop.index > 1}">readonly="readonly"</c:if>
									value="" onkeypress="digitFormat(this);dateFormat(this);"
									onblur="chkValidDate(this);validateDOBAndMinorGuardianDtls(this,${loop.index});" />
									<img src='images/CalendarImages/ico-calendar.gif' width='20'
									onClick='window_open("nominee${loop.index}DOB", 375, 570)'
									style="cursor: pointer;" /> <label class="mandatoryindicator">*</label>

								</td>
							</tr>


							<tr>
								<td width="15%" align="left">Nominee ${loop.index}
									Relation:</td>
								<td width="35%" align="left"><select
									id="nominee${loop.index}Relation"
									name="nominee${loop.index}Relation"
									<c:if test="${loop.index > 1}">disabled="disabled"</c:if>>
										<option value="-1" id="nominee${loop.index}RelationSelect">--Select--</option>
										<c:forEach items="${RelationList}" var="Relation">
											<option value="${Relation[0]}">${Relation[1]}</option>
										</c:forEach>
								</select> <label class="mandatoryindicator">*</label></td>
								<td width="15%" align="left">Nominee ${loop.index}
									Percentage Share<br>(with no % sign)
								</td>
								<td width="35%" align="left"><input size="10" maxlength="3"
									type="text" id="nominee${loop.index}Percent"
									<c:if test="${loop.index > 1}">readonly="readonly"</c:if>
									name="nominee${loop.index}Percent" value=""
									onkeypress="return onlyNos(event,this);" />%<label
									class="mandatoryindicator">*</label></td>
							</tr>
							<tr>
								<td width="15%" align="left">Nominee ${loop.index} Guardian
									Name<br>(in case of minor nomiee)
								</td>
								<td width="35%" align="left"><input size="50"
									maxlength="30" type="text" id="nominee${loop.index}Guardian"
									name="nominee${loop.index}Guardian"
									<c:if test="${loop.index > 1}">readonly="readonly"</c:if>
									value="NA" readonly="readonly"
									onkeypress="return onlyAlphabets(event,this);" /><label
									class="mandatoryindicator">*</label></td>
								<td width="15%" align="left">Nominee ${loop.index}
									Condition rendering<br>nomination invalid
								</td>
								<td width="35%" align="left"><input size="50"
									maxlength="15" type="text"
									id="nominee${loop.index}InvalidCondition"
									name="nominee${loop.index}InvalidCondition"
									<c:if test="${loop.index > 1}">readonly="readonly"</c:if>
									value="NA" /><label class="mandatoryindicator">*</label></td>
							</tr>
							<tr>
								<td width="15%" align="left">Nominee ${loop.index} Address  
								</td>
								<td width="35%" align="left"><input size="50"
									maxlength="40" type="text"
									id="nominee${loop.index}Address"
									name="nominee${loop.index}Address" value="NA"
									readonly="readonly"
									onkeypress="return onlyAlphabets(event,this);" /><label
									class="mandatoryindicator">*</label></td>
								<td width="15%" align="left"> 
								</td>
								<td width="35%" align="left"> </td>
							</tr>
						</table>
					</fieldset>
				</center>
				<br>
				<br>
			</c:forEach>
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Subscribers Bank Details </legend>
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td width="25%" align="left">Bank A/c Number</td>
								<td width="25%" align="left"><input type="text"
									name="bankAcntNo" id="bankAcntNo" value="${empDetail[29]}"
									maxlength="30" class="form-control" value=""></td>
								<td width="25%" align="left">Bank Name<b><font
										color="red" size="4px;">*</font></b></label></td>
								<td width="25%" align="left"><select class="form-control"
									id="empBankName" name="empBankName">
										<option selected="selected" value="0">Please Select</option>
										<c:forEach items="${BankLists}" var="Bank">
											<option value="${Bank[1]}"
												<c:if test="${Bank[1]==empDetail[30]}">selected</c:if>>${Bank[1]}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>

							</tr>
							<tr>
								<td width="25%" align="left">Branch Name</td>
								<td width="25%" align="left"><input type="text"
									name="empBankBranchName" id="empBankBranchName"
									value="${fn:split(empDetail[31],',')[0]}" maxlength="30"
									class="form-control"></td>

								<td width="25%" align="left">Branch Address</td>
								<td width="25%" align="left"><input type="text"
									name="empBankBranchAddress" id="empBankBranchAddress"
									value="${fn:split(empDetail[32],',')[0]}" maxlength="30"
									class="form-control"></td>
							</tr>
							<tr>
								<td width="25%" align="left">PIN Code</td>
								<td width="25%" align="left"><input type="text"
									name="empBankPinCode" id="empBankPinCode"
									onkeypress="digitFormat(this);" value="${empDetail[33]}"
									maxlength="6" class="form-control"></td>
								<td width="25%" align="left">Bank State</td>
								<td width="25%" align="left"><select name="empBankstate"
									id="empBankstate" class="form-control">
										<option value="19">Maharashtra</option>
										<option selected="selected" value="0">Please Select</option>
										<c:forEach items="${StateLists}" var="State">
											<option value="${State[0]}"
												<c:if test="${State[2]==empDetail[34]}">selected</c:if>>${State[1]}</option>
										</c:forEach>

								</select></td>
							</tr>
							<tr>
								<td width="25%" align="left">IFSC Code</td>
								<td width="25%" align="left"><input type="text"
									name="IfscCode" id="IfscCode" value=""
									maxlength="11" class="form-control"></td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</center>
			<br />
			<br />
			<center>
				<fieldset style="width: 95%;" class="tabstyle">
					<legend>Other Details </legend>
					<table class="radiomainTb">
						<tbody>
							<tr>
								<td width="25%" align="left">Display Name Flag (Name to be
									printed on PRAN Card)</td>
								<td width="25%" align="left"><label>Father Name</label> <input
									type="radio" id="dipsFatherNameFlag" name="dispNameFlag"
									value="F" checked="checked"> <label>Mother Name</label>
									<input type="radio" id="dipsMotherNameFlag" name="dispNameFlag"
									value="M"></td>
							</tr>
							<tr>
								<td width="25%" align="left">Date of Birth Proof</td>
								<td width="25%" align="left" colspan="2">AADHAAR Card <input
									type="radio" id="dobp" name="dobProof" value="150"
									checked="checked"> PAN Card <input type="radio"
									id="dobp" name="dobProof" value="119"> Passport <input
									type="radio" id="dobp" name="dobProof" value="103">
									Driving License <input type="radio" id="dobp" name="dobProof"
									value="104"> Voter ID <input type="radio" id="dobp"
									name="dobProof" value="107">
								</td>
							</tr>
							<tr>
								<td width="25%" align="left">Educational Qulification</td>
								<td width="25%" align="left">If no information available
									from Subscriber <input type="radio" id="NoInfo" name="eduQual"
									value="00" checked="checked"> Below SSC <input
									type="radio" id="BelowSSC" name="eduQual" value="01">
									SSC <input type="radio" id="EduSSC" name="eduQual" value="02">
									HSC <input type="radio" id="EduHsc" name="eduQual" value="03">
									Graduate <input type="radio" id="EduGraduate" name="eduQual"
									value="04"> Masters <input type="radio" id="EduMaster"
									name="eduQual" value="05"> Professionals (CA, CS, CMA
									etc.) <input type="radio" id="EduProfessional" name="eduQual"
									value="06">
								</td>
							</tr>
							<tr>
								<td width="25%" align="left">Income Range</td>
								<td width="25%" align="left" colspan="2">If no information
									available from Subscriber <input type="radio" id="NoInfoIncome"
									name="incomeRange" value="00"> Upto 1 Lac <input
									type="radio" id="IncomeOne" name="incomeRange" value="01"
									checked="checked"> 1 Lac to 5 Lac <input type="radio"
									id="IncomeFive" name="incomeRange" value="02"> 5 Lac to
									10 Lac <input type="radio" id="IncomeTenLac" name="incomeRange"
									value="03"> 10 Lac to 25 Lac <input type="radio"
									id="IncomeTwenty" name="incomeRange" value="04"> 25 Lac
									and Above <input type="radio" id="IncomeAbve"
									name="incomeRange" value="05">
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</center>
			<br /> <br />

			<center>

				<script type="text/javascript">
function hidImg()
{
	newWindow1.close();
}
function showImg(lStr)
{
	document.getElementById("attachmentName1").value = lStr.name;
	var height = 600;
	var width = 600;
	var urlstring = "ifms.htm?viewName=dcpsPhoto";
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window.open(urlstring,"PensionerPhoto","'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0","false");
	//globArray[9] = newWindow1; 
}
</script>
				<input type="hidden" name="hidPhotoUrl" id="hidPhotoUrl"> <input
					type="hidden" name="hidSignUrl" id="hidSignUrl"> <input
					type="hidden" name="attachmentName1" id="attachmentName1">
				<input type="hidden" name="photoAttachementID"
					id="photoAttachementID" value="${resValue.PhotoId}"> <input
					type="hidden" name="photoAttachementSrNo" id="photoAttachementSrNo"
					value="${resValue.PhotosrNo}"> <input type="hidden"
					name="signAttachementID" id="signAttachementID"
					value="${resValue.SignId}"> <input type="hidden"
					name="signAttachementSrNo" id="signAttachementSrNo"
					value="${resValue.SignsrNo}">
				<fieldset style="width: 95%;" class="tabstyle">
					<legend id="headingMsg">
						<b>Photo/Signature</b>
					</legend>
					<table class="table table-bordered">
						<tbody>
							<%-- <c:if test="${resValue.PhotoId != null && resValue.SignId!= null }">  --%>
							 <c:if test="${signSize > 12 || photoSize > 12 }">  
							<tr>
								<td colspan="2"><p class="error">Your image and signature size ${signSize} and ${photoSize }.But NSDL not accept the more then 12 kb size. </p></td></tr>
							<tr>
							 </c:if>
								<td>
									<fieldset class="tabstyle" style="width: 100%;">
										<legend id="headingMsg">
											<b>Photo </b>
										</legend>


										<div id="prewPhoto" style="width: 180px; height: 150px;">
											<a href="#" id="Photo" name="Photo" onmouseout="hidImg()"
												onmouseover="showImg(this)"> <img
												style="width: 180px; height: 150px;" id="Photo"
												src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}">
											</a>
											<script type="text/javascript">
											document.getElementById("hidPhotoUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}"
										</script>
										</div>

										<div id="prewPhoto" style="width: 180px; height: 150px;">

										</div>


									</fieldset>
								</td>

								<td>
									<fieldset class="tabstyle" style="width: 100%;">
										<legend id="headingMsg">
											<b> Signature</b>
										</legend>

										  <div id="prewSign" style="width: 180px; height: 150px;">
											<a href="#" name="Sign" id="Sign" onmouseout="hidImg()"
												onmouseover="showImg(this)"> <img
												style="width: 180px; height: 150px;"
												src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}">
											</a>
											<script type="text/javascript">
									document.getElementById("hidSignUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}"
								</script>
										</div>  

										<div id="prewSign" style="width: 180px; height: 150px;">
										</div>




									</fieldset>
								</td>
							</tr>
							<c:if test="${PhotoFlag==0}">
								<tr>
									<td colspan="2" class="error"><p class="error">Photo or signature is not
										found in our system. So, Kindly upload your photo and
										signature from employee change details.</p></td>
								</tr>

							</c:if>
							<!-- Please upload your photo and signature using other update   -->
							<tr>
									<td colspan="2" class="mandatoryindicator">NOTE: Photo or signature is compulsory component.So, it is not available in our system
									Kindly upload your photo and signature from employee change details.</td>
								</tr>
						</tbody>
					</table>
				</fieldset>



			</center>
			<table width="100%">
				<tr>
					<td width="50%" align="right"><input class="buttontag"
						type="button" value="Reset" onclick="resetForm();"></td>
					<td width="50%" align="left">
					<c:set var="btnStatus" value=""></c:set>	
					<c:if test="${signSize > 12 || photoSize > 12 }"> 
						<c:set var="btnStatus" value="disabled"></c:set>	
						</c:if>
						 
					<input class="buttontag"
						id="btnSubmit" type="button" value="Submit Form" 
						onclick="submitForm();" />
						</td>
				</tr>
			</table>
	</hdiits:form>
</c:forEach>
