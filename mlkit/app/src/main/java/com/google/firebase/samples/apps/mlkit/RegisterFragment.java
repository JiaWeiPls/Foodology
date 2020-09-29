package com.google.firebase.samples.apps.mlkit;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    View v;
    Button btnLogin;
    TextView btnLostPw,btnRegister;
    EditText txMail,txPass;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        v = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = (Button) v.findViewById(R.id.btn_login);
        btnLostPw =(TextView) v.findViewById(R.id.login_resetPW);
        btnRegister=(TextView) v.findViewById(R.id.btn_register);
        txMail=(EditText)v.findViewById(R.id.et_email);
        txPass=(EditText)v.findViewById(R.id.et_password);


        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        return v;
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = txMail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            txMail.setError("Required.");
            valid = false;
        } else {
            txMail.setError(null);
        }

        String password = txPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            txPass.setError("Required.");
            valid = false;
        } else {
            txPass.setError(null);
        }

        return valid;
    }


    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_register) {
            createAccount(txMail.getText().toString(), txPass.getText().toString());
        }
    }
}
