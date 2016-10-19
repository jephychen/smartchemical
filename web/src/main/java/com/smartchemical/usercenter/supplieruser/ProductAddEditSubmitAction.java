/**
 * 
 */
package com.smartchemical.usercenter.supplieruser;

import java.sql.Timestamp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.smartchemical.api.frame.dao.company.BrandDao;
import com.smartchemical.api.frame.dao.product.ProductDao;
import com.smartchemical.api.frame.dao.product.ProductNoDao;
import com.smartchemical.api.frame.dao.product.ProductTypeDao;
import com.smartchemical.api.frame.dao.unit.UnitDao;
import com.smartchemical.api.frame.dao.warehouse.WarehouseDao;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.factory.DaoFactory;
import com.smartchemical.common.lock.ProductLock;
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.product.Product;
import com.smartchemical.entities.frame.product.ProductNo;
import com.smartchemical.entities.frame.product.ProductType;
import com.smartchemical.entities.frame.unit.Unit;
import com.smartchemical.entities.frame.user.ScUser;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
public class ProductAddEditSubmitAction implements Action {
	
	private int queryType;
	
	private int productId;
	
	private String returnAction;
	
	private String productName;
	
	private String productNo;
	
	private int productType;
	
	private int productCompany;
	
	private String productDesc;
	
	private float productPrice;
	
	private int productPriceUnit;
	
	private int productQuantityUnit;
	
	private int productWarehouse;
	
	private float productStocklevel;
	
	private float minSoldQuantity;
	
	private String productPicPath;
	
	private String productLongDesc;
	
	private int brandId;
	
	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getReturnAction() {
		return returnAction;
	}

	public void setReturnAction(String returnAction) {
		this.returnAction = returnAction;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public int getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(int productCompany) {
		this.productCompany = productCompany;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductPriceUnit() {
		return productPriceUnit;
	}

	public void setProductPriceUnit(int productPriceUnit) {
		this.productPriceUnit = productPriceUnit;
	}

	public int getProductQuantityUnit() {
		return productQuantityUnit;
	}

	public void setProductQuantityUnit(int productQuantityUnit) {
		this.productQuantityUnit = productQuantityUnit;
	}

	public int getProductWarehouse() {
		return productWarehouse;
	}

	public void setProductWarehouse(int productWarehouse) {
		this.productWarehouse = productWarehouse;
	}

	public float getProductStocklevel() {
		return productStocklevel;
	}

	public void setProductStocklevel(float productStocklevel) {
		this.productStocklevel = productStocklevel;
	}

	public float getMinSoldQuantity() {
		return minSoldQuantity;
	}

	public void setMinSoldQuantity(float minSoldQuantity) {
		this.minSoldQuantity = minSoldQuantity;
	}

	public String getProductPicPath() {
		return productPicPath;
	}

	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public void setProductLongDesc(String productLongDesc) {
		this.productLongDesc = productLongDesc;
	}
	
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String editSave() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		if (queryType == 0){
			returnAction = "allProducts";
		}
		else if (queryType == 1){
			returnAction = "supplierProducts";
		}
		else if (queryType == 2){
			returnAction = "merchantProducts";
		}
		else if (queryType == 3){
			returnAction = "disabledProducts";
		}
		ProductNoDao pNoDao = DaoFactory.getProductNoDao();
		ProductNo pNo = pNoDao.getProductByName(productNo);
		if (pNo == null){
			pNo = pNoDao.insertProductNo(productNo, productNo);
		}
		ProductDao pDao = DaoFactory.getProductDao();
		Product p = pDao.getProductById(productId);
		ProductTypeDao pTypeDao = DaoFactory.getProductTypeDao();
		ProductType ptype = pTypeDao.getProductTypeById(productType);
		BrandDao brandDao = DaoFactory.getBrandDao();
		Brand temp = brandDao.getBrandByName(user.getCompany().getCompanyName());
		Brand brand = null;
		if (temp == null){
			brand = brandDao.insertBrand(user.getCompany().getCompanyName(), null, null);
		}
		else {
			brand = temp;
		}
		Company company = user.getCompany();
		UnitDao uDao = DaoFactory.getUnitDao();
		Unit priceUnit = uDao.getUnitById(productPriceUnit);
		Unit quantityUnit = uDao.getUnitById(productQuantityUnit);
		WarehouseDao wDao = DaoFactory.getWarehouseDao();
		Warehouse warehouse = wDao.getWarehouseById(productWarehouse);
		String formalPath = copyPicToImg(productPicPath);
		if (p == null){
			pDao.insertProduct(productName, ptype, 1, pNo, brand, company, null, productPrice, priceUnit, 0, quantityUnit, 0 , formalPath, productStocklevel, 
					minSoldQuantity, warehouse, productDesc, new Timestamp(System.currentTimeMillis()), productLongDesc);
		}
		else {
			p.setProductName(productName);
			p.setProductType(ptype);
			p.setProductStatus(p.getProductStatus());
			p.setProductNo(pNo);
			p.setBrand(brand);
			p.setCompany(company);				//不能修改品牌
			p.setPrice(productPrice);
//			p.setPriceUnit(priceUnit);  		//修改不能改单位
//			p.setQuantityUnit(quantityUnit);	//修改不能改单位
			p.setPictureUrl(formalPath);
			p.setStockLevel(productStocklevel);
			p.setMinSoldQunatity(minSoldQuantity);
			p.setWarehouse(warehouse);
			p.setDescription(productDesc);
			p.setLastModified(new Timestamp(System.currentTimeMillis()));
			p.setContentDetail(productLongDesc);
			synchronized (ProductLock.class) {
				pDao.editProduct(p);
			}
		}
		
		return SUCCESS;
	}

	private String getFileName(String tempPath){
		int index = tempPath.lastIndexOf("/");
		if (index == -1){
			return tempPath;
		}
		return tempPath.substring(index + 1);
	}
	
	private String copyPicToImg(String tempPath) {
		String filename = getFileName(tempPath);
		String srcFile = PathConstant.PRODUCT_PIC_TEMP_FOLDER + filename;
		String destFile = PathConstant.PRODUCT_PIC_SAVE_FOLDER + filename;
		String srcFileAbsolute = ServletActionContext.getServletContext().getRealPath(srcFile);
		String destFileAbsolute = ServletActionContext.getServletContext().getRealPath(destFile);
		if (CopyFileUtil.copyFile(srcFileAbsolute, destFileAbsolute, true)){
			return PathConstant.PROJECTNAME + destFile;
		}
		else{
			return "";
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
