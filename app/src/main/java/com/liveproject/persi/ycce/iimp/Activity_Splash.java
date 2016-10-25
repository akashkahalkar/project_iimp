package com.liveproject.persi.ycce.iimp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;

/**
 * Created by Tiger on 08-10-2016.
 */
public class Activity_Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(DatabaseService.check_validity())
                    {
                    if (DatabaseService.fetchStatus().equals(Constants.STATUS[2])) {
                        Intent intent = new Intent("com.liveproject.persi.ycce.iimp.HOME_PAGE");
                        startActivity(intent);
                    }
                    }else {

                            Intent intent = new Intent("com.liveproject.persi.ycce.iimp.FRONT_LOGIN");
                            startActivity(intent);

                    }
                }
            }
        };
     //   Intent intent = new Intent("com.liveproject.persi.ycce.iimp.FRONT_LOGIN");
     //   startActivity(intent);
        timer.start();
    }
}
