function minusAmount(stockLevel, minSoldQuantity){
	var currentCount = document.getElementById("content_product_detail_adder_input").value;
	if (currentCount < minSoldQuantity + 1){
		document.getElementById("content_product_detail_adder_input").value = minSoldQuantity.toFixed(1);
	}
	else {
		document.getElementById("content_product_detail_adder_input").value = (Number(currentCount) - 1).toFixed(1);
	}
}

function plusAmount(stockLevel, minSoldQuantity){
	var currentCount = document.getElementById("content_product_detail_adder_input").value;
	if (currentCount > stockLevel - 1){
		document.getElementById("content_product_detail_adder_input").value = stocklevel.toFixed(1);
	}
	else {
		document.getElementById("content_product_detail_adder_input").value = (Number(currentCount) + 1).toFixed(1);
	}
}

function checkAmount(stockLevel, minSoldQuantity){
	var currentCount = document.getElementById("content_product_detail_adder_input").value;
	if (isNaN(currentCount)){
		document.getElementById("content_product_detail_adder_input").value = minSoldQuantity.toFixed(1);
		return;
	}
	if (currentCount >= stockLevel){
		document.getElementById("content_product_detail_adder_input").value = stockLevel.toFixed(1);
	}
	else if (currentCount <= minSoldQuantity) {
		document.getElementById("content_product_detail_adder_input").value = minSoldQuantity.toFixed(1);
	}
}

function addToCart(productId, stockLevel, minSoldQuantity){
	if (eval(stockLevel) < eval(minSoldQuantity)){
		alert("产品库存不足，请等待厂商补货后再购买");
		return;
	}
	var quantity = document.getElementById("content_product_detail_adder_input").value;
	var cartForm = document.getElementById("add_to_cart");
	cartForm.method="post";
	cartForm.action="CartSuccess.action?productId=" + productId + "&quantity=" + quantity;
	cartForm.submit();
}

function buyImmediately(productId, stockLevel, minSoldQuantity, companyLicenseStatus){
	if (companyLicenseStatus != 2){
		alert("您的账号尚未认证，请上传贵公司三证，三证审批通过之后才能使用此功能。");
		return;
	}
	if (eval(stockLevel) < eval(minSoldQuantity)){
		alert("产品库存不足，请等待厂商补货后再购买");
		return;
	}
	var quantity = document.getElementById("content_product_detail_adder_input").value;
	var buyForm = document.getElementById("buy_immediately");
	buyForm.method="post";
	buyForm.action="OrderImmediately.action?productId=" + productId + "&quantity=" + quantity;
	buyForm.submit();
}