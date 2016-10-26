package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Laptop on 26-10-2016.
 */
public class Activity_GroupMembers extends AppCompatActivity {


    ListView lv;
    String passed_string;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups);

        lv = (ListView) findViewById(R.id.lv_my_groups);

        Intent i = getIntent();
        passed_string = i.getStringExtra("string");
        Toast.makeText(Activity_GroupMembers.this, passed_string, Toast.LENGTH_LONG).show();

        String URL = Constants.SITE_URL + Constants.MEMBER_URL;

        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        RequestQueue requestQueue = volleySingleton.getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Activity_GroupMembers.this, response, Toast.LENGTH_SHORT).show();
                        setview(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_GroupMembers.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(Activity_GroupMembers.this, "Please check your connectivity!!", Toast.LENGTH_SHORT).show();
                    }
                });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
        requestQueue.add(stringRequest);


    }
    private void setview(String response) {
        JSONService pj = new JSONService(response);
        pj.parseJSON();
        ListClass cl = new ListClass(this, JSONService.ids, JSONService.names, JSONService.emails);
        lv.setAdapter(cl);
        //Here, I try to make the list "listen" to clicks.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sendString = ((TextView) view.findViewWithTag("id")).getText().toString();
                Intent i = new Intent(getApplicationContext(), Activity_FetchProfile.class);
                i.putExtra("string", sendString);
                startActivity(i);
            }
        });
    }
}
