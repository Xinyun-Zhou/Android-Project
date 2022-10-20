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
import com.example.mymarketplaceapp.models.UserSession;


/**
 * The Person Fragment
 * Show personal detail
 * @author u7326123 Rita Zhou
 */
public class PersonFragment extends Fragment {
    private UserSession userSession;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    /**
     * Get userSession from LoginFragment
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        Bundle bundle = getArguments();
        userSession = bundle.getParcelable("userSession");

        TextView textView = (TextView) view.findViewById(R.id.tv_person_hello);
        textView.setText("Hello, "+userSession.getUser().getUsername());

        TextView textViewName = (TextView) view.findViewById(R.id.tv_username);
        textViewName.setText("Username: \n" + userSession.getUser().getUsername());

        TextView textViewPassword = (TextView) view.findViewById(R.id.tv_password);
        textViewPassword.setText("Password: \n" + userSession.getUser().getPassword());

        TextView textViewADDRESS = (TextView) view.findViewById(R.id.tv_address);
        textViewADDRESS.setText("Address: \n" + userSession.getUser().getAddress());

        TextView textViewPostcode = (TextView) view.findViewById(R.id.tv_postcode);
        textViewPostcode.setText("Postcode: \n" + userSession.getUser().getPostcode());

        TextView textViewPhoneNumber = (TextView) view.findViewById(R.id.tv_phone_number);
        textViewPhoneNumber.setText("Phone Number: \n" + userSession.getUser().getPhone());

        Button logoutButton = (Button) view.findViewById(R.id.bt_logout);
        logoutButton.setOnClickListener(logoutOnClickListener);
    }

    public View.OnClickListener logoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (userSession.logout()) {
                LoginFragment loginFragment = new LoginFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("userSession", userSession);
                loginFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, loginFragment).commit();
            } else {
                Toast.makeText(view.getContext(), "This is error message", Toast.LENGTH_LONG).show();
            }
        }
    };
}