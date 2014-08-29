package com.pangers.resistorfinderviewpage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;

public class SettingsActivity extends FragmentActivity {

	final static String SETTINGSFRAG = "settingsFragment";
	public final static String STARTING_ACTIVITY = "pref_startingActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			Log.d("SettingsActivity", "got in here!");
			getFragmentManager()
					.beginTransaction()
					.replace(android.R.id.content, new SettingsFragment(),
							SETTINGSFRAG).commit();
		}
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(R.string.settings);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
