package com.peterleyva.examenmvvm.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.peterleyva.examenmvvm.MainActivity;
import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.adapters.SucursalAdapter;
import com.peterleyva.examenmvvm.model.Employee;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.viewmodel.EmployeeViewModel;
import com.peterleyva.examenmvvm.viewmodel.SucursalViewModel;
import com.peterleyva.examenmvvm.viewmodel.UserViewModel;

import java.util.List;

public class AdministratorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SucursalViewModel sucursalViewModel;
    private EmployeeViewModel employeeViewModel;
    private UserViewModel userViewModel;
    View headerView;
    TextView textview_navheader_userName;
    TextView textview_navheader_companyName;
    private int userId;
    private int TOTAL_SUCURSALES =-1;

    public final static int NEW_SUCURSAL_REQUEST = 1;
    public final static int NEW_EMPLOYEE_REQUEST = 2;
    public final static int EDIT_SUCURSAL_REQUEST = 3;
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
        View contextView = findViewById(R.id.coordinator_administrator);

        Snackbar snackbar = Snackbar.make(contextView, "Login Success", Snackbar.LENGTH_SHORT);
        snackbar.show();

        navigationView.setCheckedItem(R.id.nav_home);

        headerView = navigationView.getHeaderView(0);
        textview_navheader_userName = headerView.findViewById(R.id.textview_navheader_userName);
        textview_navheader_companyName = headerView.findViewById(R.id.textview_navheader_companyName);




        RecyclerView recyclerView = findViewById(R.id.recyclerview_administrator);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SucursalAdapter adapter = new SucursalAdapter(AdministratorActivity.this);
        recyclerView.setAdapter(adapter);

        sucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.EXTRA_ID))
            userId = intent.getExtras().getInt(MainActivity.EXTRA_ID);

//        List<Sucursal> sucursals = sucursalViewModel.getAllSucursales_ByUserId(userId);
//            adapter.setSucursals(sucursals);

        sucursalViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(List<Sucursal> sucursals) {


                TOTAL_SUCURSALES = sucursals.size();
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


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                sucursalViewModel.delete(adapter.getSucursalAt(viewHolder.getAdapterPosition()));
                View contextView = findViewById(R.id.coordinator_administrator);
                Snackbar snackbar = Snackbar.make(contextView, "Delete Successfully", Snackbar.LENGTH_SHORT);
                snackbar.show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new SucursalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sucursal sucursal) {
                Intent intent = new Intent(AdministratorActivity.this,NewEditSucursalAcivity.class);
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ID,sucursal.getId());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NAME,sucursal.getName());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ADDRESS,sucursal.getAdress());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CIUDAD,sucursal.getCity());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CODIGOPOSTAL,sucursal.getPostal_codel());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_COLONIA,sucursal.getColonial());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NUMEROEXTERIOR,sucursal.getNumber());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_PAIS,sucursal.getCountry());
                startActivityForResult(intent,EDIT_SUCURSAL_REQUEST);

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
        Intent intent;
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_register_sucursal:
                    intent = new Intent(AdministratorActivity.this, NewEditSucursalAcivity.class);
                    startActivityForResult(intent, NEW_SUCURSAL_REQUEST);

                //startActivity(intent);
                //sucursalViewModel.insert(new Sucursal("Gameloft","Madero","Nueva",1020,21396,"Mexicali","Mexico",userId));
                break;
            case R.id.nav_register_empleado:
                if(TOTAL_SUCURSALES > 0) {
                    intent = new Intent(AdministratorActivity.this, EmployeeRegisterActivity.class);
                    startActivityForResult(intent, NEW_EMPLOYEE_REQUEST);
                }
                else {

                    View contextView = findViewById(R.id.coordinator_administrator);
                    Snackbar snackbar = Snackbar.make(contextView, "Create New Sucursal First", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                //startActivity(intent);
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

//            String name = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NAME);
//            String email = data.getStringExtra(NewEditSucursalAcivity.EXTRA_EMAIL);
//            String rfc = data.getStringExtra(NewEditSucursalAcivity.EXTRA_RFC);
//            String company_name = data.getStringExtra(NewEditSucursalAcivity.EXTRA_COMPANY);
//            String password = data.getStringExtra(NewEditSucursalAcivity.EXTRA_PASSWORD);
//            String password_confirmation = data.getStringExtra(NewEditSucursalAcivity.EXTRA_PASSWORD_CONFIRMATION);


            String name = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NAME);
            String address = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ADDRESS);
            String colonia = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_COLONIA);
            int numero_exterior = data.getIntExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NUMEROEXTERIOR,0);
            int postal_code = data.getIntExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CODIGOPOSTAL,0);
            String city = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CIUDAD);
            String country = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_PAIS);

            Sucursal sucursal = new Sucursal(name,address,colonia,numero_exterior,postal_code,city,country,userId);

            sucursalViewModel.insert(sucursal);

