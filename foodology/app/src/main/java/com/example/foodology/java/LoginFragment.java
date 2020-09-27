package com.example.foodology.java;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

import com.example.foodology.java.StillImageActivity;
import com.example.foodology.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    Button btn_login;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_login, container, false);
        btn_login = (Button) myView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v) {
        // implements your things
        Intent intent = new Intent(v.getContext(), StillImageActivity.class);
        startActivity(intent);
    }

}
