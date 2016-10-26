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
public class Activity_MyGroups extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String s[];
        String my_mobile=DatabaseService.fetchMobileNo();
        GroupClass gc[] = DatabaseService.getMyGroups(my_mobile);
        s=new String[gc.length];
        for(int i=0;i<gc.length;i++)
        {
            s[i] = gc[i].getGName();
        }

        //Toast.makeText(Activity_MyGroups.this, s[0], Toast.LENGTH_LONG).show();

        lv = (ListView) findViewById(R.id.lv_my_groups);


        GroupListClass cl = new GroupListClass(this, s);
        lv.setAdapter(cl);
        //Here, I try to make the list "listen" to clicks.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String group_Name = ((TextView)view.findViewWithTag("id")).getText().toString();
                String group_Id = DatabaseService.fetchgidbygname(group_Name);
                Intent i = new Intent(getApplicationContext(),Activity_GroupMembers.class);
                i.putExtra("string",group_Id);
                startActivity(i);
            }
        });

    }

}
