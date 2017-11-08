package com.example.guest.realhouse.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.guest.realhouse.models.House;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.guest.realhouse.models.House;
//import com.example.guest.realhouse.util.ItemTouchHelperAdapter;
//import com.example.guest.realhouse.util.OnStartDragListener;
import com.example.guest.realhouse.util.ItemTouchHelperAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseHouseListAdapter extends FirebaseRecyclerAdapter <House, FirebaseHouseViewHolder> implements ItemTouchHelperAdapter {

    private ChildEventListener mChildEventListener;
    private ArrayList<House> mHouses = new ArrayList<>();
    private DatabaseReference mRef;
    private Context mContext;

    public FirebaseHouseListAdapter(Class<House> modelClass, int modelLayout,
                                    Class<FirebaseHouseViewHolder> viewHolderClass,
                                    Query ref, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
//        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mHouses.add(dataSnapshot.getValue(House.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void populateViewHolder(final FirebaseHouseViewHolder viewHolder, House model, int position) {
        viewHolder.bindHouse(model);

//        viewHolder.mDragIcon.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                    mOnStartDragListener.onStartDrag(viewHolder);
//                }
//                return false;
//            }
        }
//        );

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ImageDetailActivity.class);
//                intent.putExtra("position", viewHolder.getAdapterPosition());
//                Log.d("hello", "hello");
//                Log.d("getAdapterposition", String.valueOf(viewHolder.getAdapterPosition()));
//
//                intent.putExtra("images", Parcels.wrap(mImages));
//                mContext.startActivity(intent);
//            }
//        });

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mHouses, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mHouses.remove(position);
        getRef(position).removeValue();
    }

//    private void setIndexInFirebase() {
//        for (House house : mHouses) {
//            int index = mHouses.indexOf(house);
//            DatabaseReference ref = getRef(index);
//            house.setIndex(Integer.toString(index));
//            ref.setValue(house);
//        }
//    }

//    @Override
//    public void cleanup() {
//        super.cleanup();
//        setIndexInFirebase();
//        mRef.removeEventListener(mChildEventListener);
//    }

}
