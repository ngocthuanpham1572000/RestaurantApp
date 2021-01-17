package com.example.parkingadmin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkingadmin.function.MD5;
import com.example.parkingadmin.R;
import com.example.parkingadmin.asynctaskloader.AdminLoader;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static String MA_DANGNHAP="Ma_dangnhap";

    EditText TaiKhoan;
    EditText MatKhau;
    LoaderManager loaderManager;
    String BamMatKhau;
    public  static  int Ma_Dang_Nhap=100;
    public static String TenNhanVien="";
    public static  int Ma_NV=0;
    public static String TaiKhoanNV="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaiKhoan=findViewById(R.id.editTextAccount);
        MatKhau=findViewById(R.id.editTextPassword);
        loaderManager= LoaderManager.getInstance(this);
    }

    public void clickLogin(View view) {
//
        loaderManager.initLoader(Ma_Dang_Nhap,null,this);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AdminLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            int o=0;
            String tt="25f9e794323b453885f5181f1b624d0b";
            BamMatKhau= MD5.md5(MatKhau.getText().toString());
            JSONObject jsonObject=new JSONObject(data);
            Log.d("Testnhanvien",jsonObject.toString());
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for (int i=0; i< jsonArray.length(); i++)
            {
                JSONObject jsonObject1=  jsonArray.getJSONObject(i);


                if(TaiKhoan.getText().toString().equals(jsonObject1.getString("TaiKhoan"))&&BamMatKhau.equals(jsonObject1.getString("MatKhau")))
                {
                    Intent intent=new Intent(this, MainMenu.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("TaiKhoan",jsonObject1.getString("TaiKhoan"));
                    bundle.putString("TenNV",jsonObject1.getString("TenNV"));
                    bundle.putString("NgaySinh",jsonObject1.getString("NgaySinh"));
                    bundle.putString("GioiTinh",jsonObject1.getString("GioiTinh"));
                    bundle.putString("MatKhau",jsonObject1.getString("MatKhau"));
                    bundle.putInt("Id",jsonObject1.getInt("id"));
                    TenNhanVien=jsonObject1.getString("TenNV");
                    Ma_NV=jsonObject1.getInt("id");
                    TaiKhoanNV=jsonObject1.getString("TaiKhoan");
                    intent.putExtra(MA_DANGNHAP,bundle);
                    startActivity(intent);
                    o++;
                    Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                }
            }
            if(o==0)
                Toast.makeText(this,"Thất bại",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}