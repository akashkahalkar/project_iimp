package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Tiger on 11-08-2016.
 */
public class Activity_UserProfile extends AppCompatActivity {

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    String s_id;

    Member m1 = new Member();
    MemberService ms = new MemberService();
    TextView id, firstname, mobileno, emailid, designation;
    Button edit_profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Used getUser Function of MemberService class.

       // m1 = ms.getUser();




        //#DYNAMIC
        //SQLite fetch
        m1 = DatabaseService.getMember(1);
        //Cloud fetch
        //Fetch id from sqlite for sending to cloud.
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://ycc-developer-edition.ap2.force.com/member/services/apexrest/member?id=" + s_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    //Add parsing logic
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 4, 1));
        requestQueue.add(stringRequest);



        id = (TextView) findViewById(R.id.tv_fetchid);
        firstname = (TextView) findViewById(R.id.tv_fetchusername);
        emailid = (TextView) findViewById(R.id.tv_fetchemailid);
        mobileno = (TextView) findViewById(R.id.tv_fetchmobileno);
        designation = (TextView) findViewById(R.id.tv_fetchdesignation);

        id.setText(m1.getId());
        firstname.setText(m1.getFirstname());
        emailid.setText(m1.getEmailid());
        mobileno.setText(m1.getMobileno());
        designation.setText(m1.getDesignation());

        edit_profile = (Button) findViewById(R.id.btn_editprofile);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_p = new Intent("com.liveproject.persi.ycce.iimp.EDIT_PROFILE");
                startActivity(edit_p);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            return true;
        }

        if (id == R.id.home)
            NavUtils.navigateUpFromSameTask(this);

        return super.onOptionsItemSelected(item);
    }

}
