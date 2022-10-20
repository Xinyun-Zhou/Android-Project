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
    List<Item> itemListToShow;
    Double total;

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
        itemListToShow = new ArrayList<>();

        total = 0.0;
        itemList = ItemDao.getInstance().getItemList();
        if (userSession.getUser().getCart() != null) {
            for (Item item : itemList) {
                for (CartItem cartItem : userSession.getUser().getCart()) {
                    if (item.getId() == cartItem.getCartItemId()) {
                        Item newItem = new Item(item.getId(), item.getName(), item.getPrice(), item.getQuantity(), item.getSellerUid(), item.getCategory(), item.getDescription());
                        if (item.getQuantity() >= cartItem.getCartItemQuantity())
                            newItem.setQuantity(cartItem.getCartItemQuantity());
                        itemListToShow.add(newItem);
                        total += itemListToShow.get(itemListToShow.size()-1).getPrice() * itemListToShow.get(itemListToShow.size()-1).getQuantity();
                    }
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
    }
    private View.OnClickListener nextPageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (itemListToShow.size() > 0 && total > 0) {
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
        // Pass the id of clicked item
        Bundle bundle = new Bundle();
        bundle.putInt("itemId", itemListToShow.get(position).getId());
        bundle.putParcelable("userSession", userSession);
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, itemDetailFragment).commit();
    }

}