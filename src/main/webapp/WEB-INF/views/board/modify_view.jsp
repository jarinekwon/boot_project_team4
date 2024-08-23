<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/board/modify_view.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/header/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/footer/footer.css">

<script>
    function fn_submit(){
        // form 요소 자체
        var formData = $("#frm").serialize();

        $.ajax({
             type: "post",
             data: formData,
             url: "modify",
             success: function(data){
                alert("수정이 완료되었습니다.");
                location.href = "list";
             },
             error: function(){
                alert("오류가 발생했습니다.");
             }
        });
    }
</script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
    <div class="container">
    <h2>게시판 글 수정</h2>
    <div class="button-container">
        <a href="list"><button class="listbutton">목록보기</button></a>
    </div>
    <table>
        <form id="frm" method="post">
            <input type="hidden" name="boardno" value="${modify_view.boardno}">
            <input type="hidden" name="authorid" value="${modify_view.authorid}">
            <tr>
                <td class="table_td">제목</td>
                <td>
                    <input type="text" name="title" value="${modify_view.title}">
                </td>
            </tr>
            <tr>
                <td class="table_td">내용</td>
                <td>
                    <textarea rows="10" name="content">${modify_view.content}</textarea>
                </td>
            </tr>
        </form>
    </table>
        <div class="button-container-bottom">
            <button type="button" class="submitbutton" onclick="fn_submit()">수정완료</button>
        </div>
    </div>
</body>
</html>