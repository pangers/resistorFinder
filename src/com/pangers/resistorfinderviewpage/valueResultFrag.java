package com.pangers.resistorfinderviewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class valueResultFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		View result = inflater.inflate(R.layout.byvalueresultfrag, container,
				false);

		return result;
	}
}
