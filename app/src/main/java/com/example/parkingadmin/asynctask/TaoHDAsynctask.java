package com.example.parkingadmin.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.parkingadmin.model.HoaDon;
import com.example.parkingadmin.function.NetworkUnit;
import com.example.parkingadmin.model.ThucDon;
import com.example.parkingadmin.function.ThucDonDBHelper;
import com.example.parkingadmin.activity.activity_menu_food;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class TaoHDAsynctask extends AsyncTask<HoaDon,Void,String> {
    Context context;
    String Thoigian;
    LinkedList<ThucDon> thucdon;
    ThucDonDBHelper td;
    public TaoHDAsynctask(Context con) {
        this.context=con;
        td=new ThucDonDBHelper(context);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            int id=jsonObject.getInt("id");
            if(id!=0)
            {
                thucdon=td.DSgiohangtheoma(activity_menu_food.Maban);
                for(int i=0;i<thucdon.size();i++) {
                    new TaoChitietHDAsyncTask(id).execute(thucdon.get(i));
                }
                Toast.makeText(context,"Thanh toán thành công",Toast.LENGTH_LONG).show();
                int xoa=td.xoaDSsaoThanhToan(activity_menu_food.Maban);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected String doInBackground(HoaDon... hoaDons) {
        Thoigian=hoaDons[0].getThoigianlap();
        return NetworkUnit.TaoHoaDon(hoaDons[0]);
    }
}
