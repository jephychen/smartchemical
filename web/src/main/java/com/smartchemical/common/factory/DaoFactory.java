/**
 * 
 */
package com.smartchemical.common.factory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.smartchemical.api.frame.common.service.SmsService;
import com.smartchemical.api.frame.dao.account.RechargeRequestDao;
import com.smartchemical.api.frame.dao.account.WithdrawRequestDao;
import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.order.CartDao;
import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.api.frame.dao.order.OrderItemDao;
import com.smartchemical.api.frame.dao.order.PurchaseOrderDao;
import com.smartchemical.api.frame.dao.product.ProductCategoryDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.product.ProductNoDao;
import com.smartchemical.api.frame.dao.product.ProductTypeDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.region.ProvinceDao;
import com.smartchemical.api.frame.dao.region.RegionDao;
import com.smartchemical.api.frame.dao.unit.UnitDao;
import com.smartchemical.api.frame.dao.user.BankAccountDao;
import com.smartchemical.api.frame.dao.user.CartGetterDao;
import com.smartchemical.api.frame.dao.user.CartReceiverDao;
import com.smartchemical.api.frame.dao.user.RoleDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.api.frame.dao.user.SmsVerifyCodeDao;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.api.frame.pay.OnlinePayService;
import com.smartchemical.common.constant.BeanConstant;

/**
 * @author Jephy
 *
 */
public class DaoFactory {
	
	private static InitialContext getContext() throws NamingException{
		return new InitialContext();
	}
	
	public static CompanyDao getCompanyDao() throws NamingException{
		InitialContext ctx = getContext();
		return (CompanyDao) ctx.lookup(BeanConstant.COMPANY_DAO_JNDI);
	}
	
	public static ProductDao getProductDao() throws NamingException{
		InitialContext ctx = getContext();
		return (ProductDao) ctx.lookup(BeanConstant.PRODUCT_DAO_JNDI);
	}
	
	public static ProductNoDao getProductNoDao() throws NamingException{
		InitialContext ctx = getContext();
		return (ProductNoDao) ctx.lookup(BeanConstant.PRODUCTNO_DAO_JNDI);
	}
	
	public static RegionDao getRegionDao() throws NamingException{
		InitialContext ctx = getContext();
		return (RegionDao) ctx.lookup(BeanConstant.REGION_DAO_JNDI);
	}
	
	public static ProvinceDao getProvinceDao() throws NamingException{
		InitialContext ctx = getContext();
		return (ProvinceDao) ctx.lookup(BeanConstant.PROVINCE_DAO_JNDI);
	}
	
	public static CityDao getCityDao() throws NamingException{
		InitialContext ctx = getContext();
		return (CityDao) ctx.lookup(BeanConstant.CITY_DAO_JNDI);
	}
	
	public static RoleDao getRoleDao() throws NamingException{
		InitialContext ctx = getContext();
		return (RoleDao) ctx.lookup(BeanConstant.ROLE_DAO_JNDI);
	}
	
	public static ScUserDao getScUserDao() throws NamingException{
		InitialContext ctx = getContext();
		return (ScUserDao) ctx.lookup(BeanConstant.SCUSER_DAO_JNDI);
	}
	
	public static SmsVerifyCodeDao getSmsVerifyCodeDao() throws NamingException{
		InitialContext ctx = getContext();
		return (SmsVerifyCodeDao) ctx.lookup(BeanConstant.SMS_VERIFYCODE_DAO_JNDI);
	}
	
	public static CartDao getCartDao() throws NamingException{
		InitialContext ctx = getContext();
		return (CartDao) ctx.lookup(BeanConstant.CART_DAO_JNDI);
	}
	
	public static CartGetterDao getCartGetterDao() throws NamingException{
		InitialContext ctx = getContext();
		return (CartGetterDao) ctx.lookup(BeanConstant.CART_GETTER_DAO_JNDI);
	}
	
	public static CartReceiverDao getCartReceiverDao() throws NamingException{
		InitialContext ctx = getContext();
		return (CartReceiverDao) ctx.lookup(BeanConstant.CART_RECEIVER_DAO_JNDI);
	}
	
	public static PurchaseOrderDao getPurchaseOrderDao() throws NamingException{
		InitialContext ctx = getContext();
		return (PurchaseOrderDao) ctx.lookup(BeanConstant.PURCHASEORDER_DAO_JNDI);
	}
	
	public static InvoiceDao getInvoiceDao() throws NamingException{
		InitialContext ctx = getContext();
		return (InvoiceDao) ctx.lookup(BeanConstant.INVOICE_DAO_JNDI);
	}
	
	public static OrderItemDao getOrderItemDao() throws NamingException{
		InitialContext ctx = getContext();
		return (OrderItemDao) ctx.lookup(BeanConstant.ORDERITEM_DAO_JNDI);
	}
	
	public static SmsService getSmsServiceBean() throws NamingException{
		InitialContext ctx = getContext();
		return (SmsService) ctx.lookup(BeanConstant.SMS_SERVICE_JNDI);
	}
	
	public static OnlinePayService getOnlinePayService() throws NamingException{
		InitialContext ctx = getContext();
		return (OnlinePayService) ctx.lookup(BeanConstant.ONLINEPAY_SERVICE_JNDI);
	}
	
	public static ProductTypeDao getProductTypeDao() throws NamingException{
		InitialContext ctx = getContext();
		return (ProductTypeDao) ctx.lookup(BeanConstant.PRODUCTTYPE_DAO_JNDI);
	}
	
	public static ProductCategoryDao getProductCategoryDao() throws NamingException{
		InitialContext ctx = getContext();
		return (ProductCategoryDao) ctx.lookup(BeanConstant.PRODUCTCATEGORY_DAO_JNDI);
	}
	
	public static UnitDao getUnitDao() throws NamingException{
		InitialContext ctx = getContext();
		return (UnitDao) ctx.lookup(BeanConstant.UNIT_DAO_JNDI);
	}

	public static WarehouseDao getWarehouseDao() throws NamingException {
		InitialContext ctx = getContext();
		return (WarehouseDao) ctx.lookup(BeanConstant.WAREHOUSE_DAO_JNDI);
	}
	
	public static BrandDao getBrandDao() throws NamingException {
		InitialContext ctx = getContext();
		return (BrandDao) ctx.lookup(BeanConstant.BRANDS_DAO_JNDI);
	}
	
	public static BankAccountDao getBankAccountDao() throws NamingException {
		InitialContext ctx = getContext();
		return (BankAccountDao) ctx.lookup(BeanConstant.BANKACCOUNT_DAO_JNDI);
	}
	
	public static WithdrawRequestDao getWithdrawRequestDao() throws NamingException {
		InitialContext ctx = getContext();
		return (WithdrawRequestDao) ctx.lookup(BeanConstant.WITHDRAWREQUEST_DAO_JNDI);
	}
	
	public static RechargeRequestDao getRechargeRequestDao() throws NamingException {
		InitialContext ctx = getContext();
		return (RechargeRequestDao) ctx.lookup(BeanConstant.RECHARGEREQUEST_DAO_JNDI);
	}
}
