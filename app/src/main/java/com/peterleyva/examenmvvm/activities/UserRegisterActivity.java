package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peterleyva.examenmvvm.R;

public class UserRegisterActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.peterleyva.examenmvvm.activities.EXTRA_NAME";

    public static final String EXTRA_EMAIL =
            "com.peterleyva.examenmvvm.activities.EXTRA_EMAIL";

    public static final String EXTRA_RFC =
            "com.peterleyva.examenmvvm.activities.EXTRA_RFC";
    public static final String EXTRA_COMPANY =
            "com.peterleyva.examenmvvm.activities.EXTRA_COMPANY";

    public static final String EXTRA_PASSWORD =
            "com.peterleyva.examenmvvm.activities.EXTRA_PASSWORD";

    public static final String EXTRA_PASSWORD_CONFIRMATION =
            "com.peterleyva.examenmvvm.activities.EXTRA_PASSWORD_CONFIRMATION";

    private EditText edittext_userRegister_name;
    private EditText edittext_userRegister_email;
    private EditText edittext_userRegister_rfc;
    private EditText edittext_userRegister_companyName;
    private EditText edittext_userRegister_password;
    private EditText edittext_userRegister_passwordConfirmation;

    private Button button_userRegister_register;
    private Button button_userRegister_back;


    private View.OnFocusChangeListener validateOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(validateFields())
                button_userRegister_register.setEnabled(true);
            else
                button_userRegister_register.setEnabled(false);
        }
    };

    private TextWatcher validateaddTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(validateFields())
                button_userRegister_register.setEnabled(true);
            else
                button_userRegister_register.setEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        edittext_userRegister_name = findViewById(R.id.edittext_userRegister_name);
        edittext_userRegister_email = findViewById(R.id.edittext_userRegister_email);
        edittext_userRegister_rfc = findViewById(R.id.edittext_userRegister_rfc);
        edittext_userRegister_companyName = findViewById(R.id.edittext_userRegister_companyName);
        edittext_userRegister_password = findViewById(R.id.edittext_userRegister_password);
        edittext_userRegister_passwordConfirmation = findViewById(R.id.edittext_userRegister_passwordConfirmation);

        edittext_userRegister_name.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_userRegister_email.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_userRegister_rfc.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_userRegister_companyName.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_userRegister_password.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_userRegister_passwordConfirmation.setOnFocusChangeListener(validateOnFocusChangeListener);


        edittext_userRegister_name.addTextChangedListener(validateaddTextChangedListener);
        edittext_userRegister_email.addTextChangedListener(validateaddTextChangedListener);
        edittext_userRegister_rfc.addTextChangedListener(validateaddTextChangedListener);
        edittext_userRegister_companyName.addTextChangedListener(validateaddTextChangedListener);
        edittext_userRegister_password.addTextChangedListener(validateaddTextChangedListener);
        edittext_userRegister_passwordConfirmation.addTextChangedListener(validateaddTextChangedListener);
        edittext_userRegister_passwordConfirmation.addTextChangedListener(validateaddTextChangedListener);


        edittext_userRegister_rfc.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

//        edittext_userRegister_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//                    // If view having focus.
//
//                } else {
////                    edittext_userRegister_name.getText().toString().isEmpty();
////                    edittext_userRegister_name.setHighlightColor(Color.RED);
//                    //edittext_userRegister_name.setBackgroundColor(Color.RED);
//                    // If view not having focus. You can validate here
//                }
//
//            }
//        });


        button_userRegister_register = findViewById(R.id.button_userRegister_register);
        button_userRegister_register.setEnabled(false);
//        button_userRegister_back = findViewById(R.id.button_userRegister_back);
//
//        button_userRegister_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        button_userRegister_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sign Up");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean validateFields(){

        String name = edittext_userRegister_name.getText().toString();
        String email = edittext_userRegister_email.getText().toString();
        String rfc = edittext_userRegister_rfc.getText().toString();
        String company_name = edittext_userRegister_companyName.getText().toString();
        String password = edittext_userRegister_password.getText().toString();
        String password_confirmation = edittext_userRegister_passwordConfirmation.getText().toString();

        if(name.trim().isEmpty() ||
                email.trim().isEmpty() ||
                rfc.trim().isEmpty() ||
                rfc.trim().length() >= 12 ||
                company_name.trim().isEmpty() ||
                password.trim().isEmpty() ||
                password_confirmation.trim().isEmpty()
                )
        {
            return false;

        }

        if(password.compareTo(password_confirmation) != 0){
            Toast.makeText(this, "password is not the same", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!email.contains("@"))
            return false;

        return true;
    }

    private void saveUser(){
        String name = edittext_userRegister_name.getText().toString();
        String email = edittext_userRegister_email.getText().toString();
        String rfc = edittext_userRegister_rfc.getText().toString();
        String company_name = edittext_userRegister_companyName.getText().toString();
        String password = edittext_userRegister_password.getText().toString();
        String password_confirmation = edittext_userRegister_passwordConfirmation.getText().toString();


        if(name.trim().isEmpty() ||
                email.trim().isEmpty() ||
                rfc.trim().isEmpty() ||
                rfc.trim().length() >= 12 ||
                company_name.trim().isEmpty() ||
                password.trim().isEmpty() ||
                password_confirmation.trim().isEmpty()
                )
        {
            Toast.makeText(this, "Fill all information", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.compareTo(password_confirmation) != 0){
            Toast.makeText(this, "password is not the same", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!email.contains("@")) {
            Toast.makeText(this, "Email not contains @", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_EMAIL,email);
        data.putExtra(EXTRA_RFC,rfc);
        data.putExtra(EXTRA_COMPANY,company_name);
        data.putExtra(EXTRA_PASSWORD,password);
        data.putExtra(EXTRA_PASSWORD_CONFIRMATION,password_confirmation);

        setResult(RESULT_OK,data);
        finish();

    }
}
