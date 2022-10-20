package com.example.mymarketplaceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.*;
import com.example.mymarketplaceapp.fragments.*;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private NavigationBarView navigationBarView;
    private Fragment homeFragment = new HomeFragment();
    private Fragment loginFragment = new LoginFragment();
    private Fragment personFragment = new PersonFragment();
    private Fragment cartFragment = new CartFragment();
    private Fragment sellingFragment = new SellingFragment();
    private Fragment loginNotificationFragment = new LoginNotificationFragment();

    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getBaseContext());

        initView(savedInstanceState);
        userSession = new UserSession();

        // Pass userSession to other fragments
        Bundle bundle = new Bundle();
        bundle.putParcelable("userSession", userSession);
        homeFragment.setArguments(bundle);
        loginFragment.setArguments(bundle);
        personFragment.setArguments(bundle);
        cartFragment.setArguments(bundle);
        loginNotificationFragment.setArguments(bundle);
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
         * @author u7366711 Yuxuan Zhao, u7326123 Rita Zhou
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
                    return true;
                case R.id.person:
                    if (userSession.getUserState() instanceof NoSessionState)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit();
                    else
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, personFragment).commit();
                    return true;
                case R.id.cart:
                    if (userSession.getUserState() instanceof NoSessionState)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, loginNotificationFragment).commit();
                    else
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, cartFragment).commit();
                    return true;
                case R.id.menu:
                    if (userSession.getUserState() instanceof NoSessionState)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, loginNotificationFragment).commit();
                    else
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, sellingFragment).commit();
                    return true;
                default:
                    return false;
            }
        }
    };
}