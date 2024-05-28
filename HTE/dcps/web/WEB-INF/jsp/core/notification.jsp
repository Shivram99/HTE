<!--<script type="text/javascript">
self.location.href = "ifms.htm?actionFlag=displayPendingWork";
</script>
-->



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Css Globe: tag clouds</title>
<script src="script/NewLogin/js/jquery.js"
	type="text/javascript"></script>
<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalEmployeeForVerificationGPF" value="${resValue.totalEmployeeForVerificationGPF}"></c:set>
<c:set var="totalEmployeeForVerificationDCPS" value="${resValue.totalEmployeeForVerificationDCPS}"></c:set>
<c:set var="totalFilesNotSend" value="${resValue.totalFilesNotSend}"></c:set>
<c:set var="filesNotSendToTreasury" value="${resValue.filesNotSendToTreasury}"></c:set>

<style>
.link:hover {
   font-family: cursive;
   font-stretch: wider;
   color: lime;
   text-decoration: blink;
 
}
</style>
	
<script type="text/javascript">
function callThisFunction(actionFlag){
	//alert("in side caloing");
self.location.href = "ifms.htm?actionFlag="+actionFlag;

}


</script>

	<style>
         .ui-widget-header,.ui-state-default, .ui-button, .ui-widget, .ui-state-focus, .ui-state-hover{
            background: #AD6000;
            border: 1px solid  #AD6000;
           
            font-weight: bold;
            font-family: Calibri; 
            font-size: 22px;
            
         }
         .ui-dialog .ui-dialog-content { 
         background: #FFDAA3; 
         }
      </style>
 <script>
 $(document).ready(function() {
         $(function() {
            $( "#dialog-1" ).dialog({
               show: {effect: 'slide', speed: 'fast'},
               autoOpen: true,  
               height: 'auto',
               width: 450,
           
               position: {
                   my: "right bottom",
                   at: "right bottom"
            }
            
            });
         
            
            $( "#ignore" ).click(function() {
               $( "#dialog-1" ).dialog( "close" );
            });
         });
 });
  </script>

</head>

<body >
<br/>

<c:choose>
<c:when test="${totalFilesNotSend!=0 || filesNotSendToTreasury!=0}">
<div id="dialog-1" title="You have notifications pending" style="height: 1%">
      <table >
     
     <c:if test="${totalFilesNotSend!=0}">
	      <tr>
		      <td width="100%" style="font-size: 20px; font-style: italic">
		       You have ${totalFilesNotSend} file(s) pending . Kindly upload the same in NSDL
		      </td>
		   </tr>
		   
		<%--     <tr>
		      <td class="link">
		      <a href="ifms.htm?actionFlag=loadNPSNSDLForm" style="font-variant: small-caps; font-weight: bold;"  target="_blank">Generate NSDL File</a>
		      </td>
		      
	      </tr>--%>
	 </c:if>
	     <c:if test="${filesNotSendToTreasury!=0}">    
	       <tr>
		      <td width="100%" style="font-size: 20px; font-style: italic">
		      You have received transaction id for ${filesNotSendToTreasury} file(s). Please generate bill against these files and send the contribution amount to trustee bank
		      </td>
		   </tr>
		   
		    <tr>
		      <td class="link">
		      <a href="ifms.htm?actionFlag=loadNPSNSDLForm" style="font-variant: small-caps; font-weight: bold;"  target="_blank">Generate NSDL file</a>
		      </td>
		      
	      </tr>
	      
	       
	     </c:if>
      </table>
     
     </div>

</c:when>

</c:choose>


</body>
</html>
