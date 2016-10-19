var totalProductFee = 0;
var logisticFee = 0;
var logisticFee_temp = 0;
var acBillServiceFee = 0;
var acBillServiceFee_temp = 0;

function onload(_totalProductPrice, _logisticFee, _acBillServiceFee, _invoiceId){
	totalProductFee = _totalProductPrice;
	logisticFee = _logisticFee;
	logisticFee_temp = 0;
	acBillServiceFee = _acBillServiceFee;
	invoiceId = _invoiceId;
//	document.getElementById("order_page_acbillservicefee_div").innerHTML =  _acBillServiceFee.toFixed(2);
}

function showTotalFee(){
	var totalFee = parseFloat(totalProductFee) + parseFloat(logisticFee_temp) +  parseFloat(acBillServiceFee_temp);
	document.getElementById("order_page_logisticfee_div").innerHTML = logisticFee_temp.toFixed(2);
//	document.getElementById("order_page_acbillservicefee_div").innerHTML =  acBillServiceFee_temp.toFixed(2);
	document.getElementById("order_page_totalfee_1").innerHTML = totalFee.toFixed(2);
	document.getElementById("order_page_totalfee_2").innerHTML = "￥" + totalFee.toFixed(2);
}

function vendorDeliver(){
	document.getElementById("vendor_delivery_button").className = "order_page_button_regular_selected";
//	document.getElementById("zhixuan_delivery_button").className = "order_page_button_regular";
	document.getElementById("get_byuser_button").className = "order_page_button_regular";
	
	document.getElementById("delivery_tip").innerHTML = "厂商负责配送，不收取配送费";
	
	document.getElementById("order_receiver").className = "order_page_item";
	document.getElementById("order_getter").className = "order_page_item_hidden";
	
	logisticFee_temp = 0;
	showTotalFee();
}

function zhixuanDeliver(){
	document.getElementById("vendor_delivery_button").className = "order_page_button_regular";
//	document.getElementById("zhixuan_delivery_button").className = "order_page_button_regular_selected";
	document.getElementById("get_byuser_button").className = "order_page_button_regular";
	
	document.getElementById("delivery_tip").innerHTML = "智选化学负责配送，配送会收取一定的物流费用";
	
	document.getElementById("order_receiver").className = "order_page_item";
	document.getElementById("order_getter").className = "order_page_item_hidden";
	
	logisticFee_temp = logisticFee;
	showTotalFee();
}

function getByUser(){
	document.getElementById("vendor_delivery_button").className = "order_page_button_regular";
//	document.getElementById("zhixuan_delivery_button").className = "order_page_button_regular";
	document.getElementById("get_byuser_button").className = "order_page_button_regular_selected";
	
	document.getElementById("delivery_tip").innerHTML = "用户自行到厂商仓库进行货物提取";
	
	document.getElementById("order_receiver").className = "order_page_item_hidden";
	document.getElementById("order_getter").className = "order_page_item";
	
	logisticFee_temp = 0;
	showTotalFee();
}

function addressMore(){
	var addresses = document.getElementsByName("order_page_address");
	for (var i = 0; i < addresses.length; i++){
		addresses[i].className = "order_page_item_address_wrapper";
	}
	document.getElementById("address_more_button").className = "order_page_item_address_more_hidden";
	document.getElementById("address_less_button").className = "order_page_item_address_more";
}

function addressLess(){
	var addresses = document.getElementsByName("order_page_address");
	for (var i = 0; i < addresses.length; i++){
		var childButton = document.getElementById("order_page_address_child" + addresses[i].id);
		if (childButton.className == "order_page_button_large_selected"){
			continue;
		}
		addresses[i].className = "order_page_item_address_wrapper_hidden";
	}
	document.getElementById("address_more_button").className = "order_page_item_address_more";
	document.getElementById("address_less_button").className = "order_page_item_address_more_hidden";
}

function selectAddress(nid){
	var addresses = document.getElementsByName("order_page_address_child");
	for (var i = 0; i < addresses.length; i++){
		if ( ("order_page_address_child" + nid) == addresses[i].id){
			addresses[i].className = "order_page_button_large_selected";
		}else {
			addresses[i].className = "order_page_button_large";
		}
	}
}

function addressMoreGetter(){
	var addresses = document.getElementsByName("order_page_address_getter");
	for (var i = 0; i < addresses.length; i++){
		addresses[i].className = "order_page_item_address_wrapper";
	}
	document.getElementById("address_more_button_getter").className = "order_page_item_address_more_hidden";
	document.getElementById("address_less_button_getter").className = "order_page_item_address_more";
}

function addressLessGetter(){
	var addresses = document.getElementsByName("order_page_address_getter");
	for (var i = 0; i < addresses.length; i++){
		var childButton = document.getElementById("order_page_address_child_" + addresses[i].id);
		if (childButton.className == "order_page_button_large_selected"){
			continue;
		}
		addresses[i].className = "order_page_item_address_wrapper_hidden";
	}
	document.getElementById("address_more_button_getter").className = "order_page_item_address_more";
	document.getElementById("address_less_button_getter").className = "order_page_item_address_more_hidden";
}

function selectAddressGetter(nid){
	var addresses = document.getElementsByName("order_page_address_child_getter");
	for (var i = 0; i < addresses.length; i++){
		if ( ("order_page_address_child_getter" + nid) == addresses[i].id){
			addresses[i].className = "order_page_button_large_selected";
		}else {
			addresses[i].className = "order_page_button_large";
		}
	}
}

function onlinePay(){
	document.getElementById("order_page_payment_onlinepay").className = "order_page_button_regular_selected";
	document.getElementById("order_page_payment_transfer").className = "order_page_button_regular";
//	document.getElementById("order_page_payment_acceptance_bill").className = "order_page_button_regular";
	document.getElementById("order_page_payment_tip").innerHTML = "直接网上支付，即时到账。支持绝大多数银行。";
//	document.getElementById("order_page_acbillservicefee_line").className = "order_page_item_total_line_hidden";
	document.getElementById("depositpay_checkbox_id").checked = false;
	acBillServiceFee_temp = 0;
	showTotalFee();
}

