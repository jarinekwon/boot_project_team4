function call_write() {
	location.href="/callwriteform";
}

function move_calllist() {
	location.href="/callcenter";
}

function call_add() {
	callfrm.submit();
}

function search() {
	var searchForm = $("#searchForm");

	searchForm.attr("action", "callcenter_search").submit();
}

function change() {
	var searchForm = $("#searchForm");

	// 전체일때
	if(searchForm.find("option:selected").val() == ""){
		// 키워드를 널값으로 변경
		searchForm.find("input[name='keyword']").val("");
	}
}

/* 관리자 계정 */
function admin_search() {
	var searchForm = $("#searchForm");

	searchForm.attr("action", "admin_call_search").submit();
}

function admin_callreply() {
	var searchForm = $("#commentfrm");
	
	searchForm.attr("action", "admin_reply").submit();
}

function admincall() {
	var moveform = $("#commentfrm");
	moveform.attr("action", "admincall").submit();
}

function call_update(callno, id) {
	var searchForm = $("#callviewfrm");
	$("#callno").val(callno);
	$("#authorid").val(id);
	
	searchForm.attr("action", "call_p_update").submit();
}

function call_delete(callno, id) {
	var searchForm = $("#callviewfrm");
	$("#callno").val(callno);
	$("#authorid").val(id);
	
	searchForm.attr("action", "call_p_delete").submit();
}