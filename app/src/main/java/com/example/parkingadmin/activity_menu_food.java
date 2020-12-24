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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;



public class activity_menu_food extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    static final int MOAN_LOADER_ID = 1000;
    LoaderManager loaderManager;
    LinkedList<MonAn> MoAnList;
    RecyclerView recycler;
    monanadapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food);

        loaderManager = LoaderManager.getInstance(this);

        loaderManager.initLoader(MOAN_LOADER_ID,null,this);
    }


//    @NonNull
//
//    public Loader<String> onCreateLoader(int id) {
//        return new MonAnLoader(this);
//    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new MonAnLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        Log.d("Test_data",data.toString());
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("data");
            Log.d("TEST_LG", itemsArray.toString());
            MoAnList=new LinkedList<MonAn>();
            String TenMon=null;
            String Gia=null;
            for (int i = 0 ; i < itemsArray.length() ; i++)
            {
                JSONObject jsonObject1 = itemsArray.getJSONObject(i);
                try {
                    TenMon=jsonObject1.getString("TenMon");
                    Gia=jsonObject1.getString("Gia");
                    MonAn temp=new MonAn(TenMon,Gia,null);
                    Log.d("list_t_a",temp.TenMon + " | " + temp.Gia);
                    this.MoAnList.add(temp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                recycler=findViewById(R.id.rvDSMon);
                Log.d("Test_array",this.MoAnList.get(0).TenMon);
                mAdapter=new monanadapter(this,this.MoAnList);
                recycler.setAdapter(mAdapter);
                recycler.setLayoutManager(new LinearLayoutManager(this));
            }

        }  catch (JSONException e) {
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