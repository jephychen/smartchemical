/**
 * 
 */
package com.smartchemical.frame.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.api.frame.dao.order.DeliverType;
import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.api.frame.dao.order.OrderItemDao;
import com.smartchemical.api.frame.dao.order.OrderItemStatus;
import com.smartchemical.api.frame.dao.order.OrderStatus;
import com.smartchemical.api.frame.dao.order.PaymentType;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.user.CartGetterDao;
import com.smartchemical.api.frame.dao.user.CartReceiverDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.ProductLock;
import com.smartchemical.common.lock.UserLock;
import com.smartchemical.common.util.DecimalFormatUtil;
import com.smartchemical.common.util.PoNoGenerator;
import com.smartchemical.entities.frame.order.Cart;
import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.order.PurchaseOrder;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
public class OrderSubmitAction implements Action {

	private int deliveryType = -1;
	
	private int receiverId = -1;
	
	private int getterId = -1;
	
	private int paymentType = -1;
	
	/**
	 * 所有商品的价格，不含运费
	 * */
	private float totalPrice = 0;
	
	private String selectedIds;
	
	/**
	 * 点击立即购买时的id
	 * */
	private int productId;
	
	/**
	 * 点击立即购买时的数量
	 * */
	private float quantity;
	
	private String poId;
	
	private String poNo;
	
	private float logisticFee;
	
	/**
	 * 承兑汇票手续费
	 * */
	private float acBillServiceFee = 0;
	
	private int invoiceId;
	
	private String invoiceCompany;
	
	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getGetterId() {
		return getterId;
	}

	public void setGetterId(int getterId) {
		this.getterId = getterId;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
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

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
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

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceCompany() {
		return invoiceCompany;
	}

	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}

	private boolean myVerify(){
		if (deliveryType < 1 || deliveryType > 3 || paymentType < 1 || paymentType > 4 ){
			return false;
		}
		if ((deliveryType == DeliverType.VERDOR.getIndex() || deliveryType == DeliverType.ZHIXUAN.getIndex()) && receiverId < 1){
			return false;
		}
		if ( deliveryType == DeliverType.USER.getIndex() && getterId < 1){
			return false;
		}
		if (acBillServiceFee < 0 || logisticFee < 0){
			return false;
		}
		String reg = "([0-9]+\\u007C)*";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(selectedIds);
		if (!matcher.matches()){
			return false;
		}
		logisticFee = roundFloat(logisticFee);
		acBillServiceFee = roundFloat(acBillServiceFee);
		return true;
	}

	public String makeIds(){
		String params = selectedIds.replace("|", ",");
		return "(" + params.substring(0, params.length() - 1) + ")";
	}
	
	public float roundFloat(float original){
		int scale = 1;
		int roundingMode = 4;
		BigDecimal bd = new BigDecimal((double) original);
		bd = bd.setScale(scale,roundingMode);
		return bd.floatValue();
	}
	
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (!myVerify()){
			return ERROR;
		}
		CartReceiverDao cartReceiverDao = DaoFactory.getCartReceiverDao();
		CartReceiver receiver = cartReceiverDao.getReceiverById(receiverId);
		//选择了之后，将receiver的顺序调到最高。
		if (receiver != null){
			receiver.setLastSelected(System.currentTimeMillis());
			cartReceiverDao.editCartReceiver(receiver);
		}
		
		CartGetterDao cartGetterDao = DaoFactory.getCartGetterDao();
		CartGetter getter = cartGetterDao.getGetterById(getterId);
		//选择了之后，将receiver的顺序调到最高。
		if (getter != null){
			getter.setLastSelected(System.currentTimeMillis());
			cartGetterDao.editGetter(getter);
		}
		
		//取得发票信息
		InvoiceDao invoiceDao = DaoFactory.getInvoiceDao();
		Invoice invoice = invoiceDao.getInvoice(invoiceId);
		
		//插入订单
		PurchaseOrderDao purchaseOrderDao = DaoFactory.getPurchaseOrderDao();
		PurchaseOrder purchaseOrder = null;
		ScUserDao scUserDao = DaoFactory.getScUserDao();
		if (paymentType == PaymentType.ACCEPTANCE_BILL.getIndex()){
			purchaseOrder = purchaseOrderDao.insertPurchaseOrder(user, deliveryType, receiver, getter, paymentType, logisticFee, acBillServiceFee, totalPrice + logisticFee + acBillServiceFee, 
					invoice, OrderStatus.PAYED.getIndex(), OrderStatus.SUB_UPLOADING_ACCEPTANCEBILL.getIndex(), new Timestamp(System.currentTimeMillis()), null);
		}
		else if (paymentType == PaymentType.DEPOSIT.getIndex()){
			float totalOrderPrice = totalPrice + logisticFee;
			synchronized (UserLock.class) {
				ScUser currentUser = scUserDao.getUserById(user.getUserId());
				float newDeposit = currentUser.getDeposit() - totalOrderPrice;
				if (newDeposit < 0){
					return ERROR;
				}
				currentUser.setDeposit(newDeposit);
				if (!scUserDao.editUser(currentUser)){
					return ERROR;
				}
				user = currentUser;
			}
			purchaseOrder = purchaseOrderDao.insertPurchaseOrder(user, deliveryType, receiver, getter, paymentType, logisticFee, 0, totalOrderPrice, 
					invoice, OrderStatus.PAYED.getIndex(), OrderStatus.SUB_EMPTY.getIndex(), new Timestamp(System.currentTimeMillis()), null);
		}
		else {
			purchaseOrder = purchaseOrderDao.insertPurchaseOrder(user, deliveryType, receiver, getter, paymentType, logisticFee, 0, totalPrice + logisticFee, 
					invoice, OrderStatus.WAITING_TO_BE_PAID.getIndex(), OrderStatus.SUB_EMPTY.getIndex(), new Timestamp(System.currentTimeMillis()), null);
		}
		if (purchaseOrder == null){
			return ERROR;
		}
		poNo = PoNoGenerator.makePurchaseOrderNo(purchaseOrder.getPurchaseOrderId());
		purchaseOrder.setPurchaseOrderNo(poNo);
		boolean isSuccess = purchaseOrderDao.editPurchaseOrder(purchaseOrder);
		if (!isSuccess){
			purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
			return ERROR;
		}
		OrderItemDao orderItemDao = DaoFactory.getOrderItemDao();
		
