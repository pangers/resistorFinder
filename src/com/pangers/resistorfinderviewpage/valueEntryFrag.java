package com.pangers.resistorfinderviewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class valueEntryFrag extends Fragment {

	final static String TAG = "valueEntryFrag";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		
		View result = inflater.inflate(R.layout.byvalueentryfrag, container,
				false);

		return result;
	}
}
