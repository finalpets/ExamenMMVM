package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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


        button_userRegister_register = findViewById(R.id.button_userRegister_register);

        button_userRegister_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
        setTitle("Register");
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
                company_name.trim().isEmpty() ||
                password.trim().isEmpty() ||
                password_confirmation.trim().isEmpty()
                )
        {
            Toast.makeText(this, "Fill all information", Toast.LENGTH_SHORT).show();
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