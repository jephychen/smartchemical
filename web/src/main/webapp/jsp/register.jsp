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
<script type="text/javascript" src="./js/register.js" charset="UTF-8"></script>
</head>

<s:if test="smsCheckFirst == true">
	<body class="background_gray" onload="bodyOnLoad()">
</s:if>
<s:else>
	<body class="background_gray">
</s:else>
<body>
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
			<div class="register_pad_table">
				<div class="register_pad_table_title">
					<div class="register_pad_table_title_text">第一步：填写基本信息<font class="font_gray register_pad_table_title_subtext"> —— 请真实填写自身信息</font></div>
				</div>
				<div class="register_pad_form">
					<form id="register_form"  name="register_form" action="Register.action" method="post">
						<ul>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">登录名：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:textfield id="username_field" name="userName" class="register_pad_form_item_input" onblur="checkUserName()" />
								</div>
								<div id="username_field_result" class="register_pad_form_item_right"></div>
								<div id="username_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="username_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										5-20位字符或数字,不以数字开头
									</div>
								</div>
								<div id="username_field_result_illegal_existed" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										登录名已存在
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">公司名称：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:textfield id="companyname_field" name="companyName" class="register_pad_form_item_input" onblur="checkCompanyName()"/>
								</div>
								<div id="companyname_field_result" class="register_pad_form_item_right"></div>
								<div id="companyname_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="companyname_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										公司名应为6-30位中文字符
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">邮箱：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:textfield id="email_field" name="email" class="register_pad_form_item_input" onblur="checkEmail()" />
								</div>
								<div id="email_field_result" class="register_pad_form_item_right"></div>
								<div id="email_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="email_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										请填写正确的邮箱
									</div>
								</div>
								<div id="email_field_result_illegal_existed" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										邮箱已被使用
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">手机号：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:textfield id="mobile_field" name="mobile" class="register_pad_form_item_input" onblur="checkMobile()" />
								</div>
								<div id="mobile_field_result" class="register_pad_form_item_right"></div>
								<div id="mobile_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="mobile_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										请填写正确的手机号
									</div>
								</div>
								<div id="mobile_field_result_illegal_existed" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										手机号码已被使用
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">短信验证码：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:textfield id="register_smsVerifyCode_field" name="smsVerifyCode" class="register_pad_form_item_text_verifycode" onblur="checkSmsverifycodeAjax()" />
									<input type="button" id="register_pad_form_item_getverifycode_button" value="获取验证码" onclick="getVerifyCode()" />
								</div>
								<div id="smsverifycode_field_result" class="register_pad_form_item_right"></div>
								<div id="smsverifycode_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="smsverifycode_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										短信验证码错误
									</div>
								</div>
								<div id="smsverifycode_field_result_illegal_expired" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										验证码已过期
									</div>
								</div>
								<div id="smsverifycode_field_result_illegal_mobile" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										请先填写正确的手机号码
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">密码：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:password id="password_field" name="password" class="register_pad_form_item_input" onblur="checkPassword()" />
								</div>
								<div id="password_field_result" class="register_pad_form_item_right"></div>
								<div id="password_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="password_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										密码应为6-20位
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
									<div class="register_pad_form_item_text">重复密码：</div>
								</div>
								<div class="register_pad_form_item_middle">
									<s:password id="passworddup_field" name="passwordDup" class="register_pad_form_item_input" onblur="checkPasswordDup()" />
								</div>
								<div id="passworddup_field_result" class="register_pad_form_item_right"></div>
								<div id="passworddup_field_result_legal" class="register_pad_form_item_right_legal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/legal.png"/>
								</div>
								<div id="passworddup_field_result_illegal" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										密码应为6-20位
									</div>
								</div>
								<div id="passworddup_field_result_illegal_inconsist" class="register_pad_form_item_right_illegal_hidden">
									<img class="register_form_item_tip_icon" src="./img/register/illegal.png"/>
									<div class="register_form_item_tip_red">
										两次密码必须一致
									</div>
								</div>
							</li>
							<li class="register_pad_form_item">
								<div class="register_pad_form_item_left">
								</div>
								<div class="register_pad_form_item_middle">
									<div class="register_pad_form_item_middle_checkbox"><input id="is_consent_agreement" name="isConsent" type="checkbox" /></div>
									<a href="BulletinDetail.action?type=serviceagreement" target="_blank">
										<div class="register_pad_form_item_middle_text">阅读并同意《智选化学用户协议》</div>
									</a>
								</div>
								<div class="register_pad_form_item_right"></div>
							</li>
							<li id="register_pad_form_common"  class="register_pad_form_item">
								<div class="register_pad_form_item_left">
								</div>
								<div class="register_pad_form_item_middle">
									<div class="register_pad_form_item_middle_submit" onclick="submit_register_common()">提交注册</div>		
								</div>
								<div class="register_pad_form_item_right"></div>
							</li>
						</ul>
					</form>
				</div>
				<div class="register_pad_right">
					<div class="register_pad_right_title">
						<div class="register_pad_right_title_text">注册信息</div>
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
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

