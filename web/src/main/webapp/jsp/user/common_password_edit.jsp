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
			<%@ include file="common_user_menu.jsp" %>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					修改密码
				</div>
			</div>
			<form id="user_edit_form">
			</form>
			<div class="my_zhixuan_right_content">
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">原密码：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_password" type="password" class="product_edit_item_input" onblur="checkPassword(this.value,'pop_content_password')"></input>
						<div id="pop_content_password" class="pop_content_input_tip1_hidden">密码应为6-20位</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">新密码：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_newpassword1" type="password" class="product_edit_item_input" onblur="checkNewPassword(this.value,'pop_content_newpassword1', 'pop_content_newpassword2')"></input>
						<div id="pop_content_newpassword1" class="pop_content_input_tip1_hidden">密码应为6-20位</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">重复新密码：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_newpassword2" type="password" class="product_edit_item_input" onblur="checkNewPassword(this.value,'pop_content_newpassword2', 'pop_content_newpassword1')"></input>
						<div id="pop_content_newpassword2" class="pop_content_input_tip1_hidden">密码应为6-20位</div>
					</div>
				</div>
				<div class="product_edit_title1">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text"></div>
					</div>
					<div class="product_edit_item_right">
						<div class="product_edit_save_button hover_pointer" onclick="savePassword()">
							保存
						</div>
					</div>
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

