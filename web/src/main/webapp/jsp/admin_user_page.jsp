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
<script type="text/javascript" src="./js/admin_query_order.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/main_search.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

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
			<%@ include file="./user/admin_ordermanagement_menu.jsp" %>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
				<s:if test='queryType == "0"'>
					所有订单
				</s:if>
				<s:elseif test='queryType == "1"'>
					未付款的订单
				</s:elseif>
				<s:elseif test='queryType == "2"'>
					待收货的订单
				</s:elseif>
				<s:elseif test='queryType == "3"'>
					待提货的订单
				</s:elseif>
				<s:elseif test='queryType == "4"'>
					已完成的订单
				</s:elseif>
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<div class="my_zhixuan_right_content_filter">
					<div class="my_zhixuan_right_content_filter_item my_zhixuan_right_content_filter_date">
						<s:if test='lastMonth == "0"'>
							所有订单 
						</s:if>
						<s:elseif test='lastMonth == "1"'>
							最近一月的订单 
						</s:elseif>
						<s:elseif test='lastMonth == "3"'>
							最近三个月订单 
						</s:elseif>
						<s:elseif test='lastMonth == "6"'>
							最近六个月订单 
						</s:elseif>
						<s:elseif test='lastMonth == "12"'>
							最近一年的订单 
						</s:elseif>
						<s:else>
							最近${lastMonth}个月订单 
						</s:else>
						<img src="./img/more_down_gray.png" />
						<div class="my_zhixuan_right_content_filter_date_filter_dropdown">
							<ul>
								<li class="my_zhixuan_right_content_filter_date_filter_dropdown_item" onclick="datefilter(${queryType}, 1)">
									最近一月的订单
								</li>
								<li class="my_zhixuan_right_content_filter_date_filter_dropdown_item" onclick="datefilter(${queryType}, 3)">
									最近三个月订单
								</li>
								<li class="my_zhixuan_right_content_filter_date_filter_dropdown_item" onclick="datefilter(${queryType}, 6)">
									最近六个月订单
								</li>
								<li class="my_zhixuan_right_content_filter_date_filter_dropdown_item" onclick="datefilter(${queryType}, 12)">
									最近一年的订单
								</li>
								<li class="my_zhixuan_right_content_filter_date_filter_dropdown_item" onclick="datefilter(${queryType}, 0)">
									所有订单
								</li>
							</ul>
						</div>
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
								<a href="AdminQueryOrder_allOrders.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										全部订单
									</li>
								</a>
								<a href="AdminQueryOrder_unpaid.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										未付款
									</li>
								</a>
								<a href="AdminQueryOrder_waitingReceived.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										待收货
									</li>
								</a>
								<a href="AdminQueryOrder_waitingGot.action">
									<li class="my_zhixuan_right_content_filter_status_dropdown_item">
										待提货
									</li>
								</a>
								<a href="AdminQueryOrder_doneOrder.action">
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
						<div class="right_content_order_item_title_poNo">
							${order.user.userName}
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
										<a href="ProductDetail.action?productId=${orderItem.product.productId}" target="_blank"><font class="font_light_gray">${orderItem.product.productName} ${orderItem.product.description}</font></a>
									</div>
									<div class="content_order_item_product_link_wrapper">
										<s:if test='#orderItem.product.merchantCompany != ""'>
											<a><font class="font_light_gray">品牌：${orderItem.product.brand.brandName}</font></a>
										</s:if>
										<s:else>
											<a><font class="font_light_gray">品牌：${orderItem.product.brand.brandName}</font></a>
											<br>
											<a href="Company.action?companyId=${orderItem.product.merchantCompany.companyId}" target="_blank"><font class="font_red_yahei">经销商：${orderItem.product.merchantCompany.companyName}</font></a>
										</s:else>
									</div>
									<s:if test='#order.deliverType == "3"'>
										<div class="content_order_item_product_link_warehouse1_wrapper">
											<font class="font_blue font_style_yahei content_order_item_product_warehouse_link">仓库：${orderItem.product.warehouse.city.cityName}-${orderItem.product.warehouse.address}</font>
										</div>
										<div class="content_order_item_product_link_warehouse_wrapper">
											<font class="font_blue font_style_yahei content_order_item_product_warehouse_link">库管员：${orderItem.product.warehouse.warehouseAdmin.realName}&nbsp;${orderItem.product.warehouse.warehouseAdmin.mobileNo}</font>
										</div>
									</s:if>									
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
													<div>正在发货</div>
													<div class="product_item_operation" onclick="confirmDeliver(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认发货</font></div>
												</s:if>
												<s:elseif test='#orderItem.orderItemStatus == "2"'>
													<div><font class="font_light_gray">等待收货</font></div> 
													<div class="product_item_operation" onclick="confirmReceive(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认收货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "3"'>
													<div><font class="font_light_gray">等待提货</font></div> 
													<div class="product_item_operation" onclick="confirmGet(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认提货</font></div> 
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
													<div>正在发货</div>
													<div class="product_item_operation" onclick="confirmDeliver(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认发货</font></div>
												</s:if>
												<s:elseif test='#orderItem.orderItemStatus == "2"'>
													<div><font class="font_light_gray">等待收货</font></div> 
													<div class="product_item_operation" onclick="confirmReceive(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认收货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "3"'>
													<div><font class="font_light_gray">等待提货</font></div> 
													<div class="product_item_operation" onclick="confirmGet(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认提货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "4"'>
													<div><font class="font_light_gray">已收货</font></div> 
												</s:elseif>
												</form>
											</s:elseif>
											<s:elseif test='queryType == "3"'>
												<form id="myzhixuan_product_item_operation${orderItem.orderItemId}">
												<s:if test='#orderItem.orderItemStatus == "1"'>
													<div>正在发货</div>
													<div class="product_item_operation" onclick="confirmDeliver(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认发货</font></div>
												</s:if>
												<s:elseif test='#orderItem.orderItemStatus == "2"'>
													<div><font class="font_light_gray">等待收货</font></div> 
													<div class="product_item_operation" onclick="confirmReceive(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认收货</font></div> 
												</s:elseif>
												<s:elseif test='#orderItem.orderItemStatus == "3"'>
													<div><font class="font_light_gray">等待提货</font></div> 
													<div class="product_item_operation" onclick="confirmGet(${orderItem.orderItemId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">确认提货</font></div> 
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
										等待上传承兑汇票
										<div class="text_operation" onclick="acceptanceBillPay(${order.purchaseOrderId}, ${currentPage}, ${lastMonth})"><font class="font_red">上传</font></div>
									</s:if>
									<s:elseif test='#order.subStatus == "102"'>
										承兑汇票审核中
										<div class="text_operation" onclick="checkBillValid(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">审核通过</font></div>
										<div class="text_operation" onclick="checkBillInvalid(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">审核不过</font></div>
									</s:elseif>
									<s:elseif test='#order.subStatus == "103"'>
										等待接收承兑汇票
										<div class="text_operation" onclick="receiveBill(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">已接收</font></div>
									</s:elseif>
									<s:elseif test='#order.subStatus == "-1"'>
										<font class="font_red">等待付款</font>
									</s:elseif>
								</s:if>
								<s:elseif test='#order.status == "2"'>
									<s:if test='queryType == "0"'>
										已付款
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
									<s:else>
										已付款
									</s:else>
								</s:elseif>
								<s:elseif test='#order.status == "3"'>
									已收货
								</s:elseif>
							</div>
							<div class="my_zhixuan_right_content_order_item_right_item_button">
							<form id="my_zhixuan_admin_settopaid_form">
							<s:if test='queryType == "1"'>
								<s:if test='#order.status == "1"'>
									<div class="my_zhixuan_right_content_order_item_right_button_setpaid" onclick="adminSetToPaid(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})">置为已付</div>
									<div class="cancel_order_admin" onclick="cancelOrder(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">取消订单</font></div>
								</s:if>
							</s:if>
							<s:elseif test='queryType == "0"'>
								<s:if test='#order.status == "1"'>
									<div class="my_zhixuan_right_content_order_item_right_button_setpaid" onclick="adminSetToPaid(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})">置为已付</div>
									<div class="cancel_order_admin" onclick="cancelOrder(${order.purchaseOrderId}, ${queryType}, ${currentPage}, ${lastMonth})"><font class="font_red">取消订单</font></div>
								</s:if>
							</s:elseif>
							</form>
							</div>
						</div>
					</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="AdminQueryOrder_unpaid.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="AdminQueryOrder_waitingReceived.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="AdminQueryOrder_waitingGot.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="AdminQueryOrder_doneOrder.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.prePage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:else>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<s:if test='queryType == "0"'>
								<a href="AdminQueryOrder_allOrders.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="AdminQueryOrder_unpaid.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="AdminQueryOrder_waitingReceived.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="AdminQueryOrder_waitingGot.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="AdminQueryOrder_doneOrder.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="AdminQueryOrder_allOrders.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:else>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="AdminQueryOrder_allOrders.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="AdminQueryOrder_unpaid.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="AdminQueryOrder_waitingReceived.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="AdminQueryOrder_waitingGot.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="AdminQueryOrder_doneOrder.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="AdminQueryOrder_allOrders.action?currentPage=1&queryType=${queryType}&lastMonth=${lastMonth}">
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
										<a href="AdminQueryOrder_allOrders.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "1"'>
										<a href="AdminQueryOrder_unpaid.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										<a href="AdminQueryOrder_waitingReceived.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										<a href="AdminQueryOrder_waitingGot.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "4"'>
										<a href="AdminQueryOrder_doneOrder.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="AdminQueryOrder_allOrders.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:else>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<s:if test='queryType == "0"'>
										<a href="AdminQueryOrder_allOrders.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "1"'>
										<a href="AdminQueryOrder_unpaid.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										<a href="AdminQueryOrder_waitingReceived.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										<a href="AdminQueryOrder_waitingGot.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "4"'>
										<a href="AdminQueryOrder_doneOrder.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="AdminQueryOrder_allOrders.action?currentPage=${current-1}&queryType=${queryType}&lastMonth=${lastMonth}">
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
									<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "1"'>
									<a href="AdminQueryOrder_unpaid.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "2"'>
									<a href="AdminQueryOrder_waitingReceived.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "3"'>
									<a href="AdminQueryOrder_waitingGot.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "4"'>
									<a href="AdminQueryOrder_doneOrder.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<s:if test='queryType == "0"'>
									<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "1"'>
									<a href="AdminQueryOrder_unpaid.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "2"'>
									<a href="AdminQueryOrder_waitingReceived.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "3"'>
									<a href="AdminQueryOrder_waitingGot.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "4"'>
									<a href="AdminQueryOrder_doneOrder.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.totalPage}&queryType=${queryType}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="AdminQueryOrder_unpaid.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="AdminQueryOrder_waitingReceived.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="AdminQueryOrder_waitingGot.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<a href="AdminQueryOrder_doneOrder.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="AdminQueryOrder_allOrders.action?currentPage=${paginator.nextPage}&queryType=${queryType}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:else>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<s:if test='queryType == "0"'>
								<form name="page_form" action="AdminQueryOrder_allOrders.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<form name="page_form" action="AdminQueryOrder_unpaid.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<form name="page_form" action="AdminQueryOrder_waitingReceived.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<form name="page_form" action="AdminQueryOrder_waitingGot.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "4"'>
								<form name="page_form" action="AdminQueryOrder_doneOrder.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:else>
								<form name="page_form" action="AdminQueryOrder_allOrders.action">
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
</body>
</html>

