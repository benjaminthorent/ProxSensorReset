package com.abfactory.proxsensorreset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ResetFlowPhaseOneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_phase_one);

		// Validate Button
		Button startResetFlow = (Button) findViewById(R.id.validate);
		startResetFlow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResetFlowPhaseOneActivity.this, ResetFlowPhaseTwoActivity.class);
				startActivity(intent);
			}
		});

	}

}
