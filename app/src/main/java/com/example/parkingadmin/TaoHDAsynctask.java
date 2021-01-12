package com.example.parkingadmin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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
            String kq=jsonObject.getString("message");
            if(kq.equals("tao hoa don thanh cong"))
            {
                thucdon=td.DSgiohangtheoma(activity_menu_food.Maban);
                for(int i=0;i<thucdon.size();i++) {
                    new TaoChitietHDAsyncTask(Thoigian).execute(thucdon.get(i));
                }
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
