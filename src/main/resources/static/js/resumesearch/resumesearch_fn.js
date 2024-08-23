//초기화 버튼 이벤트
function resetBtn() {
	var searchbox = document.querySelector(".search-box");
	selectedItemsContainer.innerHTML = ''; //선택한 내용 초기화
	cnt = 0; //갯수 초기화
	searchbox.classList.remove("box-show");
	
	resetPage();
}

//검색 버튼 이벤트
function searchBtn() {
    var names = ['skill','loc','pcareer'];
    var arr = ['','','']; // 배열 초기화

    for (var i = 0; i < names.length; i++) { // names.length로 반복문 조건 수정
        var items = document.getElementsByName(names[i]);
        if (items.length > 0) {
            var item = "";
            for (var j = 0; j < items.length; j++) {
                item += items[j].value + ",";
            }
            $("#" + names[i] + "_s").val(item.slice(0, -1)); // Hidden Input 업데이트
            arr[i] = item.slice(0, -1);
        }
    }
	
	// 디버깅을 위해 console.log 추가
    console.log("Sending AJAX request with data:", { skill_s: arr[0], loc_s: arr[1], pcareer_s: arr[2] });
	
    $.ajax({
        type: "post",
        data: { skill_s: arr[0], loc_s: arr[1], pcareer_s: arr[2] },
        url: "/resumeSearchAjax",
		success: function (result) {
            console.log("result: " + JSON.stringify(result));
            $("#postArea").html(result); // 수정: 결과를 #postArea에 출력
        },
		error: function(xhr, status, error) {
	        console.error("Ajax 요청 실패:", status, error);
	    }
    });
}

function resetPage() {
	$.ajax({
		type:'post',
		data: { skill_s: "" , loc_s:"", pcareer_s:""},
		url: "/resumesearchRestAjax",
		success: function(result) {
			$("#postArea").html(result);
		}, error: function(xhr, status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}