		//订单条目的状态
		int currentOrderItemStatus = OrderItemStatus.WAITING_TO_BE_DELIVERED.getIndex();
		if (deliveryType == DeliverType.USER.getIndex()){
			currentOrderItemStatus = OrderItemStatus.WAITING_TO_BE_GOT.getIndex();
		}
		else{
			currentOrderItemStatus = OrderItemStatus.WAITING_TO_BE_DELIVERED.getIndex();
		}
		//表示通过购物车购买
		if (productId == -1){
			CartDao cartDao = DaoFactory.getCartDao();
			List<Cart> cartProducts = cartDao.getCartByUserAndProductIds(user.getUserId(), makeIds());
			if (cartProducts == null || cartProducts.isEmpty()){
				purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
				return ERROR;
			}
			float totalPriceCheck = 0;
			for (Cart cartProduct : cartProducts){
				totalPriceCheck += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
				if (!orderItemDao.insertOrderItem(purchaseOrder, cartProduct.getProduct(), cartProduct.getQuantity(), currentOrderItemStatus,
						null, null, null)){
					orderItemDao.removeOrderItemsByOrderId(purchaseOrder.getPurchaseOrderId());
					purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
					return ERROR;
				}
				synchronized (ProductLock.class) {
					//订单入库之前检测并修改库存，如果商品没库存了则删除订单。
					if (!updateProductStocklevel(cartProduct.getProduct().getProductId(), cartProduct.getQuantity())){
						orderItemDao.removeOrderItemsByOrderId(purchaseOrder.getPurchaseOrderId());
						purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
						return ERROR;
					}	
				}
			}
			cartDao.removeCartByUserProductIds(user.getUserId(), makeIds());
			if (DecimalFormatUtil.formatFloat(totalPriceCheck) != totalPrice){
				orderItemDao.removeOrderItemsByOrderId(purchaseOrder.getPurchaseOrderId());
				purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
				return ERROR;
			}
			int userCartQuantity = cartDao.getCartByUser(user.getUserId()).size();
			user.setCartQuantity(userCartQuantity);
			synchronized (UserLock.class) {
				scUserDao.editUser(user);
			}
		}
		//表示通直接购买
		else 
		{
			ProductDao productDao = DaoFactory.getProductDao();
			Product product = productDao.getProductById(productId);
			if (!orderItemDao.insertOrderItem(purchaseOrder, product, quantity, currentOrderItemStatus,
					null, null, null)){
				orderItemDao.removeOrderItemsByOrderId(purchaseOrder.getPurchaseOrderId());
				purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
				return ERROR;
			}
			synchronized (ProductLock.class) {
				//订单入库之前检测并修改库存，如果商品每库存了则删除订单。
				if (!updateProductStocklevel(productId, quantity)){
					orderItemDao.removeOrderItemsByOrderId(purchaseOrder.getPurchaseOrderId());
					purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
					return ERROR;
				}
			}
			float totalPriceCheck = product.getPrice() * quantity;
			if (DecimalFormatUtil.formatFloat(totalPriceCheck) != totalPrice){
				orderItemDao.removeOrderItemsByOrderId(purchaseOrder.getPurchaseOrderId());
				purchaseOrderDao.removePurchaseOrder(purchaseOrder.getPurchaseOrderId());
				return ERROR;
			}
		}
		updateSession(user);
		poId = String.valueOf(purchaseOrder.getPurchaseOrderId());
		if (paymentType == PaymentType.ONLINE.getIndex()){
			return "toonlinepay";
		}
		else if (paymentType == PaymentType.TRANSFER.getIndex()){
			return "totransferpay";
		}
		else if (paymentType == PaymentType.ACCEPTANCE_BILL.getIndex()){
			return "toacceptancebillpay";
		}
		else if (paymentType == PaymentType.DEPOSIT.getIndex()){
			return "todepositpay";
		}
		else {
			return ERROR;
		}
	}

	private void updateSession(ScUser currentUser) {
		ActionContext ctx = ActionContext.getContext();
		currentUser.setDepositStr(DecimalFormatUtil.formatFloat2String(currentUser.getDeposit()));
		ctx.getSession().put("user", currentUser);
	}

	//修改库存量。产品的卖出总数和卖出总价在订单完成之后再修改
	private boolean updateProductStocklevel(int productId, float quantity) throws NamingException {
		ProductDao pDao = DaoFactory.getProductDao();
		Product product = pDao.getProductById(productId);
		if (product == null || product.getStockLevel() < quantity){
			return false;
		}
		product.setStockLevel(product.getStockLevel() - quantity);
		if (!pDao.editProduct(product)){
			System.out.println("ERROR: update stocklevel failed");
			return false;
		}
		return true;
	}

}
