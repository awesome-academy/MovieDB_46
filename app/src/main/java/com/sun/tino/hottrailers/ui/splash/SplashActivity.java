package com.sun.tino.hottrailers.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sun.tino.hottrailers.R;
import com.sun.tino.hottrailers.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int TIME_DELAY = 3100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, TIME_DELAY);
    }
}
