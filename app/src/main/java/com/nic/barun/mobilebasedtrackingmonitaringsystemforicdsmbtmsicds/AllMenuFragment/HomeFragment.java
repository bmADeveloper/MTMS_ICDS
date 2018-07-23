package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.AssignmentReportRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.Utils.UserParcelable;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment.CentreDetailsFragment;

import org.json.JSONObject;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private Button Bassignment_data_submit,Bassign_data_report,Bgenerate_report;
private CheckBox Cone_day_report,Cweekly_data_report,Cany_date_report;
private EditText Edate_pick ,Eweekly_date_pick_to,Edate_pick_from,Edate_pick_to,Eweekly_date_pick_from;
String sone_date,c_date;
ProgressDialog pd;
public String recv_sup_code_from_main;
    private UserParcelable user;
    int sweekly_date_from;
    String sweekly_date_to;
    String sany_date_from;
    String sany_date_to;
    DatePickerDialog datePickerDialog;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);



//.................send sup_code into HomeFrag.................................................


//.................................................................................................


        //......................Casting all.........................................................
        Bassignment_data_submit=(Button)v.findViewById(R.id.Bdemo);                                 //.....  1
        Bassign_data_report=(Button)v.findViewById(R.id.Bassign_data_report);                       //.....  2
        Bgenerate_report=(Button)v.findViewById(R.id.Bgenerate_report);
        Cone_day_report=(CheckBox) v.findViewById(R.id.Cone_day_report);                            //.....  3
        Edate_pick=(EditText)v.findViewById(R.id.Edate_pick);                                       //.....  5
        Cweekly_data_report=(CheckBox)v.findViewById(R.id.Cweekly_data_report);                     //.....  6

        Eweekly_date_pick_from=(EditText)v.findViewById(R.id.Eweekly_date_pick_from);
        Eweekly_date_pick_to=(EditText)v.findViewById(R.id.Eweekly_date_pick_to);                         //.....  9

        Cany_date_report=(CheckBox)v.findViewById(R.id.Cany_date_report);                           //.....  10
        Edate_pick_from=(EditText)v.findViewById(R.id.Edate_pick_from);                             //.....  12
        Edate_pick_to=(EditText)v.findViewById(R.id.Edate_pick_to);                                 //.....  14

        //..........................................................................................
//Edate_pick.setText(recv_sup_code_from_main);

        Bassign_data_report.setVisibility(View.GONE);


//..................................... All gone casting id............................................

        Cone_day_report.setVisibility(View.GONE);  //.............  3
        Edate_pick.setVisibility(View.GONE);      //.............   5
        Cweekly_data_report.setVisibility(View.GONE);  //.........  6
        Eweekly_date_pick_to.setVisibility(View.GONE);  //...........  9
        Cany_date_report.setVisibility(View.GONE);  //............. 10
        Edate_pick_from.setVisibility(View.GONE);  //.............  12
        Edate_pick_to.setVisibility(View.GONE);  //...............  14
        Bgenerate_report.setVisibility(View.GONE);//.............. generate report button
        Eweekly_date_pick_from.setVisibility(View.GONE);  //.......from button






//..................................................................................................

