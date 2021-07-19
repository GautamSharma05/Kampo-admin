package com.example.kampo_admin.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.kampo_admin.Fragments.ProfileFragment;
import com.example.kampo_admin.Fragments.OrderFragment;
import com.example.kampo_admin.R;
import com.example.kampo_admin.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new ProfileFragment()).commit();
        binding.mainScreenBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment temp = null;
            switch (item.getItemId()){
                case R.id.order: temp = new OrderFragment();
                    break;
                case R.id.edit_profile: temp = new ProfileFragment();
                    break;

            }
            assert temp != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
            return false;
        });
    }
}