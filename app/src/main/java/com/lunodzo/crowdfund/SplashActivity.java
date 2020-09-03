package com.lunodzo.crowdfund;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        displaySplash();
    }

    public void displaySplash(){
        Thread myThread = new Thread() {
            public void run(){
                try {
                    int display = 8000;
                    int wait = 0;
                    while (wait < display){
                        sleep(100);
                        wait = wait + 100;
                    }
                    super.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent a = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(a);
                    finish();
                }
            }

        };
        myThread.start();
    }
}
