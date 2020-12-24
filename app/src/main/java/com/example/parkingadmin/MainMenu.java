package com.example.parkingadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    private CardView info;
    private CardView monan;
    private CardView table;
    private CardView logout;
    TextView TenDangNhap;
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
        Intent intent=getIntent();
        TenDangNhap.setText(intent.getStringExtra(MainActivity.Ma));
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

            }break;
//                Toast.makeText(this, "Thông tin", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.logout:

                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                break;


        }


    }

    public void ClickBan(View view) {

    }
}
