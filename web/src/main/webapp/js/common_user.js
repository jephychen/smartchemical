var isPasswordValid = false;
var isNewPasswordValid = false;

function checkPassword(str, tipwidget){
	if (isValidPassword(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isPasswordValid = true;
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isPasswordValid = false;
	}
}

function checkNewPassword(str, tipwidget, tipwidget1){
	if (isValidPassword(str)){
		var password1 = document.getElementById("user_edit_newpassword1").value;
		var password2 = document.getElementById("user_edit_newpassword2").value;
		if (password1 == "" || password2 == ""){
			return;
		}
		if (password1 != password2){
			document.getElementById(tipwidget).className = "pop_content_input_tip1";
			document.getElementById(tipwidget).innerHTML = "两次密码应该一致";
			document.getElementById(tipwidget1).className = "pop_content_input_tip1_hidden";
		}
		else{
			document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
			document.getElementById(tipwidget1).className = "pop_content_input_tip1_hidden";
			document.getElementById(tipwidget).innerHTML = "密码应为6-20位";
			isNewPasswordValid = true;
		}
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isNewPasswordValid = false;
	}
}

function isValidPassword(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /.*/;
	return reg.test(str);
}

function savePassword(){
	var passwordOld = document.getElementById("user_edit_password").value;
	var passwordNew = document.getElementById("user_edit_newpassword1").value;
	checkPassword(passwordOld, "pop_content_password");checkNewPassword(passwordNew,'pop_content_newpassword2', 'pop_content_newpassword1');
	if (!isPasswordValid || !isNewPasswordValid){
		return;
	}
	var submitForm = document.getElementById("user_edit_form");
	
	var param0 = document.createElement("input");
	param0.setAttribute("name", "password");
	param0.setAttribute("value", passwordOld);
	param0.setAttribute("type", "hidden");
	submitForm.appendChild(param0);
	
	var param1 = document.createElement("input");
	param1.setAttribute("name", "newPassword");
	param1.setAttribute("value", passwordNew);
	param1.setAttribute("type", "hidden");
	submitForm.appendChild(param1);
	
	submitForm.method = "post";
	submitForm.action = "UserEdit_changePasswordSubmit.action";
	submitForm.submit();
}

var isCompanyValid = false;
var isAccountBankValid = false;
var isAccountNoValid = false;
var isAddressValid = false;
var isMobileValid = false;

function checkCompany(){
	var newCompany = document.getElementById("account_edit_companyname").value;
	if (newCompany.length < 5 || newCompany.length > 50){
		document.getElementById("pop_content_account_companyname").className = "pop_content_input_tip1";
		isCompanyValid = false;
	}
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,50}$/;
	if (reg.test(newCompany)){
		document.getElementById("pop_content_account_companyname").className = "pop_content_input_tip1_hidden";
		isCompanyValid = true;
	}
	else {
		document.getElementById("pop_content_account_companyname").className = "pop_content_input_tip1";
		isCompanyValid = false;
	}
}

function checkAccountBank(){
	var str = document.getElementById("account_edit_accountbank").value;
	if (isValidAccountBank(str)){
		document.getElementById("pop_content_account_accountbank").className = "pop_content_input_tip1_hidden";
		isAccountBankValid = true;
	}
	else {
		document.getElementById("pop_content_account_accountbank").className = "pop_content_input_tip1";
		isAccountBankValid = false;
	}
}

function isValidAccountBank(str){
	if (str.length < 4 || str.length > 15){
		return false;
	}
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]{2,15}/;
	return reg.test(str);
}

function checkAccountNo(){
	var username = document.getElementById("account_edit_accountno").value;
	if (isValidAccountNo(username)){
		document.getElementById("pop_content_account_accountno").className = "pop_content_input_tip1_hidden";
		isAccountNoValid = true;
	}
	else {
		document.getElementById("pop_content_account_accountno").className = "pop_content_input_tip1";
		isAccountNoValid = false;
	}
}

function isValidAccountNo(str){
	if (str.length < 10 || str.length > 25){
		return false;
	}
	var reg = /^[0-9]{12,25}/;
	return reg.test(str);
}

function checkAddress(){
	var str = document.getElementById("account_edit_bankaddress").value;
	if (isValidAddress(str)){
		document.getElementById("pop_content_account_bankaddress").className = "pop_content_input_tip1_hidden";
		isAddressValid = true;
	}
	else {
		document.getElementById("pop_content_account_bankaddress").className = "pop_content_input_tip1";
		isAddressValid = false;
	}
}

