package de.timoklostermann.refuel.adapter;

public class Filling {
	private String date;
	private String consupmtion;
	private String price;
	private String quantitiy;
	
	public Filling(String date, String consumption, String price, String quantity) {
		this.date = date;
		this.consupmtion = consumption;
		this.price = price;
		this.quantitiy = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getConsupmtion() {
		return consupmtion;
	}

	public void setConsupmtion(String consupmtion) {
		this.consupmtion = consupmtion;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantitiy() {
		return quantitiy;
	}

	public void setQuantitiy(String quantitiy) {
		this.quantitiy = quantitiy;
	}
}
