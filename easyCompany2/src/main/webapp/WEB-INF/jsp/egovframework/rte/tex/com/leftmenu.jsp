<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 메뉴 시작 -->
<div id="LoginStatus">
	<fieldset><legend></legend>
		<ul>
			<c:if test="${not empty loginVO}">
				<li><c:out value="${loginVO.name}" /><spring:message code="login.msg.nim" /> <spring:message code="login.msg.welcom" /></li>
				<li><span class="btn_blue" title="<spring:message code="login.msg.mypage" />"><a href="<c:url value='/mbr/updateMemberView.do'/>"><spring:message code="login.msg.mypage" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="login.msg.mypage" />"/></span>
				<span class="btn_blue" title="<spring:message code="login.msg.logout" />"><a href="<c:url value='/j_spring_security_logout'/>"><spring:message code="login.msg.logout" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="login.msg.logout" />"/></span></li>
			</c:if>
			<c:if test="${empty loginVO}">
				<li><spring:message code="login.msg.login" /></li>
				<li><span class="btn_blue" title="<spring:message code="mbr.login" />"><a href="<c:url value='/mbr/loginView.do'/>"><spring:message code="mbr.login" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="mbr.login" />"/></span>
				<span class="btn_blue" title="<spring:message code="mbr.register" />"><a href="<c:url value='/mbr/insertMemberView.do'/>"><spring:message code="mbr.register" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;" alt="<spring:message code="mbr.register" />"/></span></li>
			</c:if>
		</ul>
	</fieldset>
</div>
<div id="nav">
	<div class="navtop"></div>
	<div class="nav_style">
		<ul>
			<li class="leftmenu_dept01"><spring:message code="menu.user" />
				<ul>
					<li class="dept02"><a href="<c:url value='/gds/selectListGoods.do'/>" ><spring:message code="menu.goods" /></a></li>
					<li class="dept02"><a href="<c:url value='/pcs/selectListCart.do'/>" ><spring:message code="menu.cart" /></a></li>
					<li class="dept02"><a href="<c:url value='/dlv/selectListPurchase.do'/>" ><spring:message code="menu.purchs" /></a></li>
					<li class="dept02"><a href="<c:url value='/brd/egovBoardList.do'/>" ><spring:message code="menu.board" /></a></li>
				</ul>
			</li>
			<c:if test="${loginVO.mngrSe == 'ROLE_ADMIN' || loginVO.mngrSe == 'CODE01'}">
			<li class="leftmenu_dept01"><spring:message code="menu.admin" />
				<ul>
					<li class="dept02"><a href="<c:url value='/springrest/cgr.html'/>" ><spring:message code="menu.category" /></a></li>
					<li class="dept02"><a href="<c:url value='/gds/insertGoodsView.do'/>" ><spring:message code="menu.addGoods" /></a></li>
					<li class="dept02"><a href="<c:url value='/dlv/selectAllListPurchase.do'/>" ><spring:message code="menu.allPurchs" /></a></li>
				</ul>
			</li>
			</c:if>
		</ul>
	</div>
	<div class="bottom"></div>
</div>
