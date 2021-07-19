package com.example.kampo_admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.kampo_admin.R;
import com.example.kampo_admin.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    String dateSelected;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        binding.dayDatePicker.setStartDate(19, 7,2021);
        binding.dayDatePicker.getSelectedDate(date -> {
            if(date != null){
                dateSelected = date.toString();
                DocumentReference documentReference = fStore.collection("Workers").document(mAuth.getUid()).collection("Appointment").document(dateSelected);
                Map<String,Object> slots = new HashMap<>();
                slots.put("10:00AM",true);
                slots.put("11:00AM",true);
                slots.put("12:00PM",true);
                slots.put("01:00PM",true);
                documentReference.set(slots).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(), "Your Slot is Updated", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getContext(), "There is Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });


        fStore.collection("Workers").document(mAuth.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if(value != null) {
                    binding.profileWorkerName.setText(value.getString("FullName"));
                    Glide.with(getContext()).load(value.getString("ProfilePicUri")).placeholder(R.drawable.avatar).into(binding.workerProfileImage);
                    Glide.with(getContext()).load(value.getString("ProfileThumbnail")).placeholder(R.drawable.avatar).into(binding.profileImageThumbnail);
                }
            }
        });
        return binding.getRoot();
    }
}