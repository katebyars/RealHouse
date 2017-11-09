package com.example.guest.realhouse.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.realhouse.R;
import com.example.guest.realhouse.constants.Constants;
import com.example.guest.realhouse.models.House;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAHouseFragment extends Fragment implements View.OnClickListener {
    private String mOwner;
    private String mStreetNumber;
    private String mStreetName;
    private String mCity;
    private String mState;
    private String mZip;
    private String mImage;

    @BindView(R
            .id.saveHouseButton)
    Button mSaveHouseButton;

    @BindView(R.id.ownerEditText)
    EditText mOwnerEditText;

    @BindView(R.id.streetNumberEditText)
    EditText mStreetNumberEditText;

    @BindView(R.id.streetNameEditText)
    EditText mStreetNameEditText;

    @BindView(R.id.cityEditText)
    EditText mCityEditText;

    @BindView(R.id.stateEditText)
    EditText mStateEditText;

    @BindView(R.id.zipEditText)
    EditText mZipEditText;

    @BindView(R.id.imageURLEditText)
    EditText mImageURLEditText;

    private House mHouse;

    private ProgressDialog mAuthProgressDialog;
    private String mName;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    public ListAHouseFragment() {
        // Required empty public constructor
    }


    public static ListAHouseFragment newInstance(House house) {
        ListAHouseFragment houseDetailFragment = new ListAHouseFragment();
        Bundle args = new Bundle();
        args.putParcelable("house", Parcels.wrap(house));
        houseDetailFragment.setArguments(args);
        return houseDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_a_house, container, false);
        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();
        createAuthStateListener();;

        mSaveHouseButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveHouseButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            createNewHouse();

            DatabaseReference houseRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_SAVED_HOUSES)
                    .child(uid);

            DatabaseReference pushRef = houseRef.push();
            String pushId = pushRef.getKey();
            mHouse.setPushId(pushId);
            houseRef.push().setValue(mHouse);

            Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
        }
        mAuthProgressDialog.dismiss();
        android.app.FragmentManager manager = getFragmentManager();
        MyHousesFragment myHousesFragment = new MyHousesFragment();
        manager.beginTransaction().replace(R.id.content, myHousesFragment, myHousesFragment.getTag()).commit();
    }


    private void createNewHouse() {
        mOwner = mOwnerEditText.getText().toString().trim();
        mStreetNumber = mStreetNumberEditText.getText().toString().trim();
        mStreetName = mStreetNameEditText.getText().toString().trim();
        mCity = mCityEditText.getText().toString().trim();
        mState = mStateEditText.getText().toString().trim();
        mZip = mZipEditText.getText().toString().trim();
        mImage = mImageURLEditText.getText().toString().trim();

        boolean validOwner = isValidOwner(mOwner);
        boolean validStreetNumber = isValidStreetNumber(mStreetNumber);
        boolean validStreetName = isValidStreetName(mStreetName);
        boolean validCity = isValidCity(mCity);
        boolean validState = isValidState(mState);
        boolean validZip = isValidZip(mZip);
        boolean validImage = isValidImage(mImage);

        if (!validStreetNumber || !validOwner || !validStreetName || !validCity || !validState || !validZip || !validImage ) return;

        mAuthProgressDialog.show();

        mHouse = new House(mOwner, mStreetNumber, mStreetName, mCity, mState, mZip, mImage);

        }

    private boolean isValidOwner(String owner) {
        if (owner.equals("")) {
            mOwnerEditText.setError("Please enter the owner of the house.");
            return false;
        }
        return true;
    }

    private boolean isValidStreetNumber(String streetNumber) {
        if (streetNumber.equals("")) {
            mStreetNumberEditText.setError("Enter your info here");
            return false;
        }
        return true;
    }
    private boolean isValidStreetName(String streetName) {
        if (streetName.equals("")) {
            mStreetNameEditText.setError("Enter your info here");
            return false;
        }
        return true;
    }
    private boolean isValidCity(String city) {
        if (city.equals("")) {
            mCityEditText.setError("Enter your info here");
            return false;
        }
        return true;
    }
    private boolean isValidState(String state) {
        if (state.equals("")) {
            mStateEditText.setError("Enter your info here");
            return false;
        }
        return true;
    }
    private boolean isValidZip(String zip) {
        if (zip.equals("")) {
            mZipEditText.setError("Enter your info here");
            return false;
        }
        return true;
    }
    private boolean isValidImage(String image) {
        if (image.equals("")) {
            mImageURLEditText.setError("Enter your info here");
            return false;
        }
        return true;
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(getActivity());
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }


    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}


