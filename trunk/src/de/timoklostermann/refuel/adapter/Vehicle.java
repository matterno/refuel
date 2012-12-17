package de.timoklostermann.refuel.adapter;

public class Vehicle {
	
	private long id;
	
	private String name;

	private int year;

	private String make;

	private String model;

	private int type;

	private int distanceUnit;

	private int quantityUnit;

	private int consumptionUnit;

	private String currency;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(int distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public int getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(int quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public int getConsumptionUnit() {
		return consumptionUnit;
	}

	public void setConsumptionUnit(int consumptionUnit) {
		this.consumptionUnit = consumptionUnit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