function transferPay(){
	document.getElementById("order_page_payment_onlinepay").className = "order_page_button_regular";
	document.getElementById("order_page_payment_transfer").className = "order_page_button_regular_selected";
//	document.getElementById("order_page_payment_acceptance_bill").className = "order_page_button_regular";
	document.getElementById("order_page_payment_tip").innerHTML = "直接将货物款项转到智选化学账户，根据1~3个工作日到账。提交订单后工作人员会及时联系您。";
//	document.getElementById("order_page_acbillservicefee_line").className = "order_page_item_total_line_hidden";
	document.getElementById("depositpay_checkbox_id").checked = false;
	acBillServiceFee_temp = 0;
	showTotalFee();
}
/*
function acceptanceBill(){
	document.getElementById("order_page_payment_onlinepay").className = "order_page_button_regular";
	document.getElementById("order_page_payment_transfer").className = "order_page_button_regular";
	document.getElementById("order_page_payment_acceptance_bill").className = "order_page_button_regular_selected";
	document.getElementById("order_page_payment_tip").innerHTML = "使用银行承兑汇票付款，需上传汇票扫描件，同时邮寄纸质汇票到智选化学。<br>"
		+ "<font class='font_black'>1.</font>智选平台只接收具有资质的银行开具的承兑汇票；支付方对承兑的完整性/真实性/合法性承担所有法律责任；<br>"
		+ "<font class='font_black'>2.</font>智选平台为入驻企业提供银行承兑汇票代收及协助贴现服务，并通过具有合法资质的贴现机构进行贴现；<br>"
		+ "<font class='font_black'>3.</font>智选平台在协助上游企业进行银行承兑汇票过程中不收取任何中间费用，贴现手续费为贴现机构提供并收取；<br>"
		+ "<font class='font_black'>4.</font>贴现并支付货款后的余额将会以现金方式显示在账户余额中，支持余额取现。<br>"
		+ "当期贴现费率：<font class='font_red'>4.5%</font><br>";
	
	document.getElementById("order_page_acbillservicefee_line").className = "order_page_item_total_line";
	acBillServiceFee_temp = acBillServiceFee;
	showTotalFee();
}*/

function removeAddress(receiverId){
	removeReceiverAjax(receiverId);
}

function removeAddressGetter(getterId){
	removeGetterAjax(getterId);
}

function editAddress(receiverId){
	document.getElementById("pop_dialog_frame_order_address").className = "pop_dialog_frame_show";
	document.getElementById("pop_content_button_receiver").setAttribute("onclick", "saveReceiver()");
	getReceiverAjax(receiverId);
}

function addAddress(){
	document.getElementById("pop_dialog_frame_order_address").className = "pop_dialog_frame_show";
	document.getElementById("pop_content_receivername").value = "";
	var regionDropdown = document.getElementById("pop_content_region_dropdown");
	regionDropdown.options[0].selected = true;
	var provinceDropdown = document.getElementById("pop_content_province_dropdown");
	provinceDropdown.options[0].selected = true;
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	cityDropdown.options[0].selected = true;
	var selectedRegionId = -1;
	var selectedProvinceId = -1;
	var selectedCityId = -1;
	document.getElementById("pop_content_address").value = "";
	document.getElementById("pop_content_receivermobileno").value = "";
	document.getElementById("pop_content_email").value = "";
	document.getElementById("pop_content_button_receiver").setAttribute("onclick", "saveAddReceiver()");
	document.getElementById("pop_content_input_tip_receiver").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_address").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_mobile").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_email").className = "pop_content_input_tip_hidden";
}

function shutdownAddressFrame(){
	document.getElementById("pop_dialog_frame_order_address").className = "pop_dialog_frame";
}

function editAddressGetter(getterId){
	document.getElementById("pop_dialog_frame_order_address_getter").className = "pop_dialog_frame_show";
	document.getElementById("pop_content_button_getter").setAttribute("onclick", "saveGetter()");
	getGetterAjax(getterId);
}

function addAddressGetter(){
	document.getElementById("pop_dialog_frame_order_address_getter").className = "pop_dialog_frame_show";
	document.getElementById("pop_content_gettername").value = "";
	document.getElementById("pop_content_getteridno").value = "";
	document.getElementById("pop_content_gettermobileno").value = "";
	document.getElementById("pop_content_trucklicenseno").value = "";
	document.getElementById("pop_content_getteremail").value = "";
	document.getElementById("pop_content_button_getter").setAttribute("onclick", "saveAddGetter()");
	document.getElementById("pop_content_input_tip_getter").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_idno").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_getter_mobile").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_car_licence").className = "pop_content_input_tip_hidden";
	document.getElementById("pop_content_input_tip_getter_email").className = "pop_content_input_tip_hidden";
}

function shutdownAddressFrameGetter(){
	document.getElementById("pop_dialog_frame_order_address_getter").className = "pop_dialog_frame";
}

function saveAddReceiver(){
	checkReceiverName();checkCity();checkAddress();checkReceiverMobile();checkReceiverEmail();
	if (!isReceiverNameValid || !isReceiverCityValid || !isAddressValid || !isReceiverAddressValid || !isReceiverEmailValid){
		return;
	}
	var receiverName = document.getElementById("pop_content_receivername").value;
	var cityId = document.getElementById("pop_content_city_dropdown").value;
	var address = document.getElementById("pop_content_address").value;
	var mobileNo = document.getElementById("pop_content_receivermobileno").value;
	var email = document.getElementById("pop_content_email").value;
	addReceiverAjax(receiverName, cityId, address, mobileNo, email);
	document.getElementById("pop_dialog_frame_order_address").className = "pop_dialog_frame";
}

function saveAddGetter(){
	checkGetterName();checkGetterIdNo();checkGetterMobile();checkTruchLicenseNo();checkGetterEmail();
	if (!isGetterNameValid || !isGetterIdNoValid || !isGetterMobileValid || !isTruckLicenseNoValid || !isGetterEmailValid){
		return;
	}
	var getterName = document.getElementById("pop_content_gettername").value;
	var getterIdNo = document.getElementById("pop_content_getteridno").value;
	var getterMobileNo = document.getElementById("pop_content_gettermobileno").value;
	var getterEmail = document.getElementById("pop_content_getteremail").value;
	var truckLicenseNo = document.getElementById("pop_content_trucklicenseno").value;
	addGetterAjax(getterName, getterIdNo, getterMobileNo, getterEmail, truckLicenseNo);
	document.getElementById("pop_dialog_frame_order_address_getter").className = "pop_dialog_frame";
}

