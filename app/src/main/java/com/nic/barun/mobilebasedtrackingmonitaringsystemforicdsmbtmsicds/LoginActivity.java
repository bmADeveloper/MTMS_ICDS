package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.Utils.UserParcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maycol Meza on 14/11/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private Button sup_login,registrar;
    private TextView forgot;
    private EditText email;
    private EditText password;
    private ProgressDialog progreso;
    private RequestQueue requestQueue;
    StringRequest stringRequest;
    private ProgressBar loginForgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.Eemail);
        password = (EditText) findViewById(R.id.Epass);

        sup_login = (Button)findViewById(R.id.Blogin);
        registrar = (Button) findViewById(R.id.Bsignup);
        forgot=(TextView)findViewById(R.id.Eforgot);

        loginForgo=(ProgressBar)findViewById(R.id.progressBar);
        loginForgo.setVisibility(View.GONE);

        requestQueue = Volley.newRequestQueue(this);

        //.................................validation...............................................
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEmail = email.getText().toString();
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
                        email.setError("Invalid Email Id...!!!");

                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_security_on);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        email.setError("correct" ,tickChecked);
                    }
                }
            }
        });

   /*     password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sPassword =  password.getText().toString();
                    if (sPassword.isEmpty() || !sPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}+$")) {
                        password.setError("Invalid password(atleast one upper,lower,special char.,digit and length must be atleast 8)!!!");

                    } else {
                        Drawable tickChecked = getResources().getDrawable(R.drawable.ic_security_on);
                        tickChecked.setBounds(0, 0, tickChecked.getIntrinsicWidth(), tickChecked.getIntrinsicHeight());
                        password.setError("correct", tickChecked);
                    }
                }
            }
        });
*/        //..........................................................................................





        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginForgo.setVisibility(View.VISIBLE);
                Intent intent =new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                LoginActivity.this.startActivity(intent);
                // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginForgo.setVisibility(View.VISIBLE);
                Intent intent =new Intent(LoginActivity.this,SignUpActivity.class);
                LoginActivity.this.startActivity(intent);
                // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });

        sup_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginForgo.setVisibility(View.VISIBLE);

                login_method();
            }
        });
    }

    private void login_method() {

        if (!validar()) return;    //..................................................................Validation
        progreso = new ProgressDialog(this);
        progreso.setMessage("Login processing please wait...");
        progreso.show();

        //http://marmot.co.in/nic_icds_app/sup_login.php
        String login_request_url = "http://192.168.1.102/nic_icds_app/sup_login.php?";
        //String login_request_url = "http://icdsjalpaiguri.in/nic_icds_app/sup_login.php?";

        stringRequest = new StringRequest(Request.Method.POST, login_request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserParcelable userParcelable = new UserParcelable();;
                Log.i("RESPUESTA JSON: ",""+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){
                        email.setText("");
                        password.setText("");

                        userParcelable.setId(jsonObject.getJSONArray("tabledata").getJSONObject(0).getString("sup_code"));
                        userParcelable.setEmail(jsonObject.getJSONArray("tabledata").getJSONObject(0).getString("email"));
                        userParcelable.setName(jsonObject.getJSONArray("tabledata").getJSONObject(0).getString("name"));
                        userParcelable.setImage(jsonObject.getJSONArray("tabledata").getJSONObject(0).getString("photo"));
                        userParcelable.setNumber(jsonObject.getJSONArray("tabledata").getJSONObject(0).getString("ph_number"));

                        //Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                        success_login_Toast();
                        progreso.dismiss();

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("DATA_USER",userParcelable);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                        Log.i("RESPUESTA JSON: ",""+jsonObject.getString("error"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progreso.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"Login faild check internet connection!!!",Toast.LENGTH_SHORT).show();
                login_failed_conn_Toast();
                progreso.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {//para enviar los datos mediante POST
                String sEmail = email.getText().toString();
                String sPassword =  password.getText().toString();

                Map<String,String> parametros = new HashMap<>();
                parametros.put("email",sEmail);
                parametros.put("password",sPassword);
                //estos parametros son enviados a nuestro web service

                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }

    private boolean validar() {
        boolean valid = true;

        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();

        if (sEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            email.setError("Invalid Email ID");
            valid = false;
        } else {
            email.setError(null);
        }

        if (sPassword.isEmpty() || password.length() < 8 || password.length() > 16) {
            password.setError("Password should be allow 8 to 16 char....");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    public void success_login_Toast()
    {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.login_success,null);
        Toast toast=new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,500);
        toast.show();
    }

    public void login_failed_conn_Toast()
    {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.login_failed_error_conn,null);
        Toast toast=new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,500);
        toast.show();
    }

}
