package com.example.kampo_admin.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kampo_admin.Activity.LoaderActivity;
import com.example.kampo_admin.Activity.LoginActivity;
import com.example.kampo_admin.Activity.MainActivity;
import com.example.kampo_admin.databinding.FragmentRegistrationBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class RegistrationFragment extends Fragment {
FragmentRegistrationBinding binding;
//Creating Firebase Instance
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore fStore = FirebaseFirestore.getInstance();

//    Creating Variable
      String userFullName,email,mobileNumber,specialization;
      String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding=FragmentRegistrationBinding.inflate(inflater,container,false);

    //calling Signup to login Screen
    binding.signUpButton.setOnClickListener(v -> signup());
        //Sending User To login Screen
        binding.logInText.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
    return binding.getRoot();
    }

    private void signup() {
        userFullName = Objects.requireNonNull(binding.fullNameText.getText()).toString().trim();
        email = Objects.requireNonNull(binding.userEmailAddressText.getText()).toString().trim();
        mobileNumber=Objects.requireNonNull(binding.numberText.getText()).toString().trim();
        specialization=Objects.requireNonNull(binding.specialistText.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.loginPasswordText.getText()).toString();
        if (TextUtils.isEmpty(userFullName)) {
            binding.fullNameText.setError("Full Name is Required");
        }
        else if(TextUtils.isEmpty(email)){
            binding.userEmailAddressText.setError("Email is required");
        }
        else if(!email.trim().matches(emailPattern)){
            binding.userEmailAddressText.setError("Email is Not Valid");
        }
        else if(TextUtils.isEmpty(password)){
            binding.loginPasswordText.setError("Password is required");
        }
        else if(password.length() < 6){
            binding.loginPasswordText.setError("Password Must be equal to 6 character or greater than 6");
        }
        else if (TextUtils.isEmpty(mobileNumber)){
            binding.numberText.setError("Mobile Number is Required");
        }
        else if (TextUtils.isEmpty(specialization)){
            binding.specialistText.setError("Specialization is Required");
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task ->{
                if (task.isSuccessful())
                {
                    FirebaseUser user= mAuth.getCurrentUser();
                    DocumentReference documentReference = fStore.collection("Workers").document(Objects.requireNonNull(mAuth.getUid()));
                    Map<String, Object> workers =new HashMap<>();
                    workers.put("FullName",userFullName);
                    workers.put("Email",email);
                    workers.put("PhoneNumber",mobileNumber);
                    workers.put("Specialization",specialization);
                    documentReference.set(workers).addOnCompleteListener(unused -> updateUI(user));
                }
                else {
                    Toast.makeText(getContext(), "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            } );
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null)
        {
            Intent intent = new Intent(getContext(), LoaderActivity.class);
            intent.putExtra("Name",userFullName);
            intent.putExtra("Email",email);
            startActivity(intent);
        }

        else {
            Toast.makeText(getContext(), "Try Again Some Time!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    }

}
