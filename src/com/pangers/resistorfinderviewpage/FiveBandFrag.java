package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class FiveBandFrag extends Fragment implements
		AdapterView.OnItemClickListener {

	static final String TAG = "FiveBandFrag";

	// TextView to show the resistance and tolerance values
	private TextView resultView;

	// ArrayLists to hold the lists and adapters
	private ArrayList<ListView> lists = new ArrayList<ListView>();
	private ArrayList<CustomAdapter> adapters = new ArrayList<CustomAdapter>();

	// number to symbolise band is unselected
	final int unselected = -17;

	// Row numbers selected by user
	private int[] bandRowNumber = { unselected, unselected, unselected,
			unselected, unselected };

	public static FiveBandFrag newInstance() {
		FiveBandFrag fiveBF = new FiveBandFrag();

		return fiveBF;
	}

	static String getTitle(Context ctxt) {
		return ctxt.getString(R.string.fivebandresistor);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setRetainInstance(true);

		View result = inflater.inflate(R.layout.fivebandlayoutfrag, container,
				false);

		return result;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);

		resultView = (TextView) getActivity().findViewById(R.id.FiveResultView);

		lists.add((ListView) getActivity().findViewById(R.id.FiveListView1));
		lists.add((ListView) getActivity().findViewById(R.id.FiveListView2));
		lists.add((ListView) getActivity().findViewById(R.id.FiveListView3));
		lists.add((ListView) getActivity().findViewById(R.id.FiveListView4));
		lists.add((ListView) getActivity().findViewById(R.id.FiveListView5));

		adapters.add(new CustomAdapter(getActivity(), generateData(1)));
		adapters.add(new CustomAdapter(getActivity(), generateData(1)));
		adapters.add(new CustomAdapter(getActivity(), generateData(1)));
		adapters.add(new CustomAdapter(getActivity(), generateData(2)));
		adapters.add(new CustomAdapter(getActivity(), generateData(3)));

		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAdapter(adapters.get(i));
		}

		for (int q = 0; q < lists.size(); q++) {
			lists.get(q).setOnItemClickListener(this);
		}

		// Retain radio button selections after configuration change
		for (int w = 0; w < bandRowNumber.length; w++) {
			if (bandRowNumber[w] != unselected) {
				adapters.get(w).setSelectedIndex(bandRowNumber[w]);
				adapters.get(w).notifyDataSetChanged();
			}
		}
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.actions, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			return true;

		case R.id.colourcode:

			return true;
		case R.id.help:

			return true;
		case R.id.header:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// parameter = 1 for digit bands, parameter = 2 for multiplier bands,
	// parameter = 3 for tolerance bands
	private ArrayList<Model> generateData(int whichBand) {
		ArrayList<Model> models = new ArrayList<Model>();

		switch (whichBand) {
		case 1:
			models.add(new Model(R.drawable.rectangleblack, ""));
			models.add(new Model(R.drawable.rectanglebrown, ""));
			models.add(new Model(R.drawable.rectanglered, ""));
			models.add(new Model(R.drawable.rectangleorange, ""));
			models.add(new Model(R.drawable.rectangleyellow, ""));
			models.add(new Model(R.drawable.rectanglegreen, ""));
			models.add(new Model(R.drawable.rectangleblue, ""));
			models.add(new Model(R.drawable.rectangleviolet, ""));
			models.add(new Model(R.drawable.rectanglegrey, ""));
			models.add(new Model(R.drawable.rectanglewhite, ""));
			break;
		case 2:
			models.add(new Model(R.drawable.rectanglesilver, ""));
			models.add(new Model(R.drawable.rectanglegold, ""));
			models.add(new Model(R.drawable.rectangleblack, ""));
			models.add(new Model(R.drawable.rectanglebrown, ""));
			models.add(new Model(R.drawable.rectanglered, ""));
			models.add(new Model(R.drawable.rectangleorange, ""));
			models.add(new Model(R.drawable.rectangleyellow, ""));
			models.add(new Model(R.drawable.rectanglegreen, ""));
			models.add(new Model(R.drawable.rectangleblue, ""));
			models.add(new Model(R.drawable.rectangleviolet, ""));
			break;
		case 3:
			models.add(new Model(R.drawable.rectanglesilver, ""));
			models.add(new Model(R.drawable.rectanglegold, ""));
			models.add(new Model(R.drawable.rectanglebrown, ""));
			models.add(new Model(R.drawable.rectanglered, ""));
			models.add(new Model(R.drawable.rectanglegreen, ""));
			models.add(new Model(R.drawable.rectangleblue, ""));
			models.add(new Model(R.drawable.rectangleviolet, ""));
		}
		return models;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// To check the radio button user clicked on
		((CustomAdapter) parent.getAdapter()).setSelectedIndex(position);
		((CustomAdapter) parent.getAdapter()).notifyDataSetChanged();

		// ArrayList to keep track of unchecked bands
		ArrayList<Integer> uncheckedBands = new ArrayList<Integer>();

		// array to hold resistance and tolerance
		BigDecimal[] results = new BigDecimal[2];

		// Carries out changes to the textview
		TextDisplayer textDisplayer = new TextDisplayer();

		switch (parent.getId()) {
		case R.id.FiveListView1:
			// update selection array
			bandRowNumber[0] = position;
			textDisplayer.lastSelection(resultView, 1, position);

			// which bands still need to be selected
			uncheckedBands = (new BandChecker()).bandCheck(bandRowNumber,
					unselected);
			textDisplayer.unselectedBands(resultView, uncheckedBands);

			// current values selected by user
			textDisplayer.bandRowValues(resultView, bandRowNumber);

			// if all rows are selected
			if (bandRowNumber[0] != unselected
					&& bandRowNumber[1] != unselected
					&& bandRowNumber[2] != unselected
					&& bandRowNumber[3] != unselected
					&& bandRowNumber[4] != unselected) {
				// calculate resistance and tolerance and show result
				results = new ResistorCalculator().calculate(bandRowNumber);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView2:
			bandRowNumber[1] = position;
			textDisplayer.lastSelection(resultView, 2, position);
			uncheckedBands = (new BandChecker()).bandCheck(bandRowNumber,
					unselected);
			textDisplayer.unselectedBands(resultView, uncheckedBands);
			textDisplayer.bandRowValues(resultView, bandRowNumber);
			if (bandRowNumber[0] != unselected
					&& bandRowNumber[1] != unselected
					&& bandRowNumber[2] != unselected
					&& bandRowNumber[3] != unselected
					&& bandRowNumber[4] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumber);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView3:
			bandRowNumber[2] = position;
			textDisplayer.lastSelection(resultView, 3, position);
			uncheckedBands = (new BandChecker()).bandCheck(bandRowNumber,
					unselected);
			textDisplayer.unselectedBands(resultView, uncheckedBands);
			textDisplayer.bandRowValues(resultView, bandRowNumber);
			if (bandRowNumber[0] != unselected
					&& bandRowNumber[1] != unselected
					&& bandRowNumber[2] != unselected
					&& bandRowNumber[3] != unselected
					&& bandRowNumber[4] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumber);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView4:
			bandRowNumber[3] = position - 2;
			textDisplayer.lastSelection(resultView, 4, position);
			uncheckedBands = (new BandChecker()).bandCheck(bandRowNumber,
					unselected);
			textDisplayer.unselectedBands(resultView, uncheckedBands);
			textDisplayer.bandRowValues(resultView, bandRowNumber);
			if (bandRowNumber[0] != unselected
					&& bandRowNumber[1] != unselected
					&& bandRowNumber[2] != unselected
					&& bandRowNumber[3] != unselected
					&& bandRowNumber[4] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumber);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView5:
			bandRowNumber[4] = position;
			textDisplayer.lastSelection(resultView, 5, position);
			uncheckedBands = (new BandChecker()).bandCheck(bandRowNumber,
					unselected);
			textDisplayer.unselectedBands(resultView, uncheckedBands);
			textDisplayer.bandRowValues(resultView, bandRowNumber);
			if (bandRowNumber[0] != unselected
					&& bandRowNumber[1] != unselected
					&& bandRowNumber[2] != unselected
					&& bandRowNumber[3] != unselected
					&& bandRowNumber[4] != unselected) {
				results = (new ResistorCalculator()).calculate(bandRowNumber);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		}
	}
}
