package com.pangers.resistorfinderviewpage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class StartUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set default values for preferences
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

		// read SharedPreferences
		SharedPreferences sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		// Grab value either 0 or 1
		String startingActivity = sharedPref.getString(
				SettingsActivity.STARTING_ACTIVITY, "");

		int startingActivityInt = Integer.parseInt(startingActivity);
		Log.d("StartUpActivity", "Starting Activity is: " + startingActivityInt);
		if (startingActivityInt == 0) {
			startActivity(new Intent(this, ResistorFinderActivity.class));
			finish();
		} else if (startingActivityInt == 1) {
			startActivity(new Intent(this, ByValueActivity.class));
			finish();
		}
	}
}
