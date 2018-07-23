package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/17/2018.
 */

public class ChangePasswordRequest extends StringRequest {
    private static final String Change_Password_URL = "http://192.168.1.102/nic_icds_app/sup_change_password.php";
    //private static final String Change_Password_URL = "http://icdsjalpaiguri.in/nic_icds_app/sup_change_password.php";
    private Map<String, String> params;

    public ChangePasswordRequest(String sEmail,String sCurrentPass,String sNewPass,String sConfirmPass, Response.Listener<String> listener2)
    {
        super(Method.POST,Change_Password_URL,listener2,null);
        params=new HashMap<>();
        params.put("email",sEmail);
        params.put("current_password",sCurrentPass);
        params.put("new_password",sNewPass);
        params.put("confirm_password",sConfirmPass);
    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
