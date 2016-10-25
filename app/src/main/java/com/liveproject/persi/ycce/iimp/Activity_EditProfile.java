package com.liveproject.persi.ycce.iimp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tiger on 27-08-2016.
 */
public class Activity_EditProfile extends AppCompatActivity {

    Member m1, m2;
    DesignationService d = new DesignationService();

    TextView id;
    EditText firstname, emailid, mobileno;
    Spinner designation;
    Button upload;

    String s_designation, s_username, s_emailid, s_mobileno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_profile);

    //    m1 = new Member("1", "Demo Name", "demo@demo.com", "9876543210", "Principal");

        id = (TextView) findViewById(R.id.tv_fetchid);
        id.setText(m1.getId());

        firstname = (EditText) findViewById(R.id.ep_et_username);
        firstname.setText(m1.getFirstname());


        emailid = (EditText) findViewById(R.id.ep_et_emailid);
        emailid.setText(m1.getEmailid());


        mobileno = (EditText) findViewById(R.id.ep_et_mobileno);
        mobileno.setText(m1.getMobileno());

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

        upload = (Button) findViewById(R.id.btn_upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m2 = new Member(m1);

                s_username = firstname.getText().toString();
                s_emailid = emailid.getText().toString();
                s_mobileno = mobileno.getText().toString();

                if (!s_username.isEmpty()) {

                    m2.setFirstname(s_username);
                }

                if (!s_emailid.isEmpty()) {
                    m2.setEmailid(s_emailid);
                }

                if (!s_mobileno.isEmpty()) {
                    m2.setMobileno(s_mobileno);
                }

                if (!s_designation.equals(d.s[0])) {
                    m2.setDesignation(s_designation);
                }

          /*      Context context = getApplicationContext();
                CharSequence text = s_username;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show(); */
            }
        });
    }
}