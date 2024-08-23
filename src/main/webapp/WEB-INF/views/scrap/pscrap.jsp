<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/scrap/pscrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scrap/pscrap_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
	<div class="container">
		<section class="section" >
			<h2>스크랩 공고</h2>
		</section>
		<div class="listSortArea">
			<div>
				<input type="checkbox" id="allcheckbox" name="agreementAll" class="checkbox" onclick="AllCheck(this)">
            </div>
            <div>
				<button type="button" onclick="deleteBtn()">삭제</button>
	        </div>
            <div>
	            <select id="sortOptions" name="sortOptions" class="dropdown" onchange="changeSortOption(this.value)">
	                <option value="scrapDate" ${sort == 'scrapDate' ? 'selected' : ''}>스크랩일순</option>
    				<option value="deadline" ${sort == 'deadline' ? 'selected' : ''}>마감임박순</option>
	            </select>
            </div>
		</div>
		<form method="post" name="scrapdel" action="scrap_p_delete">
			<input type="hidden" name="csrno_s"  id="csrno_s">
			<input type="hidden" name="scrapno_s" id="scrapno_s">
			<input type="hidden" name="scrapid_s" id="scrapid_s">
			<c:forEach items="${scraplist}" var="dto">
				<div class="scrap_view">
					<input type="checkbox" name="agreement" class="checkbox" value="N">
					<div>
						<input type="hidden" name="csrno" value="${dto.csrno}">
						<input type="hidden" name="scrapno" value="${dto.scrapno}">
						<input type="hidden" name="scrapid" value="${dto.scrapid}">
						<div class="authorid_td">${dto.cusnm}</div>
						<div class="jobtitle">
							<a href="/recruitshowform?writer=${dto.scrapid}&csrno=${dto.csrno}&jobno=${dto.scrapno}" target="_blank">${dto.jobtitle}</a>
						</div>
						<div class="criteria">
							<span>${dto.careernm}↑</span>&nbsp;&nbsp;
							<span>${dto.edunm}↑</span>&nbsp;&nbsp;
							<span>${dto.loc01}</span> &nbsp;&nbsp;
							<span>${dto.wrktynm}</span> &nbsp;&nbsp;
						</div>
					</div>
					<div class="scrap_view_right">
						<div class="apply-button">
						<c:set var="daycha" value="${dto.daycha }"/>
						<c:set var="jobno" value="${dto.jobno }"/>
						<c:if test="${jobno == 0 }">
							<c:if test="${daycha >= 0 }">
<%-- 							<input type="text" value="${dto.scrapid }"> --%>
								<button type="button" class="apply_btn" onclick="open_Popup('${dto.scrapid }', '${dto.csrno }', '${dto.scrapno }');">즉시 지원</button>
							</c:if>
							<c:if test="${daycha < 0 }">
							</c:if>
						</c:if>
						<c:if test="${jobno > 0 }">
							<p>지원완료</p>
						</c:if>
						</div>
<%-- 						<div class="adate_td"><fmt:formatDate value="${dto.adate }" pattern="yyyy-MM-dd"/></div> --%>
						<div class="ddate">
							<c:choose>
			                    <c:when test="${dto.daycha > 0}">
			                        <font color="red">D-${dto.daycha}</font>
			                    </c:when>
			                    <c:otherwise>
			                        <font color="red">마감</font>
			                    </c:otherwise>
		                	</c:choose>
<%-- 							<fmt:formatDate value="${dto.ddate }" pattern="~MM-dd"/> --%>
						</div>
					</div>
				</div>
			</c:forEach>
		</form>
				<!-- 페이징 부분 -->
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
			--%>
			<input type="hidden" name="sort" value="">
		</form>
	</div>
</body>
<jsp:include page="../../footer.jsp"/>

</html>

<script>
	//페이징 처리
	var actionForm = $("#actionForm");
	
	//	페이지번호 처리
	$(".paginate_button a").on("click", function (e){
		//기본 동작 막음: 페이지 링크를 통해서 이동
		e.preventDefault();
		console.log("@# href=>"+$(this).attr("href"));
	
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.attr("action", "pscrap").submit();
	});//end of paginate_button clcik

</script>



