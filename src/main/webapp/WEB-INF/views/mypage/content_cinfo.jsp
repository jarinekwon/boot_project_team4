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
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/mypage/content_cinfo.css">
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
<body>
<jsp:include page="../../header.jsp"/>

<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/update_cinfo">
		<div class="form-header">
		    <h2><%=session.getAttribute("username") %></h2>&nbsp;<h3>님의 마이페이지</h3>
		</div>
		<div class="user-info">
			<input type="hidden" id="userid" value="<%=userid %>">
		    <input type="hidden" id="usergubun" value="<%=usergubun %>">
			<a href="/content_cinfo">회원 정보 수정</a>
			<a href="/list?type=W&keyword=<%=userid %>&pageNum=1&pageNum=1&amount=10">내가 쓴 글 ( ${content_cinfo.boardcnt} )</a>
			<a href="/recruitlist">모집 공고 ( ${content_cinfo.jobpostcnt} )</a>
			<a href="/recruitlist">지원자 수 ( ${content_cinfo.jobaplycnt} )</a>
			<a href="/offercview">포지션 제안 ( ${content_cinfo.offercnt} )</a>
		</div>
<!--		    <span class="required-field">기업명, ID, 사업자번호는 변경할 수 없습니다.</span>-->
		<table>
            <tr>
                <td>기업명</td>
                <td class="td">${content_cinfo.cusnm}</td>
            </tr>
            <tr>
                <td>사업자번호</td>
                <td class="td">${content_cinfo.bsnum}</td>
            </tr>
            <tr>
                <td>ID</td>
				<td class="td">${content_cinfo.cuserid}</td>
                <td><input type="hidden" id="cuserid" name="cuserid" value="${content_cinfo.cuserid}"></td>
            </tr>
            <tr>
                <td>PW</td>
                <td><input type="text" id="cpass" name="cpass" value="${content_cinfo.cpass}"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="email" id="cmail" name="cmail" value="${content_cinfo.cmail}"></td>
            </tr>
            <tr>
                <td>연락처</td>
                <td><input type="text" id="phone" name="phone" value="${content_cinfo.phone}"></td>
            </tr>
            <tr>
                <td>대표자명</td>
                <td><input type="text" id="cleader" name="cleader" value="${content_cinfo.cleader}"></td>
            </tr>
            <tr>
                <td>회사주소</td>
                <td><input type="text" id="caddr" name="caddr" value="${content_cinfo.caddr}"></td>
            </tr>
        </table>
        <div class="btn_area">
        	<input type="button" class="modifybutton" value="수정" onclick="myinfo_modify_c()">
        	<input type="button" class="modifybutton" value="탈퇴" onclick="myinfo_delete_c()">
        </div>
    </form>
</div>

</body>
<jsp:include page="../../footer.jsp"/>
</html>