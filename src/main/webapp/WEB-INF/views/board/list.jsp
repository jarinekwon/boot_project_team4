<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/board/list.css">
</head>
<jsp:include page="../../header.jsp"/>
<body>
	
	<div class="container">
		
	<div class="section">
		<h3>취업 게시판</h3>
		<button id="myPostsButton">작성한글 보기</button>
		<a href="write_view" style="text-decoration: none;"><button class="boardsearchwrite">글 작성하기</button></a>
	</div>
	
	<div class="dropMenu">
        <select id="selectSort" name="sortOrder" onchange="submitForm()">
            <option>정렬방식</option>
            <option value="views_desc" ${sortOrder == 'views_desc' ? 'selected' : ''}>조회수 높은 순</option>
            <option value="views_asc" ${sortOrder == 'views_asc' ? 'selected' : ''}>조회수 낮은 순</option>
            <option value="date_desc" ${sortOrder == 'date_desc' ? 'selected' : ''}>최근에 올린 순</option>
            <option value="date_asc" ${sortOrder == 'date_asc' ? 'selected' : ''}>예전에 올린 순</option>
        </select>
	</div>
	
	<table>
		<tr class="first">
			<td>번호</td>	
			<td>작성자</td>	
			<td>제목</td>	
			<td>작성일</td>	
			<td class="th_last">조회수</td>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr class="second" onclick="location.href='/content_view?boardno=${dto.boardno}&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}'">
				<td class="boardno_td">${dto.boardno}</td>
				<td class="authorid_td">${dto.authorid}</td>
				<td class="title_td">${dto.title}</td>
				<td class="adate_td"><fmt:formatDate value="${dto.adate }" pattern="yyyy-MM-dd"/></td>
				<td class="boardhit">${dto.boardhit}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="div_page">
		<ul>
			<c:if test="${pageMaker.prev}">
				<li class="paginate_button" style="padding: 0px; border-bottom: 0px;">
					<a class="page-link" href="${pageMaker.startpage - 1}">
						Prev
					</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startpage}" end="${pageMaker.endpage}">
				<li class="page-item paginate_button ${pageMaker.cri.pageNum == num?'active':'' }" style="padding: 0px; border-bottom: 0px; ">
					<a class="page-link" href="${num}">
						${num}
					</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next}">
				<li class="page-item paginate_button" style="padding: 0px; border-bottom: 0px;">
					<a class="page-link" href="${pageMaker.endpage + 1}">
						Next
					</a>
				</li>
			</c:if>
		</ul>
	</div>
	
	<form id="actionForm" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
		<!-- 페이징 검색시 페이지번호를 클릭할때 필요한 파라미터 -->
		<input type="hidden" name="type" value="${pageMaker.cri.type}">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="sort" value="${pageMaker.cri.sort}">
		<input type="hidden" name="order" value="${pageMaker.cri.order}">
		<input type="hidden" id="sortOrder" name="sortOrder" value="${sortOrder}">
	</form>
	
	<div class="search">
	<form id="searchForm" method="get">
		<select name="type" name="searchbox" class="searchoption">
			<option value="TCW" <c:out value="${pageMaker.cri.type eq 'TCW' ? 'selected':''}"/> >전체</option>
			<option value="T" <c:out value="${pageMaker.cri.type eq 'T' ? 'selected':''}"/> >제목</option>
			<option value="C" <c:out value="${pageMaker.cri.type eq 'C' ? 'selected':''}"/> >내용</option>
			<option value="W" <c:out value="${pageMaker.cri.type eq 'W' ? 'selected':''}"/> >작성자</option>
		</select>
		<input type="text" id="boardsearchbar" name="keyword" value="${pageMaker.cri.keyword}" placeholder="  검색어를 입력해주세요">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 
		<input type="hidden" name="pageNum" value="1">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
		<input type="hidden" name="mypost" value="">
		<button id="boardsearchbutton">검색</button>
	</form>
	</div>
</div>
</body>
<jsp:include page="../../footer.jsp"/>

</html>
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
		actionForm.attr("action","list").submit();
	});//end of paginate_button clcik

	// 	게시글 처리
	$(".move_link").on("click", function(e){
		e.preventDefault();

		console.log("@# move_link click~!!!");
		console.log("@# href=>"+$(this).attr("href"));

		var targetBno = $(this).attr("href");
	
		// 게시글 클릭후 뒤로가기 누른후 다른 게시글 클릭할때 &boardno=번호 계속 누적되는거 방지
		var bno = actionForm.find("input[name='boardno']").val();
		if(bno != ""){
			actionForm.find("input[name='boardno']").remove();
		}

		// "content_view?boardno=${dto.boardno}" 를 actionForm 로 처리
		actionForm.append("<input type='hidden' name='boardno' value='"+targetBno+"'>");
		// actionForm.submit();
		// 컨트롤러에 content_view 로 찾아감
		actionForm.attr("action","content_view").submit();
	});//end of move_link click

	var searchForm = $("#searchForm");

	// 	Search 버튼 클릭
	// $("#searchForm").on("click", function(){
	$("#searchForm button").on("click", function(){
		// alert("검색");

		// 아래는 검색종류까지 할때 참고
		// if(!searchForm.find("option:selected").val()){
		// 	alert("검색종류를 선택하세요.");
		// 	return false;
		// }
		var selectboxvalue = document.getElementById("searchbox").value;
		if(selectboxvalue != 'TCW' ||(searchForm.find("option:selected").val() != "" && !searchForm.find("input[name='keyword']").val())){
			alert("키워드를 입력하세요.");
			return false;
		}
		searchForm.find("input[name='mypost']").val("");
		searchForm.attr("action","list").submit();
	});//end of searchForm click

	// 	type 콤보박스 변경
	$("#searchForm select").on("change", function(){
		// 전체일때
		if(searchForm.find("option:selected").val() == ""){
			// 키워드를 널값으로 변경
			searchForm.find("input[name='keyword']").val("");
		}
	});//end of searchForm select change
	
	<% 
	    // 세션에서 현재 로그인 중인 아이디 가져오기
	    String UserId = (String) session.getAttribute("id"); 
	%>

    // JSP에서 가져온 로그인된 아이디를 JavaScript 변수에 설정
    var id = "<%= UserId != null ? UserId : "" %>";

    $(document).ready(function() {
        $("#myPostsButton").on("click", function() {
            // 검색 폼에서 타입을 'W'로 설정
           	searchForm.find("select[name='type']").val("W");
            // 사용자 ID를 키워드로 설정
            searchForm.find("input[name='keyword']").val(id);
            searchForm.find("input[name='mypost']").val("my");
            // 폼을 제출
            searchForm.attr("action", "list").submit();
			document.getElementById().reset();
        });
    });
    
    function submitForm() {
    	var sort = $("#selectSort").val();
		$("#sortOrder").val(sort);
        document.getElementById("actionForm").submit();
    }
</script>











