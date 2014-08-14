package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ByValueActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener,
		ValueEntryFrag.onResistanceEnteredListener {

	private ActionBar actionBar = null;
	private ArrayList<String> dropDownMenu = new ArrayList<String>();
	private ArrayAdapter<String> dropDownAdapter = null;
	private int dropdownSelection;

	final static String RESULTTAG = "resultTag";
	final static String DROP_DOWN_SELECTION = "dropDownSelection";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.byvalueactivity);

		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		dropDownMenu.add("By Colour");
		dropDownMenu.add("By Value");
		dropDownAdapter = new ArrayAdapter<String>(getActionBar()
				.getThemedContext(),
				android.R.layout.simple_spinner_dropdown_item, dropDownMenu);
		actionBar.setListNavigationCallbacks(dropDownAdapter, this);
		dropdownSelection = getIntent().getIntExtra("DROPDOWN_POS_FROM_MAIN",
				-1);
		// if (dropdownSelection != -1) {
		// actionBar.setSelectedNavigationItem(dropdownSelection);
		// } else {
		// actionBar.setSelectedNavigationItem(1);
		// }

		if (findViewById(R.id.valueentryframe) != null) {
			if (savedInstanceState != null) {
				return;
			}
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.valueentryframe, new ValueEntryFrag())
					.add(R.id.valueresultframe, new ValueResultFrag(),
							RESULTTAG).commit();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		actionBar.setSelectedNavigationItem(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.colourcode:
			startActivity(new Intent(this, ColourCodeActivity.class));
			return true;
		case R.id.help:
			showHelpDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		actionBar.setSelectedNavigationItem(state.getInt(DROP_DOWN_SELECTION));

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		switch (itemPosition) {
		case 0:
			// Intent i = new Intent(this, ResistorFinderActivity.class);
			// i.putExtra("DROPDOWN_POS_FROM_VALUE", itemPosition);
			startActivity(new Intent(this, ResistorFinderActivity.class));
			return true;
		case 1:
			return true;
		}
		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(DROP_DOWN_SELECTION, 1);
	}

	@Override
	public void onResistanceEntered(ArrayList<ResistorData> resistors) {
		ValueResultFrag fragment = (ValueResultFrag) getSupportFragmentManager()
				.findFragmentByTag(RESULTTAG);
		if (fragment != null) {

			fragment.updateData(resistors);
		}
	}

	public void showHelpDialog() {
		ByValueDialog dialog = new ByValueDialog();
		dialog.show(getSupportFragmentManager(), "ByValueDialog");
	}
}
