var selectedBank = "";

function selectBank(bank, imgObj){
	if (selectedBank != ""){
		document.getElementById("bank_img_" + selectedBank).className = "bank_logo";
	}
	selectedBank = bank;
	imgObj.className = "bank_logo_selected";
}

function toPayOnline(poNo, totalPrice){
	if (selectedBank == ""){
		alert("请选择付款银行");
		return;
	}
	var topayForm = document.getElementById("pay_pad_topay_online_form");
	topayForm.method="post";
	topayForm.action="OnlinePaySubmit.action?poNo=" + poNo + "&totalPrice=" + totalPrice + "&bank=" + selectedBank;
	topayForm.submit();
}