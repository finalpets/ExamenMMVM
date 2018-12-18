package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.viewmodel.EmployeeViewModel;

public class SucursalDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_SUCURSAL_NAME =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_NAME";

    public static final String EXTRA_SUCURSAL_ADDRESS =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_ADDRESS";

    public static final String EXTRA_SUCURSAL_COLONIA =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_COLONIA";
    public static final String EXTRA_SUCURSAL_NUMEROEXTERIOR =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_NUMEROEXTERIOR";

    public static final String EXTRA_SUCURSAL_CODIGOPOSTAL =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_CODIGOPOSTAL";

    public static final String EXTRA_SUCURSAL_CIUDAD =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_CIUDAD";

    public static final String EXTRA_SUCURSAL_PAIS =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_PAIS";

    public static final String EXTRA_SUCURSAL_ID =
            "com.peterleyva.examenmvvm.activities.EXTRA_SUCURSAL_ID";


    TextView textview_sucursalDetails_name;
    TextView textview_sucursalDetails_adress;
    TextView textview_sucursalDetails_colonia;
    TextView textview_sucursalDetails_numeroExterior;
    TextView textview_sucursalDetails_postalCode;
    TextView textview_sucursalDetails_ciudad;
    TextView textview_sucursalDetails_pais;

    public final static int NEW_EMPLOYEE_REQUEST = 2;


    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detalles De Sucursal");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        textview_sucursalDetails_name = findViewById(R.id.textview_sucursalDetails_name);
        textview_sucursalDetails_adress = findViewById(R.id.textview_sucursalDetails_adress);
        textview_sucursalDetails_colonia = findViewById(R.id.textview_sucursalDetails_colonia);
        textview_sucursalDetails_numeroExterior = findViewById(R.id.textview_sucursalDetails_numeroExterior);
        textview_sucursalDetails_postalCode = findViewById(R.id.textview_sucursalDetails_postalCode);
        textview_sucursalDetails_ciudad = findViewById(R.id.textview_sucursalDetails_ciudad);
        textview_sucursalDetails_pais = findViewById(R.id.textview_sucursalDetails_pais);

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_SUCURSAL_ID)){

            textview_sucursalDetails_name.setText(intent.getStringExtra(EXTRA_SUCURSAL_NAME));
            textview_sucursalDetails_adress.setText(intent.getStringExtra(EXTRA_SUCURSAL_ADDRESS));
            textview_sucursalDetails_colonia.setText(intent.getStringExtra(EXTRA_SUCURSAL_COLONIA));
            textview_sucursalDetails_numeroExterior.setText(""+intent.getIntExtra(EXTRA_SUCURSAL_NUMEROEXTERIOR,0));
            textview_sucursalDetails_postalCode.setText(""+intent.getIntExtra(EXTRA_SUCURSAL_CODIGOPOSTAL,0));
            textview_sucursalDetails_ciudad.setText(intent.getStringExtra(EXTRA_SUCURSAL_CIUDAD));
            textview_sucursalDetails_pais.setText(intent.getStringExtra(EXTRA_SUCURSAL_PAIS));

        }


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_new_employee);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SucursalDetailsActivity.this, EmployeeRegisterActivity.class);
                startActivityForResult(intent, NEW_EMPLOYEE_REQUEST);

            }
        });




    }
}
