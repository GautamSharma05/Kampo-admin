package com.example.kampo_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.kampo_admin.R;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loader);
        new Handler().postDelayed(() -> startActivity(new Intent(getApplicationContext(),MainActivity.class)),3000);
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, LoaderActivity.class));
        finish();
    }
}
