package com.liveproject.persi.ycce.iimp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tiger on 11-08-2016.
 */
public class Activity_SearchMembers extends AppCompatActivity {

    MemberService  ms = new MemberService();

    Button search;
    EditText id, firstname, mobileno, emailid;
    Spinner designation;
    TextView url;
    EditText searchlist;

    String s_id, s_username, s_mobileno, s_emailid, s_designation;

    DesignationService d = new DesignationService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_members);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        search = (Button) findViewById(R.id.btn_search);
        id = (EditText) findViewById(R.id.et_id);
        firstname = (EditText) findViewById(R.id.et_username);
        mobileno = (EditText) findViewById(R.id.et_mobileno);
        emailid = (EditText) findViewById(R.id.et_emailid);

        designation = (Spinner) findViewById(R.id.dd_designation);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, d.getAllDesignation());

        designation.setAdapter(adapter);


        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView designation = (TextView) view;
                s_designation = designation.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s_id = id.getText().toString();
                s_username = firstname.getText().toString();
                s_mobileno = mobileno.getText().toString();
                s_emailid = emailid.getText().toString();

                String s_query = Constants.SITE_URL+Constants.MEMBER_URL+"?";

                if (!s_id.isEmpty()) {
                    s_query = s_query + "id=" + s_id;
                } else {
                    if (!s_username.isEmpty()) {
                        s_query = s_query + "firstname=" + s_username + "&";
                    }
                    if (!s_mobileno.isEmpty()) {
                        s_query = s_query + "mobileno=" + s_mobileno + "&";
                    }
                    if (!s_emailid.isEmpty()) {
                        s_query = s_query + "emailid=" + s_emailid + "&";
                    }
                    if (!s_designation.equals(d.s[0])) {
                        s_query = s_query + "designation=" + s_designation;
                    }
                }

                url = (TextView) findViewById(R.id.tv_url);
                url.setVisibility(View.VISIBLE);
                url.setText(s_query);

              //Code for Search List:
                searchlist = (EditText) findViewById(R.id.sm_et_searchlist);
                searchlist.setVisibility(View.VISIBLE);
                searchlist.setText(ms.displayMember(ms.getAll()));


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