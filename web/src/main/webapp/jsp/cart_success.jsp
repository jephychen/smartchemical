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
<script type="text/javascript" src="./js/cart_success.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/main_search.js" charset="UTF-8"></script>
</head>

<body>
<%@ include file="./common/header.jsp" %>

<div id="total_cart">
<div id="dark_blue_header">
	<div class="dark_blue_header_wrapper">
		<div id="dark_blue_logo">
			<a href="TitaniumMainPage.action"><img id="dark_blue_logo_img" src="./img/logo_dark_blue.png" /></a>
		</div>
		<div id="dark_blue_title">
			<span id="dark_blue_title_span">购物车</span>
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
	<div class="cart_success_pad_table_success">
		<form id="cart_form" action="Cart.action">
			<div class="cart_success_result">
				<img class="cart_success_result_icon" src="./img/register/success.png" />
				<div class="cart_success_result_right">
					<div class="cart_success_result_font">商品已成功加入购物车</div>
						<div class="cart_success_gotocart_button" onclick="goCart(${productId}, ${quantity})">去购物车结算</div>
					<div class="cart_success_continue" onclick="window.history.back()" >继续购物</div>
				</div>
			</div>
		</form>
	</div>
</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>