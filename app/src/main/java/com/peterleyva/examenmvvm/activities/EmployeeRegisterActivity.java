package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.viewmodel.SucursalViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRegisterActivity extends AppCompatActivity {

    EditText edittext_employeeRegister_name;
    EditText edittext_employeeRegister_rfc;
    EditText edittext_employeeRegister_puesto;
    AppCompatSpinner spinner;

    private SucursalViewModel sucursalViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Employee");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edittext_employeeRegister_name = findViewById(R.id.edittext_employeeRegister_name);
        edittext_employeeRegister_rfc = findViewById(R.id.edittext_employeeRegister_rfc);
        edittext_employeeRegister_puesto = findViewById(R.id.edittext_employeeRegister_puesto);
        spinner =findViewById(R.id.spinner_employee_sucursal);

        Button button_employeeRegister_back = findViewById(R.id.button_employeeRegister_back);

        button_employeeRegister_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        sucursalViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(List<Sucursal> sucursals) {

                ArrayList<String> items = new ArrayList<>();

                for (int x= 0;x<sucursals.size();x++)
                    items.add(sucursals.get(x).getName());
                ArrayAdapter<String > adapter = new ArrayAdapter<String >(EmployeeRegisterActivity.this, android.R.layout.simple_spinner_item,items);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);
                //spinner_promote_tool_current.setSelection(currentLevel);

                //adapter.setSucursals(sucursals);



            }
        });
    }
}
