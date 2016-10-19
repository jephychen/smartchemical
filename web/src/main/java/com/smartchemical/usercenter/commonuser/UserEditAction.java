/**
 * 
 */
package com.smartchemical.usercenter.commonuser;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.UserLock;
import com.smartchemical.common.util.DesUtil;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class UserEditAction implements Action {
	
	private int queryType;
	
	private String title;
	
	private String tip;
	
	private String password;
	
	private String newPassword;
	
	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String changePassword() throws Exception {
		queryType = 5;
		return SUCCESS;
	}
	
	public String changePasswordSubmit() throws Exception {
		queryType = 5;
		title = "修改密码";
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ScUserDao userDao = DaoFactory.getScUserDao();
		ScUser currentUser = userDao.getUserById(user.getUserId());
		String currentPasword = DesUtil.decrypt(currentUser.getPassword(), "matrix1986");
		if (!currentPasword.equals(password)){
			tip = "修改密码失败，原密码错误！";
			return "toresult";
		}
		String encryptedPassword = DesUtil.encrypt(newPassword, "matrix1986");
		currentUser.setPassword(encryptedPassword);
		synchronized (UserLock.class) {
			if (!userDao.editUser(currentUser)){
				tip = "修改密码失败！";
				return "toresult";
			}
			else {
				tip = "修改密码成功！";
				return "toresult";
			}
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
