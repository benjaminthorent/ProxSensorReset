package com.abfactory.proxsensorreset;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResetFlowPhaseThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_phase_three);

		// Display value to be set
		TextView defaultValue = (TextView) findViewById(R.id.value_to_be_set);
		defaultValue.setText(getString(R.string.value_to_be_set) + " " + getValueToBeSet());

		// Validate Button
		Button startResetFlow = (Button) findViewById(R.id.validate);
		startResetFlow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new AlertDialog.Builder(ResetFlowPhaseThreeActivity.this)
				.setTitle(R.string.calibration_conf_dialog_title)
				.setMessage(R.string.calibration_conf_dialog_content)
				.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// Do calibration process
						Intent intent = new Intent(ResetFlowPhaseThreeActivity.this, ProxSensorReset.class);
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
	}

	private String getValueToBeSet() {
		return "4.5";
	}

}
