package com.example.mymarketplaceapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.browse.MediaBrowser;
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
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.User;
import com.example.mymarketplaceapp.models.UserSession;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Pay the cart Fragment
 * The user can finish the payment in this page
 *
 * @author u7326123 Rita Zhou
 */
public class CartPayFragment extends Fragment {
    View view;
    private UserSession userSession;
    private List<Item> itemList;

    EditText inputName;
    EditText inputAddress;
    EditText inputPostcode;
    EditText inputPhone;
    TextView totalAmount;

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
        userSession = bundle.getParcelable("userSession");
        itemList = ItemDao.getInstance().getAllItems();
        int total = 0;
        for (Item item : itemList){
            for (CartItem cartItem : userSession.getUser().getCart()){
                if (item.getId() == cartItem.getCartItemId()){
                    total += cartItem.getCartItemQuantity() * item.getPrice();
                }
            }
        }

        inputName = view.findViewById(R.id.et_type_name);
        inputName.setText(userSession.getUser().getUsername());

        inputAddress = view.findViewById(R.id.et_type_address);
        inputAddress.setText(userSession.getUser().getAddress());

        inputPostcode = view.findViewById(R.id.et_type_postcode);
        inputPostcode.setText(String.valueOf(userSession.getUser().getPostcode()));

        inputPhone = view.findViewById(R.id.et_type_phone);

        totalAmount = view.findViewById(R.id.tv_total_pay);
        totalAmount.setText("TOTAL: $" + total);

        Button buttonPay = view.findViewById(R.id.bt_cart_pay);
        buttonPay.setOnClickListener(payOnClickListener);

    }

    public View.OnClickListener payOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
<<<<<<< HEAD
            if (inputName.length() == 0 || inputAddress.length() == 0 || inputPostcode.length() == 0 || inputPhone.length() == 0){
=======
            if (inputName != null && inputAddress != null && inputPostcode != null && inputPhone != null) {

            } else {
>>>>>>> 157124b41a2c3455ac74a86d278d547793d21db7
                Toast.makeText(view.getContext(), "Please fill out your detail", Toast.LENGTH_LONG).show();
            }
            // TODO: Fix Data Updates
            else{
                for (Item item : itemList){
                    for (CartItem cartItem : userSession.getUser().getCart()){
                        if (item.getId() == cartItem.getCartItemId()){
                            System.out.println(cartItem.getCartItemQuantity());
                            System.out.println(item.getQuantity());
                            item.setQuantity(item.getQuantity() - cartItem.getCartItemQuantity());
                            System.out.println(item.getQuantity());
                        }
                    }
                }
                System.out.println(itemList.get(3).getQuantity());
                userSession.getUser().setCart(new ArrayList<>());
                showDialog();
            }
        }
    };

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Payment Complete!");
        builder.setMessage("Thank you!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CartFragment cartFragment = new CartFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("userSession", userSession);
                cartFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, cartFragment).commit();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}