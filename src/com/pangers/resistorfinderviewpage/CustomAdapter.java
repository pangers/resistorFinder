package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomAdapter extends ArrayAdapter<Model> {

	static final String TAG = "FourBandFrag";
	private final Context context;
	private final ArrayList<Model> modelsArrayList;
	private int selectedIndex = -1;

	public CustomAdapter(Context context, ArrayList<Model> modelsArrayList) {
		super(context, R.layout.listitem, modelsArrayList);

		this.context = context;
		this.modelsArrayList = modelsArrayList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Create Layout inflater - inflates xml and gives us a view
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// Get rowView from inflater
		View rowView = null;

		// ViewHolder to recycles inner views
		ViewHolder holder;

		// if data is a list item
		if (!modelsArrayList.get(position).isHeader()) {
			if (convertView == null) {
				rowView = inflater.inflate(R.layout.listitem, parent, false);
				holder = new ViewHolder(rowView);
				rowView.setTag(holder);
				
			} else {
				rowView = convertView;
				holder = (ViewHolder) convertView.getTag();
			}

			// Check the correct radio button when person selects a listview
			// item
			if (selectedIndex == position) {
				holder.radiobutton.setChecked(true);
				
			} else {
				holder.radiobutton.setChecked(false);
				
			}

			// Fill the rowView with data
			holder.imageview.setImageResource(modelsArrayList.get(position)
					.getIcon());
			holder.titleview.setText(modelsArrayList.get(position).getTitle());

			// if data is a list heading
		} else {
			// rowView = inflater.inflate(R.layout.listheader, parent, false);
			// TextView titleview = (TextView)
			// rowView.findViewById(R.id.header);
			// titleview.setText(modelsArrayList.get(position).getTitle());
		}

		return rowView;
	}

	public void setSelectedIndex(int index) {
		selectedIndex = index;
	}

}
