package com.kkontus.sensorapp.activities;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kkontus.sensorapp.R;
import com.kkontus.sensorapp.helpers.SharedPreferencesHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioLanguageGroup;
    private RadioButton radioLanguageButton;
    private Button btnSelectLanguage;
    private Hashtable<String, Integer> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        getKeyHashForApp();

        table = new Hashtable<>();
        table.put("en", 0);
        table.put("de", 1);
        table.put("hr", 2);
        table.put("it", 3);
        table.put("si", 4);

        radioLanguageGroup = (RadioGroup) findViewById(R.id.radioLanguage);
        btnSelectLanguage = (Button) findViewById(R.id.btnSelectLangeuage);

        String currentSelectedLanguageLocale = SharedPreferencesHelper.getLanguageSelectedLocale(getApplicationContext());
        int langIndex = table.get(currentSelectedLanguageLocale);
        radioLanguageGroup.check(radioLanguageGroup.getChildAt(langIndex).getId());

        btnSelectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioLanguageGroup.getCheckedRadioButtonId();
                radioLanguageButton = (RadioButton) findViewById(selectedId);

                //"hr"; //set it wherever you want, some dropdown etc, this is only hardcoded version of how to use it, just set selected locale to SharedPrefs
                String locale = radioLanguageButton.getText().toString();
                SharedPreferencesHelper.setLanguageSelectedLocale(SettingsActivity.this, locale);
                finish();

                Toast.makeText(SettingsActivity.this, locale, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getKeyHashForApp() {
        //###############################################

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    //"com.sensor.sensorapp",
                    //"com.android.smartcamera",
                    //"com.android.cloudcamera",
                    "com.kkontus.sensorapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        //###############################################
    }
}
