package com.example.pablo.conectaCuatro2;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by pablo on 6/02/18.
 */

public class PrefActivityFragtment extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }


}
