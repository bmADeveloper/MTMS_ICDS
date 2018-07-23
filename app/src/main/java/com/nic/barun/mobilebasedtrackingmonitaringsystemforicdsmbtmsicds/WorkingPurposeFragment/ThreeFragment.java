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
import android.widget.RadioButton;
import android.widget.Toast;

import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.HomeFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {
    private RadioButton Bece_curriculam_followed_no,Bece_curriculam_followed_yes;

private Button Bsave_next3,Bprev2;
private TextInputEditText Echild_5yrs_below_weighed,Etotal_child_below_5yrs,Emalnou_child_modarate,Emalnou_child_severe,Eece_curriculam_followed;
 String schild_5yrs_below_weighed,stotal_child_below_5yrs,smalnou_child_modarate,smalnou_child_severe,sece_curriculam_followed;
    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_three, container, false);
        //....................12 Data received from twoFrag.............................
        Bundle bundle=getArguments();
        final String  centre_code=bundle.getString("centre_code");                                   //.......................... centre code
        final String      sup_code=bundle.getString("sup_code");                                          //.......................... sup code
        final String sgp_ward_name= bundle.getString("gp_ward_name");
        final String sblock_name= bundle.getString("block_name");

        final String      awc_building_yes=bundle.getString("awc_building_yes");                          //.......................... 1
        final String     awc_found_open=bundle.getString("awc_found_open");                              //.......................... 2
        final String      total_snp=bundle.getString("total_snp");                                         //.......................... 3
        final String     benef_with_snp=bundle.getString("benef_with_snp");                                //.......................... 4
        final String        total_child_7mnth_6yrs=bundle.getString("total_child_7mnth_6yrs");                 //.......................... 5

        final String      children_served_7mnth_6years=bundle.getString("schildren_served_7mnth_6years");     //.......................... 6
        final String        total_children_3_6years=bundle.getString("stotal_children_3_6years");               //.......................... 7
        final String      children_in_pse_3_6years=bundle.getString("schildren_in_pse_3_6years");             //.......................... 8
        final String       register_present_for_assessment=bundle.getString("sregister_present_for_assessment");   //.......................... 9
        final String      mothers_meeting=bundle.getString("smothers_meeting");                                   //.......................... 10




        final String c_name= bundle.getString("c_name");
        final String c_add= bundle.getString("c_add");

      //  Toast.makeText(getActivity(),"CentreDetails Frag data"+centre_code+sup_code,Toast.LENGTH_LONG).show();
       // Toast.makeText(getActivity(),"First Fragment data"+total_child_7mnth_6yrs+benef_with_snp+total_snp+awc_found_open+awc_building_yes,Toast.LENGTH_LONG).show();
      //  Toast.makeText(getActivity(),"Second Frag data "+children_served_7mnth_6years+total_children_3_6years+children_in_pse_3_6years+register_present_for_assessment+mothers_meeting ,Toast.LENGTH_LONG).show();

        //..............................................................................


        //...........................................Casting 11 to 15 id............................
        Echild_5yrs_below_weighed=(TextInputEditText)v.findViewById(R.id.Echild_5yrs_below_weighed);
        Etotal_child_below_5yrs=(TextInputEditText)v.findViewById(R.id.Etotal_child_below_5yrs);
        Emalnou_child_modarate=(TextInputEditText)v.findViewById(R.id.Emalnou_child_modarate);
        Emalnou_child_severe=(TextInputEditText)v.findViewById(R.id.Emalnou_child_severe);

        Bece_curriculam_followed_yes=(RadioButton)v.findViewById(R.id.yes);
        Bece_curriculam_followed_no=(RadioButton)v.findViewById(R.id.no);




        //..................validation assign data......................................................
        Etotal_child_below_5yrs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEtotal_child_below_5yrs = Etotal_child_below_5yrs.getText().toString();
                    if (sEtotal_child_below_5yrs.isEmpty() || !sEtotal_child_below_5yrs.matches("^[0-9]{0,3}$")) {
                        Etotal_child_below_5yrs.setError("Field required. max 3 digit!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        Etotal_child_below_5yrs.setError("correct" ,tickChecked);
                    }
                }

            }
        });


        Echild_5yrs_below_weighed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEtotal_child_below_5yrs = Etotal_child_below_5yrs.getText().toString();
                    String sEchild_5yrs_below_weighed = Echild_5yrs_below_weighed.getText().toString();

                    int total_child_below_5yrs_int=Integer.parseInt(sEtotal_child_below_5yrs);
                    int child_5yrs_below_weighed_int=Integer.parseInt(sEchild_5yrs_below_weighed);

                    if (sEchild_5yrs_below_weighed.isEmpty() || !sEchild_5yrs_below_weighed.matches("^[0-9]{0,3}$")) {
                        Echild_5yrs_below_weighed.setError("Field required. max 3 digit!!!");
                    }
                    else
                        {
                            if(total_child_below_5yrs_int>=child_5yrs_below_weighed_int)
                            {
                                Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                                tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                                Echild_5yrs_below_weighed.setError("correct" ,tickChecked);
                            }
                            else
                            {
                                Echild_5yrs_below_weighed.setError("Not more than Total Chi 3to6 yrs pse..!!!");
                            }
                    }
                }

            }
        });


        Emalnou_child_modarate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEmalnou_child_modarate = Emalnou_child_modarate.getText().toString();
                    String sEchild_5yrs_below_weighed = Echild_5yrs_below_weighed.getText().toString();

                    int malnou_child_modarate_int=Integer.parseInt(sEmalnou_child_modarate);
                    int child_5yrs_below_weighed_int=Integer.parseInt(sEchild_5yrs_below_weighed);

                    if (sEmalnou_child_modarate.isEmpty() || !sEmalnou_child_modarate.matches("^[0-9]{0,3}$")) {
                        Emalnou_child_modarate.setError("Field required. max 3 digit!!!");
                    }
                    else
                        {
                            if(child_5yrs_below_weighed_int>=malnou_child_modarate_int)
                            {
                                Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                                tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                                Emalnou_child_modarate.setError("correct" ,tickChecked);
                            }
                            else
                            {
                                Echild_5yrs_below_weighed.setError("Not more than Total Chi 3to6 yrs pse..!!!");
                            }
                    }
                }

            }
        });
        Emalnou_child_severe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEmalnou_child_modarate = Emalnou_child_modarate.getText().toString();
                    String sEmalnou_child_severe = Emalnou_child_severe.getText().toString();
                    String sEchild_5yrs_below_weighed = Echild_5yrs_below_weighed.getText().toString();


                    int malnou_child_modarate_int=Integer.parseInt(sEmalnou_child_modarate);
                    int malnou_child_severe_int=Integer.parseInt(sEmalnou_child_severe);
                    int child_5yrs_below_weighed_int=Integer.parseInt(sEchild_5yrs_below_weighed);

                    int severe=((child_5yrs_below_weighed_int)-(malnou_child_modarate_int));




                    if (sEmalnou_child_severe.isEmpty() || !sEmalnou_child_severe.matches("^[0-9]{0,3}$")) {
                        Emalnou_child_severe.setError("Field required. max 3 digit!!!");

                    }
                    else
                        {
                            if(severe==malnou_child_severe_int) {
                                Drawable tickChecked = getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                                tickChecked.setBounds(0, 0, tickChecked.getIntrinsicWidth(), tickChecked.getIntrinsicHeight());
                                Emalnou_child_severe.setError("correct", tickChecked);
                            }
                            else
                            {
                                Emalnou_child_severe.setError("servere not more than weighed and moderate...!!! ");
                            }

                    }
                }

            }
        });




        //................................................................................................








        Bece_curriculam_followed_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Bece_curriculam_followed_no.isChecked())
                {
                    Bsave_next3.setEnabled(true);

                    sece_curriculam_followed=Bece_curriculam_followed_no.getText().toString();
                }
            }
        });
        Bece_curriculam_followed_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Bece_curriculam_followed_yes.isChecked())
                {
                    Bsave_next3.setEnabled(true);
                   // Bsave_next3.setBackgroundColor(Color.parseColor("#48C9B0"));    //Green Color

                    sece_curriculam_followed=Bece_curriculam_followed_yes.getText().toString();   //...................15
                }
            }
        });
        //..........................................................................................


          Bprev2=(Button)v.findViewById(R.id.Bprev2);
          Bsave_next3=(Button)v.findViewById(R.id.Bnext3);

          Bsave_next3.setEnabled(false);

          Bsave_next3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                //  if (!validation_save_next()) return;  //..................................................................Validation


                  schild_5yrs_below_weighed=Echild_5yrs_below_weighed.getText().toString();         //...................11
                  stotal_child_below_5yrs=Etotal_child_below_5yrs.getText().toString();             //...................12
                  smalnou_child_modarate=Emalnou_child_modarate.getText().toString();               //...................13
                  smalnou_child_severe=Emalnou_child_severe.getText().toString();                   //...................14
                  //sece_curriculam_followed=Eece_curriculam_followed.getText().toString();           //...................15



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

                  bundle.putString("children_served_7mnth_6years",children_served_7mnth_6years);    //................. 6
                  bundle.putString("total_children_3_6years",total_children_3_6years);              //.................. 7
                  bundle.putString("children_in_pse_3_6years",children_in_pse_3_6years);             //................. 8
                  bundle.putString("register_present_for_assessment",register_present_for_assessment);//...............  9
                  bundle.putString("mothers_meeting",mothers_meeting);                                //...............  10

                  bundle.putString("child_5yrs_below_weighed",schild_5yrs_below_weighed);           //................. 11
                  bundle.putString("total_child_below_5yrs",stotal_child_below_5yrs);               //..................12
                  bundle.putString("malnou_child_modarate",smalnou_child_modarate);                 //................. 13
                  bundle.putString("malnou_child_severe",smalnou_child_severe);                     //...............    14
                  bundle.putString("ece_curriculam_followed",sece_curriculam_followed);             //...............     15



                  bundle.putString("c_name",c_name);
                  bundle.putString("c_add",c_add);


                  FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                  FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                  FourFragment fourFragment=new FourFragment();
                  fourFragment.setArguments(bundle);

                  fragmentTransaction.replace(R.id.mainLayout,fourFragment);
                  fragmentTransaction.commit();
              }
          });

         Bprev2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                 HomeFragment homeFragment=new HomeFragment();

                 fragmentTransaction.replace(R.id.mainLayout,homeFragment);
                 fragmentTransaction.commit();
             }
         });

        return v;
    }

    private boolean validation_save_next() {
        boolean valid = true;
        schild_5yrs_below_weighed=Echild_5yrs_below_weighed.getText().toString();         //...................11
        stotal_child_below_5yrs=Etotal_child_below_5yrs.getText().toString();             //...................12
        smalnou_child_modarate=Emalnou_child_modarate.getText().toString();               //...................13
        smalnou_child_severe=Emalnou_child_severe.getText().toString();                   //...................14

        if(schild_5yrs_below_weighed.isEmpty())
        {
            Echild_5yrs_below_weighed.setError("Field reqiured...");
            valid = false;
        }
        if(stotal_child_below_5yrs.isEmpty())
        {
            Etotal_child_below_5yrs.setError("Field reqiured...");
            valid = false;
        }
        if(smalnou_child_modarate.isEmpty())
        {
            Emalnou_child_modarate.setError("Field reqiured...");
            valid = false;
        }
        if(smalnou_child_severe.isEmpty())
        {
            Emalnou_child_severe.setError("Field reqiured...");
            valid = false;
        }

        return valid;
    }


}
