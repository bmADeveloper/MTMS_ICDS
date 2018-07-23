package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuidlineFragment extends Fragment {
private TextView guidline;

    public GuidlineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_guidline, container, false);
        guidline=(TextView)v.findViewById(R.id.guidline);
        String htmlAsString = getString(R.string.html);
        Spanned htmlAsSpanned = Html.fromHtml(htmlAsString);

        guidline.setText(htmlAsSpanned);



               WebView webView = (WebView)v.findViewById(R.id.webView);
              webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);

        return  v;
    }

}
