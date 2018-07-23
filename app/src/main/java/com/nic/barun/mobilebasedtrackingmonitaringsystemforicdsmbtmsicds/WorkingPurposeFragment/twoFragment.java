package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.HomeFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class twoFragment extends Fragment {
  private TextInputEditText EEchildren_served_7mnth_6years,EEtotal_children_3_6years,EEchildren_in_pse_3_6years,EEregister_present_for_assessment,EEmothers_meeting;
 private Button Bsave_next2,Bprev;
 String schildren_served_7mnth_6years,stotal_children_3_6years,schildren_in_pse_3_6years,sregister_present_for_assessment,smothers_meeting;
    public twoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_two, container, false);
      //....................7 data received form oneFrag..........................................
        Bundle bundle=getArguments();
        final String centre_code=bundle.getString("centre_code");
        final String sup_code=bundle.getString("sup_code");
        final String sgp_ward_name= bundle.getString("gp_ward_name");
        final String sblock_name= bundle.getString("block_name");

        final String awc_building_yes=bundle.getString("awc_building_yes");
        final String awc_found_open=bundle.getString("awc_found_open");
        final String total_snp=bundle.getString("total_snp");
        final String benef_with_snp=bundle.getString("benef_with_snp");
        final String total_child_7mnth_6yrs=bundle.getString("total_child_7mnth_6yrs");


        final String c_name= bundle.getString("c_name");
        final String c_add= bundle.getString("c_add");





        //Toast.makeText(getActivity(), total_child_7mnth_6yrs_Int, Toast.LENGTH_LONG).show();
        //Toast.makeText(getActivity(),"CentreDetails Frag data"+centre_code+sup_code,Toast.LENGTH_LONG).show();
        //..........................................................................................

        Bprev=(Button)v.findViewById(R.id.Bprev);
        Bsave_next2=(Button)v.findViewById(R.id.Bnext2);
       //...................casting 6 to 10 data....................................................

        EEchildren_served_7mnth_6years=(TextInputEditText)v.findViewById(R.id.Echildren_served_7mnth_6years);
        EEtotal_children_3_6years=(TextInputEditText)v.findViewById(R.id.Etotal_children_3_6years);
        EEchildren_in_pse_3_6years=(TextInputEditText)v.findViewById(R.id.Echildren_in_pse_3_6years);
        EEregister_present_for_assessment=(TextInputEditText)v.findViewById(R.id.Eregister_present_for_assessment);
        EEmothers_meeting=(TextInputEditText)v.findViewById(R.id.Emothers_meeting);
        //..........................................................................................



        //..................validation assign data......................................................

        EEchildren_served_7mnth_6years.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEEchildren_served_7mnth_6years = EEchildren_served_7mnth_6years.getText().toString();

                                int children_served_7mnth_6years_int=Integer.parseInt(sEEchildren_served_7mnth_6years);
                                int total_child_7mnth_6yrs_int=Integer.parseInt(total_child_7mnth_6yrs);

                    if (sEEchildren_served_7mnth_6years.isEmpty() || !sEEchildren_served_7mnth_6years.matches("^[0-9]{0,3}$")) {
                        EEchildren_served_7mnth_6years.setError("Field required. max 3 digit!!!");
                    } else {

                        if(total_child_7mnth_6yrs_int>=children_served_7mnth_6years_int)
                        {
                            Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                            tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                            EEchildren_served_7mnth_6years.setError("correct" ,tickChecked);
                        }
                        else
                        {
                            EEchildren_served_7mnth_6years.setError("Not more than Total Chi 7mnth to yrs..!!!");
                        }
                    }
                }

            }
        });

        EEtotal_children_3_6years.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEEtotal_children_3_6years = EEtotal_children_3_6years.getText().toString();



                    if (sEEtotal_children_3_6years.isEmpty() || !sEEtotal_children_3_6years.matches("^[0-9]{0,3}$")) {
                        EEtotal_children_3_6years.setError("Field required. max 3 digit!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        EEtotal_children_3_6years.setError("correct" ,tickChecked);
                    }
                }

            }
        });
        EEchildren_in_pse_3_6years.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEEtotal_children_3_6years = EEtotal_children_3_6years.getText().toString();
                    String sEEchildren_in_pse_3_6years = EEchildren_in_pse_3_6years.getText().toString();

                    int total_children_3_6years_int=Integer.parseInt(sEEtotal_children_3_6years);
                    int children_in_pse_3_6years_int=Integer.parseInt(sEEchildren_in_pse_3_6years);

                    if (sEEchildren_in_pse_3_6years.isEmpty() || !sEEchildren_in_pse_3_6years.matches("^[0-9]{0,3}$")) {
                        EEchildren_in_pse_3_6years.setError("Field required. max 3 digit!!!");
                    }
                    else
                        {
                            if(total_children_3_6years_int>=children_in_pse_3_6years_int) {

                                Drawable tickChecked = getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                                tickChecked.setBounds(0, 0, tickChecked.getIntrinsicWidth(), tickChecked.getIntrinsicHeight());
                                EEchildren_in_pse_3_6years.setError("correct", tickChecked);
                            }
                            else
                            {
                                EEchildren_in_pse_3_6years.setError("Not more than Total Chi 3to6 yrs pse..!!!");
                            }
                    }
                }

            }
        });
        EEregister_present_for_assessment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEEregister_present_for_assessment = EEregister_present_for_assessment.getText().toString();
                    if (sEEregister_present_for_assessment.isEmpty() || !sEEregister_present_for_assessment.matches("^[0-9]{0,3}$")) {
                        EEregister_present_for_assessment.setError("Field required. max 3 digit!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        EEregister_present_for_assessment.setError("correct" ,tickChecked);
                    }
                }

            }
        });
        EEmothers_meeting.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEEmothers_meeting = EEmothers_meeting.getText().toString();
                    if (sEEmothers_meeting.isEmpty() || !sEEmothers_meeting.matches("^[0-9]{0,3}$")) {
                        EEmothers_meeting.setError("Field required. max 3 digit!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        EEmothers_meeting.setError("correct" ,tickChecked);
                    }
                }

            }
        });


        //................................................................................................





        Bsave_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if (!validation_save_next()) return;      //..................................................................Validation

                //.........................convert string.....................
                 schildren_served_7mnth_6years=EEchildren_served_7mnth_6years.getText().toString();    //..................6
                 stotal_children_3_6years=EEtotal_children_3_6years.getText().toString();                //..................7
                 schildren_in_pse_3_6years=EEchildren_in_pse_3_6years.getText().toString();               //..................8
                 sregister_present_for_assessment=EEregister_present_for_assessment.getText().toString(); //..................9
                 smothers_meeting=EEmothers_meeting.getText().toString();                                 //..................10
                //............................................................

                Bundle bundle=new Bundle();
                bundle.putString("centre_code",centre_code);        //................. centre code
                bundle.putString("sup_code",sup_code);          //.................. sup code
                bundle.putString("gp_ward_name",sgp_ward_name);        //................. gp_ward_name
                bundle.putString("block_name",sblock_name);          //.................. block_name
                bundle.putString("awc_building_yes",awc_building_yes);        //................. 1
                bundle.putString("awc_found_open",awc_found_open);          //.................. 2
                bundle.putString("total_snp",total_snp);                   //................... 3
                bundle.putString("benef_with_snp",benef_with_snp);         //................... 4
                bundle.putString("total_child_7mnth_6yrs",total_child_7mnth_6yrs);  //.......... 5

                bundle.putString("schildren_served_7mnth_6years",schildren_served_7mnth_6years);    //................. 6
                bundle.putString("stotal_children_3_6years",stotal_children_3_6years);              //.................. 7
                bundle.putString("schildren_in_pse_3_6years",schildren_in_pse_3_6years);             //................. 8
                bundle.putString("sregister_present_for_assessment",sregister_present_for_assessment);//...............  9
                bundle.putString("smothers_meeting",smothers_meeting);                                //...............  10

                bundle.putString("c_name",c_name);
                bundle.putString("c_add",c_add);

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                ThreeFragment threeFragment=new ThreeFragment();
                threeFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.mainLayout,threeFragment);
                fragmentTransaction.commit();
            }
        });

        Bprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                HomeFragment homeFragment=new HomeFragment();

                fragmentTransaction.replace(R.id.mainLayout,homeFragment);
                fragmentTransaction.commit();
            }
        });


