package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class SignUpActivity extends AppCompatActivity {
    //..................Declare.....................................................................

    private CircleImageView imagePhoto;
    EditText name,dob,email,phone_number,password,confirmpassword;
    RadioButton Rmale,Rfemale;
    String sGender;
    private Button registrar,loginLink;

    Integer REQUEST_CAMERA=1,SELECT_FILE=0;
    private ProgressBar progressBar;
    private int request_code = 1;
    private Bitmap bitmap;
    private ProgressDialog progreso;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//....................Casting All...................................................................

        loginLink = (Button) findViewById(R.id.link_login);
        registrar = (Button)findViewById(R.id.Breg);

        imagePhoto = (CircleImageView) findViewById(R.id.ImageViewId);
        name = (EditText) findViewById(R.id.Ename);
        dob=(EditText) findViewById(R.id.Edob);
        Rmale=(RadioButton)findViewById(R.id.male);
        Rfemale=(RadioButton)findViewById(R.id.female);
        email = (EditText) findViewById(R.id.Eemail);
        phone_number=(EditText) findViewById(R.id.Ephone_number);
        password = (EditText)findViewById(R.id.Epassword);
        confirmpassword=(EditText) findViewById(R.id.Econfirmpassword);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);





        //.................................Validation..........................................



        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String sname = name.getText().toString();

                    if (sname.isEmpty() || !sname.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        name.setError("Name allow only String,length 3 to 25!!!");

                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        name.setError("correct" ,tickChecked);
                    }
                }

            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sEmail = email.getText().toString();
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
                        email.setError("Invalid Email Id...!!!");

                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        email.setError("correct" ,tickChecked);
                    }
                }

            }
        });

        phone_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sphn = phone_number.getText().toString();
                    if (sphn.isEmpty() || !sphn.matches("^[0-9]{10,10}$")) {
                        phone_number.setError("10 digit mobile number allow!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        phone_number.setError("correct" ,tickChecked);
                    }
                }

            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String sPassword = password.getText().toString();

                    if (sPassword.isEmpty() || !sPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}+$")) {
                        password.setError("Invalid password(atleast one upper,lower,special char.,digit and length must be atleast 8)!!!");

                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        password.setError("correct" ,tickChecked);
                    }
                }

            }
        });
        confirmpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String sPassword = password.getText().toString();
                    String sConPassword = confirmpassword.getText().toString();
                    if (sConPassword.isEmpty()||!sPassword.equals(sConPassword)) {
                        confirmpassword.setError("Password does not match!!!!!!");
                    } else {
                        Drawable tickChecked=getResources().getDrawable(R.drawable.ic_checked);
                        tickChecked.setBounds(0,0,tickChecked.getIntrinsicWidth(),tickChecked.getIntrinsicHeight());
                        confirmpassword.setError("correct" ,tickChecked);
                    }
                }

            }
        });

        //..........................................................................................
















        requestQueue = Volley.newRequestQueue(this);
//..........................End....................................................................
//....................................male or female...............................................
                 Rmale.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         if (Rmale.isChecked()) {
                             sGender = Rmale.getText().toString();
                         }
                     }
                 });
                 Rfemale.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(Rfemale.isChecked())
                         {
                             sGender=Rfemale.getText().toString();
                         }
                     }
                 });


        //............................................................................

//....................................Date picker..................................................
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c=Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth + "/"+ (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();

                if (dob != null) {
                    Drawable tickChecked = getResources().getDrawable(R.drawable.ic_checked);
                    tickChecked.setBounds(0, 0, tickChecked.getIntrinsicWidth(), tickChecked.getIntrinsicHeight());
                    dob.setError("correct", tickChecked);
                }
            }
        });
//................................end Date picker...................................................


//............................Supervisor data save..................................................
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Registrar();
            }
        });
//.................................end..............................................................


//...........................Image choose internal storage..........................................

        imagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     Intent i = null;
                if(Build.VERSION.SDK_INT < 19){
                    i = new Intent();
                    i.setAction(Intent.ACTION_GET_CONTENT);
                }else {
                    i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                }
                i.setType("image/*");
                startActivityForResult(i, request_code);*/

                SelectImage();


            }
        });
//..................................end.............................................................

//............................Goto LoginActivity....................................................

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                SignUpActivity.this.startActivity(intent);
                finish();
            }
        });
//....................................End...........................................................

    }  //End onCreate method( bundle )

    private void SelectImage() {

        final CharSequence[]items={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);

        builder .setTitle( Html.fromHtml("<font color='#027b71'> Add Image </font>"));

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(items[i].equals("Camera"))
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);

                }
                /*else if(items[i].equals("Gallery"))
                {

                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    startActivityForResult(intent,SELECT_FILE);
                }
                */else if(items[i].equals("Cancel"))
                {
                    dialogInterface.dismiss();
                }
            }

        });
        builder.show();

    }
    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        //super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== RESULT_OK){

            if(requestCode==REQUEST_CAMERA){
                    Bundle bundle = data.getExtras();
                    bitmap = (Bitmap) bundle.get("data");
                    imagePhoto.setImageBitmap(bitmap);



            }
            else if(requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                    imagePhoto.setImageURI(selectedImageUri);
                    try{
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
                    imagePhoto.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }

        }
    }


