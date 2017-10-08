package app.complyglobal.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import app.complyglobal.R;
import app.complyglobal.dao.UserDAO;
import app.complyglobal.dto.UserDTO;
import app.complyglobal.utils.Constants;

public class UserAccount extends AppCompatActivity {

    Button removeAccountButton;
    UserDAO dao;
    SharedPreferences sharedPreferences;

    ImageView editFirstName;
    ImageView editLastName;
    ImageView editMobileNumber;
    ImageView editMobilePin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences=getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);

        dao=new UserDAO(getApplicationContext());
        removeAccountButton=(Button)findViewById(R.id.remove_account_button);
        removeAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDTO dto=new UserDTO();
                dto.setUserId(1);
                dao.deleteUser(dto);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(),"User has been removed from mobile",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserAccount.this,MainActivity.class));
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
