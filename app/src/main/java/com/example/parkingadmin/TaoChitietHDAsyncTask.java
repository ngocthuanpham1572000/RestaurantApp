package com.example.parkingadmin;

import android.content.Context;
import android.os.AsyncTask;

public class TaoChitietHDAsyncTask extends AsyncTask<ThucDon,Void,String> {
    String Thoigian;
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public TaoChitietHDAsyncTask(String thoigian) {
        this.Thoigian=thoigian;
    }

    @Override
    protected String doInBackground(ThucDon... thucDons) {
        return NetworkUnit.TaoChiTietHD(thucDons[0],Thoigian);
    }
}
