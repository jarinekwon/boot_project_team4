function myinfo_modify_p() {
	var puserid = $("#puserid").val();
	var ppass = $("#ppass").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	
	$.ajax({
		type: 'put',
		data: {puserid: puserid, ppass: ppass, phone: phone, email: email},
		url: '/RestUserUpdate_p',
		success: function(response) {
			if(response == 'success') {
				alert("수정이 완료되었습니다.")
				window.location.href= "/content_pinfo";
			}
		}, error: function(xhr, status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function myinfo_delete_p() {
	var puserid = $("#puserid").val();
	
	if(confirm("모든 정보가 다 삭제 됩니다. 탈퇴하시겠습니까?")) {
		$.ajax({
			type: 'delete',
			data: { puserid: puserid },
			url: "/RestUserDelete_p",
			success: function(responce) {
				if(responce == 'success') {
					window.location.href= "/main";
				}
			} , error: function(xhr, status, error) {
				console.error("Ajax 요청 실패:", status, error);
			}
		});
	}
}

function myinfo_modify_c() {
	var cuserid = $("#cuserid").val();
	var cpass = $("#cpass").val();
	var cusnm = $("#cusnm").val();
	var phone = $("#phone").val();
	var cmail = $("#cmail").val();
	var bsnum = $("#bsnum").val();
	var cleader = $("#cleader").val();
	var caddr = $("#caddr").val();
	
	$.ajax({
		type: 'put',
		data: { cuserid: cuserid, cpass: cpass, cusnm: cusnm, phone: phone, cmail: cmail, bsnum: bsnum, cleader: cleader, caddr: caddr },
		url: '/RestUserUpdate_c',
		success: function(response) {
			if(response == 'success') {
				alert("수정이 완료되었습니다.")
				window.location.href= "/content_cinfo";
			}
		}, error: function(xhr, status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function myinfo_delete_c() {
	var cuserid = $("#cuserid").val();
	
	if(confirm("모든 정보가 삭제됩니다. 탈퇴를 진행하시겠습니까?")) {
		$.ajax({
			type: 'delete',
			data: { cuserid: cuserid },
			url: "/RestUserDelete_c",
			success: function(responce) {
				if(responce == 'success') {
					window.location.href= "/main";
				}
			} , error: function(xhr, status, error) {
				console.error("Ajax 요청 실패:", status, error);
			}
		});
	}
}