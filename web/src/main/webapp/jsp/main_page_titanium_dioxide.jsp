<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/main_page_titanium_dioxide.css" rel="stylesheet" type="text/css" />
<link href="./css/slider.css" rel="stylesheet" type="text/css">
<link href="./css/slider_low.css" rel="stylesheet" type="text/css">
</head>

<body>
<script src="js/slider_yu.js" type="text/javascript"></script>
<script src="js/slider_tb.js" type="text/javascript"></script>
<script src="js/slider_low.js" type="text/javascript"></script>
<script src="js/main_page_titanium_dioxide.js" type="text/javascript"></script>
<%@ include file="./common/header.jsp" %>

<div id="top_navigator">
	<div id="top_navigator_wrapper_mainpage">
		<div id="top_navigator_logo">
			<img id="top_navigator_logo" src="./img/logo_blue.png" />
		</div>
		<div id="top_navigator_right">
			<ul>	
				<li class="top_navigator_search_li">
					<div class="top_search_div">
						<form name="search_form" action="MainSearch.action">
							<s:textfield name="keyword" id="top_search" />
							<div id="top_search_button"  onclick="click_submit();"><img src="./img/search_button.png" /></div>
							<script>
								function click_submit(){
								  search_form.submit();
								}
							</script>
						</form>
					</div>
				</li>
				<li class="top_navigator_li">
					<a href="Bulletin.action" target="_blank" >
						<div class="top_navigator_item">资讯</div>
					</a>
				</li>
				<li class="top_navigator_li">
					<a href="AllCompanies.action" target="_blank" >
						<div class="top_navigator_item">品牌</div>
					</a>
				</li>
				<li class="top_navigator_li">
					<a href="MainSearch.action" target="_blank" >
						<div class="top_navigator_item">商品</div>
					</a>
				</li>
				<li class="top_navigator_li_selected">
					<a href="TitaniumMainPage.action">
						<div class="top_navigator_item_selected">首页</div>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<div id="top_advert">
	<div id="MainPromotionBanner">
	<div id="SlidePlayer">
		<ul class="Slides">
			<li><a href="Bulletin.action" target="_blank"><img src="./img/advertisement_pics/banner2.png"></a></li>
			<li><a href="Bulletin.action" target="_blank"><img src="./img/advertisement_pics/banner.jpg"></a></li>
		</ul>
	</div>
	<script type="text/javascript">
		TB.widget.SimpleSlide.decoration('SlidePlayer', {eventType:'mouse', effect:'scroll'});
	</script>
	</div>
</div>


<div id="total">
<div id="total_wrapper_mainpage">
<div id="content">
	<div id="content_ad_low0" class="content_ad_low">
		<div class="content_ad_low_title">
			<div id="content_ad_low_title_merchant" class="content_ad_low_title_text" onclick="selectMerchantList()">合作经销商</div>
			<div id="content_ad_low_title_company" class="content_ad_low_title_text_unselected" onclick="selectCompanyList()">合作品牌</div>
		</div>
		<div id="merchant_list_pad" class="slider_low_wrapper">
			<div class="company_list">
				<s:iterator value="merchantCompanies" id="merchant">
					<a href="Company.action?companyId=${merchant.companyId}" target="_blank"><img src="${merchant.companyIcon}" class="content_list_company_logo_mainpage"/></a>
				</s:iterator>
			</div>
			<s:if test='merchantCompanies.size == 8'>
			<div class="company_list_more">
				<a href="AllCompanies.action" target="_blank">
					<div class="company_list_more_button">
						查看更多
					</div>
				</a>
			</div>
			</s:if>
		</div>
		<div id="company_list_pad" class="slider_low_wrapper_hidden">
			<div class="company_list">
				<s:iterator value="companies" id="vendor">
					<a href="Company.action?companyId=${vendor.companyId}" target="_blank"><img src="${vendor.companyIcon}" class="content_list_company_logo_mainpage"/></a>
				</s:iterator>
			</div>
			<s:if test='companies.size == 12'>
			<div class="company_list_more">
				<a href="AllCompanies.action" target="_blank">
					<div class="company_list_more_button">
						查看更多
					</div>
				</a>
			</div>
			</s:if>
		</div>
	</div>
	<div id="content_ad_low2" class="content_ad_low">
		<div class="content_ad_low_title">
			<a href="MainSearch.action" target="_blank" >
				<div class="content_ad_low_title_text">热销商品</div>
			</a>
		</div>
		<div class="content_ad_low_wrapper_mainpage">
			<ul class="content_ad_low_ul">
				<s:iterator value="hotProducts" id="product">
					<li class="content_ad_low_li_mainpage">
						<div class="content_list_item">
							<a href="ProductDetail.action?productId=${product.productId}" target="_blank">
								<img class="content_list_item" src="${product.pictureUrl}" />
								<div class="content_list_item1">${product.productName}</div>
								<div class="content_list_item2">${product.brand.brandName}</div>
								<div class="content_list_item3">
									<span class="content_ad_low_span_price">￥${product.price}</span>
									<span class="content_ad_low_span_amount">已售${product.totalSoldQuantity}${product.quantityUnit.unitName}</span>
								</div>
							</a>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div id="content_ad_low3" class="content_ad_low">
		<div class="content_ad_low_title">
			<a href="MainSearch.action" target="_blank" >
				<div class="content_ad_low_title_text">推荐商品</div>
			</a>
		</div>
		<div class="content_ad_low_wrapper_mainpage">
			<ul class="content_ad_low_ul">
				<s:iterator value="recommendProducts" id="product">
					<li class="content_ad_low_li_mainpage">
						<div class="content_list_item">
							<a href="ProductDetail.action?productId=${product.productId}" target="_blank">
								<img class="content_list_item" src="${product.pictureUrl}" />
								<p class="content_list_item1">${product.productName}</p>
								<p class="content_list_item2">${product.brand.brandName}</p>
								<p class="content_list_item3">
									<span class="content_ad_low_span_price">￥${product.price}</span>
									<span class="content_ad_low_span_amount">已售${product.totalSoldQuantity}${product.quantityUnit.unitName}</span>
								</p>
							</a>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

