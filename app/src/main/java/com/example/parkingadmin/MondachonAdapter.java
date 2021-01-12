package com.example.parkingadmin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class MondachonAdapter extends RecyclerView.Adapter<MondachonAdapter.MondachonHolder> {
    Context context;
    LinkedList<ThucDon> thucDons;
    ThucDonDBHelper thucDonDBHelper;
    LayoutInflater layoutInflater;
    private WeakReference<TextView> txtTong;
    public MondachonAdapter(Context context,TextView txt)
    {
        thucDonDBHelper=new ThucDonDBHelper(context);
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
        thucDons=thucDonDBHelper.DSgiohangtheoma(activity_menu_food.Maban);
        this.txtTong=new WeakReference<>(txt);
    }
    @NonNull
    @Override
    public MondachonAdapter.MondachonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=this.layoutInflater.inflate(R.layout.mondachon_list,parent,false);
        return new MondachonAdapter.MondachonHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull MondachonHolder holder, int position) {
        ThucDon td=thucDons.get(position);
        holder.txtMon.setText(td.getTenmon());
        holder.txtGia.setText(Double.toString(td.getDongia()));
        holder.txtSL.setText(Integer.toString(td.getSoluong()));
        if(td.getTrangthai()==1) {
            int white = ContextCompat.getColor(context, R.color.white);
            holder.layout.setBackgroundColor(white);
            holder.Xoa.setVisibility(View.VISIBLE);
            holder.Tang.setEnabled(true);
            holder.Giam.setEnabled(true);
        }
        else {
            int gray = ContextCompat.getColor(context, R.color.gray);
            holder.layout.setBackgroundColor(gray);
            holder.Xoa.setVisibility(View.INVISIBLE);
            holder.Tang.setEnabled(false);
            holder.Giam.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return thucDons.size();
    }

    public class MondachonHolder extends RecyclerView.ViewHolder  {
        final TextView txtMon;
        final TextView txtGia;
        final TextView txtSL;
        final Button Tang;
        final Button Giam;
        final MondachonAdapter mondachonAdapter;
        final ImageButton Xoa;
        final RelativeLayout layout;
        public MondachonHolder(@NonNull View itemView ,MondachonAdapter mAdapter) {
            super(itemView);
            txtMon=itemView.findViewById(R.id.txtTenMon_mdc);
            txtGia=itemView.findViewById(R.id.txtGia_mdc);
            txtSL=itemView.findViewById(R.id.txtSoLuong_mdc);
            Tang=itemView.findViewById(R.id.btnTang);
            Giam=itemView.findViewById(R.id.btnGiam);
            layout=itemView.findViewById(R.id.layout_mdc_item);
            Xoa=itemView.findViewById(R.id.imgHuy);
            this.mondachonAdapter=mAdapter;
            Tang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positison = getLayoutPosition();
                    ThucDon td=thucDons.get(positison);
                    long kqTang=  thucDonDBHelper.TangSoLuong(td);
                  /*  int i=Integer.valueOf(txtSL.getText().toString())+1;
                    Tang.setText(Integer.toString(i));*/
                    thucDons.clear();
                    thucDons=thucDonDBHelper.DSgiohangtheoma(activity_menu_food.Maban);
                    mondachonAdapter.notifyDataSetChanged();
                    setLayoutAdapter();
                }
            });
            Giam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positison = getLayoutPosition();
                    ThucDon td=thucDons.get(positison);
                    long kq=thucDonDBHelper.GiamSoLuong(td);
                   setLayoutAdapter();
                }
            });
            Xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positison = getLayoutPosition();
                    ThucDon td=thucDons.get(positison);
                    long kq=thucDonDBHelper.XoaMonAn(td);
                    setLayoutAdapter();
                }
            });

        }
        public void setLayoutAdapter()
        {
            thucDons.clear();
            thucDons=thucDonDBHelper.DSgiohangtheoma(activity_menu_food.Maban);
            mondachonAdapter.notifyDataSetChanged();
            double Tong=thucDonDBHelper.TongTien(activity_menu_food.Maban);
            txtTong.get().setText("Tổng: "+ Double.toString(Tong)+"đ");
        }
    }
}
