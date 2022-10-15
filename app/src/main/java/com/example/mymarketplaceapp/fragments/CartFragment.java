package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.UserSession;
import com.google.firebase.FirebaseApp;

/**
 * Cart Fragment
 * Shows up current user's cart
 * The user can buy items in the cart by click the Next button
 * @author Rita Zhou
 */
public class CartFragment extends Fragment {
    private UserSession userSession;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    /**
     * Get userSession from MainActivity and set listener
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        Bundle bundle = getArguments();
        userSession = bundle.getParcelable("userSession");

        Button cartNext = (Button) view.findViewById(R.id.bt_cart_next);
        cartNext.setOnClickListener(cartNextOnClickListener);
    }

    private View.OnClickListener cartNextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LoginFragment loginFragment = new LoginFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("userSession", userSession);
            loginFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, loginFragment).commit();
        }
    };
}