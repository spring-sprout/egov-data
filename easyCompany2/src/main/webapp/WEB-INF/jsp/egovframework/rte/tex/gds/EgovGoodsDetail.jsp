<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @JSP Name : EgovGoodsDetail.jsp
  * @Description : 품목 상세화면
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
<title>egov | <spring:message code="goods.title"/></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/egov.css'/>">

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="goodsVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

/* 상품 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/gds/selectListGoods.do'/>";
   	document.detailForm.submit();		
}

/* 상품 삭제 function */
function fn_egov_delete() {
	if(!confirm("<spring:message code="msg.del" />")) {
		return;
	}
   	document.detailForm.action = "<c:url value='/gds/deleteGoods.do'/>";
   	document.detailForm.submit();		
}

/* 상품 수정 화면 function */
function fn_egov_update() {	
   	document.detailForm.action = "<c:url value='/gds/updateGoodsView.do' />";
   	document.detailForm.submit();
}

/* 장바구니 담기 function */
function fn_egov_insertCart() {
	var qy = document.detailForm.qy.value;
	if(qy<=0){
		alert("<spring:message code="errors.qy" />");
		return;
	}
	if(!confirm("<spring:message code="msg.cart" />")) {
		return;
	}
   	document.detailForm.action = "<c:url value='/pcs/insertCart.do' />";
   	document.detailForm.submit();
}

/* 바로 구매 function */
function fn_egov_insertPurchase() {
	var qy = document.detailForm.qy.value;
	if(qy<=0){
		alert("<spring:message code="errors.qy" />");
		return;
	}
	if(!confirm("<spring:message code="msg.purchase" />")) {
		return;
	}
   	document.detailForm.action = "<c:url value='/pcs/Purchase.do' />";
   	document.detailForm.submit();
}


function intCheck(f) {
		var pattern = /(^[0-9]+$)/;
		if(!pattern.test(f.value)){
			alert("<spring:message code="errors.integer" />");
			f.value = '';
			f.focus();
			return false;
		}
		return true;
}


function requestSum(hiddenPrice) {
	var qy = document.detailForm.qy;
	if(intCheck(qy)){
		document.getElementById("message").innerHTML = addComma(hiddenPrice * qy.value) + "<spring:message code="cart.won" />";//보여주기
	}else{
		document.getElementById("message").innerHTML = "";
	}
}