//............................................Data Save Method pass the value.......................

    private void Registrar() {
       // if (!validation()) return;    //..................................................................Validation
        final String sname = name.getText().toString();
        final String semail = email.getText().toString();
        final String sphone = phone_number.getText().toString();
        progreso = new ProgressDialog(this);
        progreso.setMessage("Registration processing please wait...");
        progreso.show();
        //String url = "http://icdsjalpaiguri.in/nic_icds_app/sup_signup.php?";
        String url = "http://192.168.1.102/nic_icds_app/sup_signup.php?";


        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserParcelable userParcelable = new UserParcelable();;
                Log.i("RESPUESTA JSON: ",""+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){

                        progreso.dismiss();
                        //Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_LONG).show();
                        signup_success_Toast();

                        Intent intent = new Intent(SignUpActivity.this,VerifyActivity.class);
                        intent.putExtra("Name",sname);
                        intent.putExtra("Email",semail);
                        intent.putExtra("Phone",sphone);
                        SignUpActivity.this.startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_LONG).show();

/*
                        Toast toast=new Toast(SignUpActivity.this);
                        toast.setText(jsonObject.getString("error"));
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,600);
                        toast.show();
                        */
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
                //Toast.makeText(getApplicationContext(),"no internet connection!!!",Toast.LENGTH_SHORT).show();
                reg_failed_conn_Toast();

                Log.i("ERROR: ",""+error.toString());
                progreso.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String  sImagePhoto = convertImgString(bitmap);

                String sname = name.getText().toString();
                String sDob=dob.getText().toString();
                //String sGender=gender.getText().toString();
                String sEmail = email.getText().toString();
                String sPhone_number = phone_number.getText().toString();
                String sPassword =  password.getText().toString();
                

                Map<String,String> parametros = new HashMap<>();
                parametros.put("photo",sImagePhoto);
                parametros.put("name",sname);
                parametros.put("dob",sDob);
                parametros.put("gender",sGender);
                parametros.put("email",sEmail);
                parametros.put("phone_number",sPhone_number);
                parametros.put("password",sPassword);




                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }

//....................................................End...........................................

//...........................................Image Convert to String................................

    private String convertImgString(Bitmap bitmap) {

        String imagenString;
        ByteArrayOutputStream array=new ByteArrayOutputStream();
        if(bitmap!=null){
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,array);
            byte[] imagenByte=array.toByteArray();
            imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);
        }else{
            imagenString = "no imagen";
        }

        return imagenString;
    }

//...................................................End............................................

//............................................Validation All fields.................................

    private boolean validation() {
        boolean valid = true;

        String sname = name.getText().toString();
       // String sgender = gender.getText().toString();
        String sdob = dob.getText().toString();
        String sphn = phone_number.getText().toString();
        String sPassword = password.getText().toString();
        String sConPassword = confirmpassword.getText().toString();
        String sEmail = email.getText().toString();

        if (sname.isEmpty() || !sname.matches("^[A-Za-z.]{4,25}$")) {
            name.setError("Name allow only String,length 4 to 25!!!");
            valid = false;
        } else {
            name.setError(null);
        }

        if (sGender.isEmpty()) {
            Rmale.setError("Invalid!!!");
            valid = false;
        }
        if (sdob.isEmpty()) {
            dob.setError("Invale DOB allow only digit or - or /!!!");
            valid = false;
        } else {
            dob.setError(null);
        }


        if (sEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            email.setError("Invalid email ID!!!");
            valid = false;
        } else {
            email.setError(null);
        }


        if (sphn.isEmpty() || !sphn.matches("^[0-9]{10,10}$")) {
            phone_number.setError("10 digit mobile number allow!!!");
            valid = false;
        } else {
            phone_number.setError(null);
        }



        if (sPassword.isEmpty() || !sPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}+$")) {
            password.setError("Invalid passwoed(atleast one upper/lower/special/digit length 8)!!!");
            valid = false;
        } else {
            password.setError(null);
        }

        if(!sPassword.equals(sConPassword))
        {
            confirmpassword.setError("Password does not match!!!!!!");
            valid = false;
        }
        else
        {
            confirmpassword.setError(null);
        }

        return valid;
    }

//........................................................End validation............................
public void signup_success_Toast()
{
    LayoutInflater inflater=getLayoutInflater();
    View view=inflater.inflate(R.layout.success_reg,null);
    Toast toast=new Toast(this);
    toast.setView(view);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,600);
    toast.show();
}
    public void reg_failed_conn_Toast()
    {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.signup_failed_conn_error,null);
        Toast toast=new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,0,600);
        toast.show();
    }
//..........................................Image brouse media Storage allow........................
 /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == request_code){
            imagePhoto.setImageURI(data.getData());

            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
                imagePhoto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
*/



//...................................................End...........................................

}//end main Activity class
