<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function fnMove() {
        var offset = $("#section1").offset();
        $('html, body').animate({
            scrollTop: offset.top
        }, 400); //0.4초 느리게
    }
</script>

<center>
	<input type="hidden" id="loc_s" name="loc_s" value="${loc_s}">
	<input type="hidden" id="skill_s" name="skill_s" value="${skill_s}">
	<input type="hidden" id="pcareer_s" name="pcareer_s" value="${pcareer_s }">
	<div class="container">
		<div class="header">
		    <div class="header-item header-name">이름</div>
		    <div class="header-item header-title">제목</div>
		    <div class="header-item header-skill">스킬</div>
		    <div class="header-item header-location">지역</div>
		    <div class="header-item header-cnt">경력사항</div>
		</div>
		<c:forEach items="${skilllist}" var="skill">
			<a onclick="location.href='/resume_v?puserid=${skill.puserid}&prono=${skill.prono}'" class="secondtr">
				<div class="list-item">
					<div class="name">${skill.pname}</div>
					<div class="title">
						${skill.protitle}
					</div>
					<div class="skill">${skill.skillnms}</div>
					<div class="location">${skill.paddr}</div>
					<div class="cnt">${skill.cnt == 0 ? '신입' : '경력'}</div>
				</div>
			</a>
		</c:forEach>
	</div>
	<div class="div_page">
		<ul>
			<c:if test="${pageMaker.prev}">
				<li class="paginate_button" style="padding: 0px; border-bottom: 0px;">
					<a class="page-link" href="${pageMaker.startpage - 1}">Prev</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startpage}" end="${pageMaker.endpage}">
				<li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : ''}" style="padding: 0px; border-bottom: 0px;">
					<a class="page-link" href="${num}">${num}</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next}">
				<li class="paginate_button" style="padding: 0px; border-bottom: 0px;">
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
</center>

<script type="text/javascript">
	$(".paginate_button a").on("click", function(e) {
		e.preventDefault();
		
		// 선택한 페이지 번호만 active 유지
	    $(".paginate_button").removeClass("active");
	    $(this).parent().addClass("active");
	    
	    // 컨트롤러단에 넘길 값들
		var loc_s = $("#loc_s").val();
		var skill_s = $("#skill_s").val();
		var pcareer_s = $("#pcareer_s").val();
		var pageNum = $(this).attr("href");
		var amount = $('#amount').val();
		
		$("input[name='pageNum']").val(pageNum); // 현재 페이지 값 삽입
		
		$.ajax({
			type: 'get',
			data: { loc_s: loc_s, skill_s: skill_s, pcareer_s: pcareer_s, pageNum: pageNum, amount: amount },
			url: '/resumesearchPageAjax',
			success: function(result) {
	            $("#postArea").html(result); // 수정: 결과를 #postArea에 출력
	            fnMove();
	        },
			error: function(xhr, status, error) {
		        console.error("Ajax 요청 실패:", status, error);
		    }
		});
	}); // end paginate_button
</script>