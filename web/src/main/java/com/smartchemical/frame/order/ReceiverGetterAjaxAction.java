/**
 * 
 */
package com.smartchemical.frame.order;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.company.CompanyDao;
import com.smartchemical.api.frame.dao.order.InvoiceDao;
import com.smartchemical.api.frame.dao.region.CityDao;
import com.smartchemical.api.frame.dao.user.CartGetterDao;
import com.smartchemical.api.frame.dao.user.CartReceiverDao;
import com.smartchemical.api.frame.dao.user.RoleDao;
import com.smartchemical.api.frame.dao.user.ScUserDao;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.util.DesUtil;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.order.Invoice;
import com.smartchemical.entities.frame.region.City;
import com.smartchemical.entities.frame.user.CartGetter;
import com.smartchemical.entities.frame.user.CartReceiver;
import com.smartchemical.entities.frame.user.Role;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class ReceiverGetterAjaxAction implements Action {
	
	private int receiverId = -1;
	
	private String receiverName;
	
	private int cityId;
	
	private String address;
	
	private String mobileNo;
	
	private String email;
	
	private int getterId = -1;
	
	private String getterName;
	
	private String getterIdNo;
	
	private String getterMobileNo;
	
	private String getterEmail;
	
	private String truckLicenseNo;
	
	private InputStream inputStream;
	
	private int invoiceType;
	
	private String invoiceCompany;
	
	private String invoiceAccountName;
	
	private String invoiceAccountBank;
	
	private String invoiceAddress;
	
	private String invoiceTaxerId;
	
	private String invoicePhone;
	
	private String warehouseName;
	
	private int warehouseCityId;
	
	private String warehouseAddress;
	
	private String warehouseAdminName;
	
	private String warehouseAdminMobile;

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGetterId() {
		return getterId;
	}

	public void setGetterId(int getterId) {
		this.getterId = getterId;
	}

	public String getGetterName() {
		return getterName;
	}

	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}

	public String getGetterIdNo() {
		return getterIdNo;
	}

	public void setGetterIdNo(String getterIdNo) {
		this.getterIdNo = getterIdNo;
	}

	public String getGetterMobileNo() {
		return getterMobileNo;
	}

	public void setGetterMobileNo(String getterMobileNo) {
		this.getterMobileNo = getterMobileNo;
	}

	public String getGetterEmail() {
		return getterEmail;
	}

	public void setGetterEmail(String getterEmail) {
		this.getterEmail = getterEmail;
	}

	public String getTruckLicenseNo() {
		return truckLicenseNo;
	}

	public void setTruckLicenseNo(String truckLicenseNo) {
		this.truckLicenseNo = truckLicenseNo;
	}

	public int getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceCompany() {
		return invoiceCompany;
	}

	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}

	public String getInvoiceAccountName() {
		return invoiceAccountName;
	}

	public void setInvoiceAccountName(String invoiceAccountName) {
		this.invoiceAccountName = invoiceAccountName;
	}

	public String getInvoiceAccountBank() {
		return invoiceAccountBank;
	}

	public void setInvoiceAccountBank(String invoiceAccountBank) {
		this.invoiceAccountBank = invoiceAccountBank;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getInvoiceTaxerId() {
		return invoiceTaxerId;
	}

	public void setInvoiceTaxerId(String invoiceTaxerId) {
		this.invoiceTaxerId = invoiceTaxerId;
	}

	public String getInvoicePhone() {
		return invoicePhone;
	}

	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public int getWarehouseCityId() {
		return warehouseCityId;
	}

	public void setWarehouseCityId(int warehouseCityId) {
		this.warehouseCityId = warehouseCityId;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public String getWarehouseAdminName() {
		return warehouseAdminName;
	}

	public void setWarehouseAdminName(String warehouseAdminName) {
		this.warehouseAdminName = warehouseAdminName;
	}

	public String getWarehouseAdminMobile() {
		return warehouseAdminMobile;
	}

	public void setWarehouseAdminMobile(String warehouseAdminMobile) {
		this.warehouseAdminMobile = warehouseAdminMobile;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String addInvoice() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		long lastModified = System.currentTimeMillis();
		InvoiceDao invoiceDao = DaoFactory.getInvoiceDao();
		Invoice invoice = invoiceDao.insertInvoice(invoiceType, user, invoiceCompany, invoiceAccountName, invoiceAccountBank, invoiceAddress, invoiceTaxerId, invoicePhone, lastModified);
		if (invoice == null){
			setInputStream(new StringBufferInputStream("-1"));
			return SUCCESS;
		}
		setInputStream(new StringBufferInputStream(parseInvoice(invoice)));
		return SUCCESS;
	}
	
	private String parseInvoice(Invoice invoice) throws UnsupportedEncodingException {
		if (invoice == null){
			return "";
		}
		String result = invoice.getInvoiceId() + "," + invoice.getInvoiceCompany() + "," + invoice.getInvoiceAccountName()
				+ "," + invoice.getInvoiceAccountBank() + "," + invoice.getInvoiceAddress() + "," + invoice.getInvoiceTaxerId()
				+ "," + invoice.getInvoicePhone();
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	public String editCartGetter() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		long lastSelected = System.currentTimeMillis();
		CartGetterDao getterDao = DaoFactory.getCartGetterDao();
		CartGetter getter = getterDao.getGetterById(getterId);
		if (getter == null){
			getterDao.insertGetter(user, getterName, getterIdNo, getterMobileNo, getterEmail, truckLicenseNo, lastSelected, null);
		}
		else{
			getterDao.editGetter(getterId, user, getterName, getterIdNo, getterMobileNo, getterEmail, truckLicenseNo, lastSelected, null);
		}
		CartGetter getterParam = getterDao.getGetterByLastselected(user.getUserId(), lastSelected);
		setInputStream(new StringBufferInputStream(parseGetter(getterParam)));
		return SUCCESS;
	}
	
	public String editCartReceiver() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		CityDao cityDao = DaoFactory.getCityDao();
		City city = cityDao.getCityById(cityId);
		if (city == null){
			setInputStream(new StringBufferInputStream("failed"));
			return SUCCESS;
		}
		long lastSelected = System.currentTimeMillis();
		CartReceiverDao receiverDao = DaoFactory.getCartReceiverDao();
		CartReceiver receiver = receiverDao.getReceiverById(receiverId);
		if (receiver == null){
			receiverDao.insertCartReceiver(receiverName, user, city, address, mobileNo, email, lastSelected, null);
		}
		else{
			receiverDao.editCartReceiver(receiverId, receiverName, user, city, address, mobileNo, email, lastSelected, null);
		}
		CartReceiver receiverParam = receiverDao.getReceiverByIdLastselected(user.getUserId(), lastSelected);
		setInputStream(new StringBufferInputStream(parseReceiver(receiverParam)));
		return SUCCESS;
	}
	
	public String getReceiver() throws Exception {
		CartReceiverDao cartReceiverDao = DaoFactory.getCartReceiverDao();
		CartReceiver receiver = cartReceiverDao.getReceiverById(receiverId);
		setInputStream(new StringBufferInputStream(parseReceiver(receiver)));
		return SUCCESS;
	}

	private String parseReceiver(CartReceiver receiver) throws UnsupportedEncodingException {
		if (receiver == null){
			return "";
		}
		String result = receiver.getReceiverId() + "," + receiver.getReceiverName() + "," + receiver.getCity().getProvince().getRegion().getRegionId() + "," + receiver.getCity().getProvince().getProvinceId()
				+ "," + receiver.getCity().getCityId() + "," + receiver.getAddress() + "," + receiver.getMobileNo() + "," + receiver.getEmail()
				+ "," + receiver.getCity().getProvince().getProvinceName() + "," + receiver.getCity().getCityName();
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	public String getGetter() throws Exception {
		CartGetterDao cartGetterDao = DaoFactory.getCartGetterDao();
		CartGetter getter = cartGetterDao.getGetterById(getterId);
		setInputStream(new StringBufferInputStream(parseGetter(getter)));
		return SUCCESS;
	}

	private String parseGetter(CartGetter getter) throws UnsupportedEncodingException {
		if (getter == null){
			return "";
		}
		String result = getter.getGetterId() + "," + getter.getGetterName() + "," + getter.getGetterIdNo() 
				+ "," + getter.getGetterMobileNo() + "," + getter.getTruckLicenseNo() + "," + getter.getGetterEmail();
		String resultWrapper = new String(result.getBytes("UTF-8"), "iso-8859-1");
		return resultWrapper;
	}
	
	public String removeReceiver() throws Exception {
		CartReceiverDao cartReceiverDao = DaoFactory.getCartReceiverDao();
		boolean isSuccess = cartReceiverDao.disableReceiver(receiverId);
		if (isSuccess){
			setInputStream(new StringBufferInputStream(String.valueOf(receiverId)));
			return SUCCESS;
		}
		else {
			setInputStream(new StringBufferInputStream("failed"));
			return SUCCESS;
		}
	}
	
	public String removeGetter() throws Exception {
		CartGetterDao cartGetterDao = DaoFactory.getCartGetterDao();
		boolean isSuccess = cartGetterDao.disableGetter(getterId);
		if (isSuccess){
			setInputStream(new StringBufferInputStream(String.valueOf(getterId)));
			return SUCCESS;
		}
		else {
			setInputStream(new StringBufferInputStream("failed"));
			return SUCCESS;
		}
	}
	
	public String addWarehouse() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		CompanyDao companyDao = DaoFactory.getCompanyDao();
		Company company = companyDao.getCompanyById(1);
		RoleDao roleDao = DaoFactory.getRoleDao();
		Role role = roleDao.getRoleById(5);
		WarehouseDao wDao = DaoFactory.getWarehouseDao();
		CityDao cDao = DaoFactory.getCityDao();
		City city = cDao.getCityById(warehouseCityId);
		ScUserDao userDao = DaoFactory.getScUserDao();
		ScUser whAdmin = userDao.insertUser(String.valueOf(System.currentTimeMillis()), warehouseAdminName, DesUtil.encrypt("123456", "matrix1986"), null, company, user.getExternalCompanyName(), role, String.valueOf(System.currentTimeMillis()) + "@zhixuanhuaxue.com", city, warehouseAddress, 0, 0, warehouseAdminMobile, new Timestamp(System.currentTimeMillis()));
		Warehouse warehouse = wDao.insertWarehouse(warehouseName, city, warehouseAddress, whAdmin, 0, 0, 1);
		if (warehouse != null){
			String result = warehouse.getWarehouseId() + "," + warehouse.getWarehouseName();
			String resultStr = new String(result.getBytes("UTF-8"), "iso-8859-1");
			setInputStream(new StringBufferInputStream(resultStr));
			return SUCCESS;
		}
		else {
			setInputStream(new StringBufferInputStream("failed"));
			return SUCCESS;
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
