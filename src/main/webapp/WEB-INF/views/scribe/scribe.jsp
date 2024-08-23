<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/scribe/scribe.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scribe/scribe_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
	<div class="container">
		<div class="section">
			<h3>나의 관심 기업</h3>
		</div>
	
		<table>
			<tr class="first">
				<td width="100">번호</td>	
				<td width="300">기업명</td>
				<td width="100">위치</td>
				<td width="200">등록일</td>	
			</tr>
			<c:forEach items="${scribelist}" var="dto">
				<tr class="second">
					<td class="boardno_td">${dto.rn}</td>
					<td class="authorid_td">
   						<a href="/coinfoshow?writer=${dto.scribeid}&gubun=post">${dto.cusnm}</a>
					</td>
					<td class="title_td">${dto.caddr}</td>
					<td class="adate_td"><fmt:formatDate value="${dto.adate }" pattern="yyyy-MM-dd"/></td>
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
		</form>
	
		<div class="search">
			<form method="get" id="searchForm">
				<select name="type" class="searchoption">
					<option value="all" <c:out value="${pageMaker.cri.type eq 'all' ? 'selected':''}"/> >전체</option>
					<option value="nm" <c:out value="${pageMaker.cri.type eq 'nm' ? 'selected':''}"/> >기업명</option>
					<option value="lc" <c:out value="${pageMaker.cri.type eq 'lc' ? 'selected':''}"/> >위치</option>
				</select>
				<input type="text" id="boardsearchbar" name="keyword" value="${pageMaker.cri.keyword}">
				 <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<button id="boardsearchbutton" onclick="search()">검색</button>
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

		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.attr("action", "scribe").submit();
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

	// 	type 콤보박스 변경
	$("#searchForm select").on("change", function(){
		// 전체일때
		if(searchForm.find("option:selected").val() == ""){
			// 키워드를 널값으로 변경
			searchForm.find("input[name='keyword']").val("");
		}
	});//end of searchForm select change
</script>











