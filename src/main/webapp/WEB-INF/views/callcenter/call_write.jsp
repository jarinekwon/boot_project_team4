<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/callcenter/write_view.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/callcenter/callcenter_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
	<div class="container">
	<h2>문의 글 작성</h2>
	<div class="button-container">
		<button class="listbutton" onclick="move_calllist()">목록보기</button>
    </div>
	<table>
		<form id="frm" name="callfrm" method="post" action=callwriteadd>
			<tr>
				<td class="table_td">제목</td>
				<td>
					<input type="text" class="outlinenone" name="calltitle" size="50" required="required">
				</td>
			</tr>
			<tr>
				<td class="table_td">내용</td>
				<td>
					<textarea rows="10" class="outlinenone" name="callcontent" maxlength="490"></textarea>
				</td>
			</tr>
		</form>
	</table>
		<div class="button-container-bottom">
	        <button type="button" class="submitbutton" onclick="call_add()">작성완료</button>
	    </div>
	</div>
</body>
</html>