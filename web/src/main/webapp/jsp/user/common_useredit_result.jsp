<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/user_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/common_user.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="../common/header.jsp" %>

<div id="top_navigator_blue_bg">
	<div id="top_navigator_wrapper">
		<div id="top_navigator_logo">
			<a href="TitaniumMainPage.action"><img id="top_navigator_logo" src="./img/logo_blue_small_blue_bg.png" /></a>
		</div>
		<div id="user_page_title">
			<span id="user_page_title_span">我的订单</span>
		</div>
		<div id="top_navigator_right_user_page">
			<div class="top_search_div_user_page">
				<form id="search_form_global" name="search_form" action="MainSearch.action">
					<input id="top_search_user_page_id" class="top_search_user_page" name="keyword" />
					<div id="top_search_button"  onclick="submitSearch()"><img src="./img/search_button_white.png" /></div>
				</form>
			</div>	
		</div>
	</div>
</div>

<div id="total">
<div id="total_wrapper">
<div id="content">
	<div id="my_zhixuan_wrapper">
		<div id="my_zhixuan_left">
			<div class="my_zhixuan_menu_group">
				<ul>
					<li class="my_zhixuan_left_li_title">
						订单中心
					</li>
					<li class="my_zhixuan_left_li">
						<a href="QueryOrder_allOrders.action">
							<s:if test='queryType == "0"'>
								<font class="font_red">我的所有订单</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">我的所有订单</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="QueryOrder_unpaid.action">
							<s:if test='queryType == "1"'>
								<font class="font_red">未付款的订单</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">未付款的订单</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="QueryOrder_waitingReceived.action">
							<s:if test='queryType == "2"'>
								<font class="font_red">待收货的订单</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">待收货的订单</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="QueryOrder_waitingGot.action">
							<s:if test='queryType == "3"'>
								<font class="font_red">待提货的订单</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">待提货的订单</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="QueryOrder_doneOrder.action">
							<s:if test='queryType == "4"'>
								<font class="font_red">已完成的订单</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">已完成的订单</font>
							</s:else>
						</a>
					</li>
				</ul>
				<br>
				<ul>
					<li class="my_zhixuan_left_li_title">
						账户管理
					</li>
					<li class="my_zhixuan_left_li">
						<a href="UserEdit_changePassword.action">
							<s:if test='queryType == "5"'>
								<font class="font_red">修改密码</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">修改密码</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="BankAccountEdit_editBankAccount.action">
							<s:if test='queryType == "6"'>
								<font class="font_red">银行账户管理</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">银行账户管理</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="DepositRequestManagement_allRequest.action">
							<s:if test='queryType == "7"'>
								<font class="font_red">提现/代付请求</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">提现/代付请求</font>
							</s:else>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					${title}
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<div class="use_edit_result">
					<font class="font_red">${tip}</font>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>

