package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.HomeFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.FewAssignDataRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.GPSTracker.GPSTracker;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class oneFrag extends Fragment {
    private TextInputEditText Etotal_snp,Ebenef_with_snp,Etotal_child_7mnth_6yrs;
    private Button Bsave_next1,Bdata_save,Bdata_incom_save,Bnext1;
    private CheckBox Eawc_building_yes;
    private CheckBox Eawc_found_open,Eawc_found_close,ch_lati_longi_empty_data,ch_location_empty_data;


    private RadioButton Bawc_building_yes,Bawc_building_no;
    private String sBEawc_building_yesno,sEawc_found_close;
    private String fullAddress;

    int Total_SNP=0,Serve_with_smp=0,total_child_7mnth_6yrs_Int=0;
    String lati2,longi2;
    //TextView resultView;
    Geocoder geocoder2;
    List<Address> addressList2;
    // GPSTracker class    GPSTracker gps;
    Context mContext2;
    double latitude2,longitude2;
    ProgressDialog pd;
    public oneFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_one_frag, container, false);

        //................data received from centreDetailsFragment.................................

        Bundle bundle=getArguments();
        final String  scentre_code=bundle.getString("centre_code");
        final String ssup_code=bundle.getString("sup_code");
        final String sgp_ward_name= bundle.getString("gp_ward_name");
        final String sblock_name= bundle.getString("block_name");

        final String c_name= bundle.getString("c_name");
        final String c_add= bundle.getString("c_add");

        // Toast.makeText(getActivity(),"centre details frag: "+scentre_code+ssup_code,Toast.LENGTH_LONG).show();
        //..........................................................................................


        geocoder2 = new Geocoder(getActivity(), Locale.getDefault());
        mContext2 = getActivity();

        Bdata_save=(Button)v.findViewById(R.id.Bdata_incom_save);
        Bsave_next1=(Button)v.findViewById(R.id.Bnext1);



        Bawc_building_yes=(RadioButton)v.findViewById(R.id.yes);   //................. 1
        Bawc_building_no=(RadioButton)v.findViewById(R.id.no);


        Eawc_found_open=(CheckBox)v.findViewById(R.id.Eawc_found_open);  //............... 2
        Eawc_found_close=(CheckBox)v.findViewById(R.id.Eawc_found_close); //.............. 2
        Etotal_snp=(TextInputEditText)v.findViewById(R.id.Etotal_snp);  //................ 3
        Ebenef_with_snp=(TextInputEditText)v.findViewById(R.id.Ebenef_with_snp);  //.......... 4
        Etotal_child_7mnth_6yrs=(TextInputEditText)v.findViewById(R.id.Etotal_child_7mnth_6yrs);// ... 5
        ch_lati_longi_empty_data=(CheckBox)v.findViewById(R.id.ch_lati_longi_empty_data);  //......Latitude and Longitude
        ch_location_empty_data=(CheckBox)v.findViewById(R.id.ch_location_empty_data);      //...... Location Address
        //default all disable
        Bdata_save.setEnabled(false);
        Bsave_next1.setEnabled(false);
        ch_lati_longi_empty_data.setEnabled(false);
        ch_lati_longi_empty_data.setVisibility(View.GONE);
        ch_location_empty_data.setEnabled(false);
        ch_location_empty_data.setVisibility(View.GONE);




        //..................validation assign data......................................................

        Etotal_snp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEtotal_snp = Etotal_snp.getText().toString();

                                                         Total_SNP=Integer.parseInt(sEtotal_snp);

                    if (sEtotal_snp.isEmpty() || !sEtotal_snp.matches("^[0-9]{0,3}$")) {
                        Etotal_snp.setError("Field required. max 3 digit!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        Etotal_snp.setError("correct" ,tickChecked);
                    }
                }

            }
        });

        Ebenef_with_snp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEbenef_with_snp = Ebenef_with_snp.getText().toString();

                                                       Serve_with_smp=Integer.parseInt(sEbenef_with_snp);

                    if (sEbenef_with_snp.isEmpty() || !sEbenef_with_snp.matches("^[0-9]{0,3}$")) {
                        Ebenef_with_snp.setError("Field required. max 3 digit!!!");
                    } else
                        {
                            if(Total_SNP>=Serve_with_smp)
                            {
                                Drawable tickChecked = getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                                tickChecked.setBounds(0, 0, tickChecked.getIntrinsicWidth(), tickChecked.getIntrinsicHeight());
                                Ebenef_with_snp.setError("correct", tickChecked);
                            }
                            else
                            {

                                Ebenef_with_snp.setError("Not more than Total Benef..!!!");

                            }
                        }



                }

            }
        });
        Etotal_child_7mnth_6yrs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEtotal_child_7mnth_6yrs = Etotal_child_7mnth_6yrs.getText().toString();



                    if (sEtotal_child_7mnth_6yrs.isEmpty() || !sEtotal_child_7mnth_6yrs.matches("^[0-9]{0,3}$")) {
                        Etotal_child_7mnth_6yrs.setError("Field required. max 3 digit!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checkmark_assign_val);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        Etotal_child_7mnth_6yrs.setError("correct" ,tickChecked);
                    }
                }

            }
        });


        //................................................................................................

