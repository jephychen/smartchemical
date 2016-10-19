function submit_user_search_form(queryType){
	var keyword = document.getElementById("user_list_search_input").value;
	var actionPostfix;
	if (queryType == 10){
		actionPostfix = "allUsers";
	}
	else if (queryType == 11){
		actionPostfix = "merchantUsers";
	}
	else if (queryType == 12){
		actionPostfix = "supplierUsers";
	}
	else if (queryType == 13){
		actionPostfix = "commonUsers";
	}
	else if (queryType == 14){
		actionPostfix = "adminUsers";
	}
	else if (queryType == 15){
		actionPostfix = "warehouseAdminUsers";
	}
	else{
		actionPostfix = "allUsers";
	}
	var searchForm = document.getElementById("users_search");
	searchForm.method = "post";
	searchForm.action = "AdminUserManagement_" + actionPostfix + ".action?keyword=" + keyword;
	searchForm.submit();
	return;
}