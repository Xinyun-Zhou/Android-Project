package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.Cart;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Pay the cart Fragment
 * The user can finish the payment in this page
 * @author u7326123 Rita Zhou
 */
public class CartPayFragment extends Fragment {
    View view;
    private Cart cart;
    EditText inputName;
    EditText inputAddress;
    EditText inputPostcode;
    EditText inputPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_pay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        Bundle bundle = getArguments();
        cart = bundle.getParcelable("cart");

        inputName = view.findViewById(R.id.et_type_name);
        inputAddress = view.findViewById(R.id.et_type_address);
        inputPostcode = view.findViewById(R.id.tv_postcode);
        inputPhone = view.findViewById(R.id.tv_phone_number);

        Button buttonPay = view.findViewById(R.id.bt_cart_pay);
        buttonPay.setOnClickListener(payOnClickListener);

    }

    public View.OnClickListener payOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (inputName != null && inputAddress != null && inputPostcode != null && inputPhone != null){

            }
            else{
                Toast.makeText(view.getContext(), "Please fill out your detail", Toast.LENGTH_LONG).show();
            }
        }
    };

}