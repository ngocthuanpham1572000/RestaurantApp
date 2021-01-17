package com.example.parkingadmin.asynctask;

import android.os.AsyncTask;

import com.example.parkingadmin.function.NetworkUnit;
import com.example.parkingadmin.model.ThucDon;

public class TaoChitietHDAsyncTask extends AsyncTask<ThucDon,Void,String> {
    int id;
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public TaoChitietHDAsyncTask(int id) {
        this.id=id;
    }

    @Override
    protected String doInBackground(ThucDon... thucDons) {
        return NetworkUnit.TaoChiTietHD(thucDons[0],id);
    }
}
