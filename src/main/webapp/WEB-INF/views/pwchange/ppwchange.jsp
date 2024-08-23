<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hello World</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/pwchange.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login_fn.js"></script>
<script>
$(document).ready(function() {
    var message = "${message}";
    if (message) {
        alert(message);
        if (message === "Password updated successfully.") {
            window.location.href = "${pageContext.request.contextPath}/login";
		}
    }
}
</script>
<script>   
	//일반 비밀번호 재확인 
function ppw_chk() {
    var ppass = document.getElementById("ppass").value;
    var pconfirmNewPw = document.getElementById("pconfirmNewPw").value;
    if (ppass !== pconfirmNewPw) {
        alert("비밀번호가 같지 않습니다.");
        return false;
    } else {
        alert("비밀번호가 수정되었습니다.");
        return true;
    }
}
</script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
<div class="container" >
    <div class="header-container">
        <div class="pwckhead">
            일반회원 비밀번호 수정
		</div>
	</div>
	  <form action="${pageContext.request.contextPath}/ppwfind_yn" id="generalForm" method="post" class="visible" onsubmit="return ppw_chk()">
		
		  <label for="puserid">사용자 ID</label>
	      <input type="text" id="puserid" name="puserid" placeholder="아이디를 입력해주세요" required><br>
		  
		  <label for="ppass">비밀번호</label>
	      <input type="password" id="ppass" name="ppass" placeholder="새로운 비밀번호를 입력해주세요" required>
		  
	      <label for="pconfirmNewPw">비밀번호 확인 </label>
	      <input type="password" id="pconfirmNewPw" name="pconfirmNewPw" placeholder="비밀번호를 한번 더 입력해주세요" required>
		  
	      <br>
	      <button type="submit" class="submit-btn">비밀번호 수정</button>
	  </form>
</div>
</body>
<jsp:include page="../../footer.jsp"/>
</html>