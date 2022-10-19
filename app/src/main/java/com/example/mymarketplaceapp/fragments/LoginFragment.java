package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.User;
import com.example.mymarketplaceapp.models.UserSession;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Login Fragment
 *
 * @author u7366711 Yuxuan Zhao
 */
public class LoginFragment extends Fragment {
    private UserSession userSession;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /**
     * Get userSession from MainActivity and set listener
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        // Get userSession from home fragment
        Bundle bundle = getArguments();
        userSession = bundle.getParcelable("userSession");

        // Set listener
        Button signInButton = (Button) view.findViewById(R.id.bt_login_sign_in);
        signInButton.setOnClickListener(signInOnClickListener);
    }

    private View.OnClickListener signInOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (userSession.login(getUsername(), getPassword())) {
                // Sign in successfully -> person fragment
                PersonFragment personFragment = new PersonFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("userSession", userSession);
                personFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, personFragment).commit();
            } else {
                TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.et_login_password);
                textInputLayout.setError("Incorrect username or password");
                textInputLayout.getEditText().setText("");
            }
        }
    };

    private String getUsername() {
        TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.et_login_username);

        return textInputLayout.getEditText().getText().toString();
    }

    private String getPassword() {
        TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.et_login_password);

        return textInputLayout.getEditText().getText().toString();
    }
}









