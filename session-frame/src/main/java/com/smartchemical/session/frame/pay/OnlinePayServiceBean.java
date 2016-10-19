/**
 * 
 */
package com.smartchemical.session.frame.pay;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.pay.OnlinePayService;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ OnlinePayService.class })
@RemoteBinding(jndiBinding = "smart-chemical/OnlinePayServiceBean/remote")
public class OnlinePayServiceBean implements OnlinePayService {

	public boolean pay(String poNo, String productName, float totalAmount) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String paydate = sdf.format(new Date());
//        
//		Map<String, String> payParam = new HashMap<String, String>();
//		payParam.put("apiName", "WEB_PAY_B2C");
//		payParam.put("apiVersion", "1.0.0.1");
//		payParam.put("platformID", "MerchTest");
//		payParam.put("merchNo", "210001110100250");
//		payParam.put("orderNo", poNo);
//		payParam.put("tradeDate", paydate);
//		payParam.put("amt", String.valueOf(totalAmount) + "0");
//		payParam.put("merchUrl", "http://www.merchant.com/handler.jsp");
//		payParam.put("merchParam", "");
//		payParam.put("tradeSummary", productName);
//		
//		String payParamStr = null;
//		String signedMsg = null;
//		try {
//			payParamStr = Mobo360Merchant.generatePayRequest(payParam);
//			Mobo360SignUtil.initMD5();
//			signedMsg = Mobo360SignUtil.signByMD5(payParamStr, "22c41d776c24deddca95b1709a88f04b");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (payParamStr == null || signedMsg == null){
//			return false;
//		}
//		payParamStr += "&signMsg=" + signedMsg;
//		payParamStr += "&bankCode=" + "ICBC";
//		
//		String result = null;
//		try {
//			result = Mobo360Merchant.transact(payParamStr, "https://182.148.123.7/cgi-bin/netpayment/pay_gate.cgi");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		return false;
	}

}
