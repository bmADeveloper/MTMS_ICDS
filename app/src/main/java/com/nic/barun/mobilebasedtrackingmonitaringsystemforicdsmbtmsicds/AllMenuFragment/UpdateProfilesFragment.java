package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.UpdateProfileRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfilesFragment extends Fragment {

private EditText Ephone;
private TextView TVemail;
private Button Bupdate;
ProgressDialog pd;

    public String recv_email,recv_name;

    public UpdateProfilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_update_profiles, container, false);
        Ephone=(EditText)v.findViewById(R.id.Ephone);
        TVemail=(TextView)v.findViewById(R.id.TVemail);
        Bupdate=(Button)v.findViewById(R.id.Bupdate);

        Ephone.setText(recv_name);
        TVemail.setText(recv_email);



        Bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfile();
            }
        });

        return v;
    }

    private void UpdateProfile() {
        pd=new ProgressDialog(getActivity());
        pd.setMessage("Update Processing.......");
        pd.show();
        String sPhone=Ephone.getText().toString();
        String sEmail=TVemail.getText().toString();

        Response.Listener<String>responseListener2= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonResponsePasswordChange=new JSONObject(response);
                    boolean updateProfile=jsonResponsePasswordChange.getBoolean("UpdateSuccess");
                    if(updateProfile)
                    {

                        String arrayMsg1=jsonResponsePasswordChange.getString("update");
                        //Toast.makeText(getActivity(),arrayMsg1,Toast.LENGTH_LONG).show();
                        int ChangePassSuccess=jsonResponsePasswordChange.getInt("check");
                        if(ChangePassSuccess==1)
                        {
                            pd.dismiss();
                            Toast.makeText(getActivity(),arrayMsg1,Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        pd.dismiss();
                        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                        builder.setMessage("Update Profile  Faild !!!!!!!")
                                .setNegativeButton("Retry .....",null)
                                .create()
                                .show();
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        };
        UpdateProfileRequest updateProfileRequest=new UpdateProfileRequest(sEmail,sPhone,responseListener2);
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(updateProfileRequest);
    }

}
