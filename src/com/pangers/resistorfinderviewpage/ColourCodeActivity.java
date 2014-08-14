package com.pangers.resistorfinderviewpage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class ColourCodeActivity extends FragmentActivity {
	
	private TextView bufferTextView; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.colourcodetable);
		bufferTextView = (TextView) findViewById(R.id.multiplierblack);
		setMultiplier(bufferTextView, 0);
		bufferTextView = (TextView) findViewById(R.id.multiplierbrown);
		setMultiplier(bufferTextView, 1);
		bufferTextView = (TextView) findViewById(R.id.multiplierred);
		setMultiplier(bufferTextView, 2);
		bufferTextView = (TextView) findViewById(R.id.multiplierorange);
		setMultiplier(bufferTextView, 3);
		bufferTextView = (TextView) findViewById(R.id.multiplieryellow);
		setMultiplier(bufferTextView, 4);
		bufferTextView = (TextView) findViewById(R.id.multipliergreen);
		setMultiplier(bufferTextView, 5);
		bufferTextView = (TextView) findViewById(R.id.multiplierblue);
		setMultiplier(bufferTextView, 6);
		bufferTextView = (TextView) findViewById(R.id.multiplierviolet);
		setMultiplier(bufferTextView, 7);
		bufferTextView = (TextView) findViewById(R.id.multipliergold);
		setMultiplier(bufferTextView, 8);
		bufferTextView = (TextView) findViewById(R.id.multipliersilver);
		setMultiplier(bufferTextView, 9);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(R.string.colourcode);
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
	
	public void setMultiplier(TextView textview, int pos) {
		switch (pos) {
		case 0:
			textview.setText(Html.fromHtml("10<sup><small>0</small></sup>"));
			break;
		case 1:
			textview.setText(Html.fromHtml("10<sup><small>1</small></sup>"));
			break;
		case 2:
			textview.setText(Html.fromHtml("10<sup><small>2</small></sup>"));
			break;
		case 3:
			textview.setText(Html.fromHtml("10<sup><small>3</small></sup>"));
			break;
		case 4:
			textview.setText(Html.fromHtml("10<sup><small>4</small></sup>"));
			break;
		case 5:
			textview.setText(Html.fromHtml("10<sup><small>5</small></sup>"));
			break;
		case 6:
			textview.setText(Html.fromHtml("10<sup><small>6</small></sup>"));
			break;
		case 7:
			textview.setText(Html.fromHtml("10<sup><small>7</small></sup>"));
			break;
		case 8:
			textview.setText(Html.fromHtml("10<sup><small>-1</small></sup>"));
			break;
		case 9:
			textview.setText(Html.fromHtml("10<sup><small>-2</small></sup>"));
			break;
		}
		
	}

}
