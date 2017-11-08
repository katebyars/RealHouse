package com.example.guest.realhouse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.realhouse.R;
import com.example.guest.realhouse.constants.Constants;
import com.example.guest.realhouse.models.House;
import com.example.guest.realhouse.ui.HouseDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.parceler.Parcels;
import java.util.ArrayList;

public class FirebaseHouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    View mView;
    Context mContext;

    public FirebaseHouseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindHouse(House house) {

        TextView nameTextView = (TextView) mView.findViewById(R.id.fragmentHouseDetailOwnerNameTextView);
        TextView addressTextView = (TextView) mView.findViewById(R.id.fragmentHouseDetailAddressTextView);

        nameTextView.setText(house.getAgentFirstName());
        Log.d("first name" , house.getAgentFirstName());
        addressTextView.setText(house.getStreetName());
    }

    @Override
    public void onClick(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final ArrayList<House> houses = new ArrayList<>();
        DatabaseReference ref =
                FirebaseDatabase.getInstance()
                        .getReference(Constants.FIREBASE_SAVED_HOUSES)
                        .child(user.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    houses.add(snapshot.getValue(House.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, HouseDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("houses", Parcels.wrap(houses));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(),"Unable to retrieve data", Toast.LENGTH_LONG).show();
            }
        });
    }
}
