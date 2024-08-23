<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/footer/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/mypage/content_pinfo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage/mypage_fn.js"></script>
<%	
	String userid = "", usergubun = "";
	try{
		userid = (String)session.getAttribute("id");
		usergubun = (String)session.getAttribute("usergubun");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</head>
<jsp:include page="../../header.jsp"/>

<body>

<div class="container">
    <form method="post" action="update_pinfo">
		<div class="form-header">
		    <h2><%=session.getAttribute("username") %></h2>&nbsp;<h3>님의 마이페이지</h3>
		</div>
		<div class="user-info">
			<input type="hidden" id="userid" value="<%=userid %>">
		    <input type="hidden" id="usergubun" value="<%=usergubun %>">
			<a href="/content_pinfo">회원 정보 수정</a>
			<a href="/list?type=W&keyword=<%=userid %>&pageNum=1&pageNum=1&amount=10&mypost=my">작성한 글 ( ${content_pinfo.boardcnt} )</a>
			<a href="/jobaplylist_p">지원 완료 ( ${content_pinfo.jobaplylistcnt} )</a>
			<a href="/rview">이력서 열람 기업 ( ${content_pinfo.opencnt } )</a>
			<a href="/offerview">포지션 제안 ( ${content_pinfo.offercnt} )</a>
			<a href="/callcenter">문의한 글 ( ${content_pinfo.callcnt} )</a>
		</div>
<!--		    <span class="required-field">이름과 ID는 변경할 수 없습니다.</span>-->
        <table>
            <tr>
                <td>이 름</td>
                <td>&nbsp;${content_pinfo.pname}</td>
            </tr>
            <tr>
                <td>ID </td>
                <td>&nbsp;${content_pinfo.puserid}</td>
                <input type="hidden" id="puserid" name="puserid" value="${content_pinfo.puserid}">
            </tr>
            <tr>
                <td>P W</td>
                <td><input type="text" id="ppass" name="ppass" value="${content_pinfo.ppass}"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="email" id="email" name="email" value="${content_pinfo.email}"></td>
            </tr>
            <tr>
                <td>연락처</td>
                <td><input type="text" id="phone" name="phone" value="${content_pinfo.phone}"></td>
            </tr>
        </table>
        <div class="btn_area">
           <input type="button" class="modifybutton" value="수정" onclick="myinfo_modify_p()">
           <input type="button" class="modifybutton" value="탈퇴" onclick="myinfo_delete_p()">
		</div>
    </form>
</div>

</body>
<jsp:include page="../../footer.jsp"/>
</html>