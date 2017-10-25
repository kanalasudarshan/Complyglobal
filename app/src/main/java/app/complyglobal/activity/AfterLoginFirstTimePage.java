package app.complyglobal.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import app.complyglobal.R;
import app.complyglobal.dao.UserDAO;
import app.complyglobal.dto.UserDTO;
import app.complyglobal.utils.Constants;

public class AfterLoginFirstTimePage extends AppCompatActivity {

    private Button save;
    private EditText first_Name;
    private EditText last_Name;
    private EditText mobilePin;
    private EditText confirmMobilePin;

    private TextView firstNameHeadding;
    private TextView mobilePinHeadding;
    private TextView confirmMobilePinHeadding;

    private Boolean noErrors=true;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_first_time_page);
        sharedPreferences=getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);

        firstNameHeadding=(TextView)findViewById(R.id.first_name_text);
        mobilePinHeadding=(TextView)findViewById(R.id.mobile_pin_text);
        confirmMobilePinHeadding=(TextView)findViewById(R.id.confirm_mobile_pin_text);

        first_Name=(EditText)findViewById(R.id.first_name);
        last_Name=(EditText)findViewById(R.id.last_name);
        mobilePin=(EditText)findViewById(R.id.mobile_pin);
        confirmMobilePin=(EditText)findViewById(R.id.confirm_mobile_pin);

        save=(Button)findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noErrors=true;
                if(first_Name.getText().toString().equals("")){
                    noErrors=false;
                    firstNameHeadding.setTextColor(Color.RED);
                }

                if(mobilePin.getText().toString().equals("")){
                    noErrors=false;
                    mobilePinHeadding.setTextColor(Color.RED);
                }else if(mobilePin.getText().toString().length()!=4){
                    noErrors=false;
                    mobilePin.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "Mobile PIN should have 4 digits", Toast.LENGTH_SHORT).show();
                }else  if(confirmMobilePin.getText().toString().equals("")){
                    noErrors=false;
                    confirmMobilePinHeadding.setTextColor(Color.RED);
                }else{
                    if(!confirmMobilePin.getText().toString().equals(mobilePin.getText().toString())){
                        noErrors=false;
                        mobilePin.setText("");
                        confirmMobilePin.setText("");
                        mobilePinHeadding.setTextColor(Color.RED);
                        firstNameHeadding.setTextColor(Color.RED);
                        Toast.makeText(getApplicationContext(), "Confirm Mobile PIN not matching", Toast.LENGTH_SHORT).show();
                    }
                }
                if(noErrors) {
                    UserDAO dao = new UserDAO(getApplicationContext());
                    UserDTO userDTO = dao.getUser(1);

                    UserDTO dto = new UserDTO();
                    dto.setUserId(1);
                    dto.setUserName("admin@complyglobal.com");
                    dto.setPassword("123456");
                    dto.setFirstName(first_Name.getText().toString());
                    dto.setLastName(last_Name.getText().toString());
                    dto.setMobilePin(Integer.parseInt(mobilePin.getText().toString()));

                    if (userDTO == null) {
                        dao.addUser(dto);
                    } else {
                        dao.updateUser(dto);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("firstName", first_Name.getText().toString());
                    editor.putString("mpin", mobilePin.getText().toString());
                    editor.putBoolean("isUserSaved", true);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "User information saved", Toast.LENGTH_SHORT).show();
                    Log.i("ÄfterLogin", "isUserSaved :" + sharedPreferences.getBoolean("isUserSaved", false));
                    startActivity(new Intent(AfterLoginFirstTimePage.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
