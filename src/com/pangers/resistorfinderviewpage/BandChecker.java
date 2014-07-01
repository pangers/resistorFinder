package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

public class BandChecker {

	public ArrayList<Integer> bandCheck(int[] bandsChecked, int unselected) {
		
		ArrayList<Integer> uncheckedBands = new ArrayList<Integer>();
		
		for (int i = 0; i < bandsChecked.length; i++) {
			if (bandsChecked[i] == unselected) {
				//store bands that have not yet been selected
				uncheckedBands.add(i);
			}
		}
//		if (bandsChecked.length == 5) {
//			for (int i = 0; i < bandsChecked.length; i++) {
//				if (bandsChecked[i] == unselected) {
//					//store bands that have not yet been selected
//					uncheckedBands.add(i);
//				}
//			}
//		} else if (bandsChecked.length == 4) {
//			for (int i = 0; i < bandsChecked.length; i++) {
//				if (bandsChecked[i] == unselected) {
//					//store bands that have not yet been selected
//					uncheckedBands.add(i);
//				}
//			}
//		}	
		return  uncheckedBands;
	}
}