function saveReceiver(){
	checkReceiverName();checkCity();checkAddress();checkReceiverMobile();checkReceiverEmail();
	if (!isReceiverNameValid || !isReceiverCityValid || !isAddressValid || !isReceiverAddressValid || !isReceiverEmailValid){
		return;
	}
	var receiverId = document.getElementById("pop_content_receiverid").value;
	var receiverName = document.getElementById("pop_content_receivername").value;
	var cityId = document.getElementById("pop_content_city_dropdown").value;
	var address = document.getElementById("pop_content_address").value;
	var mobileNo = document.getElementById("pop_content_receivermobileno").value;
	var email = document.getElementById("pop_content_email").value;
	editReceiverAjax(receiverId, receiverName, cityId, address, mobileNo, email);
	document.getElementById("pop_dialog_frame_order_address").className = "pop_dialog_frame";
	selectAddress(receiverId);
}

function saveGetter(){
	checkGetterName();checkGetterIdNo();checkGetterMobile();checkTruchLicenseNo();checkGetterEmail();
	if (!isGetterNameValid || !isGetterIdNoValid || !isGetterMobileValid || !isTruckLicenseNoValid || !isGetterEmailValid){
		return;
	}
	var getterId = document.getElementById("pop_content_getterid").value;
	var getterName = document.getElementById("pop_content_gettername").value;
	var getterIdNo = document.getElementById("pop_content_getteridno").value;
	var getterMobileNo = document.getElementById("pop_content_gettermobileno").value;
	var getterEmail = document.getElementById("pop_content_getteremail").value;
	var truckLicenseNo = document.getElementById("pop_content_trucklicenseno").value;
	editGetterAjax(getterId, getterName, getterIdNo, getterMobileNo, getterEmail, truckLicenseNo);
	document.getElementById("pop_dialog_frame_order_address_getter").className = "pop_dialog_frame";
	selectAddressGetter(getterId);
}

var xhr_receiver_getter; 

function createReceiverGetterXHR(){    
    if( window.ActiveXObject ){  
        xhr_receiver_getter = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_receiver_getter = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function addReceiverAjax(receiverName, cityId, address, mobileNo, email){ 
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = addReceiverHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_editCartReceiver.action?receiverName=" 
		+ encodeURI(receiverName) + "&cityId=" + cityId + "&address=" + encodeURI(address) + "&mobileNo=" + mobileNo + "&email=" + email);
	xhr_receiver_getter.send();
}

function addGetterAjax(getterName, getterIdNo, getterMobileNo, getterEmail, truckLicenseNo){ 
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = addGetterHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_editCartGetter.action?getterName="
		+ encodeURI(getterName) + "&getterIdNo=" + getterIdNo + "&getterMobileNo=" + getterMobileNo + "&truckLicenseNo=" + encodeURI(truckLicenseNo) + "&getterEmail=" + getterEmail);
	xhr_receiver_getter.send();
}

function editReceiverAjax(receiverId, receiverName, cityId, address, mobileNo, email){ 
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = editReceiverHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_editCartReceiver.action?receiverId=" + receiverId + "&receiverName=" 
		+ encodeURI(receiverName) + "&cityId=" + cityId + "&address=" + encodeURI(address) + "&mobileNo=" + mobileNo + "&email=" + email);
	xhr_receiver_getter.send();
}

function editGetterAjax(getterId, getterName, getterIdNo, getterMobileNo, getterEmail, truckLicenseNo){ 
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = editGetterHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_editCartGetter.action?getterId=" + getterId + "&getterName="
		+ encodeURI(getterName) + "&getterIdNo=" + getterIdNo + "&getterMobileNo=" + getterMobileNo + "&truckLicenseNo=" + encodeURI(truckLicenseNo) + "&getterEmail=" + getterEmail );
	xhr_receiver_getter.send();
}

function addGetterHandler(){
	if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			var infos = new Array();
			infos = xhr_receiver_getter.responseText.split(",");
			var div = document.createElement("div");
			div.setAttribute("id", "getter" + infos[0]);
			div.setAttribute("name", "order_page_address_getter");
			div.setAttribute("class", 	"order_page_item_address_wrapper");
				var div1 = document.createElement("div");
				div1.setAttribute("id", "order_page_address_child_getter" + infos[0]);
				div1.setAttribute("name", "order_page_address_child_getter");
				div1.setAttribute("class", "order_page_button_large");
				div1.setAttribute("onclick", "selectAddressGetter(" + infos[0] + ")");
				div1.innerHTML=infos[1] + " " + infos[3];
			div.appendChild(div1);
				var div2 = document.createElement("div");
				div2.setAttribute("class", "order_page_item_address_content_wrapper");
					var div21 = document.createElement("div");
					div21.setAttribute("id", "order_page_item_getter_detail" + infos[0]);
					div21.setAttribute("class", "order_page_item_address_content");
					div21.innerHTML=infos[1] + " 身份证：" + infos[2] + " 手机：" + infos[3] + " 车牌号：" + infos[4];
					var div22 = document.createElement("div");
					div22.setAttribute("class", "order_page_item_address_content_right");
					div22.setAttribute("onclick", "removeAddressGetter(" + infos[0] + ")");
					div22.innerHTML="删除";
					var div23 = document.createElement("div");
					div23.setAttribute("class", "order_page_item_address_content_right");
					div23.setAttribute("onclick", "editAddressGetter(" + infos[0] + ")");
					div23.innerHTML="编辑";
				div2.appendChild(div21);
				div2.appendChild(div22);
				div2.appendChild(div23);
			div.appendChild(div2);
			document.getElementById("order_page_item_detail_getters_wrapper").appendChild(div);
			selectAddressGetter(infos[0]);
        }
    }  
}

