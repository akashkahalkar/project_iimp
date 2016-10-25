package com.liveproject.persi.ycce.iimp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tiger on 28-08-2016.
 */
public class Activity_FetchProfile extends AppCompatActivity {
    JSONService jser = new JSONService();
    JSONArray jarray = new JSONArray();
    Member m = new Member();
    MemberService ms = new MemberService();
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    TextView firstname, emailid, id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch_profile);


        /*



        // Here, the async task trial will be carried out.


        //new JsonTask().execute(Constants.SiteURL + Constants.MemberURL);
        new JsonTask().execute("https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member?name=Pat");
        //  jarray = jser.getJSON(Constants.SiteURL+Constants.MemberURL);
        // m = ms.fetchUserData(jarray);


        */


        //Here the volley trail will be carried out.

        firstname = (TextView) findViewById(R.id.fp_tv_fetchusername);


        volleySingleton = VolleySingleton.getInstance();
        Toast.makeText(Activity_FetchProfile.this, "VolleySingleton instance created", Toast.LENGTH_SHORT).show();
        requestQueue = volleySingleton.getRequestQueue();
        Toast.makeText(Activity_FetchProfile.this, "Request added to Queue", Toast.LENGTH_SHORT).show();


       /*  Using jsonarrayrequest(a,b,c,d);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(Activity_FetchProfile.this, "Got Response", Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            m = ms.fetchUserData(jsonarray);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        try {
                            JSONObject json_data = response.getJSONObject(0);
                            Toast.makeText(Activity_FetchProfile.this,json_data.toString() , Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        firstname.setText(m.getUsername().toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        firstname.setText("Error");


                    }
                });



        requestQueue.add(request);

*/
        // firstname.setText(m.getUsername());



        /*

        Json Array request(a,b,c);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(Activity_FetchProfile.this, "Got Response", Toast.LENGTH_SHORT).show();

                   /*     try {
                            JSONArray jsonarray = new JSONArray(response);
                            m = ms.fetchUserData(jsonarray);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        try {
                            JSONObject json_data = response.getJSONObject(0);
                            Toast.makeText(Activity_FetchProfile.this, json_data.toString(), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        firstname.setText(m.getUsername().toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        firstname.setText("Error:"+error.getMessage()+"!!!");

                        Toast.makeText(Activity_FetchProfile.this,error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

        requestQueue.add(jsonArrayRequest);
           */

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Activity_FetchProfile.this, "INSIDE RESPONSE" + response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_FetchProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        firstname.setText(error.toString());
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
        requestQueue.add(stringRequest);


        emailid = (TextView) findViewById(R.id.fp_tv_fetchemailid);
        emailid.setText("asdfasdf");
    }



    /*
    private class JsonTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONArray jsonarray = new JSONArray(result);
                m = ms.fetchUserData(jsonarray);
            } catch (Exception e) {
                e.printStackTrace();
            }

            firstname = (TextView) findViewById(R.id.fp_tv_fetchusername);
            firstname.setText(m.getUsername());
        }
    }   */
}
