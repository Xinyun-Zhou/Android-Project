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
import com.example.mymarketplaceapp.adapters.RecyclerViewInterface;
import com.example.mymarketplaceapp.models.Category;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.UserSession;
import com.example.mymarketplaceapp.utils.Query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Item List Fragment
 * Implement a draggable item list
 *
 * @author u7366711 Yuxuan Zhao
 */
public class ItemListFragment extends Fragment implements RecyclerViewInterface {
    private List<Item> itemList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    UserSession userSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Category or Query from other fragments
        Bundle bundle = getArguments();
        int categoryIndex = bundle.getInt("Category");
        userSession = bundle.getParcelable("userSession");
        Query query = bundle.getParcelable("Query");

        // Select items
        ItemDao itemDao = ItemDao.getInstance();

        itemList = query != null ? itemDao.search(query) : itemDao.getCategoryItems(Category.values()[categoryIndex]);

        for (Item item : itemList) {
            System.out.println(item.getId());
            System.out.println(item.getQuantity());
        }

        // Show item list
        recyclerView = view.findViewById(R.id.rv_item_list);
        refreshRecycleView(itemList);

        // AVL tree test
        //RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), itemDao.getAllItemsAVL().inOrder());

        // Set listener
        Button button1 = (Button) view.findViewById(R.id.bt_item_list_price_low);
        button1.setOnClickListener(sortOnClickListener);
        Button button2 = (Button) view.findViewById(R.id.bt_item_list_price_high);
        button2.setOnClickListener(sortOnClickListener);
    }

    private void refreshRecycleView(List<Item> itemList) {
        recyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), itemList, false, null, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private View.OnClickListener sortOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // sort by price: low to high
                case R.id.bt_item_list_price_low:
                    Collections.sort(itemList, new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            if (o1.getPrice() > o2.getPrice())
                                return 1;
                            else if (o1.getPrice() < o2.getPrice())
                                return -1;
                            else {
                                if (o1.getId() > o2.getId())
                                    return 1;
                                else
                                    return -1;
                            }
                        }
                    });
                    break;
                // sort by price: high to low
                case R.id.bt_item_list_price_high:
                    Collections.sort(itemList, new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            if (o1.getPrice() > o2.getPrice())
                                return -1;
                            else if (o1.getPrice() < o2.getPrice())
                                return 1;
                            else {
                                if (o1.getId() > o2.getId())
                                    return 1;
                                else
                                    return -1;
                            }
                        }
                    });
                    break;
                default:
                    return;
            }
            // refresh recycler view
            refreshRecycleView(itemList);
        }
    };

    @Override
    public void onItemClick(int position) {
        // Pass the id of clicked item
        Bundle bundle = new Bundle();
        bundle.putInt("itemId", itemList.get(position).getId());
        bundle.putParcelable("userSession", userSession);
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, itemDetailFragment).commit();
    }
}