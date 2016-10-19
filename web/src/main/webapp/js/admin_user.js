function selectStatus(requestStatusSelected, bankAccountId, currentPage, queryType, lastMonth, requestStatus){
	var keyword = document.getElementById("request_search_keyword").value;
	if (requestStatus != requestStatusSelected){
		currentPage = 1;
	}
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "AdminDepositRequestManagement_allRequest.action?bankAccountId=" + bankAccountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastMonth + "&requestStatusSelected=" + requestStatusSelected + "&keyword=" + keyword;
	submitForm.submit();
}

function selectLastmonth(lastmonthSelected, bankAccountId, currentPage, queryType, lastMonth, requestStatus){
	var keyword = document.getElementById("request_search_keyword").value;
	if (lastMonth != lastmonthSelected){
		currentPage = 1;
	}
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "AdminDepositRequestManagement_allRequest.action?bankAccountId=" + bankAccountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastmonthSelected + "&requestStatusSelected=" + requestStatus + "&keyword=" + keyword;
	submitForm.submit();
}

function deleteRequest(requestId, accountId, currentPage, queryType, lastMonth, requestStatus){
	if (!confirm("确定取消请求？")){
		return;
	}
	var keyword = document.getElementById("request_search_keyword").value;
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "AdminDepositRequestManagement_removeRequest.action?bankAccountId=" + accountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastMonth + "&requestId=" + requestId + "&requestStatusSelected=" + requestStatus + "&keyword=" + keyword;
	submitForm.submit();
}

function processRequest(requestId, accountId, currentPage, queryType, lastMonth, requestStatus){
	if (!confirm("提现完成后，提现余额将从用户账户扣去。确定？")){
		return;
	}
	var keyword = document.getElementById("request_search_keyword").value;
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "AdminDepositRequestManagement_processRequest.action?bankAccountId=" + accountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastMonth + "&requestId=" + requestId + "&requestStatusSelected=" + requestStatus + "&keyword=" + keyword;
	submitForm.submit();
}

function submit_request_search_form(bankAccountId, currentPage, queryType, lastMonth, requestStatus){
	var keyword = document.getElementById("request_search_keyword").value;
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "AdminDepositRequestManagement_allRequest.action?bankAccountId=" + bankAccountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastMonth + "&requestStatusSelected=" + requestStatus + "&keyword=" + keyword;
	submitForm.submit();
}

function selectRechargeLastmonth( lastmonthSelected, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (lastMonth != lastmonthSelected){
		currentPage = 1;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastmonthSelected + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&userNameFilter=" + username;
	submitForm.submit();
}

function selectRechargeType( requestTypeFilterSelected, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (requestTypeFilter != requestTypeFilterSelected){
		currentPage = 1;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilterSelected + "&requestStatusFilter=" + requestStatusFilter + "&userNameFilter=" + username;
	submitForm.submit();
}

function selectRechargeStatus( requestStatusFilterSelected, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (requestStatusFilter != requestStatusFilterSelected){
		currentPage = 1;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilterSelected + "&userNameFilter=" + username;
	submitForm.submit();
}

function deleteRechargeRequest( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("确定取消请求？")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_removeRequest.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

function receiveRecharge( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("确定已收到转账？点击确定后会将转账金额添加到用户账户余额。")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_receiveRecharge.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

function approveAcBill( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("确定用户上传的承兑汇票合法？")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_approveAcBill.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

function rejectAcBill( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("点此选项后，用户会重新上传承兑汇票。确认？")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_rejectAcBill.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

function receiveAcBill( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("确定收到用户的纸质承兑？")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_receiveAcBill.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

function resendAcBill( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("收到的纸质承兑不合法，需要用户重新邮寄？")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_resendAcBill.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

var currentRequestAmount = 0;
var popRequestId = 0;

function exchangeSuccess(requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter, amount){
	popRequestId = requestId;
	currentRequestAmount = amount;
	document.getElementById("pop_dialog_exchange_id").className = "pop_dialog_frame_show";
	
	/*
	if (!confirm("确定承兑汇票兑换成功？点击确定后会将承兑金额添加到用户账户。")){
		return;
	}
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_exchangeSuccess.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId;
	submitForm.submit();
	*/
}

function exchangeSuccessSubmit( currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("确定承兑汇票兑换成功？点击确定后会将承兑实际兑换金额添加到用户账户。")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var amount = document.getElementById("pop_content_exchange_amount_id").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_exchangeSuccess.action?currentPage=" + currentPage + "&queryType=" + queryType  
		+ "&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + popRequestId
		+ "&actualamount=" + amount + "&userNameFilter=" + username;
	submitForm.submit();
}

function exchangeFailed( requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("承兑汇票兑换失败，需要用户重新邮寄？")){
		return;
	}
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_exchangeFailed.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId + "&userNameFilter=" + username;
	submitForm.submit();
}

function closeExchangeDialog(){
	document.getElementById("pop_dialog_exchange_id").className = "pop_dialog_frame";
}

function checkAmount(){
	var amount = document.getElementById("pop_content_exchange_amount_id").value;
	if (amount == ""){
		isAmountValid = false;
		document.getElementById("pop_content_exchange_amount_tip").className = "pop_content_input_tip";
		return;
	}
	if (isNaN(amount)){
		document.getElementById("pop_content_exchange_amount_id").value = parseFloat(currentRequestAmount).toFixed(2);
		document.getElementById("pop_content_exchange_amount_tip").className = "pop_content_input_tip";
		isAmountValid = false;
		return;
	}
	if (currentRequestAmount < amount){
		alert("实际提款金额不能高于原金额");
		document.getElementById("pop_content_exchange_amount_id").value = parseFloat(currentRequestAmount).toFixed(2);
		isAmountValid = false;
		return;
	}
	if (amount <= 0){
		alert("实际提款金额应大于0");
		document.getElementById("pop_content_exchange_amount_id").value = parseFloat(currentRequestAmount).toFixed(2);
		isAmountValid = false;
		return;
	}
	isAmountValid = true;
	document.getElementById("pop_content_exchange_amount_id").value = parseFloat(amount).toFixed(2);
	document.getElementById("pop_content_exchange_amount_tip").className = "pop_content_input_tip_hidden";
}

function submit_recharge_request_search_form( currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	var username = document.getElementById("username_search_input").value;
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "AdminRechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&userNameFilter=" + username;
	submitForm.submit();
}