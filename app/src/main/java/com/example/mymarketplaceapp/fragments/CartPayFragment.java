package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymarketplaceapp.R;

/**
 * Pay the cart Fragment
 * The user can finish the payment in this page
 * @author Rita Zhou
 */
public class CartPayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_pay, container, false);
    }
}