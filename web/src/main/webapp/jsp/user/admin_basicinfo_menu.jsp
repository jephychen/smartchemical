<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<div class="my_zhixuan_menu_group">
	<ul>
		<li class="my_zhixuan_left_li_title">
			用户管理
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminUserManagement_allUsers.action">
				<s:if test='queryType == "10"'>
					<font class="font_red">所有用户</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">所有用户</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminUserManagement_merchantUsers.action">
				<s:if test='queryType == "11"'>
					<font class="font_red">经销商用户</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">经销商用户</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminUserManagement_supplierUsers.action">
				<s:if test='queryType == "12"'>
					<font class="font_red">供应商用户</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">供应商用户</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminUserManagement_commonUsers.action">
				<s:if test='queryType == "13"'>
					<font class="font_red">普通用户</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">普通用户</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminUserManagement_adminUsers.action">
				<s:if test='queryType == "14"'>
					<font class="font_red">系统管理员</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">系统管理员</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="AdminUserManagement_warehouseAdminUsers.action">
				<s:if test='queryType == "15"'>
					<font class="font_red">仓库管理员</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">仓库管理员</font>
				</s:else>
			</a>
		</li>
	</ul>
</div>