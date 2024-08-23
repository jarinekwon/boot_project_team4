<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.nio.charset.StandardCharsets"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/offer/offer.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/offer/offer_fn.js"></script>
    <title>포지션 제안</title>
</head>
<body>
	<div class="container">
	    <h1>포지션 제안</h1>
	    <form id="offerForm" name="offerForm" method="post" action="/offeryn">
	        <input type="hidden" id="offerno" name="offerno" value="${data.offerno}">
	        <input type="hidden" id="puserid" name="puserid" value="${data.puserid}">
	        <input type="hidden" id="cuserid" name="cuserid" value="${data.cuserid}">
	        <table>
		        <tr class="first">
					<td class="table_td">제목</td>
					<td>
						<input type="text" id="title" name="title" value="${data.title }" readonly="readonly">
					</td>
				</tr>
				<tr class="second">
					<td class="table_td" style="vertical-align: top; padding-top: 15px;">내용</td>
					<td>
						<textarea rows="10" id="content" name="content" readonly="readonly">${data.content }</textarea>
					</td>
				</tr>
				<tr class="second">
					<td class="table_td">마감일자</td>
					<td>
						<fmt:formatDate value="${data.ddate }" var="dateValue" pattern="yyyy-MM-dd"/>
						${dateValue}
					</td>
				</tr>
				<c:if test="${data.gubun == 'Y' && gubun == 'C'}">
					<tr class="second">
						<td class="table_td">이름</td>
						<td>
							${data.pname }
						</td>
					</tr>
					<tr class="second">
						<td class="table_td">전화번호</td>
						<td>
							${data.phone }
						</td>
					</tr>
				</c:if>
			</table>
			<c:if test="${data.gubun == 'D' && data.cha >= 0 && gubun == 'P'}">
		        <div class="apply-button">
		        	<button type="button" class="apply" onclick="offer_yn('Y')">승낙하기</button>
		            <button type="button" class="apply" onclick="offer_yn('N')">거절하기</button>
		        </div>
	        </c:if>
	        <c:if test="${gubun == 'C' || data.cha < 0}">
	        	<div class="apply-button">
	        		<button type="button" class="apply" onclick="window.close()">닫기</button>
	        	</div>
	        </c:if>
	    </form>
	</div>
</body>
</html>
