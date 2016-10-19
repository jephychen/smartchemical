<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/order_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/order_page.js" charset="UTF-8"></script>
</head>

<body onload="onload(${totalProductPrice}, ${logisticFee}, ${acBillServiceFee}, ${invoiceId})">
<%@ include file="./common/header.jsp" %>

<div id="total_cart">	
<div id="total_wrapper">
	<div id="order_page">
		<div id="order_page_header">
			<div id="order_page_logo">
				<a href="TitaniumMainPage.action" id="header_logo_link"><img id="company_logo" src="./img/logo_blue.png" /></a>
			</div>
			<div id="order_page_title">
				<span id="cart_title_span">结算页</span>
			</div>
		</div>
		<div id="order_page_content">
			<div id="order_page_content_detail">
				<div id="order_page_content_detail_text">填写并核对订单信息</div>
			</div>
			<div id="order_page_content_items">
				<div class="order_page_item">
					<div class="order_page_item_text">
						配送方式
					</div>
					<div class="order_page_item_detail">
						<div class="order_page_logistic_wrapper">
							<div id="vendor_delivery_button" class="order_page_button_regular_selected" onclick="vendorDeliver()">
								厂商配送
							</div>
							<!--<div id="zhixuan_delivery_button" class="order_page_button_regular" onclick="zhixuanDeliver()">
								智选物流
							</div>-->
							<div id="get_byuser_button" class="order_page_button_regular" onclick="getByUser()">
								用户自提
							</div>
						</div>
						<div id="delivery_tip" class="order_page_item_address_more">
							厂商负责配送，不收取配送费
						</div>
					</div>
				</div>
				<div id="order_receiver" class="order_page_item">
					<div class="order_page_item_text">
						收货人信息
						<div class="order_page_item_add_address_button" onclick="addAddress()">添加收货人</div>
					</div>
					<div id="order_page_item_detail_receivers" class="order_page_item_detail">
						<div id="order_page_item_detail_receivers_wrapper">
						<s:iterator value="cartReceivers" id="receiver" status="st">
							<div id="${receiver.receiverId}" name="order_page_address" 
							<s:if test="#st.first">
								class="order_page_item_address_wrapper" 
							</s:if>
							<s:else>
								class="order_page_item_address_wrapper_hidden" 
							</s:else>
							>
								<div id="order_page_address_child${receiver.receiverId}" name="order_page_address_child" 
								<s:if test="#st.first">
									class="order_page_button_large_selected" 
								</s:if>
								<s:else>
									class="order_page_button_large" 
								</s:else>
								onclick="selectAddress(${receiver.receiverId})">
									${receiver.receiverName} ${receiver.city.province.provinceName}-${receiver.city.cityName}
								</div>
								<div class="order_page_item_address_content_wrapper">
									<div id="order_page_item_receiver_detail${receiver.receiverId}" class="order_page_item_address_content">
										${receiver.receiverName} ${receiver.city.province.provinceName}-${receiver.city.cityName}-${receiver.address} 手机：${receiver.mobileNo}
									</div>
									<div class="order_page_item_address_content_right" onclick="removeAddress(${receiver.receiverId})">
										删除
									</div>
									<div class="order_page_item_address_content_right" onclick="editAddress(${receiver.receiverId})">
										编辑
									</div>
								</div>
							</div>
						</s:iterator>
						</div>
						<div id="address_more_button" class="order_page_item_address_more" onclick="addressMore()">
							更多地址 <img src="./img/more_down.png" />
						</div>
						<div id="address_less_button" class="order_page_item_address_more_hidden" onclick="addressLess()">
							收起地址 <img src="./img/more_up.png" />
						</div>
					</div>
				</div>
				<div id="order_getter" class="order_page_item_hidden">
					<div class="order_page_item_text">
						提货人信息
						<div class="order_page_item_add_address_button" onclick="addAddressGetter()">添加提货人</div>
					</div>
					<div id="order_page_item_detail_getters" class="order_page_item_detail">
						<div id="order_page_item_detail_getters_wrapper">
						<s:iterator value="cartGetters" id="getter" status="st">
							<div id="getter${getter.getterId}" name="order_page_address_getter" 
							<s:if test="#st.first">
								class="order_page_item_address_wrapper" 
							</s:if>
							<s:else>
								class="order_page_item_address_wrapper_hidden" 
							</s:else>
							>
								<div id="order_page_address_child_getter${getter.getterId}" name="order_page_address_child_getter" 
								<s:if test="#st.first">
									class="order_page_button_large_selected"  
								</s:if>
								<s:else>
									class="order_page_button_large" 
								</s:else>
								onclick="selectAddressGetter(${getter.getterId})">
									${getter.getterName} ${getter.getterMobileNo}
								</div>
								<div class="order_page_item_address_content_wrapper">
									<div id="order_page_item_getter_detail${getter.getterId}" class="order_page_item_address_content">
										${getter.getterName} 身份证：${getter.getterIdNo} 手机：${getter.getterMobileNo} 车牌号：${getter.truckLicenseNo}
									</div>
									<div class="order_page_item_address_content_right" onclick="removeAddressGetter(${getter.getterId})">
										删除
									</div>
									<div class="order_page_item_address_content_right" onclick="editAddressGetter(${getter.getterId})">
										编辑
									</div>
								</div>
							</div>
						</s:iterator>
						</div>
						<div id="address_more_button_getter" class="order_page_item_address_more" onclick="addressMoreGetter()">
							更多地址 <img src="./img/more_down.png" />
						</div>
						<div id="address_less_button_getter" class="order_page_item_address_more_hidden" onclick="addressLessGetter()">
							收起地址 <img src="./img/more_up.png" />
						</div>
					</div>
				</div>
				<div class="order_page_item">
					<div class="order_page_item_text">
						支付方式/配送清单
					</div>
					<div class="order_page_item_detail">
						<div class="order_page_payment">
							<div id="order_page_payment_onlinepay" class="order_page_button_regular_selected" onclick="onlinePay()">
								在线支付
							</div>
							<div id="order_page_payment_transfer" class="order_page_button_regular" onclick="transferPay()">
								银行转账
							</div>
							<!--<div id="order_page_payment_acceptance_bill" class="order_page_button_regular" onclick="acceptanceBill()">
								银行承兑
							</div>-->
							<div class="depositpay_area">
								<div class="depositpay_text">当前智选账户余额：<font class="font_red">${userDeposit}</font>&nbsp元 &nbsp&nbsp&nbsp&nbsp <font id="recharge_button_id" class="hide_me">充值</font></div>
								<input id="depositpay_checkbox_id" class="depositpay_checkbox_icon" type="checkbox" onclick="depositPaySelect(this, ${userDeposit})" ><div class="depositpay_checkbox">使用余额支付</div></input>
							</div>
							<div class="order_page_payment_tip_wrapper">
								<div id="order_page_payment_tip" class="order_page_payment_tip">
									直接网上支付，即时到账。支持绝大多数银行。推荐使用此付款方式。
								</div>
							</div>
						</div>
						<div class="order_page_product_list">
							<s:iterator value="cartProducts" id="cart">
							<div class="order_page_product_item">
								<div class="order_page_product_item_img_col">
									<a href="ProductDetail.action?productId=${cart.product.productId}" target="_blank"><img class="order_page_product_item_img" src="${cart.product.pictureUrl}" /></a>
								</div>
								<div class="order_page_product_item_product_name_col">
									<div class="order_page_product_link_wrapper">
										<a class="order_page_product_link_wrapper" href="ProductDetail.action?productId=${cart.product.productId}"  target="_blank"><font class="order_page_font_gray">${cart.product.productName}  ${cart.product.description}</font></a>
									</div>
									<div class="order_page_product_link_wrapper">
										<s:if test='#cart.product.merchantCompany != ""'>
											<font class="order_page_font_gray">${cart.product.brand.brandName}</font>
										</s:if>
										<s:else>
											<font class="order_page_font_gray">${cart.product.brand.brandName}</font>
											<a class="order_page_product_link_wrapper" href="Company.action?companyId=${cart.product.merchantCompany.companyId}"  target="_blank" ><font class="order_page_font_red">&nbsp;经销商：${cart.product.merchantCompany.companyName}</font></a>
										</s:else>
									</div>
									<div class="order_page_product_link_wrapper">
										<font class="font_blue font_style_yahei">仓库：${cart.product.warehouse.city.cityName}-${cart.product.warehouse.address}</font>
									</div>
								</div>
								<div class="order_page_product_item_price_col">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${cart.product.price}</div></font>
								</div>
								<div class="order_page_product_item_quantity_col">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${cart.quantity} ${cart.product.quantityUnit.unitName}</div></font>
								</div>
							</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="order_page_item">
					<div class="order_page_item_text_left">
						发票信息
					</div>
					<div class="order_page_item_text_right hover_pointer" onclick="showInvoiceDialog()">
						修改
					</div>
					<div class="order_page_item_invoice_detail">
						<div class="order_page_item_address_content">
							<s:if test='invoice.invoiceType == "1"'>
								<div class="order_page_invoice_info">发票类型：</div><div id="order_page_invoice_info_invoicetype" class="order_page_invoice_info_right">普通发票</div>
							</s:if>
							<s:elseif test='invoice.invoiceType == "2"'>
								<div class="order_page_invoice_info">发票类型：</div><div id="order_page_invoice_info_invoicetype" class="order_page_invoice_info_right">增值税发票</div>
							</s:elseif>
							<s:else>
								<div class="order_page_invoice_info">发票类型：</div><div id="order_page_invoice_info_invoicetype" class="order_page_invoice_info_right">普通发票</div>
							</s:else>
						</div>
					</div>
					<s:if test='invoice.invoiceType == "1"'>
						<div id="order_page_invoice_info_invoicecompany_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">公司名：</div><div id="order_page_invoice_info_invoicecompany" class="order_page_invoice_info_right">${invoice.invoiceCompany}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaccount_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户名：</div><div id="order_page_invoice_info_invoiceaccount" class="order_page_invoice_info_right">${invoice.invoiceAccountName}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicebank_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户银行：</div><div id="order_page_invoice_info_invoicebank" class="order_page_invoice_info_right">${invoice.invoiceAccountBank}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaddress_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">地址：</div><div id="order_page_invoice_info_invoiceaddress" class="order_page_invoice_info_right">${invoice.invoiceAddress}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicetaxerid_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">纳税人识别号：</div><div id="order_page_invoice_info_invoicetaxerid" class="order_page_invoice_info_right">${invoice.invoiceTaxerId}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicephone_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">电话：</div><div id="order_page_invoice_info_invoicephone" class="order_page_invoice_info_right">${invoice.invoicePhone}</div>
							</div>
						</div>
					</s:if>
					<s:elseif test='invoice.invoiceType == "2"'>
						<div id="order_page_invoice_info_invoicecompany_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">公司名：</div><div id="order_page_invoice_info_invoicecompany" class="order_page_invoice_info_right">${invoice.invoiceCompany}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaccount_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户名：</div><div id="order_page_invoice_info_invoiceaccount" class="order_page_invoice_info_right">${invoice.invoiceAccountName}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicebank_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户银行：</div><div id="order_page_invoice_info_invoicebank" class="order_page_invoice_info_right">${invoice.invoiceAccountBank}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaddress_wrapper"  class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">地址：</div><div id="order_page_invoice_info_invoiceaddress" class="order_page_invoice_info_right">${invoice.invoiceAddress}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicetaxerid_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">纳税人识别号：</div><div id="order_page_invoice_info_invoicetaxerid" class="order_page_invoice_info_right">${invoice.invoiceTaxerId}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicephone_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">电话：</div><div id="order_page_invoice_info_invoicephone" class="order_page_invoice_info_right">${invoice.invoicePhone}</div>
							</div>
						</div>
					</s:elseif>
					<s:else>
						<div id="order_page_invoice_info_invoicecompany_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">公司名：</div><div id="order_page_invoice_info_invoicecompany" class="order_page_invoice_info_right">${invoice.invoiceCompany}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaccount_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户名：</div><div id="order_page_invoice_info_invoiceaccount" class="order_page_invoice_info_right">${invoice.invoiceAccountName}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicebank_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户银行：</div><div id="order_page_invoice_info_invoicebank" class="order_page_invoice_info_right">${invoice.invoiceAccountBank}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaddress_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">地址：</div><div id="order_page_invoice_info_invoiceaddress" class="order_page_invoice_info_right">${invoice.invoiceAddress}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicetaxerid_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">纳税人识别号：</div><div id="order_page_invoice_info_invoicetaxerid" class="order_page_invoice_info_right">${invoice.invoiceTaxerId}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicephone_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">电话：</div><div id="order_page_invoice_info_invoicephone" class="order_page_invoice_info_right">${invoice.invoicePhone}</div>
							</div>
						</div>
					</s:else>
					<div class="order_page_item_invoice_detail">
						&nbsp;
					</div>
				</div>
				<div class="order_page_item_total">
					<div class="order_page_item_total_line">
						<div class="order_page_item_total_col_right">
							<div class="float_right">${totalProductPrice}</div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							<div class="float_right">件商品，总价：</div>
							<div class="font_red float_right"><s:property value="cartProducts.size()"/>&nbsp;</div> 
						</div>
					</div>
					<div class="order_page_item_total_line">
						<div class="order_page_item_total_col_right">
							<div id="order_page_logisticfee_div" class="float_right">0</div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							物流费用：
						</div>
					</div>
					<!--<div id="order_page_acbillservicefee_line" class="order_page_item_total_line_hidden">
						<div class="order_page_item_total_col_right">
							<div id="order_page_acbillservicefee_div" class="float_right">0</div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							银行承兑手续费：
						</div>
					</div>-->
					<div class="order_page_item_total_line">
						<div class="order_page_item_total_col_right">
							<div id="order_page_totalfee_1" class="float_right"><s:property value="totalProductPrice"/></div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							应付总额：
						</div>
					</div>
				</div>
				<div class="order_page_item">
					<div id="order_page_content_submit">
						<form id="submit_order">
							<input type="hidden" id="selected_ids" value="${selectedIds}" />
							<div id="submit_button" onclick="openConfirmDialog()">
								提交订单
							</div>
						</form>
						<div id="submit_info">
							应付总价：<font id="order_page_totalfee_2" class="submit_info_red">￥<s:property value="totalProductPrice"/></font>
						</div>
					</div>
				</div>
				<input id="totalProductfee_hidden" type="hidden" value="<s:property value="totalProductPrice"/>" />
				<input id="logisticfee_hidden" type="hidden" value="<s:property value="logisticFee"/>" />
				<input id="acceptancebill_servicefee_rate_hidden" type="hidden" value="<s:property value="acBillServiceFee"/>" />
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
<div id="pop_dialog_frame_order_address" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog">
			<form id="pop_receiver_form">
				<input type="hidden" id="pop_content_receiverid" value="" />
				<div class="pop_title">
					<div class="pop_title_text">编辑收货人信息</div>
					<div id="pop_dialog_frame_order_address_shutdown_button" class="pop_title_toolbar" onclick="shutdownAddressFrame()">
						<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							收货人：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_receivername" class="pop_content_input" type="text" onblur="checkReceiverName()" />
						</div>
						<div id="pop_content_input_tip_receiver" class="pop_content_input_tip_hidden">请填写正确的提货人名字</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							所在地区：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<select id="pop_content_region_dropdown" class="pop_content_dropdown" onchange="regionSelect()" >
								<option value="-1">请选择</option>
								<s:iterator value="regions" id="region">
									<option value="${region.regionId}">${region.regionName}</option>
								</s:iterator>
							</select>
							<select id="pop_content_province_dropdown" class="pop_content_dropdown" onchange="provinceSelect()" >
								<option value="-1">请选择</option>
							</select>
							<select id="pop_content_city_dropdown" class="pop_content_dropdown" >
								<option value="-1">请选择</option>
							</select>
						</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							详细地址：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_address" class="pop_content_input_long" type="text" onblur="checkAddress()" />
						</div>
						<div id="pop_content_input_tip_address" class="pop_content_input_tip_hidden">请填写正确的收货地址</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							手机号码：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_receivermobileno" class="pop_content_input" type="text" onblur="checkReceiverMobile()" />
						</div>
						<div id="pop_content_input_tip_mobile" class="pop_content_input_tip_hidden">请填写正确的手机号码</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							邮箱地址：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_email" class="pop_content_input" type="text" onblur="checkReceiverEmail()" />
						</div>
						<div id="pop_content_input_tip_email" class="pop_content_input_tip_hidden">请填写正确的邮箱</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_button_receiver" class="pop_content_button" type="button" value="保存收货人信息" onclick="saveReceiver()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="pop_dialog_frame_order_address_getter" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog_small">
			<form id="pop_getter_form">
				<input type="hidden" id="pop_content_getterid" value="" />
				<div class="pop_title">
					<div class="pop_title_text">编辑提货人信息</div>
					<div id="pop_dialog_frame_order_address_shutdown_button" class="pop_title_toolbar" onclick="shutdownAddressFrameGetter()">
						<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							提货人：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_gettername" class="pop_content_input" type="text" onblur="checkGetterName()" />
						</div>
						<div id="pop_content_input_tip_getter" class="pop_content_input_tip_hidden">请填写正确的提货人名字</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							身份证号码：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_getteridno" class="pop_content_input" type="text" onblur="checkGetterIdNo()" />
						</div>
						<div id="pop_content_input_tip_idno" class="pop_content_input_tip_hidden">请输入正确的身份证号</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							手机号码：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_gettermobileno" class="pop_content_input" type="text" onblur="checkGetterMobile()" />
						</div>
						<div id="pop_content_input_tip_getter_mobile" class="pop_content_input_tip_hidden">请输入正确的手机号码</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							车牌号：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_trucklicenseno" class="pop_content_input" type="text" onblur="checkTruchLicenseNo()" />
						</div>
						<div id="pop_content_input_tip_car_licence" class="pop_content_input_tip_hidden">请输入正确的车牌号</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							邮箱地址：
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_getteremail" class="pop_content_input" type="text" onblur="checkGetterEmail()" />
						</div>
						<div id="pop_content_input_tip_getter_email" class="pop_content_input_tip_hidden">请输入正确的邮箱</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
						</div>
					</div>
					<div class="pop_content_item_right">
						<div class="pop_content_content">
							<input id="pop_content_button_getter" class="pop_content_button" type="button" value="保存提货人信息" onclick="saveGetter()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="pop_dialog_confirm" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog_confirm">
			<div class="pop_dialog_confirm_header">
				<div class="pop_dialog_confirm_header_title">
					确认提交
				</div>
				<div class="pop_dialog_confirm_header_exit" onclick="closeConfirmDialog()">
					<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
				</div>
			</div>
			<div class="pop_dialog_confirm_content">
				<div class="pop_dialog_confirm_content_text">
					确认订单所有信息并提交订单？
				</div>
				<div class="pop_dialog_confirm_content_buttons">
					<div class="pop_dialog_confirm_content_btn2" onclick="submitOrder(${productId}, ${quantity})">
						确认
					</div>
					<div class="pop_dialog_confirm_content_btn2" onclick="closeConfirmDialog()">
						取消
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="loading_pic" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog_loading">
			<img src="./img/loading.gif" />
		</div>
	</div>
