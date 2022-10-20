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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.adapters.RecyclerViewAdapter;
import com.example.mymarketplaceapp.adapters.RecyclerViewInterface;
import com.example.mymarketplaceapp.models.Cart;
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.UserSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Cart Fragment
 * Shows up current user's cart
 * The user can buy items in the cart by click the Next button
 *
 * @author u7326123 Rita Zhou
 */
public class CartFragment extends Fragment implements RecyclerViewInterface {
    private UserSession userSession;
    View view;
    List<Item> itemList;
//    List<CartItem> itemRequired;
    List<Item> itemListToShow;
//    Cart cart;

    Button btNext;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ImageButton btPlus;
    ImageButton btMinus;
    TextView noItemShow;

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
//        int uid = userSession.getUser().getUid();
//        String uidStr = String.valueOf(uid);

//        itemList = new ArrayList<>();
//        itemRequired = new ArrayList<>();
        itemListToShow = new ArrayList<>();

        itemList = ItemDao.getInstance().getItemList();
<<<<<<< HEAD
        if (userSession.getUser().getCart() != null) {
            for (Item item : itemList) {
                for (CartItem cartItem : userSession.getUser().getCart()) {
                    if (item.getId() == cartItem.getCartItemId()) {
                        Item newItem = item;
                        newItem.setQuantity(cartItem.getCartItemQuantity());
                        itemListToShow.add(newItem);
                    }
=======
        for (Item item : itemList){
            for (CartItem cartItem : userSession.getUser().getCart()){
                if (item.getId() == cartItem.getCartItemId()){
                    Item newItem = new Item(item.getId(),item.getName(),item.getPrice(),item.getQuantity(),item.getSellerUid(),item.getCategory(),item.getDescription());
                    newItem.setQuantity(cartItem.getCartItemQuantity());
                    itemListToShow.add(newItem);
>>>>>>> 0970164c81cdf9a8114d28f8accaea30517fbc01
                }
            }
        }

        if (itemListToShow.size() == 0){
            noItemShow = view.findViewById(R.id.tv_cart_no_item);
            noItemShow.setVisibility(View.VISIBLE);
        }

        btNext = view.findViewById(R.id.bt_cart_next);
        btNext.setOnClickListener(nextPageOnClickListener);

        btPlus = view.findViewById(R.id.btn_plus_item);
        btMinus = view.findViewById(R.id.btn_minus_item);

        recyclerView = view.findViewById(R.id.rt_cart_list);
        recyclerViewAdapter = new RecyclerViewAdapter(view.getContext(), itemListToShow, true, userSession, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

//         //read the data from the database
//        DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("user").child(uidStr);
//        users.addValueEventListener(findUsersValueEventListener);
    }
    private View.OnClickListener nextPageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (itemListToShow.size() > 0) {
                CartPayFragment cartPayFragment = new CartPayFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("userSession", userSession);
                cartPayFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, cartPayFragment).commit();
            }
        }
    };

    @Override
    public void onItemClick(int position) {
    }

//    private ValueEventListener findUsersValueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if (snapshot.exists()){
//                int currentUserUid = Integer.parseInt(snapshot.child("uid").getValue().toString());
//                String username = snapshot.child("username").getValue().toString();
//                String password = snapshot.child("password").getValue().toString();
//                String postcode = snapshot.child("postcode").getValue().toString();
//                String address = snapshot.child("address").getValue().toString();
//                int phone = Integer.parseInt(snapshot.child("phone").getValue().toString());
//                DataSnapshot cartPath = snapshot.child("cart");
//                for (DataSnapshot cart : cartPath.getChildren()) {
//                    int id = Integer.parseInt(cart.child("id").getValue().toString());
//                    int quantity = Integer.parseInt(cart.child("quantity").getValue().toString());
//                    CartItem cartItem = new CartItem(id, quantity);
//                    itemRequired.add(cartItem);
//                }
//                userDetail = new User(currentUserUid, username, password, postcode, address, phone, itemRequired);
//            }
//            if (itemRequired.size() > 0) {
//                DatabaseReference items = FirebaseDatabase.getInstance().getReference().child("item");
//                items.addValueEventListener(findItemsValueEventListener);
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//        }
//    };

//    private ValueEventListener findItemsValueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            if (dataSnapshot.exists()) {
//                for (DataSnapshot currentItem : dataSnapshot.getChildren()) {
//                    int currentItemId = Integer.parseInt(currentItem.child("id").getValue().toString());
//                    for (int i = 0; i < itemRequired.size(); i++) {
//                        if (currentItemId == itemRequired.get(i).getCartItemId()) {
//                            String itemName = currentItem.child("name").getValue().toString();
//                            double itemPrice = Integer.parseInt(currentItem.child("price").getValue().toString());
//                            int itemQuantity = Integer.parseInt(currentItem.child("quantity").getValue().toString());
//                            int itemSellerUid = Integer.parseInt(currentItem.child("sellerUid").getValue().toString());
//                            Category itemCategory = Category.valueOf(currentItem.child("category").getValue().toString());
//                            String itemDescription = currentItem.child("description").getValue().toString();
//                            Item newItem = new Item(currentItemId, itemName, itemPrice, itemQuantity, itemSellerUid, itemCategory, itemDescription);
//                            itemList.add(newItem);
//                            Item showItem = new Item(currentItemId, itemName, itemPrice, itemRequired.get(i).getCartItemQuantity(), itemSellerUid, itemCategory, itemDescription);
//                            itemListToShow.add(showItem);
//                        }
//                    }
//                }
//
//                for (Item item : itemList) {
//                    for (CartItem cartItem : itemRequired) {
//                        if (item.getId() == cartItem.getCartItemId())
//                            totalAmount += (item.getPrice() * cartItem.getCartItemQuantity());
//                    }
//                }
//
//                cart = new Cart(userDetail, itemList, totalAmount);
//
//                totalText.setText("TOTAL : $" + totalAmount);
//                recyclerViewAdapter.notifyDataSetChanged();
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//        }
//    };

}