package com.example.parkingadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextSwitcher;

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
    EditText edSearch;
    LinkedList<infoban> TimKiemList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);
        edSearch=findViewById(R.id.edSearch_ban);
        context=this;
        loaderManager = LoaderManager.getInstance(this);
       /* loaderManager.initLoader(Table_Load,null,this);*/
        if (loaderManager.getLoader(Table_Load) == null) {

            loaderManager.initLoader(Table_Load,null,this);
        } else {
            loaderManager.restartLoader(Table_Load, null, this);
        }
        edSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edSearch.getText()==null)
                {
                    recyclerView.setAdapter(adaptertable);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else {
                    TimKiemList=new LinkedList<infoban>();
                    for(int j=0;j<infobans.size();j++){
                        String text=edSearch.getText().toString();
                        if ((infobans.get(j).getTenban().indexOf(text))!=-1)
                            TimKiemList.add(infobans.get(j));
                    }
                    if (TimKiemList.size()!=0)
                    {
                        adaptertable = new Adaptertable(context, TimKiemList);
                        recyclerView.setAdapter(adaptertable);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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