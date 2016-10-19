var isUserNameValid = false;
var isPasswordValid = false;

var usernameFocusCount = 0;
var passwordFocusCount = 0;
function clearUsername(){
	if (usernameFocusCount == 0){
		document.getElementById("username_field").value = "";
		usernameFocusCount = 1;
	}
}

function clearPassword(){
	if (passwordFocusCount == 0){
		document.getElementById("password_field").type = "password";
		document.getElementById("password_field").value = "";
		passwordFocusCount = 1;
	}
}

function tipUsername(){
	if (document.getElementById("username_field").value == ""){
		document.getElementById("username_field").value = "请输入用户名";
		usernameFocusCount = 0;
	}
}

function tipPassword(){
	if (document.getElementById("password_field").value == ""){
		document.getElementById("password_field").type = "text";
		document.getElementById("password_field").value = "请输入密码";
		passwordFocusCount = 0;
	}
}

function submit_login_form(){
	if (usernameFocusCount == 0){
		document.getElementById("login_result_tip").innerHTML="用户名应为5-20位字符或数字,不以数字开头";
		return;
	}
	if (passwordFocusCount == 0){
		document.getElementById("login_result_tip").innerHTML="密码应为6-20位";
		return;
	}
	checkUserName();
	checkPassword();
	if (!isUserNameValid){
		document.getElementById("login_result_tip").innerHTML="用户名应为5-20位字符或数字,不以数字开头";
		return;
	}
	if (!isPasswordValid){
		document.getElementById("login_result_tip").innerHTML="密码应为6-20位";
		return;
	}
	document.getElementById("login_form").submit();
}

function checkUserName(){
	var str = document.getElementById("username_field").value;
	if (isValidUserName(str)){
		isUserNameValid = true;
	}else{
		isUserNameValid = false;
	}
}

function checkPassword(){
	var str = document.getElementById("password_field").value;
	if (isValidPassword(str)){
		isPasswordValid = true;
	}else{
		isPasswordValid = false;
	}
}

function isValidUserName(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]|[._]){4,19}$/;
	return reg.test(str);
}

function isValidPassword(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /.*/;
	return reg.test(str);
}