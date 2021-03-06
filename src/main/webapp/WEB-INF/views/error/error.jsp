<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body id="errorContent">
	<div id="errorCodeBox">
		<span id="errorCode">401</span> 
	</div>
	<div id="errorDetailBox">
	${responseCode }
		<span>No permission.</span>
	</div>
	<br>
	<span id="errorRefreshMsg"></span>		
</body>
<script src="/dist/error/error.js"></script>
</html>