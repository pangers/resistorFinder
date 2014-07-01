package com.pangers.resistorfinderviewpage;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class ResistorFinderActivity extends FragmentActivity {
	
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		
		pager.setAdapter(new myPagerAdapter(this, getSupportFragmentManager(), getFragments()));
	}
	
	private List<Fragment> getFragments() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		
		fragments.add(FourBandFrag.newInstance());
		fragments.add(FiveBandFrag.newInstance());
				
		return fragments;
	}
	
}
