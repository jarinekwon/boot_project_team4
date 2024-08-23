<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/jobpost/jobpost.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jobpost/dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jobpost/jobpost_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<jsp:include page="../../header.jsp"/>
<body onload="searchBtn()">
	<section id="section1">
	    <div class="button-nav">
	    	<%-- 
			<dl>
				<button class="dropdown-button">직무</button>
				<ul id="job" class="dropdown-content scrollable-content">
					<li class="item" value="1">직무 1</li>
					<li class="item" value="2">직무 2</li>
					<li class="item" value="3">직무 3</li>
					<li class="item" value="4">직무 4</li>
					<li class="item" value="5">직무 5</li>			
				</ul>
			</dl>
			--%>
			<dl>
				<button class="dropdown-button">근무지역</button>
				<ul id="loc" class="dropdown-content scrollable-content">
					<li class="item" value="서울">서울</li>
					<li class="item" value="경기">경기</li>
					<li class="item" value="인천">인천</li>
					<li class="item" value="대전">대전</li>
					<li class="item" value="세종">세종</li>
					<li class="item" value="충남">충남</li>
					<li class="item" value="충북">충북</li>
					<li class="item" value="광주">광주</li>
					<li class="item" value="전남">전남</li>
					<li class="item" value="전북">전북</li>
					<li class="item" value="대구">대구</li>
					<li class="item" value="경북">경북</li>
					<li class="item" value="부산">부산</li>
					<li class="item" value="울산">울산</li>
					<li class="item" value="경남">경남</li>
					<li class="item" value="강원">강원</li>
					<li class="item" value="제주">제주</li>
				</ul>
			</dl>
			<dl>
				<button class="dropdown-button">경력</button>
				<ul id="career" class="dropdown-content scrollable-content">
					<c:forEach items="${career }" var="career">
						<li class="item" value="${career.careerno }">${career.careernm }</li>	
					</c:forEach>
				</ul>
			</dl>
			<dl>
				<button class="dropdown-button">학력</button>
				<ul id="edu" class="dropdown-content scrollable-content">
					<c:forEach items="${edu }" var="edu">
						<li class="item" value="${edu.eduno }">${edu.edunm }</li>
					</c:forEach>
				</ul>
			</dl>
			<dl>
				<button class="dropdown-button">고용형태</button>
				<ul id="wrkty" class="dropdown-content scrollable-content">
					<c:forEach items="${wrkty }" var="wrkty">
						<li class="item" value="${wrkty.wrktyno }">${wrkty.wrktynm }</li>
					</c:forEach>
				</ul>
			</dl>
	    </div>
		<div id="searchbox" class="search-box">
	            <div id="selectedItems" class="search-list">
	                <!-- 클릭된 항목이 추가될 위치 -->
	            </div>
	            <div class="button-area"> 
	                <button class="sb-btn" onclick="resetBtn();">초기화</button>
	                <button class="sb-btn" onclick="searchBtn();">검색</button>
	            </div>
	    </div>
	    <div id="postArea" class="postArea">
	    	<!-- jobpost_ajax.jsp 출력 영역 -->
	    </div>
    </section>
</body>
<jsp:include page="../../footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jobpost/search-box.js"></script>
</html>