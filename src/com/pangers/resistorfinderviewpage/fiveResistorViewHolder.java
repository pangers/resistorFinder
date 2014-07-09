package com.pangers.resistorfinderviewpage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FiveResistorViewHolder {

	ImageView fiveimageview1 = null;
	ImageView fiveimageview2 = null;
	ImageView fiveimageview3 = null;
	ImageView fiveimageview4 = null;
	ImageView fiveimageview5 = null;
	TextView fivetextview = null;

	FiveResistorViewHolder(View row) {
		this.fiveimageview1 = (ImageView) row
				.findViewById(R.id.fiveresultcolour1);
		this.fiveimageview2 = (ImageView) row
				.findViewById(R.id.fiveresultcolour2);
		this.fiveimageview3 = (ImageView) row
				.findViewById(R.id.fiveresultcolour3);
		this.fiveimageview4 = (ImageView) row
				.findViewById(R.id.fiveresultcolour4);
		this.fiveimageview5 = (ImageView) row
				.findViewById(R.id.fiveresultcolour5);
		this.fivetextview = (TextView) row.findViewById(R.id.fiveresvalue);
	}
}
