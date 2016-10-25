package com.liveproject.persi.ycce.iimp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by user on 9/4/2016.
 */
public class Activity_NewRegistration extends AppCompatActivity {

    EditText et_firstname, et_lastname, et_emailid, et_mobileno, et_dob, et_doj;
    String s_firstname, s_lastname, s_emailid, s_mobileno, s_gender, s_dob, s_doj, s_designation;

    Spinner spinner_designation;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;

    Button btn_register;

    DatePicker datePicker;
    Calendar calendar;
    TextView dateView;
    int year, month, day;


    DesignationService d = new DesignationService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.personal_info);

        et_firstname = (EditText) findViewById(R.id.pi_et_firstname);
        et_lastname = (EditText) findViewById(R.id.pi_et_lastname);
        et_emailid = (EditText) findViewById(R.id.pi_et_emailid);
        et_mobileno = (EditText) findViewById(R.id.pi_et_mobileno);
        et_dob = (EditText) findViewById(R.id.pi_et_dob);
        et_doj = (EditText) findViewById(R.id.pi_et_doj);


        s_firstname = et_firstname.getText().toString();
        s_lastname = et_lastname.getText().toString();
        s_emailid = et_emailid.getText().toString();
        s_mobileno = et_mobileno.getText().toString();


        spinner_designation= (Spinner) findViewById(R.id.pi_dd_designation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, d.getAllDesignation());
        spinner_designation.setAdapter(adapter);
        spinner_designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView designation = (TextView) view;
                s_designation = designation.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

/*
        radioGenderGroup=(RadioGroup)findViewById(R.id.pi_rg_gender);
        radioGenderButton = (RadioButton) findViewById(radioGenderGroup.getCheckedRadioButtonId());
        s_gender = radioGenderButton.getText().toString();
*/

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                showDialog(999);
            }
        });

        et_doj.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                showDialog(998);
            }
        });


        btn_register = (Button) findViewById(R.id.pi_btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            



            }
        });

    }

    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDOBListener, year, month, day);
        }
        else if (id == 998) {
            return new DatePickerDialog(this, myDOJListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDOBListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            et_dob.setText(arg3+"/"+arg2+"/"+arg1);
        }
    };
    private DatePickerDialog.OnDateSetListener myDOJListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            et_doj.setText(arg3+"/"+arg2+"/"+arg1);
        }
    };
}