<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bookstoreStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/used.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/service.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/libBasicPage_layout.css">
</head>
<div id="main" class="wrapper">
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="basic_main_body">
		<div id="page_nav">
			<tiles:insertAttribute name="nav"/>
		</div>
		<div id="page_body">
			<tiles:insertAttribute name="body"/>
		</div>
	</div>
	<div id="main_footer" class="page_clear">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</html>