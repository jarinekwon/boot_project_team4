<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/resume/resumetb_view.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/resume/resume_fn.js"></script>
</head>
<body>
<div class="container" >
    <button class="back-button" onclick="window.history.back()">돌아가기</button>
    <button class="offer-button" onclick="openOfferPopup('${resumeselect.puserid}','${resumeselect.prono }')">포지션 제안</button>
    <h2>제목: ${resumeselect.protitle}</h2>
    <h3>1. 인적사항</h3>
    <table>
        <tr>
			<td class="photo-cell" rowspan='5'>
				<img src="show_resume_img?writer=${resumeselect.puserid}&prono=${resumeselect.prono}&imgno=${resumeselect.imgno}&imggubun=b">
			</td>
            <th>지원구분</th>
            <td>${resumeselect.prsup == 0?'신입':'경력'}</td>
            <th>성별</th>
            <td>${resumeselect.gender == 1?'남성':'여성'}</td>
        </tr>
        <tr>
            <th>성명</th>
            <td>${resumeselect.pname }</td>
            <th>생년월일</th>
            <td>${resumeselect.birdy}</td>
        </tr>
        <tr>
            <th>현주소</th>
            <td colspan="3">${resumeselect.paddr}</td>
        </tr>
        <tr>
            <th>연락처</th>
            <td>${resumeselect.phone}</td>
            <th>이메일</th>
            <td>${resumeselect.email}</td>
        </tr>
        <tr>
            <th>병역사항</th>
            <td>${resumeselect.armgu == 1?'필':resumeselect.armgu == 2?'미필':'면제'}</td>
            <th>희망연봉</th>
            <td>${resumeselect.salary}
                <a class="right-align">만원</a>
            </td>
        </tr>
        <tr>
            <th>학력사항</th>
            <td colspan="2">${resumeselect.classgb == 1?'고등학교':resumeselect.classgb == 2?'대학교(2,3년)':resumeselect.classgb == 3?'대학교(4년)':'대학원'} (${resumeselect.gradesta == 1?'휴학':resumeselect.gradesta == 2?'중퇴':'졸업'})</td>
            <th>직무</th>
            <td>${resumeselect.job}</td>
        </tr>
        <tr>
            <th>보유기술</th>
            <td colspan="4">
            	<c:forEach items="${skill_list }" var="skill">
            		${skill.skillname}&nbsp;
            	</c:forEach>
            </td>
        </tr>
        <tr>
            <th>포트폴리오</th>
            <td colspan="4">${resumeselect.propo}</td>
        </tr>
    </table>
    <h3>2.경력</h3>
    <table class="experience">
        <c:forEach var="career" items="${rcareerList}">
            <tr>
                <th>회사명</th>
                <td class="photo-cell">${career.corpnm}</td>
                <th>직급</th>
                <td>${career.position}</td>
                <th>근무형태</th>
                <td>${career.wrkty == 1 ? '정규직' : career.wrkty == 2 ? '계약직' : '기간제'}</td>
            </tr>
            <tr>
                <th>입사년도</th>
                <td colspan="2"><fmt:formatDate value="${career.sdate}" pattern="yyyy-MM-dd"/></td>
                <th>퇴사년도</th>
                <td colspan="2"><fmt:formatDate value="${career.edate}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <th>주요 업무</th>
                <td colspan="5" style="text-align: left">${career.task}</td>
            </tr>
        </c:forEach>
    </table>
    <h3>3. 자기소개</h3>
    <textarea rows="30" cols="140" readonly="readonly">${resumeselect.proself}</textarea>
</div>
</body>
</html>