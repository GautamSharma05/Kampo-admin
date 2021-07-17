package com.example.kampo_admin.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kampo_admin.Activity.LoaderActivity;
import com.example.kampo_admin.Activity.RegistrationActivity;
import com.example.kampo_admin.R;
import com.example.kampo_admin.databinding.ActivityLoginBinding;
import com.example.kampo_admin.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    //FireBase instance
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //Email pattern
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        //Calling Function Login When user click on button
        binding.loginButton.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.userEmailAddressText.getText()).toString().trim();
            String password = Objects.requireNonNull(binding.loginPasswordText.getText()).toString().trim();
            logIn(email, password);

        });

        //Sending User To the Registration Activity
        binding.signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }

    private void logIn(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            binding.userEmailAddressText.setError("Email is required");
        } else if (!email.trim().matches(emailPattern)) {
            binding.userEmailAddressText.setError("Email is Not Valid");
        } else if (TextUtils.isEmpty(password)) {
            binding.loginPasswordText.setError("Password is required");
        } else {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUi(user);

                        } else {
                            Toast.makeText(getContext(), "May Be Your Email or Password Wrong!", Toast.LENGTH_SHORT).show();
                            updateUi(null);

                        }
                    });
        }
    }

    private void updateUi(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getContext(), LoaderActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Try Again!", Toast.LENGTH_SHORT).show();
        }
    }
}


