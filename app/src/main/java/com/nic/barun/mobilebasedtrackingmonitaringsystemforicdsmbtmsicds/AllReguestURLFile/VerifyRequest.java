package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/11/2018.
 */

public class VerifyRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://192.168.1.102/nic_icds_app/verified.php";
    //private static final String REGISTER_REQUEST_URL="http://icdsjalpaiguri.in/nic_icds_app/verified.php";
    private Map<String,String>params;
    public VerifyRequest(String sEmail, String sCode,Response.Listener<String>listener)
    {
        super(Method.POST,REGISTER_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("email",sEmail);
        params.put("code",sCode);



    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
