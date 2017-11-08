package com.example.guest.realhouse.ui;
import com.example.guest.realhouse.adapters.FirebaseHouseViewHolder;
import com.example.guest.realhouse.models.House;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.os.Bundle;

import com.example.guest.realhouse.adapters.HouseListAdapter;
import com.example.guest.realhouse.constants.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.guest.realhouse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHousesFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DatabaseReference mHouseReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    public MyHousesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_houses, container, false);
        ButterKnife.bind(this, view);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mHouseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_SAVED_HOUSES)
                .child(uid);

        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new HouseListAdapter(House.class, R.layout.house_list_item,
                FirebaseHouseViewHolder.class, mHouseReference, getActivity()) {

            @Override
            protected void populateViewHolder(FirebaseHouseViewHolder viewHolder,
                                              House model, int position) {
                viewHolder.bindHouse(model);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
