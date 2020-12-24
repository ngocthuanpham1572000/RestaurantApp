package com.example.parkingadmin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class monanadapter extends RecyclerView.Adapter<monanadapter.MonAnViewHolder> {
    final List<MonAn> mDataSet;
    LayoutInflater mInflater;
    Context context;
    public monanadapter(Context context, LinkedList<MonAn> mData)
    {
        this.mDataSet=mData;
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public monanadapter.MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=this.mInflater.inflate(R.layout.food_list,parent,false);
        return new MonAnViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAn monan=this.mDataSet.get(position);
        holder.TenMon.setText(monan.TenMon);
        holder.Gia.setText(monan.Gia);
        Picasso.with(context)
                .load("https://imgur.com/z5HOAvl")
                .into(holder.HinhAnh);
    }



    @Override
    public int getItemCount() {
        return this.mDataSet.size();
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder {
        final monanadapter mAdapter;
        final TextView TenMon;
        final TextView Gia;
        final ImageView HinhAnh;
        public MonAnViewHolder(@NonNull View itemView,monanadapter mAdapter) {
            super(itemView);
            this.mAdapter=mAdapter;
            this.TenMon=itemView.findViewById(R.id.txtTenMon);
            this.Gia=itemView.findViewById(R.id.txtGia);
            this.HinhAnh=itemView.findViewById(R.id.imgFood);
        }
    }
}
