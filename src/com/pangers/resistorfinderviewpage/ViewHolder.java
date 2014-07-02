package com.pangers.resistorfinderviewpage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

class ViewHolder {
	ImageView imageview = null;
	TextView titleview = null;
	RadioButton radiobutton = null;

	ViewHolder(View row) {
		this.imageview = (ImageView) row.findViewById(R.id.bandcolour);
		this.titleview = (TextView) row.findViewById(R.id.colourvalue);
		this.radiobutton = (RadioButton) row.findViewById(R.id.radioButton);
	}
}
