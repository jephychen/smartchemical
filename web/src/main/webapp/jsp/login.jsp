<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/login.js"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

<div id="total_login">	
<div id="total_wrapper">
	<div id="login_pad">
		<div id="login_pad_header">
			<div id="login_pad_logo">
				<a href="TitaniumMainPage.action"><img id="login_logo" src="./img/logo_gray.png" /></a>
			</div>
			<div id="login_pad_title">
				<span id="login_title_span">用户登录</span>
			</div>
		</div>
		<div id="login_pad_content">
			<div id="login_pad_left">
				<img id="login_left_picture" src="./img/demo_pg1.jpg" />
			</div>
			<div id="login_pad_right">
				<div id="login_pad_window">
					<div id="login_pad_window_title">
						欢迎登陆
					</div>
					<div id="login_pad_window_title2">
						还没有账号？<a href="Register.action">立即注册</a>
					</div>
					<form id="login_form" action="Login.action" method="post">
					<input name="refererUrl" type="hidden" value="<s:property value="refererUrl"/>" />
					<ul>
						<li class="login_pad_window_item">
							<input id="username_field" name="username" class="login_pad_window_item_input" value="请输入用户名" onfocus="clearUsername()" 
								onblur="tipUsername()" onkeydown="if(event.keyCode==13){submit_login_form();}" />
						</li>
						<li class="login_pad_window_item">
							<input id="password_field" name="password" class="login_pad_window_item_input" value="请输入密码" onfocus="clearPassword()" 
								onblur="tipPassword()" onkeydown="if(event.keyCode==13){submit_login_form();}" />
						</li>
						<li class="login_pad_window_tool_bar">
							<div class="login_pad_window_item_autologin_checkbox"><s:checkbox name="isConsent" /></div>
							<div class="login_pad_window_item_autologin_text">15天内自动登陆</div>
							<div class="login_pad_window_item_autologin_forget"></div>
						</li>
						<li class="login_pad_window_item">
							<div class="login_pad_window_login_button" onclick="submit_login_form()">登录</div>
						</li>
						<li class="login_pad_window_item">
							<div id="login_result_tip" class="login_result_tip">${sessionScope.tip}</div>
						</li>
					</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