//.................................RadioButton enable or disable all field.........................
        Bawc_building_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //..................................................................................

                if (ContextCompat.checkSelfPermission(mContext2,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(mContext2,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {

                    GPSTracker gps = new GPSTracker(mContext2, getActivity());
                    if (gps.canGetLocation()) {
                        latitude2 = gps.getLatitude();
                        longitude2 = gps.getLongitude();
                    } else {
                        gps.showSettingsAlert();
                    }
                }
                //..................................................................................




                if(Bawc_building_no.isChecked())
                {
                    Bdata_save.setEnabled(false);
                    Bdata_save.setBackgroundColor(Color.parseColor("#E4005A"));    //Red Color

                    //  Eawc_found_close.setVisibility(View.VISIBLE);    //............... 2
                    //  Eawc_found_open.setVisibility(View.VISIBLE);
                    //  Etotal_snp.setVisibility(View.VISIBLE);          //................ 3
                    //  Ebenef_with_snp.setVisibility(View.VISIBLE);  //................ 4
                    //  Etotal_child_7mnth_6yrs.setVisibility(View.VISIBLE); //......... 5

                    //  ch_lati_longi_empty_data.setEnabled(true);      //location show
                    //   ch_lati_longi_empty_data.setVisibility(View.GONE);
                    //   ch_location_empty_data.setEnabled(false);
                    //  ch_location_empty_data.setVisibility(View.GONE);

                    Bsave_next1.setEnabled(true);
                    Bsave_next1.setBackgroundColor(Color.parseColor("#48C9B0"));   //green color
                }

            }
        });
        Bawc_building_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.....................................call Lati/Longi..............................

                if (ContextCompat.checkSelfPermission(mContext2,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(mContext2,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    GPSTracker gps = new GPSTracker(mContext2, getActivity());
                    if (gps.canGetLocation()) {
                        latitude2 = gps.getLatitude();
                        longitude2 = gps.getLongitude();
                    } else {
                        gps.showSettingsAlert();
                    }
                }
                //..................................................................................


                if(Bawc_building_yes.isChecked())
                {
                    Bdata_save.setEnabled(true);
                    Bdata_save.setBackgroundColor(Color.parseColor("#375676"));    //Green Color

                    Eawc_found_close.setVisibility(View.VISIBLE);    //............... 2
                    Eawc_found_open.setVisibility(View.VISIBLE);
                    Etotal_snp.setVisibility(View.VISIBLE);          //................ 3
                    Ebenef_with_snp.setVisibility(View.VISIBLE);  //................ 4
                    Etotal_child_7mnth_6yrs.setVisibility(View.VISIBLE); //......... 5

                    ch_lati_longi_empty_data.setEnabled(false);      //location hide
                    ch_lati_longi_empty_data.setVisibility(View.GONE);
                    ch_location_empty_data.setEnabled(false);
                    ch_location_empty_data.setVisibility(View.GONE);

                    Bsave_next1.setEnabled(false);
                    Bsave_next1.setBackgroundColor(Color.parseColor("#375676"));   //red color
                }
            }
        });

