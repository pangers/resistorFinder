package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;

public class ResistorCalculator {

	final private double[] tolerance = { 10, 5, 1, 2, 0.5, 0.25, 0.1 };

	public BigDecimal[] calculate(int[] bandRowNumber) {
		// results[0] = resistance, results[1] = tolerance
		BigDecimal[] results = new BigDecimal[2];
		BigDecimal buffer1;
		BigDecimal buffer2;
		BigDecimal ten = new BigDecimal("10");
		BigDecimal one = new BigDecimal("1");
		BigDecimal hundred = new BigDecimal("100");

		// four band resistor
		if (bandRowNumber.length == 4) {
			BigDecimal bandZero = new BigDecimal(Integer.toString(bandRowNumber[0]));
			BigDecimal bandOne = new BigDecimal(Integer.toString(bandRowNumber[1]));
		
			// calculate resistance	
			buffer1 = (bandZero.multiply(ten)).add(bandOne);
			if(bandRowNumber[2] == -1) {
				buffer2 = one.divide(ten);
			} else if (bandRowNumber[2] == -2) {
				buffer2 = one.divide(hundred);
			} else {
				buffer2 = ten.pow(bandRowNumber[2]);
			}
			results[0] = buffer1.multiply(buffer2);
			// find tolerance
			results[1] = BigDecimal.valueOf(tolerance[bandRowNumber[3]]);

			// five band resistor
		} else {
			BigDecimal bandZero = new BigDecimal(Integer.toString(bandRowNumber[0]));
			BigDecimal bandOne = new BigDecimal(Integer.toString(bandRowNumber[1]));
			BigDecimal bandTwo = new BigDecimal(Integer.toString(bandRowNumber[2]));

			// calculate resistance
			buffer1 = ((bandZero.multiply(hundred)).add(bandOne.multiply(ten))).add(bandTwo);
			if(bandRowNumber[3] == -1) {
				buffer2 = one.divide(ten);
			} else if (bandRowNumber[3] == -2) {
				buffer2 = one.divide(hundred);
			} else {
				buffer2 = ten.pow(bandRowNumber[3]);
			}
	
			results[0] = buffer1.multiply(buffer2);
			// find tolerance
			results[1] = BigDecimal.valueOf(tolerance[bandRowNumber[4]]);
		}

		return results;
	}

}
