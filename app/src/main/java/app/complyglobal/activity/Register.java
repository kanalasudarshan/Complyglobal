package app.complyglobal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import app.complyglobal.R;
import app.complyglobal.fragment.CheckedListFragment;
import app.complyglobal.fragment.RegisterListFragment;

public class Register extends AppCompatActivity {

    LinearLayout delay_view;
    LinearLayout delay_show_more_view;
    LinearLayout due_view;
    LinearLayout due_show_more_view;
    LinearLayout completed_view;
    LinearLayout inprogress_show_more_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        delay_show_more_view=(LinearLayout)findViewById(R.id.delay_show_more);
        delay_show_more_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listPage=new Intent(Register.this,RegisterListPage.class);
                startActivity(listPage);
            }
        });
        due_show_more_view=(LinearLayout)findViewById(R.id.due_show_more);
        due_show_more_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listPage=new Intent(Register.this,RegisterListPage.class);
                startActivity(listPage);
            }
        });
        inprogress_show_more_view=(LinearLayout)findViewById(R.id.inprogress_show_more);
        inprogress_show_more_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listPage=new Intent(Register.this,RegisterListPage.class);
                startActivity(listPage);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
