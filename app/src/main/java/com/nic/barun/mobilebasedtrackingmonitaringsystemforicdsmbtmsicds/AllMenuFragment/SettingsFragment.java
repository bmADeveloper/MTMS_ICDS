package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //..........................header Email value call......................................

        //.........................................................................................
        // Inflate the layout for this fragment
        //String ee=this.getArguments().getString("Email").toString();
        //Toast.makeText(getActivity(),"current pass: "+ee, Toast.LENGTH_LONG).show();
//..................................................................................................
        View v= inflater.inflate(R.layout.fragment_settings, container, false);




    return v;
    }
}
