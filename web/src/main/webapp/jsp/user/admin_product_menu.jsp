<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<div class="my_zhixuan_menu_group">
	<ul>
		<li class="my_zhixuan_left_li_title">
			产品管理
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminProductManagement_allProducts.action">
				<s:if test='queryType == "10"'>
					<font class="font_red">所有产品</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">所有产品</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminProductManagement_supplierProducts.action">
				<s:if test='queryType == "11"'>
					<font class="font_red">供应商产品</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">供应商产品</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminProductManagement_merchantProducts.action">
				<s:if test='queryType == "12"'>
					<font class="font_red">经销商产品</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">经销商产品</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminProductManagement_disabledProducts.action">
				<s:if test='queryType == "13"'>
					<font class="font_red">已下架的产品</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">已下架的产品</font>
				</s:else>
			</a>
		</li>
	</ul>
	<br>
	<ul>
		<li class="my_zhixuan_left_li_title">
			公司管理
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminCompanyManagement_allCompanies.action">
				<s:if test='queryType == "20"'>
					<font class="font_red">所有公司</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">所有公司</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminCompanyManagement_supplierCompanies.action">
				<s:if test='queryType == "21"'>
					<font class="font_red">供应商公司</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">供应商公司</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminCompanyManagement_merchantCompanies.action">
				<s:if test='queryType == "22"'>
					<font class="font_red">经销商公司</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">经销商公司</font>
				</s:else>
			</a>
		</li>
	</ul>
	<br>
	<ul>
		<li class="my_zhixuan_left_li_title">
			仓库管理
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminWarehouseManagement_allWarehouses.action">
				<s:if test='queryType == "30"'>
					<font class="font_red">所有仓库</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">所有仓库</font>
				</s:else>
			</a>
		</li>
	</ul>
</div>