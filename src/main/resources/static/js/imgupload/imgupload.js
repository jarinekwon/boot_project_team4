function upload() {
	var formData = new FormData();
	var file = $("#img")[0].files[0];
	
	formData.append("img", file);
	
	$.ajax({
		type: 'post',
		data: formData,
		url: "/imgupload_ajax",
		processData: false, //기본은 key-value를 Query String으로 전송하는게 true (파일은 false)
		contentType: false, //기본은 application / x-www-from-urlencoded; charset=UTF-8 (파일은 false -> multipart/form-data로 정송)
		success: function(result) {
			console.log("result: "+result);
			$("#img").val("");
			$("#imgshow").html(result);
		}, error: function(){
			console.log("ajax 에러...");
		}
	});
}