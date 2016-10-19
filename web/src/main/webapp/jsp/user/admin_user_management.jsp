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
<script type="text/javascript" src="./js/admin_user_management.js" charset="UTF-8"></script>
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
				<li class="user_page_navi_li_selected hover_pointer">
					基础信息管理
				</li>
				</a>
				<a href="AdminProductManagement_allProducts.action">
				<li class="user_page_navi_li hover_pointer">
					产品管理
				</li>
				</a>
				<a href="AdminQueryOrder_allOrders.action">
				<li class="user_page_navi_li hover_pointer">
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
			<%@ include file="admin_basicinfo_menu.jsp" %>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
				<s:if test='queryType == "10"'>
					所有用户
				</s:if>
				<s:elseif test='queryType == "11"'>
					贸易商用户
				</s:elseif>
				<s:elseif test='queryType == "12"'>
					供应商用户
				</s:elseif>
				<s:elseif test='queryType == "13"'>
					普通用户
				</s:elseif>
				<s:elseif test='queryType == "14"'>
					系统管理员
				</s:elseif>
				<s:elseif test='queryType == "15"'>
					仓库管理员
				</s:elseif>
				<s:else>
					所有用户
				</s:else>
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<form id="users_search" method="post">
				</form>
				<div class="product_list_filters">
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							搜索用户：
						</div>
						<div class="product_list_filter_item_right">
							<input id="user_list_search_input" class="product_list_filter_item_input" value="${keyword}" onkeydown="if(event.keyCode==13){submit_user_search_form(${queryType});}" />
						</div>
					</div>
					<a href="AdminUserAdd.action?queryType=${queryType}">
					<div class="product_list_create_button hover_pointer">
						新建用户
					</div>
					</a>
				</div>
				<div class="order_page_product_list_title_wrapper">
					<div class="order_page_product_list_title">
						<div class="user_list_title_username">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">用户名</div></font>
						</div>
						<div class="user_list_title_companyname">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">公司名</div></font>
						</div>
						<div class="user_list_title_mobile">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">用户状态</div></font>
						</div>
						<div class="user_list_title_role">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">角色</div></font>
						</div>
						<div class="user_list_title_createtime">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">创建时间</div></font>
						</div>
						<div class="user_list_title_op">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">操作</div></font>
						</div>
					</div>
				</div>
				<s:iterator value="users" id="currentUser">
				<div class="order_page_product_item">
					<div class="user_list_title_username_item">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${currentUser.userName}</div></font>
					</div>
					<div class="user_list_title_companyname">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${currentUser.externalCompanyName}</div></font>
					</div>
					<div class="user_list_title_mobile">
						<s:if test='#currentUser.companyLicenseStatus == "0"'>
							<font class="order_page_font_red">
								<div class="order_page_product_price_wrapper">
									待提交公司证件信息
								</div>
							</font>
						</s:if>
						<s:elseif test='#currentUser.companyLicenseStatus == "1"'>
							<font class="order_page_font_red">
								<div class="order_page_product_price_wrapper">
									审核中
								</div>
							</font>
						</s:elseif>
						<s:elseif test='#currentUser.companyLicenseStatus == "2"'>
							<font class="order_page_font_green">
								<div class="order_page_product_price_wrapper">
									审核通过
								</div>
							</font>
						</s:elseif>
					</div>
					<div class="user_list_title_role"><font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${currentUser.role.roleName}</div></font>
					</div>
					<div class="user_list_title_createtime">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper"><s:date name="#currentUser.createTime" format="yyyy-MM-dd HH:mm:ss" /></div></font>
					</div>
					<div class="user_list_title_op">
						<a href="AdminUserEdit.action?userId=${currentUser.userId}&queryType=${queryType}"><font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer">修改</div></font></a>
					</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "10"'>
								<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "11"'>
								<a href="${paginator.actionName}_merchantUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "12"'>
								<a href="${paginator.actionName}_supplierUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "13"'>
								<a href="${paginator.actionName}_commonUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "14"'>
								<a href="${paginator.actionName}_adminUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "15"'>
								<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:else>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<s:if test='queryType == "10"'>
								<a href="${paginator.actionName}_allUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "11"'>
								<a href="${paginator.actionName}_merchantUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "12"'>
								<a href="${paginator.actionName}_supplierUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "13"'>
								<a href="${paginator.actionName}_commonUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "14"'>
								<a href="${paginator.actionName}_adminUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "15"'>
								<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:else>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "10"'>
								<a href="${paginator.actionName}_allUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "11"'>
								<a href="${paginator.actionName}_merchantUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "12"'>
								<a href="${paginator.actionName}_supplierUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "13"'>
								<a href="${paginator.actionName}_commonUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "14"'>
								<a href="${paginator.actionName}_adminUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "15"'>
								<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allUsers.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
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
									<s:if test='queryType == "10"'>
										<a href="${paginator.actionName}_allUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "11"'>
										<a href="${paginator.actionName}_merchantUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "12"'>
										<a href="${paginator.actionName}_supplierUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "13"'>
										<a href="${paginator.actionName}_commonUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "14"'>
										<a href="${paginator.actionName}_adminUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "15"'>
										<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="${paginator.actionName}_allUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:else>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<s:if test='queryType == "10"'>
										<a href="${paginator.actionName}_allUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "11"'>
										<a href="${paginator.actionName}_merchantUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "12"'>
										<a href="${paginator.actionName}_supplierUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "13"'>
										<a href="${paginator.actionName}_commonUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "14"'>
										<a href="${paginator.actionName}_adminUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "15"'>
										<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="${paginator.actionName}_allUsers.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
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
								<s:if test='queryType == "10"'>
									<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "11"'>
									<a href="${paginator.actionName}_merchantUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "12"'>
									<a href="${paginator.actionName}_supplierUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "13"'>
									<a href="${paginator.actionName}_commonUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "14"'>
									<a href="${paginator.actionName}_adminUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "15"'>
									<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<s:if test='queryType == "10"'>
									<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "11"'>
									<a href="${paginator.actionName}_merchantUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "12"'>
									<a href="${paginator.actionName}_supplierUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "13"'>
									<a href="${paginator.actionName}_commonUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "14"'>
									<a href="${paginator.actionName}_adminUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "15"'>
									<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "10"'>
								<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "11"'>
								<a href="${paginator.actionName}_merchantUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "12"'>
								<a href="${paginator.actionName}_supplierUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "13"'>
								<a href="${paginator.actionName}_commonUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "14"'>
								<a href="${paginator.actionName}_adminUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "15"'>
								<a href="${paginator.actionName}_warehouseAdminUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allUsers.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:else>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<s:if test='queryType == "10"'>
								<form name="page_form" action="${paginator.actionName}_allUsers.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:if>
							<s:elseif test='queryType == "11"'>
								<form name="page_form" action="${paginator.actionName}_merchantUsers.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "12"'>
								<form name="page_form" action="${paginator.actionName}_supplierUsers.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "13"'>
								<form name="page_form" action="${paginator.actionName}_commonUsers.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "14"'>
								<form name="page_form" action="${paginator.actionName}_adminUsers.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "15"'>
								<form name="page_form" action="${paginator.actionName}_warehouseAdminUsers.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:else>
								<form name="page_form" action="${paginator.actionName}_allUsers.action">
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
<%@ include file="../common/footer.jsp" %>
</body>
</html>

