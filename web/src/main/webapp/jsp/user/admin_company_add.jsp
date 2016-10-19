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
<script type="text/javascript" src="./js/admin_company_edit.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
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
		<form id="company_save_form">
		</form>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					添加公司
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<div class="product_edit_title">
					一、基本信息
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司名称：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_companyname" class="product_edit_item_input" onblur="checkCompanyName()"></input>
						<div id="pop_content_productname" class="pop_content_input_tip1_hidden">请输入4~20位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司全称：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_companyfullname" class="product_edit_item_input" onblur="checkCompanyFullName()"></input>
						<div id="pop_content_productno" class="pop_content_input_tip1_hidden">请输入4~40位汉字</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司类型：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="company_edit_companytype" class="product_edit_item_select">
							<option value="0">选择类型</option>
							<option value="1">供应商</option>
							<option value="2">经销商</option>
						</select>
						<div id="pop_content_companytype" class="pop_content_input_tip1_hidden">请选择公司类型</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司主页：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_companyurl" class="product_edit_item_input" onblur="checkCompanyUrl()"></input>
						<div id="pop_content_companyurl" class="pop_content_input_tip2_hidden">请输入正确的网址，例如:http://www.zhixuanhuaxue.com/</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司短描述：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_desc" class="product_edit_item_input_long" onblur="checkCompanyDesc()"></input>
						<div id="pop_content_companydesc" class="pop_content_input_tip1_hidden">请输入4~50位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_title1">
					二、高级信息
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">所在城市：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="pop_content_region_dropdown" class="product_edit_item_select" onchange="regionSelect()">
							<option value="0">选择地区</option>
							<s:iterator value="regions" id="region">
								<option value="${region.regionId}">${region.regionName}</option>
							</s:iterator>
						</select>
						<select id="pop_content_province_dropdown" class="product_edit_item_select" onchange="provinceSelect()">
							<option value="0">选择省份</option>
						</select>
						<select id="pop_content_city_dropdown"  class="product_edit_item_select" onchange="checkCity()">
							<option value="0">选择城市</option>
						</select>
						<div id="pop_content_city" class="pop_content_input_tip1_hidden">请选择城市</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">详细地址：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_address" class="product_edit_item_input_long" onblur="checkCompanyAddress()"></input>
						<div id="pop_content_address" class="pop_content_input_tip1_hidden">请输入4~50位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">经度：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_longtitude" class="product_edit_item_input_short" onblur="checkLongitude()"></input>
						<div class="company_edit_text">纬度：</div>
						<input id="company_edit_latitude" class="product_edit_item_input_short" onblur="checkLatitude()"></input>
						<div id="pop_content_lnglat" class="pop_content_input_tip1_hidden">请填写正确的经纬度</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司口号：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="company_edit_slogan" class="product_edit_item_input_long" onblur="checkSlogan()"></input>
						<div id="pop_content_slogan" class="pop_content_input_tip1_hidden">请输入4~50位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司图片上传：</div>
					</div>
					<div class="product_edit_item_right">
						<form id="company_edit_upload_form" enctype="multipart/form-data">
							<input type="file" id="product_edit_pic_upload" name="companyPicFile" class="product_edit_item_fileupload" onchange="checkFile(this)" />
							<div class="product_edit_upload_button hover_pointer" onclick="uploadCompanyPic()">上传</div>
						</form>
						<div id="pop_content_companypic" class="pop_content_input_tip1_hidden">请上传产品图片</div>
					</div>
				</div>
				<div class="acceptance_bill_img"><img id="company_edit_pic_icon1" class="company_icon_upload_small" /></div>
				<div class="product_edit_item_big">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司长描述：</div>
					</div>
					<div class="product_edit_item_right">
						<textarea id="company_edit_companylongdesc" class="product_edit_item_textarea">
						</textarea>
					</div>
				</div>
				<div class="product_edit_title1">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text"></div>
					</div>
					<div class="product_edit_item_right">
						<div class="product_edit_save_button hover_pointer" onclick="saveCompany(${queryType},'0')">
							保存
						</div>
					</div>
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

