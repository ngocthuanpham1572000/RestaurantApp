package com.example.parkingadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class Adaptertable extends RecyclerView.Adapter<Adaptertable.InfoViewHolder>{
    final LinkedList<infoban> mdataset;
    LayoutInflater layoutInflater;
    Context context;
    public  static String Setup_ban="Setupban";

    public Adaptertable(Context context, LinkedList<infoban> mdataset) {
        this.mdataset = mdataset;
        this.layoutInflater = LayoutInflater.from(context);
        this.context=context;

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
        if(ban.getTrangThai()==2)
        {
            holder.txtTrangThai.setText("Đang phục vụ");
            Picasso.with(context).load(R.drawable.red).into(holder.imgTrangThai);
        }
        else
        {
            holder.txtTrangThai.setText("Đang trống");
            Picasso.with(context).load(R.drawable.green).into(holder.imgTrangThai);
        }
        
    }

    @Override
    public int getItemCount() {
        return this.mdataset.size();
    }

    public  class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        final Adaptertable mAdapTer;
        final TextView txt_tenban;
        final TextView txt_songuoi_ghe;
        final TextView txtTrangThai;
        final ImageView imgTrangThai;
        public InfoViewHolder(@NonNull View itemView, Adaptertable mAdapter) {

            super(itemView);
            this.mAdapTer = mAdapter;
            this.txt_tenban = itemView.findViewById(R.id.txtTenBan);
            this.txt_songuoi_ghe = itemView.findViewById(R.id.txtSoNguoi);
            this.txtTrangThai=itemView.findViewById(R.id.txtTrangThai);
            this.imgTrangThai=itemView.findViewById(R.id.imgTrangThai);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            int positison = getLayoutPosition();
            Log.d("testview", String.valueOf(positison));
            infoban item=mdataset.get(positison);
            Log.d("testitem", String.valueOf(item.getTrangThai()));
            SetupBan(item);

        }
        public void SetupBan(infoban item)
        {
            if(item.getTrangThai()==1) {
                Intent intent = new Intent(context, activity_setupban.class);
                Bundle ban = new Bundle();
                ban.putInt("id", item.getId());
                ban.putString("ViTri", item.getViTri());
                ban.putInt("SoGhe", item.getSoghe());
                ban.putString("TenBan", item.getTenban());
                intent.putExtra(Setup_ban, ban);
                context.startActivity(intent);
            }
            if(item.getTrangThai()==2){
                Intent intent=new Intent(context,activity_mondachon.class);
                activity_menu_food.Maban=item.getId();
                activity_menu_food.TenBan=item.getTenban();
                context.startActivity(intent);
            }

        }
    }

}
