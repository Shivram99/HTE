<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="commonLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="allowNames" value="${resValue.allowNames}" > </c:set>	
<c:set var="componentNames" value="${resValue.componentNames}" > </c:set> 	
<c:set var="statusTypes" value="${resValue.statusTypes}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<script>

function trim(s) 
{
// Remove leading spaces and carriage returns
//  s = s.replace(/&nbsp;/gi,'');

 while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
  {
    s = s.substring(1,s.length);   
  }

  // Remove trailing spaces and carriage returns

  while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
  {
    s = s.substring(0,s.length-1);
  }
  return s;
}

function chkRuleDesc()
{ 
  
  var name =document.insExprFrm.txtRuleDesc.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&ruleDesc='+ document.insExprFrm.txtRuleDesc.value;
		  var actionf="chkRuleDesc";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
 //        alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_ruleDesc;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
}

function chk_ruleDesc()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('rule-desc');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
                    {                    
                     alert('Rule Name already exists.');
                     document.insExprFrm.txtRuleDesc.value = '';
                     document.insExprFrm.txtRuleDesc.focus();
                    }
  }
}

function CheckDates()
{
		var fromdate = new Date(document.frmIO.txtFromDate.value);
		var todate = new Date(document.frmIO.txtToDate.value);
		if (todate < fromdate)
    	{
    		alert('To Date must be greater than or equal to From Date');
    		return false;
    	}
    	else
    	return true;
}


function undo_exp(val)
{
 	var exp = new Array();
    exp = document.getElementById('txtExp').value.split(' ');
    var pop = exp.pop();
    document.getElementById('txtExp').value = exp.join(' ');
    if(trim(document.getElementById('txtExp').value) == '')
     disableOperators();
}
function enableOperators()
{

document.getElementById('add_plus').disabled=false;
document.getElementById('add_minus').disabled=false;
document.getElementById('add_mul').disabled=false;
document.getElementById('add_div').disabled=false;
document.getElementById('add_LBracket').disabled=false;
document.getElementById('add_RBracket').disabled=false;
}