// ................................. END......................................
//.................................check box enable or disable......................................
        Eawc_found_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Bdata_save.setEnabled(false);
                    Bdata_save.setBackgroundColor(Color.parseColor("#E4005A"));   //red color

                    Bsave_next1.setEnabled(true);
                    Bsave_next1.setBackgroundColor(Color.parseColor("#48C9B0"));  //Green color

                    Eawc_found_close.setVisibility(View.GONE);

                    Etotal_snp.setVisibility(View.VISIBLE);       //................ 3
                    Ebenef_with_snp.setVisibility(View.VISIBLE);  //................ 4
                    Etotal_child_7mnth_6yrs.setVisibility(View.VISIBLE); //......... 5

                    ch_lati_longi_empty_data.setEnabled(false);      //location hide
                    ch_lati_longi_empty_data.setVisibility(View.GONE);
                    ch_location_empty_data.setEnabled(false);
                    ch_location_empty_data.setVisibility(View.GONE);

                }
                else if (!isChecked)
                {
                    Bdata_save.setEnabled(true);
                    Bdata_save.setBackgroundColor(Color.parseColor("#375676"));

                    Bsave_next1.setEnabled(false);
                    Bsave_next1.setBackgroundColor(Color.parseColor("#375676"));

                    Eawc_found_close.setVisibility(View.VISIBLE);


                    ch_lati_longi_empty_data.setEnabled(false);      //location hide
                    ch_lati_longi_empty_data.setVisibility(View.GONE);
                    ch_location_empty_data.setEnabled(false);
                    ch_location_empty_data.setVisibility(View.GONE);
                }
            }
        });
        Eawc_found_close.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Bdata_save.setEnabled(true);
                    Bdata_save.setBackgroundColor(Color.parseColor("#48C9B0"));    //Green Color

                    Bsave_next1.setEnabled(false);
                    Bsave_next1.setBackgroundColor(Color.parseColor("#E4005A"));   //red color

                    Eawc_found_open.setVisibility(View.GONE);


                    Etotal_snp.setVisibility(View.GONE);          //................ 3
                    Ebenef_with_snp.setVisibility(View.GONE);  //................ 4
                    Etotal_child_7mnth_6yrs.setVisibility(View.GONE); //......... 5

                    ch_lati_longi_empty_data.setEnabled(true);      //location show
                    ch_lati_longi_empty_data.setVisibility(View.VISIBLE);
                    ch_location_empty_data.setEnabled(false);
                    ch_location_empty_data.setVisibility(View.VISIBLE);
                }
                else if (!isChecked)
                {
                    Bsave_next1.setEnabled(true);
                    Bsave_next1.setBackgroundColor(Color.rgb(26, 82, 118));

                    Bdata_save.setEnabled(false);
                    Bdata_save.setBackgroundColor(Color.rgb(26, 82, 118    ));

                    Eawc_found_open.setVisibility(View.VISIBLE);

                    Etotal_snp.setVisibility(View.VISIBLE);          //................ 3
                    Ebenef_with_snp.setVisibility(View.VISIBLE);  //................ 4
                    Etotal_child_7mnth_6yrs.setVisibility(View.VISIBLE); //......... 5

                    ch_lati_longi_empty_data.setEnabled(false);      //location hide
                    ch_lati_longi_empty_data.setVisibility(View.GONE);
                    ch_location_empty_data.setEnabled(false);
                    ch_location_empty_data.setVisibility(View.GONE);

                }
            }
        });
//..................................................................................................

