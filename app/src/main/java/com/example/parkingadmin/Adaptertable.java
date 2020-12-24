package com.example.parkingadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class Adaptertable extends RecyclerView.Adapter<Adaptertable.InfoViewHolder>{
    final LinkedList<infoban> mdataset;
    LayoutInflater layoutInflater;

    public Adaptertable(Context context, LinkedList<infoban> mdataset) {
        this.mdataset = mdataset;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =this.layoutInflater.inflate(R.layout.ban_list,parent,false);

        return new InfoViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        infoban ban = this.mdataset.get(position);
        holder.txt_tenban.setText(ban.getTenban());
        int soghe = ban.getSoghe();
        int songuoi = ban.getSonguoi();
        String nguoi_ghe = songuoi+"/"+soghe;
        holder.txt_songuoi_ghe.setText(nguoi_ghe);
    }

    @Override
    public int getItemCount() {
        return this.mdataset.size();
    }

    public  class InfoViewHolder extends RecyclerView.ViewHolder {
        final Adaptertable mAdapTer;
        final TextView txt_tenban;
        final TextView txt_songuoi_ghe;
        public InfoViewHolder(@NonNull View itemView, Adaptertable mAdapter) {

            super(itemView);
            this.mAdapTer = mAdapter;
            this.txt_tenban = itemView.findViewById(R.id.txtTenBan);
            this.txt_songuoi_ghe = itemView.findViewById(R.id.txtSoNguoi);
        }
    }
}
