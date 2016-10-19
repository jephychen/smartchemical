<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智选化学 厂家直售的化工智能电商平台</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" media="screen" />
<link href="./css/verify.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script>
(function(){
	var bp = document.createElement('script');
	bp.src = '//push.zhanzhang.baidu.com/push.js';
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(bp, s);
})();
</script>
	<div class="pop_dialog_frame">
		<div class="pop_dialog_mask">	
			<div class="pop_dialog_wrapper">
				<div class="verify_table">
					<div class="verify_title">
						智选化学公测中，请输入邀请码
					</div>
					<div class="verify_content">
						<form action="Verify.action" method="post">
							<input id="verify_code" name="verifyCode" type="password" />
							<input name="verify_code_submit" type="button" value="提交"  onClick="submit()"/>
						</form>
					</div>
					<div class="verify_tip">
						${tip}
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

