package com.peterleyva.examenmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.adapters.SucursalAdapter;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.viewmodel.SucursalViewModel;

import java.util.List;

public class AdministratorActivity extends AppCompatActivity {

    private SucursalViewModel sucursalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_administrator);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SucursalAdapter adapter = new SucursalAdapter();
        recyclerView.setAdapter(adapter);
        sucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        sucursalViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(List<Sucursal> sucursals) {

                adapter.setSucursals(sucursals);


            }
        });
    }
}
