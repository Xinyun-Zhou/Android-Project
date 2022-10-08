package com.example.mymarketplaceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.*;
import com.example.mymarketplaceapp.fragments.CartFragment;
import com.example.mymarketplaceapp.fragments.HomeFragment;
import com.example.mymarketplaceapp.fragments.MenuFragment;
import com.example.mymarketplaceapp.fragments.LoginFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private NavigationBarView navigationBarView;
    private Fragment homeFragment = new HomeFragment();
    private Fragment loginFragment = new LoginFragment();
    private Fragment cartFragment = new CartFragment();
    private Fragment menuFragment = new MenuFragment();
    private UserSession userSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(savedInstanceState);
        userSession = new UserSession();

        // Pass userSession to loginFragment
        Bundle bundle = new Bundle();
        bundle.putParcelable("userSession", userSession);
        loginFragment.setArguments(bundle);
    }

    /**
     * Initialise bottom navigation
     *
     * @param savedInstanceState
     * @author u7366711 Yuxuan Zhao
     */
    private void initView(Bundle savedInstanceState) {
        navigationBarView = (NavigationBarView) findViewById(R.id.navigationBarView);
        navigationBarView.setOnItemSelectedListener(onItemSelectedListener);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
    }

    private NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        /**
         * Switch fragments
         *
         * @param item
         * @author u7366711 Yuxuan Zhao
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
                    return true;
                case R.id.person:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit();
                    return true;
                case R.id.cart:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, cartFragment).commit();
                    return true;
                case R.id.menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, menuFragment).commit();
                    return true;
                default:
                    return false;
            }
        }
    };
}