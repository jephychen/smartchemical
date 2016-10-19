function confirmDeliver(orderItemId, queryType, currentPage, lastMonth, actionName){
	var queryOrderForm = document.getElementById("myzhixuan_product_item_operation" + orderItemId);
	queryOrderForm.method="post";
	queryOrderForm.action=actionName + "_confirmDeliver.action?orderItemId=" + orderItemId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	queryOrderForm.submit();
}