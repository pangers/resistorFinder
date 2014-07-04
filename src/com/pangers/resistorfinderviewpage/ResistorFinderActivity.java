package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;

public class ResistorFinderActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	private ActionBar actionBar = null;
	private ArrayList<String> dropDownMenu = new ArrayList<String>();
	private ArrayAdapter<String> dropDownAdapter = null;
	
	final static String DROP_DOWN_SELECTION = "dropDownSelection";

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

}
