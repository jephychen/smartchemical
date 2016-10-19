var isUserNameValid = false;
var isCompanyNameValid = false;
var isEmailValid = false;
var isMobileValid = false;
var isSmsVerifyCodeValid = false;
var isPasswordValid = false;
var isPasswordDupValid = false;

function bodyOnLoad(){
	checkMobile();
	checkSmsverifycodeAjax();
}

function tab_selected(type){
	if (type == "1"){
		document.getElementById("common_user_tab").className = "register_pad_type_item_selected";
		document.getElementById("supplier_user_tab").className = "register_pad_type_item";
		document.getElementById("merchant_user_tab").className = "register_pad_type_item";
		
		document.getElementById("register_pad_form_common").className = "register_pad_form_item";
		document.getElementById("register_pad_form_supplier").className = "register_pad_form_item_hidden";
		document.getElementById("register_pad_form_merchant").className = "register_pad_form_item_hidden";
	}else if (type == "2"){
		document.getElementById("common_user_tab").className = "register_pad_type_item";
		document.getElementById("supplier_user_tab").className = "register_pad_type_item_selected";
		document.getElementById("merchant_user_tab").className = "register_pad_type_item";
		
		document.getElementById("register_pad_form_common").className = "register_pad_form_item_hidden";
		document.getElementById("register_pad_form_supplier").className = "register_pad_form_item";
		document.getElementById("register_pad_form_merchant").className = "register_pad_form_item_hidden";
	}else if (type == "3"){
		document.getElementById("common_user_tab").className = "register_pad_type_item";
		document.getElementById("supplier_user_tab").className = "register_pad_type_item";
		document.getElementById("merchant_user_tab").className = "register_pad_type_item_selected";
		
		document.getElementById("register_pad_form_common").className = "register_pad_form_item_hidden";
		document.getElementById("register_pad_form_supplier").className = "register_pad_form_item_hidden";
		document.getElementById("register_pad_form_merchant").className = "register_pad_form_item";
	}
}
function submit_register_common(){
	checkUserName();checkCompanyName();checkEmail();checkMobile();checkPassword();checkPasswordDup();
	if (!isUserNameValid || !isCompanyNameValid || !isEmailValid || !isMobileValid || !isSmsVerifyCodeValid || !isPasswordValid || !isPasswordDupValid){
		return;
	}
	if (!document.getElementById("is_consent_agreement").checked){
		alert("请阅读并同意《智选化学用户协议》");
		return;
	}
	document.getElementById("mobile_field").disabled = false;
	var registerForm = document.getElementById("register_form");
	registerForm.method="post";
	registerForm.action="Register.action?userType=2";
	registerForm.submit();
}
function submit_register_supplier(){
	checkUserName();checkCompanyName();checkEmail();checkMobile();checkPassword();checkPasswordDup();
	if (!isUserNameValid || !isCompanyNameValid || !isEmailValid || !isMobileValid || !isSmsVerifyCodeValid || !isPasswordValid || !isPasswordDupValid){
		return;
	}
	if (!document.getElementById("is_consent_agreement").checked){
		alert("请阅读并同意《智选化学用户协议》");
		return;
	}
	document.getElementById("mobile_field").disabled = false;
	var registerForm = document.getElementById("register_form");
	registerForm.method="post";
	registerForm.action="Register.action?userType=supplier";
	registerForm.submit();
}
function submit_register_merchant(){
	checkUserName();checkCompanyName();checkEmail();checkMobile();checkPassword();checkPasswordDup();
	if (!isUserNameValid || !isCompanyNameValid || !isEmailValid || !isMobileValid || !isSmsVerifyCodeValid || !isPasswordValid || !isPasswordDupValid){
		return;
	}
	if (!document.getElementById("is_consent_agreement").checked){
		alert("请阅读并同意《智选化学用户协议》");
		return;
	}
	document.getElementById("mobile_field").disabled = false;
	var registerForm = document.getElementById("register_form");
	registerForm.method="post";
	registerForm.action="Register.action?userType=merchant";
	registerForm.submit();
}

