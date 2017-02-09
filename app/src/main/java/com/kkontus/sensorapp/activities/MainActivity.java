package com.kkontus.sensorapp.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kkontus.sensorapp.R;
import com.kkontus.sensorapp.helpers.LanguageHelper;
import com.kkontus.sensorapp.helpers.SharedPreferencesHelper;
import com.kkontus.sensorapp.listeners.ShakeEventListener;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String currentSelectedLanguageLocale = SharedPreferencesHelper.getLanguageSelectedLocale(getApplicationContext());
        LanguageHelper.changeLanguageLocale(getApplicationContext(), currentSelectedLanguageLocale);
        System.out.println("MainActivity onCreate " + currentSelectedLanguageLocale);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                Toast.makeText(MainActivity.this, "Shake!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        String currentSelectedLanguageLocale = SharedPreferencesHelper.getLanguageSelectedLocale(getApplicationContext());
        LanguageHelper.changeLanguageLocale(getApplicationContext(), currentSelectedLanguageLocale);
        System.out.println("MainActivity onResume " + currentSelectedLanguageLocale);

        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        mSensorManager.unregisterListener(mSensorListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent intent;

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
