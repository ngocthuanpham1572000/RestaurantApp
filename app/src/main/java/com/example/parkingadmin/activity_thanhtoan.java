package com.example.parkingadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_thanhtoan extends AppCompatActivity {
    RecyclerView rvThanhToan;
    ThanhToanAdapter mAdapter;
    ThucDonDBHelper td=new ThucDonDBHelper(this);
    TextView txtTong;
    TextView txtNhanVien;
    TextView txtTenBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        rvThanhToan=findViewById(R.id.rvDS_thanhtoan);
        mAdapter=new ThanhToanAdapter(this);
        rvThanhToan.setAdapter(mAdapter);
        rvThanhToan.setLayoutManager(new LinearLayoutManager(this));
        txtTong=findViewById(R.id.txtTongCong_tt);
        double Tong=td.TongTien(activity_menu_food.Maban);
        txtTong.setText("Tổng: "+Double.toString(Tong));
        txtNhanVien=findViewById(R.id.txtNhanVien_tt);
      txtNhanVien.setText("Nhân viên: "+MainMenu.TenNhanVien);
        txtTenBan=findViewById(R.id.txtBan_tt);
        txtTenBan.setText("Tên bàn: "+activity_menu_food.TenBan);
    }

    public void ClickThanhToan(View view) {
          double tong=td.TongTien(activity_menu_food.Maban);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);;

        Log.d("hd",s);
        HoaDon hd=new HoaDon(activity_menu_food.Maban,MainMenu.Ma_NV,tong,s);
        new TaoHDAsynctask(this).execute(hd);
        new ClosebanAsyncTask(this).execute(activity_menu_food.Maban);
    }
}