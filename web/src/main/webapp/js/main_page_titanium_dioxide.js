function selectCompanyList(){
	document.getElementById("content_ad_low_title_company").className = "content_ad_low_title_text";
	document.getElementById("content_ad_low_title_merchant").className = "content_ad_low_title_text_unselected";
	document.getElementById("company_list_pad").className = "slider_low_wrapper";
	document.getElementById("merchant_list_pad").className = "slider_low_wrapper_hidden";
}

function selectMerchantList(){
	document.getElementById("content_ad_low_title_company").className = "content_ad_low_title_text_unselected";
	document.getElementById("content_ad_low_title_merchant").className = "content_ad_low_title_text";
	document.getElementById("company_list_pad").className = "slider_low_wrapper_hidden";
	document.getElementById("merchant_list_pad").className = "slider_low_wrapper";
}