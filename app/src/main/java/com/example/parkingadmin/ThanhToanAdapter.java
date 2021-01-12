package com.example.parkingadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.ThanhToanHolder> {
    Context context;
    LayoutInflater layoutInflater;
    LinkedList<ThucDon> thucdon;
    ThucDonDBHelper td;
    public ThanhToanAdapter(Context context)
    {
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
        td=new ThucDonDBHelper(context);
        thucdon= td.DSgiohangtheoma(activity_menu_food.Maban);
    }
    @NonNull
    @Override
    public ThanhToanAdapter.ThanhToanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=this.layoutInflater.inflate(R.layout.thanhtoan_list_item,parent,false);
        return new ThanhToanHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhToanAdapter.ThanhToanHolder holder, int position) {
        ThucDon thucDon=thucdon.get(position);
        holder.TenMon.setText(thucDon.getTenmon());
        holder.SoLuong.setText(Integer.toString(thucDon.getSoluong()));
        holder.DonGia.setText(Double.toString(thucDon.getDongia()));
        holder.ThanhTien.setText(Double.toString(thucDon.getGiatien()));
    }

    @Override
    public int getItemCount() {
        return thucdon.size();
    }

    public class ThanhToanHolder extends RecyclerView.ViewHolder {
        final TextView TenMon;
        final TextView SoLuong;
        final TextView DonGia;
        final TextView ThanhTien;
        final ThanhToanAdapter thanhToanAdapter;
        public ThanhToanHolder(@NonNull View itemView,ThanhToanAdapter mAdapter) {
            super(itemView);
            TenMon=itemView.findViewById(R.id.txtTenMon_tt);
            SoLuong=itemView.findViewById(R.id.txtSoLuong_txt);
            DonGia=itemView.findViewById(R.id.txtDonGia_tt);
            ThanhTien=itemView.findViewById(R.id.txtThanhTien_tt);
            this.thanhToanAdapter=mAdapter;
        }
    }
}
