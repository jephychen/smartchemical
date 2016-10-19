<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.smartchemical.thirdparty.payapi.mobaopay.*"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>跳转中...</title>
	</head>
	<%
		try {

			Mobo360SignUtil.init();
			Map<String, String> paramsMap = new HashMap<String, String>();
			request.setCharacterEncoding("UTF-8");
			paramsMap.put("apiName", Mobo360Config.MOBAOPAY_APINAME_PAY);
			paramsMap.put("apiVersion", Mobo360Config.MOBAOPAY_API_VERSION);
			paramsMap.put("platformID", Mobo360Config.PLATFORM_ID);
			paramsMap.put("merchNo", Mobo360Config.MERCHANT_ACC);
			paramsMap.put("orderNo", request.getParameter("poNo"));
			paramsMap.put("tradeDate", "20160226");
			paramsMap.put("amt", request.getParameter("totalPrice"));
			paramsMap.put("merchUrl", Mobo360Config.MERCHANT_NOTIFY_URL);
			paramsMap.put("merchParam", "zhixuan");
			paramsMap.put("tradeSummary", "summary");
			paramsMap.put("bankCode", request.getParameter("bank"));

			String paramsStr = Mobo360Merchant.generatePayRequest(paramsMap);	
			String signMsg = Mobo360SignUtil.signByMD5(paramsStr, Mobo360Config.MD5_KEY);	
			String epayUrl = Mobo360Config.MOBAOPAY_GETWAY;	
			paramsMap.put("signMsg", signMsg);

			StringBuffer sbHtml = new StringBuffer();
			sbHtml
					.append("<form id='mobaopaysubmit' name='mobaopaysubmit' action='"
							+ epayUrl + "' method='post'>");
			for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
				sbHtml.append("<input type='hidden' name='"
						+ entry.getKey() + "' value='" + entry.getValue()
						+ "'/>");
			}
			sbHtml.append("</form>");
			sbHtml
					.append("<script>document.forms['mobaopaysubmit'].submit();</script>");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(sbHtml.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	%>
	<body>
	</body>
</html>


