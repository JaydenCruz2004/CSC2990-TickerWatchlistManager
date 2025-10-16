package com.example.csc2990_tickerwatchlistmanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS}, 67);
        }

        String newTicker = getIntent().getStringExtra(SMSReceiver.EXTRA_TICKER);

        if (newTicker != null) {
            TickerListFragment listFragment = (TickerListFragment)
                    getSupportFragmentManager().findFragmentById(R.id.tickerListFragment);
            if (listFragment != null) {
                listFragment.addTicker(newTicker);
            }

            InfoWebFragment webFragment = (InfoWebFragment)
                    getSupportFragmentManager().findFragmentById(R.id.infoWebFragment);
            if (webFragment != null) {
                webFragment.loadUrlForTicker(newTicker);
            }
        }
    }
}
