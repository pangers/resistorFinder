package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

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
			BigDecimal bandZero = new BigDecimal(
					Integer.toString(bandRowNumber[0]));
			BigDecimal bandOne = new BigDecimal(
					Integer.toString(bandRowNumber[1]));

			// calculate resistance
			buffer1 = (bandZero.multiply(ten)).add(bandOne);
			if (bandRowNumber[2] == -1) {
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
			BigDecimal bandZero = new BigDecimal(
					Integer.toString(bandRowNumber[0]));
			BigDecimal bandOne = new BigDecimal(
					Integer.toString(bandRowNumber[1]));
			BigDecimal bandTwo = new BigDecimal(
					Integer.toString(bandRowNumber[2]));

			// calculate resistance
			buffer1 = ((bandZero.multiply(hundred)).add(bandOne.multiply(ten)))
					.add(bandTwo);
			if (bandRowNumber[3] == -1) {
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

	public ArrayList<resistorData> findBandColours(BigDecimal data,
			int toleranceSelection) {
		ArrayList<resistorData> resistors = null;
		resistors = find4BandColours(data, resistors, toleranceSelection);
		resistors = find5BandColours(data, resistors, toleranceSelection);
		return resistors;
	}

	private ArrayList<resistorData> find4BandColours(BigDecimal data,
			ArrayList<resistorData> resistors, int toleranceSelection) {
		BigDecimal hundMill = new BigDecimal("100000000");
		BigDecimal tenMill = new BigDecimal("10000000");
		BigDecimal Mill = new BigDecimal("1000000");
		BigDecimal hundThou = new BigDecimal("100000");
		BigDecimal tenThou = new BigDecimal("10000");
		BigDecimal thou = new BigDecimal("1000");
		BigDecimal hund = new BigDecimal("100");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal one = new BigDecimal("1");
		BigDecimal tenth = one.divide(ten);
		BigDecimal hundredth = tenth.divide(ten);
		
	
		

		if (data.compareTo(hundMill) >= 0) {
			resistors = fourBandCalculator(resistors, data, tenMill, toleranceSelection);
			return resistors;
		} else if (data.compareTo(tenMill) >= 0) {
			resistors = fourBandCalculator(resistors, data, Mill, toleranceSelection);
			return resistors;
		} else if (data.compareTo(Mill) >= 0) {
			resistors = fourBandCalculator(resistors, data, hundThou, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hundThou) >= 0) {
			resistors = fourBandCalculator(resistors, data, tenThou, toleranceSelection);
			return resistors;
		} else if (data.compareTo(tenThou) >= 0) {
			resistors = fourBandCalculator(resistors, data, thou, toleranceSelection);
			return resistors;
		} else if (data.compareTo(thou) >= 0) {
			resistors = fourBandCalculator(resistors, data, hund, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hund) >= 0) {
			resistors = fourBandCalculator(resistors, data, ten, toleranceSelection);
			return resistors;
		} else if (data.compareTo(ten) >= 0) {
			resistors = fourBandCalculator(resistors, data, one, toleranceSelection);
			return resistors;
		} else if (data.compareTo(one) >= 0) {
			resistors = fourBandCalculator(resistors, data, tenth, toleranceSelection);
			return resistors;
		} else if (data.compareTo(tenth) >= 0) {
			resistors = fourBandCalculator(resistors, data, hundredth, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hundredth) >= 0){
			resistors = fourBandCalculator(resistors, data, hundredth, toleranceSelection);
			return resistors;
		} else {
			return resistors;
		}
	}

	private ArrayList<resistorData> fourBandCalculator(
			ArrayList<resistorData> resistors, BigDecimal data,
			BigDecimal multiplier, int toleranceSelection) {
		BigDecimal dig1 = null;
		BigDecimal dig2 = null;
		BigDecimal temp = null;
		
		BigDecimal ninetyNineFiveMill = new BigDecimal("99500000");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal pointZeroNineFive = new BigDecimal("0.095");
		BigDecimal zero = new BigDecimal("0");
		
		temp = data;
		data = (data.divide(multiplier)).setScale(0, BigDecimal.ROUND_HALF_UP);
		dig1 = (data.divide(ten)).setScale(0, BigDecimal.ROUND_DOWN);
		dig2 = data.subtract(dig1.multiply(ten));
		resistors.add(new resistorData(dig1.intValue(), dig2.intValue(),
				multiplier.intValue(), toleranceSelection));
		if ((temp.compareTo(ninetyNineFiveMill) < 0) && (temp.compareTo(pointZeroNineFive) >= 0)) {
			if (dig2.compareTo(zero) == 0) {
				resistors
						.add(new resistorData(0, dig1.intValue(),
								(multiplier.multiply(ten)).intValue(), toleranceSelection));
			}
		}
		return resistors;
	}

	private ArrayList<resistorData> find5BandColours(BigDecimal data,
			ArrayList<resistorData> resistors, int toleranceSelection) {
		BigDecimal bill = new BigDecimal("1000000000");
		BigDecimal hundMill = new BigDecimal("100000000");
		BigDecimal tenMill = new BigDecimal("10000000");
		BigDecimal Mill = new BigDecimal("1000000");
		BigDecimal hundThou = new BigDecimal("100000");
		BigDecimal tenThou = new BigDecimal("10000");
		BigDecimal thou = new BigDecimal("1000");
		BigDecimal hund = new BigDecimal("100");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal one = new BigDecimal("1");
		BigDecimal tenth = one.divide(ten);
		BigDecimal hundredth = tenth.divide(ten);
		BigDecimal thousandth = hundredth.divide(ten);
		
		if (data.compareTo(bill) >= 0) {
			resistors = fiveBandCalculator(resistors, data, tenMill, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hundMill) >= 0) {
			resistors = fiveBandCalculator(resistors, data, Mill, toleranceSelection);
			return resistors;
		} else if (data.compareTo(tenMill) >= 0) {
			resistors = fiveBandCalculator(resistors, data, hundThou, toleranceSelection);
			return resistors;
		} else if (data.compareTo(Mill) >= 0) {
			resistors = fiveBandCalculator(resistors, data, tenThou, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hundThou) >= 0) {
			resistors = fiveBandCalculator(resistors, data, thou, toleranceSelection);
			return resistors;
		} else if (data.compareTo(tenThou) >= 0) {
			resistors = fiveBandCalculator(resistors, data, hund, toleranceSelection);
			return resistors;
		} else if (data.compareTo(thou) >= 0) {
			resistors = fiveBandCalculator(resistors, data, ten, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hund) >= 0) {
			resistors = fiveBandCalculator(resistors, data, one, toleranceSelection);
			return resistors;
		} else if (data.compareTo(ten) >= 0) {
			resistors = fiveBandCalculator(resistors, data, tenth, toleranceSelection);
			return resistors;
		} else if (data.compareTo(one) >= 0) {
			resistors = fiveBandCalculator(resistors, data, hundredth, toleranceSelection);
			return resistors;
		} else if (data.compareTo(tenth) >= 0) {
			resistors = fiveBandCalculator(resistors, data, thousandth, toleranceSelection);
			return resistors;
		} else if (data.compareTo(hundredth) >= 0) {
			resistors = fiveBandCalculator(resistors, data, thousandth, toleranceSelection);
			return resistors;
		} else {
			return resistors;
		}
	}
	
	private ArrayList<resistorData> fiveBandCalculator(
			ArrayList<resistorData> resistors, BigDecimal data,
			BigDecimal multiplier, int toleranceSelection) {
		BigDecimal dig1 = null;
		BigDecimal dig2 = null;
		BigDecimal dig3 = null;
		BigDecimal temp = null;
		
		BigDecimal nineHundninetyNineFiveMill = new BigDecimal("999500000");
		BigDecimal ninetyNineFiveMill = new BigDecimal("99500000");
		BigDecimal hund = new BigDecimal("100");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal pointZeroNineNineFive = new BigDecimal("0.0995");
		BigDecimal pointZeroZeroNineFive = new BigDecimal("0.0095");
		BigDecimal zero = new BigDecimal("0");
		
		temp = data;
		data = (data.divide(multiplier)).setScale(0, BigDecimal.ROUND_HALF_UP);
		dig1 = (data.divide(hund)).setScale(0, BigDecimal.ROUND_DOWN);
		dig2 = ((data.subtract(dig1.multiply(hund))).divide(ten)).setScale(0, BigDecimal.ROUND_DOWN);
		dig3 = data.subtract((dig1.multiply(hund)).add(dig2.multiply(ten)));
		resistors.add(new resistorData(dig1.intValue(), dig2.intValue(), dig3.intValue(),
				multiplier.intValue(), toleranceSelection));
		if ((temp.compareTo(nineHundninetyNineFiveMill) < 0) && (temp.compareTo(pointZeroNineNineFive) >= 0)) {
			if (dig3.compareTo(zero) == 0) {
				resistors
						.add(new resistorData(0, dig1.intValue(), dig2.intValue(),
								(multiplier.multiply(ten)).intValue(), toleranceSelection));
			}
			if ((temp.compareTo(ninetyNineFiveMill) < 0) && (temp.compareTo(pointZeroZeroNineFive) >= 0)) {
				if(dig2.compareTo(zero) == 0) {
					resistors
					.add(new resistorData(0, 0, dig1.intValue(),
							(multiplier.multiply(hund)).intValue(), toleranceSelection));
				}
			}
		}
		return resistors;
	}

}
