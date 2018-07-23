package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.ChangePasswordRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    private TextInputEditText EnewPass,EcurrentPass,EconfPass;
    private Button BchangPass;
    ProgressDialog pd;

    public String Email_passing;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       //String sname=getArguments().getString("value");
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_change_password, container, false);

        EcurrentPass=(TextInputEditText)view.findViewById(R.id.Ecurrent_password);
        EnewPass=(TextInputEditText)view.findViewById(R.id.Enew_password);
        EconfPass=(TextInputEditText)view.findViewById(R.id.Econfirm_password);

        BchangPass=(Button)view.findViewById(R.id.Bchange_password);
        BchangPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword();
            }
        });

//........................................................Validation............................................................
        EcurrentPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String sPassword = EcurrentPass.getText().toString();

                    if (sPassword.isEmpty()) {
                        EcurrentPass.setError("Invalid password(atleast one upper,lower,special char.,digit and length must be atleast 8)!!!");

                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        EcurrentPass.setError("correct" ,tickChecked);
                    }
                }

            }
        });
        EnewPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String sPassword = EnewPass.getText().toString();

                    if (sPassword.isEmpty() || !sPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}+$")) {
                        EnewPass.setError("Invalid password(atleast one upper,lower,special char.,digit and length must be atleast 8)!!!");

                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        EnewPass.setError("correct" ,tickChecked);
                    }
                }

            }
        });
        EconfPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sPassword = EnewPass.getText().toString();
                    String sConPassword = EconfPass.getText().toString();
                    if (sConPassword.isEmpty()||!sPassword.equals(sConPassword)) {
                        EconfPass.setError("Password does not match!!!!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        EconfPass.setError("correct" ,tickChecked);
                    }
                }

            }
        });



        return view;
    }

    private void ChangePassword() {
        pd=new ProgressDialog(getActivity());
        pd.setMessage("Change Password Processing.......");
        pd.show();
        final String sEmail=Email_passing;
        final String sCurrentPass=EcurrentPass.getText().toString();
        final String sNewPass=EnewPass.getText().toString();
        final String sConfirmPass=EconfPass.getText().toString();

        Response.Listener<String>responseListener2= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonResponsePasswordChange=new JSONObject(response);
                    boolean ChangePass=jsonResponsePasswordChange.getBoolean("ChangePassSuccess");
                    if(ChangePass)
                    {

                        String arrayMsg1=jsonResponsePasswordChange.getString("changed");
                       // Toast.makeText(getActivity(),arrayMsg1,Toast.LENGTH_LONG).show();
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
                        builder.setMessage("Password Change Faild !!!!!!!")
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
        ChangePasswordRequest changePasswordRequest=new ChangePasswordRequest(sEmail,sCurrentPass,sNewPass,sConfirmPass,responseListener2);
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(changePasswordRequest);


    }

}
