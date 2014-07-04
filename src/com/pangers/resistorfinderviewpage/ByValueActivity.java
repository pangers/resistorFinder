package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

public class ByValueActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	private ActionBar actionBar = null;
	private ArrayList<String> dropDownMenu = new ArrayList<String>();
	private ArrayAdapter<String> dropDownAdapter = null;

	final static String TAG = "valueEntryFrag";
	
	final static String DROP_DOWN_SELECTION = "dropDownSelection";
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.byvalueactivity);
		
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
			
			getSupportFragmentManager().beginTransaction()
					.add(R.id.valueentryframe, new valueEntryFrag())
					.add(R.id.valueresultframe, new valueResultFrag()).commit();
		}

		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		dropDownMenu.add("By Colour");
		dropDownMenu.add("By Value");
		dropDownAdapter = new ArrayAdapter<String>(getActionBar()
				.getThemedContext(),
				android.R.layout.simple_spinner_dropdown_item, dropDownMenu);
		actionBar.setListNavigationCallbacks(dropDownAdapter, this);
		actionBar.setSelectedNavigationItem(1);
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
}
