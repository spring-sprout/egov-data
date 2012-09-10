<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : egovMemberRegister.jsp
   * @Description :  회원 등록,수정 화면
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
<c:set var="registerFlag" value="${empty memberVO.memberNo ? 'title.insert' : 'title.update'}"/>
<title>egov | <spring:message code="member" /> <spring:message code="${registerFlag}" /> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>">


<script type="text/javaScript" language="javascript" defer="defer">

/* 회원 삭제 function */
function fn_egov_delete() {
	if(confirm("<spring:message code="mbr.removemem" />")){
	   	document.detailForm.action = "<c:url value='/mbr/deleteMember.do'/>";
	   	document.detailForm.submit();		
	}
}

/* 회원 등록, 수정 function */
function fn_egov_save() {	
	frm = document.detailForm;
  	if("<c:out value="${registerFlag}"/>" == "title.insert"){
		if(confirm("<spring:message code="msg.insert" />")){
			frm.action  = "<c:url value='/mbr/insertMember.do'/>";
			frm.submit();		
		}
	}else{
		if(confirm("<spring:message code="msg.update" />")){
			frm.action  = "<c:url value='/mbr/updateMember.do'/>";
			frm.submit();		
		}
	}
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
<form:form commandName="memberVO" name="detailForm">
	<!-- 타이틀 -->
	<div id="title2">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="member" /> <spring:message code="${registerFlag}" /></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table width="100%" border="1" cellpadding="0" cellspacing="0"  style="BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;" summary="회원가입하거나 정보를 변경 할 수 있습니다.">
			<caption>회원 가입 및 정보 수정</caption>
		<colgroup>
			<col width="150" >
			<col width="" >
		</colgroup>
		<tr>
			<td class="tbtd_caption">*<spring:message code="mbr.header.id" /></td>
			<td class="tbtd_content">
				<c:if test="${registerFlag == 'title.insert'}">
					<form:input path="id" maxlength="20" cssClass="txt" style="width: 30%;" title="id"/> &nbsp; <spring:message code="mbr.onlyeng" />
					&nbsp;<form:errors path="id" />
					<c:if test="${resultMsg == 'idDpl'}" ><spring:message code="mbr.idDpl.msg" /></c:if>
				</c:if>
				<c:if test="${registerFlag == 'title.update'}">
					<form:input path="id" readonly="true" cssClass="essentiality" style="width: 30%;" title="id"/>
					&nbsp;<form:errors path="id" />
				</c:if>
				
			</td>
		</tr>
		<tr>
			<td class="tbtd_caption">*<spring:message code="mbr.header.pass" /></td>
			<td class="tbtd_content">
				<form:password path="password" maxlength="20" cssClass="txt" style="width: 30%;" title="password"/>
					&nbsp;<form:errors path="password" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption">*<spring:message code="mbr.header.name" /></td>
			<td class="tbtd_content">
				<form:input path="name" maxlength="20" cssClass="txt" style="width: 30%;" title="name"/>
					&nbsp;<form:errors path="name" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption">*<spring:message code="mbr.header.email" /></td>
			<td class="tbtd_content">
				<form:input path="email" maxlength="50" cssClass="txt" style="width: 70%;" title="email"/>
				  &nbsp;<form:errors path="email" /></td>
		</tr>
		
		<c:if test="${memberVO.mngrSe == 'ROLE_USER' || memberVO.mngrSe == 'CODE02'}">
			<tr>
				<td class="tbtd_caption">*<spring:message code="mbr.header.auth" /></td>
				<td class="tbtd_content">
					<form:select path="mngrSe" title="member code">
						<option value="CODE02" label="manager code"></option>
					</form:select>
					&nbsp;<form:errors path="mngrSe" /></td>
			</tr>
		</c:if>
		<c:if test="${memberVO.mngrSe != 'ROLE_USER' && memberVO.mngrSe != 'CODE02'}">
			<tr>
				<td class="tbtd_caption">*<spring:message code="mbr.header.auth" /></td>
				<td class="tbtd_content">
					<form:select path="mngrSe" title="member code">
						<option value="CODE01" label="<spring:message code="mbr.select.admin" />"></option>
						<option value="CODE02" label="<spring:message code="mbr.select.user" />"></option>
					</form:select>
					&nbsp;<form:errors path="mngrSe" /></td>
			</tr>
		</c:if>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.tel" /></td>
			<td class="tbtd_content">
				<form:input path="telno" maxlength="20" cssClass="txt" style="width: 30%;" title="tel"/>&nbsp;<spring:message code="mbr.tel" />
					&nbsp;<form:errors path="telno" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.mobile" /></td>
			<td class="tbtd_content">
				<form:input path="mobile" maxlength="20" cssClass="txt" style="width: 30%;" title="mobile"/>&nbsp;<spring:message code="mbr.mobile" />
					&nbsp;<form:errors path="mobile" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.zip" /></td>
			<td class="tbtd_content">
				<form:input path="zip" maxlength="6" cssClass="txt" style="width: 10%;" title="zip"/>&nbsp;<spring:message code="mbr.zip" />
					&nbsp;<form:errors path="zip" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.adres" /></td>
			<td class="tbtd_content">
				<form:input path="adres" cssClass="txt"  cssStyle="width:80%;" title="address"/>
					&nbsp;<form:errors path="adres" /></td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="mbr.header.dAdres" /></td>
			<td class="tbtd_content">
				<form:input path="detailAdres"  cssClass="txt"  cssStyle="width:80%;" title="detail address"/>
					&nbsp;<form:errors path="detailAdres" /></td>
		</tr>
	</table>
  </div>
	<div id="sysbtn">
		<ul>
			<li><span class="btn_blue_l" title="<spring:message code="${registerFlag}" />"><a href="#link" onclick="fn_egov_save(); return false;"><spring:message code="${registerFlag}" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="${registerFlag}" />"/></span></li>
			<c:if test="${registerFlag == 'title.update'}">
			<li><span class="btn_blue_l" title="<spring:message code="button.delete" />"><a href="#link" onclick="fn_egov_delete(); return false;"><spring:message code="button.delete" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.delete" />"/></span></li>
			</c:if>
			<li><span class="btn_blue_l" title="<spring:message code="button.reset" />"><a href="#link" onclick="document.detailForm.reset(); return false;"><spring:message code="button.reset" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.reset" />"/></span></li>
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

