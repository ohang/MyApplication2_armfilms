package com.ohanyan.goro.armfilms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



/**
 * Created by Goro on 12.03.2018.
 */

public class SplashScreen extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashlayout);


        // Make call to execute AsycTasks<> here
        // This helps avoid the extra step of clicking on a button
        // to take you to the MainActivity

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // After 2 seconds the Splashscreen will disappear and user is taken to MainActivity
                    Intent splashScreenIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(splashScreenIntent);
                }
            }
        };

        timerThread.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


    }
