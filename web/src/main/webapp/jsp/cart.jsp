<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/cart.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/cart.js" charset="UTF-8"></script>
</head>

<body>
<%@ include file="./common/header.jsp" %>

<div id="total_login">	
<div id="total_wrapper">
	<div id="cart_pad">
		<div id="cart_pad_header">
			<div id="cart_pad_logo">
				<a href="TitaniumMainPage.action" id="header_logo_link"><img id="cart_logo" src="./img/logo_blue.png" /></a>
			</div>
			<div id="cart_pad_title">
				<span id="cart_title_span">购物车</span>
			</div>
		</div>
		<div id="cart_pad_content">
			<div id="cart_pad_content_mycart">
				<div id="cart_pad_content_mycart_text"><div class="cart_count">我的购物车 </div><input id="cart_count1" class="cart_count_input1" disabled="disabled" type="text" value="<s:property value="carts.size()"/>" /></div>
			</div>
			<div id="cart_pad_content_header">
				<div class="cart_pad_content_header_all">
				<input id="cart_checkbox_main" class="cart_pad_content_input" type="checkbox" checked="true" onclick="selectAll()"></input>
				全选
				</div>
				<div class="cart_pad_content_header_product padding_top_header">商品</div>
				<div class="cart_pad_content_header_price padding_top_header">单价(元)</div>
				<div class="cart_pad_content_header_count padding_top_header">数量</div>
				<div class="cart_pad_content_header_total_price padding_top_header">总价(元)</div>
				<div class="cart_pad_content_header_operation padding_top_header">操作</div>
			</div>
			<div id="cart_pad_content_items">
				<s:set var ="total" value="0" />
				<s:iterator value="carts" id="cart">
					<div id="cart_pad_content_item${cart.product.productId}" class="cart_pad_content_item_selected">
						<div class="cart_pad_content_header_all cart_pad_content_item_col">
							<input id="${cart.product.productId}" name="cart_checkbox" class="cart_pad_content_item_checkbox" type="checkbox" checked="true" onclick="selectItem(${cart.product.productId})" />
							<a href="ProductDetail.action?productId=${cart.product.productId}" target="_blank"><img class="cart_pad_content_item_img" src="${cart.product.pictureUrl}" /></a>
						</div>
						<div class="cart_pad_content_header_product cart_pad_content_item_col">
							<div class="cart_pad_content_product_link_wrapper">
								<a class="cart_pad_content_product_link" href="ProductDetail.action?productId=${cart.product.productId}" target="_blank"><font class="cart_pad_font_gray">${cart.product.productName}  ${cart.product.description}</font></a>
							</div>
							<div class="cart_pad_content_product_link_wrapper">
								<font class="cart_pad_font_gray">${cart.product.brand.brandName}</font>
							</div>
						</div>
						<div class="cart_pad_content_header_price cart_pad_content_item_col padding_top_header">
							<div class="cart_pad_content_product_price_wrapper">
								<font class="price_black">${cart.product.price}</font>
							</div>
						</div>
						<div class="cart_pad_content_header_count cart_pad_content_item_col padding_top_header">
							<div id="content_product_detail_adder_div">
								<div class="content_product_detail_adder" onclick="minusAmount(${cart.product.productId}, ${cart.product.price})">-</div>
								<s:if test='#cart.quantity > #cart.product.stockLevel'>
									<input id="content_product_detail_adder_input${cart.product.productId}" class="content_product_detail_adder_input" value="${cart.product.stockLevel}" onblur="checkAmount(${cart.product.stockLevel}, ${cart.product.productId}, ${cart.product.price})" />
								</s:if>
								<s:else>
									<input id="content_product_detail_adder_input${cart.product.productId}" class="content_product_detail_adder_input" value="${cart.quantity}" onblur="checkAmount(${cart.product.stockLevel}, ${cart.product.productId}, ${cart.product.price})" />
								</s:else>
								<div class="content_product_detail_adder" onclick="plusAmount(${cart.product.stockLevel}, ${cart.product.productId}, ${cart.product.price})">+</div>
							</div>
							<div class="content_product_detail_unit">
								${cart.product.quantityUnit.unitName}
							</div>
							<div class="content_product_detail_current_stocklevel">
								<div class="content_product_detail_current_stocklevel_text">库存</div>
								<div class="content_product_detail_current_stocklevel_text_red">${cart.product.stockLevel}</div>
								<div class="content_product_detail_current_stocklevel_text">${cart.product.quantityUnit.unitName}</div> 
							</div>
						</div>
						<div class="cart_pad_content_header_total_price cart_pad_content_item_col padding_top_header">
							<div id="cart_pad_content_item_total_price${cart.product.productId}" class="cart_pad_content_product_price_wrapper">
								<font class="price_black"><input id="cart_pad_content_product_price_input${cart.product.productId}" class="cart_pad_content_product_price_input_selected" disabled="disabled" type="text" value="" /></font>
								<script>caculateItemPrice(${cart.product.productId}, ${cart.product.price}, ${cart.quantity})</script>
								<s:set var ="total" value="#cart.product.price * #cart.quantity + #total"/>
							</div>
						</div>
						<div class="cart_pad_content_header_operation cart_pad_content_item_col padding_top_header">
							<div class="cart_pad_content_product_operation_wrapper">
								<a href="Cart.action?removeProductId=${cart.product.productId}"><font class="cart_pad_font_gray">删除</font></a>
							</div>
						</div>
					</div>
				</s:iterator>
			</div>
			<div id="cart_pad_content_submit">
				<div class="all_select_button">
					<input id="cart_checkbox_main1" class="cart_pad_content_input" type="checkbox" checked="true" onclick="selectAll1()"></input>
				</div>
				<div class="all_select_text">
					全选
				</div>
				<form id="remove_selected_form" action="Cart.action">
					<div class="all_remove_text" onclick="removeSelected()">
						删除选中商品
					</div>
				</form>
				<form id="submit_form">
					<div id="submit_button" onclick="submitToOrder(${sessionScope.user.companyLicenseStatus})">
						去结算
					</div>
				</form>
				<div id="submit_info">
					<div class="cart_count1">已选择 </div>
					<input id="cart_count2" class="cart_count_input2" disabled="disabled" type="text" value="<s:property value="carts.size()"/>" />
					<div class="cart_count1"> 件商品，总价(不含运费)：</div>
					<font class="submit_info_red">
						<div class="cart_count">￥</div>
						<input id="cart_pad_total_price_input" class="cart_pad_content_product_price_input_red" disabled="disabled" type="text" value="<s:property value="#total"/>" />
						<script>showTotalPrice()</script>
					</font>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

