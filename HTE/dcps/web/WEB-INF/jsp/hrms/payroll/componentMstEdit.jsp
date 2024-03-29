<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	

<c:set var="componentList" value="${resValue.componentList}" > </c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>	


<fmt:setBundle basename="resources.eis.eisLables" var="commonLables" scope="request"/>



<script>
function chk(desc)
{
	var desc=desc.value;
	
	if(desc!="")
	{
	try
   	{   
   		xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{    // Internet Explorer    
		try
     	{
     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
     	}
	    catch (e)
	    {
	    	try
       		{
            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       		}
		    catch (e)
		    {
		        alert("Your browser does not support AJAX!");        
		        return false;        
		    }
		}
	}
    var url = "hrms.htm?actionFlag=getComponentView&editdesc=${actionList.componentDesc}&chk=1&desc="+desc;  
   
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				
				
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var componentdetails = XMLDocForAjax.getElementsByTagName('componentdetails');	
				
				
				if(componentdetails.length != 0)
				{
						
						//alert(componentdetails[0].childNodes[0].text+" "+(componentdetails[0].childNodes[0].text!='null'));
						if(componentdetails[0].childNodes[0].text!='null')
						{			
						alert("Component Description already Exists, Please Enter other Description");
						document.frmBF.txt_component_desc.value='';
						document.frmBF.txt_component_desc.focus();
						}
						
						
					
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	
	
}

function addtxt(val)
{
	var name=val.name;
	var txtval=document.getElementById('component_exp').value;
		if(name=="add")
			txtval+=document.frmBF.component(document.frmBF.component.selectedIndex).text;
		if(name=="add_amount")
			txtval+=document.getElementById('txt_amount').value;	
		if(name=="add_plus")
			txtval+=document.getElementById('add_plus').value;
		if(name=="add_minus")
			txtval+=document.getElementById('add_minus').value;
		if(name=="add_mul")
			txtval+=document.getElementById('add_mul').value;
		if(name=="add_div")
			txtval+=document.getElementById('add_div').value;
		if(name=="add_Lbracket")
			txtval+=document.getElementById('add_Lbracket').value;
		if(name=="add_Rbracket")
			txtval+=document.getElementById('add_Rbracket').value;
				
document.getElementById('component_exp').value=txtval;

}
function  chkKey(e)
{
	if(window.event) // IE
	{
		if(e.keyCode==13)
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}
	
	
}
</script>

<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertComponentData&edit=Y&componentid=${actionList.componentCode}" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">COMPONENT INFORMATION</a></li>
	</ul>
</div>
	
	
<div class="tabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">

    
<table class="tabtable" >

<tR><td></td></tR>

 <TR>
			<td class="fieldLabel"  colspan=2>
			<hdiits:caption captionid="eis.component_desc" bundle="${commonLables}"></hdiits:caption>
			</TD>
			
			<TD colspan=2> <hdiits:text
				name="txt_component_desc"
				 captionid="eis.component_desc" bundle="${commonLables}" maxlength="100" validation ="txt.isrequired" default ="${actionList.componentDesc}" onblur="chk(this)"  onkeypress="return chkKey(event)"/></TD>	
				 
			
			<TD class="fieldLabel" >
			
			</TD>
			<TD> </TD>	
</tr>
<tR><td></td></tR>
 <TR>
			<TD class="fieldLabel" >
			<hdiits:caption captionid="Component" bundle="${commonLables}"></hdiits:caption>
			</TD>
				<td><hdiits:select  captionid="Component" bundle="${commonLables}" name="component" > 
						<hdiits:option value="Select">-----------Select-----------</hdiits:option>
						<c:forEach var="component" items="${componentList}">
							
		    						<option value=<c:out value="${component.componentCode}"/>>
										<c:out value="${component.componentDesc}"/></option>
		    								    					
										
										
						</c:forEach>		
					
				</hdiits:select>
			
					
			</tD>
			<TD class="fieldLabel" >
				
				
				<hdiits:button name="add" value="ADD" type="button" onclick="addtxt(this)" />
			</TD>
			<TD><hdiits:caption captionid="amount" bundle="${commonLables}"></hdiits:caption></TD>	
			<TD class="fieldLabel" >
			<hdiits:text
				name="txt_amount"
				 captionid="amount" bundle="${commonLables}" maxlength="10" />
			</TD>
			<TD> <hdiits:button name="add_amount" value="ADD" type="button"  onclick="addtxt(this)"/></TD>				
</TR>
<tR><td></td></tR>
 <TR>
			<TD class="fieldLabel" colspan=6 align=center>
				<hdiits:button name="add_plus" value="+" type="button" onclick="addtxt(this)"/>
				<hdiits:button name="add_minus" value="-" type="button"  onclick="addtxt(this)"/>
				<hdiits:button name="add_mul" value="*" type="button"  onclick="addtxt(this)"/>
				<hdiits:button name="add_div" value="/" type="button"  onclick="addtxt(this)"/>
				<hdiits:button name="add_Lbracket" value="(" type="button"  onclick="addtxt(this)"/>
				<hdiits:button name="add_Rbracket" value=")" type="button"  onclick="addtxt(this)"/>
			</TD>					
</TR>
<tR><td></td></tR>

 <TR>
			<TD class="fieldLabel" colspan=2>
			<hdiits:caption captionid="eis.component_exp" bundle="${commonLables}"></hdiits:caption>
			</TD >
			<TD class="fieldLabel" colspan=4><hdiits:textarea rows="3" cols="50" name="component_exp" captionid="eis.component_exp" bundle="${commonLables}" default ="${actionList.expression}"></hdiits:textarea></td>
</TR>
<tR><td></td></tR>
<tr></tr>
<tr></tr>
      
      
      
         
	</table> 
    
 </div>
 

 
 

	<jsp:include page="../../core/tabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

