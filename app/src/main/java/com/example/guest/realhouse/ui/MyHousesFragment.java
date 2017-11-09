package com.example.guest.realhouse.ui;
import com.example.guest.realhouse.R;
import com.example.guest.realhouse.adapters.FirebaseHouseViewHolder;
import com.example.guest.realhouse.models.House;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.app.Fragment;
import android.os.Bundle;

import com.example.guest.realhouse.adapters.FirebaseHouseListAdapter;
import com.example.guest.realhouse.constants.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.Query;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHousesFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DatabaseReference mHouseReference;
    private FirebaseHouseListAdapter mFirebaseAdapter;

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

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_SAVED_HOUSES)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseHouseListAdapter(House.class,
                R.layout.house_list_item, FirebaseHouseViewHolder.class,
                query, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}