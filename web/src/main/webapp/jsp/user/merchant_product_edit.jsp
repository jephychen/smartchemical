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
<script type="text/javascript" src="./js/merchant_product_edit.js" charset="UTF-8"></script>
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
				<a href="MerchantProductManagement_allProducts.action">
				<li class="user_page_navi_li_selected hover_pointer">
					产品管理
				</li>
				</a>
				<a href="MerchantQueryOrder_allOrders.action">
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
			<div class="my_zhixuan_menu_group">
				<ul>
					<li class="my_zhixuan_left_li_title">
						产品管理
					</li>
					<li class="my_zhixuan_left_li">
						<a href="MerchantProductManagement_allProducts.action">
							<s:if test='queryType == "0"'>
								<font class="font_red">所有产品</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">所有产品</font>
							</s:else>
						</a>
					</li>
					<li class="my_zhixuan_left_li">
						<a href="MerchantProductManagement_disabledProducts.action">
							<s:if test='queryType == "3"'>
								<font class="font_red">已下架的产品</font>
							</s:if>
							<s:else>
								<font class="font_gray_light">已下架的产品</font>
							</s:else>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<form id="product_save_form">
		</form>
		<div id="my_zhixuan_right">
			<div class="my_zhixuan_right_title">
				<div class="my_zhixuan_right_title_text">
					修改产品
				</div>
			</div>
			<div class="my_zhixuan_right_content">
				<div class="product_edit_title">
					一、基本信息
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品名称：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="product_edit_productname" class="product_edit_item_input" value="${currentProduct.productName}" onblur="checkProductName()" ></input>
						<div id="pop_content_productname" class="pop_content_input_tip1_hidden">请输入3~20位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品型号：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="product_edit_productno" class="product_edit_item_input" value="${currentProduct.productNo.productNoName}"  onblur="checkProductNo()"></input>
						<div id="pop_content_productno" class="pop_content_input_tip1_hidden">应为2~20位英文,数字或字符</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品分类：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="pop_content_category_dropdown" class="product_edit_item_select" onchange="categorySelect()">
							<option value="0">选择分类</option>
							<s:iterator value="productCategories" id="productCategory">
								<s:if test='#productCategory.productCategoryId == currentProduct.productType.productCategory.productCategoryId'>
									<option selected="selected" value="${productCategory.productCategoryId}">${productCategory.productCategoryName}</option>
								</s:if>
								<s:else>
									<option value="${productCategory.productCategoryId}">${productCategory.productCategoryName}</option>
								</s:else>
							</s:iterator>
						</select>
						<select id="pop_content_producttype_dropdown" class="product_edit_item_select" onchange="checkProductType()">
							<option value="0">选择类型</option>
							<s:iterator value="productTypes" id="productType">
								<s:if test='#productType.productTypeId == currentProduct.productType.productTypeId'>
									<option selected="selected" value="${productType.productTypeId}">${productType.productTypeName}</option>
								</s:if>
								<s:else>
									<option value="${productType.productTypeId}">${productType.productTypeName}</option>
								</s:else>
							</s:iterator>
						</select>
						<div id="pop_content_producttype" class="pop_content_input_tip1_hidden">请选择产品分类</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品品牌：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="pop_content_company_dropdown" class="product_edit_item_select" onchange="checkCompany()">
							<option value="0">选择品牌</option>
							<s:iterator value="brands" id="brand">
								<s:if test='#brand.brandId == currentProduct.brand.brandId'>
									<option selected="selected" value="${brand.brandId}">${brand.brandName}</option>
								</s:if>
								<s:else>
									<option value="${brand.brandId}">${brand.brandName}</option>
								</s:else>
							</s:iterator>
							<option value="-1">其他</option>
						</select>
						<input id="other_brand" class="product_edit_item_input_short_right_hidden" value="请填写品牌" onfocus="clearDefault()" onblur="checkBrand()"></input>
						<div id="pop_content_productcompany" class="pop_content_input_tip1_hidden">请选择品牌</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品短描述：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="product_edit_desc" class="product_edit_item_input_long" value="${currentProduct.description}" onblur="checkProductDesc()"></input>
						<div id="pop_content_productdesc" class="pop_content_input_tip1_hidden">请输入4~50位汉字或英文</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">单价：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="product_edit_price" class="product_edit_item_input_short" value="${currentProduct.price}" onblur="checkProductPrice()"></input>
						<select id="pop_content_priceunit_dropdown" disabled="disabled" class="product_edit_item_select">
							<option value="${currentProduct.priceUnit.unitId}">${currentProduct.priceUnit.unitName}</option>
						</select>
						<div class="product_edit_text"><font class="font_gray">每</font></div>
						<select id="pop_content_quantityunit_dropdown" disabled="disabled" class="product_edit_item_select">
							<s:iterator value="quantityUnits" id="quantityUnit">
								<s:if test='#quantityUnit.unitId == currentProduct.quantityUnit.unitId'>
									<option selected="selected" value="${quantityUnit.unitId}">${quantityUnit.unitName}</option>
								</s:if>
								<s:else>
									<option value="${quantityUnit.unitId}">${quantityUnit.unitName}</option>
								</s:else>
							</s:iterator>
						</select>
						<div id="pop_content_productprice" class="pop_content_input_tip1_hidden">请输入正确的价格</div>
					</div>
				</div>
				<div class="product_edit_title1">
					二、库存信息
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">所在仓库：</div>
					</div>
					<div class="product_edit_item_right">
						<select id="pop_content_region_dropdown" class="product_edit_item_select" onchange="regionSelect()">
							<option value="0">选择地区</option>
							<s:iterator value="regions" id="region">
								<s:if test='#region.regionId == currentProduct.warehouse.city.province.region.regionId'>
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
								<s:if test='#province.provinceId == currentProduct.warehouse.city.province.provinceId'>
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
								<s:if test='#city.cityId == currentProduct.warehouse.city.cityId'>
									<option selected="selected" value="${city.cityId}">${city.cityName}</option>
								</s:if>
								<s:else>
									<option value="${city.cityId}">${city.cityName}</option>
								</s:else>
							</s:iterator>
						</select>
						<select id="pop_content_warehouse_dropdown" class="product_edit_item_select" onchange="checkWarehouse()">
							<option value="0">选择仓库</option>
							<s:iterator value="warehouses" id="warehouse">
								<s:if test='#warehouse.warehouseId == currentProduct.warehouse.warehouseId'>
									<option selected="selected" value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
								</s:if>
								<s:else>
									<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
								</s:else>
							</s:iterator>
						</select>
						<div id="product_edit_nowarehouse_text" class="product_edit_text_hidden"><font class="font_gray">所在城市尚无仓库</font></div>
						<div class="product_edit_text">
							<font class="font_red">&nbsp&nbsp
								<span id="new_warehouse_button" class="hover_pointer" onclick="showWarehouseDialog()">新建仓库</span>
							</font>
						</div>
						<div id="pop_content_warehouse" class="pop_content_input_tip1_hidden">请选择仓库</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">库存量：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="product_edit_stocklevel" class="product_edit_item_input_short" value="${currentProduct.stockLevel}" onblur="checkStockLevel()"></input> 
						<div class="product_edit_text"><font class="font_gray">${currentProduct.quantityUnit.unitName}</font></div>
						<div id="pop_content_stocklevel" class="pop_content_input_tip1_hidden">请输入正确的库存量</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">最小起售量：</div>
					</div>
					<div class="product_edit_item_right">
						<input id="product_edit_minsoldquantity" class="product_edit_item_input_short" value="${currentProduct.minSoldQunatity}" onblur="checkMinSoldQuantity()"></input> 
						<div class="product_edit_text"><font id="stocklevel_unit" class="font_gray">${currentProduct.quantityUnit.unitName}</font></div>
						<div id="pop_content_minsoldquantity" class="pop_content_input_tip1_hidden">请输入正确的起售量</div>
					</div>
				</div>
				<div class="product_edit_item">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品图片上传：</div>
					</div>
					<div class="product_edit_item_right">
						<form id="product_edit_upload_form" enctype="multipart/form-data">
							<input type="file" id="product_edit_pic_upload" name="productPicFile" class="product_edit_item_fileupload" onchange="checkFile(this)" />
							<div class="product_edit_upload_button hover_pointer" onclick="uploadProductPic()">上传</div>
						</form>
						<div id="pop_content_productpic" class="pop_content_input_tip1_hidden">请上传产品图片</div>
					</div>
				</div>
				<div class="acceptance_bill_img"><img id="product_edit_pic_icon1" class="acceptance_bill_small" src="${currentProduct.pictureUrl}" /></div>
				<div class="product_edit_item_big">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text">产品长描述：</div>
					</div>
					<div class="product_edit_item_right">
						<textarea id="product_edit_productlongdesc" class="product_edit_item_textarea">
							${currentProduct.contentDetail}
						</textarea>
					</div>
				</div>
				<div class="product_edit_title1">
					<div class="product_edit_item_left">
						<div class="product_edit_item_left_text"></div>
					</div>
					<div class="product_edit_item_right">
						<div class="product_edit_save_button hover_pointer" onclick="saveProduct(${queryType}, ${currentProduct.productId})">
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
<div id="pop_dialog_warehouse" class="pop_dialog_frame">
	<div class="pop_dialog_mask">	
	</div>
	<div class="pop_dialog_wrapper">
		<div class="pop_dialog_warehouse">
			<form id="pop_getter_form">
				<div class="pop_title">
					<div class="pop_title_text">编辑仓库信息</div>
					<div id="pop_dialog_frame_order_address_shutdown_button" class="pop_title_toolbar" onclick="closeWarehouseDialog()">
						<img class="pop_title_toolbar_icon" src="./img/shutdown.png" />
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							仓库名：
						</div>
					</div>
					<div class="pop_dialog_warehouse_right">
						<div class="pop_content_content">
							<input id="pop_content_input_warehouse_name" class="pop_content_input_invoice" type="text" onblur="checkWarehouseName()" />
						</div>
						<div id="pop_content_input_warehousename_tip" class="pop_content_input_tip_hidden">请输入2~15位汉字</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							仓库地区：
						</div>
					</div>
					<div class="pop_dialog_warehouse_right">
						<div class="pop_content_content">
							<div id="pop_content_content_regioninfo" class="pop_content_title pop_content_regioninfo">
								华北-北京-北京
							</div>
						</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							仓库管理员：
						</div>
					</div>
					<div class="pop_dialog_warehouse_right">
						<div class="pop_content_content">
							<input id="pop_content_input_whadmin_id" class="pop_content_input_invoice" type="text" onblur="checkWhAdminName()" />
						</div>
						<div id="pop_content_input_whadmin_tip" class="pop_content_input_tip_hidden">请输入正确的姓名</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							库管员电话：
						</div>
					</div>
					<div class="pop_dialog_warehouse_right">
						<div class="pop_content_content">
							<input id="pop_content_input_whadminmobile_id" class="pop_content_input_invoice" type="text" onblur="checkWhAdminMobile()" />
						</div>
						<div id="pop_content_input_whadminmobile_tip" class="pop_content_input_tip_hidden">请输入正确的电话</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
							详细地址：
						</div>
					</div>
					<div class="pop_dialog_warehouse_right">
						<div class="pop_content_content">
							<input id="pop_content_input_address_id" class="pop_content_input_invoice" type="text" onblur="checkAddress()" />
						</div>
						<div id="pop_content_input_address_tip" class="pop_content_input_tip_hidden">请输入正确的地址</div>
					</div>
				</div>
				<div class="pop_content_item">
					<div class="pop_content_item_left">
						<div class="pop_content_title">
						</div>
					</div>
					<div class="pop_dialog_warehouse_right">
						<div class="pop_content_content">
							<input class="pop_invoice_content_button" type="button" value="保存仓库信息" onclick="saveWarehouse()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>

