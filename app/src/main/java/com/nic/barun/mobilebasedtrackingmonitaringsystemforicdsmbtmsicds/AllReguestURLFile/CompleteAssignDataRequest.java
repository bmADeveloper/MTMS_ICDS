package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllReguestURLFile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NIC1 on 28-02-2018.
 */


public class CompleteAssignDataRequest extends StringRequest {

    //http://marmot.co.in/nic_icds_app/assignment_data.php
    private static final String COMPLETE_DATA_REQUEST_URL="http://192.168.1.102/nic_icds_app/assignment_data.php";
    //private static final String COMPLETE_DATA_REQUEST_URL="http://icdsjalpaiguri.in/nic_icds_app/assignment_data.php";
    private Map<String,String> params;
    public CompleteAssignDataRequest(String centre_code,String sup_code,String gp_ward_name,String block_name, String awc_building_yes, String awc_found_open, String fullAddress, String lati,String longi,String total_snp,String benef_with_snp,String total_child_7mnth_6yrs,String children_served_7mnth_6years,String total_children_3_6years,String children_in_pse_3_6years,String register_present_for_assessment,String child_5yrs_below_weighed,String mothers_meeting,String total_child_below_5yrs,String malnou_child_modarate,String malnou_child_severe,String ece_curriculam_followed,String slocation_image, Response.Listener<String> listener)
    {
        super(Method.POST,COMPLETE_DATA_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("centre_code",centre_code);
        params.put("sup_code",sup_code);
        params.put("gp_ward_name",gp_ward_name);
        params.put("block_name",block_name);

        params.put("awc_building_yesno",awc_building_yes);//................1 yes no
        params.put("awc_found_open",awc_found_open);   //.................2
        params.put("fullAddress",fullAddress);
        params.put("lati2",lati);
        params.put("longi2",longi);

        params.put("total_snp_benefeceries",total_snp);//......................... 3
        params.put("benefeceries_served_with_snp",benef_with_snp);//......................... 4
        params.put("total_child_7mnth_6yr",total_child_7mnth_6yrs);//......................... 5
        params.put("child_served_7mnth_6yr",children_served_7mnth_6years);//......................... 6
        params.put("total_child_3yr_6yr",total_children_3_6years);//......................... 7
        params.put("child_in_pse_3yr_6yr",children_in_pse_3_6years);//......................... 8
        params.put("register_present_for_assessment",register_present_for_assessment);//......................... 9

        params.put("child_below_5yr_weighed",child_5yrs_below_weighed);//......................... 11
        params.put("mothers_meeting",mothers_meeting);//......................... 10
        params.put("total_child_below_5yr",total_child_below_5yrs);//......................... 12
        params.put("malnourished_child_moderate",malnou_child_modarate);//......................... 13
        params.put("malnourished_child_severe",malnou_child_severe);//......................... 14
        params.put("eece_curriculom_followed",ece_curriculam_followed);//......................... 15

        params.put("location_image",slocation_image);//......................... 15

    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

