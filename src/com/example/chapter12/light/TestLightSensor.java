package com.example.chapter12.light;

import com.example.chapter12.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TestLightSensor extends Activity {
	private SensorManager sensorManager;
	private TextView light_level;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b1_light_sensor);
		light_level=  (TextView) findViewById(R.id.light_level);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	
	@Override
	protected void onDestroy() {
		if(sensorManager != null){
			sensorManager.unregisterListener(listener);
		}
		super.onDestroy();
	}


	private SensorEventListener listener = new SensorEventListener(){

		@Override
		public void onSensorChanged(SensorEvent event) {
			//values数组中第一个下标的值就是当前的光照强度
			float value = event.values[0];
			light_level.setText("当前光照强度是" +value+"  lx");
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};
}
