package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 2/24/2018.
 */

public class FewAssignDataRequest extends StringRequest {

    private static final String FEW_DATA_REQUEST_URL="http://192.168.1.102/nic_icds_app/assignment_data.php";
    //private static final String FEW_DATA_REQUEST_URL="http://icdsjalpaiguri.in/nic_icds_app/assignment_data.php";
    private Map<String,String> params;
    public FewAssignDataRequest(String scentre_code,String ssup_code,String sgp_ward_name,String sblock_name, String sBEawc_building_yesno, String sEawc_found_close, String fullAddress, String lati2,String longi2, Response.Listener<String> listener)
    {
        super(Method.POST,FEW_DATA_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("centre_code",scentre_code);
        params.put("sup_code",ssup_code);
        params.put("gp_ward_name",sgp_ward_name);
        params.put("block_name",sblock_name);

        params.put("awc_building_yesno",sBEawc_building_yesno);
        params.put("awc_found_close",sEawc_found_close);
        params.put("fullAddress",fullAddress);
        params.put("lati2",lati2);
        params.put("longi2",longi2);


    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

