package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ValueResultFrag extends Fragment {

	//final static String TAG = "valueResultFrag";
	final static String RESISTOR_LIST = "resistorList";

	private ListView resultListView = null;
	private ArrayList<ResistorData> resistors = new ArrayList<ResistorData>();
	private ResistorAdapter adapter = null;

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
		resultListView = (ListView) getActivity().findViewById(
				R.id.resultsbyvaluelist);
		if(savedInstanceState != null) {
			resistors = savedInstanceState.getParcelableArrayList(RESISTOR_LIST);
		}
		updateData(resistors);
	}

	public void updateData(ArrayList<ResistorData> bufferList) {
//		ArrayList<resistorData> newData = new ArrayList<resistorData>();
//		newData = bufferList;
		//resistors = new ArrayList<resistorData>();
		resistors = bufferList;
		adapter = new ResistorAdapter(getActivity(), resistors);
		resultListView.setAdapter(adapter);
		((ResistorAdapter) resultListView.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList(RESISTOR_LIST, resistors);
		super.onSaveInstanceState(outState);
	}
}
