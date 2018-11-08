package com.tedu.jt.web.backend;
/**
 * 商品信息实体类（javaBean）
 * 属性私有化，方法公有化，提供无参构造方法
 */
public class Product {
	private int id;
	private String name;
	private String category;
	private double price;
	private int pnum;
	private String description;

	public Product() {
		super();
	}

	public Product(int id, String name, String category, double price, int pnum, String description) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.pnum = pnum;
		this.description = description;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", pnum=" + pnum
				+ ", description=" + description + "]";
	}
	
}
