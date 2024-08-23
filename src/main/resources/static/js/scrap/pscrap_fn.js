//스크랩 정렬함수
function changeSortOption(sortOption) {
    var actionForm = $("#actionForm");
    actionForm.find("input[name='sort']").val(sortOption); // 정렬 옵션 추가
    actionForm.attr("action", "pscrap").submit();
}

//한번에 체크박스 체크하는 기능
function AllCheck(masterCheckbox) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"][name="agreement"]');
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = masterCheckbox.checked;
    });
}

function deleteBtn() {
	var names = ['csrno', 'scrapno', 'scrapid'];
    var arr = ['', '', '']; // 배열 초기화
	var items1 = "";
	var items2 = "";
	var items3 = "";
	var count = 0;
	for(var i =0; i<document.getElementsByName("agreement").length; i++) {
		if(document.getElementsByName("agreement")[i].checked == true) {
			document.getElementsByName("agreement")[i].value = "Y";
			count++;
		}
	}
	
	if(count == 0) {
		alert('선택한 스크랩 내역이 없습니다.');
		return false;
	}
	
	var cnt = document.getElementsByName("agreement").length;
	for (var i = 0; i < cnt; i++) { //checkbox.length로 반복문 조건 수정
        var chk = document.getElementsByName("agreement")[i].value;
		
		if(chk == 'Y') { //체크 상태 true
			items1 += document.getElementsByName("csrno")[i].value+",";
			items2 += document.getElementsByName("scrapno")[i].value+",";
			items3 += document.getElementsByName("scrapid")[i].value+",";
		}
    }
	
	// 마지막 콤마 제거
	items1 = items1.slice(0, -1);
	items2 = items2.slice(0, -1);
	items3 = items3.slice(0, -1);
	
	for(var i=0; i<names.length; i++) {
		var item = '';
		switch(i) {
			case 0:
				item = items1;
				break;
			case 1:
				item = items2;
				break;
			case 2:
				item = items3;
				break;
		}
		$("#" + names[i] + "_s").val(item); // Hidden Input 업데이트
		arr[i] = item;
	}
	
	if(confirm("선택한 스크랩 공고를 삭제하시겠습니까?")) {
		scrapdel.submit();
	}
}

//즉시지원시 팝업 함수
function open_Popup(writer, csrno, jobno ) {
	var popupWidth = 600;
	var popupHeight = 400;

	// 현재 브라우저 창의 너비와 높이를 가져옵니다.
	var screenWidth = window.innerWidth;
	var screenHeight = window.innerHeight;

	// 팝업 창의 위치를 중앙으로 설정합니다.
	var left = (screenWidth - popupWidth) / 2 + window.screenX;
	var top = (screenHeight - popupHeight) / 2 + window.screenY;
	
    var popupUrl = '/resumePopup?writer='+writer+'&csrno='+csrno+'&jobno='+jobno;
	var win = window.open(popupUrl, 'ApplyPopup', 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + left + ',top=' + top);

    // 팝업 창이 완전히 열린 후에 이력서 리스트를 가져오기 위해 onload 이벤트를 설정합니다.
    win.onload = function() {
        // 이력서 리스트를 가져와서 콤보박스에 추가합니다.
        var resumelist = win.document.getElementById('resumelist');
        var select = document.getElementById('resumelist'); // 콤보박스 id를 'resumelist'로 변경해야 합니다.

        for (var i = 0; i < resumelist.options.length; i++) {
            var option = document.createElement('option');
            option.value = resumelist.options[i].value;
            option.text = resumelist.options[i].text;
            select.appendChild(option);
        }
    };
}