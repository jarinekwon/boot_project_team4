function commentWrite() {
	var boardno = $("#boardno").val();
	var replyid = $("#replyid").val();
	var content = $("#content").val();
	
	$.ajax({
		type: 'post',
		data: {replyid: replyid, content: content, boardno: boardno },
		url: '/comment/Ajaxsave',
		success: function(result) {
			//console.log("result");
			//입력칸 초기화
			$("#replyid").val("");
			$("#content").val("");
			//댓글 화면 출력
			$("#comment-list").html(result);
		},
		error: function() {
			console.log("ajax 에러...");
		}
	});
}

function commentShow() {
	var boardno = $("#boardno").val();
	var replyid = $("#replyid").val();
	var content = $("#content").val();
	
	$.ajax({
		type: 'post',
		data: {replyid: replyid, content: content, boardno: boardno },
		url: '/comment/AjaxShow',
		success: function(result) {
			$("#comment-list").html(result);
		},
		error: function() {
			console.log("ajax 에러...");
		}
	});
}

function commentModify() {
    var boardno = $("#boardno").val();
    var replyid = $("#replyid").val();
    var replyno = $("#replyno").val();
    var content = $("input[name='content']").val();

    $.ajax({
        type: 'post',
        data: { replyid: replyid, replyno: replyno, content: content, boardno: boardno },
        url: '/comment/modify',
        success: function(result) {
			alert("댓글이 성공적으로 수정되었습니다.")
            $("#comment-list").html(result);
        },
        error: function() {
            console.log("ajax 에러...");
        }
    });
}

function commentDelete() {
    if (confirm("댓글을 삭제하시겠습니까?")) {
        var boardno = $("#boardno").val();
        var replyno = $("#replyno").val();

        $.ajax({
            type: 'post',
            data: { replyno: replyno, boardno: boardno },
            url: '/comment/delete',
            success: function(result) {
                alert("댓글이 성공적으로 삭제되었습니다.");
                $("#comment-list").html(result);
            },
            error: function() {
                console.log("ajax 에러...");
            }
        });
    }
}