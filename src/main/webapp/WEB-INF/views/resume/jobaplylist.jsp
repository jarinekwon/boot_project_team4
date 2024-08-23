<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>지원 내역</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/jobaply/jobaply.css">
    <script src="${pageContext.request.contextPath}/js/jobaply/jobaplylist.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="container">
    	<div class="back-button-wrap">
			<button class="back-button" onclick="window.history.back()">뒤로가기</button>
		</div>
        <h2>지원 내역</h2>
        <p> ${jobaply.jobtitle}에 지원한 지원자 내역입니다 (지원자 수: ${count })</p>
        <form name="jobaplyfrm" method="get" class="applicant" action="resumetb_view">
        	<input type="hidden" id="writer" name="writer">
            <input type="hidden" id="prono" name="prono">
            <input type="hidden" name="jobno" value="${jobaply.jobno }">
            <input type="hidden" name="csrno" value="${jobaply.csrno }">
	        <c:forEach items="${jobaplylist}" var="dto">
	            <div class="applicant-list">
                    <div id="jobaplylist" class="jobaplylist">
                    	<div class="imgarea">
							<img src="/show_resume_img?writer=${dto.puserid}&prono=${dto.prono}&imgno=${dto.imgno}&imggubun=s">
						</div>
                        <h2>${dto.protitle}</h2>
                        <div class="button">
    	                    <button type="button" class="view-button1" onclick="resume_view('${dto.puserid }','${dto.prono}')">보기</button> 
	                    </div>
                    </div>
	            </div>
        	</c:forEach>
        </form>
    </div>
</body>
</html>