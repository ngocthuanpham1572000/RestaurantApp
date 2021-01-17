package com.example.parkingadmin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkingadmin.function.MD5;
import com.example.parkingadmin.R;
import com.example.parkingadmin.asynctaskloader.MatKhauLoader;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.parkingadmin.activity.MainActivity.MA_DANGNHAP;

public class activity_changepass extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    EditText MKCu,MKMoi,MKXacNhan;
    String PSCu;
    int Id;
    Bundle bundle;
    LoaderManager loaderManager;
    public static final int DOI_MK=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        loaderManager = LoaderManager.getInstance(this);
        MKCu=findViewById(R.id.editTextPWCu);
        MKMoi=findViewById(R.id.editTextPSMoi);
        MKXacNhan=findViewById(R.id.editTextPW2);
        loaderManager=LoaderManager.getInstance(this);
        Intent intent=getIntent();
        bundle = intent.getBundleExtra(MA_DANGNHAP);
        PSCu=bundle.getString("MatKhau");
        Id=bundle.getInt("id");

    }

    public void OK(View view) {
        String mkmoi=MKMoi.getText().toString();
        String mkxacnhan=MKXacNhan.getText().toString();
        String mkcu=MKCu.getText().toString();
        if(KTMKCu(mkcu)==true && KTMKMoi(mkmoi,mkxacnhan)==true){
            String Bammk= MD5.md5(mkmoi);
            bundle.putString("MatKhau",Bammk);
            bundle.putInt("id",Id);
            if (loaderManager.getLoader(DOI_MK) == null) {
                loaderManager.initLoader(DOI_MK, bundle, this);
            } else {
                loaderManager.restartLoader(DOI_MK, bundle, this);
            }


        }
        else{
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
        }
    }



    public boolean KTMKCu(String mkcu){
        if(mkcu==""){
            Toast.makeText(this,"Vui lòng nhập mật khẩu hiện tại!",Toast.LENGTH_SHORT).show();
            return false;

        }
        String BamMatKhau=MD5.md5(mkcu);

        if(BamMatKhau.equals(PSCu)){
            return true;
        }
        else{
            Toast.makeText(this,"Mật khẩu cũ không đúng!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean KTMKMoi(String mkmoi,String mkxacnhan){
        if(mkmoi.equals("")){
            Toast.makeText(this,"Vui lòng nhập mật khẩu mới!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mkmoi.length()<8){
            Toast.makeText(this,"Mật khẩu phải nhiều hơn 8 chữ số!",Toast.LENGTH_SHORT).show();
            return false;

        }
        if(mkxacnhan==""){
            Toast.makeText(this,"Vui lòng nhập lại mật khẩu mới!",Toast.LENGTH_SHORT).show();
            return false;

        }
        if(mkmoi.equals(mkxacnhan)){
            return true;
        }
        else{
            Toast.makeText(this,"Mật khẩu không trùng khớp!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new MatKhauLoader(this,args.getInt("id"),args.getString("MatKhau"));

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String ketqua=jsonObject.getString("result");
            if(ketqua.equals("success"))
            {

                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this,"OK!",Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}