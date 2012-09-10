<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
   /**
    * @JSP Name : EgovCategoryPopup.jsp
    * @Description : 카테고리 선택 팝업화면
    * @Modification Information
    * 
    *   수정일         수정자                   수정내용
    *  -------    --------    ---------------------------
    *  2011.06.07  이영진          최초 생성
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
<title>egov | <spring:message code="category.title" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>">

<script type="text/javaScript" language="javascript" defer="defer">

/* 카테고리코드 전달*/
function fn_egov_select(ctgryId, ctgryNm) {
	window.opener.document.insertForm.ctgryId.value = ctgryId;
	window.opener.document.insertForm.ctgryNm.value = ctgryNm;
	document.listForm.submit();
	self.close();
}
/* 카테고리 리스트 출력 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/springrest/cgr.html?name=popup'/>";
   	document.listForm.submit();
}
 
/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/springrest/cgr.html;'/>";
   	document.listForm.submit();
}

</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form name="listForm" method="GET">
<%-- <form:form commandName="searchVO" name="listForm" method="GET"> --%>
<input type="hidden" name="selectedId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title2">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="category.title" /></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리의 목록을 볼 수 있습니다.">
			<caption>카테고리 목록</caption>
			<colgroup>
				<col width="10">				
				<col width="100">				
				<col width="50" >
				<col width="100" >
			</colgroup>
			<thead>		  
				<tr>
					<th align="center"><spring:message code="cgr.header.no" /></th>
					<th align="center"><spring:message code="cgr.header.code" /></th>
					<th align="center"><spring:message code="cgr.header.nm" /></th>
					<th align="center"><spring:message code="cgr.header.dc" /></th>
				</tr>
			</thead>
			<c:forEach var="category" items="${categoryList}" varStatus="status">
			<tr>
				<td align="center" class="listtd"><c:out value="${status.count}"/></td>
				<td align="center" class="listtd"><a href="#link" onclick="fn_egov_select('<c:out value="${category.ctgryId}"/>','<c:out value="${category.ctgryNm}"/>'); return false;"><c:out value="${category.ctgryId}"/>&nbsp;</a></td>
				<td align="left" class="listtd"><c:out value="${category.ctgryNm}"/></td>
				<td align="center" class="listtd"><c:out value="${category.dc}"/>&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<input type="submit" style="display:none" title="submit"/>
	<!-- /List -->
	
<!-- 	<div id="paging"> -->
<%-- 		<ui:pagination paginationInfo = "${paginationInfo}" --%>
<!-- 				   type="image" -->
<!-- 				   jsFunction="fn_egov_link_page" -->
<!-- 				   /> -->
<%-- 		<form:hidden path="pageIndex" /> --%>
<!-- 	</div> -->
</div>
</form:form>
</body>
</html>
