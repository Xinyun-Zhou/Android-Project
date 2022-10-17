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

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.adapters.RecyclerViewAdapter;
import com.example.mymarketplaceapp.models.Category;
import com.example.mymarketplaceapp.models.ItemDao;

import java.util.Locale;

/**
 * Item List Fragment
 *
 * @author u7366711 Yuxuan Zhao
 */
public class ItemListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        int categoryIndex = bundle.getInt("Category");

        ItemDao itemDao = ItemDao.getInstance();

        RecyclerView recyclerView = view.findViewById(R.id.rv_item_list);

        // AVL tree test
        //RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), itemDao.getAllItemsAVL().inOrder());

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), itemDao.getCategoryItems(Category.values()[categoryIndex]), false);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}