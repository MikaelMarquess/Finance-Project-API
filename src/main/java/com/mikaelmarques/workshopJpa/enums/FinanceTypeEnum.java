package com.mikaelmarques.workshopJpa.enums;

public enum FinanceTypeEnum {
	
	REVENUE(1),
	EXPENSE(2);
	
	
	private int code;

	private FinanceTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static FinanceTypeEnum valueOf(int code) {
		for (FinanceTypeEnum value : FinanceTypeEnum.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Dispense code");
	}
}
