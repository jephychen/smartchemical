function submit_product_search_form(queryType, brandIdSelected){
	var keyword = document.getElementById("product_list_search_input").value;
	var actionPostfix;
	if (queryType == 10){
		actionPostfix = "allProducts";
	}
	else if (queryType == 11){
		actionPostfix = "supplierProducts";
	}
	else if (queryType == 12){
		actionPostfix = "merchantProducts";
	}
	else if (queryType == 13){
		actionPostfix = "disabledProducts";
	}
	else{
		actionPostfix = "allProducts";
	}
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "AdminProductManagement_" + actionPostfix + ".action?keyword=" + keyword + "&brandIdSelected=" + brandIdSelected;
	searchForm.submit();
	return;
}

function companyFilterSelect(queryType){
	var selectObj = document.getElementById("product_list_search_company_dropdown");
	var keyword = document.getElementById("product_list_search_input").value;
	var brandIdSelected = selectObj.options[selectObj.selectedIndex].value;
	var actionPostfix;
	if (queryType == 10){
		actionPostfix = "allProducts";
	}
	else if (queryType == 11){
		actionPostfix = "supplierProducts";
	}
	else if (queryType == 12){
		actionPostfix = "merchantProducts";
	}
	else if (queryType == 13){
		actionPostfix = "disabledProducts";
	}
	else{
		actionPostfix = "allProducts";
	}
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "AdminProductManagement_" + actionPostfix + ".action?keyword=" + keyword + "&brandIdSelected=" + brandIdSelected;
	searchForm.submit();
	return;
}

function deleteProduct(queryType, productId, brandIdSelected, currentPage){
	var keyword = document.getElementById("product_list_search_input").value;
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "AdminProductManagement_removeProduct.action?keyword=" + keyword + "&productId=" + productId
		+ "&queryType=" + queryType + "&brandIdSelected=" + brandIdSelected + "&currentPage=" + currentPage;
	searchForm.submit();
	return;
}

function restoreProduct(queryType, productId, brandIdSelected, currentPage){
	var keyword = document.getElementById("product_list_search_input").value;
	var searchForm = document.getElementById("products_search");
	searchForm.method = "post";
	searchForm.action = "AdminProductManagement_restoreProduct.action?keyword=" + keyword + "&productId=" + productId
		+ "&queryType=" + queryType + "&brandIdSelected=" + brandIdSelected + "&currentPage=" + currentPage;
	searchForm.submit();
	return;
}