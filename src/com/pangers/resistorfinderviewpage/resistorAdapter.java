package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class resistorAdapter extends ArrayAdapter<resistorData> {

	Context context;
	ArrayList<resistorData> resistors = new ArrayList<resistorData>();

	private final int unselected = -17;

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

		// Get rowView from inflater
		View rowView = null;

		resistorViewHolder holder;

		if (resistors.get(position).dig3 == unselected) {
			if (convertView == null) {
				rowView = inflater.inflate(R.layout.bycolourlistitem4, parent,
						false);
				holder = new resistorViewHolder(rowView);
				rowView.setTag(holder);
			} else {
				rowView = convertView;
				holder = (resistorViewHolder) convertView.getTag();
			}
		}

		return rowView;
	}

}