/*
          Esec_name=(TextView) v.findViewById(R.id.Esec_name);




        Bundle bundle=getArguments();
         n=bundle.getString("Building");
         m=bundle.getString("Found");

        Esec_name.setText(n);
        Toast.makeText(getContext(),n+m,Toast.LENGTH_LONG).show();
*/

    return v;
    }

    private boolean validation_save_next() {
        boolean valid = true;
        schildren_served_7mnth_6years=EEchildren_served_7mnth_6years.getText().toString();    //..................6
        stotal_children_3_6years=EEtotal_children_3_6years.getText().toString();                //..................7
        schildren_in_pse_3_6years=EEchildren_in_pse_3_6years.getText().toString();               //..................8
        sregister_present_for_assessment=EEregister_present_for_assessment.getText().toString(); //..................9
        smothers_meeting=EEmothers_meeting.getText().toString();                                 //..................10
        if(schildren_served_7mnth_6years.isEmpty())
        {
            EEchildren_served_7mnth_6years.setError("Field reqiured...");
            valid = false;
        }
        if(stotal_children_3_6years.isEmpty())
        {
            EEtotal_children_3_6years.setError("Field reqiured...");
            valid = false;
        }
        if(schildren_in_pse_3_6years.isEmpty())
        {
            EEchildren_in_pse_3_6years.setError("Field reqiured...");
            valid = false;
        }
        if(sregister_present_for_assessment.isEmpty())
        {
            EEregister_present_for_assessment.setError("Field reqiured...");
            valid = false;
        }
        if(smothers_meeting.isEmpty())
        {
            EEmothers_meeting.setError("Field reqiured...");
            valid = false;
        }
        return valid;
    }

}