function addReceiverHandler(){
	if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			var infos = new Array();
			infos = xhr_receiver_getter.responseText.split(",");
			var div = document.createElement("div");
			div.setAttribute("id", infos[0]);
			div.setAttribute("name", "order_page_address");
			div.setAttribute("class", 	"order_page_item_address_wrapper");
				var div1 = document.createElement("div");
				div1.setAttribute("id", "order_page_address_child" + infos[0]);
				div1.setAttribute("name", "order_page_address_child");
				div1.setAttribute("class", "order_page_button_large");
				div1.setAttribute("onclick", "selectAddress(" + infos[0] + ")");
				div1.innerHTML=infos[1] + " " + infos[8] + "-" + infos[9];
			div.appendChild(div1);
				var div2 = document.createElement("div");
				div2.setAttribute("class", "order_page_item_address_content_wrapper");
					var div21 = document.createElement("div");
					div21.setAttribute("id", "order_page_item_receiver_detail" + infos[0]);
					div21.setAttribute("class", "order_page_item_address_content");
					div21.innerHTML=infos[1] + " " + infos[8] + "-" + infos[9] + "-" + infos[5];
					var div22 = document.createElement("div");
					div22.setAttribute("class", "order_page_item_address_content_right");
					div22.setAttribute("onclick", "removeAddress(" + infos[0] + ")");
					div22.innerHTML="删除";
					var div23 = document.createElement("div");
					div23.setAttribute("class", "order_page_item_address_content_right");
					div23.setAttribute("onclick", "editAddress(" + infos[0] + ")");
					div23.innerHTML="编辑";
				div2.appendChild(div21);
				div2.appendChild(div22);
				div2.appendChild(div23);
			div.appendChild(div2);
			document.getElementById("order_page_item_detail_receivers_wrapper").appendChild(div);
			selectAddress(infos[0]);
        }
    }  
}

function editReceiverHandler(){    
    if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){ 
			var infos = new Array();
			infos = xhr_receiver_getter.responseText.split(",");
			document.getElementById("order_page_address_child" + infos[0]).innerHTML=infos[1] + " " + infos[8] + "-" + infos[9];
			document.getElementById("order_page_item_receiver_detail" + infos[0]).innerHTML=infos[1] + " " + infos[8] + "-" + infos[9] + "-" 
				+ infos[5] + " 手机：" + infos[6];
        }
    }  
}

function editGetterHandler(){    
    if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			var infos = new Array();
			infos = xhr_receiver_getter.responseText.split(",");
			document.getElementById("order_page_address_child_getter" + infos[0]).innerHTML=infos[1] + " " + infos[3];
			document.getElementById("order_page_item_getter_detail" + infos[0]).innerHTML=infos[1] + " 身份证：" + infos[2] + " 手机：" + infos[3] + " 车牌号：" + infos[4];
        }
    }  
}

function getReceiverAjax(receiverId){
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = getReceiverHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_getReceiver.action?receiverId=" + receiverId);
	xhr_receiver_getter.send();
}

function getGetterAjax(getterId){
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = getGetterHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_getGetter.action?getterId=" + getterId);
	xhr_receiver_getter.send();
}

function removeReceiverAjax(receiverId){
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = removeReceiverHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_removeReceiver.action?receiverId=" + receiverId);
	xhr_receiver_getter.send();
}

function removeGetterAjax(getterId){
	if (xhr_receiver_getter == undefined){
		if(createReceiverGetterXHR() == 0){
			return true; 
		} 
	}
	xhr_receiver_getter.onreadystatechange = removeGetterHandler;
	xhr_receiver_getter.open("post", "ReceiverGetterAjax_removeGetter.action?getterId=" + getterId);
	xhr_receiver_getter.send();
}

function getReceiverHandler(){
	if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			addToReceiverDialog(xhr_receiver_getter.responseText);
			checkReceiverName();checkCity();checkAddress();checkReceiverMobile();checkReceiverEmail();
        }
    } 
}

function getGetterHandler(){
	if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			addToGetterDialog(xhr_receiver_getter.responseText);
			checkGetterName();checkGetterIdNo();checkGetterMobile();checkTruchLicenseNo();checkGetterEmail();
        }
    } 
}

function removeReceiverHandler(){
	if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			if (xhr_receiver_getter.responseText != "failed"){
				var address = document.getElementById(xhr_receiver_getter.responseText);
				address.parentNode.removeChild(address);
			}
        }
    } 
}

function removeGetterHandler(){
	if( xhr_receiver_getter.readyState == 4 ){     
        if( xhr_receiver_getter.status == 200 ){
			if (xhr_receiver_getter.responseText != "failed"){
				var address = document.getElementById("getter" + xhr_receiver_getter.responseText);
				address.parentNode.removeChild(address);
			}
        }
    } 
}

var selectedRegionId = -1;
var selectedProvinceId = -1;
var selectedCityId = -1;

function addToReceiverDialog(params){
	var infos = new Array();
	infos = params.split(",");
	selectedRegionId = infos[2];
	selectRegionDropDown();
	selectedProvinceId = infos[3];
	selectedCityId = infos[4];
	getProvinceAjax(infos[2]);
	getCityAjax(infos[3]);
	document.getElementById("pop_content_receiverid").value = infos[0];
	document.getElementById("pop_content_receivername").value = infos[1];
	document.getElementById("pop_content_address").value = infos[5];
	document.getElementById("pop_content_receivermobileno").value = infos[6];
	document.getElementById("pop_content_email").value = infos[7];
}

function selectRegionDropDown(){
	var regionDropdown = document.getElementById("pop_content_region_dropdown");
	for (var i = 0; i < regionDropdown.options.length; i++){
		if (regionDropdown.options[i].value == selectedRegionId){
			regionDropdown.options[i].selected = true;
		}
	}
}

function addToGetterDialog(params){
	var infos = new Array();
	infos = params.split(",");
	document.getElementById("pop_content_getterid").value = infos[0];
	document.getElementById("pop_content_gettername").value = infos[1];
	document.getElementById("pop_content_getteridno").value = infos[2];
	document.getElementById("pop_content_gettermobileno").value = infos[3];
	document.getElementById("pop_content_trucklicenseno").value = infos[4];
	document.getElementById("pop_content_getteremail").value = infos[5];
}

var isReceiverNameValid = false;
var isReceiverCityValid = false;
var isAddressValid = false;
var isReceiverAddressValid = false;
var isReceiverEmailValid = false;
var isGetterNameValid = false;
var isGetterIdNoValid = false;
var isGetterMobileValid = false;
var isTruckLicenseNoValid = false;
var isGetterEmailValid = false;

