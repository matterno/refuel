package de.timoklostermann.refuel.adapter;

import java.util.Date;

public class Filling implements Comparable<Filling> {
	private Date date;
	private double distance;
	private double price;
	private double quantitiy;
	private Boolean filledToTop;
	private double consumptionToPrevious = 0;
	
	public Filling(Date date, double distance, double price, double quantity, Boolean filledToTop) {
		this.date = date;
		this.distance = distance;
		this.price = price;
		this.quantitiy = quantity;
		this.filledToTop = filledToTop;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantitiy() {
		return quantitiy;
	}

	public void setQuantitiy(double quantitiy) {
		this.quantitiy = quantitiy;
	}

	public Boolean getFilledToTop() {
		return filledToTop;
	}

	public void setFilledToTop(Boolean filledToTop) {
		this.filledToTop = filledToTop;
	}

	public double getConsumptionToPrevious() {
		return consumptionToPrevious;
	}

	public void setConsumptionToPrevious(double consumptionToPrevious) {
		this.consumptionToPrevious = consumptionToPrevious;
	}

	/**
	 * Compares another Filling to this one by date.
	 * The newest date is the last position.
	 */
	@Override
	public int compareTo(Filling another) {
		if(this.getDate().after(another.getDate())) {
			return 1;
		} else if(this.getDate().before(another.getDate())) {
			return -1;
		} else {
			return 0;
		}
	}
}