function checkUserName(){
	var str = document.getElementById("username_field").value;
	if (isValidUserName(str)){
		document.getElementById("username_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("username_field_result_legal").className = "register_pad_form_item_right_legal";
		document.getElementById("username_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
		document.getElementById("username_field_result_illegal_existed").className = "register_pad_form_item_right_illegal_hidden";
		isUserNameValid = true;
		checkUserNameAjax(str);
	}else{
		document.getElementById("username_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("username_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("username_field_result_illegal").className = "register_pad_form_item_right_illegal";
		document.getElementById("username_field_result_illegal_existed").className = "register_pad_form_item_right_illegal_hidden";
		isUserNameValid = false;
	}
}
function checkCompanyName(){
	var str = document.getElementById("companyname_field").value;
	if (isValidCompanyName(str)){
		document.getElementById("companyname_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("companyname_field_result_legal").className = "register_pad_form_item_right_legal";
		document.getElementById("companyname_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
		isCompanyNameValid = true;
	}else{
		document.getElementById("companyname_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("companyname_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("companyname_field_result_illegal").className = "register_pad_form_item_right_illegal";
		isCompanyNameValid = false;
	}
}
function checkEmail(){
	var str = document.getElementById("email_field").value;
	if (isValidEmail(str)){
		document.getElementById("email_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("email_field_result_legal").className = "register_pad_form_item_right_legal";
		document.getElementById("email_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
		document.getElementById("email_field_result_illegal_existed").className = "register_pad_form_item_right_illegal_hidden";	
		isEmailValid = true;
		checkEmailAjax(str);
	}else{
		document.getElementById("email_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("email_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("email_field_result_illegal").className = "register_pad_form_item_right_illegal";
		document.getElementById("email_field_result_illegal_existed").className = "register_pad_form_item_right_illegal_hidden";
		isEmailValid = false;
	}
}
function checkMobile(){
	var str = document.getElementById("mobile_field").value;
	if (isValidMobile(str)){
		document.getElementById("mobile_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("mobile_field_result_legal").className = "register_pad_form_item_right_legal";
		document.getElementById("mobile_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
		document.getElementById("mobile_field_result_illegal_existed").className = "register_pad_form_item_right_illegal_hidden";
		isMobileValid = true;
		checkMobileAjax(str);
	}else{
		document.getElementById("mobile_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("mobile_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("mobile_field_result_illegal").className = "register_pad_form_item_right_illegal";
		document.getElementById("mobile_field_result_illegal_existed").className = "register_pad_form_item_right_illegal_hidden";
		isMobileValid = false;
	}
}
function checkPassword(){
	var str = document.getElementById("password_field").value;
	if (isValidPassword(str)){
		document.getElementById("password_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("password_field_result_legal").className = "register_pad_form_item_right_legal";
		document.getElementById("password_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
		isPasswordValid = true;
	}else{
		document.getElementById("password_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("password_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("password_field_result_illegal").className = "register_pad_form_item_right_illegal";
		isPasswordValid = false;
	}
}
function checkPasswordDup(){
	var passwordDup = document.getElementById("passworddup_field").value;
	if (!isValidPassword(passwordDup)){
		document.getElementById("passworddup_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("passworddup_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("passworddup_field_result_illegal").className = "register_pad_form_item_right_illegal";
		document.getElementById("passworddup_field_result_illegal_inconsist").className = "register_pad_form_item_right_illegal_hidden";
		isPasswordDupValid = false;
	}else{
		var password1 = document.getElementById("password_field").value;
		if (passwordDup != password1){
			document.getElementById("passworddup_field_result").className = "register_pad_form_item_right_hidden";
			document.getElementById("passworddup_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
			document.getElementById("passworddup_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
			document.getElementById("passworddup_field_result_illegal_inconsist").className = "register_pad_form_item_right_illegal";
			isPasswordDupValid = false;
		}else{
			document.getElementById("passworddup_field_result").className = "register_pad_form_item_right_hidden";
			document.getElementById("passworddup_field_result_legal").className = "register_pad_form_item_right_legal";
			document.getElementById("passworddup_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
			document.getElementById("passworddup_field_result_illegal_inconsist").className = "register_pad_form_item_right_illegal_hidden";
			isPasswordDupValid = true;
		}
	}
}

function isValidUserName(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]|[._]){4,19}$/;
	return reg.test(str);
}
function isValidCompanyName(str){
	if (str.length < 6 || str.length > 30){
		return false;
	}
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]{6,30}/;
	return reg.test(str);
}
function isValidMobile(str){
	if (str.length != 11){
		return false;
	}
	var reg = /^1[0-9]{10}/;
	return reg.test(str);
}
function isValidEmail(str){
	var reg = /[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z0-9]{1,20}(\.[a-zA-Z0-9]{1,20}){0,1}$/;
	return reg.test(str);
}
function isValidPassword(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /.*/;
	return reg.test(str);
}

var xhr_username; 

function createUsernameXHR(){    
    if( window.ActiveXObject ){  
        xhr_username = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_username = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function checkUserNameAjax(username){ 
	if (xhr_username == undefined){
		if(createUsernameXHR() == 0){
			return true; 
		} 
	}
	xhr_username.onreadystatechange = checkUserNameHandler;
	xhr_username.open("post", "RegisterAjax_checkUserName.action?keyWord=" + username);
	xhr_username.send();
}

function checkUserNameHandler(){    
    if( xhr_username.readyState == 4 ){     
        if( xhr_username.status == 200 ){  
            if (xhr_username.responseText == "existed"){
				isUserNameValid = false;
				document.getElementById("username_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("username_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
				document.getElementById("username_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("username_field_result_illegal_existed").className = "register_pad_form_item_right_illegal";
			}
        }
    }  
}

var xhr_email; 

function createEmailXHR(){    
    if( window.ActiveXObject ){  
        xhr_email = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_email = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function checkEmailAjax(email){ 
	if (xhr_email == undefined){
		if(createEmailXHR() == 0){
			return true; 
		} 
	}
	xhr_email.onreadystatechange = checkEmailHandler;
	xhr_email.open("post", "RegisterAjax_checkEmail.action?keyWord=" + email);
	xhr_email.send();
}

function checkEmailHandler(){    
    if( xhr_email.readyState == 4 ){     
        if( xhr_email.status == 200 ){  
            if (xhr_email.responseText == "existed"){
				isEmailValid = false;
				document.getElementById("email_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("email_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
				document.getElementById("email_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("email_field_result_illegal_existed").className = "register_pad_form_item_right_illegal";	
			}
        }
    }  
}

var xhr_mobile; 

function createMobileXHR(){    
    if( window.ActiveXObject ){  
        xhr_mobile = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_mobile = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function checkMobileAjax(mobile){ 
	if (xhr_mobile == undefined){
		if(createMobileXHR() == 0){
			return true; 
		} 
	}
	xhr_mobile.onreadystatechange = checkMobileHandler;
	xhr_mobile.open("post", "RegisterAjax_checkMobile.action?keyWord=" + mobile);
	xhr_mobile.send();
}

function checkMobileHandler(){    
    if( xhr_mobile.readyState == 4 ){     
        if( xhr_mobile.status == 200 ){  
            if (xhr_mobile.responseText == "existed"){
				isMobileValid = false;
				document.getElementById("mobile_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("mobile_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
				document.getElementById("mobile_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("mobile_field_result_illegal_existed").className = "register_pad_form_item_right_illegal";
			}
        }
    }  
}            

function getVerifyCode(){
	if (!isMobileValid){
		document.getElementById("smsverifycode_field_result").className = "register_pad_form_item_right_hidden";
		document.getElementById("smsverifycode_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
		document.getElementById("smsverifycode_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
		document.getElementById("smsverifycode_field_result_illegal_mobile").className = "register_pad_form_item_right_illegal";
		document.getElementById("smsverifycode_field_result_illegal_expired").className = "register_pad_form_item_right_illegal_hidden";
		return;
	}
	document.getElementById("smsverifycode_field_result").className = "register_pad_form_item_right";
	document.getElementById("smsverifycode_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
	document.getElementById("smsverifycode_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
	document.getElementById("smsverifycode_field_result_illegal_mobile").className = "register_pad_form_item_right_illegal_hidden";
	document.getElementById("smsverifycode_field_result_illegal_expired").className = "register_pad_form_item_right_illegal_hidden";
	
	document.getElementById("mobile_field").disabled = true;
	document.getElementById("register_smsVerifyCode_field").focus();
	
	settime(document.getElementById("register_pad_form_item_getverifycode_button"));
	var mobile = document.getElementById("mobile_field").value;
	sendVerifyAjax(mobile);
}

var countdown=60; 
function settime(val) { 
	if (countdown == 0) {
		val.disabled = false;
		val.value="获取验证码"; 
		countdown = 60;
		document.getElementById("mobile_field").disabled = false;
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

function sendVerifyAjax(mobile){ 
	if (xhr_sendverifycode == undefined){
		if(createSendVerifyXHR() == 0){
			return true; 
		} 
	}
	xhr_sendverifycode.onreadystatechange = checkSendVerifyHandler;
	xhr_sendverifycode.open("post", "RegisterAjax_sendVerifyCode.action?keyWord=" + mobile);
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

function checkSmsverifycodeAjax(){ 
	if (xhr_smsverifycode == undefined){
		if(createSmsverifycodeXHR() == 0){
			return true; 
		} 
	}
	var code = document.getElementById("register_smsVerifyCode_field").value;
	var mobile = document.getElementById("mobile_field").value;
	xhr_smsverifycode.onreadystatechange = checkSmsverifycodeHandler;
	xhr_smsverifycode.open("post", "RegisterAjax_checkSmsVerifyCode.action?keyWord=" + code + "&mobile=" + mobile);
	xhr_smsverifycode.send();
}

function checkSmsverifycodeHandler(){    
    if( xhr_smsverifycode.readyState == 4 ){     
        if( xhr_smsverifycode.status == 200 ){  
            if (xhr_smsverifycode.responseText == "illegal"){
				isSmsVerifyCodeValid = false;
				document.getElementById("smsverifycode_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("smsverifycode_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
				document.getElementById("smsverifycode_field_result_illegal").className = "register_pad_form_item_right_illegal";
				document.getElementById("smsverifycode_field_result_illegal_mobile").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_expired").className = "register_pad_form_item_right_illegal_hidden";
			}else if (xhr_smsverifycode.responseText == "legal"){
				isSmsVerifyCodeValid = true;
				document.getElementById("smsverifycode_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("smsverifycode_field_result_legal").className = "register_pad_form_item_right_legal";
				document.getElementById("smsverifycode_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_mobile").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_expired").className = "register_pad_form_item_right_illegal_hidden";
			}else if (xhr_smsverifycode.responseText == "expired"){
				isSmsVerifyCodeValid = true;
				document.getElementById("smsverifycode_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("smsverifycode_field_result_legal").className = "register_pad_form_item_right_legal_hidden";
				document.getElementById("smsverifycode_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_mobile").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_expired").className = "register_pad_form_item_right_illegal";
			}else {
				isSmsVerifyCodeValid = true;
				document.getElementById("smsverifycode_field_result").className = "register_pad_form_item_right_hidden";
				document.getElementById("smsverifycode_field_result_legal").className = "register_pad_form_item_right_legal";
				document.getElementById("smsverifycode_field_result_illegal").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_mobile").className = "register_pad_form_item_right_illegal_hidden";
				document.getElementById("smsverifycode_field_result_illegal_expired").className = "register_pad_form_item_right_illegal_hidden";
			}
        }
    }  
} 