package com.peterleyva.examenmvvm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.activities.AdministratorActivity;
import com.peterleyva.examenmvvm.activities.NewEditSucursalAcivity;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SucursalAdapter extends RecyclerView.Adapter<SucursalAdapter.UserHolder> {

    private List<Sucursal> sucursals = new ArrayList<>();
    private OnItemClickListener listener;
    private Context mContext;

    public SucursalAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sucursal, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {

        Sucursal currentSucursal = sucursals.get(position);
        holder.textview_sucursal_name.setText(currentSucursal.getName());
        holder.textview_sucursal_cityCountry.setText(currentSucursal.getCity() + " " + currentSucursal.getCountry());


        holder.button_itemSucursal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,NewEditSucursalAcivity.class);
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ID,sucursals.get(position).getId());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NAME,sucursals.get(position).getName());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_ADDRESS,sucursals.get(position).getAdress());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CIUDAD,sucursals.get(position).getCity());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_CODIGOPOSTAL,sucursals.get(position).getPostal_codel());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_COLONIA,sucursals.get(position).getColonial());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_NUMEROEXTERIOR,sucursals.get(position).getNumber());
                intent.putExtra(NewEditSucursalAcivity.EXTRA_SUCURSAL_PAIS,sucursals.get(position).getCountry());
                ((AdministratorActivity) mContext).startActivityForResult(intent,AdministratorActivity.EDIT_SUCURSAL_REQUEST);
                //startActivityForResult(intent,AdministratorActivity.EDIT_SUCURSAL_REQUEST);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sucursals.size();
    }

    public void setSucursals(List<Sucursal> sucursals) {
        this.sucursals = sucursals;
        notifyDataSetChanged();
    }

    public Sucursal getSucursalAt(int positon) {
        return sucursals.get(positon);
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private TextView textview_sucursal_name;
        private TextView textview_sucursal_cityCountry;
        Button button_itemSucursal;

        public UserHolder(View itemView) {
            super(itemView);

            textview_sucursal_name = itemView.findViewById(R.id.textview_sucursal_name);
            textview_sucursal_cityCountry = itemView.findViewById(R.id.textview_sucursal_cityCountry);
            button_itemSucursal = itemView.findViewById(R.id.button_itemSucursal);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(sucursals.get(position));
                    }
                }
            });
            //Todo: Number of Empoolyes

        }


    }

    public interface OnItemClickListener {
        void onItemClick(Sucursal sucursal);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }
}
