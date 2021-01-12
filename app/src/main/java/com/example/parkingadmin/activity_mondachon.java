package com.example.parkingadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class activity_mondachon extends AppCompatActivity   {

    RecyclerView rvMDC;
    MondachonAdapter mAdapter;
    TextView txtTong;
    ThucDonDBHelper td=new ThucDonDBHelper(this);
   final  static  int Mon_da_chon=1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondachon);
        rvMDC=findViewById(R.id.rvMonDC);
        txtTong=findViewById(R.id.txtTong_mdc);
        mAdapter=new MondachonAdapter(this,txtTong);
        rvMDC.setAdapter(mAdapter);
        rvMDC.setLayoutManager(new LinearLayoutManager(this));
        double Tong=td.TongTien(activity_menu_food.Maban);
        txtTong.setText("Tổng: "+Double.toString(Tong));
    }


    public void ClickThanhToan(View view) {
       /* double tong=td.TongTien(activity_menu_food.Maban);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);;

        Log.d("hd",s);
        HoaDon hd=new HoaDon(activity_menu_food.Maban,MainMenu.Ma_NV,tong,s);
        new TaoHDAsynctask(this).execute(hd);

        mAdapter=new MondachonAdapter(this,txtTong);
        rvMDC.setAdapter(mAdapter);
        rvMDC.setLayoutManager(new LinearLayoutManager(this));
        new ClosebanAsyncTask(this).execute(activity_menu_food.Maban);*/
        LinkedList<ThucDon> thucDons;
        thucDons=new LinkedList<>();
        thucDons=td.DSgiohangtheoma(activity_menu_food.Maban);
        if(thucDons.size()>0) {
            if(td.KiemTraGoiMon(activity_menu_food.Maban)) {
                Intent intent = new Intent(this, activity_thanhtoan.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Vui lòng gọi món",Toast.LENGTH_SHORT).show();
            }
        }else {
            new ClosebanAsyncTask(this).execute(activity_menu_food.Maban);
        }
    }

    public void ClickGoiMon(View view) {
        long kq;
        kq=td.GoiMonAn(activity_menu_food.Maban);
        mAdapter=new MondachonAdapter(this,txtTong);
        rvMDC.setAdapter(mAdapter);
        rvMDC.setLayoutManager(new LinearLayoutManager(this));
    }

    public void ClickGoiThem(View view) {
        Intent intent =new Intent(this,activity_menu_food.class);
        startActivity(intent);
    }

}