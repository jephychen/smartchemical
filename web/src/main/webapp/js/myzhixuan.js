function showGetdeposit(accountSize, currentAmount){
	if (accountSize == 0){
		alert("您尚未添加账户，请在账户管理中添加账户再进行提现操作。");
		return;
	}
	if (currentAmount == 0){
		alert("您的余额为0，暂时不能进行提取。");
		return;
	}
	document.getElementById("pop_dialog_getdeposit").className = "pop_dialog_frame_show";
}

function closeGetdepostDialog(){
	document.getElementById("pop_dialog_getdeposit").className = "pop_dialog_frame";
}

function showTransferdeposit(currentAmount){
	if (currentAmount == 0){
		alert("您的余额为0，暂时不能进行提取。");
		return;
	}
	document.getElementById("pop_dialog_transferdeposit").className = "pop_dialog_frame_show";
}

function closeTransferdepostDialog(){
	document.getElementById("pop_dialog_transferdeposit").className = "pop_dialog_frame";
}

var CONST1 = 1;
var isGetAmountValid = false;
var isTransferAmountValid = false;
var isAccountNoValid = false;
var isAccountNameValid = false;
var isAccountBankValid = false;
var isAddressValid = false;
var isMobileValid = false;
var isSmsVerifyCodeValid = false;

function checkGetAmount(currentAmount){
	var amount = document.getElementById("pop_content_getdeposit_amount_id").value;
	if (amount == ""){
		isGetAmountValid = false;
		document.getElementById("pop_content_getdeposit_amount_tip").className = "pop_content_input_tip";
		return;
	}
	if (isNaN(amount)){
		document.getElementById("pop_content_getdeposit_amount_id").value = parseFloat(currentAmount).toFixed(2);
		document.getElementById("pop_content_getdeposit_amount_tip").className = "pop_content_input_tip";
		isGetAmountValid = false;
		return;
	}
	if (currentAmount < amount){
		alert("提款金额不能高于余额");
		document.getElementById("pop_content_getdeposit_amount_id").value = parseFloat(currentAmount).toFixed(2);
		isGetAmountValid = false;
		return;
	}
	if (amount <= 0){
		alert("提款金额必须大于0");
		document.getElementById("pop_content_getdeposit_amount_id").value = parseFloat(currentAmount).toFixed(2);
		isGetAmountValid = false;
		return;
	}
	isGetAmountValid = true;
	document.getElementById("pop_content_getdeposit_amount_id").value = parseFloat(amount).toFixed(2);
	document.getElementById("pop_content_getdeposit_amount_tip").className = "pop_content_input_tip_hidden";
}

function checkTransferAmountPop(currentAmount){
	var amount = document.getElementById("pop_content_transfer_amount_id").value;
	if (amount == ""){
		isTransferAmountValid = false;
		document.getElementById("pop_content_transfer_amount_tip").className = "pop_content_input_tip";
		return;
	}
	if (isNaN(amount)){
		document.getElementById("pop_content_transfer_amount_id").value = parseFloat(currentAmount).toFixed(2);
		document.getElementById("pop_content_transfer_amount_tip").className = "pop_content_input_tip";
		isTransferAmountValid = false;
		return;
	}
	if (currentAmount < amount){
		alert("提款金额不能高于余额");
		document.getElementById("pop_content_transfer_amount_id").value = parseFloat(currentAmount).toFixed(2);
		isGetAmountValid = false;
		return;
	}
	if (amount <= 0){
		alert("提款金额必须大于0");
		document.getElementById("pop_content_transfer_amount_id").value = parseFloat(currentAmount).toFixed(2);
		isGetAmountValid = false;
		return;
	}
	isTransferAmountValid = true;
	document.getElementById("pop_content_transfer_amount_id").value = parseFloat(amount).toFixed(2);
	document.getElementById("pop_content_transfer_amount_tip").className = "pop_content_input_tip_hidden";
}

function checkAccountNoPop(){
	var str = document.getElementById("pop_content_transfer_acountno_id").value;
	if (isValidAccountNoPop(str)){
		document.getElementById("pop_content_transfer_acountno_tip").className = "pop_content_input_tip_hidden";
		isAccountNoValid = true;
	}
	else {
		document.getElementById("pop_content_transfer_acountno_tip").className = "pop_content_input_tip";
		isAccountNoValid = false;
	}
}

