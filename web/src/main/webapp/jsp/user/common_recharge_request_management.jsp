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
					所有请求
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<form id="products_search" action="Login.action" method="post">
				</form>
				<div class="product_list_filters">
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							请求时间：
						</div>
						<div class="product_list_filter_item_right">
							<select class="product_list_filter_item_select" onchange="selectRechargeLastmonth(this.value, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestTypeFilter}, ${requestStatusFilter})" >
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
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							充值方式：
						</div>
						<div class="product_list_filter_item_right">
							<select class="product_list_filter_item_select" onchange="selectRechargeType(this.value, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestTypeFilter}, ${requestStatusFilter})">
								<s:if test='requestTypeFilter == 0'>
									<option selected="selected" value="0">所有方式</option>
									<option value="1">承兑充值</option>
									<option value="2">转账充值</option>
								</s:if>
								<s:elseif test='requestTypeFilter == 1'>
									<option value="0">所有方式</option>
									<option selected="selected" value="1">承兑充值</option>
									<option value="2">转账充值</option>
								</s:elseif>
								<s:elseif test='requestTypeFilter == 2'>
									<option value="0">所有方式</option>
									<option value="1">承兑充值</option>
									<option selected="selected" value="2">转账充值</option>
								</s:elseif>
							</select>
						</div>
					</div>
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							请求状态：
						</div>
						<div class="product_list_filter_item_right">
							<select class="product_list_filter_item_select" onchange="selectRechargeStatus(this.value, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestTypeFilter}, ${requestStatusFilter})" >
								<s:if test='requestStatusFilter == 0'>
									<option selected="selected" value="0">所有状态</option>
									<option value="127">已完成</option>
									<option value="1">等待转账</option>
									<option value="2">等待上传承兑</option>
									<option value="3">等待邮寄承兑</option>
									<option value="4">正在兑换承兑</option>
									<option value="5">承兑汇票审批中</option>
								</s:if>
								<s:elseif test='requestStatusFilter == 127'>
									<option value="0">所有状态</option>
									<option selected="selected" value="127">已完成</option>
									<option value="1">等待转账</option>
									<option value="2">等待上传承兑</option>
									<option value="3">等待邮寄承兑</option>
									<option value="4">正在兑换承兑</option>
									<option value="5">承兑汇票审批中</option>
								</s:elseif>
								<s:elseif test='requestStatusFilter == 1'>
									<option value="0">所有状态</option>
									<option value="127">已完成</option>
									<option selected="selected" value="1">等待转账</option>
									<option value="2">等待上传承兑</option>
									<option value="3">等待邮寄承兑</option>
									<option value="4">正在兑换承兑</option>
									<option value="5">承兑汇票审批中</option>
								</s:elseif>
								<s:elseif test='requestStatusFilter == 2'>
									<option value="0">所有状态</option>
									<option value="127">已完成</option>
									<option value="1">等待转账</option>
									<option selected="selected" value="2">等待上传承兑</option>
									<option value="3">等待邮寄承兑</option>
									<option value="4">正在兑换承兑</option>
									<option value="5">承兑汇票审批中</option>
								</s:elseif>
								<s:elseif test='requestStatusFilter == 3'>
									<option value="0">所有状态</option>
									<option value="127">已完成</option>
									<option value="1">等待转账</option>
									<option value="2">等待上传承兑</option>
									<option selected="selected" value="3">等待邮寄承兑</option>
									<option value="4">正在兑换承兑</option>
									<option value="5">承兑汇票审批中</option>
								</s:elseif>
								<s:elseif test='requestStatusFilter == 4'>
									<option value="0">所有状态</option>
									<option value="127">已完成</option>
									<option value="1">等待转账</option>
									<option value="2">等待上传承兑</option>
									<option value="3">等待邮寄承兑</option>
									<option selected="selected" value="4">正在兑换承兑</option>
									<option value="5">承兑汇票审批中</option>
								</s:elseif>
								<s:elseif test='requestStatusFilter == 5'>
									<option value="0">所有状态</option>
									<option value="127">已完成</option>
									<option value="1">等待转账</option>
									<option value="2">等待上传承兑</option>
									<option value="3">等待邮寄承兑</option>
									<option value="4">正在兑换承兑</option>
									<option selected="selected" value="5">承兑汇票审批中</option>
								</s:elseif>
							</select>
						</div>
					</div>
					<a href="RechargeRequestManagement_newRequest.action">
						<div class="product_list_create_button hover_pointer">
							新建请求
						</div>
					</a>
				</div>
				<div class="order_page_product_list_title_wrapper">
					<div class="order_page_product_list_title">
						<div class="order_page_product_list_title_company">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">充值金额</div></font>
						</div>
						<div class="order_page_product_list_title_accountno">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">充值方式</div></font>
						</div>
						<div class="order_page_product_list_title_accountno">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">请求状态</div></font>
						</div>
						<div class="order_page_product_list_title_accountno">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">提交时间</div></font>
						</div>
						<div class="order_page_product_list_title_op">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">操作</div></font>
						</div>
					</div>
				</div>
				<s:iterator value="requests" id="request">
				<div class="order_page_product_item">
					<div class="order_page_product_list_title_company_content">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper">
								￥${request.amount}
							</div>
						</font>
					</div>
					<div class="order_page_product_list_title_accountno">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper">
							<s:if test='#request.requestType == 1'>
								承兑充值
							</s:if>
							<s:elseif test='#request.requestType == 2'>
								转账充值
							</s:elseif>
							</div>
						</font>
					</div>
					<div class="order_page_product_list_title_accountno">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper">
								<s:if test='#request.requestStatus == 1'>
									<font class="font_red">等待转账</font>
								</s:if>
								<s:elseif test='#request.requestStatus == 2'>
									<font class="font_red">等待上传承兑汇票</font>
								</s:elseif>
								<s:elseif test='#request.requestStatus == 3'>
									<font class="font_red">等待邮寄承兑汇票</font>
								</s:elseif>
								<s:elseif test='#request.requestStatus == 4'>
									<font class="font_red">正在兑换承兑汇票</font>
								</s:elseif>
								<s:elseif test='#request.requestStatus == 5'>
									<font class="font_red">承兑汇票审批中</font>
									<font class="order_page_font_blue">
										<div class="order_page_product_price_wrapper">
											图片<img src="./img/more_down.png" />
											<div class="acbill_pic_pop">
												<s:iterator value="#request.acBillPaths" id="path">
													<a href="${path}" target="_blank"><img class="acceptance_bill_small" src="${path}" /></a>
												</s:iterator>
											</div>
										</div>
									</font>
								</s:elseif>
								<s:elseif test='#request.requestStatus == 127'>
									<font class="font_green">充值完成</font>
								</s:elseif>
							</div>
						</font>
					</div>
					<div class="order_page_product_list_title_accountno">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper"><s:date name="#request.createdtime" format="yyyy-MM-dd HH:mm:ss" /></div></font>
					</div>
					<div class="order_page_product_list_title_op">
						<s:if test="#request.requestStatus == 1">
							<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="deleteRechargeRequest(${request.requestId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestTypeFilter}, ${requestStatusFilter})" >取消</div></font>
						</s:if>
						<s:elseif test="#request.requestStatus == 2">
							<a href="AcceptanceBillRecharge.action?amount=${request.amount}&requestId=${request.requestId}">
								<div class="order_page_product_price_wrapper hover_pointer" >上传</div>
							</a>
							<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="deleteRechargeRequest(${request.requestId}, ${paginator.currentPage}, ${queryType}, ${lastMonth}, ${requestTypeFilter}, ${requestStatusFilter})" >取消</div></font>
						</s:elseif>
					</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
								<div class="content_paginator_number_3">上一页</div>
							</a>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
								<div class="content_paginator_number_selected">1</div>
							</a>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
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
									<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${current-1}</div>
									</a>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
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
								<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">${paginator.totalPage}</div>
								</a>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">${paginator.totalPage}</div>
								</a>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<a href="RechargeRequestManagement_allRequests.action?requestTypeFilter=${requestTypeFilter}&requestStatusFilter=${requestStatusFilter}&currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
								<div class="content_paginator_number_3">下一页</div>
							</a>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<form name="page_form" action="RechargeRequestManagement_allRequests.action">
								<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
								<input type="hidden" name="queryType" value="${queryType}" />
								<input type="hidden" name="lastMonth" value="${lastMonth}" />
								<input type="hidden" name="requestTypeFilter" value="${requestTypeFilter}" />
								<input type="hidden" name="requestStatusFilter" value="${requestStatusFilter}" />
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

