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
</script>
<script>   
	//기업 비밀번호 재확인 
function cpw_chk() {
    var cpass = document.getElementById("cpass").value;
    var cconfirmNewPw = document.getElementById("cconfirmNewPw").value;
    if (cpass !== cconfirmNewPw) {
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
    <div class="pwckhead">
		기업회원 비밀번호 변경
	</div>
	<form action="${pageContext.request.contextPath}/cpwfind_yn" id="businessForm" method="post" class="visible" onsubmit="return cpw_chk()">
		<label for="cuserid">사용자 ID</label>
		<input type="text" id="cuserid" name="cuserid" placeholder="아이디를 입력해주세요" required><br>
		<label for="cpass">비밀번호</label>
		<input type="password" id="cpass" name="cpass" placeholder="새로운 비밀호를 입력해주세요" required>
		<label for="cconfirmNewPw">비밀번호 확인 </label>
		<input type="password" id="cconfirmNewPw" name="cconfirmNewPw" placeholder="비밀번호를 한번 더 입력해주세요" required><br>
		<button type="submit" class="submit-btn">비밀번호 수정</button>
	</form>
</div>
	  
</body>
<jsp:include page="../../footer.jsp"/>
</html>