function isValidAddress(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkMobile(){
	var mobile = document.getElementById("account_edit_mobile").value; 
	if (isValidMobile(mobile)){
		document.getElementById("pop_content_account_mobile").className = "pop_content_input_tip1_hidden";
		isMobileValid = true;
	}
	else {
		document.getElementById("pop_content_account_mobile").className = "pop_content_input_tip1";
		isMobileValid = false;
	}
}

function isValidMobile(str){
	if (str.length != 11){
		return false;
	}
	var reg = /^1[0-9]{10}/;
	return reg.test(str);
}

function saveBankAccount(edit){
	checkCompany();checkAccountNo();checkAccountBank();checkAddress();checkMobile();
	if (!isCompanyValid || !isAccountBankValid || !isAccountNoValid || !isAddressValid || !isMobileValid){
		return;
	}
	
	var companyName = document.getElementById("account_edit_companyname").value;
	var accountNo = document.getElementById("account_edit_accountno").value;
	var accountBank = document.getElementById("account_edit_accountbank").value;
	var bankAddress = document.getElementById("account_edit_bankaddress").value;
	var mobile = document.getElementById("account_edit_mobile").value;
	
	var submitForm = document.getElementById("bankaccount_edit_form");
	
	var param0 = document.createElement("input");
	param0.setAttribute("name", "companyName");
	param0.setAttribute("value", companyName);
	param0.setAttribute("type", "hidden");
	submitForm.appendChild(param0);
	
	var param1 = document.createElement("input");
	param1.setAttribute("name", "accountNo");
	param1.setAttribute("value", accountNo);
	param1.setAttribute("type", "hidden");
	submitForm.appendChild(param1);
	
	var param2 = document.createElement("input");
	param2.setAttribute("name", "accountBank");
	param2.setAttribute("value", accountBank);
	param2.setAttribute("type", "hidden");
	submitForm.appendChild(param2);
	
	var param3 = document.createElement("input");
	param3.setAttribute("name", "bankAddress");
	param3.setAttribute("value", bankAddress);
	param3.setAttribute("type", "hidden");
	submitForm.appendChild(param3);
	
	var param4 = document.createElement("input");
	param4.setAttribute("name", "mobile");
	param4.setAttribute("value", mobile);
	param4.setAttribute("type", "hidden");
	submitForm.appendChild(param4);
	
	var param5 = document.createElement("input");
	param5.setAttribute("name", "edit");
	param5.setAttribute("value", edit);
	param5.setAttribute("type", "hidden");
	submitForm.appendChild(param5);
	
	submitForm.method = "post";
	submitForm.action = "BankAccountEdit_editBankAccountSubmit.action";
	submitForm.submit();
}

function selectAccount(accountId, bankAccountId, currentPage, queryType, lastMonth){
	if (bankAccountId != accountId){
		currentPage = 1;
	}
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "DepositRequestManagement_allRequest.action?bankAccountId=" + accountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastMonth;
	submitForm.submit();
}

function selectLastmonth(lastmonthSelected, bankAccountId, currentPage, queryType, lastMonth){
	if (lastMonth != lastmonthSelected){
		currentPage = 1;
	}
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "DepositRequestManagement_allRequest.action?bankAccountId=" + bankAccountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastmonthSelected;
	submitForm.submit();
}

function deleteRequest(requestId, accountId, currentPage, queryType, lastMonth){
	if (!confirm("确定取消请求？")){
		return;
	}
	var submitForm = document.getElementById("common_deposit_request_formid");
	submitForm.method = "post";
	submitForm.action = "DepositRequestManagement_removeRequest.action?bankAccountId=" + accountId + "&currentPage=" 
		+ currentPage + "&queryType=" + queryType + "&lastMonth=" + lastMonth + "&requestId=" + requestId;
	submitForm.submit();
}

var isRechargeAmountValid = false;

function checkAmount(){
	var amount = document.getElementById("user_recharge_amount").value;
	if (amount == ""){
		isRechargeAmountValid = false;
		document.getElementById("pop_user_recharge_amount").className = "pop_content_input_tip1";
		return;
	}
	if (isNaN(amount)){
		document.getElementById("user_recharge_amount").value = parseFloat(10000).toFixed(2);
		document.getElementById("pop_user_recharge_amount").className = "pop_content_input_tip1";
		isRechargeAmountValid = false;
		return;
	}
	if (amount <= 0){
		document.getElementById("user_recharge_amount").value = parseFloat(10000).toFixed(2);
		document.getElementById("pop_user_recharge_amount").className = "pop_content_input_tip1";
		isRechargeAmountValid = false;
		return;
	}
	isRechargeAmountValid = true;
	document.getElementById("user_recharge_amount").value = parseFloat(amount).toFixed(2);
	document.getElementById("pop_user_recharge_amount").className = "pop_content_input_tip1_hidden";
}

function goRecharge(){
	checkAmount();
	if (!isRechargeAmountValid){
		return;
	}
	var amount = document.getElementById("user_recharge_amount").value;
	var submitForm = document.getElementById("recharge_form");
	var isAcceptanceBill = document.getElementById("user_edit_recharge1").checked;
	var isTransfer = document.getElementById("user_edit_recharge2").checked;
	if (isAcceptanceBill){
		submitForm.action = "RechargeRequestManagement_newRequestSubmit.action?rechargetype=1&amount=" + amount;
	}
	else if (isTransfer){
		submitForm.action = "RechargeRequestManagement_newRequestSubmit.action?rechargetype=2&amount=" + amount;;
	}
	submitForm.method = "post";
	submitForm.submit();
}

function selectRechargeLastmonth(lastmonthSelected, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (lastMonth != lastmonthSelected){
		currentPage = 1;
	}
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "RechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastmonthSelected + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter;
	submitForm.submit();
}

function selectRechargeType(requestTypeFilterSelected, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (requestTypeFilter != requestTypeFilterSelected){
		currentPage = 1;
	}
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "RechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilterSelected + "&requestStatusFilter=" + requestStatusFilter;
	submitForm.submit();
}

function selectRechargeStatus(requestStatusFilterSelected, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (requestStatusFilter != requestStatusFilterSelected){
		currentPage = 1;
	}
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "RechargeRequestManagement_allRequests.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilterSelected;
	submitForm.submit();
}

function deleteRechargeRequest(requestId, currentPage, queryType, lastMonth, requestTypeFilter, requestStatusFilter){
	if (!confirm("确定取消请求？")){
		return;
	}
	var submitForm = document.getElementById("products_search");
	submitForm.method = "post";
	submitForm.action = "RechargeRequestManagement_removeRequest.action?currentPage=" + currentPage + "&queryType=" + queryType + 
		"&lastMonth=" + lastMonth + "&requestTypeFilter=" + requestTypeFilter + "&requestStatusFilter=" + requestStatusFilter + "&requestId=" + requestId;
	submitForm.submit();
}