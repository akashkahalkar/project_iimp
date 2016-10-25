package com.liveproject.persi.ycce.iimp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by user on 9/4/2016.
 */
public class Activity_Validate_EU extends AppCompatActivity {

    Button validate;
    String mobile;
    EditText editmobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //HINT : INITIALIZE THE DATABASE.
        DatabaseService.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_user);

        editmobile = (EditText) findViewById(R.id.eu_et_mobile);

        validate = (Button) findViewById(R.id.eu_btn_existing_mobile);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = editmobile.getText().toString();

                // HINT : VALIDATION OF MOBILE NO.
                if (mobile.isEmpty())
                    Toast.makeText(Activity_Validate_EU.this, "Enter Mobile Number!!!", Toast.LENGTH_SHORT).show();

                else {

                    //HINT : DATABASE ENTRY IN LOGIN TABLE.
                    DatabaseService.insertLogin(mobile);

                    String URL = Constants.SITE_URL + Constants.OTP_URL + "?phone=" + mobile +"&isAlready=true";

                    VolleySingleton volleySingleton = VolleySingleton.getInstance();
                    RequestQueue requestQueue = volleySingleton.getRequestQueue();

                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(Activity_Validate_EU.this, response, Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Activity_Validate_EU.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(Activity_Validate_EU.this, "ERROR", Toast.LENGTH_SHORT).show();
                                }
                            });

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
                    requestQueue.add(stringRequest);

                    Intent i = new Intent("com.liveproject.persi.ycce.iimp.OTP_VERIFICATION");
                    startActivity(i);
                }
            }
        });
    }
}