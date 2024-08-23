function offersave() {
	puserid = $("#puserid").val();
	title = $("#title").val();
	content = $("#content").val();
	ddate = $("#ddate").val();
	
	$.ajax({
		type: "post",
		data: { puserid: puserid, title: title, content: content, ddate: ddate },
		url: "/offersave",
		success: function(value){
			if(value== 'success') {
				alert('제안서를 보냈습니다.');
				window.close();
			}
		}, error: function () {
			console.log("Ajax 오류...");
		}
	});
}

function offerViewPopup(offerno, puserid, cuserid, gubun) {
	var popupWidth = 800;
	var popupHeight = 600;

	// 현재 브라우저 창의 너비와 높이를 가져옵니다.
	var screenWidth = window.innerWidth;
	var screenHeight = window.innerHeight;

	// 팝업 창의 위치를 중앙으로 설정합니다.
	var left = (screenWidth - popupWidth) / 2 + window.screenX;
	var top = (screenHeight - popupHeight) / 2 + window.screenY;
	
    var popupUrl = '/offerViewPopup?offerno='+offerno+'&puserid='+puserid+'&cuserid='+cuserid+'&gubun='+gubun;
	window.open(popupUrl, 'ApplyPopup', 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + left + ',top=' + top);
}

function offer_yn(yn) {
	var offerno = $("#offerno").val();
	var puserid = $("#puserid").val();
	var cuserid = $("#cuserid").val();
	var gubun = yn;
	if(yn == 'N') {
		if(confirm("제안을 거절하시껫습니까? 따로 불이익은 없습니다.")) {
			ajax_fn(offerno, puserid, cuserid, gubun);
		} else {
			return;
		}
	} else {
		ajax_fn(offerno, puserid, cuserid, gubun);
	}
}

function ajax_fn(offerno, puserid, cuserid, gubun) {
	$.ajax({
		type: "post",
		url: "/offeryn",
		data: { offerno: offerno, puserid: puserid, cuserid: cuserid, gubun: gubun },
		success: function(msg) {
			var result = JSON.parse(msg);
			if(result.gubun == 'Y') {
				alert('해당 제안을 수락했습니다.');
			} else {
				alert('해당 제안을 거절했습니다.');
			}
			
			if (window.opener && !window.opener.closed) {
				window.opener.location.href = '/offerview'; // 리다이렉트할 URL
			}
			window.close();
		}, error: function() {
			console.log("Ajax 에러...");
		}
	});
}