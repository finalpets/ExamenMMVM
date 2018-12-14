package com.peterleyva.examenmvvm.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.peterleyva.examenmvvm.MainActivity;
import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.adapters.SucursalAdapter;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.viewmodel.SucursalViewModel;
import com.peterleyva.examenmvvm.viewmodel.UserViewModel;

import java.util.List;

public class AdministratorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SucursalViewModel sucursalViewModel;
    private UserViewModel userViewModel;
    View headerView;
    TextView textview_navheader_userName;
    TextView textview_navheader_companyName;
    private int userId;

    public final static int NEW_SUCURSAL_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Administrator");

        drawer = findViewById(R.id.drawer_administrator);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.nav_home);

        headerView = navigationView.getHeaderView(0);
        textview_navheader_userName = headerView.findViewById(R.id.textview_navheader_userName);
        textview_navheader_companyName = headerView.findViewById(R.id.textview_navheader_companyName);




        RecyclerView recyclerView = findViewById(R.id.recyclerview_administrator);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SucursalAdapter adapter = new SucursalAdapter();
        recyclerView.setAdapter(adapter);

        sucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.EXTRA_ID))
            userId = intent.getExtras().getInt(MainActivity.EXTRA_ID);

//        List<Sucursal> sucursals = sucursalViewModel.getAllSucursales_ByUserId(userId);
//            adapter.setSucursals(sucursals);

        sucursalViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(List<Sucursal> sucursals) {

                adapter.setSucursals(sucursals);



            }
        });

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getUserById(userId).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                textview_navheader_userName.setText(user.getName());
                textview_navheader_companyName.setText(user.getCompany_name());
            }
        });

//        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//
//                Intent intent = getIntent();
//                if(intent.hasExtra(MainActivity.EXTRA_ID)){
//                    userId = intent.getExtras().getInt(MainActivity.EXTRA_ID);
//
//                    //View inflatedView = getLayoutInflater().inflate(R.layout.nav_header,null);
//
//                    for (int x=0;x<users.size();x++){
//                        if(users.get(x).getId() == intent.getExtras().getInt(MainActivity.EXTRA_ID)){
//                            String username = users.get(x).getName();
//                            textview_navheader_userName.setText(username);
//                        }
//                    }
//
//
//
//
//                }
//
//            }
//        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_register_sucursal:
                Intent intent = new Intent(AdministratorActivity.this,NewSucursalAcivity.class);
                startActivityForResult(intent,NEW_SUCURSAL_REQUEST);
                //startActivity(intent);
                //sucursalViewModel.insert(new Sucursal("Gameloft","Madero","Nueva",1020,21396,"Mexicali","Mexico",userId));
                break;
            case R.id.nav_register_empleado:
                break;
            case R.id.nav_logout:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  NEW_SUCURSAL_REQUEST && resultCode == RESULT_OK){

//            String name = data.getStringExtra(NewSucursalAcivity.EXTRA_SUCURSAL_NAME);
//            String email = data.getStringExtra(NewSucursalAcivity.EXTRA_EMAIL);
//            String rfc = data.getStringExtra(NewSucursalAcivity.EXTRA_RFC);
//            String company_name = data.getStringExtra(NewSucursalAcivity.EXTRA_COMPANY);
//            String password = data.getStringExtra(NewSucursalAcivity.EXTRA_PASSWORD);
//            String password_confirmation = data.getStringExtra(NewSucursalAcivity.EXTRA_PASSWORD_CONFIRMATION);


            String name = data.getStringExtra(NewSucursalAcivity.EXTRA_SUCURSAL_NAME);
            String address = data.getStringExtra(NewSucursalAcivity.EXTRA_SUCURSAL_ADDRESS);
            String colonia = data.getStringExtra(NewSucursalAcivity.EXTRA_SUCURSAL_COLONIA);
            int numero_exterior = data.getIntExtra(NewSucursalAcivity.EXTRA_SUCURSAL_NUMEROEXTERIOR,0);
            int postal_code = data.getIntExtra(NewSucursalAcivity.EXTRA_SUCURSAL_CODIGOPOSTAL,0);
            String city = data.getStringExtra(NewSucursalAcivity.EXTRA_SUCURSAL_CIUDAD);
            String country = data.getStringExtra(NewSucursalAcivity.EXTRA_SUCURSAL_PAIS);

            Sucursal sucursal = new Sucursal(name,address,colonia,numero_exterior,postal_code,city,country,userId);

            sucursalViewModel.insert(sucursal);

//            User user = new User(name,email,rfc,password,company_name,password_confirmation);
//            userViewModel.insert(user);
            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "User not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
}
