package com.google.firebase.samples.apps.mlkit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
public class LoginFragment extends Fragment {


    /** UI Components **/
    private EditText mEmail;
    private EditText mPassword;


    /** Activity callback **/
    private ActivityCallback mCallback;

    /** Firebase objects **/
    private FirebaseAuth mAuth;

    /**
     * Create a instance of this fragment
     *
     * @return fragment instance
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    /// Lifecycle methods

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        mEmail = (EditText) root.findViewById(R.id.et_email);
        mPassword = (EditText) root.findViewById(R.id.et_password);

        final Button signInButton = (Button) root.findViewById(R.id.btn_login);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.closeKeyboard(getContext(), signInButton);
                attemptLogin();
            }
        });

        //final Button createAccount = (Button) root.findViewById(R.id.btn_register);
        //createAccount.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Utils.closeKeyboard(getContext(), createAccount);
        //        //mCallback.registerAccount();
        //    }
        //});



        mAuth = FirebaseAuth.getInstance();
        Utils.closeKeyboard(getContext(), mEmail);

        return root;
    }

    //@Override
    //public void onAttach(Context context) {
    //    super.onAttach(context);
    //    //mCallback = (ActivityCallback) context;
    //}

    //@Override
    //public void onDetach() {
    //    super.onDetach();
    //    mCallback = null;
    //}

    /// Private methods

    private void attemptLogin() {

        // Reset errors.
        mEmail.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String username = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            //mEmail.setError(getString(R.string.error_empty));
            mEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //mPassword.setError(getString(R.string.error_password));
            mPassword.requestFocus();
            return;
        }

        login();
    }

    private void login() {


        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if (mCallback != null) {
                    Utils.saveLocalUser(getContext(), Constants.DEFAULT_USER,
                            mEmail.getText().toString(),
                            authResult.getUser().getUid());


                    //mCallback.openChat();
                    Intent intent = new Intent(getActivity(), StillImageActivity.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}




