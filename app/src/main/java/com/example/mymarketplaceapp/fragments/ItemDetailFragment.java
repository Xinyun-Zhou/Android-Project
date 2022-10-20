package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.User;
import com.example.mymarketplaceapp.models.UserDao;


public class ItemDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Category or Query from other fragments
        Bundle bundle = getArguments();
        int itemId = bundle.getInt("itemId");

        ItemDao itemDao = ItemDao.getInstance();
        Item item = itemDao.getItem(itemId);
        UserDao userDao = UserDao.getInstance();

        TextView nameTextView = (TextView) view.findViewById(R.id.tv_item_detail_name);
        nameTextView.setText(item.getName());
        TextView priceTextView = (TextView) view.findViewById(R.id.tv_item_detail_price);
        priceTextView.setText("$ " + String.format("%.2f", item.getPrice()));
        TextView sellerTextView = (TextView) view.findViewById(R.id.tv_item_detail_seller);
        sellerTextView.setText("Sold by " + userDao.getUsername(item.getSellerUid()));
        TextView descriptionTextView = (TextView) view.findViewById(R.id.tv_item_detail_description);
        descriptionTextView.setText("Description:" + '\n' + item.getDescription());
    }
}