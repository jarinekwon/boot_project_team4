<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/admin/content.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/callcenter/callcenter_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>

<body>
	<section class="board-name">
		<h2>문의 내용</h2>
	</section>
	
<div class="container">	
		<button type="button" class="listbutton" onclick="admincall()">목록보기</button>
		<div class="content_area">
		<div class="content_title_area">
			<h1>${calldetail.calltitle}</h1><br>
			ID: ${calldetail.authorid}
		</div>
		<textarea class="content_content_area" rows="15" cols="50">${calldetail.callcontent}</textarea>
	</div>
	<div class="comment-write">
		<form id="commentfrm" method="post">
		<!-- 작성자 ID 넘기기 -->
			<input type="hidden" id="callno" name="callno" value="${pageMaker.callno}">
			<input type="hidden" id="authorid" name="authorid" value="${pageMaker.authorid}">
			<input type="hidden" name="pageNum" value="${pageMaker.pageNum}">
			<input type="hidden" name="amount" value="${pageMaker.amount}">
			<input type="hidden" id="callyn" name="callyn" value="${callyn }">
			<h2>답변</h2>
			<textarea class="callreply" name="callreply" id="callreply" rows="30" cols="100">${calldetail.callreply }</textarea>
			<button type="button" onclick="admin_callreply()">등록</button>
		</form>
	</div>
	<%-- 
	<div class="comment-list" id="comment-list">
		<h4>문의</h4>
		<div class="p2">
			<p>${calldetail.callreply}</p>
		</div>
		<p><fmt:formatDate value="${calldetail.mdate }" pattern="yyyy-MM-dd HH시 mm분"/>에 작성</p>
	</div>
	--%>
</div>

</body>
<jsp:include page="../../footer.jsp"/>
</html>