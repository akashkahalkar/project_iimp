package com.liveproject.persi.ycce.iimp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 9/4/2016.
 */
public class Activity_FrontLogin extends AppCompatActivity{
    Button btn_New, btn_Existing, btn_Trail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //HINT : INITIALIZE THE DATABASE.
        DatabaseService.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_login);

        btn_New = (Button) findViewById(R.id.fl_btn_newuser);
        btn_Existing = (Button) findViewById(R.id.fl_btn_existing);
        btn_Trail = (Button) findViewById(R.id.fl_btn_trail);

        //if(DatabaseService.fetchStatus().equals(Constants.STATUS[2]));


        btn_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openEnterProfile = new Intent("com.liveproject.persi.ycce.iimp.NR_LOGIN");
                startActivity(openEnterProfile);
            }
        });

        btn_Existing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent openEnterProfile = new Intent("com.liveproject.persi.ycce.iimp.EX_LOGIN");
                startActivity(openEnterProfile);
            }
        });

        //Remove this button ::
        btn_Trail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trail = new Intent("com.liveproject.persi.ycce.iimp.NEW_REGISTRATION_TRAIL");
                startActivity(trail);
            }
        });
    }
}