<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>포지션 제안</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/rview/rview.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/offer/offer_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
    <div class="container">
        <div class="section">
            <h3>포지션 제안</h3>
            <a href="content_pinfo" style="text-decoration: none;"><button class="boardsearchwrite">돌아가기</button></a>
        </div>
        <table>
            <tr class="first">
                <td>No.</td>
                <td>기업명</td>
                <td>포지션 제안 명</td>
                <td>제안 일자</td>
                <td>마감 일자</td>
                <td>답변 여부</td>
            </tr>
            <c:forEach items="${offerlist}" var="offer">
                <tr class="second" onclick="offerViewPopup('${offer.offerno}','${offer.puserid}','${offer.cuserid}', 'P')">
                    <td width="100px">${offer.rn}</td>
                    <td width="300px">${offer.cusnm}</td>
                    <td width="300px">${offer.title }</td>
                    <td width="300px"><fmt:formatDate value="${offer.adate}" pattern="yyyy-MM-dd"/></td>
                    <td width="300px"><fmt:formatDate value="${offer.ddate}" pattern="yyyy-MM-dd"/></td>
                    <td width="300px">${offer.gubun == 'D' ? '미응답':offer.gubun == 'Y' ? '수락':'거절'}</td>
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
		<%-- 
		<input type="hidden" name="type" value="${pageMaker.cri.type}">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="sort" value="${pageMaker.cri.sort}">
		<input type="hidden" name="order" value="${pageMaker.cri.order}">
		<input type="hidden" id="sortOrder" name="sortOrder" value="${sortOrder}">
		--%>
	</form>
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
		actionForm.attr("action","offerview").submit();
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
</script>