//.......................................latitude/longitude/location listener......................

        ch_lati_longi_empty_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast toast = Toast.makeText(getActivity(), Html.fromHtml("please wait until latitude and longitude is viewed"), Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.CENTER, 0, 0);
                //toast.show();
                //lati_longi_search_wait();

                ch_location_empty_data.setEnabled(true);
                ch_location_empty_data.setVisibility(View.VISIBLE);
                Bdata_save.setEnabled(true);
                Bdata_save.setBackgroundColor(Color.parseColor("#48C9B0"));    //Green Color



                if (ContextCompat.checkSelfPermission(mContext2,
                        Manifest.permission.ACCESS_FINE_LOCATION)

                        != PackageManager.PERMISSION_GRANTED

                        && ActivityCompat.checkSelfPermission(mContext2,
                        Manifest.permission.ACCESS_COARSE_LOCATION)

                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    // Toast.makeText(mContext2, "You need have granted permission",Toast.LENGTH_SHORT).show();
                    GPSTracker gps = new GPSTracker(mContext2, getActivity());

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        latitude2 = gps.getLatitude();
                        longitude2 = gps.getLongitude();

                        // \n is for new line

                        Toast.makeText(getActivity(),"Your Location is - \nLat: " + latitude2 + "\nLong: " + longitude2, Toast.LENGTH_LONG).show();
                        lati2 = Double.toString(latitude2);                //find latitude
                        longi2 = Double.toString(longitude2);              //find longitude
                        //resultView.setText(lati);
                        //resultView.setText(longi);

                    } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }
                }
            }
        });
        ch_location_empty_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    addressList2 = geocoder2.getFromLocation(latitude2,longitude2,1);

                    String addressStr = addressList2.get(0).getAddressLine(0);
                    String areaStr = addressList2.get(0).getLocality();
                    String cityStr = addressList2.get(0).getAdminArea();
                    String countryStr = addressList2.get(0).getCountryName();
                    String postalcodeStr = addressList2.get(0).getPostalCode();

                    fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;
                    Toast.makeText(getActivity(),fullAddress,Toast.LENGTH_LONG).show();
                    //result.setText(fullAddress);
                    //Toast.makeText(getActivity(),"location here"+fullAddress,Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//..................................................................................................

// ......................Next second fragment................................................


        Bsave_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validation_save_next()) return;  //..................................................................Validation


                if(Bawc_building_no.isChecked())
                {
                    sBEawc_building_yesno=Bawc_building_no.getText().toString();   //........... 1 String values
                }
                if(Bawc_building_yes.isChecked())
                {
                    sBEawc_building_yesno=Bawc_building_yes.getText().toString();   //........... 1  String values
                }


               // String sawc_building_yes=Bawc_building_yes.getText().toString().trim();
                String sEawc_found_open=Eawc_found_open.getText().toString().trim();
                String sEtotal_snp=Etotal_snp.getText().toString().trim();             //................ 3
                String sEbenef_with_snp=Ebenef_with_snp.getText().toString().trim();  //.......... 4
                String sEtotal_child_7mnth_6yrs=Etotal_child_7mnth_6yrs.getText().toString().trim();  // ... 5

                //String sawc_building_no=Bawc_building_no.getText().toString().trim();   //........Building no
                //................send 7 data into twoFragment....................................

                Bundle bundle=new Bundle();
                bundle.putString("centre_code",scentre_code);        //................. centre code
                bundle.putString("sup_code",ssup_code);          //.................. sup code
                bundle.putString("gp_ward_name",sgp_ward_name);        //................. gp_ward_name
                bundle.putString("block_name",sblock_name);          //.................. block_name
                bundle.putString("awc_building_yes",sBEawc_building_yesno);        //................. 1
                bundle.putString("awc_found_open",sEawc_found_open);          //.................. 2
                bundle.putString("total_snp",sEtotal_snp);                   //................... 3
                bundle.putString("benef_with_snp",sEbenef_with_snp);         //................... 4
                bundle.putString("total_child_7mnth_6yrs",sEtotal_child_7mnth_6yrs);  //.......... 5


                bundle.putString("c_name",c_name);
                bundle.putString("c_add",c_add);



                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                twoFragment twoFragment=new twoFragment();
                twoFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.mainLayout,twoFragment);
                fragmentTransaction.commit();

            }
        });
