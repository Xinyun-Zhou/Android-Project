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
import com.example.mymarketplaceapp.adapters.OrderRecyclerViewAdapter;
import com.example.mymarketplaceapp.adapters.RecyclerViewAdapter;
import com.example.mymarketplaceapp.adapters.RecyclerViewInterface;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.Order;
import com.example.mymarketplaceapp.models.OrderManager;
import com.example.mymarketplaceapp.models.UserSession;
import com.example.mymarketplaceapp.utils.Query;

import java.util.List;


public class OrderListFragment extends Fragment implements RecyclerViewInterface {
    private List<Order> orderList;
    private RecyclerView recyclerView;
    private OrderRecyclerViewAdapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        UserSession userSession = bundle.getParcelable("userSession");
        OrderManager orderManager = OrderManager.getInstance();
        List<Order> fullList = orderManager.getOrderList();
        List<Order> orderList = orderManager.getOrderList(userSession.getUser().getUid());

        // Show order list
        recyclerView = view.findViewById(R.id.rv_order_list);
        refreshRecycleView(orderList);
    }

    private void refreshRecycleView(List<Order> orderList) {
        recyclerViewAdapter = new OrderRecyclerViewAdapter(this.getContext(), orderList, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onItemClick(int position) {

    }
}