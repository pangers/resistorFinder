package com.pangers.resistorfinderviewpage;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class SettingsFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	ListPreference startActPref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("SettingsFragment", "got in here!");
		addPreferencesFromResource(R.xml.preferences);
		startActPref = (ListPreference) findPreference(SettingsActivity.STARTING_ACTIVITY);
		startActPref.setSummary(startActPref.getEntry());
	}

	public void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals(SettingsActivity.STARTING_ACTIVITY)) {
			startActPref.setSummary(startActPref.getEntry());
		}
	}

}
