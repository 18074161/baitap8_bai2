package com.example.room_datbase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<User> users;
    OnCLickListner listener;

    public CustomAdapter(List<User> users, OnCLickListner listener) {
        this.users = users;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder( CustomAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.muser = user;
        holder.tvfirstName.setText(user.firstName);
        holder.tvlastName.setText(user.lastName);



    }

    public void setListChange(List<User> userschange) {
         users=userschange;
         notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvfirstName, tvlastName;
        User muser;
        public ViewHolder( View itemView) {
            super(itemView);
            tvfirstName=  itemView.findViewById(R.id.tvfirstName);
            tvlastName=  itemView.findViewById(R.id.tvLastName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(muser);
                }
            });
        }
    }
}
