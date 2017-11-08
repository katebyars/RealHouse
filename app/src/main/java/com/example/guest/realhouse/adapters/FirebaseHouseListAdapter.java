package com.example.guest.realhouse.adapters;

import android.content.Context;
import com.example.guest.realhouse.models.House;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import java.util.ArrayList;


public class FirebaseHouseListAdapter extends FirebaseRecyclerAdapter <House, FirebaseHouseViewHolder> {
    private DatabaseReference mRef;
    private ArrayList<House> mHouses = new ArrayList<>();
    private Context mContext;

    public FirebaseHouseListAdapter(Class<House> modelClass, int modelLayout,
                                    Class<FirebaseHouseViewHolder> viewHolderClass,
                                    Query ref, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseHouseViewHolder viewHolder, House model, int position) {
        viewHolder.bindHouse(model);

        }
}
