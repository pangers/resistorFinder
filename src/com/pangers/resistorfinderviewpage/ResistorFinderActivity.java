package com.pangers.resistorfinderviewpage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ResistorFinderActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	private ActionBar actionBar = null;
	private ArrayList<String> dropDownMenu = new ArrayList<String>();
	private ArrayAdapter<String> dropDownAdapter = null;

	final static String DROP_DOWN_SELECTION = "dropDownSelection";
	final static String TAG = "ResistorFinderActivity";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		

		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		dropDownMenu.add("By Colour");
		dropDownMenu.add("By Value");
		dropDownAdapter = new ArrayAdapter<String>(getActionBar()
				.getThemedContext(),
				android.R.layout.simple_spinner_dropdown_item, dropDownMenu);
		actionBar.setListNavigationCallbacks(dropDownAdapter, this);
		actionBar.setSelectedNavigationItem(0);

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new myPagerAdapter(this, getSupportFragmentManager(),
				getFragments()));
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

			return true;
		case R.id.help:

			return true;
		case R.id.header:

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

	private List<Fragment> getFragments() {
		List<Fragment> fragments = new ArrayList<Fragment>();

		fragments.add(FourBandFrag.newInstance());
		fragments.add(FiveBandFrag.newInstance());

		return fragments;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		switch (itemPosition) {
		case 0:
			return true;
		case 1:
			startActivity(new Intent(this, ByValueActivity.class));	
			return true;
		}

		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(DROP_DOWN_SELECTION, 0);
	}
	
	@Override
	public void onBackPressed() {
		actionBar.setSelectedNavigationItem(1);
	}
	

}
