package com.pangers.resistorfinderviewpage;

public class resistorData {

	private final int unselected = -17;
	private int dig1 = unselected;
	private int dig2 = unselected;
	public int dig3 = unselected;
	private int multiplier = unselected;
	private int tolerance = unselected;

	public resistorData(int dig1, int dig2, int multiplier, int tolerance) {
		this.dig1 = dig1;
		this.dig2 = dig2;
		this.multiplier = multiplier;
		this.tolerance = tolerance;
	}
	public resistorData(int dig1, int dig2, int dig3, int multiplier, int tolerance) {
		this(dig1, dig2, multiplier, tolerance);
		this.dig3 = dig3;
	}

}
