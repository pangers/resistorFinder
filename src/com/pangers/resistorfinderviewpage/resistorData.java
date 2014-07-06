package com.pangers.resistorfinderviewpage;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class resistorData implements Parcelable {

	private int unselected = -17;
	private int dig1 = unselected;
	private int dig2 = unselected;
	public int dig3 = unselected;
	private int multiplier = unselected;
	private int tolerance = unselected;

	final static String TAG = "resistorData";

	public resistorData(int dig1, int dig2, int multiplier, int tolerance) {
		this.dig1 = dig1;
		this.dig2 = dig2;
		this.multiplier = (int) Math.log10(multiplier);
		this.tolerance = tolerance;

	}

	public resistorData(int dig1, int dig2, int dig3, int multiplier,
			int tolerance) {
		this(dig1, dig2, multiplier, tolerance);
		this.dig3 = dig3;
	}

	public int getDig1() {
		return dig1;
	}

	public void setDig1(int dig1) {
		this.dig1 = dig1;
	}

	public int getDig2() {
		return dig2;
	}

	public void setDig2(int dig2) {
		this.dig2 = dig2;
	}

	public int getDig3() {
		return dig3;
	}

	public void setDig3(int dig3) {
		this.dig3 = dig3;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	// constructor to use when reconstructing object from parcel
	public resistorData(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	// read each instance variable into the parcel
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(unselected);
		dest.writeInt(dig1);
		dest.writeInt(dig2);
		dest.writeInt(dig3);
		dest.writeInt(multiplier);
		dest.writeInt(tolerance);
	}

	// called from constructor to create this object from parcel, in the same
	// order written to
	private void readFromParcel(Parcel in) {
		unselected = in.readInt();
		dig1 = in.readInt();
		dig2 = in.readInt();
		dig3 = in.readInt();
		multiplier = in.readInt();
		tolerance = in.readInt();
	}

	// Code required by Android
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public resistorData createFromParcel(Parcel in) {
			return new resistorData(in);
		}

		public resistorData[] newArray(int size) {
			return new resistorData[size];
		}
	};

}
