<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @JSP Name : egovBoardRegister.jsp
  * @Description : 게시판 글 등록,수정 화면
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
<c:set var="registerFlag" value="${empty boardVO.bbscttNo ? 'title.insert' : 'title.update'}"/>
<title>egov.<spring:message code="board" /> <spring:message code="${registerFlag}" /> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>">

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="boardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/brd/egovBoardList.do'/>";
   	document.detailForm.submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
	if(confirm("<spring:message code="msg.del" />")){
		document.detailForm.action = "<c:url value='/brd/deleteBoard.do'/>";
	   	document.detailForm.submit();		
	}
}

/* 글 등록  및 수정 function */
function fn_egov_save() {	
	frm = document.detailForm;
	if(!validateBoardVO(frm)){
		return;
    }else{
    	if("<c:out value="${registerFlag}"/>" == "title.insert"){
    		if(confirm("<spring:message code="msg.insert" />")){
    			frm.action  = "<c:url value='/brd/insertBoard.do'/>";
    			frm.submit();		
    		}
    	}else{
    		if(confirm("<spring:message code="msg.update" />")){
    			frm.action  = "<c:url value='/brd/updateBoard.do'/>";
    			frm.submit();		
    		}
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
<form:form commandName="boardVO" name="detailForm">
	<!-- 타이틀 -->
	<div id="title2">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="board" /> <spring:message code="${registerFlag}" /></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;" summary="게시판에 글을 등록하거나 수정합니다.">
			<caption>게시판 등록 및 수정</caption>
		<colgroup>
			<col width="150" >
			<col width="" >
		</colgroup>
		<tr>
			<td class="tbtd_caption"><spring:message code="board.header.title" /></td>
			<td class="tbtd_content">
				<c:if test="${loginVO.memberNo != boardVO.memberNo && registerFlag !='title.insert' }">
					<c:out value="${boardVO.title}" />
				</c:if>
				<c:if test="${loginVO.memberNo == boardVO.memberNo || registerFlag =='title.insert'}">
					<form:input path="title" maxlength="20" cssClass="txt"  style="width: 50%" title="title"/>
					&nbsp;<form:errors path="title" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="tbtd_caption"><spring:message code="board.header.contents" /></td>
			<td class="tbtd_content">
			<c:if test="${loginVO.memberNo != boardVO.memberNo && registerFlag !='title.insert' }">
					<form:textarea path="contents" rows="20" cols="60" cssClass="textarea" readonly="true"/>
					&nbsp;<form:errors path="contents" />
				</c:if>
				<c:if test="${loginVO.memberNo == boardVO.memberNo || registerFlag =='title.insert'}">
					<form:textarea path="contents" rows="20" cols="60" cssClass="textarea"/>
					&nbsp;<form:errors path="contents" />
				</c:if>
			</td>
		</tr>
	</table>
  </div>
	<div id="sysbtn">
		<ul>
			<li><span class="btn_blue_l" title="<spring:message code="button.list" />"><a href="#link" onclick="fn_egov_selectList(); return false;"><spring:message code="button.list" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.list" />"/></span></li>
				<c:if test="${registerFlag == 'title.insert'}">
					<li><span class="btn_blue_l" title="<spring:message code="${registerFlag}" />"><a href="#link" onclick="fn_egov_save(); return false;"><spring:message code="${registerFlag}" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="${registerFlag}" />"/></span></li>
					<li><span class="btn_blue_l" title="<spring:message code="button.reset" />"><a href="#link" onclick="document.detailForm.reset(); return false;"><spring:message code="button.reset" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.reset" />"/></span></li>
				</c:if>
				<c:if test="${registerFlag == 'title.update'}">
					<c:if test="${loginVO.memberNo == boardVO.memberNo}">
						<li><span class="btn_blue_l" title="<spring:message code="${registerFlag}" />"><a href="#link" onclick="fn_egov_save(); return false;"><spring:message code="${registerFlag}" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="${registerFlag}" />"/></span></li>
						<li><span class="btn_blue_l" title="<spring:message code="button.delete" />"><a href="#link" onclick="fn_egov_delete(); return false;"><spring:message code="button.delete" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.delete" />"/></span></li>
						<li><span class="btn_blue_l" title="<spring:message code="button.reset" />"><a href="#link" onclick="document.detailForm.reset(); return false;"><spring:message code="button.reset" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.reset" />"/></span></li>
					</c:if>
					<c:if test="${loginVO.memberNo != boardVO.memberNo && loginVO.mngrSe == 'ROLE_ADMIN'}">
						<li><span class="btn_blue_l" title="<spring:message code="button.delete" />"><a href="#link" onclick="fn_egov_delete(); return false;"><spring:message code="button.delete" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.delete" />"/></span></li>
					</c:if>
				</c:if>
				<li><input type="submit" style="display:none" title="submit"/></li>
			</ul>
	</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
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