//..............................................Save data with validation...........................

        Bdata_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validation()) return;    //..................................................................Validation
                if(Bawc_building_no.isChecked())
                {
                    sBEawc_building_yesno=Bawc_building_no.getText().toString();   //........... 1 String values
                }
                if(Bawc_building_yes.isChecked())
                {
                    sBEawc_building_yesno=Bawc_building_yes.getText().toString();   //........... 1  String values
                }
                sEawc_found_close=Eawc_found_close.getText().toString();

                // Toast.makeText(getActivity(),"AWC building exists? "+ sBEawc_building_yesno+"\n AWC found ? "+sEawc_found_close+"\n Full Address  : "+fullAddress+"\n Latitude : "+lati2+"\n Longitude : "+longi2+"\n centre code : "+scentre_code+"\n sup_code: "+ssup_code+"Save Data", Toast.LENGTH_LONG).show();

                pd=new ProgressDialog(getActivity());
                pd.setMessage("Data Save processing please wait......");
                pd.show();

                Response.Listener<String>responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonresponse=new JSONObject(response);
                            boolean success=jsonresponse.getBoolean("success");
                            if(success)
                            {
                                pd.dismiss();
                                String assignment_data=jsonresponse.getString("assignment_data");
                                Toast.makeText(getActivity(),assignment_data,Toast.LENGTH_LONG).show();
                                int success_true=jsonresponse.getInt("change");
                                if(success_true==1)
                                {

                                    //.................
                                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                                    HomeFragment homeFragment=new HomeFragment();


                                    fragmentTransaction.replace(R.id.mainLayout,homeFragment);
                                    fragmentTransaction.commit();

                                }
                            }
                            else
                            {
                                pd.dismiss();
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                builder.setMessage("Opps Somthing wrong...!!! ")
                                        .setNegativeButton("Try Again",null)
                                        .create()
                                        .show();

                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                FewAssignDataRequest fewDataRequest=new FewAssignDataRequest(scentre_code,ssup_code,sgp_ward_name,sblock_name,sBEawc_building_yesno,sEawc_found_close,fullAddress,lati2,longi2,responseListener);
                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                requestQueue.add(fewDataRequest);


            }
        });



//..................................................................................................

        return v;
    }

    private boolean validation() {
        boolean valid = true;
        if(!ch_lati_longi_empty_data.isChecked())
        {
            ch_lati_longi_empty_data.setError("checked first...");
            valid = false;
        }
        if(!ch_location_empty_data.isChecked())
        {
            ch_location_empty_data.setError("checked first..");
            valid = false;
        }

        return valid;
    }
    private boolean validation_save_next() {
        boolean valid = true;
        String sEtotal_snp=Etotal_snp.getText().toString();             //................ 3
        String sEbenef_with_snp=Ebenef_with_snp.getText().toString();  //.......... 4
        String sEtotal_child_7mnth_6yrs=Etotal_child_7mnth_6yrs.getText().toString();  // ... 5





        if(sEtotal_snp.isEmpty()) //|| sEtotal_snp.matches("^[0-9]{1,2}$"))

        {
            Etotal_snp.setError("Field reqiured 2 digit...");
            valid = false;
        }
        if(sEbenef_with_snp.isEmpty())
        {
            Ebenef_with_snp.setError("Field reqiured...");
            valid = false;
        }
        if(sEtotal_child_7mnth_6yrs.isEmpty())
        {
            Etotal_child_7mnth_6yrs.setError("Field reqiured...");
            valid = false;
        }

        return valid;
    }


    public void lati_longi_search_wait()
    {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.wait,null);
        Toast toast=new Toast(getActivity());
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,300);
        toast.show();
    }

}
