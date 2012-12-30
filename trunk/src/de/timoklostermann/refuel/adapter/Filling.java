package de.timoklostermann.refuel.adapter;

import java.util.Date;

public class Filling implements Comparable<Filling> {
	private Date date;
	private String consupmtion;
	private String price;
	private String quantitiy;
	
	public Filling(Date date, String consumption, String price, String quantity) {
		this.date = date;
		this.consupmtion = consumption;
		this.price = price;
		this.quantitiy = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	@Override
	public int compareTo(Filling another) {
		if(this.getDate().after(another.getDate())) {
			return -1;
		} else if(this.getDate().before(another.getDate())) {
			return 1;
		} else {
			return 0;
		}
	}
}
