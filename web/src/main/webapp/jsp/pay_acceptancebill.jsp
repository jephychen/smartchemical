<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 智能化工产品在线交易平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/frame.css" rel="stylesheet" type="text/css" />
<link href="./css/pay.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/pay_acceptancebill.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
</head>

<body class="background_gray">
<%@ include file="./common/header.jsp" %>

<div id="total_cart">	
<div id="total_wrapper">
	<div id="pay_pad">
		<div id="pay_pad_header">
			<div id="pay_pad_logo">
				<a href="TitaniumMainPage.action" id="header_logo_link"><img id="cart_logo" src="./img/logo_gray.png" /></a>
			</div>
			<div id="pay_pad_title">
				<span id="cart_title_span">收银台</span>
			</div>
		</div>
		<div id="pay_pad_content">
			<div id="pay_pad_content_mypay">
				<div id="pay_pad_content_mypay_text">订单号 <font class="font_red">${poNo}</font></div>
				<div id="pay_pad_content_mypay_totalprice">应付金额 <font class="font_red">￥${totalPrice}</font></div>
			</div>
			<div id="pay_pad_content_mypay">
				<div id="pay_pad_content_mypay_tip">请在提交订单尽快完成银行承兑汇票扫描件的上传，以及银行承兑汇票的邮寄。以免影响发货。</div>
			</div>
			<div id="pay_pad_content_items">
				<div id="pay_pad_content_title">
					请上传银行承兑汇票扫描件。上传的图片大小应小于5M。支持格式 bmp, png, gif, jpeg, jpg。
				</div>
				<div id="acceptancebill_uploaddiv1" class="pay_pad_acceptancebill_item">
					<form id="acceptance_bill_upload_form1" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload1" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form1', 'ac_bill_upload1', 'acceptance_bill_img_icon1', '1')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv2')">继续添加扫描件</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon1" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv2" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form2" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload2" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form2', 'ac_bill_upload2', 'acceptance_bill_img_icon2', '2')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv3')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form2', '2')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon2" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv3" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form3" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload3" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form3', 'ac_bill_upload3', 'acceptance_bill_img_icon3', '3')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv4')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form3', '3')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon3" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv4" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form4" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload4" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form4', 'ac_bill_upload4', 'acceptance_bill_img_icon4', '4')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv5')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form4', '4')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon4" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv5" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form5" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload5" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form5', 'ac_bill_upload5', 'acceptance_bill_img_icon5', '5')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv6')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form5', '5')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon5" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv6" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form6" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload6" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form6', 'ac_bill_upload6', 'acceptance_bill_img_icon6', '6')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv7')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form6', '6')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon6" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv7" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form7" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload7" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form7', 'ac_bill_upload7', 'acceptance_bill_img_icon7', '7')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv8')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form7', '7')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon7" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv8" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form8" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload8" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form8', 'ac_bill_upload8', 'acceptance_bill_img_icon8', '8')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv9')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form8', '8')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon8" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv9" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form9" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload9" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form9', 'ac_bill_upload9', 'acceptance_bill_img_icon9', '9')">上传</div>
						<div class="acceptance_bill_upload_continue" onclick="continue_upload('acceptancebill_uploaddiv10')">继续添加扫描件</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form9', '9')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon9" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div id="acceptancebill_uploaddiv10" class="pay_pad_acceptancebill_item_hidden">
					<form id="acceptance_bill_upload_form10" enctype="multipart/form-data">
						<input type="file" id="ac_bill_upload10" name="acceptanceBillFile" class="acceptance_bill_upload_input" onchange="checkFile(this)" />
						<div id="acceptance_bill_upload_button" onclick="uploadAcceptanceBillImg(${poNo}, 'acceptance_bill_upload_form10', 'ac_bill_upload10', 'acceptance_bill_img_icon10', '10')">上传</div>
						<div class="acceptance_bill_upload_remove" onclick="remove_upload(${poNo}, 'acceptance_bill_upload_form10', '10')">删除</div>
						<div class="acceptance_bill_img"><img id="acceptance_bill_img_icon10" class="acceptance_bill_small" /></div>
					</form>
				</div>
				<div class="pay_pad_acceptancebill_item">
					<div id="acceptance_bill_address">银行承兑汇票邮寄地址：中国四川省成都市高新区，天府五街菁蓉国际广场4号楼8楼</div>
				</div>
			</div>
			<div id="pay_pad_content_footer">
				<form id="pay_pad_acceptancebill_submit_form">
					<div class="pay_button" onclick="submitToCheck(${poNo})">
						提交审核
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="./common/footer.jsp" %>
</body>
</html>

