<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String userid = "", usergubun = "";
    try {
        userid = (String) session.getAttribute("id");
        usergubun = (String) session.getAttribute("usergubun");
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<c:set var="userid" value="<%= userid %>" />

<h4>댓글</h4>

<c:forEach items="${commentList}" var="comment">
    <div class="comments">
        <div class="chobutton">
            <c:choose>
                <c:when test="${userid == comment.replyid}">
                    <form id="commentfrm" method="post">
						<div class="comment-header">
		                    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/board/content_button.css">
		                    <input type="hidden" id="boardno" name="boardno" value="${pageMaker.boardno}">
		                    <input type="hidden" id="replyid" name="replyid" value="${comment.replyid}">
		                    <input type="hidden" id="replyno" name="replyno" value="${comment.replyno}">
							
		                    <p>ID: ${comment.replyid}</p>
		                    <button type="button" onclick="commentModify()">수정</button>
		                    <button type="button" onclick="commentDelete()">삭제</button>
						</div>
                        <div class="p3">
                            <input type="text" name="content" value="${comment.content}">
                        </div>
                        <p><fmt:formatDate value="${comment.adate}" pattern="yyyy-MM-dd HH시 mm분"/>에 작성</p>
                    </form>
                </c:when>
				
                <c:otherwise>
                    <!-- 수정 및 삭제 버튼을 표시하지 않음 -->
                    <p>ID: ${comment.replyid}</p>
                    <div class="p2">
                        <p>${comment.content}</p>
                    </div>
                    <p><fmt:formatDate value="${comment.adate}" pattern="yyyy-MM-dd HH시 mm분"/>에 작성</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:forEach>