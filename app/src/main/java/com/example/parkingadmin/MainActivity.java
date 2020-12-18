package com.example.parkingadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String Ma="Ma_dangnhap";
    EditText TaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaiKhoan=findViewById(R.id.editTextAccount);
    }

    public void clickLogin(View view) {
        Intent intent=new Intent(this,MainMenu.class);
        intent.putExtra(Ma,TaiKhoan.getText().toString());
        startActivity(intent);

    }
}