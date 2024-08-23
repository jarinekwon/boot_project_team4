function search() {
	var searchForm = $("#searchForm");
	
	searchForm.attr("action", "scribe_search").submit();
}