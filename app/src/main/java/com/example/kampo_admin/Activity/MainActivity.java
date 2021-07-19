package com.example.kampo_admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.kampo_admin.EditFragment;
import com.example.kampo_admin.OrderFragment;
import com.example.kampo_admin.R;
import com.example.kampo_admin.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new EditFragment()).commit();
//        binding.mainScreenBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            Fragment temp = null;
//            switch (item.getItemId()){
//                case R.id.edit_profile: temp = new EditFragment();
//                    break;
//                case R.id.order: temp = new OrderFragment();
//                    break;
//            }
//            assert temp != null;
//            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
//            return false;
//        });
//    }
       binding.mainScreenBottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
           @Override
           public void onNavigationItemReselected(@NonNull  MenuItem item) {
               Fragment temp = null;
            switch (item.getItemId()){
                case R.id.edit_profile: temp = new EditFragment();
                    break;
                case R.id.order: temp = new OrderFragment();
                    break;
            }
            assert temp != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
            return ;
           }
       });
    }
}