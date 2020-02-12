package com.example.jain.sensorexample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static android.hardware.Sensor.TYPE_ALL;
import static android.hardware.Sensor.TYPE_LIGHT;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mLight;
    private static TextView msg;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allSensors = mSensorManager.getSensorList(TYPE_ALL);
        mLight = mSensorManager.getDefaultSensor(TYPE_LIGHT);
        msg = (TextView)findViewById(R.id.msg);
        lv = (ListView)findViewById(R.id.lv1);
        lv.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,allSensors));
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){
    }


    @Override
    public final void onSensorChanged(SensorEvent event){
        float lux = event.values[0];
        msg.setText("Light Sensor Value: " + String.valueOf(lux));

    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
