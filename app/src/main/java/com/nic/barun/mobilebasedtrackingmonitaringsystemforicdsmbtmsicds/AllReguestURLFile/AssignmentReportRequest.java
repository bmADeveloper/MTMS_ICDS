package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barun on 3/3/2018.
 */



public class AssignmentReportRequest extends StringRequest {

    private static final String ASSIGN_REPORT_REQUEST_URL="http://icdsjalpaiguri.in/nic_web/nic_icds_app/assignment_data.php";
    private Map<String,String> params;
    public AssignmentReportRequest(String recv_sup_code_from_main,String sone_date,String c_date,String sweekly_date_to,String sany_date_from,String sany_date_to,Response.Listener<String>listener)
    {
        super(Method.POST,ASSIGN_REPORT_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("sup_code",recv_sup_code_from_main);
        params.put("any_one_date_pick",sone_date);
        params.put("weekly_date_pick_from",c_date);
        params.put("weekly_date_to",sweekly_date_to);
        params.put("any_date_pick_from",sany_date_from);
        params.put("any_date_pick_to",sany_date_to);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