function disableOperators()
{

document.getElementById('add_plus').disabled=true;
document.getElementById('add_minus').disabled=true;
document.getElementById('add_mul').disabled=true;
document.getElementById('add_div').disabled=true;
//document.getElementById('add_LBracket').disabled=true;
document.getElementById('add_RBracket').disabled=true;
}
function checkExp(val)
{
 if(val=='+' || val=='-' || val=='/' || val=='*' || val=='(')
  return false;
 else
  return true;
}
function addtxt(val)
{
	var name=val.name;
	var txtval=document.getElementById('txtExp').value;
    var exp = new Array();
    exp = document.getElementById('txtExp').value.split(' ');
	
	if(trim(document.getElementById('txtVal').value) != '')
    {
      var r=confirm('If You Enter the Expression then Value Will be Removed. Are You Sure?')
				if (r==true)
			   	{
                    document.getElementById('txtVal').value = '';
                    return true;
                }
               else return false;
     }

		if(name=="add_component")
		{
 		   if(document.insExprFrm.components.value=='Select')
 		    alert('Select The Value');
 		    else if(trim(exp[exp.length-1])!='')
 		   { 		    
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		      alert('Illegal Expression.'); 		    		    
 		    else 
 		    {	 		    
			  txtval+=' ' + document.insExprFrm.components(document.insExprFrm.components.selectedIndex).text;
			  enableOperators();
			 }
		  }
 		    else
 		    {
			 txtval+=' ' + document.insExprFrm.components(document.insExprFrm.components.selectedIndex).text;
 		     enableOperators();
			}
		}
		
		if(name=="add_var")
		{
		  var expVal = document.getElementById('txtExp').value;

 		   if(document.insExprFrm.vars.value=='Select')
 		    alert('Select The Value');
 		   
 		   else if(document.insExprFrm.vars.value == 'y')
 		   {
 		    if(expVal.search('x')== -1)
 		    {
 		      alert('You cannot select this option.');
 		     }
 		    else if(trim(exp[exp.length-1])!='')
 		     { 		    
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		      alert('Illegal Expression.'); 

 		    else 
 		    {	 		    
			  txtval+=' ' + document.insExprFrm.vars(document.insExprFrm.vars.selectedIndex).text;
			  enableOperators();
			 }
 		    }
 		   }
 		    
 	      else if(document.insExprFrm.vars.value == 'z')
 		   {
 		    if((expVal.search('x')== -1) || (expVal.search('y')== -1))
 		    {
 		      alert('You cannot select this option.');
 		     }
 		     else if(trim(exp[exp.length-1])!='')
 		     { 		    
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		      alert('Illegal Expression.'); 
 		     
 		    else 
 		    {	 		    
			  txtval+=' ' + document.insExprFrm.vars(document.insExprFrm.vars.selectedIndex).text;
			  enableOperators();
			 }
 		    }
 		   }
 		    
 		    else if(trim(exp[exp.length-1])!='')
 		   { 		    
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		      alert('Illegal Expression.'); 		    		    
 		    else if(trim(exp[exp.length-1])!='')
 		     { 		    
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		      alert('Illegal Expression.'); 
 		    
 		    else 
 		    {	 		    
			  txtval+=' ' + document.insExprFrm.vars(document.insExprFrm.vars.selectedIndex).text;
			  enableOperators();
			 }
		   }
		  }
 		    else
 		    {
			 txtval+=' ' + document.insExprFrm.vars(document.insExprFrm.vars.selectedIndex).text;
 		     enableOperators();
			}
		}

		if(name=="add_amount")
		{
		  if(trim(document.insExprFrm.txtAmount.value)=='')
		    alert('Enter The Amount.');
		    else if(trim(exp[exp.length-1])!='')
 		   {
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		      alert('Illegal Expression.'); 		    		    
 		    else 
 		    {	 		    
			  txtval+=' ' + document.getElementById('txtAmount').value;
			  enableOperators();
			 }
		  }
		   else
		   {
			txtval+=' ' + document.getElementById('txtAmount').value;	
 		     enableOperators();
			}
		}
		if(name=="add_plus")
		{
		  if(trim(exp[exp.length-1])!='')
 		   {
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
    			txtval+=' ' + document.getElementById('add_plus').value;
 		    else 
   		      alert('Illegal Expression.'); 		    		    
		  }
		}

		if(name=="add_minus")
		{
		 if(trim(exp[exp.length-1])!='')
 		   {
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 			   txtval+=' '+ document.getElementById('add_minus').value;
 		    else 
   		      alert('Illegal Expression.'); 		    		    
		  }
		}

		if(name=="add_mul")
		{
		 if(trim(exp[exp.length-1])!='')
 		   {
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 			txtval+=' ' +document.getElementById('add_mul').value;
 		    else 
   		      alert('Illegal Expression.'); 		    		    
		  }
		}
		
		
		if(name=="add_div")
		{
		 if(trim(exp[exp.length-1])!='')
 		   {
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 			  txtval+=' ' +document.getElementById('add_div').value;
 		    else 
   		      alert('Illegal Expression.'); 		    		    
		  }
		}
		
		if(name=="add_Lbracket")
		{
		 
		 /*if(trim(exp[exp.length-1])!='')
 		   {*/
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 		    { 		 
 			 txtval+=' ' + document.getElementById('add_Lbracket').value;
 			  enableOperators();
 		    }
 		    else 
   		      alert('Illegal Expression.'); 		    		    
		  //}
		}
		
		if(name=="add_Rbracket")
		{
		 if(trim(exp[exp.length-1])!='')
 		   {
 		    if(checkExp(trim(exp[exp.length-1]))) 		      
 			txtval+=' ' + document.getElementById('add_Rbracket').value;
 		    else 
   		      alert('Illegal Expression.'); 		    		    
		  }
		}
		
				
document.getElementById('txtExp').value=txtval;
 }


function chkValue()
{
  if(trim(document.getElementById('txtExp').value) != '')
  {
      var r=confirm('If You Enter the value then Expression Will be Removed. Are You Sure?')
				if (r==true)
			   	{
                    document.getElementById('txtExp').value = '';
                    disableOperators();
                    document.insExpFrm.txtVal.focus();
                 }
                 else
                 {
                   document.insExprFrm.flag.focus();
                   return false;
                  }
  }
}

