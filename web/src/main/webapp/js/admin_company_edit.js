var CONST0 = 0;
var isCompanyNameValid = false;
var isCompanyFullNameValid = false;
var isCompanyUrlValid = false;
var isCompanyDescValid = false;
var isCompanyAddressValid = false;
var isSloganValid = false;
var isLongitudeValid = false;
var isLatitudeValid = false;

function checkCompanyName(){
	var str = document.getElementById("company_edit_companyname").value; 
	if (isValidCompanyName(str)){
		document.getElementById("pop_content_productname").className = "pop_content_input_tip1_hidden";
		isCompanyNameValid = true;
	}
	else {
		document.getElementById("pop_content_productname").className = "pop_content_input_tip1";
		isCompanyNameValid = false;
	}
}

function isValidCompanyName(str){
	var reg = /^([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,20}$/;
	return reg.test(str);
}

function checkCompanyFullName(){
	var str = document.getElementById("company_edit_companyfullname").value; 
	if (isValidCompanyFullName(str)){
		document.getElementById("pop_content_productno").className = "pop_content_input_tip1_hidden";
		isCompanyFullNameValid = true;
	}
	else {
		document.getElementById("pop_content_productno").className = "pop_content_input_tip1";
		isCompanyFullNameValid = false;
	}
}

function isValidCompanyUrl(str){
	var reg = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
	return reg.test(str);
}

function isValidCompanyFullName(str){
	var reg = /^([\u0391-\uFFE5]){4,40}$/;
	return reg.test(str);
}

function isValidDesc(str){
	var reg = /^([a-zA-Z0-9\u0391-\uFFE5\(\)\s\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkCompanyUrl(){
	var str = document.getElementById("company_edit_companyurl").value; 
	if (isValidCompanyUrl(str)){
		document.getElementById("pop_content_companyurl").className = "pop_content_input_tip2_hidden";
		isCompanyUrlValid = true;
	}
	else {
		document.getElementById("pop_content_companyurl").className = "pop_content_input_tip2";
		isCompanyUrlValid = false;
	}
}

function checkCompanyDesc(){
	var str = document.getElementById("company_edit_desc").value; 
	if (isValidDesc(str)){
		document.getElementById("pop_content_companydesc").className = "pop_content_input_tip1_hidden";
		isCompanyDescValid = true;
	}
	else {
		document.getElementById("pop_content_companydesc").className = "pop_content_input_tip1";
		isCompanyDescValid = false;
	}
}

function checkCompanyAddress(){
	var str = document.getElementById("company_edit_address").value; 
	if (isValidDesc(str)){
		document.getElementById("pop_content_address").className = "pop_content_input_tip1_hidden";
		isCompanyAddressValid = true;
	}
	else {
		document.getElementById("pop_content_address").className = "pop_content_input_tip1";
		isCompanyAddressValid = false;
	}
}

function checkSlogan(){
	var str = document.getElementById("company_edit_slogan").value; 
	if (isValidDesc(str)){
		document.getElementById("pop_content_slogan").className = "pop_content_input_tip1_hidden";
		isSloganValid = true;
	}
	else {
		document.getElementById("pop_content_slogan").className = "pop_content_input_tip1";
		isSloganValid = false;
	}
}

function checkLongitude(){
	var str = document.getElementById("company_edit_longtitude").value; 
	if (str == ""){
		document.getElementById("pop_content_lnglat").className = "pop_content_input_tip1";
		isLongitudeValid = false;
		return;
	}
	if (isNaN(str) || str < -180 || str > 180){
		document.getElementById("company_edit_longtitude").value = CONST0.toFixed(6);
		document.getElementById("pop_content_lnglat").className = "pop_content_input_tip1";
		isLongitudeValid = false;
		return;
	}
	str = parseFloat(str).toFixed(6);
	document.getElementById("pop_content_lnglat").className = "pop_content_input_tip1_hidden";
	document.getElementById("company_edit_longtitude").value = str;
	isLongitudeValid = true;
}

function checkLatitude(){
	var str = document.getElementById("company_edit_latitude").value; 
	if (str == ""){
		document.getElementById("pop_content_lnglat").className = "pop_content_input_tip1";
		isLatitudeValid = false;
		return;
	}
	if (isNaN(str) || str < -180 || str > 180){
		document.getElementById("company_edit_latitude").value = CONST0.toFixed(6);
		document.getElementById("pop_content_lnglat").className = "pop_content_input_tip1";
		isLatitudeValid = false;
		return;
	}
	str = parseFloat(str).toFixed(6);
	document.getElementById("pop_content_lnglat").className = "pop_content_input_tip1_hidden";
	document.getElementById("company_edit_latitude").value = str;
	isLatitudeValid = true;
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

function checkCity(){
	var companyCity = document.getElementById("pop_content_city_dropdown").value;
	if (companyCity == "0"){
		document.getElementById("pop_content_city").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_city").className = "pop_content_input_tip1_hidden";
	}
}

function checkFile(obj){
	var fileSize = 0;
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;            
    if (isIE && !obj.files) {          
         var filePath = obj.value;            
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
         var file = fileSystem.GetFile (filePath);               
         fileSize = file.Size;         
    }else {  
         fileSize = obj.files[0].size;     
    }
	if (fileSize > 5242880){
		obj.value = "";
		alert("上传的文件应小于5M");
		return;
	}
	var fileName = obj.files[0].name;
	var dotPosition = fileName.lastIndexOf(".");
	var extendName = fileName.substr(dotPosition);
	if (extendName != ".bmp" && extendName != ".png" && extendName != ".gif" && extendName != ".jpeg" && extendName != ".jpg"
			&& extendName != ".BMP" && extendName != ".PNG" && extendName != ".GIF" && extendName != ".JPEG" && extendName != ".JPG"){
		obj.value = "";
		alert("上传的图片格式不正确。支持图片格式为bmp, png, gif, jpeg, jpg");
		return;
	}
}

function uploadCompanyPic(){
	if (document.getElementById("product_edit_pic_upload").value == ""){
		alert("请先选择上传的图片");
		return;
	}
	var options ={   
		url:"AcceptanceBillAjax_uploadCompanyPic.action",  
		type:'post',                    
		data:null,
		success:fillUploadCallback,
		error:errorCallback
    };
	var form = $("#company_edit_upload_form");
	form.ajaxSubmit(options);
}

function fillUploadCallback(data){
	var img1 = document.getElementById("company_edit_pic_icon1");
	img1.setAttribute("src", data);
}

function errorCallback(){
	
}

function saveCompany(queryType, companyId){
	checkCompanyName();
	checkCompanyFullName();
	checkCompanyUrl();
	checkCompanyDesc();
	checkCompanyAddress();
	checkSlogan();
	checkLongitude();
	checkLatitude();
	if (!isCompanyNameValid || !isCompanyFullNameValid || !isCompanyUrlValid || !isCompanyDescValid || !isCompanyAddressValid || !isSloganValid 
		|| !isLongitudeValid || !isLatitudeValid){
		return;
	}	
	var companyName = document.getElementById("company_edit_companyname").value;
	var companyFullName = document.getElementById("company_edit_companyfullname").value;
	var companyType = document.getElementById("company_edit_companytype").value;
	var companyMainPage = document.getElementById("company_edit_companyurl").value;
	var companyShortDesc = document.getElementById("company_edit_desc").value;
	var companyCityId = document.getElementById("pop_content_city_dropdown").value;
	var companyAddress = document.getElementById("company_edit_address").value;
	var longitude = document.getElementById("company_edit_longtitude").value;
	var latitude = document.getElementById("company_edit_latitude").value;
	var companySlogan = document.getElementById("company_edit_slogan").value;
	var companyPicPath = document.getElementById("company_edit_pic_icon1").getAttribute("src");
	var companyLongDesc = document.getElementById("company_edit_companylongdesc").value;
	
	if (companyType == "0"){
		document.getElementById("pop_content_companytype").className = "pop_content_input_tip1";
		return;
	}
	else{
		document.getElementById("pop_content_companytype").className = "pop_content_input_tip1_hidden";
	}
	
	if (companyCityId == "0"){
		document.getElementById("pop_content_city").className = "pop_content_input_tip1";
		return;
	}
	else{
		document.getElementById("pop_content_city").className = "pop_content_input_tip1_hidden";
	}
	
	if (companyPicPath == ""){
		document.getElementById("pop_content_companypic").className = "pop_content_input_tip1";
		return;
	}
	else{
		document.getElementById("pop_content_companypic").className = "pop_content_input_tip1_hidden";
	}
	
	var submitForm = document.getElementById("company_save_form");
	
	var queryTypeInput = document.createElement("input");
	queryTypeInput.setAttribute("name", "queryType");
	queryTypeInput.setAttribute("value", queryType);
	queryTypeInput.setAttribute("type", "hidden");
	submitForm.appendChild(queryTypeInput);
	
	var productIdInput = document.createElement("input");
	productIdInput.setAttribute("name", "companyId");
	productIdInput.setAttribute("value", companyId);
	productIdInput.setAttribute("type", "hidden");
	submitForm.appendChild(productIdInput);
	
	var productNameInput = document.createElement("input");
	productNameInput.setAttribute("name", "companyName");
	productNameInput.setAttribute("value", companyName);
	productNameInput.setAttribute("type", "hidden");
	submitForm.appendChild(productNameInput);
	
	var productNoInput = document.createElement("input");
	productNoInput.setAttribute("name", "companyFullName");
	productNoInput.setAttribute("value", companyFullName);
	productNoInput.setAttribute("type", "hidden");
	submitForm.appendChild(productNoInput);
	
	var productTypeInput = document.createElement("input");
	productTypeInput.setAttribute("name", "companyType");
	productTypeInput.setAttribute("value", companyType);
	productTypeInput.setAttribute("type", "hidden");
	submitForm.appendChild(productTypeInput);
	
	var productCompanyInput = document.createElement("input");
	productCompanyInput.setAttribute("name", "companyMainPage");
	productCompanyInput.setAttribute("value", companyMainPage);
	productCompanyInput.setAttribute("type", "hidden");
	submitForm.appendChild(productCompanyInput);
	
	var productDescInput = document.createElement("input");
	productDescInput.setAttribute("name", "companyShortDesc");
	productDescInput.setAttribute("value", companyShortDesc);
	productDescInput.setAttribute("type", "hidden");
	submitForm.appendChild(productDescInput);
	
	var productPriceInput = document.createElement("input");
	productPriceInput.setAttribute("name", "companyCityId");
	productPriceInput.setAttribute("value", companyCityId);
	productPriceInput.setAttribute("type", "hidden");
	submitForm.appendChild(productPriceInput);
	
	var productPriceUnitInput = document.createElement("input");
	productPriceUnitInput.setAttribute("name", "companyAddress");
	productPriceUnitInput.setAttribute("value", companyAddress);
	productPriceUnitInput.setAttribute("type", "hidden");
	submitForm.appendChild(productPriceUnitInput);
	
	var productQuantityUnitInput = document.createElement("input");
	productQuantityUnitInput.setAttribute("name", "longitude");
	productQuantityUnitInput.setAttribute("value", longitude);
	productQuantityUnitInput.setAttribute("type", "hidden");
	submitForm.appendChild(productQuantityUnitInput);
	
	var productWarehouseInput = document.createElement("input");
	productWarehouseInput.setAttribute("name", "latitude");
	productWarehouseInput.setAttribute("value", latitude);
	productWarehouseInput.setAttribute("type", "hidden");
	submitForm.appendChild(productWarehouseInput);
	
	var productStocklevelInput = document.createElement("input");
	productStocklevelInput.setAttribute("name", "companySlogan");
	productStocklevelInput.setAttribute("value", companySlogan);
	productStocklevelInput.setAttribute("type", "hidden");
	submitForm.appendChild(productStocklevelInput);
	
	var productPicPathInput = document.createElement("input");
	productPicPathInput.setAttribute("name", "companyPicPath");
	productPicPathInput.setAttribute("value", companyPicPath);
	productPicPathInput.setAttribute("type", "hidden");
	submitForm.appendChild(productPicPathInput);
	
	var productLongDescInput = document.createElement("input");
	productLongDescInput.setAttribute("name", "companyLongDesc");
	productLongDescInput.setAttribute("value", companyLongDesc);
	productLongDescInput.setAttribute("type", "hidden");
	submitForm.appendChild(productLongDescInput);
	
	submitForm.method = "post";
	submitForm.action = "AdminCompanySubmit_editSave.action";
	submitForm.submit();
}