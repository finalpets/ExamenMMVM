package com.peterleyva.examenmvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peterleyva.examenmvvm.R;
import com.peterleyva.examenmvvm.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter <UserAdapter.UserHolder>{

    private List<User> users = new ArrayList<>();
    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item,parent,false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        User currentUser = users.get(position);
        holder.textview_name.setText(currentUser.getName());
        holder.textview_rfc.setText(currentUser.getRfc());
        holder.textview_email.setText(currentUser.getEmail());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
    class UserHolder extends RecyclerView.ViewHolder {

        private TextView textview_name;
        private TextView textview_rfc;
        private TextView textview_email;

        public UserHolder(View itemView){
            super(itemView);

            textview_name = itemView.findViewById(R.id.textview_name);
            textview_email = itemView.findViewById(R.id.textview_email);
            textview_rfc = itemView.findViewById(R.id.textview_rfc);
        }


    }
}
