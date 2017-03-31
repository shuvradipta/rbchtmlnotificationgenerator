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

<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script> -->

<!--  ANGULARJS : END -->

<title>HTML Page Generator</title>
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
<body>

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

<div class="container-fluid text-center">
	<div class="row content">
		<!-- LHN : BEGIN -->
		<div class="col-sm-2 sidenav">
			<p><a href="${serviceNoticeGenAppUrl}">Work with Service Notices</a></p>
			<p class="text-muted">Work with HTML</p>
			<p><a href="/MobileNoticeServlet">Work with Mobile</a></p>
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
			<h1>Generate an HTML</h1>
			<p class="text-info">
				Please fill up the details below and the HTML file will be generated
			</p>
			<form action="/HTMLGeneratorServlet" method="post" name="htmlGeneratorForm">
			<div class="container">
				<div class="row">
					<div class="table-responsive">
						<table class="col-sm-8 bodyContentText">
							<tr>
								<td>
									<div class="form-group">
								    	<label for="htmlPageTitle">HTML Page Title:</label>
								    	<input type="text" class="form-control" id="htmlPageTitle" name="htmlPageTitle">
								  	</div>
							  	</td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
								    	<label for="htmlFileName">HTML File Name:</label>
								    	<input type="text" class="form-control" id="htmlFileName" name="htmlFileName">
								  	</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
								    	<label for="bodyContent">Page Content:</label>
								    	<p><textarea rows="10" cols="100" id="bodyContent" name="bodyContent"></textarea></p>
								  	</div>
								</td>
							</tr>
							<tr>
								<td>
									<div style="padding-bottom:15px;padding-top:10px;"><div class="text-center">
								    	<button type="submit" class="btn btn-primary btn-md">Generate HTML file <span class="glyphicon glyphicon-floppy-disk"></span></button>
								    </div></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			</form>
		</div>
		<!-- Middle section : END -->
		<!-- RHN : BEGIN -->
		<div class="col-sm-2 sidenav">
			<c:if test="${PROCESSING_STATUS eq true}">
				<p><a class="btn btn-success btn-sm" download="${htmlFileName}" href="/html/generated.html">Download HTML <span class="glyphicon glyphicon-floppy-save"></span></a></p>
			</c:if>
		</div>
		<!-- RHN : END -->
	</div>
</div>
<!-- Main Body : END -->

</body>
</html>