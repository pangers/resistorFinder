package com.pangers.resistorfinderviewpage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class fourResistorViewHolder {

	ImageView fourimageview1 = null;
	ImageView fourimageview2 = null;
	ImageView fourimageview3 = null;
	ImageView fourimageview4 = null;
	TextView fourtextview = null;

	fourResistorViewHolder(View row) {
		this.fourimageview1 = (ImageView) row
				.findViewById(R.id.fourresultcolour1);
		this.fourimageview2 = (ImageView) row
				.findViewById(R.id.fourresultcolour2);
		this.fourimageview3 = (ImageView) row
				.findViewById(R.id.fourresultcolour3);
		this.fourimageview4 = (ImageView) row
				.findViewById(R.id.fourresultcolour4);
		this.fourtextview = (TextView) row.findViewById(R.id.fourresvalue);

	}

}
