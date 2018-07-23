package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/18/2018.
 */
public class DataReportSaveRequest extends StringRequest {
    private static final String report_save_request_url="http://icdsjalpaiguri.in/nic_icds_app/assignment_data.php";
    private Map<String,String> params;
    public DataReportSaveRequest(int child, String centre,String lati,String longi,String fullAddress, Response.Listener<String>listener)
    {
        super(Method.POST,report_save_request_url,listener,null);
        params=new HashMap<>();
        params.put("nochild",child+"");
        params.put("centre",centre);
        params.put("lati",lati);
        params.put("longi",longi);
        params.put("fullAddress",fullAddress);
    }
    @Override
    public Map<String,String>getParams()
    {
        return params;
    }

}
