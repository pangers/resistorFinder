package com.pangers.resistorfinderviewpage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class resistorViewHolder {

	ImageView fourimageview1 = null;
	ImageView fourimageview2 = null;
	ImageView fourimageview3 = null;
	ImageView fourimageview4 = null;
	ImageView fiveimageview1 = null;
	ImageView fiveimageview2 = null;
	ImageView fiveimageview3 = null;
	ImageView fiveimageview4 = null;
	ImageView fiveimageview5 = null;
	TextView fourtextview = null;
	TextView fivetextview = null;

	resistorViewHolder(View row) {
		this.fourimageview1 = (ImageView) row.findViewById(R.id.fourresultcolour1);
		this.fourimageview2 = (ImageView) row.findViewById(R.id.fourresultcolour2);
		this.fourimageview3 = (ImageView) row.findViewById(R.id.fourresultcolour3);
		this.fourimageview4 = (ImageView) row.findViewById(R.id.fourresultcolour4);
		this.fiveimageview1 = (ImageView) row.findViewById(R.id.fiveresultcolour1);
		this.fiveimageview2 = (ImageView) row.findViewById(R.id.fiveresultcolour2);
		this.fiveimageview3 = (ImageView) row.findViewById(R.id.fiveresultcolour3);
		this.fiveimageview4 = (ImageView) row.findViewById(R.id.fiveresultcolour4);
		this.fiveimageview5 = (ImageView) row.findViewById(R.id.fiveresultcolour5);
		this.fourtextview = (TextView) row.findViewById(R.id.fourresvalue);
		this.fivetextview = (TextView) row.findViewById(R.id.fiveresvalue);
	}

}
