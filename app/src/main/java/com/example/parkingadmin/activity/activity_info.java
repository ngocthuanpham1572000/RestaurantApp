package com.example.parkingadmin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.parkingadmin.R;

import static com.example.parkingadmin.activity.MainActivity.MA_DANGNHAP;

public class activity_info extends AppCompatActivity {

    TextView Ten,NgaySinh, GioiTinh,TaiKhoan;
    Bundle bundle;
    public static  String SEND = "SEND";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Ten=findViewById(R.id.txt_Ten);
        NgaySinh=findViewById(R.id.txt_NgaySinh);
        GioiTinh=findViewById(R.id.txt_GioiTinh);
        TaiKhoan=findViewById(R.id.txt_Taikhoan);

        Intent intent=getIntent();
        bundle = intent.getBundleExtra(MA_DANGNHAP);
        TaiKhoan.setText(bundle.getString("TaiKhoan"));
        Ten.setText(bundle.getString("TenNV"));
        NgaySinh.setText(bundle.getString("NgaySinh"));
        GioiTinh.setText(bundle.getString("GioiTinh"));
        id=bundle.getInt("id");


    }

    public void doiMatKhau(View view) {
        String mk=bundle.getString("MatKhau");
        Intent intent=new Intent(this, activity_changepass.class);
        Bundle bund=new Bundle();
        bund.putString("MatKhau",mk);
        bund.putInt("id",id);
        intent.putExtra(MA_DANGNHAP,bund);
        startActivity(intent);

    }
}