<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/recurit/recruitlist.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/recruit/recruit_fn.js"></script>
</head>
<script>
	 function addRecruit() {
	        var form = document.createElement('form');
	        form.method = 'POST';
	        form.action = '<c:url value="/recruitadd" />';

	        document.body.appendChild(form);
	        form.submit();
	    }
	</script>
	<body>
	<jsp:include page="../../header.jsp"/>
	<!--  지원자 보기 조회버튼 추가 -->
	<form id="recruitlist" method="get">
		<input type="hidden" id="jobno" name="jobno" >
		<input type="hidden" id="csrno" name="csrno" >
	    <div class="container">
	        <section class="resume-management">
	            <h2>모집공고 관리</h2>
	            <div id="addButtonDiv" class="resume-card">
                   <div class="resume-details" onclick="addRecruit()">
                       <span class="f-s-b" id="addResumeButton" > + 새 모집공고 등록 </span>
                   </div>
               </div>
	            <c:forEach items="${list}" var="dto">
	                <div class="resume-item">
	                    <div class="resume-details">
	                        <p id="title" onclick="viewRecruitInfo('${dto.csrno}', '${dto.jobno}')">${dto.jobtitle}</p><br>
	                        <p id="subtitle">${dto.jobsubtitle}</p><br><br>
	                        <fmt:formatDate value="${dto.adate}" pattern="yyyy-MM-dd" var="dateValue"/>
	                        <fmt:formatDate value="${dto.ddate}" pattern="yyyy-MM-dd" var="dateValue2"/>
	                        <p id="adate" name="adate">공고등록일 : ${dateValue} & 마감일 : ${dateValue2}</p>
	                    </div>
	                    <div class="buttons">
						    <button class="view" type="button" onclick="viewItem('${dto.csrno}', '${dto.jobno}')">지원자 지원 내역 보기</button>
						    <div class="icon-buttons">
						        <span class="edit" onclick="ModifyItem('${dto.csrno}', '${dto.jobno}')">&#9998;</span>
						        <span class="delete" onclick="deleteItem('${dto.csrno}', '${dto.jobno}')">x</span>
						    </div>
						</div>
	                </div>
	            </c:forEach>
	        </section>
	    </div>
	</form>
	</body>
	</html>