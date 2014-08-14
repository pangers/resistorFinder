package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.util.Log;

public class ResistorCalculator {

	//final static String TAG = "ResistorCalculator";
	final private double[] tolerance = { 10, 5, 1, 2, 0.5, 0.25, 0.1 };
	private static BigDecimal TEN = new BigDecimal(10);
	private static BigDecimal LOGTEN = new BigDecimal(Math.log(10));
	private static BigDecimal MAXSIMPLE = new BigDecimal(Double.MAX_VALUE);

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

	public ArrayList<ResistorData> findBandColours(BigDecimal data,
			int toleranceSelection) {
		ArrayList<ResistorData> resistors = new ArrayList<ResistorData>();
		resistors = find4BandColours(data, resistors, toleranceSelection);
		resistors = find5BandColours(data, resistors, toleranceSelection);
		return resistors;
	}

	private ArrayList<ResistorData> find4BandColours(BigDecimal data,
			ArrayList<ResistorData> resistors, int toleranceSelection) {
		BigDecimal maxLimit = new BigDecimal("990000000");
		BigDecimal hundMill = new BigDecimal("100000000");
		BigDecimal tenMill = new BigDecimal("10000000");
		BigDecimal Mill = new BigDecimal("1000000");
		BigDecimal hundThou = new BigDecimal("100000");
		BigDecimal tenThou = new BigDecimal("10000");
		BigDecimal thou = new BigDecimal("1000");
		BigDecimal hund = new BigDecimal("100");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal one = new BigDecimal("1");
		BigDecimal zero = new BigDecimal("0");
		BigDecimal tenth = one.divide(ten);
		BigDecimal hundredth = tenth.divide(ten);
		BigDecimal pointZeroNineFive = new BigDecimal("0.095");

		BigDecimal dataSigFig = toSignificantFigures(data, 2);
		if ((dataSigFig.compareTo(tenth) < 0)
				&& (dataSigFig.compareTo(pointZeroNineFive) > 0)) {
			dataSigFig = tenth;
		}
//		if (dataSigFig.compareTo(hundredth) < 0) {
//			dataSigFig = dataSigFig.setScale(2, BigDecimal.ROUND_HALF_UP);
//		}

		if ((dataSigFig.compareTo(maxLimit) <= 0)) {

			if ((dataSigFig.compareTo(hundMill) >= 0)) {
				resistors = fourBandCalculator(resistors, dataSigFig, tenMill,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(tenMill) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, Mill,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(Mill) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, hundThou,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(hundThou) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, tenThou,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(tenThou) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, thou,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(thou) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, hund,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(hund) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, ten,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(ten) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, one,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(one) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig, tenth,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(tenth) >= 0) {
				resistors = fourBandCalculator(resistors, dataSigFig,
						hundredth, toleranceSelection);
				return resistors;
//			} else if (dataSigFig.compareTo(hundredth) >= 0) {
//				resistors = fourBandSpecialCase(resistors, dataSigFig,
//						hundredth, toleranceSelection);
//				return resistors;
			}
		}
		return resistors;
	}

	private ArrayList<ResistorData> fourBandCalculator(
			ArrayList<ResistorData> resistors, BigDecimal data,
			BigDecimal multiplier, int toleranceSelection) {
		BigDecimal dig1 = null;
		BigDecimal dig2 = null;
		BigDecimal temp = null;

		BigDecimal ninetyMill = new BigDecimal("90000000");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal pointOne = new BigDecimal("0.1");
		BigDecimal zero = new BigDecimal("0");

		temp = data;
		// data = (data.divide(multiplier)).setScale(0,
		// BigDecimal.ROUND_HALF_UP);
		data = (data.divide(multiplier));
		dig1 = (data.divide(ten)).setScale(0, BigDecimal.ROUND_DOWN);
		dig2 = data.subtract(dig1.multiply(ten));

		resistors.add(new ResistorData(dig1.intValue(), dig2.intValue(),
				(log10(multiplier)).intValue(), toleranceSelection));
//		resistors.add(new resistorData(0, dig1.intValue(), dig2.intValue(),
//				(log10(multiplier)).intValue(), toleranceSelection));

//		if ((temp.compareTo(ninetyMill) <= 0)
//				&& (temp.compareTo(pointOne) >= 0)) {
//			if (dig2.compareTo(zero) == 0) {
//				resistors.add(new resistorData(0, dig1.intValue(),
//						(log10(multiplier.multiply(ten))).intValue(),
//						toleranceSelection));
//				resistors.add(new resistorData(0, 0, dig1.intValue(),
//						(log10(multiplier.multiply(ten))).intValue(),
//						toleranceSelection));
//			}
//		}
		return resistors;
	}

//	private ArrayList<resistorData> fourBandSpecialCase(
//			ArrayList<resistorData> resistors, BigDecimal data,
//			BigDecimal multiplier, int toleranceSelection) {
//		data = data.setScale(2, BigDecimal.ROUND_HALF_UP);
//		data = data.divide(multiplier);
//		switch (data.intValue()) {
//		case 9:
//			resistors.add(new resistorData(0, 9,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 9, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 8:
//			resistors.add(new resistorData(0, 8,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 8, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 7:
//			resistors.add(new resistorData(0, 7,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 7, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 6:
//			resistors.add(new resistorData(0, 6,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 6, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 5:
//			resistors.add(new resistorData(0, 5,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 5, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 4:
//			resistors.add(new resistorData(0, 4,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 4, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 3:
//			resistors.add(new resistorData(0, 3,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 3, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 2:
//			resistors.add(new resistorData(0, 2,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 2, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		case 1:
//			resistors.add(new resistorData(0, 1,
//					(log10(multiplier)).intValue(), toleranceSelection));
//			resistors.add(new resistorData(0, 0, 1, (log10(multiplier))
//					.intValue(), toleranceSelection));
//			break;
//		}
//		return resistors;
//	}

	private ArrayList<ResistorData> find5BandColours(BigDecimal data,
			ArrayList<ResistorData> resistors, int toleranceSelection) {
		BigDecimal maxLimit = new BigDecimal("9990000000");
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

		
		BigDecimal dataSigFig = toSignificantFigures(data, 3);
	
		if (dataSigFig.compareTo(one) < 0) {
			dataSigFig = dataSigFig.setScale(0, BigDecimal.ROUND_HALF_UP);
		}
		if (dataSigFig.compareTo(maxLimit) <= 0) {
			if (data.compareTo(bill) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, tenMill,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(hundMill) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, Mill,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(tenMill) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, hundThou,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(Mill) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, tenThou,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(hundThou) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, thou,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(tenThou) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, hund,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(thou) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, ten,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(hund) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, one,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(ten) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, tenth,
						toleranceSelection);
				return resistors;
			} else if (dataSigFig.compareTo(one) >= 0) {
				resistors = fiveBandCalculator(resistors, dataSigFig, hundredth,
						toleranceSelection);
				return resistors;
			}
		}
		return resistors;
	}

