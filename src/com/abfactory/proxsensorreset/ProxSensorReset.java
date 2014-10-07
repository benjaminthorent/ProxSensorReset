package com.abfactory.proxsensorreset;

import java.util.Locale;

import com.abfactory.proxsensorreset.PreferencesHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProxSensorReset extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prox_sensor_reset);
		
		// Settings Button
		Button startResetFlow = (Button) findViewById(R.id.main_button);
		startResetFlow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ProxSensorReset.this, ResetFlowPhaseOneActivity.class);
				startActivity(intent);
			}
		});
		
		// Display current setting
		TextView currentSettingText = (TextView) findViewById(R.id.current_setting_text);
		currentSettingText.setText(getString(R.string.current_setting) + " " + getCurrentSetting());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setAppLanguage();
	}

	private String getCurrentSetting() {
		return "3.9";
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
			Intent settingsIntent = new Intent(ProxSensorReset.this, SettingsActivity.class);
			startActivity(settingsIntent);
			return true;
		case R.id.action_more_info:
			Intent moreInfoIntent = new Intent(ProxSensorReset.this, MoreInfoActivity.class);
			startActivity(moreInfoIntent);
			return true;
		case R.id.action_give_me_a_beer:
			Intent supportUsIntent = new Intent(ProxSensorReset.this, SupportUsActivity.class);
			startActivity(supportUsIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/*
	 * Set application language as per current setting to have proper localizations to be displayed on this screen
	 */
	private void setAppLanguage(){
		PreferencesHandler preferenceshandler = new PreferencesHandler(getApplicationContext());
		Locale locale = new Locale(preferenceshandler.getLanguage());
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	}

}
