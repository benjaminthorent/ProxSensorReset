package com.abfactory.proxsensorreset;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalibrationsHistoryActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calibrations_history_list);
		
		ListView listviewHistory = (ListView) findViewById(R.id.listviewHistory);
		
        // Here is where the list should be called first time
        List<String> history = new ArrayList<String>();
        history.add("foo");
        history.add("bar");

        // Use adapter to display history
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                history );

        listviewHistory.setAdapter(arrayAdapter);	
	}
}
