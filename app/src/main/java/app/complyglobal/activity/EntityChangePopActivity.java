package app.complyglobal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import app.complyglobal.R;

public class EntityChangePopActivity extends AppCompatActivity {

    private Button entityChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_change_pop);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*.8),(int)(dm.heightPixels*.5));

        entityChange=(Button)findViewById(R.id.entity_change_button);

        entityChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepage=new Intent(EntityChangePopActivity.this,HomeActivity.class);
                startActivity(homepage);
                finish();
            }
        });


    }
}
