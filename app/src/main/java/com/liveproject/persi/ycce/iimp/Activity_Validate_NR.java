package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Tiger on 07-10-2016.
 */
public class Activity_Validate_NR extends AppCompatActivity {

    Button validate;
    String mobile;
    EditText editmobile;
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //HINT : INITIALIZE THE DATABASE.
        DatabaseService.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_user);

        editmobile = (EditText) findViewById(R.id.eu_et_mobile);
        tv = (TextView) findViewById(R.id.eu_tv_timepass);

        validate = (Button) findViewById(R.id.eu_btn_existing_mobile);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = editmobile.getText().toString();

                // HINT : VALIDATION OF MOBILE NO.
                if (mobile.isEmpty())
                    Toast.makeText(Activity_Validate_NR.this, "Enter Mobile Number!!!", Toast.LENGTH_SHORT).show();

                else {

                    //HINT : DATABASE ENTRY IN LOGIN TABLE.
                    if(DatabaseService.insertLogin(mobile) == true)
                        Toast.makeText(Activity_Validate_NR.this, "Inserted in Login Table", Toast.LENGTH_SHORT).show();
                    //String phoneno = DatabaseService.fetchLogin();
                    //Toast.makeText(Activity_Validate_NR.this, phoneno , Toast.LENGTH_SHORT).show();

                    String URL = Constants.SITE_URL + Constants.OTP_URL + "?phone=" + mobile;

                    VolleySingleton volleySingleton = VolleySingleton.getInstance();
                    RequestQueue requestQueue = volleySingleton.getRequestQueue();

                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(Activity_Validate_NR.this, response, Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Activity_Validate_NR.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(Activity_Validate_NR.this, "ERROR", Toast.LENGTH_SHORT).show();
                                }
                            });

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
                    requestQueue.add(stringRequest);

                    Intent i = new Intent("com.liveproject.persi.ycce.iimp.OTP_VERIFICATION_NR");
                    startActivity(i);
                }

                //tv.setText(mobile);
            }
        });
    }
}
