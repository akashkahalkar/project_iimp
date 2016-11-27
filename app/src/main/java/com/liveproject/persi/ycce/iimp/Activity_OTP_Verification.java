package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Tiger on 07-09-2016.
 */
public class Activity_OTP_Verification extends AppCompatActivity {

    Button btn_verify;
    EditText et_otp;
    String mobileno, otp, URL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //HINT : INITIALIZE THE DATABASE.
        DatabaseService.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification);

        btn_verify = (Button) findViewById(R.id.otp_btn_verify);
        et_otp = (EditText) findViewById(R.id.otp_et_otp);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    otp = et_otp.getText().toString();

                //HINT : VALDIATE OTP.
                if (otp.isEmpty())
                    Toast.makeText(Activity_OTP_Verification.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                else {

                    //HINT : DATABASE FETCHING FROM LOGIN TABLE.
                    mobileno = DatabaseService.fetchMobileNo();

                    URL = Constants.SITE_URL + Constants.OTP_URL + "?phone=" + mobileno + "&opt=" + otp + "&isAlready=true";

                    VolleySingleton volleySingleton = VolleySingleton.getInstance();
                    RequestQueue requestQueue = volleySingleton.getRequestQueue();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(Activity_OTP_Verification.this, response, Toast.LENGTH_SHORT).show();
                                    if (response.equals("\"valid\"")) {

                                        // HINT : DATABASE ENTRY OF STATUS VERIFIED.
                                        DatabaseService.statusUpdate();

                                        Intent i = new Intent("com.liveproject.persi.ycce.iimp.HOME_PAGE");
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(Activity_OTP_Verification.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Activity_OTP_Verification.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(Activity_OTP_Verification.this, "Please check your connectivity!!", Toast.LENGTH_SHORT).show();
                                }
                            });

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
                    requestQueue.add(stringRequest);

                }
            }
        });
    }
}
