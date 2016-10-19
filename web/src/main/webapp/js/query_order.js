function onlinePay(poId, currentPage, lastMonth, queryType){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="OnlinePay.action?poId=" + poId + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth + "&queryType=" + queryType;
	payForm.submit();
}

function acceptanceBillPay(poId, currentPage, lastMonth, queryType){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="AcceptanceBillPay.action?poId=" + poId + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth + "&queryType=" + queryType;
	payForm.submit();
}

function confirmReceive(orderItemId, currentPage, lastMonth, queryType){
	var queryOrderForm = document.getElementById("myzhixuan_product_item_operation" + orderItemId);
	queryOrderForm.method="post";
	queryOrderForm.action="QueryOrder_confirmReceive.action?orderItemId=" + orderItemId + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth + "&queryType=" + queryType;
	queryOrderForm.submit();
}

function confirmGet(orderItemId, currentPage, lastMonth, queryType){
	var queryOrderForm = document.getElementById("myzhixuan_product_item_operation" + orderItemId);
	queryOrderForm.method="post";
	queryOrderForm.action="QueryOrder_confirmGet.action?orderItemId=" + orderItemId + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth + "&queryType=" + queryType;
	queryOrderForm.submit();
}

function datefilter(queryType, lastMonth){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	if (queryType == 0){
		payForm.action="QueryOrder_allOrders.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 1){
		payForm.action="QueryOrder_unpaid.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 2){
		payForm.action="QueryOrder_waitingReceived.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 3){
		payForm.action="QueryOrder_waitingGot.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 4){
		payForm.action="QueryOrder_doneOrder.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else {
		payForm.action="QueryOrder_unpaid.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	payForm.submit();
}

function cancelOrder(poId, currentPage, lastMonth, queryType){
	if (!confirm("确定取消订单？")){
		return;
	}
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="QueryOrder_cancelOrder.action?poId=" + poId + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth + "&queryType=" + queryType;
	payForm.submit();
}