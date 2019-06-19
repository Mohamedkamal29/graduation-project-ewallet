package com.graduation.ewallet.Authorization.FirstFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.graduation.ewallet.Authorization.FirstFragment.LogIn.LogInFragment;
import com.graduation.ewallet.Authorization.FirstFragment.SignUp.SignUpFragment;
import com.graduation.ewallet.R;

public class ChoiceFragment extends Fragment {

    FragmentManager fragmentManager;
    LogInFragment logInFragment;
    SignUpFragment signUpFragment;
    Button existingAccountButton, newAccountButton;
    public ChoiceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_choice_fragment, container, false);

        fragmentManager = getFragmentManager();
        logInFragment = new LogInFragment();
        signUpFragment = new SignUpFragment();
        existingAccountButton = view.findViewById(R.id.existingAccountButton);
        existingAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchChoice(v);
            }
        });
        newAccountButton = view.findViewById(R.id.newAccountButton);
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchChoice(v);
            }
        });
        existingAccountButton.performClick();

        return view;
    }

    private void switchChoice(View v) {
        switch (v.getId()){
            case R.id.existingAccountButton:

                fragmentManager.beginTransaction().replace(R.id.Container, logInFragment).commit();
                existingAccountButton.setTextColor(getResources().getColor(R.color.colorAccent));
                existingAccountButton.setBackground(getResources().getDrawable(R.drawable.registration_left_round_choice_selected));
                newAccountButton.setTextColor(getResources().getColor(R.color.white));
                newAccountButton.setBackground(getResources().getDrawable(R.drawable.registration_right_round_choice));

                break;

            case R.id.newAccountButton:

                fragmentManager.beginTransaction().replace(R.id.Container, signUpFragment).commit();
                newAccountButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                newAccountButton.setBackground(getResources().getDrawable(R.drawable.registration_right_round_choice_selected));
                existingAccountButton.setTextColor(getResources().getColor(R.color.white));
                existingAccountButton.setBackground(getResources().getDrawable(R.drawable.registration_left_round_choice));

                break;
        }
    }

}
