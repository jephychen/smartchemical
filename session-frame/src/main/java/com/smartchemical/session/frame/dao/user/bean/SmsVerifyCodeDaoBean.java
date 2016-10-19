/**
 * 
 */
package com.smartchemical.session.frame.dao.user.bean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.user.SmsVerifyCodeDao;
import com.smartchemical.entities.frame.user.SmsVerifyCode;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ SmsVerifyCodeDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/SmsVerifyCodeDaoBean/remote")
public class SmsVerifyCodeDaoBean implements SmsVerifyCodeDao {

	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;
	
	public boolean insertSmsVerifyCode(String mobile, String smsVerifyCode, int codeType, long generateTime) {
		try {
			SmsVerifyCode verifyCode = new SmsVerifyCode();
			verifyCode.setMobileNo(mobile);
			verifyCode.setSmsVerifyCode(smsVerifyCode);
			verifyCode.setCodeType(codeType);
			verifyCode.setGenerateTime(generateTime);
			em.persist(verifyCode);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean editSmsVerifyCode(int smsVerifyCodeId, String mobile, String smsVerifyCode, int codeType, long generateTime) {
		try {
			SmsVerifyCode verifyCode = em.find(SmsVerifyCode.class, smsVerifyCodeId);
			verifyCode.setMobileNo(mobile);
			verifyCode.setSmsVerifyCode(smsVerifyCode);
			verifyCode.setCodeType(codeType);
			verifyCode.setGenerateTime(generateTime);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public SmsVerifyCode getSmsVerifyCodeByMobile(String mobile, int codeType) {
		try {
			Query query = em.createQuery("select r from SmsVerifyCode r where r.mobileNo = ?1 and r.codeType = ?2");
			query.setParameter(1, mobile);
			query.setParameter(2, codeType);
			@SuppressWarnings("unchecked")
			List<SmsVerifyCode> codes = query.getResultList();
			if (codes == null || codes.isEmpty()){
				return null;
			}
			return codes.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeSmsVerifyCodeByMobile(String mobile) {
		try {
			Query query = em.createQuery("select r from SmsVerifyCode r where r.mobileNo = ?1");
			query.setParameter(1, mobile);
			@SuppressWarnings("unchecked")
			List<SmsVerifyCode> codes = query.getResultList();
			if (codes == null || codes.isEmpty()){
				return true;
			}
			em.remove(codes.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
