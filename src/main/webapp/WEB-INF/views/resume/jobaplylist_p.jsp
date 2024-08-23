<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>지원 내역</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/jobaply/jobaplylist_p.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jobaply/jobaplylist_p.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="container">
    	<div class="title">
	        <h2>지원내역</h2>
	        <p>${name} 님이 지원한 모든 구직공고목록입니다</p>
    	</div>
		<form name="jobaplyfrm" method="get" class="applicant" action="recruitinfo_p">
            <input type="hidden" id="cuserid" name="cuserid">
            <input type="hidden" id="csrno" name="csrno">
            <input type="hidden" id="jobno" name="jobno">
			<c:forEach items="${jobaplylist_p}" var="dto">
            	<div class="applicant-list">
                    <div id="jobaplylist" class="jobaplylist">
                        <h2>${dto.jobtitle} (${dto.prchk == 1?'*기업에서 확인을 했습니다.*':'*아직 기업에서 확인을 하지 않았습니다.*' })</h2>
                        <fmt:formatDate value="${dto.adate}" pattern="yyyy-MM-dd" var="dateValue"/>
						지원일자: ${dateValue}                        
                    </div>
                    <div class="button">
                        <button type="button" class="view-button1" onclick="recruitinfo_view('${dto.cuserid}','${dto.csrno}','${dto.jobno}')">보기</button> 
                    </div>
            	</div>
  	      	</c:forEach>
		</form>
		    <div class="div_page">
		<ul>
			<c:if test="${pageMaker.prev }">
				<li class="page-item paginate_button" style="padding: 0px; border-bottom: 0px; ">
					<a class="page-link" href="${pageMaker.startpage - 1}">Prev</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startpage }" end="${pageMaker.endpage }">
				<li class="page-item paginate_button ${pageMaker.cri.pageNum == num?'active':'' }" style="padding: 0px; border-bottom: 0px; ">
					<a class="page-link" href="${num}">${num }</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next }">
				<li class="page-item paginate_button" style="padding: 0px; border-bottom: 0px;">
					<a class="page-link" href="${pageMaker.endpage + 1}">Next</a>
				</li>
			</c:if>
		</ul>
    </div>
    <form id="actionForm" method="get">
		<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum}">
		<input type="hidden" id="amount" name="amount" value="${pageMaker.cri.amount}">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="type" value="${pageMaker.cri.type}">
	</form>
</div>
</body>
<script>
var actionForm = $("#actionForm");

//	페이지번호 처리
$(".paginate_button a").on("click", function (e){
	//기본 동작 막음: 페이지 링크를 통해서 이동
	e.preventDefault();
	console.log("click~!!!");
	console.log("@# href=>"+$(this).attr("href"));

	// 게시글 클릭후 뒤로가기 누른후 다른 페이지 클릭할때 &boardno=번호 계속 누적되는거 방지
	var bno = actionForm.find("input[name='boardno']").val();
	if(bno != ""){
		actionForm.find("input[name='boardno']").remove();
	}

	actionForm.find("input[name='pageNum']").val($(this).attr("href"));
	actionForm.attr("action","jobaplylist_p").submit();
});
</script>
</body>
</html>