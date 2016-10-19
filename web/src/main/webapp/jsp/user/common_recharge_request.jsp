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
<script type="text/javascript" src="./js/myzhixuan.js" charset="UTF-8"></script>
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
					充值
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<form id="recharge_form">
					<div class="product_edit_item">
						<div class="product_edit_item_left">
							<div class="product_edit_item_left_text">充值金额：</div>
						</div>
						<div class="product_edit_item_right">
							<input id="user_recharge_amount" class="product_edit_item_input" onblur="checkAmount()"></input><div class="product_edit_item_right_text">元</div>
							<div id="pop_user_recharge_amount" class="pop_content_input_tip1_hidden">请输入正确的金额</div>
						</div>
					</div>
					<div class="product_edit_item">
						<div class="product_edit_item_left">
							<div class="product_edit_item_left_text">充值方式：</div>
						</div>
						<div class="product_edit_item_right_radio">
							<input id="user_edit_recharge1" name="recharge_radio" type="radio" checked="true" class="recharge_radio_style">承兑充值</input>
							<input id="user_edit_recharge2" name="recharge_radio" type="radio" class="recharge_radio_style">转账充值</input>
						</div>
					</div>
					<div class="product_edit_title1">
						<div class="product_edit_item_left">
							<div class="product_edit_item_left_text"></div>
						</div>
						<div class="product_edit_item_right">
							<div class="product_edit_save_button hover_pointer" onclick="goRecharge()">
								充值
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<%@ include file="../common/footer.jsp" %>
<div id="pop_dialog_getdeposit" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog_withdraw">
			<form id="pop_get_deposit_form">
				<div class="pop_title">
					<div class="pop_title_text">提现请求</div>
					<div id="pop_dialog_frame_order_address_shutdown_button" class="pop_title_toolbar" onclick="closeGetdepostDialog()">
						<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							提现金额：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_getdeposit_amount_id" name="amount" class="pop_content_input_invoice" type="text" onblur="checkGetAmount(${sessionScope.user.depositStr})" />
						</div>
						<div id="pop_content_getdeposit_amount_tip" class="pop_content_input_tip_hidden">请输入正确的提现金额</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							提现账户：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<select id="pop_content_getdeposit_accountno_id" name="accountId" class="pop_content_content_bankaccount">
								<s:iterator value="userAccounts" id="useraccount">
									<option value="${useraccount.accountId}">${useraccount.companyName}&nbsp ${useraccount.accountNo}</option>
								</s:iterator>
							</select>
						</div>
						<div id="pop_content_getdeposit_accountno_tip" class="pop_content_input_tip_hidden">请选择提现账户</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							验证码：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_getdeposit_verifycode_id" class="pop_content_input_verifycode" type="text" onchange="checkCompany()" />
							<input id="pop_content_getdeposit_verifycode_button" class="pop_content_input_verifycode_button" type="button" value="获取验证码" onclick="sendGetdepositVerifyCode(${sessionScope.user.mobileNo})" />
						</div>
						<div id="pop_content_getdeposit_verifycode_tip1" class="pop_content_input_tip_hidden">验证码错误</div>
						<div id="pop_content_getdeposit_verifycode_tip2" class="pop_content_input_tip_hidden">已发送到手机18682731302</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input class="pop_invoice_content_button" type="button" value="提交提现请求" onclick="saveGetdepositRequest()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="pop_dialog_transferdeposit" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog_transfer">
			<form id="pop_transfer_deposit_form">
				<div class="pop_title">
					<div class="pop_title_text">代付请求</div>
					<div id="pop_dialog_frame_order_address_shutdown_button" class="pop_title_toolbar" onclick="closeTransferdepostDialog()">
						<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							提现账户：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<select id="pop_content_transfer_acountno_select_id" name="accountId" class="pop_content_content_bankaccount" onchange="transferAccountSelect()">
								<option value="0">新建账户</option>
								<s:iterator value="userOtherAccounts" id="useraccount">
									<option value="${useraccount.accountId}">${useraccount.companyName}&nbsp ${useraccount.accountNo}</option>
								</s:iterator>
							</select>
						</div>
						<div id="pop_content_transfer_acountno_select_tip" class="pop_content_input_tip_hidden">请选择提现账户</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							提现金额：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_amount_id" name="amount" class="pop_content_input_invoice" type="text" onblur="checkTransferAmount(${sessionScope.user.depositStr})" />
						</div>
						<div id="pop_content_transfer_amount_tip" class="pop_content_input_tip_hidden">请输入正确的提现金额</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							账号：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_acountno_id" name="accountNo" class="pop_content_input_invoice" type="text" onblur="checkAccountNo()" />
						</div>
						<div id="pop_content_transfer_acountno_tip" class="pop_content_input_tip_hidden">请输入正确的账号</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							账户名：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_acountname_id" name="accountName" class="pop_content_input_invoice" type="text" onblur="checkAccountName()" />
						</div>
						<div id="pop_content_transfer_acountname_tip" class="pop_content_input_tip_hidden">请输入正确的账户名</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							开户行：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_acountbank_id" name="accountBank" class="pop_content_input_invoice" type="text" onblur="checkAccountBank()" />
						</div>
						<div id="pop_content_transfer_acountbank_tip" class="pop_content_input_tip_hidden">请输入正确的开户行</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							地址：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_address_id" name="bankAddress" class="pop_content_input_invoice" type="text" onblur="checkAddress()" />
						</div>
						<div id="pop_content_transfer_address_tip" class="pop_content_input_tip_hidden">请输入正确的地址</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							电话：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_mobile_id" name="mobile" class="pop_content_input_invoice" type="text" onblur="checkMobile()" />
						</div>
						<div id="pop_content_transfer_mobile_tip" class="pop_content_input_tip_hidden">请输入正确的电话</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							验证码：
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input id="pop_content_transfer_verifycode_id" class="pop_content_input_verifycode" type="text" onchange="checkCompany()" />
							<input id="pop_content_transfer_verifycode_button" class="pop_content_input_verifycode_button" type="button" value="获取验证码" onclick="sendTransferdepositVerifyCode(${sessionScope.user.mobileNo})" />
						</div>
						<div id="pop_content_transfer_verifycode_tip1" class="pop_content_input_tip_hidden">验证码错误</div>
						<div id="pop_content_transfer_verifycode_tip2" class="pop_content_input_tip_hidden">已发送到手机18682731302</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
						</div>
					</div>
					<div class="pop_dialog_invoice_right">
						<div class="pop_content_content">
							<input class="pop_invoice_content_button" type="button" value="提交代付请求" onclick="saveTransferRequest()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>

