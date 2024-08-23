<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/board/content.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/board/board_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>

<body onload="commentShow()">
	<div class="board-name">
		<h2>취업 게시판</h2>
		<a href="list"><button class="listbutton">목록보기</button></a>
	</div>
	
<div class="container">

	<form method="post" action="modify_view">
		<input type="hidden" id="boardno" name="boardno" value="${pageMaker.boardno}">
		<input type="hidden" name="pageNum" value="${pageMaker.pageNum}">
		<input type="hidden" name="amount" value="${pageMaker.amount}">
	
			<div class="content_area">
			<div class="content_title_area">
				<h1>${content_view.title}</h1><br>
				ID: ${content_view.authorid}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				조회수: ${content_view.boardhit}<br>
					<!-- 사용자 권한에 따른 버튼 표시 -->
					<div class="choosebutton">
				        <c:choose>
				            <c:when test="${userid == content_view.authorid}">
								<link rel="stylesheet" href="${pageContext.request.contextPath}/style/board/mod_ded.css">
				                <button type="submit">수정</button>
				                <button type="submit" formaction="delete">삭제</button>
				            </c:when>
				            <c:otherwise>
				                <!-- 수정 및 삭제 버튼을 표시하지 않음 -->
				            </c:otherwise>
				        </c:choose>
					</div>
						
			</div>
			<div class="content_content_area">${content_view.content}</div>
		</div>
	
	</form>
	<div class="comment-write">
		<!-- <input type="text" id="commentWriter" placeholder="작성자"> -->
		<!-- 작성자 ID 넘기기 -->
		<form id="commentfrm" method="post">
			<input type="hidden" id="boardno" name="boardno" value="${pageMaker.boardno}">
			<%-- <input type="text" class="commentWriter" id="replyid" placeholder="ID"> --%>
			<input type="text" class="commentContent" id="content" placeholder="댓글을 남겨보세요">
			<button type="button" onclick="commentWrite()">작성</button>
		</form>
	</div>
	
	<div class="comment-list" id="comment-list">
		<h4>댓글</h4>
		<%-- comment_ajax.jsp 출력 --%>
	</div>
</div>

</body>
<jsp:include page="../../footer.jsp"/>
	<%-- 
	<script>
		$(document).ready(function (){
			// 즉시실행함수
			(function(){
				console.log("@# document ready");
				var boardno = "<c:out value='${content_view.boardno}'/>";
				console.log("@# boardno=>"+boardno);

				$.getJSON("/getFileList", {boardno: boardno}, function (arr){
					console.log("@# arr=>"+arr);

					var str="";

					$(arr).each(function (i, attach){
						//image type
						if (attach.image) {
							var fileCallPath = encodeURIComponent(attach.uploadPath +"/s_"+ attach.uuid + "_" + attach.fileName);
							str += "<li data-path='" + attach.uploadPath + "'";
							str += " data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.image + "'"
							str + " ><div>";
							str += "<span>"+attach.fileName+"</span>";
							str += "<img src='/display?fileName="+fileCallPath+"'>";//이미지 출력 처리(컨트롤러단)
							// str += "<span data-file=\'"+ fileCallPath +"\'data-type='image'> x </span>";
							str += "</div></li>";
						} else {
							// var fileCallPath = encodeURIComponent(attach.uploadPath +"/"+ attach.uuid + "_" + attach.fileName);
							str += "<li data-path='" + attach.uploadPath + "'";
							str += " data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.image + "'"
							str + " ><div>";
							str += "<span>"+attach.fileName+"</span>";
							str += "<img src='./resources/img/attach.png'>";
							// str += "<span data-file=\'"+ fileCallPath +"\'data-type='file'> x </span>";
							str += "</div></li>";						
						}
					});//end of arr each

					$(".uploadResult ul").html(str);
				});//end of getJSON

					$(".uploadResult").on("click", "li", function (e){
					console.log("@# uploadResult click");
					
					var liObj = $(this);
					console.log("@# path 01=>",liObj.data("path"));
					console.log("@# uuid=>",liObj.data("uuid"));
					console.log("@# filename=>",liObj.data("filename"));
					console.log("@# type=>",liObj.data("type"));
					
					var path = encodeURIComponent(liObj.data("path") +"/"+ liObj.data("uuid") + "_" + liObj.data("filename"));
					console.log("@# path 02=>",path);

					if (liObj.data("type")) {
						console.log("@# 01");
						console.log("@# view");

						showImage(path);
					} else {
						console.log("@# 02");
						console.log("@# download");

						//컨트롤러의 download 호출
						self.location="/download?fileName="+path;
					}
				});//end of uploadResult click

				function showImage(fileCallPath){
					// alert(fileCallPath);

					$(".bigPicture").css("display","flex").show();
					$(".bigPic")
						.html("<img src='/display?fileName="+fileCallPath+"'>")
						.animate({width: "100%", height: "100%"}, 1000);
				}

				$(".bigPicture").on("click", function (e){
					$(".bigPic")
						.animate({width: "0%", height: "0%"}, 1000);
					setTimeout(function (){
						$(".bigPicture").hide();
					}, 1000);//end of setTimeout
				});//end of bigPicture click
			})();
		});//end of document ready
	</script>
	--%>
</html>






