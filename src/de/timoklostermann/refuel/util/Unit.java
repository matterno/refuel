package de.timoklostermann.refuel.util;

public class Unit {

	private int key;
	
	private String value;

	public Unit(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
