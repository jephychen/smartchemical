<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<div class="my_zhixuan_menu_group">
	<ul>
		<li class="my_zhixuan_left_li_title">
			订单中心
		</li>
		<li class="my_zhixuan_left_li">
			<a href="QueryOrder_allOrders.action">
				<s:if test='queryType == "0"'>
					<font class="font_red">我的所有订单</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">我的所有订单</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="QueryOrder_unpaid.action">
				<s:if test='queryType == "1"'>
					<font class="font_red">未付款的订单</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">未付款的订单</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="QueryOrder_waitingReceived.action">
				<s:if test='queryType == "2"'>
					<font class="font_red">待收货的订单</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">待收货的订单</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="QueryOrder_waitingGot.action">
				<s:if test='queryType == "3"'>
					<font class="font_red">待提货的订单</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">待提货的订单</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="QueryOrder_doneOrder.action">
				<s:if test='queryType == "4"'>
					<font class="font_red">已完成的订单</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">已完成的订单</font>
				</s:else>
			</a>
		</li>
	</ul>
	<br>
	<ul>
		<li class="my_zhixuan_left_li_title">
			账户管理
		</li>
		<li class="my_zhixuan_left_li">
			<a href="UserEdit_changePassword.action">
				<s:if test='queryType == "5"'>
					<font class="font_red">修改密码</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">修改密码</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="BankAccountEdit_editBankAccount.action">
				<s:if test='queryType == "6"'>
					<font class="font_red">银行账户管理</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">银行账户管理</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="DepositRequestManagement_allRequest.action">
				<s:if test='queryType == "7"'>
					<font class="font_red">提现/代付请求</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">提现/代付请求</font>
				</s:else>
			</a>
		</li>
		<li class="my_zhixuan_left_li">
			<a href="RechargeRequestManagement_allRequests.action">
				<s:if test='queryType == "8"'>
					<font class="font_red">账户充值</font>
				</s:if>
				<s:else>
					<font class="font_gray_light">账户充值</font>
				</s:else>
			</a>
		</li>
	</ul>
</div>