//.................................Data report button listnr........................................
        Bassign_data_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cone_day_report.setVisibility(View.VISIBLE);  //.............  3
                Cweekly_data_report.setVisibility(View.VISIBLE);  //.........  6
                Cany_date_report.setVisibility(View.VISIBLE);  //............. 10

                Bassignment_data_submit.setVisibility(View.GONE);
            }
        });
        Cone_day_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cone_day_report.isChecked())
                {
                    Bgenerate_report.setVisibility(View.VISIBLE);//.............. generate report button
                    Edate_pick.setVisibility(View.VISIBLE);      //.............   5

                    Cweekly_data_report.setVisibility(View.GONE);  //.........  6
                    Cany_date_report.setVisibility(View.GONE);  //............. 10

                }
                else
                {
                    Bgenerate_report.setVisibility(View.GONE);//.............. generate report button
                    Edate_pick.setVisibility(View.GONE);      //.............   5
                    Edate_pick.setText(null);


                    Cweekly_data_report.setVisibility(View.VISIBLE);  //.........  6
                    Cany_date_report.setVisibility(View.VISIBLE);  //............. 10
                }
            }
        });
        Cweekly_data_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //.............current date.............
                Calendar now = Calendar.getInstance();
                int day=now.get(Calendar.DAY_OF_MONTH);
                int month=now.get(Calendar.MONTH);
                int year=now.get(Calendar.YEAR);
                c_date=day+"/"+(month+1)+"/"+year;
                Eweekly_date_pick_from.setText(c_date);


                if(Cweekly_data_report.isChecked())
                {

                    Bgenerate_report.setVisibility(View.VISIBLE);//.............. generate report button
                    Eweekly_date_pick_to.setVisibility(View.VISIBLE);  //...........  9
                    Eweekly_date_pick_to.setText(null);

                    Eweekly_date_pick_from.setVisibility(View.VISIBLE);  //.......from button

                    Cone_day_report.setVisibility(View.GONE);  //.............  3
                    Cany_date_report.setVisibility(View.GONE);  //............. 10

                }
                else
                {

                    Bgenerate_report.setVisibility(View.GONE);//.............. generate report button
                    Eweekly_date_pick_to.setVisibility(View.GONE);  //...........  9

                    Eweekly_date_pick_from.setText(null);


                    Eweekly_date_pick_from.setVisibility(View.GONE);  //.......from button
                    Cone_day_report.setVisibility(View.VISIBLE);  //.............  3
                    Cany_date_report.setVisibility(View.VISIBLE);  //............. 10
                }
            }
        });
        Cany_date_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cany_date_report.isChecked())
                {
                    Bgenerate_report.setVisibility(View.VISIBLE);//.............. generate report button

                    Edate_pick_from.setVisibility(View.VISIBLE);  //.............  12
                    Edate_pick_to.setVisibility(View.VISIBLE);  //...............  14

                    Cone_day_report.setVisibility(View.GONE);  //.............  3
                    Cweekly_data_report.setVisibility(View.GONE);  //.........  6

                }
                else
                {
                    Bgenerate_report.setVisibility(View.GONE);//.............. generate report button

                    Edate_pick_from.setVisibility(View.GONE);  //.............  12
                    Edate_pick_to.setVisibility(View.GONE);  //...............  14
                    Edate_pick_from.setText(null);
                    Edate_pick_to.setText(null);

                    Cone_day_report.setVisibility(View.VISIBLE);  //.............  3
                    Cweekly_data_report.setVisibility(View.VISIBLE);  //.........  6
                }
            }
        });

//.................................................................................................

//...........................Goto CentreDetails Fragment....................
        Bassignment_data_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.................................
               /*
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainLayout, new oneFrag())
                        .commit();
                        */


                Bundle bundle=new Bundle();
                bundle.putString("sup_code",recv_sup_code_from_main);
                //bundle.putString("sup_code",sup_code);

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                CentreDetailsFragment centreDetailsFragment=new CentreDetailsFragment();
                centreDetailsFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.mainLayout,centreDetailsFragment);
                fragmentTransaction.commit();
            }
        });
//..................................................................................................

//................................... date picker...........................................
        Edate_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //................pick date................................
        final Calendar c=Calendar.getInstance();
        int mYear=c.get(Calendar.YEAR);
        int mMonth=c.get(Calendar.MONTH);
        int mDay=c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Edate_pick.setText(dayOfMonth + "/"+ (month + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

            }
        });

        Eweekly_date_pick_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c=Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Eweekly_date_pick_to.setText(dayOfMonth + "/"+ (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        Edate_pick_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c=Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Edate_pick_from.setText(dayOfMonth + "/"+ (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        Edate_pick_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c=Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Edate_pick_to.setText(dayOfMonth + "/"+ (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
//................................... End date picker...........................................


//.................................................Generate Report..................................
        Bgenerate_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(getActivity());
                pd.setMessage("Report generat processing please wait...");
                pd.show();


                sone_date=Edate_pick.getText().toString();
                sweekly_date_to=Eweekly_date_pick_to.getText().toString();
                sany_date_from=Edate_pick_from.getText().toString();
                sany_date_to=Edate_pick_to.getText().toString();
                Toast.makeText(getActivity(),sone_date+"month: "+c_date,Toast.LENGTH_LONG).show();


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
                                String latitude=jsonresponse.getString("latitude");
                                Toast.makeText(getActivity(),latitude ,Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                pd.dismiss();
                                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(getActivity());
                                builder.setMessage("Report Does't generate ")
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
                AssignmentReportRequest assignmentReportRequest=new AssignmentReportRequest(recv_sup_code_from_main,sone_date,c_date,sweekly_date_to,sany_date_from,sany_date_to,responseListener);
                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                requestQueue.add(assignmentReportRequest);

            }
        });


//..................................................................................................
        return v;
    }



}
