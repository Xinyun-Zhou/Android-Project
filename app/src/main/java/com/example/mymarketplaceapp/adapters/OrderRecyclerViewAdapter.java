package com.example.mymarketplaceapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.Order;
import com.example.mymarketplaceapp.models.UserSession;

import java.util.List;


/**
 * Order Recycler View Adapter
 * Implement the recycler views for orders
 *
 * @author u7366711 Yuxuan Zhao
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Order> orderList;

    public OrderRecyclerViewAdapter(Context context, List<Order> orderList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.orderList = orderList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public OrderRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_order, parent, false);

        return new OrderRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.typeTextView.setText(orderList.get(position).getOrderType().toString().toUpperCase());
        holder.idTextView.setText("Order id: " + orderList.get(position).getOrderId());
        holder.priceTextView.setText("Order value: " + orderList.get(position).getOrderValue());
        if (orderList.get(position).getOrderType().equals(Order.Type.SELL))
            holder.buyerTextView.setText("Order From " + orderList.get(position).getBuyerName());
        else
            holder.buyerTextView.setText("");

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView typeTextView, idTextView, priceTextView, buyerTextView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_rvro);
            typeTextView = (TextView) itemView.findViewById(R.id.tv_rvro_type);
            idTextView = (TextView) itemView.findViewById(R.id.tv_rvro_id);
            priceTextView = (TextView) itemView.findViewById(R.id.tv_rvro_price);
            buyerTextView = (TextView) itemView.findViewById(R.id.tv_rvro_buyer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
