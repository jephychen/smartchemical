function adminSetToPaid(poId, queryType, currentPage, lastMonth){
	var paidForm = document.getElementById("my_zhixuan_admin_settopaid_form");
	paidForm.method="post";
	paidForm.action="AdminQueryOrder_setToPaid.action?poId=" + poId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	paidForm.submit();
}

function confirmReceive(orderItemId, queryType, currentPage, lastMonth){
	var queryOrderForm = document.getElementById("myzhixuan_product_item_operation" + orderItemId);
	queryOrderForm.method="post";
	queryOrderForm.action="AdminQueryOrder_confirmReceive.action?orderItemId=" + orderItemId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	queryOrderForm.submit();
}

function confirmGet(orderItemId, queryType, currentPage, lastMonth){
	var queryOrderForm = document.getElementById("myzhixuan_product_item_operation" + orderItemId);
	queryOrderForm.method="post";
	queryOrderForm.action="AdminQueryOrder_confirmGet.action?orderItemId=" + orderItemId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	queryOrderForm.submit();
}

function confirmDeliver(orderItemId, queryType, currentPage, lastMonth){
	var queryOrderForm = document.getElementById("myzhixuan_product_item_operation" + orderItemId);
	queryOrderForm.method="post";
	queryOrderForm.action="AdminQueryOrder_confirmDeliver.action?orderItemId=" + orderItemId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	queryOrderForm.submit();
}

function acceptanceBillPay(poId, currentPage, lastMonth){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="AcceptanceBillPay.action?poId=" + poId + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	payForm.submit();
}

function checkBillValid(poId, queryType, currentPage, lastMonth){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="AdminQueryOrder_checkBillValid.action?poId=" + poId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	payForm.submit();
}

function checkBillInvalid(poId, queryType, currentPage, lastMonth){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="AdminQueryOrder_checkBillInvalid.action?poId=" + poId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	payForm.submit();
}

function receiveBill(poId, queryType, currentPage, lastMonth){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="AdminQueryOrder_receiveBill.action?poId=" + poId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	payForm.submit();
}

function datefilter(queryType, lastMonth){
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	if (queryType == 0){
		payForm.action="AdminQueryOrder_allOrders.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 1){
		payForm.action="AdminQueryOrder_unpaid.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 2){
		payForm.action="AdminQueryOrder_waitingReceived.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 3){
		payForm.action="AdminQueryOrder_waitingGot.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else if (queryType == 4){
		payForm.action="AdminQueryOrder_doneOrder.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	else {
		payForm.action="AdminQueryOrder_allOrders.action?queryType=" + queryType + "&currentPage=" + 1 + "&lastMonth=" + lastMonth;
	}
	payForm.submit();
}

function cancelOrder(poId, queryType, currentPage, lastMonth){
	if (!confirm("确定取消订单？")){
		return;
	}
	var payForm = document.getElementById("my_zhixuan_right_content_unpaid_form");
	payForm.method="post";
	payForm.action="AdminQueryOrder_cancelOrder.action?poId=" + poId + "&queryType=" + queryType + "&currentPage=" + currentPage + "&lastMonth=" + lastMonth;
	payForm.submit();
}