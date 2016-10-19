/**
 * 
 */
package com.smartchemical.frame.search;

/**
 * @author Jephy
 *
 */
public class FilterItem {
	
	private String originalFilter;
	
	private String type;
	
	private String value;

	public String getOriginalFilter() {
		return originalFilter;
	}

	public void setOriginalFilter(String originalFilter) {
		this.originalFilter = originalFilter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public FilterItem(String filter){
		originalFilter = filter;
		String[] entry = filter.split(":");
		if (entry[0].equals("company")){
			type = "公司";
		}
		else if (entry[0].equals("brandName")){
			type = "品牌";
		}
		else if (entry[0].equals("merchantcompany")){
			type = "供应商";
		}
		else if (entry[0].equals("productNo")){
			type = "产品型号";
		}
		else if (entry[0].equals("region")){
			type = "区域";
		}
		else if (entry[0].equals("productCategory")){
			type = "产品类型";
		}
		else{
			type = entry[0];
		}
		value = entry[1];
	}
}
