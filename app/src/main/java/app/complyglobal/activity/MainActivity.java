package app.complyglobal.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Annotation;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.complyglobal.R;
import app.complyglobal.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private Button login;

    private EditText mPinFirstDigitEditText;
    private EditText mPinSecondDigitEditText;
    private EditText mPinThirdDigitEditText;
    private EditText mPinForthDigitEditText;


    TextView button_1;
    TextView button_2;
    TextView button_3;
    TextView button_4;
    TextView button_5;
    TextView button_6;
    TextView button_7;
    TextView button_8;
    TextView button_9;
    TextView button_0;
    ImageView button_back;

    TextView forgotMPIN;

    private EditText mPinHiddenEditText;
    private Intent homepage;

    LinearLayout passwordLayout;
    Animation shakeAnimation;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.activity_loader);
        AlertDialog alert=builder.create();
        alert.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        homepage=new Intent(MainActivity.this,HomeActivity.class);



        forgotMPIN=(TextView)findViewById(R.id.forgot_mpin);
        forgotMPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                startActivity(getIntent());
            }
        });
        passwordLayout=(LinearLayout)findViewById(R.id.password_layout);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        shakeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                keyBoardEnable();
                setDefaultPinBackground(mPinFirstDigitEditText);
                setDefaultPinBackground(mPinSecondDigitEditText);
                setDefaultPinBackground(mPinThirdDigitEditText);
                setDefaultPinBackground(mPinForthDigitEditText);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if(sharedPreferences.getBoolean("isUserSaved",false)) {

            Log.i("LoginPage","Existing User");
            TextView displayUserName=(TextView)findViewById(R.id.display_user_name);
            displayUserName.setText("Hi, "+sharedPreferences.getString("firstName",null));
            LinearLayout layout_1=(LinearLayout)findViewById(R.id.first_time_login_page);
            layout_1.setVisibility(View.GONE);
            LinearLayout layout_2=(LinearLayout)findViewById(R.id.regular_time_login_page);
            layout_2.setVisibility(View.VISIBLE);
            LinearLayout layout_3=(LinearLayout)findViewById(R.id.custom_key_board);
            layout_3.setVisibility(View.VISIBLE);

            init();

            button_1 = (TextView) findViewById(R.id.button_1);
            button_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("1"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_2 = (TextView) findViewById(R.id.button_2);
            button_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("2"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_3 = (TextView) findViewById(R.id.button_3);
            button_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinFirstDigitEditText.setText(mPinHiddenEditText.getText().append("3"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_4 = (TextView) findViewById(R.id.button_4);
            button_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("4"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_5 = (TextView) findViewById(R.id.button_5);
            button_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("5"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_6 = (TextView) findViewById(R.id.button_6);
            button_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("6"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });


            button_7 = (TextView) findViewById(R.id.button_7);
            button_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("7"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });


            button_8 = (TextView) findViewById(R.id.button_8);
            button_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("8"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_9 = (TextView) findViewById(R.id.button_9);
            button_9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("9"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });


            button_0 = (TextView) findViewById(R.id.button_0);
            button_0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPinHiddenEditText.setText(mPinHiddenEditText.getText().append("0"));
                    onTextChanged(mPinHiddenEditText.getText());
                    onFocusChange(mPinHiddenEditText, true);
                }
            });

            button_back = (ImageView) findViewById(R.id.button_back);
            button_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onKey(mPinHiddenEditText, KeyEvent.KEYCODE_DEL, KeyEvent.ACTION_DOWN);
                }
            });
        }else{
            Log.i("LoginPage","New User");

            login=(Button)findViewById(R.id.login_button);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,AfterLoginFirstTimePage.class));
                    finish();
                }
            });
        }

        alert.cancel();
    }

    /**
     * Initialize EditText fields.
     */
    private void init() {
        mPinFirstDigitEditText = (EditText) findViewById(R.id.password_number_1);
        mPinFirstDigitEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPinSecondDigitEditText = (EditText) findViewById(R.id.password_number_2);
        mPinSecondDigitEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPinThirdDigitEditText = (EditText) findViewById(R.id.password_number_3);
        mPinThirdDigitEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPinForthDigitEditText = (EditText) findViewById(R.id.password_number_4);
        mPinForthDigitEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPinHiddenEditText = (EditText) findViewById(R.id.password);

    }


    public void onFocusChange(View v, boolean hasFocus) {
        Log.i("MainActivity","onFocusChange called condtion value :"+v.getId() +" hasFocus :"+hasFocus);
        final int id = v.getId();
        switch (id) {
            case R.id.password_number_1:
                if (hasFocus) {
                    Log.i("MainActivity","onFocusChange password_number_1");
                    setFocus(mPinHiddenEditText);
                }
                break;

            case R.id.password_number_2:
                if (hasFocus) {
                    Log.i("MainActivity","onFocusChange password_number_2");
                    setFocus(mPinHiddenEditText);
                }
                break;

            case R.id.password_number_3:
                if (hasFocus) {
                    Log.i("MainActivity","onFocusChange password_number_3");
                    setFocus(mPinHiddenEditText);
                }
                break;

            case R.id.password_number_4:
                if (hasFocus) {
                    Log.i("MainActivity","onFocusChange password_number_4");
                    setFocus(mPinHiddenEditText);
                }
                break;
            default:
                break;
        }
    }


    public boolean onKey(View v, int keyCode, int event) {
        Log.i("MainActivity","onKey called");
        if (event == KeyEvent.ACTION_DOWN) {
            final int id = v.getId();
            switch (id) {
                case R.id.password:
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        Log.i("MainActivity","onKey delete");
                        if (mPinHiddenEditText.getText().length() == 4) {
                            mPinForthDigitEditText.setText("");
                            setDefaultPinBackground(mPinForthDigitEditText);
                        }else if (mPinHiddenEditText.getText().length() == 3) {
                            mPinThirdDigitEditText.setText("");
                            setDefaultPinBackground(mPinThirdDigitEditText);
                        }else if (mPinHiddenEditText.getText().length() == 2) {
                            mPinSecondDigitEditText.setText("");
                            setDefaultPinBackground(mPinSecondDigitEditText);
                        }else if (mPinHiddenEditText.getText().length() == 1) {
                            mPinFirstDigitEditText.setText("");
                            setDefaultPinBackground(mPinFirstDigitEditText);
                        }
                        if (mPinHiddenEditText.length() > 0)
                            mPinHiddenEditText.setText(mPinHiddenEditText.getText().subSequence(0, mPinHiddenEditText.length() - 1));

                        return true;
                    }

                    break;

                default:
                    return false;
            }
        }

        return false;
    }




    /**
     * Sets focus on a specific EditText field.
     *
     * @param editText EditText to set focus on
     */
    public void setFocus(EditText editText) {
        Log.i("MainActivity","setFocus called condtion value :"+editText);
        if (editText == null)
            return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }


    private void setFocusedPinBackground(EditText editText) {
        setViewBackground(editText,ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes_focus));
    }

    private void setDefaultPinBackground(EditText editText) {
        setViewBackground(editText, ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes));
    }

    private void setEnteredPinBackground(EditText editText) {
        setViewBackground(editText, ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes_key_enterd));
    }




    /**
     * Sets background of the view.
     * This method varies in implementation depending on Android SDK version.
     *
     * @param view       View to which set background
     * @param background Background to set to view
     */
    @SuppressWarnings("deprecation")
    public void setViewBackground(View view, Drawable background) {
        if (view == null || background == null)
            return;
         view.setBackground(background);
    }


    public void onTextChanged(CharSequence charSequence) {
        //setDefaultPinBackground(mPinFirstDigitEditText);
        Log.i("MainActivity","Password : "+ mPinHiddenEditText.getText());
        Log.i("MainActivity","onTextChanged called condtion value :"+charSequence.length());
        if (charSequence.length() == 0) {
            setFocusedPinBackground(mPinFirstDigitEditText);
            mPinFirstDigitEditText.setText("");
        } else if (charSequence.length() == 1) {
            setFocusedPinBackground(mPinSecondDigitEditText);
            setEnteredPinBackground(mPinFirstDigitEditText);
            mPinFirstDigitEditText.setText(charSequence.charAt(0) + "");

            mPinSecondDigitEditText.setText("");
            mPinThirdDigitEditText.setText("");
            mPinForthDigitEditText.setText("");
            setDefaultPinBackground(mPinSecondDigitEditText);
            setDefaultPinBackground(mPinThirdDigitEditText);
            setDefaultPinBackground(mPinForthDigitEditText);
        } else if (charSequence.length() == 2) {
            setFocusedPinBackground(mPinThirdDigitEditText);
            setEnteredPinBackground(mPinSecondDigitEditText);
            mPinSecondDigitEditText.setText(charSequence.charAt(1) + "");

            mPinThirdDigitEditText.setText("");
            mPinForthDigitEditText.setText("");
            setDefaultPinBackground(mPinThirdDigitEditText);
            setDefaultPinBackground(mPinForthDigitEditText);
        } else if (charSequence.length() == 3) {
            setFocusedPinBackground(mPinForthDigitEditText);
            setEnteredPinBackground(mPinThirdDigitEditText);
            mPinThirdDigitEditText.setText(charSequence.charAt(2) + "");
            mPinForthDigitEditText.setText("");
            setDefaultPinBackground(mPinForthDigitEditText);
        } else if (charSequence.length() == 4) {
            mPinForthDigitEditText.setText(charSequence.charAt(3) + "");
            setEnteredPinBackground(mPinForthDigitEditText);
            if(mPinHiddenEditText.getText().toString().equals(sharedPreferences.getString("mpin",null))){
                startActivity(homepage);
                finish();
            }else{
                Log.i("M","Password is :"+sharedPreferences.getString("mpin",null));
                keyBoardDisable();
                setViewBackground(mPinFirstDigitEditText, ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes_key_wrong));
                mPinFirstDigitEditText.setText("");
                setViewBackground(mPinSecondDigitEditText, ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes_key_wrong));
                mPinSecondDigitEditText.setText("");
                setViewBackground(mPinThirdDigitEditText, ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes_key_wrong));
                mPinThirdDigitEditText.setText("");
                setViewBackground(mPinForthDigitEditText, ContextCompat.getDrawable(getApplicationContext(),R.drawable.style_password_boxes_key_wrong));
                mPinForthDigitEditText.setText("");
                mPinHiddenEditText.setText("");
                passwordLayout.startAnimation(shakeAnimation);

            }
        }
    }

    private void keyBoardEnable(){
        button_1.setEnabled(true);
        button_2.setEnabled(true);
        button_3.setEnabled(true);
        button_4.setEnabled(true);
        button_5.setEnabled(true);
        button_6.setEnabled(true);
        button_7.setEnabled(true);
        button_8.setEnabled(true);
        button_9.setEnabled(true);
        button_0.setEnabled(true);
    }

    private void keyBoardDisable(){
        button_1.setEnabled(false);
        button_2.setEnabled(false);
        button_3.setEnabled(false);
        button_4.setEnabled(false);
        button_5.setEnabled(false);
        button_6.setEnabled(false);
        button_7.setEnabled(false);
        button_8.setEnabled(false);
        button_9.setEnabled(false);
        button_0.setEnabled(false);
    }

}
