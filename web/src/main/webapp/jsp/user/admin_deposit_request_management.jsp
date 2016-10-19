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
<script type="text/javascript" src="./js/admin_user.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="../common/header.jsp" %>

<div id="top_navigator_red_bg">
	<div id="top_navigator_wrapper">
		<div id="top_navigator_logo">
			<a href="TitaniumMainPage.action"><img id="top_navigator_logo" src="./img/logo_red_small_red_bg.png" /></a>
		</div>
		<div id="user_page_title">
			<span id="user_page_title_span">管理后台</span>
		</div>
		<div class="user_page_navi">
			<ul>
				<a href="AdminUserManagement_allUsers.action">
				<li class="user_page_navi_li hover_pointer">
					基础信息管理
				</li>
				</a>
				<a href="AdminProductManagement_allProducts.action">
				<li class="user_page_navi_li hover_pointer">
					产品管理
				</li>
				</a>
				<a href="AdminQueryOrder_allOrders.action">
				<li class="user_page_navi_li_selected hover_pointer">
					订单管理
				</li>
				</a>
			</ul>
		</div>
	</div>
</div>

<div id="total">
<div id="total_wrapper">
<div id="content">
	<div id="my_zhixuan_wrapper">
		<div id="my_zhixuan_left">
			<%@ include file="admin_ordermanagement_menu.jsp" %>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					所有请求
				</div>
			</div>
			<form id="common_deposit_request_formid">
			</form>
			<div class="my_zhixuan_right_content">
				<div class="product_list_filters">
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							提交人：
						</div>
						<div class="product_list_filter_item_right">
							<input id="request_search_keyword" class="product_list_filter_item_input" value="${keyword}" onkeydown="if(event.keyCode==13){submit_request_search_form(${bankAccountId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestStatusSelected});}" />
						</div>
					</div>
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							请求状态：
						</div>
						<div class="product_list_filter_item_right">
							<select class="product_list_filter_item_select" onchange="selectStatus(this.value, ${bankAccountId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestStatusSelected})" >
								<s:if test='requestStatusSelected == 0'>
									<option selected="selected" value="0">所有请求</option>
									<option value="1">正在处理</option>
									<option value="2">已完成</option>
								</s:if>
								<s:elseif test='requestStatusSelected == 1'>
									<option value="0">所有请求</option>
									<option selected="selected" value="1">正在处理</option>
									<option value="2">已完成</option>
								</s:elseif>
								<s:elseif test='requestStatusSelected == 2'>
									<option value="0">所有请求</option>
									<option value="1">正在处理</option>
									<option selected="selected" value="2">已完成</option>
								</s:elseif>
							</select>
						</div>
					</div>
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							请求时间：
						</div>
						<div class="product_list_filter_item_right">
							<select class="product_list_filter_item_select" onchange="selectLastmonth(this.value, ${bankAccountId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestStatusSelected})" >
								<s:if test='lastMonth == 0'>
									<option selected="selected" value="0">所有请求</option>
									<option value="1">最近一月</option>
									<option value="3">最近三月</option>
									<option value="6">最近半年</option>
									<option value="12">最近一年</option>
								</s:if>
								<s:elseif test='lastMonth == 1'>
									<option value="0">所有请求</option>
									<option selected="selected" value="1">最近一月</option>
									<option value="3">最近三月</option>
									<option value="6">最近半年</option>
									<option value="12">最近一年</option>
								</s:elseif>
								<s:elseif test='lastMonth == 3'>
									<option value="0">所有请求</option>
									<option value="1">最近一月</option>
									<option selected="selected" value="3">最近三月</option>
									<option value="6">最近半年</option>
									<option value="12">最近一年</option>
								</s:elseif>
								<s:elseif test='lastMonth == 6'>
									<option value="0">所有请求</option>
									<option value="1">最近一月</option>
									<option value="3">最近三月</option>
									<option selected="selected" value="6">最近半年</option>
									<option value="12">最近一年</option>
								</s:elseif>
								<s:elseif test='lastMonth == 12'>
									<option value="0">所有请求</option>
									<option value="1">最近一月</option>
									<option value="3">最近三月</option>
									<option value="6">最近半年</option>
									<option selected="selected" value="12">最近一年</option>
								</s:elseif>
							</select>
						</div>
					</div>
				</div>
				<div class="order_page_product_list_title_wrapper">
					<div class="order_page_product_list_title">
						<div class="order_page_product_list_title_accountno">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">目标账户号</div></font>
						</div>
						<div class="order_page_product_list_title_accountname">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">提交人</div></font>
						</div>
						<div class="order_page_product_list_title_company">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">金额</div></font>
						</div>
						<div class="order_page_product_list_title_status">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">请求状态</div></font>
						</div>
						<div class="order_page_product_list_title_company">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">提交时间</div></font>
						</div>
						<div class="order_page_product_list_title_op">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">操作</div></font>
						</div>
					</div>
				</div>
				<s:iterator value="requests" id="currentrequest">
					<div class="order_page_product_item">
					<div class="order_page_product_list_title_accountno_content">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper">
								${currentrequest.destAccount.accountNo}
								<div class="my_zhixuan_person_pop">
									<div class="my_zhixuan_person_pop_item">
										账户号：${currentrequest.destAccount.accountNo}
									</div>
									<div class="my_zhixuan_person_pop_item">
										账户名：${currentrequest.destAccount.companyName}
									</div>
									<div class="my_zhixuan_person_pop_item">
										开户行：${currentrequest.destAccount.accountBank}
									</div>
									<div class="my_zhixuan_person_pop_item">
										地址：${currentrequest.destAccount.bankAddress}
									</div>
									<div class="my_zhixuan_person_pop_item">
										账户手机：${currentrequest.destAccount.mobile}
									</div>
								</div>
							</div>
						</font>
					</div>
					<div class="order_page_product_list_title_accountname">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${currentrequest.user.userName}</div></font>
					</div>
					<div class="order_page_product_list_title_company">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">￥${currentrequest.amount}</div></font>
					</div>
					<div class="order_page_product_list_title_status">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper">
								<s:if test='#currentrequest.requestStatus == 1'>
									<font class="font_red">正在处理</font>
								</s:if>
								<s:elseif test='#currentrequest.requestStatus == 2'>
									<font class="font_green">已完成</font>
								</s:elseif>
							</div>
						</font>
					</div>
					<div class="order_page_product_list_title_company">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper"><s:date name="#currentrequest.createdtime" format="yyyy-MM-dd HH:mm:ss" /></div></font>
					</div>
					<div class="order_page_product_list_title_op">
						<s:if test='#currentrequest.requestStatus == 1'>
							<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="deleteRequest(${currentrequest.requestId}, ${bankAccountId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestStatusSelected})" >取消</div></font>
							<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="processRequest(${currentrequest.requestId}, ${bankAccountId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestStatusSelected})" >提现完成</div></font>
						</s:if>
						<s:elseif test='#currentrequest.requestStatus == 2'>
						</s:elseif>
						</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
								<div class="content_paginator_number_3">上一页</div>
							</a>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
								<div class="content_paginator_number_selected">1</div>
							</a>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
								<div class="content_paginator_number">1</div>
							</a>
						</li>
						</s:else>
						<s:if test="paginator.startPage > 2">
						<li class="content_paginator_li">
							<div class="content_paginator_elim">...</div>
						</li>
						</s:if>
						<s:bean name= "org.apache.struts2.util.Counter"  id= "counter" >    
							<s:param name="first"  value= "%{paginator.startPage}"  />    
							<s:param name="last"  value= "%{paginator.endPage}" />    
							<s:iterator> 
								<s:if test="current-1 == paginator.currentPage">
								<li class="content_paginator_li_selected">
									<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
										<div class="content_paginator_number_selected">${current-1}</div>
									</a>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
										<div class="content_paginator_number">${current-1}</div>
									</a>
								</li>
								</s:else>
							</s:iterator>    
						</s:bean>
						<s:if test="paginator.endPage < paginator.totalPage - 1">
						<li class="content_paginator_li">
							<div class="content_paginator_elim">...</div>
						</li>
						</s:if>
						<s:if test="paginator.totalPage > 1">
							<s:if test="paginator.currentPage == paginator.totalPage">
							<li class="content_paginator_li_selected">
								<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
									<div class="content_paginator_number_selected">${paginator.totalPage}</div>
								</a>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
									<div class="content_paginator_number">${paginator.totalPage}</div>
								</a>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<a href="AdminDepositRequestManagement_allRequest.action?bankAccountId=${bankAccountId}&currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}&requestStatusSelected=${requestStatusSelected}&keyword=${keyword}">
								<div class="content_paginator_number_3">下一页</div>
							</a>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<form name="page_form" action="AdminDepositRequestManagement_allRequest.action">
								<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
								<input type="hidden" name="queryType" value="${queryType}" />
								<input type="hidden" name="lastMonth" value="${lastMonth}" />
								<input type="hidden" name="bankAccountId" value="${bankAccountId}" />
								<input type="hidden" name="requestStatusSelected" value="${requestStatusSelected}" />
								<input type="hidden" name="keyword" value="${keyword}" />
							</form>
						</li>
						<li class="content_paginator_li">
							<div class="content_paginator_text">页</div>
						</li>
						<li class="content_paginator_li_submit content_paginator_number_onhover">
							<div class="content_paginator_number_2"  onclick="click_submit();">确定</div>
							<script>
							function click_submit(){
								page_form.submit();
							}
							</script>	
						</li>
					</ul>
				</div>
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
							<input id="pop_content_transfer_amount_id" name="amount" class="pop_content_input_invoice" type="text" onblur="checkTransferAmountPop(${sessionScope.user.depositStr})" />
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
							<input id="pop_content_transfer_acountno_id" name="accountNo" class="pop_content_input_invoice" type="text" onblur="checkAccountNoPop()" />
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
							<input id="pop_content_transfer_acountname_id" name="accountName" class="pop_content_input_invoice" type="text" onblur="checkAccountNamePop()" />
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
							<input id="pop_content_transfer_acountbank_id" name="accountBank" class="pop_content_input_invoice" type="text" onblur="checkAccountBankPop()" />
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
							<input id="pop_content_transfer_address_id" name="bankAddress" class="pop_content_input_invoice" type="text" onblur="checkAddressPop()" />
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
							<input id="pop_content_transfer_mobile_id" name="mobile" class="pop_content_input_invoice" type="text" onblur="checkMobilePop()" />
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

