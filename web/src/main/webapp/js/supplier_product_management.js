function submit_product_search_form(queryType, companyIdSelected){
	var keyword = document.getElementById("product_list_search_input").value;
	var actionPostfix;
	if (queryType == 0){
		actionPostfix = "allProducts";
	}
	else if (queryType == 1){
		actionPostfix = "supplierProducts";
	}
	else if (queryType == 2){
		actionPostfix = "merchantProducts";
	}
	else if (queryType == 3){
		actionPostfix = "disabledProducts";
	}
	else{
		actionPostfix = "allProducts";
	}
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "SupplierProductManagement_" + actionPostfix + ".action?keyword=" + keyword + "&companyIdSelected=" + companyIdSelected;
	searchForm.submit();
	return;
}

function companyFilterSelect(queryType, companyIdSelected){
	var selectObj = document.getElementById("product_list_search_company_dropdown");
	var keyword = document.getElementById("product_list_search_input").value;
	var companyIdSelected = selectObj.options[selectObj.selectedIndex].value;
	var actionPostfix;
	if (queryType == 0){
		actionPostfix = "allProducts";
	}
	else if (queryType == 1){
		actionPostfix = "supplierProducts";
	}
	else if (queryType == 2){
		actionPostfix = "merchantProducts";
	}
	else if (queryType == 3){
		actionPostfix = "disabledProducts";
	}
	else{
		actionPostfix = "allProducts";
	}
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "SupplierProductManagement_" + actionPostfix + ".action?keyword=" + keyword + "&companyIdSelected=" + companyIdSelected;
	searchForm.submit();
	return;
}

function deleteProduct(queryType, productId, companyIdSelected, currentPage){
	var keyword = document.getElementById("product_list_search_input").value;
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "SupplierProductManagement_removeProduct.action?keyword=" + keyword + "&productId=" + productId
		+ "&queryType=" + queryType + "&companyIdSelected=" + companyIdSelected + "&currentPage=" + currentPage;
	searchForm.submit();
	return;
}

function restoreProduct(queryType, productId, companyIdSelected, currentPage){
	var keyword = document.getElementById("product_list_search_input").value;
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "SupplierProductManagement_restoreProduct.action?keyword=" + keyword + "&productId=" + productId
		+ "&queryType=" + queryType + "&companyIdSelected=" + companyIdSelected + "&currentPage=" + currentPage;
	searchForm.submit();
	return;
}