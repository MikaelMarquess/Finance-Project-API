package com.mikaelmarques.workshopJpa.enums;

public enum ExpensesEnum {

	FOOD(1), 
	HOUSING(2), 
	TRANSPORTATION(3), 
	LEISURE(4), 
	HEALTH(5);

	private int code;

	private ExpensesEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ExpensesEnum valueOf(int code) {
		for (ExpensesEnum value : ExpensesEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Dispense code");
	}
}
