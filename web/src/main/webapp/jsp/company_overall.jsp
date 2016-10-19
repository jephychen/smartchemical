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
			<span id="dark_blue_title_span">合作品牌</span>
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
		<div id="companies_top">
			<div class="content_top_title">
				品牌专题
			</div>
			<div class="content_top_navigator">
				<ul class="content_top_navigator_ul">
					<li class="content_top_navigator_item">
						所有品牌
					</li>
					<li class="content_top_navigator_item content_top_navigator_item_divider">
						|
					</li>
					<li class="content_top_navigator_item">
						精细化工
					</li>
				</ul>
			</div>
		</div>
		<div class="companies_content">
			<s:iterator value="companies" id="company">
				<a href="Company.action?companyId=${company.companyId}">
					<div class="company_item">
						<div class="company_name">
							${company.companyName}
						</div>
						<div class="company_icon">
							<img class="company_icon_img" src="${company.companyIcon}" />
						</div>
						<div class="company_region">
							所在地区：${company.companyCity.province.provinceName}-${company.companyCity.cityName}
						</div>
						<div class="company_region">
							主要产品：精细化工
						</div>
					</div>
				</a>
			</s:iterator>
		</div>
	</div>
</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