function checkReceiverName(){
	var username = document.getElementById("pop_content_receivername").value;
	if (isValidReceiverGetterName(username)){
		document.getElementById("pop_content_input_tip_receiver").className = "pop_content_input_tip_hidden";
		isReceiverNameValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_receiver").className = "pop_content_input_tip";
		isReceiverNameValid = false;
	}
}

function isValidReceiverGetterName(str){
	if (str.length < 2 || str.length > 15){
		return false;
	}
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]{2,15}/;
	return reg.test(str);
}

function checkCity(){
	var regionDropdown = document.getElementById("pop_content_region_dropdown");
	var provinceDropdown = document.getElementById("pop_content_province_dropdown");
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	if (regionDropdown.value == -1 || provinceDropdown.value == -1 || cityDropdown == -1){
		isReceiverCityValid = false;
		return;
	}
	isReceiverCityValid = true;
}

function checkAddress(){
	var address = document.getElementById("pop_content_address").value; 
	if (isValidAddress(address)){
		document.getElementById("pop_content_input_tip_address").className = "pop_content_input_tip_hidden";
		isAddressValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_address").className = "pop_content_input_tip";
		isAddressValid = false;
	}
}

function isValidAddress(str){
	var reg = /^[a-zA-Z\u4E00-\u9FA5\uF900-\uFA2D]{1}([a-zA-Z0-9\u0391-\uFFE5\(\)\[\],\-\.]|[._]){4,50}$/;
	return reg.test(str);
}

function checkReceiverMobile(){
	var mobile = document.getElementById("pop_content_receivermobileno").value; 
	if (isValidMobile(mobile)){
		document.getElementById("pop_content_input_tip_mobile").className = "pop_content_input_tip_hidden";
		isReceiverAddressValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_mobile").className = "pop_content_input_tip";
		isReceiverAddressValid = false;
	}
}

function isValidMobile(str){
	if (str.length != 11){
		return false;
	}
	var reg = /^1[0-9]{10}/;
	return reg.test(str);
}

function checkReceiverEmail(){
	var email = document.getElementById("pop_content_email").value; 
	if (isValidEmail(email)){
		document.getElementById("pop_content_input_tip_email").className = "pop_content_input_tip_hidden";
		isReceiverEmailValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_email").className = "pop_content_input_tip";
		isReceiverEmailValid = false;
	}
}

function isValidEmail(str){
	var reg = /[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z0-9]{1,20}(\.[a-zA-Z0-9]{1,20}){0,1}$/;
	return reg.test(str);
}

function checkGetterName(){
	var username = document.getElementById("pop_content_gettername").value;
	if (isValidReceiverGetterName(username)){
		document.getElementById("pop_content_input_tip_getter").className = "pop_content_input_tip_hidden";
		isGetterNameValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_getter").className = "pop_content_input_tip";
		isGetterNameValid = false;
	}
}

function checkGetterIdNo(){
	var idNo = document.getElementById("pop_content_getteridno").value;
	if (isValidIdNo(idNo)){
		document.getElementById("pop_content_input_tip_idno").className = "pop_content_input_tip_hidden";
		isGetterIdNoValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_idno").className = "pop_content_input_tip";
		isGetterIdNoValid = false;
	}
}

function isValidIdNo(str){
	if (str.length != 15 && str.length != 18){
		return false;
	}
	var reg = /[0-9]*/;
	return reg.test(str);
}

function checkGetterMobile(){
	var mobile = document.getElementById("pop_content_gettermobileno").value; 
	if (isValidMobile(mobile)){
		document.getElementById("pop_content_input_tip_getter_mobile").className = "pop_content_input_tip_hidden";
		isGetterMobileValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_getter_mobile").className = "pop_content_input_tip";
		isGetterMobileValid = false;
	}
}

function checkTruchLicenseNo(){
	var licenseNo = document.getElementById("pop_content_trucklicenseno").value; 
	if (isValidTruckLicenseNo(licenseNo)){
		document.getElementById("pop_content_input_tip_car_licence").className = "pop_content_input_tip_hidden";
		isTruckLicenseNoValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_car_licence").className = "pop_content_input_tip";
		isTruckLicenseNoValid = false;
	}
}

function isValidTruckLicenseNo(str){
	var reg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	return reg.test(str);
}

function checkGetterEmail(){
	var email = document.getElementById("pop_content_getteremail").value; 
	if (isValidEmail(email)){
		document.getElementById("pop_content_input_tip_getter_email").className = "pop_content_input_tip_hidden";
		isGetterEmailValid = true;
	}
	else {
		document.getElementById("pop_content_input_tip_getter_email").className = "pop_content_input_tip";
		isGetterEmailValid = false;
	}
}

function regionSelect(){
	var provinceDropdown = document.getElementById("pop_content_province_dropdown");
	for(var i= provinceDropdown.options.length - 1; i>=0 ; i--){
		provinceDropdown.options.remove(i);
	}
	var opt = document.createElement("option");
		opt.setAttribute("value", "-1");
		opt.innerHTML = "请选择";
		provinceDropdown.appendChild(opt);
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	for(var i= cityDropdown.options.length - 1; i>=0 ; i--){
		cityDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "-1");
		opt1.innerHTML = "请选择";
		cityDropdown.appendChild(opt1);
	getProvinceAjax(document.getElementById("pop_content_region_dropdown").value);
}

function provinceSelect(){
	var cityDropdown = document.getElementById("pop_content_city_dropdown");
	for(var i= cityDropdown.options.length - 1; i>=0 ; i--){
		cityDropdown.options.remove(i);
	}
	var opt1 = document.createElement("option");
		opt1.setAttribute("value", "-1");
		opt1.innerHTML = "请选择";
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
		if (keyAndValue[0] == selectedProvinceId){
			opt.setAttribute("selected", "selected");
		}
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
		if (keyAndValue[0] == selectedCityId){
			opt.setAttribute("selected", "selected");
		}
		cityDropdown.appendChild(opt);
	}
}

