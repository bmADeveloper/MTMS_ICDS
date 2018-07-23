package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile.DataReportSaveRequest;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.GPSTracker.GPSTracker;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataReportWithLocationFragment extends Fragment {

    private EditText Echild,Ecentre;
    private Button save_report;
    private ProgressDialog pd;
    private int child;
    String lati,longi;
    String fullAddress;
    Geocoder geocoder;
    List<Address> addressList;
    Context mContext;
    double latitude,longitude;


    public DataReportWithLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_data_report_with_location, container, false);

//.............................Casting id...........................................................
        Echild =(EditText)v.findViewById(R.id.Enoc);
        Ecentre=(EditText)v.findViewById(R.id.Ecn);
        save_report=(Button)v.findViewById(R.id.Bsave);

//....................................End...........................................................


//..................................Onclick listional Button........................................

        save_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd=new ProgressDialog(getActivity());
                pd.setMessage("Data Report saved Processing.......");
                pd.show();
                //call the method
                save_data();
                Echild.setText("");
                Ecentre.setText("");

            }
        });
        //.................................................................................................
        return v;
    }

    private void save_data() {
        final int child=Integer.parseInt(Echild.getText().toString());
        final String centre=Ecentre.getText().toString();
        //location with lati and longi  method
        Latitude_Longitude();

        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonrespnse = new JSONObject(response);
                    boolean success = jsonrespnse.getBoolean("success");
                    if (success) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Report saved successfully", Toast.LENGTH_LONG).show();
                        //goto next activity
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Report saved faild...")
                                .setNegativeButton("try again...", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        //call SaveRequest class files pass the string values
        DataReportSaveRequest dataReportSaveRequest = new DataReportSaveRequest(child, centre,lati,longi,fullAddress, response);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(dataReportSaveRequest);
    }

    private void Latitude_Longitude() {
          mContext=getActivity();
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




                //lati or longi value here
                lati = Double.toString(latitude);
                longi = Double.toString(longitude);
                Toast.makeText(getActivity(),
                        "Your Location is - \nLat: " + lati + "\nLong: " + longi, Toast.LENGTH_LONG).show();
                try {
                    addressList = geocoder.getFromLocation(latitude,longitude,1);

                    String addressStr = addressList.get(0).getAddressLine(0);
                    String areaStr = addressList.get(0).getLocality();
                    String cityStr = addressList.get(0).getAdminArea();
                    String countryStr = addressList.get(0).getCountryName();
                    String postalcodeStr = addressList.get(0).getPostalCode();

                    fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;
                    Toast.makeText(getActivity(),
                            "Your Location is - \nLat: " + lati + "\nLong: " + fullAddress, Toast.LENGTH_LONG).show();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            else {
                // Can't get location. // GPS or network is not enabled.// Ask user to enable GPS/network in settings.

                gps.showSettingsAlert();
            }
        }
    }

}
