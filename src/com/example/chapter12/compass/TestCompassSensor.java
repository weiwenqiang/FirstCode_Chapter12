package com.example.chapter12.compass;

import com.example.chapter12.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class TestCompassSensor extends Activity {
	private SensorManager sensorManager;
	private ImageView compassImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b3_compass);
		compassImg = (ImageView) findViewById(R.id.compass_img);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor magneicSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		Sensor accelerometerSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(listener, magneicSensor,
				SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(listener, accelerometerSensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onDestroy() {
		if (sensorManager != null) {
			sensorManager.unregisterListener(listener);
		}
		super.onDestroy();
	}

	private SensorEventListener listener = new SensorEventListener() {
		float[] accelerometerValues = new float[3];
		float[] magneticValues = new float[3];
		
		private float lastRotateDegree;

		@Override
		public void onSensorChanged(SensorEvent event) {
			// 判断当前是加速度传感器还是地磁传感器
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accelerometerValues = event.values.clone();
			} else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				magneticValues = event.values.clone();
			}
			float[] R = new float[9];
			float[] values = new float[3];
			SensorManager.getRotationMatrix(R, null, accelerometerValues,
					magneticValues);
			SensorManager.getOrientation(R, values);
			Log.d("TestCompass", "value[0] is " + Math.toDegrees(values[0]));
			
			//将计算出旋转角取反，用于旋转指南针背景图
			float rotateDegree = -(float) Math.toDegrees(values[0]);
			if(Math.abs(rotateDegree-lastRotateDegree) >1){
				RotateAnimation animation = new RotateAnimation(lastRotateDegree, rotateDegree,
						Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				animation.setFillAfter(true);
				compassImg.startAnimation(animation);
				lastRotateDegree = rotateDegree;
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};
}
