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
<script type="text/javascript" src="./js/pay_online.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

<div id="total_cart">	
<div id="total_wrapper">
	<div id="pay_pad">
		<div id="pay_pad_header">
			<div id="pay_pad_logo">
				<a href="TitaniumMainPage.action" id="header_logo_link"><img id="cart_logo" src="./img/logo_gray.png" /></a>
			</div>
			<div id="pay_pad_title">
				<span id="cart_title_span">收银台</span>
			</div>
		</div>
		<div id="pay_pad_content">
			<div id="pay_pad_content_mypay">
				<div id="pay_pad_content_mypay_text">订单号 <font class="font_red">${poNo}</font></div>
				<div id="pay_pad_content_mypay_totalprice">应付金额 <font class="font_red">￥${totalPrice}</font></div>
			</div>
			<div id="pay_pad_content_mypay">
				<div id="pay_pad_content_mypay_tip">请在提交订单<font class="font_red">24</font>小时之内完成支付，否则订单会自动取消。</div>
			</div>
			<div id="pay_pad_content_items">
				<div id="pay_pad_content_title">
					请选择付款银行
				</div>
				<ul>
					<li class="pay_bank_item">
						<img id="bank_img_ABC" class="bank_logo" src="./img/bank_icons/ABC.gif" onclick="selectBank('ABC', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_BOC" class="bank_logo" src="./img/bank_icons/BOC.gif" onclick="selectBank('BOC', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_CCB" class="bank_logo" src="./img/bank_icons/CCB.gif" onclick="selectBank('CCB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_CEB" class="bank_logo" src="./img/bank_icons/CEB.gif" onclick="selectBank('CEB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_CIB" class="bank_logo" src="./img/bank_icons/CIB.gif" onclick="selectBank('CIB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_CMB" class="bank_logo" src="./img/bank_icons/CMB.gif" onclick="selectBank('CMB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_CMBC" class="bank_logo" src="./img/bank_icons/CMBC.gif" onclick="selectBank('CMBC', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_CNCB" class="bank_logo" src="./img/bank_icons/CNCB.gif" onclick="selectBank('CNCB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_COMM" class="bank_logo" src="./img/bank_icons/COMM.gif" onclick="selectBank('COMM', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_GDB" class="bank_logo" src="./img/bank_icons/GDB.gif" onclick="selectBank('GDB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_HXB" class="bank_logo" src="./img/bank_icons/HXB.gif" onclick="selectBank('HXB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_ICBC" class="bank_logo" src="./img/bank_icons/ICBC.gif" onclick="selectBank('ICBC', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_PAB" class="bank_logo" src="./img/bank_icons/PAB.gif" onclick="selectBank('PAB', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_PSBC" class="bank_logo" src="./img/bank_icons/PSBC.gif" onclick="selectBank('PSBC', this)" />
					</li>
					<li class="pay_bank_item">
						<img id="bank_img_SPDB" class="bank_logo" src="./img/bank_icons/SPDB.gif" onclick="selectBank('SPDB', this)" />
					</li>
				</ul>
			</div>
			<div id="pay_pad_content_footer">
				<form id="pay_pad_topay_online_form">
					<div class="pay_button" onclick="toPayOnline(${poNo}, ${totalPrice})">
						立即支付
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

