package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.HomeFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessFragment extends Fragment {

private TextView TVsuccess_c_code,TVsuccess_c_name,TVsuccess_c_add;
private Button home;
String succe_c_code;

    ProgressDialog pd_success;
String ass_date,centre_code2,centre_name,location;
    public SuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle2=getArguments();
        final String  centre_code=bundle2.getString("centre_code");
        final String  Address=bundle2.getString("Address");
        final String  c_name=bundle2.getString("c_name");




        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_success2, container, false);

        TVsuccess_c_code=(TextView)v.findViewById(R.id.TVsuccess_c_code);
        TVsuccess_c_name=(TextView)v.findViewById(R.id.TVsuccess_c_name);
        TVsuccess_c_add=(TextView)v.findViewById(R.id.TVsuccess_c_add);
        home=(Button)v.findViewById(R.id.home_button);

       // Toast.makeText(getActivity(),centre_code,Toast.LENGTH_LONG);


        //...............................autoload..........................

        TVsuccess_c_code.setText("Visited centre code ::  19328"+centre_code);
        TVsuccess_c_name.setText("Visited Centre Name  ::  "+c_name);
        TVsuccess_c_add.setText("Visited Address ::  "+Address);

home.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        HomeFragment homeFragment=new HomeFragment();


        fragmentTransaction.replace(R.id.mainLayout,homeFragment);
        fragmentTransaction.commit();

    }
});






        //...........................................................





        return v;
    }

}
