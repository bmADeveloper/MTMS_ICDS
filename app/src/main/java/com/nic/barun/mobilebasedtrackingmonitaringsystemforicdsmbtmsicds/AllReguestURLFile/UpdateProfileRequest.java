package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/17/2018.
 */


public class UpdateProfileRequest extends StringRequest {
    private static final String update_profile_URL = "http://192.168.1.102/nic_icds_app/sup_update_profile.php";
    //private static final String update_profile_URL = "http://icdsjalpaiguri.in/nic_icds_app/sup_update_profile.php";
    private Map<String, String> params;

    public UpdateProfileRequest(String sEmail,String sPhone, Response.Listener<String> listener2)
    {
        super(Method.POST,update_profile_URL,listener2,null);
        params=new HashMap<>();
        params.put("email",sEmail);
        params.put("phone",sPhone);

    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}