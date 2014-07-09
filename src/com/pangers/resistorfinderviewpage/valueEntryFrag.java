package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ValueEntryFrag extends Fragment implements
		AdapterView.OnItemSelectedListener, OnClickListener {

	final static String TAG = "valueEntryFrag";
	final static String RESISTANCE = "resistance";
	final static String TOLERANCE_SELECTION = "toleranceSelection";
	final static String UNIT_SELECTION = "unitSelection";

	private Spinner toleranceSpinner = null;
	private Spinner unitSpinner = null;
	private ArrayAdapter<CharSequence> toleranceAdapter = null;
	private ArrayAdapter<CharSequence> unitAdapter = null;

	private int toleranceSelection;
	private double unitSelection;
	private double[] units = { 1, 1000, 1000000 };
	private EditText resistanceText;
	private Button calculateButton;
	final private double maxLimit = 9990000000.0;

	onResistanceEnteredListener listener;

	public interface onResistanceEnteredListener {
		public void onResistanceEntered(ArrayList<ResistorData> resistors);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (onResistanceEnteredListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement onResistanceEnteredListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);

		View result = inflater.inflate(R.layout.byvalueentryfrag, container,
				false);

		return result;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		toleranceSpinner = (Spinner) getActivity().findViewById(
				R.id.toleranceselect);
		// individual item(row) views
		toleranceAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.tolerancechoices, android.R.layout.simple_spinner_item);
		// view containing all the items(rows)
		toleranceAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		toleranceSpinner.setAdapter(toleranceAdapter);
		toleranceSpinner.setOnItemSelectedListener(this);

		unitSpinner = (Spinner) getActivity().findViewById(R.id.unitselect);
		unitAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.unitchoices, android.R.layout.simple_spinner_item);
		unitAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		unitSpinner.setAdapter(unitAdapter);
		unitSpinner.setOnItemSelectedListener(this);

		resistanceText = (EditText) getActivity().findViewById(
				R.id.resistancetext);
		calculateButton = (Button) getActivity().findViewById(R.id.calculate);
		calculateButton.setOnClickListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		switch (parent.getId()) {
		case R.id.toleranceselect:
			toleranceSelection = pos;
			break;
		case R.id.unitselect:
			unitSelection = units[pos];
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	@Override
	public void onClick(View v) {
		ArrayList<ResistorData> resistors = null;
		if (TextUtils.isEmpty(resistanceText.getText().toString().trim()) != true) {
			if ((Double.parseDouble(resistanceText.getText().toString()))
					* unitSelection <= maxLimit) {
				double data = (Double.parseDouble(resistanceText.getText()
						.toString())) * unitSelection;

				BigDecimal BDdata = BigDecimal.valueOf(data);
				resistors = (new ResistorCalculator()).findBandColours(BDdata,
						toleranceSelection);
				listener.onResistanceEntered(resistors);
			}
		}
	}

}
