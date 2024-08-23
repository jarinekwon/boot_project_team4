<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/board/write_view.css">
	<script>
		function fn_submit(){
			//form 요소 자체
			var formData = $("#frm").serialize();

			$.ajax({
				 type:"post"
				,data:formData
				,url:"write"
				,success: function(data){
					alert("저장완료");
					location.href="list";
				}
				,error: function(){
					alert("오류발생");
				}
			});
		}
	</script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
	<div class="container">
		
		<h2>게시판 글 작성</h2>
		
		<div class="button-container">
			<a href="list"><button class="listbutton">목록보기</button></a>
	    </div>
		
		<table>
			<form id="frm" method="post" action="write">
				<tr class="first">
					<td class="table_td">제목</td>
					<td>
						<input type="text" name="title" size="50">
					</td>
				</tr>
				<tr class="second">
					<td class="table_td">내용</td>
					<td>
						<textarea rows="10" name="content"></textarea>
					</td>
				</tr>
		
			</form>
		</table>
				<div class="button-container-bottom">
			        <button type="button" class="submitbutton" onclick="fn_submit()">작성완료</button>
			    </div>

	</div>
</body>
</html>
<!--
<script>
	$(document).ready(function (e){
		var formObj = $("form[id='frm']");

		$("button[type='submit']").on("click", function(e){
			e.preventDefault();
			console.log("submit clicked");

			var str="";

			$(".uploadResult ul li").each(function (i, obj){
				console.log("@# obj=>"+$(obj));
				console.log("@# obj=>"+$(obj).data());
				console.log("@# obj=>"+$(obj).data("filename"));
				// return;

				var jobj = $(obj);
				// JavaScript개체의 속성에 대한 목록을 표시
				console.dir(jobj);
				console.log("================================");
				console.log(jobj.data("filename"));
				console.log(jobj.data("uuid"));
				console.log(jobj.data("path"));
				console.log(jobj.data("type"));

				str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].image' value='"+jobj.data("type")+"'>";
			});//end of uploadResult ul li

			console.log(str);
			// return;
			formObj.append(str).submit();
		});//end of button submit

		//확장자(exe, sh, alz), 파일크기(5MB 미만) 조건
		//확장자가 exe|sh|alz 업로드 금지하기 위한 정규식
		var regex = new RegExp("(.*?)\.(exe|sh|alz)$");
		var maxSize = 5242880;//5MB

		function checkExtension(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			return true;
		}

		$("input[type='file']").change(function (e){
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			//files : 파일정보
			var files = inputFile[0].files;

			for(var i=0; i<files.length; i++){
				console.log("@# files=>"+files[i].name);

				//파일크기와 종류중에서 거짓이면 리턴
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}

				//파일 정보를 formData에 추가
				formData.append("uploadFile",files[i]);
			}

			$.ajax({
				 type: "post"
				,data: formData
				//컨트롤러단 호출
				,url: "uploadAjaxAction"
//processData : 기본은 key/value 를 Query String 으로 전송하는게 true
//(파일 업로드는 false)
				,processData: false
//contentType : 기본값 : "application / x-www-form-urlencoded; charset = UTF-8"
//첨부파일은 false : multipart/form-data로 전송됨
				,contentType: false
				,success: function(result){
					alert("Uploaded");
					console.log(result);
					//파일정보들을 함수로 보냄
					showUploadResult(result);//업로드 결과 처리 함수 
				}
			});//end of ajax

			function showUploadResult(uploadResultArr){
				if(!uploadResultArr || uploadResultArr.length == 0){
					return;
				}

				var uploadUL = $(".uploadResult ul");
				var str="";

				$(uploadResultArr).each(function (i, obj){
					//image type
					if (obj.image) {
						console.log("@# obj.uploadPath=>"+obj.uploadPath);
						console.log("@# obj.uuid=>"+obj.uuid);
						console.log("@# obj.fileName=>"+obj.fileName);

						// var fileCallPath = obj.uploadPath + obj.uuid + "_" + obj.fileName;
						// var fileCallPath = encodeURIComponent(obj.uploadPath +"/"+ obj.uuid + "_" + obj.fileName);
						var fileCallPath = encodeURIComponent(obj.uploadPath +"/s_"+ obj.uuid + "_" + obj.fileName);
						// str += "<li><div>";

						str += "<li data-path='" + obj.uploadPath + "'";
						str += " data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'"
						str + " ><div>";

						str += "<span>"+obj.fileName+"</span>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";//이미지 출력 처리(컨트롤러단)
						str += "<span data-file=\'"+ fileCallPath +"\'data-type='image'> x </span>";
						str += "</div></li>";
					} else {
						// var fileCallPath = obj.uploadPath + obj.uuid + "_" + obj.fileName;
						var fileCallPath = encodeURIComponent(obj.uploadPath +"/"+ obj.uuid + "_" + obj.fileName);
						// str += "<li><div>";

						str += "<li data-path='" + obj.uploadPath + "'";
						str += " data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'"
						str + " ><div>";

						str += "<span>"+obj.fileName+"</span>";
						str += "<img src='./resources/img/attach.png'>";
						str += "<span data-file=\'"+ fileCallPath +"\'data-type='file'> x </span>";
						str += "</div></li>";
					}
				});//end of each

				//div class 에 파일 목록 추가
				uploadUL.append(str);
			}

			$(".uploadResult").on("click","span",function(){
				var targetFile = $(this).data("file");
				var type = $(this).data("type");
				var uploadResultItem = $(this).closest("li");

				console.log("@# targetFile=>"+targetFile);
				console.log("@# type=>"+type);
				console.log("@# uploadResultItem=>"+uploadResultItem);

				//컨트롤러 단에서 업로드된 실제파일 삭제
				$.ajax({
					 type: "post"
					,data: {fileName: targetFile, type: type}
					,url: "deleteFile"
					,success: function(result){
						alert(result);
						//브라우저에서 해당 썸네일이나 첨부파일이미지 제거
						uploadResultItem.remove();
					}
				});//end of ajax
			});//end of click
		});//end of change
	});//end of ready
</script>
-->
<script>
/*
$(uploadResultArr).each(function (i, obj){
	//image type
	if (obj.image) {
		console.log("@# obj.uploadPath=>"+obj.uploadPath);
		console.log("@# obj.uuid=>"+obj.uuid);
		console.log("@# obj.fileName=>"+obj.fileName);

		// var fileCallPath = obj.uploadPath + obj.uuid + "_" + obj.fileName;
		// var fileCallPath = encodeURIComponent(obj.uploadPath +"/"+ obj.uuid + "_" + obj.fileName);
		var fileCallPath = encodeURIComponent(obj.uploadPath +"/s_"+ obj.uuid + "_" + obj.fileName);
		str += "<li><div>";
		str += "<span>"+obj.fileName+"</span>";
		str += "<img src='/display?fileName="+fileCallPath+"'>";//이미지 출력 처리(컨트롤러단)
		str += "</div></li>";
	} else {
		var fileCallPath = obj.uploadPath + obj.uuid + "_" + obj.fileName;
		str += "<li><div>";
		str += "<span>"+obj.fileName+"</span>";
		str += "<img src='./resources/img/attach.png'>";
		str += "</div></li>";
	}
});//end of each
*/
</script>





