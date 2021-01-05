package com.example.parkingadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class activity_ban extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    static final int Table_Load = 1000;
    public  static String Setup_ban="Setupban";
    LoaderManager loaderManager;
    LinkedList<infoban> infobans;
    RecyclerView recyclerView;
    Adaptertable adaptertable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);
        loaderManager = LoaderManager.getInstance(this);
       /* loaderManager.initLoader(Table_Load,null,this);*/
        if (loaderManager.getLoader(Table_Load) == null) {

            loaderManager.initLoader(Table_Load,null,this);
        } else {
            loaderManager.restartLoader(Table_Load, null, this);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new FetchLoaderTable(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray banArray = jsonObject.getJSONArray("data");

            // Tạo nguồn dữ liệu
            this.infobans = new LinkedList<infoban>();
            String Tenban = null;
            int SoNguoi = 0;
            int SoGhe = 0;
            int Id=0;
            int TrangThai=1;
            String vitri=null;
            for(int i = 0 ; i < banArray.length() ; i++){
                JSONObject jsonObject1 = banArray.getJSONObject(i);
                try{
                    Id=jsonObject1.getInt("id");
                    Tenban = jsonObject1.getString("TenBan");
                    SoNguoi = jsonObject1.getInt("SoNguoi");
                    SoGhe = jsonObject1.getInt("SoGhe");
                    vitri=jsonObject1.getString("ViTri");
                    TrangThai=jsonObject1.getInt("TrangThai");
                    infoban temp = new infoban(Id,vitri,Tenban,SoNguoi,SoGhe,TrangThai);
                    this.infobans.add(temp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            //Thiết lập Adapter và LayoutManager cho RecyclerView
            recyclerView = findViewById(R.id.recycviewban);

            adaptertable = new Adaptertable(this, this.infobans);

            recyclerView.setAdapter(adaptertable);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        //if(id == R.id.acction_settings)
        return super.onOptionsItemSelected(item);
    }
}