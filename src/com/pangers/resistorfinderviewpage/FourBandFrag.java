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

public class FourBandFrag extends Fragment implements
		AdapterView.OnItemClickListener {

	static final String TAG = "FourBandFrag";

	private TextView resultView;

	private ArrayList<ListView> lists = new ArrayList<ListView>();
	private ArrayList<CustomAdapter> adapters = new ArrayList<CustomAdapter>();
	private TextDisplayer textDisplayer;
	private ArrayList<Integer> uncheckedBands = new ArrayList<Integer>();
	private BigDecimal[] results = new BigDecimal[2];

	final int unselected = -17;

	private int[] bandRowNumber = { 1, 0, 5, 1 };
	private int[] bandRowNumberTrue = { 1, 0, 3, 1 };
	private int lastUserSelectionRow;
	private int lastUserSelectionList;

	static FourBandFrag newInstance() {
		FourBandFrag fourBF = new FourBandFrag();

		return fourBF;
	}

	static String getTitle(Context ctxt) {
		return ctxt.getString(R.string.fourbandresistor);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		View result = inflater.inflate(R.layout.fourbandlayoutfrag, container,
				false);

		return result;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		
		textDisplayer = new TextDisplayer(getActivity());

		resultView = (TextView) getActivity().findViewById(R.id.FourResultView);

		lists.add((ListView) getActivity().findViewById(R.id.FourListView1));
		lists.add((ListView) getActivity().findViewById(R.id.FourListView2));
		lists.add((ListView) getActivity().findViewById(R.id.FourListView3));
		lists.add((ListView) getActivity().findViewById(R.id.FourListView4));

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
		// // Retain results textview after configuration change
		// if (bandRowNumberTrue[0] != unselected || bandRowNumberTrue[1] !=
		// unselected
		// || bandRowNumberTrue[2] != unselected
		// || bandRowNumberTrue[3] != unselected) {

		if (bandRowNumberTrue[0] != unselected
				&& bandRowNumberTrue[1] != unselected
				&& bandRowNumberTrue[2] != unselected
				&& bandRowNumberTrue[3] != unselected) {
			// calculate resistance and tolerance and show result
			results = new ResistorCalculator().calculate(bandRowNumberTrue);
			textDisplayer.resistanceDisplay(resultView, results);
		}

		// }

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
			models.add(new Model(R.drawable.rectanglesilver, Html.fromHtml("10<sup><small>-2</small></sup>")));
			models.add(new Model(R.drawable.rectanglegold, Html.fromHtml("10<sup><small>-1</small></sup>")));
			models.add(new Model(R.drawable.rectangleblack, Html.fromHtml("10<sup><small>0<small></sup>")));
			models.add(new Model(R.drawable.rectanglebrown, Html.fromHtml("10<sup><small>1</small></sup>")));
			models.add(new Model(R.drawable.rectanglered, Html.fromHtml("10<sup><small>2</small></sup>")));
			models.add(new Model(R.drawable.rectangleorange, Html.fromHtml("10<sup><small>3</small></sup>")));
			models.add(new Model(R.drawable.rectangleyellow, Html.fromHtml("10<sup><small>4</small></sup>")));
			models.add(new Model(R.drawable.rectanglegreen, Html.fromHtml("10<sup><small>5</small></sup>")));
			models.add(new Model(R.drawable.rectangleblue, Html.fromHtml("10<sup><small>6</small></sup>")));
			models.add(new Model(R.drawable.rectangleviolet, Html.fromHtml("10<sup><small>7</small></sup>")));
			break;
		case 3:
			models.add(new Model(R.drawable.rectanglesilver, Html.fromHtml("\u00B110%")));
			models.add(new Model(R.drawable.rectanglegold, Html.fromHtml("\u00B15%")));
			models.add(new Model(R.drawable.rectanglebrown, Html.fromHtml("\u00B11%")));
			models.add(new Model(R.drawable.rectanglered, Html.fromHtml("\u00B12%")));
			models.add(new Model(R.drawable.rectanglegreen, Html.fromHtml("\u00B10.5%")));
			models.add(new Model(R.drawable.rectangleblue, Html.fromHtml("\u00B10.25%")));
			models.add(new Model(R.drawable.rectangleviolet, Html.fromHtml("\u00B10.1%")));
		}
		return models;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		((CustomAdapter) parent.getAdapter()).setSelectedIndex(position);
		((CustomAdapter) parent.getAdapter()).notifyDataSetChanged();

		lastUserSelectionRow = position;
		switch (parent.getId()) {
		case R.id.FourListView1:
			// update selection array
			bandRowNumber[0] = position;
			((CustomAdapter) lists.get(0).getAdapter())
					.setBandRowNumber(bandRowNumber);
			bandRowNumberTrue[0] = position;
			lastUserSelectionList = 0;

			// if all rows are selected
			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected) {
				// calculate resistance and tolerance and show result
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FourListView2:
			bandRowNumber[1] = position;
			((CustomAdapter) lists.get(1).getAdapter())
					.setBandRowNumber(bandRowNumber);
			bandRowNumberTrue[1] = position;
			lastUserSelectionList = 1;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FourListView3:
			bandRowNumber[2] = position;
			((CustomAdapter) lists.get(2).getAdapter())
					.setBandRowNumber(bandRowNumber);
			bandRowNumberTrue[2] = position - 2;
			lastUserSelectionList = 2;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;
		case R.id.FourListView4:
			bandRowNumber[3] = position;
			((CustomAdapter) lists.get(3).getAdapter())
					.setBandRowNumber(bandRowNumber);
			bandRowNumberTrue[3] = position;
			lastUserSelectionList = 3;

			if (bandRowNumberTrue[0] != unselected
					&& bandRowNumberTrue[1] != unselected
					&& bandRowNumberTrue[2] != unselected
					&& bandRowNumberTrue[3] != unselected) {
				results = new ResistorCalculator().calculate(bandRowNumberTrue);
				textDisplayer.resistanceDisplay(resultView, results);
			}
			break;

		}
	}
}
