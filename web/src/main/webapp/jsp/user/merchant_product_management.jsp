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
<script type="text/javascript" src="./js/merchant_product_management.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="../common/header.jsp" %>

<div id="top_navigator_red_bg">
	<div id="top_navigator_wrapper">
		<div id="top_navigator_logo">
			<a href="TitaniumMainPage.action"><img id="top_navigator_logo" src="./img/logo_red_small_red_bg.png" /></a>
		</div>
		<div id="user_page_title">
			<span id="user_page_title_span">${pageTitle}</span>
		</div>
		<div class="user_page_navi">
			<ul>
				<a href="MerchantProductManagement_allProducts.action">
				<li class="user_page_navi_li_selected hover_pointer">
					产品管理
				</li>
				</a>
				<a href="MerchantQueryOrder_allOrders.action">
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
			<div class="my_zhixuan_menu_group">
				<ul>
					<li class="my_zhixuan_left_li_title">
						产品管理
					</li>
					<li class="my_zhixuan_left_li">
						<a href="MerchantProductManagement_allProducts.action">
							<s:if test='queryType == "0"'>
								<font class="font_red">所有产品</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">所有产品</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="MerchantProductManagement_disabledProducts.action">
							<s:if test='queryType == "3"'>
								<font class="font_red">已下架的产品</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">已下架的产品</font>
							</s:else>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
				<s:if test='queryType == "0"'>
					所有产品
				</s:if>
				<s:elseif test='queryType == "3"'>
					已下架的产品
				</s:elseif>
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<form id="products_search" action="Login.action" method="post">
				</form>
				<div class="product_list_filters">
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							搜索产品：
						</div>
						<div class="product_list_filter_item_right">
							<input id="product_list_search_input" class="product_list_filter_item_input" value="${keyword}" onkeydown="if(event.keyCode==13){submit_product_search_form(${queryType}, ${brandIdSelected});}" />
						</div>
					</div>
					<div class="product_list_filter_item">
						<div class="product_list_filter_item_left">
							品牌：
						</div>
						<div class="product_list_filter_item_right">
							<select id="product_list_search_company_dropdown" class="product_list_filter_item_select" onchange="companyFilterSelect(${queryType}, ${brandIdSelected})" >
								<option value="0">所有品牌</option>
								<s:iterator value="brands" id="brand">
									<s:if test='#brand.brandId == brandIdSelected'>
										<option selected="selected" value="${brand.brandId}">${brand.brandName}</option>
									</s:if>
									<s:else>
										<option value="${brand.brandId}">${brand.brandName}</option>
									</s:else>
								</s:iterator>
							</select>
						</div>
					</div>
					<a href="MerchantProductAdd.action?queryType=${queryType}">
					<div class="product_list_create_button hover_pointer">
						新建产品
					</div>
					</a>
				</div>
				<div class="order_page_product_list_title_wrapper">
					<div class="order_page_product_list_title">
						<div class="order_page_product_list_title_name">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">产品</div></font>
						</div>
						<div class="order_page_product_list_title_company">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">品牌</div></font>
						</div>
						<div class="order_page_product_list_title_price">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">单价</div></font>
						</div>
						<div class="order_page_product_list_title_quantity">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">库存</div></font>
						</div>
						<div class="order_page_product_list_title_price">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">仓库</div></font>
						</div>
						<div class="order_page_product_list_title_op">
							<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">操作</div></font>
						</div>
					</div>
				</div>
				<s:iterator value="products" id="product">
				<div class="order_page_product_item">
					<div class="order_page_product_item_img_col">
						<a href="ProductDetail.action?productId=${product.productId}" target="_blank" ><img class="order_page_product_item_img" src="${product.pictureUrl}" /></a>
					</div>
					<div class="order_page_product_item_product_name_col">
						<div class="order_page_product_link_wrapper">
							<a class="order_page_product_link_wrapper" href="ProductDetail.action?productId=${product.productId}" target="_blank" ><font class="order_page_font_gray">${product.productName} ${product.description}</font></a>
						</div>
					</div>
					<div class="order_page_product_item_company_col">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${product.brand.brandName}</div></font>
						<s:if test='#product.merchantCompany != ""'>
						</s:if>
						<s:else>
							<a class="content_order_item_product_link" href="Company.action?companyId=${product.merchantCompany.companyId}" target="_blank" ><font class="order_page_font_red"><div class="order_page_product_price_wrapper">经销商：${product.merchantCompany.companyName}</div></font></a>
						</s:else>
					</div>
					<div class="order_page_product_item_price_col">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">￥${product.price}</div></font>
					</div>
					<div class="order_page_product_item_quantity_col">
						<font class="order_page_font_gray"><div class="order_page_product_price_wrapper">${product.stockLevel} ${product.quantityUnit.unitName}</div></font>
					</div>
					<div class="order_page_product_item_price_col">
						<font class="order_page_font_gray">
							<div class="order_page_product_price_wrapper order_page_product_warehouse_wrapper">
								${product.warehouse.warehouseName}
								<div class="order_page_product_warehouse_pop">
									<div class="order_page_product_warehouse_pop_item">
										所在城市：${product.warehouse.city.province.provinceName}-${product.warehouse.city.cityName}
									</div>
									<div class="order_page_product_warehouse_pop_item">
										详细地址：${product.warehouse.address}
									</div>
								</div>
							</div>
						</font>
					</div>
					<div class="order_page_product_item_op_col">
						<a href="MerchantProductEdit.action?productId=${product.productId}&queryType=${queryType}"><font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer">修改</div></font></a>
						<s:if test='queryType != "3"'>
							<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="deleteProduct(${queryType}, ${product.productId}, ${brandIdSelected}, ${currentPage})">下架</div></font>
						</s:if>
						<s:else>
							<font class="order_page_font_red"><div class="order_page_product_price_wrapper hover_pointer" onclick="restoreProduct(${queryType}, ${product.productId}, ${brandIdSelected}, ${currentPage})">上架</div></font>
						</s:else>
					</div>
				</div>
				</s:iterator>
				<div id="content_paginator">
					<ul class="content_paginator_ul">
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="${paginator.actionName}_supplierProducts.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="${paginator.actionName}_merchantProducts.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="${paginator.actionName}_disabledProducts.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.prePage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">上一页</div>
								</a>
							</s:else>
						</li>
						<s:if test="paginator.currentPage == 1">
						<li class="content_paginator_li_selected">
							<s:if test='queryType == "0"'>
								<a href="${paginator.actionName}_allProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="${paginator.actionName}_supplierProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="${paginator.actionName}_merchantProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="${paginator.actionName}_disabledProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_selected">1</div>
								</a>
							</s:else>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="${paginator.actionName}_allProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="${paginator.actionName}_supplierProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="${paginator.actionName}_merchantProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="${paginator.actionName}_disabledProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number">1</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allProducts.action?currentPage=1&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
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
										<a href="${paginator.actionName}_allProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "1"'>
										<a href="${paginator.actionName}_supplierProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										<a href="${paginator.actionName}_merchantProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										<a href="${paginator.actionName}_disabledProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="${paginator.actionName}_allProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number_selected">${current-1}</div>
										</a>
									</s:else>
								</s:if>
								<s:else>
								<li class="content_paginator_li content_paginator_number_onhover">
									<s:if test='queryType == "0"'>
										<a href="${paginator.actionName}_allProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:if>
									<s:elseif test='queryType == "1"'>
										<a href="${paginator.actionName}_supplierProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "2"'>
										<a href="${paginator.actionName}_merchantProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:elseif test='queryType == "3"'>
										<a href="${paginator.actionName}_disabledProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
											<div class="content_paginator_number">${current-1}</div>
										</a>
									</s:elseif>
									<s:else>
										<a href="${paginator.actionName}_allProducts.action?currentPage=${current-1}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
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
									<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "1"'>
									<a href="${paginator.actionName}_supplierProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "2"'>
									<a href="${paginator.actionName}_merchantProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "3"'>
									<a href="${paginator.actionName}_disabledProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number_selected">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
								<s:if test='queryType == "0"'>
									<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:if>
								<s:elseif test='queryType == "1"'>
									<a href="${paginator.actionName}_supplierProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "2"'>
									<a href="${paginator.actionName}_merchantProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:elseif test='queryType == "3"'>
									<a href="${paginator.actionName}_disabledProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:elseif>
								<s:else>
									<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.totalPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
										<div class="content_paginator_number">${paginator.totalPage}</div>
									</a>
								</s:else>
							</li>
							</s:else>
						</s:if>
						<li class="content_paginator_li content_paginator_number_onhover">
							<s:if test='queryType == "0"'>
								<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<a href="${paginator.actionName}_supplierProducts.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<a href="${paginator.actionName}_merchantProducts.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<a href="${paginator.actionName}_disabledProducts.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:elseif>
							<s:else>
								<a href="${paginator.actionName}_allProducts.action?currentPage=${paginator.nextPage}&queryType=${queryType}&keyword=${keyword}&brandIdSelected=${brandIdSelected}&lastMonth=${lastMonth}">
									<div class="content_paginator_number_3">下一页</div>
								</a>
							</s:else>
						</li>
						<li class="content_paginator_li left_margin_13px">
							<div class="content_paginator_text">转到</div>
						</li>
						<li class="content_paginator_li">
							<s:if test='queryType == "0"'>
								<form name="page_form" action="${paginator.actionName}_allProducts.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:if>
							<s:elseif test='queryType == "1"'>
								<form name="page_form" action="${paginator.actionName}_supplierProducts.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "2"'>
								<form name="page_form" action="${paginator.actionName}_merchantProducts.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:elseif test='queryType == "3"'>
								<form name="page_form" action="${paginator.actionName}_disabledProducts.action">
									<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
									<input type="hidden" name="queryType" value="${queryType}" />
									<input type="hidden" name="lastMonth" value="${lastMonth}" />
								</form>
							</s:elseif>
							<s:else>
								<form name="page_form" action="${paginator.actionName}_allProducts.action">
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

