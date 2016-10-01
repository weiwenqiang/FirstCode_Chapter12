package com.example.chapter12;

import com.example.chapter12.accelerometer.TestAccelerometerSensor;
import com.example.chapter12.compass.TestCompassSensor;
import com.example.chapter12.light.TestLightSensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.c12_b1).setOnClickListener(this);
		findViewById(R.id.c12_b2).setOnClickListener(this);
		findViewById(R.id.c12_b3).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.c12_b1:
			startActivity(new Intent(MainActivity.this, TestLightSensor.class));
			break;
		case R.id.c12_b2:
			startActivity(new Intent(MainActivity.this, TestAccelerometerSensor.class));
			break;
		case R.id.c12_b3:
			startActivity(new Intent(MainActivity.this, TestCompassSensor.class));
			break;
		}
	}
}
