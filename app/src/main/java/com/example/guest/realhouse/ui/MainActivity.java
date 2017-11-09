package com.example.guest.realhouse.ui;
import com.example.guest.realhouse.ui.ListAHouseFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.example.guest.realhouse.R;
import android.support.v4.view.MenuItemCompat;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.profile_nav:
                    return true;

                case R.id.list_a_house_nav:
                    android.app.FragmentManager manager = getFragmentManager();
                    ListAHouseFragment listAHouseFragment = new ListAHouseFragment();
                    manager.beginTransaction().replace(R.id.content, listAHouseFragment, listAHouseFragment.getTag()).commit();
                    return true;

                case R.id.my_houses_nav:
                    android.app.FragmentManager manager2 = getFragmentManager();
                    MyHousesFragment myHousesFragment = new MyHousesFragment();
                    manager2.beginTransaction().replace(R.id.content, myHousesFragment, myHousesFragment.getTag()).commit();
                    return true;

                case R.id.my_routes:
                    mTextMessage.setText("My Routes");
                    return true;

                case R.id.logout:
                    mTextMessage.setText("Logout");
                    Toast.makeText(MainActivity.this, "You are logged out.",
                            Toast.LENGTH_SHORT).show();
                    logout();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //do something//
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

