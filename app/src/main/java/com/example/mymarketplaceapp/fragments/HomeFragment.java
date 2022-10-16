package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.Category;

/**
 *  Home Fragment
 *
 * @author u7366711 Yuxuan Zhao
 */
public class HomeFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        setListeners();
    }

    private void setListeners() {
        Button categoryButton0 = (Button) view.findViewById(R.id.bt_home_category_0);
        categoryButton0.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton1 = (Button) view.findViewById(R.id.bt_home_category_1);
        categoryButton1.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton2 = (Button) view.findViewById(R.id.bt_home_category_2);
        categoryButton2.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton3 = (Button) view.findViewById(R.id.bt_home_category_3);
        categoryButton3.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton4 = (Button) view.findViewById(R.id.bt_home_category_4);
        categoryButton4.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton5 = (Button) view.findViewById(R.id.bt_home_category_5);
        categoryButton5.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton6 = (Button) view.findViewById(R.id.bt_home_category_6);
        categoryButton6.setOnClickListener(categoryButtonOnClickListener);
        Button categoryButton7 = (Button) view.findViewById(R.id.bt_home_category_7);
        categoryButton7.setOnClickListener(categoryButtonOnClickListener);
    }

    private View.OnClickListener categoryButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            switch (v.getId()) {
                case R.id.bt_home_category_0:
                    bundle.putInt("Category", 0);
                    break;
                case R.id.bt_home_category_1:
                    bundle.putInt("Category", 1);
                    break;
                case R.id.bt_home_category_2:
                    bundle.putInt("Category", 2);
                    break;
                case R.id.bt_home_category_3:
                    bundle.putInt("Category", 3);
                    break;
                case R.id.bt_home_category_4:
                    bundle.putInt("Category", 4);
                    break;
                case R.id.bt_home_category_5:
                    bundle.putInt("Category", 5);
                    break;
                case R.id.bt_home_category_6:
                    bundle.putInt("Category", 6);
                    break;
                case R.id.bt_home_category_7:
                    bundle.putInt("Category", 7);
                default:
                    break;
            }
            ItemListFragment itemListFragment = new ItemListFragment();
            itemListFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, itemListFragment).commit();
        }
    };
}