package app.complyglobal.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.complyglobal.R;
import app.complyglobal.utils.Constants;

public class Splashscreen extends AppCompatActivity {

    private static int SCREEN_WAIT_TIME=1000;;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        sharedPreferences=getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginpage=new Intent(Splashscreen.this,MainActivity.class);
                startActivity(loginpage);
                finish();
            }
        },SCREEN_WAIT_TIME);
    }
}
