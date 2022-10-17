package com.example.mymarketplaceapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.Item;

import java.util.List;


/**
 * Recycler View Adapter
 *
 * @author u7366711 Yuxuan Zhao
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Item> itemList;
    boolean hasQuantity;

    public RecyclerViewAdapter(Context context, List<Item> itemList, boolean hasQuantity) {
        this.context = context;
        this.itemList = itemList;
        this.hasQuantity = hasQuantity;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.nameTextView.setText(itemList.get(position).getName());
        holder.priceTextView.setText("$ " + String.format("%.2f", itemList.get(position).getPrice()));
        if (hasQuantity)
            holder.quantityTextView.setText("Quantity: " + itemList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, priceTextView, quantityTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_rvr);
            nameTextView = (TextView) itemView.findViewById(R.id.tv_rvr_name);
            priceTextView = (TextView) itemView.findViewById(R.id.tv_rvr_price);
            quantityTextView = (TextView) itemView.findViewById(R.id.tv_rvr_quantity);
        }
    }
}
