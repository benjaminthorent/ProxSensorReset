package com.abfactory.proxsensorreset;

import com.abfactory.proxsensorreset.datamodel.CalibrationProcedureData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ResetFlowPhaseTwoActivity extends Activity {

	// Calibration data track throughout the procedure
	private CalibrationProcedureData calibrationData = new CalibrationProcedureData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_phase_two);

		calibrationData = getIntent().getParcelableExtra(CalibrationProcedureData.CALIBRATION_PROCEDURE_DATA);

		// Validate Button
		Button startResetFlow = (Button) findViewById(R.id.validate);
		startResetFlow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResetFlowPhaseTwoActivity.this, ResetFlowPhaseThreeActivity.class);
				// Get sensor calibration value and set it
				calibrationData.setFreeSensorValue(getCalibrationData());
				// Convey it via the intent
				intent.putExtra(CalibrationProcedureData.CALIBRATION_PROCEDURE_DATA, calibrationData);
				// Start new activity
				startActivity(intent);
			}
		});

		// Use Previous Button
		Button usePrevious = (Button) findViewById(R.id.use_previous);
		usePrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResetFlowPhaseTwoActivity.this, ResetFlowPhaseThreeActivity.class);
				// Convey calibration data via the intent without changing it
				intent.putExtra(CalibrationProcedureData.CALIBRATION_PROCEDURE_DATA, calibrationData);
				// Start new activity
				startActivity(intent);
			}
		});	


	}

	@Override
	protected void onResume() {
		super.onResume();

		// Handle 'Use previous' button display
		Button usePrevious = (Button) findViewById(R.id.use_previous);
		if(calibrationData!=null && calibrationData.getFreeSensorValue()!=0){
			usePrevious.setVisibility(View.VISIBLE);
			usePrevious.setText(getString(R.string.use_previous) + " (" + calibrationData.getFreeSensorValue() +  ")");
		} else {
			usePrevious.setVisibility(View.GONE);
		}
	}

	private double getCalibrationData() {
		return 10;
	}

}
