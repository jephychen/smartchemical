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
<script type="text/javascript" src="./js/query_order.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/main_search.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

<div id="top_navigator_blue_bg">
	<div id="top_navigator_wrapper">
		<div id="top_navigator_logo">
			<a href="TitaniumMainPage.action"><img id="top_navigator_logo" src="./img/logo_blue_small_blue_bg.png" /></a>
		</div>
		<div id="user_page_title">
			<span id="user_page_title_span">个人中心</span>
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
			<%@ include file="./user/common_user_menu.jsp" %>
		</div>
		<div id="my_zhixuan_right">
			<div class="user_profile">
				<div class="user_profile_left">
					<div class="user_photo_div">
						<img class="user_photo" src="./img/usericon.png" />
					</div>
				</div>
				<div class="user_profile_right">
					<div class="user_profile_right_order_status">
						<div class="user_profile_right_order_status_title">
							订单状态
						</div>
						<div class="user_profile_right_order_status_content">
							<div class="user_profile_right_order_status_content_item">
								<a href="QueryOrder_unpaid.action">
									<div class="user_profile_right_order_status_content_item_button">
										<div class="user_profile_img_frame">
											<img class="user_profile_img" src="./img/function_icon2.png" />
										</div>
										<div class="user_profile_text_frame">
											未付款的订单 <font class="font_red">(${unpaidOrdersCount})</font>
										</div>
									</div>
								</a>
							</div>
							<div class="user_profile_right_order_status_content_item">
								<a href="QueryOrder_waitingReceived.action">
									<div class="user_profile_right_order_status_content_item_button">
										<div class="user_profile_img_frame">
											<img class="user_profile_img" src="./img/function_icon1.png" />
										</div>
										<div class="user_profile_text_frame">
											待收货的订单 <font class="font_red">(${waitingReceiveOrdersCount})</font>
										</div>
									</div>
								</a>
							</div>
							<div class="user_profile_right_order_status_content_item">
								<a href="QueryOrder_waitingGot.action">
									<div class="user_profile_right_order_status_content_item_button">
										<div class="user_profile_img_frame">
											<img class="user_profile_img" src="./img/function_icon1.png" />
										</div>
										<div class="user_profile_text_frame">
											待提货的订单 <font class="font_red">(${waitingGetOrdersCount})</font>
										</div>
									</div>
								</a>
							</div>
							<div class="user_profile_right_order_status_content_item">
								<a href="QueryOrder_doneOrder.action">
									<div class="user_profile_right_order_status_content_item_button">
										<div class="user_profile_img_frame">
											<img class="user_profile_img" src="./img/function_icon2.png" />
										</div>
										<div class="user_profile_text_frame">
											已完成的订单 <font class="font_red">(${doneOrdersCount})</font>
										</div>
									</div>
								</a>
							</div>
						</div>
					</div>
					<div class="user_profile_right_user_info">
						<div class="user_profile_right_user_info_title">
							用户信息
						</div>
						<div class="user_profile_right_order_status_content">
							<div class="userinfo_wrapper">
								<div class="user_profile_right_userinfo_item">
									&nbsp &nbsp用户名：${session.user.userName}
								</div>
								<div class="user_profile_right_userinfo_item">
									&nbsp &nbsp公&nbsp &nbsp司：${session.user.externalCompanyName}
								</div>
								<div class="user_profile_right_userinfo_item">
									账户余额：<font class="font_red">￥${session.user.depositStr}</font> &nbsp &nbsp 
									<s:if test='userAccounts.size == 0'>
										<font onclick="showGetdeposit(0, ${sessionScope.user.depositStr})" class="font_red hover_pointer">提现</font> &nbsp 
									</s:if>
									<s:else>
										<font onclick="showGetdeposit(1, ${sessionScope.user.depositStr})" class="font_red hover_pointer">提现</font> &nbsp 
									</s:else>
									<font onclick="showTransferdeposit(${sessionScope.user.depositStr})" class="font_red hover_pointer">代付</font> &nbsp
									<a href="RechargeRequestManagement_newRequest.action">
									<font class="recharge_text font_blue hover_pointer">
										充值
									</font>
									</a>
								</div>
								<div class="user_profile_right_userinfo_item">
									&nbsp &nbsp邮&nbsp &nbsp箱：${session.user.email}
								</div>
								<div class="user_profile_right_userinfo_item">
									&nbsp &nbsp手&nbsp &nbsp机：${session.user.mobileNo}
								</div>
								<div class="user_profile_right_userinfo_item">
									公司认证：
									<s:if test='#session.user.companyLicenseStatus == "0"'>
										<font class="font_gray">待上传公司证件信息</font>
										&nbsp &nbsp <a href="Register1.action"><font class="font_red hover_pointer">上传</font></a>
									</s:if>
									<s:elseif test='#session.user.companyLicenseStatus == "1"'>
										<font class="font_gray">公司证件信息审核中</font>
									</s:elseif>
									<s:elseif test='#session.user.companyLicenseStatus == "2"'>
										<font class="font_gray">已认证</font>
									</s:elseif>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<div class="user_profile_right_title_text">我的订单</div>
				<div class="my_zhixuan_right_content_filter">
					<div class="my_zhixuan_right_content_filter_item my_zhixuan_right_content_filter_date">
						
					</div>
					<div class="my_zhixuan_right_content_filter_item my_zhixuan_right_content_filter_detail">
						订单详情
					</div>
					<div class="my_zhixuan_right_content_filter_item my_zhixuan_right_content_filter_person">
						<s:if test='queryType == "0"'>
							收货人/提货人
						</s:if>
						<s:elseif test='queryType == "1"'>
							收货人/提货人
						</s:elseif>
						<s:elseif test='queryType == "2"'>
							收货人
						</s:elseif>
						<s:elseif test='queryType == "3"'>
							提货人
						</s:elseif>
						<s:elseif test='queryType == "4"'>
							收货人/提货人
						</s:elseif>
					</div>
					<div class="my_zhixuan_right_content_filter_item my_zhixuan_right_content_filter_price">
						金额
					</div>
					<div class="my_zhixuan_right_content_filter_item my_zhixuan_right_content_filter_status">
						订单状态 <img src="./img/more_down_gray.png" />
						<div class="my_zhixuan_right_content_filter_status_dropdown">
							<ul>
								<a href="QueryOrder_unpaid.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										未付款
									</li>
								</a>
								<a href="QueryOrder_waitingReceived.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										待收货
									</li>
								</a>
								<a href="QueryOrder_waitingGot.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										待提货
									</li>
								</a>
								<a href="QueryOrder_doneOrder.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										已完成
									</li>
								</a>
							</ul>
						</div>
					</div>
				</div>
				<form id="my_zhixuan_right_content_unpaid_form">
				</form>
				<s:iterator value="orders" id="order">
				<div class="my_zhixuan_right_content_order_item">
					<div class="my_zhixuan_right_content_order_item_title">
						<div class="right_content_order_item_title_orderdate">
							<s:date name="#order.createTime" format="yyyy-MM-dd HH:mm:ss" />
						</div>
						<div class="right_content_order_item_title_poNo">
							订单号：<a href="OrderDetail.action?poNo=${order.purchaseOrderNo}" target="_blank"><font class="font_red">${order.purchaseOrderNo}</font></a>
						</div>
						<div class="right_content_order_item_title_poNo">
							<s:if test='#order.deliverType == "1"'>
								厂商配送
							</s:if>
							<s:elseif test='#order.deliverType == "2"'>
								智选物流
							</s:elseif>
							<s:elseif test='#order.deliverType == "3"'>
								用户自提
							</s:elseif>
						</div>
					</div>
					<div class="my_zhixuan_right_content_order_item_content">
						<div class="my_zhixuan_right_content_order_item_productlist_wrapper">
							<s:iterator value="#order.orderItems" id="orderItem">
							<div class="my_zhixuan_right_content_order_item_product">
								<div class="content_order_item_product_col1">
									<a href="ProductDetail.action?productId=${orderItem.product.productId}" target="_blank"><img class="content_order_item_product_img" src="${orderItem.product.pictureUrl}" /></a>
								</div>
								<div class="content_order_item_product_col2">
									<div class="content_order_item_product_link_wrapper">
										<a class="content_order_item_product_link" href="ProductDetail.action?productId=${orderItem.product.productId}" target="_blank"><font class="font_light_gray">${orderItem.product.productName} ${orderItem.product.description}</font></a>
									</div>
									<div class="content_order_item_product_link_wrapper">
										<a class="content_order_item_product_link"><font class="font_light_gray">品牌：${orderItem.product.brand.brandName}</font></a>
									</div>
								</div>
								<div class="content_order_item_product_col3">
									<div class="content_order_item_product_quantity_wrapper">
										<font class="font_light_gray">${orderItem.quantity} ${orderItem.product.quantityUnit.unitName}</font>
									</div>
								</div>
								<div class="content_order_item_product_col4">
										<div class="content_order_item_product_quantity_wrapper">
											<s:if test='queryType == "0"'>
												<s:if test='#order.status != "1"'>
													<form id="myzhixuan_product_item_operation${orderItem.orderItemId}">
													<s:if test='#orderItem.orderItemStatus == "1"'>
														<s:if test='#order.deliverType != "3"'>
															<div><font class="font_red">正在发货</font></div>
														</s:if>
													</s:if>
													<s:elseif test='#orderItem.orderItemStatus == "2"'>
														<div><font class="font_light_gray">等待收货</font></div> 
														<div class="product_item_operation" onclick="confirmReceive(${orderItem.orderItemId}, ${currentPage}, ${lastMonth}, ${queryType})"><font class="font_red">确认收货</font></div> 
													</s:elseif>
													<s:elseif test='#orderItem.orderItemStatus == "3"'>
														<div><font class="font_light_gray">等待提货</font></div> 
														<div class="product_item_operation" onclick="confirmGet(${orderItem.orderItemId}, ${currentPage}, ${lastMonth}, ${queryType})"><font class="font_red">确认提货</font></div> 
													</s:elseif>
													<s:elseif test='#orderItem.orderItemStatus == "4"'>
														<div><font class="font_light_gray">已收货</font></div> 
													</s:elseif>
													</form>
												</s:if>
											</s:if>
											<s:elseif test='queryType == "1"'>
											</s:elseif>
											<s:elseif test='queryType == "2"'>
												<form id="myzhixuan_product_item_operation${orderItem.orderItemId}">
												<s:if test='#orderItem.orderItemStatus == "1"'>
													<div><font class="font_red">正在发货</font></div>
												</s:if>
												<s:elseif test='#orderItem.orderItemStatus == "2"'>
													<div><font class="font_light_gray">等待收货</font></div> 
													<div class="product_item_operation" onclick="confirmReceive(${orderItem.orderItemId}, ${currentPage}, ${lastMonth}, ${queryType})"><font class="font_red">确认收货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "3"'>
													<div><font class="font_light_gray">等待提货</font></div> 
													<div class="product_item_operation" onclick="confirmGet(${orderItem.orderItemId}, ${currentPage}, ${lastMonth}, ${queryType})"><font class="font_red">确认提货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "4"'>
													<div><font class="font_light_gray">已收货</font></div> 
												</s:elseif>
												</form>
											</s:elseif>
											<s:elseif test='queryType == "3"'>
												<form id="myzhixuan_product_item_operation${orderItem.orderItemId}">
												<s:if test='#orderItem.orderItemStatus == "1"'>
													<div><font class="font_red">正在发货</font></div>
												</s:if>
												<s:elseif test='#orderItem.orderItemStatus == "2"'>
													<div><font class="font_light_gray">等待收货</font></div> 
													<div class="product_item_operation" onclick="confirmReceive(${orderItem.orderItemId}, ${currentPage}, ${lastMonth}, ${queryType})"><font class="font_red">确认收货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "3"'>
													<div><font class="font_light_gray">等待提货</font></div> 
													<div class="product_item_operation" onclick="confirmGet(${orderItem.orderItemId}, ${currentPage}, ${lastMonth}, ${queryType})"><font class="font_red">确认提货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "4"'>
													<div><font class="font_light_gray">已收货</font></div> 
												</s:elseif>
												</form>
											</s:elseif>
											<s:elseif test='queryType == "4"'>
												<div><font class="font_light_gray">已收货</font></div> 
											</s:elseif>
										</div>
								</div>
							</div>
							</s:iterator>
						</div>
						<div class="my_zhixuan_right_content_order_item_right">
							<div class="my_zhixuan_right_content_order_item_right_item my_zhixuan_right_content_order_item_right_item_person">
								<s:if test='#order.deliverType == "1"'>
									${order.receiver.receiverName}
									<div class="my_zhixuan_person_pop">
										<div class="my_zhixuan_person_pop_item">
											收货人：${order.receiver.receiverName}
										</div>
										<div class="my_zhixuan_person_pop_item">
											手机号码：${order.receiver.mobileNo}
										</div>
										<div class="my_zhixuan_person_pop_item">
											地址：${order.receiver.city.province.provinceName}-${order.receiver.city.cityName}-${order.receiver.address}
										</div>
									</div>
								</s:if>
								<s:elseif test='#order.deliverType == "2"'>
									${order.receiver.receiverName}
									<div class="my_zhixuan_person_pop">
										<div class="my_zhixuan_person_pop_item">
											收货人：${order.receiver.receiverName}
										</div>
										<div class="my_zhixuan_person_pop_item">
											手机号码：${order.receiver.mobileNo}
										</div>
										<div class="my_zhixuan_person_pop_item">
											地址：${order.receiver.city.province.provinceName}-${order.receiver.city.cityName}-${order.receiver.address}
										</div>
									</div>
								</s:elseif>
								<s:elseif test='#order.deliverType == "3"'>
									${order.getter.getterName}
									<div class="my_zhixuan_person_pop">
										<div class="my_zhixuan_person_pop_item">
											提货人：${order.getter.getterName}
										</div>
										<div class="my_zhixuan_person_pop_item">
											身份证号：${order.getter.getterIdNo}
										</div>
										<div class="my_zhixuan_person_pop_item">
											手机号码：${order.getter.getterMobileNo}
										</div>
										<div class="my_zhixuan_person_pop_item">
											车牌号：${order.getter.truckLicenseNo}
										</div>
									</div>
								</s:elseif>
							</div>
							<div class="my_zhixuan_right_content_order_item_right_item my_zhixuan_right_content_order_item_right_item_price">
								￥${order.totalPrice}
							</div>
							<div class="my_zhixuan_right_content_order_item_right_item my_zhixuan_right_content_order_item_right_item_status">
								<s:if test='#order.status == "1"'>
									<s:if test='#order.subStatus == "101"'>
										<font class="font_red">等待上传承兑汇票</font>
									</s:if>
									<s:elseif test='#order.subStatus == "102"'>
										<font class="font_red">承兑汇票审核中</font>
									</s:elseif>
									<s:elseif test='#order.subStatus == "103"'>
										<font class="font_red">等待接收承兑汇票</font>
									</s:elseif>
									<s:elseif test='#order.subStatus == "-1"'>
										<font class="font_red">等待付款</font>
									</s:elseif>
								</s:if>
								<s:elseif test='#order.status == "2"'>
									<s:if test='queryType == "0"'>
										<s:if test='#order.deliverType == "3"'>
											等待提货
										</s:if>
										<s:else>
											等待收货
										</s:else>
									</s:if>
									<s:elseif test='queryType == "1"'>
										已付款
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										等待收货
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										等待提货
									</s:elseif>
									<s:elseif test='queryType == "4"'>
										已付款
									</s:elseif>
								</s:elseif>
								<s:elseif test='#order.status == "3"'>
									已收货
								</s:elseif>
							</div>
							<div class="my_zhixuan_right_content_order_item_right_item_button">
							<s:if test='queryType == "0"'>
								<s:if test='#order.status == "1"'>
									<s:if test='#order.paymentType == "1"'>
										<div class="my_zhixuan_right_content_order_item_right_button_paynow" onclick="onlinePay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth}, ${queryType})">付款</div>
									</s:if>
									<s:elseif test='#order.paymentType == "2"'>
										<div class="my_zhixuan_right_content_order_item_right_button_paynow" onclick="onlinePay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth}, ${queryType})">付款</div>
									</s:elseif>
									<s:elseif test='#order.paymentType == "3"'>
										<s:if test='#order.subStatus == "101"'>
										<div class="my_zhixuan_right_content_order_item_right_button_paynow" onclick="acceptanceBillPay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth}, ${queryType})">
											上传
										</div>
										</s:if>
									</s:elseif>
								</s:if>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<s:if test='#order.paymentType == "1"'>
									<div class="my_zhixuan_right_content_order_item_right_button_paynow" onclick="onlinePay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth}, ${queryType})">付款</div>
								</s:if>
								<s:elseif test='#order.paymentType == "2"'>
									<div class="my_zhixuan_right_content_order_item_right_button_paynow" onclick="onlinePay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth}, ${queryType})">付款</div>
								</s:elseif>
								<s:elseif test='#order.paymentType == "3"'>
									<s:if test='#order.subStatus == "101"'>
									<div class="my_zhixuan_right_content_order_item_right_button_paynow" onclick="acceptanceBillPay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth}, ${queryType})">
										上传
									</div>
									</s:if>
								</s:elseif>
							</s:elseif>
							</div>
						</div>
					</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="MyZhixuan.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="MyZhixuan.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="MyZhixuan.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="MyZhixuan.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="MyZhixuan.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="MyZhixuan.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:else>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<s:if test='queryType == "0"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:else>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="MyZhixuan.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:else>
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
									<s:if test='queryType == "0"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "1"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "4"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:else>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<s:if test='queryType == "0"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "1"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "4"'>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="MyZhixuan.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:else>
								</s:else>
								</li>
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
								<s:if test='queryType == "0"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "1"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "2"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "3"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "4"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<s:if test='queryType == "0"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "1"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "2"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "3"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "4"'>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="MyZhixuan.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="MyZhixuan.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="MyZhixuan.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="MyZhixuan.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="MyZhixuan.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="MyZhixuan.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="MyZhixuan.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:else>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<s:if test='queryType == "0"'>
								<form name="page_form" action="MyZhixuan.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<form name="page_form" action="MyZhixuan.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<form name="page_form" action="MyZhixuan.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<form name="page_form" action="MyZhixuan.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<form name="page_form" action="MyZhixuan.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:else>
								<form name="page_form" action="MyZhixuan.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:else>
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
<%@ include file="./common/footer.jsp" %>
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
							<input id="pop_content_getdeposit_verifycode_id" class="pop_content_input_verifycode" type="text" onchange="checkSmsverifycodeAjax(${sessionScope.user.mobileNo})" />
							<input id="pop_content_getdeposit_verifycode_button" class="pop_content_input_verifycode_button" type="button" value="获取验证码" onclick="sendGetdepositVerifyCode(${sessionScope.user.mobileNo})" />
						</div>
						<div id="pop_content_getdeposit_verifycode_tip1" class="pop_content_input_tip_hidden">验证码错误</div>
						<div id="pop_content_getdeposit_verifycode_tip2" class="pop_content_input_tip_hidden">已发送到手机${sessionScope.user.mobileNo}</div>
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
							<input id="pop_content_transfer_verifycode_id" class="pop_content_input_verifycode" type="text" onchange="checkTransferSmsverifycodeAjax(${sessionScope.user.mobileNo})" />
							<input id="pop_content_transfer_verifycode_button" class="pop_content_input_verifycode_button" type="button" value="获取验证码" onclick="sendTransferdepositVerifyCode(${sessionScope.user.mobileNo})" />
						</div>
						<div id="pop_content_transfer_verifycode_tip1" class="pop_content_input_tip_hidden">验证码错误</div>
						<div id="pop_content_transfer_verifycode_tip2" class="pop_content_input_tip_hidden">已发送到手机${sessionScope.user.mobileNo}</div>
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

