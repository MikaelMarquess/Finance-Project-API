package com.mikaelmarques.workshopJpa.enums;

public enum RevenueEnum {
	
	SALARY(1),
	EXTRA(2),
	INVESTMENTS(3);
	
	private int code;

	private RevenueEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RevenueEnum valueOf(int code) {
		for (RevenueEnum value : RevenueEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Dispense code");
	}
	
}
