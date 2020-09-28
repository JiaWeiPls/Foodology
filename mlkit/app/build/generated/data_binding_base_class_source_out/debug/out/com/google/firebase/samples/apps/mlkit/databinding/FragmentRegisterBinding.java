// Generated by view binder compiler. Do not edit!
package com.google.firebase.samples.apps.mlkit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.google.firebase.samples.apps.mlkit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnRegister;

  @NonNull
  public final ImageView elipse;

  @NonNull
  public final EditText etEmail;

  @NonNull
  public final EditText etName;

  @NonNull
  public final EditText etPassword;

  @NonNull
  public final EditText etRepassword;

  @NonNull
  public final View lineEmail;

  @NonNull
  public final View lineName;

  @NonNull
  public final View linePassword;

  @NonNull
  public final View lineRepassword;

  @NonNull
  public final ImageView logo;

  @NonNull
  public final TextView swipeLeft;

  @NonNull
  public final TextView tvSubtitle;

  private FragmentRegisterBinding(@NonNull RelativeLayout rootView, @NonNull Button btnRegister,
      @NonNull ImageView elipse, @NonNull EditText etEmail, @NonNull EditText etName,
      @NonNull EditText etPassword, @NonNull EditText etRepassword, @NonNull View lineEmail,
      @NonNull View lineName, @NonNull View linePassword, @NonNull View lineRepassword,
      @NonNull ImageView logo, @NonNull TextView swipeLeft, @NonNull TextView tvSubtitle) {
    this.rootView = rootView;
    this.btnRegister = btnRegister;
    this.elipse = elipse;
    this.etEmail = etEmail;
    this.etName = etName;
    this.etPassword = etPassword;
    this.etRepassword = etRepassword;
    this.lineEmail = lineEmail;
    this.lineName = lineName;
    this.linePassword = linePassword;
    this.lineRepassword = lineRepassword;
    this.logo = logo;
    this.swipeLeft = swipeLeft;
    this.tvSubtitle = tvSubtitle;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_register;
      Button btnRegister = rootView.findViewById(id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.elipse;
      ImageView elipse = rootView.findViewById(id);
      if (elipse == null) {
        break missingId;
      }

      id = R.id.et_email;
      EditText etEmail = rootView.findViewById(id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.et_name;
      EditText etName = rootView.findViewById(id);
      if (etName == null) {
        break missingId;
      }

      id = R.id.et_password;
      EditText etPassword = rootView.findViewById(id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.et_repassword;
      EditText etRepassword = rootView.findViewById(id);
      if (etRepassword == null) {
        break missingId;
      }

      id = R.id.line_email;
      View lineEmail = rootView.findViewById(id);
      if (lineEmail == null) {
        break missingId;
      }

      id = R.id.line_name;
      View lineName = rootView.findViewById(id);
      if (lineName == null) {
        break missingId;
      }

      id = R.id.line_password;
      View linePassword = rootView.findViewById(id);
      if (linePassword == null) {
        break missingId;
      }

      id = R.id.line_repassword;
      View lineRepassword = rootView.findViewById(id);
      if (lineRepassword == null) {
        break missingId;
      }

      id = R.id.logo;
      ImageView logo = rootView.findViewById(id);
      if (logo == null) {
        break missingId;
      }

      id = R.id.swipeLeft;
      TextView swipeLeft = rootView.findViewById(id);
      if (swipeLeft == null) {
        break missingId;
      }

      id = R.id.tv_subtitle;
      TextView tvSubtitle = rootView.findViewById(id);
      if (tvSubtitle == null) {
        break missingId;
      }

      return new FragmentRegisterBinding((RelativeLayout) rootView, btnRegister, elipse, etEmail,
          etName, etPassword, etRepassword, lineEmail, lineName, linePassword, lineRepassword, logo,
          swipeLeft, tvSubtitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
