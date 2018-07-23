package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.ForgotRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText regEmail;
    private Button Bforgot;
    private TextView Bsignup;
    ProgressDialog pd;
private ProgressBar gotoReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        regEmail=(EditText) findViewById(R.id.Eemail);
        Bforgot=(Button)findViewById(R.id.Bforgot);
        Bsignup=(TextView) findViewById(R.id.Esignup);

        gotoReg=(ProgressBar)findViewById(R.id.progressBar);
        gotoReg.setVisibility(View.GONE);
//.................................................................................................
        Bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoReg.setVisibility(View.VISIBLE);
                Intent lingSignup=new Intent(ForgotPasswordActivity.this,SignUpActivity.class);
                ForgotPasswordActivity.this.startActivity(lingSignup);

            }
        });
//..................................................................................................

//..................................................................................................
        Bforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if (!validar()) return;    //..................................................................Validation
                ForgotPassword();
            }
        });
    }

    private void ForgotPassword() {
        pd=new ProgressDialog(ForgotPasswordActivity.this);
        pd.setMessage("Forgot Password Processing.......");
        pd.show();

        final String sRegEmail=regEmail.getText().toString();
        Response.Listener<String>responseListener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonResponse=new JSONObject(response);
                    boolean success=jsonResponse.getBoolean("success");
                    if(success)
                    {
                        pd.dismiss();
                        String arrayMsg=jsonResponse.getString("forgoted");
                        Toast.makeText(ForgotPasswordActivity.this,arrayMsg,Toast.LENGTH_LONG).show();
                        int forgotedSuccess=jsonResponse.getInt("check");
                        if(forgotedSuccess==1)
                        {
                            Intent linkLogin=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                            ForgotPasswordActivity.this.startActivity(linkLogin);
                        }
                    }
                    else
                    {
                        pd.dismiss();
                        AlertDialog.Builder builder= new AlertDialog.Builder(ForgotPasswordActivity.this);
                        builder.setMessage("Veryfy Faild !!!!!!!")
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
        ForgotRequest forgotRequest=new ForgotRequest(sRegEmail,responseListener);
        RequestQueue queue= Volley.newRequestQueue(ForgotPasswordActivity.this);
        queue.add(forgotRequest);

    }

    private boolean validar() {
        boolean valid = true;

        String sEmail = regEmail.getText().toString();


        if (sEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            regEmail.setError("Invalid Email ID");
            valid = false;
        } else {
            regEmail.setError(null);
        }

        return valid;
    }
}
