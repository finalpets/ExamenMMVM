package com.peterleyva.examenmvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SucursalAdapter extends RecyclerView.Adapter <SucursalAdapter.UserHolder>{

    private List<Sucursal> sucursals = new ArrayList<>();
    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sucursal,parent,false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        Sucursal currentSucursal = sucursals.get(position);
        holder.textview_sucursal_name.setText(currentSucursal.getName());
        holder.textview_sucursal_cityCountry.setText(currentSucursal.getCity()+" "+currentSucursal.getCountry());
    }

    @Override
    public int getItemCount() {
        return sucursals.size();
    }

    public void setSucursals(List<Sucursal> sucursals){
        this.sucursals = sucursals;
        notifyDataSetChanged();
    }
    class UserHolder extends RecyclerView.ViewHolder {

        private TextView textview_sucursal_name;
        private TextView textview_sucursal_cityCountry;

        public UserHolder(View itemView){
            super(itemView);

            textview_sucursal_name = itemView.findViewById(R.id.textview_sucursal_name);
            textview_sucursal_cityCountry = itemView.findViewById(R.id.textview_sucursal_cityCountry);
            //Todo: Number of Empoolyes

        }


    }
}
