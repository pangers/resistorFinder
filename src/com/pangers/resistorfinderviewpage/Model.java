package com.pangers.resistorfinderviewpage;

public class Model {

	private int icon;
	private String title;

	private boolean isHeader = false;

	// constructors - one for list header, one for list items
	public Model(String listHeader) {
		this(-1, listHeader);
		isHeader = true;
	}

	public Model(int icon, String title) {
		this.icon = icon;
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

}
