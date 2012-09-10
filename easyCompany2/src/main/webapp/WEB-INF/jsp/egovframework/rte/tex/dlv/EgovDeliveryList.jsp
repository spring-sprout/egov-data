<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovDeliveryList.jsp
   * @Description : 입찰목록
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
<title>egov | <spring:message code="purchs.title"/></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>">
<script type="text/javaScript" language="javascript" defer="defer">

/* 배송상태 변경 function */
function fn_egov_changeDlvy(id,sttus) {
	document.listForm.purchaseId.value = id;
	document.listForm.dlvySe.value = sttus;
	
   	document.listForm.action = "<c:url value='/dlv/updateDlvySttus.do'/>";
   	document.listForm.submit();		
}

/* 상품상세 화면 function */
function fn_egov_select(id) {
	document.listForm.selectedId.value = id;
   	document.listForm.action = "<c:url value='/gds/getGoodsView.do'/>";
   	document.listForm.submit();		
}

/* 검색 후 리스트화면 function */
function fn_egov_selectList() {
	document.listForm.pageIndex.value='1';
	document.listForm.action = "<c:url value='/dlv/selectListPurchase.do'/>";
   	document.listForm.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/dlv/selectListPurchase.do'/>";
   	document.listForm.submit();
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
			<form:form commandName="searchVO" name="listForm" method="post">
			<input type="hidden" name="selectedId" />
			<input type="hidden" name="searchCondition" value="1"/>
			<input type="hidden" name="purchaseId" />
			<input type="hidden" name="dlvySe" />
				<!-- 타이틀 -->
				<div id="title2">
					<ul>
						<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="purchs.title"/> </li>
					</ul>
				</div>
				<!-- // 타이틀 -->
				<div id="search">
					<ul>
						<li><spring:message code="purchs.header.goodsNm"/>: </li>
						<li><form:input path="searchKeyword" cssClass="txt" title="searchKeyword"/></li>
						<li><span class="btn_blue_l" title="<spring:message code="button.search" />"><a href="#link" onclick="fn_egov_selectList(); return false;"><spring:message code="button.search" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="button.search" />"/></span></li>
					</ul>		
				</div>
				<!-- List -->
				<div id="table">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="입찰 목록을 확인합니다.">
						<caption>입찰목록</caption>
						<colgroup>
							<col width="40" >	 			
							<col width="100" >	 			
							<col width="100" >	 			
							<col width="200" >  
							<col width="100" > 
							<col width="40" > 
							<col width="100" >
							<col width="80" >
						</colgroup>	
						<thead>	  
						<tr>
							<th align="center"><spring:message code="purchs.header.no"/></th>
							<th align="center"><spring:message code="purchs.header.bidId"/></th>
							<th align="center"><spring:message code="purchs.header.date"/></th>
							<th align="center"><spring:message code="purchs.header.goodsNm"/></th>
							<th align="center"><spring:message code="purchs.header.price"/></th>
							<th align="center"><spring:message code="purchs.header.qy"/></th>
							<th align="center"><spring:message code="purchs.header.sum"/></th>
							<th align="center"><spring:message code="purchs.header.status"/></th>
						</tr>
						</thead>
							<c:forEach var="result" items="${resultList}" varStatus="status"> 
								<tr>
									<td align="center" class="listtd"><c:out value="${(paginationInfo.currentPageNo - 1) * paginationInfo.pageSize + status.count}"/>&nbsp;</td>
									<td align="center" class="listtd"><c:out value="${result.purchsId}"/>&nbsp;</td>
									<td align="center" class="listtd"><c:out value="${result.purchsDe}"/>&nbsp;</td>
									<td align="center" class="listtd"><a href="#link" onclick="fn_egov_select('<c:out value="${result.goodsVO.goodsId}"/>');return false;"><c:out value="${result.goodsVO.goodsNm}"/></a>&nbsp;</td>
									<td align="center" class="listtd"><spring:eval expression='new java.text.DecimalFormat("###,##0").format(${result.goodsVO.price})' /><spring:message code="cart.won" />&nbsp;</td>
									<td align="center" class="listtd"><c:out value="${result.qy}"/>&nbsp;</td>
									
									<c:if test="${(result.goodsVO.price * result.qy) <= 999999999}">
										<td align="center" class="listtd"><spring:eval expression='new java.text.DecimalFormat("###,##0").format(${result.goodsVO.price * result.qy})' />&nbsp;<spring:message code="cart.won"/></td>
									</c:if>
									<c:if test="${(result.goodsVO.price * result.qy) > 999999999}">
										<td align="center" class="listtd"><c:out value="${result.goodsVO.price * result.qy}"/>&nbsp;<spring:message code="cart.won"/></td>
									</c:if>
						
						
									<td align="center" class="listtd"><spring:message code="${result.dlvySe}"/></td>
								</tr>
							</c:forEach>
					</table>
				</div>
				<!-- /List -->
				<div id="paging">
					<ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="fn_egov_link_page"/>
					<form:hidden path="pageIndex" />
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
