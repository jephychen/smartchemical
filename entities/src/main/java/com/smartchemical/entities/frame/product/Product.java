/**
 * 
 */
package com.smartchemical.entities.frame.product;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.smartchemical.entities.frame.company.Brand;
import com.smartchemical.entities.frame.company.Company;
import com.smartchemical.entities.frame.unit.Unit;
import com.smartchemical.entities.frame.warehouse.Warehouse;

/**
 * @author Jephy
 *
 */
@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productId;
	
	private String productName;
	
	/**
	 * 产品类型
	 */
	private ProductType productType;
	
	/**
	 * 1 - 正在销售，2 - 已下架
	 */
	private int productStatus = 1;
	
	/**
	 * 产品型号
	 */
	private ProductNo productNo;
	
	/**
	 * 产品图片地址
	 */
	private String pictureUrl;
	
	/**
	 * 商品品牌
	 */
	private Brand brand;
	
	/**
	 * 贸易商公司
	 */
	private Company company;
	
	/**
	 * 经销商公司
	 */
	private Company merchantCompany;
	
	private float price;
	
	/**
	 * 价格单位
	 */
	private Unit priceUnit;
	
	/**
	 * 数量单位
	 */
	private Unit quantityUnit;
	
	/**
	 * 此产品卖出的总数
	 */
	private float totalSoldQuantity;
	
	/**
	 * 此产品卖出的总金额
	 */
	private float totalSoldAmount;
	
	/**
	 * 此产品库存数量
	 */
	private float stockLevel;
	
	/**
	 * 最小起售数量
	 */
	private float minSoldQunatity;
	
	/**
	 * 产品所在仓库
	 */
	private Warehouse warehouse;
	
	/**
	 * 产品描述
	 */
	private String description;
	
	/**
	 * 产品参数，来自参数表，不生成数据库字段
	 */
	private List<Parameter> parameters;
	
	/**
	 * 最近修改时间
	 */
	private Timestamp lastModified;
	
	/**
	 * 在产品详细信息页面显示的内容
	 */
	private String contentDetail;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(nullable=false, length=255)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "productTypeId")
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Column(nullable=false)
	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "productNoId")
	public ProductNo getProductNo() {
		return productNo;
	}

	public void setProductNo(ProductNo productNo) {
		this.productNo = productNo;
	}
	
	@Column(nullable=false, length=255)
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "brandId")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "companyId")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=true) 
	@JoinColumn(name = "merchantCompanyId")
	public Company getMerchantCompany() {
		return merchantCompany;
	}

	public void setMerchantCompany(Company merchantCompany) {
		this.merchantCompany = merchantCompany;
	}

	@Column(nullable=false)
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "priceUnitId")
	public Unit getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(Unit priceUnit) {
		this.priceUnit = priceUnit;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "quantityUnitId")
	public Unit getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(Unit quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	@Column(nullable=false)
	public float getTotalSoldQuantity() {
		return totalSoldQuantity;
	}

	public void setTotalSoldQuantity(float totalSoldQuantity) {
		this.totalSoldQuantity = totalSoldQuantity;
	}

	@Column(nullable=false)
	public float getTotalSoldAmount() {
		return totalSoldAmount;
	}

	public void setTotalSoldAmount(float totalSoldAmount) {
		this.totalSoldAmount = totalSoldAmount;
	}

	@Column(nullable=false)
	public float getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(float stockLevel) {
		this.stockLevel = stockLevel;
	}

	@Column(nullable=false)
	public float getMinSoldQunatity() {
		return minSoldQunatity;
	}

	public void setMinSoldQunatity(float minSoldQunatity) {
		this.minSoldQunatity = minSoldQunatity;
	}

	@ManyToOne(cascade=CascadeType.REFRESH ,optional=false) 
	@JoinColumn(name = "warehouseId")
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Column(length=1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	@Column(nullable=false)
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	@Lob
	public String getContentDetail() {
		return contentDetail;
	}

	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}
	
}
