package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Laptop on 26-10-2016.
 */
public class Activity_GroupMembers extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups);

        lv = (ListView) findViewById(R.id.lv_my_groups);


        ListClass cl = new ListClass(this, JSONService.ids,JSONService.names,JSONService.emails);
        lv.setAdapter(cl);
        //Here, I try to make the list "listen" to clicks.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sendString = ((TextView)view.findViewWithTag("id")).getText().toString();
                Intent i = new Intent(getApplicationContext(),Activity_FetchProfile.class);
                i.putExtra("string",sendString);
                startActivity(i);
            }
        });

    }
}
