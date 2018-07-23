package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/24/2018.
 * http://192.168.1.102/
 */

public class CentreDetailsRequest extends StringRequest {
    private static final String CENTRE_DETAILS_REQUEST_URL="http://192.168.1.102/nic_icds_app/centre_details_search.php";
    //private static final String CENTRE_DETAILS_REQUEST_URL="http://icdsjalpaiguri.in/nic_icds_app/centre_details_search.php";
    private Map<String,String> params;
    public CentreDetailsRequest(String sEcentre_code,String ssup_code,Response.Listener<String>listener)
    {
        super(Method.POST,CENTRE_DETAILS_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("centre_code",sEcentre_code);
        params.put("sup_code",ssup_code);



    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
