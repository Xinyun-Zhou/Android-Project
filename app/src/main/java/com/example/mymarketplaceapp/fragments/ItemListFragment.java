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
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.utils.Query;

import java.util.List;
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
        Query query = bundle.getParcelable("Query");

        ItemDao itemDao = ItemDao.getInstance();

        List<Item> itemList = query != null ? itemDao.search(query) : itemDao.getCategoryItems(Category.values()[categoryIndex]);

        for (Item item : itemList){
            System.out.println(item.getId());
            System.out.println(item.getQuantity());
        }

        RecyclerView recyclerView = view.findViewById(R.id.rv_item_list);

        // AVL tree test
        //RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), itemDao.getAllItemsAVL().inOrder());

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), itemList, false, null);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}