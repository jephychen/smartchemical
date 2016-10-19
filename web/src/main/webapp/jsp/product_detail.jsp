<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/product_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/product_page.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/main_search.js" charset="UTF-8"></script>
</head>

<body>
<%@ include file="./common/header.jsp" %>

<div id="total">
<div id="dark_blue_header">
	<div class="dark_blue_header_wrapper">
		<div id="dark_blue_logo">
			<a href="TitaniumMainPage.action"><img id="dark_blue_logo_img" src="./img/logo_dark_blue.png" /></a>
		</div>
		<div id="dark_blue_title">
			<span id="dark_blue_title_span">产品中心</span>
		</div>
		<div id="top_navigator_right_user_page">
			<div class="top_search_div_user_page_high">
				<form id="search_form_global" name="search_form" action="MainSearch.action">
					<input id="top_search_user_page_id" class="top_search_user_page" name="keyword" />
					<div id="top_search_button"  onclick="submitSearch()"><img src="./img/search_button_white.png" /></div>
				</form>
			</div>	
		</div>
	</div>
</div>
<div id="total_wrapper">

<div id="content">
	<div class="content_path">
		<ul class="content_path_ul">
			<li class="content_path_li"><a href="TitaniumMainPage.action">首页</a></li>
			<li class="content_path_li"><em>></em></li>
			<li class="content_path_li"><a href="MainSearch.action">${product.productType.productCategory.productCategoryName}</a></li>
			<li class="content_path_li"><em>></em></li>
			<li class="content_path_li"><a href="MainSearch.action">${product.productType.productTypeName}</a></li>
			<li class="content_path_li"><em>></em></li>
			<li class="content_path_li"><a href="MainSearch.action?keyword=${product.productName}">${product.productName}</a></li>
		</ul>
	</div>
	<div id="content_product_detail">
		<div id="content_product_detail_left">
			<div id="content_product_detail_l">
				<img class="content_product_detail_pic" src="${product.pictureUrl}" />
			</div>
			<div id="content_product_detail_r">
				<div id="content_product_detail_r_title">
					<p class="content_product_detail_r_title">
						${product.productName} &nbsp;
						<s:if test='product.productStatus != 1'>
							<font class="font_red">产品已下架</font>
						</s:if>
						<s:else>
							<s:if test='product.stockLevel < product.minSoldQunatity'>
								<font class="font_red">无货</font>
							</s:if>
						</s:else>
					<p>
					<span class="content_product_detail_r_desc">${product.description}</span>
				</div>
				<div id="content_product_detail_r_logistic">品牌：${product.brand.brandName}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<s:if test='product.merchantCompany != ""'>
					</s:if>
					<s:else>
						<font class="font_red">经销商：${product.merchantCompany.companyName}</font>
					</s:else>
				</div>
				<div id="content_product_detail_r_price">
					<div id="content_product_detail_r_price1">
						<font class="content_product_detail_r_price">现金价格</font><font class="font_red">￥${product.price}</font>
					</div>
					<div id="content_product_detail_r_price2">已售${product.totalSoldQuantity}吨</div>
				</div>
				<div id="content_product_detail_r_logistic">
					仓库地址： ${product.warehouse.city.province.provinceName}-${product.warehouse.city.cityName}-${product.warehouse.address}				
				</div>
				<div id="content_product_detail_r_quantity">
					<span id="content_product_detail_r_quantity">数量</span>
						<div id="content_product_detail_adder_div">
							<div class="content_product_detail_adder" onclick="minusAmount(${product.stockLevel}, ${product.minSoldQunatity})">-</div>
							<input id="content_product_detail_adder_input" value="${product.minSoldQunatity}" onblur="checkAmount(${product.stockLevel}, ${product.minSoldQunatity})" />
							<div class="content_product_detail_adder" onclick="plusAmount(${product.stockLevel}, ${product.minSoldQunatity})">+</div>
						</div>
						吨 (库存<font class="font_red">${product.stockLevel}</font>吨，最少<font class="font_red">${product.minSoldQunatity}</font>吨起售)
					</div>
				<div id="content_product_detail_r_purchase">
					<s:if test='product.productStatus == 1'>
						<s:if test='product.stockLevel < product.minSoldQunatity'>
							<div id="content_product_detail_r_purchase_button" class="content_product_detail_purchase_button_disable">
								立即购买
							</div>
							<div id="content_product_detail_r_cart_button" class="content_product_detail_cart_button_disable">
								加入购物车
							</div>
						</s:if>
						<s:else>
							<form id="buy_immediately">
								<div id="content_product_detail_r_purchase_button" class="content_product_detail_purchase_button" onclick="buyImmediately(${product.productId}, ${product.stockLevel}, ${product.minSoldQunatity}, ${sessionScope.user.companyLicenseStatus})">
									立即购买
								</div>
							</form>
							<form id="add_to_cart" action="CartSuccess.action">
								<div id="content_product_detail_r_cart_button" class="content_product_detail_cart_button" onclick="addToCart(${product.productId}, ${product.stockLevel}, ${product.minSoldQunatity})">
									加入购物车
								</div>
							</form>
						</s:else>
					</s:if>
					<s:else>
						<div id="content_product_detail_r_purchase_button" class="content_product_detail_purchase_button_disable">
							立即购买
						</div>
						<div id="content_product_detail_r_cart_button" class="content_product_detail_cart_button_disable">
							加入购物车
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="content_product_detail_page">
				<div id="content_product_detail_page_tab">
					<ul id="content_product_detail_page_tab">
						<li class="content_product_detail_page_tab_item">
							<a class="content_product_detail_page_tab_item">产品介绍</a>
						</li>
					</ul>
				</div>
				<div class="content_product_detail_page_summary">
					<ul id="content_product_detail_page_summary">
						<s:iterator value="product.parameters" id="parameter">
							<li class="content_product_detail_page_summary_item">
								${parameter.parameterName} : ${parameter.parameterValue}
							</li>
						</s:iterator>
					</ul>
				</div>
				<div class="content_product_detail_page_detail">
					${product.contentDetail}
				</div>
			</div>
		</div>
		<s:if test='product.merchantCompany == null'>
			<div id="content_product_detail_right">
				<div id="content_product_detail_right_vendor_title">品牌信息</div>
				<div id="content_product_detail_right_vendor_name">${product.brand.brandName}</div>
				<div id="content_product_detail_right_vendor_icon"><img class="content_list_logo" src="${product.brand.logoPath}" /></div>
			</div>
		</s:if>
		<s:else>
			<div id="content_product_detail_right">
				<div id="content_product_detail_right_vendor_title">经销商信息</div>
				<div id="content_product_detail_right_vendor_name">${product.merchantCompany.companyName}</div>
				<div id="content_product_detail_right_vendor_icon"><img class="content_list_logo" src="${product.merchantCompany.companyIcon}" /></div>
				<div id="content_product_detail_right_vendor_desc">
					<p class="content_product_detail_right_vendor_p">所在地区:${product.merchantCompany.companyCity.province.provinceName} ${product.merchantCompany.companyCity.cityName}</p>
					<p class="content_product_detail_right_vendor_p">${product.merchantCompany.description}</p>
					<p class="content_product_detail_right_vendor_p">另外的描述</p>		
				</div>
				<div id="content_product_detail_right_vendor_botton">
					<a class="content_product_detail_right_vendor_botton" href="Company.action?companyId=${product.merchantCompany.companyId}" target="_blank">进入经销商主页</a>
				</div>
			</div>
		</s:else>
	</div>
</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>