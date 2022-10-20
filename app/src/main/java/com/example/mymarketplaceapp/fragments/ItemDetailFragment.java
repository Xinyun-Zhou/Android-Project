package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.CartItem;
import com.example.mymarketplaceapp.models.Item;
import com.example.mymarketplaceapp.models.ItemDao;
import com.example.mymarketplaceapp.models.UserDao;
import com.example.mymarketplaceapp.models.UserSession;

import java.util.List;

/**
 * Item Detail Fragment
 * Show item detail
 * Click button to add item to cart or contact buyer
 *
 * @author u7366711 Yuxuan Zhao, u7326123 Rita Zhou
 */
public class ItemDetailFragment extends Fragment {
    UserSession userSession;
    Item item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get the id of clicked item
        Bundle bundle = getArguments();
        int itemId = bundle.getInt("itemId");
        userSession = bundle.getParcelable("userSession");

        // get item and user
        ItemDao itemDao = ItemDao.getInstance();
        item = itemDao.getItem(itemId);
        UserDao userDao = UserDao.getInstance();

        // set text
        TextView nameTextView = (TextView) view.findViewById(R.id.tv_item_detail_name);
        nameTextView.setText(item.getName());

        TextView priceTextView = (TextView) view.findViewById(R.id.tv_item_detail_price);
        priceTextView.setText("$ " + String.format("%.2f", item.getPrice()));

        TextView sellerTextView = (TextView) view.findViewById(R.id.tv_item_detail_seller);
        sellerTextView.setText("Sold by " + userDao.getUsername(item.getSellerUid()));

        TextView descriptionTextView = (TextView) view.findViewById(R.id.tv_item_detail_description);
        descriptionTextView.setText("Description:" + '\n' + item.getDescription());

        // contact seller
        Button chatButton = view.findViewById(R.id.bt_item_detail_contact);
        chatButton.setOnClickListener(chatOnClickListener);
        // add to cart
        Button addButton = view.findViewById(R.id.bt_item_detail_add);
        addButton.setOnClickListener(addOnClickListener);
    }

    private View.OnClickListener chatOnClickListener = new View.OnClickListener() {
        /**
         * Link to the chat page
         * Bring the information of current user and the seller
         * @author u7326123 Rita Zhou
         */
        @Override
        public void onClick(View view) {
            // if the user does not log in, tell the user should log in
            if (userSession.getUser() == null) {
                LoginNotificationFragment loginNotificationFragment = new LoginNotificationFragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, loginNotificationFragment).commit();
            }

            // the user should not send message to themselves
            else if (userSession.getUser().getUid() == item.getSellerUid()) {
                Toast.makeText(getContext(), "You can't message to yourself", Toast.LENGTH_LONG).show();
            }

            // otherwise, bring the information and jump to the message page
            else {
                MessageFragment messageFragment = new MessageFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("userSession", userSession);
                bundle.putInt("receiver", item.getSellerUid());
                messageFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, messageFragment).commit();
            }
        }
    };


    private View.OnClickListener addOnClickListener = new View.OnClickListener() {
        /**
         * Add the current item to the cart
         * @author u7326123 Rita Zhou
         */
        @Override
        public void onClick(View v) {
            // tell the user should log in before add the item in cart
            if (userSession.getUser() == null) {
                LoginNotificationFragment loginNotificationFragment = new LoginNotificationFragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, loginNotificationFragment).commit();
            }

            // can't add the item with no stock
            else if (item.getQuantity() == 0){
                Toast.makeText(getContext(), "This item is out of stock", Toast.LENGTH_LONG).show();
            }

            // can't buy the seller's own item
            else if (item.getSellerUid() == userSession.getUser().getUid()){
                Toast.makeText(getContext(), "You can't buy your own selling stuff", Toast.LENGTH_LONG).show();
            }

            // else, add to cart
            else {
                // check if the current item is already in cart
                List<Integer> cartIdList = UserDao.getInstance().cartItemsId(userSession.getUser().getUsername());
                if (cartIdList.contains(item.getId())) {
                    Toast.makeText(getContext(), "This item is already in your cart", Toast.LENGTH_LONG).show();
                }

                // if it not in cart, add item into cart
                else {
                    CartItem newCartItem = new CartItem(item.getId(), 1);
                    List<CartItem> cartList = userSession.getUser().getCart();
                    cartList.add(newCartItem);
                    userSession.getUser().setCart(cartList);
                }
            }
        }
    };
}