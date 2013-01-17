package it.polimi.swimv2.enums;

public enum FeedbackValue {
	NEGATIVE(0), NEUTRAL(1), POSITIVE(2);
	
	private int value;
	
	private FeedbackValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static FeedbackValue stringToValue(String value) {
		try {
			int v = Integer.parseInt(value);
			if(v == 0) {
				return NEGATIVE;
			} else if(v == 2) {
				return POSITIVE;
			}
			return NEUTRAL;
		} catch(NumberFormatException e) {
			return NEUTRAL;
		}
	}
}
