package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.peterleyva.examenmvvm.R;

public class NewSucursalAcivity extends AppCompatActivity {

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


    private View.OnFocusChangeListener validateOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(validateFields())
                button_newSucursal_register.setEnabled(true);
            else
                button_newSucursal_register.setEnabled(false);
        }
    };

    private TextWatcher validateaddTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(validateFields())
                button_newSucursal_register.setEnabled(true);
            else
                button_newSucursal_register.setEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    EditText edittext_newSucursal_name;
    EditText edittext_newSucursal_adress;
    EditText edittext_newSucursal_colonia;
    EditText edittext_newSucursal_numeroExterior;
    EditText edittext_newSucursal_postalCode;
    EditText edittext_newSucursal_ciudad;
    EditText edittext_newSucursal_pais;
    Button button_newSucursal_back;
    Button button_newSucursal_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sucursal_acivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Sucursal");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edittext_newSucursal_name = findViewById(R.id.edittext_newSucursal_name);
        edittext_newSucursal_adress = findViewById(R.id.edittext_newSucursal_adress);
        edittext_newSucursal_colonia = findViewById(R.id.edittext_newSucursal_colonia);
        edittext_newSucursal_numeroExterior = findViewById(R.id.edittext_newSucursal_numeroExterior);
        edittext_newSucursal_postalCode = findViewById(R.id.edittext_newSucursal_postalCode);
        edittext_newSucursal_ciudad = findViewById(R.id.edittext_newSucursal_ciudad);
        edittext_newSucursal_pais = findViewById(R.id.edittext_newSucursal_pais);
        //button_newSucursal_back = findViewById(R.id.button_newSucursal_back);
        button_newSucursal_register = findViewById(R.id.button_newSucursal_register);


        edittext_newSucursal_name.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_newSucursal_adress.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_newSucursal_colonia.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_newSucursal_numeroExterior.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_newSucursal_postalCode.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_newSucursal_ciudad.setOnFocusChangeListener(validateOnFocusChangeListener);
        edittext_newSucursal_pais.setOnFocusChangeListener(validateOnFocusChangeListener);

        edittext_newSucursal_name.addTextChangedListener(validateaddTextChangedListener);
        edittext_newSucursal_adress.addTextChangedListener(validateaddTextChangedListener);
        edittext_newSucursal_colonia.addTextChangedListener(validateaddTextChangedListener);
        edittext_newSucursal_numeroExterior.addTextChangedListener(validateaddTextChangedListener);
        edittext_newSucursal_postalCode.addTextChangedListener(validateaddTextChangedListener);
        edittext_newSucursal_ciudad.addTextChangedListener(validateaddTextChangedListener);
        edittext_newSucursal_pais.addTextChangedListener(validateaddTextChangedListener);



//        button_newSucursal_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        button_newSucursal_register = findViewById(R.id.button_newSucursal_register);
        button_newSucursal_register.setEnabled(false);

        button_newSucursal_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSucursal();
            }
        });

    }

    private boolean validateFields(){

        int numero_exterior = 0;
        int postal_code = 0;

        String name = edittext_newSucursal_name.getText().toString();
        String address = edittext_newSucursal_adress.getText().toString();
        String colonia = edittext_newSucursal_colonia.getText().toString();
        try{
            numero_exterior = Integer.parseInt(edittext_newSucursal_numeroExterior.getText().toString());

        }
        catch (NumberFormatException e){
            numero_exterior = 0;

            edittext_newSucursal_numeroExterior.setText("0");

        }

        try{

            postal_code = Integer.parseInt(edittext_newSucursal_postalCode.getText().toString());
        }
        catch (NumberFormatException e){

            postal_code = 0;
            edittext_newSucursal_postalCode.setText("0");
        }

        String city = edittext_newSucursal_ciudad.getText().toString();
        String country = edittext_newSucursal_pais.getText().toString();

        if(name.trim().isEmpty() ||
                numero_exterior == 0 ||
                postal_code == 0 ||
                address.trim().isEmpty() ||
                colonia.trim().isEmpty() ||
                city.trim().isEmpty() ||
                country.trim().isEmpty()
                )
        {
            return false;

        }


        return true;
    }

    private void saveSucursal(){
        String name = edittext_newSucursal_name.getText().toString();
        String address = edittext_newSucursal_adress.getText().toString();
        String colonia = edittext_newSucursal_colonia.getText().toString();
        int numero_exterior = Integer.parseInt(edittext_newSucursal_numeroExterior.getText().toString());
        int postal_code = Integer.parseInt(edittext_newSucursal_postalCode.getText().toString());
        String city = edittext_newSucursal_ciudad.getText().toString();
        String country = edittext_newSucursal_pais.getText().toString();


        if(name.trim().isEmpty() ||
                numero_exterior == 0 ||
                postal_code == 0 ||
                address.trim().isEmpty() ||
                colonia.trim().isEmpty() ||
                city.trim().isEmpty() ||
                country.trim().isEmpty()
                )
        {
            return;

        }

        Intent data = new Intent();
        data.putExtra(EXTRA_SUCURSAL_NAME,name);
        data.putExtra(EXTRA_SUCURSAL_ADDRESS,address);
        data.putExtra(EXTRA_SUCURSAL_COLONIA,colonia);
        data.putExtra(EXTRA_SUCURSAL_NUMEROEXTERIOR,numero_exterior);
        data.putExtra(EXTRA_SUCURSAL_CODIGOPOSTAL,postal_code);
        data.putExtra(EXTRA_SUCURSAL_CIUDAD,city);
        data.putExtra(EXTRA_SUCURSAL_PAIS,country);

        setResult(RESULT_OK,data);
        finish();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
