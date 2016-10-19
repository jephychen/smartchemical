var CONST1 = 1;
var isProductNameValid = false;
var isProductNoValid = false;
var isProductDescValid = false;
var isProductPriceValid = false;
var isBrandTextValid = false;

function checkProductName(){
	var str = document.getElementById("product_edit_productname").value; 
	if (isValidProductName(str)){
		document.getElementById("pop_content_productname").className = "pop_content_input_tip1_hidden";
		isProductNameValid = true;
	}
	else {
		document.getElementById("pop_content_productname").className = "pop_content_input_tip1";
		isProductNameValid = false;
	}
}

function isValidProductName(str){
	var reg = /^([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){3,20}$/;
	return reg.test(str);
}

function checkProductNo(){
	var str = document.getElementById("product_edit_productno").value; 
	if (isValidProductNo(str)){
		document.getElementById("pop_content_productno").className = "pop_content_input_tip1_hidden";
		isProductNoValid = true;
	}
	else {
		document.getElementById("pop_content_productno").className = "pop_content_input_tip1";
		isProductNoValid = false;
	}
}

function isValidProductNo(str){
	var reg = /^([a-zA-Z0-9\(\)\[\],\-\.]|[._]){2,20}$/;
	return reg.test(str);
}

function checkProductDesc(){
	var str = document.getElementById("product_edit_desc").value; 
	if (isValidProductDesc(str)){
		document.getElementById("pop_content_productdesc").className = "pop_content_input_tip1_hidden";
		isProductDescValid = true;
	}
	else {
		document.getElementById("pop_content_productdesc").className = "pop_content_input_tip1";
		isProductDescValid = false;
	}
}

function isValidProductDesc(str){
	var reg = /^([a-zA-Z0-9\u0391-\uFFE5\(\)\s\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkProductPrice(){
	var str = document.getElementById("product_edit_price").value; 
	if (str == ""){
		document.getElementById("pop_content_productprice").className = "pop_content_input_tip1";
		isProductPriceValid = false;
		return;
	}
	if (isNaN(str) || str < 0){
		document.getElementById("product_edit_price").value = CONST1.toFixed(2);
		document.getElementById("pop_content_productprice").className = "pop_content_input_tip1";
		isProductPriceValid = false;
		return;
	}
	str = parseFloat(str).toFixed(2);
	document.getElementById("pop_content_productprice").className = "pop_content_input_tip1_hidden";
	document.getElementById("product_edit_price").value = str;
	isProductPriceValid = true;
}

function checkStockLevel(){
	var str = document.getElementById("product_edit_stocklevel").value; 
	if (str == ""){
		document.getElementById("pop_content_stocklevel").className = "pop_content_input_tip1";
		isProductPriceValid = false;
		return;
	}
	if (isNaN(str) || str < 0){
		document.getElementById("product_edit_stocklevel").value = CONST1.toFixed(2);
		document.getElementById("pop_content_stocklevel").className = "pop_content_input_tip1";
		isProductPriceValid = false;
		return;
	}
	str = parseFloat(str).toFixed(2);
	document.getElementById("pop_content_stocklevel").className = "pop_content_input_tip1_hidden";
	document.getElementById("product_edit_stocklevel").value = str;
	isProductPriceValid = true;
}

function checkMinSoldQuantity(){
	var str = document.getElementById("product_edit_minsoldquantity").value; 
	if (str == ""){
		document.getElementById("pop_content_minsoldquantity").className = "pop_content_input_tip1";
		isStockLevelValid = false;
		return;
	}
	if (isNaN(str) || str < 0){
		document.getElementById("product_edit_minsoldquantity").value = CONST1.toFixed(2);
		document.getElementById("pop_content_minsoldquantity").className = "pop_content_input_tip1";
		isStockLevelValid = false;
		return;
	}
	str = parseFloat(str).toFixed(2);
	document.getElementById("pop_content_minsoldquantity").className = "pop_content_input_tip1_hidden";
	document.getElementById("product_edit_minsoldquantity").value = str;
	isStockLevelValid = true;
}

function checkProductType(){
	var productProductType = document.getElementById("pop_content_producttype_dropdown").value;
	if (productProductType == "0"){
		document.getElementById("pop_content_producttype").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_producttype").className = "pop_content_input_tip1_hidden";
	}
}

function checkCompany(){
	var productCompany = document.getElementById("pop_content_company_dropdown").value;
	if (productCompany == "0"){
		document.getElementById("other_brand").className = "product_edit_item_input_short_right_hidden";
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1";
		isBrandTextValid = false;
	}
	else if (productCompany == "-1"){
		document.getElementById("other_brand").className = "product_edit_item_input_short_right";
		document.getElementById("other_brand").value = "请填写品牌名";
		isFirst = true;
		isBrandTextValid = false;
	}
	else{
		document.getElementById("other_brand").className = "product_edit_item_input_short_right_hidden";
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1_hidden";
		isBrandTextValid = false;
	}
}

var isFirst = true;

function clearDefault(){
	if (isFirst){
		document.getElementById("other_brand").value = '';
	}
	isFirst = false;
}

function checkBrand(){
	var brandText = document.getElementById("other_brand").value;
	if (brandText == ''){
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1";
		document.getElementById("pop_content_productcompany").innerHTML = "请填写品牌名";
		return;
	}
	if (isValidBrandName(brandText)){
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1_hidden";
		isBrandTextValid = true;
	}
	else {
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1";
		document.getElementById("pop_content_productcompany").innerHTML = "请输入2~20位汉字或英文";
		isBrandTextValid = false;
	}
}

function isValidBrandName(str){
	var reg = /^([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){2,20}$/;
	return reg.test(str);
}

function checkWarehouse(){
	var productWarehouse = document.getElementById("pop_content_warehouse_dropdown").value;
	if (productWarehouse == "0"){
		document.getElementById("pop_content_warehouse").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_warehouse").className = "pop_content_input_tip1_hidden";
	}
}

function quantityDropdownChange(){
	var provinceDropdown = document.getElementById("pop_content_quantityunit_dropdown");
	var selectedId = provinceDropdown.value;
	var quantityUnitText;
	for(var i= provinceDropdown.options.length - 1; i>=0 ; i--){
		if (provinceDropdown.options[i].value == selectedId){
			quantityUnitText = provinceDropdown.options[i].innerHTML;
			break;
		}
	}
	document.getElementById("stocklevel_unit").innerHTML = quantityUnitText;
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
	var warehouseDropdown = document.getElementById("pop_content_warehouse_dropdown");
	for(var i= warehouseDropdown.options.length - 1; i>=0 ; i--){
		warehouseDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择仓库";
		warehouseDropdown.appendChild(opt1);
	document.getElementById("product_edit_nowarehouse_text").className = "product_edit_text_hidden";
	document.getElementById("new_warehouse_button").className = "hide_me";
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
	var warehouseDropdown = document.getElementById("pop_content_warehouse_dropdown");
	for(var i= warehouseDropdown.options.length - 1; i>=0 ; i--){
		warehouseDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择仓库";
		warehouseDropdown.appendChild(opt1);
	document.getElementById("product_edit_nowarehouse_text").className = "product_edit_text_hidden";
	document.getElementById("new_warehouse_button").className = "hide_me";
	getCityAjax(document.getElementById("pop_content_province_dropdown").value);
}

function citySelect(){
	var warehouseDropdown = document.getElementById("pop_content_warehouse_dropdown");
	for(var i= warehouseDropdown.options.length - 1; i>=0 ; i--){
		warehouseDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择仓库";
		warehouseDropdown.appendChild(opt1);
	document.getElementById("new_warehouse_button").className = "hover_pointer";
	var regionObj = document.getElementById("pop_content_region_dropdown");
	var provinceObj = document.getElementById("pop_content_province_dropdown");
	var cityObj = document.getElementById("pop_content_city_dropdown");
	document.getElementById("new_warehouse_button").className = "hover_pointer";
	document.getElementById("pop_content_content_regioninfo").innerHTML = regionObj.options[regionObj.options.selectedIndex].text + " - " 
		+ provinceObj.options[provinceObj.options.selectedIndex].text + " - " + cityObj.options[cityObj.options.selectedIndex].text;
	getWarehouseAjax(document.getElementById("pop_content_city_dropdown").value);
}

function categorySelect(){
	var producttypeDropdown = document.getElementById("pop_content_producttype_dropdown");
	for(var i= producttypeDropdown.options.length - 1; i>=0 ; i--){
		producttypeDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "0");
		opt1.innerHTML = "选择类型";
		producttypeDropdown.appendChild(opt1);
	getProductTypeAjax(document.getElementById("pop_content_category_dropdown").value);
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

var xhr_warehouse; 

function createWarehouseXHR(){    
    if( window.ActiveXObject ){  
        xhr_warehouse = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_warehouse = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function getWarehouseAjax(cityId){
	if (xhr_warehouse == undefined){
		if(createWarehouseXHR() == 0){
			return true; 
		} 
	}
	xhr_warehouse.onreadystatechange = getWarehouseHandler;
	xhr_warehouse.open("post", "RegionAjax_getWarehouse.action?cityId=" + cityId);
	xhr_warehouse.send();
}

function getWarehouseHandler(){    
    if( xhr_warehouse.readyState == 4 ){     
        if( xhr_warehouse.status == 200 ){ 
            fillWarehouseDropdown(xhr_warehouse.responseText);
        }
    }  
}

function fillWarehouseDropdown(warehouses){
	var warehouseDropdown = document.getElementById("pop_content_warehouse_dropdown");
	if (warehouses == null || warehouses.length == 0){
		document.getElementById("product_edit_nowarehouse_text").className = "product_edit_text";
		return;
	}
	document.getElementById("product_edit_nowarehouse_text").className = "product_edit_text_hidden";
	var houses = new Array();
	houses = warehouses.split(",");
	for (var i = 0; i < houses.length; i++){
		var keyAndValue = houses[i].split(":");
		var opt = document.createElement("option");
		opt.setAttribute("value", keyAndValue[0]);
		opt.innerHTML = keyAndValue[1];
		warehouseDropdown.appendChild(opt);
	}
}

var xhr_producttype; 

function createProductTypeXHR(){    
    if( window.ActiveXObject ){  
        xhr_producttype = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_producttype = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function getProductTypeAjax(categoryId){
	if (xhr_producttype == undefined){
		if(createProductTypeXHR() == 0){
			return true; 
		} 
	}
	xhr_producttype.onreadystatechange = getProductTypeHandler;
	xhr_producttype.open("post", "RegionAjax_getProductType.action?productCategoryId=" + categoryId);
	xhr_producttype.send();
}

function getProductTypeHandler(){    
    if( xhr_producttype.readyState == 4 ){     
        if( xhr_producttype.status == 200 ){ 
            fillProductTypeDropdown(xhr_producttype.responseText);
        }
    }  
}

function fillProductTypeDropdown(productTypes){
	var productTypeDropdown = document.getElementById("pop_content_producttype_dropdown");
	if (productTypes == null || productTypes.length == 0){
		return;
	}
	var types = new Array();
	types = productTypes.split(",");
	for (var i = 0; i < types.length; i++){
		var keyAndValue = types[i].split(":");
		var opt = document.createElement("option");
		opt.setAttribute("value", keyAndValue[0]);
		opt.innerHTML = keyAndValue[1];
		productTypeDropdown.appendChild(opt);
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

function uploadProductPic(){
	if (document.getElementById("product_edit_pic_upload").value == ""){
		alert("请先选择上传的图片");
		return;
	}
	var options ={   
		url:"AcceptanceBillAjax_uploadProductPic.action",  
		type:'post',                    
		data:null,
		success:fillUploadCallback,
		error:errorCallback
    };
	var form = $("#product_edit_upload_form");
	form.ajaxSubmit(options);
}

function fillUploadCallback(data){
	var img1 = document.getElementById("product_edit_pic_icon1");
	img1.setAttribute("src", data);
}

function errorCallback(){
	
}

function saveProduct(queryType, productId){
	checkProductName();
	checkProductDesc();
	checkProductNo();
	checkProductPrice();
	checkStockLevel();
	var productName = document.getElementById("product_edit_productname").value;
	var productNo = document.getElementById("product_edit_productno").value;
	var productType = document.getElementById("pop_content_producttype_dropdown").value;
	var productBrand = document.getElementById("pop_content_company_dropdown").value;
	var brandText = document.getElementById("other_brand").value;
	var productDesc = document.getElementById("product_edit_desc").value;
	var productPrice = document.getElementById("product_edit_price").value;
	var productPriceUnit = document.getElementById("pop_content_priceunit_dropdown").value;
	var productQuantityUnit = document.getElementById("pop_content_quantityunit_dropdown").value;
	var productWarehouse = document.getElementById("pop_content_warehouse_dropdown").value;
	var productStocklevel = document.getElementById("product_edit_stocklevel").value;
	var minSoldQuantity = document.getElementById("product_edit_minsoldquantity").value;
	if (productBrand == "-1" && isBrandTextValid == false){
		document.getElementById("other_brand").className = "product_edit_item_input_short_right";
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1";
		document.getElementById("pop_content_productcompany").innerHTML = "请填写正确的品牌名";
		return;
	}
	if (eval(minSoldQuantity) > eval(productStocklevel)){
		alert("最小起售量不能大于库存量");
		return;
	}
	var productPicPath = document.getElementById("product_edit_pic_icon1").getAttribute("src");
	var productLongDesc = document.getElementById("product_edit_productlongdesc").value;
	
	if (productType == "0"){
		document.getElementById("pop_content_producttype").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_producttype").className = "pop_content_input_tip1_hidden";
	}
	if (productBrand == "0"){
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_productcompany").className = "pop_content_input_tip1_hidden";
	}
	if (productWarehouse == "0"){
		document.getElementById("pop_content_warehouse").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_warehouse").className = "pop_content_input_tip1_hidden";
	}
	if (productPicPath == ""){
		document.getElementById("pop_content_productpic").className = "pop_content_input_tip1";
	}
	else{
		document.getElementById("pop_content_productpic").className = "pop_content_input_tip1_hidden";
	}
	if (productType == "0" || productBrand == "0" || productWarehouse == "0"){
		return;
	}
	var submitForm = document.getElementById("product_save_form");
	
	var queryTypeInput = document.createElement("input");
	queryTypeInput.setAttribute("name", "queryType");
	queryTypeInput.setAttribute("value", queryType);
	queryTypeInput.setAttribute("type", "hidden");
	submitForm.appendChild(queryTypeInput);
	
	var productIdInput = document.createElement("input");
	productIdInput.setAttribute("name", "productId");
	productIdInput.setAttribute("value", productId);
	productIdInput.setAttribute("type", "hidden");
	submitForm.appendChild(productIdInput);
	
	var productNameInput = document.createElement("input");
	productNameInput.setAttribute("name", "productName");
	productNameInput.setAttribute("value", productName);
	productNameInput.setAttribute("type", "hidden");
	submitForm.appendChild(productNameInput);
	
	var productNoInput = document.createElement("input");
	productNoInput.setAttribute("name", "productNo");
	productNoInput.setAttribute("value", productNo);
	productNoInput.setAttribute("type", "hidden");
	submitForm.appendChild(productNoInput);
	
	var productTypeInput = document.createElement("input");
	productTypeInput.setAttribute("name", "productType");
	productTypeInput.setAttribute("value", productType);
	productTypeInput.setAttribute("type", "hidden");
	submitForm.appendChild(productTypeInput);
	
	var productCompanyInput = document.createElement("input");
	productCompanyInput.setAttribute("name", "brandId");
	productCompanyInput.setAttribute("value", productBrand);
	productCompanyInput.setAttribute("type", "hidden");
	submitForm.appendChild(productCompanyInput);
	
	var productCompanyInput = document.createElement("input");
	productCompanyInput.setAttribute("name", "brandText");
	productCompanyInput.setAttribute("value", brandText);
	productCompanyInput.setAttribute("type", "hidden");
	submitForm.appendChild(productCompanyInput);
	
	var productDescInput = document.createElement("input");
	productDescInput.setAttribute("name", "productDesc");
	productDescInput.setAttribute("value", productDesc);
	productDescInput.setAttribute("type", "hidden");
	submitForm.appendChild(productDescInput);
	
	var productPriceInput = document.createElement("input");
	productPriceInput.setAttribute("name", "productPrice");
	productPriceInput.setAttribute("value", productPrice);
	productPriceInput.setAttribute("type", "hidden");
	submitForm.appendChild(productPriceInput);
	
	var productPriceUnitInput = document.createElement("input");
	productPriceUnitInput.setAttribute("name", "productPriceUnit");
	productPriceUnitInput.setAttribute("value", productPriceUnit);
	productPriceUnitInput.setAttribute("type", "hidden");
	submitForm.appendChild(productPriceUnitInput);
	
	var productQuantityUnitInput = document.createElement("input");
	productQuantityUnitInput.setAttribute("name", "productQuantityUnit");
	productQuantityUnitInput.setAttribute("value", productQuantityUnit);
	productQuantityUnitInput.setAttribute("type", "hidden");
	submitForm.appendChild(productQuantityUnitInput);
	
	var productWarehouseInput = document.createElement("input");
	productWarehouseInput.setAttribute("name", "productWarehouse");
	productWarehouseInput.setAttribute("value", productWarehouse);
	productWarehouseInput.setAttribute("type", "hidden");
	submitForm.appendChild(productWarehouseInput);
	
	var productStocklevelInput = document.createElement("input");
	productStocklevelInput.setAttribute("name", "productStocklevel");
	productStocklevelInput.setAttribute("value", productStocklevel);
	productStocklevelInput.setAttribute("type", "hidden");
	submitForm.appendChild(productStocklevelInput);
	
	var productStocklevelInput = document.createElement("input");
	productStocklevelInput.setAttribute("name", "minSoldQuantity");
	productStocklevelInput.setAttribute("value", minSoldQuantity);
	productStocklevelInput.setAttribute("type", "hidden");
	submitForm.appendChild(productStocklevelInput);
	
	var productPicPathInput = document.createElement("input");
	productPicPathInput.setAttribute("name", "productPicPath");
	productPicPathInput.setAttribute("value", productPicPath);
	productPicPathInput.setAttribute("type", "hidden");
	submitForm.appendChild(productPicPathInput);
	
	var productLongDescInput = document.createElement("input");
	productLongDescInput.setAttribute("name", "productLongDesc");
	productLongDescInput.setAttribute("value", productLongDesc);
	productLongDescInput.setAttribute("type", "hidden");
	submitForm.appendChild(productLongDescInput);
	
	submitForm.method = "post";
	submitForm.action = "MerchantProductSubmit_editSave.action";
	submitForm.submit();
}

function showWarehouseDialog(){
	document.getElementById("pop_dialog_warehouse").className = "pop_dialog_frame_show";
}

function closeWarehouseDialog(){
	document.getElementById("pop_dialog_warehouse").className = "pop_dialog_frame";
}

var isWarehouseNameValid = false;
var isAddressValid = false;
var isWhAdminNameValid = false;
var isWhAdminMobileValid = false;

function checkWarehouseName(){
	var warehouseName = document.getElementById("pop_content_input_warehouse_name").value;
	if (isValidWarehouseName(warehouseName)){
		document.getElementById("pop_content_input_warehousename_tip").className = "pop_content_input_tip_hidden";
		isWarehouseNameValid = true;
	}
	else {
		document.getElementById("pop_content_input_warehousename_tip").className = "pop_content_input_tip";
		isWarehouseNameValid = false;
	}
}

function isValidWarehouseName(str){
	if (str.length < 2 || str.length > 15){
		return false;
	}
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]{2,15}/;
	return reg.test(str);
}

function checkAddress(){
	var address = document.getElementById("pop_content_input_address_id").value; 
	if (isValidAddress(address)){
		document.getElementById("pop_content_input_address_tip").className = "pop_content_input_tip_hidden";
		isAddressValid = true;
	}
	else {
		document.getElementById("pop_content_input_address_tip").className = "pop_content_input_tip";
		isAddressValid = false;
	}
}

function isValidAddress(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkWhAdminName(){
	var address = document.getElementById("pop_content_input_whadmin_id").value; 
	if (isValidWarehouseName(address)){
		document.getElementById("pop_content_input_whadmin_tip").className = "pop_content_input_tip_hidden";
		isWhAdminNameValid = true;
	}
	else {
		document.getElementById("pop_content_input_whadmin_tip").className = "pop_content_input_tip";
		isWhAdminNameValid = false;
	}
}

function checkWhAdminMobile(){
	var mobile = document.getElementById("pop_content_input_whadminmobile_id").value; 
	if (isValidMobile(mobile)){
		document.getElementById("pop_content_input_whadminmobile_tip").className = "pop_content_input_tip_hidden";
	}
	else {
		document.getElementById("pop_content_input_whadminmobile_tip").className = "pop_content_input_tip";
		document.getElementById("pop_content_input_whadminmobile_tip").innerHTML = "请输入正确的电话";
		isWhAdminMobileValid = false;
		return;
	}
	checkMobileAjax(mobile);
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
				isWhAdminMobileValid = false;
				document.getElementById("pop_content_input_whadminmobile_tip").className = "pop_content_input_tip";
				document.getElementById("pop_content_input_whadminmobile_tip").innerHTML = "电话已存在，请重新输入";
			}
			else if (xhr_mobile.responseText == "unexisted") {
				isWhAdminMobileValid = true;
				document.getElementById("pop_content_input_whadminmobile_tip").className = "pop_content_input_tip_hidden";
			}
        }
    }  
}            

function isValidMobile(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /[0-9\-]{6,20}/;
	return reg.test(str);
}

function saveWarehouse(){
	checkWarehouseName();checkAddress();checkWhAdminName();
	if (!isWarehouseNameValid || !isAddressValid || !isWhAdminNameValid || !isWhAdminMobileValid){
		return;
	}
	var warehouseName = document.getElementById("pop_content_input_warehouse_name").value;
	var cityId = document.getElementById("pop_content_city_dropdown").value;
	var address = document.getElementById("pop_content_input_address_id").value;
	var adminName = document.getElementById("pop_content_input_whadmin_id").value;
	var adminMobile = document.getElementById("pop_content_input_whadminmobile_id").value;
	saveWarehouseAjax(warehouseName, cityId, address, adminName, adminMobile);
}

var xhr_warehouse_save; 

function createWarehouseXHRSave(){    
    if( window.ActiveXObject ){  
        xhr_warehouse_save = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_warehouse_save = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function saveWarehouseAjax(warehouseName, cityId, address, adminName, adminMobile){
	if (xhr_warehouse_save == undefined){
		if(createWarehouseXHRSave() == 0){
			return true; 
		} 
	}
	xhr_warehouse_save.onreadystatechange = saveWarehouseHandler;
	xhr_warehouse_save.open("post", "ReceiverGetterAjax_addWarehouse.action?warehouseName=" + warehouseName
		+ "&warehouseCityId=" + cityId + "&warehouseAddress=" + address + "&warehouseAdminName=" + adminName 
		+ "&warehouseAdminMobile=" + adminMobile);
	xhr_warehouse_save.send();
}

function saveWarehouseHandler(){    
    if( xhr_warehouse_save.readyState == 4 ){     
        if( xhr_warehouse_save.status == 200 ){ 
            result = xhr_warehouse_save.responseText;
			if (result == "failed"){
				return;
			}
			var infos = new Array();
			infos = result.split(",");
			var warehouseId = infos[0];
			var warehouseName = infos[1];
			
			var warehouseDropdown = document.getElementById("pop_content_warehouse_dropdown");
			var opt = document.createElement("option");
			opt.setAttribute("value", warehouseId);
			opt.setAttribute("selected", "selected");
			opt.innerHTML = warehouseName;
			warehouseDropdown.appendChild(opt);
			
			document.getElementById("product_edit_nowarehouse_text").className = "product_edit_text_hidden";
			closeWarehouseDialog();
        }
    }  
}