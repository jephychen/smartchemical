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
<script type="text/javascript" src="./js/admin_user_edit.js" charset="UTF-8"></script>
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
		<form id="user_save_form">
		</form>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					修改用户
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<div class="product_edit_title">
					一、基本信息
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">用户名：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_username" disabled="disabled" class="product_edit_item_input" value="${currentUser.userName}"></input>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">真实姓名：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_realname" class="product_edit_item_input" value="${currentUser.realName}" onblur="checkRealName(this.value,'pop_content_realname')"></input>
						<div class="product_edit_text"><font class="font_gray">（可选）</font></div>
						<div id="pop_content_realname" class="pop_content_input_tip1_hidden">请输入2~25位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">密码：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_password" type="password" class="product_edit_item_input" value="${currentUser.password}" onblur="checkPassword(this.value,'pop_content_password')"></input>
						<div id="pop_content_password" class="pop_content_input_tip1_hidden">密码应为6-20位</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">公司全名：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_externalcompany" class="product_edit_item_input" value="${currentUser.externalCompanyName}" onblur="checkExternalCompany(this.value, 'pop_content_externalcompany')"></input>
						<div id="pop_content_externalcompany" class="pop_content_input_tip1_hidden">公司名应为6-30位中文字符</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">用户余额：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_deposit" class="product_edit_item_input" value="${currentUser.deposit}" onblur="checkDeposit(this.value, 'user_edit_deposit', 'pop_content_deposit')"></input>
						<div id="pop_content_deposit" class="pop_content_input_tip1_hidden">请输入正确的金额</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">所在城市：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="pop_content_region_dropdown" class="product_edit_item_select" onchange="regionSelect()">
							<option value="0">选择地区</option>
							<s:iterator value="regions" id="region">
								<s:if test='#region.regionId == currentUser.city.province.region.regionId'>
									<option selected="selected" value="${region.regionId}">${region.regionName}</option>
								</s:if>
								<s:else>
									<option value="${region.regionId}">${region.regionName}</option>
								</s:else>
							</s:iterator>
						</select>
						<select id="pop_content_province_dropdown" class="product_edit_item_select" onchange="provinceSelect()">
							<option value="0">选择省份</option>
							<s:iterator value="provinces" id="province">
								<s:if test='#province.provinceId == currentUser.city.province.provinceId'>
									<option selected="selected" value="${province.provinceId}">${province.provinceName}</option>
								</s:if>
								<s:else>
									<option value="${province.provinceId}">${province.provinceName}</option>
								</s:else>
							</s:iterator>
						</select>
						<select id="pop_content_city_dropdown" class="product_edit_item_select" onchange="citySelect()">
							<option value="0">选择城市</option>
							<s:iterator value="cities" id="city">
								<s:if test='#city.cityId ==currentUser.city.cityId'>
									<option selected="selected" value="${city.cityId}">${city.cityName}</option>
								</s:if>
								<s:else>
									<option value="${city.cityId}">${city.cityName}</option>
								</s:else>
							</s:iterator>
						</select>
						<div id="pop_content_city" class="pop_content_input_tip1_hidden">请选择用户城市</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">详细地址：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_address" class="product_edit_item_input" value="${currentUser.userAddress}" onblur="checkAddress(this.value, 'pop_content_address')"></input>
						<div class="product_edit_text"><font class="font_gray">（可选）</font></div>
						<div id="pop_content_address" class="pop_content_input_tip1_hidden">请输入正确的地址</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">邮箱：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_email" class="product_edit_item_input" value="${currentUser.email}" onblur="checkEmail(this.value, 'pop_content_email', 1, ${userId})"></input>
						<div id="pop_content_email" class="pop_content_input_tip1_hidden">请输入正确的邮箱</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">手机号码：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="user_edit_mobile" class="product_edit_item_input" value="${currentUser.mobileNo}" onblur="checkMobile(this.value, 'pop_content_mobile', 1, ${userId})"></input>
						<div id="pop_content_mobile" class="pop_content_input_tip1_hidden">请输入正确的手机号码</div>
					</div>
				</div>
				<div class="product_edit_title1">
					二、高级信息
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">用户角色：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="user_edit_roledropdown" class="product_edit_item_select" onchange="selectRole()">
							<option value="0">选择角色</option>
							<s:iterator value="roles" id="role">
								<s:if test='#role.roleId ==currentUser.role.roleId'>
									<option selected="selected" value="${role.roleId}">${role.roleName}</option>
								</s:if>
								<s:else>
									<option value="${role.roleId}">${role.roleName}</option>
								</s:else>
							</s:iterator>
						</select>
						<s:if test='currentUser.role.roleId == 3'>
							<select id="user_edit_supplierdropdown" class="product_edit_item_select">
								<option value="0">选择供应商公司</option>
								<s:iterator value="supplierCompanies" id="sCompany">
									<s:if test='#sCompany.companyId == currentUser.company.companyId'>
										<option selected="selected" value="${sCompany.companyId}">${sCompany.companyName}</option>
									</s:if>
									<s:else>
										<option value="${sCompany.companyId}">${sCompany.companyName}</option>
									</s:else>
								</s:iterator>
							</select>
						</s:if>
						<s:else>
							<select id="user_edit_supplierdropdown" class="hide_me">
								<option value="0">选择供应商公司</option>
							</select>
						</s:else>
						<s:if test='currentUser.role.roleId == 4'>
							<select id="user_edit_merchantdropdown" class="product_edit_item_select">
								<option value="0">选择经销商公司</option>
								<s:iterator value="merchantCompanies" id="mCompany">
									<s:if test='#mCompany.companyId == currentUser.company.companyId'>
										<option selected="selected" value="${mCompany.companyId}">${mCompany.companyName}</option>
									</s:if>
									<s:else>
										<option value="${mCompany.companyId}">${mCompany.companyName}</option>
									</s:else>
								</s:iterator>
							</select>
						</s:if>
						<s:else>
							<select id="user_edit_merchantdropdown" class="hide_me">
								<option value="0">选择经销商公司</option>
							</select>
						</s:else>
						
						<div id="pop_content_role" class="pop_content_input_tip1_hidden">请选择用户角色</div>
					</div>
				</div>
				<s:if test='currentUser.companyLicenseStatus == "0"'>
					<div class="product_edit_item">
						<div class="product_edit_item_left">
							<div class="product_edit_item_left_text">公司三证：</div>
						</div>
						<div class="product_edit_item_right">
							<div class="product_edit_text"><font class="font_gray">审核状态</font></div>
							<select id="user_edit_companylicensestatus" class="product_edit_item_select">
								<s:if test='currentUser.companyLicenseStatus == "0"'>
									<option selected="selected" value="0">待提交公司证件信息</option>
								</s:if>
								<s:else>
									<option value="0">待提交公司证件信息</option>
								</s:else>
								<s:if test='currentUser.companyLicenseStatus == "1"'>
									<option selected="selected" value="1">审核中</option>
								</s:if>
								<s:else>
									<option value="1">审核中</option>
								</s:else>
								<s:if test='currentUser.companyLicenseStatus == "2"'>
									<option selected="selected" value="2">审核通过</option>
								</s:if>
								<s:else>
									<option value="2">审核通过</option>
								</s:else>
							</select>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="product_edit_item">
						<div class="product_edit_item_left">
							<div class="product_edit_item_left_text">公司三证：</div>
						</div>
						<div class="product_edit_item_right">
							<div class="product_edit_text"><font class="font_gray">审核状态</font></div>
							<select id="user_edit_companylicensestatus" class="product_edit_item_select">
								<s:if test='currentUser.companyLicenseStatus == "0"'>
									<option selected="selected" value="0">待提交公司证件信息</option>
								</s:if>
								<s:else>
									<option value="0">待提交公司证件信息</option>
								</s:else>
								<s:if test='currentUser.companyLicenseStatus == "1"'>
									<option selected="selected" value="1">审核中</option>
								</s:if>
								<s:else>
									<option value="1">审核中</option>
								</s:else>
								<s:if test='currentUser.companyLicenseStatus == "2"'>
									<option selected="selected" value="2">审核通过</option>
								</s:if>
								<s:else>
									<option value="2">审核通过</option>
								</s:else>
							</select>
							<div id="pop_content_warehouse" class="pop_content_input_tip1_hidden">请选择用户角色</div>
						</div>
					</div>
					<s:iterator value="companyLicensePics" id="lpath">
						<div class="acceptance_bill_img"><img id="product_edit_pic_icon1" class="acceptance_bill_small" src="${lpath}" /></div>
					</s:iterator>
				</s:else>
				<div class="product_edit_title1">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text"></div>
					</div>
					<div class="product_edit_item_right">
						<div class="product_edit_save_button hover_pointer" onclick="saveUser(${queryType}, ${userId})">
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
