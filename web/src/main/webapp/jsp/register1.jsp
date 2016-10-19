<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/register1.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
</head>

<s:if test="smsCheckFirst == true">
	<body class="background_gray" onload="bodyOnLoad()">
</s:if>
<s:else>
	<body class="background_gray">
</s:else>
<%@ include file="./common/header.jsp" %>

<div id="total_register">	
<div id="total_wrapper">
	<div id="register_pad">
		<div id="register_pad_header">
			<div id="register_pad_logo">
				<a href="TitaniumMainPage.action"><img id="register_logo" src="./img/logo_gray.png" /></a>
			</div>
			<div id="register_pad_title">
				<span id="register_title_span">用户注册</span>
			</div>
		</div>
		<div id="register_pad_content">
			<!--div id="register_pad_type">
				<ul class="register_pad_type_ul">
					<li class="register_pad_type_li">
						<s:if test="userType == 'common' || userType == null">
							<div id="common_user_tab" class="register_pad_type_item_selected" onclick="tab_selected('1')">普通用户注册</div>
						</s:if>
						<s:else>
							<div id="common_user_tab" class="register_pad_type_item" onclick="tab_selected('1')">普通用户注册</div>
						</s:else>
					</li>
					<li class="register_pad_type_li">
						<s:if test="userType == 'supplier'">
							<div id="supplier_user_tab" class="register_pad_type_item_selected" onclick="tab_selected('2')">供应商用户注册</div>
						</s:if>
						<s:else>
							<div id="supplier_user_tab" class="register_pad_type_item" onclick="tab_selected('2')">供应商用户注册</div>
						</s:else>
					</li>
					<li class="register_pad_type_li">
						<s:if test="userType == 'merchant'">
							<div id="merchant_user_tab" class="register_pad_type_item_selected" onclick="tab_selected('3')">贸易商用户注册</div>
						</s:if>
						<s:else>
							<div id="merchant_user_tab" class="register_pad_type_item" onclick="tab_selected('3')">贸易商用户注册</div>
						</s:else>
						
					</li>
				</ul>
			</div-->
			<s:if test="registerStatus == false">
				<div class="register_pad_table">
			</s:if>
			<s:else>
				<div class="register_pad_table_hidden">
			</s:else>
				<div class="register_pad_table_title">
					<div class="register_pad_table_title_text"><font class="font_red">用户注册成功，建议立即上传三证。三证审核通过后，便可以使用系统所有功能。</font></div>
					<div class="register_pad_table_title_text">第二步：上传三证扫描件<font class="font_gray register_pad_table_title_subtext"> —— 三证包括营业执照、税务登记证和组织机构代码</font></div>
				</div>
				<div id="register_pad_form_common" class="register_pad_form1">
					<div class="register_pad_upload_license_item">
						<form id="register_pad_upload_form1" enctype="multipart/form-data">
							<input type="file" name="acceptanceBillFile" class="register_pad_upload_input" onchange="checkFile(this)" />
							<div class="register_pad_upload_button" onclick="uploadCompanyLicense('register_pad_upload_form1', 1)">上传</div>
							<div class="register_pad_img"><img id="register_pad_img1" class="register_pad_img_small"/></div>
						</form>
					</div>
					<div class="register_pad_upload_license_item">
						<form id="register_pad_upload_form2" enctype="multipart/form-data">
							<input type="file" name="acceptanceBillFile" class="register_pad_upload_input" onchange="checkFile(this)" />
							<div class="register_pad_upload_button" onclick="uploadCompanyLicense('register_pad_upload_form2', 2)">上传</div>
							<div class="register_pad_img"><img id="register_pad_img2" class="register_pad_img_small"/></div>
						</form>
					</div>
					<div class="register_pad_upload_license_item">
						<form id="register_pad_upload_form3" enctype="multipart/form-data">
							<input type="file" name="acceptanceBillFile" class="register_pad_upload_input" onchange="checkFile(this)" />
							<div class="register_pad_upload_button" onclick="uploadCompanyLicense('register_pad_upload_form3', 3)">上传</div>
							<div class="register_pad_img"><img id="register_pad_img3" class="register_pad_img_small"/></div>
						</form>
					</div>
					<div class="register_pad_upload_submit">
						<form id="register_pad_upload_submit_form">
							<div class="register_pad_form_item_middle_submit" onclick="submit_upload()">提交审核</div>
						</form>						
					</div>
				</div>
				<div class="register_pad_right">
					<div class="register_pad_right_title">
						<div class="register_pad_right_title_text">注册指导</div>
					</div>
					<div class="register_pad_right_content">
						<ul class="register_pad_right_content_ul">
							<li class="register_pad_right_content_li"><a href="BulletinDetail.action?type=serviceagreement" target="_blank">用户协议</a></li>
							<li class="register_pad_right_content_li"><a href="BulletinDetail.action?type=aboutus" target="_blank">关于我们-智选化学介绍</a></li>
							<li class="register_pad_right_content_li"><a href="BulletinDetail.action?type=registerproblem" target="_blank">注册遇到问题？</a></li>
						</ul>
					</div>
				</div>
			</div>
			<s:if test="registerStatus == false">
				<div class="register_pad_table_success_hidden">
			</s:if>
			<s:else>
				<div class="register_pad_table_success">
			</s:else>
				<div class="register_result">
					<img class="register_result_icon" src="./img/register/success.png" />
					<font class="register_result_font">注册成功 </font>
					<a href="TitaniumMainPage.action"> 返回智选化学主页</a>
					<a href="MyZhixuan.action">&nbsp; 转到我的主页</a>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

