package com.peterleyva.examenmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.peterleyva.examenmvvm.activities.AdministratorActivity;
import com.peterleyva.examenmvvm.activities.UserRegisterActivity;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.viewmodel.UserViewModel;
import com.peterleyva.examenmvvm.adapters.UserAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String PREFERENCES_REMEMBER = "PREFERENCES_REMEMBER";
    private final String PREFERENCES_EMAIL = "PREFERENCES_EMAIL";
    private final String PREFERENCES_PASSWORD = "PREFERENCES_PASSWORD";
    private boolean remember = false;

    public final static int ADD_USER_REQUEST = 1;
    public static final String EXTRA_ID =
            "com.peterleyva.examenmvvm.EXTRA_ID";
    private static final String TAG = "MainActivity";
    private UserViewModel userViewModel;
    private EditText edittext_main_email;
    private EditText edittext_main_password;

    private AppCompatCheckBox checkbox_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_main_register);
        MaterialButton button_login = findViewById(R.id.button_main_login);

        edittext_main_email = findViewById(R.id.edittext_main_email);
        edittext_main_password = findViewById(R.id.edittext_main_password);



        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userViewModel.getUserLogin(edittext_main_email.getText().toString(),edittext_main_password.getText().toString()).observe(MainActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {

                        if(user != null) {
                            Intent intent = new Intent(MainActivity.this, AdministratorActivity.class);
                            if(isRemember())
                            {
                                saveEmail(user.getEmail());
                                savePassword(user.getPassword());
                            }
                            else
                            {
                                saveEmail("");
                                savePassword("");

                                edittext_main_email.setText("");
                                edittext_main_password.setText("");
                            }
                            intent.putExtra(EXTRA_ID, user.getId());
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(MainActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        checkbox_main = findViewById(R.id.checkbox_main);
        checkbox_main.setChecked(isRemember());
        checkbox_main.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveRemember(isChecked);

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,UserRegisterActivity.class);


                startActivityForResult(intent,ADD_USER_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);



        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //update RecyclerView
                //adapter.setUsers(users); //coment users Recyclerview
               // Toast.makeText(MainActivity.this, "onChange", Toast.LENGTH_SHORT).show();
            }
        });
        if(isRemember()){
            edittext_main_email.setText(getEmail());
            edittext_main_password.setText(getPassword());
        }
    }

    public boolean isRemember(){

        SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES_REMEMBER, MODE_PRIVATE);

        remember = sharedpreferences.getBoolean(PREFERENCES_REMEMBER, false);
        return remember;
    }

    public void saveRemember(boolean isChecked){

        SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES_REMEMBER, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putBoolean(PREFERENCES_REMEMBER, isChecked);
        editor.commit();
        
        //saveEmail();
        //savePassword();

    }

    private void savePassword(String password) {
        SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES_PASSWORD, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(PREFERENCES_PASSWORD, password);
        editor.commit();
    }

    private void saveEmail(String email) {
        SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES_EMAIL, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(PREFERENCES_EMAIL, email);
        editor.commit();
    }

    public String getEmail()
    {

        SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES_EMAIL, MODE_PRIVATE);

        return sharedpreferences.getString(PREFERENCES_EMAIL,"");

    }

    public String getPassword()
    {

        SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES_PASSWORD, MODE_PRIVATE);

        return sharedpreferences.getString(PREFERENCES_PASSWORD,"");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==  ADD_USER_REQUEST && resultCode == RESULT_OK){

            String name = data.getStringExtra(UserRegisterActivity.EXTRA_NAME);
            String email = data.getStringExtra(UserRegisterActivity.EXTRA_EMAIL);
            String rfc = data.getStringExtra(UserRegisterActivity.EXTRA_RFC);
            String company_name = data.getStringExtra(UserRegisterActivity.EXTRA_COMPANY);
            String password = data.getStringExtra(UserRegisterActivity.EXTRA_PASSWORD);
            String password_confirmation = data.getStringExtra(UserRegisterActivity.EXTRA_PASSWORD_CONFIRMATION);

            User user = new User(name,email,rfc,password,company_name,password_confirmation);
            userViewModel.insert(user);
            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "User not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
