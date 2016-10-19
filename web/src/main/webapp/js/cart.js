var CONST1 = 1;
var totalCartPrice = parseFloat(0);

function caculateItemPrice(productId, price, quantity){
	var itemPrice = price * quantity;
	document.getElementById("cart_pad_content_product_price_input" + productId).value = itemPrice.toFixed(2);
	totalCartPrice = parseFloat(totalCartPrice) + parseFloat(itemPrice.toFixed(2));
}

function showTotalPrice(){
	document.getElementById("cart_pad_total_price_input").value = totalCartPrice.toFixed(2);
}

function minusAmount(productId, price){
	var inputElementId = "content_product_detail_adder_input" + productId;
	var itemPriceElementId = "cart_pad_content_product_price_input" + productId;
	var totalPriceElementId = "cart_pad_total_price_input";
	var currentCount = document.getElementById(inputElementId).value;
	var currentItemPrice = document.getElementById(itemPriceElementId).value;
	var currentTotalPrice = document.getElementById(totalPriceElementId).value;
	if (currentCount <= 1){
		var result = parseFloat(price) * parseFloat(currentCount);
		document.getElementById(inputElementId).value = CONST1.toFixed(2);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, parseFloat(currentCount));
	}
	else {
		var result = parseFloat(price) * (parseFloat(currentCount) - 1);
		document.getElementById(inputElementId).value = (parseFloat(currentCount) - 1).toFixed(2);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, parseFloat(currentCount) - 1);
	}
	
}

function plusAmount(stocklevel, productId, price){
	var inputElementId = "content_product_detail_adder_input" + productId;
	var itemPriceElementId = "cart_pad_content_product_price_input" + productId;
	var totalPriceElementId = "cart_pad_total_price_input";
	var currentCount = document.getElementById(inputElementId).value;
	var currentItemPrice = document.getElementById(itemPriceElementId).value;
	var currentTotalPrice = document.getElementById(totalPriceElementId).value;
	if (currentCount > stocklevel - 1){
		var result = parseFloat(price) * parseFloat(stocklevel);
		document.getElementById(inputElementId).value = stocklevel.toFixed(2);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, parseFloat(stocklevel));
	}
	else {
		var result = parseFloat(price) * (parseFloat(currentCount) + 1);
		document.getElementById(inputElementId).value = (parseFloat(currentCount) + 1).toFixed(2);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, parseFloat(currentCount) + 1);
	}
}

var xhr_product_quantity; 

function createProductQuantityXHR(){    
    if( window.ActiveXObject ){  
        xhr_product_quantity = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_product_quantity = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function editProductQuantityAjax(productId, quantity){ 
	if (xhr_product_quantity == undefined){
		if(createProductQuantityXHR() == 0){
			return true; 
		} 
	}
	xhr_product_quantity.onreadystatechange = editProductQuantityHandler;
	xhr_product_quantity.open("post", "CartAjax_editProductQuantity.action?productId=" + productId + "&quantity=" + quantity);
	xhr_product_quantity.send();
}

function editProductQuantityHandler(){    
    if( xhr_product_quantity.readyState == 4 ){     
        if( xhr_product_quantity.status == 200 ){  
        }
    }  
}

function checkAmount(stocklevel, productId, price){
	var inputElementId = "content_product_detail_adder_input" + productId;
	var currentCount = document.getElementById(inputElementId).value;
	if (isNaN(currentCount)){
		document.getElementById(inputElementId).value = CONST1.toFixed(2);
		currentCount = CONST1;
	}
	currentCount = parseFloat(currentCount).toFixed(2);
	document.getElementById(inputElementId).value = currentCount;
	var itemPriceElementId = "cart_pad_content_product_price_input" + productId;
	var totalPriceElementId = "cart_pad_total_price_input";
	
	var currentItemPrice = document.getElementById(itemPriceElementId).value;
	var currentTotalPrice = document.getElementById(totalPriceElementId).value
	if (currentCount >= stocklevel){
		var result = parseFloat(price) * parseFloat(stocklevel);
		document.getElementById(inputElementId).value = stocklevel.toFixed(2);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, stocklevel);
	}
	else if (currentCount <= 0) {
		var result = parseFloat(price);
		document.getElementById(inputElementId).value = CONST1.toFixed(2);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, 1);
	}else{
		var result = parseFloat(price) * parseFloat(currentCount);
		document.getElementById(itemPriceElementId).value = result.toFixed(2);
		document.getElementById(totalPriceElementId).value = (currentTotalPrice - currentItemPrice + result).toFixed(2);
		editProductQuantityAjax(productId, currentCount);
	}
}