function openConfirmDialog(){
	var deliveryType = 3;
	var receiverId = -1;
	var getterId = -1;
	
	if (document.getElementById("vendor_delivery_button").className == "order_page_button_regular_selected")
	{
		deliveryType = 1;
	}
//	else if (document.getElementById("zhixuan_delivery_button").className == "order_page_button_regular_selected"){
//		deliveryType = 2;
//	}
	else if (document.getElementById("get_byuser_button").className == "order_page_button_regular_selected"){
		deliveryType = 3;
	}
	else {
		return;
	}
	
	if (deliveryType == 1 || deliveryType == 2){
		var addresses = document.getElementsByName("order_page_address");
		if (addresses == null || addresses.length == 0){
			alert("请先创建并选择一个收货人");
			return;
		}
		for (var i = 0; i < addresses.length; i++){
			var childButton = document.getElementById("order_page_address_child" + addresses[i].id);
			if (childButton.className == "order_page_button_large_selected"){
				receiverId = addresses[i].id;
			}
		}
		if (receiverId == -1){
			alert("请选择一个收货人");
			return;
		}
	}
	else if (deliveryType == 3){
		var addresses = document.getElementsByName("order_page_address_getter");
		if (addresses == null || addresses.length == 0){
			alert("请先创建并选择一个提货人");
			return;
		}
		for (var i = 0; i < addresses.length; i++){
			var childButton = document.getElementById("order_page_address_child_" + addresses[i].id);
			if (childButton.className == "order_page_button_large_selected"){
				getterId = addresses[i].id.substring(6);
			}
		}
		if (getterId == -1){
			alert("请选择一个提货人");
			return;
		}
	}
	else {
		return;
	}
	
	if (invoiceId == -1){
		alert("请修改发票信息");
		document.getElementById("loading_pic").className = "pop_dialog_frame";
		return;
	}
	document.getElementById("pop_dialog_confirm").className = "pop_dialog_frame_show";
}

function closeConfirmDialog(){
	document.getElementById("pop_dialog_confirm").className = "pop_dialog_frame";
}

function showInvoiceDialog(){
	document.getElementById("pop_dialog_invoice").className = "pop_dialog_frame_show";
}

function closeInvoiceDialog(){
	document.getElementById("pop_dialog_invoice").className = "pop_dialog_frame";
}

function saveInvoice(){
	var invoiceType = document.getElementById("pop_invoice_type_dropdown").value;
	var invoiceCompany = document.getElementById("pop_content_input_invoice_company").value;
	var invoiceAccountName = document.getElementById("pop_content_input_invoice_accountname").value;
	var invoiceAccountBank = document.getElementById("pop_content_input_invoice_accountbank").value;
	var invoiceAddress = document.getElementById("pop_content_input_invoice_address").value;
	var invoiceTaxerId = document.getElementById("pop_content_input_invoice_taxerid").value;
	var invoicePhone = document.getElementById("pop_content_input_invoice_phone").value;
	if (invoiceType == 1){
		checkCompany();
		if (!isInvoiceCompanyValid){
			return;
		}
		saveInvoiceAjax(invoiceType, invoiceCompany, invoiceAccountName, invoiceAccountBank, invoiceAddress, invoiceTaxerId, invoicePhone);
	}
	else if (invoiceType == 2){
		checkAccountName();
		checkAccountBank();
		checkInvoiceAddress();
		checkTaxerId();
		checkPhone();
		if (!isInvoiceAccountNameValid || !isInvoiceAccountBankValid || !isInvoiceAddressValid || !isInvoiceTaxerIdValid || !isInvoicePhoneValid){
			return;
		}
		saveInvoiceAjax(invoiceType, invoiceCompany, invoiceAccountName, invoiceAccountBank, invoiceAddress, invoiceTaxerId, invoicePhone);
	}
	else {
		return;
	}
}

var xhr_invoice; 

function createInvoiceXHR(){    
    if( window.ActiveXObject ){  
        xhr_invoice = new ActiveXObject( "Microsoft.XMLHTTP" ) ;  
        return 1;  
    }else if( window.XMLHttpRequest ){  
        xhr_invoice = new XMLHttpRequest();  
        return 2;    
    }else{  
        return 0;  
    }        
}

function saveInvoiceAjax(invoiceType, invoiceCompany, invoiceAccountName, invoiceAccountBank, invoiceAddress, invoiceTaxerId, invoicePhone){
	if (xhr_invoice == undefined){
		if(createInvoiceXHR() == 0){
			return true; 
		} 
	}
	xhr_invoice.onreadystatechange = saveInvoiceHandler;
	xhr_invoice.open("post", "ReceiverGetterAjax_addInvoice.action?invoiceType=" + invoiceType
		+ "&invoiceCompany=" + encodeURI(invoiceCompany) + "&invoiceAccountName=" + encodeURI(invoiceAccountName) + "&invoiceAccountBank=" + encodeURI(invoiceAccountBank) 
		+ "&invoiceAddress=" + encodeURI(invoiceAddress) + "&invoiceTaxerId=" + encodeURI(invoiceTaxerId) + "&invoicePhone=" + encodeURI(invoicePhone));
	xhr_invoice.send();
}

