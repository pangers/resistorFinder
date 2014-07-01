package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.widget.TextView;

public class TextDisplayer {

	public void lastSelection(TextView textview, int listNumber, int position) {
		textview.setText("Last selection from List " + listNumber
				+ " at position: " + position);
	}

	public void bandRowValues(TextView textview, int[] bandRowNumber) {
		textview.append("\nBand One: " + bandRowNumber[0]);
		textview.append("\nBand Two: " + bandRowNumber[1]);
		textview.append("\nBand Three: " + bandRowNumber[2]);
		textview.append("\nBand Four: " + bandRowNumber[3]);
		if (bandRowNumber.length == 5) {
			textview.append("\nBand Five: " + bandRowNumber[4]);
		}
	}

	public void unselectedBands(TextView textview,
			ArrayList<Integer> uncheckedBands) {
		if (uncheckedBands.size() == 0) {
			textview.append("\nAll bands chosen");
		} else if (uncheckedBands.size() == 1) {
			textview.append("\nPlease select band "
					+ (uncheckedBands.get(0) + 1));
		} else if (uncheckedBands.size() > 1) {
			textview.append("\nPlease select bands ");
			for (int i = 0; i < uncheckedBands.size(); i++) {
				textview.append("" + (uncheckedBands.get(i) + 1));
				if (i != uncheckedBands.size() - 1) {
					textview.append(", ");
				}
			}
		}
	}

	public void resistanceDisplay(TextView textview, BigDecimal[] results) {
//		if (results[0] >= 10000000) {
//			textview.append("\n\nResistance: " + (results[0] / 1000000)
//					+ "M ohms");
//		} else if (results[0] >= 1000000) {
//			textview.append("\n\nResistance: " + (results[0] / 1000000)
//					+ "M ohms");
//		} else if (results[0] >= 100000) {
//			textview.append("\n\nResistance: " + (results[0] / 1000) + "K ohms");
//		} else if (results[0] >= 10000) {
//			textview.append("\n\nResistance: " + results[0] / 1000 + "K ohms");
//		} else if (results[0] >= 1000) {
//			textview.append("\n\nResistance: " + results[0] / 1000 + "K ohms");
//		} else if (results[0] >= 100) {
//			textview.append("\n\nResistance: " + results[0] + " ohms");
//		} else if (results[0] >= 10) {
//			textview.append("\n\nResistance: " + results[0] + " ohms");
//		} else if (results[0] >= 1) {
//			BigDecimal test = BigDecimal.valueOf(results[0]);
//			textview.append("\n\nResistance: " + results[0] + " ohms");
//		} else if (results[0] >= 0.1) {
//			textview.append("\n\nResistance: " + results[0] + " ohms");
//		} else if (results[0] >= 0.01) {
//			textview.append("\n\nResistance: " + results[0] + " ohms");
//		} else if (results[0] == 0) {
//			textview.append("\n\nResistance: 0 ohms");
//		}
		BigDecimal tenMill = new BigDecimal("10000000");
		BigDecimal Mill = new BigDecimal("1000000");
		BigDecimal hundThou = new BigDecimal("10000");
		BigDecimal tenThou = new BigDecimal("10000");
		BigDecimal Thou = new BigDecimal("1000");
		BigDecimal Hund = new BigDecimal("100");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal one = new BigDecimal("1");
		BigDecimal pointOne = new BigDecimal("0.1");
		BigDecimal pointZeroOne = new BigDecimal("0.01");
		BigDecimal zero = new BigDecimal("0");
		if (results[0].compareTo(tenMill) >= 0) {
			textview.append("\n\nResistance: " + (results[0].divide(Mill))
					+ "M ohms");
		} else if (results[0].compareTo(Mill) >= 0) {
			textview.append("\n\nResistance: " + (results[0].divide(Mill))
					+ "M ohms");
		} else if (results[0].compareTo(hundThou) >= 0) {
			textview.append("\n\nResistance: " + (results[0].divide(Thou)) + "K ohms");
		} else if (results[0].compareTo(tenThou) >= 0) {
			textview.append("\n\nResistance: " + (results[0].divide(Thou)) + "K ohms");
		} else if (results[0].compareTo(Thou) >= 0) {
			textview.append("\n\nResistance: " + (results[0].divide(Thou)) + "K ohms");
		} else if (results[0].compareTo(Hund) >= 0) {
			textview.append("\n\nResistance: " + results[0] + " ohms");
		} else if (results[0].compareTo(ten) >= 0) {
			textview.append("\n\nResistance: " + results[0] + " ohms");
		} else if (results[0].compareTo(one) >= 0) {
			textview.append("\n\nResistance: " + results[0] + " ohms");
		} else if (results[0].compareTo(pointOne) >= 0) {
			textview.append("\n\nResistance: " + results[0] + " ohms");
		} else if (results[0].compareTo(pointZeroOne) >= 0) {
			textview.append("\n\nResistance: " + results[0] + " ohms");
		} else if (results[0].compareTo(zero) == 0) {
			textview.append("\n\nResistance: 0 ohms");
		}
		textview.append("\nTolerance: \u00B1" + results[1] + "%");
	}
}
