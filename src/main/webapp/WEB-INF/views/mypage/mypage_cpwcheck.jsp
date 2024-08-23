<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String cuserid = (String)session.getAttribute("member");
    String usergubun = (String)session.getAttribute("usergubun");
    System.out.println("cuserid: "+cuserid);
    System.out.println("usergubun: "+usergubun);
%>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/mypage/mypage_cpwcheck.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/footer/footer.css">

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
    <div class="container">
    	<table class="table">
    		<tr>
    			<td><h2>비밀번호 확인</h2></td>
    		</tr>
    		<tr>
    			<td class="my_pwcheck">"회원님의 정보확인을 위해 비밀번호를 입력해 주세요"</td>
    		</tr>
    	</table>
        <form method="post" action="cpassword_yn">
            <div class="form-group">
                <input type="password" id="cpass" name="cpass" required placeholder="비밀번호를 입력해주세요">
                <input type="hidden" name="cuserid" value="<%= cuserid %>">
            </div>
            <button type="submit">확인</button>
        </form>
    </div>

</body>
<jsp:include page="../../footer.jsp"/>
</html>