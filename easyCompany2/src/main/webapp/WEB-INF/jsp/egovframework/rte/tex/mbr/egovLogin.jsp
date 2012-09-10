<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @JSP Name : egovLogin.jsp
  * @Description : 로그인 화면
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
<meta http-equiv="content-language" content="ko">
<title>egov | <spring:message code="mbr.login" /> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>">


<script type="text/javaScript" language="javascript" defer="defer">

/* 로그인 function */
function fn_egov_login() {
	mberId = document.detailForm.j_username.value;
	password = document.detailForm.j_password.value;
	
	if(mberId == "" && password == ""){
		alert("<spring:message code="mbr.blankBoth" />");
		return;
	}else if(mberId != "" && password == ""){
		document.detailForm.j_password.focus();
		alert("<spring:message code="mbr.blankPass" />");
		return;
	}else if(password != "" && mberId == ""){
		document.detailForm.j_username.focus();
		alert("<spring:message code="mbr.blankId" />");
		return;
	}
	
   	document.detailForm.action = "<c:url value='/j_spring_security_check'/>";
   	document.detailForm.submit();		
}

/* 회원 가입 function */
// function fn_egov_register() {	
// 	document.detailForm.action = "<c:url value='/mbr/insertMemberView.do'/>";
//    	document.detailForm.submit();
// }

/* 비밀번호 찾기 function */
function fn_egov_searchPass() {	
	document.detailForm.action = "<c:url value='/mbr/searchPasswordView.do'/>";
   	document.detailForm.submit();
}

function initFn(){
	error = document.detailForm.login_error.value;
	if(error == 1){
		alert("<spring:message code="mbr.loginFail" />");
	}
}

</script>
</head>
<body onload="initFn();" style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
	<div id="header"><%@ include file="/WEB-INF/jsp/egovframework/rte/tex/com/header.jsp" %></div>
	<!-- //header 끝 -->	
	<!-- container 시작 -->
	<div id="container">
		<!-- 좌측메뉴 시작 -->
		<div id="leftmenu"><%@ include file="/WEB-INF/jsp/egovframework/rte/tex/com/leftmenu.jsp" %></div>
		<!-- //좌측메뉴 끝 -->
		
		
<!-- content 시작 -->
<div id="content_pop">
<form:form  name="detailForm" >
<input type=hidden name=login_error value="${login_error}" />
			
	<!-- 타이틀 -->
	<div id="title2">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="mbr.login" /></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;" summary="아이디와 비밀번호를 입력하여 로그인 합니다.">
		<caption>로그인</caption>
		<colgroup>
			<col width="150" >
			<col width="" >
		</colgroup>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.id" /></td>
			<td class="tbtd_content">
					<input name="j_username" maxlength="20" style="width: 30%;" title="<spring:message code="mbr.header.id" />"/>	</td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.pass" /></td>
			<td class="tbtd_content">
				<input type=password name="j_password" maxlength="20" style="width: 30%;" title="<spring:message code="mbr.header.pass" />"/></td>
		</tr>
	</table>
  </div>
	<div id="sysbtn">
		<ul>
			<li><span class="btn_blue_l" title="<spring:message code="mbr.login" />"><a href="#link" onclick="fn_egov_login(); return false;"><spring:message code="mbr.login" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="mbr.login" />"/></span></li>
			<li><span class="btn_blue_l" title="<spring:message code="mbr.searchPass" />"><a href="#link" onclick="fn_egov_searchPass(); return false;"><spring:message code="mbr.searchPass" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="mbr.searchPass" />"/></span></li>
			<li><input type="submit" style="display:none" title="submit"/></li>
		</ul>
	</div>
</form:form>
</div>


<!-- //content 끝-->
	</div>
	<!-- //container 끝-->
	<!-- footer 시작 -->
	<div id="footer"><%@ include file="/WEB-INF/jsp/egovframework/rte/tex/com/footer.jsp" %></div>
	<!-- //footer 끝 -->
	</div>
	<!--// 전체 레이어 끝 -->
</body>
</html>

