package com.example.kampo_admin.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kampo_admin.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {
    FragmentEditProfileBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}