function isValidAccountNoPop(str){
	if (str.length < 10 || str.length > 25){
		return false;
	}
	var reg = /^[0-9]{10,25}/;
	return reg.test(str);
}

function checkAccountNamePop(){
	var str = document.getElementById("pop_content_transfer_acountname_id").value;
	if (str.length < 2 || str.length > 50){
		document.getElementById("pop_content_transfer_acountname_tip").className = "pop_content_input_tip";
		isAccountNameValid = false;
	}
	var reg = /^([a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]|[._]){2,50}$/;
	if (reg.test(str)){
		document.getElementById("pop_content_transfer_acountname_tip").className = "pop_content_input_tip_hidden";
		isAccountNameValid = true;
	}
	else {
		document.getElementById("pop_content_transfer_acountname_tip").className = "pop_content_input_tip";
		isAccountNameValid = false;
	}
}

function checkAccountBankPop(){
	var str = document.getElementById("pop_content_transfer_acountbank_id").value;
	if (isValidAccountBankPop(str)){
		document.getElementById("pop_content_transfer_acountbank_tip").className = "pop_content_input_tip_hidden";
		isAccountBankValid = true;
	}
	else {
		document.getElementById("pop_content_transfer_acountbank_tip").className = "pop_content_input_tip";
		isAccountBankValid = false;
	}
}

function isValidAccountBankPop(str){
	if (str.length < 4 || str.length > 15){
		return false;
	}
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]{2,15}/;
	return reg.test(str);
}

function checkAddressPop(){
	var str = document.getElementById("pop_content_transfer_address_id").value;
	if (isValidAddressPop(str)){
		document.getElementById("pop_content_transfer_address_tip").className = "pop_content_input_tip_hidden";
		isAddressValid = true;
	}
	else {
		document.getElementById("pop_content_transfer_address_tip").className = "pop_content_input_tip";
		isAddressValid = false;
	}
}

