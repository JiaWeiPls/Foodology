package com.google.firebase.samples.apps.mlkit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.samples.apps.mlkit.MainActivity;
import com.google.firebase.samples.apps.mlkit.java.StillImageActivity;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    View v;
    Button btnLogin;
    TextView btnLostPw,btnRegister;
    EditText txMail,txPass;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        //btnRegister.setOnClickListener(this);
        //btnLostPw.setOnClickListener(this);
        //btnLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        return v;
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    // [END on_start_check_user]

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }



        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // [START_EXCLUDE]

                            // [END_EXCLUDE]
                        }



                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();

    }


    private void reload() {
        mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(),
                            "Reload successful!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "reload", task.getException());
                    Toast.makeText(getActivity(),
                            "Failed to reload user.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
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


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {
            signIn(txMail.getText().toString(),txPass.getText().toString());
        }
    }
}




