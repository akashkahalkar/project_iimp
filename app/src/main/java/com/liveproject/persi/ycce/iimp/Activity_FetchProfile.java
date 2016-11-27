package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

/**
 * Created by Tiger on 28-08-2016.
 */
public class Activity_FetchProfile extends AppCompatActivity {

    TextView tv_firstname, tv_emailid, tv_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch_profile);

        tv_id = (TextView) findViewById(R.id.fp_tv_fetchid);
        tv_firstname = (TextView) findViewById(R.id.fp_tv_fetchusername);
        tv_emailid = (TextView) findViewById(R.id.fp_tv_fetchemailid);

        Intent i = getIntent();
        String s_intentresponse = i.getStringExtra("name");

        String s_url = "https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member?name="+ s_intentresponse;

        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        RequestQueue requestQueue = volleySingleton.getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                s_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONService pj = new JSONService(response);
                        pj.parseJSON();
                        tv_id.setText(pj.ids[0]);
                        tv_firstname.setText(pj.names[0]);
                        tv_emailid.setText(pj.emails[0]);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_FetchProfile.this, "Please check your internet connection!!!", Toast.LENGTH_SHORT).show();
                    }
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
        requestQueue.add(stringRequest);

    }
}
