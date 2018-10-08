package com.example.pc_.mycode.model.bean;

import java.util.ArrayList;
import java.util.List;

public class GoodShopBean  extends BaseRequest{


	
	private int total;
	
	private List<SubGoodShopBean>  shopBeans=new ArrayList<>();
	public class SubGoodShopBean{
		private int id;
		private String name;
		private String total;
		private String allSales;
		private String type;
		private String url;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		public String getAllSales() {
			return allSales;
		}
		public void setAllSales(String allSales) {
			this.allSales = allSales;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<SubGoodShopBean> getShopBenas() {
		return shopBeans;
	}
	public void setShopBenas(List<SubGoodShopBean> shopBenas) {
		this.shopBeans = shopBenas;
	}
}
