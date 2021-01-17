package com.example.parkingadmin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.parkingadmin.R;
import com.example.parkingadmin.activity.MainActivity;
import com.example.parkingadmin.activity.activity_ban;
import com.example.parkingadmin.activity.activity_info;
import com.example.parkingadmin.activity.activity_menu_food;

import static com.example.parkingadmin.activity.MainActivity.MA_DANGNHAP;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    private CardView info;
    private CardView monan;
    private CardView table;
    private CardView logout;
    TextView TenDangNhap;
    TextView TenNV;
    Bundle bundle;
    public static String SEND_DATA="send";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        if (getSupportActionBar().isShowing()){
            getSupportActionBar().hide();
        }
        initObjects();


    }

    private void initObjects(){


        table=findViewById(R.id.ban);

        monan=findViewById(R.id.monan);
        info=findViewById(R.id.info);
        logout=findViewById(R.id.logout);

        table.setOnClickListener(this);
        monan.setOnClickListener(this);

        info.setOnClickListener(this);
        logout.setOnClickListener(this);
        TenDangNhap=findViewById(R.id.txtTaiKhoan);
        TenNV=findViewById(R.id.txt_TenNV);
        Intent intent=getIntent();
        bundle = intent.getBundleExtra(MainActivity.MA_DANGNHAP);


        TenDangNhap.setText(MainActivity.TaiKhoanNV);
        TenNV.setText(MainActivity.TenNhanVien);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ban: {
                Intent intent = new Intent(this, activity_ban.class);
                startActivity(intent);
            }break;

            case R.id.monan:
                   {
                       Intent intent = new Intent(this, activity_menu_food.class);
                       startActivity(intent);
                   }break;
            case R.id.info:
            {
                String taikhoan=bundle.getString("TaiKhoan");
                String ten=bundle.getString("TenNV");
                String ngaysinh=bundle.getString("NgaySinh");
                String gioitinh=bundle.getString("GioiTinh");
                String matkhau=bundle.getString("MatKhau");
                int id=bundle.getInt("Id");

                Intent intent=new Intent(this, activity_info.class);
                Bundle bund=new Bundle();
                bund.putString("TaiKhoan",taikhoan);
                bund.putString("TenNV",ten);
                bund.putString("NgaySinh",ngaysinh);
                bund.putString("GioiTinh",gioitinh);
                bund.putString("MatKhau",matkhau);
                bund.putInt("id",id);
                intent.putExtra(MA_DANGNHAP,bund);
                startActivity(intent);
            }break;
//                Toast.makeText(this, "Thông tin", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.logout:

                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);


        }


    }

    public void ClickBan(View view) {

    }
}
