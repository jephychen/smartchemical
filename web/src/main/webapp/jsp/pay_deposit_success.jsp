<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/pay.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/pay_acceptancebill.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

<div id="total_cart">	
<div id="total_wrapper">
	<div id="pay_pad">
		<div id="pay_pad_header">
			<div id="pay_pad_logo">
				<img id="cart_logo" src="./img/logo_gray.png" />
			</div>
			<div id="pay_pad_title">
				<span id="cart_title_span">收银台</span>
			</div>
		</div>
		<div id="pay_pad_content">
			<div id="pay_pad_content_mypay">
				<div id="pay_pad_content_mypay_text">订单号 <font class="font_red">${poNo}</font></div>
			</div>
			<div id="pay_pad_content_items_acceptancebill_success">
				<div id="pay_pad_content_title_acceptancebill_success">
					<a href="QueryOrder_allOrders.action">
						<div class="back_icon"><img class="back_button" src="./img/back_button.jpg" /></div>
						<div class="back_text">我的订单</div>
					</a>
				</div>
				<div class="pay_pad_acceptancebill_item">
					<div class="cart_success_result">
						<img class="deposit_success_result_icon" src="./img/register/success.png" />
						<div class="cart_success_result_right">
							<div class="cart_success_result_font">支付成功！</div>
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

