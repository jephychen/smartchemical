/**
 * 
 */
package com.smartchemical.session.frame.dao.user.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.RemoteBinding;

import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.entities.frame.user.BankAccount;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@Stateless
@Remote({ BankAccountDao.class })
@RemoteBinding(jndiBinding = "smart-chemical/BankAccountDaoBean/remote")
public class BankAccountDaoBean implements BankAccountDao {
	
	@PersistenceContext(unitName = "smartchemical")
	protected EntityManager em;

	public BankAccount insertBankAccount(ScUser user, String companyName,
			String accountNo, String accountBank, int accountType, String bankAddress,
			String mobile, String remark) {
		try {
			BankAccount account = new BankAccount();
			account.setUser(user);
			account.setCompanyName(companyName);
			account.setAccountNo(accountNo);
			account.setAccountBank(accountBank);
			account.setAccountType(accountType);
			account.setBankAddress(bankAddress);
			account.setMobile(mobile);
			account.setRemark(remark);
			account.setLastmodified(new Timestamp(System.currentTimeMillis()));
			account.setCreatetime(new Timestamp(System.currentTimeMillis()));
			em.persist(account);
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeBankAccount(int accountId) {
		try {
			BankAccount currentAccount = em.find(BankAccount.class, accountId);
			em.remove(currentAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editBankAcount(BankAccount account) {
		try {
			BankAccount currentAccount = em.find(BankAccount.class, account.getAccountId());
			currentAccount.setUser(account.getUser());
			currentAccount.setCompanyName(account.getCompanyName());
			currentAccount.setAccountNo(account.getAccountNo());
			currentAccount.setAccountBank(account.getAccountBank());
			currentAccount.setBankAddress(account.getBankAddress());
			currentAccount.setMobile(account.getMobile());
			currentAccount.setRemark(account.getRemark());
			currentAccount.setLastmodified(new Timestamp(System.currentTimeMillis()));
			em.merge(currentAccount);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public BankAccount getBankAcountById(int accountId) {
		try {
			BankAccount currentAccount = em.find(BankAccount.class, accountId);
			return currentAccount;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<BankAccount> getBankAcountByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from BankAccount where scUserId = ?1 order by accountType", BankAccount.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<BankAccount> accounts = query.getResultList();
			return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<BankAccount> getMainBankAcountByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from BankAccount where scUserId = ?1 and accountType = 1", BankAccount.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<BankAccount> accounts = query.getResultList();
			return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<BankAccount> getOtherBankAcountByUser(int userId) {
		try {
			Query query = em.createNativeQuery("select * from BankAccount where scUserId = ?1 and accountType = 2", BankAccount.class);
			query.setParameter(1, userId);
			@SuppressWarnings("unchecked")
			List<BankAccount> accounts = query.getResultList();
			return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
