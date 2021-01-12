package com.example.parkingadmin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class monanadapter extends RecyclerView.Adapter<monanadapter.MonAnViewHolder> {
    final List<MonAn> mDataSet;
    LayoutInflater mInflater;
    Context context;
    public static ThucDonDBHelper thucdon;
    private WeakReference<Button> btnTong;
    public monanadapter(Context context, LinkedList<MonAn> mData,Button btn)
    {
        this.mDataSet=mData;
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        thucdon=new ThucDonDBHelper(context);
        this.btnTong= new WeakReference<>(btn);

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
        holder.Gia.setText(Double.toString(monan.Gia));

        Picasso.with(context)
                .load("http://10.0.2.2:8000/images/"+monan.HinhAnh)
                .placeholder(R.mipmap.ic_launcher)
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
        final Button btnAdd;

        public MonAnViewHolder(@NonNull View itemView,monanadapter mAdapter) {
            super(itemView);
            this.mAdapter=mAdapter;
            this.TenMon=itemView.findViewById(R.id.txtTenMon);
            this.Gia=itemView.findViewById(R.id.txtGia);
            this.HinhAnh=itemView.findViewById(R.id.imgFood);
            this.btnAdd=itemView.findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positison = getLayoutPosition();
                    MonAn monAn= mDataSet.get(positison);
                    ThucDon thucDon=new ThucDon(monAn.Id,monAn.TenMon,activity_menu_food.Maban,1,monAn.Gia);
                    long ThemMon=  thucdon.ThemMon(thucDon);
                    if (ThemMon>0)
                    {
                        btnAdd.setText("✓");
                        double tong =thucdon.TongTien(activity_menu_food.Maban);
                        btnTong.get().setText(Double.toString(tong));
                    }
                }
            });

        }
    }
}
