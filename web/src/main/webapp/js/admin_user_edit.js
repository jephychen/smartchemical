var CONST1 = 1;
var isUserNameValid = false;
var isRealNameValid = true;
var isPasswordValid = false;
var isExternalCompanyValid = false;
var isAddressValid = true;
var isEmailValid = false;
var isMobileValid = false;
var isDepositValid = false;

function checkUserName(str, tipwidget){
	if (isValidUserName(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isUserNameValid = true;
		checkUserNameAjax(str);
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		document.getElementById(tipwidget).innerHTML = "请输入5~20位汉字或英文";
		isUserNameValid = false;
	}
	
}

function checkRealName(str, tipwidget){
	if (str == ""){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isRealNameValid = true;
		return;
	}
	if (isValidUserName(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isRealNameValid = true;
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isRealNameValid = false;
	}
}

function isValidUserName(str){
	var reg = /([a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]|[\s]){2,25}$/;
	return reg.test(str);
}

function checkPassword(str, tipwidget){
	if (isValidPassword(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isPasswordValid = true;
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isPasswordValid = false;
	}
}

function isValidPassword(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /.*/;
	return reg.test(str);
}

function checkExternalCompany(str, tipwidget){
	if (isValidCompanyName(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isExternalCompanyValid = true;
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isExternalCompanyValid = false;
	}
}

function isValidCompanyName(str){
	if (str.length < 6 || str.length > 30){
		return false;
	}
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]{6,30}/;
	return reg.test(str);
}

function checkDeposit(str, inputElementId, tipwidget){
	if (isNaN(str)){
		document.getElementById(inputElementId).value = CONST1.toFixed(2);
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isDepositValid = false;
		return;
	}
	document.getElementById(inputElementId).value = parseFloat(str).toFixed(2);
	if (isValidDeposit(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isDepositValid = true;
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isDepositValid = false;
	}
}

function isValidDeposit(str){
	if (str < 0){
		return false;
	}
	return true;
}

function checkAddress(str, tipwidget){
	if (str == ""){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isAddressValid = true;
		return;
	}
	if (isValidAddress(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isAddressValid = true;
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		isAddressValid = false;
	}
}

function isValidAddress(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkEmail(str, tipwidget, isEdit, userId){
	if (isValidEmail(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isEmailValid = true;
		checkEmailAjax(str, isEdit, userId);
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		document.getElementById(tipwidget).innerHTML = "请输入正确的邮箱";
		isEmailValid = false;
	}
}

function isValidEmail(str){
	var reg = /[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z0-9]{1,20}(\.[a-zA-Z0-9]{1,20}){0,1}$/;
	return reg.test(str);
}

function checkMobile(str, tipwidget, isEdit, userId){
	if (isValidMobile(str)){
		document.getElementById(tipwidget).className = "pop_content_input_tip1_hidden";
		isMobileValid = true;
		checkMobileAjax(str, isEdit, userId);
	}else{
		document.getElementById(tipwidget).className = "pop_content_input_tip1";
		document.getElementById(tipwidget).innerHTML = "请输入正确的手机号码";
		isMobileValid = false;
	}
}

function isValidMobile(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /[0-9\-]{6,20}/;
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
				document.getElementById("pop_content_username").className = "pop_content_input_tip1";
				document.getElementById("pop_content_username").innerHTML = "用户名已存在";
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

function checkEmailAjax(email, isEdit, userId){ 
	if (xhr_email == undefined){
		if(createEmailXHR() == 0){
			return true; 
		} 
	}
	xhr_email.onreadystatechange = checkEmailHandler;
	xhr_email.open("post", "RegisterAjax_checkEmail.action?keyWord=" + email + "&isEdit=" + isEdit + "&userId=" + userId);
	xhr_email.send();
}

function checkEmailHandler(){    
    if( xhr_email.readyState == 4 ){     
        if( xhr_email.status == 200 ){  
            if (xhr_email.responseText == "existed"){
				isEmailValid = false;
				document.getElementById("pop_content_email").className = "pop_content_input_tip1";
				document.getElementById("pop_content_email").innerHTML = "邮箱已被使用";
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

function checkMobileAjax(mobile, isEdit, userId){ 
	if (xhr_mobile == undefined){
		if(createMobileXHR() == 0){
			return true; 
		} 
	}
	xhr_mobile.onreadystatechange = checkMobileHandler;
	xhr_mobile.open("post", "RegisterAjax_checkMobile.action?keyWord=" + mobile + "&isEdit=" + isEdit + "&userId=" + userId);
	xhr_mobile.send();
}

function checkMobileHandler(){    
    if( xhr_mobile.readyState == 4 ){     
        if( xhr_mobile.status == 200 ){  
            if (xhr_mobile.responseText == "existed"){
				isMobileValid = false;
				document.getElementById("pop_content_mobile").className = "pop_content_input_tip1";
				document.getElementById("pop_content_mobile").innerHTML = "手机号码已被使用";
			}
        }
    }  
}            

function regionSelect(){
	var provinceDropdown = document.getElementById("pop_content_province_dropdown");
	for(var i= provinceDropdown.options.length - 1; i>=0 ; i--){
		provinceDropdown.options.remove(i);
	}
	var opt = document.createElement("option");
		opt.setAttribute("value", "0");
		opt.innerHTML = "选择省份";
		provinceDropdown.appendChild(opt);
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	for(var i= cityDropdown.options.length - 1; i>=0 ; i--){
		cityDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择城市";
		cityDropdown.appendChild(opt1);
	getProvinceAjax(document.getElementById("pop_content_region_dropdown").value);
}

function provinceSelect(){
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	for(var i= cityDropdown.options.length - 1; i>=0 ; i--){
		cityDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择城市";
		cityDropdown.appendChild(opt1);
	getCityAjax(document.getElementById("pop_content_province_dropdown").value);
}

var xhr_province; 

function createProvinceXHR(){    
    if( window.ActiveXObject ){  
        xhr_province = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_province = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function getProvinceAjax(regionId){ 
	if (xhr_province == undefined){
		if(createProvinceXHR() == 0){
			return true; 
		} 
	}
	xhr_province.onreadystatechange = getProvinceHandler;
	xhr_province.open("post", "RegionAjax_getProvince.action?regionId=" + regionId);
	xhr_province.send();
}

function getProvinceHandler(){    
    if( xhr_province.readyState == 4 ){     
        if( xhr_province.status == 200 ){ 
            fillProvinceDropdown(xhr_province.responseText);
        }
    }  
}

function fillProvinceDropdown(provinces){
	var provinceDropdown = document.getElementById("pop_content_province_dropdown");
	if (provinces == null || provinces.length == 0){
		return;
	}
	var provs = new Array();
	provs = provinces.split(",");
	for (var i = 0; i < provs.length; i++){
		var keyAndValue = provs[i].split(":");
		var opt = document.createElement("option");
		opt.setAttribute("value", keyAndValue[0]);
		opt.innerHTML = keyAndValue[1];
		provinceDropdown.appendChild(opt);
	}
}

var xhr_city; 

function createCityXHR(){    
    if( window.ActiveXObject ){  
        xhr_city = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_city = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function getCityAjax(provinceId){
	if (xhr_city == undefined){
		if(createCityXHR() == 0){
			return true; 
		} 
	}
	xhr_city.onreadystatechange = getCityHandler;
	xhr_city.open("post", "RegionAjax_getCity.action?provinceId=" + provinceId);
	xhr_city.send();
}

function getCityHandler(){    
    if( xhr_city.readyState == 4 ){     
        if( xhr_city.status == 200 ){ 
            fillCityDropdown(xhr_city.responseText);
        }
    }  
}

function fillCityDropdown(cities){
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	if (cities == null || cities.length == 0){
		return;
	}
	var cits = new Array();
	cits = cities.split(",");
	for (var i = 0; i < cits.length; i++){
		var keyAndValue = cits[i].split(":");
		var opt = document.createElement("option");
		opt.setAttribute("value", keyAndValue[0]);
		opt.innerHTML = keyAndValue[1];
		cityDropdown.appendChild(opt);
	}
}

function selectRole(){
	var roleId = document.getElementById("user_edit_roledropdown").value;
	if (roleId != 3 && roleId != 4){
		document.getElementById("user_edit_supplierdropdown").className = "hide_me";
		document.getElementById("user_edit_merchantdropdown").className = "hide_me";
		return;
	}
	if (roleId == 3){
		document.getElementById("user_edit_supplierdropdown").className = "product_edit_item_select";
		document.getElementById("user_edit_merchantdropdown").className = "hide_me";
		var supplierDropdown = document.getElementById("user_edit_supplierdropdown");
		for(var i= supplierDropdown.options.length - 1; i>=0 ; i--){
			supplierDropdown.options.remove(i);
		}
		var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择供应商";
		supplierDropdown.appendChild(opt1);
	}
	else if (roleId == 4){
		document.getElementById("user_edit_supplierdropdown").className = "hide_me";
		document.getElementById("user_edit_merchantdropdown").className = "product_edit_item_select";
		var merchantDropdown = document.getElementById("user_edit_merchantdropdown");
		for(var i= merchantDropdown.options.length - 1; i>=0 ; i--){
			merchantDropdown.options.remove(i);
		}
		var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择经销商";
		merchantDropdown.appendChild(opt1);
	}
	getCompanyAjax(roleId);
}

var xhr_company; 
var selectedRoleId; 

function createCompanyXHR(){    
    if( window.ActiveXObject ){  
        xhr_company = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_company = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function getCompanyAjax(roleId){
	selectedRoleId = roleId;
	if (xhr_company == undefined){
		if(createCompanyXHR() == 0){
			return true; 
		} 
	}
	xhr_company.onreadystatechange = getCompanyHandler;
	xhr_company.open("post", "CommonAjax_getCompanyByRole.action?roleId=" + roleId);
	xhr_company.send();
}

function getCompanyHandler(){    
    if( xhr_company.readyState == 4 ){     
        if( xhr_company.status == 200 ){ 
            fillCompanyDropdown(xhr_company.responseText);
        }
    }  
}

function fillCompanyDropdown(companies){
	var companyDropdown;
	if (selectedRoleId == 3){
		var companyDropdown = document.getElementById("user_edit_supplierdropdown");
	}
	else if(selectedRoleId == 4) {
		var companyDropdown = document.getElementById("user_edit_merchantdropdown");
	}
	else{
		var companyDropdown = document.getElementById("user_edit_merchantdropdown");
	}
	
	if (companies == null || companies.length == 0){
		return;
	}
	var companyArray = new Array();
	companyArray = companies.split(",");
	for (var i = 0; i < companyArray.length; i++){
		var keyAndValue = companyArray[i].split(":");
		var opt = document.createElement("option");
		opt.setAttribute("value", keyAndValue[0]);
		opt.innerHTML = keyAndValue[1];
		companyDropdown.appendChild(opt);
	}
}

function saveUser(queryType, userId){
	var userName = document.getElementById("user_edit_username").value;
	var realName = document.getElementById("user_edit_realname").value;
	var userpassword = document.getElementById("user_edit_password").value;
	var externalCompany = document.getElementById("user_edit_externalcompany").value;
	var deposit = 0;
	if (userId != 0){
		var deposit = document.getElementById("user_edit_deposit").value;
	}
	else {
		isDepositValid = true;
	}
	var cityId = document.getElementById("pop_content_city_dropdown").value;
	var address = document.getElementById("user_edit_address").value;
	var email = document.getElementById("user_edit_email").value;
	var mobile = document.getElementById("user_edit_mobile").value;
	var roleId = document.getElementById("user_edit_roledropdown").value;
	var supplierId = document.getElementById("user_edit_supplierdropdown").value;
	var merchantId = document.getElementById("user_edit_merchantdropdown").value;
	var statusId = 0;
	if (userId != 0){
		statusId = document.getElementById("user_edit_companylicensestatus").value;
		isUserNameValid = true;
	}
	
	if (userId == 0){
		checkUserName(userName, "pop_content_username");
	}
	checkRealName(realName, "pop_content_realname");
	checkPassword(userpassword, "pop_content_password");
	checkExternalCompany(externalCompany, "pop_content_externalcompany");
	if (userId != 0){
		checkDeposit(deposit, 'user_edit_deposit', 'pop_content_deposit');
	}
	checkAddress(address, "pop_content_address");
	if (userId == 0){
		checkEmail(email, "pop_content_email", 0, 0);
		checkMobile(mobile, "pop_content_mobile", 0, 0);
	}
	else{
		checkEmail(email, "pop_content_email", 1, userId);
		checkMobile(mobile, "pop_content_mobile", 1, userId);
	}

	if (!isUserNameValid || !isRealNameValid || !isPasswordValid || !isExternalCompanyValid || !isDepositValid || !isAddressValid || !isEmailValid 
		|| !isMobileValid){
		return;
	}
	if (cityId == 0){
		document.getElementById("pop_content_city").className = "pop_content_input_tip1";
		return;
	}
	if (roleId == 0){
		document.getElementById("pop_content_role").className = "pop_content_input_tip1";
		return;
	}
	if (roleId == 3 && supplierId == 0){
		document.getElementById("pop_content_role").className = "pop_content_input_tip1";
		document.getElementById("pop_content_role").innerHTML = "请选择供应商公司";
		return;
	}
	if (roleId == 4 && merchantId == 0){
		document.getElementById("pop_content_role").className = "pop_content_input_tip1";
		document.getElementById("pop_content_role").innerHTML = "请选择经销商公司";
		return;
	}
	
	var submitForm = document.getElementById("user_save_form");
	
	var param0 = document.createElement("input");
	param0.setAttribute("name", "queryType");
	param0.setAttribute("value", queryType);
	param0.setAttribute("type", "hidden");
	submitForm.appendChild(param0);
	
	var param1 = document.createElement("input");
	param1.setAttribute("name", "userName");
	param1.setAttribute("value", userName);
	param1.setAttribute("type", "hidden");
	submitForm.appendChild(param1);
	
	var param2 = document.createElement("input");
	param2.setAttribute("name", "realName");
	param2.setAttribute("value", realName);
	param2.setAttribute("type", "hidden");
	submitForm.appendChild(param2);
	
	var param3 = document.createElement("input");
	param3.setAttribute("name", "userpassword");
	param3.setAttribute("value", userpassword);
	param3.setAttribute("type", "hidden");
	submitForm.appendChild(param3);
	
	var param4 = document.createElement("input");
	param4.setAttribute("name", "externalCompany");
	param4.setAttribute("value", externalCompany);
	param4.setAttribute("type", "hidden");
	submitForm.appendChild(param4);
	
	var param5 = document.createElement("input");
	param5.setAttribute("name", "cityId");
	param5.setAttribute("value", cityId);
	param5.setAttribute("type", "hidden");
	submitForm.appendChild(param5);
	
	var param6 = document.createElement("input");
	param6.setAttribute("name", "address");
	param6.setAttribute("value", address);
	param6.setAttribute("type", "hidden");
	submitForm.appendChild(param6);
	
	var param7 = document.createElement("input");
	param7.setAttribute("name", "email");
	param7.setAttribute("value", email);
	param7.setAttribute("type", "hidden");
	submitForm.appendChild(param7);
	
	var param8 = document.createElement("input");
	param8.setAttribute("name", "mobile");
	param8.setAttribute("value", mobile);
	param8.setAttribute("type", "hidden");
	submitForm.appendChild(param8);
	
	var param9 = document.createElement("input");
	param9.setAttribute("name", "roleId");
	param9.setAttribute("value", roleId);
	param9.setAttribute("type", "hidden");
	submitForm.appendChild(param9);
	
	var param10 = document.createElement("input");
	param10.setAttribute("name", "supplierId");
	param10.setAttribute("value", supplierId);
	param10.setAttribute("type", "hidden");
	submitForm.appendChild(param10);
	
	var param11 = document.createElement("input");
	param11.setAttribute("name", "merchantId");
	param11.setAttribute("value", merchantId);
	param11.setAttribute("type", "hidden");
	submitForm.appendChild(param11);
	
	var param12 = document.createElement("input");
	param12.setAttribute("name", "deposit");
	param12.setAttribute("value", deposit);
	param12.setAttribute("type", "hidden");
	submitForm.appendChild(param12);
	
	if (userId != 0){
		var param13 = document.createElement("input");
		param13.setAttribute("name", "statusId");
		param13.setAttribute("value", statusId);
		param13.setAttribute("type", "hidden");
		submitForm.appendChild(param13);
	}
	
	var param14 = document.createElement("input");
	param14.setAttribute("name", "userId");
	param14.setAttribute("value", userId);
	param14.setAttribute("type", "hidden");
	submitForm.appendChild(param14);
	
	submitForm.method = "post";
	submitForm.action = "AdminUserSubmit_editSave.action";
	submitForm.submit();
}