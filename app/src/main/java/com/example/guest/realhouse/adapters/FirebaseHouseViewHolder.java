package com.example.guest.realhouse.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.realhouse.R;
import com.example.guest.realhouse.models.House;


public class FirebaseHouseViewHolder extends RecyclerView.ViewHolder {

    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    View mView;
    Context mContext;
    public ImageView mDragIcon;

    public FirebaseHouseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

        mContext = itemView.getContext();
    }

    public void bindHouse(House house) {
//        mDragIcon = (ImageView) mView.findViewById(R.id.dragIcon);
//        ImageView savedImage = (ImageView) mView.findViewById(imageImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.fragmentHouseDetailOwnerNameTextView);
        TextView websiteTextView = (TextView) mView.findViewById(R.id.fragmentHouseDetailAddressTextView);

//        Picasso.with(mContext)
//                .load(house.getImageUrl())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(savedImage);

//        nameTextView.setText(image.getImagePhotographerUserName());
//        websiteTextView.setText(image.getImageWebsiteLabel());


    }



}
