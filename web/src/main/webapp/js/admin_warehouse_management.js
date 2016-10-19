function submit_warehouse_search_form(queryType){
	var keyword = document.getElementById("warehouse_list_search_input").value;
	var actionPostfix;
	if (queryType == 0){
		actionPostfix = "allWarehouses";
	}
	else{
		actionPostfix = "allWarehouses";
	}
	var searchForm = document.getElementById("warehouse_search");
	searchForm.method = "post";
	searchForm.action = "AdminWarehouseManagement_" + actionPostfix + ".action?keyword=" + keyword;
	searchForm.submit();
}

function deleteWarehouse(queryType, warehouseId, currentPage){
	var keyword = document.getElementById("warehouse_list_search_input").value;
	var searchForm = document.getElementById("warehouse_search");
	searchForm.method = "post";
	searchForm.action = "AdminWarehouseManagement_removeWarehouse.action?keyword=" + keyword + "&warehouseId=" + warehouseId
		+ "&queryType=" + queryType + "&currentPage=" + currentPage;
	searchForm.submit();
}