function countInstances(string,word) 
{
  var index = string.split(word).length-1;
  return index;
}

function validateForm()
{
  var exp = new Array();
  var diff = compareDate(document.insExprFrm.txtRuleSDate.value,document.insExprFrm.txtRuleEDate.value);   
  exp = document.getElementById('txtExp').value.split(' ');
  
  var countLBracket = countInstances(document.getElementById('txtExp').value,'(');
  var countRBracket = countInstances(document.getElementById('txtExp').value,')');
//  alert(countLBracket + ' ' + countRBracket);
  if(countLBracket!=countRBracket)
  {
    alert('Illegal Expression.'); 
    return false;
  }
  
  if(!checkExp(trim(exp[exp.length-1]))) 		      
  {
     alert('Illegal Expression.'); 
     return false;
   }
  if(diff < 0)
  {
   	alert('To Date must be greater than From Date');
   	return false;
  }
   if((trim(document.insExprFrm.txtExp.value)!='') && (trim(document.insExprFrm.txtVal.value)!=''))
   {
       alert('Either Expression or Value can Be Entered.');
       return false;   
   }
   if((trim(document.insExprFrm.txtExp.value)=='') && (trim(document.insExprFrm.txtVal.value)==''))
     {
       alert('Either Expression or Value Must Be Entered.');
       return false;
     }

    	else 
    	return true;
}

function resetForm()
{
  if(confirm("All values will be reseted please confirm !") == true)
  {
	document.insExprFrm.reset();
  }		  				  	
}
</script>

<hdiits:form name="insExprFrm" validate="true" method="POST" 
	action="hrms.htm?actionFlag=insertExprData&edit=N" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="EX.insertRule" bundle="${commonLables}"></hdiits:caption></b></a></li>
	</ul>
	</div>
	
	
<div class="halfplustabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">

    
<table class="tabtable">



 <TR>
			<td class="fieldLabel">
			<b><hdiits:caption captionid="EX.DESC" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			
			<TD> <hdiits:text
				name="txtRuleDesc" mandatory="true"
				 captionid="EX.DESC" bundle="${commonLables}" maxlength="100" 
				 validation ="txt.isrequired" 
				 onblur="chkRuleDesc()"  onkeypress="if(event.keyCode == 13) event.returnValue = false;"/></TD>	
				 
			
			
</tr>
<tR><td></td></tR>
 <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EX.AT" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
				<td><hdiits:select  captionid="EX.AT" bundle="${commonLables}" name="allowance" mandatory="true"  validation="sel.isrequired"> 
						<hdiits:option value="Select">-----------Select-----------</hdiits:option>
						<c:forEach var="allow" items="${allowNames}">
				    					<option value=<c:out value="${allow.allowCode}"/>>
										<c:out value="${allow.allowName}"/></option>
						</c:forEach>		
					
				</hdiits:select>
			
					
			</tD>
			
			
			<TD class="fieldLabel" ><b><hdiits:caption captionid="EX.AMT" bundle="${commonLables}"></hdiits:caption></b></TD>	
			<TD>
			<hdiits:text
				name="txtAmount"
				 captionid="EX.AMT" bundle="${commonLables}" maxlength="10" />
			</TD>
			<TD> <hdiits:button name="add_amount" value="ADD" type="button"  onclick="addtxt(this)"/></TD>				
</TR>
<tR><td></td></tR>

<TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EX.VC" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
				<td><hdiits:select  captionid="EX.VC" bundle="${commonLables}" name="components" > 
						<hdiits:option value="Select">-----------Select-----------</hdiits:option>
						<c:forEach var="components" items="${componentNames}">
						<hdiits:option value="${components.componentCode}"> ${components.componentDesc} </hdiits:option>
						</c:forEach>							
				</hdiits:select>								
			</tD>
			<TD>				
				<hdiits:button name="add_component" value="ADD" type="button" onclick="addtxt(this)" />
			</TD>
		</TR>
		<tr>	
			
			<TD class="fieldLabel">
			<b><hdiits:caption captionid="EX.VAR" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
				<td><hdiits:select  captionid="EX.VAR" bundle="${commonLables}" name="vars" > 
						<hdiits:option value="Select">-----------Select-----------</hdiits:option>
						<hdiits:option value="x"> x </hdiits:option>
						<hdiits:option value="y"> y </hdiits:option>
						<hdiits:option value="z"> z </hdiits:option>				    							
				</hdiits:select>								
			</tD>
			<TD colspan=6 align="left">				
				<hdiits:button name="add_var" value="ADD" type="button" onclick="addtxt(this)" />
			</TD>
			</tr>
			
			

<tr> </tr> <tr> </tr>


 <TR>
			<TD class="fieldLabel" colspan=6 align=center>
				<hdiits:button name="add_plus" value="+" type="button" onclick="addtxt(this)" readonly="true"/>
				<hdiits:button name="add_minus" value="-" type="button"  onclick="addtxt(this)" readonly="true"/>
				<hdiits:button name="add_mul" value="*" type="button"  onclick="addtxt(this)" readonly="true"/>
				<hdiits:button name="add_div" value="/" type="button"  onclick="addtxt(this)" readonly="true"/>
				<hdiits:button name="add_Lbracket" value="(" type="button"  onclick="addtxt(this)"/>
				<hdiits:button name="add_Rbracket" value=")" type="button"  onclick="addtxt(this)" readonly="true"/>
			</TD>					
</TR>
<tR></tR>

 <TR>
			<TD class="fieldLabel">
			<b><hdiits:caption captionid="EX.EXPR" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD class="fieldLabel" colspan=2>
			<hdiits:textarea rows="3" cols="50" name="txtExp" 
			 captionid="EX.EXPR" bundle="${commonLables}" readonly="true">
			</hdiits:textarea></td>
			
			<td>
			<hdiits:button name="btn_undo" value="Undo" type="button"  onclick="undo_exp(this)"/>
			</td>
</TR>

<tr>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EX.FLG" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<td><hdiits:select  captionid="EX.FLG" bundle="${commonLables}" name="flag" > 
						<hdiits:option value="-1">-----------Select-----------</hdiits:option>
						<hdiits:option value="0"> Active </hdiits:option>
						<hdiits:option value="1"> Inactive </hdiits:option>
						</hdiits:select>
			</td>						
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EX.VAL" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			
            <TD class="fieldLabel" >
			<hdiits:text
				name="txtVal"
				 captionid="EX.VAL" bundle="${commonLables}" maxlength="10" onfocus="chkValue()"/>
			</TD>

			</tr>
		<TR>
		
		<td><b><hdiits:caption captionid="EX.STDATE"  bundle="${commonLables}"/></b></td>
		<TD><hdiits:dateTime captionid="EX.STDATE"  bundle="${commonLables}" name="txtRuleSDate" validation="txt.isrequired,txt.isdt" /></TD>	
        
		<td><b><hdiits:caption captionid="EX.ENDATE"  bundle="${commonLables}"/></b></td>
		<TD><hdiits:dateTime captionid="EX.ENDATE"  bundle="${commonLables}" name="txtRuleEDate" 
		validation="txt.isrequired,txt.isdt" /></TD>	
		
	</tr> 
			
<tR><td></td></tR>
<tr></tr>
<tr></tr>
      
      
      
         
	</table> 
    
 </div>
 
<table class="tabtable">
<tr>
    <TD  class=fieldLabel align="center" colspan="4" >
	
	
   <!-- <hdiits:submitbutton name="submit" />
    <input type="submit"name="btnSubmit" value="&nbsp;Submit&nbsp;" onclick=" return validateForm()" /> 
    <input type="reset" name="btnSubmit" value="&nbsp;Reset&nbsp;" onclick=" resetForm()"/>  --> 
    </TD> 
</tr> 
</table>
<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables" scope="request"/> 
 <jsp:include page="../../core/PayTabnavigation.jsp" />

	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getExprView";
			document.insExprFrm.action=url;
			document.insExprFrm.submit();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

   