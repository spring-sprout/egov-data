<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @JSP Name : EgovMain.jsp
  * @Description : 메인 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2011.06.07  신혜연          최초 생성
  *
  * author 실행환경팀 
  * Copyright (C) 2011 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>egov | main</title>

<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>" >
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/jquery-ui-1.8.14.custom.css'/>">
<script type="text/javaScript" src="<c:url value='/js/jquery-1.5.1.min.js'/>"></script>
<script type="text/javaScript" src="<c:url value='/js/jquery-ui-1.8.14.custom.min.js'/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">

$(function(){
	$('#tabs').tabs();
	ajaxCall('Restful');
	$("#tabs").bind('tabsselect', function(event, ui) {
		ajaxCall(ui.tab.innerHTML);
	});
});

function ajaxCall(tabName){
	$.ajax({
		  url:  '<c:url value="/com/ajax.do" />',
		  data: "tabName="+tabName,
		  dataType: 'json',
		  success: function(data) { 
			  $(data.divId).html(data.description);
		}
	});
}

</script>

</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<!-- 전체 레이어 시작 -->
	<div id="wrap">
		<!-- header 시작 -->
		<div id="header"><%@ include
				file="/WEB-INF/jsp/egovframework/rte/tex/com/header.jsp"%></div>
		<!-- //header 끝 -->
		<!-- container 시작 -->
		<div id="container">
			<!-- 좌측메뉴 시작 -->
			<div id="leftmenu"><%@ include
					file="/WEB-INF/jsp/egovframework/rte/tex/com/leftmenu.jsp"%></div>
			<!-- //좌측메뉴 끝 -->

			<!-- content 시작 -->
			<div id="content_pop">
			<spring:message code="main.title"/>
			<div id="tabs">
					<ul>
						<li><a href="#tabs-1">Restful</a></li>
						<li><a href="#tabs-2">ORM</a></li>
						<li><a href="#tabs-3">Excel</a></li>
						<li><a href="#tabs-4">OXM</a></li>
					</ul>
					<div id="tabs-1" class="Restful" style="height: 250px;"></div>
					<div id="tabs-2" class="ORM" style="height: 250px;"></div>
					<div id="tabs-3" class="Excel" style="height: 250px;"></div>
					<div id="tabs-4" class="OXM" style="height: 250px;"></div>
				</div>
			</div>
			<!-- //content 끝-->
		</div>
		<!-- //container 끝-->
		<!-- footer 시작 -->
		<div id="footer"><%@ include file="/WEB-INF/jsp/egovframework/rte/tex/com/footer.jsp"%></div>
		<!-- //footer 끝 -->
	</div>
	<!--// 전체 레이어 끝 -->
</body>
</html>

