/**
 * 
 */
package com.smartchemical.api.frame.dao.user;

import java.util.List;

import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public interface CartReceiverDao {
	public boolean insertCartReceiver(String receiverName, ScUser user, City city, String address, String mobileNo, String email, long lastSelected, String remark);
	
	public boolean editCartReceiver(int receiverId, String receiverName, ScUser user, City city, String address, String mobileNo, String email, long lastSelected, String remark);
	
	public boolean editCartReceiver(CartReceiver receiver);
	
	public boolean disableReceiver(int receiverId);
	
	public boolean removeReceiver(int receiverId);
	
	public boolean removeReceiverByUser(int userId);
	
	public List<CartReceiver> getReceiverByUser(int userId);
	
	public List<CartReceiver> getEnableReceiverByUser(int userId);
	
	public CartReceiver getReceiverById(int receiverId);
	
	public CartReceiver getReceiverByIdLastselected(int userId, long lastSelected);
}
