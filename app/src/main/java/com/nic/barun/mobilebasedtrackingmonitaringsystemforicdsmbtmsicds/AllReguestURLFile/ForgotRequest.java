package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/14/2018.
 */

public class ForgotRequest extends StringRequest {
    private static final String FORGOT_REQUEST_URL="http://192.168.1.102/nic_icds_app/sup_forgot_password.php";
    //private static final String FORGOT_REQUEST_URL="http://icdsjalpaiguri.in/nic_icds_app/sup_forgot_password.php";
    private Map<String,String>params;
    public ForgotRequest(String sRegEmail,Response.Listener<String>listener)
    {
        super(Method.POST,FORGOT_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("mobile",sRegEmail);
    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }

}
