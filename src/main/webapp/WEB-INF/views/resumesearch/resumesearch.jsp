<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/resumesearch/resumesearch.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/resumesearch_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>

<body onload="searchBtn()">
	<section id="section1">
	    <div class="button-nav">
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
				<button class="dropdown-button">보유 기술</button>
				<ul id="skill" class="dropdown-content scrollable-content">
					<c:forEach items="${showskill}" var="skill">
						<li class="item" value="${skill.skillno}">${skill.skillnm}</li>
					</c:forEach>
				</ul>
			</dl>
			<dl>
				<button class="dropdown-button">경력사항</button>
				<ul id="pcareer" class="dropdown-content scrollable-content">
					<li class="item" value="0">신입</li>
					<li class="item" value="1">경력</li>
				</ul>
			</dl>
	    </div>
		<div id="searchbox" class="search-box">
			<div id="selectedItems" class="search-list">
	        	<!-- 클릭된 항목이 추가될 위치 -->
            </div>
            <div class="button-area">
                <button class="sb-btn" onclick="resetBtn();">초기화</button>&nbsp;
                <button class="sb-btn" onclick="searchBtn();">검색</button>
            </div>
	    </div>
	    <div id="postArea" class="postArea">
	    	<!-- resumesearch_ajax.jsp 출력 영역 -->
	    </div>
    </section>
</body>
<jsp:include page="../../footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/search-box.js"></script>
</html>