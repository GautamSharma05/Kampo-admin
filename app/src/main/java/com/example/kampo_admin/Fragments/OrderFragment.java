package com.example.kampo_admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kampo_admin.Adapters.OrderAdapter;
import com.example.kampo_admin.Models.Orders;
import com.example.kampo_admin.R;
import com.example.kampo_admin.databinding.FragmentOrderBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OrderFragment extends Fragment {
    FragmentOrderBinding binding;
    OrderAdapter orderAdapter;
    private final FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater,container,false);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid().trim();
        Query query = fStore.collection("Booking").whereEqualTo("WorkerId",currentUser);
        binding.ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        FirestoreRecyclerOptions<Orders> orders = new FirestoreRecyclerOptions.Builder<Orders>().
                setQuery(query,Orders.class).build();
        orderAdapter = new OrderAdapter(orders);
        binding.ordersRecyclerView.setAdapter(orderAdapter);
        return binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
        orderAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        orderAdapter.stopListening();
    }
}