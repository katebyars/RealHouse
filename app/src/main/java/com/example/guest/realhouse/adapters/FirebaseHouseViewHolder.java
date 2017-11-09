package com.example.guest.realhouse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.realhouse.R;
import com.example.guest.realhouse.constants.Constants;
import com.example.guest.realhouse.models.House;
import com.example.guest.realhouse.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import java.util.ArrayList;

public class FirebaseHouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private String image2;
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

        TextView ownerTextView = (TextView) mView.findViewById(R.id.fragmentHouseDetailOwnerNameTextView);
        TextView streetNumber = (TextView) mView.findViewById(R.id.fragmentHouseDetailStreetNumberTextView);
        TextView streetName = (TextView) mView.findViewById(R.id.fragmentHouseDetailStreetNameTextView);
        TextView city = (TextView) mView.findViewById(R.id.fragmentHouseDetailCityTextView);
        TextView state = (TextView) mView.findViewById(R.id.fragmentHouseDetailStateTextView);
        TextView zip = (TextView) mView.findViewById(R.id.fragmentHouseDetailZipTextView);
        ImageView image = (ImageView) mView.findViewById(R.id.fragmentHouseDetailPhotoImageView);


        ownerTextView.setText(house.getAgentFirstName());
        streetNumber.setText(house.getStreetNumber());
        streetName.setText(house.getStreetName());
        city.setText(house.getCity());
        state.setText(house.getState());
        zip.setText(house.getZip());


        image2 = house.getPhotos();

        Log.d("image", image2);

        Picasso.with(mContext)
                .load(house.getPhotos())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(image);
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

                Intent intent = new Intent(mContext, MainActivity.class);
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
