<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<title>${projectName}</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body oncontextmenu="return true" ondragstart="return true" onselectstart="return true">
	<div id="layoutHeader">
		<tiles:insertAttribute name="header" ignore="true"/>
	</div>
	<div id="layoutBody">
		<div id="sidebar">
			<tiles:insertAttribute name="sidebar" ignore="true"/>
		</div>

		<div id="content">
				<tiles:insertAttribute name="content" ignore="true"/>
		</div>
	</div>
</body>
</html>
