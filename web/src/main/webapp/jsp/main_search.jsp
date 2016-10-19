<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/main_search.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/main_search.js" charset="UTF-8"></script>
</head>

<body>
<%@ include file="./common/header.jsp" %>

<div id="total">
<div id="dark_blue_header">
	<div class="dark_blue_header_wrapper">
		<div id="dark_blue_logo">
			<a href="TitaniumMainPage.action"><img id="dark_blue_logo_img" src="./img/logo_dark_blue.png" /></a>
		</div>
		<div id="dark_blue_title">
			<span id="dark_blue_title_span">产品中心</span>
		</div>
		<div id="top_navigator_right_user_page">
			<div class="top_search_div_user_page_high">
				<form id="search_form_global" name="search_form" action="MainSearch.action">
					<input id="top_search_user_page_id" class="top_search_user_page" name="keyword" />
					<div id="top_search_button"  onclick="submitSearch()"><img src="./img/search_button_white.png" /></div>
				</form>
			</div>	
		</div>
	</div>
</div>
<div id="total_wrapper_mainsearch">
<div id="content_mainsearch">
	<div class="content_path">
		<ul class="content_path_ul">
			<li class="content_path_li"><a href="TitaniumMainPage.action">首页</a></li>
			<li class="content_path_li"><em>></em></li>
			<li class="content_path_li"><a href="MainSearch.action">所有产品</a></li>
		</ul>
		<ul class="content_filters_ul">
			<s:iterator value="filters" id="f">
				<li class="content_filters_li gray_wrapper">
					<ul>
						<li class="content_filter_condition">${f.type}:${f.value}</li>
						<li class="content_filter_condition_cancel"><a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&removeFilter=${f.originalFilter}">X</a></li>			
					</ul>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div id="content_filter">
		<div class="content_filter_statistic">
			<span class="content_filter_statistic_title">共找到 <font class="font_red">${keyword}</font> 相关产品 <font class="font_red">${totalRecordCount}</font> 个</span>
		</div>
		<div class="content_filter_item">
			<div class="content_filter_item_title">
				<span class="content_filter_item_title">产品类型:</span>
			</div>
			<div class="content_filter_item_content">
				<ul class="content_filter_item_content_ul">
					<li class="content_filter_item_content_li_region">
						<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},productCategory:all" class="content_filter_item_content_span_region content_filter_selected">不限</a>
					</li>
					<s:iterator value="categories" id="category">
						<li class="content_filter_item_content_li_region">
							<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},productCategory:${category.productCategoryName}" class="content_filter_item_content_span_region">${category.productCategoryName}</a>
						</li>
					</s:iterator>
				</ul>
			</div>	
		</div>
		<div class="content_filter_item">
			<div class="content_filter_item_title">
				<span class="content_filter_item_title">品牌:</span>
			</div>
			<div class="content_filter_item_content">
				<ul class="content_filter_item_content_ul">
					<li class="content_filter_item_content_li_region">
						<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},brandName:all" class="content_filter_item_content_span_region content_filter_selected">不限</a>
					</li>
					<s:iterator value="brands" id="brand">
						<li class="content_filter_item_content_li_region">
							<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},brandName:${brand.brandName}" class="content_filter_item_content_span_region">${brand.brandName}</a>
						</li>
					</s:iterator>
				</ul>
			</div>	
		</div>
		<div class="content_filter_item">
			<div class="content_filter_item_title">
				<span class="content_filter_item_title">产品型号:</span>
			</div>
			<div class="content_filter_item_content">
				<ul class="content_filter_item_content_ul">
					<li class="content_filter_item_content_li_region">
						<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},productNo:all" class="content_filter_item_content_span_region content_filter_selected">不限</a>
					</li>
					<s:iterator value="productNos" id="productNo">
						<li class="content_filter_item_content_li_region">
							<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},productNo:${productNo}" class="content_filter_item_content_span_region">${productNo}</a>
						</li>
					</s:iterator>
				</ul>
			</div>	
		</div>
		<div class="content_filter_item">
			<div class="content_filter_item_title">
				<span class="content_filter_item_title">地区:</span>
			</div>
			<div class="content_filter_item_content">
				<ul class="content_filter_item_content_ul">
					<li class="content_filter_item_content_li_region">
						<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},region:all" class="content_filter_item_content_span_region content_filter_selected">不限</a>
					</li>
					<s:iterator value="regions" id="region">
						<li class="content_filter_item_content_li_region">
							<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},region:${region.regionName}" class="content_filter_item_content_span_region">${region.regionName}</a>
							<div class="content_filter_item_content_region">
							<ul>
								<s:iterator value="#region.provinces" id="province">
									<li class="content_filter_item_content_region_province">
										<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},region:${region.regionName}-${province.provinceName}" class="content_filter_item_content_span_region">${province.provinceName}</a>
										<!--<div class="content_filter_item_content_city">
											<ul>
												<s:iterator value="#province.cities" id="city">
													<li class="content_filter_item_content_region_city">
														<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter},region:${region.regionName}-${province.provinceName}-${city.cityName}" class="content_filter_item_content_span_region">${city.cityName}</a>
													</li>
												</s:iterator>
											</ul>
										</div>-->
									</li>
								</s:iterator>
							</ul>
							</div>
						</li>
					</s:iterator>
				</ul>
			</div>	
		</div>
	</div>
	<div id="content_sort">
		<ul class="content_sort_ul">
			<a href="MainSearch.action?keyword=${keyword}&sortby=soldquantity&filter=${filter}">
				<li class="content_sort_li">
					<div class="content_sort_item"><font class="font_gray">销量</font><img class="content_sort_img" src="./img/sort_down.png" /></div>
				</li>
			</a>
			<a href="MainSearch.action?keyword=${keyword}&sortby=lastmodified&filter=${filter}">
				<li class="content_sort_li">
					<div class="content_sort_item"><font class="font_gray">新品</font><img class="content_sort_img" src="./img/sort_down.png" /></div>
				</li>
			</a>
		</ul>
	</div>
	<div id="content_list_wrapper">
		<div id="content_list">
			<ul class="content_list_item_ul">
				<s:iterator value="products" id="product">
					<li class="content_ad_low_li">
						<div class="content_list_item">
							<a href="ProductDetail.action?productId=${product.productId}" target="_blank">
								<img class="content_list_item" src="${product.pictureUrl}" />
								<div class="content_list_item1">${product.productName}
									<font class="font_red float_right">
										<s:if test='#product.stockLevel < #product.minSoldQunatity'>
											无货
										</s:if>
									</font>
								</div>
								<div class="content_list_item2">${product.brand.brandName}<font class="font_blue float_right">${product.merchantCompany.companyName}</font></div>
								<div class="content_list_item3">
									<span class="content_list_item_price">￥${product.price}</span>
									<span class="content_list_item_amount">已售${product.totalSoldQuantity}${product.quantityUnit.unitName}</span>
								</div>
							</a>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div id="content_paginator">
		<ul class="content_paginator_ul">
			<li class="content_paginator_li content_paginator_number_onhover">
				<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=${paginator.prePage}">
					<div class="content_paginator_number_3">上一页</div>
				</a>
			</li>
			<s:if test="paginator.currentPage == 1">
			<li class="content_paginator_li_selected">
				<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=1">
					<div class="content_paginator_number_selected">1</div>
				</a>
			</li>
			</s:if>
			<s:else>
			<li class="content_paginator_li content_paginator_number_onhover">
				<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=1">
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
						<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=${current-1}">
							<div class="content_paginator_number_selected">${current-1}</div>
						</a>
					</s:if>
					<s:else>
					<li class="content_paginator_li content_paginator_number_onhover">
						<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=${current-1}">
							<div class="content_paginator_number">${current-1}</div>
						</a>
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
					<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=${paginator.totalPage}">
						<div class="content_paginator_number_selected">${paginator.totalPage}</div>
					</a>
				</li>
				</s:if>
				<s:else>
				<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
					<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=${paginator.totalPage}">
						<div class="content_paginator_number">${paginator.totalPage}</div>
					</a>
				</li>
				</s:else>
			</s:if>
			<li class="content_paginator_li content_paginator_number_onhover">
				<a href="MainSearch.action?keyword=${keyword}&sortby=${sortby}&filter=${filter}&currentPage=${paginator.nextPage}">
					<div class="content_paginator_number_3">下一页</div>
				</a>
			</li>
			<li class="content_paginator_li left_margin_13px">
				<div class="content_paginator_text">转到</div>
			</li>
			<li class="content_paginator_li">
				<form name="page_form" action="MainSearch.action">
					<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
					<input type="hidden" name="keyword" value="${keyword}" />
					<input type="hidden" name="filter" value="${filter}" />
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
<%@ include file="./common/footer.jsp" %>
</body>
</html>