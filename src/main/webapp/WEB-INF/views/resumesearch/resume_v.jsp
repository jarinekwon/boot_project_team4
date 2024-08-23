<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/resume/resumetb_view.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/resumesearch_fn.js"></script>
</head>
<body>
<div class="container" >
    <%-- <h1>이력서 ${list.prono}</h1> --%>
    <h2>제목: ${list.protitle}</h2>
    <h3>1. 인적사항</h3>
    <table>
        <tr>
			<td class="photo-cell" rowspan='5'>
				<img src="show_resume_img?writer=${list.puserid}&prono=${list.prono}&imgno=${list.imgno}&imggubun=b">
			</td>
            <th>지원구분</th>
            <td>${list.prsup == 0?'신입':'경력'}</td>
            <th>성별</th>
            <td>${list.gender == 1?'남성':'여성'}</td>
        </tr>
        <tr>
            <th>성명</th>
            <td>${list.pname }</td>
            <th>생년월일</th>
            <td>${list.birdy}</td>
        </tr>
        <tr>
            <th>현주소</th>
            <td colspan="3">${list.paddr}</td>
        </tr>
        <tr>
            <th>연락처</th>
			<td>
                ${list.phone}
			</td>
			<th>이메일</th>
            <td>
                ${list.email}
            </td>
        </tr>
        <tr>
            <th>병역사항</th>
            <td>${list.armgu == 1?'필':list.armgu == 2?'미필':'면제'}</td>
            <th>희망연봉</th>
            <td>${list.salary}
                <a class="right-align">만원</a>
            </td>
        </tr>
        <tr>
            <th>학력사항</th>
            <td colspan="4">${list.classgb == 1?'고등학교':list.classgb == 2?'대학교(2,3년)':'대학교(4년)'} (${list.gradesta == 1?'휴학':list.gradesta == 2?'중퇴':'졸업'})</td>
        </tr>
        <tr>
            <th>보유기술</th>
            <td colspan="4">
            		${list.skillnms}&nbsp;
            </td>
        </tr>
        <tr>
            <th>포트폴리오</th>
            <td colspan="4">${list.propo}</td>
        </tr>
    </table>
    <h3>2. 자기소개</h3>
    <textarea rows="30" cols="140" readonly="readonly">${list.proself}</textarea>
    <a href="resumesearch"><button class="back-button">돌아가기</button></a>
</div>
</body>
</html>