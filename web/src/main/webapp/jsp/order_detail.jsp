<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/order_detail.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%@ include file="./common/header.jsp" %>

<div id="total_cart">	
<div id="total_wrapper">
	<div id="order_page">
		<div id="order_page_header">
			<div id="order_page_logo">
				<img id="company_logo" src="./img/logo_blue.png" />
			</div>
			<div id="order_page_title">
				<span id="cart_title_span">订单详情</span>
			</div>
		</div>
		<div id="order_page_content">
			<div id="order_page_content_detail">
				<div id="order_page_content_detail_text">订单号：<font class="font_red">${order.purchaseOrderNo}</font></div>
			</div>
			<div id="order_page_content_items">
				<div id="order_receiver" class="order_page_item">
					<s:if test='order.deliverType == "1"'>
						<div class="order_page_item_text">
							收货人信息
						</div>
						<div class="order_page_item_detail">
							<div id="delivery_tip" class="order_detail_item_info">
								收货人：${order.receiver.receiverName}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								地&nbsp;址：${order.receiver.city.province.provinceName}-${order.receiver.city.cityName}-${order.receiver.address}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								手&nbsp;机：${order.receiver.mobileNo}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								邮&nbsp;箱：${order.receiver.email}
							</div>
						</div>
					</s:if>
					<s:elseif test='order.deliverType == "2"'>
						<div class="order_page_item_text">
							收货人信息
						</div>
						<div class="order_page_item_detail">
							<div id="delivery_tip" class="order_detail_item_info">
								收货人：${order.receiver.receiverName}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								地&nbsp;址：${order.receiver.city.province.provinceName}-${order.receiver.city.cityName}-${order.receiver.address}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								手&nbsp;机：${order.receiver.mobileNo}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								邮&nbsp;箱：${order.receiver.email}
							</div>
						</div>
					</s:elseif>
					<s:elseif test='order.deliverType == "3"'>
						<div class="order_page_item_text">
							提货人信息
						</div>
						<div class="order_page_item_detail">
							<div id="delivery_tip" class="order_detail_item_info">
								提货人：${order.getter.getterName}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								身份证：${order.getter.getterIdNo}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								手机号：${order.getter.getterMobileNo}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								车牌号：${order.getter.truckLicenseNo}
							</div>
							<div id="delivery_tip" class="order_detail_item_info">
								邮&nbsp;箱：${order.getter.getterEmail}
							</div>
						</div>
					</s:elseif>
				</div>
				<div class="order_page_item">
					<div class="order_page_item_text">
						支付方式和配送方式
					</div>
					<div class="order_page_item_detail">
						<div id="delivery_tip" class="order_detail_item_info">
							<s:if test='order.paymentType == "1"'>
								支付方式：在线支付
							</s:if>
							<s:elseif test='order.paymentType == "2"'>
								支付方式：银行转账
							</s:elseif>
							<s:elseif test='order.paymentType == "3"'>
								支付方式：银行承兑汇票
							</s:elseif>
						</div>
						<div id="delivery_tip" class="order_detail_item_info">
							<s:if test='order.deliverType == "1"'>
								配送方式：厂商配送
							</s:if>
							<s:elseif test='order.deliverType == "2"'>
								配送方式：智选物流
							</s:elseif>
							<s:elseif test='order.deliverType == "3"'>
								配送方式：用户自提
							</s:elseif>
						</div>
						<div id="delivery_tip" class="order_detail_item_info">
							运&nbsp;&nbsp;费：￥${order.logisticFee}
						</div>
						<s:if test='order.paymentType == "3"'>
						<div id="delivery_tip" class="order_detail_item_info">
							承兑手续费：￥${order.acBillServiceFee}
						</div>
						</s:if>
					</div>
				</div>
				<div class="order_page_item">
					<div class="order_page_item_text">
						商品清单
					</div>
					<div class="order_page_item_detail">
						<div class="order_page_product_list_title_wrapper">
							<div class="order_page_product_list_title">
								<div class="order_page_product_list_title_name">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">产品</div></font>
								</div>
								<div class="order_page_product_list_title_company">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">品牌</div></font>
								</div>
								<div class="order_page_product_list_title_price">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">单价</div></font>
								</div>
								<div class="order_page_product_list_title_quantity">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">数量</div></font>
								</div>
								<div class="order_page_product_list_title_price">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">小计</div></font>
								</div>
							</div>
						</div>
						<div class="order_page_product_list">
							<s:iterator value="order.orderItems" id="orderItem">
							<div class="order_page_product_item">
								<div class="order_page_product_item_img_col">
									<a href="ProductDetail.action?productId=${orderItem.product.productId}" target="_blank"><img class="order_page_product_item_img" src="${orderItem.product.pictureUrl}" /></a>
								</div>
								<div class="order_page_product_item_product_name_col">
									<div class="order_page_product_link_wrapper">
										<a class="order_page_product_link_wrapper" href="ProductDetail.action?productId=${orderItem.product.productId}" target="_blank"><font class="order_page_font_gray">${orderItem.product.productName} ${orderItem.product.description}</font></a>
									</div>
									<div class="order_page_product_link_warehouse1_wrapper">
										<font class="font_blue font_style_yahei">仓库：${orderItem.product.warehouse.city.cityName}-${orderItem.product.warehouse.address}</font>
									</div>
									<s:if test='order.deliverType == "3"'>
										<div class="order_page_product_link_warehouse_wrapper">
											<font class="font_blue font_style_yahei">库管员：${orderItem.product.warehouse.warehouseAdmin.realName}&nbsp;${orderItem.product.warehouse.warehouseAdmin.mobileNo}</font>
										</div>
									</s:if>
								</div>
								<div class="order_page_product_item_price_col">
									<s:if test='#orderItem.product.merchantCompany != ""'>
										<div class="order_page_product_price_wrapper">${orderItem.product.brand.brandName}</div></font>
									</s:if>
									<s:else>
										<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${orderItem.product.brand.brandName}</div></font>
										<a class="order_page_product_link_wrapper" href="Company.action?companyId=${orderItem.product.merchantCompany.companyId}" target="_blank"><font class="order_page_font_red"><div class="order_page_product_price_wrapper">贸易商：${orderItem.product.merchantCompany.companyName}</div></font></a>
									</s:else>
								</div>
								<div class="order_page_product_item_price_col">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">￥${orderItem.product.price}</div></font>
								</div>
								<div class="order_page_product_item_quantity_col">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${orderItem.quantity} ${orderItem.product.quantityUnit.unitName}</div></font>
								</div>
								<div class="order_page_product_item_price_col">
									<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">￥<s:property value="#orderItem.product.price * #orderItem.quantity"/></div></font>
								</div>
							</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="order_page_item">
					<div class="order_page_item_text">
						发票信息
					</div>
					<div class="order_page_item_invoice_detail">
						<div class="order_page_item_address_content">
							<s:if test='order.invoice.invoiceType == "1"'>
								<div class="order_page_invoice_info">发票类型：</div><div id="order_page_invoice_info_invoicetype" class="order_page_invoice_info_right">普通发票</div>
							</s:if>
							<s:elseif test='order.invoice.invoiceType == "2"'>
								<div class="order_page_invoice_info">发票类型：</div><div id="order_page_invoice_info_invoicetype" class="order_page_invoice_info_right">增值税发票</div>
							</s:elseif>
							<s:else>
								<div class="order_page_invoice_info">发票类型：</div><div id="order_page_invoice_info_invoicetype" class="order_page_invoice_info_right">普通发票</div>
							</s:else>
						</div>
					</div>
					<s:if test='order.invoice.invoiceType == "1"'>
						<div id="order_page_invoice_info_invoicecompany_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">公司名：</div><div id="order_page_invoice_info_invoicecompany" class="order_page_invoice_info_right">${order.invoice.invoiceCompany}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaccount_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户名：</div><div id="order_page_invoice_info_invoiceaccount" class="order_page_invoice_info_right">${order.invoice.invoiceAccountName}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicebank_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户银行：</div><div id="order_page_invoice_info_invoicebank" class="order_page_invoice_info_right">${order.invoice.invoiceAccountBank}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaddress_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">地址：</div><div id="order_page_invoice_info_invoiceaddress" class="order_page_invoice_info_right">${order.invoice.invoiceAddress}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicetaxerid_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">纳税人识别号：</div><div id="order_page_invoice_info_invoicetaxerid" class="order_page_invoice_info_right">${order.invoice.invoiceTaxerId}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicephone_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">电话：</div><div id="order_page_invoice_info_invoicephone" class="order_page_invoice_info_right">${order.invoice.invoicePhone}</div>
							</div>
						</div>
					</s:if>
					<s:elseif test='order.invoice.invoiceType == "2"'>
						<div id="order_page_invoice_info_invoicecompany_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">公司名：</div><div id="order_page_invoice_info_invoicecompany" class="order_page_invoice_info_right">${order.invoice.invoiceCompany}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaccount_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户名：</div><div id="order_page_invoice_info_invoiceaccount" class="order_page_invoice_info_right">${order.invoice.invoiceAccountName}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicebank_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户银行：</div><div id="order_page_invoice_info_invoicebank" class="order_page_invoice_info_right">${order.invoice.invoiceAccountBank}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaddress_wrapper"  class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">地址：</div><div id="order_page_invoice_info_invoiceaddress" class="order_page_invoice_info_right">${order.invoice.invoiceAddress}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicetaxerid_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">纳税人识别号：</div><div id="order_page_invoice_info_invoicetaxerid" class="order_page_invoice_info_right">${order.invoice.invoiceTaxerId}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicephone_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">电话：</div><div id="order_page_invoice_info_invoicephone" class="order_page_invoice_info_right">${order.invoice.invoicePhone}</div>
							</div>
						</div>
					</s:elseif>
					<s:else>
						<div id="order_page_invoice_info_invoicecompany_wrapper" class="order_page_item_invoice_detail">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">公司名：</div><div id="order_page_invoice_info_invoicecompany" class="order_page_invoice_info_right">${order.invoice.invoiceCompany}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaccount_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户名：</div><div id="order_page_invoice_info_invoiceaccount" class="order_page_invoice_info_right">${order.invoice.invoiceAccountName}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicebank_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">开户银行：</div><div id="order_page_invoice_info_invoicebank" class="order_page_invoice_info_right">${order.invoice.invoiceAccountBank}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoiceaddress_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">地址：</div><div id="order_page_invoice_info_invoiceaddress" class="order_page_invoice_info_right">${order.invoice.invoiceAddress}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicetaxerid_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">纳税人识别号：</div><div id="order_page_invoice_info_invoicetaxerid" class="order_page_invoice_info_right">${order.invoice.invoiceTaxerId}</div>
							</div>
						</div>
						<div id="order_page_invoice_info_invoicephone_wrapper" class="order_page_item_invoice_detail_hidden">
							<div class="order_page_item_address_content">
								<div class="order_page_invoice_info">电话：</div><div id="order_page_invoice_info_invoicephone" class="order_page_invoice_info_right">${order.invoice.invoicePhone}</div>
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
							<div class="float_right"><s:property value="order.totalPrice - order.logisticFee - order.acBillServiceFee"/></div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							<div class="float_right">件商品，总价：</div>
							<div class="font_red float_right"><s:property value="order.orderItems.size()"/>&nbsp;</div> 
						</div>
					</div>
					<div class="order_page_item_total_line">
						<div class="order_page_item_total_col_right">
							<div class="float_right">${order.logisticFee}</div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							物流费用：
						</div>
					</div>
					<s:if test='order.paymentType == "3"'>
					<div class="order_page_item_total_line">
						<div class="order_page_item_total_col_right">
							<div class="float_right">${order.acBillServiceFee}</div>
							<div class="float_right">￥</div>
						</div>
						<div class="order_page_item_total_col">
							承兑手续费：
						</div>
					</div>
					</s:if>
				</div>
				<div class="order_page_item">
					<div id="order_page_content_submit">
						<div id="submit_info">
							订单总价：<font class="submit_info_red">￥${order.totalPrice}</font>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

