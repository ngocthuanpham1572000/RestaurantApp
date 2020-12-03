package com.example.parkingadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    private CardView info;
    private CardView food;
    private CardView table;
    private CardView logout;
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

        food=findViewById(R.id.food);
        info=findViewById(R.id.info);
        logout=findViewById(R.id.logout);

        table.setOnClickListener(this);
        table.setOnClickListener(this);

        info.setOnClickListener(this);
        logout.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.ban:

                Toast.makeText(this, "Bàn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.food:

                Toast.makeText(this, "Đồ ăn", Toast.LENGTH_SHORT).show();
                break;

            case R.id.info:

                Toast.makeText(this, "Thông tin", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:

                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                break;

        }


    }
}
