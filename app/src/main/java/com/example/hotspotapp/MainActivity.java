package com.example.hotspotapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    //WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    //WifiConfiguration wifiConfiguration = new WifiConfiguration();

    public static String TAG = "MainActivity";

    private Button hotspotenable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotspotenable  = (Button)findViewById(R.id.button);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this.getApplicationContext())) {

            } else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
        hotspotenable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewWifiApNetwork();
            }
        });
    }


    public void CreateNewWifiApNetwork() {

        ApManager ap = new ApManager(this.getApplicationContext());
        if(ap.isApOn()==true)
            hotspotenable.setText("Turn On");
        else
            hotspotenable.setText("Turn Off");
        ap.createNewNetwork("SolutionBits","");

    }


//    private void changeStateWifiAp(boolean activated) {
//        Method method;
//        try {
//            method = wifiManager.getClass().getDeclaredMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
//            method.invoke(wifiManager, wifiConfiguration, activated);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
