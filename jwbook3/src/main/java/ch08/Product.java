
package ch08;

public class Product {
	private String id, name, maker, date;
	private int price;

	// 데이터 생성을 쉽게 하기위한 생성자입니다.
	public Product(String id, String name, String maker, String date, int price) {
		this.id = id;
		this.name = name;
		this.maker = maker;
		this.date = date;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
