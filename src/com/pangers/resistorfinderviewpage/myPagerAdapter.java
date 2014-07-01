package com.pangers.resistorfinderviewpage;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class myPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	Context context;
	
	public myPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.context = context;
		this.fragments = fragments;
	}
	
	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
	
	@Override
	public String getPageTitle(int position) {
		String title = null;
		
		if (position == 0) {
			title = FourBandFrag.getTitle(this.context);
		} else if (position == 1) {
			title = FiveBandFrag.getTitle(this.context);		
		}
		
		return title;
	}
	

}
