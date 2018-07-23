package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.HomeFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.CompleteAssignDataRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.GPSTracker.GPSTracker;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {
private ImageView location_image;
private Button Bprev3,Bdata_save_final;
private CheckBox ch_lati_longi,ch_location;
private TextView TVcentre_name,TVcentre_address,TVgp_ward_name,TVblock_name,TVcentre_code;
Integer REQUEST_CAMERA=1,SELECT_FILE=0;
private Bitmap bitmap;

ProgressDialog pd;
    String  slocation_image;
    String fullAddress;
    String lati,longi;
    TextView resultView;
    Geocoder geocoder;
    List<Address> addressList;
    // GPSTracker class    GPSTracker gps;
    Context mContext;
    double latitude,longitude;

    public FourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_four, container, false);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        mContext = getActivity();


        ch_lati_longi=(CheckBox)v.findViewById(R.id.Ch_lati_lingi);
        ch_location=(CheckBox)v.findViewById(R.id.Ch_location);
       // resultView=(TextView)v.findViewById(R.id.shorslt);
        location_image=(ImageView) v.findViewById(R.id.location_image);


        TVcentre_code=(TextView)v.findViewById(R.id.TVcentre_code);
        TVcentre_name=(TextView)v.findViewById(R.id.TVcentre_name);
        TVcentre_address=(TextView)v.findViewById(R.id.TVcentre_adds);
        TVgp_ward_name=(TextView)v.findViewById(R.id.TVgp_ward_name);
        TVblock_name=(TextView)v.findViewById(R.id.TVblock_name);






//....................17 Data received from twoFrag.............................
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

        final String      children_served_7mnth_6years=bundle.getString("children_served_7mnth_6years");     //.......................... 6
        final String        total_children_3_6years=bundle.getString("total_children_3_6years");               //.......................... 7
        final String      children_in_pse_3_6years=bundle.getString("children_in_pse_3_6years");             //.......................... 8
        final String       register_present_for_assessment=bundle.getString("register_present_for_assessment");   //.......................... 9
        final String      mothers_meeting=bundle.getString("mothers_meeting");                                   //.......................... 10

        final String child_5yrs_below_weighed= bundle.getString("child_5yrs_below_weighed");           //................. 11
        final String  total_child_below_5yrs=bundle.getString("total_child_below_5yrs");               //..................12
        final String malnou_child_modarate=bundle.getString("malnou_child_modarate");                 //................. 13
        final String  malnou_child_severe=bundle.getString("malnou_child_severe");                     //...............    14
        final String ece_curriculam_followed=bundle.getString("ece_curriculam_followed");             //...............     15


        final String c_name= bundle.getString("c_name");
        final String c_add= bundle.getString("c_add");

        //Toast.makeText(getActivity(),"awc_building_yesno:"+awc_building_yes,Toast.LENGTH_LONG).show();
       // Toast.makeText(getActivity(),"First Fragment data"+total_child_7mnth_6yrs+benef_with_snp+total_snp+awc_found_open+awc_building_yes,Toast.LENGTH_LONG).show();
      //  Toast.makeText(getActivity(),"Second Frag data "+children_served_7mnth_6years+total_children_3_6years+children_in_pse_3_6years+register_present_for_assessment+mothers_meeting ,Toast.LENGTH_LONG).show();

        TVcentre_code.setText("19328"+centre_code);
        TVcentre_name.setText(c_name);
        TVcentre_address.setText(c_add);
        //TVgp_ward_name.setText(sgp_ward_name);
        //TVblock_name.setText(sblock_name);

       // Toast.makeText(getActivity(),"Three Frag data "+child_5yrs_below_weighed+total_child_below_5yrs+malnou_child_modarate+malnou_child_severe+ece_curriculam_followed ,Toast.LENGTH_LONG).show();
        //..............................................................................




        Bprev3=(Button)v.findViewById(R.id.Bprev3);
        Bdata_save_final=(Button)v.findViewById(R.id.Bdata_save_final);

        Bdata_save_final.setEnabled(false);

//.............................................goto three fragment..................................
        Bprev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                HomeFragment homeFragment=new HomeFragment();
                //twoFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.mainLayout,homeFragment);
                fragmentTransaction.commit();
            }
        });

//..................................................................................................

        //.......................................location image listnr..............................
               location_image.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Bdata_save_final.setEnabled(true);
                       Bdata_save_final.setBackgroundColor(Color.parseColor("#48C9B0"));    //Green Color
                       SelectLocationImage();

                   }
               });


        //.......................................END location image listnr..............................


