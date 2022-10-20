package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.activities.MainActivity;
import com.example.mymarketplaceapp.models.Category;
import com.example.mymarketplaceapp.models.UserSession;
import com.example.mymarketplaceapp.utils.Parser;
import com.example.mymarketplaceapp.utils.Query;
import com.example.mymarketplaceapp.utils.Token;
import com.example.mymarketplaceapp.utils.Tokenizer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Home Fragment
 * Implement interaction on the home page
 *
 * @author u7366711 Yuxuan Zhao
 */
public class HomeFragment extends Fragment {
    View view;
    UserSession userSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        userSession = bundle.getParcelable("userSession");

        this.view = view;
        setListeners();
    }

    private void setListeners() {
        // category buttons
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

        // search box
        TextInputEditText textInputEditText = (TextInputEditText) view.findViewById(R.id.et_home_search);
        textInputEditText.setOnEditorActionListener(searchOnEditorActionListener);
    }

    private View.OnClickListener categoryButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("userSession", userSession);
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
            // Pass chosen category to item list fragment
            ItemListFragment itemListFragment = new ItemListFragment();
            itemListFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, itemListFragment).commit();
        }
    };

    private TextView.OnEditorActionListener searchOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Parse query
                Query query = parseQuery(v.getText().toString());
                if (query == null)
                    return false;

                // Check query
                String message = query.checkQuery();
                if (message != "") {
                    v.setText("");
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    return false;
                }

                // Pass query to item list fragment
                Bundle bundle = new Bundle();
                bundle.putParcelable("Query", query);
                ItemListFragment itemListFragment = new ItemListFragment();
                itemListFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, itemListFragment).commit();

                return true;
            }

            return false;
        }
    };

    /**
     * Parse strings in the search box
     *
     * @param queryString
     * @return parsed query
     */
    private Query parseQuery(String queryString) {
        // token error
        Tokenizer tokenizer;
        try {
            tokenizer = new Tokenizer(queryString);
        } catch (Token.IllegalTokenException e) {
            Toast.makeText(getContext(), "Invalid query", Toast.LENGTH_LONG).show();
            return null;
        }
        // grammar error
        Query query;
        try {
            query = new Parser(tokenizer).parseQuery();
        } catch (Parser.IllegalProductionException e) {
            Toast.makeText(getContext(), "Invalid query", Toast.LENGTH_LONG).show();
            return null;
        }

        return query;
    }
}