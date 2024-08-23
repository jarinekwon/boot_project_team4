<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/imgupload/imgupload.js"></script>
<script>
	$(document).ready({function(){
			(function(){
				var gubun = "<c:out value='${gubun }'/>";
				var table = "<c:out value='${table }'/>";
				var path = "<c:out value='${path }'/>";
				
				if(gubun != '') {
					$.getJSON("/getFileSelect", {gubun: gubun, table: table, path: path}, function(result) {
						console.log("@# result: "+result);
					});
				}
			})();	
		}
	});
</script>
<body>
	<form method="post">
		<input type="hidden" name="usetb" value="${usetb }">
		<input type="hidden" name="gubun" value="${gubun }">
		<input type="hidden" name="path" value="${path }">
		
		<div id="show">
			<img src="/display?usetb=${usetb}&gubun=${gubun}&path=${path}">
		</div>
	</form>
</body>