package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class resistorAdapter extends ArrayAdapter<resistorData> {

	Context context;
	ArrayList<resistorData> resistors = new ArrayList<resistorData>();
	int counter = 1;

	private final int unselected = -17;
	final static String TAG = "resistorAdapter";

	public resistorAdapter(Context context, ArrayList<resistorData> resistors) {
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
		Log.d(TAG, "getView called this many times: " + counter);
		counter = counter + 1;
		Log.d(TAG, "resistors.size(): " + resistors.size());
		Log.d(TAG, "Inflating position: " + position);
		// inflating 4 band resistors
		if (resistors.get(position).dig3 == unselected) {
			// if (getItemViewType(position) == 0) {
//			Log.d(TAG, "which is a 4 band");
			TextDisplayer textDisplayer = new TextDisplayer(context);
			fourResistorViewHolder holder;
			//check the convertview is null but also if the convertview given by the system is of the right type
			if (convertView == null
					|| !(convertView.getTag() instanceof fourResistorViewHolder)) {
//				Log.d(TAG, "fails in convertview==null");
				rowView = inflater.inflate(R.layout.bycolourlistitem4, parent,
						false);
				holder = new fourResistorViewHolder(rowView);
				rowView.setTag(holder);
			} else {
//				Log.d(TAG, "fails in else condition");
				rowView = convertView;
				holder = (fourResistorViewHolder) convertView.getTag();
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
			fiveResistorViewHolder holder;
//			Log.d(TAG, "which is a 5 band");
			if (convertView == null
					|| !(convertView.getTag() instanceof fiveResistorViewHolder)) {
//				Log.d(TAG, "fails in convertview==null");
				rowView = inflater.inflate(R.layout.bycolourlistitem5, parent,
						false);
				holder = new fiveResistorViewHolder(rowView);
				rowView.setTag(holder);
			} else {
//				Log.d(TAG, "fails in else condition");
				rowView = convertView;
//				Log.d(TAG, "still alive");
				holder = (fiveResistorViewHolder) convertView.getTag();
//				Log.d(TAG, "survived cast to fiveResisHolder");
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
