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
<script type="text/javascript" src="./js/admin_warehouse_management.js" charset="UTF-8"></script>
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
				<li class="user_page_navi_li_selected hover_pointer">
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
			<%@ include file="admin_product_menu.jsp" %>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					所有仓库
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<form id="warehouse_search" action="Login.action" method="post">
				</form>
				<div class="product_list_filters">
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							搜索仓库：
						</div>
						<div class="product_list_filter_item_right">
							<input id="warehouse_list_search_input" class="product_list_filter_item_input" onkeydown="if(event.keyCode==13){submit_warehouse_search_form();}" />
						</div>
					</div>
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							位置：
						</div>
						<div class="product_list_filter_item_right">
							<select class="product_list_filter_item_select" >
								<option value="1">选择区域</option>
								<option value="1">西南</option>
								<option value="2">华东</option>
							</select>
							<select class="product_list_filter_item_select" >
								<option value="1">选择省份</option>
								<option value="1">四川</option>
								<option value="2">广州</option>
							</select>
							<select class="product_list_filter_item_select" >
								<option value="1">选择城市</option>
								<option value="1">成都</option>
								<option value="2">上海</option>
							</select>
						</div>
						<div class="admin_list_search_button hover_pointer">
							搜索
						</div>
					</div>
					<div class="product_list_create_button hover_pointer">
						新建仓库
					</div>
				</div>
				<div class="order_page_product_list_title_wrapper">
					<div class="order_page_product_list_title">
						<div class="warehouse_management_title_warehousename">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">仓库名</div></font>
						</div>
						<div class="warehouse_management_title_admin">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">仓库管理员</div></font>
						</div>
						<div class="warehouse_management_title_address">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">地址</div></font>
						</div>
						<div class="warehouse_management_title_cordinate">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">经纬度</div></font>
						</div>
						<div class="order_page_product_item_op_col">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">操作</div></font>
						</div>
					</div>
				</div>
				<s:iterator value="warehouses" id="warehouse">
				<div class="order_page_product_item">
					<div class="warehouse_management_title_warehousename_col">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${warehouse.warehouseName}</div></font>
					</div>
					<div class="warehouse_management_title_admin">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper order_page_product_warehouse_wrapper">
								${warehouse.warehouseAdmin.realName}
								<div class="order_page_product_warehouse_pop">
									<div class="order_page_product_warehouse_pop_item">
										仓库管理员：${warehouse.warehouseAdmin.realName}
									</div>
									<div class="order_page_product_warehouse_pop_item">
										联系电话：${warehouse.warehouseAdmin.mobileNo}
									</div>
								</div>
							</div>
						</font>
					</div>
					<div class="warehouse_management_title_address">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper order_page_product_warehouse_wrapper">
								${warehouse.city.province.region.regionName}-${warehouse.city.province.provinceName}-${warehouse.city.cityName}
								<div class="order_page_product_warehouse_pop">
									<div class="order_page_product_warehouse_pop_item">
										所在城市：${warehouse.city.province.provinceName}-${warehouse.city.cityName}
									</div>
									<div class="order_page_product_warehouse_pop_item">
										详细地址：${warehouse.address}
									</div>
								</div>
							</div>
						</font>
					</div>
					<div class="warehouse_management_title_cordinate">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">LNG：${warehouse.longitude}</div></font>
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">LAT：${warehouse.latitude}</div></font>
					</div>
					<div class="order_page_product_item_op_col">
						<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer">修改</div></font>
						<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="deleteWarehouse(${queryType}, ${warehouse.warehouseId}, ${paginator.currentPage})" >删除</div></font>
					</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "30"'>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "31"'>
								<a href="${paginator.actionName}_supplierCompanies.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "32"'>
								<a href="${paginator.actionName}_merchantCompanies.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "33"'>
								<a href="${paginator.actionName}_disabledCompanies.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:else>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<s:if test='queryType == "30"'>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "31"'>
								<a href="${paginator.actionName}_supplierCompanies.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "32"'>
								<a href="${paginator.actionName}_merchantCompanies.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "33"'>
								<a href="${paginator.actionName}_disabledCompanies.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:else>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "30"'>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "31"'>
								<a href="${paginator.actionName}_supplierCompanies.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "32"'>
								<a href="${paginator.actionName}_merchantCompanies.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "33"'>
								<a href="${paginator.actionName}_disabledCompanies.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=1&queryType=${queryType}&keyword=${keyword}">
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
									<s:if test='queryType == "30"'>
										<a href="${paginator.actionName}_allWarehouses.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "31"'>
										<a href="${paginator.actionName}_supplierCompanies.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "32"'>
										<a href="${paginator.actionName}_merchantCompanies.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "33"'>
										<a href="${paginator.actionName}_disabledCompanies.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="${paginator.actionName}_allWarehouses.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:else>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<s:if test='queryType == "30"'>
										<a href="${paginator.actionName}_allWarehouses.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "31"'>
										<a href="${paginator.actionName}_supplierCompanies.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "32"'>
										<a href="${paginator.actionName}_merchantCompanies.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "33"'>
										<a href="${paginator.actionName}_disabledCompanies.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="${paginator.actionName}_allWarehouses.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}">
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
								<s:if test='queryType == "30"'>
									<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "31"'>
									<a href="${paginator.actionName}_supplierCompanies.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "32"'>
									<a href="${paginator.actionName}_merchantCompanies.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "33"'>
									<a href="${paginator.actionName}_disabledCompanies.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<s:if test='queryType == "30"'>
									<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "31"'>
									<a href="${paginator.actionName}_supplierCompanies.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "32"'>
									<a href="${paginator.actionName}_merchantCompanies.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "33"'>
									<a href="${paginator.actionName}_disabledCompanies.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "30"'>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "31"'>
								<a href="${paginator.actionName}_supplierCompanies.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "32"'>
								<a href="${paginator.actionName}_merchantCompanies.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "33"'>
								<a href="${paginator.actionName}_disabledCompanies.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allWarehouses.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:else>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<s:if test='queryType == "30"'>
								<form name="page_form" action="${paginator.actionName}_allWarehouses.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:if>
							<s:elseif test='queryType == "31"'>
								<form name="page_form" action="${paginator.actionName}_supplierCompanies.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "32"'>
								<form name="page_form" action="${paginator.actionName}_merchantCompanies.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "33"'>
								<form name="page_form" action="${paginator.actionName}_disabledCompanies.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:else>
								<form name="page_form" action="${paginator.actionName}_allWarehouses.action">
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

