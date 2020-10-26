package com.google.firebase.samples.apps.foodology.common.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.google.firebase.samples.apps.foodology.R;

/** Configures still image demo settings. */
public class StillImagePreferenceFragment extends PreferenceFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preference_still_image);
  }
}
