package com.google.firebase.samples.apps.mlkit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.samples.apps.mlkit.java.StillImageActivity;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    /** UI Components **/
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    //private View mProgressView;
    //private View mCreateForm;

    /** Activity callback **/
    private ActivityCallback mCallback;

    /** Firebase objects **/
    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * Create a instance of this fragment
     *
     * @return fragment instance
     */
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    /// Lifecycle methods

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        mUsername = (EditText) root.findViewById(R.id.et_name);
        mPassword = (EditText) root.findViewById(R.id.et_password);
        mEmail = (EditText) root.findViewById(R.id.et_email);


        Button createButton = (Button) root.findViewById(R.id.btn_register);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        return root;
    }

    //@Override
    //public void onAttach(Context context) {
    //    super.onAttach(context);
    //    mCallback = (ActivityCallback) context;
    //}

    //@Override
    //public void onDetach() {
    //    super.onDetach();
    //    mCallback = null;
    //}

    /// Private methods

    private void createAccount() {


        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()) {
                    //Toast.makeText(getContext(), R.string.error_create_account, Toast.LENGTH_SHORT).show();
                } else {
                    Utils.saveLocalUser(getContext(),
                            mUsername.getText().toString(),
                            mEmail.getText().toString(),
                            task.getResult().getUser().getUid());

                    //mCallback.openChat();
                    Intent intent = new Intent(getActivity(), StillImageActivity.class);
                    startActivity(intent);
                }


                Utils.closeKeyboard(getContext(), mEmail);
            }
        });
    }

}
