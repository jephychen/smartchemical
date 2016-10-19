function submitSearch(){
	var searchForm = document.getElementById("search_form_global");
	var keyword = document.getElementById("top_search_user_page_id").value;
	searchForm.method="post";
	searchForm.action="MainSearch.action?keyword=" + keyword;
	searchForm.submit();
}