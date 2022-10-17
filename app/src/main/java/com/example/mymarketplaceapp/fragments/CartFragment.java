package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.adapters.RecyclerViewAdapter;
import com.example.mymarketplaceapp.models.Cart;
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Category;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.User;
import com.example.mymarketplaceapp.models.UserSession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Cart Fragment
 * Shows up current user's cart
 * The user can buy items in the cart by click the Next button
 * @author u7326123 Rita Zhou
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
     * Show the user's cart
     *
     * @param view
     * @param savedInstanceState
     * @author u7326123 Rita Zhou
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        Bundle bundle = getArguments();
        userSession = bundle.getParcelable("userSession");
        int uid = userSession.getUser().getUid();

        List<Item> itemList = new ArrayList<>();
        List<CartItem> itemRequired = new ArrayList<>();
        List<Item> itemListToShow = new ArrayList<>();

        Button cartNext = (Button) view.findViewById(R.id.bt_cart_next);

        // read the data from the database
        DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("user");
        DatabaseReference items = FirebaseDatabase.getInstance().getReference().child("item");
        // find the current user's cart item
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot currentUser : snapshot.getChildren()) {
                        int currentUserUid = Integer.parseInt(currentUser.child("uid").getValue().toString());
                        if (currentUserUid == uid) {
                            String username = currentUser.child("username").getValue().toString();
                            String password = currentUser.child("password").getValue().toString();
                            String postcode = currentUser.child("postcode").getValue().toString();
                            String address = currentUser.child("address").getValue().toString();
                            int phone = Integer.parseInt(currentUser.child("phone").getValue().toString());
                            DataSnapshot cartPath = currentUser.child("cart");
                            for (DataSnapshot cart : cartPath.getChildren()) {
                                int id = Integer.parseInt(cart.child("id").getValue().toString());
                                int quantity = Integer.parseInt(cart.child("quantity").getValue().toString());
                                CartItem cartItem = new CartItem(id, quantity);
                                itemRequired.add(cartItem);
                            }
                            User userDetail = new User(currentUserUid, username, password, postcode, address, phone, itemRequired);

                            // find the detail about these item in the cart
                            if (itemRequired.size() > 0) {
                                items.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot currentItem : dataSnapshot.getChildren()) {
                                                int currentItemId = Integer.parseInt(currentItem.child("id").getValue().toString());
                                                for (int i = 0; i < itemRequired.size(); i++) {
                                                    if (currentItemId == itemRequired.get(i).getCartItemId()) {
                                                        String itemName = currentItem.child("name").getValue().toString();
                                                        double itemPrice = Integer.parseInt(currentItem.child("price").getValue().toString());
                                                        int itemQuantity = Integer.parseInt(currentItem.child("quantity").getValue().toString());
                                                        int itemSellerUid = Integer.parseInt(currentItem.child("sellerUid").getValue().toString());
                                                        Category itemCategory = Category.valueOf(currentItem.child("category").getValue().toString());
                                                        String itemDescription = currentItem.child("description").getValue().toString();
                                                        Item newItem = new Item(currentItemId, itemName, itemPrice, itemQuantity, itemSellerUid, itemCategory, itemDescription);
                                                        itemList.add(newItem);
                                                        Item showItem = new Item(currentItemId, itemName, itemPrice, itemRequired.get(i).getCartItemQuantity(), itemSellerUid, itemCategory, itemDescription);
                                                        itemListToShow.add(showItem);
                                                    }
                                                }
                                            }
                                        }
                                        int totalAmount = 0;
                                        for (Item item : itemList) {
                                            for (CartItem cartItem : itemRequired) {
                                                if (item.getId() == cartItem.getCartItemId())
                                                    totalAmount += (item.getPrice() * cartItem.getCartItemQuantity());
                                            }
                                        }

                                        Cart cart = new Cart(userDetail, itemList, totalAmount);

                                        // Set the view after getting the value
                                        cartNext.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                CartPayFragment cartPayFragment = new CartPayFragment();
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelable("cart", cart);
                                                cartPayFragment.setArguments(bundle);
                                                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, cartPayFragment).commit();
                                            }
                                        });
                                        TextView totalText = view.findViewById(R.id.tv_cart_total);
                                        totalText.setText("TOTAL : $" + totalAmount);

                                        RecyclerView recyclerView = view.findViewById(R.id.rt_cart_list);
                                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(view.getContext(), itemListToShow, true);
                                        recyclerView.setAdapter(recyclerViewAdapter);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        System.out.println("Can't find the value from the database");
                                    }
                                });
                            }
                            else{
                                cartNext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(view.getContext(), "No Item in the cart", Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Can't find the value from the database");
            }
        });
    }
}