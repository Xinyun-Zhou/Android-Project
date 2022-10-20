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
import com.example.mymarketplaceapp.models.UserSession;

import java.util.List;


/**
 * Recycler View Adapter
 * Implement the recycler views for shopping cart and item list
 *
 * @author u7366711 Yuxuan Zhao, u7326123 Rita Zhou
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    // set recyclerViewInterface to help click on itme
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Item> itemList;
    boolean hasQuantity;
    UserSession userSession;

    public RecyclerViewAdapter(Context context, List<Item> itemList, boolean hasQuantity, UserSession userSession, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.itemList = itemList;
        this.hasQuantity = hasQuantity;
        this.userSession = userSession;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.nameTextView.setText(itemList.get(position).getName());
        // price to two decimal places
        holder.priceTextView.setText("$ " + String.format("%.2f", itemList.get(position).getPrice()));

        // Recycler View rows in cart fragment has quantity
        if (hasQuantity) {
            Item item = itemList.get(position);
            holder.quantityTextView.setVisibility(View.VISIBLE);
            holder.quantityTextView.setText(item.getQuantity() + "");
            holder.btPlus.setVisibility(View.VISIBLE);
            holder.btMinus.setVisibility(View.VISIBLE);

            holder.btPlus.setOnClickListener(view -> {
                plusCartItem(holder, itemList.get(position));
            });

            holder.btMinus.setOnClickListener(view -> {
                minusCartItem(holder, itemList.get(position));
            });
        }
    }

    /**
     * The selected item's quantity plus one
     * @param holder make change in view
     * @param getItem the item change quantity
     * @author u7326123 Rita Zhou
     */
    private void plusCartItem(MyViewHolder holder, Item getItem) {
        //Find the item with the maximum quantity
        List<Item> allItem = ItemDao.getInstance().getItemList();
        for (Item item : allItem) {
            if (item.getId() == getItem.getId()) {
                int getStockForItem = item.getQuantity();

                // comparing the quantity and the maximum
                for (CartItem cartItem : userSession.getUser().getCart()) {
                    if (cartItem.getCartItemId() == getItem.getId()) {

                        // this item is out of stock
                        if (getStockForItem == 0) {
                            getItem.setQuantity(0);
                            cartItem.setCartItemQuantity(0);
                            holder.quantityTextView.setText(cartItem.getCartItemQuantity() + "");
                            Toast.makeText(context, "No stock for this item", Toast.LENGTH_LONG).show();
                        }

                        // the stock is not enough for user
                        else if (getStockForItem < getItem.getQuantity()) {
                            getItem.setQuantity(getStockForItem);
                            cartItem.setCartItemQuantity(getStockForItem);
                            holder.quantityTextView.setText(cartItem.getCartItemQuantity() + "");
                            Toast.makeText(context, "Stock not enough", Toast.LENGTH_LONG).show();
                        }

                        // the selected quantity is get maximum
                        else if (getStockForItem == getItem.getQuantity()) {
                            Toast.makeText(context, "You get maximum of this item", Toast.LENGTH_LONG).show();
                        }

                        // else, plus one for the current quantity
                        else {
                            getItem.setQuantity(getItem.getQuantity() + 1);
                            cartItem.setCartItemQuantity(getItem.getQuantity());
                            holder.quantityTextView.setText(cartItem.getCartItemQuantity() + "");
                        }
                    }
                }
            }
        }
    }

    /**
     * The selected items quantity minus one
     * @param holder make change in view
     * @param getItem the item change quantity
     * @author u7326123 Rita Zhou
     */
    private void minusCartItem(MyViewHolder holder, Item getItem) {
        // check if the selected item's quantity is greater than stock
        List<Item> allItem = ItemDao.getInstance().getItemList();
        for (Item item : allItem) {
            if (item.getId() == getItem.getId()) {
                int getStockForItem = item.getQuantity();

                // comparing the stock and quantity
                for (CartItem cartItem : userSession.getUser().getCart()) {
                    if (cartItem.getCartItemId() == getItem.getId()) {

                        // the quantity can't be a negative number
                        if (getItem.getQuantity() == 0) {
                            Toast.makeText(context, "Please choose a number more than 0", Toast.LENGTH_LONG).show();
                        }

                        // if the stock is not enough
                        else if (getStockForItem < getItem.getQuantity()) {
                            getItem.setQuantity(getStockForItem);
                            cartItem.setCartItemQuantity(getStockForItem);
                            holder.quantityTextView.setText(cartItem.getCartItemQuantity() + "");
                            Toast.makeText(context, "Stock not enough", Toast.LENGTH_LONG).show();
                        }

                        // if the item is out of stock
                        else if (getStockForItem == 0) {
                            getItem.setQuantity(0);
                            cartItem.setCartItemQuantity(0);
                            holder.quantityTextView.setText(cartItem.getCartItemQuantity() + "");
                            Toast.makeText(context, "This item is out of stock", Toast.LENGTH_LONG).show();
                        }

                        // else, the item's quantity minus 1
                        else {
                            getItem.setQuantity(getItem.getQuantity() - 1);
                            cartItem.setCartItemQuantity(getItem.getQuantity());
                            holder.quantityTextView.setText(cartItem.getCartItemQuantity() + "");
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, priceTextView, quantityTextView;
        ImageButton btMinus, btPlus;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_rvr);
            nameTextView = (TextView) itemView.findViewById(R.id.tv_rvr_name);
            priceTextView = (TextView) itemView.findViewById(R.id.tv_rvr_price);
            quantityTextView = (TextView) itemView.findViewById(R.id.tv_rvr_quantity);

            btMinus = itemView.findViewById(R.id.btn_minus_item);
            btPlus = itemView.findViewById(R.id.btn_plus_item);

            // set item click listener
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
