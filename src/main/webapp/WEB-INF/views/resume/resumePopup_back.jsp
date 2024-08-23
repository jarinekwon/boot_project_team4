<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.nio.charset.StandardCharsets"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/resume/resumepopup.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/resume/resume_fn.js"></script>
    <title>이력서 선택</title>
</head>
<body>
	<div class="container">
	    <h1>이력서 선택</h1>
	    <form id="resumeForm" name="resumeForm" method="post" action="jobaplysupport">
	    	<input type="hidden" name="writer" value="${writer}">
	    	<input type="hidden" name="csrno" value="${csrno}">
	    	<input type="hidden" name="jobno" value="${jobno}">
	    	<input type="hidden" id="prono" name="prono">
	        <select id="resumelist" name="resume">
	            <option value="">이력서를 선택하세요.</option>
	            <c:forEach var="resume" items="${resumelist}">
	                <option value="${resume.prono}">${resume.protitle}</option>
	            </c:forEach>
	        </select>
	        <div class="apply-button">
	            <button type="button" class="apply" onclick="applyResume();">지원하기</button>
	            <%-- <button type="button" class="cancel" onclick="closePopup();">닫기</button> --%>
	        </div>
	    </form>
	</div>
	<script>
		function applyResume() {
	        var selectedResumeId = document.getElementById('resumelist').value;

	        if (selectedResumeId) {
	            // 이력서 ID를 hidden input으로 추가
				$("#prono").val(selectedResumeId);	
	            // 폼 제출
	            resumeForm.submit();
	            // 팝업 창 닫기
	            //window.close();
	            // 지원 완료 메시지 출력
	            alert('지원 완료!');
	        } else {
	            alert('이력서를 선택하세요.');
	        }
	    }

		function closePopup() {
		    window.close();
		}
    </script>
</body>
</html>