function saveInvoiceHandler(){    
    if( xhr_invoice.readyState == 4 ){     
        if( xhr_invoice.status == 200 ){ 
            result = xhr_invoice.responseText;
			var infos = new Array();
			infos = result.split(",");
			invoiceId = infos[0];
			invoiceType = document.getElementById("pop_invoice_type_dropdown").value;
			if (invoiceType == '1'){
				document.getElementById("order_page_invoice_info_invoicetype").innerHTML = '普通发票';
				document.getElementById("order_page_invoice_info_invoicecompany").innerHTML = infos[1];
				document.getElementById("order_page_invoice_info_invoiceaccount").innerHTML = infos[2];
				document.getElementById("order_page_invoice_info_invoicebank").innerHTML = infos[3];
				document.getElementById("order_page_invoice_info_invoiceaddress").innerHTML = infos[4];
				document.getElementById("order_page_invoice_info_invoicetaxerid").innerHTML = infos[5];
				document.getElementById("order_page_invoice_info_invoicephone").innerHTML = infos[6];
				document.getElementById("order_page_invoice_info_invoicecompany_wrapper").className = "order_page_item_invoice_detail";
				document.getElementById("order_page_invoice_info_invoiceaccount_wrapper").className = "order_page_item_invoice_detail_hidden";
				document.getElementById("order_page_invoice_info_invoicebank_wrapper").className = "order_page_item_invoice_detail_hidden";
				document.getElementById("order_page_invoice_info_invoiceaddress_wrapper").className = "order_page_item_invoice_detail_hidden";
				document.getElementById("order_page_invoice_info_invoicetaxerid_wrapper").className = "order_page_item_invoice_detail_hidden";
				document.getElementById("order_page_invoice_info_invoicephone_wrapper").className = "order_page_item_invoice_detail_hidden";
			}
			else if (invoiceType == '2'){
				document.getElementById("order_page_invoice_info_invoicetype").innerHTML = '增值税发票';
				document.getElementById("order_page_invoice_info_invoicecompany").innerHTML = infos[1];
				document.getElementById("order_page_invoice_info_invoiceaccount").innerHTML = infos[2];
				document.getElementById("order_page_invoice_info_invoicebank").innerHTML = infos[3];
				document.getElementById("order_page_invoice_info_invoiceaddress").innerHTML = infos[4];
				document.getElementById("order_page_invoice_info_invoicetaxerid").innerHTML = infos[5];
				document.getElementById("order_page_invoice_info_invoicephone").innerHTML = infos[6];
				document.getElementById("order_page_invoice_info_invoicecompany_wrapper").className = "order_page_item_invoice_detail_hidden";
				document.getElementById("order_page_invoice_info_invoiceaccount_wrapper").className = "order_page_item_invoice_detail";
				document.getElementById("order_page_invoice_info_invoicebank_wrapper").className = "order_page_item_invoice_detail";
				document.getElementById("order_page_invoice_info_invoiceaddress_wrapper").className = "order_page_item_invoice_detail";
				document.getElementById("order_page_invoice_info_invoicetaxerid_wrapper").className = "order_page_item_invoice_detail";
				document.getElementById("order_page_invoice_info_invoicephone_wrapper").className = "order_page_item_invoice_detail";
			}
			closeInvoiceDialog();
        }
    }  
}

function changeInvoiceType(){
	var invoiceType = document.getElementById("pop_invoice_type_dropdown").value;
	if (invoiceType == 1){
		document.getElementById("pop_dialog_invoice_dialog").className = "pop_dialog_invoice";
		document.getElementById("pop_content_item_invoice_company").className = "pop_content_item";
		document.getElementById("pop_content_item_invoice_accountname").className = "pop_content_item_hidden";
		document.getElementById("pop_content_item_invoice_accountbank").className = "pop_content_item_hidden";
		document.getElementById("pop_content_item_invoice_address").className = "pop_content_item_hidden";
		document.getElementById("pop_content_item_invoice_taxerid").className = "pop_content_item_hidden";
		document.getElementById("pop_content_item_invoice_phone").className = "pop_content_item_hidden";
	}
	else if (invoiceType == 2){
		document.getElementById("pop_dialog_invoice_dialog").className = "pop_dialog_invoice_tax";
		document.getElementById("pop_content_item_invoice_company").className = "pop_content_item_hidden";
		document.getElementById("pop_content_item_invoice_accountname").className = "pop_content_item";
		document.getElementById("pop_content_item_invoice_accountbank").className = "pop_content_item";
		document.getElementById("pop_content_item_invoice_address").className = "pop_content_item";
		document.getElementById("pop_content_item_invoice_taxerid").className = "pop_content_item";
		document.getElementById("pop_content_item_invoice_phone").className = "pop_content_item";
	}
	else {
		return;
	}
}

var invoiceId = -1;
var isInvoiceCompanyValid = false;
var isInvoiceAccountNameValid = false;
var isInvoiceAccountBankValid = false;
var isInvoiceAddressValid = false;
var isInvoiceTaxerIdValid = false;
var isInvoicePhoneValid = false;

function checkCompany(){
	var newCompany = document.getElementById("pop_content_input_invoice_company").value;
	if (newCompany.length < 5 || newCompany.length > 50){
		document.getElementById("pop_content_input_invoice_tip").className = "pop_content_input_tip";
		isInvoiceCompanyValid = false;
	}
	var reg = /^([a-zA-Z0-9\u4E00-\u9FA5\uF900-\uFA2D]|[._]){5,50}$/;
	if (reg.test(newCompany)){
		document.getElementById("pop_content_input_invoice_tip").className = "pop_content_input_tip_hidden";
		isInvoiceCompanyValid = true;
	}
	else {
		document.getElementById("pop_content_input_invoice_tip").className = "pop_content_input_tip";
		isInvoiceCompanyValid = false;
	}
}

function checkAccountName(){
	var username = document.getElementById("pop_content_input_invoice_accountname").value;
	if (isValidReceiverGetterName(username)){
		document.getElementById("pop_content_input_invoice_tip_accountname").className = "pop_content_input_tip_hidden";
		isInvoiceAccountNameValid = true;
	}
	else {
		document.getElementById("pop_content_input_invoice_tip_accountname").className = "pop_content_input_tip";
		isInvoiceAccountNameValid = false;
	}
}

function checkAccountBank(){
	var username = document.getElementById("pop_content_input_invoice_accountbank").value;
	if (isValidReceiverGetterName(username)){
		document.getElementById("pop_content_input_invoice_tip_accountbank").className = "pop_content_input_tip_hidden";
		isInvoiceAccountBankValid = true;
	}
	else {
		document.getElementById("pop_content_input_invoice_tip_accountbank").className = "pop_content_input_tip";
		isInvoiceAccountBankValid = false;
	}
}

function checkInvoiceAddress(){
	var username = document.getElementById("pop_content_input_invoice_address").value;
	if (isValidAddress(username)){
		document.getElementById("pop_content_input_invoice_tip_address").className = "pop_content_input_tip_hidden";
		isInvoiceAddressValid = true;
	}
	else {
		document.getElementById("pop_content_input_invoice_tip_address").className = "pop_content_input_tip";
		isInvoiceAddressValid = false;
	}
}

function checkTaxerId(){
	var username = document.getElementById("pop_content_input_invoice_taxerid").value;
	if (isValidTaxerId(username)){
		document.getElementById("pop_content_input_invoice_tip_taxerid").className = "pop_content_input_tip_hidden";
		isInvoiceTaxerIdValid = true;
	}
	else {
		document.getElementById("pop_content_input_invoice_tip_taxerid").className = "pop_content_input_tip";
		isInvoiceTaxerIdValid = false;
	}
}

