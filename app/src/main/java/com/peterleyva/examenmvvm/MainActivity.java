package com.peterleyva.examenmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peterleyva.examenmvvm.activities.AdministratorActivity;
import com.peterleyva.examenmvvm.activities.UserRegisterActivity;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.viewmodel.UserViewModel;
import com.peterleyva.examenmvvm.adapters.UserAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static int ADD_USER_REQUEST = 1;
    public static final String EXTRA_ID =
            "com.peterleyva.examenmvvm.EXTRA_ID";
    private static final String TAG = "MainActivity";
    private UserViewModel userViewModel;
    private EditText edittext_main_email;
    private EditText edittext_main_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_main_register);
        Button button_login = findViewById(R.id.button_main_login);

        edittext_main_email = findViewById(R.id.edittext_main_email);
        edittext_main_password = findViewById(R.id.edittext_main_password);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0;x<userViewModel.getAllUsers().getValue().size();x++)
                {
                    if(userViewModel.getAllUsers().getValue().get(x).getEmail().compareToIgnoreCase(edittext_main_email.getText().toString()) == 0){
                        if(userViewModel.getAllUsers().getValue().get(x).getPassword().compareToIgnoreCase(edittext_main_password.getText().toString()) == 0) {
                            Intent intent = new Intent(MainActivity.this,AdministratorActivity.class);

                            intent.putExtra(EXTRA_ID,userViewModel.getAllUsers().getValue().get(x).getId());
                            startActivity(intent);

                            Toast.makeText(MainActivity.this, "Password correct", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        
                    }
                    else
                        Toast.makeText(MainActivity.this, "Email Incorrect", Toast.LENGTH_SHORT).show();
                }
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
                adapter.setUsers(users);
                Toast.makeText(MainActivity.this, "onChange", Toast.LENGTH_SHORT).show();
            }
        });
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
