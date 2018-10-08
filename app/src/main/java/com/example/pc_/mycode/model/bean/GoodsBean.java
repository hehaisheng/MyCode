package com.example.pc_.mycode.model.bean;

import java.util.ArrayList;
import java.util.List;

public class GoodsBean  extends BaseResponse{


	//返回给商家的商品json数据
	
	private int total;




	private List<SubGoodsBean>  subGoodsBeans=new ArrayList<>();
	
	public class SubGoodsBean{
		
		private int id;
		private String price;
		private String sales;
		private String goods_name;
		private String type;
		private String url;
		public String shopName;
		private  String mark;
		private String dan;




		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getSales() {
			return sales;
		}
		public void setSales(String sales) {
			this.sales = sales;
		}
		public String getGoods_name() {
			return goods_name;
		}
		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
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
		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public String getMark() {
			return mark;
		}

		public void setMark(String mark) {
			this.mark = mark;
		}
		public String getDan() {
			return dan;
		}

		public void setDan(String dan) {
			this.dan = dan;
		}


	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<SubGoodsBean> getSubGoodsBeans() {
		return subGoodsBeans;
	}

	public void setSubGoodsBeans(List<SubGoodsBean> subGoodsBeans) {
		this.subGoodsBeans = subGoodsBeans;
	}
}
