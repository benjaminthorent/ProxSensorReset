package com.abfactory.proxsensorreset;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.abfactory.proxsensorreset.PreferencesHandler.Language;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		// Set language spinner
		Spinner languageSpinner = (Spinner) findViewById(R.id.language_spinner);
		List<String> list = new ArrayList<String>();
		for(Language l: Language.values()){
			list.add(getResources().getString(l.getRessourceID()));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		languageSpinner.setAdapter(dataAdapter);

		languageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				Locale locale = new Locale(Language.getLocaleStringFromIndex(position));
				Locale.setDefault(locale);
				Configuration config = new Configuration();
				config.locale = locale;
				getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
				// Notify user for language change
				if(!Language.getLocaleStringFromIndex(((Spinner)findViewById(R.id.language_spinner)).getSelectedItemPosition())
						.equals(new PreferencesHandler(getApplicationContext()).getLanguage())){
					Toast.makeText(getApplicationContext(), R.string.languageUpdateOK, Toast.LENGTH_SHORT).show();
					// Save preferences
					PreferencesHandler ph = new PreferencesHandler(getApplicationContext());
					ph.saveSettings(Language.getLocaleStringFromIndex(((Spinner)findViewById(R.id.language_spinner)).getSelectedItemPosition()));
					// Update language display
					setAppLanguage();
					Intent settingsIntent = new Intent(SettingsActivity.this, SettingsActivity.class);
					startActivity(settingsIntent);
					finish();
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do nothing
			}
		});
		((Spinner)findViewById(R.id.language_spinner)).setSelection((new PreferencesHandler(getApplicationContext())).getDefaultLanguageIndex());

		// Display default value
		TextView defaultValue = (TextView) findViewById(R.id.default_value);
		defaultValue.setText(getString(R.string.default_value) + " " + getDefaultValue());

		// Display history count
		TextView calibrationsPerformed = (TextView) findViewById(R.id.calibrations_performed);
		calibrationsPerformed.setText(getString(R.string.calibrations_performed) + " " + getCalibrationsCount());
		
		// Restore Button
		Button restoreButton = (Button) findViewById(R.id.restore);
		restoreButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new AlertDialog.Builder(SettingsActivity.this)
			    .setTitle(R.string.calibration_conf_dialog_title)
			    .setMessage(R.string.calibration_conf_dialog_content)
			    .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	// Do calibration process
			        	Intent intent = new Intent(SettingsActivity.this, ProxSensorReset.class);
						startActivity(intent);
			        }
			     })
			    .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			    .show();
			}
		});

		// History Button
		Button historyButton = (Button) findViewById(R.id.history);
		historyButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(SettingsActivity.this, CalibrationsHistoryActivity.class);
				startActivity(intent);
			}
		});
	}

	private String getDefaultValue() {
		return "3.9";
	}
	
	private String getCalibrationsCount() {
		return "3";
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