//....................................................data save final...............................

        Bdata_save_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validation_save_final()) return;//..................................................................Validation

                 slocation_image = convertImgString(bitmap);
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
                                    Bundle bundle2=new Bundle();
                                    bundle2.putString("centre_code",centre_code);
                                    bundle2.putString("Address",fullAddress);
                                    bundle2.putString("c_name",c_name);
                                    //.................
                                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                                    SuccessFragment successFragment=new SuccessFragment();
                                    successFragment.setArguments(bundle2);

                                    fragmentTransaction.replace(R.id.mainLayout,successFragment);
                                    fragmentTransaction.commit();

                                }
                            }
                            else
                            {
                                pd.dismiss();
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                builder.setMessage("Data does not get Saved ")
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
                CompleteAssignDataRequest completeAssignDataRequest=new CompleteAssignDataRequest(centre_code,sup_code,sgp_ward_name,sblock_name,awc_building_yes,awc_found_open,fullAddress,lati,longi,total_snp,benef_with_snp,total_child_7mnth_6yrs,children_served_7mnth_6years,total_children_3_6years,children_in_pse_3_6years,register_present_for_assessment,child_5yrs_below_weighed,mothers_meeting,total_child_below_5yrs,malnou_child_modarate,malnou_child_severe,ece_curriculam_followed,slocation_image,responseListener);
                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                requestQueue.add(completeAssignDataRequest);


            }
        });
//..................................................................................................

//...............................................get latitude and longitude.........................

        ch_lati_longi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)

                        != PackageManager.PERMISSION_GRANTED

                        && ActivityCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION)

                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    Toast.makeText(mContext, "You need have granted permission",

                            Toast.LENGTH_SHORT).show();
                    GPSTracker gps = new GPSTracker(mContext, getActivity());

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();

                        // \n is for new line

                        Toast.makeText(getActivity(),
                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                        lati = Double.toString(latitude);                //find latitude
                        longi = Double.toString(longitude);              //find longitude
                       // resultView.setText(lati);
                       // resultView.setText(longi);
                    } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }
                }
            }
        });

//..................................................................................................

//..................................................get location with address.......................
ch_location.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            addressList = geocoder.getFromLocation(latitude,longitude,1);

            String addressStr = addressList.get(0).getAddressLine(0);
            String areaStr = addressList.get(0).getLocality();
            String cityStr = addressList.get(0).getAdminArea();
            String countryStr = addressList.get(0).getCountryName();
            String postalcodeStr = addressList.get(0).getPostalCode();

            fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;
            Toast.makeText(getActivity(),fullAddress,Toast.LENGTH_LONG).show();
            //result.setText(fullAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
});


//..................................................................................................
        return v;
    }

    private boolean validation_save_final() {
        boolean valid = true;
       String slocation_image = convertImgString(bitmap);

        if(!ch_lati_longi.isChecked())
        {
            ch_lati_longi.setError("Field reqiured...");
            valid = false;
        }
        if(!ch_location.isChecked())
        {
            ch_location.setError("Field reqiured...");
            valid = false;
        }

        return valid;
    }

    private void SelectLocationImage() {
        final CharSequence[]items={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        builder .setTitle( Html.fromHtml("<font color='#027b71'> Add Image </font>"));

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(items[i].equals("Camera"))
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);

                }else if(items[i].equals("Gallery"))
                {
                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    startActivityForResult(intent,SELECT_FILE);
                }else if(items[i].equals("Cancel"))
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
                location_image.setImageBitmap(bitmap);



            }else if(requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                location_image.setImageURI(selectedImageUri);
                try{
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),data.getData());
                    location_image.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }

        }
    }
    //...........................................Image Convert to String................................

    private String convertImgString(Bitmap bitmap) {

        String imagenString;
        ByteArrayOutputStream array=new ByteArrayOutputStream();
        if(bitmap!=null){
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
            byte[] imagenByte=array.toByteArray();
            imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);
        }else{
            imagenString = "no imagen";
        }

        return imagenString;
    }

//...................................................End............................................


    //..........................................extra gps code........................................
 @Override    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                                     int[] grantResults) {
     super.onRequestPermissionsResult(requestCode, permissions, grantResults);

     switch (requestCode) {
         case 1: {
             // If request is cancelled, the result arrays are empty.

             if (grantResults.length > 0

                     && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                 // permission was granted, yay! Do the
                 // contacts-related task you need to do.
                 GPSTracker gps = new GPSTracker(mContext, getActivity());

                 // Check if GPS enabled

                 if (gps.canGetLocation()) {

                     double latitude = gps.getLatitude();
                     double longitude = gps.getLongitude();

                     // \n is for new line

                     Toast.makeText(getActivity(),
                             "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                 } else {
                     // Can't get location.

                     // GPS or network is not enabled.

                     // Ask user to enable GPS/network in settings.

                     gps.showSettingsAlert();
                 }

             } else {

                 // permission denied, boo! Disable the

                 // functionality that depends on this permission.
                 Toast.makeText(mContext, "You need to grant permission",

                         Toast.LENGTH_SHORT).show();
             }
             return;
         }
     }
 }

//..................................................................................................


}
