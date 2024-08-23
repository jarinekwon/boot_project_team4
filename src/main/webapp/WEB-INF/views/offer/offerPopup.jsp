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
	    <form id="offerForm" name="offerForm" method="post" action="/offersave">
	        <input type="hidden" id="puserid" name="puserid" value="${data.puserid}">
	        <table>
		        <tr class="first">
					<td class="table_td">제목</td>
					<td>
						<input type="text" id="title" name="title" size="50">
					</td>
				</tr>
				<tr class="second">
					<td class="table_td">내용</td>
					<td>
						<textarea rows="10" id="content" name="content"></textarea>
					</td>
				</tr>
				<tr class="second">
					<td class="table_td">마감일자</td>
					<td>
						<fmt:formatDate value="${date }" var="dateValue" pattern="yyyy-MM-dd"/>
						<input type="date" class="inpDefault" id="ddate" name="ddate" value="${dateValue}"/>
					</td>
				</tr>
			</table>
	        <div class="apply-button">
	            <button type="button" class="apply" onclick="offersave()">제안하기</button>
	        </div>
	    </form>
	</div>
</body>
</html>