function isValidTaxerId(str){
	if (str.length < 12 || str.length > 25){
		return false;
	}
	var reg = /^[0-9]{12,25}/;
	return reg.test(str);
}

function checkPhone(){
	var username = document.getElementById("pop_content_input_invoice_phone").value;
	if (isValidInvoicePhone(username)){
		document.getElementById("pop_content_input_invoice_tip_phone").className = "pop_content_input_tip_hidden";
		isInvoicePhoneValid = true;
	}
	else {
		document.getElementById("pop_content_input_invoice_tip_phone").className = "pop_content_input_tip";
		isInvoicePhoneValid = false;
	}
}

function isValidInvoicePhone(str){
	if (str.length < 6 || str.length > 20){
		return false;
	}
	var reg = /^[0-9]{6,20}/;
	return reg.test(str);
}

function submitOrder(productId, quantity){
	closeConfirmDialog();
	document.getElementById("loading_pic").className = "pop_dialog_frame_show";

	var deliveryType = 3;
	var receiverId = -1;
	var getterId = -1;
	var paymentType = -1;
	var selectedIds = document.getElementById("selected_ids").value;
	
	if (document.getElementById("vendor_delivery_button").className == "order_page_button_regular_selected")
	{
		deliveryType = 1;
	}
//	else if (document.getElementById("zhixuan_delivery_button").className == "order_page_button_regular_selected"){
//		deliveryType = 2;
//	}
	else if (document.getElementById("get_byuser_button").className == "order_page_button_regular_selected"){
		deliveryType = 3;
	}
	else {
		document.getElementById("loading_pic").className = "pop_dialog_frame";
		return;
	}
	
	if (deliveryType == 1 || deliveryType == 2){
		var addresses = document.getElementsByName("order_page_address");
		if (addresses == null || addresses.length == 0){
			document.getElementById("loading_pic").className = "pop_dialog_frame";
			alert("请先创建并选择一个收货人");
			return;
		}
		for (var i = 0; i < addresses.length; i++){
			var childButton = document.getElementById("order_page_address_child" + addresses[i].id);
			if (childButton.className == "order_page_button_large_selected"){
				receiverId = addresses[i].id;
			}
		}
		if (receiverId == -1){
			document.getElementById("loading_pic").className = "pop_dialog_frame";
			alert("请选择一个收货人");
			return;
		}
	}
	else if (deliveryType == 3){
		var addresses = document.getElementsByName("order_page_address_getter");
		if (addresses == null || addresses.length == 0){
			document.getElementById("loading_pic").className = "pop_dialog_frame";
			alert("请先创建并选择一个提货人");
			return;
		}
		for (var i = 0; i < addresses.length; i++){
			var childButton = document.getElementById("order_page_address_child_" + addresses[i].id);
			if (childButton.className == "order_page_button_large_selected"){
				getterId = addresses[i].id.substring(6);
			}
		}
		if (getterId == -1){
			document.getElementById("loading_pic").className = "pop_dialog_frame";
			alert("请选择一个提货人");
			return;
		}
	}
	else {
		document.getElementById("loading_pic").className = "pop_dialog_frame";
		return;
	}
	
	if (document.getElementById("order_page_payment_onlinepay").className == "order_page_button_regular_selected")
	{
		paymentType = 1;
	}
	else if (document.getElementById("order_page_payment_transfer").className == "order_page_button_regular_selected"){
		paymentType = 2;
	}
//	else if (document.getElementById("order_page_payment_acceptance_bill").className == "order_page_button_regular_selected"){
//		paymentType = 3;
//	}
	else if (document.getElementById("depositpay_checkbox_id").checked == true){
		paymentType = 4;
	}
	else {
		document.getElementById("loading_pic").className = "pop_dialog_frame";
		return;
	}
	
	
	var actionandparams = "";
	var orderForm = document.getElementById("submit_order");
	orderForm.method="post";

	if (deliveryType == 1 || deliveryType == 2){
		actionandparams="OrderSubmit.action?deliveryType=" + deliveryType + "&receiverId=" + receiverId + "&paymentType=" + paymentType + 
			"&totalPrice=" + totalProductFee + "&logisticFee=" + logisticFee_temp + "&selectedIds=" + selectedIds + "&productId=" + productId + 
			"&quantity=" + quantity + "&invoiceId=" + invoiceId;
	}
	else if (deliveryType == 3){
		actionandparams="OrderSubmit.action?deliveryType=" + deliveryType + "&getterId=" + getterId + "&paymentType=" + paymentType + 
			"&totalPrice=" + totalProductFee + "&logisticFee=" + logisticFee_temp + "&selectedIds=" + selectedIds + "&productId=" + productId + 
			"&quantity=" + quantity + "&invoiceId=" + invoiceId;;
	}
	else {
		document.getElementById("loading_pic").className = "pop_dialog_frame";
		return;
	}
	
	actionandparams += "&acBillServiceFee=" + acBillServiceFee_temp;
	orderForm.action = actionandparams;
	orderForm.submit();
}

function depositPaySelect(obj, depositAmount){
	var totalFee = parseFloat(totalProductFee) + parseFloat(logisticFee_temp) +  parseFloat(acBillServiceFee_temp);
	if (depositAmount < totalFee){
//		document.getElementById("recharge_button_id").className = "recharge_button";
		if (obj.checked){
			obj.checked = false;
		}
		alert("您的余额不足以支付此订单，请充值后再购买");
		return;
	}
	if (obj.checked){
		document.getElementById("order_page_payment_onlinepay").className = "order_page_button_regular";
		document.getElementById("order_page_payment_transfer").className = "order_page_button_regular";
		document.getElementById("order_page_payment_tip").innerHTML = "";
	}
	else{
		document.getElementById("order_page_payment_onlinepay").className = "order_page_button_regular_selected";
		document.getElementById("order_page_payment_transfer").className = "order_page_button_regular";
		document.getElementById("order_page_payment_tip").innerHTML = "直接网上支付，即时到账。支持绝大多数银行。";
	}
	
}