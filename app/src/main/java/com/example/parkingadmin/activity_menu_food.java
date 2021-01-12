package com.example.parkingadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;


public class activity_menu_food extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    static final int MOAN_LOADER_ID = 1000;
    LoaderManager loaderManager;
    LinkedList<MonAn> MoAnList;
    RecyclerView recycler;
    monanadapter mAdapter;
    monanadapter mTimKiem;
    EditText edSearch;
    LinkedList<MonAn> TimKiemList;
    Context context;
    ThucDonDBHelper thucdon;
    Button btnTong;
    public static int Maban;
    public static String TenBan="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food);
        thucdon=new ThucDonDBHelper(this);
        /*Intent intent = getIntent();
         Maban=intent.getIntExtra("id",0);*/
        edSearch=findViewById(R.id.editSearch);
        recycler=findViewById(R.id.rvDSMon);

        context=this;

         btnTong=findViewById(R.id.btnTongTien);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(edSearch.getText()==null)
               {
                   recycler.setAdapter(mAdapter);
                   recycler.setLayoutManager(new LinearLayoutManager(context));
               }
               else {
                   TimKiemList=new LinkedList<MonAn>();
                   for(int j=0;j<MoAnList.size();j++){
                       String text=edSearch.getText().toString();
                       if ((MoAnList.get(j).TenMon.indexOf(text))!=-1)
                           TimKiemList.add(MoAnList.get(j));
                   }
                   if (TimKiemList.size()!=0)
                   {
                       mAdapter = new monanadapter(context, TimKiemList,btnTong);
                       recycler.setAdapter(mAdapter);
                       recycler.setLayoutManager(new LinearLayoutManager(context));
                   }
               }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loaderManager = LoaderManager.getInstance(this);
        if(loaderManager.getLoader(MOAN_LOADER_ID)==null)
        loaderManager.initLoader(MOAN_LOADER_ID,null,this);
        else
            loaderManager.restartLoader(MOAN_LOADER_ID,null,this);
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
            double Gia=0;
            String HinhAnh=null;
            int Id=0;
            for (int i = 0 ; i < itemsArray.length() ; i++)
            {
                JSONObject jsonObject1 = itemsArray.getJSONObject(i);
                try {
                    TenMon=jsonObject1.getString("TenMon");
                    Gia=jsonObject1.getDouble("Gia");
                    HinhAnh=jsonObject1.getString("HinhAnh");
                    Id=jsonObject1.getInt("id");
                    MonAn temp=new MonAn(Id,TenMon,Gia,HinhAnh);
                    Log.d("list_t_a",temp.TenMon + " | " + temp.Gia);
                    this.MoAnList.add(temp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

//                Log.d("Test_array",this.MoAnList.get(0).TenMon);
                mAdapter=new monanadapter(this,this.MoAnList,btnTong);
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


    public void ClickMondachon(View view) {
        Intent intent = new Intent(this,activity_mondachon.class);
        startActivity(intent);
    }
}