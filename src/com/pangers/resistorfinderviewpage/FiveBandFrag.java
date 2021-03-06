package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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

	//static final String TAG = "FiveBandFrag";

	// TextView to show the resistance and tolerance values
	private TextView resultView;

	// ArrayLists to hold the lists and adapters
	private ArrayList<ListView> lists = new ArrayList<ListView>();
	private ArrayList<CustomAdapter> adapters = new ArrayList<CustomAdapter>();
	private TextDisplayer textDisplayer;
	private ArrayList<Integer> uncheckedBands = new ArrayList<Integer>();
	private BigDecimal[] results = new BigDecimal[2];

	// number to symbolise band is unselected
	final int unselected = -17;

	// Row numbers selected by user
	private int[] bandRowNumber = { 1, 0, 0, 4, 1 };
	private int[] bandRowNumberTrue = { 1, 0, 0, 2, 1 };
	private int lastUserSelectionRow;
	private int lastUserSelectionList;

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
		setRetainInstance(true);

		View result = inflater.inflate(R.layout.fivebandlayoutfrag, container,
				false);

		return result;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		textDisplayer = new TextDisplayer(getActivity());

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
		for (int p = 0; p < lists.size(); p++) {
			((CustomAdapter) lists.get(p).getAdapter())
					.setBandRowNumber(bandRowNumber);
		}

		if (bandRowNumberTrue[0] != unselected
				&& bandRowNumberTrue[1] != unselected
				&& bandRowNumberTrue[2] != unselected
				&& bandRowNumberTrue[3] != unselected
				&& bandRowNumberTrue[4] != unselected) {
			// calculate resistance and tolerance and show result
			results = new ResistorCalculator().calculate(bandRowNumberTrue);
			textDisplayer.resistanceDisplay(resultView, results);
		}

	}

	// parameter = 1 for digit bands, parameter = 2 for multiplier bands,
	// parameter = 3 for tolerance bands
	private ArrayList<Model> generateData(int whichBand) {
		ArrayList<Model> models = new ArrayList<Model>();

		switch (whichBand) {
		case 1:
			models.add(new Model(R.drawable.rectangleblack, Html.fromHtml("0")));
			models.add(new Model(R.drawable.rectanglebrown, Html.fromHtml("1")));
			models.add(new Model(R.drawable.rectanglered, Html.fromHtml("2")));
			models.add(new Model(R.drawable.rectangleorange, Html.fromHtml("3")));
			models.add(new Model(R.drawable.rectangleyellow, Html.fromHtml("4")));
			models.add(new Model(R.drawable.rectanglegreen, Html.fromHtml("5")));
			models.add(new Model(R.drawable.rectangleblue, Html.fromHtml("6")));
			models.add(new Model(R.drawable.rectangleviolet, Html.fromHtml("7")));
			models.add(new Model(R.drawable.rectanglegrey, Html.fromHtml("8")));
			models.add(new Model(R.drawable.rectanglewhite, Html.fromHtml("9")));
			break;
		case 2:
			models.add(new Model(R.drawable.rectanglesilver, Html
					.fromHtml("10<sup><small>-2</small></sup>")));
			models.add(new Model(R.drawable.rectanglegold, Html
					.fromHtml("10<sup><small>-1</small></sup>")));
			models.add(new Model(R.drawable.rectangleblack, Html
					.fromHtml("10<sup><small>0<small></sup>")));
			models.add(new Model(R.drawable.rectanglebrown, Html
					.fromHtml("10<sup><small>1</small></sup>")));
			models.add(new Model(R.drawable.rectanglered, Html
					.fromHtml("10<sup><small>2</small></sup>")));
			models.add(new Model(R.drawable.rectangleorange, Html
					.fromHtml("10<sup><small>3</small></sup>")));
			models.add(new Model(R.drawable.rectangleyellow, Html
					.fromHtml("10<sup><small>4</small></sup>")));
			models.add(new Model(R.drawable.rectanglegreen, Html
					.fromHtml("10<sup><small>5</small></sup>")));
			models.add(new Model(R.drawable.rectangleblue, Html
					.fromHtml("10<sup><small>6</small></sup>")));
			models.add(new Model(R.drawable.rectangleviolet, Html
					.fromHtml("10<sup><small>7</small></sup>")));
			break;
		case 3:
			models.add(new Model(R.drawable.rectanglesilver, Html
					.fromHtml("\u00B110%")));
			models.add(new Model(R.drawable.rectanglegold, Html
					.fromHtml("\u00B15%")));
			models.add(new Model(R.drawable.rectanglebrown, Html
					.fromHtml("\u00B11%")));
			models.add(new Model(R.drawable.rectanglered, Html
					.fromHtml("\u00B12%")));
			models.add(new Model(R.drawable.rectanglegreen, Html
					.fromHtml("\u00B10.5%")));
			models.add(new Model(R.drawable.rectangleblue, Html
					.fromHtml("\u00B10.25%")));
			models.add(new Model(R.drawable.rectangleviolet, Html
					.fromHtml("\u00B10.1%")));
		}
		return models;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// To check the radio button user clicked on
		((CustomAdapter) parent.getAdapter()).setSelectedIndex(position);
		((CustomAdapter) parent.getAdapter()).notifyDataSetChanged();

		lastUserSelectionRow = position;

		switch (parent.getId()) {
		case R.id.FiveListView1:
			// update selection array
			bandRowNumber[0] = position;
			for (int p = 0; p < lists.size(); p++) {
				((CustomAdapter) lists.get(p).getAdapter())
						.setBandRowNumber(bandRowNumber);
			}
			bandRowNumberTrue[0] = position;
			lastUserSelectionList = 0;

			// if all rows are selected
			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected
					&& bandRowNumberTrue[4] != unselected) {
				// calculate resistance and tolerance and show result
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView2:
			bandRowNumber[1] = position;
			for (int p = 0; p < lists.size(); p++) {
				((CustomAdapter) lists.get(p).getAdapter())
						.setBandRowNumber(bandRowNumber);
			}
			bandRowNumberTrue[1] = position;
			lastUserSelectionList = 1;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected
					&& bandRowNumberTrue[4] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView3:
			bandRowNumber[2] = position;
			for (int p = 0; p < lists.size(); p++) {
				((CustomAdapter) lists.get(p).getAdapter())
						.setBandRowNumber(bandRowNumber);
			}
			bandRowNumberTrue[2] = position;
			lastUserSelectionList = 2;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected
					&& bandRowNumberTrue[4] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView4:
			bandRowNumber[3] = position;
			for (int p = 0; p < lists.size(); p++) {
				((CustomAdapter) lists.get(p).getAdapter())
						.setBandRowNumber(bandRowNumber);
			}
			bandRowNumberTrue[3] = position - 2;
			lastUserSelectionList = 3;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected
					&& bandRowNumberTrue[4] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FiveListView5:
			bandRowNumber[4] = position;
			for (int p = 0; p < lists.size(); p++) {
				((CustomAdapter) lists.get(p).getAdapter())
						.setBandRowNumber(bandRowNumber);
			}
			bandRowNumberTrue[4] = position;
			lastUserSelectionList = 4;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected
					&& bandRowNumberTrue[4] != unselected) {
				results = (new ResistorCalculator())
						.calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		}
	}
}
