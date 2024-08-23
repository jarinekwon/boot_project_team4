document.getElementById('addButtonDiv').addEventListener('click', function() {
    window.location.href = 'resume';
});

function update_resume(prono, imgno) {
	document.getElementById('resumefrm').action = 'resumeselect';
	
	$("#prono").val(prono);
	$("#imgno").val(imgno);
	
	document.getElementById('resumefrm').submit();
}

function delete_resume(prono, imgno) {
	document.getElementById('resumefrm').action = 'deleteresume';
	
	$("#prono").val(prono);
	$("#imgno").val(imgno);
	
	document.getElementById('resumefrm').submit();
}

function setRepresentative(prono, imgno) {
    $.ajax({
        url: '/setRepresentative',
        method: 'POST',
        data: {
            prono: prono,
            imgno: imgno
        },
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        dataType: 'json',
        success: function(response) {
            if (response.success) {
                // 기존 대표 이력서 해제
                document.querySelectorAll('.resume-card[data-representative="true"]').forEach(card => {
                    card.removeAttribute('data-representative');
                });

                // 새로운 대표 이력서 설정
                const newRepCard = document.querySelector(`.resume-card img[src*="${imgno}"]`).closest('.resume-card');
                newRepCard.setAttribute('data-representative', 'true');

                alert('대표 이력서로 설정되었습니다.');
            } else {
                alert('대표 이력서 설정에 실패했습니다.');
            }
        },
        error: function() {
            alert('대표 이력서 설정 중 오류가 발생했습니다.');
        }
    });
}

var actionForm = $("#actionForm");

var actionForm = $("#actionForm");

var actionForm = $("#actionForm");

// 페이지번호 처리
$(".paginate_button a").on("click", function (e){
    // 기본 동작 막음: 페이지 링크를 통해서 이동
    e.preventDefault();

    actionForm.find("input[name='pageNum']").val($(this).attr("href"));
    actionForm.attr("action", "resumelist").submit();
});

// 삭제 확인 함수
function confirmDelete(prono, imgno) {
    if (confirm("이력서를 삭제하시겠습니까?")) {
        delete_resume(prono, imgno);
    }
}

// 이력서 삭제 함수
function delete_resume(prono, imgno) {
    $.ajax({
        url: '/delete_resume', // 서버 측 삭제 처리 URL
        type: 'POST',
        data: {
            prono: prono,
            imgno: imgno
        },
        success: function(response) {
            if (response.success) {
                alert('이력서가 삭제되었습니다.');
                location.reload(); // 페이지 새로고침
            } else {
                alert('이력서 삭제에 실패했습니다.');
            }
        },
        error: function() {
            alert('이력서 삭제 중 오류가 발생했습니다.');
        }
    });
}