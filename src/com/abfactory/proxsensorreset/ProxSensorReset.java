package com.abfactory.proxsensorreset;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ProxSensorReset extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prox_sensor_reset);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prox_sensor_reset, menu);
		return true;
	}

}
