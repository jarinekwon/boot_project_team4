<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hello World</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login_fn.js"></script>
<script>
	$(document).ready(function() {
	    var message = "${message}";
	    if (message) {
	        alert(message);
	    }
	});
</script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
<div class="container" >
	<div class="btn-group">
	    <button type="button" id="generalBtn" class="form-button active" data-form="generalForm" onclick="geloform()">일반회원 로그인</button>
		<button type="button" id="businessBtn" class="form-button" data-form="businessForm" onclick="buloform()">기업회원 로그인</button>
	</div>
    <form method="post" id="generalForm" class="visible" action="plogin_yn" >
         일반회원 아이디<input type="text" name="puserid" placeholder="아이디를 입력해주세요" required><br>
         비밀번호<input type="password" name="ppass" placeholder="비밀번호를 입력해주세요" required><br>
        <button type="submit" class="submit-btn">로그인</button>
    <div class="find">
	    <a href="/idfind" class="btn btn-google btn-user btn-block">아이디 찾기</a>&nbsp;&nbsp;&nbsp;
	    <a href="/pwfind" class="btn btn-google btn-user btn-block">비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;
	    <a href="/register" class="btn btn-google btn-user btn-block">회원가입</a> 
    </div>
    </form>
    <form method="post" id="businessForm" action="clogin_yn">
         기업회원 아이디<input type="text" name="cuserid" placeholder="아이디를 입력해주세요" required><br>
         비밀번호<input type="password" name="cpass" placeholder="비밀번호를 입력해주세요" required><br>
        <button type="submit" class="submit-btn">로그인</button>
    <div class="find">
	    <a href="/idfind" class="btn btn-google btn-user btn-block">아이디 찾기</a>&nbsp;&nbsp;&nbsp;
	    <a href="/pwfind" class="btn btn-google btn-user btn-block">비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;  
	    <a href="/register" class="btn btn-google btn-user btn-block">회원가입</a>     
    </div> 
    </form>
</div>
</body>
<jsp:include page="../../footer.jsp"/>
</html>