</div>
<div id="pop_dialog_invoice" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<s:if test='invoice.invoiceType == "1"'>
			<div id="pop_dialog_invoice_dialog" class="pop_dialog_invoice">
		</s:if>
		<s:elseif test='invoice.invoiceType == "2"'>
			<div id="pop_dialog_invoice_dialog" class="pop_dialog_invoice_tax">
		</s:elseif>
		<s:else>
			<div id="pop_dialog_invoice_dialog" class="pop_dialog_invoice">
		</s:else>
			<form id="pop_getter_form">
				<div class="pop_title">
					<div class="pop_title_text">编辑发票信息</div>
					<div id="pop_dialog_frame_order_address_shutdown_button" class="pop_title_toolbar" onclick="closeInvoiceDialog()">
						<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							发票类型：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<select id="pop_invoice_type_dropdown" class="pop_content_input_invoice" onchange="changeInvoiceType()" >
								<s:if test='invoice.invoiceType == "1"'>
									<option value="1" selected = "selected">普通发票</option>
									<option value="2">增值税发票</option>
								</s:if>
								<s:elseif test='invoice.invoiceType == "2"'>
									<option value="1">普通发票</option>
									<option value="2" selected = "selected">增值税发票</option>
								</s:elseif>
								<s:else>
									<option value="1" selected = "selected">普通发票</option>
									<option value="2">增值税发票</option>
								</s:else>
							</select>
						</div>
					</div>
				</div>
				<s:if test='invoice.invoiceType == "1"'>
					<div id="pop_content_item_invoice_company" class="pop_content_item">
				</s:if>
				<s:elseif test='invoice.invoiceType == "2"'>
					<div id="pop_content_item_invoice_company" class="pop_content_item_hidden">
				</s:elseif>
				<s:else>
					<div id="pop_content_item_invoice_company" class="pop_content_item">
				</s:else>
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							公司名：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_input_invoice_company" class="pop_content_input_invoice" disabled="disabled" type="text" value="${sessionScope.user.externalCompanyName}" onchange="checkCompany()" />
						</div>
						<div id="pop_content_input_invoice_tip" class="pop_content_input_tip_hidden">请输入正确的公司名</div>
					</div>
				</div>
				<s:if test='invoice.invoiceType == "1"'>
					<div id="pop_content_item_invoice_accountname" class="pop_content_item_hidden">
				</s:if>
				<s:elseif test='invoice.invoiceType == "2"'>
					<div id="pop_content_item_invoice_accountname" class="pop_content_item">
				</s:elseif>
				<s:else>
					<div id="pop_content_item_invoice_accountname" class="pop_content_item_hidden">
				</s:else>
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							开户名：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_input_invoice_accountname" class="pop_content_input_invoice" disabled="disabled" type="text" value="${sessionScope.user.externalCompanyName}" onchange="checkAccountName()" />
						</div>
						<div id="pop_content_input_invoice_tip_accountname" class="pop_content_input_tip_hidden">请输入正确的开户名</div>
					</div>
				</div>
				<s:if test='invoice.invoiceType == "1"'>
					<div id="pop_content_item_invoice_accountbank" class="pop_content_item_hidden">
				</s:if>
				<s:elseif test='invoice.invoiceType == "2"'>
					<div id="pop_content_item_invoice_accountbank" class="pop_content_item">
				</s:elseif>
				<s:else>
					<div id="pop_content_item_invoice_accountbank" class="pop_content_item_hidden">
				</s:else>
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							开户银行：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_input_invoice_accountbank" class="pop_content_input_invoice" type="text" value="${invoice.invoiceAccountBank}" onchange="checkAccountBank()" />
						</div>
						<div id="pop_content_input_invoice_tip_accountbank" class="pop_content_input_tip_hidden">请输入正确的开户银行</div>
					</div>
				</div>
				<s:if test='invoice.invoiceType == "1"'>
					<div id="pop_content_item_invoice_address" class="pop_content_item_hidden">
				</s:if>
				<s:elseif test='invoice.invoiceType == "2"'>
					<div id="pop_content_item_invoice_address" class="pop_content_item">
				</s:elseif>
				<s:else>
					<div id="pop_content_item_invoice_address" class="pop_content_item_hidden">
				</s:else>
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							地址：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_input_invoice_address" class="pop_content_input_invoice" type="text" value="${invoice.invoiceAddress}" onchange="checkInvoiceAddress()" />
						</div>
						<div id="pop_content_input_invoice_tip_address" class="pop_content_input_tip_hidden">请输入正确的地址</div>
					</div>
				</div>
				<s:if test='invoice.invoiceType == "1"'>
					<div id="pop_content_item_invoice_taxerid" class="pop_content_item_hidden">
				</s:if>
				<s:elseif test='invoice.invoiceType == "2"'>
					<div id="pop_content_item_invoice_taxerid" class="pop_content_item">
				</s:elseif>
				<s:else>
					<div id="pop_content_item_invoice_taxerid" class="pop_content_item_hidden">
				</s:else>
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							纳税人识别号：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_input_invoice_taxerid" class="pop_content_input_invoice" type="text" value="${invoice.invoiceTaxerId}" onchange="checkTaxerId()" />
						</div>
						<div id="pop_content_input_invoice_tip_taxerid" class="pop_content_input_tip_hidden">请输入正确的纳税人识别号</div>
					</div>
				</div>
				<s:if test='invoice.invoiceType == "1"'>
					<div id="pop_content_item_invoice_phone" class="pop_content_item_hidden">
				</s:if>
				<s:elseif test='invoice.invoiceType == "2"'>
					<div id="pop_content_item_invoice_phone" class="pop_content_item">
				</s:elseif>
				<s:else>
					<div id="pop_content_item_invoice_phone" class="pop_content_item_hidden">
				</s:else>
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
							电话：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_input_invoice_phone" class="pop_content_input_invoice" type="text" value="${invoice.invoicePhone}" onchange="checkPhone()" />
						</div>
						<div id="pop_content_input_invoice_tip_phone" class="pop_content_input_tip_hidden">请输入正确的电话</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left_invoice">
						<div class="pop_content_title">
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input class="pop_invoice_content_button" type="button" value="保存发票信息" onclick="saveInvoice()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>

