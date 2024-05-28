<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalInstituteVerification"
	value="${resValue.totalInstituteVerification}"></c:set>
<c:set var="totalEmployeeForVerificationGPF"
	value="${resValue.totalEmployeeForVerificationGPF}"></c:set>


<c:set var="totalEmployeeForVerificationDCPS"
	value="${resValue.totalEmployeeForVerificationDCPS}"></c:set>
<c:set var="totalOfficeForVerification"
	value="${resValue.totalOfficeForVerification}"></c:set>
<c:set var="userType" value="${resValue.userType}"></c:set>
<c:set var="pendingWorkForAsstDDO"
	value="${resValue.pendingWorkForAsstDDO}"></c:set>
<c:set var="lFlag" value="${resValue.lFlag}"></c:set>


<hdiits:form name="pendingWork" action="" id="pendingWork"
	encType="multipart/form-data" validate="true" method="post">
	<input type="hidden" name="csrfToken" value="${csrfToken}" />
	<c:choose>
		<c:when
			test="${totalInstituteVerification != null || totalEmployeeForVerification != null || totalSchoolForVerification !=null || totalDDOForVerification != null}">
			<br />
			<br />
			<br />
			<br />


			<fieldset class="tabstyle">
				<legend>
					<font color="Red" size="4">Pending Works</font>
				</legend>

				<table align="center" border="1" width="60%">

					<tr>
						<td width="40%">Total Number of Institute Need your Approval
							is</td>
						<td width="7%">${totalInstituteVerification}</td>
						<td width="13%"><a
							href="./hrms.htm?actionFlag=approveDdoOfficeDataList&elementId=90002611"
							style="size: 10pxl;"> Approve Institute </a> <BR></td>
					</tr>
					<tr class="even">
						<td width="40%">Total Number of DCPS Employee Need your
							Verification is</td>
						<td width="7%">${totalEmployeeForVerificationDCPS}</td>
						<td width="13%"><a
							href="./hrms.htm?actionFlag=viewFormsForwardedByAsstZpRepoDDO&User=ReportingDDO&Use=Forward&elementId=90002593"
							style="size: 10pxl;"> Verify DCPS Employee </a> <BR></td>
					</tr>
					<tr>
						<td width="40%">Total Number of GPF Employee Need your
							Verification is</td>
						<td width="7%">${totalEmployeeForVerificationGPF}</td>
						<td width="13%"><a
							href="./hrms.htm?actionFlag=viewFormsForwardedByAsst&User=ZPDDO&elementId=700009"
							style="size: 10pxl;"> Verify GPF Employee </a> <BR></td>
					</tr>
					<tr class="even">
						<td width="40%">Total Number of Office Need your Verification
							is</td>
						<td width="7%">${totalOfficeForVerification}</td>
						<td width="13%"><a
							href="./hrms.htm?actionFlag=loadApproveDdoOffice&elementId=90002603"
							style="size: 10pxl;"> Approve Office Details </a> <BR></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<p>
								<marquee behavior="alternate" direction="left"
									style="width: 80%;" scrollamount="4">
									<font color="red">Please Complete your Pending Works.</font>
								</marquee>
							</p>
						</td>
					</tr>

				</table>
				<br />
				<div style="width: 100%; text-align: center;">
					<marquee behavior="alternate" direction="left" style="width: 80%;"
						scrollamount="4">

						<font color="green" size="4">Please Complete your all
							pending works.</font>

					</marquee>
				</div>
				<br />
			</fieldset>


		</c:when>
		<c:otherwise>
			<script>
				alert('Congratulation! You have no pending works');
			</script>
		</c:otherwise>
	</c:choose>

</hdiits:form>
