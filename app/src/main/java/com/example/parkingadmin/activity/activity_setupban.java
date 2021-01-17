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
import android.widget.TextView;

import com.example.parkingadmin.R;
import com.example.parkingadmin.activity.activity_menu_food;
import com.example.parkingadmin.adapter.Adaptertable;
import com.example.parkingadmin.asynctaskloader.SetupLoader;

import org.json.JSONException;
import org.json.JSONObject;

public class activity_setupban extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    static final int Setup_Ban = 1010;
    TextView TenBan;
    TextView ViTri;
    TextView SoGhe;
    EditText SoNguoi;
    TextView Loi;
    Bundle bundle;
    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setupban);
        loaderManager = LoaderManager.getInstance(this);
        Intent intent = getIntent();
         bundle = intent.getBundleExtra(Adaptertable.Setup_ban);
         TenBan=findViewById(R.id.txtTenBan_setup);
         ViTri=findViewById(R.id.txtViTri_setup);
         SoGhe=findViewById(R.id.txtSoGhe_setup);
         SoNguoi=findViewById(R.id.txtSoNguoi_setup);
         Loi=findViewById(R.id.txtLoi);
         TenBan.setText(bundle.getString("TenBan"));
         ViTri.setText(bundle.getString("ViTri"));
         SoGhe.setText(String.valueOf(bundle.getInt("SoGhe")));
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new SetupLoader(this,args.getInt("id"),args.getInt("SoNguoi"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {
            JSONObject jsonObject = new JSONObject(data);
            String ketqua=jsonObject.getString("result");
            if(ketqua.equals("success"))
            {
                Intent intent =new Intent(this, activity_menu_food.class);
               int maban;
                maban=bundle.getInt("id");
                /*intent.putExtra("id",maban);*/
                activity_menu_food.Maban=maban;
                activity_menu_food.TenBan=bundle.getString("TenBan");
                startActivity(intent);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void ClickMoBan(View view) {
        if(KiemTraSoNguoi(SoNguoi)) {

            bundle.putInt("SoNguoi",Integer.parseInt(SoNguoi.getText().toString()));
          /*  loaderManager.initLoader(Setup_Ban, bundle, this);*/
            /*SoNguoi.setText(String.valueOf(bundle.getInt("SoGhe")));*/
          /*  TenBan.setText(bundle.getString("SoNguoi"));
            Log.d("text123","Texy");*/

            if (loaderManager.getLoader(Setup_Ban) == null) {

                loaderManager.initLoader(Setup_Ban, bundle, this);
            } else {
                loaderManager.restartLoader(Setup_Ban, bundle, this);
            }
        }

    }
    public boolean KiemTraSoNguoi(EditText edit)
    {

        if(edit.getText()==null){
            Loi.setText("Nhập số lượng người");
            return false;
        }
        int i=Integer.parseInt(edit.getText().toString());
        if(i>Integer.parseInt(SoGhe.getText().toString())||i<1)
        {
            Loi.setText("Số người không phù hợp");
            return false;
        }
        return true;

    }


}