	private ArrayList<ResistorData> fiveBandCalculator(
			ArrayList<ResistorData> resistors, BigDecimal data,
			BigDecimal multiplier, int toleranceSelection) {
		BigDecimal dig1 = null;
		BigDecimal dig2 = null;
		BigDecimal dig3 = null;
		BigDecimal temp = null;

		BigDecimal nineHundNinetyMill = new BigDecimal("990000000");
		BigDecimal ninetyMill = new BigDecimal("90000000");
		BigDecimal hund = new BigDecimal("100");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal pointZeroNineNineFive = new BigDecimal("0.0995");
		BigDecimal pointZeroZeroNineFive = new BigDecimal("0.0095");
		BigDecimal zero = new BigDecimal("0");
		
		temp = data;
		data = (data.divide(multiplier));
		dig1 = (data.divide(hund)).setScale(0, BigDecimal.ROUND_DOWN);
		dig2 = ((data.subtract(dig1.multiply(hund))).divide(ten)).setScale(0,
				BigDecimal.ROUND_DOWN);
		dig3 = data.subtract((dig1.multiply(hund)).add(dig2.multiply(ten)));
		resistors
				.add(new ResistorData(dig1.intValue(), dig2.intValue(), dig3
						.intValue(), (log10(multiplier)).intValue(),
						toleranceSelection));

		// if (temp.compareTo(nineHundNinetyMill) <= 0) {
		// if (dig3.compareTo(zero) == 0) {
		// resistors.add(new resistorData(0, dig1.intValue(), dig2
		// .intValue(), (multiplier.multiply(ten)).intValue(),
		// toleranceSelection));
		// }
		// }
		// if (temp.compareTo(ninetyMill) <= 0) {
		// if ((dig3.compareTo(zero) == 0) && (dig2.compareTo(zero) == 0)) {
		// resistors.add(new resistorData(0, 0, dig1.intValue(),
		// (multiplier.multiply(hund)).intValue(),
		// toleranceSelection));
		// }
		// }
		// if ((temp.compareTo(nineHundNinetyMill) <= 0)
		// && (temp.compareTo(pointZeroNineNineFive) >= 0)) {
		// if (dig3.compareTo(zero) == 0) {
		// resistors.add(new resistorData(0, dig1.intValue(), dig2
		// .intValue(), (multiplier.multiply(ten)).intValue(),
		// toleranceSelection));
		// }
		// if ((temp.compareTo(ninetyNineFiveMill) < 0)
		// && (temp.compareTo(pointZeroZeroNineFive) >= 0)) {
		// if (dig2.compareTo(zero) == 0) {
		// resistors.add(new resistorData(0, 0, dig1.intValue(),
		// (multiplier.multiply(hund)).intValue(),
		// toleranceSelection));
		// }
		// }
		// }
		return resistors;
	}

	public BigDecimal toSignificantFigures(BigDecimal bd, int significantFigures) {
		String s = String.format("%." + significantFigures + "G", bd);
		BigDecimal result = new BigDecimal(s);
		return result;
	}

	public static BigDecimal log10(BigDecimal v) {
		if (v.compareTo(MAXSIMPLE) > 0) {
			return v.divide(TEN).add(LOGTEN);
		} else {
			return new BigDecimal(Math.log10(v.doubleValue()));
		}
	}

}
