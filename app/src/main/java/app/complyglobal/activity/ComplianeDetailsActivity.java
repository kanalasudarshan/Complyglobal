package app.complyglobal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import app.complyglobal.R;

public class ComplianeDetailsActivity extends AppCompatActivity {

    private Button showMore;
    private Button saveButton;
    private ScrollView scroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliane_details);
        setTitle("Compliance details");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        saveButton=(Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Compliance saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        scroller = (ScrollView)findViewById(R.id.task_details_scroll_view);
        showMore=(Button)findViewById(R.id.show_more_button);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView view_1=(TextView)findViewById(R.id.information);
                TextView view_2=(TextView)findViewById(R.id.information_head);
                TextView view_3=(TextView)findViewById(R.id.help_text);
                TextView view_4=(TextView)findViewById(R.id.help_text_head);
                TextView view_5=(TextView)findViewById(R.id.statue_ref);
                TextView view_6=(TextView)findViewById(R.id.statue_ref_head);
                if(view_1.getVisibility()==View.GONE){
                    showMore.setText("Hide More Information");

                    view_1.setFocusable(true);
                    view_1.requestFocus();
                    scroller.fullScroll(View.FOCUS_DOWN);
                    view_1.setVisibility(View.VISIBLE);
                    view_2.setVisibility(View.VISIBLE);
                    view_3.setVisibility(View.VISIBLE);
                    view_4.setVisibility(View.VISIBLE);
                    view_5.setVisibility(View.VISIBLE);
                    view_6.setVisibility(View.VISIBLE);
                }else{
                    showMore.setText("Show More Information");
                    scroller.fullScroll(View.FOCUS_UP);
                    view_1.setVisibility(View.GONE);
                    view_2.setVisibility(View.GONE);
                    view_3.setVisibility(View.GONE);
                    view_4.setVisibility(View.GONE);
                    view_5.setVisibility(View.GONE);
                    view_6.setVisibility(View.GONE);
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
