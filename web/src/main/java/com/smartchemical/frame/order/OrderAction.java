/**
 * 
 */
package com.smartchemical.frame.order;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.api.frame.dao.user.CartGetterDao;
import com.smartchemical.api.frame.dao.user.CartReceiverDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.DecimalFormatUtil;
import com.smartchemical.entities.frame.region.Region;
import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.order.Cart;
import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.product.Product;

/**
 * @author Jephy
 *
 */
public class OrderAction implements Action {
	
	private String selectedIds;
	
	private List<Cart> cartProducts;
	
	private List<CartReceiver> cartReceivers;
	
	private List<CartGetter> cartGetters;
	
	private List<Region> regions;
	
	private int productId = -1;
	
	private float quantity;
	
	private float totalProductPrice = 0;
	
	private float logisticFee = 0;
	
	private float acBillServiceFee = 0;
	
	private String userDeposit;
	
	private Invoice invoice;
	
	private int invoiceId;
	
//	private static float AC_SERVICEFEE_RATE = (float) 0.045;

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public List<Cart> getCartProducts() {
		return cartProducts;
	}

	public List<CartReceiver> getCartReceivers() {
		return cartReceivers;
	}

	public void setCartReceivers(List<CartReceiver> cartReceivers) {
		this.cartReceivers = cartReceivers;
	}

	public List<CartGetter> getCartGetters() {
		return cartGetters;
	}

	public void setCartGetters(List<CartGetter> cartGetters) {
		this.cartGetters = cartGetters;
	}

	public void setCartProducts(List<Cart> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(float totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public float getLogisticFee() {
		return logisticFee;
	}

	public void setLogisticFee(float logisticFee) {
		this.logisticFee = logisticFee;
	}

	public float getAcBillServiceFee() {
		return acBillServiceFee;
	}

	public void setAcBillServiceFee(float acBillServiceFee) {
		this.acBillServiceFee = acBillServiceFee;
	}

	public String getUserDeposit() {
		return userDeposit;
	}

	public void setUserDeposit(String userDeposit) {
		this.userDeposit = userDeposit;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean caculateAndVerifyCarts() throws NamingException{
		ProductDao pDao = DaoFactory.getProductDao();
		for (Cart cartProduct : cartProducts){
			totalProductPrice += cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
			Product p = pDao.getProductById(cartProduct.getProduct().getProductId());
			if (p.getStockLevel() < cartProduct.getQuantity()){
				return false;
			}
		}
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		totalProductPrice= Float.parseFloat(decimalFormat.format(totalProductPrice));
		return true;
	}

	public String makeIds(){
		String params = selectedIds.replace("|", ",");
		return "(" + params.substring(0, params.length() - 1) + ")";
	}
	
	private void caculateLogisticFee() {
		logisticFee = 200;
	}
	
//	private void caculateAcServiceFee() {
//		acBillServiceFee = totalProductPrice / (1 - AC_SERVICEFEE_RATE) - totalProductPrice;
//	}
	
	public String orderWithoutCarts() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		ProductDao productDao = DaoFactory.getProductDao();
		Product product = productDao.getProductById(productId);
		if (product == null || product.getProductStatus() != 1){
			return ERROR;
		}
		if (quantity > product.getStockLevel()){
			return ERROR;
		}
		/**
		 * 创建一个cart，返回页面
		 * */
		Cart dummyCart = new Cart();
		dummyCart.setProduct(product);
		dummyCart.setQuantity(quantity);
		dummyCart.setUser(user);
		
		cartProducts = new LinkedList<Cart>();
		cartProducts.add(dummyCart);
		//判断货物库存是否足够
		if (!caculateAndVerifyCarts()){
			return ERROR;
		}
		CartReceiverDao cartReceiverDao = DaoFactory.getCartReceiverDao();
		cartReceivers = cartReceiverDao.getEnableReceiverByUser(user.getUserId());
		CartGetterDao cartGetterDao = DaoFactory.getCartGetterDao();
		cartGetters = cartGetterDao.getEnableGetterByUser(user.getUserId());
		totalProductPrice = DecimalFormatUtil.formatFloat(product.getPrice() * quantity);
		userDeposit = DecimalFormatUtil.formatFloat2String(user.getDeposit());
		RegionDao regionDao = DaoFactory.getRegionDao();
		regions = regionDao.getAllRegions();
		InvoiceDao invoiceDao = DaoFactory.getInvoiceDao();
		invoice = invoiceDao.getLastInvoice(user.getUserId());
		if (invoice == null){
			invoice = invoiceDao.insertInvoice(1, user, user.getExternalCompanyName(), user.getExternalCompanyName(), null, null, null, null, System.currentTimeMillis());
			invoiceId = invoice.getInvoiceId();
		}
		else {
			invoiceId = invoice.getInvoiceId();
		}
		caculateLogisticFee();
//		caculateAcServiceFee();
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (selectedIds == null || selectedIds.isEmpty()){
			return "tosearh";
		}
		CartDao cartDao = DaoFactory.getCartDao();
		cartProducts = cartDao.getCartByUserAndProductIds(user.getUserId(), makeIds());
		if (cartProducts == null || cartProducts.isEmpty()){
			return "tosearh";
		}
		if (!caculateAndVerifyCarts()){
			return ERROR;
		}
		CartReceiverDao cartReceiverDao = DaoFactory.getCartReceiverDao();
		cartReceivers = cartReceiverDao.getEnableReceiverByUser(user.getUserId());
		CartGetterDao cartGetterDao = DaoFactory.getCartGetterDao();
		cartGetters = cartGetterDao.getEnableGetterByUser(user.getUserId());
		RegionDao regionDao = DaoFactory.getRegionDao();
		regions = regionDao.getAllRegions();
		InvoiceDao invoiceDao = DaoFactory.getInvoiceDao();
		invoice = invoiceDao.getLastInvoice(user.getUserId());
		if (invoice == null){
			invoiceId = -1;
		}
		else {
			invoiceId = invoice.getInvoiceId();
		}
		userDeposit = DecimalFormatUtil.formatFloat2String(user.getDeposit());
		caculateLogisticFee();
//		caculateAcServiceFee();
		return SUCCESS;
	}
	
}
