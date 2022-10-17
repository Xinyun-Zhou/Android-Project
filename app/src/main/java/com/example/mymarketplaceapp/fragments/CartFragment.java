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

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.adapters.RecyclerViewAdapter;
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Category;
import com.example.mymarketplaceapp.models.Item;
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
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        Bundle bundle = getArguments();
        userSession = bundle.getParcelable("userSession");
        String uid = userSession.getUser().getUid() + "";

        List<Item> itemList = new ArrayList<>();
        List<CartItem> itemRequired = new ArrayList<>();

        // read the data from the database
        DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("user");
        DatabaseReference items = FirebaseDatabase.getInstance().getReference().child("item");
        // find the current user's cart item
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot currentUser : snapshot.getChildren()){
                        if (currentUser.child("uid").getValue().toString().equals(uid)){
                            DataSnapshot cartPath = currentUser.child("cart");
                            for (DataSnapshot cart : cartPath.getChildren()){
                                int id = Integer.parseInt(cart.child("id").getValue().toString());
                                int quantity = Integer.parseInt(cart.child("quantity").getValue().toString());
                                CartItem cartItem = new CartItem(id, quantity);
                                itemRequired.add(cartItem);
                            }
                        }
                    }
                    // find the detail about these item in the cart
                    if (itemRequired.size() > 0){
                        items.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot currentItem : dataSnapshot.getChildren()) {
                                        int currentItemId = Integer.parseInt(currentItem.child("id").getValue().toString());
                                        for (int i = 0; i < itemRequired.size(); i++) {
                                            if (currentItemId == itemRequired.get(i).getCartItemId()) {
                                                String itemName = currentItem.child("name").getValue().toString();
                                                int itemPrice = Integer.parseInt(currentItem.child("price").getValue().toString());
                                                int itemQuantity = Integer.parseInt(currentItem.child("quantity").getValue().toString());
                                                int itemSellerUid = Integer.parseInt(currentItem.child("sellerUid").getValue().toString());
                                                Category itemCategory = Category.valueOf(currentItem.child("category").getValue().toString());
                                                String itemDescription = currentItem.child("description").getValue().toString();
                                                Item newItem = new Item(currentItemId, itemName, itemPrice, itemQuantity, itemSellerUid, itemCategory, itemDescription);
                                                itemList.add(newItem);
                                            }
                                        }
                                    }
                                }
                                RecyclerView recyclerView = view.findViewById(R.id.rt_cart_list);
                                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(view.getContext(), itemList);
                                recyclerView.setAdapter(recyclerViewAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println("Can't find the value from the database");
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Can't find the value from the database");
            }
        });

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