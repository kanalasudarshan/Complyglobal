package app.complyglobal.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;

import app.complyglobal.R;

public class FilterActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private EditText dueFrom;
    private EditText dueTo;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Button applyFilter;

    private ImageView typeExpandMore;
    private ImageView typeExpandLess;
    private ImageView categoryExpandMore;
    private ImageView categoryExpandLess;
    private ImageView statusExpandMore;
    private ImageView statusExpandLess;

    private LinearLayout typeItems;
    private LinearLayout categoryItems;
    private LinearLayout statusItems;

    private RelativeLayout typeHeadding;
    private RelativeLayout categoryHeadding;
    private RelativeLayout statusHeadding;

    private Animation slide_down;
    private Animation slide_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("Filter by");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR); // current year
        mMonth = c.get(Calendar.MONTH); // current month
        mDay = c.get(Calendar.DAY_OF_MONTH); // current day


        dueFrom=(EditText)findViewById(R.id.due_from);
        dueFrom.setCursorVisible(false);
        dueFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // date picker dialog
                datePickerDialog = new DatePickerDialog(FilterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dueFrom.setText(dayOfMonth + "/"  + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        dueTo=(EditText)findViewById(R.id.due_to);
        dueTo.setCursorVisible(false);
        dueTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // date picker dialog
                datePickerDialog = new DatePickerDialog(FilterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dueTo.setText(dayOfMonth + "/"  + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        applyFilter=(Button)findViewById(R.id.apply_button);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        typeItems=(LinearLayout)findViewById(R.id.type_items);
        typeExpandMore=(ImageView)findViewById(R.id.type_expand_more);
        typeExpandLess=(ImageView)findViewById(R.id.type_expand_less);
        typeHeadding=(RelativeLayout)findViewById(R.id.type_headding);

        typeHeadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(typeItems.getVisibility()==View.GONE) {
                    //view.startAnimation(slide_down);
                    typeExpandMore.setVisibility(View.GONE);
                    typeItems.setVisibility(View.VISIBLE);
                    typeExpandLess.setVisibility(View.VISIBLE);
                    if(categoryItems.getVisibility()==View.VISIBLE)
                        categoryHeadding.callOnClick();
                    if(statusItems.getVisibility()==View.VISIBLE)
                        statusHeadding.callOnClick();
                }else{
                    //view.startAnimation(slide_up);
                    typeItems.setVisibility(View.GONE);
                    typeExpandLess.setVisibility(View.GONE);
                    typeExpandMore.setVisibility(View.VISIBLE);
                }
            }
        });


        categoryItems=(LinearLayout)findViewById(R.id.category_items);
        categoryExpandMore=(ImageView)findViewById(R.id.category_expand_more);
        categoryExpandLess=(ImageView)findViewById(R.id.category_expand_less);
        categoryHeadding=(RelativeLayout)findViewById(R.id.category_headding);
        categoryHeadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categoryItems.getVisibility()==View.GONE) {
                    //view.startAnimation(slide_down);
                    categoryExpandMore.setVisibility(View.GONE);
                    categoryItems.setVisibility(View.VISIBLE);
                    categoryExpandLess.setVisibility(View.VISIBLE);

                    if(typeItems.getVisibility()==View.VISIBLE)
                        typeHeadding.callOnClick();
                    if(statusItems.getVisibility()==View.VISIBLE)
                        statusHeadding.callOnClick();
                }else{
                    //view.startAnimation(slide_up);
                    categoryItems.setVisibility(View.GONE);
                    categoryExpandLess.setVisibility(View.GONE);
                    categoryExpandMore.setVisibility(View.VISIBLE);
                }
            }
        });



        statusItems=(LinearLayout)findViewById(R.id.status_items);
        statusExpandMore=(ImageView)findViewById(R.id.status_expand_more);
        statusExpandLess=(ImageView)findViewById(R.id.status_expand_less);
        statusHeadding=(RelativeLayout)findViewById(R.id.status_headding);
        statusHeadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statusItems.getVisibility()==View.GONE) {
                    statusItems.setVisibility(View.VISIBLE);
                    //statusItems.startAnimation(slide_down);
                    statusExpandMore.setVisibility(View.GONE);
                    statusExpandLess.setVisibility(View.VISIBLE);
                    if(typeItems.getVisibility()==View.VISIBLE)
                        typeHeadding.callOnClick();
                    if(categoryItems.getVisibility()==View.VISIBLE)
                        categoryHeadding.callOnClick();
                }else {
                    statusItems.setVisibility(View.GONE);
                    //statusItems.startAnimation(slide_up);
                    statusExpandLess.setVisibility(View.GONE);
                    statusExpandMore.setVisibility(View.VISIBLE);
                }
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
