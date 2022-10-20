package com.example.mymarketplaceapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.OrderManager;
import com.example.mymarketplaceapp.models.UserDao;
import com.example.mymarketplaceapp.models.UserSession;

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
        itemList = ItemDao.getInstance().getItemList();

        // calculate the total cost and display on the view
        double total = 0;
        for (Item item : itemList){
            for (CartItem cartItem : userSession.getUser().getCart()){
                if (item.getId() == cartItem.getCartItemId()){
                    total += cartItem.getCartItemQuantity() * item.getPrice();
                }
            }
        }

        // set the default input
        totalAmount = view.findViewById(R.id.tv_total_pay);
        totalAmount.setText("Total: $ " + String.format("%.2f",total));

        inputName = view.findViewById(R.id.et_type_name);
        inputName.setText(userSession.getUser().getUsername());

        inputAddress = view.findViewById(R.id.et_type_address);
        inputAddress.setText(userSession.getUser().getAddress());

        inputPostcode = view.findViewById(R.id.et_type_postcode);
        inputPostcode.setText(String.valueOf(userSession.getUser().getPostcode()));

        inputPhone = view.findViewById(R.id.et_type_phone);

        Button buttonPay = view.findViewById(R.id.bt_cart_pay);
        buttonPay.setOnClickListener(payOnClickListener);

    }

    public View.OnClickListener payOnClickListener = new View.OnClickListener() {
        /**
         * finish the payment
         * @author u7326123 Rita Zhou, u7366711 Yuxuan Zhao
         */
        @Override
        public void onClick(View view) {
            // if any input is empty, create a toast to make a notice
            if (inputName.length() == 0 || inputAddress.length() == 0 || inputPostcode.length() == 0 || inputPhone.length() == 0){
                Toast.makeText(view.getContext(), "Please fill out your detail", Toast.LENGTH_LONG).show();
            }
            else{
                // make changes for the stock in seller
                for (Item item : itemList){
                    for (CartItem cartItem : userSession.getUser().getCart()){
                        if (item.getId() == cartItem.getCartItemId()){
                            item.setQuantity(item.getQuantity() - cartItem.getCartItemQuantity());
                        }
                    }
                }

                // create order
                OrderManager orderManager = OrderManager.getInstance();
                orderManager.createNewOrder(userSession.getUser().getUid(),userSession.getUser().getCart());

                // clear cart
                userSession.getUser().clearCart();
                UserDao userDao = UserDao.getInstance();
                userDao.searchUser(userSession.getUser().getUsername()).clearCart();
                showDialog();
            }
        }
    };

    /**
     * Create a dialog to show the payment success
     * @author u7326123 Rita Zhou
     */
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