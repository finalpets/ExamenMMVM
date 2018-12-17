package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.viewmodel.SucursalViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRegisterActivity extends AppCompatActivity {

    public static final String EXTRA_EMPLOYEE_NAME =
            "com.peterleyva.examenmvvm.activities.EXTRA_EMPLOYEE_NAME";

    public static final String EXTRA_EMPLOYEE_RFC =
            "com.peterleyva.examenmvvm.activities.EXTRA_EMPLOYEE_RFC";

    public static final String EXTRA_EMPLOYEE_PUESTO =
            "com.peterleyva.examenmvvm.activities.EXTRA_EMPLOYEE_PUESTO";

    public static final String EXTRA_EMPLOYEE_SUCURSAL_ID =
            "com.peterleyva.examenmvvm.activities.EXTRA_EMPLOYEE_SUCURSAL_ID";

    EditText edittext_employeeRegister_name;
    EditText edittext_employeeRegister_rfc;
    EditText edittext_employeeRegister_puesto;
    AppCompatSpinner spinner;
    Button button_employeeRegister_register;
    private int sucursalId;

    private SucursalViewModel sucursalViewModel;


    private View.OnFocusChangeListener validateOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(validateFields())
                button_employeeRegister_register.setEnabled(true);
            else
                button_employeeRegister_register.setEnabled(false);
        }
    };

    private TextWatcher validateaddTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(validateFields())
                button_employeeRegister_register.setEnabled(true);
            else
                button_employeeRegister_register.setEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


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
        button_employeeRegister_register = findViewById(R.id.button_employeeRegister_register);
        button_employeeRegister_register.setEnabled(false);


        edittext_employeeRegister_name.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_employeeRegister_rfc.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_employeeRegister_puesto.setOnFocusChangeListener(validateOnFocusChangeListener);

        edittext_employeeRegister_name.addTextChangedListener(validateaddTextChangedListener);
        edittext_employeeRegister_rfc.addTextChangedListener(validateaddTextChangedListener);
        edittext_employeeRegister_puesto.addTextChangedListener(validateaddTextChangedListener);


        edittext_employeeRegister_rfc.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        button_employeeRegister_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEmployee();

            }
        });

        spinner =findViewById(R.id.spinner_employee_sucursal);

        //Button button_employeeRegister_back = findViewById(R.id.button_employeeRegister_back);

//        button_employeeRegister_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        sucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        sucursalViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(final List<Sucursal> sucursals) {

                ArrayList<String> items = new ArrayList<>();

                for (int x= 0;x<sucursals.size();x++)
                    items.add(sucursals.get(x).getName());
                ArrayAdapter<String > adapter = new ArrayAdapter<String >(EmployeeRegisterActivity.this, android.R.layout.simple_spinner_item,items);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        sucursalId = sucursals.get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //spinner_promote_tool_current.setSelection(currentLevel);

                //adapter.setSucursals(sucursals);



            }
        });
    }

    private boolean validateFields(){


        String name = edittext_employeeRegister_name.getText().toString();
        String rfc = edittext_employeeRegister_rfc.getText().toString();
        String puesto = edittext_employeeRegister_puesto.getText().toString();


        if(name.trim().isEmpty() ||
                rfc.trim().isEmpty() ||
                puesto.trim().isEmpty()
                )
        {
            return false;

        }


        return true;
    }

    public void saveEmployee(){

        String name = edittext_employeeRegister_name.getText().toString();
        String rfc = edittext_employeeRegister_rfc.getText().toString();
        String puesto = edittext_employeeRegister_puesto.getText().toString();

        if(name.trim().isEmpty() ||
                rfc.trim().isEmpty() ||
                puesto.trim().isEmpty()
                )
        {
            return;

        }

        Intent data = new Intent();
        data.putExtra(EXTRA_EMPLOYEE_NAME,name);
        data.putExtra(EXTRA_EMPLOYEE_RFC,rfc);
        data.putExtra(EXTRA_EMPLOYEE_PUESTO,puesto);
        data.putExtra(EXTRA_EMPLOYEE_SUCURSAL_ID,sucursalId);


        setResult(RESULT_OK,data);
        finish();

    }
}
