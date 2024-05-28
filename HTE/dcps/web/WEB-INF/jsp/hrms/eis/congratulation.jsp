<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Added By Shivram 09122020 -->

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

<style>
.modal {
	position: fixed;
	top: 0;
	left: 30%;
	z-index: 1050;
	width: 40%;
	height: 100%;
	overflow: hidden;
	outline: 0;
}

.modal-header {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: start;
	align-items: flex-start;
	-ms-flex-pack: justify;
	justify-content: space-between;
	padding: 1rem 1rem;
	border-bottom: 1px solid #dee2e6;
	border-top-left-radius: calc(.3rem - 1px);
	border-top-right-radius: calc(.3rem - 1px);
}

.modal-title {
	margin-bottom: 0;
	line-height: 1.5;
	margin: 0;
}

.close {
	float: right;
	font-size: 22px;
	font-weight: 900;
	line-height: 1;
	color: #000;
	text-shadow: 0 1px 0 #fff;
	opacity: .5;
	margin-top: -31px;
	border: 1px solid transparent !important;
}

.modal-dialog {
	max-width: 500px;
	margin: 1.75rem auto;
	margin-top: 13% !important;
	box-shadow: 2px 2px 7px #000;
	border: 1px solid #824000;
}

.modal-dialog {
	max-width: 500px;
	margin: 1.75rem auto;
	margin-top: 13% !important;
	box-shadow: 2px 2px 7px #000;
	border: 1px solid #824000;
}

.modal-dialog {
	position: relative;
	width: auto;
	margin: .5rem;
	pointer-events: none;
}

.modal-dialog {
	position: relative;
	width: auto;
	margin: .5rem;
	pointer-events: none;
}

.modal-content {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: .3rem;
	outline: 0;
}

.h4, h4 {
	font-size: 1.5rem;
}

.modal-header .close {
	padding: 1rem 1rem;
	margin: 0 -1rem -1rem auto;
	cursor: pointer;
	color: #fff;
	background: transparent;
}

.modal-body {
	position: relative;
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	padding: 1rem;
}

.modal-footer {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-wrap: wrap;
	flex-wrap: wrap;
	-ms-flex-align: center;
	align-items: center;
	-ms-flex-pack: end;
	justify-content: flex-end;
	padding: .75rem;
	border-top: 1px solid #dee2e6;
	border-bottom-right-radius: calc(.3rem - 1px);
	border-bottom-left-radius: calc(.3rem - 1px);
}

.modal-title {
	color: #fff;
	text-align: center;
}

h3 {
	color: #000;
}

.modal {
	top: 7%
}

.close {
	color: #fff !important;
}

.modal-body {
	padding: 20px;
	text-align: justify;
	font-style: normal;
}

.modal-body h3 {
	font-size: 18px;
}

.modal-header {
	padding: 8px;
}

.modal-title {
	font-size: 20px;
}

.modal-footer {
	text-align: center;
	padding: 5px;
}
</style>


<script>
	function hide() {
		document.getElementById("myModal").style.display = "none";
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		$(".mclose").click(function() {
			$("#myModal").hide();
		});
		$(".close").click(function() {
			$("#myModal").hide();
		});

	});
</script>

<hdiits:form name="pendingWork" action="" id="pendingWork"
	encType="multipart/form-data" validate="true" method="post">
	<c:choose>
		<c:when
			test="${totalInstituteVerification != null || totalEmployeeForVerification != null || totalSchoolForVerification !=null || totalDDOForVerification != null}">
			<br />
			<br />
			<br />
			<br />


			<fieldset class="tabstyle">
				<legend>
					<font color="Red" size="5">Congratulation</font>
				</legend>
				<br /> <br />
				<div style="width: 100%; text-align: center;">
					<marquee behavior="alternate" direction="left" style="width: 80%;"
						scrollamount="4">

						<font color="green" size="6">You have no pending work.</font>

					</marquee>
				</div>
				<br /> <br /> <br /> <br />
			</fieldset>


		</c:when>
		<c:otherwise>
			<script>
				alert('Congratulation! You have no pending work');
			</script>
		</c:otherwise>
	</c:choose>

</hdiits:form>
