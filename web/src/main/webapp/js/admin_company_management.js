function submit_company_search_form(queryType){
	var keyword = document.getElementById("company_list_search_input").value;
	var actionPostfix;
	if (queryType == 0){
		actionPostfix = "allCompanies";
	}
	else if (queryType == 1){
		actionPostfix = "supplierCompanies";
	}
	else if (queryType == 2){
		actionPostfix = "merchantCompanies";
	}
	else if (queryType == 3){
		actionPostfix = "disabledCompanies";
	}
	else{
		actionPostfix = "allCompanies";
	}
	var searchForm = document.getElementById("companies_search");
	searchForm.method = "post";
	searchForm.action = "AdminCompanyManagement_" + actionPostfix + ".action?keyword=" + keyword;
	searchForm.submit();
}

function deleteProduct(queryType, companyId, currentPage){
	var keyword = document.getElementById("company_list_search_input").value;
	var searchForm = document.getElementById("companies_search");
	searchForm.method = "post";
	searchForm.action = "AdminCompanyManagement_removeCompany.action?keyword=" + keyword + "&companyId=" + companyId
		+ "&queryType=" + queryType + "&currentPage=" + currentPage;
	searchForm.submit();
}