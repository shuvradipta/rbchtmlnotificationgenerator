<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
       uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="https://www1.royalbank.com/uos/common/images/icons/favicon.ico?5" rel="icon" />

<!-- BOOTSTRAP : BEGIN -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- BOOTSTRAP : END -->

<!--  ANGULARJS : BEGIN -->

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script src="/js/angular_modules/mobileNoticeAngularModule.js"></script>

<!--  ANGULARJS : END -->

<title>XML Page Generator</title>
</head>
<!-- Override CSS : BEGIN -->
<style>
.navbar-default {
    background-color: white;
    border-color: white;
}
.panel-nopadding {
	padding-right: 0px;
	padding-left: 0px;
}
.bodyContentText {
	font-size: 14px;
}
</style>
<!-- Override CSS : END -->
<body ng-app="mobileNoticeApp" ng-controller="mobileNoticeController as mnCtrl">

<!-- Header : BEGIN -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${appConfigMappings.HOMEDOMAIN}/home.html"><img src="${appConfigMappings.HOMEDOMAIN}/images/rbc_royalbank_en.gif" /></a>
		</div>
	</div>
</nav>
<!-- Header : END -->

<!-- Main Body : BEGIN -->
<c:set var="serviceNoticeGenAppUrl" value="${appConfigMappings.SERVICENOTICEGENAPPURL}"></c:set>
<c:set var="htmlGenAppUrl" value="${appConfigMappings.HTMLGENAPPURL}"></c:set>

<div class="container-fluid text-center">
	<div class="row content">
		<!-- LHN : BEGIN -->
		<div class="col-sm-2 sidenav">
			<p><a href="${serviceNoticeGenAppUrl}">Work with Service Notices</a></p>
			<p><a href="${htmlGenAppUrl}">Work with HTML</a></p>
			<p class="text-muted">Work with Mobile</p>
		</div>
		<!-- LHN : END -->
		<!-- Middle section : BEGIN -->
		<div class="col-sm-8 text-left">
			<c:if test="${PROCESSING_STATUS eq true}">
			<div class="alert alert-success">
			  	<strong>Success!</strong> The file was successfully generated. 
			  	<p>Please use the Download File buttons on the right to view/download the file. </p> 
			</div>
			</c:if>
			<c:if test="${PROCESSING_STATUS eq false}">
			<div class="alert alert-danger">
			  	<strong>Error!</strong> There was some error while processing the input.
			</div>
			</c:if>
			<h1>Generate XML</h1>
			<p class="text-info">
				Please fill up the details below and the XML file will be generated
			</p>
			<p class="text-warning">
				For safety purposes, a row will be ignored if the any of the fields are blank.
			</p>
			<form action="/MobileNoticeServlet" method="post" name="xmlGeneratorForm">
			<div class="container">
				<div class="row">
					<div class="table-responsive">
						<table class="table table-condensed table-bordered col-sm-8 bodyContentText">
							<thead>
								<tr>
									<c:forEach var="columnName" items="${tableColumnNames}">
										<th>${columnName}</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="1" end="${appConfigMappings.MAX_MOBILE_NOTICE_COUNT}" var="row">
								<tr>
									<td><input type="text" name="noticeHeader${row}"/></td>
									<td><textarea cols="50" rows="5" name="noticeDescription${row}" ></textarea></td>
									<td><select multiple="multiple" name="platform${row}"><option value="iphone">iphone</option><option value="android">android</option><option value="bb10">bb10</option></select></td>
									<td><input type="date" name="startDate${row}" ng-model="mnCtrl.startTime${row}" min="${serverDateTime}" />
									<div role="alert">
								    <span class="error" ng-show="xmlGeneratorForm.startDate${row}.$error.datetimelocal">Not a valid date!</span></div></td>
									<td><input type="date" name="expiryDate${row}" ng-model="mnCtrl.expiryTime${row}" min="${serverDateTime}"/>
									<div role="alert">
								    <span class="error" ng-show="xmlGeneratorForm.expiryDate${row}.$error.datetimelocal">Not a valid date!</span></div></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div style="padding-bottom:15px;padding-top:10px;"><div class="text-center"><button class="btn btn-primary btn-md">Submit Changes <span class="glyphicon glyphicon-floppy-disk"></span></button></div></div>
				</div>
			</div>
			</form>
		</div>
		<!-- Middle section : END -->
		<!-- RHN : BEGIN -->
		<div class="col-sm-2 sidenav">
			<c:if test="${PROCESSING_STATUS eq true}">
				<p><a class="btn btn-success btn-sm" download="mobile-message.xml" href="/xml/mobile-message.xml">Download XML File <span class="glyphicon glyphicon-floppy-save"></span></a></p>
			</c:if>
		</div>
		<!-- RHN : END -->
	</div>
</div>
<!-- Main Body : END -->

</body>
</html>