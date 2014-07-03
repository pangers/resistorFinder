package com.pangers.resistorfinderviewpage;

import android.text.Spanned;

public class Model {

	private int icon;
	private Spanned title;

	private boolean isHeader = false;

	// constructors - one for list header, one for list items
	public Model(Spanned listHeader) {
		this(-1, listHeader);
		isHeader = true;
	}

	public Model(int icon, Spanned title) {
		this.icon = icon;
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Spanned getTitle() {
		return title;
	}

	public void setTitle(Spanned title) {
		this.title = title;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

}
