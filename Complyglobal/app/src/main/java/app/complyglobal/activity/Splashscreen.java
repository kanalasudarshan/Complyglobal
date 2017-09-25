package app.complyglobal.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.complyglobal.R;

public class Splashscreen extends AppCompatActivity {

    private static int SCREEN_WAIT_TIME=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginpage=new Intent(Splashscreen.this,MainActivity.class);
                startActivity(loginpage);
            }
        },SCREEN_WAIT_TIME);
    }
}
