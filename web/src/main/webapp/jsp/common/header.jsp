<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<div id="top_bar">
	<div id="top_bar_wrapper_mainpage">
		<ul class="top_bar_ul_left">
			<li class="top_bar_li_left"><a href="TitaniumMainPage.action"><font class="font_gray">智选主页</font></a></li>
			<li class="top_bar_li"><a href="MainSearch.action"><font class="font_gray">产品搜索</font></a></li>
		</ul>
		<ul class="top_bar_ul_right">
			<s:if test='#session.user.role.roleId == "1"'>
				<li class="top_bar_li_left"><a href="AdminQueryOrder_allOrders.action"><font class="font_red_dec">管理后台</font></a></li>
			</s:if>
			<s:elseif test='#session.user.role.roleId == "3"'>
				<li class="top_bar_li_left"><a href="SupplierQueryOrder_allOrders.action"><font class="font_red_dec">供应商后台</font></a></li>
			</s:elseif>
			<s:elseif test='#session.user.role.roleId == "4"'>
				<li class="top_bar_li_left"><a href="MerchantQueryOrder_allOrders.action"><font class="font_red_dec">经销商后台</font></a></li>
			</s:elseif>
			<s:else>
				<li class="top_bar_li_left"><a href="TitaniumMainPage.action"><font class="font_gray">帮助中心</font></a></li>
			</s:else>
			<li class="top_bar_li"><a href="TitaniumMainPage.action"><font class="font_gray">更多</font></a></li>
		</ul>
		<ul class="top_bar_ul_right">
			<li class="top_bar_li_left"><a href="QueryOrder_allOrders.action"><font class="font_gray">我的订单</font></a></li>
			<li class="top_bar_li_left"><a href="MyZhixuan.action"><font class="font_gray">我的智选</font></a></li>
			<li class="top_bar_li_cart"><a href="Cart.action"><img class="cart_icon_black" src="./img/cart.png" /><div class="cart_text">购物车 <font class="font_red">${sessionScope.user.cartQuantity}</font></div></a></li>
		</ul>
		<ul class="top_bar_ul_right">
			<s:if test='#session.user.userName != ""'>
				<li class="top_bar_li"><font class="font_gray">${sessionScope.user.userName}, </font><a href="Logout.action"><font class="font_gray">退出登录</font></a></li>
			</s:if>
			<s:else>
				<li class="top_bar_li_left"><a href="Login.action"><font class="font_gray">登录</font></a></li>
				<li class="top_bar_li"><a href="Register.action"><font class="font_gray">注册</font></a></li>
			</s:else>
		</ul>
	</div>
</div>