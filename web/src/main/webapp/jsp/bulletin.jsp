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

<div id="total_bulletin_page">
<div id="dark_blue_header">
	<div class="dark_blue_header_wrapper">
		<div id="dark_blue_logo">
			<a href="TitaniumMainPage.action"><img id="dark_blue_logo_img" src="./img/logo_dark_blue.png" /></a>
		</div>
		<div id="dark_blue_title">
			<span id="dark_blue_title_span">资讯</span>
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
<div id="content_bulletin">
	<div id="content_wrapper">
		<div id="content_left">
			<div class="content_left_company_desc">
				<div class="bulletin_list_title">
					<h3>智选化学最新资讯</h3>
				</div>
				<div class="bulletin_list_item">
					<a href="BulletinDetail.action?type=serviceagreement" target="_blank"><p>智选化学服务协议</p></a>
					<p>"智选化学"提醒您：在使用智选化学平台各项服务前，请您务必仔细阅读并透彻理解本声明。您可以选择不使用智选化学平台服务，但如果您使用智选化学，您的使用行为将被视为对本声明全部内容的认可。</p>
				</div>
				<div class="bulletin_list_item">
					<a href="BulletinDetail.action?type=aboutus" target="_blank"><p>关于我们</p></a>
					<p>智选化学是基于智能运算与专业顾问服务的化工产品在线交易平台，为成都智圆行方电子商务有限公司旗下产品。公司成立于2015年12月，由化工行业以及互联网行业职业经理人联合创办。</p>
				</div>
			</div>
		</div>
		<div id="content_right">
			<div class="company_short_desc">
				<div class="bulletin_short_desc_title">
					推荐新闻
				</div>
				<a href="BulletinDetail.action?type=serviceagreement" target="_blank">
				<div class="bulletin_recommend_item">
					<div class="bulletin_recommend_item_left"><img src="./img/bulletin_item.jpg" class="bulletin_recommend_item_icon"></img></div>
					<div class="bulletin_recommend_item_right">智选化学服务协议</div>
				</div>
				</a>
				<a href="BulletinDetail.action?type=aboutus" target="_blank">
				<div class="bulletin_recommend_item">
					<div class="bulletin_recommend_item_left"><img src="./img/bulletin_item1.jpg" class="bulletin_recommend_item_icon"></img></div>
					<div class="bulletin_recommend_item_right">关于我们</div>
				</div>
				</a>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

