package com.example.guest.realhouse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.realhouse.R;
import com.example.guest.realhouse.models.House;
import com.example.guest.realhouse.ui.HouseDetailActivity;
import com.firebase.ui.database.FirebaseListAdapter;


import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;

public class HouseListAdapter extends FirebaseRecyclerAdapter <HouseListAdapter.HouseViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<House> mHouses = new ArrayList<>();
    private Context mContext;

    public HouseListAdapter(Context context, ArrayList<House> houses) {
        mContext = context;
        mHouses = houses;
    }

    @Override
    public HouseListAdapter.HouseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_list_item, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HouseListAdapter.HouseViewHolder holder, int position) {
        holder.bindHouse(mHouses.get(position));
    }

    @Override
    public int getItemCount() {
        return mHouses.size();
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @Bind(R.id.restaurantImageView)
//        ImageView mRestaurantImageView;
//        @Bind(R.id.restaurantNameTextView)
//        TextView mNameTextView;
//        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
//        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindHouse(House house) {

//
//            mNameTextView.setText(restaurant.getName());
//            mCategoryTextView.setText(restaurant.getCategories().get(0));
//            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            Intent intent = new Intent(mContext, HouseDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("houses", Parcels.wrap(mHouses));

            mContext.startActivity(intent);
        }
    }
}