function addComma(n){
	if(isNaN(n)){
		return 0;
	}
	var reg=/(^[+-]?\d+)(\d{3})/;
	n +='';
	while(reg.test(n)){
		n = n.replace(reg, '$1' + ',' + '$2');
	}
	return n;
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
<form:form commandName="goodsVO" name="detailForm">
<!-- 선택된 goodsId 유지 -->
<input type="hidden" name="selectedId" value="<c:out value='${goodsVO.goodsId}'/>"/>

	<!-- 타이틀 -->
	<div id="title2">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt=""/> <spring:message code="goods.title" /> </li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table border='0' width='100%' summary="품목의 상세 정보를 확인합니다.">
		<caption>품목정보</caption>
		<colgroup>
						<col width="150" >
						<col width="" >
		</colgroup>
		<tr>
		<td colspan='2'> <c:out value="${goodsVO.categoryVO.ctgryNm}"/></td>
		</tr>
		<tr>
			<td colspan='2' >
				<div id="title3" style="border-bottom:0px">
					<ul>
						<li>&nbsp;<c:out value="${goodsVO.goodsNm}"/></li>
					</ul>
				</div>
			</td>
		</tr> 
		<tr>
			<td rowspan='2'><img src="<c:url value='/GoodsImage/${goodsVO.goodsImageVO.goodsImageId}'/>" style="margin-left:6px;margin-right:40px;" height="200" width="200" alt="Item Image"/>
			</td>
			<td>
				<table width="100%" border="1" cellpadding="0" cellspacing="0"  style="BORDER-TOP: #C2D0DB 2px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-RIGHT: #ffffff 1px solid; BORDER-BOTTOM: #C2D0DB 1px solid; border-collapse: collapse;" summary="품목의 상세정보를 확인 할 수 있습니다.">
					<caption>품목 상세 정보</caption>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<td class="tbtd_caption"><spring:message code="goods.price"/> </td>
						<td class="tbtd_content">
							<spring:eval expression='new java.text.DecimalFormat("###,##0").format(goodsVO.price)' /><spring:message code="cart.won" />
						&nbsp;<form:errors path="price" />
						</td>
					</tr>
					<tr>
						<td class="tbtd_caption"><spring:message code="goods.makr"/></td>
						<td class="tbtd_content">
							<c:out value="${goodsVO.makr}"/> &nbsp;<form:errors path="makr" />
						</td>
					</tr>
					<c:if test="${goodsVO.useAt == '1'}">
						<tr>
							<td class="tbtd_caption"><spring:message code="goods.qy"/></td>
							<td class="tbtd_content"><input type="text" name="qy" value="0"	maxlength="3" onkeyup="javascript:requestSum('${goodsVO.price}')" 	onchange="javascript:requestSum('${goodsVO.price}')" title="<spring:message code="goods.qy"/>"/></td>
						</tr>
						<tr>
							<td class="tbtd_caption"><spring:message code="goods.sum"/></td>
							<td class="tbtd_content"><div id="message"></div></td>
						</tr>
					</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			<div class="sysbtn">
				<ul>
				<c:if test="${goodsVO.useAt == '1' && loginVO != null}">
					<li><span class="btn_blue_l" title="<spring:message code="goods.btn.insertCart"/>"><a href="#link" onclick="fn_egov_insertCart(); return false;"><spring:message code="goods.btn.insertCart"/></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="goods.btn.insertCart"/>"/></span></li>
					<li><span class="btn_blue_l" title="<spring:message code="goods.btn.buyNow"/>"><a href="#link" onclick="fn_egov_insertPurchase(); return false;"><spring:message code="goods.btn.buyNow"/></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="goods.btn.buyNow"/>"/></span></li>
				</c:if>
				<c:if test="${goodsVO.useAt == '0'}">
					<li><spring:message code="goods.deleteGoods"/></li>
				</c:if>
					<li><span class="btn_blue_l" title="<spring:message code="goods.btn.list"/>"><a href="#link" onclick="fn_egov_selectList(); return false;"><spring:message code="goods.btn.list"/></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="goods.btn.list"/>" /></span></li>
				</ul>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan='2'> &nbsp;</td>
		</tr>
		
	</table>
	<table border="0" width="100%" summary="품목의 상세 이미지를 확인 할 수 있습니다.">
		<caption>품목 상세 이미지</caption>
		<tr>
			<th style="text-align: left" colspan='2' height="40">&nbsp;&nbsp;&nbsp;<spring:message code="goods.detail"/></th>
		</tr>
		<tr>
			<td colspan='2' class="tbtd_content">
				<img src="<c:url value='/GoodsImage/${goodsVO.detailImageVO.goodsImageId}'/>" width='100%' alt="<spring:message code="goods.detail"/>"/>
			</td>
		</tr>
	</table>
	</div>
	
	<c:if test="${loginVO.mngrSe == 'ROLE_ADMIN' && goodsVO.useAt == '1'}">
		<div class="sysbtn" style="margin-top:10px">
			<ul>
				<li><span class="btn_blue_l" title="<spring:message code="goods.btn.update"/>"><a href="#link" onclick="fn_egov_update(); return false;"><spring:message code="goods.btn.update"/></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="goods.btn.update"/>"/></span></li>
				<li><span class="btn_blue_l" title="<spring:message code="goods.btn.delete"/>"><a href="#link" onclick="fn_egov_delete(); return false;"><spring:message code="goods.btn.delete"/></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="goods.btn.delete"/>"/></span></li>
				<li><input type="submit" style="display:none" title="submit"/></li>
			</ul>
		</div>
	</c:if>
	
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>" />
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" />
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