function isValidAddressPop(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkMobilePop(){
	var mobile = document.getElementById("pop_content_transfer_mobile_id").value; 
	if (isValidMobilePop(mobile)){
		document.getElementById("pop_content_transfer_mobile_tip").className = "pop_content_input_tip_hidden";
		isMobileValid = true;
	}
	else {
		document.getElementById("pop_content_transfer_mobile_tip").className = "pop_content_input_tip";
		isMobileValid = false;
	}
}

function isValidMobilePop(str){
	if (str.length != 11){
		return false;
	}
	var reg = /^1[0-9]{10}/;
	return reg.test(str);
}

function saveGetdepositRequest(){
	checkGetAmount();
	if (!isGetAmountValid){
		return;
	}
	if (!isSmsVerifyCodeValid){
		document.getElementById("pop_content_getdeposit_verifycode_tip1").className = "pop_content_input_tip";
		document.getElementById("pop_content_getdeposit_verifycode_tip2").className = "pop_content_input_tip_hidden";
		return;
	}
	var submitForm = document.getElementById("pop_get_deposit_form");
	submitForm.method = "post";
	submitForm.action = "DepositRequest_addGetRequest.action";
	submitForm.submit();
}

function saveTransferRequest(){
	checkTransferAmountPop();checkAccountNoPop();checkAccountNamePop();checkAccountBankPop();checkAddressPop();checkMobilePop();
	if (!isTransferAmountValid || !isAccountNoValid || !isAccountNameValid || !isAccountBankValid || !isAddressValid || !isMobileValid){
		return;
	}
	var submitForm = document.getElementById("pop_transfer_deposit_form");
	submitForm.method = "post";
	submitForm.action = "DepositRequest_addTransferRequest.action";
	submitForm.submit();
}

function transferAccountSelect(){
	var accountId = document.getElementById("pop_content_transfer_acountno_select_id").value; 
	if (accountId == 0){
		document.getElementById("pop_content_transfer_acountno_id").readOnly = false;
		document.getElementById("pop_content_transfer_acountname_id").readOnly = false;
		document.getElementById("pop_content_transfer_acountbank_id").readOnly = false;
		document.getElementById("pop_content_transfer_address_id").readOnly = false;
		document.getElementById("pop_content_transfer_mobile_id").readOnly = false;
		document.getElementById("pop_content_transfer_acountno_id").value = "";
		document.getElementById("pop_content_transfer_acountname_id").value = "";
		document.getElementById("pop_content_transfer_acountbank_id").value = "";
		document.getElementById("pop_content_transfer_address_id").value = "";
		document.getElementById("pop_content_transfer_mobile_id").value = "";
		return;
	}
	getAccountAjax(accountId);
}

var xhr_account; 

function createAccountXHR(){    
    if( window.ActiveXObject ){  
        xhr_account = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_account = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function getAccountAjax(accountId){ 
	if (xhr_account == undefined){
		if(createAccountXHR() == 0){
			return true; 
		} 
	}
	xhr_account.onreadystatechange = getAccountHandler;
	xhr_account.open("post", "CommonAjax_getAccountById.action?accountId=" + accountId);
	xhr_account.send();
}

function getAccountHandler(){    
    if( xhr_account.readyState == 4 ){     
        if( xhr_account.status == 200 ){ 
            fillAccountItem(xhr_account.responseText);
        }
    }  
}

function fillAccountItem(accounts){
	if (accounts == null || accounts.length == 0){
		return;
	}
	var acnts = new Array();
	acnts = accounts.split(",");
	document.getElementById("pop_content_transfer_acountno_id").value = acnts[0];
	document.getElementById("pop_content_transfer_acountno_id").readOnly = true;
	document.getElementById("pop_content_transfer_acountname_id").value = acnts[1];
	document.getElementById("pop_content_transfer_acountname_id").readOnly = true;
	document.getElementById("pop_content_transfer_acountbank_id").value = acnts[2];
	document.getElementById("pop_content_transfer_acountbank_id").readOnly = true;
	document.getElementById("pop_content_transfer_address_id").value = acnts[3];
	document.getElementById("pop_content_transfer_address_id").readOnly = true;
	document.getElementById("pop_content_transfer_mobile_id").value = acnts[4];
	document.getElementById("pop_content_transfer_mobile_id").readOnly = true;
}

function sendGetdepositVerifyCode(mobile){
	checkGetAmount();
	if (!isGetAmountValid){
		return;
	}
	settime(document.getElementById("pop_content_getdeposit_verifycode_button"));
	document.getElementById("pop_content_getdeposit_verifycode_tip2").className = "pop_content_input_tip_green";
	var account = document.getElementById("pop_content_getdeposit_accountno_id").value;
	var amount = document.getElementById("pop_content_getdeposit_amount_id").value;
	sendVerifyAjax(mobile, account, amount);
}

function sendTransferdepositVerifyCode(mobile){
	checkTransferAmountPop();checkAccountNoPop();
	if (!isTransferAmountValid || !isAccountNoValid){
		return;
	}
	settime(document.getElementById("pop_content_transfer_verifycode_button"));
	document.getElementById("pop_content_transfer_verifycode_tip2").className = "pop_content_input_tip_green";
	var accountNo = document.getElementById("pop_content_transfer_acountno_id").value;
	var amount = document.getElementById("pop_content_transfer_amount_id").value;
	sendTransferVerifyAjax(mobile, accountNo, amount);
}

var countdown=60; 
function settime(val) { 
	if (countdown == 0) {
		val.disabled = false;
		val.value="获取验证码"; 
		countdown = 60;
		return;
	} 
	else {
		val.disabled = true;
		val.value="重新发送(" + countdown + ")"; 
		countdown--; 
	} 
	setTimeout(function() { settime(val) },1000) 
}

var xhr_sendverifycode; 

function createSendVerifyXHR(){    
    if( window.ActiveXObject ){  
        xhr_sendverifycode = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_sendverifycode = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function sendVerifyAjax(mobile, account, amount){ 
	if (xhr_sendverifycode == undefined){
		if(createSendVerifyXHR() == 0){
			return true; 
		} 
	}
	xhr_sendverifycode.onreadystatechange = checkSendVerifyHandler;
	xhr_sendverifycode.open("post", "CommonAjax_sendDepositVerifyCode.action?mobileNo=" + mobile + "&account=" + account + "&amount=" + amount);
	xhr_sendverifycode.send();
}

function sendTransferVerifyAjax(mobile, accountNo, amount){ 
	if (xhr_sendverifycode == undefined){
		if(createSendVerifyXHR() == 0){
			return true; 
		} 
	}
	xhr_sendverifycode.onreadystatechange = checkSendVerifyHandler;
	xhr_sendverifycode.open("post", "CommonAjax_sendDepositTransferVerifyCode.action?mobileNo=" + mobile + "&accountNo=" + accountNo + "&amount=" + amount);
	xhr_sendverifycode.send();
}

function checkSendVerifyHandler(){    
    if( xhr_sendverifycode.readyState == 4 ){     
        if( xhr_sendverifycode.status == 200 ){  
            if (xhr_sendverifycode.responseText == "success"){
				
			}
        }
    }  
} 

var xhr_smsverifycode; 

function createSmsverifycodeXHR(){    
    if( window.ActiveXObject ){  
        xhr_smsverifycode = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_smsverifycode = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function checkSmsverifycodeAjax(mobile){ 
	if (xhr_smsverifycode == undefined){
		if(createSmsverifycodeXHR() == 0){
			return true; 
		} 
	}
	var code = document.getElementById("pop_content_getdeposit_verifycode_id").value;
	xhr_smsverifycode.onreadystatechange = checkSmsverifycodeHandler;
	xhr_smsverifycode.open("post", "CommonAjax_checkSmsVerifyCode.action?verifycode=" + code + "&mobileNo=" + mobile);
	xhr_smsverifycode.send();
}

function checkTransferSmsverifycodeAjax(mobile){ 
	if (xhr_smsverifycode == undefined){
		if(createSmsverifycodeXHR() == 0){
			return true; 
		} 
	}
	var code = document.getElementById("pop_content_transfer_verifycode_id").value;
	xhr_smsverifycode.onreadystatechange = checkTransferSmsverifycodeHandler;
	xhr_smsverifycode.open("post", "CommonAjax_checkSmsVerifyCode.action?verifycode=" + code + "&mobileNo=" + mobile);
	xhr_smsverifycode.send();
}

function checkSmsverifycodeHandler(){    
    if( xhr_smsverifycode.readyState == 4 ){     
        if( xhr_smsverifycode.status == 200 ){  
			document.getElementById("pop_content_getdeposit_verifycode_tip2").className = "pop_content_input_tip_hidden";
            if (xhr_smsverifycode.responseText == "illegal"){
				isSmsVerifyCodeValid = false;
				document.getElementById("pop_content_getdeposit_verifycode_tip1").className = "pop_content_input_tip";
			}else if (xhr_smsverifycode.responseText == "legal"){
				isSmsVerifyCodeValid = true;
				document.getElementById("pop_content_getdeposit_verifycode_tip1").className = "pop_content_input_tip_hidden";
			}else if (xhr_smsverifycode.responseText == "expired"){
				isSmsVerifyCodeValid = false;
				document.getElementById("pop_content_getdeposit_verifycode_tip1").className = "pop_content_input_tip";
			}else {
				isSmsVerifyCodeValid = false;
			}
        }
    }  
}   

function checkTransferSmsverifycodeHandler(){    
    if( xhr_smsverifycode.readyState == 4 ){     
        if( xhr_smsverifycode.status == 200 ){  
			document.getElementById("pop_content_transfer_verifycode_tip2").className = "pop_content_input_tip_hidden";
            if (xhr_smsverifycode.responseText == "illegal"){
				isSmsVerifyCodeValid = false;
				document.getElementById("pop_content_transfer_verifycode_tip1").className = "pop_content_input_tip";
			}else if (xhr_smsverifycode.responseText == "legal"){
				isSmsVerifyCodeValid = true;
				document.getElementById("pop_content_transfer_verifycode_tip1").className = "pop_content_input_tip_hidden";
			}else if (xhr_smsverifycode.responseText == "expired"){
				isSmsVerifyCodeValid = false;
				document.getElementById("pop_content_transfer_verifycode_tip1").className = "pop_content_input_tip";
			}else {
				isSmsVerifyCodeValid = false;
			}
        }
    }  
} 