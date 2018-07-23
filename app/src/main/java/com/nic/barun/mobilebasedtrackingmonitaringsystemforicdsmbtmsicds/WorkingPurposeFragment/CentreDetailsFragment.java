package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.CentreDetailsRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CentreDetailsFragment extends Fragment {
    //private EditText Ecentre_code;
    private TextView TVcentre_name,TVcentre_address,TVcentre_code,TVgp_ward_name,TVblock_name,TVsup_name;
    private Button Bsave_next0;
    private CheckBox centre_code_checked;
    ProgressDialog pd;
    String sEcentre_code;
    String ssup_code;
    String gp_ward_name,block_name,sup_code;
    String c_name,c_add,c_code,sup_name;
    private PinEntryEditText Ecentre_code;
    public CentreDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_centre_details, container, false);

        Bundle bundle=getArguments();
        ssup_code=bundle.getString("sup_code");//receive sup_code



        Ecentre_code=(PinEntryEditText)v.findViewById(R.id.Ecentre_code);   //sent code in jsonobject

        TVcentre_code=(TextView)v.findViewById(R.id.TVcentre_code);
        TVcentre_name=(TextView)v.findViewById(R.id.TVcentre_name);
        TVcentre_address=(TextView)v.findViewById(R.id.TVcentre_adds);

         TVgp_ward_name=(TextView)v.findViewById(R.id.TVgp_ward_name);
         TVblock_name=(TextView)v.findViewById(R.id.TVblock_name);
        TVsup_name=(TextView)v.findViewById(R.id.TVsup_name);


        centre_code_checked=(CheckBox) v.findViewById(R.id.centre_code_checked);
        Bsave_next0=(Button)v.findViewById(R.id.Bsave_next0);

//.......................disable Textview ......................................................
        TVcentre_name.setVisibility(View.GONE);
        TVcentre_address.setVisibility(View.GONE);











//..................................................................................................

        centre_code_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sEcentre_code=Ecentre_code.getText().toString();

                pd = new ProgressDialog(getActivity());
                pd.setMessage("Searching centre code please wait...");
                pd.show();

                sEcentre_code=Ecentre_code.getText().toString();

                Response.Listener<String>responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");

                            if(success)
                            {


                                pd.dismiss();

                                //String erro = jsonResponse.getString("error_searched");
                                // Toast.makeText(getActivity(), Ecentre_code, Toast.LENGTH_LONG).show();
                                int search_true3=jsonResponse.getInt("one");
                                if(search_true3==1) {
                                    //Ecentre_code.setText(null);

                                    c_code=jsonResponse.getString("c_code");
                                    c_name=jsonResponse.getString("c_name");
                                    c_add=jsonResponse.getString("c_add");
                                    gp_ward_name=jsonResponse.getString("gp_ward_name");//pass the data submit assignment table
                                    block_name=jsonResponse.getString("block_name");//pass the data submit assignment table
                                    sup_name=jsonResponse.getString("sup_name");//pass the data submit assignment table



                                    TVcentre_code.setText(c_code);
                                    TVcentre_name.setText(c_name);
                                    TVcentre_address.setText(c_add);
                                    TVsup_name.setText(sup_name);
                                    // TVgp_ward_name.setText(gp_ward_name);
                                    // TVblock_name.setText(block_name);

                                    TVcentre_name.setVisibility(View.VISIBLE);
                                    TVcentre_address.setVisibility(View.VISIBLE);


                                  /*  getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.mainLayout, new oneFrag())
                                            .commit();*/
                                }


                            }

                            else
                            {

                                pd.dismiss();
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                builder.setMessage("Searching Failed !!!!!!!")
                                        .setNegativeButton("Retry .....",null)
                                        .create()
                                        .show();

                            }


                        } catch (JSONException e) {
                            data_not_found_Toast();

                            TVcentre_code.setText(null);
                            TVcentre_name.setText(null);
                            TVcentre_address.setText(null);
                            TVsup_name.setText(null);
                            e.printStackTrace();
                        }
                    }
                };

                CentreDetailsRequest centreDetailsRequest=new CentreDetailsRequest(sEcentre_code,ssup_code, responseListener);
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                queue.add(centreDetailsRequest);

            }
        });
        Bsave_next0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validation()) return;

                Ecentre_code.setText(null);
                TVcentre_name.setText(null );
                TVcentre_address.setText(null);
                TVgp_ward_name.setText(null);
                TVblock_name.setText(null);

              /*   getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainLayout, new oneFrag())
                .commit();
                Toast.makeText(getActivity(),sEcentre_code+sup_code,Toast.LENGTH_LONG).show();
*/
                Bundle bundle=new Bundle();
                bundle.putString("centre_code",sEcentre_code);
                bundle.putString("sup_code",ssup_code);
                bundle.putString("gp_ward_name",gp_ward_name);
                bundle.putString("block_name",block_name);

                bundle.putString("c_name",c_name);
                bundle.putString("c_add",c_add);


                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                oneFrag oneFrag=new oneFrag();
                oneFrag.setArguments(bundle);

                fragmentTransaction.replace(R.id.mainLayout,oneFrag);
                fragmentTransaction.commit();

            }
        });

        return  v;

    }

    private boolean validation() {
        boolean valid = true;
        String sCentre_name=TVcentre_name.getText().toString();
        String sCentre_add=TVcentre_address.getText().toString();
        String sEcentre_code=Ecentre_code.getText().toString();
        if (sCentre_name.isEmpty() || sCentre_add.isEmpty())
        {
            //Toast.makeText(getActivity(),"Centre Code Not Found",Toast.LENGTH_LONG).show();
            data_not_found_Toast();

            centre_code_checked.setChecked(false);
            valid = false;
        }
        if(sEcentre_code.isEmpty())
        {
            Ecentre_code.setError("Field reqiured...");
            valid = false;
        }
        if(!centre_code_checked.isChecked())
        {
            centre_code_checked.setError("checked button...");
            valid = false;
        }
        return valid;
    }


    public void data_not_found_Toast()
    {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.exclaim,null);
        Toast toast=new Toast(getActivity());
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,600);
        toast.show();
    }



}
