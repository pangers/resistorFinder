package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ResistorAdapter extends ArrayAdapter<ResistorData> {

	Context context;
	ArrayList<ResistorData> resistors = new ArrayList<ResistorData>();
	int counter = 1;

	private final int unselected = -17;
	//final static String TAG = "resistorAdapter";

	public ResistorAdapter(Context context, ArrayList<ResistorData> resistors) {
		super(context, R.layout.bycolourlistitem4, resistors);

		this.context = context;
		this.resistors = resistors;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Create Layout inflater - inflates xml and gives us a view
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// convertView = null;
		// Get rowView from inflater
		View rowView = null;

		counter = counter + 1;

		// inflating 4 band resistors
		if (resistors.get(position).dig3 == unselected) {

			TextDisplayer textDisplayer = new TextDisplayer(context);
			FourResistorViewHolder holder;
			// check the convertview is null but also if the convertview given
			// by the system is of the right type
			if (convertView == null
					|| !(convertView.getTag() instanceof FourResistorViewHolder)) {

				rowView = inflater.inflate(R.layout.bycolourlistitem4, parent,
						false);
				holder = new FourResistorViewHolder(rowView);
				rowView.setTag(holder);
			} else {

				rowView = convertView;
				holder = (FourResistorViewHolder) convertView.getTag();
			}
			holder.fourimageview1.setImageResource(getDigRectangle(resistors
					.get(position).getDig1()));
			holder.fourimageview2.setImageResource(getDigRectangle(resistors
					.get(position).getDig2()));
			holder.fourimageview3.setImageResource(getMultRectangle(resistors
					.get(position).getMultiplier() + 2));
			holder.fourimageview4.setImageResource(getTolRectangle(resistors
					.get(position).getTolerance()));
			int[] bandValues = { resistors.get(position).getDig1(),
					resistors.get(position).getDig2(),
					(resistors.get(position).getMultiplier()),
					resistors.get(position).getTolerance() };
			BigDecimal[] results = ((new ResistorCalculator())
					.calculate(bandValues));
			textDisplayer.resistanceDisplay(holder.fourtextview, results);
			// inflating 5 band resistors
		} else {
			TextDisplayer textDisplayer = new TextDisplayer(context);
			FiveResistorViewHolder holder;

			if (convertView == null
					|| !(convertView.getTag() instanceof FiveResistorViewHolder)) {
				rowView = inflater.inflate(R.layout.bycolourlistitem5, parent,
						false);
				holder = new FiveResistorViewHolder(rowView);
				rowView.setTag(holder);
			} else {
				rowView = convertView;
				holder = (FiveResistorViewHolder) convertView.getTag();
			}
			holder.fiveimageview1.setImageResource(getDigRectangle(resistors
					.get(position).getDig1()));
			holder.fiveimageview2.setImageResource(getDigRectangle(resistors
					.get(position).getDig2()));
			holder.fiveimageview3.setImageResource(getDigRectangle(resistors
					.get(position).getDig3()));
			holder.fiveimageview4.setImageResource(getMultRectangle(resistors
					.get(position).getMultiplier() + 2));
			holder.fiveimageview5.setImageResource(getTolRectangle(resistors
					.get(position).getTolerance()));
			int[] bandValues = { resistors.get(position).getDig1(),
					resistors.get(position).getDig2(),
					resistors.get(position).getDig3(),
					(resistors.get(position).getMultiplier()),
					resistors.get(position).getTolerance() };
			BigDecimal[] results = ((new ResistorCalculator())
					.calculate(bandValues));
			textDisplayer.resistanceDisplay(holder.fivetextview, results);
		}
		return rowView;
	}

	private int getDigRectangle(int number) {
		switch (number) {
		case 0:
			return R.drawable.rectangleblack;
		case 1:
			return R.drawable.rectanglebrown;
		case 2:
			return R.drawable.rectanglered;
		case 3:
			return R.drawable.rectangleorange;
		case 4:
			return R.drawable.rectangleyellow;
		case 5:
			return R.drawable.rectanglegreen;
		case 6:
			return R.drawable.rectangleblue;
		case 7:
			return R.drawable.rectangleviolet;
		case 8:
			return R.drawable.rectanglegrey;
		case 9:
			return R.drawable.rectanglewhite;
		default:
			return 0;

		}
	}

	private int getMultRectangle(int number) {
		switch (number) {
		case 0:
			return R.drawable.rectanglesilver;
		case 1:
			return R.drawable.rectanglegold;
		case 2:
			return R.drawable.rectangleblack;
		case 3:
			return R.drawable.rectanglebrown;
		case 4:
			return R.drawable.rectanglered;
		case 5:
			return R.drawable.rectangleorange;
		case 6:
			return R.drawable.rectangleyellow;
		case 7:
			return R.drawable.rectanglegreen;
		case 8:
			return R.drawable.rectangleblue;
		case 9:
			return R.drawable.rectangleviolet;
		default:
			return 0;

		}
	}

	private int getTolRectangle(int number) {
		switch (number) {
		case 0:
			return R.drawable.rectanglesilver;
		case 1:
			return R.drawable.rectanglegold;
		case 2:
			return R.drawable.rectanglebrown;
		case 3:
			return R.drawable.rectanglered;
		case 4:
			return R.drawable.rectanglegreen;
		case 5:
			return R.drawable.rectangleblue;
		case 6:
			return R.drawable.rectangleviolet;
		default:
			return 0;

		}
	}

}
