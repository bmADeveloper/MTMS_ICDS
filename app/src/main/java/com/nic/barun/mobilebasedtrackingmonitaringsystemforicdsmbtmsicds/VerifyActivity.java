package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.VerifyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class VerifyActivity extends AppCompatActivity {
    private TextView email,name,phone;
    private EditText code;
    private Button verify;
    ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        name=(TextView)findViewById(R.id.Ename);
        email=(TextView)findViewById(R.id.Eemail);
        phone=(TextView)findViewById(R.id.Ephone);

        code=(EditText)findViewById(R.id.Ecode);
        verify=(Button)findViewById(R.id.Bverified);


        String sName=getIntent().getExtras().getString("Name");
        name.setText(sName);
        String sEmail=getIntent().getExtras().getString("Email");
        email.setText(sEmail);
        String sPhone=getIntent().getExtras().getString("Phone");
        phone.setText(sPhone);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progreso = new ProgressDialog(VerifyActivity.this);
                progreso.setMessage("Verification processing please wait...");
                progreso.show();

                String sCode=code.getText().toString();
                String sEmail=getIntent().getExtras().getString("Email");


                Response.Listener<String>responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success)
                            {
                                progreso.dismiss();
                                String arraymessage=jsonResponse.getString("verified");
                                Toast.makeText(VerifyActivity.this,arraymessage,Toast.LENGTH_LONG).show();
                                int array1=jsonResponse.getInt("ver");
                                if(array1==1) {
                                    Intent intent = new Intent(VerifyActivity.this, LoginActivity.class);
                                    VerifyActivity.this.startActivity(intent);
                                    finish();
                                }

                            }

                            else
                            {
                                progreso.dismiss();
                                AlertDialog.Builder builder=new AlertDialog.Builder(VerifyActivity.this);
                                builder.setMessage("Veryfy Failed !!!!!!!")
                                        .setNegativeButton("Retry .....",null)
                                        .create()
                                        .show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                VerifyRequest registerRequest=new VerifyRequest(sEmail, sCode, responseListener);
                RequestQueue queue= Volley.newRequestQueue(VerifyActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}
