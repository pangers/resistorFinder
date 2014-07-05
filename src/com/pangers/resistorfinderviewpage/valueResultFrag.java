package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class valueResultFrag extends Fragment {

	final static String TAG = "valueEntryFrag";
	
	private ListView resultListView = null;
	private ArrayList<resistorData> resistors = new ArrayList<resistorData>();
	private resistorAdapter adapter = null;
	
	private int unselected = -17;
	private int[] rowValues = {unselected, unselected, unselected, unselected, unselected};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		View result = inflater.inflate(R.layout.byvalueresultfrag, container,
				false);

		return result;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		resultListView = (ListView) getActivity().findViewById(R.id.resultsbyvaluelist);
		adapter = new resistorAdapter(getActivity(), resistors);
		resultListView.setAdapter(adapter);
	}
	
	public void updateData(ArrayList<resistorData> resistors) {
		this.resistors = resistors;
		((resistorAdapter) resultListView.getAdapter()).notifyDataSetChanged();
	}
}