//            User user = new User(name,email,rfc,password,company_name,password_confirmation);
//            userViewModel.insert(user);
            View contextView = findViewById(R.id.coordinator_administrator);

            Snackbar snackbar = Snackbar.make(contextView, "Register Success", Snackbar.LENGTH_SHORT);
            snackbar.show();
            //Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();

        }
        else
        if(requestCode ==  NEW_EMPLOYEE_REQUEST && resultCode == RESULT_OK){

            String name = data.getStringExtra(EmployeeRegisterActivity.EXTRA_EMPLOYEE_NAME);
            String rfc = data.getStringExtra(EmployeeRegisterActivity.EXTRA_EMPLOYEE_RFC);
            String puesto = data.getStringExtra(EmployeeRegisterActivity.EXTRA_EMPLOYEE_PUESTO);
            int sucursalId = data.getIntExtra(EmployeeRegisterActivity.EXTRA_EMPLOYEE_SUCURSAL_ID,0);

            Employee employee = new Employee(sucursalId,name,rfc,puesto);
            View contextView = findViewById(R.id.coordinator_administrator);

            Snackbar snackbar = Snackbar.make(contextView, "Register Success", Snackbar.LENGTH_SHORT);
            snackbar.show();

            employeeViewModel.insert(employee);



        }
        else
        if(requestCode ==  EDIT_SUCURSAL_REQUEST && resultCode == RESULT_OK){

            int id = data.getIntExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ID, -1);

            if(id == -1){
                View contextView = findViewById(R.id.coordinator_administrator);

                Snackbar snackbar = Snackbar.make(contextView, "Sucursal Can't be updated", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            String name = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NAME);
            String address = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ADDRESS);
            String colonia = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_COLONIA);
            int numero_exterior = data.getIntExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NUMEROEXTERIOR,0);
            int postal_code = data.getIntExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CODIGOPOSTAL,0);
            String city = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CIUDAD);
            String country = data.getStringExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_PAIS);

            Sucursal sucursal = new Sucursal(name,address,colonia,numero_exterior,postal_code,city,country,userId);
            sucursal.setId(id);

            sucursalViewModel.update(sucursal);

//            User user = new User(name,email,rfc,password,company_name,password_confirmation);
//            userViewModel.insert(user);
            View contextView = findViewById(R.id.coordinator_administrator);

            Snackbar snackbar = Snackbar.make(contextView, "Sucursal Updated", Snackbar.LENGTH_SHORT);
            snackbar.show();


        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            // Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Chain together various setter methods to set the dialog characteristics
            builder.setTitle("Log Out?");
            // Add the buttons
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    finish();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            // Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.administrator_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.delete_all_sucursales:
                sucursalViewModel.deleteAllSucursales();
                View contextView = findViewById(R.id.coordinator_administrator);

                Snackbar snackbar = Snackbar.make(contextView, "Delete all Sucursales Done", Snackbar.LENGTH_SHORT);
                snackbar.show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
