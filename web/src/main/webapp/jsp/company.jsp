<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/company_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UhE6QIaWFvVuOYbF5x91onC9"></script>
<script type="text/javascript" src="./js/main_search.js" charset="UTF-8"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

<div id="total_company_page">
<div id="dark_blue_header">
	<div class="dark_blue_header_wrapper">
		<div id="dark_blue_logo">
			<a href="TitaniumMainPage.action"><img id="dark_blue_logo_img" src="./img/logo_dark_blue.png" /></a>
		</div>
		<div id="dark_blue_title">
			<s:if test='company.companyType == 1'>
				<span id="dark_blue_title_span">合作品牌</span>
			</s:if>
			<s:else>
				<span id="dark_blue_title_span">合作经销商</span>
			</s:else>
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
<div id="total_wrapper">
<div id="content">
	<div id="content_wrapper">
		<div id="content_top">
			<div class="content_top_left">
				<div class="company_icon_wrapper">
					<img class="company_icon_img" src="${company.companyIcon}" />
				</div>
			</div>
			<div class="content_top_right">
				<div class="company_title_text">
					${company.companyFullName}
				</div>
				<div class="company_title_desc">
					${company.companySlogan}
				</div>
			</div>
		</div>
		<div id="content_left">
			<div class="content_left_company_desc">
				${company.longDescription}
				<br>
			</div>
			<s:if test='products.size != 0'>
			<div class="content_left_company_product_title">
				<s:if test='company.companyType == 1'>
					<h3>公司热销产品</h3>
				</s:if>
				<s:else>
					<h3>经销商热销产品</h3>
				</s:else>
			</div>
			<div class="company_product_list">
				<div class="company_product_list_header">
					<div class="company_product_list_header_col1">
						产品
					</div>
					<div class="company_product_list_header_col2">
						单价
					</div>
					<div class="company_product_list_header_col3">
						库存
					</div>
					<div class="company_product_list_header_col3">
						所在地
					</div>
				</div>
				<s:iterator value="products" id="product">
				<div class="company_product_list_item">
					<div class="content_order_item_product_col1">
						<a href="ProductDetail.action?productId=${product.productId}" target="_blank" ><img class="content_order_item_product_img" src="${product.pictureUrl}" /></a>
					</div>
					<div class="content_order_item_product_col2">
						<div class="content_order_item_product_link_wrapper">
							<a class="content_order_item_product_link" href="ProductDetail.action?productId=${product.productId}" target="_blank"><font class="font_light_gray">${product.productName} ${product.description}</font></a>
						</div>
						<div class="content_order_item_product_link_wrapper">
							<a class="content_order_item_product_link"><font class="font_light_gray">${product.brand.brandName}</font></a>
						</div>
					</div>
					<div class="content_order_item_product_col3">
						<div class="content_order_item_product_quantity_wrapper">
							<font class="font_red_dec">￥${product.price}</font>
						</div>
					</div>
					<div class="content_order_item_product_col3">
						<div class="content_order_item_product_quantity_wrapper">
							<font class="font_light_gray">${product.stockLevel}吨</font>
						</div>
					</div>
					<div class="content_order_item_product_col3">
						<div class="content_order_item_product_quantity_wrapper">
							<font class="font_light_gray">${product.warehouse.city.province.provinceName}-${product.warehouse.city.cityName}</font>
						</div>
					</div>
					<div class="content_order_item_product_col3">
						<a href="ProductDetail.action?productId=${product.productId}" target="_blank" >
							<div class="product_list_item_buynow">
								查看产品
							</div>
						</a>
					</div>
				</div>
				</s:iterator>
			</div>
			<div id="content_paginator">
				<ul class="content_paginator_ul">
					<li class="content_paginator_li content_paginator_number_onhover">
						<a href="Company.action?companyId=${company.companyId}&currentPage=${paginator.prePage}">
							<div class="content_paginator_number_3">上一页</div>
						</a>
					</li>
					<s:if test="paginator.currentPage == 1">
					<li class="content_paginator_li_selected">
						<a href="Company.action?companyId=${company.companyId}&currentPage=1">
							<div class="content_paginator_number_selected">1</div>
						</a>
					</li>
					</s:if>
					<s:else>
					<li class="content_paginator_li content_paginator_number_onhover">
						<a href="Company.action?companyId=${company.companyId}&currentPage=1">
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
								<a href="Company.action?companyId=${company.companyId}&currentPage=${current-1}">
									<div class="content_paginator_number_selected">${current-1}</div>
								</a>
							</s:if>
							<s:else>
							<li class="content_paginator_li content_paginator_number_onhover">
								<a href="Company.action?companyId=${company.companyId}&currentPage=${current-1}">
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
							<a href="Company.action?companyId=${company.companyId}&currentPage=${paginator.totalPage}">
								<div class="content_paginator_number_selected">${paginator.totalPage}</div>
							</a>
						</li>
						</s:if>
						<s:else>
						<li class="content_paginator_li content_paginator_number_onhover content_paginator_number_onhover">
							<a href="Company.action?companyId=${company.companyId}&currentPage=${paginator.totalPage}">
								<div class="content_paginator_number">${paginator.totalPage}</div>
							</a>
						</li>
						</s:else>
					</s:if>
					<li class="content_paginator_li content_paginator_number_onhover">
						<a href="Company.action?companyId=${company.companyId}&currentPage=${paginator.nextPage}">
							<div class="content_paginator_number_3">下一页</div>
						</a>
					</li>
					<li class="content_paginator_li left_margin_13px">
						<div class="content_paginator_text">转到</div>
					</li>
					<li class="content_paginator_li">
						<form name="page_form" action="Company.action">
							<div class="content_paginator_elim"><s:textfield name="currentPage" id="page_search" /></div>
							<input type="hidden" name="companyId" value="${companyId}" />
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
			</s:if>
		</div>
		<div id="content_right">
			<div class="company_short_desc">
				<div class="company_short_desc_title">
					详细地址
				</div>
				<div id="company_map_container" class="company_map_container">
				</div>
				<script type="text/javascript"> 
					var map = new BMap.Map("company_map_container");          	// 创建地图实例  
					var point = new BMap.Point(113.137239,35.246126);  				// 创建点坐标  
					map.centerAndZoom(point, 15);                 				// 初始化地图，设置中心点坐标和地图级别  
				</script>
				<div class="company_short_desc_content">
					详细地址：${company.companyCity.province.provinceName}-${company.companyCity.cityName} &nbsp;${company.companyAddress}
				</div>
				<div class="company_product_search_div">
					<s:if test='company.companyType == 1'>
						<a href="MainSearch.action?filter=company:${company.companyName}" >
							<div class="company_product_search_button">
								搜索公司产品
							</div>
						</a>
					</s:if>
					<s:else>
						<a href="MainSearch.action?filter=merchantcompany:${company.companyName}" >
							<div class="company_product_search_button">
								搜索供应商产品
							</div>
						</a>
					</s:else>
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

