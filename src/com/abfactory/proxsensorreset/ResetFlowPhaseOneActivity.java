package com.abfactory.proxsensorreset;

import com.abfactory.proxsensorreset.datamodel.CalibrationProcedureData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ResetFlowPhaseOneActivity extends Activity {
	
	private static final int MY_REQUEST_CODE = 123;

	// Calibration data track throughout the procedure
	private CalibrationProcedureData calibrationData = new CalibrationProcedureData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_phase_one);

		// Validate Button
		Button startResetFlow = (Button) findViewById(R.id.validate);
		startResetFlow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResetFlowPhaseOneActivity.this, ResetFlowPhaseTwoActivity.class);
				// Get sensor calibration value and set it
				calibrationData.setHiddenSensorValue(getCalibrationData());
				// Convey it via the intent
				intent.putExtra(CalibrationProcedureData.CALIBRATION_PROCEDURE_DATA, calibrationData);
				// Start new activity
				startActivityForResult(intent, MY_REQUEST_CODE);
			}
		});

		// Use Previous Button
		Button usePrevious = (Button) findViewById(R.id.use_previous);
		usePrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResetFlowPhaseOneActivity.this, ResetFlowPhaseTwoActivity.class);
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

		// Update action bar if necessary
		// TODO
		
		// Handle 'Use previous' button display
		Button usePrevious = (Button) findViewById(R.id.use_previous);
		if(calibrationData!=null && calibrationData.getHiddenSensorValue()!=0){
			usePrevious.setVisibility(View.VISIBLE);
			usePrevious.setText(getString(R.string.use_previous) + " (" + calibrationData.getHiddenSensorValue() +  ")");
		} else {
			usePrevious.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent backIntent) {
	    super.onActivityResult(requestCode, resultCode, backIntent);
	    if(resultCode == RESULT_OK && requestCode == MY_REQUEST_CODE && backIntent != null){
	    	if (resultCode == RESULT_OK) {
	        	calibrationData =backIntent.getParcelableExtra(CalibrationProcedureData.CALIBRATION_PROCEDURE_DATA);
	        }
        }
	}

	private double getCalibrationData() {
		return 5;
	}

}
