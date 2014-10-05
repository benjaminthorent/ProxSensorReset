package com.abfactory.proxsensorreset;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProxSensorReset extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prox_sensor_reset);

		// Settings Button
		ImageButton startResetFlow = (ImageButton) findViewById(R.id.main_button);
		startResetFlow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ProxSensorReset.this, ResetFlowPhaseOneActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prox_sensor_reset, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(ProxSensorReset.this, SettingsActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_more_info:
			Toast.makeText(getApplicationContext(), "More info activity TBD", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_give_me_a_beer:
			Toast.makeText(getApplicationContext(), "Give me a beer activity TBD", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