function selectItem(checkboxId){
	var quantity1 = document.getElementById("cart_count1");
	var quantity2 = document.getElementById("cart_count2");
	var totalPrice = document.getElementById("cart_pad_total_price_input");
	var isChecked = document.getElementById(checkboxId).checked;
	if (isChecked == true){
		document.getElementById("cart_pad_content_item" + checkboxId).className = "cart_pad_content_item_selected";
		document.getElementById("cart_pad_content_product_price_input" + checkboxId).className = "cart_pad_content_product_price_input_selected";
		quantity1.value = Number(quantity1.value) + Number(1);
		quantity2.value = Number(quantity2.value) + Number(1);
		var newTotalPrice = parseFloat(totalPrice.value) + parseFloat(document.getElementById("cart_pad_content_product_price_input" + checkboxId).value);
		totalPrice.value = newTotalPrice.toFixed(2);
	}else{
		document.getElementById("cart_pad_content_item" + checkboxId).className = "cart_pad_content_item";
		document.getElementById("cart_pad_content_product_price_input" + checkboxId).className = "cart_pad_content_product_price_input";
		quantity1.value = Number(quantity1.value) - Number(1);
		quantity2.value = Number(quantity2.value) - Number(1);
		var newTotalPrice = parseFloat(totalPrice.value) - parseFloat(document.getElementById("cart_pad_content_product_price_input" + checkboxId).value);
		totalPrice.value = newTotalPrice.toFixed(2);
	}
	if (isChecked == false){
		document.getElementById("cart_checkbox_main").checked = false;
		document.getElementById("cart_checkbox_main1").checked = false;
		return;
	}
	var a = document.getElementsByName("cart_checkbox");
	var temp = true;
	for (var i = 0; i < a.length; i++){
		temp &= a[i].checked;
	}
	if (temp == true){
		document.getElementById("cart_checkbox_main").checked = true;
		document.getElementById("cart_checkbox_main1").checked = true;
	}
}

function selectAll(){
	var quantity1 = document.getElementById("cart_count1");
	var quantity2 = document.getElementById("cart_count2");
	var totalPrice = document.getElementById("cart_pad_total_price_input");
	var isChecked = document.getElementById("cart_checkbox_main").checked;
	document.getElementById("cart_checkbox_main1").checked = isChecked;
	var a = document.getElementsByName("cart_checkbox");
	var totalPrice_temp = 0;
	for (var i = 0; i < a.length; i++){
		a[i].checked = isChecked;
		var id = "cart_pad_content_item" + a[i].id;
		if (isChecked == true){
			document.getElementById(id).className = "cart_pad_content_item_selected";
			document.getElementById("cart_pad_content_product_price_input" + a[i].id).className = "cart_pad_content_product_price_input_selected";
		}else{
			document.getElementById(id).className = "cart_pad_content_item";
			document.getElementById("cart_pad_content_product_price_input" + a[i].id).className = "cart_pad_content_product_price_input";
		}
		totalPrice_temp += parseFloat(document.getElementById("cart_pad_content_product_price_input" + a[i].id).value);
	}
	if (isChecked == true){
		quantity1.value = a.length;
		quantity2.value = a.length;
		totalPrice.value = totalPrice_temp;
	}else {
		quantity1.value = 0;
		quantity2.value = 0;
		totalPrice.value = 0;
	}
}

function selectAll1(){
	var quantity1 = document.getElementById("cart_count1");
	var quantity2 = document.getElementById("cart_count2");
	var totalPrice = document.getElementById("cart_pad_total_price_input");
	var isChecked = document.getElementById("cart_checkbox_main1").checked;
	document.getElementById("cart_checkbox_main").checked = isChecked;
	var a = document.getElementsByName("cart_checkbox");
	var totalPrice_temp = 0;
	for (var i = 0; i < a.length; i++){
		a[i].checked = isChecked;
		var id = "cart_pad_content_item" + a[i].id;
		if (isChecked == true){
			document.getElementById(id).className = "cart_pad_content_item_selected";
			document.getElementById("cart_pad_content_product_price_input" + a[i].id).className = "cart_pad_content_product_price_input_selected";
		}else{
			document.getElementById(id).className = "cart_pad_content_item";
			document.getElementById("cart_pad_content_product_price_input" + a[i].id).className = "cart_pad_content_product_price_input";
		}
		totalPrice_temp += parseFloat(document.getElementById("cart_pad_content_product_price_input" + a[i].id).value);
	}
	if (isChecked == true){
		quantity1.value = a.length;
		quantity2.value = a.length;
		totalPrice.value = totalPrice_temp;
	}else {
		quantity1.value = 0;
		quantity2.value = 0;
		totalPrice.value = 0;
	}
}

function removeSelected(){
	var a = document.getElementsByName("cart_checkbox");
	var params = "";
	for (var i = 0; i < a.length; i++){
		if (a[i].checked == true){
			params += a[i].id + "|";
		}
	}
	if (params == ""){
		return;
	}
	var removeForm = document.getElementById("remove_selected_form");
	removeForm.method="post";
	removeForm.action="Cart.action?removeSelectedIds=" + params;
	removeForm.submit();
}

function submitToOrder(companyLicenseStatus){
	if (companyLicenseStatus != 2){
		alert("您的账号尚未认证，请上传贵公司三证，三证审批通过之后才能使用此功能。");
		return;
	}
	var a = document.getElementsByName("cart_checkbox");
	var params = "";
	for (var i = 0; i < a.length; i++){
		if (a[i].checked == true){
			params += a[i].id + "|";
		}
	}
	if (params == ""){
		alert("请至少选择一个商品再进行结算");
		return;
	}
	var cartForm = document.getElementById("submit_form");
	cartForm.method="post";
	cartForm.action="Order.action?selectedIds=" + params;
	cartForm.submit();
}