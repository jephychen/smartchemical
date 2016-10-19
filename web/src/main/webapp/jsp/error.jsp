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
</head>

<body>
<%@ include file="./common/header.jsp" %>

<div id="total_cart">	
<div id="total_wrapper">
	<div id="cart_pad">
		<div id="error_pad_header">
			<div id="cart_pad_logo">
				<a href="TitaniumMainPage.action"><img id="cart_logo" src="./img/logo_blue.png" /></a>
			</div>
			<div id="cart_pad_title">
				<span id="cart_title_span"></span>
			</div>
		</div>
		<div id="error_pad_content">
			<img class="cart_pad_content_img" src="./img/error.jpg">
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>