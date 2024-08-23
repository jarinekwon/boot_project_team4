<%@page import="ch.qos.logback.core.recovery.ResilientSyslogOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<%	
	String userid = "", usergubun = "";
	try{
		userid = (String)session.getAttribute("id");
		usergubun = (String)session.getAttribute("usergubun");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	function logout(){
		location.href = "/logout";
	}
</script>
<div class="navbar">
    <div class="navbar-left">
            <a href="/main">
				<img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo">
			</a>
    </div>
	<%-- 일반 회원 로그인 --%>
	<div class="navbar-right">
	    <input type="hidden" id="userid" value="<%=userid %>">
	    <input type="hidden" id="usergubun" value="<%=usergubun %>">
	    <%if(usergubun != null && usergubun.equals("p")) { %>	
	        <div class="navbar-left">
	        	<a href="/jobpost">구직공고</a>
	            <a href="/list">취업 게시판</a>
	            <a href="/mypage_ppwcheck">마이페이지</a>
				<div class="menu-dropdown">
				<%-- 
					<div class="sessionTimeout" id="sessionTimeout">
						<font size="2">접속 유지 시간:</font>
					</div>
				--%>
					<%=session.getAttribute("username") %>  님<span class="arrow-down">▼</span>
				    <div class="content-dropdown">
						<a href="/resumelist">내 이력서 관리</a>
						<a href="/jobaplylist_p">나의 지원 현황</a>
						<a href="/scribe">관심기업 현황</a>
						<a href="/pscrap">스크랩 현황</a>
						<a href="/callcenter">고객 센터</a>
						
					</div>
				</div>
			</div>
	        <button onclick="logout()">로그아웃</button>
	    <%} else if(usergubun != null && usergubun.equals("c")) { %>
	    	<div class="navbar-left">
	        	<a href="/jobpost">구직공고</a>
	        	<a href="/resumesearch">인재검색</a>
	            <a href="/list">취업 게시판</a>
	            <a href="/mypage_cpwcheck">마이페이지</a>
				<div class="menu-dropdown">
					<%=session.getAttribute("username") %>  님<span class="arrow-down">▼</span>
					<div class="content-dropdown">
						<a href="/recruitlist">모집 공고 관리</a>
						<a href="/coinfoshow">기업 정보</a>
					</div>
				</div>
	        </div>
	        <button onclick="logout()">로그아웃</button>
	    <%} else if(usergubun != null && usergubun.equals("m")) { %>
	    	<div class="navbar-left">
	        	<a href="/admincall">고객 센터</a>
	            <a href="/list">취업 게시판</a>
				<div class="menu-dropdown">
					<%=session.getAttribute("username") %>  님
				</div>
	        </div>
	        <button onclick="logout()">로그아웃</button>
	    <%} else { %>
	    	<div class="navbar-left">
	        	<a href="/jobpost">구직공고</a>
	            <a href="/list">취업 게시판</a>
	            <a href="/login">로그인</a>
	            <a href="/register">회원가입</a>
	        </div>
	    <%} %>
    </div>
</div>

<script>
	var getid = '<%=session.getAttribute("id") %>';
	var time = '<%=session.getMaxInactiveInterval()%>';
	var gubun = '<%=session.getAttribute("usergubun") %>'
	console.log("getid: "+getid);
	console.log("usergubun: "+gubun);
	console.log("유지 시간: "+time);
	if(getid) {
		var settime = 0;
		console.log("id 값 있음");
		//한번 호출은 호출 해야 함
		//updateSessionTimeout();
	}
	
	function updateSessionTimeout() {
		settime += 1;
		$.ajax({
			url: "/getSessionTimeout",
			data: {settime: settime},
			success: function(seconds) {
				var displayTime = Number(seconds)
				console.log('displayTime: '+displayTime);
				if(displayTime < 1) {
					location.href = "/logout";
				}
				var mm = Math.floor(displayTime / 60);
				var ss = Math.floor(displayTime % 60);
				ss = ss < 10 ? "0"+ss:ss;
				
	            $('#sessionTimeout').text('접속 유지 시간: ' + mm + ':' + ss + '초');
	            // 1초마다 세션 유지 시간을 감소시키는 함수 호출
	            setTimeout(function() {
	                updateSessionTimeout();
	            }, 1000);
			}, error: function() {
				console.log("ajax 에러...");
			}
		});
	}
</script>
<script>
document.addEventListener("DOMContentLoaded", function() {
	var userid = $("#userid").val();
	var usergubun = $("#usergubun").val();
	if((userid != 'null') && (usergubun == 'p' || usergubun == 'c')) {
	    var dropdownButton = document.querySelector(".menu-dropdown");
	    var contentDropdown = document.querySelector(".content-dropdown");
	    var navbarArea = document.querySelectorAll("a");
	    var buttons = document.querySelectorAll("button");
	    dropdownButton.addEventListener("mouseover", function() {
	        contentDropdown.classList.add("active");
	    });
	    
	    navbarArea.forEach(function(navbar) {
	    	navbar.addEventListener("mouseover", function() {
		        contentDropdown.classList.remove("active");
		    });
	    });
	    
	    buttons.forEach(function(button) {
	    	button.addEventListener("mouseover", function() {
		        contentDropdown.classList.remove("active");
		    });
	    });
	}
});
</script>
