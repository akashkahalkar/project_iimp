package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Activity_HomePage extends AppCompatActivity {

    Button user_profile, search_members,fetch_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        user_profile =(Button)findViewById(R.id.btn_userprofile);
        search_members=(Button)findViewById(R.id.btn_searchmember);
        fetch_profile = (Button) findViewById(R.id.btn_fetchprofile);


        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.liveproject.persi.ycce.iimp.USER_PROFILE");
                startActivity(i);            }
        });

        search_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.liveproject.persi.ycce.iimp.SEARCH_MEMBER");
                startActivity(i);
            }
        });

        fetch_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.liveproject.persi.ycce.iimp.FETCH_PROFILE");
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
