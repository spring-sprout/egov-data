<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : egovSearchPassword.jsp
   * @Description : 비밀번호 찾기 화면
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

/* 비밀번호 찾기 function */
function fn_egov_searchPass() {	
	var frm=document.detailForm;
	if(frm.id.value == ""){
		alert("<spring:message code="mbr.blankId" />");
		frm.id.focus();
		return;
	}else if(frm.email.value == ""){
		alert("<spring:message code="mbr.blankEmail" />");
		frm.email.focus();
		return;
	}
	frm.action = "<c:url value='/mbr/sendEmail.do'/>";
   	frm.submit();
}

</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
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
<form:form name="detailForm" >
	<!-- 타이틀 -->
	<div id="title2">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="mbr.searchPass" /></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table width="100%" border="1" cellpadding="0" cellspacing="0"  style="BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;" summary="회원의 아이디와 이메일주소를 입력하여 비밀번호를 확인합니다.">
			<caption>비밀번호 찾기</caption>
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		<tr>
			<td class="tbtd_content" colspan=2>* <spring:message code="msg.searchEmail" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.id" /></td>
			<td class="tbtd_content">
					<input name="id" maxlength="20" style="width: 30%;"/>
			</td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.email" /></td>
			<td class="tbtd_content">
				<input name="email" maxlength="50" style="width: 70%;"/> </td>
		</tr>
	</table>
  </div>
	<div id="sysbtn">
		<ul>
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

