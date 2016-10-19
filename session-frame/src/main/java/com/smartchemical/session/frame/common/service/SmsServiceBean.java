/**
 * 
 */
package com.smartchemical.session.frame.common.service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.common.service.SmsService;
import com.smartchemical.entities.frame.configuration.SmsConfiguration;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ SmsService.class })
@RemoteBinding(jndiBinding = "smart-chemical/SmsServiceBean/remote")
public class SmsServiceBean implements SmsService {
	
	public static String SMS_REGISTER_VERIFYCODE_NAME = "register_verify_code";
	
	public static String SMS_GET_DEPOSIT_VERIFYCODE_NAME = "get_deposit_verify_code";

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public void sendSms(String mobiles, String content) {
	}

	public void sendRegisterVerifyCodes(String mobile, String verifyCode) {
		SmsConfiguration conf = getSmsConfiguration(SMS_REGISTER_VERIFYCODE_NAME);
		if (conf == null){
			System.out.println("Smartchemical Error, cannot find register sms configuration.");
			return;
		}
		TaobaoClient client = new DefaultTaobaoClient(conf.getUrl(), conf.getAppkey(), conf.getSecret());
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(conf.getExtend());
		req.setSmsType(conf.getSmsType());
		req.setSmsFreeSignName(conf.getSmsFreeSignName());
		req.setSmsParamString(conf.getSmsParamString().replace("verifyCode", verifyCode));
		req.setRecNum(mobile);
		req.setSmsTemplateCode(conf.getSmsTemplateCode());
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
	}
	
	public void sendGetDepositVerifyCodes(String mobile, String verifyCode, String account, String amount) {
		SmsConfiguration conf = getSmsConfiguration(SMS_GET_DEPOSIT_VERIFYCODE_NAME);
		if (conf == null){
			System.out.println("Smartchemical Error, cannot find register sms configuration.");
			return;
		}
		TaobaoClient client = new DefaultTaobaoClient(conf.getUrl(), conf.getAppkey(), conf.getSecret());
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(conf.getExtend());
		req.setSmsType(conf.getSmsType());
		req.setSmsFreeSignName(conf.getSmsFreeSignName());
		String smsParam = conf.getSmsParamString().replace("verifyCode", verifyCode).replace("depositAccount", account).replace("depositAmount", amount);
		req.setSmsParamString(smsParam);
		req.setRecNum(mobile);
		req.setSmsTemplateCode(conf.getSmsTemplateCode());
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
	}

	public SmsConfiguration getSmsConfiguration(String type){
		try {
			Query query = em.createNativeQuery("select * from SmsConfiguration where smsConfName = ?1", SmsConfiguration.class);
			query.setParameter(1, type);
			@SuppressWarnings("unchecked")
			List<SmsConfiguration> confs = query.getResultList();
			if (confs != null && !confs.isEmpty()){
				return confs.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
