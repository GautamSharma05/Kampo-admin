package com.example.kampo_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kampo_admin.Fragments.RegistrationFragment;
import com.example.kampo_admin.R;
import com.example.kampo_admin.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {
    ActivityRegistrationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_registration);
        getSupportFragmentManager().beginTransaction().replace(R.id.registrationContainer,new RegistrationFragment()).commit();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();}
}